$(document).ready(function(){
	
	var path = $('#path').val();
	
	$('.loadDiv').hide();
	
	//滚动到最底部
	$(window).scroll(function() {
		if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
			var returnFlag = $('#returnFlag').val();
			if(returnFlag == '1'){
				//从后台查出数据
				var categoryId = $('#categoryId').val();
				var start = $('.aui-it-list ul li').length;
				$.ajax({
					url:path+'/categoryDetail/loadGirlCategory/'+categoryId+'/'+start+'/19',
					data:{},
					dataType:'json',
					type:'post',
					beforeSend:function(){
						$('#returnFlag').val('0');
						$('.loadDiv').show();
					},
					success:function(result){
						if(result == null || result.length == 0){
							//没有数据
							layer.open({
							    content: '没有更多啦',
							    skin: 'msg',
							    time: 2 //2秒后自动关闭
							});
						}else{
							//有数据
							var insertData = '';
							for(var i=0;i<result.length;i++){
								insertData = insertData + 
								'<li>'+
									'<a href="'+path+'/detail/girlDetail/'+result[i].girlId+'">'+
										'<div class="aui-it-title"><img src="'+path+'/'+result[i].mainImg+'" width="90"></div>'+
										'<div class="aui-it-middle">'+
											'<h2 style="padding-bottom:0;">'+result[i].girlName+'</h2>'+
											'<h3 style="color:#ff5a00;padding:0; margin:0;">￥'+result[i].price+'</h3>'+
											'<span class="aui-aui-s">新品</span> '+
											'<span class="aui-aui-s">红包</span> '+
											'<span class="aui-aui-s">折扣</span> '+
											'<p class="aui-spill">'+result[i].title+'</p>'+
										'</div>'+
									'</a>'+
								'</li>';
							}
						}
						
						$('.aui-it-list ul').append(insertData);
						
						$('.loadDiv').hide();
						$('#returnFlag').val('1');
					}
				});
			}
		}
	});
});