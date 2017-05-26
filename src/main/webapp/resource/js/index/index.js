$(document).ready(function(){
	
	var path = $('#path').val();
	
	//ajax加载详情妹纸
	loadGirl();
	
	//封装加载妹纸方法
	function loadGirl(){
		var girlLen = $('.girlDetailDiv').length;
		$.ajax({
			url:path+'/main/loadGirl',
			data:{'start':girlLen},
			dataType:'json',
			type:'post',
			beforeSend:function(){
				$('.loadDiv').show();
				$('#returnFlag').val('0');
			},
			success:function(result){
				$('.loadDiv').hide();
				var loadGirlLen = result.length;
				if(result == null || loadGirlLen==0){
					layer.open({
					    content: '没有更多啦',
					    skin: 'msg',
					    time: 2 //2秒后自动关闭
					  });
					$('#returnFlag').val('1');
				}else{
					var insertData = '';
					for(var i=0;i<loadGirlLen;i++){
						if(i%2 == 0){
							//第一条
							insertData = insertData +
							'<div class="aui-flex">'+
							'<div class="aui-flex-item aui-flex-items1 aui-flex-items2 girlDetailDiv">'+
								'<span>'+
									'<img src="'+path+'/'+result[i].mainImg+'">'+
								'</span>'+
								'<a href="'+path+'/detail/girlDetail/'+result[i].girlId+'" class="aui-flex-box">'+
									'<h2>'+result[i].title+' </h2>'+
									'<em>见面礼：￥'+result[i].price+'</em>'+
								'</a>'+
							'</div>'
						}else{
							//第二条
							insertData = insertData +
							'<div class="aui-flex-item aui-flex-items1 aui-flex-items2 girlDetailDiv">'+
								'<span>'+
									'<img src="'+path+'/'+result[i].mainImg+'">'+
								'</span>'+
								'<a href="'+path+'/detail/girlDetail/'+result[i].girlId+'" class="aui-flex-box">'+
									'<h2>'+result[i].title+' </h2>'+
									'<em>见面礼：￥'+result[i].price+'</em>'+
								'</a>'+
							'</div>'+
						'</div>'
						}
					}
					
					if(i%2 != 0){
						//需要闭合div
						insertData = insertData + '</div>';
					}
					
					$('#returnFlag').val('1');
					$('.loadDiv').before(insertData);
				}
			},
			error:function(){
				alert('页面发生错误');
			}
		});
	}
	
	//点击详情的图片
	$(document).on('click','.girlDetailDiv',function(){
		var hrefUrl = $(this).find('a').attr('href');
		window.location.href=hrefUrl;
	});
	
	//滚动到最底部
	$(window).scroll(function() {
		if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
			var returnFlag = $('#returnFlag').val();
			if('1' == returnFlag){
				loadGirl();
			}
		}
	});
});