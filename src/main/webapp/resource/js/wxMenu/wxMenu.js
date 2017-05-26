$(document).ready(function(){
	
	var path = $("#path").val();
	$(".loadWxMenu").hide();
	
	$(".erji").parents(".form-group").hide();
	$(".fatherId").parents(".form-group").hide();
	$(".type").parents(".form-group").hide();
	
	$("input[name='url']").parents(".form-group").hide();
	$("input[name='menuKey']").parents(".form-group").hide();
	
	$(document).on("change","#level",function(){
		var level = $(this).val();
		if("1" == level){
			$(".fatherId").parents(".form-group").hide();
			$(".erji").parents(".form-group").show();
			$(".type").parents(".form-group").hide();
			$("input[name='url']").parents(".form-group").hide();
			$("input[name='menuKey']").parents(".form-group").hide();
			$("input[name='url']").val("");
			$("input[name='menuKey']").val("");
			$("#type").val("");
		}else if("2" == level){
			$(".erji").parents(".form-group").hide();
			$(".fatherId").parents(".form-group").show();
			$(".type").parents(".form-group").show();
			$("input[name='url']").parents(".form-group").hide();
			$("input[name='menuKey']").parents(".form-group").hide();
			$("#type").val("");
			$("#erji").val("");
		}else{
			$(".erji").parents(".form-group").hide();
			$(".fatherId").parents(".form-group").hide();
			$(".type").parents(".form-group").hide();
			$("input[name='url']").parents(".form-group").hide();
			$("input[name='menuKey']").parents(".form-group").hide();
			$("#erji").val("");
		}
	});
	
	$(document).on("change","#erji",function(){
		var erji = $(this).val();
		if("1" == erji){
			//有二级菜单
			$(".type").parents(".form-group").hide();
			$("input[name='url']").parents(".form-group").hide();
			$("input[name='menuKey']").parents(".form-group").hide();
		}else if("0" == erji){
			//无二级菜单
			$("#type").val("");
			$(".type").parents(".form-group").show();
		}else{
			$("input[name='url']").parents(".form-group").hide();
			$("input[name='menuKey']").parents(".form-group").hide();
			$(".type").parents(".form-group").hide();
		}
	});
	
	$(document).on("change","#type",function(){
		var type = $(this).val();
		if("click" == type){
			$("input[name='url']").parents(".form-group").hide();
			$("input[name='menuKey']").parents(".form-group").show();
		}else if("view" == type){
			$("input[name='url']").parents(".form-group").show();
			$("input[name='menuKey']").parents(".form-group").hide();
		}else{
			$("input[name='url']").parents(".form-group").hide();
			$("input[name='menuKey']").parents(".form-group").hide();
		}
	});
	
	$("#wxMenuForm").validate({
		rules:{
			level:{
				required:true
			},
			name:{
				required:true
			},
			sort:{
				required:true,
				number:true,
				min:1
			}
		},
		messages:{
			level:{
				required:"请选择菜单级别"
			},
			name:{
				required:"菜单名称不能为空"
			},
			sort:{
				required:"排序不能为空",
				number:"排序必须输入合法数字",
				min:"排序不能为0或负数"
			}
		},
		submitHandler:function(){
			$.ajax({
				url:path+"/wxMenu/saveWxMenu",
				dataType:"json",
				data:$("#wxMenuForm").serialize(),
				type:"post",
				beforeSend:function(){},
				success:function(){
					layer.msg("保存菜单成功");
					setTimeout(function(){
						location.reload();
					},2000);
				}
			});
		},
		showErrors:function(errorMap, errorList){
			var msg = "";  
	        $.each(errorList, function(i, v) {  
	         msg += (v.message + "\r\n");  
	        });  
	        if (msg != ""){
	        	layer.msg(msg,{
	        		time:5000,
	        	});
	        }
         },
         onsubmit:true,
         onfocusout : false,
         onkeyup:false,
         onclick:false
	});
	
	$(".createWxMenu").click(function(){
		$.ajax({
			url:path+"/wxMenu/createWxMenu",
			data:{},
			dataType:"json",
			type:"post",
			beforeSend:function(){
				$(".createWxMenu").hide();
				$(".loadWxMenu").show();
			},
			success:function(result){
				$(".createWxMenu").show();
				$(".loadWxMenu").hide();
				if("0" == result){
					layer.msg("微信菜单生成成功");
				}else if("1" == result){
					layer.msg("微信菜单生成失败，请稍后重试....");
				}
			}
		});
	});
	
	$(document).on("click",".deleteA",function(){
		var id = $(this).prev().val();
		var name = $(this).prev().prev().html();
		layer.confirm("确定删除 '"+name+"' 吗？",{
			title:"警告"
		},function(){
			//确定删除操作
			$.ajax({
				url:path+"/wxMenu/deleteWxMenu",
				dataType:"json",
				data:{"id":id},
				type:"post",
				beforeSend:function(){
					layer.load("1");
				},
				success:function(result){
					if("1" == result){
						// id为空
						layer.msg("删除失败");
					}else if("2" == result){
						//删除成功
						layer.msg("删除成功");
					}else if("3" == result){
						//菜单已删除
						layer.msg("请检查当前菜单是否存在");
					}
					setTimeout(function(){
						location.reload();
					},3000);
				}
			});
		},function(){
			//取消删除操作
		});
	});
	
	$(".fatherMenuSpan").mouseover(function(){
		$(this).siblings(".deleteA").css("opacity","1");
	}).mouseout(function(){
		$(this).siblings(".deleteA").css("opacity","0");
	});
	
	$(".childrenMenuSpan").mouseover(function(){
		$(this).siblings(".deleteA").css("opacity","1");
	}).mouseout(function(){
		$(this).siblings(".deleteA").css("opacity","0");
	});
});