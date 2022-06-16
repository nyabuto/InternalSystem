<%-- 
    Document   : loadTBExcel
    Created on : Jul 27, 2015, 2:41:29 PM
    Author     : Maureen
--%>




<%@page import="General.IdGenerator2"%>
<%@page import="database.dbConn"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

   <html>

   <!-- BEGIN HEAD -->
   <head>
   <meta charset="utf-8" />
   <title>EMR Status</title>
   <link rel="shortcut icon" href="images/emr_status.png"/>
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
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-datepicker/css/datepicker1.css" />
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-timepicker/compiled/timepicker.css" />
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-colorpicker/css/colorpicker.css" />
   <link rel="stylesheet" href="assets/bootstrap-toggle-buttons/static/stylesheets/bootstrap-toggle-buttons.css" />
   <link rel="stylesheet" href="assets/data-tables/DT_bootstrap.css" />
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-daterangepicker/daterangepicker.css" />
   <link rel="stylesheet" type="text/css" href="assets/uniform/css/uniform.default.css" />
   <link rel="stylesheet" href="select2/css/select2.css">
   <link rel="stylesheet" href="css/animate.css">


                
<style>
                    
        [data-notify="progressbar"] 
        {
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
            <h2 style="text-align:center;font-size: 20px;color:white;padding-bottom:16px ;font-weight: bolder;">EMR Monthly Status</h2><br/>
            
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
         <%@include file="menu/menu.jsp" %>
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
                     <li style="width: 100%;">
                        <i class="icon-home"></i>
                        <a href="#" style="text-align: center;">Monthly EMR Status</a> 
                        <!--<span class="icon-angle-right"></span>-->
                     </li>
           
                  </ul>
               </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div ng-app="emrapp" ng-controller="emrcont" class="row-fluid">
               <div class="span12">
                  <!-- BEGIN SAMPLE FORM PORTLET-->   
                  <div class="portlet box blue">
                     <div class="portlet-title">
                        <h4><i class="icon-reorder"></i></h4>
                       Monthly EMR Status Form
                     </div>
                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <form id="monthlyEmrForm" action="saveEmrStatus" method="post" enctype="multipart/form-data" class="form-horizontal" >
                       
                           
                          
                            
                             <div class="control-group">
                              <label class="control-label">Reporting Period<required-field></required-field></label>
                              <div class="controls">
                       
                                  <select  onchange="setformId();getFacilities();" ng-model="period" id="yearmonth" name="yearmonth" required="true" >
                                 
                                      <option ng-repeat="y in yearmonth.periods" value="{{y.id}}">{{y.month+' '+y.year }}</option>
                                  
                                  </select> 
                                  <input value="" required=""  type="hidden" name="id" id='id'>  
                            </div>
                           </div>
                            
                  <div class="control-group">
                              <label class="control-label">Facility Name<required-field></required-field></label>
                              <div class="controls">
                       
                                  <select onchange="setformId();"  ng-model="facilityid"  id="facility_id" name="facility_id" required="true" >
                                 </select>     
                                    
                            </div>
                           </div>
                           
                            <div class="control-group">
                              <label class="control-label">EMR Version<required-field></required-field></label>
                              <div class="controls">
                       
                                  <select  id="emr_version" name="emr_version" required="true" >
                                  <option value="">Select Version</option>
                                  <option ng-repeat="es in emrversion" value="{{es}}">{{es}}</option>
                                      
                                  </select>      
                            </div>
                           </div>

                     <div class="control-group">
                              <label class="control-label">EMR Status<required-field></required-field></label>
                              <div class="controls">
                       
                                  <select onchange="is_tx_curr_emr_status();tx_curr_paper_status();" id="emr_status" name="emr_status" required="true" >
                                    <option value="">Select Status</option>
                                    <option ng-repeat="b in emrstatus" value="{{b}}">{{b}}</option>
                                     
                                      
                                  </select>      
                            </div>
                           </div>

                            
                             <div class="control-group">
                              <label class="control-label">Hard Disk for EMR data backup available?<required-field></required-field></label>
                              <div class="controls">
                       
                                  <select id="has_backup_disk" name="has_backup_disk" required="true" >
                                      <option value="">Select Status</option>
                                      <option ng-repeat="a in yesno" value="{{a}}">{{a}}</option>
                                    
                                    
                                      
                                  </select>      
                            </div>
                           </div>

                            
                            
                              <div class="control-group">
                              <label class="control-label"># of computers used for EMR & ADT<required-field></required-field></label>
                              <div class="controls">
                       
                                  <input onkeypress="return numbers(event);" maxlength="2" type='text' id="no_of_emr_adt_comps" name="no_of_emr_adt_comps" required="true" />
                                           
                            </div>
                            </div>
                            
                            
                     
                            <div class="control-group">
                              <label class="control-label">Is ADT Installed?<required-field></required-field></label>
                              <div class="controls">
                       
                                  <select onchange="adt_version_status();" id="has_adt" name="has_adt" required="true" >
                                      <option value="">Select Status</option>
                                        <option ng-repeat="a in yesno" value="{{a}}">{{a}}</option>                                 
                                  </select>      
                            </div>
                           </div> 
                            
                           
                            <div class="control-group adt_version" style="display:none;">
                              <label  class="control-label ">ADT Version<required-field></required-field></label>
                              <div class="controls">
                       
                                  <select id="adt_version" name="adt_version"  >
                                      <option value="">Select Version</option>
                                      <option ng-repeat="p in adtversion" value="{{p}}">{{p}}</option>
                                                                      
                                  </select>      
                            </div>
                           </div>
                            
                            
                            
                             <div class="control-group">
                              <label class="control-label">Reliable Power backup available?<required-field></required-field></label>
                              <div class="controls">
                       
                                  <select id="has_power_backup" name="has_power_backup" required="true" >
                                      <option value="">Select Status</option>
                                         <option ng-repeat="a in yesno" value="{{a}}">{{a}}</option>                                 
                                  </select>      
                            </div>
                           </div> 
                            
                           <div  class="control-group">
                              <label class="control-label">Site Reported TX_Curr for this month using EMR?<required-field></required-field></label>
                              <div class="controls">
                       
                                  <select id="is_tx_curr_emr" name="is_tx_curr_emr" required="true" >
                                      <option value="">Select Status</option>
                                       <option ng-repeat="a in yesno" value="{{a}}">{{a}}</option>                                  
                                  </select>      
                            </div>
                           </div> 
                            
                            
                             <div  style="display:none;" class="control-group tx_curr_paper">
                              <label class="control-label">Current On ART MOH731 Paper <required-field></required-field></label>
                              <div class="controls">
                       
                                  <input onkeypress="return numbers(event);" maxlength="5" type='text' id="tx_curr_paper" name="tx_curr_paper" />
                                           
                            </div>
                            </div>
                            
                              <div class="control-group">
                              <label class="control-label">Current On ART EMR<required-field></required-field></label>
                              <div class="controls">
                       
                                  <input onkeypress="return numbers(event);" maxlength="5" type='text' id="tx_curr_emr" name="tx_curr_emr"  />
                                           
                            </div>
                            </div>
                                                        
                            
                            <div class="control-group">
                              <label class="control-label">Has the site dropped the use of CCC DAR (MOH 366)?<required-field></required-field></label>
                              <div class="controls">
                       
                                  <select id="site_dropped_ccc_dar" name="site_dropped_ccc_dar" required="true" >
                                      <option value="">Select Status</option>
                                        <option ng-repeat="a in yesno" value="{{a}}">{{a}}</option>                                 
                                  </select>      
                            </div>
                           </div>  
                            
                            
                            <ul class="breadcrumb" style="background:#4b8df8;color:white;">
                     <li >
                        
                      Do the following service delivery points have EMR?
                        <!--<span class="icon-angle-right"></span>-->
                     </li>
           
                  </ul>
                          
                              <div class="control-group">
                              <label class="control-label">HIV Testing Services<required-field></required-field></label>
                              <div class="controls">
                       
                                  <select id="hts_emr" name="hts_emr" required="true" >
                                      <option value="">Select Status</option>
                                         <option ng-repeat="a in yesno" value="{{a}}">{{a}}</option>                                 
                                  </select>      
                            </div>
                           </div>  
                            
                             
                             
                              <div class="control-group">
                              <label class="control-label">Care and Treatment<required-field></required-field></label>
                              <div class="controls">
                       
                                  <select placeholder=" (Includes Pediatric and Adolescent Care and Treatment services)" id="art_emr" name="art_emr" required="true" >
                                      <option value="">Select Status</option>
                                        <option ng-repeat="op in yesno" value='{{op}}'>{{op}}</option>                                  
                                  </select>      
                            </div>
                           </div> 
                             
                             
                              <div class="control-group">
                              <label class="control-label">Antenatal Care and/or Maternity Services<font color='red'><b>*</b></font></label>
                              <div class="controls">
                       
                                  <select placeholder=" (Includes Pediatric and Adolescent Care and Treatment services)" id="anc_emr" name="anc_emr" required="true" >
                                      <option value="">Select Status</option>
                                         <option ng-repeat="a in yesno" value="{{a}}">{{a}}</option>                                 
                                  </select>      
                            </div>
                           </div>
                             
                             
                              <div class="control-group">
                              <label class="control-label">Early Infant Diagnosis and/or Under Five Clinic (not Pediatric ART Services)<font color='red'><b>*</b></font></label>
                              <div class="controls">
                       
                                  <select placeholder="Early Infant Diagnosis and/or Under Five Clinic (not Pediatrict ART Services)" id="eid_emr" name="eid_emr" required="true" >
                                      <option value="">Select Status</option>
                                         <option ng-repeat="a in yesno" value="{{a}}">{{a}}</option>                                  
                                  </select>      
                            </div>
                           </div>
                             
                                <div class="control-group">
                              <label class="control-label">HIV / TB Services<font color='red'><b>*</b></font></label>
                              <div class="controls">
                       
                                  <select placeholder="Early Infant Diagnosis and/or Under Five Clinic (not Pediatrict ART Services)" id="tb_emr" name="tb_emr" required="true" >
                                      <option value="">Select Status</option>
                                         <option ng-repeat="a in yesno" value="{{a}}">{{a}}</option>                                 
                                  </select>      
                            </div>
                           </div>
                            

                            
                            
                            
                            
                            
                             <div class="control-group">
                              <label class="control-label">Comments</label>
                              <div class="controls">
                                  <textarea rows='2'  id="comments" name="comments"  ></textarea>       
                            </div>
                           </div> 
                            
                         
                          
                        <table style="width: 100%;">
                           <tr>
<!--                               <td class="col-xs-2">
                            <div class="form-actions">
                              <button type="submit" class="btn blue">Generate Report Excel.</button>

                           </div>
                                   </td>-->
                                   
                                   <td class="col-xs-10">
                           <div class="form-actions">
                             
                         
                              
                               <input type="submit" id="submitdata" name='submitdata' class="btn green" value='Submit data' />
                          

                         
                           </div>
                                   </td>
                            </tr> 
                         </table>
                        <img src='images/ajax_loader.gif' alt='loading' style="display:none; margin-left:30% ;" class='loading'/>
                                        
                        <div class="form-actions" id="matokeo">
                        <div class="form-actions">
                            
                        </div>
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
    
     
     <h4 class="portlet-title" style="text-align: center;color:black;"> &copy; USAID Tujenge Jamii | USAID  Host Name :<b><i> </i></b> &nbsp;   Database Name :<i> </i></h4>
      <div class="span pull-right">
         <span class="go-top"><i class="icon-angle-up"></i></span>
      </div>
   </div>
   <!-- END FOOTER -->
   <!-- BEGIN JAVASCRIPTS -->    
   <!-- Load javascripts at bottom, this will reduce page load time -->
   
<script src="assets/js/jquery-1.8.3.min.js"></script>
   

<script type="text/javascript" src="js/bootstrap-notify.js"></script>

 <script type="text/javascript" src="js/jquery.fileDownload.js"></script>
      
   
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
   <script src="js/angular.js"></script>
   <script src="js/angular_options.js"></script>
  
     


<script>
      
function adt_version_status()
{

var vl=$('#has_adt').val();

if(vl==='Yes')
{
$(".adt_version").show();
   $('#adt_version').attr('required', true);
// $('#adt_version').removeAttr('required');
}
else 
{
 $(".adt_version").hide();   
 $("#adt_version").val("");   
  
            
   $('#adt_version').removeAttr('required');


            
}

}   
 
  adt_version_status();
  
    
    
    
  function is_tx_curr_emr_status()
{
    
var vl=$('#emr_status').val();


if(vl==='POC')
{
$("#is_tx_curr_emr").val("Yes");
}


console.log(" emr function called "+vl);
} 
  
  
  
  
 
  

//tx_curr_paper  depends on emr_status  POC
  
 function tx_curr_paper_status()
{

var vl=$('#emr_status').val();

if(vl==='POC')
{
    
 $(".tx_curr_paper").hide();   
 $("#tx_curr_paper").val("");
// $('#tx_curr_paper').attr('required', true);
$('#tx_curr_paper').removeAttr('required');
}
else 
{
  $(".tx_curr_paper").show();  
  $('#tx_curr_paper').attr('required', true);
//$('#tx_curr_paper').removeAttr('required'); 
    
}

} 
  tx_curr_paper_status();
  
  
  
    
      $(".tarehe").datepicker({
    clearBtn: true,format: "yyyy-mm-dd"
}).on('changeDate', function(ev){
    $(this).datepicker('hide');
});
      
      
      
     
function getReport()
{
    
    
  
   var exelend=$("#weekend").val();
  
        
        
   //end date
       if (exelend==='')
     {
         
     alert('Select report ending date');
   $("#enddaterpt").focus();    
     } 

                
                  
                
                else {
                    //call the report generation page
                 downloadrpt(exelend) ;  
                    
                }
        
    
}



  function downloadrpt(enddate){
      
                $('.loading').show();
                $('#generaterpt').hide();
               var urel="Missing_VL_KenyaEMR";
                //?startdate=" + startdate + "&enddate=" + enddate + "&cbos=" + cbos
             
                var ur=urel+"?enddate=" + enddate;
                  console.log(ur);
                $.fileDownload(ur).done(function () { $('.loading').hide(); $('#generaterpt').show(); $('#generaterpt').html("<i class='glyphicon glyphicon-ok'></i> Report Generated"); }).fail(function () { alert('Report generation failed, kindly try again!'); $('.loading').hide(); $('#generaterpt').show(); });
 
                //$('.loading').hide();
            }




function selectoutput(){
    
    
    var outputii=$("#output").val();
    
   
    
}
selectoutput();



   
 function getPeriod(){
       
   var sec=$("#section").val();
       
       //yearmonth
              $.ajax({
                         url:'getPeriod?per=yes',                            
                    type:'post',  
                    dataType: 'json',  
                    success: function(data) {                        
                       
        var dat=data.periods;
        
      
        var o="";
                        
                        for(var a=0;a<dat.length;a++)
                        {                           
                     
                          o+="<option value='"+dat[a].id+"'>"+dat[a].year+" "+dat[a].month+"</option>"; 
                          
                          if(a===(dat.length)-1){
                              
                                   //getFacilities();
                          }
                          
                        }
                        
                   $("#yearmonth").html(o);
                 
                        
                        
                    }});
                
                
           
                            
             
   
   }
   
   




function getFacilities()  
{
    var ym=202003;
     
     //.find(":selected").text();
     //ym=$("#yearmonth").find(":selected").text();
    ym=document.getElementById("yearmonth").value;
    
$.ajax({  url:'loadEMRFacilities?yrm='+ym,                            
                    type:'post',  
                    dataType: 'json',  
                    success: function(data)
                    { 
                        
                    
                        console.log("mwaka:"+ym);
                        var fcs=data.facilities;
                        
                        var facillist="<option data-county='' data-subcounty='' value=''>select Faciity</option>";
                        
                    for(var a=0;a<fcs.length;a++)
                    {
                        
                        var mn=fcs[a];
                        
                           
            facillist+="<option data-county='"+mn.county+"' data-subcounty='"+mn.subcounty+"' value='"+mn.mflcode+"'>"+mn.facility_name+" ["+mn.county+"]</option>";
                           //console.log("a "+mn.facility_name); 
                            
                        }
                        
                      $("#facility_id").html(facillist);
                      $("#facility_id").select2();
                        
                    }
                    
    
    }) ;  
    
    
 }
 
  $(document).ready(function() {
                  getFacilities();
                                 });
 
 
 

           function   numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode;
if(charCode > 31 && (charCode < 48 || charCode>57)){
return false;
}

else{
return true;
}
}

function setformId()
{
    
    var period=$("#yearmonth").val();
    var facility=$("#facility_id").val();
    var ii=period+"_"+facility;
   $("#id").val(ii); 
   if(facility!=='' && period!==''){
       
    //pull data if existing        
            retrieveSavedData(ii);
   }
   
    console.log(" id ni "+ii);
}


//load saved data

function retrieveSavedData(__id)
{    
    $.ajax({
        url:'retrieveSavedData?id='+__id,
        dataType:'json',
        type:'post',
        success:function(data)
        {          
        
          var dt=data.emr_data[0];
          
         // var elems=get_all_data_elements();
  
          if (dt.id==='0')
          {  
           resetFormFields("");   
          }
          else 
          {
              
              Object.keys(dt).forEach(function(key)
              {
    var value = dt[key];
    $("#"+key).val(value);
    
    //console.log(key + ':' + value);
});
              
      adt_version_status();
 tx_curr_paper_status();
              
          }
         
            
        }
    });
    
    


}



function get_all_data_elements()
{
         
  
  var x = $('#monthlyEmrForm').serializeArray();
  var allelements=[];
  $.each(x, function(i, field)
  {
      allelements.push(field.name);
 
  });

        
 return allelements;   
    
    
}

function resetFormFields()
{
    
get_all_data_elements().forEach(function(item,index)
{
if(item!=='id' && item!=='facility_id' && item!=='yearmonth') {
    
    $("#"+item).val("");
}  
    
});
    
    
    
}


//console.log(get_all_data_elements());
      
   </script>

                  
 

     

  
   
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>


