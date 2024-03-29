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
  <link rel="shortcut icon" href="images/imis.png" width="20px" />
  
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
      <img src="images/imis.png" alt="" width="650px"  style="margin-left:12%; margin-top:10px;height:100px;" /> 
  </div><br><br><br><br>-->
  <!-- END LOGO -->
  <!-- BEGIN LOGIN -->
      <h3 style="color:#4b8df8; ">
          </h3> 
      
  <div style="margin-left: 38%;">
      <div style="margin-right:25% ;">
          <h3 style="color:#4b8df8;font-family: cambria;text-align: left;font-size: 32px; margin-left: -16%;font-weight: bold;"><b></b></h3> 
      </div>
      <br/>
  <div class="content" style="">
      
 
      
    <!-- BEGIN LOGIN FORM -->
    <form action="login" class="form-vertical login-form" method="post" style="">
        <div>
          <img src="images/imis_banner.JPG"  style=" ">
          
      </div>
      <h3 class="form-title well">Login to your account.</h3>
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
       
        <button type="submit" class="btn btn-lg blue pull-left">
        Login <i class="m-icon-swapright m-icon-white"></i>
        </button>            
      </div>
      
<!--      <div class="well">
          
          <h4><font color='orange'><b>* Note: </b></font><font color='green'><b>New Moh 711 form (July. 2015) is now active  from 8th June 2016.</b></font></h4>
          <h4><font color='orange'>* <b>The old MOH 711 is still active</b></font></h4>
         
          <h4 style="font-family:cambria;"><font color='red'>* [<b>29/Jun/2016</b> ] </font><font color='orange'>  <b>New 711 static excel report added</b></font></h4>
          <h4 style="font-family:cambria;"><font color='red'>* [<b>01/Jul/2016]</b> </font><font color='orange'>  <b>731 HTC ratios applied in datim HTC report</b></font></h4>
          <h4 style="font-family:cambria;"><font color='red'>* [<b>06/Jul/2016]</b> </font><font color='orange'>  <b>No. of sites in datim HTC excel report is now static for all periods, regardless of whether the site has data or not for the selected period.  </b></font></h4>
          <h4 style="font-family:cambria;"><font color='red'>* [<b>26/Jul/2016]</b> <br></font><font color='orange'>  <b>High Volume Sites labels added on Datim HTC/PMTCT/HTC_SDP , ART/CARE/PMTCT Report, Raw Data Reports   </b></font></h4>
            <hr>
          <h4 style="font-family:cambria;"><font color='red'>* [<b>7/Sep/2016]</b> <br/></font><font color='orange'>  <b>The Raw data report has the following features added <br/>(1) Autofilter <br/>(2) Autosum at the end of all rows <br/>(3) Ability to select multiple months <br/>(4) Auto column width <br/>(5) Auto freezed pane for easy scrolling and navigation <br/>(6) Generate reports for More than one month/quarter/semi-annual <br/>(7) Generate report for specific form elements  </b></font></h4>
            <hr>
          <h4 style="font-family:cambria;"><font color='red'>* [<b>28/Sep/2016]</b> <br/></font><font color='orange'>  <b>EID uploading modules added </b></font></h4>
          <h4 style="font-family:cambria;"><font color='red'>* [<b>3/Oct/2016]</b> <br/></font><font color='orange'>  <b>EID report added on the datim reports. HTC data in datim excludes VMMC.Under 5 data is from nascop website  </b></font></h4>
          <h4 style="font-family:cambria;"><font color='red'>* [<b>27/Oct/2016]</b> <br/></font><font color='orange'>  <b>*Datim Viral load report shows data for ART sites only <br/>*Tb screen quarterly report picks  data for the last two months of the quarter. </b></font></h4>
          <h4 style="font-family:cambria;"><font color='red'>* [<b>05/Dec/2016]</b> <br/></font><font color='orange'>  <b>*Master List Report now available. </b></font></h4>
          <h4 style="font-family:cambria;"><font color='red'>* [<b>06/Dec/2016]</b> <br/></font><font color='orange'>  <b>*Annual Facilities auto back up. </b></font></h4>
          <h4 style="font-family:cambria;"><font color='red'>* [<b>19/Jan/2017]</b> <br/></font><font color='orange'>  <b>*New datim output report (HTC_TST). </b><br/> New worksheets added are; <br/>(a) PITC IPD <br>(b)Other PITC (OPD) <br>(c) VCT <br>(d) PMTCT ANC & STAT</font></h4>
          <h4 style="font-family:cambria;"><font color='red'>* [<b>24/Mar/2017]</b> <br/></font><font color='orange'>  <b>*MOH 364(SGBV) added as a module. </b><br/></font></h4>
      
      </div>-->
    </form>
    
    
      <%if (session.getAttribute("login") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("login")%>',
                        layout: 'center',
                        type: 'Success'
 
                        });
                    
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
                        
                        
                        <img src="images/aphia_logo.png" style="border:1px solid #cbcdcc; padding:1%; width:410px;margin-left: 0%;"  />
                        
                        
  </div>
                        
  <!-- END LOGIN -->
    <div class="copyright" style="width:auto;">
   <div id="footer">

<%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
<br><br>
               <p align="center" style=" font-size: 18px;"> &copy IMIS - USAID Tujenge Jamii | USAID <%=year%></p>
            </div>
  </div>
  <!-- END COPYRIGHT -->
  <!-- BEGIN JAVASCRIPTS -->

  <script src="assets/bootstrap/js/bootstrap.min.js"></script>  
  <script src="assets/uniform/jquery.uniform.min.js"></script> 
  <script src="assets/js/jquery.blockui.js"></script>
  <script type="text/javascript" src="assets/jquery-validation/dist/jquery.validate.min.js"></script>
  <script src="assets/js/app.js"></script>
  <script>
    jQuery(document).ready(function() {     
//       $.ajax({
//url:'facilityautobackup',
//type:'post',
//dataType:'html',
//success:function (data){
//        // $("#facility").chosen();
//}
//
//});
 $.ajax({
url:'validateTotals',
type:'post',
dataType:'html',
success:function (data){
         // $("#facility").chosen();       
}

});
});
  </script>
  <!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
