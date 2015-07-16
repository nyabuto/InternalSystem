<%-- 
    Document   : index
    Created on : May 11, 2015, 10:04:46 AM
    Author     : Maureen
--%>
<%@page import="java.util.Calendar"%>
<%-- 
    Document   : index
    Created on : Sep 18, 2014, 12:44:59 PM
    Author     : Maureen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
  <meta charset="utf-8" />
  <title>IMIS System</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport" />
  <meta content="" name="description" />
  <meta content="" name="author" />
  <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
  <link href="assets/css/metro.css" rel="stylesheet" />
  <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <link href="assets/css/style.css" rel="stylesheet" />
  <!--<link href="assets/css/style_responsive.css" rel="stylesheet" />-->
  <!--<link href="assets/css/style_default.css" rel="stylesheet" id="style_color" />-->
  <!--<link rel="stylesheet" type="text/css" href="assets/uniform/css/uniform.default.css" />-->
  <link rel="shortcut icon" href="images/index.JPG" width="20px" />
  
    <script src="assets/js/jquery-1.8.3.min.js"></script>
    
  <script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<script type="text/javascript" src="js/noty/themes/default.js"></script>

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login" style="background-color: white;">
  <!-- BEGIN LOGO -->
<!--  <div class="logo" style="height:70px; width:400px; margin-left: 30%; margin-top: 0%">
      <img src="images/index.JPG" alt="" width="650px"  style="margin-left:12%; margin-top:10px;height:100px;" /> 
  </div><br><br><br><br>-->
  <!-- END LOGO -->
  <!-- BEGIN LOGIN -->
  <div style="margin-left: 38%;">
      <div style="margin-right:25% ;">
          <h3 style="color:#4b8df8;font-family: cambria;text-align: left;font-size: 32px; margin-left: -16%;font-weight: bold;"><b>Integrated Management Information System</b></h3> 
          <h3 style="color:#4b8df8; text-align: left;font-size: 42px; font-family: cambria;margin-left: 25%;font-weight: bold;"><b>( IMIS )</b></h3> 
      </div>
      <br/>
  <div class="content" style="">
      
 
      
    <!-- BEGIN LOGIN FORM -->
    <form action="login" class="form-vertical login-form" method="post" style="">
      <h3 class="form-title">Login to your account.</h3>
      <div class="alert alert-error hide">
        <button class="close" data-dismiss="alert"></button>
        <span>Enter your username and password.</span>
      </div>
      <div class="control-group">
        <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
        <label class="control-label visible-ie8 visible-ie9">Username</label>
        <div class="controls">
          <div class="input-icon left">
            <i class="icon-user"></i>
            <input class="m-wrap placeholder-no-fix" required="true" type="text" placeholder="Username" value="" name="username"/>
          </div>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label visible-ie8 visible-ie9">Password</label>
        <div class="controls">
          <div class="input-icon left">
            <i class="icon-lock"></i>
            <input class="m-wrap placeholder-no-fix" required="true" type="password" placeholder="Password" value="" name="password"/>
          </div>
        </div>
      </div>
      <div class="form-actions">
       
        <button type="submit" class="btn green pull-right">
        Login <i class="m-icon-swapright m-icon-white"></i>
        </button>            
      </div>
     
    </form>
    
    
      <%if (session.getAttribute("login") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("login")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("login");
                            }

                        %>
                        
    <!-- END LOGIN FORM -->        
    <!-- BEGIN FORGOT PASSWORD FORM -->
   
    <!-- END FORGOT PASSWORD FORM -->
    <!-- BEGIN REGISTRATION FORM -->
  
    <!-- END REGISTRATION FORM -->
  </div>
                        
                        
                        <img src="images/aphia_logo.png" style="height:110px;border:1px solid #cbcdcc; padding:1%; width:410px;margin-left: 0%;"  />
                        
                        
  </div>                 
  <!-- END LOGIN -->
    <div class="copyright" style="width:auto;">
   <div id="footer">

<%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
<br><br>
               <p align="center" style=" font-size: 18px;"> &copy IMIS - APHIAPLUS | USAID <%=year%></p>
            </div>
  </div>
  <!-- END COPYRIGHT -->
  <!-- BEGIN JAVASCRIPTS -->

  <script src="assets/bootstrap/js/bootstrap.min.js"></script>  
  <script src="assets/uniform/jquery.uniform.min.js"></script> 
  <script src="assets/js/jquery.blockui.js"></script>
  <script type="text/javascript" src="assets/jquery-validation/dist/jquery.validate.min.js"></script>
  <script src="assets/js/app.js"></script>
<!--  <script>
    jQuery(document).ready(function() {     
      App.initLogin();
    });
  </script>-->
  <!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
