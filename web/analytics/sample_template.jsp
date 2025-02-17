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
   
</head>




<body class="" >
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

    <div class="pcoded-content" style="background-color:#E9E9E9;">
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
        
        <div class="row" >
            
            
            
            
            
            <!-- table card-1 start -->
           
            <!-- table card-1 end -->
            <!-- table card-2 start -->
         
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
            <div class="col-xl-12 col-md-12">
                
                
                
                  <div class="col-md-12 col-xxl-12">
            <div class="row">
                
              <div class="col-3">
                <div class="card">
                  <div class="card-body">
                    <div class="my-n4 text-center mx-auto" style="max-width: 130px">
                      <div id="total-earning-chart-1"></div>
					  <label>Test</label>
                    </div>
                  </div>
                </div>
              </div>
                
                
                  <div class="col-md-3">
            <div class="card card-border-warning">
              <div class="card-body">
                <h3>20500</h3>
                <p class="text-muted">Total Sales</p>
                <div id="seo-anlytics2"></div>
              </div>
            </div>
          </div>
                
                  <div class="col-md-3">
            <div class="card card-border-success">
              <div class="card-body">
                <h3>20500</h3>
                <p class="text-muted">Total Sales</p>
                <div id="seo-anlytics2"></div>
              </div>
            </div>
          </div>
                
                
          <div class="col-md-3">
            <div class="card">
              <div class="card-header">
                <h5 style="text-align: center;">Vl Suppression</h5>
              </div>
              <div class="card-body">
                <div id="radialBar-chart-1"></div>
              </div>
            </div>
          </div>
                
                
                
            </div>
          </div>
              
            </div>
            
            
            
<div class="col-xl-4 col-md-12">
            
                
                
                
          <div class="card rating-bar">
              <div class="card-body">
                <div class="row mb-3">
                  <div class="col-6">
                    <h6 class="m-0">Task Completed</h6>
                    <span>Successfull tested</span>
                  </div>
                  <div class="col-6">
                    <h2 class="text-end">20%</h2>
                  </div>
                </div>
                <div class="progress">
                  <div class="progress-bar bg-primary" style="width: 20%"></div>
                </div>
              </div>
            </div>
 </div>
            
            
            
            
            
            
            
         
                
                
                
            
        
         
              
            
            
          
            
            
            
            
            
            <div class="row">
                
                
          <!-- browser-section start -->
          <div class="col-md-12">
            <div class="card table-card">
              <div class="card-header borderless">
                <h5>Browser States</h5>
              </div>
              <div class="card-body px-0 py-0">
                <div class="table-responsive">
                  <table class="table table-hover mb-0">
                    <tbody>
                      <tr>
                        <td>Google Chrome</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                            <span class="m-r-10">90%</span>
                            <div class="progress wid-50">
                              <div class="progress-bar bg-success" style="width: 90%"></div>
                            </div>
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td>Mozila Firefox</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                            <span class="m-r-10">76%</span>
                            <div class="progress wid-50">
                              <div class="progress-bar bg-primary" style="width: 76%"></div>
                            </div>
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td>Apple Safari</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                            <span class="m-r-10">50%</span>
                            <div class="progress wid-50">
                              <div class="progress-bar bg-warning" style="width: 50%"></div>
                            </div>
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td>Internet Explorer</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                            <span class="m-r-10">26%</span>
                            <div class="progress wid-50">
                              <div class="progress-bar bg-danger" style="width: 26%"></div>
                            </div>
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td>Opera mini</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                            <span class="m-r-10">15%</span>
                            <div class="progress wid-50">
                              <div class="progress-bar bg-danger" style="width: 15%"></div>
                            </div>
                          </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
          <!-- browser-section end -->
                
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
   
   




<!-- custom-chart js -->
<script src="rmc_assets/js/pages/dashboard-main.js"></script>


<script src="rmc_assets/js/plugins/popper.min.js"></script>
<script src="rmc_assets/js/plugins/simplebar.min.js"></script>
 <script src="rmc_assets/js/plugins/bootstrap.min_1.js"></script>
<script src="rmc_assets/fonts/custom-font.js"></script>
<script src="rmc_assets/js/pcoded.js"></script>
 <!--<script src="rmc_assets/js/pcoded.min.js"></script>-->
<script src="rmc_assets/js/plugins/feather.min.js"></script>


<!-- Apex Chart -->
<script src="rmc_assets/js/plugins/apexcharts.min.js"></script>
<script src="rmc_assets/js/pages/chart-apex_1.js"></script>
<script src="rmc_assets/js/pages/w-chart.js"></script>

<script src="rmc_assets/js/plugins/datepicker-full.min.js"></script>
<script src="rmc_assets/js/plugins/peity-vanilla.min.js"></script>
<script src="rmc_assets/js/pages/membership-dashboard.js"></script>

</body>

</html>
