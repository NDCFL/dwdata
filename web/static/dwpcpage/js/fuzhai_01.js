$(function(){
	$(".sc_ul_right>li:first").hover(function(){
		$(".ul_down").toggle();
	})
	

    $(".ns_ul li").click(function(){
    	$(this).css({"color":"#0583CE","background-color":"#fff"});
    	$(this).children().show();
    	$(this).siblings().css({"color":"#444","background-color":"#f5f5f5"});
    	$(this).siblings().children().hide();
    	var index = $(this).index();
    	console.log(index)
    	$(".tab_box").hide().eq(index).show();
    	
    })
    
    $(".ns_ul_ul li").click(function(){
    	$(this).css("background-color","#fff");
    	$(this).siblings().css("background-color","#E4E4E4");
    	var index = $(this).index();
    	console.log(index)
        highSearch();
    	$(".tab_box_box").hide().eq(index).show();
    	
    })
    
    $(".table33").click(function(){
    	
    	var $a = $(this).find(".table_i")
    	if(!$a.hasClass("table_ii")){
    		$a.addClass("table_ii");
    		$a.removeClass("table_iii");
    		$(".table22").hide();
    	}else{
    		$a.removeClass("table_ii");
    		$a.addClass("table_iii");
    		$(".table22").show();
    	}
    	   
    })
})
