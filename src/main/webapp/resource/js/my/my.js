$(document).ready(function(){
	
	var path = $('#path').val();
	
	//点击退出账号
	$(document).on('click','.exit',function(){
		layer.open({
			content: '您确定退出账号？',
		    btn: ['退出', '不要'],
		    yes: function(index){
		    	window.location.href=path+'/userLogin/exitUser';
		    }
		});
	});
});