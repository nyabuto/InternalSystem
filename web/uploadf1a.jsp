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
   <title>Upload F1A</title>
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
<style>
#notify {
  position: relative;
  text-transform: uppercase;
  letter-spacing: 6px;
  font-weight: 900;
  text-decoration: none;
  color: white;
  display: inline-block;
  background-size: 120% 100%;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  -moz-background-clip: text;
  -moz-text-fill-color: transparent;
  -ms-background-clip: text;
  -ms-text-fill-color: transparent;
  background-clip: text;
  text-fill-color: transparent;
  background-image: linear-gradient(45deg, 
                    #7794ff, 
                    #44107A,
                    #FF1361,
                    #FFF800);
  animation: .2s shake infinite alternate;
}

@keyframes shake {
  0% { transform: skewX(-15deg); }
  5% { transform: skewX(15deg); }
  10% { transform: skewX(-15deg); }
  15% { transform: skewX(15deg); }
  20% { transform: skewX(0deg); }
  100% { transform: skewX(0deg); }  
} 
    </style>
  
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
                        <h4><i class="icon-reorder"></i>  UPLOAD Form 1A   (.xlsx)</h4>
                       
                     </div>
                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <form action="uploadf1a" id="formActions" method="post" enctype="multipart/form-data" class="form-horizontal">
                        <!--<form action="fas_trial" id="formActions" method="post" enctype="multipart/form-data" class="form-horizontal">-->
                          
                        <div class="control-group " ></div>
                           <div class="control-group col-lg-12" >
                              <label class="control-label col-lg-6">Select Filled Form1A excel file<font color='red'><b>*</b></font></label>
                              <div class="controls col-lg-6">
                                 <input accept=".xlsx" required type="file" name="file_name" multiple="true" id="upload" value="" class="textbox" required> 
                                    
                              </div>
                           </div>  
                           <div class="control-group" style="display:none;">
                              <label class="control-label">Skip Errors <font color='red'><b>?</b></font></label>
                              <div class="controls">
                                  <input value="1" data-placeholder="Reporting Month" type="checkbox" class="span6 m-wrap" tabindex="-1"  id="generateoutput" name="generateoutput" style="width: 400px;"/>
                                    
                              </div>
                           </div>  
                            
                           <div class="form-actions">
                              <button type="submit" id="run_upload" class="btn blue">Upload</button>
<!--                              <button type="button" class="btn">Cancel</button>-->
<h3 style="color:green;margin-left: 160px;font-family: cambria;">Note:Ensure by the time of uploading, you have corrected all the displayed errors in the excel template. 
    <br/><b>Excel file(s) with Errors will not go through the upload process </b>.</h3>
                           </div>
                        </form>
                        <!-- END FORM-->  
                      </div>
                  </div>
                  <!-- END SAMPLE FORM PORTLET-->
                  <% if(session.getAttribute("warnings")!=null){%>
                  <div id="table_output">
                      <div>
                          <div style="font-weight: bolder; color: red;" id="message">
                              <% if(session.getAttribute("message")!=null){
                              out.println(session.getAttribute("message").toString());
                              }%>
                              </div>
                          
                          <%if(!session.getAttribute("warnings").toString().equals("")){%>
                          <br>
                          <div>
                              <div style="text-align: center; font-size: 30px; font-family: bolder; text-decoration: underline;">Early Warning Indicators: Data Quality Issues</div>
                      <div style="text-align: right;">
                          <button id="generate_output" class="btn-info btn-sm" style="background: "><b>Convert to Excel(.xls)</b></button>
                      </div>
                      <table id="table_warning" class="table table-striped table-bordered table-advance table-hover">
                          <thead> <tr> <th>County</th><th>Sub County</th><th>Health Facility</th><th>MFL Code</th><th>Calendar Year</th><th>Month</th><th>Year-Month</th><th>Program</th><th>Message</th><th>Age Group</th></tr></thead>
                      <tbody id="warnings_details">
               <%
               out.println(session.getAttribute("warnings").toString());
                   %>
                    
                      </tbody>
                </table>
               </div>
                   <%}%>
                   </div>
                  </div>
               <%
               }
               else{ // no session do
               
               }
                session.removeAttribute("warnings");
               %>
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
       
       &copy; Afya Nyota Ya Bonde | USAID <%=year%>.
      <div class="span pull-right">
         <span class="go-top"><i class="icon-angle-up"></i></span>
      </div>
   </div>
   <!-- END FOOTER -->
   <!-- BEGIN JAVASCRIPTS -->    
   <!-- Load javascripts at bottom, this will reduce page load time -->
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
        
  // $('#gapsection').select2(); 
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
url:'loadFacilities',
type:'post',
dataType:'html',
success:function (data){
       $("#facility").html(data);
        // if(document.getElementById("facility").value!==''){
         if(1==1){
      updatefacilsession();
      
      $('#facility').select2();  
      }  
     
         // $("#facility").chosen();
       
       
}


});     
         
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
        loadSemiAnnual();
        loadQuarters();
        loadmonths();     
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
       //  if(document.getElementById("facility").value!==''){
         if(1==1){
      updatefacilsession();
      
     
      }  
      $('#facility').select2();  
         // $("#facility").chosen();
       
       
}


}); 
         
         
        }
      
    
    function updatefacilsession(){
          
        var facil="";
        //var facil=document.getElementById("facility").value;
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
              loadcounty();
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
                loadsubcounty();
              //  App.init();   
            }
            
            
        });
        
    }
    
    
       function loadsubcounty(){
        
        //var county=document.getElementById("county").value;
        var county="";
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
      
      
      
       $('#gapsection').attr('size', $('#gapsection option').length);
    //$('#gapsection').selectmenu({ width : 'auto'});
    
     $('#select_all').click(function() {
   $('#gapsection option').prop('selected', true);
                                        });
      
   </script>
   <script>
       $("#generate_output").click(function(){
       exportTableToExcel("table_warning","Exported_Form1A_Excel_Warnings_Data");
    });
    
        function exportTableToExcel(tableID, filename = ''){
    var downloadLink;
    var dataType = 'application/vnd.ms-excel';
    var tableSelect = document.getElementById(tableID);
    var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');
    
    // Specify file name
    filename = filename?filename+'.xls':'excel_data.xls';
    
    // Create download link element
    downloadLink = document.createElement("a");
    
    document.body.appendChild(downloadLink);
    
    if(navigator.msSaveOrOpenBlob){
        var blob = new Blob(['\ufeff', tableHTML], {
            type: dataType
        });
        navigator.msSaveOrOpenBlob( blob, filename);
    }else{
        // Create a link to the file
        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;
    
        // Setting the file name
        downloadLink.download = filename;
        
        //triggering the function
        downloadLink.click();
    }
}

      jQuery(document).ready(function() {  
//      $("#run_upload").click(function(){
//          alert("called");
//      });    
      $("#formActions").submit(function(){
        check_warning_notification();
      });    
      });
      
      function check_warning_notification(){
          $("#table_output").hide();
          $("#warnings_details").val("");
      }
   </script>
   
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>

