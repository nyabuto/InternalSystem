<%-- 
    Document   : RawQuery
    Created on : Jan 24, 2019, 9:21:50 AM
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
   <title>Raw Query Module</title>
     <link rel="shortcut icon" href="images/imis.png"/>
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
   <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/progress_bar.css">
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
<!--<link href="linedtextarea/jquery-linedtextarea.css" type="text/css" rel="stylesheet" />--> 

                
                <style>
                    
                    [data-notify="progressbar"] {
	margin-bottom: 0px;
	position: absolute;
	bottom: 0px;
	left: 0px;
	width: 100%;
	height: 5px;
}

textarea {
  width: 32%;
  float: top;
  min-height: 450px;
  overflow: scroll;
  margin: auto;
  display: inline-block;
  background: black;
  color: white;
  outline: none;
  font-family: BatangChe,'Book Antiqua';
  font-size: 14px;
  width: 98%;
  border-color: black;
  font-size: 17px;
  border-radius:5px ;
}
                    
                </style>
                
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
                  
               </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
               <div class="span12">
                  <!-- BEGIN SAMPLE FORM PORTLET-->   
                  <div class="portlet box blue">
                     <div  style="text-align: center; font-weight: 900; padding: 20px 0 40px 0;">
                         <div style="float: left; font-size: 30px; margin-left: 30%; color:#ffffff;">
                             
Generate Reports from Raw Queries</div> <div style=" margin-left: 60px; float:left; text-align: center; color:black ;font-family: cambria;"></div>
                     </div>
                      
                      
                   <div  class="portlet-body form" id="progress_area" hidden="true">
                     <div class="progress"  style="height: 35px;">
                         <div class="progress-bar progress-bar-striped active" id="progess" role="progressbar" style="width: 0%;  padding-top: 10px; font-weight: 900;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                      </div>   
                    </div> 
                  
                      <form action="RawQuery" method="post" class="form-horizontal"  >
                          
                            <input type='hidden' name='qname' id='qname'>
                       <div  class="portlet-body form" >
                           <select name="queryhistory" id="queryhistory" style='width:80%;' onchange="showqry();">
                               <option value=''>Query History</option>
                               
                           </select> <button type="submit" class="btn green" style="font-weight: bolder;">Execute Query</button>
                    </div> 
                      
                      
                     <div class="portlet-body form"  id="upload_area">
                        <!-- BEGIN FORM-->
                        
                            <textarea  name="query" id="query" value="" class="lined" rows="29" cols="40" spellcheck="false" placeholder="Enter your query here" required><%if (session.getAttribute("query") != null) { out.println(session.getAttribute("query"));  session.removeAttribute("query");}%></textarea>   
                        <br><br><br><br>
                        
                        
                        
                        <div class="form-actions">
                            <button type="submit" class="btn blue" style="font-weight: bolder;">Execute Query</button>

                           </div>
                       <div>
                           <br>
                           <h4>Note:</h4>
                        <ul>
                            <li>This module is only used to generate reports by Running queries. Please note that we only run stored procedures and select queries. Other commands like insert,update,replace into will NOT be EXECUTED.</li>
                        </ul>
                        </div>
                        </div>
                        </form>
                        <!-- END FORM-->           
                     
                  </div>
                  <!-- END SAMPLE FORM PORTLET-->
               </div>
            </div>
            <div style="color: red; font-weight: bold; font-size: 20px;">
          <%if (session.getAttribute("errors") != null) {
            out.println(session.getAttribute("errors"));  
            session.removeAttribute("errors");
             }
         %>
         
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
<!--<script src="linedtextarea/jquery-linedtextarea.js"></script>-->    

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
   

<script>
//$(function() {

//  // Target all classed with ".lined"
//  $(".lined").linedtextarea(
//    {selectedLine: 1}
//  );
//
//  // Target a single one
//  $("#mytextarea").linedtextarea();
//
//});


function loadqueries(){
  
        
      
        
         $.ajax({
            url:'loadQueryHistory',
            type:'post',
            dataType:'json',
            success:function (data){
                 var qrs="<option value=''>Select Query </value>";
                for(var as=0;as<data.length;as++){
               
            qrs+="<option value=\""+data[as].qry+"\">"+data[as].queryname+"</option>";
            
            $("#queryhistory").html(qrs);
            $("#queryhistory").select2();
                
            }
            }

                                   });
            
            
                                 }
        
    loadqueries();


function showqry(){
    
    var vl1=$("#queryhistory").val();
    
   $("#query").val(vl1);
    var qn=$("#queryhistory").find(':selected').attr('data-qname');
    qn=qn.replace("/"," or ");
    qn=qn.replaceAll(" ","_");
    qn=qn.substring(0,60);
    
      $("#qname").val(qn);
    
}

</script>
   
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>


