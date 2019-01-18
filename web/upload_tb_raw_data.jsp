<%-- 
    Document   : loadTBExcel
    Created on : Jul 27, 2015, 2:41:29 PM
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
   <title>Load Tibu tb raw Excel Data.</title>
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
                        <a href="#" style="margin-left:40%;">Load TB raw Excel data from TIBU system into IMIS.</a> 
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
                        <h4 style="text-align:center;"><i class="icon-reorder"></i> TB RAW DATA (excel) UPLOADING MODULE</h4>
                       
                     </div>
                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <form action="Load_tb_rawv2" method="post" enctype="multipart/form-data" class="form-horizontal" >
                       
                            
                          <table class='table-striped' border='0'>
                         <tr> <td><b>(1) Select File :</b> </td><td><input type="file" name="file_name" id="upload" value="" class="textbox" required></td> </tr>
                         <tr> <td colspan='2'><b>(2) Specify the date range for updating dashboards Tb data:</b> </td></tr>
                              <tr><td>Startdate </td><td> <input required type="text" title="" class="form-control input-lg tarehe" name="startdate" id="startdate"></td> </tr>
                              <tr><td>Enddate </td><td> <input required type="text" title="" class="form-control input-lg tarehe" name="enddate" id="enddate"></td> </tr>
                          </table>
                        <br><br><br><br>



                         
                           <div class="form-actions">
                              <button type="submit" class="btn blue">Load Excel.</button>

                           </div>
                        <div class="form-actions" style="overflow-x: scroll;">
                              <h4 style="text-align: center; color:red;font-family: cambria;">Note: Remove the column patient name. Ensure Is patient prisoner is there </h4>
                              <h4 style="text-align: center; color:red;font-family: cambria;"> Kindly ensure the excel file containing the raw data being uploaded is of extension <b>XLS</b> and has all the following column headers arranged in the following order </h4>
                            
                               <table border="1"><tr><td>(0)<br/>Serial Number</td><td>(1)<br/>Date of Registration</td><td>(2)<br/>District Registration Number</td><td>(3)<br/>Province</td><td>(4) <br/>County</td><td>(5)<br/>District</td><td>(6)<br/>Zone</td><td>(7)<br/>Health Facility</td><td>(8)<br/>Year</td><td>(9)<br/>Quarter</td><td>(10)<br/>Sector</td><td>(11)<br/>Sex M/F</td><td>(12)<br/>Age on registration</td><td>(13)<br/>Weight (Kgs)</td><td>(14)<br/>Height (Mtrs)</td><td>(15)<br/>BMI</td><td>(16)<br/>MUAC</td><td>(17)<br/>Physical address (Neighbor,Primary School) Cell Phone</td><td>(18)<br/>DOT by</td><td>(19)<br/>Type of TB P/EP</td><td>(20)EPTB Sub Type</td><td>(21)<br/>EPTB Others</td><td>(22)<br/>Type of patient	</td><td>(23)<br/>CD4 First Date</td><td>(24)<br/>CD4 Last Date</td><td>(25)<br/>Culture S</td><td>(26)<br/>Culture R</td><td>(27)<br/>Culture E	</td><td>(28)<br/>Culture H</td><td>(29)<br/>X-ray Y/N</td><td>(30)<br/>Sputum Smear Examination</td><td>(31)<br/>0th Month Result</td><td>(32)<br/>0th Month Serial No and Quantification</td><td>(33)<br/>Sputum Smear Examination 2by3 Month Result</td><td>(34)<br/>2by3 Month Serial No and Quantification</td><td>(35)<br/>Sputum Smear Examination 5th Month Result</td><td>(36)<br/>5th Month Serial No and Quantification</td><td>(37)<br/>Sputum Smear Examination 6by8 Month Result</td><td>(38)<br/>6by8 Month Serial No and Quantification</td><td>(39)<br/>Regimen</td><td>(40)<br/>Date of treatment started</td><td>(41)<br/>Gen expert</td><td>(42)<br/>Lipa Hain Rifampicin</td><td>(43)<br/>Lipa Hain Isoniazid</td><td>(44)<br/>HIV Test Date</td><td>(45)<br/>HIV Status</td><td>(46)<br/>Partner HIV Test Date</td><td>(47)<br/>Partner HIV Status</td><td>(48)<br/>Referred BY: VCT/HCC/STI/HBC/PS/ANC/SR/CI</td><td>(49)<br/>Referred TO VCT/HCC/STI/HBC/PS/ANC</td><td>(50)<br/>Cotrimoxazole Preventive Therapy Y/N</td><td>(51)<br/>Cotrimoxazole Preventive Therapy (Date Started)</td><td>(52)<br/>ART Y/N</td><td>(53)<br/>ART (Date Started)</td><td>(54)<br/>Nutrition Support</td><td>(55)<br/>Treatment Outcome</td><td>(56)<br/>Treatment Outcome Date</td><td>(57)<br/>Remarks</td></tr></table>
                           
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
                
</script>
   
 <%if (session.getAttribute("upload_success") != null) { %>
                                <script type="text/javascript"> 
                    
                    
                    
                         
      $.notify(
      {icon: "images/validated.jpg", 
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
    
//       function getAction(){
//      var reportFormat="",form="",formActions=""; 
//
//   
//    var reportFormat=$("#").val();
//    var form=$("#form").val();
//    if(reportFormat==="" || form===""){
//        
//    }
//    else{
//    if( reportFormat==="datimReport") {
//   document.getElementById("formActions").action = "datimReport";
//                                      }
//    else if(reportFormat==="datimvmmc"){
//        
//           document.getElementById("formActions").action = "datimvmmc";
//        
//             }
//    
//
//     }  
//   }
   
   </script>
   
   
    <script>
      
   
  
      
      $(".tarehe").datepicker({
    clearBtn: true
}).on('changeDate', function(ev)
{
    $(this).datepicker('hide');
});
      
      
   </script>
   
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>


