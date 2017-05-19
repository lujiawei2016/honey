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
			console.log(this);
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
	
	
	
});