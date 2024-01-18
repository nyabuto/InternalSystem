<!DOCTYPE html>
<html lang="en">
<head>
	<title>Ramcah-Home</title>
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
		
		<!-- [ breadcrumb ] end -->
		<!-- [ Main Content ] start -->
		<div class="row">
			<!-- [ card ] start -->
			
			<div class="col-xl-12">
				<div class="row">
					<div class="col-sm-12 col-md-4">
						<h5>Indicators Summary</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">Indicators Summary</h5>
								<p class="card-text"></p>
                                                               
                                                                <br/>
								<a href="rmc_indicatorssummary.jsp" class="btn  btn-primary">Data Entry</a>
								
								
							</div>
						</div>
					</div>
					<div class="col-sm-12 col-md-4">
						<h5>Trainings</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">Participants list</h5>
								<p class="card-text"></p>
								<br/>
                                                                <a href="T1FORM.jsp" class="btn  btn-primary">Data Entry</a>
								
							</div>
						</div>
					</div>
                                    
                                    <div class="col-sm-12 col-md-4">
						<h5>Wash Summary</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">Wash Summary Forms</h5>
								<p class="card-text"></p>
														
								<br/>
								
                                                                <a target="blank" href="https://enketo.ona.io/x/dMrtIVW3" class="btn  btn-primary">Data Entry</a>
								
							</div>
						</div>
					</div>
                                    
                                   
					
				</div>
			</div>
                        
                      
                        <div class="col-xl-12">
				<div class="row">
					<div class="col-sm-12 col-md-4">
						<h5>Emonc (ODK)</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">EMonc Monthly Assignment Signal</h5>
								<p class="card-text"></p>
                                                               
                                                                <br/>
								<a href="https://enketo.ona.io/x/d9VLmDP4" target="_blank" class="btn  btn-primary">Data Entry</a>
								
								
							</div>
						</div>
					</div>
					<div class="col-sm-12 col-md-4">
						<h5>BFCI</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">BFCI Form</h5>
								<p class="card-text"></p>
								<br/>
                                                                <a href="rmc_bfci.jsp" class="btn  btn-primary">Data Entry</a>
                                                                
                                                                
                                                                <%@include file="ramcah/bfci_tracker_modal.jsp" %>        
                                                                
                                                                
								
							</div>
						</div>
					</div>
                                    
                                    <div class="col-sm-12 col-md-4">
						<h5>Outreach Data Reporting Tool</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">Manage Forms</h5>
								<p class="card-text"></p>
														
								<br/>
								
                                                                <a href="applicants.jsp" class="btn  btn-primary">Data Entry</a>
								
							</div>
						</div>
					</div>
                                    
                                   
					
				</div>
			</div>
                        
                        <div class="col-xl-12">
				<div class="row">
					<div class="col-sm-12 col-md-4">
						<h5>Data Verification</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">Data Verification forms</h5>
								<p class="card-text"></p>
                                                               
                                                                <br/>
								<a target="_blank" href="https://enketo.ona.io/x/F2BUMbLK" class="btn  btn-primary">Open Forms</a>
								<!--<a href="grants.jsp" class="btn  btn-primary">Reports</a>-->
								
							</div>
						</div>
					</div>
					
                                    
                                    <div class="col-sm-12 col-md-4">
						<h5>Functionality Score Card</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">Manage Forms</h5>
								<p class="card-text"></p>
														
								<br/>
								
                                                                <a href="applicants.jsp" class="btn  btn-primary">Data Entry</a>
								
							</div>
						</div>
					</div>
                                    
                                    <div class="col-sm-12 col-md-4">
						<h5>Pregnancy Mapping</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">ONA Form</h5>
								<p class="card-text"></p>
														
								<br/>
								
                                                                <a target="_blank" href="https://enketo.ona.io/x/bEPGpfQT" class="btn  btn-primary">Data Entry</a>
								
							</div>
						</div>
					</div>
                                    
					
				</div>                        
                            
			</div>
                        
                        
                        
                        
                        
                        <div class="col-xl-12">
				<div class="row">
                                    
                         <div class="col-sm-12 col-md-12">
						<h5>Binti Shujaa</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">Manage Forms</h5>
								<p class="card-text"></p>
								<br/>
                                                                <a href="https://usaidtujengejamii.org:8443/Cohorts/binti_shujaa.jsp?frm=binti_add_groups" class="btn  btn-primary">Add Groups</a>
                                                                <a href="https://usaidtujengejamii.org:8443/Cohorts/binti_shujaa.jsp?frm=binti_add_shujaas" class="btn  btn-primary">Add Binti Shujaas</a>
                                                                <a href="https://usaidtujengejamii.org:8443/Cohorts/binti_shujaa.jsp?frm=binti_enrollment" class="btn  btn-primary">Enrollment</a>
                                                                <a href="https://usaidtujengejamii.org:8443/Cohorts/binti_shujaa.jsp?frm=binti_anc_visits" class="btn  btn-primary">ANC Visits</a>
                                                                <a href="https://usaidtujengejamii.org:8443/Cohorts/binti_shujaa.jsp?frm=binti_delivery_immunization" class="btn  btn-primary">Immunization</a>
                                                                <a href="https://usaidtujengejamii.org:8443/Cohorts/binti_shujaa.jsp?frm=binti_pnc_visits" class="btn  btn-primary">Post Natal Services</a>
                                                                <a href="https://usaidtujengejamii.org:8443/Cohorts/binti_shujaa.jsp?frm=binti_followup" class="btn  btn-primary">Follow Up</a>
                                                                <a href="https://usaidtujengejamii.org:8443/Cohorts/binti_shujaa.jsp?frm=binti_exit" class="btn  btn-primary">Exit Form</a>
								
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
    
   <script type="text/javascript" src="js/jquery.fileDownload.js"></script>
    
   <script type="text/javascript" src="ramcah/defaults.js"></script>
          


</body>
</html>
