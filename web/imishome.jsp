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
	<link rel="stylesheet" href="rmc_assets/css/style.css">
	
	   <link rel="stylesheet" href="select2/css/select2.css"/>

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
 <li class="nav-item">
								<a class="nav-link" id="pills-moremodules-tab" data-toggle="pill" href="#pills-moremodules" role="tab" aria-controls="pills-moremodules" aria-selected="false"> <span class="pcoded-micon"><i class="feather icon-plus"></i></span>Extras</a>
							</li>
						</ul>
						<div class="tab-content" id="pills-tabContent">
							<div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
								
                                                            
                                                            <%@include file="menu/menu_i2_charts.jsp" %> 
                                                            
                                                            
							</div>
							<div class="tab-pane fade" id="pills-forms" role="tabpanel" aria-labelledby="pills-forms-tab">
								<div style="overflow-y:scroll; height:90vh;overflow-x:hidden ;">
                                                            <%@include file="menu/menu_i2_forms.jsp" %> 
                                                            </div>
                                                            
							</div>
							<div class="tab-pane fade" id="pills-gapanalysis" role="tabpanel" aria-labelledby="pills-gapanalysis-tab">
                                                           <%@include file="menu/menu_i2_gaps.jsp" %> 
							</div>
                                                            
                                                        <div class="tab-pane fade" id="pills-reprates" role="tabpanel" aria-labelledby="pills-reprates-tab">
                                                            <!----Reporting Rates---->
                                                            <%@include file="menu/menu_i2_missingreps.jsp"  %>
							</div>
                                                        <div class="tab-pane fade" id="pills-queries" role="tabpanel" aria-labelledby="pills-queries-tab">
                                                            <!----Queries---->
                                                           
                   <%@include file="queries/menu_i2_queries.jsp" %>                                           
                                                            
                                                            

                                                         
							</div>
                                                         <div class="tab-pane fade" id="pills-dataexchange" role="tabpanel" aria-labelledby="pills-dataexchange-tab">
                                                            <!----Data Exchange---->
                                                            
                                                            <%@include file="menu/menu_i2_dataexchange.jsp" %> 
                                                            
							</div>  
                                                            <div class="tab-pane fade" id="pills-emr" role="tabpanel" aria-labelledby="pills-emr-tab">
                                                            <!----EMR---->
                                                            <%@include file="menu/menu_i2_emr.jsp" %> 
							</div> 
                                                        <div class="tab-pane fade" id="pills-reports" role="tabpanel" aria-labelledby="pills-reports-tab">
                                                            <!----Excel Reports---->
                                                            <%@include file="menu/menu_i2_reports.jsp" %> 
							</div> 
                                                        <div class="tab-pane fade" id="pills-moremodules" role="tabpanel" aria-labelledby="pills-moremodules-tab">
                                                            <!----More Modules---->
							</div> 
                                                            
                                                        <div class="tab-pane fade" id="pills-management" role="tabpanel" aria-labelledby="pills-management-tab">
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

                                                           
<!--<script src="assets/js/jquery-1.8.3.min.js"></script>-->  
                                                           
<script src="select2/js/select2.js"></script>         
  <script src="analytics/loadfilters.js"></script>         
  <script src="queries/loadqueries.js"></script>         
                                                           <script>
                                                               
jQuery(document).ready(function() {
   
   
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
                                                               
                                                           </script>

</body>
</html>
