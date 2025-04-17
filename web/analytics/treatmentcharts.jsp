<!DOCTYPE html>
<html lang="en">

<head>
    <title>Treatment Charts</title>
    <!-- HTML5 Shim and Respond.js IE11 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 11]>
    	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    	<![endif]-->
    <!-- Meta -->
<!--  
     Favicon icon 
    <link rel="icon" href="rmc_assets/images/favicon.ico" type="image/x-icon">

     vendor css 
    <link rel="stylesheet" href="rmc_assets/css/style_2.css">
    
    -->
    <!--<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500;600;700&display=swap" rel="stylesheet" />-->

<!-- [Tabler Icons] https://tablericons.com 
<link rel="stylesheet" href="rmc_assets/fonts/tabler-icons.min.css" />
 [Feather Icons] https://feathericons.com 
<link rel="stylesheet" href="rmc_assets/fonts/feather.css" />
 [Font Awesome Icons] https://fontawesome.com/icons 
<link rel="stylesheet" href="rmc_assets/fonts/fontawesome.css" />
 [Material Icons] https://fonts.google.com/icons 
<link rel="stylesheet" href=rmc_assets/fonts/material.css" />
    
    
    -->
    
    <style>
    .fontheader1{
    font-size:18px;
      /*color: #37474f;*/
    font-weight: 600;
    }
    
    
    
    .indicatorlabel{
        
       font-size:11px;
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
       
	<!-- [ navigation menu ] end -->
	<!-- [ Header ] start -->

	<!-- [ Header ] end -->
	
	

<!-- [ Main Content ] start -->

    <div class="pcoded-content">
        <!-- [ breadcrumb ] start -->
       
        <!-- [ breadcrumb ] end -->
        <!-- [ Main Content ] start -->
        
        <div class="row">
            
         
                        
          
                                    
 <!--<div class="col-sm-12">-->
              
            <!--</div>-->



                

            
        </div>
        
        
         <%--<%@include file="treatmentannualsummary.html" %>--%> 
            
          <div class="col-xl-12 col-md-12">
                <h5 class=" btn-primary" style="text-align: center;padding:4px;">Performance Trends</h5>
            </div>
            <hr/>
            
         <div class="row">
           <!----Monthly Trendlines------> 
          
        <div class="col-md-6 col-xl-6">
                <div class="card">
                    <div class="card-header">
                          <h6 class='btn-info' style="text-align:center;padding:4px;">Starting ART Monthly Trends</h6>
                    </div>
                    <div class="card-body">
                        <div class="ap_tx_new_trends"></div>
                    </div>
                </div>
            </div>    
          
            <div class="col-md-6 col-xl-6">
                <div class="card">
                    <div class="card-header">
                        
                            <h6 class='btn-info' style="text-align:center;padding:4px;">Current on ART Quarterly Trends</h6>
                    </div>
                    <div class="card-body">
                        <div class="ap_tx_curr_trends"></div>
                    </div>
                </div>
            </div> 
            </div>
              <div class="row">
           <!----Monthly Trendlines------> 
          
     
             
           <div class="col-md-6 col-xl-6">
                <div class="card">
                    <div class="card-header">
                        
                         <h6 class='btn-info' style="text-align:center;padding:4px;">Screened for TB Monthly Trends</h6>
                    </div>
                    <div class="card-body">
                        <div class="ap_tx_tb_d_trends"></div>
                    </div>
                </div>
            </div> 
          
            <div class="col-md-6 col-xl-6">
                <div class="card">
                    <div class="card-header">
                        <h6 class='btn-info' style="text-align:center;padding:4px;">TB Co-infected put on TB Treatment Monthly Trends</h6>
                    </div>
                    <div class="card-body">
                        <div class="ap_tx_tb_n_trends"></div>
                    </div>
                </div>
            </div> 
            </div>
           
             <div class="row">
           <!----Monthly Trendlines------> 
          
            <div class="col-md-6 col-xl-6">
                <div class="card">
                    <div class="card-header">
                    
                         <h6 class='btn-info' style="text-align:center;padding:4px;">PMTCT ART Monthly Trends</h6>
                    </div>
                    <div class="card-body">
                        <div class="ap_pmtct_art_trends"></div>
                    </div>
                </div>
            </div> 
           
        <div class="col-md-6 col-xl-6">
                <div class="card">
                    <div class="card-header">
                    
                         <h6 class='btn-info' style="text-align:center;padding:4px;">TB ART Monthly Trends</h6>
                    </div>
                    <div class="card-body">
                        <div class="ap_tb_art_trends"></div>
                    </div>
                </div>
            </div> 
            </div> 
            
           
            
             <div class="col-xl-12 col-md-12">
                <h5 class=" btn-primary" style="text-align: center;padding:4px;">Regional Performance  </h5>
            </div>
           <hr/>
            
         
              <div class="row">       
                     
                        
     <div class="col-xl-6 col-md-12">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h6 class='btn-info' style="text-align:center;padding:4px;">Starting ART By Region</h6>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm  ap_tx_new_summary">
                        <td>
                            <div class="d-flex justify-content-center">
                                <div class=" spinner-border" role="status">
                                    <span class="sr-only">Loading...</span>
                                </div>
                            </div>
                        </td>
                      </table>
                     
                  </div>
                </div>
              </div>
            </div>
          </div>
                     
     <div class="col-xl-6 col-md-12">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h6 class='btn-info' style="text-align:center;padding:4px;">Current on ART By Region</h6>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm  ap_tx_curr_summary">
                        <td>
                            <div class="d-flex justify-content-center">
                                <div class=" spinner-border" role="status">
                                    <span class="sr-only">Loading...</span>
                                </div>
                            </div>
                        </td>
                      </table>
                     
                  </div>
                </div>
              </div>
            </div>
          </div>
              
           </div>
                     
           
           
           <!---TB Prev start--->
           
           
             <div class="row">       
                     
                        
     <div class="col-xl-6 col-md-12">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h6 class='btn-info' style="text-align:center;padding:4px;">Screened for TB By Region</h6>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm  ap_tx_tb_d_summary">
                        <td>
                            <div class="d-flex justify-content-center">
                                <div class=" spinner-border" role="status">
                                    <span class="sr-only">Loading...</span>
                                </div>
                            </div>
                        </td>
                      </table>
                     
                  </div>
                </div>
              </div>
            </div>
          </div>
                     
     <div class="col-xl-6 col-md-12">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h6 class='btn-info' style="text-align:center;padding:4px;">TB Cases on ART Put on TB Treatment By Region</h6>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm  ap_tx_tb_n_summary">
                        <td>
                            <div class="d-flex justify-content-center">
                                <div class=" spinner-border" role="status">
                                    <span class="sr-only">Loading...</span>
                                </div>
                            </div>
                        </td>
                      </table>
                     
                  </div>
                </div>
              </div>
            </div>
          </div>
              
           </div>
           
           <!---TB Prev end--->
           
           
           
           <!----Violence summary  Start---->
           
          <div class="row"> 
           
              
               <div class="col-xl-6 col-md-12">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h6 class='btn-info' style="text-align:center;padding:4px;">PMTCT HIV +ve put on ART summary By Region</h6>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm  ap_pmtct_art_summary">
                        <td>
                            <div class="d-flex justify-content-center">
                                <div class=" spinner-border" role="status">
                                    <span class="sr-only">Loading...</span>
                                </div>
                            </div>
                        </td>
                      </table>
                     
                  </div>
                </div>
              </div>
            </div>
          </div>
              
           <div class="col-xl-6 col-md-12">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h6 class='btn-info' style="text-align:center;padding:4px;">TB Co-infected put on TB Treatment summary By Region</h6>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm  ap_tx_tb_n_summary">
                        <td>
                            <div class="d-flex justify-content-center">
                                <div class=" spinner-border" role="status">
                                    <span class="sr-only">Loading...</span>
                                </div>
                            </div>
                        </td>
                      </table>
                     
                  </div>
                </div>
              </div>
            </div>
          </div>
              
        </div>   
              
           <!----Violence Summary end---->
           
           
          <!-- [ user activity section ] end -->
          
            <!-- mdt end -->

            <!-- Latest Customers start -->
            
          
            <!-- Latest Customers end -->
        </div>
        <!-- [ Main Content ] end -->
    <!--</div>-->

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
<!--    <script src="rmc_assets/js/vendor-all.min.js"></script>
    <script src="rmc_assets/js/plugins/bootstrap.min.js"></script>
    <script src="rmc_assets/js/pcoded.min.js"></script>

 Apex Chart 
<script src="rmc_assets/js/plugins/apexcharts.min.js"></script>-->
<!--<script src="rmc_assets/js/pages/chart-apex_1.js"></script>-->
<!--<script src="./rmc_assets/js/pages/chartsdata.js"></script>-->


<!--<script src="rmc_assets/js/pages/chart-apex.js"></script>-->
<!-- custom-chart js -->
<!--<script src="rmc_assets/js/pages/dashboard-main.js"></script>-->




</body>

</html>
