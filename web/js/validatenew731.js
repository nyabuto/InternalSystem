/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validateHTPS(){
var checker=0;
 
   var HV0101=document.getElementById("HV0101").value;
   var HV0102=document.getElementById("HV0102").value;
   var HV0103=document.getElementById("HV0103").value;
   var HV0104=document.getElementById("HV0104").value;
   var HV0105=document.getElementById("HV0105").value;
   var HV0106=document.getElementById("HV0106").value;
   var HV0107=document.getElementById("HV0107").value;
   var HV0108=document.getElementById("HV0108").value;
   var HV0109=document.getElementById("HV0109").value;
   var HV0110=document.getElementById("HV0110").value;
   var HV0111=document.getElementById("HV0111").value;
   var HV0112=document.getElementById("HV0112").value;
   var HV0113=document.getElementById("HV0113").value;
   var HV0114=document.getElementById("HV0114").value;
   var HV0115=document.getElementById("HV0115").value;
   var HV0116=document.getElementById("HV0116").value; 
   var HV0117=document.getElementById("HV0117").value;
   var HV0118=document.getElementById("HV0118").value;
   var HV0119=document.getElementById("HV0119").value;
   var HV0120=document.getElementById("HV0120").value;
   var HV0121=document.getElementById("HV0121").value;
   var HV0122=document.getElementById("HV0122").value;
   var HV0123=document.getElementById("HV0123").value;
   var HV0124=document.getElementById("HV0124").value;
   var HV0125=document.getElementById("HV0125").value;
   var HV0126=document.getElementById("HV0126").value;
   var HV0127=document.getElementById("HV0127").value;
   var HV0128=document.getElementById("HV0128").value;
   var HV0129=document.getElementById("HV0129").value;
   var HV0130=document.getElementById("HV0130").value;
   var HV0131=document.getElementById("HV0131").value; 
   var HV0132=document.getElementById("HV0132").value;
   var HV0133=document.getElementById("HV0133").value;
   var HV0134=document.getElementById("HV0134").value;
   var HV0135=document.getElementById("HV0135").value;
   var HV0136=document.getElementById("HV0136").value;
   var HV0137=document.getElementById("HV0137").value;
   var HV0138=document.getElementById("HV0138").value;
   var HV0139=document.getElementById("HV0139").value;
   var HV0140=document.getElementById("HV0140").value; 
   
if(HV0101==""){HV0101="0";}
if(HV0102==""){HV0102="0";}
if(HV0103==""){HV0103="0";}
if(HV0104==""){HV0104="0";}
if(HV0105==""){HV0105="0";}
if(HV0106==""){HV0106="0";}
if(HV0107==""){HV0107="0";}
if(HV0108==""){HV0108="0";}
if(HV0109==""){HV0109="0";}
if(HV0110==""){HV0110="0";}
if(HV0111==""){HV0111="0";}
if(HV0112==""){HV0112="0";}
if(HV0113==""){HV0113="0";}
if(HV0114==""){HV0114="0";}
if(HV0115==""){HV0115="0";}
if(HV0116==""){HV0116="0";}    
if(HV0117==""){HV0117="0";}
if(HV0118==""){HV0118="0";}
if(HV0119==""){HV0119="0";}
if(HV0120==""){HV0120="0";}
if(HV0121==""){HV0121="0";}
if(HV0122==""){HV0122="0";}
if(HV0123==""){HV0123="0";}
if(HV0124==""){HV0124="0";}
if(HV0125==""){HV0125="0";}
if(HV0126==""){HV0126="0";}
if(HV0127==""){HV0127="0";}
if(HV0128==""){HV0128="0";}
if(HV0129==""){HV0129="0";}
if(HV0130==""){HV0130="0";}
if(HV0131==""){HV0131="0";}    
if(HV0132==""){HV0132="0";}
if(HV0133==""){HV0133="0";}
if(HV0134==""){HV0134="0";}
if(HV0135==""){HV0135="0";}
if(HV0136==""){HV0136="0";}
if(HV0137==""){HV0137="0";}
if(HV0138==""){HV0138="0";}   
if(HV0139==""){HV0139="0";}   
if(HV0140==""){HV0140="0";}   

var i=1;
while(i<40){
    if(i<10){
 $("#HV010"+i).css({'background-color' : 'white'});
// $("#HV010"+i).removeProp('title');
    }
    else{
  $("#HV01"+i).css({'background-color' : 'white'});
//  $("#HV01"+i).removeProp('title');
    }
i++;   
}
//checking           
if(parseInt(HV0101) > parseInt(HV0110)){checker++;
   $("#HV0101").css({'background-color' : 'yellow'});
   $("#HV0101").prop('title', 'HV0101 should not be more than HV0110');
}
if(parseInt(HV0102) > parseInt(HV0110)){checker++;
   $("#HV0102").css({'background-color' : 'yellow'});
   $("#HV0102").prop('title', 'HV0102 should not be more than HV0110');
}
if(parseInt(HV0103) > parseInt(HV0110)){checker++;
   $("#HV0103").css({'background-color' : 'yellow'});
   $("#HV0103").prop('title', 'HV0103 should not be more than HV0110');
}
if(parseInt(HV0104) > parseInt(HV0110)){checker++;
   $("#HV0104").css({'background-color' : 'yellow'});
   $("#HV0104").prop('title', 'HV0104 should not be more than HV0110');
}
if(parseInt(HV0105) > parseInt(HV0110)){checker++;
   $("#HV0105").css({'background-color' : 'yellow'});
   $("#HV0105").prop('title', 'HV0105 should not be more than HV0110');
}
if(parseInt(HV0106) > parseInt(HV0110)){checker++;
   $("#HV0106").css({'background-color' : 'yellow'});
   $("#HV0106").prop('title', 'HV0106 should not be more than HV0110');
}
if(parseInt(HV0107) > parseInt(HV0110)){checker++;
   $("#HV0107").css({'background-color' : 'yellow'});
   $("#HV0107").prop('title', 'HV0107 should not be more than HV0110');
}
if(parseInt(HV0108) > parseInt(HV0110)){checker++;
   $("#HV0108").css({'background-color' : 'yellow'});
   $("#HV0108").prop('title', 'HV0108 should not be more than HV0110');
}
if(parseInt(HV0109) > parseInt(HV0110)){checker++;
   $("#HV0109").css({'background-color' : 'yellow'});
   $("#HV0109").prop('title', 'HV0109 should not be more than HV0110');
}
if((parseInt(HV0110) != (parseInt(HV0101)+parseInt(HV0102)+parseInt(HV0103)+parseInt(HV0104)+parseInt(HV0105)+parseInt(HV0106)+parseInt(HV0107)+parseInt(HV0108)+parseInt(HV0109)))){checker++;
   $("#HV0110").css({'background-color' : 'yellow'});
   $("#HV0110").prop('title', 'HV0110 should be equal to the sum of HV0101 to HV0109');
}
if( parseInt(HV0110) != (parseInt(HV0111)+ parseInt(HV0112))){checker++;
   $("#HV0110").css({'background-color' : 'yellow'});
   $("#HV0110").prop('title', 'HV0110 should be equal to the sum of HV0111 and HV0112');
}


//SECONDARY
if(parseInt(HV0110) != (parseInt(HV0113)+parseInt(HV0114))){checker++;
   $("#HV0110").css({'background-color' : 'yellow'});
   $("#HV0110").prop('title', 'HV0110 should be equal to the sum of  HV0113 and HV0114');
}

//OTHER VALIDATIONS
if(parseInt(HV0110) != (parseInt(HV0126)+parseInt(HV0127))){checker++;
   $("#HV0110").css({'background-color' : 'yellow'});
   $("#HV0110").prop('title', 'HV0110 should  be equal to the sum of HV0126 and HV0127');
}

//END
if(parseInt(HV0111) > parseInt(HV0110)){checker++;
   $("#HV0111").css({'background-color' : 'yellow'});
   $("#HV0111").prop('title', 'HV0111 should not be more than HV0110');
}
if(parseInt(HV0112) > parseInt(HV0110)){checker++;
   $("#HV0112").css({'background-color' : 'yellow'});
   $("#HV0112").prop('title', 'HV0112 should not be more than HV0110');
}
if(parseInt(HV0113) > parseInt(HV0110)){checker++;
   $("#HV0113").css({'background-color' : 'yellow'});
   $("#HV0113").prop('title', 'HV0113 should not be more than HV0110');
}
if(parseInt(HV0114) > parseInt(HV0110)){checker++;
   $("#HV0114").css({'background-color' : 'yellow'});
   $("#HV0114").prop('title', 'HV0114 should not be more than HV0110');
}
if(parseInt(HV0115) > parseInt(HV0110)){checker++;
   $("#HV0115").css({'background-color' : 'yellow'});
   $("#HV0115").prop('title', 'HV0115 should not be more than HV0110');
}
if(parseInt(HV0116) > parseInt(HV0110)){checker++;
   $("#HV0116").css({'background-color' : 'yellow'});
   $("#HV0116").prop('title', 'HV0116 should not be more than HV0110');
}

//1.2
if(parseInt(HV0117) > parseInt(HV0126)){checker++;
   $("#HV0117").css({'background-color' : 'yellow'});
   $("#HV0117").prop('title', 'HV0117 should not be more than HV0126');
}
if(parseInt(HV0118) > parseInt(HV0126)){checker++;
   $("#HV0118").css({'background-color' : 'yellow'});
   $("#HV0118").prop('title', 'HV0118 should not be more than HV0126');
}
if(parseInt(HV0119) > parseInt(HV0126)){checker++;
   $("#HV0119").css({'background-color' : 'yellow'});
   $("#HV0119").prop('title', 'HV0119 should not be more than HV0126');
}
if(parseInt(HV0120) > parseInt(HV0126)){checker++;
   $("#HV0120").css({'background-color' : 'yellow'});
   $("#HV0120").prop('title', 'HV0120 should not be more than HV0126');
}
if(parseInt(HV0121) > parseInt(HV0126)){checker++;
   $("#HV0121").css({'background-color' : 'yellow'});
   $("#HV0121").prop('title', 'HV0121 should not be more than HV0126');
}
if(parseInt(HV0122) > parseInt(HV0126)){checker++;
   $("#HV0122").css({'background-color' : 'yellow'});
   $("#HV0122").prop('title', 'HV0122 should not be more than HV0126');
}
if(parseInt(HV0123) > parseInt(HV0126)){checker++;
   $("#HV0123").css({'background-color' : 'yellow'});
   $("#HV0123").prop('title', 'HV0123 should not be more than HV0126');
}
if(parseInt(HV0124) > parseInt(HV0126)){checker++;
   $("#HV0124").css({'background-color' : 'yellow'});
   $("#HV0124").prop('title', 'HV0124 should not be more than HV0126');
}
if(parseInt(HV0125) > parseInt(HV0126)){checker++;
   $("#HV0125").css({'background-color' : 'yellow'});
   $("#HV0125").prop('title', 'HV0125 should not be more than HV0126');
}
if(parseInt(HV0126) >= parseInt(HV0110)){checker++;
   $("#HV0126").css({'background-color' : 'yellow'});
   $("#HV0126").prop('title', 'HV0126 should be less than HV0110');
}
if(parseInt(HV0127) > parseInt(HV0110)){checker++;
   $("#HV0127").css({'background-color' : 'yellow'});
   $("#HV0127").prop('title', 'HV0127 should not be more than HV0110');
}
if(parseInt(HV0128) > parseInt(HV0115)){checker++;
   $("#HV0128").css({'background-color' : 'yellow'});
   $("#HV0128").prop('title', 'HV0128 should not be more than HV0115');
}
if(parseInt(HV0129) > parseInt(HV0116)){checker++;
   $("#HV0129").css({'background-color' : 'yellow'});
   $("#HV0129").prop('title', 'HV0129 should not be more than HV0116');
}
if(parseInt(HV0126) != (parseInt(HV0117)+parseInt(HV0118)+parseInt(HV0119)+parseInt(HV0120)+parseInt(HV0121)+parseInt(HV0122)+parseInt(HV0123)+parseInt(HV0124)+parseInt(HV0125))){checker++;
   $("#HV0126").css({'background-color' : 'yellow'});
   $("#HV0126").prop('title', 'HV0126 should be equal to the sum of (HV0117 to HV0125)');
}
//1.3
if(parseInt(HV0130) > parseInt(HV0117)){checker++;
   $("#HV0130").css({'background-color' : 'yellow'});
   $("#HV0130").prop('title', 'HV0130 should not be more than HV0117');
}
if(parseInt(HV0131) > (parseInt(HV0118)+parseInt(HV0119))){checker++;
   $("#HV0131").css({'background-color' : 'yellow'});
   $("#HV0131").prop('title', 'HV0131 should not be more than the sum of HV0118 and HV0118');
}
if(parseInt(HV0132) > (parseInt(HV0120)+parseInt(HV0121))){checker++;
   $("#HV0132").css({'background-color' : 'yellow'});
   $("#HV0132").prop('title', 'HV01 should not be more than the sum of HV0120 and HV0121');
}
if(parseInt(HV0133) > (parseInt(HV0122)+parseInt(HV0123))){checker++;
   $("#HV0133").css({'background-color' : 'yellow'});
   $("#HV0133").prop('title', 'HV0133 should not be more than the sum of HV0122 and HV0123');
}
if(parseInt(HV0134) > (parseInt(HV0124)+parseInt(HV0125))){checker++;
   $("#HV0134").css({'background-color' : 'yellow'});
   $("#HV0134").prop('title', 'HV0134 should not be more than the sum of HV0124 and HV0125');
}
if(parseInt(HV0135) > parseInt(HV0126)){checker++;
   $("#HV0135").css({'background-color' : 'yellow'});
   $("#HV0135").prop('title', 'HV0135 should not be more than HV0126');
}
if(parseInt(HV0136) != parseInt(HV0126)){checker++;
   $("#HV0136").css({'background-color' : 'yellow'});
   $("#HV0136").prop('title', 'HV0136 should be equal to HV0126');
}
if(parseInt(HV0136) < parseInt(HV0135)){checker++;
   $("#HV0136").css({'background-color' : 'yellow'});
   $("#HV0136").prop('title', 'HV0136 should be less than HV0135');
}
if(parseInt(HV0135) != (parseInt(HV0130)+parseInt(HV0131)+parseInt(HV0132)+parseInt(HV0133)+parseInt(HV0134))){checker++;
   $("#HV0135").css({'background-color' : 'yellow'});
   $("#HV0135").prop('title', 'HV0135 should be equal to the sum of (HV0130 to HV0134)');
}
//1.4 
if(parseInt(HV0138) > parseInt(HV0137)){checker++;
 $("#HV0138").css({'background-color' : 'yellow'});
 $("#HV0138").prop('title', 'HV0138 should not be more than HV0137');
}

//if(parseInt(HV0145) != (parseInt(HV0137)+parseInt(HV0144)+parseInt(HV0132)+parseInt(HV0133)+parseInt(HV0134))){checker++;
//   $("#HV0135").css({'background-color' : 'yellow'});
//   $("#HV0135").prop('title', 'HV0135 should be equal to the sum of (HV0130 to HV0134)');
//}

 return checker;
}

function validatePMTCT(){
var checker=0;
 
   var HV0201=document.getElementById("HV0201").value;
   var HV0202=document.getElementById("HV0202").value;
   var HV0203=document.getElementById("HV0203").value;
   var HV0204=document.getElementById("HV0204").value;
   var HV0205=document.getElementById("HV0205").value;
   var HV0206=document.getElementById("HV0206").value;
   var HV0207=document.getElementById("HV0207").value;
   var HV0208=document.getElementById("HV0208").value;
   var HV0209=document.getElementById("HV0209").value;
   var HV0210=document.getElementById("HV0210").value;
   var HV0211=document.getElementById("HV0211").value;
   var HV0212=document.getElementById("HV0212").value;
   var HV0213=document.getElementById("HV0213").value;
   var HV0214=document.getElementById("HV0214").value;
   var HV0215=document.getElementById("HV0215").value;
   var HV0216=document.getElementById("HV0216").value; 
   var HV0217=document.getElementById("HV0217").value;
   var HV0218=document.getElementById("HV0218").value;
   var HV0219=document.getElementById("HV0219").value;
   var HV0220=document.getElementById("HV0220").value;
   var HV0221=document.getElementById("HV0221").value;
   var HV0222=document.getElementById("HV0222").value;
   var HV0223=document.getElementById("HV0223").value;
   var HV0224=document.getElementById("HV0224").value;
   var HV0225=document.getElementById("HV0225").value;
   var HV0226=document.getElementById("HV0226").value;
   var HV0227=document.getElementById("HV0227").value;
   var HV0228=document.getElementById("HV0228").value;
   var HV0229=document.getElementById("HV0229").value;
   var HV0230=document.getElementById("HV0230").value;
   var HV0231=document.getElementById("HV0231").value; 
   var HV0232=document.getElementById("HV0232").value;
   var HV0233=document.getElementById("HV0233").value;
   var HV0234=document.getElementById("HV0234").value;
   var HV0235=document.getElementById("HV0235").value;
   var HV0236=document.getElementById("HV0236").value;
   var HV0237=document.getElementById("HV0237").value;
   var HV0238=document.getElementById("HV0238").value;
   var HV0239=document.getElementById("HV0239").value;
   var HV0240=document.getElementById("HV0240").value; 
   var HV0241=document.getElementById("HV0241").value;
   var HV0242=document.getElementById("HV0242").value;
   var HV0243=document.getElementById("HV0243").value;
   var HV0244=document.getElementById("HV0244").value;
   var HV0245=document.getElementById("HV0245").value;
   var HV0246=document.getElementById("HV0246").value;
   var HV0247=document.getElementById("HV0247").value;
   var HV0248=document.getElementById("HV0248").value; 
   var HV0249=document.getElementById("HV0249").value;
   var HV0250=document.getElementById("HV0250").value;
   var HV0251=document.getElementById("HV0251").value;
   var HV0252=document.getElementById("HV0252").value;
   var HV0253=document.getElementById("HV0253").value;
   var HV0254=document.getElementById("HV0254").value;
   var HV0255=document.getElementById("HV0255").value;
   var HV0256=document.getElementById("HV0256").value;
   
if(HV0201==""){HV0201="0";}
if(HV0202==""){HV0202="0";}
if(HV0203==""){HV0203="0";}
if(HV0204==""){HV0204="0";}
if(HV0205==""){HV0205="0";}
if(HV0206==""){HV0206="0";}
if(HV0207==""){HV0207="0";}
if(HV0208==""){HV0208="0";}
if(HV0209==""){HV0209="0";}
if(HV0210==""){HV0210="0";}
if(HV0211==""){HV0211="0";}
if(HV0212==""){HV0212="0";}
if(HV0213==""){HV0213="0";}
if(HV0214==""){HV0214="0";}
if(HV0215==""){HV0215="0";}
if(HV0216==""){HV0216="0";}    
if(HV0217==""){HV0217="0";}
if(HV0218==""){HV0218="0";}
if(HV0219==""){HV0219="0";}
if(HV0220==""){HV0220="0";}
if(HV0221==""){HV0221="0";}
if(HV0222==""){HV0222="0";}
if(HV0223==""){HV0223="0";}
if(HV0224==""){HV0224="0";}
if(HV0225==""){HV0225="0";}
if(HV0226==""){HV0226="0";}
if(HV0227==""){HV0227="0";}
if(HV0228==""){HV0228="0";}
if(HV0229==""){HV0229="0";}
if(HV0230==""){HV0230="0";}
if(HV0231==""){HV0231="0";}    
if(HV0232==""){HV0232="0";}
if(HV0233==""){HV0233="0";}
if(HV0234==""){HV0234="0";}
if(HV0235==""){HV0235="0";}
if(HV0236==""){HV0236="0";}
if(HV0237==""){HV0237="0";}
if(HV0238==""){HV0238="0";}   
if(HV0239==""){HV0239="0";}   
if(HV0240==""){HV0240="0";}   
if(HV0241==""){HV0241="0";}
if(HV0242==""){HV0242="0";}
if(HV0243==""){HV0243="0";}
if(HV0244==""){HV0244="0";}
if(HV0245==""){HV0245="0";}
if(HV0246==""){HV0246="0";}
if(HV0247==""){HV0247="0";}    
if(HV0248==""){HV0248="0";}
if(HV0249==""){HV0249="0";}
if(HV0250==""){HV0250="0";}
if(HV0251==""){HV0251="0";}
if(HV0252==""){HV0252="0";}
if(HV0253==""){HV0253="0";}
if(HV0254==""){HV0254="0";}   
if(HV0255==""){HV0255="0";}   
if(HV0256==""){HV0256="0";}   


var i=1;
while(i<56){
    if(i<10){
 $("#HV020"+i).css({'background-color' : 'white'});
    }
    else{
  $("#HV02"+i).css({'background-color' : 'white'});       
    }
i++;   
}
//  2.1
//others

//if(parseInt(HV0201) > parseInt(HV0137)){checker++;
// $("#HV0201").css({'background-color' : 'yellow'});
//}
//if(parseInt(HV0202) > parseInt(HV0137)){checker++;
// $("#HV0202").css({'background-color' : 'yellow'});
//}
  
//2.2
if(parseInt(HV0203) > parseInt(HV0207)){checker++;
 $("#HV0203").css({'background-color' : 'yellow'});
 $("#HV0203").prop('title', 'HV0203 should not be more than HV0207');
}
if(parseInt(HV0204) > parseInt(HV0207)){checker++;
 $("#HV0204").css({'background-color' : 'yellow'});
 $("#HV0204").prop('title', 'HV0204 should not be more than HV0207');
}
if(parseInt(HV0205) > parseInt(HV0207)){checker++;
 $("#HV0205").css({'background-color' : 'yellow'});
  $("#HV0205").prop('title', 'HV0205 should not be more than HV0207');
}
if(parseInt(HV0206) > parseInt(HV0207)){checker++;
 $("#HV0206").css({'background-color' : 'yellow'});
  $("#HV0206").prop('title', 'HV0206 should not be more than HV0207');
}
if(parseInt(HV0207) != (parseInt(HV0203)+parseInt(HV0204)+parseInt(HV0205)+parseInt(HV0206))){checker++;
 $("#HV0207").css({'background-color' : 'yellow'});
  $("#HV0207").prop('title', 'HV0207 should be equal to the sum of HV0203 to HV0206');
}

//2.3
if(parseInt(HV0210) != parseInt(HV0203)){checker++;
 $("#HV0210").css({'background-color' : 'yellow'});
  $("#HV0210").prop('title', 'HV02 should be equal to HV0203');
}
if(parseInt(HV0211) >= parseInt(HV0204)){checker++;
 $("#HV0211").css({'background-color' : 'yellow'});
 $("#HV0211").prop('title', 'HV0211 should be less than HV0204'); 
}
if(parseInt(HV0212) >= parseInt(HV0205)){checker++;
 $("#HV0212").css({'background-color' : 'yellow'});
  $("#HV0212").prop('title', 'HV0212 should be less than HV0205');
}
if(parseInt(HV0213) >= parseInt(HV0206)){checker++;
 $("#HV0213").css({'background-color' : 'yellow'});
  $("#HV0213").prop('title', 'HV0213 should be less than HV0206');
}
if(parseInt(HV0214) >= parseInt(HV0207)){checker++;
 $("#HV0214").css({'background-color' : 'yellow'});
  $("#HV0214").prop('title', 'HV0214 should be less than HV0207');
}
if(parseInt(HV0215) >= parseInt(HV0209)){checker++;
 $("#HV0215").css({'background-color' : 'yellow'});
  $("#HV0215").prop('title', 'HV0215 should be less than HV0209');
}

//2.4

if(parseInt(HV0216) >= parseInt(HV0210)){checker++;
 $("#HV0216").css({'background-color' : 'yellow'});
  $("#HV0216").prop('title', 'HV0211 should be less than HV0210');
}
if(parseInt(HV0217) >= parseInt(HV0211)){checker++;
 $("#HV0217").css({'background-color' : 'yellow'});
  $("#HV0217").prop('title', 'HV0217 should be less than HV0211');
}
if(parseInt(HV0218) >= parseInt(HV0212)){checker++;
 $("#HV0218").css({'background-color' : 'yellow'});
  $("#HV0218").prop('title', 'HV0218 should be less than HV0212');
}
if(parseInt(HV0219) >= parseInt(HV0213)){checker++;
 $("#HV0219").css({'background-color' : 'yellow'});
  $("#HV0219").prop('title', 'HV0219 should be less than HV0213');
}
if(parseInt(HV0220) >= parseInt(HV0214)){checker++;
 $("#HV0220").css({'background-color' : 'yellow'});
  $("#HV0220").prop('title', 'HV0220 should be less than HV0214');
}
if(parseInt(HV0221) >= parseInt(HV0215)){checker++;
 $("#HV0221").css({'background-color' : 'yellow'});
  $("#HV0221").prop('title', 'HV0221 should be less than HV0215');
}

//2.5
if(parseInt(HV0222) > parseInt(HV0223)){checker++;
 $("#HV0222").css({'background-color' : 'yellow'});
  $("#HV0222").prop('title', 'HV0222 should not be more than HV0223');
}

//2.6
if(parseInt(HV0224) >= parseInt(HV0211)){checker++;
 $("#HV0224").css({'background-color' : 'yellow'});
 $("#HV0224").prop('title', 'HV0224 should be less than HV0211');
}
if(parseInt(HV0225) >= parseInt(HV0224)){checker++;
 $("#HV0225").css({'background-color' : 'yellow'});
  $("#HV0225").prop('title', 'HV0225 should be less than HV0242');
}
if(parseInt(HV0226) > parseInt(HV0225)){checker++;
 $("#HV0226").css({'background-color' : 'yellow'});
  $("#HV0226").prop('title', 'HV0226 should not be more than HV0225');
}
//2.7
if(parseInt(HV0227) >= parseInt(HV0206)){checker++;
 $("#HV0227").css({'background-color' : 'yellow'});
  $("#HV0227").prop('title', 'HV0227 should be less than HV0206');
}
if(parseInt(HV0228) != parseInt(HV0213)){checker++;
 $("#HV0228").css({'background-color' : 'yellow'});
  $("#HV0228").prop('title', 'HV0228 should be equal to HV0213');
}

//2.8
if(parseInt(HV0229) >= parseInt(HV0210)){checker++;
 $("#HV0229").css({'background-color' : 'yellow'});
  $("#HV0229").prop('title', 'HV0229 should be less than HV0210');
}
if(parseInt(HV0230) >= parseInt(HV0204)){checker++;
 $("#HV0230").css({'background-color' : 'yellow'});
  $("#HV0230").prop('title', 'HV0230 should be less than HV0204');
}
if(parseInt(HV0231) >= parseInt(HV0205)){checker++;
 $("#HV0231").css({'background-color' : 'yellow'});
  $("#HV0231").prop('title', 'HV0231 should be less than HV0205');
}
if(parseInt(HV0232) >= parseInt(HV0207)){checker++;
 $("#HV0232").css({'background-color' : 'yellow'});
  $("#HV0232").prop('title', 'HV0232 should be less than HV0207');
}

//2.9
if(parseInt(HV0233) >= parseInt(HV0210)){checker++;
 $("#HV0233").css({'background-color' : 'yellow'});
  $("#HV0233").prop('title', 'HV0233 should be less than HV0210');
}
if(parseInt(HV0234) >= (parseInt(HV0214)+parseInt(HV0215))){checker++;
 $("#HV0234").css({'background-color' : 'yellow'});
  $("#HV0234").prop('title', 'HV0234 should be equal to the sum of HV0215 and HV015');
}
if(parseInt(HV0235) > parseInt(HV0234)){checker++;
 $("#HV0235").css({'background-color' : 'yellow'});
  $("#HV0235").prop('title', 'HV02 should not be more than HV0234');
}

//2.10
if(parseInt(HV0237) > parseInt(HV0236)){checker++;
 $("#HV0237").css({'background-color' : 'yellow'});
 $("#HV0237").prop('title', 'HV0237 should not be more than HV0236');
}

//2.11
if(parseInt(HV0238) > parseInt(HV0211)){checker++;
 $("#HV0238").css({'background-color' : 'yellow'});
 $("#HV0238").prop('title', 'HV0238 should not be more than HV0211');
}
if(parseInt(HV0239) > parseInt(HV0212)){checker++;
 $("#HV0239").css({'background-color' : 'yellow'});
 $("#HV0239").prop('title', 'HV0239 should not be more than HV0212');
}
if(parseInt(HV0240) > parseInt(HV0213)){checker++;
 $("#HV0240").css({'background-color' : 'yellow'});
 $("#HV0240").prop('title', 'HV0240 should not be more than HV0213');
}
if(parseInt(HV0241) >= parseInt(HV0214)){checker++;
 $("#HV0241").css({'background-color' : 'yellow'});
 $("#HV0241").prop('title', 'HV0241 should be less than HV0214');
}

//2.12
if(parseInt(HV0243) >= parseInt(HV0245)){checker++;
 $("#HV0243").css({'background-color' : 'yellow'});
 $("#HV0243").prop('title', 'HV0243 should be less than HV0245');
}
if(parseInt(HV0244) >= parseInt(HV0245)){checker++;
 $("#HV0244").css({'background-color' : 'yellow'});
 $("#HV0244").prop('title', 'HV0244 should be less than HV0245');
}
if(parseInt(HV0245) != (parseInt(HV0243)+parseInt(HV0244))){checker++;
 $("#HV0245").css({'background-color' : 'yellow'});
 $("#HV0245").prop('title', 'HV0245 should be equal to the sum of HV0244 and HV0244');
}

//2.13
if(parseInt(HV0246) > parseInt(HV0249)){checker++;
 $("#HV0246").css({'background-color' : 'yellow'});
 $("#HV0246").prop('title', 'HV0246 should not be more than HV0249');
}
if(parseInt(HV0247) > parseInt(HV0249)){checker++;
 $("#HV0247").css({'background-color' : 'yellow'});
 $("#HV0247").prop('title', 'HV0247 should not be more than HV0249');
}
if(parseInt(HV0248) > parseInt(HV0249)){checker++;
 $("#HV0248").css({'background-color' : 'yellow'});
 $("#HV0248").prop('title', 'HV0248 should not be more than HV0249');
}
if(parseInt(HV0249) != (parseInt(HV0246)+parseInt(HV0247)+parseInt(HV0248))){checker++;
 $("#HV0249").css({'background-color' : 'yellow'});
 $("#HV0249").prop('title', 'HV0249 should be equal to the sum of HV0246 to HV0248');
}

//2.14
if(parseInt(HV0250) > parseInt(HV0249)){checker++;
 $("#HV0250").css({'background-color' : 'yellow'});
 $("#HV0250").prop('title', 'HV0250 should not be more than HV0249');
}
if(parseInt(HV0251) < parseInt(HV0250)){checker++;
 $("#HV0251").css({'background-color' : 'yellow'});
 $("#HV0251").prop('title', 'HV0251 should not be less than HV0250');
}

//2.15
if(parseInt(HV0252) > parseInt(HV0245)){checker++;
 $("#HV0252").css({'background-color' : 'yellow'});
 $("#HV0252").prop('title', 'HV0252 should not be more than HV0245');
}
if(parseInt(HV0253) > parseInt(HV0245)){checker++;
 $("#HV0253").css({'background-color' : 'yellow'});
 $("#HV0253").prop('title', 'HV0253 should not be more than HV0245');
}
if(parseInt(HV0254) > parseInt(HV0245)){checker++;
 $("#HV0254").css({'background-color' : 'yellow'});
 $("#HV0254").prop('title', 'HV0254 should not be more than HV0245');
}
if(parseInt(HV0255) > parseInt(HV0245)){checker++;
 $("#HV0255").css({'background-color' : 'yellow'});
 $("#HV0255").prop('title', 'HV0255 should not be more than HV0245');
}
if(parseInt(HV0256) > parseInt(HV0245)){checker++;
 $("#HV0256").css({'background-color' : 'yellow'});
 $("#HV0256").prop('title', 'HV0256 should not be more than HV0245');
}
//end of all 2.xxxxxxxxx
 return checker;
}

function validateHIVTB(){
 var checker=0;
 
   var HV03001=document.getElementById("HV03001").value;
   var HV03002=document.getElementById("HV03002").value;
   var HV03003=document.getElementById("HV03003").value;
   var HV03004=document.getElementById("HV03004").value;
   var HV03005=document.getElementById("HV03005").value;
   var HV03006=document.getElementById("HV03006").value;
   var HV03007=document.getElementById("HV03007").value;
   var HV03008=document.getElementById("HV03008").value;
   var HV03009=document.getElementById("HV03009").value;
   var HV03010=document.getElementById("HV03010").value;
   var HV03011=document.getElementById("HV03011").value;
   var HV03012=document.getElementById("HV03012").value;
   var HV03013=document.getElementById("HV03013").value;
   var HV03014=document.getElementById("HV03014").value;
   var HV03015=document.getElementById("HV03015").value;
   var HV03016=document.getElementById("HV03016").value; 
   var HV03017=document.getElementById("HV03017").value;
   var HV03018=document.getElementById("HV03018").value;
   var HV03019=document.getElementById("HV03019").value;
   var HV03020=document.getElementById("HV03020").value;
   var HV03021=document.getElementById("HV03021").value;
   var HV03022=document.getElementById("HV03022").value;
   var HV03023=document.getElementById("HV03023").value;
   var HV03024=document.getElementById("HV03024").value;
   var HV03025=document.getElementById("HV03025").value;
   var HV03026=document.getElementById("HV03026").value;
   var HV03027=document.getElementById("HV03027").value;
   var HV03028=document.getElementById("HV03028").value;
   var HV03029=document.getElementById("HV03029").value;
   var HV03030=document.getElementById("HV03030").value;
   var HV03031=document.getElementById("HV03031").value; 
   var HV03032=document.getElementById("HV03032").value;
   var HV03033=document.getElementById("HV03033").value;
   var HV03034=document.getElementById("HV03034").value;
   var HV03035=document.getElementById("HV03035").value;
   var HV03036=document.getElementById("HV03036").value;
   var HV03037=document.getElementById("HV03037").value;
   var HV03038=document.getElementById("HV03038").value;
   var HV03039=document.getElementById("HV03039").value;
   var HV03040=document.getElementById("HV03040").value; 
   var HV03041=document.getElementById("HV03041").value;
   var HV03042=document.getElementById("HV03042").value;
   var HV03043=document.getElementById("HV03043").value;
   var HV03044=document.getElementById("HV03044").value;
   var HV03045=document.getElementById("HV03045").value;
   var HV03046=document.getElementById("HV03046").value;
   var HV03047=document.getElementById("HV03047").value;
   var HV03048=document.getElementById("HV03048").value; 
   var HV03049=document.getElementById("HV03049").value;
   var HV03050=document.getElementById("HV03050").value;
   var HV03051=document.getElementById("HV03051").value;
   var HV03052=document.getElementById("HV03052").value;
   var HV03053=document.getElementById("HV03053").value;
   var HV03054=document.getElementById("HV03054").value;
   var HV03055=document.getElementById("HV03055").value;
   var HV03056=document.getElementById("HV03056").value;
   var HV03057=document.getElementById("HV03057").value;
   var HV03058=document.getElementById("HV03058").value;
   var HV03059=document.getElementById("HV03059").value;
   var HV03060=document.getElementById("HV03060").value;
   var HV03061=document.getElementById("HV03061").value; 
   var HV03062=document.getElementById("HV03062").value;
   var HV03063=document.getElementById("HV03063").value;
   var HV03064=document.getElementById("HV03064").value;
   var HV03065=document.getElementById("HV03065").value;
   var HV03066=document.getElementById("HV03066").value;
   var HV03067=document.getElementById("HV03067").value;
   var HV03068=document.getElementById("HV03068").value;
   var HV03069=document.getElementById("HV03069").value;
   var HV03070=document.getElementById("HV03070").value; 
   var HV03071=document.getElementById("HV03071").value;
   var HV03072=document.getElementById("HV03072").value;
   var HV03073=document.getElementById("HV03073").value;
   var HV03074=document.getElementById("HV03074").value;
   var HV03075=document.getElementById("HV03075").value;
   var HV03076=document.getElementById("HV03076").value;
   var HV03077=document.getElementById("HV03077").value;
   var HV03078=document.getElementById("HV03078").value; 
   var HV03079=document.getElementById("HV03079").value;
   var HV03080=document.getElementById("HV03080").value;
   var HV03081=document.getElementById("HV03081").value;
   var HV03082=document.getElementById("HV03082").value;
   var HV03083=document.getElementById("HV03083").value;
   var HV03084=document.getElementById("HV03084").value;
   var HV03085=document.getElementById("HV03085").value;
   var HV03086=document.getElementById("HV03086").value;
   var HV03087=document.getElementById("HV03087").value;
   var HV03088=document.getElementById("HV03088").value;
   var HV03089=document.getElementById("HV03089").value;
   
if(HV03001==""){HV03001="0";}
if(HV03002==""){HV03002="0";}
if(HV03003==""){HV03003="0";}
if(HV03004==""){HV03004="0";}
if(HV03005==""){HV03005="0";}
if(HV03006==""){HV03006="0";}
if(HV03007==""){HV03007="0";}
if(HV03008==""){HV03008="0";}
if(HV03009==""){HV03009="0";}
if(HV03010==""){HV03010="0";}
if(HV03011==""){HV03011="0";}
if(HV03012==""){HV03012="0";}
if(HV03013==""){HV03013="0";}
if(HV03014==""){HV03014="0";}
if(HV03015==""){HV03015="0";}
if(HV03016==""){HV03016="0";}    
if(HV03017==""){HV03017="0";}
if(HV03018==""){HV03018="0";}
if(HV03019==""){HV03019="0";}
if(HV03020==""){HV03020="0";}
if(HV03021==""){HV03021="0";}
if(HV03022==""){HV03022="0";}
if(HV03023==""){HV03023="0";}
if(HV03024==""){HV03024="0";}
if(HV03025==""){HV03025="0";}
if(HV03026==""){HV03026="0";}
if(HV03027==""){HV03027="0";}
if(HV03028==""){HV03028="0";}
if(HV03029==""){HV03029="0";}
if(HV03030==""){HV03030="0";}
if(HV03031==""){HV03031="0";}    
if(HV03032==""){HV03032="0";}
if(HV03033==""){HV03033="0";}
if(HV03034==""){HV03034="0";}
if(HV03035==""){HV03035="0";}
if(HV03036==""){HV03036="0";}
if(HV03037==""){HV03037="0";}
if(HV03038==""){HV03038="0";}   
if(HV03039==""){HV03039="0";}   
if(HV03040==""){HV03040="0";}   
if(HV03041==""){HV03041="0";}
if(HV03042==""){HV03042="0";}
if(HV03043==""){HV03043="0";}
if(HV03044==""){HV03044="0";}
if(HV03045==""){HV03045="0";}
if(HV03046==""){HV03046="0";}
if(HV03047==""){HV03047="0";}    
if(HV03048==""){HV03048="0";}
if(HV03049==""){HV03049="0";}
if(HV03050==""){HV03050="0";}
if(HV03051==""){HV03051="0";}
if(HV03052==""){HV03052="0";}
if(HV03053==""){HV03053="0";}
if(HV03054==""){HV03054="0";}   
if(HV03055==""){HV03055="0";}   
if(HV03056==""){HV03056="0";}   
if(HV03057==""){HV03057="0";}
if(HV03058==""){HV03058="0";}
if(HV03059==""){HV03059="0";}
if(HV03060==""){HV03060="0";}
if(HV03061==""){HV03061="0";}    
if(HV03062==""){HV03062="0";}
if(HV03063==""){HV03063="0";}
if(HV03064==""){HV03064="0";}
if(HV03065==""){HV03065="0";}
if(HV03066==""){HV03066="0";}
if(HV03067==""){HV03067="0";}
if(HV03068==""){HV03068="0";}   
if(HV03069==""){HV03069="0";}   
if(HV03070==""){HV03070="0";}   
if(HV03071==""){HV03071="0";}
if(HV03072==""){HV03072="0";}
if(HV03073==""){HV03073="0";}
if(HV03074==""){HV03074="0";}
if(HV03075==""){HV03075="0";}
if(HV03076==""){HV03076="0";}
if(HV03077==""){HV03077="0";}    
if(HV03078==""){HV03078="0";}
if(HV03079==""){HV03079="0";}
if(HV03080==""){HV03080="0";}
if(HV03081==""){HV03081="0";}
if(HV03082==""){HV03082="0";}
if(HV03083==""){HV03083="0";}
if(HV03084==""){HV03084="0";}   
if(HV03085==""){HV03085="0";}   
if(HV03086==""){HV03086="0";}   
if(HV03087==""){HV03087="0";}   
if(HV03088==""){HV03088="0";}   
if(HV03089==""){HV03089="0";}   

var i=1;
while(i<89){
    if(i<10){
 $("#HV0300"+i).css({'background-color' : 'white'});
    }
    else{
  $("#HV030"+i).css({'background-color' : 'white'});       
    }
i++;   
}
// 3.1
if(parseInt(HV03001) > parseInt(HV03011)){checker++;
 $("#HV03001").css({'background-color' : 'yellow'});
 $("#HV03001").prop('title', 'HV03001 should not be more than HV03011');
}
if(parseInt(HV03002) > parseInt(HV03011)){checker++;
 $("#HV03002").css({'background-color' : 'yellow'});
 $("#HV03002").prop('title', 'HV03002 should not be more than HV03011');
}
if(parseInt(HV03003) > parseInt(HV03011)){checker++;
 $("#HV03003").css({'background-color' : 'yellow'});
 $("#HV03003").prop('title', 'HV03003 should not be more than HV03011');
}
if(parseInt(HV03004) > parseInt(HV03011)){checker++;
 $("#HV03004").css({'background-color' : 'yellow'});
 $("#HV03004").prop('title', 'HV03004 should not be more than HV03011');
}
if(parseInt(HV03005) > parseInt(HV03011)){checker++;
 $("#HV03005").css({'background-color' : 'yellow'});
 $("#HV03005").prop('title', 'HV03003 should not be more than HV03011');
}
if(parseInt(HV03006) > parseInt(HV03011)){checker++;
 $("#HV03006").css({'background-color' : 'yellow'});
 $("#HV03006").prop('title', 'HV03006 should not be more than HV03011');
}
if(parseInt(HV03007) > parseInt(HV03011)){checker++;
 $("#HV03007").css({'background-color' : 'yellow'});
 $("#HV03007").prop('title', 'HV03007 should not be more than HV03011');
}
if(parseInt(HV03008) > parseInt(HV03011)){checker++;
 $("#HV03008").css({'background-color' : 'yellow'});
 $("#HV03008").prop('title', 'HV03008 should not be more than HV03011');
}
if(parseInt(HV03009) > parseInt(HV03011)){checker++;
 $("#HV03009").css({'background-color' : 'yellow'});
 $("#HV03009").prop('title', 'HV03009 should not be more than HV03011');
}
if(parseInt(HV03010) > parseInt(HV03011)){checker++;
 $("#HV03010").css({'background-color' : 'yellow'});
 $("#HV03010").prop('title', 'HV03010 should not be more than HV03011');
}
if(parseInt(HV03010) > parseInt(HV03015)){checker++;
 $("#HV03010").css({'background-color' : 'yellow'});
 $("#HV03010").prop('title', 'HV03010 should not be more than HV03015');
}
if(parseInt(HV03012) > parseInt(HV03011)){checker++;
 $("#HV03012").css({'background-color' : 'yellow'});
 $("#HV03012").prop('title', 'HV03012 should not be more than HV03011');
}

//3.2
 if(parseInt(HV03013) > parseInt(HV03015)){checker++;
 $("#HV03013").css({'background-color' : 'yellow'});
 $("#HV03013").prop('title', 'HV03013 should not be more than HV03015');
}
if(parseInt(HV03014) > parseInt(HV03015)){checker++;
 $("#HV03014").css({'background-color' : 'yellow'});
 $("#HV03014").prop('title', 'HV03014 should not be more than HV03015');
}
if(parseInt(HV03015) != (parseInt(HV03013)+parseInt(HV03014))){checker++;
 $("#HV03015").css({'background-color' : 'yellow'});
 $("#HV03015").prop('title', 'HV03015 should be equal to the sum of HV03013 and HV03014');
}

//3.3
if(parseInt(HV03016) > parseInt(HV03026)){checker++;
 $("#HV03016").css({'background-color' : 'yellow'});
 $("#HV03016").prop('title', 'HV03016 should not be more than HV03026');
}
if(parseInt(HV03017) > parseInt(HV03026)){checker++;
 $("#HV03017").css({'background-color' : 'yellow'});
 $("#HV03017").prop('title', 'HV03017 should not be more than HV03026');
}
if(parseInt(HV03018) > parseInt(HV03026)){checker++;
 $("#HV03018").css({'background-color' : 'yellow'});
 $("#HV03018").prop('title', 'HV03018 should not be more than HV03026');
}
if(parseInt(HV03019) > parseInt(HV03026)){checker++;
 $("#HV03019").css({'background-color' : 'yellow'});
 $("#HV03019").prop('title', 'HV03019 should not be more than HV03026');
}
if(parseInt(HV03020) > parseInt(HV03026)){checker++;
 $("#HV03020").css({'background-color' : 'yellow'});
 $("#HV03020").prop('title', 'HV03020 should not be more than HV03026');
}
if(parseInt(HV03021) > parseInt(HV03026)){checker++;
 $("#HV03021").css({'background-color' : 'yellow'});
 $("#HV03021").prop('title', 'HV03021 should not be more than HV03026');
}
if(parseInt(HV03022) > parseInt(HV03026)){checker++;
 $("#HV03022").css({'background-color' : 'yellow'});
 $("#HV03022").prop('title', 'HV03022 should not be more than HV03026');
}
if(parseInt(HV03023) > parseInt(HV03026)){checker++;
 $("#HV03023").css({'background-color' : 'yellow'});
 $("#HV03023").prop('title', 'HV03023 should not be more than HV03026');
}
if(parseInt(HV03024) > parseInt(HV03026)){checker++;
 $("#HV03024").css({'background-color' : 'yellow'});
 $("#HV03024").prop('title', 'HV03024 should not be more than HV03026');
}
if(parseInt(HV03025) > parseInt(HV03026)){checker++;
 $("#HV03025").css({'background-color' : 'yellow'});
 $("#HV03025").prop('title', 'HV03025 should not be more than HV03026');
}
if(parseInt(HV03026) != (parseInt(HV03016)+parseInt(HV03017)+parseInt(HV03018)+parseInt(HV03019)+parseInt(HV03020)+parseInt(HV03021)+parseInt(HV03022)+parseInt(HV03023)+parseInt(HV03024)+parseInt(HV03025))){checker++;
 $("#HV03026").css({'background-color' : 'yellow'});
 $("#HV03026").prop('title', 'HV03026 should be equal to the sum of HV03016 to HV03025');
}
if(parseInt(HV03027) > parseInt(HV03011)){checker++;
 $("#HV03027").css({'background-color' : 'yellow'});
 $("#HV03027").prop('title', 'HV03027 should not be more than HV03011');
}

//3.4
if(parseInt(HV03028) > parseInt(HV03011)){checker++;
 $("#HV03028").css({'background-color' : 'yellow'});
 $("#HV03028").prop('title', 'HV03028 should not be more than HV03011');
}
if(parseInt(HV03029) > parseInt(HV03011)){checker++;
 $("#HV03029").css({'background-color' : 'yellow'});
 $("#HV03029").prop('title', 'HV03029 should not be more than HV03011');
}
if(parseInt(HV03030) > parseInt(HV03011)){checker++;
 $("#HV03030").css({'background-color' : 'yellow'});
 $("#HV03030").prop('title', 'HV03030 should not be more than HV03011');
}
if(parseInt(HV03031) > parseInt(HV03011)){checker++;
 $("#HV03031").css({'background-color' : 'yellow'});
 $("#HV03031").prop('title', 'HV030 should not be more than HV03011');
}
if(parseInt(HV03032) > parseInt(HV03011)){checker++;
 $("#HV03032").css({'background-color' : 'yellow'});
 $("#HV03032").prop('title', 'HV03032 should not be more than HV03011');
}
if(parseInt(HV03033) > parseInt(HV03011)){checker++;
 $("#HV03033").css({'background-color' : 'yellow'});
 $("#HV03033").prop('title', 'HV03033 should not be more than HV03011');
}
if(parseInt(HV03034) > parseInt(HV03011)){checker++;
 $("#HV03034").css({'background-color' : 'yellow'});
 $("#HV03034").prop('title', 'HV03034 should not be more than HV03011');
}
if(parseInt(HV03035) > parseInt(HV03011)){checker++;
 $("#HV03035").css({'background-color' : 'yellow'});
 $("#HV03035").prop('title', 'HV03035 should not be more than HV03011');
}
if(parseInt(HV03036) > parseInt(HV03011)){checker++;
 $("#HV03036").css({'background-color' : 'yellow'});
 $("#HV03036").prop('title', 'HV03036 should not be more than HV03011');
}
if(parseInt(HV03037) > parseInt(HV03011)){checker++;
 $("#HV03037").css({'background-color' : 'yellow'});
 $("#HV03037").prop('title', 'HV03037 should not be more than HV03011');
}
if(parseInt(HV03038) != (parseInt(HV03028)+parseInt(HV03029)+parseInt(HV03030)+parseInt(HV03031)+parseInt(HV03032)+parseInt(HV03033)+parseInt(HV03034)+parseInt(HV03035)+parseInt(HV03036)+parseInt(HV03037))){checker++;
 $("#HV03038").css({'background-color' : 'yellow'});
 $("#HV03038").prop('title', 'HV03038 should be equal to the sum of HV0328 to HV03037');
}
if(parseInt(HV03039) > parseInt(HV03038)){checker++;
 $("#HV03039").css({'background-color' : 'yellow'});
 $("#HV03039").prop('title', 'HV03039 should not be more than HV03038');
}

//3.5
if(parseInt(HV03040) > parseInt(HV03041)){checker++;
 $("#HV03040").css({'background-color' : 'yellow'});
 $("#HV03040").prop('title', 'HV03040 should not be more than HV03041');
}
if(parseInt(HV03042) > parseInt(HV03041)){checker++;
 $("#HV03042").css({'background-color' : 'yellow'});
 $("#HV03042").prop('title', 'HV03042 should not be more than HV03041');
}
if(parseInt(HV03043) > parseInt(HV03042)){checker++;
 $("#HV03043").css({'background-color' : 'yellow'});
 $("#HV03043").prop('title', 'HV03043 should not be more than HV03042');
}

//3.6
if(parseInt(HV03044) > parseInt(HV03050)){checker++;
 $("#HV03044").css({'background-color' : 'yellow'});
 $("#HV03044").prop('title', 'HV03044 should not be more than HV03050');
}
if(parseInt(HV03045) > parseInt(HV03050)){checker++;
 $("#HV03045").css({'background-color' : 'yellow'});
 $("#HV03045").prop('title', 'HV03045 should not be more than HV03050');
}
if(parseInt(HV03046) > parseInt(HV03050)){checker++;
 $("#HV03046").css({'background-color' : 'yellow'});
 $("#HV03046").prop('title', 'HV03046 should not be more than HV03050');
}
if(parseInt(HV03047) > parseInt(HV03050)){checker++;
 $("#HV03047").css({'background-color' : 'yellow'});
 $("#HV03047").prop('title', 'HV03047 should not be more than HV03050');
}
if(parseInt(HV03048) > parseInt(HV03050)){checker++;
 $("#HV03048").css({'background-color' : 'yellow'});
 $("#HV03048").prop('title', 'HV03048 should not be more than HV03050');
}
if(parseInt(HV03049) > parseInt(HV03050)){checker++;
 $("#HV03049").css({'background-color' : 'yellow'});
 $("#HV03049").prop('title', 'HV03049 should not be more than HV03050');
}
if(parseInt(HV03050) != (parseInt(HV03044)+parseInt(HV03045)+parseInt(HV03046)+parseInt(HV03047)+parseInt(HV03048)+parseInt(HV03049))){checker++;
 $("#HV03050").css({'background-color' : 'yellow'});
 $("#HV03050").prop('title', 'HV03050 should be equal to the sum of HV03044 to HV03049');
}

//3.7
if(parseInt(HV03051) > parseInt(HV03057)){checker++;
 $("#HV03051").css({'background-color' : 'yellow'});
 $("#HV03051").prop('title', 'HV03051 should not be more than HV03057');
}
if(parseInt(HV03052) > parseInt(HV03057)){checker++;
 $("#HV03052").css({'background-color' : 'yellow'});
 $("#HV03052").prop('title', 'HV03052 should not be more than HV03057');
}
if(parseInt(HV03053) > parseInt(HV03057)){checker++;
 $("#HV03053").css({'background-color' : 'yellow'});
 $("#HV03053").prop('title', 'HV03053 should not be more than HV03057');
}
if(parseInt(HV03054) > parseInt(HV03057)){checker++;
 $("#HV03054").css({'background-color' : 'yellow'});
 $("#HV03054").prop('title', 'HV03054 should not be more than HV03057');
}
if(parseInt(HV03055) > parseInt(HV03057)){checker++;
 $("#HV03055").css({'background-color' : 'yellow'});
 $("#HV03055").prop('title', 'HV03055 should not be more than HV03057');
}
if(parseInt(HV03056) > parseInt(HV03057)){checker++;
 $("#HV03056").css({'background-color' : 'yellow'});
 $("#HV03056").prop('title', 'HV03056 should not be more than HV03057');
}
if(parseInt(HV03057) != (parseInt(HV03051)+parseInt(HV03052)+parseInt(HV03053)+parseInt(HV03054)+parseInt(HV03055)+parseInt(HV03056))){checker++;
 $("#HV03057").css({'background-color' : 'yellow'});
 $("#HV03057").prop('title', 'HV03057 should be equal to the sum of HV051 to HV03056');
}

if(parseInt(HV03058) > parseInt(HV03057)){checker++;
 $("#HV03058").css({'background-color' : 'yellow'});
 $("#HV03058").prop('title', 'HV03058 should not be more than HV03057');
}

//3.8
if(parseInt(HV03059) > parseInt(HV03065)){checker++;
 $("#HV03059").css({'background-color' : 'yellow'});
 $("#HV03059").prop('title', 'HV03059 should not be more than HV03065');
}
if(parseInt(HV03060) > parseInt(HV03065)){checker++;
 $("#HV03060").css({'background-color' : 'yellow'});
 $("#HV03060").prop('title', 'HV03060 should not be more than HV03065');
}
if(parseInt(HV03061) > parseInt(HV03065)){checker++;
 $("#HV03061").css({'background-color' : 'yellow'});
 $("#HV03061").prop('title', 'HV03061 should not be more than HV03065');
}
if(parseInt(HV03062) > parseInt(HV03065)){checker++;
 $("#HV03062").css({'background-color' : 'yellow'});
 $("#HV03062").prop('title', 'HV03062 should not be more than HV03065');
}
if(parseInt(HV03063) > parseInt(HV03065)){checker++;
 $("#HV03063").css({'background-color' : 'yellow'});
 $("#HV03063").prop('title', 'HV03063 should not be more than HV03065');
}
if(parseInt(HV03064) > parseInt(HV03065)){checker++;
 $("#HV03064").css({'background-color' : 'yellow'});
 $("#HV03064").prop('title', 'HV03064 should not be more than HV03065');
}
if(parseInt(HV03065) > (parseInt(HV03059)+parseInt(HV03060)+parseInt(HV03061)+parseInt(HV03062)+parseInt(HV03063)+parseInt(HV03064))){checker++;
 $("#HV03065").css({'background-color' : 'yellow'});
 $("#HV03065").prop('title', 'HV03065 should be equal to the sum of HV03059 to HV03064');
}

if(parseInt(HV03066) > parseInt(HV03065)){checker++; //clarification needed on the 12 month thing
 $("#HV03066").css({'background-color' : 'yellow'});
 $("#HV03066").prop('title', 'HV03066 should not be more than HV03065');
}

//3.9
if(parseInt(HV03067) > parseInt(HV03069)){checker++;
 $("#HV03067").css({'background-color' : 'yellow'});
 $("#HV03067").prop('title', 'HV03067 should not be more than HV03069');
}
if(parseInt(HV03068) > parseInt(HV03069)){checker++;
 $("#HV03068").css({'background-color' : 'yellow'});
 $("#HV03068").prop('title', 'HV03068 should not be more than HV03069');
}
if(parseInt(HV03069) != (parseInt(HV03067)+parseInt(HV03068))){checker++;
 $("#HV03069").css({'background-color' : 'yellow'});
 $("#HV03069").prop('title', 'HV03069 should be equal to the sum of HV03067 to HV03068');
}
if(parseInt(HV03070) > parseInt(HV03072)){checker++;
 $("#HV03070").css({'background-color' : 'yellow'});
 $("#HV03070").prop('title', 'HV03070 should not be more than HV03072');
}
if(parseInt(HV03071) > parseInt(HV03072)){checker++;
 $("#HV03071").css({'background-color' : 'yellow'});
 $("#HV03071").prop('title', 'HV03071 should not be more than HV03072');
}
if(parseInt(HV03072) != (parseInt(HV03070)+parseInt(HV03071))){checker++;
 $("#HV03072").css({'background-color' : 'yellow'});
 $("#HV03072").prop('title', 'HV03072 shouldbe equal to the sum of HV03070 and HV03071');
}
if(parseInt(HV03073) > parseInt(HV03011)){checker++;
 $("#HV03073").css({'background-color' : 'yellow'});
 $("#HV03073").prop('title', 'HV03073 should not be more than HV03011');
}
if(parseInt(HV03074) > parseInt(HV03011)){checker++;
 $("#HV03074").css({'background-color' : 'yellow'});
 $("#HV03074").prop('title', 'HV03074 should not be more than HV03011');
}
if(parseInt(HV03075) > parseInt(HV03011)){checker++;
 $("#HV03075").css({'background-color' : 'yellow'});
 $("#HV03075").prop('title', 'HV03075 should not be more than HV03011');
}

//secondary
if(parseInt(HV03070) > parseInt(HV03067)){checker++;
 $("#HV03070").css({'background-color' : 'yellow'});
 $("#HV03070").prop('title', 'HV03070 should not be more than HV03067');
}
if(parseInt(HV03071) > parseInt(HV03068)){checker++;
 $("#HV03071").css({'background-color' : 'yellow'});
 $("#HV03071").prop('title', 'HV03071 should not be more than HV03068');
}
if(parseInt(HV03072) > parseInt(HV03069)){checker++;
 $("#HV03072").css({'background-color' : 'yellow'});
 $("#HV03072").prop('title', 'HV03072 should not be more than HV03069');
}
if(parseInt(HV03073) > parseInt(HV03070)){checker++;
 $("#HV03073").css({'background-color' : 'yellow'});
 $("#HV03073").prop('title', 'HV03073 should not be more than HV03070');
}
if(parseInt(HV03074) > parseInt(HV03071)){checker++;
 $("#HV03074").css({'background-color' : 'yellow'});
 $("#HV03074").prop('title', 'HV03074 should not be more than HV03071');
}
if(parseInt(HV03075) > parseInt(HV03072)){checker++;
 $("#HV03075").css({'background-color' : 'yellow'});
 $("#HV03075").prop('title', 'HV03075 should not be more than HV03072');
}

//3.10
if(parseInt(HV03077) > parseInt(HV03079)){checker++;
 $("#HV03077").css({'background-color' : 'yellow'});
 $("#HV03077").prop('title', 'HV03077 should not be more than HV03079');
}
if(parseInt(HV03078) > parseInt(HV03079)){checker++;
 $("#HV03078").css({'background-color' : 'yellow'});
 $("#HV03078").prop('title', 'HV03078 should not be more than HV03079');
}
if(parseInt(HV03079) != (parseInt(HV03077)+parseInt(HV03078))){checker++;
 $("#HV03079").css({'background-color' : 'yellow'});
 $("#HV03079").prop('title', 'HV03079 should be equal to the sum of HV03077 and HV03078');
}
if(parseInt(HV03080) > parseInt(HV03079)){checker++;
 $("#HV03080").css({'background-color' : 'yellow'});
 $("#HV03080").prop('title', 'HV03080 should not be more than HV03079');
}
if(parseInt(HV03081) != (parseInt(HV03077)+parseFloat(HV03080))){checker++;
 $("#HV03081").css({'background-color' : 'yellow'});
 $("#HV03081").prop('title', 'HV03081 should be equal to the sum of HV03077 and HV03080');
}
if(parseInt(HV03082) > parseInt(HV03077)){checker++;
 $("#HV03082").css({'background-color' : 'yellow'});
 $("#HV03082").prop('title', 'HV03082 should not be more than HV03077');
}
if(parseInt(HV03083) > parseInt(HV03081)){checker++;
 $("#HV03083").css({'background-color' : 'yellow'});
 $("#HV03083").prop('title', 'HV03083 should not be more than HV03081');
}
if(parseInt(HV03084) != (parseInt(HV03082)+parseInt(HV03083))){checker++;
 $("#HV03084").css({'background-color' : 'yellow'});
 $("#HV03084").prop('title', 'HV03084 should be equal to the sum of HV03082 and HV03083');
}

//3.11
if(parseInt(HV03085) > parseInt(HV03038)){checker++;
 $("#HV03085").css({'background-color' : 'yellow'});
 $("#HV03085").prop('title', 'HV03085 should not be more than HV03038');
}
if(parseInt(HV03086) > parseInt(HV03038)){checker++;
 $("#HV03086").css({'background-color' : 'yellow'});
 $("#HV03086").prop('title', 'HV03086 should not be more than HV03038');
}

//3.12
if(parseInt(HV03087) > parseInt(HV03088)){checker++;
 $("#HV03087").css({'background-color' : 'yellow'});
 $("#HV03087").prop('title', 'HV03087 should not be more than HV03088');
}
if(parseInt(HV03088) > parseInt(HV03050)){checker++;
 $("#HV03088").css({'background-color' : 'yellow'});
 $("#HV03088").prop('title', 'HV03088 should not be more than HV03050');
}

//3.13
if(parseInt(HV03089) > parseInt(HV03050)){checker++;
 $("#HV03089").css({'background-color' : 'yellow'});
 $("#HV03089").prop('title', 'HV03089 should not be more than HV03050');
}

//end
 return checker;
}

function validateCircumcision(){
   var checker=0;
   var HV0401=document.getElementById("HV0401").value;
   var HV0402=document.getElementById("HV0402").value;
   var HV0403=document.getElementById("HV0403").value;
   var HV0404=document.getElementById("HV0404").value;
   var HV0405=document.getElementById("HV0405").value;
   var HV0406=document.getElementById("HV0406").value;
   var HV0407=document.getElementById("HV0407").value;
   var HV0408=document.getElementById("HV0408").value;
   var HV0409=document.getElementById("HV0409").value;
   var HV0410=document.getElementById("HV0410").value;
   var HV0411=document.getElementById("HV0411").value;
   var HV0412=document.getElementById("HV0412").value;
   var HV0413=document.getElementById("HV0413").value;
   var HV0414=document.getElementById("HV0414").value;
   var HV0415=document.getElementById("HV0415").value;
   var HV0416=document.getElementById("HV0416").value; 
   var HV0417=document.getElementById("HV0417").value;
   
    if(HV0401==""){HV0401="0";}
    if(HV0402==""){HV0402="0";}
    if(HV0403==""){HV0403="0";}
    if(HV0404==""){HV0404="0";}
    if(HV0405==""){HV0405="0";}
    if(HV0406==""){HV0406="0";}
    if(HV0407==""){HV0407="0";}
    if(HV0408==""){HV0408="0";}
    if(HV0409==""){HV0409="0";}
    if(HV0410==""){HV0410="0";}
    if(HV0411==""){HV0411="0";}
    if(HV0412==""){HV0412="0";}
    if(HV0413==""){HV0413="0";}
    if(HV0414==""){HV0414="0";}
    if(HV0415==""){HV0415="0";}
    if(HV0416==""){HV0416="0";}    
    if(HV0417==""){HV0417="0";} 
    
    
    var i=1;
while(i<17){
    if(i<10){
 $("#HV040"+i).css({'background-color' : 'white'});
    }
    else{
  $("#HV04"+i).css({'background-color' : 'white'});       
    }
    i++;   
}
    
//   4.1
if(parseInt(HV0401) > parseInt(HV0407)){checker++;
 $("#HV0401").css({'background-color' : 'yellow'});
 $("#HV0401").prop('title', 'HV0401 should not be more than HV0407');
}
if(parseInt(HV0402) > parseInt(HV0407)){checker++;
 $("#HV0402").css({'background-color' : 'yellow'});
 $("#HV0402").prop('title', 'HV0402 should not be more than HV0407');
}
if(parseInt(HV0403) > parseInt(HV0407)){checker++;
 $("#HV0403").css({'background-color' : 'yellow'});
 $("#HV0403").prop('title', 'HV0403 should not be more than HV0407');
}
if(parseInt(HV0404) > parseInt(HV0407)){checker++;
 $("#HV0404").css({'background-color' : 'yellow'});
 $("#HV0404").prop('title', 'HV0404 should not be more than HV0407');
}
if(parseInt(HV0405) > parseInt(HV0407)){checker++;
 $("#HV0405").css({'background-color' : 'yellow'});
 $("#HV0405").prop('title', 'HV0405 should not be more than HV0407');
}
if(parseInt(HV0406) > parseInt(HV0407)){checker++;
 $("#HV0406").css({'background-color' : 'yellow'});
 $("#HV0406").prop('title', 'HV0406 should not be more than HV0407');
}
if(parseInt(HV0407) != (parseInt(HV0401)+parseInt(HV0402)+parseInt(HV0403)+parseInt(HV0404)+parseInt(HV0405)+parseInt(HV0406))){checker++;
 $("#HV0407").css({'background-color' : 'yellow'});
 $("#HV0407").prop('title', 'HV0407 should be equal to the sum of HV0401 to HV0406');
}
if(parseInt(HV0408) > parseInt(HV0407)){checker++;
 $("#HV0408").css({'background-color' : 'yellow'});
 $("#HV0408").prop('title', 'HV0408 should not be more than HV0407');
}
if(parseInt(HV0409) > parseInt(HV0407)){checker++;
 $("#HV0409").css({'background-color' : 'yellow'});
 $("#HV0409").prop('title', 'HV0409 should not be more than HV0407');
}
if(parseInt(HV0410) > parseInt(HV0407)){checker++;
 $("#HV0410").css({'background-color' : 'yellow'});
 $("#HV0410").prop('title', 'HV0410 should not be more than HV0407');
}

//4.2
if(parseInt(HV0411) > parseInt(HV0407)){checker++;
 $("#HV0411").css({'background-color' : 'yellow'});
 $("#HV0411").prop('title', 'HV0411 should not be more than HV0407');
}
if(parseInt(HV0412) > parseInt(HV0407)){checker++;
 $("#HV0412").css({'background-color' : 'yellow'});
  $("#HV0412").prop('title', 'HV0412 should not be more than HV0407');
}

//4.3
if(parseInt(HV0413) > parseInt(HV0407)){checker++;
 $("#HV0413").css({'background-color' : 'yellow'});
  $("#HV0413").prop('title', 'HV0413 should not be more than HV0407');
}
if(parseInt(HV0414) > parseInt(HV0407)){checker++;
 $("#HV0414").css({'background-color' : 'yellow'});
  $("#HV0414").prop('title', 'HV0414 should not be more than HV0407');
}
if(parseInt(HV0415) > parseInt(HV0407)){checker++;
 $("#HV0415").css({'background-color' : 'yellow'});
  $("#HV0415").prop('title', 'HV0415 should not be more than HV0407');
}
if(parseInt(HV0416) > parseInt(HV0407)){checker++;
 $("#HV0416").css({'background-color' : 'yellow'});
  $("#HV0416").prop('title', 'HV0416 should not be more than HV0407');
}
// end
 return checker; 
}

function validatePEP(){
  var checker=0;
   var HV0501=document.getElementById("HV0501").value;
   var HV0502=document.getElementById("HV0502").value;
   var HV0503=document.getElementById("HV0503").value;
   var HV0504=document.getElementById("HV0504").value;
   var HV0505=document.getElementById("HV0505").value;
   var HV0506=document.getElementById("HV0506").value;
   
    if(HV0501==""){HV0501="0";}
    if(HV0502==""){HV0502="0";}
    if(HV0503==""){HV0503="0";}
    if(HV0504==""){HV0504="0";}
    if(HV0505==""){HV0505="0";}
    if(HV0506==""){HV0506="0";}
   
    var i=1;
while(i<6){
    if(i<10){
 $("#HV050"+i).css({'background-color' : 'white'});
    }
i++;   
}
//    else{
//  $("#HV01"+i).css({'background-color' : 'white'});       
//    }
    
 if(parseInt(HV0501) > parseInt(HV0503)){checker++;
 $("#HV0501").css({'background-color' : 'yellow'});
 $("#HV0501").prop('title', 'HV0501 should not be more than HV0503');
}   
 if(parseInt(HV0502) > parseInt(HV0503)){checker++;
 $("#HV0502").css({'background-color' : 'yellow'});
 $("#HV0502").prop('title', 'HV0502 should not be more than HV0503');
}   
 if(parseInt(HV0503) != (parseInt(HV0501)+parseInt(HV0502))){checker++;
 $("#HV0503").css({'background-color' : 'yellow'});
 $("#HV0503").prop('title', 'HV0503 should be equal to the sum of HV0501 and HV0502');
}   
 if(parseInt(HV0504) > parseInt(HV0501)){checker++;
 $("#HV0504").css({'background-color' : 'yellow'});
 $("#HV0504").prop('title', 'HV0504 should not be more than HV0501');
}   
 if(parseInt(HV0505) > parseInt(HV0502)){checker++;
 $("#HV0505").css({'background-color' : 'yellow'});
 $("#HV0505").prop('title', 'HV0505 should not be more than HV0502');
}   
 if(parseInt(HV0506) > parseInt(HV0503)){checker++;
 $("#HV0506").css({'background-color' : 'yellow'});
 $("#HV0506").prop('title', 'HV0506 should not be more than HV0503');
}   

 return checker;
}

function validateMAT(){
  var checker=0;
  
  
  return checker;  
}