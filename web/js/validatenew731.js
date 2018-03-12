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
   var HV0141=document.getElementById("HV0141").value; 
   var HV0142=document.getElementById("HV0142").value;
   var HV0143=document.getElementById("HV0143").value;
   var HV0144=document.getElementById("HV0144").value;
   var HV0145=document.getElementById("HV0145").value;
   var HV0146=document.getElementById("HV0146").value;
   var HV0147=document.getElementById("HV0147").value;
   var HV0148=document.getElementById("HV0148").value;
   var HV0149=document.getElementById("HV0149").value;
   var HV0150=document.getElementById("HV0150").value; 
   
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
if(HV0141==""){HV0141="0";}    
if(HV0142==""){HV0142="0";}
if(HV0143==""){HV0143="0";}
if(HV0144==""){HV0144="0";}
if(HV0145==""){HV0145="0";}
if(HV0146==""){HV0146="0";}
if(HV0147==""){HV0147="0";}
if(HV0148==""){HV0148="0";}   
if(HV0149==""){HV0149="0";}   
if(HV0150==""){HV0150="0";}   

var i=1;
while(i<50){
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
//if(parseInt(HV0110) != (parseInt(HV0126)+parseInt(HV0127))){checker++;
//   $("#HV0110").css({'background-color' : 'yellow'});
//   $("#HV0110").prop('title', 'HV0110 should  be equal to the sum of HV0126 and HV0127');
//}

//END

//1.2

if(parseInt(HV0126) != (parseInt(HV0117)+parseInt(HV0118)+parseInt(HV0119)+parseInt(HV0120)+parseInt(HV0121)+parseInt(HV0122)+parseInt(HV0123)+parseInt(HV0124)+parseInt(HV0125))){checker++;
   $("#HV0126").css({'background-color' : 'yellow'});
   $("#HV0126").prop('title', 'HV0126 should be equal to the sum of (HV0117 to HV0125)');
}
//1.3

if(parseInt(HV0135) != (parseInt(HV0130)+parseInt(HV0131)+parseInt(HV0132)+parseInt(HV0133)+parseInt(HV0134))){checker++;
   $("#HV0135").css({'background-color' : 'yellow'});
   $("#HV0135").prop('title', 'HV0135 should be equal to the sum of (HV0130 to HV0134)');
}
if(parseInt(HV0136) < parseInt(HV0135)){checker++;
 $("#HV0136").css({'background-color' : 'yellow'});
 $("#HV0136").prop('title', 'HV0136 should not be less than HV0135');
}
//1.4 
if(parseInt(HV0145) != (parseInt(HV0137)+parseInt(HV0138)+parseInt(HV0139)+parseInt(HV0140)+parseInt(HV0141)+parseInt(HV0142)+parseInt(HV0143)+parseInt(HV0144))){checker++;
   $("#HV0145").css({'background-color' : 'yellow'});
   $("#HV0145").prop('title', 'HV0145 should be equal to the sum of (HV0137 to HV0144)');
}

//1.4 
if(parseInt(HV0150) != (parseInt(HV0146)+parseInt(HV0147)+parseInt(HV0148)+parseInt(HV0149))){checker++;
   $("#HV0150").css({'background-color' : 'yellow'});
   $("#HV0150").prop('title', 'HV0150 should be equal to the sum of (HV0146 to HV0149)');
}

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
   var HV0257=document.getElementById("HV0257").value;
   var HV0258=document.getElementById("HV0258").value;
   var HV0259=document.getElementById("HV0259").value;
   
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
if(HV0257==""){HV0257="0";}   
if(HV0258==""){HV0258="0";}   
if(HV0259==""){HV0259="0";}   


var i=1;
while(i<59){
    if(i<10){
 $("#HV020"+i).css({'background-color' : 'white'});
    }
    else{
  $("#HV02"+i).css({'background-color' : 'white'});       
    }
i++;   
}
//  2.1

  
//2.2
if(parseInt(HV0207) != (parseInt(HV0203)+parseInt(HV0204)+parseInt(HV0205)+parseInt(HV0206))){checker++;
 $("#HV0207").css({'background-color' : 'yellow'});
  $("#HV0207").prop('title', 'HV0207 should be equal to the sum of HV0203 to HV0206');
}

//2.3
if(parseInt(HV0214) != (parseInt(HV0210)+parseInt(HV0211)+parseInt(HV0212)+parseInt(HV0213))){checker++;
 $("#HV0214").css({'background-color' : 'yellow'});
  $("#HV0214").prop('title', 'HV0214 should be equal to the sum of HV0210 to HV0213');
}

//2.4

if(parseInt(HV0220) != (parseInt(HV0216)+parseInt(HV0217)+parseInt(HV0218)+parseInt(HV0219))){checker++;
 $("#HV0220").css({'background-color' : 'yellow'});
  $("#HV0220").prop('title', 'HV0220 should be equal to the sum of HV0216 to HV0219');
}

//2.5
if(parseInt(HV0222) > parseInt(HV0223)){checker++;
 $("#HV0222").css({'background-color' : 'yellow'});
  $("#HV0222").prop('title', 'HV0222 should not be more than HV0223');
}

//2.6

if(parseInt(HV0225) > parseInt(HV0224)){checker++;
 $("#HV0225").css({'background-color' : 'yellow'});
 $("#HV0225").prop('title', 'HV0225 should not be more than HV0224');
}

//2.7


//2.8
if(parseInt(HV0233) != (parseInt(HV0229)+parseInt(HV0230)+parseInt(HV0231)+parseInt(HV0232))){checker++;
 $("#HV0233").css({'background-color' : 'yellow'});
  $("#HV0233").prop('title', 'HV0233 should be equal to the sum of HV0229 to HV0232');
}

//2.9


//2.10


//2.11
if(parseInt(HV0242) != (parseInt(HV0239)+parseInt(HV0240)+parseInt(HV0241))){checker++;
 $("#HV0242").css({'background-color' : 'yellow'});
  $("#HV0242").prop('title', 'HV0242 should be equal to the sum of HV0239 to HV0241');
}

//2.12
if(parseInt(HV0246) != (parseInt(HV0244)+parseInt(HV0245))){checker++;
 $("#HV0246").css({'background-color' : 'yellow'});
 $("#HV0246").prop('title', 'HV0246 should be equal to the sum of HV0244 to HV0245');
}

//2.13
if(parseInt(HV0250) != (parseInt(HV0247)+parseInt(HV0248)+parseInt(HV0249))){checker++;
 $("#HV0250").css({'background-color' : 'yellow'});
 $("#HV0250").prop('title', 'HV0250 should be equal to the sum of HV0247 to HV0249');
}

//2.14
if(parseInt(HV0252) < parseInt(HV0251)){checker++;
 $("#HV0252").css({'background-color' : 'yellow'});
 $("#HV0252").prop('title', 'HV0252 should not be less than HV0251');
}

//2.15

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

if(parseInt(HV03011) != (parseInt(HV03001)+parseInt(HV03002)+parseInt(HV03003)+parseInt(HV03004)+parseInt(HV03005)+parseInt(HV03006)+parseInt(HV03007)+parseInt(HV03008)+parseInt(HV03009)+parseInt(HV03010))){checker++;
 $("#HV03011").css({'background-color' : 'yellow'});
 $("#HV03011").prop('title', 'HV03011 should be equal to the sum of HV03001 to HV03010');
}

//3.2

if(parseInt(HV03015) != (parseInt(HV03013)+parseInt(HV03014))){checker++;
 $("#HV03015").css({'background-color' : 'yellow'});
 $("#HV03015").prop('title', 'HV03015 should be equal to the sum of HV03013 and HV03014');
}

//3.3
if(parseInt(HV03026) != (parseInt(HV03016)+parseInt(HV03017)+parseInt(HV03018)+parseInt(HV03019)+parseInt(HV03020)+parseInt(HV03021)+parseInt(HV03022)+parseInt(HV03023)+parseInt(HV03024)+parseInt(HV03025))){checker++;
 $("#HV03026").css({'background-color' : 'yellow'});
 $("#HV03026").prop('title', 'HV03026 should be equal to the sum of HV03016 to HV03025');
}

//3.4

if(parseInt(HV03038) != (parseInt(HV03028)+parseInt(HV03029)+parseInt(HV03030)+parseInt(HV03031)+parseInt(HV03032)+parseInt(HV03033)+parseInt(HV03034)+parseInt(HV03035)+parseInt(HV03036)+parseInt(HV03037))){checker++;
 $("#HV03038").css({'background-color' : 'yellow'});
 $("#HV03038").prop('title', 'HV03038 should be equal to the sum of HV03028 to HV03037');
}

//3.5
if(parseInt(HV03041) < parseInt(HV03040)){checker++;
 $("#HV03041").css({'background-color' : 'yellow'});
 $("#HV03041").prop('title', 'HV03041 should not be less than HV03040');
}

if(parseInt(HV03043) < parseInt(HV03042)){checker++;
 $("#HV03043").css({'background-color' : 'yellow'});
 $("#HV03043").prop('title', 'HV03043 should not be less than HV03042');
}

//3.6

if(parseInt(HV03050) != (parseInt(HV03044)+parseInt(HV03045)+parseInt(HV03046)+parseInt(HV03047)+parseInt(HV03048)+parseInt(HV03049))){checker++;
 $("#HV03050").css({'background-color' : 'yellow'});
 $("#HV03050").prop('title', 'HV03050 should be equal to the sum of HV03044 to HV03049');
}
if(parseInt(HV03050) < parseInt(HV03057)){checker++;
 $("#HV03050").css({'background-color' : 'yellow'});
 $("#HV03050").prop('title', 'HV03050 should not be less than HV03057');
}
//3.7

if(parseInt(HV03057) < parseInt(HV03058)){checker++;
 $("#HV03057").css({'background-color' : 'yellow'});
 $("#HV03057").prop('title', 'HV03057 should not be less than HV03058');
}
if(parseInt(HV03057) != (parseInt(HV03051)+parseInt(HV03052)+parseInt(HV03053)+parseInt(HV03054)+parseInt(HV03055)+parseInt(HV03056))){checker++;
 $("#HV03057").css({'background-color' : 'yellow'});
 $("#HV03057").prop('title', 'HV03057 should be equal to the sum of HV051 to HV03056');
}

//3.8

if(parseInt(HV03065) != (parseInt(HV03059)+parseInt(HV03060)+parseInt(HV03061)+parseInt(HV03062)+parseInt(HV03063)+parseInt(HV03064))){checker++;
 $("#HV03065").css({'background-color' : 'yellow'});
 $("#HV03065").prop('title', 'HV03065 should be equal to the sum of HV03059 to HV03064');
}


//3.9
if(parseInt(HV03069) != (parseInt(HV03067)+parseInt(HV03068))){checker++;
 $("#HV03069").css({'background-color' : 'yellow'});
 $("#HV03069").prop('title', 'HV03069 should be equal to the sum of HV03067 to HV03068');
}
if(parseInt(HV03072) != (parseInt(HV03070)+parseInt(HV03071))){checker++;
 $("#HV03072").css({'background-color' : 'yellow'});
 $("#HV03072").prop('title', 'HV03072 shouldbe equal to the sum of HV03070 and HV03071');
}
if(parseInt(HV03075) != (parseInt(HV03073)+parseInt(HV03074))){checker++;
 $("#HV03075").css({'background-color' : 'yellow'});
 $("#HV03075").prop('title', 'HV03075 shouldbe equal to the sum of HV03073 and HV03074');
}

//3.10
if(parseInt(HV03076) < parseInt(HV03079)){checker++;
 $("#HV03076").css({'background-color' : 'yellow'});
 $("#HV03076").prop('title', 'HV03076 should not be less than HV03079');
}
if(parseInt(HV03079) != (parseInt(HV03077)+parseInt(HV03078))){checker++;
 $("#HV03079").css({'background-color' : 'yellow'});
 $("#HV03079").prop('title', 'HV03079 should be equal to the sum of HV03077 and HV03078');
}
if(parseInt(HV03081) != (parseInt(HV03077)+parseFloat(HV03080))){checker++;
 $("#HV03081").css({'background-color' : 'yellow'});
 $("#HV03081").prop('title', 'HV03081 should be equal to the sum of HV03077 and HV03080');
 }
if(parseInt(HV03084) != (parseInt(HV03082)+parseInt(HV03083))){checker++;
 $("#HV03084").css({'background-color' : 'yellow'});
 $("#HV03084").prop('title', 'HV03084 should be equal to the sum of HV03082 and HV03083');
}

//3.11


//3.12


//3.13

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

if(parseInt(HV0407) != (parseInt(HV0401)+parseInt(HV0402)+parseInt(HV0403)+parseInt(HV0404)+parseInt(HV0405)+parseInt(HV0406))){checker++;
 $("#HV0407").css({'background-color' : 'yellow'});
 $("#HV0407").prop('title', 'HV0407 should be equal to the sum of HV0401 to HV0406');
}
if(parseInt(HV0407) != (parseInt(HV0408)+parseInt(HV0409)+parseInt(HV0410))){checker++;
 $("#HV0407").css({'background-color' : 'yellow'});
 $("#HV0407").prop('title', 'HV0407 should be equal to the sum of HV0408 to HV0410');
}
if(parseInt(HV0407) != (parseInt(HV0411)+parseInt(HV0412))){checker++;
 $("#HV0407").css({'background-color' : 'yellow'});
 $("#HV0407").prop('title', 'HV0407 should be equal to the sum of HV0411 to HV0412');
}


//4.2

//4.3

// end
 return checker; 
}

function validatePEP(){
  var checker=0;
   var HV0501=document.getElementById("HV0501").value;
   var HV0502=document.getElementById("HV0502").value;
   var HV0503=document.getElementById("HV0503").value;
//   var HV0504=document.getElementById("HV0504").value;
   var HV0505=document.getElementById("HV0505").value;
   var HV0506=document.getElementById("HV0506").value;
   var HV0507=document.getElementById("HV0507").value;
   
    if(HV0501==""){HV0501="0";}
    if(HV0502==""){HV0502="0";}
    if(HV0503==""){HV0503="0";}
//    if(HV0504==""){HV0504="0";}
    if(HV0505==""){HV0505="0";}
    if(HV0506==""){HV0506="0";}
    if(HV0507==""){HV0507="0";}
   
    var i=1;
while(i<8){
    if(i<10){
        if(i!=4){
        $("#HV050"+i).css({'background-color' : 'white'});
    }
    }
i++;   
}
    
   
 if(parseInt(HV0503) != (parseInt(HV0501)+parseInt(HV0502))){checker++;
 $("#HV0503").css({'background-color' : 'yellow'});
 $("#HV0503").prop('title', 'HV0503 should be equal to the sum of HV0501 and HV0502');
}   
 if(parseInt(HV0507) != (parseInt(HV0505)+parseInt(HV0506))){checker++;
 $("#HV0507").css({'background-color' : 'yellow'});
 $("#HV0507").prop('title', 'HV0507 should be equal to the sum of HV0505 and HV0506');
}   
   

 return checker;
}

function validateMAT(){
  var checker=0;
  
    var checker=0;
   var HV0601=document.getElementById("HV0601").value;
   var HV0602=document.getElementById("HV0602").value;
   var HV0603=document.getElementById("HV0603").value;
   var HV0604=document.getElementById("HV0604").value;
   
    if(HV0601==""){HV0601="0";}
    if(HV0602==""){HV0602="0";}
    if(HV0603==""){HV0603="0";}
    if(HV0604==""){HV0604="0";}
   
    var i=1;
while(i<5){
    if(i<10){
        $("#HV060"+i).css({'background-color' : 'white'});
    }
    
i++;   
}
    
 if(parseInt(HV0601) < parseInt(HV0602)){checker++;
 $("#HV0601").css({'background-color' : 'yellow'});
 $("#HV0601").prop('title', 'HV0601 should be less than to HV0602');
}   
 if(parseInt(HV0602) < parseInt(HV0603)){checker++;
 $("#HV0602").css({'background-color' : 'yellow'});
 $("#HV0602").prop('title', 'HV0602 should be less than to HV0603');
}   
}

