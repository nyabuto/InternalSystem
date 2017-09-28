<%-- 
    Document   : AccessLevels
    Created on : Jul 28, 2015, 9:39:43 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>Edit Access Levels</title>
   
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
<!--   <link rel="stylesheet" href="assets/data-tables/DT_bootstrap.css" />
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-daterangepicker/daterangepicker.css" />-->
   <link rel="stylesheet" type="text/css" href="assets/uniform/css/uniform.default.css" />
   
     <link href="media/dataTables/jquery.dataTables.css" rel="stylesheet" type="text/css" />
          <link href="scripts/dataTables.tableTools.css" rel="stylesheet" type="text/css" />
         <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="stylesheet" href="themes/base/jquery.ui.all.css">

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
           <div class="control-group">
                             <div style="float: right; margin-right: 40%;"> 
                          
                                 <p style="color:white; font-size: 20px;"><b>View Documentation.</b></p> 
                            <br>
                             </div>
                              
                           </div>
            <!-- END LOGO -->
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
      <div class="page-sidebar nav-collapse collapse" style="position:fixed; ">
         <!-- BEGIN SIDEBAR MENU -->         
         <%@include file="/menu/minimenu.jsp"%>
         <!-- END SIDEBAR MENU -->
      </div>
      <!-- END SIDEBAR -->
      <!-- BEGIN PAGE -->  
      <div class="page-content" style="height: auto;">
         <div class="container-fluid">
           <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
               <div class="span12">
               <div class="breadcrumb" >
                        <p style="margin-left:40%" id="saver"></p>
                     
        </div>
                   <%if (session.getAttribute("validate731") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("validate731")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("validate731");
                            }

                        %>
                        
                      
                  <!-- BEGIN SAMPLE FORM PORTLET--> 
 <form action="#" method="post" class="form-horizontal" style="">
                      
                     
                  <!-- END SAMPLE FORM PORTLET-->
                   <div id="demo" style="width:100%;margin-left:3px; overflow-x: none;">
 <p style="margin-left: 450px; margin-top: 200px;">  loading Users...<img src="images/utube.gif"></p>
 </div>
                  <!--<input type="submit" value="update" class="btn blue" style="margin-left:45%;">-->
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
               <p align="center" style=" font-size: 18px;"> &copyInternal System, Aphia Plus | USAID <%=year%>.</p>
            </div>
   <script src="assets/js/jquery-1.8.3.min.js"></script>    
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
   <script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<script type="text/javascript" src="js/noty/themes/default.js"></script>
   
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<script type="text/javascript" src="js/noty/themes/default.js"></script>
 <script src="scripts/jquery.dataTables.js" type="text/javascript"></script>
         <script src="scripts/jquery.dataTables.editable.js" type="text/javascript"></script>
         <script src="scripts/jquery.jeditable.js" type="text/javascript"></script>
          <script src="scripts/jquery-ui.js" type="text/javascript"></script>
          <script src="scripts/jquery.validate.js" type="text/javascript"></script>
          
          <script src="scripts/dataTables.tableTools.js" type="text/javascript"></script>
          <script src="scripts/jquery.dataTables.columnFilter.js" type="text/javascript"></script>
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

<script type="text/javascript" src="js/form731Totals.js"></script>
 <script type="text/javascript">
       $(document).ready(function(){
       
      $("#demo").html("<table cellpadding='4px' cellspacing='4px' style='padding-top: 1px;' border='0' class='display' id='example'><tr><td>Loading Users...<img src='images/utube.gif' alt='.'></td></tr></table>");
             $.ajax({
        url:"loadUsers",
        type:"post",
        dataType:"html",
        success:function(data){
//        alert(data);
  $("#example").html(data);
              oTable=$('#example').dataTable().makeEditable({  
                  sUpdateURL: "save_accessRights" ,
               "aoColumns": [ null,null,null,null,null,null,null,null,null,null,null,null,null,null]      
              });
              
               $('form').submit(function(){
   //replace 'yourformsnameattribute' with the name of your form
//  $(oTable.fnGetHiddenNodes()).find('input:checkbox').appendTo(this);
  $(oTable.fnGetHiddenNodes()).attr('checked',this.checked).appendTo(this).css("visibility","hidden");

  ////this is what passes any hidden nodes to your form when a user clicks SUBMIT on your FORM
} );
              
          }
    }); 
    
    
    
    
       });   
       </script>
<script type="text/javascript">
  function updator(id){
      $("#saver").html("<font color=\"blue\"><b>Saving....</b></font>");
 var value,positionArray,position,userID,columnName;
 if(document.getElementById(id).checked){value="1";} else{value="0";}
positionArray=id.split("##");
columnName=positionArray[0];
position=positionArray[1];
userID=document.getElementById("userid_"+position).value;
//alert("facility id "+facilityID+"   columnName :"+columnName+" value is : "+value);
       $.ajax({
        url:"save_accessRights?userID="+userID+"&&columnName="+columnName+"&&data="+value,
        type:"post",
        dataType:"html",
        success:function(){
     $("#saver").html("<font color=\"green\"><b>Saved</b></font>");
    }
        });
}

    </script>
   
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>

