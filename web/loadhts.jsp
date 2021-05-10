<%-- 
    Document   : ITP
    Created on : Feb 21, 2018, 2:36:31 PM
    Author     : GNyabuto
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>HTS Form</title>
   
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
 
<link rel="stylesheet" href="select2/css/select2.css"/>
   
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
.data-cell{
    width: 32px;
}
table{
    width: 1100px;
    border-width: 2px;
    margin-left: 20px;
}
tr > .st{
border-width: 2px;    
}
.title{
    font-weight: bolder;
    margin-bottom: 130px;
}
input[type=text]{
    border-color: black;
    border-width: 0.7px;
}
td{
 text-align: center;   
}
</style>
<script type="text/javascript" src="js/sum_ipt.js"></script>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top" onkeydown="if (event.keyCode==13) {event.keyCode=9; return event.keyCode }">
    
     <!-------------------------------------------dialog box for unvalidated facils-------------------------------------------------->  
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
  <!-------------------------------------------dialog box for unvalidated facils-------------------------------------------------->  
  
    
    
   <!-- BEGIN HEADER -->
   <div class="header navbar navbar-inverse navbar-fixed-top">
      <!-- BEGIN TOP NAVIGATION BAR -->
      <div class="navbar-inner">
         <div class="container-fluid">
            <!-- BEGIN LOGO -->
                      <div class="control-group">
                             <div style="float:right;"> 
                                 
                                 <font color="white" size="3px"><b>Year: </b></font>  
                                <select style="width:100px;" required data-placeholder="Reporting Year" class="span4 m-wrap" tabindex="-1" onchange="sendtosessionyear();"  id="year" name="year">
                                    <option value=""></option>                                 
                                   
                                 </select>
<!--                                   <font color="#4b8df8" size="3px"><b><%if(session.getAttribute("year")!=null){out.println(session.getAttribute("year").toString()+" | ");}%></b></font>
                                    <input type="hidden" name="year" id="year" value="<%=session.getAttribute("year").toString()%>">
                                  -->
                                    <font color="white" size="3px"><b>Month: </b></font>  
                                  
                                  <select style="width:150px;" placeholder="Month" class="span4 m-wrap" tabindex="-1"  id="month" name="month" onchange="sendtosessionmonth();">
                                    <option value=""></option>
                                 </select>
                                   
                                    <font color="white" size="3px" margin-left="3px"><b>County : </b></font>
                              
                                <select placeholder="County" style="width:120px;color:black;" onchange="loadsubcounty();"  class="span4 m-wrap" tabindex="-1"  id="county" name="county">
                                    <option value=""></option>
                                 </select>
                                   
                                    <font color="white" size="3px" margin-left="3px"><b>Sub-County : </b></font>
                              
                               <select data-placeholder="Sub-County" style="width:120px;color:black;" onchange="loadfacils();"  class="span6 m-wrap" tabindex="-1"  id="subcounty" name="subcounty">
                                    <option value="">Select County First</option>
                                 </select>
                                    
                                   
                                   <font color="white" size="3px" margin-left="3px"><b>            Activity Site : </b></font>
                              
                                 <select onchange="updatefacilsession();" style="width:200px;color:black;" data-placeholder="Facility" required class="chosen-with-diselect span6" tabindex="-1"  id="facility" name="facility">
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
                        <i class="icon-hospital"></i>
                        <font color="#4b8df8">Service delivery point</font>
                        
                     
                     </li>
                     <li>
                        <select onchange="updatesdpsession();" style="width:200px;color:black;" data-placeholder="SDP" required class="chosen-with-diselect span6" tabindex="-1"  id="sdp" name="sdp">
                            <% if(session.getAttribute("sdp")!=null){out.println(session.getAttribute("sdp"));} %>
                                 </select>
                     
                     </li>
           <li><h3 id="validationmsg" color="red"></h3></li>
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
                        <b style="color:white;text-align: center;font-size: 20px;">HTS Monthly Summary</b>
                       <span id="recordcounter" style="margin-left:9%;color:yellow;font-size:16px;font-family: cambria;"><b></b></span>
                    <span id="newform" style="margin-left: 15%;background-color: white;padding: 2px;"><b></b></span>
                     </div>
                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <form action="validatehts" class="form-horizontal" id="loadhtsform">
                   <div id="ipt_table">   
             <i style="margin-left: 450px; margin-top: 200px;">  loading data...<img src="images/utube.gif"></i>
                          
                       
<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;'> HTS Monthly Summary</b></legend><table  cellpadding='2px' border='0' style='border-color: #e5e5e5;margin-bottom: 3px;'>
                             
    </table></fieldset><div class='form-actions'><input type='submit' class='btn blue' value='Run Validation' name='validate' id='validate'/></div>
                      
                   
                   </div>
                          <input type="hidden" name="checkblank" id="checkblank" value="0"/>
                            <input type="hidden" value='0' id='isautosave'/>
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
      jQuery(document).ready(function() {       
         // initiate layout and plugins
         
         $('form').submit(function(){
        
         return true;
         });
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
            url:'loadhts?sdp='+$("#sdp").val(),
            type:'post',
            dataType:'html',
            success:function (data){
                 $("#ipt_table").html(data);                
                 $("#newform").html($("#formstatus").html());  
                 $("#recordcounter").html($("#rc").html());  
                 $("#allunValidated").html($("#ufs").html());
           
            }
        }); 
  
$.ajax({
    url:'loadYear',
    type:'post',
    dataType:'html',
    success:function (data){
         $("#year").html(data);
         loadmonths();
        
    }
    
    
});
//               
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
//    if($("#month").val('')){
//        
//    }
// location.reload();
    
       //document.getElementById("month").innerHTML=data;
      // App.init();       
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
	  
             
             //AUTOUPDATING FUNCTION
           
           String.prototype.endsWith = function(suffix) {
    return this.match(suffix+"$") == suffix;
};
             
             function autosave(col){
                 
var totalsVariables =",couns_T_M,couns_T_F,couns_GT,tes_T_M,tes_T_F,tes_GT,tes_new_T_M,tes_new_T_F,tes_new_GT,res_T_M,res_T_F,res_GT,tes_new_res_T_M,tes_new_res_T_F,tes_new_res_GT,pos_T_M,pos_T_F,pos_GT,pos_tes_T_M,pos_tes_T_F,pos_tes_GT,ref_T_M,ref_T_F,ref_GT,tes_marps_T_M,tes_marps_T_F,tes_marps_GT,pos_marps_T_M,pos_marps_T_F,pos_marps_GT,tes_coup_T_M,tes_coup_T_F,tes_coup_GT,tes_coup_dis_T_M,tes_coup_dis_T_F,tes_coup_dis_GT,";          
 
var achieved=$("#"+col).val();
           var sdp=$("#sdp").val(); 
          var is=$("#isautosave").val();
          
          if(sdp===''){
        $("#loadhtsform").hide();
        $("#validationmsg").html("<font color='red'>Select SDP first!!</font>");
        $("#sdp").focus();
        }
          else {
              //save the first entry only but for the rest do autosave
              
          if(is=='0'){
             $("#isautosave").val("1");
             $.ajax({
url:'savehts?col='+col+"&achieved="+achieved,
type:'post',
dataType:'html',
success:function (data){
   if(data.trim()!=="success"){$("#error").html(data);
//     $("#"+col).css({'background-color' : 'red'});
        }
    else{
        $("#error").html("");
    if(achieved===""){}
  else if(totalsVariables.indexOf(","+col+",")>-1) {
   $("#"+col).css({'background-color' : 'plum'});    
  } else
  {
      $("#"+col).css({'background-color' : '#CCFFCC'});
  }

$("#isValidated").html("<font color=\"red\"><b>Form Not Validated.<img style=\"margin-left:10px;\" src=\"images/notValidated.jpg\" width=\"20px\" height=\"20px\"></b></font>");
}
}
             
             });
             
        }
    else {
        
        //show color codes but no autosave
        
        if(totalsVariables.indexOf(","+col+",")>-1) {
   $("#"+col).css({'background-color' : 'plum'});    
  } else
  {
      $("#"+col).css({'background-color' : '#CCFFCC'});
  }

$("#isValidated").html("<font color=\"red\"><b>Form Not Validated.<img style=\"margin-left:10px;\" src=\"images/notValidated.jpg\" width=\"20px\" height=\"20px\"></b></font>");

        
        
    }
        
        
        }
             
    
    }//end of function
             
           
             
          
            function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57)){
return false;
}

else{
 

document.getElementById("checkblank").value='1';
 
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
    location.reload();//reload is called after refreshing of the sdpin the function below
    
    //  $("#"+col).css({'background-color' : '#CCFFCC'});
                       }
             
             });    
          
          
          
      }
   
   
   
      function updatesdpsession(){
          //alert("update sdp called");
        var sdp=document.getElementById("sdp").value;
        
        
        if(sdp!==''){
            
            $("#loadhtsform").show();
        $("#validationmsg").html("");
            
        }
        else{
           $("#loadhtsform").hide();
        $("#validationmsg").html("<font color='red'>Select SDP first!!</font>");    
            
        }
        
        
        $.ajax({
url:'updatesdpsession?sdp='+sdp,
type:'post',
dataType:'html',
success:function (data){  
    
    $("#sdp").html(data);
    
    location.reload();
    //  $("#"+col).css({'background-color' : '#CCFFCC'});
                       }
             
             });    
          
          
          
      }
   
   
   //default call of loading sdp
   
         function autosum(sourceindicators,destinationindicator){
    var sourceindicatorsarray=sourceindicators.split("@");
  
   
    var destinationelement=destinationindicator;
    var total=0;
    var allblanks=true;
    for(b=0;b<sourceindicatorsarray.length;b++){
        //check if there
        if($("#"+sourceindicatorsarray[b]).val()!==''){
      total=parseInt(total)+parseInt($("#"+sourceindicatorsarray[b]).val()); 
        
            $("#"+destinationelement).val(total);
             allblanks=false;
            }
                                               
        }
          if(allblanks===true){  $("#"+destinationelement).val("");   }                                    
       autosave(destinationindicator);                                      
    
}
   
   
      $.ajax({
url:'updatesdpsession',
type:'post',
dataType:'html',
success:function (data){  
    
    $("#sdp").html(data);
    
    //location.reload();
    //  $("#"+col).css({'background-color' : '#CCFFCC'});
                       }
             
             });
   
   
      //______________________________________________________________________________________________________________   
      
     
      //______________________________________________________________________________________________________________
      
 
 
  $('body').on('keydown', 'input, select, textarea', function(e) {
var self = $(this)
  , form = self.parents('form:eq(0)')
  , focusable
  , next
  , prev
  ;

if (e.shiftKey) {
 if (e.keyCode == 13) {
     focusable =   form.find(':input:visible,a,select,button,textarea').filter(':not([readonly])');
     prev = focusable.eq(focusable.index(this)-1); 

     if (prev.length) {
        prev.focus();
        prev.select();
     } else {
        form.submit();
    }
  }
}
  else
if (e.keyCode == 13) {
    focusable = form.find(':input:visible,a,select,button,textarea').filter(':not([readonly])');
    next = focusable.eq(focusable.index(this)+1);
    if (next.length ) {
      //  if ( next.is('[readonly]') ) {}
      
        next.focus();
        next.select();
        
    } else {
        form.submit();
    }
    return false;
}
});
 
 
     
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
 
 loadcounty(); 
      
      
      //updatesdpsession();
   </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>

