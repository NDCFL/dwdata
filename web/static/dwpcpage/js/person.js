$(function(){
	$(".sc_ul_right>li:first").hover(function(){
		$(".ul_down").toggle();
	})

    $(".box1").click(function(){
    	$(this).css("border","1px solid #0583CE");
    	$(this).siblings(".box1").css("border","1px solid #ADADAD")
    })
	
})

