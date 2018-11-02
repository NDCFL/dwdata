//个人中心下拉
$("#user-index").hover(function(){
    $(this).find("ul").slideDown(300);
},function(){
    $(this).find("ul").slideUp(100);
});


/*************************************************************/
//载入文件
function hasFile() {
	$('.file-name').each(function(){
		var fileName = $(this).html();
		if(fileName != "") {
			$(this).parent().find('i').html('重新选择');
		}
	})
}

//添加文件
function fileChange(obj) {
	$(obj).change(function(){
		var fileName,
			index,
			fileNameStr = $(this).val(),
			arr = fileNameStr.split('\\');
			
		index = arr.length-1;
		fileName = arr[index];
		$(this).parent().parent().find('em').html(fileName);
		var emFileName = $(this).parent().parent().find('em').html();
		if(obj=="'.file-item'"){
			if(emFileName==undefined||emFileName=="") {
				$(this).parent().find("i").html('选择文件');
			} else {
				$(this).parent().find("i").html('重新选择');
			}
		}
	})
}
/*************************************************************/