<%-- 
    Document   : loadTB
    Created on : May 19, 2015, 4:44:09 PM
    Author     : Maureen
--%>

<%-- 
    Document   : Form731
    Created on : May 11, 2015, 10:09:28 AM
    Author     : Maureen
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>TB Form</title>
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
                             <div style="float:right;"> 
                               
                              <font color="white" size="3px"><b>Year: </b></font>  
                                   <font color="#4b8df8" size="3px"><b><%if(session.getAttribute("year")!=null){out.println(session.getAttribute("year").toString()+" | ");}%></b></font>
                                 
                                    <font color="white" size="3px"><b>Month: </b></font>  
                                   <font color="#4b8df8" size="3px"><b><%if(session.getAttribute("monthname")!=null){out.println(session.getAttribute("monthname").toString()+" | ");}%></b></font>
                                 
                                   
                                    <font color="white" size="3px" margin-left="3px"><b>County : </b></font>
                              
                                <select placeholder="County" onchange="loadsubcounty();"  class="span4 m-wrap" tabindex="-1"  id="county" name="county">
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
                    
             
                  </h3>
                  <ul class="breadcrumb">
                     <li>
                        <i class="icon-home"></i>
                        <font color="#4b8df8">Care and Treatment</font>
                        
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
                        <h4><i class="icon-reorder"></i></h4>
                        <b style="color:white;text-align: center;font-size: 20px;">TB</b>
                         <h4><i style="margin-left:150px;" id="isValidated"></i></h4>
                     </div>
                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        
                         
                   <%if (session.getAttribute("validatetb") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("validatetb")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("validatetb");
                            }

                        %>
                        <form action="validateTb" method="post" class="form-horizontal">
                          
                        
                        
                  
                 
                          <div id="tbtable">
                              
                               <i style="margin-left: 450px; margin-top: 200px;">  loading data...<img src="images/utube.gif"></i>
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
         // initiate layout and plugins
         
                    $.ajax({
url:'loadFacilities',
type:'post',
dataType:'html',
success:function (data){
       $("#facility").html(data);
     
        $('#facility').select2();  
}


}); 
         
         
         $.ajax({
            url:'loadTb',
            type:'post',
            dataType:'html',
            success:function (data){
                $("#tbtable").html(data);
//            $("#TB_STATN").focus();   
           
 var validity=$("#checkValidity").html();
$("#isValidated").html(validity);
            
            
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
      
      
      
//      function loadmonths(){
//      
//      var yr=document.getElementById("year").value;
//      
//              $.ajax({
//url:'loadMonth?year='+yr,
//type:'post',
//dataType:'html',
//success:function (data){
//    $("#month").html(data);     
//    
//       //document.getElementById("month").innerHTML=data;
//      // App.init();  
//        
//}
//
//
//}
//);  
//      
//      
             //  }
             
             
             
             //AUTOUPDATING FUNCTION
             
             
             function autosave(col){
            var totalsVariables=",TB_STATP,TB_IPTP,TB_SCREENP,CARPCTHTTPR,"
           
            var achieved=document.getElementById(col).value;
            
       
             $.ajax({
url:'saveTb?col='+col+"&achieved="+achieved,
type:'post',
dataType:'html',
success:function (data){
   if(data.trim()!="success"){$("#error").html(data);
//     $("#"+col).css({'background-color' : 'red'});
        }
    else{
        $("#error").html("");
    if(achieved==""){}
  else if(totalsVariables.indexOf(","+col+",")>-1) {
   $("#"+col).css({'background-color' : 'plum'});    
  } else{
      $("#"+col).css({'background-color' : '#CCFFCC'});
}

$("#isValidated").html("<font color=\"red\"><b>Form Not Validated.<img style=\"margin-left:10px;\" src=\"images/notValidated.jpg\" width=\"20px\" height=\"20px\"></b></font>");
}
}
  
             
             });
             }
            
             
           
             
          
            function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57)){
return false;
}

else{
 


 
return true;
}
}
          
      
      //a function to update the selected session
      
      function updatefacilsession(){
          
        var facil=document.getElementById("facility").value;
        $.ajax({
url:'updatefacilitysession?facil='+facil,
type:'post',
dataType:'html',
success:function (data){      
    location.reload();
    //  $("#"+col).css({'background-color' : '#CCFFCC'});
        
}
             
             });    
          
          
          
      }
   function TB_STAT(){
     
//     alert("called");
      var TB_STATN=0;
     TB_STATN= document.getElementById("TB_STATN").value;
      var TB_STATD=0;
     TB_STATD= document.getElementById("TB_STATD").value;
      if(TB_STATN==""){TB_STATN=0;}
      if(TB_STATD==""){TB_STATD=0;}
        
           var visitstotal=0;
           if(TB_STATD!="" && TB_STATN!="" && TB_STATN!==0){
           visitstotal=(parseInt(TB_STATD)/parseInt(TB_STATN))*100;
             if(visitstotal==""){visitstotal=0;}
                document.getElementById("TB_STATP").value=Math.round(visitstotal);    
             autosave('TB_STATP');     

                }
                else{
                 
                document.getElementById("TB_STATP").value=("0");    
                 autosave('TB_STATP');    
                    
                    
                }
          
         
               
           
          
     
      }    
   function TB_IPT(){
     
//     alert("called");
      var TB_IPTN=0;
     TB_IPTN= document.getElementById("TB_IPTN").value;
      var TB_IPTD=0;
     TB_IPTD= document.getElementById("TB_IPTD").value;
      if(TB_IPTN==""){TB_IPTN=0;}
      if(TB_IPTD==""){TB_IPTD=0;}
      
     
        
           var visitstotal=0;
           if(TB_IPTD!="" && TB_IPTN!="" && TB_IPTN!==0){
           visitstotal=(parseInt(TB_IPTD)/parseInt(TB_IPTN))*100;
           if(visitstotal==""){visitstotal=0;}
           document.getElementById("TB_IPTP").value=Math.round(visitstotal);
           autosave('TB_IPTP');
           }
           else{
             document.getElementById("TB_IPTP").value="0";
           autosave('TB_IPTP');   
           }
     
      }    
   function TB_SCREEN(){
     
//     alert("called");
      var TB_SCREENN=0;
     TB_SCREENN= document.getElementById("TB_SCREENN").value;
      var TB_SCREEND=0;
     TB_SCREEND= document.getElementById("TB_SCREEND").value;
     if(TB_SCREENN==""){TB_SCREENN=0;}
      if(TB_SCREEND==""){TB_SCREEND=0;}
        
           var visitstotal=0;
            if(TB_SCREEND!="" && TB_SCREENN!="" && TB_SCREENN!==0){
           visitstotal=(parseInt(TB_SCREEND)/parseInt(TB_SCREENN))*100;
           
           if(visitstotal==""){visitstotal=0;}
           
           document.getElementById("TB_SCREENP").value=Math.round(visitstotal);
         
               
            autosave('TB_SCREENP');
            } else{
             document.getElementById("TB_SCREENP").value="0";
           autosave('TB_SCREENP');   
           }
     
      }    
       function indic74(){
     
//     alert("called");
      var male=0;
     male= document.getElementById("CARPCTHTMPR").value;
      var female=0;
     female= document.getElementById("CARPCTHTFPR").value;
     
      if(male==""){male=0;}
      if(female==""){female=0;}
     
     
 
           
           var visitstotal=parseInt(female)+parseInt(male);
           
           document.getElementById("CARPCTHTTPR").value=Math.round(visitstotal);
             
            autosave('CARPCTHTTPR');
          
     
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
 
 
   </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>



