window.onload = function () {
	new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
}
$(document).ready(function(){
	
	var path = $('#path').val();
	
	//点击主页图片
	$(document).on('click','#imgShow',function(){
		$('#up_img').click();
	});
	
	$('.checkI').hide();
	//点击上传主页头像
	$(document).on('click','#uploadMainImg',function(){
		var fileVal = $('#up_img').val();
		if(fileVal != null && fileVal != undefined){
			$.ajaxFileUpload({
		        url:path+'/upload/uploadFile',  
		        secureuri:false,  
		        fileElementId:'up_img',//file标签的id  
		        dataType: 'json',//返回数据的类型  
		        data:{},//一同上传的数据  
		        success: function (data) {
		        	//主页头像上传成功
		        	$('#mainImg').val(data.imgUrl);
		        	$('.checkI').show();
		        },  
		        error: function (data) {
		        	layer.alert('上传失败',{
		        		icon:2
		        	});
		        }
		    });  
		}else{
			layer.alert('请选择文件',{
				icon:2
			});
		}
	});
	
	//选择图片上传
	var successCount = 0;
	$('#uploadImg').diyUpload({
		url:path+'/upload/uploadFile',
		success:function( data ) {
			var fileLen = $('.fileBoxUl li').length;
			successCount = successCount + 1;
			
			var girlImgs = $('#girlImgs').val();
			girlImgs = girlImgs + data.imgUrl + ",";
			$('#girlImgs').val(girlImgs);
			
			if(fileLen == successCount){
				//全部上传成功
				
			}
		},
		error:function( err ) {
			layer.alert('出现错误',{
				icon:2
			});
		},
		uploadFinished:function(){
			alert('全部上传成功');
		}
	});
	
	$('#updateForm').validate({
		rules:{
			
		},
		messages:{
			
		},
		showErrors : function(errorMap, errorList) {
			var msg = "";
			$.each(errorList, function(i, v) {
				msg += (v.message + "\r\n");
			});
			if (msg != "")
				layer.alert(msg,{
					icon:2
				});
			},
		onfocusout : false,
        onkeyup: false,
        onsubmit: true,
        submitHandler:function(){
        	$('.diyStart').click();
        }
	});
	
});