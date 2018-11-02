$(function(){
	$(".sc_ul_right>li:first").hover(function(){
		$(".ul_down").toggle();
	})
    $(".ns_ul li").click(function(){
    	$(this).css({"color":"#0583CE","background-color":"#fff"});
    	$(this).children().show();
    	$(this).siblings().css({"color":"#444","background-color":"#ddd"});
    	$(this).siblings().children().hide();
    	var index = $(this).index();
    	console.log(index)
    	$(".tab_box").hide().eq(index).show();
    	
    })
})
