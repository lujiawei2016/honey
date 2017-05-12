$(document).ready(function(){
	
	var path = $('#path').val();
	
	$('#treeDiv').hide();
	
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
	
	var userId = $('#userId').val();
	openTree(userId);
	
	//点击角色框
	$('#treeTextArea').click(function(){
		var userId = $('#userId').val();
		if($('#treeDiv').html() == ''){
			openTree(userId);
			$('#treeDiv').show();
		}else{
			$('#treeDiv').show();
		}
	});
	
	//点击提交按钮
	$('#subBtn').click(function(){
		var username = $('#username').val();
		var userId = $('#userId').val();
		var treeId = $('#treeId').val();
		
		if(username == null || username == ''){
			layer.alert('用户名不能为空',{
				icon:2
			});
			return;
		}
		
		$.ajax({
			url:path+'/user/updateUser',
			data:{'username':username,'userId':userId,'treeId':treeId},
			dataType:'json',
			type:'post',
			beforeSend:function(){
				layer.load(1);
			},
			success:function(result){
				layer.closeAll();
				if('1' == result){
					layer.msg('新建用户成功');
				}else if('2' == result){
					layer.alert('用户名已存在',{
						icon:2
					});
				}else if('3' == result){
					layer.msg('编辑用户成功');
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
	
	//打开树
	function openTree(userId){
		$.ajax({
			url:path+'/user/getRolesTreeByUserId',
			data:{'userId':userId},
			dataType:'json',
			type:'post',
			beforeSend:function(){
				layer.load(1);
			},
			success:function(result){
				$('#treeTextArea').text(result.tueeName);
				$('#treeId').val(result.treeId);
				layer.closeAll();
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
	
	//点击删除
	$(document).on('click','.delete',function(){
		var userId = $(this).prev().val();
		layer.alert(userId);
	});
});