$(document).ready(function(){
	
	var path = $('#path').val();
	
	//文件上传
	$('#uploadImg').diyUpload({
		url:path+'/upload/uploadFile',
		success:function( data ) {
			$('#imgUrl').val(data.imgUrl);
			$.ajax({
    			url:path+'/category/updateCategory',
    			data:$('#updateForm').serialize(),
    			dataType:'json',
    			type:'post',
    			beforeSend:function(){
    				layer.load(1);
    			},
    			success:function(result){
    				layer.closeAll();
    				if('1' == result){
    					layer.msg('新增图标成功');
    				}
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
		},
		error:function( err ) {
			layer.alert('出现错误',{
				icon:2
			});
		}
	});
	
	$('#updateForm').validate({
		rules:{
			cateName:{
				required:true
			},
			imgUrl:{
				required:true
			},
			sort:{
				required:true,
				min:1,
				number:true
			},
		},
		messages:{
			cateName:{
				required:'请输入种类名'
			},
			imgUrl:{
				required:'请上传图片'
			},
			sort:{
				required:'排序不能为空',
				min:'数字必须大于1',
				number:'数字必须大于1'
			},
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