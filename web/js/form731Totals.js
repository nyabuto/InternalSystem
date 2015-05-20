/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    function testingTotal(){
  
    var HV0101,HV0102,total;
    HV0101=document.getElementById("HV0101").value;
    HV0102=document.getElementById("HV0102").value;
    if(HV0101==""){HV0101="0";}
    if(HV0102==""){HV0102="0";}
     total=parseInt(HV0101)+parseInt(HV0102); 
     document.getElementById("HV0103").value=total;
     autosave("HV0103");
    
}
//1.3 RECEIVING POSTIVE RESULTS

function postiveResults(){
    var HV0110,HV0111,HV0112,HV0113,HV0114,HV0115,total;
    HV0110=document.getElementById("HV0110").value;
    HV0111=document.getElementById("HV0111").value;
    HV0112=document.getElementById("HV0112").value;
    HV0113=document.getElementById("HV0113").value;
    HV0114=document.getElementById("HV0114").value;
    HV0115=document.getElementById("HV0115").value;
    if(HV0110==""){HV0110="0";}
    if(HV0111==""){HV0111="0";}
    if(HV0112==""){HV0112="0";}
    if(HV0113==""){HV0113="0";}
    if(HV0114==""){HV0114="0";}
    if(HV0115==""){HV0115="0";}
    total=parseInt(HV0110)+parseInt(HV0111)+parseInt(HV0112)+parseInt(HV0113)+parseInt(HV0114)+parseInt(HV0115);
    document.getElementById("HV0116").value=total;
    autosave("HV0116");
}
//2.1 TESTING FOR HIV

function testingForHIV(){
    var HV0201,HV0202,HV0203,HV0205,total,total2;
    HV0201=document.getElementById("HV0201").value;
    HV0202=document.getElementById("HV0202").value;
    HV0203=document.getElementById("HV0203").value;
    if(HV0201==""){HV0201="0";}
    if(HV0202==""){HV0202="0";}
    if(HV0203==""){HV0203="0";}
    total=parseInt(HV0201)+parseInt(HV0202)+parseInt(HV0203);
    
   document.getElementById("HV0204").value=total;
   autosave("HV0204");
   HV0205=document.getElementById("HV0205").value;
   if(HV0205==""){HV0205="0";}
   total2=total+parseInt(HV0205);
   document.getElementById("HV0210").value=total2;
     autosave("HV0210");
}
//2.2 HIV POSTIVE RESULTS

function HIVPostiveResults(){
    var HV0205,HV0206,HV0207,HV0208,HV0204,total,total2;
    HV0205=document.getElementById("HV0205") .value;
    HV0206=document.getElementById("HV0206") .value;
    HV0207=document.getElementById("HV0207") .value;
    HV0208=document.getElementById("HV0208") .value;
    if(HV0205==""){HV0205="0";}
    if(HV0206==""){HV0206="0";}
    if(HV0207==""){HV0207="0";}
    if(HV0208==""){HV0208="0";}
      total=parseInt(HV0205)+parseInt(HV0206)+parseInt(HV0207)+parseInt(HV0208);
    document.getElementById("HV0209") .value=total;
    autosave("HV0209");
    HV0204=document.getElementById("HV0204").value;
   if(HV0204==""){HV0204="0";}
   total2=parseInt(HV0204)+parseInt(HV0205);
   document.getElementById("HV0210").value=total2;
  autosave("HV0210"); 
}

//2.4 MATERNAL PROPHYLAXIS

function MaternalProphylaxis(){
    var HV0213,HV0214,HV0215,HV0216,total;
    HV0213=document.getElementById("HV0213").value;
    HV0214=document.getElementById("HV0214").value;
    HV0215=document.getElementById("HV0215").value;
    HV0216=document.getElementById("HV0216").value;
    if(HV0213==""){HV0213="0";}
    if(HV0214==""){HV0214="0";}
    if(HV0215==""){HV0215="0";}
    if(HV0216==""){HV0216="0";}
    total=parseInt(HV0213)+parseInt(HV0214)+parseInt(HV0215)+parseInt(HV0216);
   document.getElementById("HV0217").value=total; 
    autosave("HV0217"); 
}

//2.5 ASSESSMENT FOR ART ELIGIBILITY IN MCH
function ARTEligibility(){
    var HV0218,HV0219,total;
    HV0218=document.getElementById("HV0218").value;
    HV0219=document.getElementById("HV0219").value;
    if(HV0218==""){HV0218="0";}
    if(HV0219==""){HV0219="0";}
    total=parseInt(HV0218)+parseInt(HV0219);
    document.getElementById("HV0220").value=total;
    autosave("HV0220");  
}
//2.6 INFANT TESTING
function infantTesting(){
    var HV0224,HV0225,HV0226,total;
    HV0224=document.getElementById("HV0224").value;
    HV0225=document.getElementById("HV0225").value;
    HV0226=document.getElementById("HV0226").value;
    if(HV0224==""){HV0224="0";}
    if(HV0225==""){HV0225="0";}
    if(HV0226==""){HV0226="0";}
    total=parseInt(HV0224)+parseInt(HV0225)+parseInt(HV0226);
    document.getElementById("HV0228").value=total;
  autosave("HV0228");   
}
//2.7 CONFIRMED INFANT TEST RESULTS
function confirmedInfants(){
    var HV0229,HV0230,HV0231,total;
    HV0229=document.getElementById("HV0229").value;
    HV0230=document.getElementById("HV0230").value;
    HV0231=document.getElementById("HV0231").value;
    if(HV0229==""){HV0229="0";}
    if(HV0230==""){HV0230="0";}
    if(HV0231==""){HV0231="0";}
    total=parseInt(HV0229)+parseInt(HV0230)+parseInt(HV0231);
    
    document.getElementById("HV0232").value=total;
  autosave("HV0232");  
}
//2.8 INFANT FEEDING
function exposedAgedSix(){
    var HV0233,HV0234,HV0235,total;
    HV0233=document.getElementById("HV0233").value;
    HV0234=document.getElementById("HV0234").value;
    HV0235=document.getElementById("HV0235").value;
    if(HV0233==""){HV0233="0";}
    if(HV0234==""){HV0234="0";}
    if(HV0235==""){HV0235="0";}
    total=parseInt(HV0233)+parseInt(HV0234)+parseInt(HV0235);
    document.getElementById("HV0236").value=total;
    autosave("HV0236"); 
}
//2.8 INFANT FEEDING
function exposedAgedTwelve(){
    var HV0237,HV0238,HV0239,total;
    
   HV0237=document.getElementById("HV0237").value;
    HV0238=document.getElementById("HV0238").value;
    HV0239=document.getElementById("HV0239").value;
    if(HV0237==""){HV0237="0";}
    if(HV0238==""){HV0238="0";}
    if(HV0239==""){HV0239="0";}
    total=parseInt(HV0237)+parseInt(HV0238)+parseInt(HV0239);
    document.getElementById("HV0240").value=total; 
    autosave("HV0240"); 
}
//2.9 INFANT ARV PROPYLAXIS
function infantARV(){
var HV0241,HV0242,HV0243,total;
    
   HV0241=document.getElementById("HV0241").value;
    HV0242=document.getElementById("HV0242").value;
    HV0243=document.getElementById("HV0243").value;
    if(HV0241==""){HV0241="0";}
    if(HV0242==""){HV0242="0";}
    if(HV0243==""){HV0243="0";}
    total=parseInt(HV0241)+parseInt(HV0242)+parseInt(HV0243);
    document.getElementById("HV0244").value=total; 
    autosave("HV0244"); 
}
//3.1 ON COTRIMOXAZOLE PROPHYLAXIS
function totalCTX(){
    var HV0303,HV0304,HV0305,HV0306,total;
    HV0303=document.getElementById("HV0303").value;
    HV0304=document.getElementById("HV0304").value;
    HV0305=document.getElementById("HV0305").value;
    HV0306=document.getElementById("HV0306").value;
    if(HV0303==""){HV0303="0";}
    if(HV0304==""){HV0304="0";}
    if(HV0305==""){HV0305="0";}
    if(HV0306==""){HV0306="0";}
    total=parseInt(HV0303)+parseInt(HV0304)+parseInt(HV0305)+parseInt(HV0306);
    document.getElementById("HV0307").value=total;
    autosave("HV0307"); 
}
//3.2 ENROLLED IN CARE
function enrolledCare(){
  var HV0309,HV0310,HV0311,HV0312,total;
    HV0309=document.getElementById("HV0309").value;
    HV0310=document.getElementById("HV0310").value;
    HV0311=document.getElementById("HV0311").value;
    HV0312=document.getElementById("HV0312").value;
    if(HV0309==""){HV0309="0";}
    if(HV0310==""){HV0310="0";}
    if(HV0311==""){HV0311="0";}
    if(HV0312==""){HV0312="0";}
    total=parseInt(HV0309)+parseInt(HV0310)+parseInt(HV0311)+parseInt(HV0312);
    document.getElementById("HV0313").value=total;   
    autosave("HV0313");
}
//3.3 CURRENTLY IN CARE
function currentCare(){
  var HV0315,HV0316,HV0317,HV0318,total;
    HV0315=document.getElementById("HV0315").value;
    HV0316=document.getElementById("HV0316").value;
    HV0317=document.getElementById("HV0317").value;
    HV0318=document.getElementById("HV0318").value;
    if(HV0315==""){HV0315="0";}
    if(HV0316==""){HV0316="0";}
    if(HV0317==""){HV0317="0";}
    if(HV0318==""){HV0318="0";}
    total=parseInt(HV0315)+parseInt(HV0316)+parseInt(HV0317)+parseInt(HV0318);
    document.getElementById("HV0319").value=total; 
    autosave("HV0319");
}
//3.4 STARTING ART
function startingART(){
     var HV0320,HV0321,HV0322,HV0323,HV0324,total;
      var HV0328,HV0329,HV0330,HV0331,HV0332;
      var HV0334,HV0335,HV0336,HV0337,HV0338,HV0339;
    HV0320=document.getElementById("HV0320").value; 
    HV0321=document.getElementById("HV0321").value;
    HV0322=document.getElementById("HV0322").value;
    HV0323=document.getElementById("HV0323").value;
    HV0324=document.getElementById("HV0324").value;
    
    HV0328=document.getElementById("HV0328").value;
    HV0329=document.getElementById("HV0329").value;
    HV0330=document.getElementById("HV0330").value;
    HV0331=document.getElementById("HV0331").value;
    HV0332=document.getElementById("HV0332").value;
    
    if(HV0320==""){HV0320="0";}
    if(HV0321==""){HV0321="0";}
    if(HV0322==""){HV0322="0";}
    if(HV0323==""){HV0323="0";}
    if(HV0324==""){HV0324="0";}
    
    
    if(HV0328==""){HV0328="0";}
    if(HV0329==""){HV0329="0";}
    if(HV0330==""){HV0330="0";}
    if(HV0331==""){HV0331="0";}
    if(HV0332==""){HV0332="0";}
    
     
    total=parseInt(HV0321)+parseInt(HV0322)+parseInt(HV0323)+parseInt(HV0324);
    document.getElementById("HV0325").value=total; 
            autosave("HV0325");
    HV0334=parseInt(HV0320)+parseInt(HV0328);
            document.getElementById("HV0334").value=HV0334;
            autosave("HV0334");
    HV0335=parseInt(HV0321)+parseInt(HV0329);
            document.getElementById("HV0335").value=HV0335;
            autosave("HV0335");
    HV0336=parseInt(HV0322)+parseInt(HV0330);
            document.getElementById("HV0336").value=HV0336;
            autosave("HV0336");
    HV0337=parseInt(HV0323)+parseInt(HV0331);
            document.getElementById("HV0337").value=HV0337;
            autosave("HV0337");
    HV0338=parseInt(HV0324)+parseInt(HV0332);
            document.getElementById("HV0338").value=HV0338;
            autosave("HV0338");
   
    HV0339=parseInt(HV0334)+parseInt(HV0335)+parseInt(HV0336)+parseInt(HV0337)+parseInt(HV0338);
   document.getElementById("HV0339").value=HV0339;
   autosave("HV0339");
}
//3.5 REVISITS ON ART
function revisitART(){
  var HV0320,HV0321,HV0322,HV0323,HV0324,total;
      var HV0328,HV0329,HV0330,HV0331,HV0332;
      var HV0334,HV0335,HV0336,HV0337,HV0338,HV0339;
    HV0320=document.getElementById("HV0320").value; 
    HV0321=document.getElementById("HV0321").value;
    HV0322=document.getElementById("HV0322").value;
    HV0323=document.getElementById("HV0323").value;
    HV0324=document.getElementById("HV0324").value;
    
    HV0328=document.getElementById("HV0328").value;
    HV0329=document.getElementById("HV0329").value;
    HV0330=document.getElementById("HV0330").value;
    HV0331=document.getElementById("HV0331").value;
    HV0332=document.getElementById("HV0332").value;
    
    if(HV0320==""){HV0320="0";}
    if(HV0321==""){HV0321="0";}
    if(HV0322==""){HV0322="0";}
    if(HV0323==""){HV0323="0";}
    if(HV0324==""){HV0324="0";}
    
    if(HV0328==""){HV0328="0";}
    if(HV0329==""){HV0329="0";}
    if(HV0330==""){HV0330="0";}
    if(HV0331==""){HV0331="0";}
    if(HV0332==""){HV0332="0";}
    
    total=parseInt(HV0329)+parseInt(HV0330)+parseInt(HV0331)+parseInt(HV0332);
    document.getElementById("HV0333").value=total;  
        autosave("HV0333");
    HV0334=parseInt(HV0320)+parseInt(HV0328);
            document.getElementById("HV0334").value=HV0334;
            autosave("HV0334");
    HV0335=parseInt(HV0321)+parseInt(HV0329);
            document.getElementById("HV0335").value=HV0335;
            autosave("HV0335");
    HV0336=parseInt(HV0322)+parseInt(HV0330);
            document.getElementById("HV0336").value=HV0336;
            autosave("HV0336");
    HV0337=parseInt(HV0323)+parseInt(HV0331);
            document.getElementById("HV0337").value=HV0337;
            autosave("HV0337");
    HV0338=parseInt(HV0324)+parseInt(HV0332);
            document.getElementById("HV0338").value=HV0338;
            autosave("HV0338");
   
    HV0339=parseInt(HV0334)+parseInt(HV0335)+parseInt(HV0336)+parseInt(HV0337)+parseInt(HV0338);
   document.getElementById("HV0339").value=HV0339;
   autosave("HV0339");
}
//3.6 CURRENT ON ART [ALL]
//function currentART(){
//     var HV0335,HV0336,HV0337,HV0338,total;
//    HV0335=document.getElementById("HV0335").value;
//    HV0336=document.getElementById("HV0336").value;
//    HV0337=document.getElementById("HV0337").value;
//    HV0338=document.getElementById("HV0338").value;
//    if(HV0335==""){HV0335="0";}
//    if(HV0336==""){HV0336="0";}
//    if(HV0337==""){HV0337="0";}
//    if(HV0338==""){HV0338="0";}
//    total=parseInt(HV0335)+parseInt(HV0336)+parseInt(HV0337)+parseInt(HV0338);
//    document.getElementById("HV0339").value=total; 
//}
//3.7 CUMULATIVE EVER ON ART
function cumulativeART(){
   var HV0340,HV0341,HV0342,HV0343,total;
    HV0340=document.getElementById("HV0340").value;
    HV0341=document.getElementById("HV0341").value;
    HV0342=document.getElementById("HV0342").value;
    HV0343=document.getElementById("HV0343").value;
    if(HV0340==""){HV0340="0";}
    if(HV0341==""){HV0341="0";}
    if(HV0342==""){HV0342="0";}
    if(HV0343==""){HV0343="0";}
    total=parseInt(HV0340)+parseInt(HV0341)+parseInt(HV0342)+parseInt(HV0343);
    document.getElementById("HV0344").value=total;  
    autosave("HV0344");
}
//3.8 SURVIVAL AND RETENTION ON ART AT 12 MONTHS
function therapyTwelveMonths(){
   var HV0346,HV0347,HV0348,total;
   
    HV0346=document.getElementById("HV0346").value;
    HV0347=document.getElementById("HV0347").value;
    HV0348=document.getElementById("HV0348").value;
   
    if(HV0346==""){HV0346="0";}
    if(HV0347==""){HV0347="0";}
    if(HV0348==""){HV0348="0";}
    total=parseInt(HV0346)+parseInt(HV0347)+parseInt(HV0348);
    document.getElementById("HV0349").value=total;  
    autosave("HV0349");
}
//3.9 SCREENING
function screening(){
    var HV0350,HV0351,HV0352,HV0353,total;
    HV0350=document.getElementById("HV0350").value;
    HV0351=document.getElementById("HV0351").value;
    HV0352=document.getElementById("HV0352").value;
    HV0353=document.getElementById("HV0353").value;
    if(HV0350==""){HV0350="0";}
    if(HV0351==""){HV0351="0";}
    if(HV0352==""){HV0352="0";}
    if(HV0353==""){HV0353="0";}
    total=parseInt(HV0350)+parseInt(HV0351)+parseInt(HV0352)+parseInt(HV0353);
    document.getElementById("HV0354").value=total;   
    autosave("HV0354");
}
//3.11 HIV CARE VISITS
function HIVCareVisits(){
     var HV0371,HV0372,total;
   HV0371=document.getElementById("HV0371").value;
    HV0372=document.getElementById("HV0372").value;
    if(HV0371==""){HV0371="0";}
    if(HV0372==""){HV0372="0";}
    total=parseInt(HV0371)+parseInt(HV0372);
    document.getElementById("HV0373").value=total; 
    autosave("HV0373");
}
//4.1 NUMBER CIRCUMCISED
function numberCircumcised(){
  var HV0401,HV0402,HV0403,total;
   
    HV0401=document.getElementById("HV0401").value;
    HV0402=document.getElementById("HV0402").value;
    HV0403=document.getElementById("HV0403").value;
   
    if(HV0401==""){HV0401="0";}
    if(HV0402==""){HV0402="0";}
    if(HV0403==""){HV0403="0";}
    total=parseInt(HV0401)+parseInt(HV0402)+parseInt(HV0403);
    document.getElementById("HV0406").value=total; 
     autosave("HV0406");
}
//4.3 ADVERSE EVENTS (CIRCUMCISION)
function totalAEDuring(){
     var HV0410,HV0411,total;
   HV0410=document.getElementById("HV0410").value;
    HV0411=document.getElementById("HV0411").value;
    if(HV0410==""){HV0410="0";}
    if(HV0411==""){HV0411="0";}
    total=parseInt(HV0410)+parseInt(HV0411);
    document.getElementById("HV0414").value=total;
    autosave("HV0414");
}

function totalAEPost(){
     var HV0412,HV0413,total;
   HV0412=document.getElementById("HV0412").value;
    HV0413=document.getElementById("HV0413").value;
    if(HV0412==""){HV0412="0";}
    if(HV0413==""){HV0413="0";}
    total=parseInt(HV0412)+parseInt(HV0413);
    document.getElementById("HV0415").value=total;
    autosave("HV0415");
}

//5.1 TYPE OF EXPOSURE
function typeExposure(){
 var HV0501,HV0502, HV0503,HV0504, HV0505,HV0506,total;
   HV0501=document.getElementById("HV0501").value;
    HV0502=document.getElementById("HV0502").value;
    HV0503=document.getElementById("HV0503").value;
    HV0504=document.getElementById("HV0504").value;
    HV0505=document.getElementById("HV0505").value;
    HV0506=document.getElementById("HV0506").value;
    if(HV0501==""){HV0501="0";}
    if(HV0502==""){HV0502="0";}
    if(HV0503==""){HV0503="0";}
    if(HV0504==""){HV0504="0";}
    if(HV0505==""){HV0505="0";}
    if(HV0506==""){HV0506="0";}
    total=parseInt(HV0501)+parseInt(HV0502)+parseInt(HV0503)+parseInt(HV0504)+parseInt(HV0505)+parseInt(HV0506);
    document.getElementById("HV0507").value=total;  
    autosave("HV0507");
}
//5.2 PROVIDED WITH PROPHYLAXIS
function ProvidedProphylaxis(){
 var HV0508,HV0509, HV0510,HV0511, HV0512,HV0513,total;
    HV0508=document.getElementById("HV0508").value;
    HV0509=document.getElementById("HV0509").value;
    HV0510=document.getElementById("HV0510").value;
    HV0511=document.getElementById("HV0511").value;
    HV0512=document.getElementById("HV0512").value;
    HV0513=document.getElementById("HV0513").value;
    if(HV0508==""){HV0508="0";}
    if(HV0509==""){HV0509="0";}
    if(HV0510==""){HV0510="0";}
    if(HV0511==""){HV0511="0";}
    if(HV0512==""){HV0512="0";}
    if(HV0513==""){HV0513="0";}
    total=parseInt(HV0508)+parseInt(HV0509)+parseInt(HV0510)+parseInt(HV0511)+parseInt(HV0512)+parseInt(HV0513);
    document.getElementById("HV0514").value=total; 
    autosave("HV0514");
}
