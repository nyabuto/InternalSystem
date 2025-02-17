

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>Download FMATT Template</title>
     
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
       <%@include file="/menu/menu.jsp"%>
         <!-- END SIDEBAR MENU -->
      </div>
      <!-- END SIDEBAR -->
      <!-- BEGIN PAGE -->  
      <div class="page-content" style="height: auto;">
<br><br>
         <div class="container-fluid">
           <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
               <div class="span12">
                   <h3 class='btn btn-success' style='width: 99%;' >
                    
                      
<!-- Modal -->

                         <!--<i class="icon-home"></i>-->
                        <b >Download FMATT  reporting Template</b>
                      
                  </h3>
           
                   <!-- Button trigger modal -->
<!--<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  Launch demo modal
</button>-->

<!-- <script type="text/javascript"> 
                    
                    var n = noty({text: ' Click to close notice.',
                        layout: 'center',
                        type: 'Success'});
                    
                </script>-->

                   <%if (session.getAttribute("locked") != null) {
                       
                  out.println(session.getAttribute("locked").toString()); 
                   %>
               
                   
                                <%
                session.removeAttribute("locked");
                            }

                        %>
                        
<!--                 <div class=\"notifications top-left"><a href="#" class="close" data-dismiss="alert"></a> here we are</div>
                 -->
                  <!-- BEGIN SAMPLE FORM PORTLET--> 
                  <form action="downloadFmattTemplate" method="post" class="form-horizontal" style="min-height: 450px;">
                      <br/>
                      <h4 class='btn-block btn-info btn-lg' style='text-align:center;'> Input fields marked with <font color='red'>*</font> are a must enter. Others are optional.   &nbsp;&nbsp;&nbsp;&nbsp;     </h4>
                      <br/>
                    <%if(session.getAttribute("userAccess")!=null){} else {%>   <h4 class='btn-block btn-default btn-lg' style='text-align:center;color:black;'> Please <a href="index.jsp" class="btn-info btn-lg "><b>Login</b></a> using your IMIS Credentials in order to access the Upload module </h4>
                      <br/>
                      <%}%>
                      <div>  

                       <table style="margin-left:20%;">
                          <div style="float:right;"> 
                             <tr><td style="padding-right: 200px;"> 
	                      <b>Year</b> <font color="red">*</font>  
                               
                                 </td><td>
                                <select required="true" data-placeholder="Reporting Year" class="span4 m-wrap" style="width: 300px;" tabindex="-1" onchange="loadmonths();"  id="year" name="year">
                                   <option value=''>Select Year</option>
                                              <%
                                                
                                                Calendar cal= Calendar.getInstance();
                                                int curyear=cal.get(Calendar.YEAR);
                                                 int curmn=cal.get(Calendar.MONTH)+1;
                                                String selected="";
                                                if(curmn>=10){curyear=curyear+1;}
                                                
                                            for(int a=curyear-1;a<=curyear;a++)
                                            {
                                               if(a==curyear){selected=" selected ";} else { selected=" ";   }
                                                
                                                if(session.getAttribute("yr")!=null){
                                                  
                                                if(new Integer(session.getAttribute("yr").toString())==a){
                                                  
                                                    
                                                    
                                                 out.println("<option "+selected+" value='"+a+"'>"+a+"</option>");
                                                }
                                                else {
                                                 out.println("<option "+selected+" value='"+a+"'>"+a+"</option>");
                                                }
                                                
                                                                                     }
                                                else {
                                                
                                             out.println("<option "+selected+" value='"+a+"'>"+a+"</option>");
                                             
                                                }
                                                %>
                                            
                                            
                                            <%
                                              }
                                            
                                            %>                                
                                   
                                 </select>
                                </td>
                             
                             
                             
                             
                             </tr>
                             <tr><td></td><td></td></tr>
                               <tr><td> 
                                <b>Month</b> <font color="red">*</font>
                                   </td><td>
                                  <select   placeholder="Month" required="true" class="span4 m-wrap" style="width: 300px;" size="1" tabindex="-1"  id="month" name="month" onchange="">
                                    
                                 </select>
                                  </td></tr>
                               
                               <tr class='bookmark'><td> <b>Show Bookmark</b> </td><td><input type='checkbox' onclick='showbookmarkselect();' value='use cluster' name='iscluster' id='iscluster'></td></tr>
                               <tr><td></td><td></td></tr>
                               <tr class='bookmarkselect' style='display:none;'><td> <b>Select Bookmark</b> </td><td><select style="width: 300px;" class="span4 m-wrap" onchange='setorgunits();'    name='clusterlist' id='clusterlist'> </select></td></tr>
                               
                                <tr><td></td><td></td></tr>
                                 <tr><td> 
                                    <b>County</b> <font color="red">*</font>
                               </td><td>
                                   <select placeholder="County" onchange="loadsubcounty();checkbkmark();" style="width: 300px;" required="true"  class="span4 m-wrap" tabindex="-1"  id="county" name="county">
                                    <option value=""></option>
                                 </select>
                                   </td></tr>
                                 <tr><td><br></td></tr>
                                    <tr><td> 
                                    <b>Sub-County</b> <font color="red"></font>
                               </td><td>
                                <select multiple data-placeholder="Sub-County" onchange="loadfacils();" size="8" style="width: 300px;"  class="span6 m-wrap" tabindex="-1"  id="subcounty" name="subcounty">
                                    <option value="">Select County First</option>
                                 </select>
                                    </td></tr>
                                    <tr><td><br></td></tr>
                                   <tr><td> 
                                <b>Activity Site</b> <font color="red"></font>
                               </td><td>
                                 <select multiple style="width:300px;float:right;color:black;" data-placeholder="Facility" class="span6" tabindex="-1"  id="facility" name="facility">
                                    <option value=""></option>
                                 </select></div>  
                           </td></tr>
                   
                   <tr><td><br></td></tr>
                                   
                   
                    <tr><td><br></td></tr>
                                   
            
            
                         
                         <!--<p style="margin-left: 450px; margin-top: 200px;">  Management module here....</p>-->

                <tr><td> </td><td> 
                              <button style='width:230px;' type="submit" id="submit"  class="btn-info btn-large "> Download Template(s)</button>
<!--                              <button type="button" class="btn">Cancel</button>-->
</td></tr>
                <tr><td>.</td><td></td></tr>
                <tr><td>.</td><td></td></tr>
                <tr><td>.</td><td></td></tr>
                   </table>
               
                          
                  <%if(session.getAttribute("userAccess")!=null){%>
                    <table class='ujumbe' style="margin-left:20%;display:none;">
                        <tr><td colspan='3'><label class='btn'><b> Are you interested in saving the selected sites for future easy selection? </b></label></td></tr>
                <tr> <td><b>Bookmark Name </b> </td><td>  <input type='text' name='clustername' placeholder="Type Bookmark name" id='clustername' class='m-wrap'/>
                       </td> <td> <label style='width:230px;' id='ujumbe' class="btn-large  btn-success" onclick="savecluster();">Save sites </label> </td></tr>
                  <tr><td colspan='2'><div ></div></td></tr>
                       
                       </table>    
                          <%}%>
                          
                        
                 </div>
                      </form>
                  <!-- END SAMPLE FORM PORTLET-->
                  
                   
               </div>
            </div>
                
         </div>
         <!-- END PAGE CONTAINER-->
      </div>
      <!-- END PAGE -->  
     
   </div>
   <!-- END CONTAINER -->
   <div>
  
   <!-- BEGIN FOOTER -->
  <div id="footer">
<%
//Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center" style=" font-size: 18px;"> &copyInternal System, USAID Tujenge Jamii | USAID <%=year%>.</p>
           
 

    <!--Modal unvalidated forms-->
    
 
       </div>
  
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
      <script src="select2/js/select2.js"></script>
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<script type="text/javascript" src="js/noty/themes/default.js"></script>

       
<script>
 $(document).ready (function(){
//           $("#alert").fadeTo(2000, 500).slideUp(20000, function(){
//    $("#alert").alert('close');
//});  

$("#lock_status").change(function(){
 if($("#lock_status").val()==="0"){ $("#submit").html("Un Lock Data");   $("#submit").attr("disabled",false) ; $("#submit").attr("class","btn blue") ; }
 else if($("#lock_status").val()==="1"){ $("#submit").html("Lock Data");  $("#submit").attr("disabled",false) ; $("#submit").attr("class","btn blue") ;  }
 else{ $("#submit").html("No action Chosen !");  $("#submit").attr("disabled",true) ; $("#submit").attr("class","btn red") ;    }
       
    
});

                  });       
</script>
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
 
   <script>
      jQuery(document).ready(function() {
          $('#form').select2(); 
          $('#lock_status').select2(); 
          
            $.ajax({
    url:'loadYear',
    type:'post',
    dataType:'html',
    success:function (data){
         //$("#year").html(data);
         loadmonths();
//        document.getElementById("year").innerHTML=data;
        
    }
    
    
});

            $.ajax({
    url:'load_to_lock_forms',
    type:'post',
    dataType:'html',
    success:function (data){
         $("#form").html(data);
         $('#form').select2(); 
//        document.getElementById("year").innerHTML=data;
        
    }
    
    
});

               $.ajax({
url:'loadMultipleFacilities',
type:'post',
dataType:'html',
success:function (data){
       $("#facility").html(data);
       $('#facility').select2(); 
//       App.init();  
}
});
});



function loadmonths(){
    
    var dt=new Date();
    
    
    var curmonth=dt.getMonth()+1;
   
       
        var curyr=dt.getYear()+1900;
     
        console.log("mwezi wa "+curmonth+"  mwaka wa "+curyr);
        
      var yr=document.getElementById("year").value;
//      alert(yr);
              $.ajax({
url:'loadLastMonth?year='+yr,
type:'post',
dataType:'html',
success:function (data){
    $("#month").html(data.replace("<option value=''>Select Month </option>",""));
    var select = document.getElementById('month');
                    //select.size = select.length;
    
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
                loadfacils();
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
            success:function (data)
            {
                $("#subcounty").html(data.replace("<option value=''>Select sub-county</option>",""));
                var select = document.getElementById('subcounty');
                    select.size = select.length;
                
              //  App.init();   
            }
            
            
        });
      return true;  
    }
    
    
       function loadsubcounty2(arr,far){
           
           
           
        
        var county=document.getElementById("county").value;
        $.ajax({
            url:'loadSubcounty?county='+county,
            type:'post',
            dataType:'html',
            success:function (data)
            {
                $("#subcounty").html(data.replace("<option value=''>Select sub-county</option>",""));
                var select = document.getElementById('subcounty');
                select.size = select.length;
                 $("#subcounty").val(arr);  
                 
                 loadfacil2(far);
              //  App.init();   
            }
            
            
        });
       
    }
    
    
    
    
    
       function loadfacils()
{
      var subcounty=$("#subcounty").val();
      
$.ajax({
url:'loadMultipleFacilities?subcounty='+subcounty,
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
    
 
    
    
       function loadfacil2( far)
{
      var subcounty=$("#subcounty").val();
      
$.ajax({
url:'loadMultipleFacilities?subcounty='+subcounty,
type:'post',
dataType:'html',
success:function (data){
       $("#facility").html(data);
         if(document.getElementById("facility").value!==''){
      updatefacilsession();
      
      
     
      }  
      $("#facility").val(far);
    $("#facility").select2(); 
         // $("#facility").chosen();
       
       
}


}); 
}
    
 
 loadcounty();
  
   </script>
   <script type="text/javascript" src="js/form731Totals.js"></script>
<script type="text/javascript" src="js/val731.js"></script>
<script type="text/javascript">
    
    
    function checkbkmark(){
        
         var county=$("#county").val();
           
          
        var subcounty=$("#subcounty").val();
        if(county!==''){
            
            
            $(".ujumbe").show();
        }
        else {
            
            $(".ujumbe").hide();
        }
        
    }
    

function savecluster(){
    
    var msg="";
    
            var county=$("#county").val();
            var cluster=$("#clustername").val();
          
            var subcounty=$("#subcounty").val();
       
            var facility=$("#facility").val();
            
            if(county===''){$("#ujumbe").html(" Select County "); $("#county").css("border-color","red");}
            else if(cluster===''){$("#ujumbe").html("Enter Bookmark Name"); $("#clustername").css("border-color","red");}
            
            else{
                
               $("#county").css("border-color","grey"); 
               $("#clustername").css("border-color","grey");
                
                $.ajax({
url:'saveFavoriteSites?county='+county+"&subcounty="+subcounty+"&facility="+facility+"&cluster="+cluster,
type:'post',
dataType:'html',
success:function (data){
    
   $("#ujumbe").html(data);
   
   if(data==='Bookmark saved successfully'){$("#clustername").val("");
   
        loadfavorite();
        }
       
       
}


});
                
            }
    
    
}

function showbookmarkselect(){
    
    
    
   
 if($('#iscluster:checkbox:checked').length > 0){
  //showtargets 

   $(".bookmarkselect").show();
  
 }
 else 
 {
         $(".bookmarkselect").hide();
 }
    
}


function loadfavorite(){
    
    
    
   
 $.ajax({
     url:'loadFavoriteSites',
     type:'post',
     dataType:'html',
     success: function (data) {
         
         $("#clusterlist").html(data);
         
         if(data.trim()===''){$(".bookmark").hide();} else { $(".bookmark").show(); }
                        
                    }
 });
    
}

loadfavorite();

function setorgunits(){
    
    if($("#clusterlist").val()!=="."){
    
    var cn=$("#clusterlist").find(':selected').data("county");
    var subc=$("#clusterlist").find(':selected').data("subcounty");
    var facil=$("#clusterlist").find(':selected').data("facil");
    console.log("sipitali ni "+facil);
    
    var scar="";
    var far="";
    
    
     if(subc!==null && typeof subc !==undefined){scar=subc.split(",");}
     if(facil!==null && typeof facil !==undefined){far=facil.split(",");}
    
    

    
    console.log("county ni _"+cn);
    $("#county").val(cn);
     loadsubcounty2(scar,far);
     
    }
    else{
        
        loadcounty();
        loadsubcounty();
        loadfacils();
        
    }
   
    
    
}

    </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>

