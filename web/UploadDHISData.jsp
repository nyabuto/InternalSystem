<%-- 
    Document   : UploadDHISData
    Created on : Mar 7, 2018, 2:08:22 PM
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
   <title>Load DHIS Excel Data.</title>
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
                        <a href="#" style="margin-left:40%;">UPLOAD DHIS Data.</a> 
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
                        <h4 style="text-align:center;"><i class="icon-reorder"></i> Uploading DHIS data from excel</h4>
                       
                     </div>
                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <form action="LoadDHIS2_Data" method="post" enctype="multipart/form-data" class="form-horizontal" style="margin: 0px 300px 0 300px">
                       
                           <br>  
                          <table>
                              <tr> <td><b> Select Year</b> </td><td><select name="year" id="year" value="" onchange="loadmonths()" class="textbox" required>
                                                      <option value=""> Choose Year</option>
                                                      
                                      </select></td> </tr>
                                                      <tr> <td><b> Select Month</b> </td><td><select name="month" id="month" value="" class="textbox" required>
                                                       <option value=""> Choose Month</option>
                                                                  
                                                  </select></td> </tr>
                         <tr> <td><b> Select File :</b> </td><td><input type="file" name="file_name" id="upload" value="" class="textbox" required></td> </tr>
                          </table>
                        <br><br>


                           <div class="form-actions">
                              <button type="submit" class="btn blue">Load Excel.</button>

                           </div>
                        <div class="form-actions scrollmenu">
                              <h4 style="text-align: center; color:red;font-family: cambria;">Note: Kindly ensure the excel file containing the raw data being uploaded is of extension <b>XLSX</b> and has all the following column headers arranged in the following order </h4>
                            <br><br>
                              <table border="1" style="width: 100%;"><tr><td>(1). orgunitlevel1</td><td>(2). orgunitlevel2</td><td>(3). orgunitlevel3</td><td>(4). orgunitlevel4</td><td>(5). orgunitlevel5</td><td>(6). organisationunitid</td><td>(7). organisationunitname</td><td>(8). organisationunitcode</td><td>(9). organisationunitdescription</td><td>(10). new anc clients</td><td>(11). antenatal positive</td><td>(12). anc hiv tests</td><td>(13). assessed eligibilit</td><td>(14). cd4 assessed</td><td>(15). assessed for eligibi</td><td>(16). bf (12 months) infan</td><td>(17). discordant couples p</td><td>(18). ebf (6 months) infan</td><td>(19). erf (6 months) infan</td><td>(20). issued anc</td><td>(21). known positive statu</td><td>(22). labour and delivery</td><td>(23). labour and deli</td><td>(24). labour and delivery</td><td>(25). mf (6 months) infant</td><td>(26). male partners tested</td><td>(27). not bf (12 months) i</td><td>(28). not known infant fee</td><td>(29). pcr (3-8 m) positive</td><td>(30). pcr (9-12m) positive</td><td>(31). pcr (2 m) positive</td><td>(32). pcr (9-12m) testing</td><td>(33). pcr (3-8m) testing</td><td>(34). pcr (by 2 months</td><td>(35). pnc (_lt72hrs)</td><td>(36). postnatal hiv+</td><td>(37). postnatal (within 72</td><td>(38). prophylaxis - (azt+sdnvp)</td><td>(39). prophylaxis - interrupted</td><td>(40). prophylaxis â€“ haart</td><td>(41). prophylaxis-nvponly</td><td>(42). serology (from 9 to</td><td>(43). total confirmed +</td><td>(44). total exposed 12 mon</td><td>(45). total exposed 6 mont</td><td>(46). total hei tested 12m</td><td>(47). total infants issued prop</td><td>(48). total pmtct prophylx</td><td>(49). total positive (pmtct)</td><td>(50). women tested (pmtct)</td><td>(51). art net cohort at 12</td><td>(52). total curr  art(_lt1)</td><td>(53). total curr art f(_lt15</td><td>(54). total curr art f(15+</td><td>(55). total curr art m(_lt15</td><td>(56). total curr art m(15+</td><td>(57). hiv care visits s</td><td>(58). hiv care visit- unsc</td><td>(59). hiv care visits female</td><td>(60). hiv currently in care total</td><td>(61). hiv exposed eligible for ctx</td><td>(62). hiv exposed eligible on ctx</td><td>(63). modern contraceptive meth</td><td>(64). number started on ip therapy m _lt 15 years</td><td>(65). number started on ip therapy m _gt 15 years</td><td>(66). number started on isoniazid preventive therapy</td><td>(67). number started on ip therapy f _gt 15 years</td><td>(68). on 2nd line</td><td>(69). on original 1st line</td><td>(70). on alternative 1st l</td><td>(71). pregnant starting on</td><td>(72). provided with condoms</td><td>(73). screened for cervica</td><td>(74). tb patient  starting art</td><td>(75). total enrolled in care</td><td>(76). total ever on art</td><td>(77). total hiv care visit</td><td>(78). total revisit on art</td><td>(79). total screened tb</td><td>(80). total starting on art</td><td>(81). total currently on art</td><td>(82). total on ctx</td><td>(83). total therapy 12 mon</td><td>(84). under 1yr starting</td><td>(85). female under 15yrs s</td><td>(86). female  above 15yr starting art</td><td>(87). male under 15yrs starting art</td><td>(88). male  above 15yrs starting art</td><td>(89). first testing hiv</td><td>(90). repeat testing hiv</td><td>(91). total tested hiv</td><td>(92). outreach testing hiv</td><td>(93). static testing hiv</td><td>(94). couples testing</td><td>(95). concordant couples rec</td><td>(96). discordant couples t</td><td>(97). female 15-24yrs  rec</td><td>(98). female (25+) receivi</td><td>(99). female under 15yrs r</td><td>(100). male 15-24yrs  recei</td><td>(101). male (25+) receiving</td><td>(102). male under 15yrs rec</tr></table>
                              <!--<table border="1" style="width: 100%;"><tr><td>(1). orgunitlevel1</td><td>(2). orgunitlevel2</td><td>(3). orgunitlevel3</td><td>(4). orgunitlevel4</td><td>(5). orgunitlevel5</td><td>(6). organisationunitid</td><td>(7). organisationunitname</td><td>(8). organisationunitcode</td><td>(9). organisationunitdescription</td><td>(10). new anc clients</td><td>(11). antenatal positive</td><td>(12). anc hiv tests</td><td>(13). assessed eligibilit</td><td>(14). cd4 assessed</td><td>(15). assessed for eligibi</td><td>(16). bf (12 months) infan</td><td>(17). discordant couples p</td><td>(18). ebf (6 months) infan</td><td>(19). erf (6 months) infan</td><td>(20). issued anc</td><td>(21). known positive statu</td><td>(22). labour and delivery</td><td>(23). labour and deli</td><td>(24). labour and delivery</td><td>(25). mf (6 months) infant</td><td>(26). male partners tested</td><td>(27). not bf (12 months) i</td><td>(28). not known infant fee</td><td>(29). pcr (3-8 m) positive</td><td>(30). pcr (9-12m) positive</td><td>(31). pcr (2 m) positive</td><td>(32). pcr (9-12m) testing</td><td>(33). pcr (3-8m) testing</td><td>(34). pcr (by 2 months</td><td>(35). pnc (_lt72hrs)</td><td>(36). postnatal hiv+</td><td>(37). postnatal (within 72</td><td>(38). prophylaxis - (azt+sdnvp)</td><td>(39). prophylaxis - interrupted</td><td>(40). prophylaxis â€“ haart</td><td>(41). prophylaxis-nvponly</td><td>(42). serology (from 9 to</td><td>(43). total confirmed +</td><td>(44). total exposed 12 mon</td><td>(45). total exposed 6 mont</td><td>(46). total hei tested 12m</td><td>(47). total infants issued prop</td><td>(48). total pmtct prophylx</td><td>(49). total positive (pmtct)</td><td>(50). women tested (pmtct)</td><td>(51). art net cohort at 12</td><td>(52). total curr  art(_lt1)</td><td>(53). total curr art f(_lt15</td><td>(54). total curr art f(15+</td><td>(55). total curr art m(_lt15</td><td>(56). total curr art m(15+</td><td>(57). hiv care visits s</td><td>(58). hiv care visit- unsc</td><td>(59). hiv care visits female</td><td>(60). hiv currently in care total</td><td>(61). hiv currently  in care _gt15yrs f</td><td>(62). hiv exposed eligible for ctx</td><td>(63). hiv exposed eligible on ctx</td><td>(64). modern contraceptive meth</td><td>(65). number started on ip therapy m _lt 15 years</td><td>(66). number started on ip therapy m _gt 15 years</td><td>(67). number started on isoniazid preventive therapy</td><td>(68). number started on ip therapy f _gt 15 years</td><td>(69). on 2nd line</td><td>(70). on original 1st line</td><td>(71). on alternative 1st l</td><td>(72). pregnant starting on</td><td>(73). provided with condoms</td><td>(74). screened for cervica</td><td>(75). tb patient  starting art</td><td>(76). total enrolled in care</td><td>(77). total ever on art</td><td>(78). total hiv care visit</td><td>(79). total revisit on art</td><td>(80). total screened tb</td><td>(81). total starting on art</td><td>(82). total currently on art</td><td>(83). total on ctx</td><td>(84). total therapy 12 mon</td><td>(85). under 1yr starting</td><td>(86). female under 15yrs s</td><td>(87). female  above 15yr starting art</td><td>(88). male under 15yrs starting art</td><td>(89). male  above 15yrs starting art</td><td>(90). first testing hiv</td><td>(91). repeat testing hiv</td><td>(92). total tested hiv</td><td>(93). outreach testing hiv</td><td>(94). static testing hiv</td><td>(95). couples testing</td><td>(96). concordant couples rec</td><td>(97). discordant couples t</td><td>(98). female 15-24yrs  rec</td><td>(99). female (25+) receivi</td><td>(100). female under 15yrs r</td><td>(101). male 15-24yrs  recei</td><td>(102). male (25+) receiving</td><td>(103). male under 15yrs rec</tr></table>-->
                           
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
  
     

<script > 
                
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
     
   <script>
      jQuery(document).ready(function() {       
            $.ajax({
    url:'loadYear',
    type:'post',
    dataType:'html',
    success:function (data){
         $("#year").html(data);
         loadmonths();      
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
     
     </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>


