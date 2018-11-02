var html = '<div class="card card_div_21" v-for="site in sites">\n' +
    '                <div class="card_div_22">\n' +
    '                    <div class="card_div_23">\n' +
    '                        <div class="card_div_24">\n' +
    '                           <span>\n' +
    '                                <img src="<%=path%>/static/img/border.png" alt="" class="card_div_25">\n' +
    '                            </span>\n' +
    '                            <div class="card_div_26">\n' +
    '                                已合作用户\n' +
    '                            </div>\n' +
    '                        </div>\n' +
    '                        <div class="card_div_27">\n' +
    '                            <img v-bind:src="site.kehuHeadImg" alt="" class="card_div_28">\n' +
    '                        </div>\n' +
    '                    </div>\n' +
    '                    <div class="card_div_29">\n' +
    '                        <div class="card_div_30">\n' +
    '                            <span class="card_div_31">{{site.kehuName}}</span>\n' +
    '                            <span style="float: left;">\n' +
    '                                <img src="<%=path%>/static/img/name_blue.png" alt="" class="card_div_32">\n' +
    '                            </span>\n' +
    '                            <span class="card_div_33">{{site.kehuAge}}岁</span>\n' +
    '\n' +
    '                        </div>\n' +
    '                        <div class="card_div_34">\n' +
    '                            <span style="float: left;">\n' +
    '                                <img src="<%=path%>/static/img/home.png" alt="" class="card_div_35">\n' +
    '                            </span>\n' +
    '                            <p v-if="site.kehuAddress.length>11" style="font-size: 14px;text-align: left;">{{site.kehuAddress}}</p>\n' +
    '                            <p v-else class="card_div_36">{{site.kehuAddress}}</p>\n' +
    '                        </div>\n' +
    '                        <div class="card_div_34">\n' +
    '                           <span style="float: left;">\n' +
    '                                <img src="<%=path%>/static/img/pone_grey.png" alt="" class="card_div_35">\n' +
    '                            </span>\n' +
    '                            <span class="card_div_36">{{site.kehuPhone}}</span>\n' +
    '                        </div>\n' +
    '                        <div class="card_div_34">\n' +
    '                           <span style="float: left;">\n' +
    '                                <img src="<%=path%>/static/img/time_grey.png" alt="" class="card_div_35">\n' +
    '                            </span>\n' +
    '                            <span class="card_div_36">上次查询:{{site.upTime | time}}</span>\n' +
    '                        </div>\n' +
    '                        <div class="card_div_34">\n' +
    '                           <span style="float: left;">\n' +
    '                                <img src="<%=path%>/static/img/bz.png" alt="" class="card_div_35">\n' +
    '                            </span>\n' +
    '                            <span v-if="site.remark.length>6" class="card_div_36">{{site.remark.substring(0,6)}}...</span>\n' +
    '                            <span v-else class="card_div_36">{{site.remark}}</span>\n' +
    '                        </div>\n' +
    '                    </div>\n' +
    '                </div>\n' +
    '                <div class="card_div_37">\n' +
    '                    <span>\n' +
    '                        <img src="<%=path%>/static/img/xin.png" alt="" class="card_div_38">\n' +
    '                    </span>\n' +
    '                    <span>\n' +
    '                        <img src="<%=path%>/static/img/ideacard.png" alt="" class="card_div_39">\n' +
    '                    </span>\n' +
    '                    <span class="card_div_40">{{site.kehuIdcard}}</span>\n' +
    '                </div>\n' +
    '            </div>';
var phone = "${userPCVo.phone}";
if(!phone){
    location.href = "/pcUser/exit";
}
var total = "";
refush(1);
function refush(val) {
    $.post(
        "/keHu/keHuList",
        {
            "pageSize":9,
            "pageIndex":val
        },function (msg) {
            total = msf.total;
            Vue.set(this.all,'0','2')
        }
    );
}
var vm = new Vue({
    el: '.page-bar',
    data: {
        all: '', //总页数
        cur: 1//当前页码
    },
    watch: {
        cur: function(oldValue , newValue){
            console.log(arguments);
        }
    },
    methods: {
        btnClick: function(data){//页码点击事件
            if(data != this.cur){
                this.cur = data
            }
        },
        pageClick: function(){
            console.log('现在在'+this.cur+'页');
            refush(this.cur);
        }
    },
    computed: {
        indexs: function(){
            var left = 1;
            var right = this.all;
            var ar = [];
            if(this.all>= 5){
                if(this.cur > 3 && this.cur < this.all-2){
                    left = this.cur - 2
                    right = this.cur + 2
                }else{
                    if(this.cur<=3){
                        left = 1
                        right = 5
                    }else{
                        right = this.all
                        left = this.all -4
                    }
                }
            }
            while (left <= right){
                ar.push(left)
                left ++
            }
            return ar
        }
    }
})
var mphone = phone.substr(0, 3) + '****' + phone.substr(7);
$('#phone').html(mphone)
$(".card").mousemove(function () {
    $(this).addClass("active_card").siblings().removeClass("active_card");
});
$(".cardlist").mouseleave(function () {
    $(".card").removeClass("active_card");
});
$("#addkehu").click(function () {
    var kehuPhone = $("#call_num").val();
    var kehuIdcard = $("#idcard").val();
    var kehuName = $("#name").val();
    if(!kehuName){
        alert("姓名不能为空!");
        return;
    }
    if(!kehuIdcard){
        alert("身份证号不能为空!");
        return;
    }
    if(!kehuPhone){
        alert("手机号不能为空!");
        return;
    }
    $.post(
        "/keHu/keHuAddSave",
        {
            "kehuPhone":kehuPhone,
            "kehuIdcard":kehuIdcard,
            "kehuName":kehuName
        },function (msg) {
            window.location.reload();
        },"json"

    );
});
