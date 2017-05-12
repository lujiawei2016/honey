$(document).ready(function(){
	
	var path = $('#path').val();
	$('#treeDiv').hide();
	
	//点击树形框
	$('#treeTextArea').click(function(){
		var rolesId = $('#rolesId').val();
		$('#treeDiv').show();
	});
	
	//监听点击事件
	$(document).click(function(e){
		var v_id = $(e.target).attr('id');
		var flag = false;
		if(v_id){
			var flag = v_id.indexOf('treeDiv')>=0;
		}
		if(!flag && 'treeTextArea' != v_id){
			$('#treeDiv').hide();
		}
	});
	
	//点击添加角色
	$('#add').click(function(){
		var rolesId = '0';
		$('#rolesId').val(rolesId);
		$('#type').removeAttr('disabled');
		$('#name').val('');
		openTree(rolesId);
	});
	
	//点击编辑
	$(document).on('click','.edit',function(){
		var rolesId = $(this).next().val();
		$('#rolesId').val(rolesId);
		$.ajax({
			url:path+'/roles/editRoles',
			data:{'rolesId':rolesId},
			dataType:'json',
			type:'post',
			success:function(result){
				$('#type').attr('disabled','disabled');
				$('#name').val(result.roles.rolesName);
				if(result.father_id == null){
					//父级角色
					$('#type').val('2');
					$('#father_id').parents('.form-group').hide();
					$('#treeTextArea').parents('.form-group').hide();
				}else{
					//子角色
					$('#type').val('1');
					$('#father_id').val(result.father_id);
					$('#father_id').parents('.form-group').show();
					$('#treeTextArea').parents('.form-group').show();
				}
				openTree(rolesId);
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
	
	//打开树
	function openTree(rolesId){
		$.ajax({
			url:path+'/roles/getPermissionTreeByRolesId',
			data:{'rolesId':rolesId},
			dataType:'json',
			type:'post',
			beforeSend:function(){
				layer.load(1);
			},
			success:function(result){
				layer.closeAll();
				
				$('#treeId').val(result.treeId);
				$('#treeTextArea').text(result.treeName);
				
				var setting = {
					check: {
						enable: true
					},
					data: {
						simpleData: {
							enable: true
						}
					},
					callback:{
						onCheck:onCheck
					}
				};
				
				var zNodes = result.treeList;
				
				$.fn.zTree.init($("#treeDiv"), setting, zNodes);
				
				$('#updateModal').modal('show');
				
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
	
	//选中节点操作
	function onCheck(e,treeId,treeNode){
		var treeObj=$.fn.zTree.getZTreeObj('treeDiv');
	    var nodes=treeObj.getCheckedNodes(true);
	    
	    var treeName = "";
	    var treeId = "";
	    
	    for(var i=0;i<nodes.length;i++){
	    	if(nodes[i].pId != null){
	    		//过滤父节点
	    		treeName = treeName + nodes[i].name + ',';
	    		treeId = treeId + nodes[i].id + ','; //获取选中节点的值
	    	}
	    }
	    
	    $('#treeTextArea').text(treeName);
	    $('#treeId').val(treeId);
    }
	
	//类型改变
	$('#type').change(function(){
		var type = $(this).val();
		if('1' == type){
			//选择角色
			$('#father_id').parents('.form-group').show();
			$('#treeTextArea').parents('.form-group').show();
		}else if('2' == type){
			//选择角色组
			$('#father_id').parents('.form-group').hide();
			$('#treeTextArea').parents('.form-group').hide();
		}
	});
	
	//点击提交按钮
	$('#subBtn').click(function(){
		
		var type = $('#type').val();
		var name = $('#name').val();
		var father_id = $('#father_id').val();
		var rolesId = $('#rolesId').val();
		var treeId = $('#treeId').val();
		
		if(name == null || name == ''){
			layer.alert('角色名称不能为空',{
				icon:2
			});
			return;
		}
		
		if('1' == type && (father_id == null || father_id == '')){
			layer.alert('请选择角色组',{
				icon:2
			});
			return;
		}
		
		$.ajax({
			url:path+'/roles/updateRoles',
			data:{'type':type,'name':name,'father_id':father_id,'rolesId':rolesId,'treeId':treeId},
			dataType:'json',
			type:'post',
			beforeSend:function(){
				layer.load(1);
			},
			success:function(result){
				layer.closeAll();
				if('1' == result){
					layer.msg('新增角色成功');
				}else if('2' == result){
					layer.msg('新增角色组成功');
				}else if('3' == result){
					layer.msg('编辑角色组成功');
				}else if('4' == result){
					layer.msg('编辑角色组成功');
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
		var rolesId = $(this).prev().val();
		layer.confirm('确定删除吗？',{
			icon:0
		},function(){
			$.ajax({
				url:path+'/roles/deleteRolesById',
				data:{'rolesId':rolesId},
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