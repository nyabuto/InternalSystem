<!DOCTYPE html>
<html lang="en">

<head>
    <title>IMIS-Dashboard</title>
    <!-- HTML5 Shim and Respond.js IE11 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 11]>
    	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    	<![endif]-->
    <!-- Meta -->
  
    <!-- Favicon icon -->
    <link rel="icon" href="rmc_assets/images/favicon.ico" type="image/x-icon">

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
       
	<!-- [ navigation menu ] end -->
	<!-- [ Header ] start -->

	<!-- [ Header ] end -->
	
	

<!-- [ Main Content ] start -->

    <div class="pcoded-content">
     
         <%@include file="preventionannualsummary.html" %> 
        <%@include file="testingannualsummary.html" %> 
        <%@include file="testingotherannualsummary.html" %> 
        <%@include file="treatmentannualsummary.html" %> 
        <%@include file="vlannualsummary.html" %> 
        
      <div class="col-xl-12 col-md-12">
                <h5 class=" btn-primary " style="text-align: center;padding-top:2px;padding-bottom:2px;">Sites Coverage</h5>
            </div>
        
        <div class="row">
       
            <!-- table card-1 start -->
            <div class="col-md-12 col-xl-6">
                <div class="card flat-card">
                    
                    
                    <!-- widget primary card start -->
                <div class="card flat-card widget-profile-card">
                    <div class="row-table">
                        <div class="col-sm-2 card-body">
                            <i class="feather text-c-red icon-map"></i>
                        </div>
                        <div class="col-sm-4">
                            <h4 id="counties">4</h4>
                            <label>Supported Counties</label>
                        </div>
                        <div class="col-sm-2 card-body">
                            <i class="feather text-c-red icon-map"></i>
                        </div>
                        <div class="col-sm-4">
                            <h4 id="districts">4</h4>
                            <label>Supported sub-counties</label>
                        </div>
                    </div>
                </div>
                <!-- widget primary card end -->
                    
                    
                    <div class="row-table">
                       
                        
                        
                        <div class="col-sm-6 card-body br">
                            <div class="row">
                                <div class="col-sm-4">
                                    <i class="icon feather icon-map-pin text-c-red  mb-1 d-block"></i>
                                </div>
                                <div class="col-sm-8 text-md-center">
                                    <h4 id="Total_Sites">201</h4>
                                    <label >Supported Sites</label>
                                </div>
                            </div>
                        </div>
                        
                        
                        <div class="col-sm-6 card-body">
                            <div class="row">
                                <div class="col-sm-4">
                                    <i class="icon feather icon-aperture text-c-red mb-1 d-block"></i>
                                </div>
                                <div class="col-sm-8 text-md-center">
                                    <h5 id="HTS">201</h5>
                                    <label >HIV Testing Sites</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row-table">
                        <div class="col-sm-6 card-body br">
                            <div class="row">
                                <div class="col-sm-4">
                                    <i class="icon feather icon-file-text text-c-red  mb-1 d-block"></i>
                                </div>
                                <div class="col-sm-8 text-md-center">
                                    <h5 id="ART">188</h5>
                                    <label >Care & Treatment Sites</label>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6 card-body">
                            <div class="row">
                                <div class="col-sm-4">
                                    <i class="icon feather icon-user text-c-red  mb-1 d-block"></i>
                                </div>
                                <div class="col-sm-8 text-md-center">
                                    <h5 id="PMTCT">180</h5>
                                     <label >PMTCT Supported Sites</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
            </div>
            <!-- table card-1 end -->
            <!-- table card-2 start -->
           <div class="col-md-12 col-xl-6">
                <div class="card flat-card">
                    
                    
                    <!-- widget primary card start -->
                <div class="card flat-card widget-profile-card">
                    <div class="row-table">
                        <div class="col-sm-3 card-body">
                            <i class="feather icon-monitor text-c-blue "></i>
                        </div>
                        <div class="col-sm-9">
                            <h4 id="EMR">142</h4>
                            <label>EMR Supported Facilities</label>
                        </div>
                    </div>
                </div>
                <!-- widget primary card end -->
                    
                    
                    <div class="row-table">
                       
                        
                        
                        <div class="col-sm-6 card-body br">
                            <div class="row">
                                <div class="col-sm-4">
                                    <i class="icon feather icon-clock text-c-blue mb-1 d-block"></i>
                                </div>
                                <div class="col-sm-8 text-md-center">
                                    <h4 id="poc">38</h4>
                                    <label >Point of Care Sites</label>
                                </div>
                            </div>
                        </div>
                        
                        
                        <div class="col-sm-6 card-body">
                            <div class="row">
                                <div class="col-sm-4">
                                    <i class="icon feather icon-tablet text-c-blue mb-1 d-block"></i>
                                </div>
                                <div class="col-sm-8 text-md-center">
                                    <h5 id="ehts">61</h5>
                                    <label >eHTS Sites</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row-table">
                        <div class="col-sm-6 card-body br">
                            <div class="row">
                                <div class="col-sm-4">
                                    <i class="icon feather icon-gitlab text-c-blue mb-1 d-block"></i>
                                </div>
                                <div class="col-sm-8 text-md-center">
                                    <h5 id="labmanifest">36</h5>
                                    <label >Lab manifest Sites</label>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6 card-body">
                            <div class="row">
                                <div class="col-sm-4">
                                    <i class="icon feather icon-smartphone text-c-red mb-1 d-block"></i>
                                </div>
                                <div class="col-sm-8 text-md-center">
                                    <h5 id="Ushauri">36</h5>
                                     <label >Ushauri Sites</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
        
       
        
        <!-- [ Main Content ] end -->
    </div>

    <!-- Warning Section Ends -->

    <!-- Required Js -->
     <script src="rmc_assets/js/vendor-all.min.js"></script>
    <script src="rmc_assets/js/plugins/bootstrap.min.js"></script>
    <!--<script src="rmc_assets/js/pcoded.min.js"></script>-->

<!-- Apex Chart -->
<script src="rmc_assets/js/plugins/apexcharts.min.js"></script>
<!--    <script src="rmc_assets/js/vendor-all.min.js"></script>
    <script src="rmc_assets/js/plugins/bootstrap.min.js"></script>
    <script src="rmc_assets/js/pcoded.min.js"></script>

 Apex Chart 
<script src="rmc_assets/js/plugins/apexcharts.min.js"></script>-->


<!-- custom-chart js -->
<!--<script src="rmc_assets/js/pages/dashboard-main.js"></script>-->

</body>

</html>
