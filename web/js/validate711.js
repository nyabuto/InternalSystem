/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function validateFP(){
    
//    ALERT("FP ENTERED");
 var checker=0;
 var FPCLIENTSN=document.getElementById("FPCLIENTSN").value;
   var FPCLIENTSR=document.getElementById("FPCLIENTSR").value;
   var FPCLIENTST=document.getElementById("FPCLIENTST").value;

 if(FPCLIENTSN==""){FPCLIENTSN="0";}
if(FPCLIENTSR==""){FPCLIENTSR="0";}
if(FPCLIENTST==""){FPCLIENTST="0";}

var total=0;
total=parseInt(FPCLIENTSN)+parseInt(FPCLIENTSR);


 if(total!=parseInt(FPCLIENTST)){
     checker++;
   $("#FPCLIENTSN").css({'background-color' : 'yellow'});
   $("#FPCLIENTSR").css({'background-color' : 'yellow'});
   $("#FPCLIENTST").css({'background-color' : 'yellow'});
  
      var data=$("#data_elements").val();
   data+="@FPCLIENTSN_FPCLIENTSR_FPCLIENTST";
   $("#data_elements").val(data);
   
    var description=$("#description").val();
    description+="@Total of FP clients is greater than the sum of new and revisits";
   $("#description").val(description);
   
    }
 return checker;
}
function validateVCT(){
    
//    alert(" VCT ENTERED");
 var checker=0;
 //FEMALE  KIDS
 
 var VCTClient_Tested_CF=document.getElementById("VCTClient_Tested_CF").value;
   var VCTClient_HIV_CF=document.getElementById("VCTClient_HIV_CF").value;
//   alert(VCTClient_Tested_CF +" nnn "+VCTClient_HIV_CF);
   // MALE
   var VCTClient_Tested_CM=document.getElementById("VCTClient_Tested_CM").value;
   var VCTClient_HIV_CM=document.getElementById("VCTClient_HIV_CM").value;
//   alert(VCTClient_Tested_CM +" nnn "+VCTClient_HIV_CF);
   /// ADULTS FEMALES
   
   var VCTClient_HIV_AF=document.getElementById("VCTClient_HIV_AF").value;
   var VCTClient_Tested_AF=document.getElementById("VCTClient_Tested_AF").value;
   
    // ADULTS MALES
   var VCTClient_Tested_AM=document.getElementById("VCTClient_Tested_AM").value;
   var VCTClient_HIV_AM=document.getElementById("VCTClient_HIV_AM").value;
   
   /// totals
   var VCTClient_Tested_TOT=document.getElementById("VCTClient_Tested_TOT").value;
   var VCTClient_HIV_TOT=document.getElementById("VCTClient_HIV_TOT").value;
   
   


 if(VCTClient_Tested_CF==""){VCTClient_Tested_CF="0";}
if(VCTClient_HIV_CF==""){VCTClient_HIV_CF="0";}

if(VCTClient_Tested_CM==""){VCTClient_Tested_CM="0";}
if(VCTClient_HIV_CM==""){VCTClient_HIV_CM="0";}

if(VCTClient_HIV_AF==""){VCTClient_HIV_AF="0";}
if(VCTClient_Tested_AF==""){VCTClient_Tested_AF="0";}

if(VCTClient_Tested_AM==""){VCTClient_Tested_AM="0";}
if(VCTClient_HIV_AM==""){VCTClient_HIV_AM="0";}

if(VCTClient_Tested_TOT==""){VCTClient_Tested_TOT="0";}
if(VCTClient_HIV_TOT==""){VCTClient_HIV_TOT="0";}

//kids
 if(parseInt(VCTClient_HIV_CF)> parseInt(VCTClient_Tested_CF)){
     checker++;
    
   $("#VCTClient_HIV_CF").css({'background-color' : 'yellow'});
   $("#VCTClient_Tested_CF").css({'background-color' : 'yellow'});
   
   $("#VCTClient_HIV_CF").prop('title', 'HIV+ should not be greater than Tested');
   $("#VCTClient_Tested_CF").prop('title', 'HIV+ should not be less than Tested');
   
   var data=$("#data_elements").val();
    data+="@VCT CLIENTS HIV + F(15-24),VCT CLIENTS TESTED + F(15-24)";
   $("#data_elements").val(data); 
   
   var description=$("#description").val();
    description+="@HIV + is greater than Tested (15-24 F)";
   $("#description").val(description);
   
   
     }
 if(parseInt(VCTClient_HIV_CM)> parseInt(VCTClient_Tested_CM)){
     checker++;
   
   $("#VCTClient_Tested_CM").css({'background-color' : 'yellow'});
   $("#VCTClient_HIV_CM").css({'background-color' : 'yellow'});
   
    $("#VCTClient_Tested_CM").prop('title', 'HIV+ should not be greater than Tested');
   $("#VCTClient_HIV_CM").prop('title', 'HIV+ should not be less than Tested');
   
   var data=$("#data_elements").val();
    data+="@VCT CLIENTS HIV + M(15-24),VCT CLIENTS TESTED + M(15-24)";
   $("#data_elements").val(data); 
   
   var description=$("#description").val();
    description+="@HIV + is greater than Tested (15-24) M";
   $("#description").val(description);
   
   
     }
     
     // female adults
 if(parseInt(VCTClient_HIV_AF)> parseInt(VCTClient_Tested_AF)){
     checker++;
 
   $("#VCTClient_HIV_AF").css({'background-color' : 'yellow'});
   $("#VCTClient_Tested_AF").css({'background-color' : 'yellow'});
   
   
      $("#VCTClient_HIV_AF").prop('title', 'HIV+ should not be greater than Tested');
   $("#VCTClient_Tested_AF").prop('title', 'HIV+ should not be less than Tested');
   
   var data=$("#data_elements").val();
    data+="@VCT CLIENTS HIV + F(>25),VCT CLIENTS TESTED + F(>25)";
   $("#data_elements").val(data); 
   
   var description=$("#description").val();
    description+="@HIV + is greater than Tested (>25) F";
   $("#description").val(description);
   
   
   
   
   
   
   
     }
//     male adults
 if(parseInt(VCTClient_HIV_AM)> parseInt(VCTClient_Tested_AM)){
     checker++;

   $("#VCTClient_Tested_AM").css({'background-color' : 'yellow'});
   $("#VCTClient_HIV_AM").css({'background-color' : 'yellow'});
   
   
   
   
      $("#VCTClient_HIV_AM").prop('title', 'HIV+ should not be greater than Tested');
   $("#VCTClient_Tested_AM").prop('title', 'HIV+ should not be less than Tested');
   
   var data=$("#data_elements").val();
    data+="@VCT CLIENTS HIV + M(>25),VCT CLIENTS TESTED + M(>25)";
   $("#data_elements").val(data); 
   
   var description=$("#description").val();
    description+="@HIV + is greater than Tested (>25) M";
   $("#description").val(description);
   
     }
     
     
//     total
 if(parseInt(VCTClient_HIV_TOT)> parseInt(VCTClient_Tested_TOT)){
     checker++;
   
   $("#VCTClient_HIV_TOT").css({'background-color' : 'yellow'});
   $("#VCTClient_Tested_TOT").css({'background-color' : 'yellow'});
   
   
   
      $("#VCTClient_HIV_TOT").prop('title', 'HIV+ Total should not be greater than Tested');
   $("#VCTClient_Tested_TOT").prop('title', 'HIV+ Total should not be less than Tested');
   
   var data=$("#data_elements").val();
    data+="@VCT CLIENTS HIV + Total,VCT CLIENTS TESTED Total";
   $("#data_elements").val(data); 
   
   var description=$("#description").val();
    description+="@HIV+ Total is greater than Total Tested ";
   $("#description").val(description);
   
   
   
   
     }
 return checker;
}
function validateDTC(){
  var checker=0;
//    var facil=document.getElementById("facility");
//    var facilityname = facil.options[facil.selectedIndex].text;
//    alert(facilityname);
 //FEMALE  KIDS
 // FOR OUTPATIENT 
 var DTCB_Test_Out_CF=document.getElementById("DTCB_Test_Out_CF").value;
   var DTCC_HIV_Out_CF=document.getElementById("DTCC_HIV_Out_CF").value;
   
 var DTCB_Test_Out_CM=document.getElementById("DTCB_Test_Out_CM").value;
   var DTCC_HIV_Out_CM=document.getElementById("DTCC_HIV_Out_CM").value;


var DTCB_Test_Out_AM=document.getElementById("DTCB_Test_Out_AM").value;
   var DTCC_HIV_Out_AM=document.getElementById("DTCC_HIV_Out_AM").value;
   
   var DTCB_Test_Out_AF=document.getElementById("DTCB_Test_Out_AF").value;
   var DTCC_HIV_Out_AF=document.getElementById("DTCC_HIV_Out_AF").value;
   
   
   // FOR INPATIENT 
   
 var DTCB_Test_In_CF=document.getElementById("DTCB_Test_In_CF").value;
   var DTCC_HIV_In_CF=document.getElementById("DTCC_HIV_In_CF").value;
   
 var DTCB_Test_In_CM=document.getElementById("DTCB_Test_In_CM").value;
   var DTCC_HIV_In_CM=document.getElementById("DTCC_HIV_In_CM").value;


var DTCB_Test_In_AM=document.getElementById("DTCB_Test_In_AM").value;
   var DTCC_HIV_In_AM=document.getElementById("DTCC_HIV_In_AM").value;
   
   var DTCB_Test_In_AF=document.getElementById("DTCB_Test_In_AF").value;
   var DTCC_HIV_In_AF=document.getElementById("DTCC_HIV_In_AF").value;
   


// FOR OUTPATIENT 

if(DTCB_Test_Out_CF==""){DTCB_Test_Out_CF="0";}
if(DTCC_HIV_Out_CF==""){DTCC_HIV_Out_CF="0";}
if(DTCB_Test_Out_CM==""){DTCB_Test_Out_CM="0";}
if(DTCC_HIV_Out_CM==""){DTCC_HIV_Out_CM="0";}
if(DTCB_Test_Out_AF==""){DTCB_Test_Out_AF="0";}
if(DTCC_HIV_Out_AF==""){DTCC_HIV_Out_AF="0";}
if(DTCB_Test_Out_AM==""){DTCB_Test_Out_AM="0";}
if(DTCC_HIV_Out_AM==""){DTCC_HIV_Out_AM="0";}

// FOR INPATIENT 
if(DTCB_Test_In_CF==""){DTCB_Test_In_CF="0";}
if(DTCC_HIV_In_CF==""){DTCC_HIV_In_CF="0";}
if(DTCB_Test_In_CM==""){DTCB_Test_In_CM="0";}
if(DTCC_HIV_In_CM==""){DTCC_HIV_In_CM="0";}
if(DTCB_Test_In_AF==""){DTCB_Test_In_AF="0";}
if(DTCC_HIV_In_AF==""){DTCC_HIV_In_AF="0";}
if(DTCB_Test_In_AM==""){DTCB_Test_In_AM="0";}
if(DTCC_HIV_In_AM==""){DTCC_HIV_In_AM="0";}



    
   if(parseInt(DTCC_HIV_Out_CF)> parseInt(DTCB_Test_Out_CF)){
     checker++;
   
   $("#DTCC_HIV_Out_CF").css({'background-color' : 'yellow'});
   $("#DTCB_Test_Out_CF").css({'background-color' : 'yellow'});
   
   
   
   
      $("#DTCC_HIV_Out_CF").prop('title', 'HIV+ Outpatient should not be greater than Tested OutPatient');
   $("#DTCB_Test_Out_CF").prop('title', 'Tested OutPatient should greater or equal  to HIV+ Outpatient');
   
   var data=$("#data_elements").val();
    data+="@DTC HIV+ OUTPATIENT F(15-24),DTC TESTED OUTPATIENT F(15-24)";
   $("#data_elements").val(data); 
   
   var description=$("#description").val();
    description+="@HIV+ is greater than Tested (15-24 F)-OUTPATIENT";
   $("#description").val(description);
   
     }
   if(parseInt(DTCC_HIV_Out_CM)> parseInt(DTCB_Test_Out_CM)){
     checker++;
   
   $("#DTCC_HIV_Out_CM").css({'background-color' : 'yellow'});
   $("#DTCB_Test_Out_CM").css({'background-color' : 'yellow'});
  
  
   $("#DTCC_HIV_Out_CM").prop('title', 'HIV+ Outpatient should not be greater than Tested OutPatient');
   $("#DTCB_Test_Out_CM").prop('title', 'Tested OutPatient should greater or equal  to HIV+ Outpatient');
   
   var data=$("#data_elements").val();
    data+="@DTC HIV+ OUTPATIENT M(15-24),DTC TESTED OUTPATIENT M(15-24)";
   $("#data_elements").val(data); 
   
   var description=$("#description").val();
    description+="@HIV+ is greater than Tested (15-24 M)-OUTPATIENT";
   $("#description").val(description);
   
   
   
     }
   if(parseInt(DTCC_HIV_Out_AM)> parseInt(DTCB_Test_Out_AM)){
     checker++;
   
   $("#DTCC_HIV_Out_AM").css({'background-color' : 'yellow'});
   $("#DTCB_Test_Out_AM").css({'background-color' : 'yellow'});
   
   
   
    $("#DTCC_HIV_Out_AM").prop('title', 'HIV+ Outpatient should not be greater than Tested OutPatient');
   $("#DTCB_Test_Out_AM").prop('title', 'Tested OutPatient should greater or equal  to HIV+ Outpatient');
   
   var data=$("#data_elements").val();
    data+="@DTC HIV+ OUTPATIENT M(>25),DTC TESTED OUTPATIENT M(>25)";
   $("#data_elements").val(data); 
   
   var description=$("#description").val();
    description+="@HIV+ is greater than Tested (>25 M)-OUTPATIENT";
   $("#description").val(description);
   
   
   
     }
      if(parseInt(DTCC_HIV_Out_AF)> parseInt(DTCB_Test_Out_AF)){
     checker++;
   
   $("#DTCC_HIV_Out_AF").css({'background-color' : 'yellow'});
   $("#DTCB_Test_Out_AF").css({'background-color' : 'yellow'});
   
   
    $("#DTCC_HIV_Out_AF").prop('title', 'HIV+ Outpatient should not be greater than Tested OutPatient');
   $("#DTCB_Test_Out_AF").prop('title', 'Tested OutPatient should greater or equal  to HIV+ Outpatient');
   
   var data=$("#data_elements").val();
    data+="@DTC HIV+ OUTPATIENT F(>25),DTC TESTED OUTPATIENT F(>25)";
   $("#data_elements").val(data); 
   
   var description=$("#description").val();
    description+="@HIV+ is greater than Tested (>25 F)-OUTPATIENT";
   $("#description").val(description);
   
   
   
   
   
   
     }
     
     
     
     // FOR INPATIENT 
     
     
     if(parseInt(DTCC_HIV_In_CF)> parseInt(DTCB_Test_In_CF)){
     checker++;
   
   $("#DTCC_HIV_In_CF").css({'background-color' : '#4b8df8'});
   $("#DTCB_Test_In_CF").css({'background-color' : '#4b8df8'});
   
   
   
   $("#DTCC_HIV_In_CF").prop('title', 'HIV+ inpatient should not be greater than Tested inpatient');
   $("#DTCB_Test_In_CF").prop('title', 'Tested inpatient should greater or equal  to HIV+ inpatient');
   
   var data=$("#data_elements").val();
    data+="@DTC HIV+ INPATIENT F(0-14),DTC TESTED INPATIENT F(0-14)";
   $("#data_elements").val(data); 
   
   var description=$("#description").val();
    description+="@HIV+ is greater than Tested (0-14 F)-INPATIENT";
   $("#description").val(description);
   
   
   
     }
   if(parseInt(DTCC_HIV_In_CM)> parseInt(DTCB_Test_In_CM)){
     checker++;
   
   $("#DTCC_HIV_In_CM").css({'background-color' : '#4b8df8'});
   $("#DTCB_Test_In_CM").css({'background-color' : '#4b8df8'});
   
   
   
     $("#DTCC_HIV_In_CM").prop('title', 'HIV+ inpatient should not be greater than Tested inpatient');
   $("#DTCB_Test_In_CM").prop('title', 'Tested inpatient should greater or equal  to HIV+ inpatient');
   
   var data=$("#data_elements").val();
    data+="@DTC HIV+ INPATIENT M(0-14),DTC TESTED INPATIENT M(0-14)";
   $("#data_elements").val(data); 
   
   var description=$("#description").val();
    description+="@HIV+ is greater than Tested (0-14 M)-INPATIENT";
   $("#description").val(description);
   
   
     }
   if(parseInt(DTCC_HIV_In_AM)> parseInt(DTCB_Test_In_AM)){
     checker++;
   
   $("#DTCC_HIV_In_AM").css({'background-color' : '#4b8df8'});
   $("#DTCB_Test_In_AM").css({'background-color' : '#4b8df8'});
   
   
   
    $("#DTCC_HIV_In_AM").prop('title', 'HIV+ inpatient should not be greater than Tested inpatient');
   $("#DTCB_Test_In_AM").prop('title', 'Tested inpatient should greater or equal  to HIV+ inpatient');
   
   var data=$("#data_elements").val();
    data+="@DTC HIV+ INPATIENT M(>14),DTC TESTED INPATIENT M(>14)";
   $("#data_elements").val(data); 
   
   var description=$("#description").val();
    description+="@HIV+ is greater than Tested (>14 M)-INPATIENT";
   $("#description").val(description);
   
     }
      if(parseInt(DTCC_HIV_In_AF)> parseInt(DTCB_Test_In_AF)){
     checker++;
   
   $("#DTCC_HIV_In_AF").css({'background-color' : '#4b8df8'});
   $("#DTCB_Test_In_AF").css({'background-color' : '#4b8df8'});
   
    
    $("#DTCC_HIV_In_AF").prop('title', 'HIV+ inpatient should not be greater than Tested inpatient');
   $("#DTCB_Test_In_AF").prop('title', 'Tested inpatient should greater or equal  to HIV+ inpatient');
   
   var data=$("#data_elements").val();
    data+="@DTC HIV+ INPATIENT F(>14),DTC TESTED INPATIENT F(>14)";
   $("#data_elements").val(data); 
   
   var description=$("#description").val();
    description+="@HIV+ is greater than Tested (>14 F)-INPATIENT";
   $("#description").val(description);
   
     }
     
     
 
 return checker;  
    
    
}