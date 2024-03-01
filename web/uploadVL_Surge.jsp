<%-- 
    Document   : UploadVL
    Created on : Mar 16, 2018, 8:37:33 AM
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
   <title>[2018+]Load Viral Excel Data.</title>
     <link rel="shortcut icon" href="images/imis.png"/>
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
   <link rel="stylesheet" href="css/progress_bar.css">
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

  <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">-->
  <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>-->
  <!--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->

                
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
                         <a href="DataCleaner.jsp" style="margin-left:40%;">Check gaps in Viral Load raw data to IMIS via excel.</a> 
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
                     <div  style="text-align: center; font-weight: 900; padding: 20px 0 40px 0;">
                         <div style="float: left; font-size: 30px; margin-left: 20%; color:#ffffff;">Upload Viral Load Raw Data [.XLSX]</div> <div style=" margin-left: 60px; float:left; text-align: center; color:black ;font-family: cambria;">  <%  dbConn conn = new dbConn();
                        
                     
                        
conn.rs=conn.st.executeQuery("select Max(Date_Tested) as md from vl_surge");

while(conn.rs.next()){

out.println("<br/><font color='white'> <i> Last date VLs Downloaded: <b>"+conn.rs.getString(1)+"</b></i> </font>");

}

                        %>   </div>
                     </div>
                      
                      <div  class="portlet-body form" id="progress_area" hidden="true">
                     <div class="progress"  style="height: 35px;">
                         <div class="progress-bar progress-bar-striped active" id="progess" role="progressbar" style="width: 0%;  padding-top: 10px; font-weight: 900;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                      </div>   
                  </div> 
                      
                     <div class="portlet-body form"  id="upload_area">
                        <!-- BEGIN FORM-->
                        <form action="LoadAllVLData_surge" method="post" enctype="multipart/form-data" class="form-horizontal" >
                       <input type="file" name="file_name" id="upload" value="" class="textbox" required>   
                       
                        <br><br><br> 
                    <br>

                     <div class="form-actions scrollmenu">
                               <button type="submit" class="btn blue" style=" float: left;">Load Excel.</button>
                           </div>
                        <div class="form-actions scrollmenu">
                           
                            
                              <h4 style="text-align: center; color:red;font-family: cambria;">Note: Kindly ensure the excel file containing the viral load raw data has column headers arranged in following order <b>[.xlsx]</b> </h4>
                            
                               <table border="1" class="table">
                                   <tr>
                                  <td>System ID<br> [1] </td><td>Batch<br> [2] </td><td>Patient CCC No<br> [3] </td><td>Lab Tested In<br> [4] </td><td>County<br> [5] </td><td>Sub-County<br> [6] </td><td>Partner<br> [7] </td><td>Facilty<br> [8] </td><td>Facility Code<br> [9] </td><td>Gender<br> [10] </td><td>DOB<br> [11] </td><td>Age<br> [12] </td><td>Sample Type<br> [13] </td><td>Date Collected<br> [14] </td><td>Justification<br> [15] </td><td>Date Received<br> [16] </td><td>Date Tested<br> [17] </td><td>Date Dispatched<br> [18] </td><td>ART Initiation Date<br> [19] </td><td>Received Status<br> [20] </td><td>Reasons for Repeat<br> [21] </td><td>Rejected Reason<br> [22] </td><td>Regimen<br> [23] </td><td>Regimen Line<br> [24] </td><td>PMTCT<br> [25] </td><td>Result<br> [26] </td>

                                   </tr>
                               </table>
                           
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
        $("#progress_area").hide();
        $("#upload_area").show();
         
    $("form").submit(function(){
        $("#progress_area").show();
        $("#upload_area").hide();
//        alert("data submitted");
     setInterval(function() {
      load_records();
      }, 100);  
    });
     });
     
     function load_records(){
             $.ajax({
        url:'check_status?load_type=viral_load',
        type:"post",
        dataType:"json",
        success:function(response){
//            alert("called");
var per_value = response.count;
var message = "["+per_value+"%] Complete "+response.message+" Records Scanned";

    $("#progess").html(message);
    $("#progess").css({'width':per_value+"%"}); 

    if(per_value<30){
     $("#progess").addClass('progress-bar-danger');  
     $("#progess").removeClass('progress-bar-success'); 
    }
    if(per_value>=30 && per_value<60){
     $("#progess").addClass('progress-bar-warning');   
     $("#progess").removeClass('progress-bar-danger');   
    }
    if(per_value>=60 && per_value<80){
     $("#progess").addClass('progress-bar-info'); 
     $("#progess").removeClass('progress-bar-warning');   
     $("#progess").removeClass('progress-bar-danger');  
    }
    if(per_value>=90){
     $("#progess").addClass('progress-bar-success'); 
     $("#progess").removeClass('progress-bar-info'); 
     $("#progess").removeClass('progress-bar-warning');   
     $("#progess").removeClass('progress-bar-danger');  
    }
    $("#status").html(response);
        }, 
        error: function(jqXHR, textStatus, errorThrown) {
       //error in loading upload status
       $("#status").html(errorThrown);
            }
  });
     }
</script>
   
 <%if (session.getAttribute("vl_loaded") != null) { %>
   <script type="text/javascript"> 
                    
         $.notify(
      {icon: "images/validated.jpg", 
  message:'<%=session.getAttribute("vl_loaded")%>'},
      {
	icon_type: 'image'
      }, 
      {
	offset: {
		x: 200,
		y: 120
	}
       }
       
            ); 
                    
                </script>
                
                <%
                session.removeAttribute("vl_loaded");
                            }

                        %>
     

   <script>
   </script>
   
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>