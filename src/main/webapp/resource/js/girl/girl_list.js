$(document).ready(function(){
	
	var path = $('#path').val();
	
	//点击删除
	$(document).on('click','.delete',function(){
		var girlId = $(this).prev().val();
		var state = $(this).html();
		layer.confirm('确定'+state+'吗？',{
			icon:0,
			btn:['确定','取消']
		},function(){
			$.ajax({
				url:path+'/girl/deleteOrLifelById',
				data:{'girlId':girlId},
				dataType:'json',
				type:'post',
				beforeSend:function(){
					layer.load(1);
				},
				success:function(result){
					layer.closeAll();
					if('1' == result){
						layer.msg('删除成功');
					}else if('2' == result){
						layer.msg('复活成功');
					}else{
						layer.alert('操作失败',{
							icon:2
						});
					}
					setTimeout(function(){
						location.reload();
					},2000);
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
		});
	});
});