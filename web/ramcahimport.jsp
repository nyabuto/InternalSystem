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
<!--					<div class="col-sm-12 col-md-4">
						<h5 style="text-align:center ;">MOH 711</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">Import data from KHIS MOH 711</h5>
								<p class="card-text"></p>
                                                               
                                                                <br/>
								<a href="https://enketo.ona.io/x/d9VLmDP4" target="_blank" class="btn  btn-primary">Data Entry</a>
								
								
							</div>
						</div>
					</div>-->
					<div class="col-sm-12 col-md-12">
						<h5 style="text-align:center ;">MOH 711</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">Import data from KHIS MOH 711</h5>
								<p class="card-text"></p>
								<br/>
                                                                
                                                                <input type="file" name="">
                                                                
                                                                     
                                                                <%--<%@include file="ramcah/import711modal.jsp" %>--%>        
                                                                
                                                                
								
							</div>
						</div>
					</div>
                                    
<!--                                    <div class="col-sm-12 col-md-4">
						<h5 class="card-title">Import data from KHIS MOH 711</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								<h5 class="card-title">Manage Forms</h5>
								<p class="card-text"></p>
														
								<br/>
                                                                <a target="_blank" href="https://enketo.ona.io/x/qaE18hpo" class="btn  btn-primary">Data Entry</a>
								
							</div>
						</div>
					</div>-->
                                    
                                   
					
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
