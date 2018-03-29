<%-- 
    Document   : ManageGaps
    Created on : Mar 27, 2018, 10:08:53 AM
    Author     : GNyabuto
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>Manage Gaps</title>
   
      <script src="assets/js/jquery-1.8.3.min.js"></script>    
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
   
     <link href="media/dataTables/jquery.dataTables.css" rel="stylesheet" type="text/css" />
          <link href="scripts/dataTables.tableTools.css" rel="stylesheet" type="text/css" />
         <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="stylesheet" href="themes/base/jquery.ui.all.css">
        
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
    width: 30px;
}
td{ 
    padding-left: 30px;
}
</style>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>

   <!-- BEGIN HEADER -->
   <div class="header navbar navbar-inverse">
      <!-- BEGIN TOP NAVIGATION BAR -->
      <div class="navbar-inner">
         <div class="container-fluid">
            <!-- BEGIN RESPONSIVE MENU TOGGLER -->
            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
            <img src="assets/img/menu-toggler.png" alt="" />
            </a>
            <!-- END RESPONSIVE MENU TOGGLER --> 
           
            <div style="float:left; margin-left: 2%;">
              <!-- BEGIN LOGO -->
            <div class="control-group">
                             <div style="float:right;"> 
                                 <div style="float: left;">
	                       <font color="white" size="3px"><b>Year: </b></font>  
                                <select required data-placeholder="Reporting Year" class="span4 m-wrap" style="width: 100px;" tabindex="-1" onchange=""  id="year" name="year">
                                <option value=""></option>                                 
                                 </select>
                               </div>
                                 
                <div style="float: left;">
                                    <font color="white" size="3px"><b>Month: </b></font>  
                                  <select placeholder="Month" class="span4 m-wrap" style="width: 150px;" tabindex="-1"  id="month" name="month" onchange="">
                                    <option value=""></option>
                                 </select>
                                    </div>
                                 
                <div style="float: left;">
                                    <font color="white" size="3px" margin-left="3px"><b>County : </b></font>
                              <select placeholder="County" onchange="" style="width: 120px;"  class="span4 m-wrap" tabindex="-1"  id="county" name="county">
                                    <option value=""></option>
                                 </select>
                                   </div>
                                 
                                 <div style="float: left;">
                                    <font color="white" size="3px" margin-left="3px"><b>Sub-County : </b></font>
                               <select data-placeholder="Sub-County" onchange="" style="width: 150px;"  class="span6 m-wrap" tabindex="-1"  id="subcounty" name="subcounty">
                                    <option value="">Select County First</option>
                                 </select>
                                  </div>
                                 
                                   <div style="float: left;">
                                   <font color="white" size="3px" margin-left="3px"><b>Facility : </b></font>
                             <select onchange="" style="width: 150px;"   data-placeholder="Facility" required class="span6" tabindex="-1"  id="facility" name="facility">
                                    <option value=""></option>
                                 </select></div>
                                   
                                    <div style="float: left;">
                                   <font color="white" size="3px" margin-left="3px"><b>Gap : </b></font>
                              <select onchange="" style="width: 150px;"   data-placeholder="Facility" required class="span6" tabindex="-1"  id="gap" name="gap">
                                    <option value=""></option>
                                 </select></div>
                                   
                                    <div style="float: left;">
                                   <font color="white" size="3px" margin-left="3px"><b> Status : </b></font>
                              <select onchange="" style="width: 150px;"   data-placeholder="Status" required class="span6" tabindex="-1"  id="status" name="status">
                                    <option value="">Choose Status</option>
                                    <option value="1">Approved</option>
                                    <option value="0">Not Approved</option>
                                 </select></div>
                                 
                                    <div style="float: left; margin-left: 20px; height: 80%; margin-bottom: 5%;">
                                        <button id="view_gaps" class=" btn btn-success" > View Gaps</button>
                                    </div>
                              
                           </div>
            <!-- END LOGO -->
            </div>
            <br><br>
            <div style="text-align: center; color: white; font-weight: bolder; height: 30px;">
                Ratio Management Module.
         </div>
            
      </div>
      <!-- END TOP NAVIGATION BAR -->
   </div>
   <!-- END HEADER -->
   </div>
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
         <!-- BEGIN PAGE CONTAINER-->
         <div class="container-fluid">
           
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
               <div class="span12">
                  <!-- BEGIN SAMPLE FORM PORTLET-->   
                  <div style="min-width:1150px;">
                  <br><br>
                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <div id="gaps_data" style="overflow: auto;">   
             NO GAPS LOADED
                   </div>
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
      <script src="select2/js/select2.js"></script>
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
   <script>
       
      jQuery(document).ready(function() {       
         // initiate layout and plugins
         loadcounty();
         loadsubcounty();
         loadfacility();
         loadyear();
         loadmonths();
         loadgaps();
         
$("#county").change(function(){
   loadsubcounty();
   loadgaps();
});

$("#subcounty").change(function(){
   loadfacility();
   loadgaps();
});
$("#facility").change(function(){
loadgaps();
});
$("#gap").change(function(){
  var facility = $("#facility").val(); 

if(facility==""){
loadfacility();
}

});
$("#view_gaps").click(function(){
   LoadData();
});
     });  
    
$("#year").change(function(){
   loadmonths();
   loadgaps();
});
$("#month").change(function(){
   loadfacility();
   loadgaps();
});

function loadcounty(){
            $.ajax({
            url:'county',
            type:'post',
            dataType:'html',
            success:function (data){
                $("#county").html(data);
            }
        });
   }
   
function loadyear(){
            $.ajax({
            url:'years',
            type:'post',
            dataType:'html',
            success:function (data){
                $("#year").html(data);
            }
        });
   }
function loadgaps(){
    var county = $("#county").val();
    var subcounty = $("#subcounty").val();
    var year = $("#year").val();
    var month = $("#month").val();
    var facility = $("#facility").val(); 
    
    var form_data = {"county":county,"subcounty":subcounty,"year":year,"month":month,"facility":facility};
    var url = "gaps";
     $.post(url,form_data , function(data) {
       $("#gap").html(data);
        });
   }
   
function loadsubcounty(){
    var county = $("#county").val();
    
    var form_data = {"county":county};
    var url = "subcounty";
     $.post(url,form_data , function(data) {
         $("#subcounty").html(data);
         loadfacility();
        });
        
   }
   
function loadmonths(){
    var year = $("#year").val();
    var form_data = {"year":year};
    var url = "months";
     $.post(url,form_data , function(data) {
        $("#month").html(data);
        loadfacility();
        });
        
   }
   
function loadfacility(){
    var county = $("#county").val();
    var subcounty = $("#subcounty").val();
    var year = $("#year").val();
    var month = $("#month").val();
    var gap = $("#gap").val();
    
        var form_data = {"county":county,"subcounty":subcounty,"year":year,"month":month,"gap":gap};
    var url = "facility";
     $.post(url,form_data , function(data) {
       $("#facility").html(data);
        });
   }
     
      function changed_cell(cell_val){
          $("#"+cell_val).css({'background-color' : '#CCFFCC'}); 
      }
      
      function edit_ratio(position){
            var  id = $("#ratio_id_"+position).val(); 
            var explanation = $("#explanation_"+position);
            var approved = $("#approved_"+position);
            if(1==1){
             var message="<font color='red'><b>Error, One age bracket cant have a ratio of more or equal to 1.</b></font>";
            
            notify(message);
            }
            else{
            var form_data = {"id":id,"explanation":explanation,"approved":approved};
        
            var url='UpdateGap';
            $.post(url,form_data , function(output) {
                      var response = JSON.parse(output).data;
                      var code=response.code;
                     var message=response.message;
                     notify(message);
           
                   });
               }
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


function notify(message){ 
var n = noty({text: message,
    layout: 'center',
    type: 'Success',
 timeout: 4800});
}   

function new_ratio(){
  $('#add_ratio').modal();  
}

function LoadData(){
    
         $("#gaps_data").html("<table border='0' class='display' id='example'><tr><td>Loading Gaps...<img src='images/utube.gif' alt='.'></td></tr></table>");
         
         var county,subcounty,gap,facility,status,year,month;
        
        county = $("#county").val();
        subcounty = $("#subcounty").val();
        gap = $("#gap").val();
        facility = $("#facility").val();
        status = $("#status").val();
        year = $("#year").val();
        month = $("#month").val();
        
        
    var form_data = {"county":county,"subcounty":subcounty,"year":year,"month":month,"facility":facility,"gap":gap,"status":status};
    var url = "ViewGaps";
     $.post(url,form_data , function(data) {
      $("#example").html(data);
      $('#example').dataTable();
        });  
         }
         
         
         function approve_gap(pos){
     var id = $("#gapid_"+pos).val();    
     var form_data = {"id":id};
     var url = "approvegap";
     $.post(url,form_data , function(data) {
        //notify user on the status
        $.notify(
        {icon: "images/checked.png", 
        message:data},
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
        // end of notification

        });  
         
         }
         
         function update_gap(pos){
        var id = $("#gapid_"+pos).val();    
        var explanation = $("#explanation_"+pos).val();  
        
        var form_data = {"id":id,"explanation":explanation};
        var url = "updategap";
        $.post(url,form_data , function(data) {
       //notify user on the status
        $.notify(
        {icon: "images/checked.png", 
        message:data},
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
        // end of notification
        
        });  
         
         
         }
         
         
   </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>