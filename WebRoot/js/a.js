//��ѡ��ĵ�ѡ����
function AAA(n){
	var cks = document.getElementsByName(n);
	var len = cks.length;
	
	for(var i=0;i<len;i++){
		
		if( cks[i].type=="checkbox"){
			//�жϿؼ�����Ϊ��ѡ��
			cks[i].checked = !cks[i].checked;//ע��html��ѡ��checked=checked��js��checked=true
	
		}
	}	
	
}
//�õ�����ѡ�е�������
function BBB(n){
	var cks = document.getElementsByName(n);
	var len = cks.length;
	var cksValue = "";
	
	for(var i=0;i<len;i++){
		
		if( cks[i].type=="checkbox"){
			//�жϿؼ�����Ϊ��ѡ��
			
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