$(document).ready(function(){
	
	var path = $('#path').val();
	
	//初始化滑动块
	$('#drag').drag();
	
	//点击登录按钮
	$('#loginBtn').click(function(){
		login();
	});
	
	//登录方法
	function login(){
		var username = $('#username').val();
		var password = $('#password').val();
		var verificationCode = $('#verificationCode').val();
		
		if(verificationCode == null || verificationCode == ''){
			layer.alert('请拖动验证码',{
				title:'提示信息',
				icon:2
			});
			return;
		}
		
		if(username == null || username == ''){
			layer.alert('用户名不能为空',{
				title:'提示信息',
				icon:2
			});
			return;
		}
		
		if(password == null || password == ''){
			layer.alert('密码不能为空',{
				title:'提示信息',
				icon:2
			});
			return;
		}
		
		$.ajax({
			url:path+'/loginView/loginIn',
			data:{'username':username,'password':password,'verificationCode':verificationCode},
			dataType:'json',
			type:'post',
			beforeSend:function(){
				layer.load(1);
			},success:function(result){
				layer.closeAll();
				if(result == '5'){
					layer.msg('登录成功，正在跳转');
					window.location.href=path+'/index/start';
					return;
				}else if(result == '1'){
					layer.msg('用户名、密码不能为空');
				}else if(result == '2'){
					layer.alert('请拖动验证码',{
						title:'提示信息',
						icon:2
					});
				}else if(result == '3'){
					layer.alert('用户名不存在',{
						title:'提示信息',
						icon:2
					});
				}else if(result == '4'){
					layer.alert('密码错误',{
						title:'提示信息',
						icon:2
					});
				}
				$('.codeImg').empty();
				$('.codeImg').append('<div id="drag"></div>');
				$('#drag').drag();
			},error:function(){
				layer.closeAll();
				layer.alert('系统繁忙，请稍后重试....',{
    				icon:2
    			});
			}
		});
	}
	
});