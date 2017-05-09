<%-- 
    Document   : Form731
    Created on : May 11, 2015, 10:09:28 AM
    Author     : Maureen
--%>

<%@page import="database.dbConn"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>SYNC EID DATA</title>
     <link rel="shortcut icon" href="images/index.JPG"/>
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
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-datepicker/css/datepicker.css" />
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-timepicker/compiled/timepicker.css" />
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-colorpicker/css/colorpicker.css" />
   <link rel="stylesheet" href="assets/bootstrap-toggle-buttons/static/stylesheets/bootstrap-toggle-buttons.css" />
   <link rel="stylesheet" href="assets/data-tables/DT_bootstrap.css" />
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-daterangepicker/daterangepicker.css" />
   <link rel="stylesheet" type="text/css" href="assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" href="select2/css/select2.css">

  
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
            <h1 style="text-align:center;font-size: 50px;color:white;padding-bottom:16px ;font-weight: bolder;">IMIS</h1><br/>
            
            <!-- END LOGO -->
            <!-- BEGIN RESPONSIVE MENU TOGGLER -->
            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
            <img src="assets/img/menu-toggler.png" alt="" />
            </a>          
            <!-- END RESPONSIVE MENU TOGGLER -->            
            <!-- BEGIN TOP NAVIGATION MENU -->              
            <ul class="nav pull-right">
              
               <!-- END NOTIFICATION DROPDOWN -->
               <!-- BEGIN INBOX DROPDOWN -->
             
             
               <!-- END TODO DROPDOWN -->
               <!-- BEGIN USER LOGIN DROPDOWN -->
               
             <!--  
               <li class="dropdown user">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                 
                  <span class="username" style="background: ;">Welcome</span>
                  <i class="icon-angle-down"></i>
                  </a>
                  <ul class="dropdown-menu">
                     <li><a href="editProfile.jsp"><i class="icon-user"></i> User Profile</a></li>
                   
                     <li class="divider"></li>
                     <li><a href="logout.jsp"><i class="icon-key"></i>Log Out</a></li>
                  </ul>
               </li> -->
               
               
               <!-- END USER LOGIN DROPDOWN -->
            </ul>
            <!-- END TOP NAVIGATION MENU --> 
         </div>
      </div>
      <!-- END TOP NAVIGATION BAR -->
   </div>
   <!-- END HEADER -->
   <!-- BEGIN CONTAINER -->
   <div class="page-container row-fluid">
      <!-- BEGIN SIDEBAR -->
      <div class="page-sidebar nav-collapse collapse">
         <!-- BEGIN SIDEBAR MENU -->         
       <%@include file="/menu/minimenu.jsp"%>
         <!-- END SIDEBAR MENU -->
      </div>
      <!-- END SIDEBAR -->
      <!-- BEGIN PAGE -->  
      <div class="page-content">
         <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
         <div id="portlet-config" class="modal hide">
            <div class="modal-header">
               <button data-dismiss="modal" class="close" type="button"></button>
               <h3>portlet Settings</h3>
            </div>
            <div class="modal-body">
               <p>Here will be a configuration form</p>
            </div>
         </div>
         <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
         <!-- BEGIN PAGE CONTAINER-->
         <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->   
            <div class="row-fluid">
               <div class="span12">
                  <!-- BEGIN STYLE CUSTOMIZER -->
               
                  <!-- END BEGIN STYLE CUSTOMIZER -->   
                  <h3 class="page-title" style="text-align: center;">
                    
<!--                    Internal System-->
                  </h3>
                  <ul class="breadcrumb">
                     <li>
                        <i class="icon-home"></i>
                        <a href="#" style="margin-left:40%;"></a> 
                        <!--<span class="icon-angle-right"></span>-->
                     </li>
           
                  </ul>
               </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
               <div class="span12">
                  <!-- BEGIN SAMPLE FORM PORTLET-->   
                  <div class="portlet box blue">
                     <div class="portlet-title">
                        <h4><i class="icon-reorder"></i>  SYNC EID DATA</h4>
                       
                     </div>
                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <form action="sync_eid" id="formActions" class="form-horizontal">
                          
                         
                           <div class="control-group">
                              <label class="control-label">Reporting Year<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select required data-placeholder="Reporting Year" class="chosen-with-diselect span6" tabindex="-1"  id="year" name="year" style="width: 400px;">
                                    <option value=""></option>                                 
                                   
                                 </select>
                              </div>
                           </div>
                          
                            <!-- <div class="control-group" id="reportTime">
                              <label class="control-label">Reporting Period<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select  data-placeholder="Reporting Period" class="span6 m-wrap" tabindex="-1"  id="reportDuration" name="reportDuration" style="width: 400px;">
                                    <option value="">Choose reporting period</option>                                 
                                   
                                 </select>
                              </div>
                           </div> -->
                            
                           <div class="control-group" id="reportSemi">
                              <label class="control-label">Semi-annual<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select data-placeholder="Sem annual" class="span6 m-wrap" tabindex="-1"  id="semi_annual" name="semi_annual" style="width: 400px;">
                                    <option value="">Choose period</option>                                 
                                   
                                 </select>
                              </div>
                           </div>
                            
                            <div class="control-group" id="reportQuarter">
                              <label class="control-label">Quarter<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select data-placeholder="Reporting Quarter" class="span6 m-wrap" tabindex="-1"  id="quarter" name="quarter" style="width: 400px;">
                                                                  
                                   <option value="">Choose Quarter</option>
                                   <option value="1">OCT (2014) - DEC (2014)</option>
                                   <option value="2">JAN (2015) - MAR 2015)</option>
                                   <option value="3">APR (2015) - JUN 2015)</option>
                                   <option value="4">JUL (2015) - SEP 2015)</option>
                                 </select>
                              </div>
                           </div>
                            
                            
                            <div class="control-group" id="reportMonth">
                              <label class="control-label">Month<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select data-placeholder="Reporting Month" class="span6 m-wrap" tabindex="-1"  id="month" name="month" style="width: 400px;">
                                    <option value="">Choose month</option>                                 
                                   
                                 </select>
                              </div>
                           </div> 
                            
                           <!--  <div class="control-group" style="display:none;">
                              <label class="control-label">Report Type </label>
                              <div class="controls">
                                 <select required placeholder="County" onchange="loadsubcounty();"  class="span6 m-wrap" tabindex="-1"  id="reportType" name="reportType" style="width: 400px;">
                                    <option value="">Choose report type</option>
                                    <option value="1">All Facilities</option>
                                    <option value="2">One Facility</option>
                                 </select>
                              </div>
                           </div>-->
                            
                            
                            <!--  <div class="control-group" id="reportCounty">
                              <label class="control-label">County </label>
                              <div class="controls">
                                 <select placeholder="County" onchange="loadsubcounty();"  class="span6 m-wrap" tabindex="-1"  id="county" name="county" style="width: 400px;">
                                    <option value=""></option>
                                 </select>
                              </div>
                           </div> -->
                            
                            <!--<div class="control-group" id="reportDistrict">
                              <label class="control-label">Sub-County </label>
                              <div class="controls">
                                 <select data-placeholder="Sub-County" onchange="loadfacils();"  class="span6 m-wrap" tabindex="-1"  id="subcounty" name="subcounty" style="width: 400px;">
                                    <option value="">Select County First</option>
                                 </select>
                              </div>
                           </div> -->
                            
                            
                           <!--   <div class="control-group" id="reportFacility">
                              <label class="control-label">Activity Site<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select data-placeholder="Facility" onchange="updatefacilsession();" class="span6 m-wrap" tabindex="-1"  id="facility" name="facility" style="width: 400px;">
                                    <option value=""></option>
                                 </select>
                              </div>
                           </div>-->
                            
                         <!--  <div class="control-group" id="reportFormats" style="display:none;">
                              <label class="control-label">Report Format<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select data-placeholder="Report Format" required="true" onchange="getAction();" class="span6 m-wrap" tabindex="-1"  id="reportFormat" name="reportFormat" style="width: 400px;">
                                    <option value="">Choose report Format</option>
                                    <option value="excel">Excel Report</option>
                                    <option value="pdf">PDF Report</option>
                                 </select>
                              </div>
                           </div> -->
                            
                             
                         
                           <div class="form-actions">
                              <button type="submit" id="generate" class="btn blue">SYNC DATA</button>
<!--                              <button type="button" class="btn">Cancel</button>-->
<h4 id="msg" style="color:green;margin-left: 160px;font-family: cambria;">Note:  This section is used to sync eid data from raw form into a Datim format for report generation.<br/>After uploading the raw data, always select the year and quarter of the data that you want to sync.Ensure that you have uploaded raw data from the tested worksheet and the positive worksheet. </h4>
                           </div>
                        </form>
                        <!-- END FORM-->           
                     </div>
                  </div>
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
   <!-- BEGIN FOOTER -->
   <div class="footer">
       <%

              Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);       
%>
       
       &copy; APHIAplus | USAID <%=year%>.
      <div class="span pull-right">
         <span class="go-top"><i class="icon-angle-up"></i></span>
      </div>
   </div>
   <!-- END FOOTER -->
   <!-- BEGIN JAVASCRIPTS -->    
   <!-- Load javascripts at bottom, this will reduce page load time -->
   <script src="assets/js/jquery-1.8.3.min.js"></script>    
   
<script type="text/javascript" src="js/bootstrap-notify.js"></script>

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
   <script type="text/javascript" src="assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
   <script type="text/javascript" src="assets/clockface/js/clockface.js"></script>
   <script type="text/javascript" src="assets/bootstrap-daterangepicker/date.js"></script>
   <script type="text/javascript" src="assets/bootstrap-daterangepicker/daterangepicker.js"></script> 
   <script type="text/javascript" src="assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>  
   <script type="text/javascript" src="assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
   <script src="assets/js/app.js"></script>  
   <script src="select2/js/select2.js"></script>
  
   <script type="text/javascript" src="js/jquery.fileDownload.js"></script>

   
   <script>
      
     function getAction(){
      var reportFormat="",form="",formActions=""; 

    var formActions=$("#formActions").val();
    var reportFormat=$("#reportFormat").val();
    var form=$("#form").val();
//    alert("format : "+reportFormat+" form : "+form);
    
    if(reportFormat==="" || form===""){
        
    }
    else{
    if( reportFormat==="pdf" && form==="MOH 731") {
   document.getElementById("formActions").action = "pdf731";
    }
    else if( reportFormat==="excel" && form==="MOH 731") {
 document.getElementById("formActions").action = "staticReportExcel731";
    }
    else if( reportFormat==="excel" && form==="MOH 711A") {
 document.getElementById("formActions").action = "moh711_excelReport";
    }
  else if(reportFormat==="pdf" &&form==="MOH 711A") {
document.getElementById("formActions").action = "moh711_StaticReport";
   }
   else if(reportFormat==="pdf" &&form==="TB") {
document.getElementById("formActions").action = "tbpdf";
   
    }
     else if(reportFormat==="excel" &&form==="TB") {
document.getElementById("formActions").action = "tb_excelReport";
   
    }
     else if(reportFormat==="pdf" && form==="KMMP") {
 document.getElementById("formActions").action = "kmmppdf";
    }
    else if(reportFormat==="excel" && form==="KMMP") {
 document.getElementById("formActions").action = "kmmpexcel";
    }
    else if(reportFormat==="pdf" && form==="VMMC") {
 document.getElementById("formActions").action = "Vmmcpdf";
    }
      else if(reportFormat==="excel" && form==="VMMC") {
 document.getElementById("formActions").action = "vmmcexcel";
    }
     else if(reportFormat==="pdf" && form==="Gender") {
 document.getElementById("formActions").action = "genderpdf";
    }
    
      else if(reportFormat==="excel" && form==="Gender") {
 document.getElementById("formActions").action = "genderexcel";
      }
       else if(reportFormat==="pdf" && form==="Nutrition") {
 document.getElementById("formActions").action = "nutritionpdf";
    }
    else if(reportFormat==="excel" && form==="Nutrition") {
 document.getElementById("formActions").action = "nutritionexcel";
      }
    
else {
 document.getElementById("formActions").action =  "allStaticReports";
}


     }  
       
    }
       
      jQuery(document).ready(function() {       
         // initiate layout and plugins
        
   $('#gapsection').select2(); 
       // FormComponents.init();
         $("#reportTime").hide();
         $("#reportSemi").hide();
         //$("#reportQuarter").hide();
         $("#reportMonth").hide();
         $("#reportCounty").hide();
  $("#reportDistrict").hide();
  $("#reportFacility").hide(); 
      //load all the facilities first to enable one to filter by county
        
         
$.ajax({
    url:'loadYear',
    type:'post',
    dataType:'html',
    success:function (data){
        document.getElementById("year").innerHTML=data;
     var year=$("#year").val();
     if(year===""){
         
     }
     else{
    //$("#reportTime").show();  
  reportingPeriod();     
     }
    }
    
    
});

$("#reportType").change(function(){
  var report=$("#reportType").val();
  if(report===""){
  $("#reportCounty").hide();
  $("#reportDistrict").hide();
  $("#reportFacility").hide(); 
 
        }
       else if(report==="1"){
   $("#reportCounty").hide();
  $("#reportDistrict").hide();
  $("#reportFacility").hide();
  $("#facility").removeAttr("required");
       } 
        
    else{
  $("#reportCounty").show();
  $("#reportDistrict").show();
  $("#reportFacility").show(); 
   $("#facility").attr("required",true);
    }    
  
    });
$("#reportDuration").change(function(){
  var report=$("#reportDuration").val();
  if(report===""){
    $("#reportSemi").hide();
    //$("#reportQuarter").hide();
    $("#reportMonth").hide();  
                 }
  else if(report==="1"){
   $("#reportSemi").hide();
    $("#reportQuarter").hide();
    $("#reportMonth").hide();
    
   $("#month").removeAttr("required");
   $("#quarter").removeAttr("required");
   $("#semi_annual").removeAttr("required");
//   $("#facility").attr("required",true);
  }
  else if(report==="2"){
  $("#reportSemi").show();
  $("#reportQuarter").hide();
 $("#reportMonth").hide(); 
 
    $("#month").removeAttr("required");
   $("#quarter").removeAttr("required");
   $("#semi_annual").attr("required",true);
  }
  
  else if(report==="3"){
  $("#reportSemi").hide();
  $("#reportQuarter").show();
 $("#reportMonth").hide();
 
  $("#quarter").attr("required",true);
  $("#month").removeAttr("required");
  $("#semi_annual").removeAttr("required");
  
  }
 
  else if(report==="4"){
  $("#reportSemi").hide();
  $("#reportQuarter").hide();
  $("#reportMonth").show(); 
  
   $("#month").attr("required",true);
   $("#quarter").removeAttr("required");
   $("#semi_annual").removeAttr("required");
  }
  
        
});
  
$("#year").change(function(){
  var year=$("#year").val();
  if(year===""){
 // $("#reportTime").hide();    
  }
  else{
// $("#reportTime").show();  
  reportingPeriod();
  }
    });
  
});
      
      
      
      function reportingPeriod(){
          
          var yea=$("#year").val();
    $.ajax({
         
url:"loadReportingPeriod?yr="+yea,
type:'post',
dataType:'html',
success:function (data){
 //$('#reportDuration').html(data); 
 //$('#reportDuration').select2();       
}
    });
        //$("#reportTime").show();
        //loadSemiAnnual();
        loadQuarters();
        //loadmonths();     
      }
      
      function loadSemiAnnual(){
      var year=$("#year").val();
    
       $.ajax({
url:'loadSemiAnnual?year='+year,
type:'post',
dataType:'html',
success:function (data){
 $('#semi_annual').html(data); 
 $('#semi_annual').select2();       

}
    });
          
      }
     
     function loadQuarters(){
     var year=$("#year").val();
       $.ajax({
url:'loadQuarters?year='+year,
type:'post',
dataType:'html',
success:function (data){
 $('#quarter').html(data); 
 $('#quarter').select2();       
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
      
    
    function updatefacilsession(){
          
        var facil=document.getElementById("facility").value;
        $.ajax({
url:'updatefacilitysession?facil='+facil,
type:'post',
dataType:'html',
success:function (data){      
    
    //  $("#"+col).css({'background-color' : '#CCFFCC'});
     
     //now load the forms
     
     loadfrms();
     
}
             
             });    
          
          
          
      }
      
    
    function loadfrms(){
        
        
        $.ajax({
            url:'loadForms',
            type:'post',
            dataType:'html',
            success:function (data){
//                $("#form").html(data);
                
              //  App.init(); 
              
              //also load county and facility
              //loadcounty();
              //loadsubcounty();
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
               // loadsubcounty();
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
    
    
    
      function loadmonths(){
      //alert("");
      var yr=document.getElementById("year").value;
      
              $.ajax({
url:'loadMonth?year='+yr,
type:'post',
dataType:'html',
success:function (data){
    $("#month").html(data);     
    
       //document.getElementById("month").innerHTML=data;
      // App.init();  
        
}


});  
      
      
      }
      
      //load default facilities
     loadcounty();
      
      
      
      
      
      //--------------------------------------------GAP ANALYSIS------------------------------------------
     //needs editing 
            function downloadrpt(){
                $('.loading').show();
                var curaction1 = document.getElementById("report").value;
                var startdate = document.getElementById("startdate").value;
                var enddate = document.getElementById("enddate").value;
                var cbos = document.getElementById("cbos").value;
                //?startdate=" + startdate + "&enddate=" + enddate + "&cbos=" + cbos
                var database=document.getElementById("database").value;
                var ur=curaction1+"?startdate=" + startdate + "&enddate=" + enddate + "&cbos=" + cbos+"&database="+database;
 
                $.fileDownload(ur).done(function () { $('.loading').hide();}).fail(function () { alert('Report generation failed!');$('.loading').hide(); });
 
                //$('.loading').hide();
            }
      
      
      
   </script>
   
   
   <script > 
                
</script>
   
 <%if (session.getAttribute("eid_upload_success") != null) { %>
                                <script type="text/javascript"> 
                    
                    
                    
                         
      $.notify(
      {icon: "images/validated.jpg", 
  message:'<%=session.getAttribute("eid_upload_success")%>'},
      {
	icon_type: 'image'
      }, 
      {
	offset: {
		x: 600,
		y: 300
	}
       }
       
            ); 
                    
                </script>
                
                <%
                session.removeAttribute("eid_upload_success");
                            }

                        %>
     

                        
                        <script>
                            
                            
                            $("form").submit(function (e) 
                            {
                            e.preventDefault(); 
                            // this will prevent from submitting the form.
                             $("#generate").prop("disabled",true);
                             $("#msg").html("Syncing tb data ...Please wait"); 
                                
                            var yr=$("#year").val();
                            var qtr=$("#quarter").val();
                            $.ajax({
                                url:"sync_eid?year="+yr+"&quarter="+qtr,
                                type:'post',
                                dataType:'html',
                                success: function(data){
                                $("#generate").prop("disabled",false);
                                $("#msg").html(data);  
                                    
                                }
                            });
                            
                            });
                            
                            
                        </script>
                        
   
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>

