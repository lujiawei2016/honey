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
				updateGirl();
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
	
	function updateGirl(){
		var mainImg = $('#mainImg').val();
		var girlImgs = $('#girlImgs').val();
		var girlId = $('#girlId').val();
		var girlName = $('#girlName').val();
		var age = $('#age').val();
		var hight = $('#hight').val();
		var weight = $('#weight').val();
		var qq = $('#qq').val();
		var weixin = $('#weixin').val();
		var phone = $('#phone').val();
		var price = $('#price').val();
		var address = $('#address').val();
		var title = $('#title').val();
		var description = $('#description').val();
		$.ajax({
			url:path+'/girl/update',
			data:{'mainImg':mainImg,'girlImgs':girlImgs,'girlId':girlId,'girlName':girlName,'age':age,'hight':hight,'weight':weight,'qq':qq,'weixin':weixin,'phone':phone,'price':price,'address':address,'title':title,'description':description},
			dataType:'json',
			type:'post',
			beforeSend:function(){
				layer.load(1);
			},
			success:function(result){
				layer.closeAll();
				alert(result);
			},
			error:function(result){
				layer.closeAll();
				if(result.responseText == 'ajaxIsTimeOut'){
					layer.confirm('您的登陆已过期，请重新登陆',{
						icon:2
					},function(){
						window.location.href='${ctx}/loginView/login';
					});
				}else if(result.responseText.indexOf('没有权限') > 0){
					layer.alert('没有权限进行该操作',{
						icon:2
					});
				}else{
					layer.alert('系统繁忙，请稍后重试....',{
						icon:2
					});
				}
			}
		});
	}
	
	$('#updateForm').validate({
		rules:{
			mainImg:{
				required:true
			},
			girlImgs:{
				required:true
			},
			girlName:{
				required:true
			},
			phone:{
				required:true
			},
			title:{
				required:true
			}
		},
		messages:{
			mainImg:{
				required:'请上传主页图片上传'
			},
			girlImgs:{
				required:'请上传图片'
			},
			girlName:{
				required:'妹纸名称不能为空'
			},
			phone:{
				required:'电话不能为空'
			},
			title:{
				required:'标题不能为空'
			}
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