
<%@page import="scripts.dataPulls"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>BFCI</title>
	<!-- HTML5 Shim and Respond.js IE11 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 11]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	<!-- Meta -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="description" content="" />
	<meta name="keywords" content="">
	<meta name="author" content="Emmanuel Kaunda" />
	<!-- Favicon icon -->
        <link rel="shortcut icon" href="images/imis.png" width="20px" />

	<!-- vendor css -->
	<link rel="stylesheet" href="rmc_assets/css/style.css">
	 <link rel="stylesheet" type="text/css" href="rmc_assets/bootstrap-datepicker/css/datepicker.css" />
	  <link href="rmc_assets/assets/css/dataTables.bootstrap.min.css" rel="stylesheet">
 <link href="rmc_assets/css/responsive.bootstrap.min.css" rel="stylesheet">
 <link href="select2/css/select2.css" rel="stylesheet">
 
 
 
 <style>
            
            td{
 text-align: left ; 
 padding-left:1px;
 vertical-align:middle;
}
fieldset.formatter {
    border: 2px groove #0394ff !important;
   
    /*padding: 0 1.4em 1.4em 1.4em !important;*/
    margin: 0 0 1.5em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
   
}

legend.formatter {
    border: 0px groove #0394ff !important;
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
    width: 85%;
    margin-top: 5%;
    margin-bottom: 5%;
}
table{
    width: 100%;
}
tr > .st{
border-width: 2px;    
}
.title{
    font-weight: bolder;
    margin-bottom: 130px;
}
.indicator{
    min-width: 100px;
    margin: 2px 2px 2px 2px;
}
input[type=text]{
    border-color: black;
    border-width: 0.5px;
}
input[readonly]{
    background-color: #aeb5ae;
}

</style>
 
 
 
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
        <%@include file="menu/ramcah.jsp" %>
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
								<div class="toast-body">
									<p class='ujumbe'></p>
								</div>
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
								<a class="nav-link active text-uppercase" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Data Entry</a>
							</li>
<!--							<li class="nav-item">
								<a class="nav-link text-uppercase" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">View/Edit</a>
							</li>-->
							
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
                                <form action="#" class=''>
                                    
						<h2 class='btn btn-primary' style='text-align: center; width:100%;'><b>BFCI summary</b></h2>
					
                                    <div class="form-row">
                                        
                                        <%
                                        
                                        dataPulls dp= new dataPulls();
                                        

                                        
                                        %>
                                        
<!--<input required="true" value="<%=dp.getRandNo(1,10000)%>" type='hidden' class='form-control' id='grant_id' name='grant_id'  placeholder='Enter id'/>-->
                                        
<!--<div class='form-group col-md-1'></div>-->


<div class='form-group col-md-4'>
<label for='donor_name'><b>Organisation Type<font color='red'>*</font></b></label>
<select required="true" type='select' onchange="getFacilitiesJson();isdisplayindicators();" class='form-control' id='dataset' name='dataset'  ><option value='facility'>Facility</option></select>
<input type="hidden" value="bfci" name="formgroup" id="formgroup">

</div>

<div class='form-group col-md-4'>
<label for='donor_name'><b>Reporting Month<font color='red'>*</font></b></label>
<select required="true" type='select' onchange="getFacilitiesJson();isdisplayindicators();" class='form-control' id='period' name='period'  ></select>
</div>
                                        
<!--<div class='form-group col-md-2'></div>-->
                                        
<div class='form-group col-md-4'>
<label for='donor_name' id="ou"><b>Organization Unit<font color='red'>*</font></b></label>
<select onchange="isdisplayindicators();" required="true" type='input' class='form-control' id='facility' name='facility'  placeholder='Enter Donor Name'></select>
</div>

<!--<div class='form-group col-md-1'></div>-->                                    
                                    

                                    
                                    

</div>



<div class="form-row">
                                    

    
     <div class="accordion form-group col-md-12" id="form1a_accordion" >
                                            
                                            
                                            <!--__________________________________________________________________________________________________-->
                                            
                                          
                                                
     <label>Select Facility and Period to load the data entry form</label>                                     
                                         
                                            
                                            
                                            <!--__________________________________________________________________________________________________-->


                                                  

                                                </div>
                                    
                                    

                                    

 
</div>
     
      
<div class="toast hide toast-3s" role="alert" aria-live="assertive" data-delay="3000" aria-atomic="true">
		<div class="toast-header">
									
									
									<small class="text-muted"></small>
									<button type="button" class="m-l-5 mb-1 mt-1 close" data-dismiss="toast" aria-label="Close">
										<span>&times;</span>
									</button>
		</div>
								<div class="toast-body">
									<p  class='ujumbe'></p>
								</div>
		</div>
 </form>
                            </div>
                         
                        </div>
							
							
							
							
						</div>
					</div>
                                                    
                                                    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
							
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
    <script src="rmc_assets/js/vendor-all.min.js"></script>
    <script src="rmc_assets/js/plugins/bootstrap.min.js"></script>
    <script src="rmc_assets/js/pcoded.min.js"></script>
    <script src="select2/js/select2.js"></script>


   <script  src="rmc_assets/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
     
 <script type="text/javascript" src="rmc_assets/js/jquery.dataTables_1.3.min.js"></script>
    
   
    <script type="text/javascript" src="rmc_assets/js/dataTables.responsive.min.js"></script>
    
    
    
     <script type="text/javascript" src="ramcah/sum_values.js"></script>
        <script type="text/javascript" src="ramcah/validation.js"></script>
        <script type="text/javascript" src="ramcah/ajaxloads.js"></script>
    
    
       <%if (session.getAttribute("grants_response") != null) { %>
                                <script type="text/javascript"> 
                    
                    var uju='<%=session.getAttribute("grants_response")%>';
                    $('.ujumbe').html(uju);
                    $('.callalert').click();
                      
                    
                </script> <%
                session.removeAttribute("grants_response");
                            }

                        %>
    
    
<script>
    
     function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode;
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}



 

function loadEdits(elementtoappend,tbl,act,vwtbl,pkid){
      
  //now load the data
          $.ajax({
                    url:'dataPulls',                            
                    type:'post',
                    dataType: 'html',  
                    data:{act:act,
                        table_docker:elementtoappend,
			tablename:tbl,
			vwtable:vwtbl,pkid:pkid},
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
              "lengthChange": true, 
             
              "order": [[0,'desc']]});
 
    $('#searchtable_'+elementtoappend+' tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
             $('#btnDeleteRow').hide();
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
             $('#btnDeleteRow').show();
        }
    } );
 
    $('#btnDeleteRow').click( function () {
        
     var tablerowid=table.$('tr.selected').attr('id');
        
        if(ConfirmDelete()===true){
        
        deletedata(tablerowid);
        
        table.row('.selected').remove().draw( false );
        
        
                        
                    }});
          
 
        });
                        
                    }});    
         
           
            
          
    
}
    
  //loadEdits('dataset','ramcah_sum_indicators','loaddatasets','vw_grants_infor','grant_id'); 
  
  
  
  
  
  function loadExistingData(recordid,maintbl,pki)
{ 
    
    //once the edit form is clicked, the assumption is that the add section will be loaded
    $('#home-tab').click();
    $('#home-tab').html("Edit Grants");
    
   
           
            
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
for (const key in data[0]) {  
    
    var ky=key;
    var vl=data[0][key];
    
  $("#"+ky).val(vl);
}
                    }  
//});
                        
                  
//                         $('.dates').datepicker({
//                             todayHighlight: true, daysOfWeekDisabled: "0,6",clearBtn: true, autoclose: true,format: "yyyy-mm-dd",
//     });
     
   
                        
                        
                    });    
             
           
            
            
    
    
    
}

   function dltrw(rw,tbl,act,tblid){
     
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


function refreshPage(){
    
      window.location.reload();
    
}
  
  
  //loadSelectOptionsFromDb('dataset','getDataSets');


</script>    
</body>
</html>
