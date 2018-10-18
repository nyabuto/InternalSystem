<%-- 
    Document   : Form731
    Created on : May 11, 2015, 10:09:28 AM
    Author     : Maureen
--%>

<%@page import="ppprev.getAttendance"%>
<%@page import="ppprev.getParticipants"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <!-- BEGIN HEAD -->
    <head>
        <meta charset="utf-8" />
        <title>PP_Prev</title>
        <link rel="shortcut icon" href="images/index.JPG"/>
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <link href="assets/css/metroprev.css" rel="stylesheet" />
        <link href="assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
        <link href="assets/bootstrap-fileupload/bootstrap-fileupload.css" rel="stylesheet" />
        <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link href="assets/css/style.css" rel="stylesheet" />
        <link href="assets/css/style_responsive.css" rel="stylesheet" />
        <link href="assets/css/style_default.css" rel="stylesheet" id="style_color" />

        <link rel="stylesheet" type="text/css" href="assets/bootstrap-wysihtml5/bootstrap-wysihtml5.css" />
        <!--<link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker.min.css" />-->
        <!--<link rel="stylesheet" type="text/css" href="assets/bootstrap-datepicker/css/datepicker_1.css" />-->


        <link rel="stylesheet" href="dataTables/css/jquery.dataTables.css" />

        <link rel="stylesheet" type="text/css" href="assets/uniform/css/uniform.default.css" />

        <link href="assets/bootstrap-wizard/prettify.css" rel="stylesheet">
        
         <link rel="stylesheet" href="select2/css/select2.css"/>
         <link rel="stylesheet" href="css/jquery-ui.css"/>
         
<style type='text/css'>
input:focus {
    border-color: red;
    }
    .control-group .select2-container {
  position: relative;
  z-index: 2;
  float: left;
  width: 100%;
  margin-bottom: 0;
  display: table;
  table-layout: fixed;
}

.datepicker-container .datepicker-dropdown .datepicker-top-left 
{
   z-index: 32767 !important;
}

/* Important part */
/*.modal-dialog{
    overflow-y: initial !important
}
.modal-body {
    height: 80%;
    overflow-y: scroll;
}*/


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
                            <h5 class="page-title" style="text-align: center;">
<div id="ujumbempya"> 
                                                
                                                <%if(session.getAttribute("msg2")!=null){out.println(session.getAttribute("msg2"));}%>
                                                
                                            </div>

                            </h5>
                            <ul class="breadcrumb">
                                <li>
                                    <i class="icon-check"></i>
                                    <font color="#4b8df8"></font>

                                </li>

                            </ul>
                        </div>
                    </div>
                    
                    
                    
                    
                    
                    
                    
                    <!------------------------------------Accordion End---------------------------------------->
                    
                   
 
<div id="accordion">


  <h3 style="text-align: center;"> <span class='badge badge-warning'>1</span> PP PREV 1 GROUP ATTENDANCE REGISTER</h3>
  <!----Start accordion div---->
  <div style="height:100%;">
    
<!--      body 1-->





 
 
 
                    
                    <!------------------------------------Accordion End---------------------------------------->
                    
                    
                    
                    <!-- END PAGE HEADER-->
                    
                    <!-- BEGIN PAGE CONTENT-->
                    <div class="row-fluid">
                        <div class="span12">
                            <!-- BEGIN SAMPLE FORM PORTLET-->   
                            <div class="portlet box blue">
                                <div class="portlet-title">
                                    <h4><i class="icon-reorder"></i>  <a style='margin-left: 100px;' onclick="setGroupDefaults();"  class='btn btn-default'  data-toggle='modal' href='#addgroup'><i class='icon-plus'></i>Add Group</a>     <a onclick='setFacilitatorDefaults();' class='btn btn-default' style='margin-left: 100px;'  data-toggle='modal' href='#addfacilitator'><i class='icon-plus'></i>Add Facilitator</a>
                                    <a  class='btn btn-default' style='margin-left: 100px;'  data-toggle='modal' href='#editattendance'><i class='icon-plus'></i>Edit Attendance</a>
                                    <a  class='btn btn-default' style='margin-left: 100px;'  data-toggle='modal' href='#rawdata'><i class='icon-plus'></i>Raw Data</a>
                                    <a id="servicesbutton" class='btn btn-default' style='margin-left: 100px;display:none;'  data-toggle='modal' href='#hcservicesmodal'><i class='icon-plus'></i>Launch Services</a></h4>
                                    <div class="tools">
                                        <a href="javascript:;" class="collapse"></a>
                                        <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                        <a href="javascript:;" class="reload"></a>
                                        <a href="javascript:;" class="remove"></a>
                                    </div>
                                </div>
                                <div class="portlet-body form">
                                    <!-- BEGIN FORM-->




                                    <div  class="form-inline">





                                        <div id="rootwizard" class="tabbable tabs-left">



                                        </div>   <!--end of root wizard-->



                                        <div class="form-actions">
                                            <!--<button type="submit" class="btn blue">Go to Form</button>-->
                                            <!--                              <button type="button" class="btn">Cancel</button>-->
                                            <div id="ujumbe">
                                                
                                                <%if(session.getAttribute("msg")!=null){out.println(session.getAttribute("msg"));}%>
                                                
                                            </div>
                                            
                                        </div>
                                    </div>
                                    <!-- END FORM-->           
                                </div>





                            </div>
                            <!-- END SAMPLE FORM PORTLET-->
                        </div>
                    </div>


<div class="modal fade" id="addgroup" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button"  class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" style='text-align: center;'><span id='addgroupmsg' style='text-align: right;'></span></h4>
            </div>
            <div class="modal-body">
                <form  id="groupsform" method="post">
                               
                    <!------------------------------------------County-------------------------------------------------------->
                       <div class="control-group" style='display:none;' >
                                    <label>County</label>
                                    <div class="controls">
                                        <select  name="rpt_county" id="rpt_county" style="width:100%;" class="form-control">
                                            <option value="">Select County (optional)</option>
                                             <option value="Baringo">Baringo</option>
                                             <option value="Kajiado">Kajiado</option>
                                             <option value="Laikipia">Laikipia</option>
                                             <option value="Nakuru">Nakuru</option>
                                             <option value="Narok">Narok</option>
                                              <option value="Samburu">Samburu</option>
                                             <option selected value="Turkana">Turkana</option>
                                            
                                           
                                        </select>
                                    </div>
                                </div>
                    
                    <!------------------------------------------SubCounty-------------------------------------------------------->  
                    
                      <div class="control-group" >
                                    <label>Sub county</label>
                                    <div class="controls" >
                                        <select onchange='getWard("subcountyname","");'   name="subcountyname" id="subcountyname" style="width:100%;" class="form-control">
                                           
                                             <!--<option title="From 1st October of the selected date year to the end date specified inside the same date year " value="excelreport_cumulative">Cumulative</option>-->
                                       
                                        </select>
                                    </div>
                                </div>
                    
                    
                     <div class="control-group" >
                                    <label>Ward</label>
                                    <div class="controls" >
                                        <select  name="ward" id="ward" style="width:100%;" class="form-control">
                                           
                                             <option value="">Select Subcounty first</option>
                                       
                                        </select>
                                    </div>
                                </div>
                    
                    
                    <div class="control-group" >
                                    <label>Implementing Partner</label>
                                    <div class="controls" >
                                        <select onchange='getPopulation();'  name="partnername" id="partnername" style="width:100%;" class="form-control">
                                           
                                             <!--<option title="From 1st October of the selected date year to the end date specified inside the same date year " value="excelreport_cumulative">Cumulative</option>-->
                                       
                                        </select>
                                    </div>
                                </div>
                     <div class="control-group" >
                                    <label>Target Population</label>
                                    <div class="controls" >
                                        <select  name="targetpopname" id="targetpopname" style="width:100%;" class="form-control">
                                           
                                             <!--<option title="From 1st October of the selected date year to the end date specified inside the same date year " value="excelreport_cumulative">Cumulative</option>-->
                                       
                                        </select>
                                    </div>
                                </div>
                    
                    
                     <div class="control-group" >
                                    <label><font color="red"><b>*</b></font>Group Name</label>
                                    <div class="controls">
                                        <input type="text" maxlength="50"  name ="groupname" id="groupname" style="width:100%;"  class="form-control" >
                                    </div>
                                </div> 
                    
                    
                 
                                <div class="control-group">
                                    
                                    <div class="controls">
                                        <button onclick="saveGroup();" id="savegroupbtn"   style="margin-left: 30%;"  class="btn-lg btn-success ">
                                            Save
                                        </button>
                                        
                                        <img src='images/ajax_loader.gif' alt='loading' style="display:none; margin-left:30% ;" class='loading'/>
                                        
                                    </div>
                                </div>   
                    
                </form>
            </div>
            <div class="modal-footer">
                <a href="#" data-dismiss="modal" class="btn">Close</a>
              
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dalog -->
</div>


                    
                    
                    
                    
<div class="modal fade" id="addfacilitator">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button"  class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" style='text-align: center;'><span id='addfacilitatormsg' style='text-align: right;'></span></h4>
            </div>
            <div class="modal-body">
                <form  id="facilitatorform" method="post">
                               
                    <!------------------------------------------Fname-------------------------------------------------------->
                         <div class="control-group" >
                                    <label><font color="red"><b>*</b></font>First Name</label>
                                    <div class="controls">
                                       <input type="text"  name ="first_name" id="first_name" style="width:100%;"  class="form-control" >
                                    </div>
                                </div> 
                    
                    <!------------------------------------------Mname-------------------------------------------------------->  
                    
                        <div class="control-group" >
                                    <label><font color="red"><b>*</b></font>middle Name</label>
                                    <div class="controls">
                                       <input type="text"  name ="middle_name" id="middle_name" style="width:100%;"  class="form-control" >
                                    </div>
                                </div> 
                    
                      <!------------------------------------------sname-------------------------------------------------------->  
                    
                        <div class="control-group" >
                                    <label><font color="red"><b>*</b></font>Last Name</label>
                                    <div class="controls">
                                       <input type="text"  name ="sur_name" id="sur_name" style="width:100%;"  class="form-control" >
                                    </div>
                                </div> 
                    
                        <!------------------------------------------phoneno-------------------------------------------------------->  
                    
                        <div class="control-group" >
                                    <label><font color="red"><b></b></font>Phone Number</label>
                                    <div class="controls">
                                        <input type="tel" maxlength="10" placeholder='07XX XXX XXX' name ="phone" id="phone" style="width:100%;"  class="form-control" >
                                    </div>
                                </div> 
                      
                    <div class="control-group" >
                                    <label>Sub County</label>
                                    <div class="controls" >
                                        <select onchange='getGroup("ward","");'  name="subcountynamed" id="subcountynamed" style="width:100%;" class="form-control">
                                           
                                             <!--<option title="From 1st October of the selected date year to the end date specified inside the same date year " value="excelreport_cumulative">Cumulative</option>-->
                                       
                                        </select>
                                    </div>
                                </div>
                     <div class="control-group" >
                                    <label>Group Name</label>
                                    <div class="controls" >
                                        <select  name="groupnamed" id="groupnamed" style="width:100%;" class="form-control">
                                           
                                             <!--<option title="From 1st October of the selected date year to the end date specified inside the same date year " value="excelreport_cumulative">Cumulative</option>-->
                                       
                                        </select>
                                    </div>
                                </div>
                    
                    
                  
                    
                    
                 
                                <div class="control-group">
                                  
                                    <div class="controls">
                                        <button onclick="saveFacilitator();" id="savefacilbtn"   style="margin-left: 30%;"  class="btn-lg btn-success ">
                                            Save
                                        </button>
                                       
                                        <img src='images/ajax_loader.gif' alt='loading' style="display:none; margin-left:30% ;" class='loading'/>
                                        
                                    </div>
                                </div>   
                    
                </form>
            </div>
            <div class="modal-footer">
                <a href="#" data-dismiss="modal" class="btn">Close</a>
              
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dalog -->
</div>
                    
                    

                                                
                                                
 <!----------------------------------------------------------------------------Attendance-------------------------------------------------------------->





<div class="modal fade" id="editattendance" >
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button"  class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" style='text-align: center;'><span  style='text-align: right;'></span></h4>
            </div>
            <div class="modal-body">
                <form  id="" method="post">
                               
                    
                    
                   
           <div id="editattendancediv"></div>         
                    
                    
              
                 
                                  
                    
                </form>
            </div>
            <div class="modal-footer">
                <a href="#" data-dismiss="modal" class="btn">Close</a>
              
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dalog -->
</div>


 </div>
  
  
  
  <h3 style="text-align: center;" id="participantsaccordion"><span class='badge badge-warning'>2</span> PP PPREV SERVICES</h3>
  <div>
    
      
      <div id="hcservices"><h3 style="text-align: center;"><img src="images/ajax_loader.gif"/></h3></div>
      
      
  </div>
  
  
  
  
  
  
</div><!-----end of accordion----->

 <!----------------------------------------------------------------------------Attendance-------------------------------------------------------------->
 <!----------------------------------------------------------------------------services modal-------------------------------------------------------------->
 
 



<div class="modal fade" id="hcservicesmodal" >
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" id='servicesclosebtn' class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" style='text-align: center;'><span  style='text-align: right;'></span></h4>
            </div>
            <div class="modal-body">
                
                               
                    
                    
                   
           <div id="servicesdiv" style='overflow-y:scroll;height:600px;'><label>Loading services</label></div>         
                    
                    
              
                 
                                  
                    
              
            </div>
            <div class="modal-footer">
                <a href="#" data-dismiss="modal" class="btn">Close</a>
              
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dalog -->
</div>
 <!----------------------------------------------------------------------------Attendance-------------------------------------------------------------->
                                                
      
 
  <!----------------------------------------------------------------------------services modal-------------------------------------------------------------->
 
 



<div class="modal fade" id="rawdata" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" id='' class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" style='text-align: center;'><span  style='text-align: right;'></span></h4>
            </div>
            <div class="modal-body">
                
                               
                    
                  <table>  <tr><th>Element</th><th>Value</th></tr>
                   
                
                   
                                 <tr><td>   <label><font color="red"><b>*</b></font>Report Start Date</label></td>
                                     <td>
                                    <div class="controls">
                                       <input type="text"  name ="sdate" id="sdate"  class="form-control tarehe1" readonly placeholder="e.g yyyy-mm-dd">
                                    </div>
                                     </td></tr>
                               
                     
                                   <tr><td> <label><font color="red"><b>*</b></font>Report End Date</label></td><td>
                                    
                                    <div class="controls">
                                       <input type="text"  name ="edate" id="edate"  class="form-control tarehe1" readonly placeholder="e.g yyyy-mm-dd">
                                    </div></td></tr>
                              
                    
                    
                     
                    
                  <tr><td colspan='2'><div class="control-group">
                                    
                                    <div class="controls">
                                        <button onclick="getreport();" id="excelreportbtn"   style="margin-left: 30%;"  class="btn-lg btn-success ">
                                            Generate
                                        </button>
                                        
                                      <img src='images/ajax_loader.gif' alt='loading' style="display:none; margin-left:30% ;" class='loading1'/>
                                        
                                    </div>
                                </div> </td></tr>   
              
              </table>   
                                  
                    
              
            </div>
            <div class="modal-footer">
                <a href="#" data-dismiss="modal" class="btn">Close</a>
              
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dalog -->
</div>
 <!----------------------------------------------------------------------------reports-------------------------------------------------------------->
 
 
                                                

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
        <!--<script src="assets/js/jquery-1.8.3.min.js"></script>-->    
        <script src="js/jquery-1.12.4.js"></script>    
        <script src="js/jquery-ui.js"></script>    

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
        <!--<script type="text/javascript" src="assets/uniform/jquery.uniform.min.js"></script>-->
        <script type="text/javascript" src="assets/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script> 
        <script type="text/javascript" src="assets/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
        <script type="text/javascript" src="assets/jquery-tags-input/jquery.tagsinput.min.js"></script>

        <script type="text/javascript" src="assets/clockface/js/clockface.js"></script>

        <script src="assets/bootstrap-wizard/jquery.bootstrap.wizard.js"></script>
        <script src="assets/bootstrap-wizard/prettify.js"></script>
        <script src="dataTables/js/jquery.dataTables.js"></script>
         <script type="text/javascript" src="js/jquery.fileDownload.js"></script>

        <!--<script src="js/bootstrap-datepicker.min.js"></script>-->
        <!--<script src="assets/bootstrap-datepicker/js/datepicker.js"></script>-->
 <script src="select2/js/select2.js"></script>
        <!--<script src="assets/js/app.js"></script>-->     
        <script>

  var counter=null;

            jQuery(document).ready(function () {
                // initiate layout and plugins

 



               





                $.ajax({
                    url: 'loadwizard',
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {
                        $("#rootwizard").html("<form id='pprevform'>"+data+"</form>");
                       // $("#rootwizard").html(data);





                        $('#rootwizard').bootstrapWizard({
                            onTabClick: function (tab, navigation, index) {
                                return false;
                            },
                            'tabClass': 'nav nav-tabs',
                            onTabShow: function (tab, navigation, index) {
                                var $total = navigation.find('li').length;
                                var $current = index + 1;
                                var $percent = ($current / $total) * 100;
                                $('#rootwizard').find('.bar').css({width: $percent + '%'});

                                // If it's the last tab then hide the last button and show the finish instead
                                if ($current >= $total) {
                                    $('#rootwizard').find('.pager .next').hide();
                                    $('#rootwizard').find('.pager .finish').show();
                                    $('#rootwizard').find('.pager .finish').removeClass('disabled');
                                } else {
                                    $('#rootwizard').find('.pager .next').show();
                                    $('#rootwizard').find('.pager .finish').hide();
                                }

                            },
                            onNext: function(tab, navigation, index) {
                                
			if(index===1) {
				// Make sure we entered the name
				if($('#partner').val()==="") {
					alert('Select partner');
					$('#partner').focus();
					return false;
				}
                                
                                
                                else if($('#subcounty').val()==="") {
					alert('Select sub-county');
					$('#subcounty').focus();
					return false;
				}
                                
                                 else if($('#wardid').val()==="") {
					alert('Select Ward');
					$('#wardid').focus();
					return false;
				}
                                
                                
                                else if($('#targetpop').val()==="") {
					alert('Select Target Population');
					$('#targetpop').focus();
					return false;
				}
                                else if($('#curriculum').val()==="") {
					alert('Select curriculum');
					$('#curriculum').focus();
					return false;
				}
                                
                                else if($('#group').val()==="") {
					alert('Select group');
					$('#group').focus();
					return false;
				}
                                else if($('#lessons').val()==="") {
					alert('Select lessons');
					$('#lessons').focus();
					return false;
				}
                                else if($('#facilitator').val()==="") {
					alert('Select facilitator');
					$('#facilitator').focus();
					return false;
				}
                                else if($('#startdate').val()==="") {
					alert('Select start date');
					$('#startdate').focus();
					return false;
				}
                                  else if($('#enddate').val()==="") {
					alert('Select end date');
					$('#enddate').focus();
					return false;
				}
                                
                                
                                 else  if(Date.parse($('#startdate').val()) > Date.parse($('#enddate').val())){
                    alert("Start date cannot be greater than end date.");   
                    $("#enddate").focus();  
                    return false;
                                }
                                
                                  else if($('#agegroup').val()==="") {
					alert('Select age group');
					$('#agegroup').focus();
					return false;
				}
                                
                                else {
                                    
                                    return true;
                                }
			
        
        
        }// end of tab 1 validations
                        
                        
                        
                        
                        
                        //------------------------tab 2 validations---------------
     
        
        else if(index===2) {
            
            
            var masomo=7;
            if($("#lessons").val()!==''){
             masomo=$("#lessons").val();
         }
            
				for(var a=1;a<=masomo;a++){
                                    
                                 //console.log(" loop number _ "+a);   
                                    //6
                                    if($('#topics'+a).val()==="" && ($('#methods'+a).val()===null || $('#methods'+a).val()==="" ) && $('#sessiondate'+a).val()==="" && $('#time'+a).val()==="" && $('#malecondom'+a).val()==="" && $('#femalecondom'+a).val()===""){
                                        
                                     alert('All columns should be filled with values');  
                                     $('#topics'+a).focus();
                                     return false;
                                        break;
                                    }
                                    
				else if($('#topics'+a).val()!=="" || ($('#methods'+a).val()!==null && $('#methods'+a).val()!=="" ) || $('#sessiondate'+a).val()!=="" || $('#time'+a).val()!=="" || $('#malecondom'+a).val()!=="" || $('#femalecondom'+a).val()!=="" )
                                {
			console.log(" topic:"+$('#topics'+a).val()+"  method:"+$('#methods'+a).val()+"  date:"+$('#sessiondate'+a).val()+" time:"+ $('#time'+a).val()+"  malecondom:"+$('#malecondom'+a).val()+"  femalecondom:"+$('#femalecondom'+a).val());		
                     if($('#topics'+a).val()===""){
                                
                                alert('Select topic taught during the session');
				$('#topics'+a).focus();
					return false;
                                        break;
                    }
                                  
                    else if($('#methods'+a).val()==="" || $('#methods'+a).val()===null){
                                
                                alert('Select method(s) used for training');
				$('#methods'+a).focus();
					return false;
                                        break;
                                    }
                     else if($('#sessiondate'+a).val()===""){
                                
                                alert('Select session date');
				$('#sessiondate'+a).focus();
					return false;
                                        break;
                                    }
                                    
                     else if($('#time'+a).val()===""){
                                
                                alert('enter time taken for the session');
				$('#time'+a).focus();
					return false;
                                        break;
                                    }
                    
                     else if($('#malecondom'+a).val()===""){
                                
                                alert('enter number of male condoms');
				$('#malecondom'+a).focus();
					return false;
                                        break;
                                                           }
                                    
                    else if($('#femalecondom'+a).val()===""){
                                
                                alert('enter number of female condoms');
				$('#femalecondom'+a).focus();
					return false;
                                        break;
                                                            }
                                                            
                   else {
                                                                //return true after all the columns have been checked
                
                        }
                    
                                    
				}
                                
                                 else {
                                    
                     if(a===masomo){  return true; }
                     else {  
                    //do nothing 
                    }
                                }
                            }
        
    }// enf of tab 2
                        
                        
			
//----------------------------------------------------------------- Tab 3---------------------------------------------------------------------------


   else if(index===3) {
            
            
            var rowsngapi=1;
            if($("#totalrows").val()!=='' )
            {
             rowsngapi=$("#totalrows").val();
             rowsngapi=rowsngapi-1;
            }
            
				for(var a=1;a<=rowsngapi;a++){
                                    
                                // console.log(" loop number ni _ "+a);   
                                    //6
				if(((typeof $('#firstname'+a).val() !== "undefined") && $('#firstname'+a).val() !==null ) && ($('#firstname'+a).val().trim()!==""  || $('#middlename'+a).val().trim()!=="" || $('#lastname'+a).val()!=="" || $('#age'+a).val()!=="" || $('#sex'+a).val()!=="" ))
                                {
			console.log(a+" fname:"+$('#firstname'+a).val()+"  mname"+$('#middlename'+a).val()+"  lastname:"+$('#lastname'+a).val()+" age:"+ $('#age'+a).val()+"  sex:"+$('#sex'+a).val());		
                     if($('#firstname'+a).val()===""){
                                
                                alert('Enter firstname');
				$('#firstname'+a).focus();
					return false;
                                        break;
                                                     }
                                  
                    else if($('#lastname'+a).val()===""){
                                
                                alert('Enter last name');
				$('#lastname'+a).focus();
					return false;
                                        break;
                                    }
                     else if($('#age'+a).val()===""){
                                
                                alert('Enter Participant Age');
				$('#age'+a).focus();
					return false;
                                        break;
                                    }
                                    
                     else if($('#sex'+a).val()===""){
                                
                                alert('Select participant sex');
				$('#sex'+a).focus();
					return false;
                                        break;
                                    }
                    
                  
                                                            
                   else {
                                                                //return true after all the columns have been checked
                
                        }
                    
                                    
				}
                                
                                 else {
                                    
                     if(a===rowsngapi){  return true; }
                     else {  
                    //do nothing 
                    }
                                }
                            }
        
    }


			
			
		}
                        
        
        });
                        
                        $('#rootwizard .finish').click(function () {
                            var dat= $("#pprevform").serialize();
                             
                             $.ajax({
                    url: 'getForm?' + dat,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {

                      
                        $("#ujumbe").html(data);
                       
location.reload();
                    }
                });

                        });

                        $('.tarehe').datepicker({
                            todayHighlight: true, clearBtn: true, autoHide: true, dateFormat: "yy-mm-dd",maxDate: "now()",changeMonth: true,
        changeYear: true
                        });

$('.tarehe1').datepicker({
                            todayHighlight: true, clearBtn: true, autoHide: true, dateFormat: "yy-mm-dd",maxDate: "now()",changeMonth: true,
        changeYear: true
                        });



 counter = $("#totalrows").val();







                    }


                });


    



                $.ajax({
                    url: 'getAgegroup',
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {

                        //console.log(data);
                        $("#agegroup").html(data);


                    }
                });

 $.ajax({
                    url: 'getPartner',
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {
                        $("#partner").html(data);
                        $("#partnername").html(data);
                        $("#partnernamed").html(data);
                        getPopulation();
                        //App.init();
                    }
                });




                $.ajax({
                    url: 'getSubcounty?county=8',
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {
                        $("#subcounty").html(data);
                        $("#subcountyname").html(data);
                        $("#subcountynamed").html(data);


                    }
                });



                $('.tarehe').datepicker({
                    todayHighlight: true, clearBtn: true, autoclose: true, dateFormat: "yy-mm-dd"
                });

setSessions();

setParticipants();

setAttendance();
   



            });//end of document .getReady


   //-------------------------------------------module for adding rows dynamically-------------------------------

   
   
<% getParticipants gp = new getParticipants();%>
<% getAttendance ga = new getAttendance();%>



   // $("#addrow").on("click", function () {
      function addrow(){
         counter = $("#totalrows").val();
         var agegroup="";
         var id="";
         var defaultsex="";
         
         agegroup=$("#agegroup").val();
         
         if(agegroup==='Young Women' || agegroup==='Older Women' ){
             
             defaultsex="Female";
             
         }
         
         if(agegroup==='Young Men' || agegroup==='Older Men' ){
             
             defaultsex="Male";
             
         }
         
         var gend="";
         if(defaultsex!==""){gend="<%=gp.getGender("Female")%>";}
else {
  gend="<%=gp.getGender("")%>";  
    
}         
    
        var lessons=7;
        
        if($("#lessons").val()!==''  ){
        if((typeof $("#group").val() !== "undefined")){
            
            lessons=$("#lessons").val();
            
            } 
        }
        
        
        var newRow = $("<tr id='tablerow"+counter+"'>");
        var cols = "<td class= 'col-sm-1 '>"+counter+"<input value='"+getRandomId(10000,90000)+"' type='hidden' id='id"+counter+"' name= 'id"+counter+"'  /></td>";
        
         cols += "<td class= 'col-sm-2 '><input style='text-transform: lowercase;' onblur='appendnames(\""+counter+"\");' type= 'text' id='firstname"+counter+"' name= 'firstname"+counter+"' class= 'form-control' /></td>"
         cols += "<td class= 'col-sm-2 '><input style='text-transform: lowercase;' onblur='appendnames(\""+counter+"\");' placeholder='optional' type= 'text' id='middlename"+counter+"' name='middlename"+counter+"' class= 'form-control' /></td>"
         cols += "<td class= 'col-sm-2 '><input style='text-transform: lowercase;' onblur='appendnames(\""+counter+"\");' type= 'text' id='lastname"+counter+"' name='lastname"+counter+"' class= 'form-control' /></td>"
         cols += "<td class= 'col-sm-2 '><input onkeypress='return numbers(event);' maxlength='2' type= 'text' id='age"+counter+"' name='age"+counter+"' class= 'form-control' /></td>"
         cols += "<td class= 'col-sm-2 '><select  id='sex"+counter+"' name='sex"+counter+"' class= 'form-control' >"+gend+"</select></td>"
         cols += "<td><input  tabindex='-1' onclick='deleterow(\"tablerow"+counter+"\",\"tableregrow"+counter+"\");' type='button' class='ibtnDel btn btn-md btn-danger' value='Delete'></td>";
         
        newRow.append(cols);
       // newRow.append("</tr>");
        $("table.order-list").append(newRow);
        //add attendance rows
        
        var newregRow = $("<tr id='tableregrow"+counter+"'>");
        
        var regrows="<td class='col-sm-1' >"+counter+"</td><td class= 'col-sm-2'><div id='participant"+counter+"'></div></td>";
                  for(var d=1;d<=lessons;d++)
                              {  
                          //presentAbsent 
                     regrows+= "<td '><select style='width:100px;' onchange='attendanceicon(\""+counter+"_"+d+"\");'  id='s"+counter+"_"+d+"' name='s"+counter+"_"+d+"' class='form-control' ><%=ga.presentAbsent("1")%></select> <i class='icon-check' id='status"+counter+"_"+d+"'></i></td>";
                              } 
                  
                regrows+="<td class= 'col-sm-1' ></a></td></tr>"; 
         newregRow.append(regrows);
       // newRow.append("</tr>");
        $("#attendancetable").append(newregRow);
        
         id="<%=gp.RandomNo(10000, 90000)%>";  
        counter++;
       $("#totalrows").val(counter); 
   }
   // });


 function deleterow(participantid,attendancerowid){
   // $("table.order-list").on("click", ".ibtnDel", function (event) {
  var sexelement=participantid.replace("tablerow","sex");
      //  $("table.order-list").closest("tr").remove();       
        $("#"+sexelement).val("");       
        $("#"+participantid).remove();       
        $("#"+attendancerowid).remove();       
        //counter -=1;
        //$("#totalrows").val(counter); 
                    
   // });
    }
   
 

            function getPopulation() {

                var partnerid = "";
                var partn = $("#partner").val();

                if (partn !== '' && partn !== null) {
                    partnerid = "partner=" + partn;
                }


                $.ajax({
                    url: 'getPopulation?' + partnerid,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {
                        $("#targetpop").html(data);
                        $("#targetpopname").html(data);
                     getCurriculum();
                    }
                });
            }



            function getCurriculum() {

                var popid = "";
                var popl = $("#targetpop").val();

                if (popl !== '' && popl !== null) {
                    popid = "targetpop=" + popl;
                }


                $.ajax({
                    url: 'getCurriculum?' + popid,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {
                        
                        $("#curriculum").html(data);
                        setLessons();setSessions(); setAttendance();

                    }
                });
            }
     //getCurriculum();

            //getPopulation();

            function getGroup(id ,defaultgroup) {

                var wardid = "";
                var ward = $("#"+id).val();

                if (ward !== '' && ward !== null) {
                    wardid = "&ward=" + ward;
                }


                $.ajax({
                    url: 'getGroup?defaultgroup='+defaultgroup + wardid,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {
                        $("#group").html(data);
                        $("#groupnamed").html(data);
                        
                        $("#group").select2();
                        $("#groupnamed").select2();

                    }
                });
            }



            function setLessons() {


                var lesson = $("#curriculum").find(':selected').data('lessons');
                $("#lessons").val(lesson);

            }



            function getFacilitator(Defaultfacil) {
                
                

                var group = "";
                var groupid = $("#group").val();

                if (groupid !== '' && groupid !== null) {
                    group = "&group=" + groupid;
                }


                $.ajax({
                    url: 'getFacilitator?Defaultfacil='+Defaultfacil + group,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {

                      
                        $("#facilitator").html(data);
                      
$("#facilitator").select2();



                    }
                });
            }

function getCoFacilitator(Defaultfacil) {

                var group = "";
                var groupid = $("#group").val();

                if (groupid !== '' && groupid !== null) {
                    group = "&group=" + groupid;
                }


                $.ajax({
                     url: 'getFacilitator?Defaultfacil='+Defaultfacil + group,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {

                      
                        
                        $("#cofacilitator").html(data);

$("#cofacilitator").select2();


                    }
                });
            }


    function getWard(id,defaultward) {
//console.log("nimeitwa");
                var subcountyid = "";
               // var subcounty = $("#subcountyname").val();
                var subcounty = $("#"+id).val();

                if (subcounty !== '' && subcounty !== null) {
                    subcountyid = "&subcounty=" + subcounty;
                }


                $.ajax({
                    url: 'getWard?defaultward='+defaultward+subcountyid,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {
                        if(id==='subcounty'){ 
                            
            $("#wardid").html(data);
             $("#ward").html(data);
             //console.log("ward imechange sasa");
        
        }
                        else {
                           $("#ward").html(data);  
                            
                        }
                        

                    }
                });
            
            }


// if(Date.parse(startdate) > Date.parse(enddate)){
//                    alert("Start date cannot be greater than end date.");   
//                    $("#enddate").focus();  
//                }


  function setSessions() {

                var curriculum= "";
                var curriculumid = $("#curriculum").val();
                var formid = $("#formid").val();
                
                  var lesson = "7";
                  
                  if($("#curriculum").find(':selected').data('lessons')!=='' && $("#curriculum").find(':selected').data('lessons')!==null){
                  
                lesson = $("#curriculum").find(':selected').data('lessons');
                
        }

                
                //if (curriculumid !== '' && curriculumid !== null) {
                if (1===1) {
                    curriculum = "&curriculumid="+curriculumid+"&lessons="+lesson;
                }


                $.ajax({
                    url: 'getSessions?formid='+formid + curriculum,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {

                      
                        $("#sessiondetails").html(data);
                   
                        
                        validatesessiondate();

                    }
                });
            }





  function setParticipants() {

                var group= "";
                
                var agegroup="";
                
                if (typeof $("#agegroup").val() !== "undefined") {
                      agegroup = $("#agegroup").val();
				  }
                
                
                var groupid ="";
                 if (typeof $("#group").val() !== "undefined") {
                      groupid = $("#group").val();
				  }
                
               
              
                //if (curriculumid !== '' && curriculumid !== null) {
           
                    group = "groupid="+groupid+"&agegroup="+agegroup;
              


                $.ajax({
                    url: 'getParticipants?' + group,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {

                    
                        $("#participants").html(data);
                 

                    }
                });
            }

function setParticipantsWithGroup(defaultgroup) {

                var group= "";
                
                var agegroup="";
                
                if (typeof $("#agegroup").val() !== "undefined") {
                      agegroup = $("#agegroup").val();
				  }
                
                
                var groupid =defaultgroup;
                
                
               
              
                //if (curriculumid !== '' && curriculumid !== null) {
           
                    group = "groupid="+groupid+"&agegroup="+agegroup;
              


                $.ajax({
                    url: 'getParticipants?' + group,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {

                    
                        $("#participants").html(data);
                 

                    }
                });
            }





function setAttendance() {

                var group= "";
                var lessons= "";
                
                
                
                
                var groupid ="";
                 if (typeof $("#group").val() !== "undefined") {
                      groupid = $("#group").val();
				  }
              if (typeof $("#lessons").val() !== "undefined") {
                      lessons = $("#lessons").val();
				  }   
               
              
                //if (curriculumid !== '' && curriculumid !== null) {
           
                    group = "groupid="+groupid+"&lessons="+lessons;
              


                $.ajax({
                    url: 'getAttendance?' + group,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {
                        $("#attendance").html(data);

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


function appendnames(id){

var firstname=$("#firstname"+id).val();    
var middlename=$("#middlename"+id).val();    
var lastname=$("#lastname"+id).val();    
        
  var fullname=firstname+" "+middlename+" "+lastname;  
  
   $("#participant"+id).html(fullname);
   
}


function attendanceicon(id){
    var absentpresent=$("#s"+id).val();
    
    if(absentpresent==='1'){$("#status"+id).addClass('icon-check').removeClass('icon-remove-sign');}
    else if(absentpresent==='0'){$("#status"+id).addClass('icon-remove-sign').removeClass('icon-check');}
    
    
    
}


function saveGroup(){
    
     var subcounty=$("#subcountyname").val();
     var ward=$("#ward").val();
     var partnername=$("#partnername").val();
     var targetpopname=$("#targetpopname").val();
     var groupname=$("#groupname").val();
    
    if(subcounty===""){
        
        //alert('Select subcounty');
	$('#addgroupmsg').html("<font color='red'>Enter sub-county</font>");   
	$('#subcountyname').focus();   
    }
    else if(ward===""){
        
        //alert('Select subcounty');
	$('#addgroupmsg').html("<font color='red'>Enter Ward</font>");   
	$('#ward').focus();   
    }
    
    else if(partnername===""){
        
        //alert('Select subcounty');
	$('#addgroupmsg').html("<font color='red'>Enter Partner name</font>");   
	$('#partnername').focus();   
    }
    
     else if(targetpopname===""){
        
        //alert('Select subcounty');
	$('#addgroupmsg').html("<font color='red'>Enter Target population</font>");   
	$('#targetpopname').focus();   
    }
    
     else if(groupname===""){
        
        //alert('Select subcounty');
	$('#addgroupmsg').html("<font color='red'>Enter group name</font>");   
	$('#groupname').focus();   
    }
    else {
        //submit the data
        var myur="subcounty="+subcounty+"&ward="+ward+"&partner="+partnername+"&population="+targetpopname+"&group="+groupname;
      $.ajax({
                    url: 'saveGroup?' + myur,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {
                        $("#addgroupmsg").html(data);
                        
  $("#subcountyname").val("");
  $("#ward").val("");
  $("#partnername").val("");
  $("#targetpopname").val("");
  $("#groupname").val("");
                        
               getGroup("ward","");         

                    }
                });  
        
    }
    
    
    }//end of save group



//----------------------------------------------------------------------------------------------------------------------
//



function saveFacilitator(){
    
     var fname=$("#first_name").val();
     var mname=$("#middle_name").val();
     var sname=$("#sur_name").val();
     var phone=$("#phone").val();
     var subcountynamed=$("#subcountynamed").val();
     var groupnamed=$("#groupnamed").val();
    
    if(fname===""){
        
        //alert('Select subcounty');
	$('#addfacilitatormsg').html("<font color='red'>Enter first_name</font>");   
	$('#first_name').focus();   
    }
//    else if(mname===""){
//        
//        //alert('Select subcounty');
//	$('#addgroupmsg').html("<font color='red'>Enter Middlename</font>");   
//	$('#middle_name').focus();   
//    }
    
    else if(sname===""){
        
        //alert('Select subcounty');
	$('#addfacilitatormsg').html("<font color='red'>Enter Last name</font>");   
	$('#sur_name').focus();   
    }
    
//     else if(phone===""){
//        
//        //alert('Select subcounty');
//	$('#addgroupmsg').html("<font color='red'>Enter Phone number</font>");   
//	$('#phone').focus();   
//    }
    
     else if(subcountynamed===""){
        
        //alert('Select subcounty');
	$('#addfacilitatormsg').html("<font color='red'>Select Sub-county</font>");   
	$('#subcountynamed').focus();   
    }
    
    else if(groupnamed===""){
        
        //alert('Select subcounty');
	$('#addfacilitatormsg').html("<font color='red'>Select group</font>");   
	$('#groupnamed').focus();   
    }
    
    
    else {
        //submit the data
        var myur="fname="+fname+"&mname="+mname+"&sname="+sname+"&phone="+phone+"&subcounty="+subcountynamed+"&group="+groupnamed;
      $.ajax({
                    url: 'saveFacilitator?' + myur,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {
   $("#addfacilitatormsg").html(data);
   $("#first_name").val("");
   $("#middle_name").val("");
   $("#sur_name").val("");
   $("#phone").val("");
   $("#subcountynamed").val("");
   $("#groupnamed").val("");
                    if($("#facilitator").val()===''){
                        getFacilitator("");   
                        
                    } 
                     if($("#cofacilitator").val()===''){
                        getCoFacilitator("");   
                        
                    }
                     
                        

                    }
                });  
        
    }
    
    
    }//end of save facilitator



//
//
//----------------------------------------------------------------------------------------------------------------------








$("#groupsform").submit(function(e){
    return false;
});

$("#facilitatorform").submit(function(e){
    return false;
});




//listen to enter click

$(document).keypress(function(e) {
    if(e.which === 13 && $("#firstname1").is(":visible") ) {
        $("#ongezarow").click();
    }
});



function setYearMonth(){
    
    var ym=$("#enddate").val();
    
    ym=ym.replace("-","");
    ym=ym.substring(0,6);
 $("#yearmonth").val(ym)   
    
    
    
    
}

function validatesessiondate(){
    var mindate=$("#startdate").val();
    var maxdate=$("#enddate").val();
    
    console.log("Startdate"+mindate+"  and maxdate "+maxdate);
    
    if(mindate!=="" && maxdate!==""){
        console.log("Tuko hapa ");
    $('.tarehe1').datepicker('destroy');
    
    $('.tarehe1').datepicker({
                            todayHighlight: false, clearBtn: true, autoHide: true, dateFormat: "yy-mm-dd",maxDate: maxdate,minDate:mindate
                        });
                    }
                    else {
                        $('.tarehe1').datepicker({
                            todayHighlight: true, clearBtn: true, autoHide: true, dateFormat: "yy-mm-dd"
                        });
                        
                    }
    
}



function setGroupDefaults(){
    
    var sc=$("#subcounty").val();
    var ward=$("#wardid").val();
    var ptn=$("#partner").val();
    var tp=$("#targetpop").val();
    
    
    $("#subcountyname").val(sc);
    $("#ward").val(ward);
    $("#partnername").val(ptn);
    $("#targetpopname").val(tp);
    
    
}


function setFacilitatorDefaults(){
    
    var sc=$("#subcounty").val();
    var grp=$("#group").val();
    
    
     $("#subcountynamed").val(sc);
     $("#groupnamed").val(grp);
      $("#groupnamed").select2();
    
}





function loadEnteredForms()
{
    
  //        
           $.ajax({
                    url: 'loaddata' ,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {

                      
                        $("#editattendancediv").html(data);
                        
                        
                        var table2 = $('#editattendancetable').DataTable({"autoWidth": true,
              "paging": true,
              "pagingType": "full",
              "lengthChange": false,  
              "order": [[0,'desc']]});
                       
//location.reload();
                    }
                });      
    
    
}
loadEnteredForms();


function editAttendanceForm(id,ward)
{
    
  $.ajax({
                    url: 'editForm?ward='+ward+'&formid='+id ,
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {

                   //load the various eleements   
                        //$("#editattendancediv").html(data);
                        
 //var mauwanja={"formid","partner","subcounty","targetpop","curriculum","group","lessons","facilitator","cofacilitator","startdate","enddate","agegroup"};      
   // for(var s=0;s<mauwanja.length;s++){
     
//    $("#"+mauwanja[s]).val(data.)        
        
   // }   
//    $("#facilitator").trigger('change');  
//    $("#cofacilitator").trigger('change'); 

   
  $("#formid").val(data.formid);   
  $("#id").val(data.id);   
  $("#partner").val(data.partner);   
  $("#partner").change();   
  $("#subcounty").val(data.subcounty); 
  getWard("subcounty",ward);
  getGroup("ward",data.group);
  $("#targetpop").val(data.targetpop); 
  //$("#targetpop").change();  
  
  $("#curriculum").val(data.curriculum); 
  
  $("#lessons").val(data.lessons); 
 
  getFacilitator(data.facilitator); 
  getCoFacilitator(data.cofacilitator); 
  $("#startdate").val(data.startdate);   
  $("#startdate").trigger('blur');   
  $("#enddate").val(data.enddate);   
  $("#enddate").trigger('blur'); ;   
  $("#agegroup").val(data.agegroup);
  //$("#agegroup").change();
     // $("#group").trigger('change'); 
  
//  $("#group").val(data.group);
//  $("#facilitator").val(data.facilitator);
//  $("#cofacilitator").val(data.cofacilitator);
  
  $("#facilitator").select2(); 
  $("#cofacilitator").select2();
  $("#group").select2();
  
  //setSessions();

setParticipantsWithGroup(data.group);

//setAttendance();
        
//location.reload();
                    }
                });    
    
    
}


function getRandomId(min,max){
    var valueni="";
    

    return Math.floor(Math.random()*(max-min+1)+min);

   
}


  $( function() {
    $( "#accordion" ).accordion({
        
        heightStyle: "content" 
        
    });
  } );
  
  
  
    function setServiceParticipants() {

              


                $.ajax({
                    url: 'loadParticipantsforServices',
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {

                    
                $("#hcservices").html(data);
                
                var table2 = $('#participants2table').DataTable({"autoWidth": true,
              "paging": true,
              "pagingType": "full",
              "lengthChange": true,
              
              "order": [[1,'desc']]});
          
//          $('#participants2table tfoot th').each( function () {
//        var title = $(this).text();
//        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
//    } );
    
//    table2.columns().every( function () {
//        var that = this;
// 
//        $( 'input', this.footer() ).on( 'keyup change', function () {
//            if ( that.search() !== this.value ) {
//                that.search( this.value ).draw();
//            }
//        } );
//    } );
          

                    }
                });
            }
  
  setServiceParticipants();
  
  function launchservices(sex,participantid,participant,age,ward){
    
    $("#servicesbutton").click();
  
    
       $.ajax({
                    url: 'loadServices?sx='+sex+"&id="+participantid+"&pp="+participant+"&ag="+age+"&wd="+ward,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) 
                    {
                     $("#servicesdiv").html("<form id='servicesf'  >"+data+"</form>");
                    
        
 
    $( ".tarehe3" ).datepicker({dateFormat:"yy-mm-dd",maxDate:  '+0m +0w' ,changeMonth: true,
        changeYear: true});
  
        
//                     $('.tarehe3').datepicker({
//                            todayHighlight: false, clearBtn: true, autoHide: true, format: "yy-mm-dd"
//                        });
                    }
                });  
      
      
  }
  
  
  function saveServices(){
       var da= $("#servicesf").serialize();
       
       
	var ishtsrefChecked = $('#htc_ref').prop('checked');
	var ishtsrecChecked = $('#htc_rec').prop('checked');
        
        var htsrefdate=$("#htc_ref_date").val();
        
        var htsrecdate=$("#htc_rec_date").val();
        var htsres=$("#hivresults").val();
       
       if(ishtsrefChecked===true && htsrefdate===''){ $("#ujumbe3").html("<font color='red'>Data not saved.Please enter HTC service referral date</font>");}
       else if(ishtsrecChecked===true && htsrecdate===''){ $("#ujumbe3").html("<font color='red'>Data not saved.Please enter HTC service received date</font>");}
       else if(ishtsrecChecked===true && htsres===''){ $("#ujumbe3").html("<font color='red'>Data not saved.Please enter HIV results</font>");}
       
      // alert(da);
      else {
          
       var idd=   $("#participantid").val();
          
          $("#status_"+idd).addClass('btn-success').removeClass('btn-danger');
          $("#status_"+idd).html('Yes');
             $.ajax({
                    url: 'saveServices?' + da,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {

                      
                        $("#ujumbempya").html(data);
                        $("#ujumbe3").html(data);
                        $("#servicesclosebtn").click();
                       

                    }
                }); 
            }
      
      
  }
  
  
  
function getreport(){
    
    
    var exelstart=$("#sdate").val();
    var exelend=$("#edate").val();
    
 
    
    //var countyrpt=$("#rpt_county").val();
    //var rpttypeurl=$("#rpt_type").val();   
    var rpttypeurl="rawdata";   
        if (exelstart==='')
     {
         
     alert('Select report begining date');
   $("#sdate").focus();    
     }    
   //end date
      else if (exelend==='')
     {
         
     alert('Select report ending date');
   $("#edate").focus();    
     } 
     
      else  if(Date.parse(exelstart) > Date.parse(exelend)){
                    alert(" Report Start date cannot be greater than end date.");   
                    $("#edate").focus();  
                }
                else {
                    //call the report generation page
                 downloadrpt(exelstart,exelend,rpttypeurl) ;  
                    
                }
        
    
}



  function downloadrpt(startdate,enddate,rpttypeurl){
      
                $('.loading1').show();
                $('#excelreportbtn').hide();
               
                //?startdate=" + startdate + "&enddate=" + enddate + "&cbos=" + cbos
             
                var uur=rpttypeurl+"?sdate=" + startdate + "&edate=" + enddate;
                console.log(uur);
                $.fileDownload(uur).done(function () { $('.loading1').hide(); $('#excelreportbtn').show(); $('#excelreportbtn').html("<i class='glyphicon glyphicon-ok'></i> Report Generated"); }).fail(function () { alert('Report generation failed, kindly try again!'); $('.loading1').hide(); $('#excelreportbtn').show(); });
 
                //$('.loading').hide();
            }



  function resetgeneratebutton(){
    
   $("#excelreportbtn").removeClass('btn-lg btn-success active').addClass('btn-lg btn-info active');  
    
}

  
  
  </script>
        
        <!-- END JAVASCRIPTS -->   
    </body>
    <!-- END BODY -->
</html>

