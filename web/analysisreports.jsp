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
   <title>analysis reports</title>
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
                        <a href="#" >IMIS REPORTS</a> 
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
                        <h4><i class="icon-reorder"></i> ANALYSIS REPORTS </h4>
                       
                     </div>
                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <form action="SpecialReport" id="formActions" class="form-horizontal">
                          
                         
                                 <div class="control-group">
                              <label class="control-label">Program Area<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                  <select multiple="true" required data-placeholder="Form" class="span6 m-wrap" onchange="loadelements();selectagebrackets();" tabindex="-1" id="programarea" title="select multiple options using control button" name="programarea"  style="width: 400px;" size="5">
                                     
                                     <%  
dbConn conn = new dbConn();                                 
                                 String getForms="select program_area  from detailed_report where is_active='1' group by program_area order by id asc";
                                 conn.rs=conn.st.executeQuery(getForms);
                                 while(conn.rs.next()){
                                 
                             
%>
                                       <option value="<%=conn.rs.getString("program_area")%>"><%=conn.rs.getString("program_area").toUpperCase()%></option>                              
                                   <%}%>
                                 </select>
                              <i onclick="maxelementheight('programarea');" style="width:30px;" class="icon-fullscreen"></i></div>
                             </div>
                            
                           <div class="control-group" id="tarehediv">
                               <label class="control-label" id="labelyaperiod" >Reporting Year<font color='red'><b>*</b> </font> &nbsp;</label>
                               
                              
                              <div class="controls">
                                 <select required data-placeholder="Reporting Year" class="chosen-with-diselect span6" tabindex="-1"  id="year" name="year" style="width: 400px;">
                                    <option value=""></option>                                 
                                   
                                 </select>
                                  
   <div  id="sandbox-container" style="display:none;" >
                                <div  id="datepicker">
       From <input placeholder="startdate" class="input1 input-small" type="text"  readonly name="startdate" id="startdate" />
        <span class="add-on">to</span>
        <input placeholder="enddate" readonly type="text" id="enddate" class="input2 input-small"  name="enddate" />
    </div> </div>
                                  
                                  
                                 <div> <input type="checkbox" id="customdate" name="customdate" onclick="togglecal();" style=" float: start;" >&nbsp; Use Calendar &nbsp;</div>
                               
                              </div>
                              
                           </div>
                                 
                                 
                                
                                 
                                 
                          
                             <div class="control-group" id="reportTime">
                              <label class="control-label">Reporting Period<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                  <select required data-placeholder="Reporting Period" onchange="grouplabel();" class="span6 m-wrap" tabindex="-1"  id="reportDuration" name="reportDuration" style="width: 400px;">
                                    <option value="">Choose reporting period</option>                                 
                                   
                                 </select>
                              </div>
                           </div>
                            
                           <div class="control-group" id="reportSemi">
                              <label class="control-label">Semi-annual<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select data-placeholder="Sem annual" class="span6 " tabindex="-1" multiple title="Note:" data-toggle="popover" data-trigger="hover" data-content="You may select more than one period. Press and hold the Ctrl button as you choose the period of interest  " id="semi_annual" name="semi_annual" style="width: 400px;height:45px;">
                                    <option value="">Choose period</option>                                 
                                   
                                 </select>
                              </div>
                           </div>
                            
                            <div class="control-group" id="reportQuarter">
                              <label class="control-label">Quarter<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select data-placeholder="Reporting Quarter" class="span6 " title="Note:" data-toggle="popover" data-trigger="hover" data-content="You may select multiple quarters. Press and hold the Ctrl button as you choose the quarter of interest  " multiple tabindex="-1"  id="quarter" name="quarter" style="width: 400px;height:100%;">
                                    <option value="">Choose Quarter</option>                                 
                                   
                                 </select>
                              </div>
                           </div>
                            
                            
                            <div class="control-group" id="reportMonth">
                              <label class="control-label">Month<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select data-placeholder="Reporting Month" class="span6 " multiple title="Note:" data-toggle="popover" data-trigger="hover" data-content="You may select multiple months. Press and hold the Ctrl button as you choose the months of interest  " tabindex="-1"  id="month" name="month" style="width: 400px;height:220px;">
                                    <option value="">Choose month</option>                                 
                                   
                                 </select>
                              </div>
                           </div>
                            
                                 
                                 <div class="control-group">
                              <label class="control-label">Organizational Unit.</label>
                              <div class="controls">
                                 <select required placeholder="organizational units" onchange="showorgunitlist();"  class="span6 m-wrap" tabindex="-1"  id="orgunit" name="orgunit" title="Note:" data-toggle="popover" data-trigger="hover" data-content=" Specify the organizational unit that you want your report grouped by i.e facility,ward, Subcounty/constituency , county and project  " style="width: 400px;">
                                   <option title="will group data by facility, wards, subcounties/constituencies,county and total as project" value="Facility">Facility</option>                                   
                                   <option  title="will group data by wards, sub-counties/constituencies,county and total as project" value="Ward">Ward</option>
                                   <option title="will group data by sub-counties/constituencies,county and total as project" value="Sub-County">Sub-County/Constituencies</option>
                                   <option title="will group data by county and total as project" value="County">County</option> 
                                   <option title="will group data total as project" value="Project">Project</option>
                                    
                                 </select>
                              </div>
                           </div>
                                 
                                 
                             <div class="control-group" id="reportTypediv">
                              <label class="control-label">Facilities list</label>
                              <div class="controls">
                                 <select required placeholder="County" onchange="loadsubcounty();"  class="span6 m-wrap" tabindex="-1"  id="reportType" name="reportType" style="width: 400px;">
                                    <option value="1">All Facilities</option>
                                    <option value="2" title="You can either select one facility  or get data for all facilities belonging to a specified subcounty or county" >Specify Facilities</option>
                                 </select>
                              </div>
                           </div>
                            
                            
                              <div class="control-group" id="reportCounty">
                              <label class="control-label">County <font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select placeholder="County" onchange="loadsubcounty();"  class="span6 m-wrap" tabindex="-1"  id="county" name="county" style="width: 400px;">
                                    <option value=""></option>
                                 </select>
                              </div>
                           </div>
                            
                            <div class="control-group" id="reportDistrict">
                              <label class="control-label">Sub-County </label>
                              <div class="controls">
                                 <select data-placeholder="Sub-County" onchange="loadfacils();"  class="span6 m-wrap" tabindex="-1"  id="subcounty" name="subcounty" style="width: 400px;">
                                    <option value="">Select County First</option>
                                 </select>
                              </div>
                           </div> 
                            
                            
                              <div class="control-group" id="reportFacility">
                              <label class="control-label">Activity Site<font color='red'><b></b></font></label>
                              <div class="controls">
                                 <select data-placeholder="Facility" onchange="updatefacilsession();" class="span6 m-wrap" tabindex="-1"  id="facility" name="facility" style="width: 400px;">
                                    <option value=""></option>
                                 </select>
                                  <i onclick="clearfacil();" style="width:30px;" class="icon-refresh"></i>
                              </div>                                                                                                                                                                                                    
                           </div>
                            
                           <!--<div class="control-group" id="reportFormats">
                              <label class="control-label">Report Format<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select data-placeholder="Report Format" required="true" onchange="getAction();" class="span6 m-wrap" tabindex="-1"  id="reportFormat" name="reportFormat" style="width: 400px;">
                                    <option value="">Choose report Format</option>
                                    <option value="excel">Excel Report</option>
                                    <option value="pdf">PDF Report</option>
                                 </select>
                              </div>
                           </div> -->
                            
                        
                            
                                 <div class="control-group" >
                              <label class="control-label" >Show advanced Options<font color='red'></font></label>
                              <div class="controls">
                                <input type='checkbox' id='advanced' onclick='toggleadvanced();'>
                              </div>
                           </div>
                                 
                                 <div id='advancedoption' style='display:none;'>
                                  <div class="control-group" style='display:none;' >
                              <label class="control-label" >Organizational Unit<font color='red'></font></label>
                              <div class="controls">
                                 <select data-placeholder="Report Format" required="true" title="Note:" data-toggle="popover" data-trigger="hover" data-content=" Specify if you want report grouped by facility, Subcounty or county  "  class="span6 m-wrap" tabindex="-1"  id="orgunit" name="orgunit" style="width: 400px;">
                                    
                                    <option title='All facilities' value="facility">Facility</option>
                                    <option title='Subcounty' value="subcounty">Sub county</option>
                                    <option title='county' value="county">County</option>
                                 </select>
                              </div>
                           </div>
                                 
                         
                                 <div class="control-group" >
                              <label class="control-label" id="periodlabel">Group by period?<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select data-placeholder="Report Format" required="true" title="Note:" data-toggle="popover" data-trigger="hover" data-content="Selecting yes means your report will be grouped by the period you will select. eg For a monthly report, data will be grouped by each month"  class="span6 m-wrap" tabindex="-1"  id="groupby" name="groupby" style="width: 400px;">
                                    
                                    <option value="month">Month</option>
                                    <option value="quarter">Quarter</option>
                                    <option value="semiannual">Semi-annual</option>
                                    <option value="annual">Annual</option>
                                    <option value="No">All time( Totals only )</option>
                                 </select>
                              </div>
                           </div> 
                                 
                           
                                  <div class="control-group" >
                              <label class="control-label" >Gender<font color='red'><b></b></font></label>
                              <div class="controls">
                                  <select multiple="" name="gender" data-placeholder="Report Format"  title="Note:" data-toggle="popover" data-trigger="hover" data-content="Select gender you want displayed on the screen"  class="span6 m-wrap" tabindex="-1"  id="gender"  style="width: 400px;" size='2'>
                                    
                                   
                                    <option selected title='Male' value="m">Male</option>
                                    <option title='Female' value="f">Female</option>
                                 </select>
                              </div>

                                     
                                 
                           
                                  <div class="control-group" >
                              <label class="control-label" >Age brackets<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                  <select multiple="" name="agebrackets" data-placeholder="Report Format"  title="Note:" data-toggle="popover" data-trigger="hover" data-content=" Specify the age brackets you want to group your data by"  class="span6 m-wrap" tabindex="-1"  id="agebrackets"  style="width: 400px;" size='2'>
                                    
                                   
                                    <option selected title='Age brackets as listed in Datim i.e <1, 1-4,5-9,10-14,15-19, 20-24, 25-49, 50+' value="datim">Datim age brackets</option>
                                    <option title='special age brackets that are not captured in datim i.e < 14, 10 to 19 , >15,25-35' value="special">Special Age categories</option>
                                 </select>
                                <i onclick="maxelementheight('agebrackets');" style="width:30px;" class="icon-fullscreen"></i></div>
                              </div>

                                     
                                     
                              <div class="control-group" >
                              <label class="control-label" >Elements</label>
                              <div class="controls">
                                 <select  multiple data-placeholder="Report Format"   title="Note:" data-toggle="popover" data-trigger="hover" data-content=" All listed elements here will be included in the report. To specify fewer elements, select them by clicking. Use Ctrl button to select multiple options"  class="span6 m-wrap" tabindex="-1"  id="elements" name="elements"  style="width: 400px;">
                                   <option title='All active indicators for the selected form' value="all">All elements</option>
                                </select> <i onclick="maxelementheight('elements');" style="width:30px;" class="icon-fullscreen"></i>
                              </div>
                           </div>        
                                     
                           
                                 
                             </div>    
                                 
                           <div class="form-actions">
                              <button type="submit" class="btn blue">Generate Report</button>
<!--                              <button type="button" class="btn">Cancel</button>-->
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
   <script type="text/javascript" src="assets/bootstrap-datepicker/js/bootstrap-datepicker_1.js"></script>
   <script type="text/javascript" src="assets/clockface/js/clockface.js"></script>

  
   <script src="assets/js/app.js"></script>  
   <script src="select2/js/select2.js"></script>
  
   
   <script>
    
       
      jQuery(document).ready(function() {       
         // initiate layout and plugins
        $('#facility').select2(); 
            load_agebrackets();
       // FormComponents.init();
         $("#reportTime").hide();
         $("#reportSemi").hide();
         $("#reportQuarter").hide();
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
         if(document.getElementById("facility").value!==''){
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
    $("#reportTime").show();  
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
  $("#county").removeAttr("required");
  //$("#facility").removeAttr("required");
       } 
        
    else{
  $("#reportCounty").show();
  $("#reportDistrict").show();
  $("#reportFacility").show(); 
   $("#county").attr("required",true);
  // $("#facility").attr("required",true);
    }    
  
    });
$("#reportDuration").change(function(){
  var report=$("#reportDuration").val();
  if(report===""){
    $("#reportSemi").hide();
    $("#reportQuarter").hide();
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
  $("#reportTime").hide();    
  }
  else{
 $("#reportTime").show();  
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
 $('#reportDuration').html(data); 
 $('#reportDuration').select2();       
}
    });
        $("#reportTime").show();
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
 $('#semi_annual').html(data.replace('<option value ="">Choose Semi Annual</option>','')); 
 //$('#semi_annual').select2();       

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
 $('#quarter').html(data.replace('<option value ="">Choose Quarter</option>','')); 
 
 //$('#quarter').select2();       
}
    });
          
      }
     function load_agebrackets(){
       $.ajax({
url:'load_all_age_sets',
type:'post',
dataType:'html',
success:function (data){
 $('#agebrackets').html(data); 
 
 //$('#quarter').select2();       
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
            url:'loadReportedForms',
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
    $("#month").html(data.replace("<option value=''>Select Month </option>",""));     
    
       //document.getElementById("month").innerHTML=data;
      // App.init();  
        
}


});  
      
      
      }
      
      //load default facilities
     loadcounty();
      
      
           $(document).ready(function(){
          
          $('[data-toggle="popover"]').popover();
          
      });
      
      
      
      function clearfacil(){
          
          console.log("clear selected facil");
         $("#facility").val([]); 
         
           $('#facility').select2(); 
      }
      
   function grouplabel(){
      var periodname="";
       var periodid=$("#reportDuration").val();
       
       if(periodid==='1'){periodname="Group by Year"; $("#groupby").val("annual");}
       else if(periodid==='2'){periodname="Group by Semi-Annual"; $("#groupby").val("semiannual");}
      else  if(periodid==='3'){periodname="Group by Quarter"; $("#groupby").val("quarter");}
      else  if(periodid==='4'){periodname="Group by Month"; $("#groupby").val("month");}
      else  {periodname="Group by Period"; $("#groupby").val("No"); }
       
       $("#periodlabel").html(periodname);
       
   }   
      
    grouplabel();
    
    function toggleadvanced(){
        
       //if($('#advancedoption').is(":checked"))
        if($('#advanced:checkbox:checked').length > 0)
       {
        $("#advancedoption").show();   
           console.log("show");
       }
       else {
           
         $("#advancedoption").hide(); 
          console.log("hide");
       }
        
                             }
    toggleadvanced();
    
   
   //add section and subsectionfilter
   
   
      function loadelements(){
        
        var pa=$("#programarea").val();
        
        if(pa!==''){
        $.ajax({
            url:'loadelements?programarea='+pa,
            type:'post',
            dataType:'html',
            success:function (data){
                $("#elements").html(data);
                
              //also load county and facility
              
              //loadsubcounty();
                                    }
            
            
        });  }
        
                           }
   
   
   //load subsections
   
   
   
    
   
    loadelements();
    
    
    
    function showorgunitlist()
    
    {
    
    var selectedorgunit=$("#orgunit").val();
    
    console.log(""+selectedorgunit);
    if(selectedorgunit==='Facility')
    {
      
      $("#reportTypediv").show();  
   
    }
      
        
    
    else
        
        {
          $("#reportTypediv").hide();    
            
        }
        
        
        
    }
    
    showorgunitlist();
    
    function maxelementheight(id){
      
    $('#'+id).attr('size', $('#'+id+' option').length);
    $('#'+id).selectmenu({ width : 'auto'});
   
    }
    
    
    
 
   
       $('#startdate').datepicker({
        endDate: "today()",
        format: "yyyy-mm-dd",
        startDate: "2014-01-01",
         clearBtn: true,
           todayHighlight: true,
           orientation: "bottom auto",
          autoclose: true
    });
    
      $('#enddate').datepicker({
        endDate: "today()",
        format: "yyyy-mm-dd",
        startDate: "2014-01-01",
         clearBtn: true,
           todayHighlight: true,
           orientation: "bottom auto",
           autoclose: true
    });
   
   
   function togglecal(){
       if($('#customdate:checkbox:checked').length > 0){
           //====show calendar
          $("#year").hide(); 
          $("#reportTime").hide(); 
          $("#semi_annual").hide(); 
          $("#quarter").hide(); 
          $("#month").hide(); 
       
         
          $("#sandbox-container").show(); 
      
       $("#labelyaperiod").html("Reporting Date range <font color='red'>*</font>"); 
          
            $("#reportDuration").prop('required',false);
           $("#quarter").prop('required',false);
           $("#month").prop('required',false);
           $("#semi_annual").prop('required',false);
           $("#startdate").prop('required',true);
           $("#enddate").prop('required',true);
            
            $("#periodlabel").html("Group by Period");
           
               $("#groupby").attr("disabled",false);
            
            
            
       }
       
       else {
           //====show year
            $("#year").show(); 
             $("#reportTime").show();
             $("#semi_annual").show(); 
          $("#quarter").show(); 
          $("#month").show(); 
            $("#sandbox-container").hide();   
           $("#labelyaperiod").html("Reporting year <font color='red'>*</font>");  
           $("#year").prop('required',true);
           $("#reportDuration").prop('required',true);
           $("#quarter").prop('required',true);
           
           $("#month").prop('required',true);
           $("#semi_annual").prop('required',true);
           
           $("#startdate").prop('required',false);
           $("#enddate").prop('required',false);
           $("#groupby").attr("disabled",true);
       }
       
   }
   togglecal();
   
   
   
   function selectagebrackets(){
       
       var pa=$("#programarea").val();
    
       if(pa.indexOf("Special Elements")>=0 && $("#programarea :selected").length>1)  {
       
            var ageoptions = ["datim","special"];
  ageoptions.forEach(function(e){
         $("#agebrackets option[value=" + e + "]").prop('selected', true);
         
        });
         //$("#").val("special");
       }
       
       else if(pa.indexOf("Special Elements")>=0 && $("#programarea :selected").length===1){
       
    
         $("#agebrackets option[value=special]").prop('selected', true);
         $("#agebrackets option[value=datim]").prop('selected', false);
         
        
         //$("#").val("special");
       }
       
        else if(pa.indexOf("Special Elements")<0 )  {
       
         $("#agebrackets option[value=special]").prop('selected', false);
         $("#agebrackets option[value=datim]").prop('selected', true);
         //$("#").val("special");
       }
       
       
   }
   
   selectagebrackets();
   </script>
   
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>

