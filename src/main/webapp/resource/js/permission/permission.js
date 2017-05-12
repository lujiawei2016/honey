$(document).ready(function(){
	
	var path = $('#path').val();
	
	//点击添加
	$('#add').click(function(){
		$('#updateModal').modal('show');
		$('#permissionId').val('0');
	});
	
	//当权限类型改变
	$('#type').change(function(){
		var type = $(this).val();
		if('1' == type){
			//权限
			$('#father_id').parents('.form-group').show();
			$('#mark').parents('.form-group').show();
		}else if('2' == type){
			//权限组
			$('#father_id').parents('.form-group').hide();
			$('#mark').parents('.form-group').hide();
		}
	});
	
	//点击提交
	$('#subBtn').click(function(){
		
		var type = $('#type').val();
		var name = $('#name').val();
		var father_id = $('#father_id').val();
		var mark = $('#mark').val();
		
		if(name == null || name == ''){
			layer.alert('权限名称不能为空',{
				icon:2
			});
			return;
		}
		if(type == '1' && (father_id == null || father_id == '')){
			layer.alert('所属组不能为空',{
				icon:2
			});
			return;
		}
		if(type == '1' && (mark==null || mark=='')){
			layer.alert('权限标识不能为空',{
				icon:2
			});
			return;
		}
		if(type == '1' && !(mark.indexOf(':') > 0)){
			layer.alert('权限标识格式不正确',{
				icon:2
			});
			return;
		}
		
		$.ajax({
			url:path+'/permission/updatePermission',
			data:{'type':type,'name':name,'father_id':father_id,'mark':mark},
			dataType:'json',
			type:'post',
			beforeSend:function(){
				layer.load(1);
			},
			success:function(result){
				layer.closeAll();
				if('1' == result){
					layer.msg('新增权限组成功');
				}else if('2' == result){
					layer.msg('修改权限组成功');
				}else if('3' == result){
					layer.alert('权限标识不能为空',{
						icon:2
					});
				}else if('4' == result){
					layer.msg('修改权限成功');
				}else if('5' == result){
					layer.msg('新增权限成功');
				}else if('6' == result){
					layer.alert('权限标识已存在',{
						icon:2
					});
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
		
	});
	
	//点击删除
	$(document).on('click','.delete',function(){
		var name = $(this).parents('td').siblings('.nameTh').html();
		var id = $(this).next().val();
		layer.confirm('确定删除'+name+"吗？",{
			icon:0
		},function(){
			$.ajax({
				url:path+'/permission/deletePermission',
				data:{'id':id},
				dataType:'json',
				type:'post',
				beofreSend:function(){
					layer.load(1);
				},
				success:function(result){
					layer.closeAll();
					if('1' == result){
						layer.msg('删除成功');
					}else if('2' == result){
						layer.alert('删除失败',{
							icon:2
						});
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
		});
	});
});