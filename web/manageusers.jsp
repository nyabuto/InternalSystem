<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="scripts.dataPulls"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Manage Users</title>
	<!-- HTML5 Shim and Respond.js IE11 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	
	<!-- Meta -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="description" content="" />
	<meta name="keywords" content="">
	<meta name="author" content="Emmanuel Kaunda" />
        <!--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">-->
	<!-- Favicon icon -->
        <link rel="icon" href="assets/images/grants.png" type="image/x-icon">

	<!-- vendor css -->
	<link rel="stylesheet" href="assets/css/style.css">
	 <link rel="stylesheet" type="text/css" href="assets/bootstrap-datepicker/css/datepicker.css" />
	  <link href="assets/css/dataTables.bootstrap.min.css" rel="stylesheet">
          <link href="assets/css/responsive.bootstrap.min.css" rel="stylesheet">
           <link href="select2/css/select2.css" rel="stylesheet">
 
</head>
<body class="">
	<!-- [ Pre-loader ] start -->
	<div class="loader-bg">
		<div class="loader-track">
			<div class="loader-fill"></div>
		</div>
	</div>
	<!-- [ Pre-loader ] End -->
	<!-- [ navigation menu ] start -->
        <%@include file="menu/menu.jsp" %>
	<!-- [ navigation menu ] end -->
	<!-- [ Header ] start -->
	<header class="navbar pcoded-header navbar-expand-lg navbar-light header-dark">
		
			
				<div class="m-header">
					<a class="mobile-menu" id="mobile-collapse" href="#!"><span></span></a>
					
					<a href="#!" class="mob-toggler">
						<i class="feather icon-more-vertical"></i>
					</a>
				</div>
            <%@include  file="menu/navbar.jsp"%>
				
			
	</header>
	<!-- [ Header ] end -->
	
	

<!-- [ Main Content ] start -->
<div class="pcoded-main-container">
	<div class="pcoded-content">
		<!-- [ breadcrumb ] start -->
		
                
                <div class="toast hide toast-3s" role="alert" aria-live="assertive" data-delay="3000" aria-atomic="true">
<div class="toast-header">
<small class="text-muted"></small>
<button type="button" class="m-l-5 mb-1 mt-1 close" data-dismiss="toast" aria-label="Close">
<span>&times;</span>
</button>
</div>
<div class="toast-body"><p class='ujumbe'></p></div>
</div>		
		
		
<p class='callalert' onclick="$('.toast-3s').toast('show')">.</p>
                
		<!-- [ breadcrumb ] end -->
		<!-- [ Main Content ] start -->
		<div class="row">
			<!-- [ card ] start -->
			
			<div class="col-xl-12">
				<div class="row">
				<div class="col-sm-12">
				<div class="card">
					
					<div class="card-body">
						<ul class="nav nav-tabs mb-3" id="myTab" role="tablist">
							<li class="nav-item">
								<a class="nav-link active text-uppercase" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true"><i class="feather icon-plus"></i> Users</a>
							</li>
							<li class="nav-item">
								<a class="nav-link text-uppercase" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false"><i class="feather icon-edit"></i> View/Edit</a>
							</li>
							
							
						</ul>
						<div class="tab-content" id="myTabContent">
							<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
								<p class="mb-0"> 
                                                                    <div class="card">
                    <div class="card-header">
                       
                    </div>
                    
<!--                        <h5>Form controls</h5>-->
                       
                        <div class="row">
                            <div class="col-md-12">
                                <form action="saveUserDetails" enctype="multipart/form-data" method="POST">
                                    
					
					<h5 class="btn-primary" style='text-align: center; width:100%;padding:5px;'><b>Add Users</b></h5>
                                    <div class="form-row">
                                        
                                        <%
              String uid="unknown";
                                            
              String ul="0";
                                            
               if (session.getAttribute("kd_session") != null) {
             
              HashMap<String,String> hm=new HashMap<String,String>();
            
            hm=(HashMap<String, String>)session.getAttribute("kd_session");
            
             uid=hm.get("userid");
             ul=hm.get("level");
                                            }
                                        
                                        dataPulls dp= new dataPulls();
                                        
                                        %>
                                        
                                        
                                        <a id="dwn"></a>

<input required="true" value="<%=uid%>" type='hidden' class='form-control' id='user_id' name='user_id'  placeholder='User id'/>
<input readonly="true" required="true" value="<%=dp.getRandNo(1,10000)%>" type='hidden' class='form-control' id='table_id' name='table_id'  placeholder='Enter id'/>           
 
                 
<div class='form-group col-md-4'>
<label for='level'><b>User Level<font color='red'>*</font></b></label>
<select onchange="showOrgUnits();" required="true"  class='form-control' id='level' name='level' >
    
    
    
    <option value=''>Select User Level</option>
    <%   %>
    
   
    
    
    <% if(new Integer(ul)>1){ out.println("<option value='1'>Facility Level</option>");} %>
    <% if(new Integer(ul)>2){ out.println("<option value='2'>Sub-county Level</option>");} %>
    <% if(new Integer(ul)>3){ out.println("<option value='3'>Regional Level</option>");} %>
    <% if(new Integer(ul)>4){ out.println("<option value='4'>County Level</option>");} %>
    <% if(new Integer(ul)>=5){ out.println("<option value='5'>Project Level</option>");} %>
    
    
</select>
</div>      
      
      
      
<div style="display:none" class='form-group col-md-4 county'>
    <label for='county'><b>County<font color='red'><i id="county_rq"></i></font></b></label>
<select onchange="loadSelectOptionsPerField('Region','getRegion','');"   class='form-control' id='county' name='county' >
    
</select>
</div>

<div style="display:none" class='form-group col-md-4 Region'>
<label for='Region'><b>Region<font color='red'><i id="Region_rq"></i></font></b></label>
<select onchange="loadSelectOptionsPerField('subcounty','getSubcounty','');"   class='form-control' id='Region' name='Region' ></select>
</div>

      
<div style="display:none" class='form-group col-md-4 subcounty'>
<label for='subcounty'><b>Sub-county<font color='red'><i id="subcounty_rq"></i></font></b></label>
<select onchange="loadSelectOptionsPerField('mflcode','getSites','');"   class='form-control' id='subcounty' name='subcounty' ></select>
</div>
      

      
<div style="display:none" class='form-group col-md-4 mflcode'>
<label for='mflcode'><b>Facility<font color='red'><i id="mflcode_rq"></i></font></b></label>
<select onchange=""   class='form-control' id='mflcode' name='mflcode' ></select>
</div>

     
      
<div class='form-group col-md-4'>
<label for='user_role'><b>User Role<font color='red'>*</font></b></label>
<select onchange="" required="true"  class='form-control' id='user_role' name='user_role' >
    
 <option title="" value="">Select Role</option>
 <option title="Can create, edit, update, delete a CQI Project, upload documents, view reports for his facility" value="Facility">Facility CQI Focal Person</option>
 <option title="Can create accounts, cannot create a project, edit a project, can download CQI projects, can view all reports" value="Manager">Manager - CQI Manager</option>
 <option title="Can create, edit, update, delete a CQI Project, upload documents, view reports for his facility" value="Admin">CQI Module Administrator</option>

</select>


</div>
      
      
<div class='form-group col-md-4'>
<label for='fname'><b>First Name<font color='red'>*</font></b></label>
<input required="true" type='input' class='form-control' id='fname' name='fname'  placeholder='First Name'/>  
</div>
      
      
      <div class='form-group col-md-4'>
<label for='mname'><b>Middle Name</b></label>
<input  type='input' class='form-control' id='mname' name='mname'  placeholder='Middle Name'/>  
</div>
      
<div class='form-group col-md-4'>
<label for='lname'><b>Last Name<font color='red'><i id="">*</i></font></b></label>
<input required="true" type='input' class='form-control' id='lname' name='lname'  placeholder='Last Name'/>  
</div>
      
      
<div class='form-group col-md-4'>
<label for='username'><b>User name<font color='red'><i id="username_rq">*</i></font></b></label>
<input required="true" type='input' class='form-control' id='username' name='username'  placeholder='Username'/>  
</div>
   
   <div class='form-group col-md-4'>
<label for='password'><b>Password<font color='red'>*</font></b></label>
<input onblur="checkvariables();" required="true" type='password' class='form-control' id='password' name='password'  placeholder='Password'/>  
</div>
   
<div class='form-group col-md-4'>
<label for='confirmpassword'><b>Confirm Password<font color='red'>*</font></b></label>
<input onblur="" required="true" type='password' class='form-control' id='confirmpassword' name='confirmpassword'  placeholder='Confirm Password'/>  
</div>   


<div class='form-group col-md-4'>
<label for='email'><b>Email Address<font color='red'>*</font></b></label>
<input onblur="checkvariables();" required="true" type='input' class='form-control' id='email' name='email'  placeholder='Email adress'/>  
</div> 
      
      
<div class='form-group col-md-4'>
<label for='phoneno'><b>Phone Number</b></label>
<input onblur="checkvariables();" onkeypress='return numbers(event);' type='input' class='form-control' id='phoneno' name='phoneno'  placeholder='Phone number'/>  
</div>      

      

 <div class='form-group col-md-4'>
<label for='designition'><b>Designation<font color='red'>*</font></b></label>

<select class='form-control' id='designition' name='designition'  >
<option value=''>Select Option</option>
<option value='SMT'>SMT Member</option>
<option value='STO'>Senior Technical Officer</option>
<option value='TIO'>Technical Integration Officer</option>
<option value='MEO'>Monitoring and Evaluation Officer</option>
<option value='MEA'>Monitoring and Evaluation Associate</option>
<option value='ARO'>Adherence and Retention Officer</option>
<option value='Clinical Officer'>Clinical Officer</option>
<option value='Nurse'>Nurse</option>
<option value='HTS Counsellor'>HTS Counsellor</option>
<option value='HRIO'>HRIO/HRIT</option>
<option value='Data Clerk'>Data Clerk</option>
<option value='Others'>Other</option>
</select>  

</div>


            </div>

<div class="form-row"> 
    

            
            <div class='form-group col-md-4'>
                <label for='submit_frm'><b>.</b></label>
                <button id="submitbtn" onmouseover="checkvariables();" id="submit_frm" type="submit" class="btn  btn-primary btn-sm form-control">Submit</button>   
            
         </div>
         </div>
       
        

 </form>
                 <div class="toast hide toast-3s" role="alert" aria-live="assertive" data-delay="3000" aria-atomic="true">
<div class="toast-header">
<small class="text-muted"></small>
<button type="button" class="m-l-5 mb-1 mt-1 close" data-dismiss="toast" aria-label="Close">
<span>&times;</span>
</button>
</div>
<div class="toast-body"><p class='ujumbe'></p></div>
</div>	                       
                                        
                                        
                                        
                            </div>
                         
                        </div>
							
							
							
							
						</div>
					</div>
                                                    
                                                    <div class="tab-pane fade active" id="profile" role="tabpanel" aria-labelledby="profile-tab">
							
                                                        <div id="searchtable">
                                  Edit Form
                                                                      </div> 
                                                        
                                                        </div>
				</div>
			</div>
					
				</div>
			</div>
                        
			
			<!-- [ card ] end -->
		</div>
		<!-- [ Main Content ] end -->
	</div>
</div>


    <!-- Required Js -->
    <script src="assets/js/vendor-all.min.js"></script>
    <script src="assets/js/plugins/bootstrap.min.js"></script>
    <script src="assets/js/pcoded.min.js"></script>


    <script  src="assets/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="assets/js/jquery.dataTables_1.3.min.js"></script> 
    <script type="text/javascript" src="assets/js/dataTables.responsive.min.js"></script>
    <script type="text/javascript" src="assets/js/jquery.fileDownload.js"></script>
    <script type="text/javascript" src="select2/js/select2.js"></script>
    
    
   <%if (session.getAttribute("users_response") != null) { %>
   <script type="text/javascript"> 
                    
                    var uju='<%=session.getAttribute("users_response")%>';
                    $('.ujumbe').html(uju);
                    $('.callalert').click();
                      
                    
                </script> <%
                session.removeAttribute("users_response");
                            }

    %>
    
    
     <%if (session.getAttribute("file_upload") != null) { %>
   <script type="text/javascript"> 
                    
                    var uju='<%=session.getAttribute("file_upload")%>';
                    $('.ujumbe').html(uju);
                    $('.callalert').click();
                      
                    
                </script> <%
                session.removeAttribute("file_upload");
                            }

    %>
    
    
<script>
    
     function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode;
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}


   $(".tarehe_kitambo").datepicker({
    endDate: "now()",
    clearBtn: true,
    format: "yyyy-mm-dd"
}).on('changeDate', function(ev){
    $(this).datepicker('hide');
//    var mk=$("#weekstart").val();
////    var mk=addDays($("#weekstart").val(),6);
//    //alert(mk);
//    $("#weekend").val(mk);
});



      $(".tarehe").datepicker({
    startDate: "2021-05-01",
    endDate: "now()",
    clearBtn: true,
    format: "yyyy-mm-dd"
}).on('changeDate', function(ev){
    $(this).datepicker('hide');
//    var mk=$("#weekstart").val();
////    var mk=addDays($("#weekstart").val(),6);
//    //alert(mk);
//    $("#weekend").val(mk);
});


    $(".tarehe_kesho").datepicker({
    startDate: "2021-05-01",
    clearBtn: true,
    format: "yyyy-mm-dd"
}).on('changeDate', function(ev){
    $(this).datepicker('hide');
//    var mk=$("#weekstart").val();
////    var mk=addDays($("#weekstart").val(),6);
//    //alert(mk);
//    $("#weekend").val(mk);
});




 

function loadEdits(elementtoappend,tbl,act,vwtbl,pkid){
      
  //now load the data
          $.ajax({
                    url:'dataPulls',                            
                    type:'post',
                    dataType: 'html',  
                    data:{act:act,
                        table_docker:elementtoappend,
			tablename:tbl,
			vwtable:vwtbl,pkid:pkid,orgunitfilter:'yes'},
                    success: function(data) 
                    {
                        
            $(document).ready(function(){
                        
                        var dt = data;
            
//<label class='btn btn-success'>Edit</label>          
$("#"+elementtoappend).html(""+dt);
              
//          $('#searchtable_'+elementtoappend).DataTable({              
//              "autoWidth": true,
//              "paging": true,
//              "pagingType": "full",
//              "lengthChange": false                    
//          });
          
          
          var table = $('#searchtable_'+elementtoappend).DataTable({
              "autoWidth": true,
              "paging": true,
              "pagingType": "full",              
              "lengthChange": true,  responsive:true,
              "order": [[0,'desc']]});
 
//    $('#searchtable_'+elementtoappend+' tbody').on( 'click', 'tr', function () {
//        if ( $(this).hasClass('selected') ) {
//            $(this).removeClass('selected');
//             $('#btnDeleteRow').hide();
//        }
//        else {
//            table.$('tr.selected').removeClass('selected');
//            $(this).addClass('selected');
//             $('#btnDeleteRow').show();
//        }
//    } );
 
//    $('#btnDeleteRow').click( function () {
//        
//     var tablerowid=table.$('tr.selected').attr('id');
//        
//        if(ConfirmDelete()===true){
//        
//        deletedata(tablerowid);
//        
//        table.row('.selected').remove().draw( false );
//        
//        
//                        
//                    }});
          
 
        });
                        
                    }});    
         
           
            
          
    
}
    
  loadEdits('searchtable','users','loadedits','vw_users','table_id'); 
  
  
  
  
  function loadExistingData(recordid,maintbl,pki)
{ 
    
    //once the edit form is clicked, the assumption is that the add section will be loaded
    $('#home-tab').click();
    $('#home-tab').html("Edit Users");
    
   
//    console.log("_"+fc+"vs"+dt);

            
 
            
           
            
            //now load the data
          $.ajax({
                    url:'dataPulls',                            
                    type:'post',  
                    data:{
                        act:'loadrecordx',
                        tablename:maintbl,
                        pkid:pki,
                        pkidval:recordid
                    },
                    dataType: 'json',  
                    success: function(data) 
                    {
                        console.log(data);
                        
                        const keys = Object.keys(data[0]);  
                        console.log(keys);
for (const key in data[0]) 
{  
    
    var ky=key;
    var vl=data[0][key];
    
    
    //don't include 
    if(vl.indexOf(".")<0)
    {
        
   if(ky!=="password" && ky!=="confirmpassword" ){
        
     $("#"+ky).val(vl);
     
                       }
    }
  
  console.log("::"+ky);
  if(ky==='username'){
      
      showOrgUnits();
     
  }
  
}


                    }  
//});
                        
                  
//                         $('.dates').datepicker({
//                             todayHighlight: true, daysOfWeekDisabled: "0,6",clearBtn: true, autoclose: true,format: "yyyy-mm-dd",
//     });
     
   
                        
                        
                    });    
             
           
            
            
    
    
    
}

  
  
 function loadSelectOptionsPerField(elemid,act,selval)
 {
    
    
    var fc="";
    var cnt="";
    var sct="";
    var rgn="";
    if($("#mflcode").val()!==null){fc=$("#mflcode").val();}
    if($("#county").val()!==null){cnt=$("#county").val();}
    if($("#subcounty").val()!==null){sct=$("#subcounty").val();}
    if($("#Region").val()!==null){rgn=$("#Region").val();}
    
            //now load the data
          $.ajax({
                    url:'dataPulls',                            
                    type:'post',  
                    dataType: 'html',  
                    data:{act:act,                         
                         loadmtrs_sel_val:selval,fc:fc,cnt:cnt,sct:sct,rgn:rgn},
                    success: function(data) 
                    {
                        var dt = data;
       
          $("#"+elemid).html(dt);
          if(elemid==='mflcode'){
                        $('#mflcode').select2(); 
                    }
                     if(elemid==='county'){
                        $('#county').select2(); 
                    }
                     if(elemid==='Region'){
                        $('#Region').select2(); 
                    }
                     if(elemid==='subcounty')
                     {
                        $('#subcounty').select2(); 
                    }
                    }});    
         
           
   
          
    
}
  
          
     loadSelectOptionsPerField('mflcode','getSites','');
     loadSelectOptionsPerField('county','getCounties','');
     loadSelectOptionsPerField('subcounty','getSubcounty','');
     loadSelectOptionsPerField('Region','getRegion','');
  
      



 function dltrw(rw,tbl,act,tblid)
 {
     
     var result = confirm("Are you sure you want to delete this row?");
if (result) {
    //Logic to delete the item

    
            //now load the data
          $.ajax({
                    url:'dataPulls',                            
                    type:'post',  
                    dataType: 'html',  
                    data:{act:act,                         
                          tablename:tbl,
                          pkid:tblid,
                        pkidval:rw},
                    success: function(data) 
                    {
                        var dt = data;
       
          refreshPage();
                        
                    }});    
            }
           
   
          
    
}

 function OpenFile(path)
 {
     
  //alert("Open File Called");
    //Logic to open the file
    
    
    var pt=decodeURIComponent(path);
    console.log(path);
    console.log(pt);
    
    var ur ="downloadFiles?fp=" + pt;
                    console.log(ur);
                    $.fileDownload(ur).done(function () {                       
                    }).fail(function () {
                        alert('File generation failed, kindly try again!');
                       
                    });

    
}


function refreshPage(){
    
      window.location.reload();
    
}


$(document).ready(function(){

});


function showOrgUnits(){
    
    var ul=$("#level").val();
    
    
    if(ul==='1')//facility
    {
        
        $(".county").show(); $("#county_rq").html("*");$("#county").select2();$("#county").attr("required", true);
       
        $(".subcounty").show(); $("#subcounty_rq").html("*");$("#subcounty").select2();$("#subcounty").attr("required", true);
        
        $(".Region").show(); $("#Region_rq").html("*");$("#Region").select2();$("#Region").attr("required", true);
        
        $(".mflcode").show();  $("#mflcode_rq").html("*");$("#mflcode").select2();$("#mflcode").attr("required", true); 
        
    }
     else if(ul==='2')//subcounty
    {
        
        $(".county").show(); $("#county_rq").html("*");$("#county").select2();$("#county").attr("required", true);
       
        $(".subcounty").show(); $("#subcounty_rq").html("*");$("#subcounty").select2();$("#subcounty").attr("required", true);
        
        $(".Region").show(); $("#Region_rq").html("*");$("#Region").select2();$("#Region").attr("required", true);
        
        $(".mflcode").hide(); $("#mflcode_rq").html("");$("#mflcode").select2(); $("#mflcode").removeAttr("required"); 
        
    }
    
     else if(ul==='3')//region
    {
        
        $(".county").show(); $("#county_rq").html("*");$("#county").select2();$("#county").attr("required", true); 
        $(".Region").show(); $("#Region_rq").html("*");$("#Region").select2();$("#subcounty").attr("required", true);
        $(".subcounty").hide(); $("#subcounty_rq").html("");$("#subcounty").select2();$("#subcounty").removeAttr("required"); 
        $(".mflcode").hide(); $("#mflcode_rq").html("");$("#mflcode").select2(); $("#mflcode").removeAttr("required"); 
        
    }
    
     else if(ul==='4')//county
    {
        
        $(".county").show(); $("#county_rq").html("*");$("#county").select2();$("#county").attr("required", true);
       
        $(".subcounty").hide(); $("#subcounty_rq").html("");$("#subcounty").select2();$("#subcounty").removeAttr("required");
        
        $(".Region").hide(); $("#Region_rq").html("");$("#Region").select2();$("#Region").removeAttr("required");
        
        $(".mflcode").hide(); $("#mflcode_rq").html("");$("#mflcode").select2(); $("#mflcode").removeAttr("required"); 
        
    }
     else if(ul==='5')//program
    {
        
        $(".county").hide(); $("#county_rq").html("");$("#county").select2();$("#county").removeAttr("required");
       
        $(".subcounty").hide(); $("#subcounty_rq").html("");$("#subcounty").select2();$("#subcounty").removeAttr("required");
        
        $(".Region").hide(); $("#Region_rq").html("");$("#Region").select2();$("#Region").removeAttr("required");
        
        $(".mflcode").hide(); $("#mflcode_rq").html("");$("#mflcode").select2(); $("#mflcode").removeAttr("required"); 
        
    }
    
    
    
  $(".select2-container").css('width','100%');  
    
}




function checkvariables(){
  
 var pn=$("#phoneno").val();
 var pw=$("#password").val();
 var cpw=$("#confirmpassword").val();
 var eml=$("#email").val();
    
    
    if(pn!=='')
    {
    if(pn.length!==10)
    {
     $('.callalert').click();
     $("#phoneno").focus();   
     $(".ujumbe").html("<font color='red'>Please enter a 10 digit Phone number</font>");  
      $("#submitbtn").hide();  
    }
    else {
       $("#submitbtn").show();   
        
    }
       
        
    }
    
    else if(eml!=='')
    {
   if(eml.indexOf(".")<0 || eml.indexOf("@")<0 )
    {
     $('.callalert').click();
     $("#email").focus();   
     $(".ujumbe").html("<font color='red'>Please Enter Email Address in the required complete format</font>");
      $("#submitbtn").hide();
    }
    else 
    {
      $("#submitbtn").show();   
        
    }
        
    }
    else  {
     if(pw!==''  && cpw!=='')   
     {
      
      if(pw!==cpw){ $("#confirmpassword").focus();  $('.callalert').click(); $(".ujumbe").html("<font color='red'>Please ensure that the two Passwords are matching</font>");   $("#submitbtn").hide();} 
      
     else  {
          $("#submitbtn").show();
           }
     
      
         
     }
        
        
    }
    
    
    
}
</script>    
</body>
</html>
