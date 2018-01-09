<%-- 
    Document   : ManageRatios
    Created on : Jan 8, 2018, 10:46:24 AM
    Author     : GNyabuto
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>Manage DATIM Ratios</title>
   
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
    padding-left: 100px;
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
            
            <div style="float:left; margin-left: 15%;">
                <button class="btn" value="" style="color: black; font-weight: bolder; width: 200px; height: 30px;" onclick="new_ratio();" name="New Ratio" id="new_ratio">New Ratio</button>
            </div>
            <div style="text-align: center; color: white; font-weight: bolder; height: 30px;">
                Ratio Management Module.
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
         <!-- BEGIN PAGE CONTAINER-->
         <div class="container-fluid">
           
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
               <div class="span12">
                  <!-- BEGIN SAMPLE FORM PORTLET-->   
                  <div style="min-width:1150px;">
                  
                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <div id="ratios_data" style="overflow: auto;">   
             
                   </div>
                        <!-- END FORM--> 
                        
                        
              <div class="modal fade" id="add_ratio" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  style="width: 800px;">
                  <div class="modal-dialog modal-lg">
                <div class="modal-content" >
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                    <h4 class="modal-title" id="myModalLabel"><p style="text-align: center; color:black; font-weight: 999;">New Ratio</p></h4>
                  </div>
                  <div class="modal-body"  style=" min-height: 470px;">
                   <datalist id="indicators">
                       <option value="Internet Explorer">
                  </datalist>
                  <table>
                      <tr>
                          <td> County : <select id="county_id" name="county_id" required> </select> </td>    
                          <td class="next_cell"> Indicator : <input type="text" list="indicators" id="indicator_id" name="indicator_id" required> </select> </td>    
                      </tr>    
                      <tr>
                          <td> F <1 : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="f_1" name="f_1" required> </td>    
                          <td class="next_cell"> M <1 : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="m_1" name="m_1" required> </select> </td>    
                      </tr>    
                      <tr>
                          <td> F 1-9: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="f_9" name="f_9" required> </td>    
                          <td class="next_cell"> M 1-9 : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="m_9" name="m_9" required> </select> </td>    
                      </tr>    
                      <tr>
                          <td> F 10-14 : <input type="text" id="f_14" name="f_14" required> </td>    
                          <td class="next_cell"> M 10-14 : <input type="text" id="m_14" name="m_14" required> </select> </td>    
                      </tr>    
                      <tr>
                          <td> F 15-19 : <input type="text" id="f_19" name="f_19" required> </td>    
                          <td class="next_cell"> M 15-19 : <input type="text" id="m_19" name="m_19" required> </select> </td>    
                      </tr>    
                      <tr>
                          <td> F 20-24 : <input type="text" id="f_24" name="f_24" required> </td>    
                          <td class="next_cell"> M 20-24 : <input type="text" id="m_24" name="m_24" required> </select> </td>    
                      </tr>    
                      <tr>
                          <td> F 25-29 : <input type="text" id="f_29" name="f_29" required> </td>    
                          <td class="next_cell"> M 25-29 : <input type="text" id="m_29" name="m_29" required> </select> </td>    
                      </tr>    
                      <tr>
                          <td> F 30-34 : <input type="text" id="f_34" name="f_34" required> </td>    
                          <td class="next_cell"> M 30-34 : <input type="text" id="m_34" name="m_34" required> </select> </td>    
                      </tr>    
                      <tr>
                          <td> F 35-39 : <input type="text" id="f_39" name="f_39" required> </td>    
                          <td class="next_cell"> M 35-39 : <input type="text" id="m_39" name="m_39" required> </select> </td>    
                      </tr>    
                      <tr>
                          <td> F 40-49 : <input type="text" id="f_49" name="f_49" required> </td>    
                          <td class="next_cell"> M 40-49 : <input type="text" id="m_49" name="m_49" required> </select> </td>    
                      </tr>    
                      <tr>
                          <td> F 50+ : &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="f_50" name="f_50" required> </td>    
                          <td class="next_cell"> M 50+ : &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="m_50" name="m_50" required> </select> </td>    
                      </tr>    
                  </table>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn-primary" data-dismiss="modal" style="height:30px; width: 100px;" id="save">Save</button>

                  </div>
                </div>
              </div>
            </div>
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
         loadIndicators();
         loadRatios();
//  

$("#save").click(function(){
            var  county_id = $("#county_id").val();
            var indicator_id = $("#indicator_id").val();
            var  f_1 = $("#f_1").val(); 
            var  f_9 = $("#f_9").val(); 
            var  f_14 = $("#f_14").val(); 
            var  f_19 = $("#f_19").val(); 
            var  f_24 = $("#f_24").val(); 
            var  f_29 = $("#f_29").val(); 
            var  f_34 = $("#f_34").val(); 
            var  f_39 = $("#f_39").val(); 
            var  f_49 = $("#f_49").val(); 
            var  f_50 = $("#f_50").val(); 
            var  m_1 = $("#m_1").val(); 
            var  m_9 = $("#m_9").val(); 
            var  m_14 = $("#m_14").val(); 
            var  m_19 = $("#m_19").val(); 
            var  m_24 = $("#m_24").val(); 
            var  m_29 = $("#m_29").val(); 
            var  m_34 = $("#m_34").val(); 
            var  m_39 = $("#m_39").val(); 
            var  m_49 = $("#m_49").val(); 
            var  m_50 = $("#m_50").val(); 
            if(f_1==""){f_1=0;}
            if(f_9==""){f_9=0;}
            if(f_14==""){f_14=0;}
            if(f_19==""){f_19=0;}
            if(f_24==""){f_24=0;}
            if(f_29==""){f_29=0;}
            if(f_34==""){f_34=0;}
            if(f_39==""){f_39=0;}
            if(f_49==""){f_49=0;}
            if(f_50==""){f_50=0;}
            if(m_1==""){m_1=0;}
            if(m_9==""){m_9=0;}
            if(m_14==""){m_14=0;}
            if(m_19==""){m_19=0;}
            if(m_24==""){m_24=0;}
            if(m_29==""){m_29=0;}
            if(m_34==""){m_34=0;}
            if(m_39==""){m_39=0;}
            if(m_49==""){m_49=0;}
            if(m_50==""){m_50=0;}
            
            
            if(county_id=="" || indicator_id=="" || f_1>=1 || f_9>=1 || f_14>=1 || f_19>=1 || f_24>=1 || f_29>=1 ||  
              f_34>=1 || f_39>=1 || f_49>=1 || f_50>=1 || m_1>=1 || m_9>=1 
              || m_14>=1 || m_19>=1 || m_24>=1 || m_29>=1 || 
              m_34>=1 || m_39>=1 || m_49>=1 || m_50>=1){
             var message="<font color='red'><b>Error, Ensure no ratio is equal to or more than 1 as we select county and indicator.</b></font>";
            
            notify(message);
            }
            else{   
            var form_data = {"county_id":county_id,"indicator_id":indicator_id,"f_1":f_1,"f_9":f_9,"f_14":f_14,"f_19":f_19,"f_24":f_24,"f_29":f_29,"f_34":f_34,"f_39":f_39,"f_49":f_49,
            "f_50":f_50,"m_1":m_1,"m_9":m_9,"m_14":m_14,"m_19":m_19,"m_24":m_24,"m_29":m_29,"m_34":m_34,"m_39":m_39,"m_49":m_49,"m_50":m_50};
        
            var url='SaveRatio';
            $.post(url,form_data , function(output) {
                      var response = JSON.parse(output).data;
                      var code=response.code;
                     var message=response.message;
                     notify(message);
                     loadRatios();
                   });
               }
});


     });
     
function loadcounty(){
            $.ajax({
            url:'loadCounty',
            type:'post',
            dataType:'html',
            success:function (data){
                $("#county_id").html(data);
            }
        });
        
    }
function loadIndicators(){
            $.ajax({
            url:'LoadIndicators',
            type:'post',
            dataType:'html',
            success:function (data){
                $("#indicators").html(data);
            }
           
        });
        
    }
      
      function changed_cell(cell_val){
          $("#"+cell_val).css({'background-color' : '#CCFFCC'}); 
      }
      
      function edit_ratio(position){
            var  id = $("#ratio_id_"+position).val(); 
            var  f_1 = $("#f_1_"+position).val(); 
            var  f_9 = $("#f_9_"+position).val(); 
            var  f_14 = $("#f_14_"+position).val(); 
            var  f_19 = $("#f_19_"+position).val(); 
            var  f_24 = $("#f_24_"+position).val(); 
            var  f_29 = $("#f_29_"+position).val(); 
            var  f_34 = $("#f_34_"+position).val(); 
            var  f_39 = $("#f_39_"+position).val(); 
            var  f_49 = $("#f_49_"+position).val(); 
            var  f_50 = $("#f_50_"+position).val(); 
            var  m_1 = $("#m_1_"+position).val(); 
            var  m_9 = $("#m_9_"+position).val(); 
            var  m_14 = $("#m_14_"+position).val(); 
            var  m_19 = $("#m_19_"+position).val(); 
            var  m_24 = $("#m_24_"+position).val(); 
            var  m_29 = $("#m_29_"+position).val(); 
            var  m_34 = $("#m_34_"+position).val(); 
            var  m_39 = $("#m_39_"+position).val(); 
            var  m_49 = $("#m_49_"+position).val(); 
            var  m_50 = $("#m_50_"+position).val(); 
            
            if(f_1>=1 || f_9>=1 || f_14>=1 || f_19>=1 || f_24>=1 || f_29>=1 ||  
              f_34>=1 || f_39>=1 || f_49>=1 || f_50>=1 || m_1>=1 || m_9>=1 
              || m_14>=1 || m_19>=1 || m_24>=1 || m_29>=1 || 
              m_34>=1 || m_39>=1 || m_49>=1 || m_50>=1){
             
             var message="<font color='red'><b>Error, One age bracket cant have a ratio of more or equal to 1.</b></font>";
            
            notify(message);
            }
            else{
            var form_data = {"id":id,"f_1":f_1,"f_9":f_9,"f_14":f_14,"f_19":f_19,"f_24":f_24,"f_29":f_29,"f_34":f_34,"f_39":f_39,"f_49":f_49,
            "f_50":f_50,"m_1":m_1,"m_9":m_9,"m_14":m_14,"m_19":m_19,"m_24":m_24,"m_29":m_29,"m_34":m_34,"m_39":m_39,"m_49":m_49,"m_50":m_50};
        
            var url='UpdateRatio';
            $.post(url,form_data , function(output) {
                      var response = JSON.parse(output).data;
                      var code=response.code;
                     var message=response.message;
                     notify(message);
                     
                      
            $("#f_1_"+position).css({'background-color' : '#ffffff'}); 
            $("#f_9_"+position).css({'background-color' : '#ffffff'}); 
            $("#f_14_"+position).css({'background-color' : '#ffffff'}); 
            $("#f_19_"+position).css({'background-color' : '#ffffff'}); 
            $("#f_24_"+position).css({'background-color' : '#ffffff'}); 
            $("#f_29_"+position).css({'background-color' : '#ffffff'}); 
            $("#f_34_"+position).css({'background-color' : '#ffffff'}); 
            $("#f_39_"+position).css({'background-color' : '#ffffff'}); 
            $("#f_49_"+position).css({'background-color' : '#ffffff'}); 
            $("#f_50_"+position).css({'background-color' : '#ffffff'}); 
            $("#m_1_"+position).css({'background-color' : '#ffffff'}); 
            $("#m_9_"+position).css({'background-color' : '#ffffff'}); 
            $("#m_14_"+position).css({'background-color' : '#ffffff'}); 
            $("#m_19_"+position).css({'background-color' : '#ffffff'}); 
            $("#m_24_"+position).css({'background-color' : '#ffffff'}); 
            $("#m_29_"+position).css({'background-color' : '#ffffff'}); 
            $("#m_34_"+position).css({'background-color' : '#ffffff'}); 
            $("#m_39_"+position).css({'background-color' : '#ffffff'}); 
            $("#m_49_"+position).css({'background-color' : '#ffffff'}); 
            $("#m_50_"+position).css({'background-color' : '#ffffff'}); 
            
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

function loadRatios(){
         $("#ratios_data").html("<table border='0' class='display' id='example'><tr><td>Loading ratios...<img src='images/utube.gif' alt='.'></td></tr></table>");
        $.ajax({
        url:'LoadRatios',
        type:'post',
        dataType:'html',
        success:function (data){
               $("#example").html(data);
               $('#example').dataTable();
        }


        });  
         }
   </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>

