<!DOCTYPE html>
<html lang="en">
   
<head>
	<title>IMIS</title>
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
	<!--<link rel="stylesheet" href="rmc_assets/css/style_2.css">-->
        
        
        
         <link rel="stylesheet" href="rmc_assets/css/style_2.css" >
    <link rel="stylesheet" href="rmc_assets/css/style-preset.css" >
    
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500;600;700&display=swap" rel="stylesheet" />

<!-- [Tabler Icons] https://tablericons.com -->
<link rel="stylesheet" href="rmc_assets/fonts/tabler-icons.min.css" />
<!-- [Feather Icons] https://feathericons.com -->
<link rel="stylesheet" href="rmc_assets/fonts/feather.css" />
<!-- [Font Awesome Icons] https://fontawesome.com/icons -->
<link rel="stylesheet" href="rmc_assets/fonts/fontawesome.css" />                                                  
<!-- [Material Icons] https://fonts.google.com/icons -->
<link rel="stylesheet" href="rmc_assets/fonts/material.css" />
   
        
	
	   <link rel="stylesheet" href="Login_v6/vendor/select2/select2.css"/>
<!--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">-->

<%if(session.getAttribute("kd_session")!=null){%><%} else {  response.sendRedirect("logout");}%> 
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
        <%@include file="menu/menu_i2.jsp" %>
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
		
		<!-- [ breadcrumb ] end -->
		<!-- [ Main Content ] start -->
                                
                
                <div class="col-sm-12">
				<!--<h5 class="mb-3" style="text-align: center;">IMIS</h5>-->
				<div class="card">
					<div class="card-body">
						<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                                                    <%if(session.getAttribute("userAccess")!=null){%>
							<li class="nav-item">
								<a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true"> <span class="pcoded-micon"><i class="feather icon-bar-chart"></i></span>  Home</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" id="pills-forms-tab" data-toggle="pill" href="#pills-forms" role="tab" aria-controls="pills-forms" aria-selected="false"> <span class="pcoded-micon"><i class="feather icon-info"></i></span> Forms</a>
							</li>
                                                        <li class="nav-item">
								<a class="nav-link" id="pills-reprates-tab" data-toggle="pill" href="#pills-reprates" role="tab" aria-controls="pills-reprates" aria-selected="false"> <span class="pcoded-micon"><i class="feather icon-clock"></i></span>Reporting Rates</a>
							</li>
                                                        
                                                        <li class="nav-item">
								<a class="nav-link" id="pills-gapanalysis-tab" data-toggle="pill" href="#pills-gapanalysis" role="tab" aria-controls="pills-gapanalysis" aria-selected="false"> <span class="pcoded-micon"><i class="feather icon-activity"></i></span>Gap Analysis</a>
							</li>
                                                        
                                                         
                                                        
                                                         <li class="nav-item">
								<a class="nav-link" id="pills-dataexchange-tab" data-toggle="pill" href="#pills-dataexchange" role="tab" aria-controls="pills-dataexchange" aria-selected="false">  <span class="pcoded-micon"><i class="feather icon-cast"></i></span>Data Exchange</a>
							</li>
                                                         <li class="nav-item">
							<a class="nav-link" id="pills-emr-tab" data-toggle="pill" href="#pills-emr" role="tab" aria-controls="pills-emr" aria-selected="false">  <span class="pcoded-micon"><i class="feather icon-link"></i></span>  EMR</a>
							</li>
                                                        
							<li class="nav-item">
								<a class="nav-link" id="pills-reports-tab" data-toggle="pill" href="#pills-reports" role="tab" aria-controls="pills-reports" aria-selected="false"> <span class="pcoded-micon"><i class="feather icon-bar-chart-2"></i></span>   Excel Reports</a>
							</li>
                                                       
                                                        
                                                        <li class="nav-item">
								<a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-queries" role="tab" aria-controls="pills-queries" aria-selected="false"> <span class="pcoded-micon"><i class="feather icon-command"></i></span> Queries</a>
							</li>
                                                        <li class="nav-item">
								<a class="nav-link" id="pills-management-tab" data-toggle="pill" href="#pills-management" role="tab" aria-controls="pills-management" aria-selected="false"> <span class="pcoded-micon"><i class="feather icon-settings"></i></span>  Management</a>
							</li>
<!--                                                         <li class="nav-item">
								<a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-resources" role="tab" aria-controls="pills-resources" aria-selected="false"> <span class="pcoded-micon"><i class="feather icon-external-link"></i></span>  Resources</a>
							</li>-->
                                                        <%%> 
                                                        <li class="nav-item">
								<a class="nav-link" id="pills-moremodules-tab" data-toggle="pill" href="#pills-moremodules" role="tab" aria-controls="pills-moremodules" aria-selected="false"> <span class="pcoded-micon"><i class="feather icon-plus"></i></span>Extras</a>
							</li>
                                                        



  <%}%>
						</ul>
						<div class="tab-content" id="pills-tabContent">
							<div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
			<h4 class="well btn-primary" style="text-align: center;border-radius:5px; padding: 10px;"> <i class="feather icon-bar-chart-2"></i> Dashboards</h4>	
<!--                                                            <hr>-->

                                                     <!--This is the regional and date filter-->
                                                               <%@include file="analytics/filter_elements.jsp" %> 

                                                            <div style="overflow-y:scroll; height:90vh;overflow-x:hidden ;">
                                                                <!--All the charts and dasboards are defined here-->
                                                                <%@include file="analytics/menu_i2_charts.jsp" %> 
                                                            
                                                            </div>
                                                            
							</div>
							<div class="tab-pane fade" id="pills-forms" role="tabpanel" aria-labelledby="pills-forms-tab">
		<h4 class="well btn-primary" style="text-align: center;border-radius:5px; padding: 10px;"> <i class="feather icon-clipboard"></i> Forms</h4>	
                        
                                                            <div style="overflow-y:scroll; height:90vh;overflow-x:hidden ;">
                                                                
                                                            <%@include file="menu/menu_i2_forms.jsp" %> 
                                                            </div>
                                                            
							</div>
							<div class="tab-pane fade" id="pills-gapanalysis" role="tabpanel" aria-labelledby="pills-gapanalysis-tab">
                                                            
                                                           <h4 class="well btn-primary" style="text-align: center;border-radius:5px; padding: 10px;"> <i class="feather icon-activity"></i>GAP Analysis</h4>
                                                            
                                                           <%@include file="menu/menu_i2_gaps.jsp" %> 
							</div>
                                                            
                                                        <div class="tab-pane fade" id="pills-reprates" role="tabpanel" aria-labelledby="pills-reprates-tab">
                                                            <h4 class="well btn-primary" style="text-align: center;border-radius:5px; padding: 10px;"> <i class="feather icon-watch"></i>Reporting Rates</h4>
                                                            <!----Reporting Rates---->
                                                            <%@include file="menu/menu_i2_missingreps.jsp"  %>
							</div>
                                                        <div class="tab-pane fade" id="pills-queries" role="tabpanel" aria-labelledby="pills-queries-tab">
                                                            <!----Queries---->
                                                           
                                                        <%@include file="queries/menu_i2_queries.jsp" %>                                           
                                                            
                                                            

                                                         
							</div>
                                                         <div class="tab-pane fade" id="pills-dataexchange" role="tabpanel" aria-labelledby="pills-dataexchange-tab">
                                                            <!----Data Exchange---->
                                                            <h4 class="well btn-primary" style="text-align: center;border-radius:5px; padding: 10px;"> <i class="feather icon-disc"></i> Data Import/Export </h4>
                                                            <%@include file="menu/menu_i2_dataexchange.jsp" %> 
                                                            
							</div>  
                                                    <div class="tab-pane fade" id="pills-emr" role="tabpanel" aria-labelledby="pills-emr-tab">
                                                        
                                                            <!----EMR---->
                    <h4 class="well btn-primary" style="text-align: center;border-radius:5px; padding: 10px;"> <i class="feather icon-clipboard"></i> EMR</h4>
                                                            <%@include file="menu/menu_i2_emr.jsp" %> 
							</div> 
                                                        <div class="tab-pane fade" id="pills-reports" role="tabpanel" aria-labelledby="pills-reports-tab">
                            <h4 class="well btn-primary" style="text-align: center;border-radius:5px; padding: 10px;"> <i class="feather icon-clipboard"></i>Report Outputs</h4>
                                                            <!----Excel Reports---->
                                                            <%@include file="menu/menu_i2_reports.jsp" %> 
							</div> 
                                                        <div class="tab-pane fade" id="pills-moremodules" role="tabpanel" aria-labelledby="pills-moremodules-tab">
                          <h4 class="well btn-primary" style="text-align: center;border-radius:5px; padding: 10px;"> <i class="feather icon-clipboard"></i> More Reporting Modules</h4>
                                                            <!----More Modules---->
                                                            
                                                            <%@include file="menu/menu_i2_extras.jsp" %> 
                                                            
							</div> 
                                                            
                                                        <div class="tab-pane fade" id="pills-management" role="tabpanel" aria-labelledby="pills-management-tab">
                           <h4 class="well btn-primary" style="text-align: center;border-radius:5px; padding: 10px;"> <i class="feather icon-settings"></i>System Management</h4>
                                                            <!----Manaegements---->
                                                            
                                                            <%@include file="menu/menu_i2_management.jsp" %>
							</div>
                                                         <div class="tab-pane fade" id="pills-resources" role="tabpanel" aria-labelledby="pills-resources-tab">
                                                            <!----Resources---->
							</div>
                                                            
                                                            
						</div>
                                                        
                                                        
					</div>
				</div>
			</div>
                
		
		<!-- [ Main Content ] end -->
	</div>
</div>


    <!-- Required Js -->
    <script src="rmc_assets/js/vendor-all.min.js"></script>
    <script src="rmc_assets/js/plugins/bootstrap.min.js"></script>
    <script src="rmc_assets/js/pcoded.min.js"></script>
<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                       <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>                                    -->
<!--<script src="assets/js/jquery-1.8.3.min.js"></script>-->  
                                                           
<script src="Login_v6/vendor/select2/select2.js"></script>         
  <script src="analytics/loadimisfilters.js"></script>         
  <script src="queries/loadqueries.js"></script>         
                                                           <script>
                                                               
//jQuery(document).ready(function() {
   
   
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

 loadcounty();

//$('#county').select2();

//});
                                                               
</script>




<script > 
    // $(document).ready(function(){
       // $("#progress_area").hide();
       // $("#upload_area").show();
         
    
     setInterval(function() {
      load_records();
      }, 1000);  
    
     //});
     
     function load_records(){
             $.ajax({
        url:'issessionactive',
        type:"post",
        dataType:"html",
        success:function(dt){
           // console.log(dt);
if(dt.trim()==='true'){} else {

window.location.href = "logout.jsp";


}         
        }, 
        error: function(jqXHR, textStatus, errorThrown) {
       //error in loading upload status
       $("#status").html(errorThrown);
            }
  });
     }
     load_records();
     
     
     
     
     
     
     
           $(document).ready(function () {
    const lp = localStorage.getItem("lastpage");
    if (lp) 
    {
//        console.log("Last Page is :"+lp);
        
       $(lp).click(); // Outputs: Hello from the main page!
    }
});
    
     
     
</script>

</body>
</html>
