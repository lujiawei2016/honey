$(document).ready(function(){
	
	var path = $('#path').val();
	
	//点击注册
	$(document).on('click','.login__submit',function(){
		var $username = $.trim($('#username').val());
		var $password = $.trim($('#password').val());
		var $topassword = $.trim($('#topassword').val());
		
		if($username == '' || $password == '' || $topassword == ''){
			layer.open({
				content:'用户名、密码、确认密码都不能为空',
				btn:'确定'
			});
			return;
		}
		
		if($password != $topassword){
			layer.open({
				content:'两次输入密码不一致',
				btn:'确定'
			});
			return;
		}
		
		if($username.length <= 5 || $password.length <= 5){
			layer.open({
				content:'用户名和密码必须六位数以上',
				btn:'确定'
			});
			return;
		}
		
		$.ajax({
			url:path+'/userLogin/registerIn/'+$username+'/'+$password+'',
			data:{},
			dataType:'json',
			type:'post',
			beforeSend:function(){
				layer.open({
					type:2,
					content:'正在注册',
					shadeClose:false
				});
			},
			success:function(result){
				layer.closeAll();
				var msg = '';
				if('1' == result){
					msg = '账号或密码不能为空';
					layer.open({
						content:msg,
						btn:'确定'
					});
				}else if('2' == result){
					msg = '该用户名已经被占用';
					layer.open({
						content:msg,
						btn:'确定'
					});
				}else if('3' == result){
					msg = '注册成功，正在跳转...';
					layer.open({
						content:msg,
						btn:'确定'
					});
					window.location.href=path+'/main/index';
				}else if('4' == result){
					msg = '账号和密码长度不能低于6';
					layer.open({
						content:msg,
						btn:'确定'
					});
				}
			},
			error:function(result){
				layer.closeAll();
				layer.open({
					content:'系统繁忙，请稍后重试',
					btn:'确定'
				});
			}
		});
		
	});
});