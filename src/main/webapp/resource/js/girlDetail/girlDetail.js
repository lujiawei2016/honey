var path = "{:U('buy')}?id=";
	//兼容性：字体大小，全局尺寸(rem)
	(function(doc, win) {
		var docEl = doc.documentElement,
				resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
				recalc = function() {
					var clientWidth = docEl.clientWidth;
					if (!clientWidth) return;
					var docElWidth = 100 * (clientWidth / 640);
					if (docElWidth > 100) docElWidth = 100;
					docEl.style.fontSize = docElWidth + 'px';
				};
		if (!doc.addEventListener) return;
		win.addEventListener(resizeEvt, recalc, false);
		doc.addEventListener('DOMContentLoaded', recalc, false);
	})(document, window);

	(function(){
		//轮播图
		new Swiper('.swiper-container', {
			pagination: '.swiper-pagination',
			paginationClickable: true,
			autoplay:3000
		});

		$('.nav a').click(function(){
			$('.nav a').removeClass('active');
			$(this).addClass('active');
		})
		//sku
		$('.sku,.buy').click(function(){
			$('.layer').addClass('acitve');
		})
		$('.close').click(function(){
			$('.layer').removeClass('acitve');
		});
		//却动
		$('#sku a').click(function(){
			$('#sku a').removeClass('active');
			$(this).addClass('active');
			$('.next').attr('href',path + $(this).data('sku'));
			$('.sku-group dd').text($(this).text());
		});
		//图片懒加载
		$("img").lazyload({
			effect : 'fadeIn',
			placeholder :'http://img.weizhi.so/placeholder.png'
		});

	})();
	
$(document).ready(function(){
	
	//一开始隐藏售前须知
	$('.preSaleDiv').hide();
	var path = $('#path').val();
	
	//点击商品详细
	$(document).on('click','.goodsDetail',function(){
		$('.preSaleDiv').hide();
		$('.goodsDetailDiv').show();
	});
	
	//点击售前须知
	$(document).on('click','.preSale',function(){
		$('.goodsDetailDiv').hide();
		$('.preSaleDiv').show();
	});
	
	//点击加入待约区
	$(document).on('click','.fighting',function(){
		var girlId = $('#girlId').val();
		$.ajaxSetup({
			url:path+'/car/addCar',
			data:{'girlId':girlId},
			dataType:'json',
			type:'post',
			beforeSend:function(){
				
			},
			success:function(result){
				if('1' == result){
					layer.open({
						content: '加入待约区成功',
						skin: 'msg',
						time: 2 //2秒后自动关闭
				  });
				}else if('2' == result){
					layer.open({
						content:'待约区已存在该妹纸',
						btn:'确定'
					});
				}else{
					layer.open({
						content:'系统繁忙，请稍后重试',
						btn:'确定'
					});
				}
			},
			error:function(){
				layer.open({
					content:'系统繁忙，请稍后重试',
					btn:'确定'
				});
			},
			complete:function(xhr,status){
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
	});
	
	//点击点赞按钮
	$(document).on('click','.thumb',function(){
		var girlId = $('#girlId').val();
		$.ajaxSetup({
			url:path+'/detail/thumbUp',
			data:{'girlId':girlId},
			dataType:'json',
			type:'post',
			beforeSend:function(){
				
			},
			success:function(result){
				if('1' == result){
					layer.open({
						content: '点赞成功',
						skin: 'msg',
						time: 2 //2秒后自动关闭
					});
					$('.thumb').removeClass('aui-icon-gz').addClass('aui-icon-gz-full');
				}else if('2' == result){
					layer.open({
						content:'24小时内只能点赞一次哦',
						btn:'确定'
					});
				}else{
					layer.open({
						content:'系统繁忙，请稍后重试',
						btn:'确定'
					});
				}
			},
			error:function(){
				layer.open({
					content:'系统繁忙，请稍后重试',
					btn:'确定'
				});
			},
			complete:function(xhr,status){
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
	});
});