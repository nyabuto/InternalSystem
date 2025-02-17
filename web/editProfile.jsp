<%-- 
    Document   : editProfile
    Created on : May 20, 2015, 10:48:22 AM
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>Edit Profile</title>
    <script src="assets/js/jquery-1.8.3.min.js"></script>  
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
    
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<script type="text/javascript" src="js/noty/themes/default.js"></script>
   <script type="text/javascript">
            function checkPasswords() {
                var password = document.getElementById('password');
                var conf_password = document.getElementById('conf_password');

                if (password.value != conf_password.value) {
                    conf_password.setCustomValidity('Passwords do not match');
                } else {
                    conf_password.setCustomValidity('');
                }
            } 
     
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
  <%if(session.getAttribute("kd_session")!=null){%><%} else {  response.sendRedirect("logout");}%> 
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">
   <!-- BEGIN HEADER -->
   <div class="header navbar navbar-inverse navbar-fixed-top">
      <!-- BEGIN TOP NAVIGATION BAR -->
      <div class="navbar-inner">
 <div class="container-fluid">
     
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
      <div class="page-sidebar nav-collapse collapse">
         <!-- BEGIN SIDEBAR MENU -->         
       <%@include file="/menu/minimenu.jsp"%>
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
                        <!--<i class="icon-home"></i>-->
                      <p>Edit Your Profile</p>
                      </li>
                  </ul>
                  
                   <%if (session.getAttribute("editProfile") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("editProfile")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("editProfile");
                            }

                        %>
                        
                        
                  <!-- BEGIN SAMPLE FORM--> 
                  <form action="updateProfile" method="post" class="form-horizontal" style="min-height: 450px;">
 
 <br>
  
  <p style="text-align: center">Fields marked <font color="red">*</font> are required/mandatory fields.</p>
  
  <br>
    <table style="margin-left: 200px;">  
      <tr>
       <td> <p style="font-family: lucida calligraphy">Enter first name <font color="red">*</font> : </p></td>  
         
       <td style="padding-left: 200px;"><input type="text" name="fname" id="fname" class="textbox" placeholder="" value="<%if(session.getAttribute("fname")!=null){out.println(session.getAttribute("fname").toString());}%>" maxlength="30" autocomplete="off" style="height:25px;" required></td>
      </tr> 
      <tr style="height: 10px;"></tr>
      <tr>
       <td><p style="font-family: lucida calligraphy">Enter middle name <font color="red"></font> :  </p></td>  
         
       <td style="padding-left: 200px;"><input type="text" name="mname" id="mname" class="textbox" placeholder="" value="<%if(session.getAttribute("mname")!=null){out.println(session.getAttribute("mname").toString());}%>" autocomplete="off" maxlength="30" style="height:25px;"> </td>
      </tr>
      <tr style="height: 10px;"></tr>
      <tr>
       <td><p style="font-family: lucida calligraphy">Enter last name <font color="red">*</font> :  </p></td>  
         
       <td style="padding-left: 200px;"><input type="text" name="lname" id="lname" class="textbox" placeholder="" value="<%if(session.getAttribute("lname")!=null){out.println(session.getAttribute("lname").toString());}%>" autocomplete="off" maxlength="30" style="height:25px;" required> </td>
      </tr>
      <tr style="height: 10px;"></tr>
      <tr>
       <td><p style="font-family: lucida calligraphy">Enter username <font color="red">*</font> : </p> </td>  
         
       <td style="padding-left: 200px;"><input type="text" name="username" id="username" class="textbox" placeholder="" value="<%if(session.getAttribute("username")!=null){out.println(session.getAttribute("username").toString());}%>" autocomplete="off" maxlength="30" style="height:25px;" required> </td>
      </tr>
      <tr>
       <td><p style="font-family: lucida calligraphy">Enter email <font color="red">*</font> : </p> </td>  
         
       <td style="padding-left: 200px;"><input type="email" name="email" id="email" class="textbox" placeholder="" value="<%if(session.getAttribute("email")!=null){out.println(session.getAttribute("email").toString());}%>" autocomplete="off" maxlength="30" style="height:25px;" required> </td>
      </tr>
      <tr style="height: 10px;"></tr>
      <tr>
       <td><p style="font-family: lucida calligraphy">Enter password <font color="red">*</font> : </p> </td>  
         
       <td style="padding-left: 200px;"><input type="password" name="password" oninput="checkPasswords()" id="password" class="textbox" placeholder="" maxlength="30" style="height:25px;" required> </td>
      </tr>
      <tr style="height: 10px;"></tr>
      <tr>
       <td><p style="font-family: lucida calligraphy">Repeat password <font color="red">*</font> : </p> </td>  
         
       <td style="padding-left: 200px;"><input type="password" oninput="checkPasswords()" name="conf_password" id="conf_password" class="textbox" placeholder="" maxlength="30" style="height:25px;" required> </td>
      </tr>
      <tr style="height: 10px;"></tr>
      <tr style="height: 10px;"></tr>
     <tr>
          <td></td>
       <td colspan=""><input type="submit" class="btn blue" value="Update Profile"> </td>  
       
      </tr>
      <tr style="height: 17px;"></tr>
 </table>   
 
 
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
   <!-- BEGIN FOOTER -->
  <div id="footer">
<%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center" style=" font-size: 18px;"> &copyInternal System, USAID Tujenge Jamii | USAID <%=year%>.</p>
            </div>

    
   <script type="text/javascript" src="assets/ckeditor/ckeditor.js"></script>  
   <script src="assets/breakpoints/breakpoints.js"></script>       
   <script src="assets/bootstrap/js/bootstrap.min.js"></script>   
   <script type="text/javascript" src="assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
   <script src="assets/js/jquery.blockui.js"></script>
   <script src="assets/js/jquery.cookie.js"></script>
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
   <script>
      jQuery(document).ready(function() {       
         // initiate layout and plugins
         App.init();
      });
   </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>

