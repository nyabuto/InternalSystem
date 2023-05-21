<!DOCTYPE html>
<html lang="en">
<head>
	<title>Grants-Home</title>
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
        <link rel="icon" href="assets/images/grants.png" type="image/x-icon">

	<!-- vendor css -->
	<link rel="stylesheet" href="assets/css/style.css">
	
	

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
		
		<!-- [ breadcrumb ] end -->
		<!-- [ Main Content ] start -->
		<div class="row">
			<!-- [ card ] start -->
			
			<div class="col-xl-12">
				<div class="row">
					<div class="col-sm-12 col-md-4">
						<h5>Grants Information</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">Manage Grants</h5>
								<p class="card-text">This section helps to manage grants details. Here you can add, edit or delete Grants details</p>
                                                                <br/>
                                                                <br/>
                                                                <br/>
								<a href="grants.jsp" class="btn  btn-primary">Manage Grants</a>
								
							</div>
						</div>
					</div>
					<div class="col-sm-12 col-md-4">
						<h5>Solicitation </h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">Manage Solicitations</h5>
								<p class="card-text">This is a module to capture data about various NOFOs that the activity may share publicly for interested parties to apply. Here you can add, edit or delete Solicitation details</p>
								<br/>
                                                                <a href="solicitation.jsp" class="btn  btn-primary">Manage Solicitation</a>
								
							</div>
						</div>
					</div>
                                    
                                    <div class="col-sm-12 col-md-4">
						<h5>Applicants </h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">Manage Applicants</h5>
								<p class="card-text">Here we are capturing the applicants details and as well Evaluating them.The sections also helps manage mandatory Requirements Check.Here you can add, edit or delete Applicant details</p>
														
								<br/>
								
                                                                <a href="applicants.jsp" class="btn  btn-primary">Manage Applicants</a>
								
							</div>
						</div>
					</div>
					
				</div>
			</div>
                        
			<div class="col-xl-12">
				<div class="row">
					<div class="col-sm-12 col-md-4">
						<h5>Evaluation</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">Conduct Evaluations</h5>
								<p class="card-text">Here we are evaluating the various applicants and scoring/rating them on various sections/criteria. Here you can add, edit or delete Evaluation details</p>
                                                                <br/>
                                                                
                                                                <br/>
								<a href="evaluation.jsp" class="btn  btn-primary">Manage Evaluations</a>
								
							</div>
						</div>
					</div>
					<div class="col-sm-12 col-md-4">
						<h5>Pre-Award Assessment</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">Update Assessments</h5>
								<p class="card-text">From the selected applicants above, overall risk rating will be done and recommendations done on whether to issue a sub award or not. Here you can add, edit or delete Solicitation details</p>
								<br/>
                                                                <a href="pre_award_assessment.jsp" class="btn  btn-primary">Manage Assessments</a>
								
							</div>
						</div>
					</div>
                                    
                                    <div class="col-sm-12 col-md-4">
						<h5>Sub Recipients</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">Manage Sub-recipients</h5>
								<p class="card-text">Here we are updating the sub-recipient details from the already existing data captured on Applicants section. Here you can add, edit or delete sub-recipients details</p>
								<br/>						
								<br/>
								
                                                                <a href="subrecipients.jsp" class="btn  btn-primary">Manage Recipients</a>
								
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



</body>
</html>
