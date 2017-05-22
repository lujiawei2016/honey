$(document).ready(function(){
	
	var path = $('#path').val();

	//点击登录
	$(document).on('click','.login__submit',function(){
		var $username = $.trim($('#username').val());
		var $password = $.trim($('#password').val());
		
		if($username == '' || $password == ''){
			layer.open({
				content:'用户名密码都不能为空',
				btn:'确定'
			});
			return;
		}
		
		$.ajax({
			url:path+'/userLogin/loginInGirl/'+$username+'/'+$password+'',
			data:{},
			dataType:'json',
			type:'post',
			beforeSend:function(){
				layer.open({
					type:2,
					content:'正在登录',
					shadeClose:false
				});
			},
			success:function(result){
				layer.closeAll();
				if('1' == result){
					layer.open({
						content:'用户名、密码不能为空',
						btn:'确定'
					});
				}else if('2' == result){
					layer.open({
						content:'用户名不存在',
						btn:'确定'
					});
				}else if('3' == result){
					layer.open({
						content:'密码错误',
						btn:'确定'
					});
				}else if('4' == result){
					layer.open({
						content:'登录成功，正在跳转',
						btn:'确定'
					});
					setTimeout(function(){
						var referer = $('#referer').val();
						if(referer != null){
							window.location.href=referer;
						}else{
							window.location.href=path+'/main/index';
						}
					},2000);
				}
			},
			error:function(){
				layer.closeAll();
				layer.open({
					content:'系统繁忙，请稍后重试',
					btn:'确定'
				});
			}
		});
	});
	
});