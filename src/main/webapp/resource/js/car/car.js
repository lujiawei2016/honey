$(document).ready(function(){
	
	var path = $('#path').val();
	
	/*选择一个商品的点击事件*/
	$(".main_con_goods li .circle").click(function(){
		var num = $(this).parent().index();
		var nums = $(".main_con_goods li div").length;
		
		var girlId = $(this).attr('id');    // 当前点击的girl id
		var girlIds = $('#girlIds').val();  // 需要传给后台的girl id
		
		girlId = girlId.replace('circle','');
		
		if($(this).css("background-color") == "rgba(0, 0, 0, 0)"){
			//选中
			girlIds = girlIds + girlId + ',';
			
			$(this).css("background-color","#bf392a");
			$(this).css("border","1px solid #bf392a");
		}else{
			//取消选择
			girlIds = girlIds.replace(girlId+',','');
			
			$(this).css("background-color","rgba(0, 0, 0, 0)");
			$(this).css("border","1px solid #bab9b9");
		}
		
		$('#girlIds').val(girlIds);
		
		//当选中一个商品时需要显示删除按钮
		for (var i = 0; i < nums; i++) {
			if($(".main_con_goods ul").children().eq(i).find(".circle").css("background-color") == "rgb(191, 57, 42)"){
				$(".main_con_allchoose img").css("display","block");
				break;
			}
			$(".main_con_allchoose img").css("display","none");
		}
		
		return false;
		
	});
	
	$(document).on('click','.delCar',function(){
		
		var girlIds = $('#girlIds').val();
		
		if(girlIds != null && girlIds != ''){
			layer.open({
				content: '确定删除所选项吗？',
				btn: ['确定', '不要'],
				yes: function(index){
					$.ajaxSetup({
						url:path+'/car/deleteCar',
						data:{'girlIds':girlIds},
						dataType:'json',
						type:'post',
						beforeSend:function(){
							layer.open({
								type: 2,
								content: '正在删除',
								shadeClose:false
							});
						},
						success:function(result){
							layer.closeAll();
							if('1' == result){
								layer.open({
									content:'删除成功',
									btn:'确定'
								});
							}else{
								layer.open({
									content:'删除失败',
									btn:'确定'
								});
							}
							var girlIdsArr = girlIds.split(',');
							for(var i=0;i<girlIdsArr.length;i++){
								if(girlIdsArr[i] != '' && girlIdsArr[i] != null){
									$('#circle'+girlIdsArr[i]+'').parents('li').remove();
								}
							}
							$('#girlIds').val('');
						},
						error:function(){
							layer.closeAll();
							layer.open({
								content:'系统繁忙，请稍后重试',
								btn:'确定'
							});
						},
						complete:function(xhr,status){
							layer.closeAll();
							var sessionStatus = xhr.getResponseHeader('sessionstatus');
					        if(sessionStatus == 'timeout') {
					        	layer.open({
					        	    content: '您还没有登录，是否跳到登录页面？',
					        	    btn: ['好的', '再看看'],
					        	    yes: function(index){
					        	    	window.location.href=path+'/userLogin/loginGirl';
					        	    }
					        	});
					        }
						}
					});
					
					$.ajax();
				}
			});
		}else{
			layer.open({
				content:'请选择需要删除的妹纸',
				btn:'确定'
			});
		}
	});
	
	//点击结算按钮
	$(document).on('click','.settlementBtn',function(){
		var totalProce = 0;
		var girlNum = 0;
		$('.main_con_goods ul li').each(function(){
			var price = $(this).find('.aui-redd').html();
			price = price.replace('￥','');
			totalProce = totalProce + parseInt(price);
			girlNum = girlNum + 1;
		});
		
		$('.totalmoney').html('￥'+totalProce);
		$('.girlNum').html(girlNum);
	});
	
	//点击商品列表li
	$('.main_con_goods ul li').click(function(){
		var girlId = ($(this).find('.circle').attr('id')).replace('circle','');
		window.location.href=path+'/detail/girlDetail/'+girlId+'';
	});
});