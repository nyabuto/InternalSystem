/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validateHCT(){
 var checker=0;
 
   var HV0101=document.getElementById("HV0101").value;
   var HV0102=document.getElementById("HV0102").value;
   var HV0103=document.getElementById("HV0103").value;
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
   
   if(HV0101==""){HV0101="0";}
if(HV0102==""){HV0102="0";}
if(HV0103==""){HV0103="0";}
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


//1.1++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    PRIMARY
   if(parseInt(HV0101)>parseInt(HV0103)){checker++;
   $("#HV0101").css({'background-color' : 'yellow'});
   $("#HV0103").css({'background-color' : 'yellow'});
    }
   if(parseInt(HV0102)>parseInt(HV0103)){checker++;
    $("#HV0102").css({'background-color' : 'yellow'});
    $("#HV0103").css({'background-color' : 'yellow'});
    }
   if(parseInt(HV0103)!=(parseInt(HV0101)+parseInt(HV0102))){checker++;
    $("#HV0103").css({'background-color' : 'yellow'});
    $("#HV0101").css({'background-color' : 'yellow'});
    $("#HV0102").css({'background-color' : 'yellow'});
    }
   
   if(parseInt(HV0105)>=parseInt(HV0103)){checker++;
    $("#HV0105").css({'background-color' : 'yellow'});
    $("#HV0103").css({'background-color' : 'yellow'});
}
   if(parseInt(HV0106)>=parseInt(HV0103)){
       checker++;
        $("#HV0106").css({'background-color' : 'yellow'});
        $("#HV0103").css({'background-color' : 'yellow'});
   }
   if(parseInt(HV0107)>=parseInt(HV0103)){
       checker++;
        $("#HV0107").css({'background-color' : 'yellow'});
        $("#HV0103").css({'background-color' : 'yellow'});
   }
   
//    SECONDARY
   if(parseInt(HV0103)!=(parseInt(HV0106)+parseInt(HV0107))){
       checker++;
    $("#HV0103").css({'background-color' : 'yellow'});
    $("#HV0106").css({'background-color' : 'yellow'});
    $("#HV0107").css({'background-color' : 'yellow'});
    }

//1.2++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    PRIMARY
   if(parseInt(HV0108)>=parseInt(HV0105)){checker++;
    $("#HV0108").css({'background-color' : 'yellow'});
    $("#HV0105").css({'background-color' : 'yellow'});
 }
   if(parseInt(HV0109)>=parseInt(HV0105)){checker++;
    $("#HV0109").css({'background-color' : 'yellow'});
    $("#HV0105").css({'background-color' : 'yellow'});
 }

//1.3++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    PRIMARY
   if(parseInt(HV0110)>=parseInt(HV0116)){checker++;
    $("#HV0110").css({'background-color' : 'yellow'});
    $("#HV0116").css({'background-color' : 'yellow'});
}
   if(parseInt(HV0111)>=parseInt(HV0116)){checker++;
    $("#HV0111").css({'background-color' : 'yellow'});
    $("#HV0116").css({'background-color' : 'yellow'});
}
   if(parseInt(HV0112)>=parseInt(HV0116)){checker++;
    $("#HV0112").css({'background-color' : 'yellow'});
    $("#HV0116").css({'background-color' : 'yellow'});
    }
   if(parseInt(HV0113)>=parseInt(HV0116)){checker++;
    $("#HV0113").css({'background-color' : 'yellow'});
    $("#HV0116").css({'background-color' : 'yellow'});
}
   if(parseInt(HV0114)>=parseInt(HV0116)){checker++;
    $("#HV0114").css({'background-color' : 'yellow'});
    $("#HV0116").css({'background-color' : 'yellow'});
}
   if(parseInt(HV0115)>=parseInt(HV0116)){checker++;
    $("#HV0115").css({'background-color' : 'yellow'});
    $("#HV0116").css({'background-color' : 'yellow'});
}
   if(parseInt(HV0116)>=parseInt(HV0103)){checker++;
    $("#HV0116").css({'background-color' : 'yellow'});
    $("#HV0103").css({'background-color' : 'yellow'});
 }
   
//    SECONDARY
  
   if(parseInt(HV0110)>=parseInt(HV0103)){checker++;
    $("#HV0110").css({'background-color' : 'yellow'});
    $("#HV0103").css({'background-color' : 'yellow'});
}
   if(parseInt(HV0111)>=parseInt(HV0103)){checker++;
    $("#HV0111").css({'background-color' : 'yellow'});
    $("#HV0103").css({'background-color' : 'yellow'});
 }
   if(parseInt(HV0112)>=parseInt(HV0103)){checker++;
    $("#HV0112").css({'background-color' : 'yellow'});
    $("#HV0103").css({'background-color' : 'yellow'});
}
   if(parseInt(HV0113)>=parseInt(HV0103)){checker++;
    $("#HV0113").css({'background-color' : 'yellow'});
    $("#HV0103").css({'background-color' : 'yellow'});
}
   if(parseInt(HV0114)>=parseInt(HV0103)){checker++;
    $("#HV0114").css({'background-color' : 'yellow'});
    $("#HV0103").css({'background-color' : 'yellow'});
}
   if(parseInt(HV0115)>=parseInt(HV0103)){checker++;
    $("#HV0115").css({'background-color' : 'yellow'});
    $("#HV0103").css({'background-color' : 'yellow'});
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


 //2.1++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    PRIMARY
   if(parseInt(HV0201)>parseInt(HV0204)){checker++;
    $("#HV0201").css({'background-color' : 'yellow'});
    $("#HV0204").css({'background-color' : 'yellow'});
   
    $("#HV0201").prop('title', 'HV0201 should not be greater than HV0204');
    $("#HV0204").prop('title', 'HV0204 should not be less than HV0201');
 }
   if(parseInt(HV0202)>parseInt(HV0204)){checker++;
    $("#HV0202").css({'background-color' : 'yellow'});
    $("#HV0204").css({'background-color' : 'yellow'});
    $("#HV0202").prop('title', 'HV0202 should not be greater than HV0204');
    $("#HV0204").prop('title', 'HV0204 should not be less than HV0202');
 }
   if(parseInt(HV0203)>parseInt(HV0204)){checker++;
    $("#HV0203").css({'background-color' : 'yellow'});
    $("#HV0204").css({'background-color' : 'yellow'});
    $("#HV0203").prop('title', 'HV0203 should not be greater than HV0204');
    $("#HV0204").prop('title', 'HV0204 should not be less than HV0203');
 }
   if(parseInt(HV0204)!=(parseInt(HV0201)+parseInt(HV0202)+parseInt(HV0203))){checker++;
    $("#HV0204").css({'background-color' : 'yellow'});
    $("#HV0201").css({'background-color' : 'yellow'});
    $("#HV0202").css({'background-color' : 'yellow'});
    $("#HV0203").css({'background-color' : 'yellow'});
    $("#HV0201").prop('title', 'Sum of HV0201,HV0202,HV0203 is not equal to HV0204');
    $("#HV0202").prop('title', 'Sum of HV0201,HV0202,HV0203 is not equal to HV0204');
    $("#HV0203").prop('title', 'Sum of HV0201,HV0202,HV0203 is not equal to HV0204');
    $("#HV0204").prop('title', 'Sum of HV0201,HV0202,HV0203 is not equal to HV0204');
   }  
   
 //2.2++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    PRIMARY
   if(parseInt(HV0206)>parseInt(HV0210)){checker++;
    $("#HV0206").css({'background-color' : 'yellow'});
    $("#HV0210").css({'background-color' : 'yellow'});
    $("#HV0206").prop('title', 'HV0206 should not be greater than HV0210');
    $("#HV0210").prop('title', 'HV0210 should not be less than HV0206');
 }
   if(parseInt(HV0208)>parseInt(HV0210)){checker++;
    $("#HV0208").css({'background-color' : 'yellow'});
    $("#HV0210").css({'background-color' : 'yellow'});
    $("#HV0208").prop('title', 'HV0208 should not be greater than HV0210');
    $("#HV0210").prop('title', 'HV0210 should not be less than HV0208');
 }
   if(parseInt(HV0207)>parseInt(HV0210)){checker++;
    $("#HV0207").css({'background-color' : 'yellow'});
    $("#HV0210").css({'background-color' : 'yellow'});
    $("#HV0207").prop('title', 'HV0207 should not be greater than HV0210');
    $("#HV0210").prop('title', 'HV0210 should not be less than HV0207');
 }
//   if(parseInt(HV0209)>parseInt(HV0204)){checker++;
//    $("#HV0209").css({'background-color' : 'yellow'});
//    $("#HV0204").css({'background-color' : 'yellow'});
//    $("#HV0209").prop('title', 'HV0209 should not be greater than HV0204');
//    $("#HV0204").prop('title', 'HV0204 should not be less than HV0209');
// }
//   if(parseInt(HV0210)>parseInt(HV0204)){checker++;
//    $("#HV0210").css({'background-color' : 'yellow'});
//    $("#HV0204").css({'background-color' : 'yellow'});
//    $("#HV0210").prop('title', 'HV0210 should not be greater than HV0204');
//    $("#HV0204").prop('title', 'HV0204 should not be less than HV0210');
// } 
    
//2.3++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    PRIMARY
   if(parseInt(HV0211)>parseInt(HV0204)){checker++;
    $("#HV0211").css({'background-color' : 'yellow'});
    $("#HV0204").css({'background-color' : 'yellow'});
    $("#HV0211").prop('title', 'HV0211 should not be greater than HV0204');
    $("#HV0204").prop('title', 'HV0204 should not be less than HV0211' );
 }
   if(parseInt(HV0212)>parseInt(HV0211)){checker++;
    $("#HV0212").css({'background-color' : 'yellow'});
    $("#HV0211").css({'background-color' : 'yellow'});
    $("#HV0210").prop('title', 'HV0210 should not be greater than HV0204');
    $("#HV0204").prop('title', 'HV0204 should not be less than HV0210');
 }
   
   
//2.4+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//  PRIMARY
   if(parseInt(HV0213)>parseInt(HV0209)){checker++;
    $("#HV0213").css({'background-color' : 'yellow'});
    $("#HV0209").css({'background-color' : 'yellow'});
    $("#HV0213").prop('title', 'HV0213 should not be greater than HV0209');
    $("#HV0209").prop('title', 'HV0209 should not be less than HV0213');
 }
   if(parseInt(HV0214)>parseInt(HV0209)){checker++;
    $("#HV0214").css({'background-color' : 'yellow'});
    $("#HV0209").css({'background-color' : 'yellow'});
    $("#HV0214").prop('title', 'HV0214 should not be greater than HV0209');
    $("#HV0209").prop('title', 'HV0209 should not be less than HV0214');
 }
   if(parseInt(HV0215)>parseInt(HV0209)){checker++;
    $("#HV0215").css({'background-color' : 'yellow'});
    $("#HV0209").css({'background-color' : 'yellow'});
    $("#HV0215").prop('title', 'HV0215 should not be greater than HV0209');
    $("#HV0209").prop('title', 'HV0209 should not be less than HV0215');
 }
   if(parseInt(HV0216)>parseInt(HV0209)){checker++;
    $("#HV0216").css({'background-color' : 'yellow'});
    $("#HV0209").css({'background-color' : 'yellow'});
    $("#HV0216").prop('title', 'HV0216 should not be greater than HV0209');
    $("#HV0209").prop('title', 'HV0209 should not be less than HV0216');
}

  if(parseInt(HV0217)!=parseInt(HV0209)){checker++;
    $("#HV0217").css({'background-color' : 'yellow'});
    $("#HV0209").css({'background-color' : 'yellow'});
    $("#HV0217").prop('title', 'HV0217 should be equal to HV0209');
    $("#HV0209").prop('title', 'HV0209 should be equal to HV0217');
}

//   2.5+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if(parseInt(HV0219)>parseInt(HV0209)){checker++;
    $("#HV0219").css({'background-color' : 'yellow'});
    $("#HV0209").css({'background-color' : 'yellow'});
    $("#HV0219").prop('title', 'HV0219 should not be greater than HV0209');
    $("#HV0209").prop('title', 'HV0209 should not be less than HV0219');
 }
   if(parseInt(HV0220)>parseInt(HV0209)){checker++;
    $("#HV0220").css({'background-color' : 'yellow'});
    $("#HV0209").css({'background-color' : 'yellow'});
    $("#HV0220").prop('title', 'HV0220 should not be greater than HV0209');
    $("#HV0209").prop('title', 'HV0209 should not be less than HV0220');
 }
   if(parseInt(HV0221)>parseInt(HV0209)){checker++;
     $("#HV0221").css({'background-color' : 'yellow'});
     $("#HV0209").css({'background-color' : 'yellow'});
     $("#HV0221").prop('title', 'HV0221 should not be greater than HV0209');
    $("#HV0209").prop('title', 'HV0209 should not be less than HV0221');
    }
   
//   2.7+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if(parseInt(HV0224)>parseInt(HV0228)){checker++;
    $("#HV0224").css({'background-color' : 'yellow'});
    $("#HV0228").css({'background-color' : 'yellow'});
    $("#HV0224").prop('title', 'HV0224 should not be greater than HV0228');
    $("#HV0228").prop('title', 'HV0228 should not be less than HV0224');
 }
   if(parseInt(HV0225)>parseInt(HV0228)){checker++;
    $("#HV0225").css({'background-color' : 'yellow'});
    $("#HV0228").css({'background-color' : 'yellow'});
    $("#HV0225").prop('title', 'HV0225 should not be greater than HV0228');
    $("#HV0228").prop('title', 'HV0228 should not be less than HV0225');
}
   if(parseInt(HV0226)>parseInt(HV0228)){checker++;
    $("#HV0226").css({'background-color' : 'yellow'});
    $("#HV0228").css({'background-color' : 'yellow'});
    $("#HV0226").prop('title', 'HV0226 should not be greater than HV0228');
    $("#HV0228").prop('title', 'HV0228 should not be less than HV0226');
 }
   
   if(parseInt(HV0228)>parseInt(HV0240)){checker++;
    $("#HV0228").css({'background-color' : 'yellow'});
    $("#HV0240").css({'background-color' : 'yellow'});
    $("#HV0228").prop('title', 'HV0228 should not be greater than HV0240');
    $("#HV0240").prop('title', 'HV0240 should not be less than HV0228');
 }
 
    
  //   2.8+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if(parseInt(HV0229)>parseInt(HV0224)){checker++;
    $("#HV0229").css({'background-color' : 'yellow'});
    $("#HV0224").css({'background-color' : 'yellow'});
     $("#HV0229").prop('title', 'HV0229 should not be greater than HV0224');
    $("#HV0224").prop('title', 'HV0224 should not be less than HV0229');
 }
   if(parseInt(HV0230)>parseInt(HV0225)){checker++;
    $("#HV0230").css({'background-color' : 'yellow'});
    $("#HV0225").css({'background-color' : 'yellow'});
     $("#HV0230").prop('title', 'HV0230 should not be greater than HV0225');
    $("#HV0225").prop('title', 'HV0225 should not be less than HV0230');
 }
   if(parseInt(HV0231)>parseInt(HV0227)){checker++;
    $("#HV0231").css({'background-color' : 'yellow'});
    $("#HV0227").css({'background-color' : 'yellow'});
     $("#HV0231").prop('title', 'HV0231 should not be greater than HV0227');
    $("#HV0227").prop('title', 'HV0227 should not be less than HV0231');
 }
   if(parseInt(HV0232)>parseInt(HV0228)){checker++;
    $("#HV0232").css({'background-color' : 'yellow'});
    $("#HV0228").css({'background-color' : 'yellow'});
     $("#HV0232").prop('title', 'HV0232 should not be greater than HV0228');
    $("#HV0228").prop('title', 'HV0228 should not be less than HV0232');
 }  
   
   //   2.9+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if(parseInt(HV0236)>parseInt(HV0240)){checker++;
    $("#HV0236").css({'background-color' : 'yellow'});
    $("#HV0240").css({'background-color' : 'yellow'});
    $("#HV0236").prop('title', 'HV0236 should not be greater than HV0240');
    $("#HV0240").prop('title', 'HV0240 should not be less than HV0236');
 }
   
  //   2.10+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if(parseInt(HV0241)>(parseInt(HV0206)+parseInt(HV0205))){checker++;
    $("#HV0241").css({'background-color' : 'yellow'});
    $("#HV0206").css({'background-color' : 'yellow'});
    $("#HV0205").css({'background-color' : 'yellow'});
    $("#HV0241").prop('title', 'HV0241 should not be greater than th sum of HV0206 and HV0205');
    $("#HV0206").prop('title', 'sum of HV0206 and HV0205 should not be less than HV0241');
    $("#HV0205").prop('title', 'sum of HV0206 and HV0205 should not be less than HV0241');
    
}
   if(parseInt(HV0241)>parseInt(HV0207)){checker++;
    $("#HV0241").css({'background-color' : 'yellow'});
    $("#HV0207").css({'background-color' : 'yellow'});
    $("#HV0241").prop('title', 'HV0241 should not be greater than HV0207');
    $("#HV0207").prop('title', 'HV0207 should not be less than HV0241');
 }
   if(parseInt(HV0241)>parseInt(HV0208)){checker++;
    $("#HV0241").css({'background-color' : 'yellow'});
    $("#HV0208").css({'background-color' : 'yellow'});
    $("#HV0241").prop('title', 'HV0241 should not be greater than HV0208');
    $("#HV0208").prop('title', 'HV0208 should not be less than HV0241');
 }

 //SECONDARY
   if(parseInt(HV0241)>parseInt(HV0209)){checker++;
    $("#HV0241").css({'background-color' : 'yellow'});
    $("#HV0209").css({'background-color' : 'yellow'});
    $("#HV0241").prop('title', 'HV0241 should not be greater than HV0209');
    $("#HV0209").prop('title', 'HV0209 should not be less than HV0241');
 }
   if(parseInt(HV0242)>parseInt(HV0209)){checker++;
    $("#HV0242").css({'background-color' : 'yellow'});
    $("#HV0209").css({'background-color' : 'yellow'});
    $("#HV0242").prop('title', 'HV0242 should not be greater than HV0209');
    $("#HV0209").prop('title', 'HV0209 should not be less than HV0242');
 }
   if(parseInt(HV0243)>parseInt(HV0209)){checker++;
    $("#HV0243").css({'background-color' : 'yellow'});
    $("#HV0209").css({'background-color' : 'yellow'});
    $("#HV0243").prop('title', 'HV0243 should not be greater than HV0209');
    $("#HV0209").prop('title', 'HV0209 should not be less than HV0243');
 }

  return checker;
}

function validateCT(){
var checker=0;

var HV0208=document.getElementById("HV0208").value;
var HV0240=document.getElementById("HV0240").value;
var HV0232=document.getElementById("HV0232").value;
var HV0301=document.getElementById("HV0301").value;
var HV0302=document.getElementById("HV0302").value;
var HV0303=document.getElementById("HV0303").value;
var HV0304=document.getElementById("HV0304").value;
var HV0305=document.getElementById("HV0305").value;
var HV0306=document.getElementById("HV0306").value;
var HV0307=document.getElementById("HV0307").value;
var HV0308=document.getElementById("HV0308").value;
var HV0309=document.getElementById("HV0309").value;
var HV0310=document.getElementById("HV0310").value;
var HV0311=document.getElementById("HV0311").value;
var HV0312=document.getElementById("HV0312").value;
var HV0313=document.getElementById("HV0313").value;
var HV0314=document.getElementById("HV0314").value;
var HV0315=document.getElementById("HV0315").value;
var HV0316=document.getElementById("HV0316").value;
var HV0317=document.getElementById("HV0317").value;
var HV0318=document.getElementById("HV0318").value;
var HV0319=document.getElementById("HV0319").value;
var HV0320=document.getElementById("HV0320").value;
var HV0321=document.getElementById("HV0321").value;
var HV0322=document.getElementById("HV0322").value;
var HV0323=document.getElementById("HV0323").value;
var HV0324=document.getElementById("HV0324").value;
var HV0325=document.getElementById("HV0325").value;
var HV0326=document.getElementById("HV0326").value;
var HV0327=document.getElementById("HV0327").value;
var HV0328=document.getElementById("HV0328").value;
var HV0329=document.getElementById("HV0329").value;
var HV0330=document.getElementById("HV0330").value;
var HV0331=document.getElementById("HV0331").value;
var HV0332=document.getElementById("HV0332").value;
var HV0333=document.getElementById("HV0333").value;
var HV0334=document.getElementById("HV0334").value;
var HV0335=document.getElementById("HV0335").value;
var HV0336=document.getElementById("HV0336").value;
var HV0337=document.getElementById("HV0337").value;
var HV0338=document.getElementById("HV0338").value;
var HV0339=document.getElementById("HV0339").value;
var HV0340=document.getElementById("HV0340").value;
var HV0341=document.getElementById("HV0341").value;
var HV0342=document.getElementById("HV0342").value;
var HV0343=document.getElementById("HV0343").value;
var HV0344=document.getElementById("HV0344").value;
var HV0345=document.getElementById("HV0345").value;
var HV0346=document.getElementById("HV0346").value;
var HV0347=document.getElementById("HV0347").value;
var HV0348=document.getElementById("HV0348").value;
var HV0349=document.getElementById("HV0349").value;
var HV0350=document.getElementById("HV0350").value;
var HV0351=document.getElementById("HV0351").value;
var HV0352=document.getElementById("HV0352").value;
var HV0353=document.getElementById("HV0353").value;
var HV0354=document.getElementById("HV0354").value;
var HV0355=document.getElementById("HV0355").value;
var HV0904=document.getElementById("HV0904").value;
var HV0905=document.getElementById("HV0905").value;
var HV0370=document.getElementById("HV0370").value;
var HV0371=document.getElementById("HV0371").value;
var HV0372=document.getElementById("HV0372").value;
var HV0373=document.getElementById("HV0373").value;


if(HV0208==""){HV0208="0";}
if(HV0240==""){HV0240="0";}
if(HV0232==""){HV0232="0";}
if(HV0301==""){HV0301="0";}
if(HV0302==""){HV0302="0";}
if(HV0303==""){HV0303="0";}
if(HV0304==""){HV0304="0";}
if(HV0305==""){HV0305="0";}
if(HV0306==""){HV0306="0";}
if(HV0307==""){HV0307="0";}
if(HV0308==""){HV0308="0";}
if(HV0309==""){HV0309="0";}
if(HV0310==""){HV0310="0";}
if(HV0311==""){HV0311="0";}
if(HV0312==""){HV0312="0";}
if(HV0313==""){HV0313="0";}
if(HV0314==""){HV0314="0";}
if(HV0315==""){HV0315="0";}
if(HV0316==""){HV0316="0";}
if(HV0317==""){HV0317="0";}
if(HV0318==""){HV0318="0";}
if(HV0319==""){HV0319="0";}
if(HV0320==""){HV0320="0";}
if(HV0321==""){HV0321="0";}
if(HV0322==""){HV0322="0";}
if(HV0323==""){HV0323="0";}
if(HV0324==""){HV0324="0";}
if(HV0325==""){HV0325="0";}
if(HV0326==""){HV0326="0";}
if(HV0327==""){HV0327="0";}
if(HV0328==""){HV0328="0";}
if(HV0329==""){HV0329="0";}
if(HV0330==""){HV0330="0";}
if(HV0331==""){HV0331="0";}
if(HV0332==""){HV0332="0";}
if(HV0333==""){HV0333="0";}
if(HV0334==""){HV0334="0";}
if(HV0335==""){HV0335="0";}
if(HV0336==""){HV0336="0";}
if(HV0337==""){HV0337="0";}
if(HV0338==""){HV0338="0";}
if(HV0339==""){HV0339="0";}
if(HV0340==""){HV0340="0";}
if(HV0341==""){HV0341="0";}
if(HV0342==""){HV0342="0";}
if(HV0343==""){HV0343="0";}
if(HV0344==""){HV0344="0";}
if(HV0345==""){HV0345="0";}
if(HV0346==""){HV0346="0";}
if(HV0347==""){HV0347="0";}
if(HV0348==""){HV0348="0";}
if(HV0349==""){HV0349="0";}
if(HV0350==""){HV0350="0";}
if(HV0351==""){HV0351="0";}
if(HV0352==""){HV0352="0";}
if(HV0353==""){HV0353="0";}
if(HV0354==""){HV0354="0";}
if(HV0355==""){HV0355="0";}
if(HV0904==""){HV0904="0";}
if(HV0905==""){HV0905="0";}
if(HV0370==""){HV0370="0";}
if(HV0371==""){HV0371="0";}
if(HV0372==""){HV0372="0";}
if(HV0373==""){HV0373="0";}


   
     //   3.1+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if(parseInt(HV0240)<parseInt(HV0301)){checker++;
    $("#HV0240").css({'background-color' : 'yellow'});
    $("#HV0301").css({'background-color' : 'yellow'});
    $("#HV0301").prop('title', 'HV0301 should not be greater than HV0240');
    $("#HV0240").prop('title', 'HV0240 should not be less than HV0301');
 }
   if(parseInt(HV0240)!=parseInt(HV0302)){checker++;
    $("#HV0240").css({'background-color' : 'yellow'});
    $("#HV0302").css({'background-color' : 'yellow'});
    $("#HV0302").prop('title', 'HV0302 should be equal HV0240');
    $("#HV0240").prop('title', 'HV0240 should be equal HV0302');
 }
   if(parseInt(HV0307)<(parseInt(HV0303)+parseInt(HV0304))){checker++;
    $("#HV0307").css({'background-color' : 'yellow'});
    $("#HV0303").css({'background-color' : 'yellow'});
    $("#HV0304").css({'background-color' : 'yellow'});
    $("#HV0303").prop('title', 'sum of HV0303 and HV0304  should not be greater than HV0307');
    $("#HV0304").prop('title', 'sum of HV0303 and HV0304  should not be greater than HV0307');
    $("#HV0307").prop('title', 'HV0307 should not be less than sum of HV0303 and HV0304');
    
 }
   if(parseInt(HV0307)<(parseInt(HV0305)+parseInt(HV0306))){checker++;
    $("#HV0307").css({'background-color' : 'yellow'});
    $("#HV0305").css({'background-color' : 'yellow'});
    $("#HV0306").css({'background-color' : 'yellow'});
    $("#HV0305").prop('title', 'sum of HV0305 and HV0306  should not be greater than HV0307');
    $("#HV0306").prop('title', 'sum of HV0305 and HV0306  should not be greater than HV0307');
    $("#HV0307").prop('title', 'HV0307 should not be less than sum of HV0305 and HV0306');
  }
 //SECONDARY
   
   if(parseInt(HV0307)>parseInt(HV0319)){checker++;
    $("#HV0307").css({'background-color' : 'yellow'});
    $("#HV0319").css({'background-color' : 'yellow'});
    $("#HV0307").prop('title', 'HV0307 should not be greater than HV0319');
    $("#HV0319").prop('title', 'HV0319 should not be less than HV0307');
   
 }
   
   //   3.2+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if(parseInt(HV0308)>(parseInt(HV0309)+parseInt(HV0310))){checker++;
    $("#HV0308").css({'background-color' : 'yellow'});
    $("#HV0309").css({'background-color' : 'yellow'});
    $("#HV0310").css({'background-color' : 'yellow'});
    $("#HV0308").prop('title', 'HV0308 should not be greater than the sum of HV0309 and HV0310');
    $("#HV0309").prop('title', 'sum of HV0309 and HV0310 should not be less than HV0308');
    $("#HV0310").prop('title', 'sum of HV0310 and HV0309 should not be less than HV0308');
 }
   if(parseInt(HV0313)<(parseInt(HV0309)+parseInt(HV0310))){checker++;
    $("#HV0313").css({'background-color' : 'yellow'});
    $("#HV0309").css({'background-color' : 'yellow'});
    $("#HV0310").css({'background-color' : 'yellow'});
    $("#HV0309").prop('title', 'sum of HV0309 and HV0310  should not be greater than HV0313');
    $("#HV0310").prop('title', 'sum of HV0309 and HV0310  should not be greater than HV0313');
    $("#HV0313").prop('title', 'HV0313 should not be less than sum of HV0309 and HV0310');
 }
   if(parseInt(HV0313)<(parseInt(HV0311)+parseInt(HV0312))){checker++;
    $("#HV0313").css({'background-color' : 'yellow'});
    $("#HV0311").css({'background-color' : 'yellow'});
    $("#HV0312").css({'background-color' : 'yellow'});
    $("#HV0311").prop('title', 'sum of HV0311 and HV0312  should not be greater than HV0313');
    $("#HV0312").prop('title', 'sum of HV0311 and HV0312  should not be greater than HV0313');
    $("#HV0313").prop('title', 'HV0313 should not be less than sum of HV0311 and HV0312');
 }
 //SECONDARY
   
   if(parseInt(HV0313)>=parseInt(HV0307)){checker++;
    $("#HV0313").css({'background-color' : 'yellow'});
    $("#HV0307").css({'background-color' : 'yellow'});
    $("#HV0313").prop('title', 'HV0313 should not be greater than or equal to HV0307');
    $("#HV0307").prop('title', 'HV0307 should not be less than or equal to  HV0313');
 } 
   
  //   3.3+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
 if(parseInt(HV0314)>(parseInt(HV0315)+parseInt(HV0316))){checker++;
    $("#HV0314").css({'background-color' : 'yellow'});
    $("#HV0315").css({'background-color' : 'yellow'});
    $("#HV0316").css({'background-color' : 'yellow'});
    $("#HV0314").prop('title', 'HV0314 should not be greater than the sum of HV0315 and HV0316');
    $("#HV0315").prop('title', 'sum of HV0315 and HV0316 should not be less than HV0314');
    $("#HV0316").prop('title', 'sum of HV0316 and HV0315 should not be less than HV0314');
 }
 
   if(parseInt(HV0319)<(parseInt(HV0315)+parseInt(HV0316))){checker++;
    $("#HV0319").css({'background-color' : 'yellow'});
    $("#HV0315").css({'background-color' : 'yellow'});
    $("#HV0316").css({'background-color' : 'yellow'});
    $("#HV0315").prop('title', 'sum of HV0315 and HV0316  should not be greater than HV0319');
    $("#HV0316").prop('title', 'sum of HV0315 and HV0316  should not be greater than HV0319');
    $("#HV0319").prop('title', 'HV0319 should not be less than sum of HV0315 and HV0316');
 }
   if(parseInt(HV0319)<(parseInt(HV0317)+parseInt(HV0318))){checker++;
    $("#HV0319").css({'background-color' : 'yellow'});
    $("#HV0317").css({'background-color' : 'yellow'});
    $("#HV0318").css({'background-color' : 'yellow'});
    $("#HV0317").prop('title', 'sum of HV0317 and HV0318  should not be greater than HV0319');
    $("#HV0318").prop('title', 'sum of HV0317 and HV0318  should not be greater than HV0319');
    $("#HV0319").prop('title', 'HV0319 should not be less than sum of HV0317 and HV0318');
}
 //SECONDARY
  
   if((parseInt(HV0303)+parseInt(HV0304))>(parseInt(HV0315)+parseInt(HV0316))){checker++;
    $("#HV0303").css({'background-color' : 'yellow'});
    $("#HV0304").css({'background-color' : 'yellow'});
    $("#HV0315").css({'background-color' : 'yellow'});
    $("#HV0316").css({'background-color' : 'yellow'});
    $("#HV0303").prop('title', 'sum of HV0303 and HV0304  should not be greater than sum of HV0315 and HV0316');
    $("#HV0304").prop('title', 'sum of HV0303 and HV0304  should not be greater than sum of HV0315 and HV0316');
    $("#HV0315").prop('title', 'sum of HV0303 and HV0304  should not be greater than sum of HV0315 and HV0316');
    $("#HV0316").prop('title', 'sum of HV0303 and HV0304  should not be greater than sum of HV0315 and HV0316');
  }
   if((parseInt(HV0311)+parseInt(HV0312))>(parseInt(HV0315)+parseInt(HV0316))){checker++;
    $("#HV0311").css({'background-color' : 'yellow'});
    $("#HV0312").css({'background-color' : 'yellow'});
    $("#HV0315").css({'background-color' : 'yellow'});
    $("#HV0316").css({'background-color' : 'yellow'});
    $("#HV0311").prop('title', 'sum of HV0311 and HV0312  should not be greater than sum of HV0315 and HV0316');
    $("#HV0312").prop('title', 'sum of HV0311 and HV0312  should not be greater than sum of HV0315 and HV0316');
    $("#HV0315").prop('title', 'sum of HV0311 and HV0312  should not be greater than sum of HV0315 and HV0316');
    $("#HV0316").prop('title', 'sum of HV0311 and HV0312  should not be greater than sum of HV0315 and HV0316');
  }
   if(parseInt(HV0313)>=parseInt(HV0319)){checker++;
    $("#HV0313").css({'background-color' : 'yellow'});
    $("#HV0319").css({'background-color' : 'yellow'});
    $("#HV0313").prop('title', 'HV0313 should not be greater than or equal to HV0319');
    $("#HV0319").prop('title', 'HV0319 should not be less than or equal to  HV0313');
 }
   
    //   3.4+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if(parseInt(HV0334)<parseInt(HV0320)){checker++;
    $("#HV0334").css({'background-color' : 'yellow'});
    $("#HV0320").css({'background-color' : 'yellow'});
    $("#HV0334").prop('title', 'HV0334 should not be less than HV0320');
    $("#HV0320").prop('title', 'HV0320 should not be greater than HV0334');
 }
   if(parseInt(HV0325)<(parseInt(HV0321)+parseInt(HV0322))){checker++;
    $("#HV0325").css({'background-color' : 'yellow'});
    $("#HV0321").css({'background-color' : 'yellow'});
    $("#HV0322").css({'background-color' : 'yellow'});
    $("#HV0321").prop('title', 'sum of HV0321 and HV0322  should not be greater than HV0325');
    $("#HV0322").prop('title', 'sum of HV0321 and HV0322  should not be greater than HV0325');
    $("#HV0325").prop('title', 'HV0325 should not be less than sum of HV0321 and HV0322');
  }
   if(parseInt(HV0325)<(parseInt(HV0323)+parseInt(HV0324))){checker++;
    $("#HV0325").css({'background-color' : 'yellow'});
    $("#HV0323").css({'background-color' : 'yellow'});
    $("#HV0324").css({'background-color' : 'yellow'});
    $("#HV0323").prop('title', 'sum of HV0323 and HV0324  should not be greater than HV0325');
    $("#HV0324").prop('title', 'sum of HV0323 and HV0324  should not be greater than HV0325');
    $("#HV0325").prop('title', 'HV0325 should not be less than sum of HV0323 and HV0324');
 }
   if(parseInt(HV0325)!=(parseInt(HV0321)+parseInt(HV0322)+parseInt(HV0323)+parseInt(HV0324))){checker++;
    $("#HV0325").css({'background-color' : 'yellow'});
    $("#HV0321").css({'background-color' : 'yellow'});
    $("#HV0322").css({'background-color' : 'yellow'});
    $("#HV0323").css({'background-color' : 'yellow'});
    $("#HV0324").css({'background-color' : 'yellow'});
    $("#HV0321").prop('title', 'sum of HV0321,HV0322,HV0323,HV0324  should be equal to HV0325');
    $("#HV0322").prop('title', 'sum of HV0321,HV0322,HV0323,HV0324  should be equal to HV0325');
    $("#HV0323").prop('title', 'sum of HV0321,HV0322,HV0323,HV0324  should be equal to HV0325');
    $("#HV0324").prop('title', 'sum of HV0321,HV0322,HV0323,HV0324  should be equal to HV0325');
    $("#HV0325").prop('title', 'HV0325 should be equal to sum of HV0321,HV0322,HV0323,HV0324');
}
   if(parseInt(HV0325)<parseInt(HV0326)){checker++;
    $("#HV0325").css({'background-color' : 'yellow'});
    $("#HV0326").css({'background-color' : 'yellow'});
    $("#HV0325").prop('title', 'HV0325 should not be less than HV0326');
    $("#HV0326").prop('title', 'HV0326 should not be greater than HV0325');
 }
   if(parseInt(HV0325)<parseInt(HV0327)){checker++;
    $("#HV0325").css({'background-color' : 'yellow'});
    $("#HV0327").css({'background-color' : 'yellow'});  
    $("#HV0325").prop('title', 'HV0325 should not be less than HV0327');
    $("#HV0327").prop('title', 'HV0327 should not be greater than HV0325');
    
 }
 //SECONDARY
  
   if((parseInt(HV0321)+parseInt(HV0322))>(parseInt(HV0335)+parseInt(HV0336))){checker++;
    $("#HV0321").css({'background-color' : 'yellow'});
    $("#HV0322").css({'background-color' : 'yellow'}); 
    $("#HV0335").css({'background-color' : 'yellow'});
    $("#HV0336").css({'background-color' : 'yellow'});
    $("#HV0321").prop('title', 'sum of HV0321 and HV0322  should not be greater than sum of HV0335 and HV0336');
    $("#HV0322").prop('title', 'sum of HV0321 and HV0322  should not be greater than sum of HV0335 and HV0336');
    $("#HV0335").prop('title', 'sum of HV0321 and HV0322  should not be greater than sum of HV0335 and HV0336');
    $("#HV0336").prop('title', 'sum of HV0321 and HV0322  should not be greater than sum of HV0335 and HV0336');
 }
   if((parseInt(HV0323)+parseInt(HV0324))>(parseInt(HV0337)+parseInt(HV0338))){checker++;
    $("#HV0323").css({'background-color' : 'yellow'});
    $("#HV0324").css({'background-color' : 'yellow'});
    $("#HV0337").css({'background-color' : 'yellow'});
    $("#HV0338").css({'background-color' : 'yellow'});
    $("#HV0323").prop('title', 'sum of HV0323 and HV0324  should not be greater than sum of HV0337 and HV0338');
    $("#HV0324").prop('title', 'sum of HV0323 and HV0324  should not be greater than sum of HV0337 and HV0338');
    $("#HV0337").prop('title', 'sum of HV0323 and HV0324  should not be greater than sum of HV0337 and HV0338');
    $("#HV0338").prop('title', 'sum of HV0323 and HV0324  should not be greater than sum of HV0337 and HV0338');
   }
   if(parseInt(HV0325)>parseInt(HV0339)){checker++;
    $("#HV0325").css({'background-color' : 'yellow'});
    $("#HV0339").css({'background-color' : 'yellow'});
    $("#HV0339").prop('title', 'HV0339 should not be less than HV0325');
    $("#HV0325").prop('title', 'HV0325 should not be greater than HV0339');
 }
   
   
      //   3.5+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if(parseInt(HV0320)>parseInt(HV0328)){checker++;
    $("#HV0320").css({'background-color' : 'yellow'});
    $("#HV0328").css({'background-color' : 'yellow'});
    $("#HV0328").prop('title', 'HV0328 should not be less than HV0320');
    $("#HV0320").prop('title', 'HV0320 should not be greater than HV0328');
 }
   if(parseInt(HV0333)<(parseInt(HV0329)+parseInt(HV0330))){checker++;
    $("#HV0333").css({'background-color' : 'yellow'});
    $("#HV0329").css({'background-color' : 'yellow'});
    $("#HV0330").css({'background-color' : 'yellow'});
    $("#HV0329").prop('title', 'sum of HV0329 and HV0330  should not be greater than HV0333');
    $("#HV0330").prop('title', 'sum of HV0329 and HV0330  should not be greater than HV0333');
    $("#HV0333").prop('title', 'HV0333 should not be less than sum of HV0329 and HV0330');
 }
   if(parseInt(HV0333)<(parseInt(HV0331)+parseInt(HV0332))){checker++;
    $("#HV0333").css({'background-color' : 'yellow'});
    $("#HV0331").css({'background-color' : 'yellow'});
    $("#HV0332").css({'background-color' : 'yellow'});
     $("#HV0331").prop('title', 'sum of HV0331 and HV0332  should not be greater than HV0333');
    $("#HV0332").prop('title', 'sum of HV0331 and HV0332  should not be greater than HV0333');
    $("#HV0333").prop('title', 'HV0333 should not be less than sum of HV0331 and HV0332');
 }
   if(parseInt(HV0333)!=(parseInt(HV0329)+parseInt(HV0330)+parseInt(HV0331)+parseInt(HV0332))){checker++;
    $("#HV0333").css({'background-color' : 'yellow'});
    $("#HV0329").css({'background-color' : 'yellow'});
    $("#HV0330").css({'background-color' : 'yellow'});
    $("#HV0331").css({'background-color' : 'yellow'});
    $("#HV0332").css({'background-color' : 'yellow'});
    $("#HV0329").prop('title', 'sum of HV0329,HV0330,HV0331,HV0332  should be equal to HV0333');
    $("#HV0330").prop('title', 'sum of HV0329,HV0330,HV0331,HV0332  should be equal to HV0333');
    $("#HV0331").prop('title', 'sum of HV0329,HV0330,HV0331,HV0332  should be equal to HV0333');
    $("#HV0332").prop('title', 'sum of HV0329,HV0330,HV0331,HV0332  should be equal to HV0333');
    $("#HV0333").prop('title', 'HV0333 should be equal to sum of HV0329,HV0330,HV0331,HV0332');
   }
  
 //SECONDARY
//   if(parseInt(HV0328)>parseInt(HV0334)){checker++;
//    $("#HV0328").css({'background-color' : 'yellow'});
//    $("#HV0334").css({'background-color' : 'yellow'});
//    $("#HV0334").prop('title', 'HV0334 should not be less than HV0328');
//    $("#HV0328").prop('title', 'HV0328 should not be greater than HV0334');
// }
   if((parseInt(HV0329)+parseInt(HV0330))>(parseInt(HV0335)+parseInt(HV0336))){checker++;
   $("#HV0329").css({'background-color' : 'yellow'});
   $("#HV0330").css({'background-color' : 'yellow'});
   $("#HV0335").css({'background-color' : 'yellow'});
   $("#HV0336").css({'background-color' : 'yellow'});
    $("#HV0329").prop('title', 'sum of HV0329 and HV0330  should not be greater than sum of HV0335 and HV0336');
    $("#HV0330").prop('title', 'sum of HV0329 and HV0330  should not be greater than sum of HV0335 and HV0336');
    $("#HV0335").prop('title', 'sum of HV0329 and HV0330  should not be greater than sum of HV0335 and HV0336');
    $("#HV0336").prop('title', 'sum of HV0329 and HV0330  should not be greater than sum of HV0335 and HV0336');
   }
   if((parseInt(HV0331)+parseInt(HV0332))>(parseInt(HV0337)+parseInt(HV0338))){checker++;
    $("#HV0331").css({'background-color' : 'yellow'});
    $("#HV0332").css({'background-color' : 'yellow'});
    $("#HV0337").css({'background-color' : 'yellow'});
    $("#HV0338").css({'background-color' : 'yellow'});
    $("#HV0331").prop('title', 'sum of HV0331 and HV0332  should not be greater than sum of HV0337 and HV0338');
    $("#HV0332").prop('title', 'sum of HV0331 and HV0332  should not be greater than sum of HV0337 and HV0338');
    $("#HV0337").prop('title', 'sum of HV0331 and HV0332  should not be greater than sum of HV0337 and HV0338');
    $("#HV0338").prop('title', 'sum of HV0331 and HV0332  should not be greater than sum of HV0337 and HV0338');
 }
   if(parseInt(HV0333)>parseInt(HV0339)){checker++;
    $("#HV0333").css({'background-color' : 'yellow'});
    $("#HV0339").css({'background-color' : 'yellow'});
    $("#HV0339").prop('title', 'HV0339 should not be less than HV0333');
    $("#HV0333").prop('title', 'HV0333 should not be greater than HV0339');
 }
   
   
 //   3.6+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
//   if(parseInt(HV0334)>parseInt(HV0328)){checker++;
//    $("#HV0334").css({'background-color' : 'yellow'});
//    $("#HV0328").css({'background-color' : 'yellow'});
//    $("#HV0328").prop('title', 'HV0328 should not be less than HV0334');
//    $("#HV0334").prop('title', 'HV0334 should not be greater than HV0328');
// }
   if(parseInt(HV0328)>parseInt(HV0334)){checker++;
    $("#HV0328").css({'background-color' : 'yellow'});
    $("#HV0334").css({'background-color' : 'yellow'});
    $("#HV0334").prop('title', 'HV0334 should not be less than HV0328');
    $("#HV0328").prop('title', 'HV0328 should not be greater than HV0334');
 }

   if(parseInt(HV0339)<(parseInt(HV0335)+parseInt(HV0336))){checker++;
    $("#HV0339").css({'background-color' : 'yellow'});
    $("#HV0335").css({'background-color' : 'yellow'});
    $("#HV0336").css({'background-color' : 'yellow'});
    $("#HV0335").prop('title', 'sum of HV0335 and HV0336  should not be greater than HV0339');
    $("#HV0336").prop('title', 'sum of HV0335 and HV0336  should not be greater than HV0339');
    $("#HV0339").prop('title', 'HV0339 should not be less than sum of HV0335 and HV0336');
  }
   if(parseInt(HV0339)<(parseInt(HV0337)+parseInt(HV0338))){checker++;
    $("#HV0339").css({'background-color' : 'yellow'});
    $("#HV0337").css({'background-color' : 'yellow'});
    $("#HV0338").css({'background-color' : 'yellow'});
    $("#HV0337").prop('title', 'sum of HV0337 and HV0338  should not be greater than HV0339');
    $("#HV0338").prop('title', 'sum of HV0337 and HV0338  should not be greater than HV0339');
    $("#HV0339").prop('title', 'HV0339 should not be less than sum of HV0337 and HV0338');
  }
   if(parseInt(HV0333)!=(parseInt(HV0335)+parseInt(HV0336)+parseInt(HV0337)+parseInt(HV0338))){checker++;
    $("#HV0333").css({'background-color' : 'yellow'});
    $("#HV0335").css({'background-color' : 'yellow'});
    $("#HV0336").css({'background-color' : 'yellow'});
    $("#HV0337").css({'background-color' : 'yellow'});
    $("#HV0338").css({'background-color' : 'yellow'});
    $("#HV0335").prop('title', 'sum of HV0335,HV0336,HV0337,HV0338  should be equal to HV0333');
    $("#HV0336").prop('title', 'sum of HV0335,HV0336,HV0337,HV0338  should be equal to HV0333');
    $("#HV0337").prop('title', 'sum of HV0335,HV0336,HV0337,HV0338  should be equal to HV0333');
    $("#HV0338").prop('title', 'sum of HV0335,HV0336,HV0337,HV0338  should be equal to HV0333');
    $("#HV0333").prop('title', 'HV0333 should be equal to sum of HV0335,HV0336,HV0337,HV0338');
   }
 //SECONDARY
  if((parseInt(HV0335)+parseInt(HV0336))>(parseInt(HV0340)+parseInt(HV0341))){checker++;
      $("#HV0335").css({'background-color' : 'yellow'});
      $("#HV0336").css({'background-color' : 'yellow'});
      $("#HV0340").css({'background-color' : 'yellow'});
      $("#HV0341").css({'background-color' : 'yellow'});
    $("#HV0335").prop('title', 'sum of HV0335 and HV0336  should not be greater than sum of HV0340 and HV0341');
    $("#HV0336").prop('title', 'sum of HV0335 and HV0336  should not be greater than sum of HV0340 and HV0341');
    $("#HV0340").prop('title', 'sum of HV0335 and HV0336  should not be greater than sum of HV0340 and HV0341');
    $("#HV0341").prop('title', 'sum of HV0335 and HV0336  should not be greater than sum of HV0340 and HV0341');
    }
   if((parseInt(HV0337)+parseInt(HV0338))>(parseInt(HV0342)+parseInt(HV0343))){checker++;
   $("#HV0337").css({'background-color' : 'yellow'});
   $("#HV0338").css({'background-color' : 'yellow'});
   $("#HV0342").css({'background-color' : 'yellow'});
   $("#HV0343").css({'background-color' : 'yellow'});
    $("#HV0337").prop('title', 'sum of HV0337 and HV0338  should not be greater than sum of HV0342 and HV0343');
    $("#HV0338").prop('title', 'sum of HV0337 and HV0338  should not be greater than sum of HV0342 and HV0343');
    $("#HV0342").prop('title', 'sum of HV0337 and HV0338  should not be greater than sum of HV0342 and HV0343');
    $("#HV0343").prop('title', 'sum of HV0337 and HV0338  should not be greater than sum of HV0342 and HV0343');
   }
   if(parseInt(HV0333)>parseInt(HV0344)){checker++;
    $("#HV0333").css({'background-color' : 'yellow'});
    $("#HV0344").css({'background-color' : 'yellow'});
    $("#HV0344").prop('title', 'HV0344 should not be less than HV0333');
    $("#HV0333").prop('title', 'HV0333 should not be greater than HV0344');
}  
   
  //   3.7+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if(parseInt(HV0344)<(parseInt(HV0340)+parseInt(HV0341))){checker++;
    $("#HV0344").css({'background-color' : 'yellow'});
    $("#HV0340").css({'background-color' : 'yellow'});
    $("#HV0341").css({'background-color' : 'yellow'});
    $("#HV0340").prop('title', 'sum of HV0340 and HV0341  should not be greater than HV0344');
    $("#HV0341").prop('title', 'sum of HV0340 and HV0341  should not be greater than HV0344');
    $("#HV0344").prop('title', 'HV0344 should not be less than sum of HV0340 and HV0341');
  }
   if(parseInt(HV0344)<(parseInt(HV0342)+parseInt(HV0343))){checker++;
    $("#HV0344").css({'background-color' : 'yellow'});
    $("#HV0342").css({'background-color' : 'yellow'});
    $("#HV0343").css({'background-color' : 'yellow'});
    $("#HV0342").prop('title', 'sum of HV0342 and HV0343  should not be greater than HV0344');
    $("#HV0343").prop('title', 'sum of HV0342 and HV0343  should not be greater than HV0344');
    $("#HV0344").prop('title', 'HV0344 should not be less than sum of HV0342 and HV0343');
 }
   if(parseInt(HV0333)!=(parseInt(HV0340)+parseInt(HV0341)+parseInt(HV0342)+parseInt(HV0343))){checker++;
    $("#HV0333").css({'background-color' : 'yellow'});
    $("#HV0340").css({'background-color' : 'yellow'});
    $("#HV0341").css({'background-color' : 'yellow'});
    $("#HV0342").css({'background-color' : 'yellow'});
    $("#HV0343").css({'background-color' : 'yellow'});
    $("#HV0340").prop('title', 'sum of HV0340,HV0341,HV0342,HV0343  should be equal to HV0333');
    $("#HV0341").prop('title', 'sum of HV0340,HV0341,HV0342,HV0343  should be equal to HV0333');
    $("#HV0342").prop('title', 'sum of HV0340,HV0341,HV0342,HV0343  should be equal to HV0333');
    $("#HV0343").prop('title', 'sum of HV0340,HV0341,HV0342,HV0343  should be equal to HV0333');
    $("#HV0333").prop('title', 'HV0333 should be equal to sum of HV0340,HV0341,HV0342,HV0343');
}
   //   
 //   3.8+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if(parseInt(HV0345)<parseInt(HV0349)){checker++;
    $("#HV0345").css({'background-color' : 'yellow'});
    $("#HV0349").css({'background-color' : 'yellow'});
    $("#HV0345").prop('title', 'HV0345 should not be less than HV0349');
    $("#HV0349").prop('title', 'HV0349 should not be greater than HV0344');
 }
   if(parseInt(HV0346)>parseInt(HV0349)){checker++;
    $("#HV0346").css({'background-color' : 'yellow'});
    $("#HV0349").css({'background-color' : 'yellow'});
    $("#HV0349").prop('title', 'HV0349 should not be less than HV0346');
    $("#HV0346").prop('title', 'HV0346 should not be greater than HV0349');
 }  
   if(parseInt(HV0347)>parseInt(HV0349)){checker++;
    $("#HV0347").css({'background-color' : 'yellow'});
    $("#HV0349").css({'background-color' : 'yellow'});
    $("#HV0349").prop('title', 'HV0349 should not be less than HV0347');
    $("#HV0347").prop('title', 'HV0347 should not be greater than HV0349');
 }
   if(parseInt(HV0348)>parseInt(HV0349)){checker++;
    $("#HV0348").css({'background-color' : 'yellow'});
    $("#HV0349").css({'background-color' : 'yellow'});
    $("#HV0349").prop('title', 'HV0349 should not be less than HV0348');
    $("#HV0348").prop('title', 'HV0348 should not be greater than HV0349');
    } 
   if(parseInt(HV0349)>parseInt(HV0345)){checker++;
    $("#HV0349").css({'background-color' : 'yellow'});
    $("#HV0345").css({'background-color' : 'yellow'});
    $("#HV0345").prop('title', 'HV0345 should not be less than HV0349');
    $("#HV0349").prop('title', 'HV0349 should not be greater than HV0345');
 }
    //SECONDARY
   if(parseInt(HV0346)>parseInt(HV0345)){checker++;
    $("#HV0346").css({'background-color' : 'yellow'});
    $("#HV0345").css({'background-color' : 'yellow'});
    $("#HV0345").prop('title', 'HV0345 should not be less than HV0346');
    $("#HV0346").prop('title', 'HV0346 should not be greater than HV0345');
 } 
   if(parseInt(HV0347)>parseInt(HV0345)){checker++;
    $("#HV0347").css({'background-color' : 'yellow'});
    $("#HV0345").css({'background-color' : 'yellow'});
    $("#HV0345").prop('title', 'HV0345 should not be less than HV0347');
    $("#HV0347").prop('title', 'HV0347 should not be greater than HV0345');
 }
   if(parseInt(HV0348)>parseInt(HV0345)){checker++;
    $("#HV0348").css({'background-color' : 'yellow'});
    $("#HV0345").css({'background-color' : 'yellow'});
    $("#HV0345").prop('title', 'HV0345 should not be less than HV0348');
    $("#HV0348").prop('title', 'HV0348 should not be greater than HV0345');
 } 
   
    //   3.9+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if(parseInt(HV0354)>parseInt(HV0373)){checker++;
    $("#HV0354").css({'background-color' : 'yellow'});
    $("#HV0373").css({'background-color' : 'yellow'});
    $("#HV0373").prop('title', 'HV0373 should not be less than HV0354');
    $("#HV0354").prop('title', 'HV0354 should not be greater than HV0373');
    }
   if(parseInt(HV0355)>parseInt(HV0370)){checker++;
    $("#HV0355").css({'background-color' : 'yellow'});
    $("#HV0370").css({'background-color' : 'yellow'});
    $("#HV0370").prop('title', 'HV0370 should not be less than HV0355');
    $("#HV0355").prop('title', 'HV0355 should not be greater than HV0370');
} 
   
   //   3.10+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if(parseInt(HV0904)>parseInt(HV0373)){checker++;
    $("#HV0904").css({'background-color' : 'yellow'});
    $("#HV0373").css({'background-color' : 'yellow'});
    $("#HV0373").prop('title', 'HV0373 should not be less than HV0904');
    $("#HV0904").prop('title', 'HV0904 should not be greater than HV0373');
 }
//   if(parseInt(HV0905)>parseInt(HV0374)){checker++;
//    $("#HV0905").css({'background-color' : 'yellow'});
//    $("#HV0374").css({'background-color' : 'yellow'});
// } 
  
   //   3.11+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   if(parseInt(HV0373)!=parseInt(HV0319)){checker++;
    $("#HV0373").css({'background-color' : 'yellow'});
    $("#HV0319").css({'background-color' : 'yellow'});
    $("#HV0373").prop('title', 'HV0373 should be equal to HV0319');
    $("#HV0319").prop('title', 'HV0319 should be equal to HV0373');
 }
  return checker;
}

function validateVMMC(){
  var checker=0;  

var HV0401=document.getElementById("HV0401").value;
var HV0402=document.getElementById("HV0402").value;
var HV0403=document.getElementById("HV0403").value;
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


if(HV0401==""){HV0401="0";}
if(HV0402==""){HV0402="0";}
if(HV0403==""){HV0403="0";}
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

 
 
   
  //   4.1+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   
//   if(parseInt(HV0401)>parseInt(HV0406)){checker++;
//    $("#HV0401").css({'background-color' : 'yellow'});
//    $("#HV0406").css({'background-color' : 'yellow'});
// }  
//   if(parseInt(HV0402)>parseInt(HV0406)){checker++;
//    $("#HV0402").css({'background-color' : 'yellow'});
//    $("#HV0406").css({'background-color' : 'yellow'});
// }
//   if(parseInt(HV0403)>parseInt(HV0406)){checker++;
//    $("#HV0403").css({'background-color' : 'yellow'});
//    $("#HV0406").css({'background-color' : 'yellow'});
// } 
   if(parseInt(HV0406)!=(parseInt(HV0401)+parseInt(HV0402)+parseInt(HV0403))){checker++;
       $("#HV0406").css({'background-color' : 'yellow'});
       $("#HV0401").css({'background-color' : 'yellow'});
       $("#HV0402").css({'background-color' : 'yellow'});
       $("#HV0403").css({'background-color' : 'yellow'});
    }
    //SECONDARY
   if(parseInt(HV0406)!=(parseInt(HV0407)+parseInt(HV0408)+parseInt(HV0409))){checker++;
   $("#HV0406").css({'background-color' : 'yellow'});
   $("#HV0407").css({'background-color' : 'yellow'});
   $("#HV0408").css({'background-color' : 'yellow'});
   $("#HV0409").css({'background-color' : 'yellow'});
}
   
   
    //   4.2+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   
   if(parseInt(HV0407)>parseInt(HV0406)){checker++;
    $("#HV0407").css({'background-color' : 'yellow'});
    $("#HV0406").css({'background-color' : 'yellow'});
 }  
   if(parseInt(HV0408)>parseInt(HV0406)){checker++;
    $("#HV0408").css({'background-color' : 'yellow'});
    $("#HV0406").css({'background-color' : 'yellow'});}
   if(parseInt(HV0409)>parseInt(HV0406)){checker++;
    $("#HV0409").css({'background-color' : 'yellow'});
    $("#HV0406").css({'background-color' : 'yellow'});
    } 
  
    //   4.3+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   
   if(parseInt(HV0410)>parseInt(HV0406)){checker++;
    $("#HV0410").css({'background-color' : 'yellow'});
    $("#HV0406").css({'background-color' : 'yellow'});
 }  
   if(parseInt(HV0411)>parseInt(HV0406)){checker++;
    $("#HV0411").css({'background-color' : 'yellow'});
    $("#HV0406").css({'background-color' : 'yellow'});
 }
   if(parseInt(HV0412)>parseInt(HV0406)){checker++;
    $("#HV0412").css({'background-color' : 'yellow'});
    $("#HV0406").css({'background-color' : 'yellow'});
 }
   if(parseInt(HV0413)>parseInt(HV0406)){checker++;
    $("#HV0413").css({'background-color' : 'yellow'});
    $("#HV0406").css({'background-color' : 'yellow'});
 }  
   if(parseInt(HV0414)>parseInt(HV0406)){checker++;
    $("#HV0414").css({'background-color' : 'yellow'});
    $("#HV0406").css({'background-color' : 'yellow'});
 }
   if(parseInt(HV0415)>parseInt(HV0406)){checker++;
    $("#HV0415").css({'background-color' : 'yellow'});
    $("#HV0406").css({'background-color' : 'yellow'});
 }
   
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
var HV0507=document.getElementById("HV0507").value;
var HV0508=document.getElementById("HV0508").value;
var HV0509=document.getElementById("HV0509").value;
var HV0510=document.getElementById("HV0510").value;
var HV0511=document.getElementById("HV0511").value;
var HV0512=document.getElementById("HV0512").value;
var HV0513=document.getElementById("HV0513").value;
var HV0514=document.getElementById("HV0514").value;

if(HV0501==""){HV0501="0";}
if(HV0502==""){HV0502="0";}
if(HV0503==""){HV0503="0";}
if(HV0504==""){HV0504="0";}
if(HV0505==""){HV0505="0";}
if(HV0506==""){HV0506="0";}
if(HV0507==""){HV0507="0";}
if(HV0508==""){HV0508="0";}
if(HV0509==""){HV0509="0";}
if(HV0510==""){HV0510="0";}
if(HV0511==""){HV0511="0";}
if(HV0512==""){HV0512="0";}
if(HV0513==""){HV0513="0";}
if(HV0514==""){HV0514="0";}

  if(parseInt(HV0507)!=(parseInt(HV0501)+parseInt(HV0502)+parseInt(HV0503)+parseInt(HV0504)+parseInt(HV0505)+parseInt(HV0506))){checker++;
    $("#HV0501").css({'background-color' : 'yellow'});
    $("#HV0502").css({'background-color' : 'yellow'});
    $("#HV0503").css({'background-color' : 'yellow'});
    $("#HV0504").css({'background-color' : 'yellow'});
    $("#HV0505").css({'background-color' : 'yellow'});
    $("#HV0506").css({'background-color' : 'yellow'});
    $("#HV0507").css({'background-color' : 'yellow'});
    
    $("#HV0501").prop('title', 'sum of HV0501,HV0502,HV0503,HV0504,HV0505,HV0506  should be equal to HV0507');
    $("#HV0502").prop('title', 'sum of HV0501,HV0502,HV0503,HV0504,HV0505,HV0506  should be equal to HV0507');
    $("#HV0503").prop('title', 'sum of HV0501,HV0502,HV0503,HV0504,HV0505,HV0506  should be equal to HV0507');
    $("#HV0504").prop('title', 'sum of HV0501,HV0502,HV0503,HV0504,HV0505,HV0506  should be equal to HV0507');
    $("#HV0505").prop('title', 'sum of HV0501,HV0502,HV0503,HV0504,HV0505,HV0506  should be equal to HV0507');
    $("#HV0506").prop('title', 'sum of HV0501,HV0502,HV0503,HV0504,HV0505,HV0506  should be equal to HV0507');
    $("#HV0507").prop('title', 'HV0507 should be equal to sum of HV0501,HV0502,HV0503,HV0504,HV0505,HV0506');
}

   if((parseInt(HV0501)+parseInt(HV0502))!=(parseInt(HV0508)+parseInt(HV0509))){checker++;
    $("#HV0501").css({'background-color' : 'yellow'});
    $("#HV0502").css({'background-color' : 'yellow'});
    $("#HV0508").css({'background-color' : 'yellow'});
    $("#HV0509").css({'background-color' : 'yellow'});
    $("#HV0501").prop('title', 'sum of HV0501 and HV0502  should be equal to the sum of HV0508 and HV0509');
    $("#HV0502").prop('title', 'sum of HV0501 and HV0502  should be equal to the sum of HV0508 and HV0509');
    $("#HV0508").prop('title', 'sum of HV0501 and HV0502  should be equal to the sum of HV0508 and HV0509');
    $("#HV0509").prop('title', 'sum of HV0501 and HV0502  should be equal to the sum of HV0508 and HV0509');   
 }
 if((parseInt(HV0503)+parseInt(HV0504))!=(parseInt(HV0510)+parseInt(HV0511))){checker++;
    $("#HV0503").css({'background-color' : 'yellow'});
    $("#HV0504").css({'background-color' : 'yellow'});
    $("#HV0510").css({'background-color' : 'yellow'});
    $("#HV0511").css({'background-color' : 'yellow'});
    $("#HV0503").prop('title', 'sum of HV0503 and HV0504  should be equal to the sum of HV0510 and HV0511');
    $("#HV0504").prop('title', 'sum of HV0503 and HV0504  should be equal to the sum of HV0510 and HV0511');
    $("#HV0510").prop('title', 'sum of HV0503 and HV0504  should be equal to the sum of HV0510 and HV0511');
    $("#HV0511").prop('title', 'sum of HV0503 and HV0504  should be equal to the sum of HV0510 and HV0511');   
 }
 
  if((parseInt(HV0505)+parseInt(HV0506))!=(parseInt(HV0512)+parseInt(HV0513))){checker++;
    $("#HV0505").css({'background-color' : 'yellow'});
    $("#HV0506").css({'background-color' : 'yellow'});
    $("#HV0512").css({'background-color' : 'yellow'});
    $("#HV0513").css({'background-color' : 'yellow'});
    $("#HV0505").prop('title', 'sum of HV0505 and HV0506  should be equal to the sum of HV0512 and HV0513');
    $("#HV0506").prop('title', 'sum of HV0505 and HV0506  should be equal to the sum of HV0512 and HV0513');
    $("#HV0512").prop('title', 'sum of HV0505 and HV0506  should be equal to the sum of HV0512 and HV0513');
    $("#HV0513").prop('title', 'sum of HV0505 and HV0506  should be equal to the sum of HV0512 and HV0513');   
 }
 
 if(parseInt(HV0514)!=(parseInt(HV0508)+parseInt(HV0509)+parseInt(HV0510)+parseInt(HV0511)+parseInt(HV0512)+parseInt(HV0513))){checker++;
    $("#HV0508").css({'background-color' : 'yellow'});
    $("#HV0509").css({'background-color' : 'yellow'});
    $("#HV0510").css({'background-color' : 'yellow'});
    $("#HV0511").css({'background-color' : 'yellow'});
    $("#HV0512").css({'background-color' : 'yellow'});
    $("#HV0513").css({'background-color' : 'yellow'});
    $("#HV0514").css({'background-color' : 'yellow'});
    $("#HV0508").prop('title', 'sum of HV0508,HV0509,HV0510,HV0511,HV0512,HV0513  should be equal to HV0514');
    $("#HV0509").prop('title', 'sum of HV0508,HV0509,HV0510,HV0511,HV0512,HV0513  should be equal to HV0514');
    $("#HV0510").prop('title', 'sum of HV0508,HV0509,HV0510,HV0511,HV0512,HV0513  should be equal to HV0514');
    $("#HV0511").prop('title', 'sum of HV0508,HV0509,HV0510,HV0511,HV0512,HV0513  should be equal to HV0514');
    $("#HV0512").prop('title', 'sum of HV0508,HV0509,HV0510,HV0511,HV0512,HV0513  should be equal to HV0514');
    $("#HV0513").prop('title', 'sum of HV0508,HV0509,HV0510,HV0511,HV0512,HV0513  should be equal to HV0514');
    $("#HV0514").prop('title', 'HV0514 should be equal to sum of HV0508,HV0509,HV0510,HV0511,HV0512,HV0513');
}

 return checker;
}

function validateBlood(){
var checker=0;
 
var HV0601=document.getElementById("HV0601").value;
var HV0602=document.getElementById("HV0602").value;
var HV0605=document.getElementById("HV0605").value;

 
if(HV0601==""){HV0601="0";}
if(HV0602==""){HV0602="0";}
if(HV0605==""){HV0605="0";}
 
  //   6+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//PRIMARY
   
   if(parseInt(HV0601)<(parseInt(HV0602)+parseInt(HV0605))){checker++;
    $("#HV0601").css({'background-color' : 'yellow'});
    $("#HV0602").css({'background-color' : 'yellow'});
    $("#HV0605").css({'background-color' : 'yellow'});
    $("#HV0602").prop('title', 'sum of HV0602,HV0605  should be equal to HV0601');
    $("#HV0605").prop('title', 'sum of HV0602,HV0605  should be equal to HV0601');
    $("#HV0601").prop('title', 'HV0601 should be equal to sum of HV0602,HV0605');
  }  
   if(parseInt(HV0602)>parseInt(HV0601)){checker++;
    $("#HV0602").css({'background-color' : 'yellow'});
    $("#HV0601").css({'background-color' : 'yellow'});
    $("#HV0601").prop('title', 'HV0601 should not be less than HV0602');
    $("#HV0602").prop('title', 'HV0602 should not be greater than HV0601');
 }
   if(parseInt(HV0605)>parseInt(HV0601)){checker++;
    $("#HV0605").css({'background-color' : 'yellow'});
    $("#HV0601").css({'background-color' : 'yellow'});
    $("#HV0601").prop('title', 'HV0601 should not be less than HV0605');
    $("#HV0605").prop('title', 'HV0605 should not be greater than HV0601');
 }  
 
    return checker;
}
