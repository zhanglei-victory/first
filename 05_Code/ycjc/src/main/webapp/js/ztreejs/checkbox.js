function allCheck(){  
var obj=document.getElementsByTagName("input");     
if(document.getElementById("all").checked==true){         
	for(var i=0;i<obj.length;i++){ 
            obj[i].checked=true; 
        } 
    }else{  
    	for(
    			var i=0;i<obj.length;i++){ 
            	obj[i].checked=false; 
        } 
    } 
}  
function checkT_F(){  
var obj=document.getElementsByTagName("input"); 
var j=0;  
	for(var i=0;i<obj.length;i++){  
		if(obj[i].id!='all'){    
			if(obj[i].checked==true){    
                j++; 
            } 
        } 
    }  
    
if(j==(obj.length-1)){    
        document.getElementById("all").checked=true; 
    }else{  
        document.getElementById("all").checked=false;     
    } 
}
