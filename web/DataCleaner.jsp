<%-- 
    Document   : DataCleaner
    Created on : Jul 11, 2018, 2:59:07 PM
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
   <title>Data Cleaning Module</title>
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
<link rel="stylesheet" href="css/animate.css">

                <style>
                    
                    [data-notify="progressbar"] {
	margin-bottom: 0px;
	position: absolute;
	bottom: 0px;
	left: 0px;
	width: 100%;
	height: 5px;
}
     div.scrollmenu {
    overflow: auto;
    white-space: nowrap;
}  
tr>td {
  padding-bottom: 1em;
  padding-right: 3em;
}
p{
    font-size: 20px;
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
            <h1 style="text-align:center;font-size: 50px;color:white;padding-bottom:16px ;font-weight: bolder; margin-bottom: 20px;">EID Error Checking Module</h1><br/>
            
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
                        <a href="#" style="margin-left:40%;">EID Error checking module</a> 
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
                        <h4 style="text-align:center;"><i class="icon-reorder"></i>Upload EID Excel File for checking</h4>
                       
                     </div>
                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <form action="DataCleaner" method="post" enctype="multipart/form-data" class="form-horizontal" style="margin: 0px 300px 0 300px">
                           <div> <b style="color: red">NOTE:</b> <p style="font-weight: bold">Ensure you have done the following before uploading your file(s) for error checking.</p></div>
                           
                        <p>1. All the excel files are in <b>.xlsx</b> format</p>
                        <p>2. All data in these files has borders. Data without borders will not be read into the system</p>
                        <p>3. Data for prior period, i.e 1&#189; Years needs to be pre-populated. You need to confirm if the dates below are right. </p>
                           <br>
                           <p id="prev_period" style="font-weight: bold; color: red;">
                           </p>
                           
                           <br>
                          <table>
                            
                                 <tr> <td><b>Report Type</b> </td><td><select name="report_type" id="report_type" value="" class="textbox" required>
                                                      <option value="">Report Type</option>
                                                      <!--<option value="tb">TB</option>-->
                                                      <!--<option value="vl">Viral Load</option>-->
                                                      <option value="eidtst" selected="">EID Tested</option>
                                                      <!--<option value="eidpos">EID POS</option>-->
                                                      
                                      </select></td> </tr>
                                 
                              <tr> 
                                  <td><b>Reporting Period Start Date</b> </td>
                                  <td>    <input name="start_date" id="start_date" autocomplete="off" class="form-control" placeholder="e.g yyyy-mm-dd"  required="true">
                                   </td> 
                              </tr>
                              <tr> 
                                  <td><b>Reporting Period End Date</b> </td>
                                  <td> 
                                      <input name="end_date" id="end_date" autocomplete="off" class="form-control" placeholder="e.g yyyy-mm-dd"  required="true">
                                  </td> 
                              </tr>
                           
                         <tr id="tst_prev" hidden="true"> <td><b>Select [1 and 1/2 years] ago EID Tested data file :</b> </td><td><input type="file" name="file_name_prev" id="file_name_prev" value="" class="textbox"></td> </tr>
                         <tr> <td><b id="current">Select File :</b> </td><td><input type="file" name="file_name" id="upload" value="" class="textbox" required></td> </tr>
                         <!--<tr> <td><b id="current">Select File :</b> </td><td><input type="file" name="file_name" id="upload" value="" class="textbox" required></td> </tr>-->
                          </table>
                        <br><br>


                           <div class="form-actions">
                              <button type="submit" class="btn blue">Upload & Check File</button>
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
  
     

<script > 
  $(document).ready(function(){
      load_prev_data_dates();
       $("#tst_prev").hide();
       $("#file_name_prev").removeAttr('required');
       
   $("#report_type").change(function(){
      var report_type=$("#report_type").val();
      if(report_type=="eidtst"){
        $("#tst_prev").show();  
        $("#current").html("Select Data File for Current Period:"); 
        $("#file_name_prev").attr("required", true);
      }
      else{
         $("#tst_prev").hide();     
         $("#current").html("Select File :");  
         $("#file_name_prev").removeAttr('required');
      }
   });
   
                    $('#start_date').datepicker({
                             todayHighlight: true, 
                             clearBtn: true, 
                             autoclose: true,
                             format: "yyyy-mm-dd",
                             startDate: '-1y',
                             endDate: '+0d'
                            });    
                    $('#end_date').datepicker({
                             todayHighlight: true, 
                             clearBtn: true, 
                             autoclose: true,
                             format: "yyyy-mm-dd",
                             startDate: '-1y',
                             endDate: '+0d'
                            }); 
  }); 
  
  function load_prev_data_dates(){
    $.ajax({        
    url:"load_prev_data_dates",
    type:'post',
    dataType:'html',
    success:function (data){
        var response = JSON.parse(data);
        if(response.min_date!=null && response.max_date!=null){
        $('#prev_period').html("<b style=\"color: green\">The previous 1&#189; Years data we have in the system ranges from <b id=\"min_date\" style=\"color:red\">date 1</b> to <b id=\"max_date\" style=\"color:red\">date 2</b>. <a href=\"EIDPrevData.jsp\"> Click Here to Load Previous Data Now </a><br><br> <b style=\"color:blue\">If this is not the right data, you need to re-upload the right data before running this module.</b>");  
        $('#min_date').html(response.min_date);
        $('#max_date').html(response.max_date);
    }
    else{
       $('#prev_period').html("<b style=\"red\">Previous 1&#189; Years data has not been loaded into the system. <a href=\"EIDPrevData.jsp\"> Click Here to Load Previous Data Now </a>. </b>");  
    }
        
    }
    });
  }
  
</script>
   
 <%if (session.getAttribute("upload_success") != null) { %>
                                <script type="text/javascript"> 
             
      $.notify(
      {icon: "images/checked.png", 
  message:'<%=session.getAttribute("upload_success")%>'},
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
                session.removeAttribute("upload_success");
                            }

                        %>
 
</body>
<!-- END BODY -->
</html>
