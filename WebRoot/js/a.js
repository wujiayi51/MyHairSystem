//复选框的点选操作
function AAA(n){
	var cks = document.getElementsByName(n);
	var len = cks.length;
	
	for(var i=0;i<len;i++){
		
		if( cks[i].type=="checkbox"){
			//判断控件类型为复选框
			cks[i].checked = !cks[i].checked;//注意html中选中checked=checked，js中checked=true
	
		}
	}	
	
}
//得到所有选中的数据项
function BBB(n){
	var cks = document.getElementsByName(n);
	var len = cks.length;
	var cksValue = "";
	
	for(var i=0;i<len;i++){
		
		if( cks[i].type=="checkbox"){
			//判断控件类型为复选框
			
			if(cks[i].checked){
				cksValue += cks[i].value+","
			}
		}
	}	
	if(cksValue!=""){
		cksValue = cksValue.substring(0,cksValue.length-1);
	}
	return cksValue;
}