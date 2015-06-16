<%-- 
    Document   : DQA
    Created on : Jun 16, 2015, 8:16:01 AM
    Author     : Maureen
--%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>DQA</title>
  <link rel="shortcut icon" href="images/index.JPG"/>
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
    
     <script src="assets/js/jquery-1.8.3.min.js"></script>     
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
   
   <script src="select2/js/select2.js"></script>
<link rel="stylesheet" href="select2/css/select2.css">
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<script type="text/javascript" src="js/noty/themes/default.js"></script>

  


        <style>
fieldset.formatter {
    border: 2px groove black !important;
   
    /*padding: 0 1.4em 1.4em 1.4em !important;*/
    margin: 0 0 1.5em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
   
}

legend.formatter {
    border: 0px groove black !important;
    margin: 0 0 0.0em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
    font-size: 1.2em !important;
    /*font-weight: bold !important;*/
    text-align: center !important;
    width:inherit; /* Or auto */
    padding:0 10px; /* To give a bit of padding on the left and right */
    border-bottom:none;
    margin-left:50px;

}
</style>
<style>
    
    
    .form-actions {
  
    padding: 4px 20px 4px;
}
</style>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top"  >
   <!-- BEGIN HEADER -->
   <div class="header navbar navbar-inverse navbar-fixed-top">
      <!-- BEGIN TOP NAVIGATION BAR -->
      <div class="navbar-inner">
         <div class="container-fluid">
            <!-- BEGIN LOGO -->
           <div class="control-group">
                             <div style="float:right;"> 
                              
                             
                               <font color="white" size="3px"><b>Year: </b></font>  
                                <select required data-placeholder="Reporting Year" class="span4 m-wrap" tabindex="-1" onchange="sendtosessionyear();"  id="year" name="year" style="width: 100px;">
                                    <option value=""></option>                                 
                                   
                                 </select>

                                    <font color="white" size="3px"><b>Month: </b></font>  
                                  
                                  <select placeholder="Month" class="span4 m-wrap" tabindex="-1"  id="month" name="month" onchange="sendtosessionmonth();" style="width: 150px;">
                                    <option value=""></option>
                                 </select>
                                
                                    <font color="white" size="3px" margin-left="3px"><b>County : </b></font>
                              
                                <select placeholder="County" onchange="loadsubcounty();"  class="span4 m-wrap" tabindex="-1"  id="county" name="county" style="width: 150px;">
                                    <option value=""></option>
                                 </select>
                                   
                                    <font color="white" size="3px" margin-left="3px"><b>Sub-County : </b></font>
                              
                                <select data-placeholder="Sub-County" onchange="loadfacils();"  class="span6 m-wrap" tabindex="-1"  id="subcounty" name="subcounty">
                                    <option value="">Select County First</option>
                                 </select>
                                    
                                   
                                   <font color="white" size="3px" margin-left="3px"><b>            Activity Site : </b></font>
                              
                                 <select onchange="updatefacilsession();" style="width:240px;float:right;color:black;" data-placeholder="Facility" required class="span6" tabindex="-1"  id="facility" name="facility">
                                    <option value=""></option>
                                 </select>
                                    <font color="white" size="3px"><b>Select Form </b></font>
                                 <select required data-placeholder="Form" class="span6 m-wrap" tabindex="-1"  id="form" name="form" onchange="viewDQA();">
                                 <option value="">Select Activity Site First</option>                                
                                   
                                 </select>
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
                     <li><a href="userProfile.html"><i class="icon-user"></i>User Profile</a></li>
                   
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
   <div class="page-container row-fluid">
      <!-- BEGIN SIDEBAR -->
      <div class="page-sidebar nav-collapse collapse">
         <!-- BEGIN SIDEBAR MENU -->         
       <%@include file="/menu/menu.jsp"%>
         <!-- END SIDEBAR MENU -->
      </div>
      <!-- END SIDEBAR -->
      <!-- BEGIN PAGE -->  
      <div class="page-content">
         <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
         <div id="portlet-config" class="modal hide">
            <div class="modal-header">
               <button data-dismiss="modal" class="close" type="button"></button>
               <!--<h3>portlet Settings</h3>-->
            </div>
            <div class="modal-body">
               <!--<p>Here will be a configuration form</p>-->
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
                    
             
                  </h3>
                  <ul class="breadcrumb">
<!--                     <li>
                        <i class="icon-home"></i>
                        <font color="#4b8df8" size="15px">MOH 711A </font>
                        
                     </li>-->
           <li style="margin-left:40%; font-size:20px;">
                    <p>DQA</p>
                    </li>
                                      </ul>
               </div>
             <div class="modal fade" id="notifier" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><p style="text-align: center; color:red; font-weight: bolder;">Errors detected.</p></h4>
      </div>
      <div class="modal-body" id="errorBody">
    
      </div>
      <div class="modal-footer">
        <button type="button" class="btn-primary" data-dismiss="modal" style="height:40px;" id="viewErrors">view errors</button>
        <button type="button" class="btn-danger" id="submit" style="height:40px;">submit with errors</button>
      </div>
    </div>
  </div>
</div>
    <!--Modal unvalidated forms-->
    
<div class="modal fade" id="unvalidatedModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
        <h4 class="modal-title" id="myModalLabel"><p style="text-align: center; color:red; font-weight: bolder;">Unvalidated Forms.</p></h4>
      </div>
      <div class="modal-body" id="allunValidated" style="font-size: 16px;">
    
      </div>
      <div class="modal-footer">
        <button type="button" class="btn-danger" data-dismiss="modal" style="height:30px;" id="viewErrors">Close</button>
      </div>
    </div>
  </div>
</div>     
            
<!--   <div id="dialog-confirm" hidden="true" title="Confirm Marking or editing for adherence">
    <p><font color="red"><b>NOTE :</b> </font><font color="black">Adherence message has been marked.</font><br>
    <br>1. Click <b>YES</b> if you want to mark adherence for the second or subsequent times. 
    <br>2.Click <b>NO</b> if want to edit the already marked data for adherence.</p>
</div>   -->
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
               <div class="span12">
                  <!-- BEGIN SAMPLE FORM PORTLET-->   
                  <!--<div class="portlet box blue">-->
<!--                     <div class="portlet-title">
                        <h4><i class="icon-reorder"></i></h4>
                        <b style="color:white;text-align: center;font-size: 20px;">MOH 711A</b>
                     </div>-->
                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <form action="validate711" method="post" class="form-horizontal">
                           
                        <div class="tabbable tabbable-custom boxless" id="DQAtable" >
                   
                        
                       
                         <i style="margin-left: 450px; margin-top: 200px;">  loading data...<img src="images/utube.gif"></i>
                        
                       
                        <!-- END FORM-->           
                   
                            
                             
                           
                            
                         
                         
                        <!-- END FORM-->           
                     </div>
                           
                        </form>
                  </div>
                  <!-- END SAMPLE FORM PORTLET-->
               </div>
            <!--</div>-->
       
          
         
          
           
         
          
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
   <!--<script src="assets/js/jquery-1.8.3.min.js"></script>-->    
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
 
   <script>
      jQuery(document).ready(function() {       
      
                    $.ajax({
url:'loadFacilities',
type:'post',
dataType:'html',
success:function (data){
       $("#facility").html(data);
      $('#facility').select2(); 
      // App.init();   
}


});

 

         
$.ajax({
    url:'loadYear',
    type:'post',
    dataType:'html',
    success:function (data){
         $("#year").html(data);
         loadmonths();
//        document.getElementById("year").innerHTML=data;
        
    }
    
    
});
               
     
         
         $.ajax({
            url:'loadDQA',
            type:'post',
            dataType:'html',
            success:function (data){
                $("#DQAtable").html(data);

    

}
            
            
        }); 
       

    
           
  $('body').on('keydown', 'input, select, textarea', function(e) {
var self = $(this)
  , form = self.parents('form:eq(0)')
  , focusable
  , next
  , prev
  ;

if (e.shiftKey) {
 if (e.keyCode == 13) {
     focusable =   form.find('input,a,select,button,textarea').filter(function(){
    return !this.readOnly &&
           !this.disabled &&
           $(this).parentsUntil('form', 'div').css('display') != "none";
});
     prev = focusable.eq(focusable.index(this)-1); 

     if (prev.length) {
        prev.focus();
        $(prev).select();
     } else {
        form.submit();
    }
  }
}
  else
if (e.keyCode == 13) {
    focusable = form.find('input,a,select,button,textarea').filter(function(){
    return !this.readOnly &&
           !this.disabled &&
           $(this).parentsUntil('form', 'div').css('display') != "none";
});
    next = focusable.eq(focusable.index(this)+1);
    if (next.length) {
        next.focus();
       $(next).select();
    } else {
        form.submit();
    }
    return false;
}
});
            
 
                    
     });
      
       
          
            function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57)){
return false;
}

else{
 


 
return true;
}
}
     

 //a function to monitor if data has been entered or its just enter and 
 
 function isIntegerPressed(status){
 if(status=="1"){    
     document.getElementById("checkblank").value='1';
 }
 else {
       document.getElementById("checkblank").value='0'; 
     
 }
     
 }
 
 
 
 
    function loadcounty(){
        
        
        $.ajax({
            url:'loadCounty',
            type:'post',
            dataType:'html',
            success:function (data){
                $("#county").html(data);
                loadsubcounty();
              //  App.init();   
            }
            
            
        });
        
    }
     function loadmonths(){
      
      var yr=document.getElementById("year").value;
//      alert(yr);
              $.ajax({
url:'loadMonth?year='+yr,
type:'post',
dataType:'html',
success:function (data){
    $("#month").html(data);
//    if($("#month").val('')){
//        
//    }
// location.reload();
    
       //document.getElementById("month").innerHTML=data;
      // App.init();  
        
}


});  
      
      
      }
  
       function loadsubcounty(){
        
        var county=document.getElementById("county").value;
        $.ajax({
            url:'loadSubcounty?county='+county,
            type:'post',
            dataType:'html',
            success:function (data){
                $("#subcounty").html(data);
                
              //  App.init();   
            }
            
            
        });
        
    }
    
    function loadfacils(){
      var subcounty=document.getElementById("subcounty").value;  
                    $.ajax({
url:'loadFacilities?subcounty='+subcounty,
type:'post',
dataType:'html',
success:function (data){
       $("#facility").html(data);
         if(document.getElementById("facility").value!==''){
      updatefacilsession();
     
     
      }  
      $('#facility').select2();  
         // $("#facility").chosen();
       
       
}


}); 
         
         
        }
    

 loadcounty();
  //a function to update the selected session
      
      function updatefacilsession(){
          
        var facil=document.getElementById("facility").value;
        $.ajax({
url:'updatefacilitysession?facil='+facil,
type:'post',
dataType:'html',
success:function (data){  
 loadfrms();    
//    location.reload();
    //  $("#"+col).css({'background-color' : '#CCFFCC'});
     
}
             
             });    
          
          
          
      }
      function loadfrms(){
        
        
        $.ajax({
            url:'loadForms',
            type:'post',
            dataType:'html',
            success:function (data){
                $("#form").html(data);
                
              //  App.init(); 
              
              //also load county and facility
//              loadcounty();
              //loadsubcounty();
            }
            
            
        });
        
    }
        function sendtosessionyear(){
      
      var yr=document.getElementById("year").value;
  
     
    
              $.ajax({
url:'monthyearsession?year='+yr,
type:'post',
dataType:'html',
success:function (data){
//    $("#month").html(data);     
     loadmonths(); 
     location.reload();
       //document.getElementById("month").innerHTML=data;
      // App.init();  
        
}


});  
      
      
      }
        function sendtosessionmonth(){
      
    
      var month=document.getElementById("month").value;
    
  
              $.ajax({
url:'monthyearsession?month='+month,
type:'post',
dataType:'html',
success:function (data){
//    $("#month").html(data);     
      location.reload();
       //document.getElementById("month").innerHTML=data;
      // App.init();  
        
}


});  
      
      
      }
 
//    document.getElementById("checkblank").value='0';
      
 
      
  
     
    </script>

   <!-- END JAVASCRIPTS -->  
   
   
   </div>
</body>
<!-- END BODY -->
</html>


