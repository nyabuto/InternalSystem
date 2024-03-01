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
        <!-- [ breadcrumb ] start -->
       
        <!-- [ breadcrumb ] end -->
        <!-- [ Main Content ] start -->
        
        <div class="row">
            
         
                        
          
                                    
 <div class="col-sm-12">
                <div class="card">
                   
                    <div class="card-body">
                        <form>
                            <div class="row">
                                <div class="col-sm-2">
                                    <div class="form-group">
                                        <label class="floating-label" for="county">County</label>
                                        <select onchange="loadmdt(); updt()" required='true' class='form-control' id='county' name='county'  ></select>
                                    </div>
                                </div>
                                
                                
                                  <div class="col-sm-2">
                                    <div class="form-group">
                                        <label class="floating-label" for="mdt">MDT Region</label>
                                        <select onchange="loadsubcounty();updt();" required='true' class='form-control' id='mdt' name='mdt'  ></select>
                                    </div>
                                </div>
                                
                                
                                <div class="col-sm-2">
                                    <div class="form-group">
                                        <label class="floating-label" for="subcounty">Sub-County</label>
                                        <select onchange="loadfacils();updt();" required='true' class='form-control' id='subcounty' name='subcounty'  ></select>
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <div class="form-group">
                                        <label class="floating-label" for="facility">Facility</label>
                                         <select onchange="updt();" required='true' class='form-control' id='facility' name='facility'  ></select>
                                    </div>
                                </div>
                                 <div class="col-sm-2">
                                    <div class="form-group">
                                        <label class="floating-label" for="startyearmonth">Start Period</label>
                                        <select required='true' class='form-control' id='startdate' name='startdate'  ></select>
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <div class="form-group">
                                        <label class="floating-label" for="facility">End Period</label>
                                         <select required='true' class='form-control' id='enddate' name='enddate'  ></select>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>



                

            
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
            <!-- table card-2 end -->
            <!-- Widget primary-success card start -->
<!--            <div class="col-md-12 col-xl-4">
                <div class="card support-bar overflow-hidden">
                   
                    <div id="support-chart"></div>
                    <div class="card-footer bg-primary text-white">
                        <div class="row text-center">
                            <div class="col">
                                <h4 class="m-0 text-white">10</h4>
                                <span>Open</span>
                            </div>
                            <div class="col">
                                <h4 class="m-0 text-white">5</h4>
                                <span>Running</span>
                            </div>
                            <div class="col">
                                <h4 class="m-0 text-white">3</h4>
                                <span>Solved</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>-->
            <!-- Widget primary-success card end -->

            <!-- prject ,team member start -->
            <div class="col-xl-6 col-md-12">
              
            </div>
            <div class="col-xl-6 col-md-12">
              
            </div>
            <!-- prject ,team member start -->
            <!-- seo start -->
<!--            
            
            
            <div class="col-xl-4 col-md-12">
                <div class="card">
                    <div class="card-body">
                        <div class="row align-items-center">
                            <div class="col-6">
                                <h3>59,847</h3>
                                <h6 class="text-muted m-b-0">Patients on ART<i class="fa fa-caret-down text-c-red m-l-10"></i></h6>
                            </div>
                            <div class="col-6">
                                <div id="seo-chart1" class="d-flex align-items-end"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-4 col-md-6">
                <div class="card">
                    <div class="card-body">
                        <div class="row align-items-center">
                            <div class="col-6">
                                <h3>87%</h3>
                                <h6 class="text-muted m-b-0">VL Access<i class="fa fa-caret-up text-c-green m-l-10"></i></h6>
                            </div>
                            <div class="col-6">
                                <div id="seo-chart2" class="d-flex align-items-end"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-4 col-md-6">
                <div class="card">
                    <div class="card-body">
                        <div class="row align-items-center">
                            <div class="col-6">
                                <h3>97%</h3>
                                <h6 class="text-muted m-b-0">VL Suppression<i class="fa fa-caret-down text-c-red m-l-10"></i></h6>
                            </div>
                            <div class="col-6">
                                <div id="seo-chart3" class="d-flex align-items-end"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            -->
            
            
            <!-- seo end -->

            <!-- Latest Customers start -->
           
           
            <!-- Latest Customers end -->
        </div>
        <!-- [ Main Content ] end -->
    </div>

<!-- [ Main Content ] end -->
    <!-- Warning Section start -->
    <!-- Older IE warning message -->
    <!--[if lt IE 11]>
        <div class="ie-warning">
            <h1>Warning!!</h1>
            <p>You are using an outdated version of Internet Explorer, please upgrade
               <br/>to any of the following web browsers to access this website.
            </p>
            <div class="iew-container">
                <ul class="iew-download">
                    <li>
                        <a href="http://www.google.com/chrome/">
                            <img src="rmc_assets/images/browser/chrome.png" alt="Chrome">
                            <div>Chrome</div>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.mozilla.org/en-US/firefox/new/">
                            <img src="rmc_assets/images/browser/firefox.png" alt="Firefox">
                            <div>Firefox</div>
                        </a>
                    </li>
                    <li>
                        <a href="http://www.opera.com">
                            <img src="rmc_assets/images/browser/opera.png" alt="Opera">
                            <div>Opera</div>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.apple.com/safari/">
                            <img src="rmc_assets/images/browser/safari.png" alt="Safari">
                            <div>Safari</div>
                        </a>
                    </li>
                    <li>
                        <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
                            <img src="rmc_assets/images/browser/ie.png" alt="">
                            <div>IE (11 & above)</div>
                        </a>
                    </li>
                </ul>
            </div>
            <p>Sorry for the inconvenience!</p>
        </div>
    <![endif]-->
    <!-- Warning Section Ends -->

    <!-- Required Js -->
    <script src="rmc_assets/js/vendor-all.min.js"></script>
    <script src="rmc_assets/js/plugins/bootstrap.min.js"></script>
    <script src="rmc_assets/js/pcoded.min.js"></script>

<!-- Apex Chart -->
<script src="rmc_assets/js/plugins/apexcharts.min.js"></script>


<!-- custom-chart js -->
<script src="rmc_assets/js/pages/dashboard-main.js"></script>
</body>

</html>
