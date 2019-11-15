var selectall=function(){
	$('input:checkbox[class=checkUser]').each(function(i){
		var check=this.checked;
		if(!check){ 
		$(this).prop("checked",true);
		}
	});		
}


var selectnone=function(){
	$('input:checkbox[class=checkUser]').each(function(i){
		var check=this.checked;
		if(check){ 
			$(this).prop("checked",false);
		}
	});		
}



var selectunder=function(){
	$('input:checkbox[class=checkUser]').each(function(i){
		var check=this.checked;
		if(check){ 
			$(this).prop("checked",false);
		}else{
			$(this).prop("checked",true);
		}
	});		
	
}
