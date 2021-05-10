<%-- 
    Document   : form1agaps
    Created on : Mar 27, 2019, 8:32:19 AM
    Author     : GNyabuto
--%>

<%@page import="database.dbConn"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>Form 1A Data Gaps</title>
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
            <ul class="nav pull-right">
          
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
                     <li style="width: 900px;">
                        <i class="icon-home"></i>
                        <a href="#" style="margin-left:40%;">FORM 1A ERRORS AND WARNINGS.</a> 
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
                        <h4><i class="icon-reorder"></i></h4>
                       
                     </div>
                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <form action="fas_trial" id="" class="form-horizontal">
                          
                            
                           <div class="control-group">
                              <p><b style="color:red;">NOTE: </b> Fields marked * are mandatory</p>
                           </div>
                         
                           <div class="control-group">
                              <label class="control-label">Reporting Year<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select onclick="reportsready();" required data-placeholder="Reporting Year" class="chosen-with-diselect span6" tabindex="-1"  id="year" name="year" style="width: 400px;">
                                    <option value=""></option>                                 
                                   
                                 </select>
                                  <input type='hidden' name='minmonth' id='minmonth' value='10'>
                              </div>
                           </div>
                          
                             <div class="control-group" id="reportTime">
                              <label class="control-label">Reporting Period<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select required data-placeholder="Reporting Period" class="span6 m-wrap" tabindex="-1"  id="reportDuration" name="reportDuration" style="width: 400px;">
                                    <option value="">Choose reporting period</option>                                 
                                   
                                 </select>
                              </div>
                           </div>
                            
                           <div class="control-group" id="reportSemi">
                              <label class="control-label">Semi-annual<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select onchange="reportsready();" data-placeholder="Semi annual" class="span6 m-wrap"  tabindex="-1"  id="semi_annual" name="semi_annual" style="width: 400px;">
                                    <option value="">Choose period</option>                                 
                                   
                                 </select>
                              </div>
                           </div>
                            
                            <div class="control-group" id="reportQuarter">
                              <label class="control-label">Quarter<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select data-placeholder="Reporting Quarter" onchange="reportsready();" class="span6 m-wrap" tabindex="-1"  id="quarter" name="quarter" style="width: 400px;">
                                    <option value="">Choose Quarter</option>                                 
                                   
                                 </select>
                              </div>
                           </div>
                            
                            
                            <div class="control-group" id="reportMonth">
                              <label class="control-label">Month<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <select data-placeholder="Reporting Month" onchange="reportsready();" class="span6 m-wrap" tabindex="-1"  id="month" name="month" style="width: 400px;">
                                    <option value="">Choose month</option>                                 
                                   
                                 </select>
                              </div>
                           </div>
                            
                             <div class="control-group orgunit" id="reportCounty">
                              <label class="control-label">County </label>
                              <div class="controls">
                                  <select placeholder="All Counties" onchange="loadsubcounty();" class="span6 m-wrap" tabindex="-1"  id="county" name="county" style="width: 400px;">
                                    <option value="">All Counties</option>
                                 </select>
                              </div>
                           </div>
                            
                            <div class="control-group orgunit" id="reportDistrict">
                              <label class="control-label">Sub-County </label>
                              <div class="controls">
                                  <select data-placeholder="All sub-counties" class="span6 m-wrap" multiple="true" tabindex="-1"  id="subcounty" name="subcounty" style="width: 400px;">
                                    <option value="">All sub-counties</option>
                                 </select>
                              </div>
                           </div> 
                            
                            
                           <div class="form-actions">
                               <button type="submit" class="btn blue" onmouseover="getAction();">Generate Errors & Warnings Report</button>
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
       
       &copy; USAID Tujenge Jamii | USAID <%=year%>.
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
  
   
   <script>
       
      jQuery(document).ready(function() {       

         $("#reportTime").hide();
         $("#reportSemi").hide();
         $("#reportQuarter").hide();
         $("#reportMonth").hide();
         loadcounty();
         
       
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
    
    
    $("#service_area").change(function(){
    load_indicators();
        });

});
 
 
 //if reports are ready, then upload 
 function reportsready(){
     
     
      var year=$("#year").val();
      var maxmonth="09"; 
   
      var maxyear=year;
      if($("#reportDuration").val()==='1'){//annual
         
         $("#minmonth").val('10'); 
         maxmonth='09';
         maxyear=year;
      }
      
else if($("#reportDuration").val()==='2'){//semiannual
        
            if($("#semi_annual").val()==='1'){//oct-mar
                $("#minmonth").val('10');
                   maxmonth='03';
                
            }
     else  if($("#semi_annual").val()==='2'){//apr-jun
                $("#minmonth").val('04');  
                   maxmonth='09';
                
            }
            
            
      }
      
      
      else if($("#reportDuration").val()==='3')
      {//quarter
        
            if($("#quarter").val()==='1'){//oct-mar
                $("#minmonth").val('10'); 
                   maxmonth='12';
                
            }
     else  if($("#quarter").val()==='2'){//apr-jun
                $("#minmonth").val('01');     
                   maxmonth='03';
            }            
        
            else  if($("#quarter").val()==='3'){//apr-jun
                $("#minmonth").val('04');     
                   maxmonth='06';
            } 
            
            else  if($("#quarter").val()==='4'){//apr-jun
                $("#minmonth").val('07');
                   maxmonth='09';
                
            }
      }
     
        
     else if($("#reportDuration").val()==='4')
      {//monthly
        
        var mwezi=$("#month").val();
        
            if(mwezi.length===1){//oct-mar
                $("#minmonth").val('0'+mwezi);     
                   maxmonth='0'+mwezi;
            }
            else  {//oct-dec
                
                $("#minmonth").val(mwezi);
               maxmonth=mwezi;
                
            }
      }    
        
        
      
      var month=$("#minmonth").val();
    
    var maxyear=year;
        if(month==='10' || month==='11' || month==='12'){
            //annual is already taken care of here
            year=parseInt(year)-1;
           
           if($("#reportDuration").val()==='1'){//annual
       maxyear=parseInt(year)+1;   
          
         }
      
      //semiannual 1
      
       if($("#reportDuration").val()==='2'){//annual
           
            if($("#semi_annual").val()==='1'){//oct-mar
                maxyear=parseInt(year)+1;
                
            }
           
         
          
         }
      
        }
        
     
   if(year!=="" && year!=='NaN' && year!==null && month!=='' && month!=='undefined' && month!==null){  
       
      
       
    
       
      loadreports(year,month,maxyear,maxmonth); 
        }
   }   
     
         function loadcounty(){
        
        
        $.ajax({
            url:'loadCounty',
            type:'post',
            dataType:'html',
            success:function (data){
                $("#county").html(data);
                $("#county").val("");
                
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
                 $("#subcounty").val("");
                
              //  App.init();   
            }
            
            
        });
        
    }
      
      
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
 
    function load_service_areas(){
        $.ajax({
            url:'load_categories',
            type:'post',
            dataType:'html',
            success:function (data){
                $("#service_area").html(data); 
                $("#service_area").select2(); 
                $("#indicators").select2(); 
            }
        });
        
    }
     function load_indicators(){
        
        var service_areas=$("#service_area").val();
        service_areas = (""+service_areas).split(",").join("_");
        $.ajax({
            url:'load_indicators?service_areas='+service_areas,
            type:'post',
            dataType:'html',
            success:function (data){
                $("#indicators").html(data);
                $("#indicators").select2(); 
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
      
}


});  
      
      
      }
      
      
   </script>
   
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>
