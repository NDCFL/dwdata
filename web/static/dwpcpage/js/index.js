$(function(){
    $(".sur_li1").hover(function(){
        $(".ul_down").toggle();
    })

    $(".star").click(function(){
        if(!$(this).hasClass("ltb_star")){
            $(this).removeClass("ltb_star1");
            $(this).addClass("ltb_star");
        }else{
            $(this).removeClass("ltb_star");
            $(this).addClass("ltb_star1");

        }
    })

    $(".iii").click(function(){
        if(!$(this).hasClass("ltlt_i")){
            $(this).removeClass("ltlt_i1");
            $(this).addClass("ltlt_i");
        }else{
            $(this).removeClass("ltlt_i");
            $(this).addClass("ltlt_i1");

        }
    })

    $(".msl_span1 a").click(function(){
        if($(this).hasClass("demo")){
            $(this).parents(".li").siblings(".li").find(".msl_span1").children().removeClass("demo");
        }else{
            $(this).addClass("demo");
            $(this).parents(".li").siblings(".li").find(".msl_span1").children().removeClass("demo");
        }
    })
    $(".dwinput input").click(function () {
        if($(this).hasClass("dwinputactive")){
            $(this).parents(".dwinput").siblings().children().removeClass("dwinputactive");
        }else{
            $(this).addClass("dwinputactive");
            $(this).parents(".dwinput").siblings().children().removeClass("dwinputactive");
        }
    });

})
