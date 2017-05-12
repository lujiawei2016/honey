//封装ajax
function AJAX(method,url,data,success){
	    var ajax=new XMLHttpRequest();
	    ajax.onreadystatechange=function(){
	    	if(ajax.readyState == 1){
	    		layer.load(1);
	    	}else if(ajax.readyState == 4){
	    		layer.closeAll();
                if(ajax.status == 200){
                    if(success){
                        success(ajax.responseText);
                    }
                }else{
                	layer.alert('系统繁忙，请稍后重试....',{
                		icon:2,
                		title:'提示信息'
                	});
                }
            }
        };
	    if(method=="POST" || method=="post"){  
	        ajax.open("POST",url);
	        ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	        ajax.send(data);
	    }else if(method=="GET" || method=="get"){  
	        ajax.open("GET",url+"?"+data);
	        ajax.send(null);
	    }else{  
	        alert("error method");
	    }
	    return ajax;  
	}