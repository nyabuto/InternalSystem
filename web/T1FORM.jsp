<%-- 
    Document   : T1FORM
    Created on : May 19, 2017, 10:06:06 AM
    Author     : GNyabuto
--%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>UTJ Training Form</title>
  <link rel="shortcut icon" href="images/imis.png"/>
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
   <link rel="stylesheet" href="select2/css/select2.css">
   
          
          <link href="media/dataTables/jquery.dataTables.css" rel="stylesheet" type="text/css" />
          <link href="scripts/dataTables.tableTools.css" rel="stylesheet" type="text/css" />
         <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="stylesheet" href="themes/base/jquery.ui.all.css">
        
<style>
.div_title {
    float: left;
    margin-right: 10px;
    width: 200px;
}
.div_data {
    float: left;
    margin-right: 40px;
    min-width: 140px;
}
.div_elem{
min-width: 300px;
}
.div_elem2{
min-width: 300px;
float: left;
}
.clear{
    clear: left;
    padding-top: 10px;
}
.div_new{
     float:  left;
    margin-bottom: 20px;
    margin-left: 20px;
}
.new_participant{
    margin-bottom: 50px;
}
input{
    width: 100px;
}
</style>

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
label {
    float: left;
    font-weight: bold;
    width: 400px;
}
.detailed-elems{
    text-align: left;
}
</style>
<style>
    
    
    .form-actions {
  
    padding: 4px 20px 4px;
}

tr > td
{
  padding-bottom: 1em;
}
</style>
  <%if(session.getAttribute("kd_session")!=null){%><%} else {  response.sendRedirect("logout");}%> 
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top" style="min-height: 700px;" >
   <!-- BEGIN HEADER -->
   <div class="header navbar navbar-inverse navbar-fixed-top">
      <!-- BEGIN TOP NAVIGATION BAR -->
      <div class="navbar-inner">
         <div class="container-fluid">
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
                    <p>Training Form</p>
                    </li>
                                      </ul>
               </div>
  
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
               <div class="span12">
                  <!-- BEGIN SAMPLE FORM PORTLET-->   

                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <form action="#" method="post" class="form-horizontal">
                            <h5 style='text-align: center;color: black;'> <b>USAID Tujenge Jamii Training Registration Form</b></h5>  
                         <br>
                         <div style="margin-left: 5%;">
                       
                         <div id="selector" style="float: left; width: 30%">
                         <b>Change Entry type :</b> <select name="entry_type" id="entry_type" style="width: 50%;"> 
                             <option value="1">New </option>   
                             <option value="2">Update Participants </option>   
                         </select>
                         </div>
                         
                             <div id="previous_summaries" style="float: right; width: 70%">
                             <b>Select Training :</b> <select name="summaries" id="summaries" style="width: 70%;" >   
                                 <option value="">Select Training</option>
                                 </select>
                             </div>
                             </div>
                         <br>
                         <br>
                         <div id="summary" style="clear: both;">
                             
                             </div>
                         <div id="detailed_existing"  style="clear: both;">
                             
                             
                             </div>
                        
                         
                        <div class="tabbable tabbable-custom boxless" id="demo"  >
                   
        
                     </div>

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
   
   
   <div class="modal fade" id="editor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
        <h4 class="modal-title" id="myModalLabel"><p style="text-align: center; font-weight: bolder;">Edit Participant Details</p></h4>
      </div>
      <div class="modal-body" id="toedit" style="font-size: 12px;">
    <table>
        <tr>
            <th>Participant Name : </th>
            <td> <input type="text" tabindex="13" placeholder="Participant Name" autocomplete="off" name="participant_name_1" list="participant_name_list" required max-length="50" id="participant_name_1" value=""  data-toggle="tooltip"  data-placement="right" style="width: 300px;"> </td>
        </tr>   
        <tr>
            <th>Gender : </th>
            <td><select tabindex="14"  placeholder="Choose Gender" name="gender_1" required id="gender_1"  data-toggle="tooltip"  data-placement="right"  style="width: 300px;"></select> </td>
        </tr>   
        <tr>
            <th>Cadre : </th>
            <td><input type="text"  placeholder="Cadre" tabindex="15" name="profession_1"  autocomplete="off" id="profession_1"  list="profession_list" value=""  data-toggle="tooltip"  data-placement="right"  style="width: 300px;"> </td>
        </tr>   
        <tr>
            <th>Personal Number : </th>
            <td><input type="text"  placeholder="Personal Number(MOH Staff only)" tabindex="16"  autocomplete="off" name="personal_no_1" list="personal_no_list" id="personal_no_1" value=""  data-toggle="tooltip"  data-placement="right"  style="width: 300px;"> </td>
        </tr>   
        <tr>
            <th>Name of Organization/Facility : </th>
            <td><input type="text"  placeholder="Name of organization/facility" tabindex="17"  autocomplete="off" name="organization_1" list="organization_list" id="organization_1" value=""  data-toggle="tooltip"  data-placement="right"  style="width: 300px;"> </td>
        </tr>   
        <tr>
            <th>Sub-county : </th>
            <td><select tabindex="18"  placeholder="Sub-county" name="district_1" required id="district_1"  autocomplete="off" data-toggle="tooltip" data-placement="right"  style="width: 300px;"></select> </td>
        </tr>   
        <tr>
            <th>Telephone Number : </th>
            <td><input type="text"  placeholder="Telephone No." tabindex="19" name="telephone_1"  autocomplete="off" id="telephone_1" list="telephone_list" value="" onkeypress="return numbers(event)"  data-toggle="tooltip"  data-placement="right"    style="width: 300px;"> </td>
        </tr>   
        <tr>
            <th>Address (Email or Postal) : </th>
            <td><input type="text"  placeholder="Address (Email/Postal)" tabindex="20" name="address_1"  autocomplete="off" list="address_list" id="address_1" value=""  data-toggle="tooltip"  style="width: 300px;"> </td>
        </tr>   
        
        
    </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn-info" data-dismiss="modal" style="height:30px;" id="update">Update</button>
      </div>
    </div>
  </div>
</div>
   
   <input type="hidden" id="tt" value="0"> 
   
   
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
   <script src="scripts/jquery-2.0.0.min.js"></script>    
   <script type="text/javascript" src="assets/ckeditor/ckeditor.js"></script>  
   <script src="assets/breakpoints/breakpoints.js"></script>       
   <script src="assets/bootstrap/js/bootstrap.min.js"></script>   
   <script type="text/javascript" src="assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
   <script src="assets/js/jquery.blockui.js"></script>
   <script src="assets/js/jquery.cookie.js"></script>
   <script src="daterangepicker/moment.min.js"></script> 
   
   <script type="text/javascript" src="js/bootstrap-notify.js"></script>

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

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<script type="text/javascript" src="js/noty/themes/default.js"></script>
 <script src="scripts/jquery.dataTables.js" type="text/javascript"></script>
<script src="scripts/jquery.dataTables.editable.js" type="text/javascript"></script>
<script src="scripts/jquery.jeditable.js" type="text/javascript"></script>
 <!--<script src="scripts/jquery-ui.js" type="text/javascript"></script>-->
 <script src="scripts/jquery.validate.js" type="text/javascript"></script>

<!-- <script src="scripts/dataTables.tableTools.js" type="text/javascript"></script>
 <script src="scripts/jquery.dataTables.columnFilter.js" type="text/javascript"></script>-->

 
 <script type="text/javascript" language="en">
   function numbers(evt){
        var charCode=(evt.which) ? evt.which : event.keyCode
        if(charCode > 31 && (charCode < 48 || charCode>57))
        return false;
        return true;
}
//-->
</script>
    <script>
    jQuery(document).ready(function(){
//        $("#entry_type").select2();
       $("#previous_summaries").hide();
       //load meetings
       loadMeetings();
        loadSummary(0); 
    $("#entry_type").change(function(){
     var  entry_type= $("#entry_type").val();
     if(entry_type==1){ // hide meetings
      $("#previous_summaries").hide();
      loadSummary(0);
     }
     else{ //show meetings
      $("#previous_summaries").show();    
     }
    });
    
    $("#summaries").change(function(){
     var  summary_id= $("#summaries").val();
     loadSummary(summary_id);
    });
    
    //submit form
//    $('#summarybtn').click(function(){
//        alert("called2");
//       
//    
//    
//    });
    
       }); 
       
    function subSummary(){
        var progam_area,districts,cordinator,agency,venue,curriculum_ids,date_range,training_name,s_male,s_female;
        progam_area = $("#progam_area").val();
        districts = $("#districts").val();
        cordinator = $("#cordinator").val();
        agency = $("#agency").val();
        venue = $("#venue").val();
        curriculum_ids = $("#curriculum_ids").val();
        date_range = $("#date_range").val();
        training_name = $("#training_name").val();
        s_male = $("#s_male").val();
        s_female = $("#s_female").val();
        if(districts!=null && progam_area!="" && cordinator!="" && agency!="" && venue!="" && curriculum_ids!=null && date_range!="" && training_name!=""){
        districts = districts+"*";
        curriculum_ids = curriculum_ids+"*";
       var form_data={"progam_area":progam_area,"districts":districts,"cordinator":cordinator,"agency":agency,"venue":venue,"curriculum_ids":curriculum_ids,"date_range":date_range,"training_name":training_name,"s_male":s_male,"s_female":s_female};
      
        var url='SaveSummary';
       
       $.post(url,form_data , function(data) {
        loadSummary(data);
        loadMeetings();
        $("#entry_type").html("<option value=\"1\">New </option> <option value=\"2\" selected>Update Participants </option>");
//        $("#entry_type")select2();
        $("#previous_summaries").show();
        
        if(data.trim()>0){
            $.notify(
      {icon: "images/checked.png", 
  message:'<font color="green">Summary Saved/Updated sucessfully.</font>'},
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
        }
        else{
        $.notify(
      {icon: "images/cross.png", 
  message:'<font color="red">Failed, similar detailed already exist.</font>'},
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
        }
        });
    }
    else{
    $.notify(
      {icon: "images/cross.png", 
  message:'<font color="red">Error, Enter all required fields.</font>'},
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
    }
        return false;
    }
    function loadMeetings(){
        $.ajax({
        url:'loadmeetings',
        type:"post",
        dataType:"html",
        success:function(data){
            $("#summaries").html(data);
            $("#summaries").select2();
          }
    });
    }
    function dates(){
//       $('#date_range').daterangepicker(
//        {
//    format: 'yyyy/MM/dd',
//    minDate: '2010/01/01',
//    maxDate: new Date()
//}
// ); 
$('#date_range').daterangepicker({
    "format": 'yyyy/MM/dd',
    "minDate": "2010/01/01",
    "maxDate": new Date(),
    "opens": "left"
}, function(start, end, label) {
  console.log("New date range selected: ' + start.format('yyyy-MM-dd') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')");
});
    }
    function loadSummary(summary_id){ 
           $.ajax({
        url:'LoadSummary?summary_id='+summary_id,
        type:"post",
        dataType:"html",
        success:function(data){
            $("#summary").html(data);
            $("#progam_area").select2();
            $("#districts").select2();
            $("#curriculum_ids").select2();
            $("#district").select2();
            dates();
        viewT1Details();

          }
    });    
    }
    
    function load_system_totals(){ 
           $.ajax({
        url:'Load_totals',
        type:"post",
        dataType:"json",
        success:function(data){
         var female = data.female;
         var male = data.male;
         $("#d_male").val(male);
         $("#d_female").val(female);
         }
    });    
    }
    function save_participants(){
       var form_data=$("#form_participant").serialize();
       var url='SaveParticipant';
       $.post(url,form_data , function(data) {
       $("#form_participant")[0].reset();
       load_system_totals();
        viewT1Details();
        });
    return  false;
    } 

     function  viewT1Details(){
         
      $("#detailed_existing").html("<fieldset class=\"formatter\" style=\"\"><legend class=\"formatter\"><b style=\"text-align:center;\">B. Participant's Details (optional)</b></legend><div class=\"tabbable tabbable-custom boxless\"><table cellpadding='4px' cellspacing='4px' style='padding-top: 1px; width:100%' border='0' class='display' id='T1Form'></table></div></fieldset>");
//      $("#detailed_existing").html("<table cellpadding='4px' cellspacing='4px' style='padding-top: 1px;' border='0' class='display' id='T1Form'><tr><td>Loading Participants...<img src='images/utube.gif' alt='.'></td></tr></table>");
      
        $.ajax({
        url:'LoadDetailed',
        type:"post",
        dataType:"html",
        success:function(data){
//        alert(data);
            $("#T1Form").html(data);
            $("#T1Form").dataTable({
              
              sUpdateURL: "updateT1",
              
              "dom": 'T<"clear">lfrtip',
                "tableTools": {
                "sSwfPath": "swf/copy_csv_xls_pdf.swf",
                "aButtons": [ {
                    "sExtends": "csv",
                    "sButtonText": "Save to csv"
                },
                {
                    "sExtends": "xls",
                    "sButtonText": "Save to xls"
                },
                {
                    "sExtends": "pdf",
                    "sButtonText": "Save to pdf"
                } ],
             "sRowSelect": "single"
            
        },
              
                "bProcessing": true,
                "sPaginationType": "full_numbers",
                "bJQueryUI": true
            }); 
       
          }
    }); 
    
  }
    
    function editor(id){
        $.ajax({
        url:'load_participant_details?id='+id,
        type:"post",
        dataType:"json",
        success:function(data){
         var participant_name = data.participant_name;
         var gender = data.gender;
         var profession = data.profession;
         var personal_no = data.personal_no;
         var organization = data.organization;
         var telephone = data.telephone;
         var address = data.address;
         var district = data.district;
         
         $("#participant_name_1").val(participant_name);
         $("#gender_1").html(gender);
         $("#profession_1").val(profession);
         $("#personal_no_1").val(personal_no);
         $("#organization_1").val(organization);
         $("#telephone_1").val(telephone);
         $("#address_1").val(address);
         $("#district_1").html(district);
       
//        
        // open the pop up window for editing
    }
    });
    }
    
       </script>
       <script>
    $(".readonly").keydown(function(e){
        e.preventDefault();
    });
</script>
   <!-- END JAVASCRIPTS -->  
       <script>
        jQuery(document).ready(function(){
        $("#update").click(function(){
          var participant_name = $("#participant_name_1").val();
          var  gender = $("#gender_1").val();
          var  profession = $("#profession_1").val();
          var  personal_no = $("#personal_no_1").val();
          var  organization = $("#organization_1").val();
          var  telephone = $("#telephone_1").val();
          var  address = $("#address_1").val();
          var  district = $("#district_1").val();
          var jobj={"participant_name":participant_name , "gender": gender, "profession": profession, "personal_no": personal_no, "organization": organization, "telephone": telephone, "address": address, "district": district};
          var str_data = JSON.stringify(jobj);
          $.ajax({
        url:'update_participant_details?participant_name='+participant_name+'&&gender='+gender+'&&profession='+profession+'&&personal_no='+personal_no+'&&organization='+organization+'&&telephone='+telephone+'&&address='+address+'&&district='+district,
        type:"post",
        dataType:"html",
        success:function(data){
         
         $.notify(
      { icon: "images/checked.png", 
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
         //reload participnt list  and totals
         viewT1Details();
         load_system_totals();
        }
          
        });
        });
        
            });
 
 function edit_totals(){
     var tt=$("#tt").val();
     if(tt=="0"){
    $("#s_female").prop("disabled", false);
    $("#s_male").prop("disabled", false);
    $("#edit_total").prop('src',"images/upload.png");
    $("#edit_total").prop('title',"Click to save updates.");
    $("#tt").val("1"); 
    
        }
        else{
      var male,female;
      male=$("#s_male").val();
      female=$("#s_female").val();
      
    $("#s_female").prop("disabled", true);
    $("#s_male").prop("disabled", true);
    $("#edit_total").prop('src',"images/edit.png");
    $("#edit_total").prop('title',"click to edit totals.");
    $("#tt").val("0"); 
    
    //update to the server
    $.ajax({
        url:'update_static_totals?male='+male+'&&female='+female,
        type:"post",
        dataType:"html",
        success:function(data){
         
         $.notify(
      { icon: "images/checked.png", 
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
        }
          
        });
    
        }
     
     
 }
    </script>
    
    <script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();
});
</script>
   </div>
</body>
<!-- END BODY -->
</html>