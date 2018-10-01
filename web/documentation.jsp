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
   <title>Documentation</title>
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
                        <h4><i class="icon-reorder"></i>  IMIS Documentation zone</h4>
                       
                     </div>
                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <form enctype="multipart/form-data" action="savedocumentation" id="formActions" method="POST" class="form-row-seperated">
                          
                         <table class='table table-striped'><tr>
                                 <td>
                           <div class="control-group">
                              <label class="control-label">Title<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <input type="text" class="input input-info"  name="title" id="title">
                              </div>
                           </div>
                          </td></tr>
                          <tr>
                                 <td>
                             <div class="control-group">
                              <label class="control-label">Change type<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                  <select name="tasktype" id="tasktype" name='tasktype'>
                                      <option value=''>select option</option>
                                      <option title='any activity to do with reports' value='Reports'>Reports</option>
                                      <option title='any activities to do with data entry interface' value='Data entry interface'>Data entry interface</option>
                                      <option title='data validation module s.e gap analysis' value='Data validations'>Data validations</option>
                                      <option title='new data collection tool being automated' value='New form'>New Form</option>
                                      <option title='pulling data from another source into IMIS' value='Data sync. module'>Data sync. module</option>
                                      <option title='any activity not captured in the above list' value='others'>Others</option>
                                      
                                  </select>
                              </div>
                           </div>
                         </td></tr>   
                             
                            <tr>
                                 <td>
                             <div class="control-group">
                              <label class="control-label">Action<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <textarea class="form-control input-lg" placeholder="explain briefly about the activity." name="details" id="details"></textarea>
                              </div>
                           </div>
                         </td></tr>
                            
                             <tr>
                                 <td>
                             <div class="control-group">
                              <label class="control-label">Previous state<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <textarea class="form-control input-lg" placeholder="What was the situation before this new decision?" name="pstate" id="pstate"></textarea>
                              </div>
                           </div>
                         </td></tr>
                             <tr>
                                 <td>
                             <div class="control-group">
                              <label class="control-label">Initial meeting Date:<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                  <input type="text" title="this is the date that the activity that you are documenting was initially proporsed" class="form-control input-lg tarehe" name="adate" id="adate">
                              </div>
                           </div>
                         </td></tr>
                              <tr>
                                 <td>
                             <div class="control-group">
                              <label class="control-label">Initial meeting Venue:<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                  <input type="text" title="This is the venue where this discussion took place" class="form-control input-lg" name="venue" id="venue">
                              </div>
                           </div>
                         </td></tr>
                             
                             <tr>
                                 <td>
                             <div class="control-group">
                              <label class="control-label">Date change was effected:<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                  <input type="text" data-date-end-date="0d"  title="this is the date that the activity you are documenting was effected in the system" class="form-control input-lg tarehe" name="edate" id="edate">
                              </div>
                           </div>
                         </td></tr>
                          
                              <tr>
                                 <td>
                             <div class="control-group">
                              <label class="control-label">Change active:<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                  <select title="is this task/module/change still active and valid?" class="form-control input-lg" name="isactive" id="isactive">
                                      
                                  <option value='yes'>yes</option>
                                  <option value='no'>no</option>
                                  </select>
                              </div>
                           </div>
                         </td></tr>
                            
                              <tr>
                                 <td>
                             <div class="control-group">
                              <label class="control-label">Email Instructions<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <textarea class="form-control input-lg" placeholder="explain briefly about the activity." name="emaildetails" id="emaildetails"></textarea>
                              </div>
                           </div>
                         </td></tr>
                              
                            
                              <tr>
                                 <td>
                             <div class="control-group">
                              <label class="control-label">Upload support file<font color='red'><b>*</b></font></label>
                              <div class="controls">
                                 <input type='file' class="form-control input-lg" placeholder="explain briefly about the activity." name="supportdoc" id="supportdoc">
                              </div>
                           </div>
                         </td></tr>
                              
                          </table>
                            
                            
                         
                           <div class="form-actions">
                              <button type="submit" class="btn blue">Save</button>
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
      
   
  
      
      $(".tarehe").datepicker({
    clearBtn: true
}).on('changeDate', function(ev){
    $(this).datepicker('hide');
});
      
      
   </script>
   
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>

