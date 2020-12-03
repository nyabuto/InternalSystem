<%-- 
    Document   : load733B
    Created on : Aug 15, 2017, 8:50:26 AM
    Author     : GNyabuto
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>MOH 733B - Nutrition</title>
     
    <link rel="shortcut icon" href="images/imis.png"/>
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
  <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
   <link href="assets/css/metro.css" rel="stylesheet" />
   <link href="assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
   <link href="assets/bootstrap-fileupload/bootstrap-fileupload.css" rel="stylesheet" />
   <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
   <link href="assets/css/style.css" rel="stylesheet" />
   <link href="assets/css/style_responsive.css" rel="stylesheet" />
   <link href="assets/css/style_default.css" rel="stylesheet" id="style_color" />
   <link rel="stylesheet" type="text/css" href="assets/gritter/css/jquery.gritter.css" />
   <link rel="stylesheet" type="text/css" href="assets/chosen-bootstrap/chosen/chosen.css" />
   <link rel="stylesheet" type="text/css" href="assets/jquery-tags-input/jquery.tagsinput.css" />
   <link rel="stylesheet" type="text/css" href="assets/clockface/css/clockface.css" />
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-wysihtml5/bootstrap-wysihtml5.css" />
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-datepicker/css/bootstrap-datepicker.css" />
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-timepicker/compiled/timepicker.css" />
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-colorpicker/css/colorpicker.css" />
   <link rel="stylesheet" href="assets/bootstrap-toggle-buttons/static/stylesheets/bootstrap-toggle-buttons.css" />
   <link rel="stylesheet" href="assets/data-tables/DT_bootstrap.css" />
   <link rel="stylesheet" type="text/css" href="assets/uniform/css/uniform.default.css" />
   <link rel="stylesheet" href="select2/css/select2.css">

        <style>
            hr {
                background-color: #fff;
                border-top: 2px dashed #8c8b8b;
                border-width: 2px;
            }
fieldset.formatter {
    border: 2px groove black !important;
   
    /*padding: 0 1.4em 1.4em 1.4em !important;*/
    margin: 0 0 1.5em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
   
}

legend.formatter {
    border: 0px groove black !important;
    margin: 0 0 0.0em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
    font-size: 1.2em !important;
    /*font-weight: bold !important;*/
    text-align: center !important;
    width:inherit; /* Or auto */
    padding:0 10px; /* To give a bit of padding on the left and right */
    border-bottom:none;
    margin-left:50px;

}
</style>
<!--form 733b styles-->
<link rel="stylesheet" href="css/733b.css"/>
   
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">
   <!-- BEGIN HEADER -->
   <div class="header navbar navbar-inverse navbar-fixed-top">
      <!-- BEGIN TOP NAVIGATION BAR -->
      <div class="navbar-inner">
 <div class="container-fluid">
     
     
            <!-- BEGIN LOGO -->
            <div class="control-group">
                             <div style="float:right;"> 
                              
	                       <font color="white" size="3px"><b>Year: </b></font>  
                                <select required data-placeholder="Reporting Year" class="span4 m-wrap" style="width: 100px;" tabindex="-1" onchange="sendtosessionyear();"  id="year" name="year">
                                    <option value=""></option>                                 
                                   
                                 </select>
<!--                                   <font color="#4b8df8" size="3px"><b><%if(session.getAttribute("year")!=null){out.println(session.getAttribute("year").toString()+" | ");}%></b></font>
                                    <input type="hidden" name="year" id="year" value="<%=session.getAttribute("year").toString()%>">
                                  -->
                                    <font color="white" size="3px"><b>Month: </b></font>  
                                  
                                  <select placeholder="Month" class="span4 m-wrap" style="width: 150px;" tabindex="-1"  id="month" name="month" onchange="sendtosessionmonth();">
                                    <option value=""></option>
                                 </select>
                                    <font color="white" size="3px" margin-left="3px"><b>County : </b></font>
                              
                               <select placeholder="County" onchange="loadsubcounty();" style="width: 120px;"  class="span4 m-wrap" tabindex="-1"  id="county" name="county">
                                    <option value=""></option>
                                 </select>
                                   
                                    <font color="white" size="3px" margin-left="3px"><b>Sub-County : </b></font>
                              
                                <select data-placeholder="Sub-County" onchange="loadfacils();" style="width: 150px;"  class="span6 m-wrap" tabindex="-1"  id="subcounty" name="subcounty">
                                    <option value="">Select County First</option>
                                 </select>
                                    
                                   
                                   <font color="white" size="3px" margin-left="3px"><b>            Activity Site : </b></font>
                              
                                 <select onchange="updatefacilsession();" style="width:240px;float:right;color:black; background-color: #CFDDEE" data-placeholder="Facility" required class="span6" tabindex="-1"  id="facility" name="facility">
                                    <option value=""></option>
                                 </select></div>
                              
                           </div>
            <!-- END LOGO -->
            <!-- BEGIN RESPONSIVE MENU TOGGLER -->
            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
            <img src="assets/img/menu-toggler.png" alt="" />
            </a>          
            <!-- END RESPONSIVE MENU TOGGLER -->            
            <!-- BEGIN TOP NAVIGATION MENU -->              
            <ul class="nav pull-left">
              
               <li class="dropdown user">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                 
                  <span class="username">Welcome</span>
                  <i class="icon-angle-down"></i>
                  </a>
                  <ul class="dropdown-menu">
                     <li><a href="editProfile.jsp"><i class="icon-user"></i>User Profile</a></li>
                   
                     <li class="divider"></li>
                     <li><a href="logout.jsp"><i class="icon-key"></i> Log Out</a></li>
                  </ul>
               </li>
               <!-- END USER LOGIN DROPDOWN -->
            </ul>
            <!-- END TOP NAVIGATION MENU --> 
         </div>
      </div>
      <!-- END TOP NAVIGATION BAR -->
   </div>
   <!-- END HEADER -->
   <!-- BEGIN CONTAINER -->
   <div class="page-container row-fluid" style="height: auto;">
      <!-- BEGIN SIDEBAR -->
      <div class="page-sidebar nav-collapse collapse" style="position:fixed; ">
         <!-- BEGIN SIDEBAR MENU -->         
       <%@include file="/menu/menu.jsp"%>
         <!-- END SIDEBAR MENU -->
      </div>
      <!-- END SIDEBAR -->
      <!-- BEGIN PAGE -->  
      <div class="page-content" style="height: auto;">
<br><br>
         <div class="container-fluid">
           <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
               <div class="span12">
                   <ul class="breadcrumb" >
                     <li style="margin-left:40%; font-size:20px;">
                      
<!-- Modal -->

                         <!--<i class="icon-home"></i>-->
                        <p>MOH 733B - Nutrition Form</p>
                      </li>
                  </ul>
           
                   <!-- Button trigger modal -->
<!--<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  Launch demo modal
</button>-->



                   <%if (session.getAttribute("validate733B") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("validate733B")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("validate733B");
                            }

                        %>
                        
                      
                  <!-- BEGIN SAMPLE FORM PORTLET--> 
                  <form action="validate733B" method="post" class="form-horizontal" style="min-height: 450px;">
                  
                      <input type="hidden" name="data_elements" id="data_elements" value="">
                   <input type="hidden" name="description" id="description" value="">    
                      
                      <div id="data">  

                       
                         <p style="margin-left: 450px; margin-top: 200px;">  loading MOH733B form...<img src="images/utube.gif"></p>
                        
                       
                        <!-- END FORM-->           
<!--                     </div>
                 
                 --></div>
                      </form>
                  <!-- END SAMPLE FORM PORTLET-->
                  
                   
               </div>
            </div>
       
          
         
          
           
         
          
            <!-- END PAGE CONTENT-->         
         </div>
         <!-- END PAGE CONTAINER-->
      </div>
      <!-- END PAGE -->  
     
   </div>
   <!-- END CONTAINER -->
   <div>
  
   <!-- BEGIN FOOTER -->
  <div id="footer">
<%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center" style=" font-size: 18px;"> &copyInternal System, Aphia Plus | USAID <%=year%>.</p>
           
               
               
    <div class="modal fade" id="notifier" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
        <h4 class="modal-title" id="myModalLabel"><p style="text-align: center; color:red; font-weight: bolder;">Errors detected.</p></h4>
      </div>
      <div class="modal-body" id="errorBody">
    
      </div>
      <div class="modal-footer">
        <button type="button" class="btn-primary" data-dismiss="modal" style="height:40px;" id="viewErrors">view errors</button>
        <button type="button" class="btn-danger" id="submit" style="height:40px;">submit with errors</button>
     
      </div>
    </div>
  </div>
</div>

    <!--Modal unvalidated forms-->
    
<div class="modal fade" id="unvalidatedModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
        <h4 class="modal-title" id="myModalLabel"><p style="text-align: center; color:red; font-weight: bolder;">Unvalidated Forms.</p></h4>
      </div>
      <div class="modal-body" id="allunValidated" style="font-size: 16px;">
    
      </div>
      <div class="modal-footer">
        <button type="button" class="btn-danger" data-dismiss="modal" style="height:30px;" id="viewErrors">Close</button>
      </div>
    </div>
  </div>
</div>     
 
       </div>
  
  </div>
   <script src="assets/js/jquery-1.8.3.min.js"></script>    
   <script type="text/javascript" src="assets/ckeditor/ckeditor.js"></script>  
   <script src="assets/breakpoints/breakpoints.js"></script>       
   <script src="assets/bootstrap/js/bootstrap.min.js"></script>   
   <script type="text/javascript" src="assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
   <script src="assets/js/jquery.blockui.js"></script>
   <script src="assets/js/jquery.cookie.js"></script>
   <!-- ie8 fixes -->
   <!--[if lt IE 9]>
   <script src="assets/js/excanvas.js"></script>
   <script src="assets/js/respond.js"></script>
   <![endif]-->
   <script type="text/javascript" src="assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
   <script type="text/javascript" src="assets/uniform/jquery.uniform.min.js"></script>
   <script type="text/javascript" src="assets/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script> 
   <script type="text/javascript" src="assets/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
   <script type="text/javascript" src="assets/jquery-tags-input/jquery.tagsinput.min.js"></script>
   <script type="text/javascript" src="assets/bootstrap-toggle-buttons/static/js/jquery.toggle.buttons.js"></script>
   <script type="text/javascript" src="assets/bootstrap-datepicker/js/bootstrap-datepicker_1.js"></script>
   <script type="text/javascript" src="assets/clockface/js/clockface.js"></script>

  
   <script src="assets/js/app.js"></script>  
   <script src="select2/js/select2.js"></script>
<!--custom js file-->
<script type="text/javascript" src="js/sum733B.js"></script>

<script>
$(document).ready(function(){
$('.reporting_date').datepicker({
    format: 'mm/dd/yyyy'
});   
});
</script>
   <!--
 -->
   <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script>
   <script type="text/javascript">
       $(document).ready(function(){
      
             $.ajax({
        url:"loadmoh733B",
        type:"post",
        dataType:"html",
        success:function(data){
            data=$.trim(data);
           
         $("#data").html(data); 
 var validity=$("#checkValidity").html();
$("#isValidated").html(validity);

var invalidatedData=$("#invalidatedData").html();
$("#allunValidated").html(invalidatedData);

$("#data_elements").val("");
$("#description").val("");

       $('#reporting_date').datepicker({
        endDate: "today()",
        format: "yyyy-mm-dd",
        startDate: "2014-01-01",
         clearBtn: true,
           todayHighlight: true,
           orientation: "bottom auto",
          autoclose: true
    });
        }
    }); 
       });
       
  function autosave(columnName){
  var totalsVariables =",RT_Grand_Total,RT_Total_5_15_Neg,RT_Total_0_59M_Neg,RT_Total_5_15_Pos,RT_Total_0_59M_Pos,RT_Total_AllAdults_Neg,RT_Total_Postnatal_Pos,RT_Total_Adults15_17_Pos,RT_Transferred_Total,RT_LS_Total,RT_InPatient_Total,TO_Grand_Total,TO_Total_5_15_Neg,TO_Total_0_59M_Neg,TO_Total_5_15_Pos,TO_Total_0_59M_Pos,TO_Total_AllAdults_Neg,TO_Total_Postnatal_Pos,TO_Total_Adults15_17_Pos,TO_RNS_Total,TO_Discharged_Total,TO_Cured_Total,TO_StaticWeight_Total,TO_LosingWeight_Total,TO_GainingWeight_Total,NH2_Grand_Total,NH2_Total_5_15_Neg,NH2_Total_0_59M_Neg,NH2_Total_5_15_Pos,NH2_Total_0_59M_Pos,NH2_Total_AllAdults_Neg,NH2_Total_Postnatal_Pos,NH2_Total_Adults15_17_Pos,NH2_Others_Total,NH2_Micronutrients_Total,NH2_LNS_Total,NH2_FBFCSB_Total,NH2_RUSF_Total,NH2_RUTF_Total,NH2_TCM_Total,NI_Grand_Total,NI_Total_5_15_Neg,NI_Total_0_59M_Neg,NI_Total_5_15_Pos,NI_Total_0_59M_Pos,NI_Total_AllAdults_Neg,NI_Total_Postnatal_Pos,NI_Total_Adults15_17_Pos,NI_IYCF_Total,NI_CNP_Total,NI_Postnatal_Total,NI_Prenatal_Total,IFP_6_12_SubTotal_Neg,IFP_6_12_SubTotal_Pos,IFP_0_6_SubTotal_Neg,IFP_0_6_SubTotal_Pos,Anaemia_Total_AdultNeg,Anaemia_Total_AllChildren,Anaemia_Total_AdultPos,ART_Revisits_OtherOIs_Total,ART_Revisits_Diarrhoea_Total,ART_Revisits_TB_Total,ART_Revisits_ARTInitiated_Total,ART_New_OtherOIs_Total,ART_New_Diarrhoea_Total,ART_New_TB_Total,ART_New_ARTInitiated_Total,NH1_Grand_Total,NH1_Total_5_15,NH1_Total_0_59M,NH1_Total_Postnatal,NH1_Total_15_17,NH1_Total_Adult,NH1_Neg_MAM_Total,NH1_Neg_SAM_Total,NH1_Pos_MAM_Total,NH1_Pos_SAM_Total,CS_Total_LinkedOVC,CS_Total_Relapse,CS_Total_Readmission,CS_Total_5_15,CS_Total_0_59M,CS_Total_Postnatal,CS_Total_15_17,CS_Total_Adult,";          
//          alert("caled"+columnName);
            var achieved=document.getElementById(columnName).value;
//           var achieved =$("#"+columnName).val();
//            alert("called : "+columnName+"   value : "+achieved);
//            if(achieved!=""){
             $.ajax({
url:'save733B?columnName='+columnName+"&value="+achieved,
type:'post',
dataType:'html',
success:function (data){
   if(data.trim()!="success"){$("#error").html(data);
     $("#"+columnName).css({'background-color' : 'red'});
        }
    else{
    $("#"+columnName).prop('title', '');    
        $("#error").html("");
    if(achieved==""){}
  else if(totalsVariables.indexOf(","+columnName+",")>-1) {
   $("#"+columnName).css({'background-color' : 'plum'});    
  } else{
      $("#"+columnName).css({'background-color' : '#CCFFCC'});
}

$("#isValidated").html("<font color=\"red\"><b>Form Not Validated.<img style=\"margin-left:10px;\" src=\"images/notValidated.jpg\" width=\"20px\" height=\"20px\"></b></font>");
$('[data-toggle="tooltip"]').tooltip();
}
}
             });
//         }
$("#isValidated").html("<font color=\"red\"><b>Form Not Validated.<img style=\"margin-left:10px;\" src=\"images/notValidated.jpg\" width=\"20px\" height=\"20px\"></b></font>");
             }
           
       </script>
       <script type="text/javascript">
           $(document).ready(function(){
               $("form").submit(function(){
            
        return true;
            }) ;
            
  $('body').on('keydown', 'input, select, textarea', function(e) {
var self = $(this)
  , form = self.parents('form:eq(0)')
  , focusable
  , next
  , prev
  ;

if (e.shiftKey) {
 if (e.keyCode == 13) {
     focusable =   form.find('input,a,select,button,textarea').filter(function(){
    return !this.readOnly &&
           !this.disabled &&
           $(this).parentsUntil('form', 'div').css('display') != "none";
});
     prev = focusable.eq(focusable.index(this)-1); 

     if (prev.length) {
        prev.focus();
        $(prev).select();
     } else {
        form.submit();
    }
  }
}
  else
if (e.keyCode == 13) {
    focusable = form.find('input,a,select,button,textarea').filter(function(){
    return !this.readOnly &&
           !this.disabled &&
           $(this).parentsUntil('form', 'div').css('display') != "none";
});
    next = focusable.eq(focusable.index(this)+1);
    if (next.length) {
        next.focus();
       $(next).select();
    } else {
        form.submit();
    }
    return false;
}
});
            
           });
           
      function updatefacilsession(){
          
        var facil=document.getElementById("facility").value;
        $.ajax({
url:'updatefacilitysession?facil='+facil,
type:'post',
dataType:'html',
success:function (data){      
    location.reload();
    //  $("#"+col).css({'background-color' : '#CCFFCC'});       
}
             
             });    
 
      }       
       </script>
   <script>
      jQuery(document).ready(function() {       
            $.ajax({
    url:'loadYear',
    type:'post',
    dataType:'html',
    success:function (data){
         $("#year").html(data);
         loadmonths();
//        document.getElementById("year").innerHTML=data;
        
    }
    
    
});

               $.ajax({
url:'loadFacilities',
type:'post',
dataType:'html',
success:function (data){
       $("#facility").html(data);
       $('#facility').select2(); 
//       App.init();  
}
});
});

function loadmonths(){
      
      var yr=document.getElementById("year").value;
//      alert(yr);
              $.ajax({
url:'loadMonth?year='+yr,
type:'post',
dataType:'html',
success:function (data){
    $("#month").html(data);
}
});  
     }
     
     function sendtosessionyear(){
      
      var yr=document.getElementById("year").value;
  
     
    
              $.ajax({
url:'monthyearsession?year='+yr,
type:'post',
dataType:'html',
success:function (data){
//    $("#month").html(data);     
     loadmonths(); 
     location.reload();
       //document.getElementById("month").innerHTML=data;
      // App.init();  
        
}


});  
      
      
      }
    
     
    function sendtosessionmonth(){
      
    
      var month=document.getElementById("month").value;
    
  
              $.ajax({
url:'monthyearsession?month='+month,
type:'post',
dataType:'html',
success:function (data){
//    $("#month").html(data);     
      location.reload();
       //document.getElementById("month").innerHTML=data;
      // App.init();  
        
}


});  
      
      
      } 
     
     
    function loadcounty(){
        
        
        $.ajax({
            url:'loadCounty',
            type:'post',
            dataType:'html',
            success:function (data){
                $("#county").html(data);
                loadsubcounty();
              //  App.init();   
            }
            
            
        });
        
    }
    
    
       function loadsubcounty(){
        
        var county=document.getElementById("county").value;
        $.ajax({
            url:'loadSubcounty?county='+county,
            type:'post',
            dataType:'html',
            success:function (data){
                $("#subcounty").html(data);
                
              //  App.init();   
            }
            
            
        });
        
    }
    
       function loadfacils(){
      var subcounty=document.getElementById("subcounty").value;  
                    $.ajax({
url:'loadFacilities?subcounty='+subcounty,
type:'post',
dataType:'html',
success:function (data){
       $("#facility").html(data);
         if(document.getElementById("facility").value!==''){
      updatefacilsession();
      
     
      }  
      $('#facility').select2();  
         // $("#facility").chosen();
       
       
}


}); 
         
         
        }
    
 
 loadcounty();
  
   </script>
<script type="text/javascript">
 $(document).ready(function(){
   var errorsHTPS=0,errorsPMTCT=0,errorsHIVTB=0,errorsVMMC=0,errorsPEP=0,errorMAT=0;

//    $('#myModal').modal();
   $("form").submit(function(){
       var errors=0;
$("#data_elements").val("");
$("#description").val("");

$(":text").css({'background-color' : 'white'});        
var totalsVariables ="HV0110,HV0126,HV0136,HV0207,HV0214,HV0220,HV0232,HV0241,HV03011,HV03015,HV03026,HV03038,HV03050,HV03057,HV03065,HV03069,HV03072,HV03075,HV03079,HV03081,HV03084,HV0407,HV0503,HV0506";
var arrayTotals=totalsVariables.split(",");  
var arrayLength=arrayTotals.length;
var i=0;
//alert("length : "+arrayLength);
while(i<arrayLength){
 $("#"+arrayTotals[i]).css({'background-color' : '#DDDDDD'});          
 i++;   
}
var allErrors="The following errors were found : <br><br>";
  var returned;
  
   if ( $( "#HV0101" ).length ) {    
   errorsHTPS=validateHTPS();
   if(parseInt(errorsHTPS)>0){
   allErrors+=" "+errorsHTPS+" errors were found in HIV Testing  and prevention services section.<br>";
   errors+=parseInt(errorsHTPS);
   }
   }
   
if ( $( "#HV0201" ).length ) {
   errorsPMTCT=validatePMTCT();
   
if(parseInt(errorsPMTCT)>0){
   allErrors+=" "+errorsPMTCT+" error(s) were found in PMTCT section.<br>";
errors+=parseInt(errorsPMTCT);
}
}

if ( $( "#HV03001" ).length ) {
   errorsHIVTB=validateHIVTB();
//   alert("errors"+errorsCT);
if(parseInt(errorsHIVTB)>0){
   allErrors+=" "+errorsHIVTB+" error(s) were found in HIV and TB Treatment section.<br>";
errors+=parseInt(errorsHIVTB);
}
}

if ( $( "#HV0401" ).length ) {
   errorsVMMC=validateCircumcision();
//   alert("errors"+errorsVMMC);
if(parseInt(errorsVMMC)>0){
   allErrors+=" "+errorsVMMC+" errors in VMMC SECTION.<br>";
   errors+=parseInt(errorsVMMC);
}
}

if ( $( "#HV0501" ).length ) {
   errorsPEP=validatePEP();
//   alert("errors"+errorsPEP);
if(parseInt(errorsPEP)>0){
   allErrors+=" "+errorsPEP+" error(s) were found in Post-Exposure Prophylaxis (PEP) section.<br>";
errors+=parseInt(errorsPEP);
}
}

if ( $( "#HV0601" ).length ) {
   errorMAT=validateMAT();
//   alert("errors"+errorBLOOD);
if(parseInt(errorMAT)>0){
   allErrors+=" "+errorMAT+" errors in MAT(Methadone Assisted Therapy) section.<br>";
   errors+=parseInt(errorMAT);
}
}

if(errors>0){
    $("#errorBody").html(allErrors);
     $('#notifier').modal();
$("#submit").click(function(){
//  alert("to submit");  
$('#notifier').modal('hide');
$.ajax({
        url: 'validatenew731',
        type: 'post',
        dataType: 'html',
        data: $('form').serialize(),
        success: function() {
//                alert("submitted");
       location.reload(); 
                 }
         
    });
  });    
 $("#viewErrors").click(function(){
//alert("to view errors");
  });
  
  returned = false;
}
else{
    returned=true;
}
 $('[data-toggle="tooltip"]').tooltip();
return returned;
 });
 
 
 });

    </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>
