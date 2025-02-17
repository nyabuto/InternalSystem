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
        <title>Hypertension Audit</title>
        <link rel="shortcut icon" href="images/imis.png"/>
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
<div id="ujumbempya" style="font-size: 12px;"> 
                                                
                                             
                                                
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


  <!--<h3 style="text-align: center;"> <span class='badge badge-warning'>1</span> PP PREV 1 GROUP ATTENDANCE REGISTER</h3>-->

  
  
  
  <h3 style="text-align: center;" id="participantsaccordion"><span class='badge badge-warning'>2</span> Accounting for Hypertensive Clients who are not Controlled</h3>
  <div>     
      
       <div class="control-group" >
                                    
                                    <div class="controls ">
                                       <table style='margin-left: 0%;background-color: gainsboro;width:70%;'> 
                                           <tr>
                                               <td><label class="col-sm-2"><b><font color="red"><b>*</b></font>Select Facility</b> </label></td>
                                        <td><select onchange='displayIncorrectCcc();'  name ="facility" id="facility"  class="form-control" >
                                            <option value=''></option>
                                        </select></td><td>
                                            <!--<a  class='btn btn-info' style='margin-left: 100px;'  data-toggle='modal' href='#rawdata'><i class='icon-plus'></i>Download Excel Reports</a>-->
                                        </td>
                                           </tr>
                                       </table>
                                    </div>
                                </div> 
      
      <div id="hcservices"><h3 style="text-align: center;color:green;">Select Facility name above to load its data</h3></div>
      
      
  </div>
  
  
  
  
  
  
</div><!-----end of accordion----->

 <!----------------------------------------------------------------------------Attendance-------------------------------------------------------------->
 <!----------------------------------------------------------------------------services modal-------------------------------------------------------------->
 
 



<div class="modal fade" id="hcservicesmodal" style='width:100%;' >
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" id='servicesclosebtn' class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" style='text-align: center;'><span  style='text-align: right;'></span></h4>
            </div>
            <div class="modal-body">
                
                               
                    
                    
                   
           <div id="servicesdiv" style='overflow-y:scroll;height:600px;' ><label>Loading services</label></div>         
                    
                    
              
                 
                                  
                    
              
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
                
                               
                    
                  <table> 
                      <!--<tr><th>Element</th><th>Value</th></tr>-->
                   
                
                   
                                 
                               
                     
                                   <tr>
                                   <td> <label><font color="red"><b>*</b></font>Report type</label></td>
                                   <td>
                                    
                                    <div class="controls">
                                        <select name='reporttype' id='reporttype'>                                           
                                            <option value='sp_vl_get_noncleancalhiv_notupdated'>Not Updated Records</option>
                                             <option value='sp_vl_get_noncleancalhiv_updated'>Updated Records Only</option>
                                            <option value='sp_vl_get_noncleancalhiv_all'>All Records</option>
                                            
                                        </select>
                                    </div>
                                   </td>
                                   </tr>
                              
                    
                    
                     
                    
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

            &copy; USAID Tujenge Jamii | USAID <%=year%>.
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

 



               





    



                $('.tarehe').datepicker({
                    todayHighlight: true, clearBtn: true, autoclose: true, dateFormat: "yy-mm-dd"
                });


   



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
         if(agegroup==='Young Men' || agegroup==='Older Men' ){gend="<%=gp.getGender("Men")%>";}
    else if(agegroup==='Young Women' || agegroup==='Older Women' ){gend="<%=gp.getGender("Female")%>";}
else 
{
  gend="<%=gp.getGender("")%>";  
    
}         
    
        var lessons=7;
        
        if($("#lessons").val()!==''  ){
        if((typeof $("#group").val() !== "undefined")){
            
            lessons=$("#lessons").val();
            
            } 
        }
        
        var rn=getRandomId(10000,90000);
        var newRow = $("<tr id='tablerow"+counter+"'>");
        var cols = "<td class= 'col-sm-1 '>"+counter+"<input value='"+rn+"' type='hidden' id='id"+counter+"' name= 'id"+counter+"'  /></td>";
        
         cols += "<td class= 'col-sm-2 '><input style='text-transform: lowercase;' onblur='appendnames(\""+counter+"\");' type= 'text' id='firstname"+counter+"' name= 'firstname"+counter+"' class= 'form-control' /></td>"
         cols += "<td class= 'col-sm-1 '><input style='text-transform: lowercase;' onblur='appendnames(\""+counter+"\");' placeholder='optional' type= 'text' id='middlename"+counter+"' name='middlename"+counter+"' class= 'form-control' /></td>"
         cols += "<td class= 'col-sm-2 '><input style='text-transform: lowercase;' onblur='appendnames(\""+counter+"\");' type= 'text' id='lastname"+counter+"' name='lastname"+counter+"' class= 'form-control' /></td>"
         cols+="<td class= 'col-sm-2 '><input style='text-transform: lowercase;' onblur='isunique(\"patient_unique_id"+counter+"\",\""+rn+"\");'  type= 'text' id='patient_unique_id"+counter+"' name='patient_unique_id"+counter+"' class= 'form-control' /></td>"
         cols += "<td class= 'col-sm-1 ' style='width:170px;'><input style='width:150px;' onkeypress='return numbers(event);' maxlength='2' type= 'text' id='age"+counter+"' name='age"+counter+"' class= 'form-control' /></td>"
         cols += "<td class= 'col-sm-2 ' style='width:170px;'><select style='width:150px;'  id='sex"+counter+"' name='sex"+counter+"' class= 'form-control' >"+gend+"</select></td>"
         cols += "<td><input   tabindex='-1' onclick='deleterow(\"tablerow"+counter+"\",\"tableregrow"+counter+"\");' type='button' class='ibtnDel btn btn-md btn-danger' value='Delete'></td>";
         
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
                  
             
                regrows+="<td class= 'col-sm-1' ><select   name='hivresults"+counter+"' id='hivresults"+counter+"' style='width:100%;' class='form-control'><%=ga.hivResults("")%></select></td>";
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
   
 



            //getPopulation();

            function getSitesWithNonStandardCccNos() {

              


                $.ajax({
                    url: 'getSitesWithHptClients',
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {
                        
                        var dt=data.facilities;
                        
                        var sites="<option value=''>select facility</option>";
                        
                        for(var a=0;a<dt.length;a++){
                            
                         sites+="<option value='"+dt[a].mflcode+"'>"+dt[a].Facility+"</option>";
                            
                        }
                        
                        $("#facility").html(sites);                     
                        
                        $("#facility").select2();
                       

                    }
                });
            }


//gname

getSitesWithNonStandardCccNos();


      



 



  


     function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57)){
return false;
}

else{
 


 
return true;
}
}




//----------------------------------------------------------------------------------------------------------------------
//



//
//
//----------------------------------------------------------------------------------------------------------------------






















function getRandomId(min,max){
    var valueni="";
    

    return Math.floor(Math.random()*(max-min+1)+min);

   
}


  $( function() {
    $( "#accordion" ).accordion({
        
        heightStyle: "content" 
        
    });
  } );
  
  

    function displayIncorrectCcc() {

              var facility=$("#facility").val();
              
              
              $("#hcservices").html('<img src="images/ajax_loader.gif"/>');

                $.ajax({
                    url: 'load_hpt_list?mflcode_sync='+facility,
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {
console.log(data);
var dt=data[0].hpt_audit_list;
//table should have ccc Number, 

 var tbl="<table id='participants2table' class= 'table  table-bordered table-responsive' style='width:100%;'><thead><tr><th style='width:30%;'>Client Details</th><th><font color='green'>Is Hypertension Controlled?</font></th><th style='width:20%;'><font color='green'>Is Client On Hypertension Treatment?</font></th><th style='width:20%;'><font color='green'>Reason Client not on Treatment</font></th><th>Save</th></tr></thead><tbody>";
        
        
        for(var i=0;i<dt.length;i++)
        {
            
            var iscontrolled=iscontrolledvalues(dt[i].rev_is_hpt_controlled);
            var treatmentoption=isontreatvalues(dt[i].is_on_hpt_treat);
            var reasonnottreatoption=isnotontreatvalues(dt[i].reason_not_on_treat);
            tbl+="<tr id='"+dt[i].Patient_Id+"'>\n\
<td><b>CCC No:</b><font color='green'>"+dt[i].ccc_number+"</font><br/><b>Required Action:</b><font color='red'>"+dt[i].required_action+"</font><br/><b>Systolic/Diastolic Readings: </b>"+dt[i].systolic+"/"+dt[i].diastolic+" <br/></b><b>Date Hypertension Recorded:</b>"+dt[i].date_hpt_recorded+"<br/><b>Hypertension Onset Date:</b>"+dt[i].hpt_onset_date+"<br/><b>Is Hypertension Controlled:</b>"+dt[i].is_hpt_controlled+"<br/> Update Status: </b>"+dt[i].status+"<br/></td><td><select style='width:100%;' id='rev_is_hpt_controlled"+i+"' name='rev_is_hpt_controlled"+i+"' onchange='updateValue(\""+dt[i].id+"\",\"rev_is_hpt_controlled"+i+"\",\"rev_is_hpt_controlled\");'> "+iscontrolled+"</select></td><td><select style='width:100%;' id='is_on_hpt_treat"+i+"' name='is_on_hpt_treat"+i+"' onchange='updateValue(\""+dt[i].id+"\",\"is_on_hpt_treat"+i+"\",\"is_on_hpt_treat\");'> "+treatmentoption+"</select></td><td><select style='width:100%;' id='reason_not_on_treat"+i+"' name='reason_not_on_treat"+i+"' onchange='updateValue(\""+dt[i].id+"\",\"reason_not_on_treat"+i+"\",\"reason_not_on_treat\");'> "+reasonnottreatoption+"</select></td><td><input onclick='updateAllValues(\""+dt[i].id+"\",\""+i+"\");' class='btn btn-success' type='submit' value='Save Record'></td></tr>";
            
        }
        tbl+="</tbody></table>"
       
                $("#hcservices").html(tbl);
                
                var table2 = $('#participants2table').DataTable({"autoWidth": true,
              "paging": true,
              "pagingType": "full",
              "lengthChange": true,
              "pageLength": 100,
              
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
          

                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                         $("#hcservices").html(" Error While Loading data <br/> "+errorThrown);
                    }
                });
            }
  
 
 
 function iscontrolledvalues(curoption)
        
    {   
        

    
    var sel="<option value=''>Is Hypertension controlled?</option>";
    var opt=['Yes','No'];
   
    
  
    
    
    for(var nm=0;nm<opt.length;nm++)
    {
        
        var selec="";
        
        if(curoption===opt[nm])
        {
            
          selec="selected";  
            
        }
        
    sel+="<option "+selec+" value='"+opt[nm]+"'>"+opt[nm]+"</option>";
    }
    
    return sel;
    }

 
 function isontreatvalues(curoption)
        
    {   
        
    
    var sel="<option value=''>is client on HPT treatment</option>";
    var opt=['Yes','No'];
   
  
    
    
    for(var nm=0;nm<opt.length;nm++)
    {
        
        var selec="";
        
        if(curoption===opt[nm])
        {
            
          selec="selected";  
            
        }
        
    sel+="<option "+selec+" value='"+opt[nm]+"'>"+opt[nm]+"</option>";
    }
    
    return sel;
    }


 
 function isnotontreatvalues(curoption)
        
    {   
        
    
    var sel="<option value=''>Reason Client not on HTP Treatmnt</option>";
    var opt=["Financial contraints","Fear of Side Effects","Denial","Unwillingless to change Lifestyle","Complexity of Treatment Regimens","Cultural Beliefs and Practices","Psychological Factors eg Depression"];
   
  
    
    
    for(var nm=0;nm<opt.length;nm++)
    {
        
        var selec="";
        
        if(curoption===opt[nm])
        {
            
          selec="selected";  
            
        }
        
    sel+="<option "+selec+" value='"+opt[nm]+"'>"+opt[nm]+"</option>";
    }
    
    return sel;
    }
 
  
  
  function updateValue(systemid,elem,elem_request_name)
  {
      
       var sid=systemid;
       var elementvalue=encodeURIComponent($("#"+elem).val());
       var proceed=true;
       
       
       //for ccc numbers, ensure the ccc number meets the expectations
       
       if(elem.includes("rev_is_hpt_controlled"))
       {   
            console.log("Controlled Updated Successfully");
        if(isClientOnTreatmentFilled(elementvalue)) 
        {
         //save          
           blackbordercl(elem); 
          proceed=true;  
        }
        else 
        {
            
           redbordercl(elem);
           proceed=false;
        }            
            
       }
       
       if(elementvalue===''){  redbordercl(elem); proceed=false;}
	
      // alert(da);
      
            if(proceed){
                $.ajax({
                    url: 'updateHptValues?sid='+sid+"&"+elem_request_name+"="+elementvalue,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {                      
                        $("#ujumbempya").html(data);
                       
                                              }
                }); 
                }
            
      
      
  }
  
  function updateAllValues(systemid,rownumber)
  {
      
       var sid=systemid;
       
       var rev_is_hpt_controlled=encodeURIComponent($("#rev_is_hpt_controlled"+rownumber).val());
       
       var is_on_hpt_treat=encodeURIComponent($("#is_on_hpt_treat"+rownumber).val());
       
       var reason_not_on_treat=encodeURIComponent($("#reason_not_on_treat"+rownumber).val());
              
       var proceed=true;
       //for ccc numbers, ensure the ccc number meets the expectations
       
       
         if(rev_is_hpt_controlled==='' || rev_is_hpt_controlled==='Not Known' )
       {   
    
    redbordercl("rev_is_hpt_controlled"+rownumber);
    
    proceed=false;
    
    alert("Please indicate if Clients Hypertension status is Known or not");
    
        }
       
       else if(rev_is_hpt_controlled==='No' && is_on_hpt_treat==='' )
       {   
    
    redbordercl("is_on_hpt_treat"+rownumber);
    
    proceed=false;
    
    alert("Please indicate if Patient is Hypertension Treatment");
    
        }
        
        else if( is_on_hpt_treat==='No' && reason_not_on_treat==='' )
       {   
    
    redbordercl("reason_not_on_treat"+rownumber);
    
    proceed=false;
    
    alert("Please indicate the reason the client is not on Hypertension Treatment");
    
        }
       else 
       {
            console.log("Controlled Updated Successfully");
      
         //save          
           blackbordercl("reason_not_on_treat"+rownumber); 
           blackbordercl("is_on_hpt_treat"+rownumber); 
          proceed=true;  
        
                  
            
       }
       
      
	
      // alert(da);
      
            if(proceed){
                $.ajax({
                    url: 'updateHptValues?sid='+sid+"&rev_is_hpt_controlled="+rev_is_hpt_controlled+"&is_on_hpt_treat="+is_on_hpt_treat+"&reason_not_on_treat="+reason_not_on_treat,
                    type: 'post',
                    dataType: 'html',
                    success: function (data) {                      
                        $("#ujumbempya").html(data);
                       
                                              }
                }); 
                }
            
      
      
  }
  
  
     function redbordercl(id){
     $("#"+id).css('background-color', '#ff0000');
     $("#"+id).css('border-color', '#ff0000');
     $("#"+id).css('color', '#ffffff');
     
    }
    
    function blackbordercl(id){
     $("#"+id).css('background-color','#ffffff');
     $("#"+id).css('border-color','#88fd88');
     $("#"+id).css('color','#000000');
     
    }
  
  
function getreport(){
    
    
    var sp=$("#reporttype").val();
    var mfl=$("#facility").val();
      
    //call the report generation page
                 downloadrpt(sp,mfl) ;  
    
}



  function downloadrpt(rpttypeurl,mfl){
      
                $('.loading1').show();
                $('#excelreportbtn').hide();
               
                //?startdate=" + startdate + "&enddate=" + enddate + "&cbos=" + cbos
             
                var uur="getVlNonStandardOutput?sp="+rpttypeurl+"&mfl="+mfl;
                console.log(uur);
                $.fileDownload(uur).done(function () { $('.loading1').hide(); $('#excelreportbtn').show(); $('#excelreportbtn').html("<i class='glyphicon glyphicon-ok'></i> Report Generated"); }).fail(function () { alert('Report generation failed, kindly try again!'); $('.loading1').hide(); $('#excelreportbtn').show(); });
 
                //$('.loading').hide();
            }



  function resetgeneratebutton(){
    
   $("#excelreportbtn").removeClass('btn-lg btn-success active').addClass('btn-lg btn-info active');  
    
}

  
 
  function isClientOnTreatmentFilled(filled)
  {
      //check if ccc number is 10 digits
      //check if the ccc number has special characters
      //check if the ccc number has non numeric characters
      var wtokay=true;
      var msg="";
      
      
      if(filled==='No')
      {
       wtokay=false; 
       msg="<b><font color='red'>Warning: Please indicate Reason not initiated on Treatment</b></font>";
      
       console.log(msg);
       $("#ujumbempya").html(msg);
      }   
      else
      {
        msg="";   
        wtokay=true;  
          
      }
      
      return wtokay;
  } 
  
  function isWeightOkay(uzito)
  {
      //check if ccc number is 10 digits
      //check if the ccc number has special characters
      //check if the ccc number has non numeric characters
      var wtokay=true;
      var msg="";
      
      
      if(uzito>=90)
      {
       wtokay=false; 
       msg="<b><font color='red'>Warning: Weight <b>"+uzito+"</b> looks Abnormal!</b></font>";
      
       console.log(msg);
       $("#ujumbempya").html(msg);
      }   
      else
      {
        msg="";   
        wtokay=true;  
          
      }
      
      return wtokay;
  } 
  
  
    function isWeightCorrect(rowpos)
     {
         
  var noofdigits="noofdigits"+rowpos;
  var weightelement="newccc"+rowpos;
         
         
var retval=false;
var weightval=$("#"+weightelement).val();


var hosi=$('#facilityname').val();

 if(!isWeightOkay(weightval)){
    
$("#"+weightelement).css('border-color','red'); 
 $("#"+weightelement).focus();
//$("#"+noofdigits).html("<font color='red'>["+idadiyano+" digits]</font>");
    retval=false;
                   }
  
 else  {
    
$("#"+weightelement).css('border-color','#33b775'); 
    
    retval=true;
 }
    
 return  retval;
 
      }
  
  
  function isNum(cc){
      
    return  $.isNumeric(cc);
  }
  
           function   numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode;
if(charCode > 31 && (charCode < 48 || charCode>57)){
return false;
}

else{
return true;
}
}
  
  </script>
        
        <!-- END JAVASCRIPTS -->   
    </body>
    <!-- END BODY -->
</html>

