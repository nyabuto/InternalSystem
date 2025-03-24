<!DOCTYPE html>
<html lang="en">

<head>
    <title>KenyaEMR-Dashboard</title>
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
    <link rel="stylesheet" href="rmc_assets/css/style_2.css">
    
    
    <!--<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500;600;700&display=swap" rel="stylesheet" />-->

<!-- [Tabler Icons] https://tablericons.com -->
<link rel="stylesheet" href="rmc_assets/fonts/tabler-icons.min.css" />
<!-- [Feather Icons] https://feathericons.com -->
<link rel="stylesheet" href="rmc_assets/fonts/feather.css" />
<!-- [Font Awesome Icons] https://fontawesome.com/icons -->
<link rel="stylesheet" href="rmc_assets/fonts/fontawesome.css" />
<!-- [Material Icons] https://fonts.google.com/icons -->
<!--<link rel="stylesheet" href=../rmc_assets/fonts/material.css" />-->
    
    
    
    
    <style>
    .fontheader{
    font-size:28px;
      /*color: #37474f;*/
    font-weight: 600;
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
            
         
                        
          
<!--                                    
 <div class="col-sm-12">
                <div class="card">
                   
                    <div class="card-body">
                        <form>
                            <div class="row">
                                <div class="col-sm-2">
                                    <div class="form-group">
                                        <label class="floating-label" for="county">County</label>
                                        <select onchange="loadmdt(); updtimis();" required='true' class='form-control county' id='county' name='county'  ></select>
                                    </div>
                                </div>
                                
                                
                                  <div class="col-sm-2">
                                    <div class="form-group">
                                        <label class="floating-label" for="mdt">MDT Region</label>
                                        <select onchange="loadsubcounty();updtimis();" required='true' class='form-control mdt' id='mdt' name='mdt'  ></select>
                                    </div>
                                </div>
                                
                                
                                <div class="col-sm-2">
                                    <div class="form-group">
                                        <label class="floating-label" for="subcounty">Sub-County</label>
                                        <select onchange="loadfacils();updtimis();" required='true' class='form-control subcounty' id='subcounty' name='subcounty'  ></select>
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <div class="form-group">
                                        <label class="floating-label" for="facility">Facility</label>
                                         <select onchange="updtimis();" required='true' class='form-control facility' id='facility' name='facility'  ></select>
                                    </div>
                                </div>
                                 <div class="col-sm-2">
                                    <div class="form-group">
                                        <label class="floating-label" for="startyearmonth">Start Period</label>
                                        <select onchange="updtimis();" required='true' class='form-control startdate' id='startdate' name='startdate'  ></select>
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <div class="form-group">
                                        <label class="floating-label" for="facility">End Period</label>
                                         <select onchange="updtimis();" required='true' class='form-control enddate' id='enddate' name='enddate'  ></select>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>-->



                

            
        </div>
        
         <div class="col-xl-12 col-md-12">
                <h5 class=" btn-primary " style="text-align: center;padding-top:4px;padding-bottom:4px;">KenyaEMR Linelist Data Analysis</h5>
            </div>
        <hr/>
         <div class="row">
            <!-- table card-1 start -->
            <div class="col-md-12 col-xl-3">
                <div class="card flat-card">
                    <div class="row-table">
                        <div class="col-sm-12 card-body br">
                            <div class="row">
                                <div class="col-sm-2">
                                    <i class="icon feather icon-users text-c-green mb-1 d-block"></i>
                                </div>
                                <div class="col-sm-10 text-md-center">
                                    <h2 class="tx_curr"></h2>
                                    <h5>Current on ART (Total)</h5>
                                </div>
                            </div>
                        </div>
                       
                    </div>
                    
                    
                   
                </div>
                <!-- widget primary card start -->
                <div class="card flat-card widget-purple-card">
                    <div class="row-table">
                        <div class="col-sm-3 card-body">
                            <i class="feather icon-shield"></i>
                        </div>
                        <div class="col-sm-9">
                            
                            <label class="vl_eligible fontheader"></label>  <label class="vl_eligible_perc font_perc"></label>
                            <h5>Eligible For Viral Load</h5>
                        </div>
                    </div>
                </div>
                <!-- widget primary card end -->
                
                
                  <div class="card flat-card">
                   
                        
                         <div class="row-table">
                        <div class="col-sm-12 card-body br">
                            <div class="row">
                                <div class="col-sm-2">
                                    <i class="icon feather icon-disc text-c-green mb-1 d-block"></i>
                                </div>
                                <div class="col-sm-10 text-md-center">
                                   
                                <label class="tpt_eligible fontheader"></label>  <label class="tpt_eligible_perc font_perc"></label>
                                    <h5>Eligible for IPT/TPT</h5>
                                </div>
                            </div>
                        </div>
                       
                    </div>
                    
                    
                </div>
                
            </div>
            <!-- table card-1 end -->
            
            
            
            <!-- table card-2 start -->
            <div class="col-md-12 col-xl-3">
                <div class="card flat-card">
                    <div class="row-table">
                        <div class="col-sm-12 card-body br">
                            <div class="row">
                                <div class="col-sm-2">
                                    <!--<i class="icon child_care"></i>-->
                                    <!--<i class="material-icons-two-tone child_care"></i>-->
                                    <img style="width:30px;height:33px;" src="./images/icons/hcw_female.PNG">
                                </div>
                                <div class="col-sm-10 text-md-center">
                                    <h2 class="tx_curr_calhiv"></h2>
                                    <h5>Current on ART(CALHIV)</h5>
                                </div>
                            </div>
                        </div>
                       
                    </div>
                    
                </div>
                <!-- widget-success-card start -->
               <div class="card flat-card widget-purple-card">
                    <div class="row-table">
                        <div class="col-sm-3 card-body">
                            <i class="feather icon-refresh-ccw"></i>
                        </div>
                        <div class="col-sm-9">
                            <label  class="vl_done fontheader"></label> 
                            <label class="vl_done_perc fontperc"></label> 
                            <h5>Valid Viral Load Done</h5>
                        </div>
                    </div>
                </div>
                <!-- widget-success-card end -->
                
                
                    <div class="card flat-card">
                   
                        
                         <div class="row-table">
                        <div class="col-sm-12 card-body br">
                            <div class="row">
                                <div class="col-sm-2">
                                    <i class="icon feather icon-activity text-c-green mb-1 d-block"></i>
                                </div>
                                <div class="col-sm-10 text-md-center">
                                    <label class="tpt_ever_enrolled fontheader"></label>  <label class="tpt_ever_enrolled_perc fontperc"></label> <br/>
                                    <h5>Initiated on TPT/IPT</h5>
                                </div>
                            </div>
                        </div>
                       
                    </div>
                    
                    
                </div>
                
            </div>
            <!-- table card-2 end -->

            
             <!-- table card-3 start -->
            <div class="col-md-12 col-xl-3">
                <div class="card flat-card">
                   
                        
                         <div class="row-table">
                        <div class="col-sm-12 card-body br">
                            <div class="row">
                                <div class="col-sm-2">
                                    <i class="icon feather icon-user-plus text-c-green mb-1 d-block"></i>
                                </div>
                                <div class="col-sm-10 text-md-center">
                                    <h2 class="tx_curr_pmtct"></h2>
                                    <h5>Current On ART(PMTCT)</h5>
                                </div>
                            </div>
                        </div>
                       
                    </div>
                    
                    
                </div>
                <!-- widget-success-card start -->
                
                
                
                
                 <div class="card flat-card widget-purple-card">
                    <div class="row-table">
                        <div class="col-sm-3 card-body">
                            <i class="icon feather icon-user-check text-c-yellow mb-1 d-block"></i>
                        </div>
                        <div class="col-sm-9">
                            
                            <label class="vl_sup fontheader"></label>  <label class="vl_sup_perc font_perc"></label>
                            <h5>Virally Suppressed</h5>
                        </div>
                    </div>
                </div>
                
                
                <!-- widget-success-card end -->
                
                
                   <div class="card flat-card">
                   
                        
                         <div class="row-table">
                        <div class="col-sm-12 card-body br">
                            <div class="row">
                                <div class="col-sm-2">
                                    <img style="width:30px;height:33px;" src="./images/icons/pmtct.png">
                                </div>
                                <div class="col-sm-10 text-md-center">
                                    <h2 class="tpt_current"></h2>
                                    <h5>Current on IPT/TPT</h5>
                                </div>
                            </div>
                        </div>
                       
                    </div>
                    
                    
                </div>
                
                
            </div>
            <!-- table card-3 end -->
            
             <!-- table card-4 start -->
            <div class="col-md-12 col-xl-3">
                <div class="card flat-card">
                   
                        
                    <div class="row-table">
                        <div class="col-sm-12 card-body br">
                            <div class="row">
                                <div class="col-sm-2">
                                   <img style="width:30px;height:33px;" src="./images/icons/hiv_blue.png">
                                </div>
                                <div class="col-sm-10 text-md-center">
                                    <h2 class="tx_new"></h2>
                                    <h5>Newly Started on ART</h5>
                                </div>
                            </div>
                        </div>
                       
                    </div>
                    
                    
                </div>
                <!-- widget-success-card start -->
                
                
                
                
                 <div class="card flat-card widget-purple-card">
                    <div class="row-table">
                        <div class="col-sm-3 card-body">
                            <i class="icon feather icon-user-minus text-c-yellow mb-1 d-block"></i>
                        </div>
                        <div class="col-sm-9">
                            <h4 class="eligiblenotdonevl"></h4>
                            
                            <h5>Eligible Not Done VL</h5>
                        </div>
                    </div>
                </div>
                
                
                <!-- widget-success-card end -->
                
                
                   <div class="card flat-card">
                   
                        
                        <div class="row-table">
                        <div class="col-sm-12 card-body br">
                            <div class="row">
                                <div class="col-sm-2">
                                    <i class="icon feather icon-user-x text-c-red mb-1 d-block"></i>
                                </div>
                                <div class="col-sm-10 text-md-center">
                                    
                                      <label class="tpt_completed fontheader"></label>  <label class="tpt_completed_perc font_perc"></label>
                                    <h5>Completed IPT/TPT</h5>
                                </div>
                            </div>
                        </div>
                       
                    </div>
                    
                    
                </div>
                
                
            </div>
            <!-- table card-4 end -->
            
            
            
            
            
            <hr/>
            
            
            
            
             <!-- Other Clinical Summaries start -->
            <div class="col-md-12 col-xl-3">
                
                   
                        
             <div class="card rating-bar">
              <div class="card-body">
                <div class="row mb-3">
                  <div class="col-6">
                    
                    <span class="txcurr_nupi fontheader"></span>
                  </div>
                  <div class="col-6">
                    <b><label class=" text-end font_perc txcurr_nupi_perc">0%</label></b>
                  </div>
                </div>
                <div class="progress">
                  <div class="progress-bar bg-success txcurr_nupi_perc_pb" style="width: 0%"></div>
                </div>
                  <br/>
                  <h6 class="m-0"><b>Nupi Verification</b></h6>
              </div>
            </div>
                    
                    
                    
                     
                    
                    
                    
              
                <!-- widget-success-card start -->
                
                
                
                
                
                
                <!-- widget-success-card end -->
                
                
                   
                
                
            </div>
             
                  <div class="col-md-12 col-xl-3">
                
                   
                        
             <div class="card rating-bar">
              <div class="card-body">
                <div class="row mb-3">
                  <div class="col-6">
                    
                    <span class="tb_screened fontheader"></span>
                  </div>
                  <div class="col-6">
                    <b><label class=" text-end font_perc tb_screened_perc"></label></b>
                  </div>
                </div>
                <div class="progress">
                  <div class="progress-bar bg-success tb_screened_perc_pb" style="width: 0%"></div>
                </div>
                  <br/>
                  <h6 class="m-0"><b>TB Screening</b></h6>
              </div>
            </div>
                    
                    
                    
                     
                    
                    
                    
              
                <!-- widget-success-card start -->
                
                
                
                
                
                
                <!-- widget-success-card end -->
                
                
                   
                
                
            </div>
             
             <div class="col-md-12 col-xl-3">
                
                   
                        
             <div class="card rating-bar">
              <div class="card-body">
                <div class="row mb-3">
                  <div class="col-6">
                    
                    <span class="mh_screened_1yr fontheader"></span>
                  </div>
                  <div class="col-6">
                    <b><label class=" text-end font_perc mh_screened_1yr_perc"></label></b>
                  </div>
                </div>
                <div class="progress">
                  <div class="progress-bar bg-warning mh_screened_1yr_perc_pb" style="width: 0%"></div>
                </div>
                  <br/>
                  <h6 class="m-0"><b>Mental Health Screening</b></h6>
              </div>
            </div>
                    
                    
                    
                     
                    
                    
                    
              
                <!-- widget-success-card start -->
                
                
                
                
                
                
                <!-- widget-success-card end -->
                
                
                   
                
                
            </div>
            <!-- other Clinical summaries end -->
            
             <div class="col-md-12 col-xl-3">
                
                   
                        
             <div class="card rating-bar">
              <div class="card-body">
                <div class="row mb-3">
                  <div class="col-6">
                    
                    <span class="hpt_scrn fontheader"></span>
                  </div>
                  <div class="col-6">
                    <b><label class=" text-end font_perc hpt_scrn_perc"></label></b>
                  </div>
                </div>
                <div class="progress">
                  <div class="progress-bar bg-warning hpt_scrn_perc_pb" style="width: 0%"></div>
                </div>
                  <br/>
                  <h6 class="m-0"><b>Hypertension Screening</b></h6>
              </div>
            </div>
                    
                
                
            </div>
            
            <hr/>
            
            <!--Start of PMTCT Clinical Cascades-->
            
            <div class="col-xl-12 col-md-12">
                <h5 class=" btn-primary" style="text-align: center;padding:4px;">Clinical Cascades for Children and Adolescents living with HIV</h5>
            </div>
            <hr/>
            
            <div class="col-md-4">
            <div class="card">
              <div class="card-header">
                <h6 style="text-align: center;"><b>OVC Enrollment</b></h6>
              </div>
              <div class="card-body">
                  <div id="ovc_enrolled_perc_rd">
                      
                  </div>
              </div>
                <h6 style="text-align: center ;" class=" ovc_enrolled"></h6>
            </div>
          </div>
            
            <div class="col-md-4">
            <div class="card">
              <div class="card-header">
                <h6 style="text-align: center;"><b>OTZ Enrollment</b></h6>
              </div>
              <div class="card-body">
                  <div id="otz_enrolled_perc_rd">
                   
                      
                  </div>
              </div>
                <h6 style="text-align: center ;" class=" otz_enrolled"></h6>
            </div>
          </div>
            
             
            
            
            
            <div class="col-xl-4 col-md-12">
                <div class="card latest-update-card">
                    <div class="card-header">
                        <h6 style="text-align: center;"><b>CALHIV Viral Load Uptake</b></h6>
                        <div class="card-header-right">
                            <div class="btn-group card-option">
                                <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="feather icon-more-horizontal"></i>
                                </button>
                                <ul class="list-unstyled card-option dropdown-menu dropdown-menu-right">
                                    <li class="dropdown-item full-card"><a href="#!"><span><i class="feather icon-maximize"></i> maximize</span><span style="display:none"><i class="feather icon-minimize"></i> Restore</span></a></li>
                                    <li class="dropdown-item minimize-card"><a href="#!"><span><i class="feather icon-minus"></i> collapse</span><span style="display:none"><i class="feather icon-plus"></i> expand</span></a></li>
                                    <li class="dropdown-item reload-card"><a href="#!"><i class="feather icon-refresh-cw"></i> reload</a></li>
                                    <li class="dropdown-item close-card"><a href="#!"><i class="feather icon-trash"></i> remove</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                 
                       
                       <!--<div id="bintismonthlyenrollments"></div>-->
                        
                        <!--Enter Content here-->
                        
                        
                        
                         <div class="">
            <div class="card table-card">
              
              <div class="card-body px-0 py-0">
                <div class="table-responsive">
                    </br>
                  <table class="table table-hover mb-0">
                    <tbody>
                        <tr>
                        <td>Current on ART</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                             
                            <div class="progress wid-50">
                              <div class="progress-bar bg-success " style="width: 100%"></div>
                            </div>
                            <b><label class=""></label></b>
                             &nbsp; <b><span class="m-r-6 tx_curr_calhiv"></span></b>
                              
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td>Eligible For Viral Load</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                             
                            <div class="progress wid-50">
                              <div class="progress-bar bg-success vl_eligible_calhiv_perc_pb" style="width: 90%"></div>
                            </div>
                            <b><label class=""></label></b>
                             &nbsp; <b><span class="m-r-6 vl_eligible_calhiv"></span></b>
                              &nbsp; (<label class="vl_eligible_calhiv_perc"></label>)
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td>Done For Viral Load</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                            
                            <div class="progress wid-50">
                              <div class="progress-bar bg-primary vl_done_calhiv_perc_pb" style="width: 76%"></div>
                            </div>
                             &nbsp; <b><span class="m-r-6 vl_done_calhiv"></span></b>
                              &nbsp; (<label class="vl_done_calhiv_perc"></label>)
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td>Virally Suppressed</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                          
                          <div class="progress wid-50">
                          <div class="progress-bar bg-warning vl_sup_calhiv_perc_pb" style="width: 50%"></div>
                          </div>
                          
                           &nbsp; <b><span class="m-r-6 vl_sup_calhiv"></span></b>
                           &nbsp; (<label class="vl_sup_calhiv_perc"></label>)
                          </div>
                            
                        </td>
                      </tr>
                      <tr>
                        <td>Eligible Not Done  VL</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                           
                            <div class="progress wid-50">
                              <div class="progress-bar bg-danger eligiblenotdonevl_calhiv_perc_pb" style="width: 26%"></div>
                            </div>
                               
                            &nbsp; <b><span class="m-r-6 eligiblenotdonevl_calhiv"></span></b>
                           &nbsp; (<label class="eligiblenotdonevl_calhiv_perc"></label>)
                            
                          </div>
                        </td>
                      </tr>
                     
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
          
                </div>
            </div>
            
            
            
            <hr/>
            
            <!--Start of PMTCT Clinical Cascades-->
            
            <div class="col-xl-12 col-md-12">
                <h5 class="btn-primary" style="text-align: center; padding:4px;">Clinical Cascades for Women of Reproductive Age</h5>
            </div>
            <hr/>
           
            
            <!-- prject ,team member start -->
            
             <div class="col-xl-4 col-md-12">
                <div class="card latest-update-card">
                    <div class="card-header">
                        <h5 style="text-align: center;">Cervical Cancer Screening (CXCA)</h5>
                        <div class="card-header-right">
                            <div class="btn-group card-option">
                                <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="feather icon-more-horizontal"></i>
                                </button>
                                <ul class="list-unstyled card-option dropdown-menu dropdown-menu-right">
                                    <li class="dropdown-item full-card"><a href="#!"><span><i class="feather icon-maximize"></i> maximize</span><span style="display:none"><i class="feather icon-minimize"></i> Restore</span></a></li>
                                    <li class="dropdown-item minimize-card"><a href="#!"><span><i class="feather icon-minus"></i> collapse</span><span style="display:none"><i class="feather icon-plus"></i> expand</span></a></li>
                                    <li class="dropdown-item reload-card"><a href="#!"><i class="feather icon-refresh-cw"></i> reload</a></li>
                                    <li class="dropdown-item close-card"><a href="#!"><i class="feather icon-trash"></i> remove</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    
                    
            <div class="">
            <div class="card table-card">
              
              <div class="card-body px-0 py-0">
                <div class="table-responsive">
                  <table class="table table-hover mb-0">
                    <tbody>
                      <tr>
                        <td>Eligible For Screening</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                            
                            <div class="progress wid-50">
                              <div class="progress-bar bg-success" style="width: 100%"></div>
                            </div>
                              &nbsp; <b><span class="m-r-10 tx_curr_wra">90%</span></b>
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td>Total Screened</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                            
                            <div class="progress wid-50">
                              <div class="progress-bar bg-primary cxca_scrn_perc_pb" style="width: 76%"></div>
                            </div>
                              &nbsp; <b><span class="m-r-10 cxca_scrn">76%</span></b>
                              &nbsp; (<label class="cxca_scrn_perc"></label>)
                            
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td>Screened Positive</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                            
                            <div class="progress wid-50">
                              <div class="progress-bar bg-warning cxca_pos_perc_pb" style="width: 50%"></div>
                            </div>
                            
                              &nbsp; <b><span class="m-r-10 cxca_pos">76%</span></b>
                              &nbsp; (<label class="cxca_pos_perc"></label>)
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td>Started on Treatment</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                           
                            <div class="progress wid-50">
                              <div class="progress-bar bg-danger cxca_pos_tx_perc_pb" style="width: 26%"></div>
                            </div>
                             &nbsp; <b><span class="m-r-10 cxca_pos_tx">76%</span></b>
                              &nbsp; (<label class="cxca_pos_tx_perc"></label>)
                          </div>
                        </td>
                      </tr>
                     
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
                    
                    
                </div>
            </div>
            
            
            <div class="col-xl-4 col-md-12">
                <div class="card latest-update-card">
                    <div class="card-header">
                        <h5 style="text-align: center;">PMTCT Viral Load Uptake</h5>
                        <div class="card-header-right">
                            <div class="btn-group card-option">
                                <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="feather icon-more-horizontal"></i>
                                </button>
                                <ul class="list-unstyled card-option dropdown-menu dropdown-menu-right">
                                    <li class="dropdown-item full-card"><a href="#!"><span><i class="feather icon-maximize"></i> maximize</span><span style="display:none"><i class="feather icon-minimize"></i> Restore</span></a></li>
                                    <li class="dropdown-item minimize-card"><a href="#!"><span><i class="feather icon-minus"></i> collapse</span><span style="display:none"><i class="feather icon-plus"></i> expand</span></a></li>
                                    <li class="dropdown-item reload-card"><a href="#!"><i class="feather icon-refresh-cw"></i> reload</a></li>
                                    <li class="dropdown-item close-card"><a href="#!"><i class="feather icon-trash"></i> remove</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                 
                       
                       <!--<div id="bintismonthlyenrollments"></div>-->
                        
                        <!--Enter Content here-->
                        
                        
                        
                         <div class="">
            <div class="card table-card">
              
              <div class="card-body px-0 py-0">
                <div class="table-responsive">
                  <table class="table table-hover mb-0">
                    <tbody>
                      <tr>
                        <td>Eligible For Viral Load</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                             
                            <div class="progress wid-50">
                              <div class="progress-bar bg-success vl_eligible_pmtct_perc_pb" style="width: 90%"></div>
                            </div>
                            <b><label class=""></label></b>
                             &nbsp; <b><span class="m-r-6 vl_eligible_pmtct"></span></b>
                              &nbsp; (<label class="vl_eligible_pmtct_perc"></label>)
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td>Done For Viral Load</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                            
                            <div class="progress wid-50">
                              <div class="progress-bar bg-primary vl_done_pmtct_perc_pb" style="width: 76%"></div>
                            </div>
                             &nbsp; <b><span class="m-r-6 vl_done_pmtct"></span></b>
                              &nbsp; (<label class="vl_done_pmtct_perc"></label>)
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td>Virally Suppressed</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                          
                          <div class="progress wid-50">
                          <div class="progress-bar bg-warning vl_sup_pmtct_perc_pb" style="width: 50%"></div>
                          </div>
                          
                           &nbsp; <b><span class="m-r-6 vl_sup_pmtct"></span></b>
                           &nbsp; (<label class="vl_sup_pmtct_perc"></label>)
                          </div>
                            
                        </td>
                      </tr>
                      <tr>
                        <td>Eligible Not Done  VL</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                           
                            <div class="progress wid-50">
                              <div class="progress-bar bg-danger eligiblenotdonevl_pmtct_perc_pb" style="width: 26%"></div>
                            </div>
                               
                            &nbsp; <b><span class="m-r-6 eligiblenotdonevl_pmtct"></span></b>
                           &nbsp; (<label class="eligiblenotdonevl_pmtct_perc"></label>)
                            
                          </div>
                        </td>
                      </tr>
                     
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
          
                </div>
            </div>
            
            
            
            <div class="col-xl-4 col-md-12">
                <div class="card latest-update-card">
                    <div class="card-header">
                        <h5 style="text-align: center;">PMTCT IPT/TPT Enrollment</h5>
                        <div class="card-header-right">
                            <div class="btn-group card-option">
                                <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="feather icon-more-horizontal"></i>
                                </button>
                                <ul class="list-unstyled card-option dropdown-menu dropdown-menu-right">
                                    <li class="dropdown-item full-card"><a href="#!"><span><i class="feather icon-maximize"></i> maximize</span><span style="display:none"><i class="feather icon-minimize"></i> Restore</span></a></li>
                                    <li class="dropdown-item minimize-card"><a href="#!"><span><i class="feather icon-minus"></i> collapse</span><span style="display:none"><i class="feather icon-plus"></i> expand</span></a></li>
                                    <li class="dropdown-item reload-card"><a href="#!"><i class="feather icon-refresh-cw"></i> reload</a></li>
                                    <li class="dropdown-item close-card"><a href="#!"><i class="feather icon-trash"></i> remove</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    
                       
                       
                        
                        <!--Enter Content here-->
                        
                         <!--<div id="bintishivstatusatenrollment"></div>-->
                        
                           <div class="">
            <div class="card table-card">
              
              <div class="card-body px-0 py-0">
                <div class="table-responsive">
                  <table class="table table-hover mb-0">
                    <tbody>
                      <tr>
                        <td>Eligible For IPT</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                             
                            <div class="progress wid-50">
                              <div class="progress-bar bg-success tpt_eligible_pmtct_perc_pb" style="width: 90%"></div>
                            </div>
                            <b><label class=""></label></b>
                             &nbsp; <b><span class="m-r-6 tpt_eligible_pmtct"></span></b>
                              &nbsp; (<label class="tpt_eligible_pmtct_perc"></label>)
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td>Initiated on IPT</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                            
                            <div class="progress wid-50">
                              <div class="progress-bar bg-primary tpt_ever_enrolled_pmtct_perc_pb" style="width: 76%"></div>
                            </div>
                             &nbsp; <b><span class="m-r-6 tpt_ever_enrolled_pmtct"></span></b>
                              &nbsp; (<label class="tpt_ever_enrolled_pmtct_perc"></label>)
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td>With IPT Outcomes</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                          
                          <div class="progress wid-50">
                          <div class="progress-bar bg-warning tpt_has_outcome_pmtct_perc_pb" style="width: 50%"></div>
                          </div>
                          
                           &nbsp; <b><span class="m-r-6 tpt_has_outcome_pmtct"></span></b>
                           &nbsp; (<label class="tpt_has_outcome_pmtct_perc"></label>)
                          </div>
                            
                        </td>
                      </tr>
                      <tr>
                        <td>Completed IPT</td>
                        <td>
                          <div class="text-end d-flex align-items-center m-0">
                           
                            <div class="progress wid-50">
                              <div class="progress-bar bg-danger tpt_completed_pmtct_perc_pb" style="width: 26%"></div>
                            </div>
                               
                            &nbsp; <b><span class="m-r-6 tpt_completed_pmtct"></span></b>
                           &nbsp; (<label class="tpt_completed_pmtct_perc"></label>)
                            
                          </div>
                        </td>
                      </tr>
                     
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
                        
                   
                </div>
            </div>
            
            
            
             <!--End of Women Clinical Cascades-->
            
            <!-- prject ,team member start -->
            <!-- mdt start -->
            
            
            
             <div class="col-xl-12 col-md-12">
                <h5 class=" btn-primary" style="text-align: center;padding:4px;">MDT Summary For Critical Indicators</h5>
            </div>
            <hr/>
            
            
                     <!-- [ user activity section ] start -->
          <div class="col-xl-12 col-md-12">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h4 style="text-align:center;">Non Communicable Diseases (NCDs) Screening and Outcomes Summary By Regions</h4>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm   ncd_summary">
                         <td>
                            <div class="d-flex justify-content-center">
                                <div class="spinner-border" role="status">
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
                     
                     
                     
          <div class="col-xl-12 col-md-12">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h4 style="text-align:center;">IPT/TPT Cascades Summary By Regions</h4>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm   ipt_summary">
                         <td>
                            <div class="d-flex justify-content-center">
                                <div class="spinner-border" role="status">
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
                     
                     
          <div class="col-xl-12 col-md-12">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h4 style="text-align:center;">Mental Health Screening Cascades  By Region</h4>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm   mh_summary">
                         <td>
                            <div class="d-flex justify-content-center">
                                <div class="spinner-border" role="status">
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
                     
                     
                     
                      <div class="col-xl-12 col-md-12">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h4 style="text-align:center;">Contact Listing For HIV Testing  By Region</h4>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm   fpt_summary">
                         <td>
                            <div class="d-flex justify-content-center">
                                <div class="spinner-border" role="status">
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
                     
          <div class="col-xl-12 col-md-6">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h4 style="text-align:center;"> Viral Load By Region</h4>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm   avl_summary">
                         <td>
                            <div class="d-flex justify-content-center">
                                <div class="spinner-border" role="status">
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
                     
                     
          <div class="col-xl-12 col-md-6">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h4 style="text-align:center;"> Children Living with HIV (CALHIV) Viral Load</h4>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm   cvl_summary">
                         <td>
                            <div class="d-flex justify-content-center">
                                <div class="spinner-border" role="status">
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
                     
                     
          <div class="col-xl-12 col-md-6">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h4 style="text-align:center;"> PMTCT Mothers Viral Load By Region</h4>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm   pvl_summary">
                         <td>
                            <div class="d-flex justify-content-center">
                                <div class="spinner-border" role="status">
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
                     
                     
          <div class="col-xl-12 col-md-6">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h4 style="text-align:center;"> Cervical Cancer Screening and treatment</h4>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm   cxca_summary">
                         <td>
                            <div class="d-flex justify-content-center">
                                <div class="spinner-border" role="status">
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
                    
              
                     
                             <div class="col-xl-12 col-md-12">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h4 style="text-align:center;">CD4 and NUPI Verification Among Patients Starting ART</h4>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm   txnew_summary">
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
                     
            
                     
                     
                        
     <div class="col-xl-12 col-md-12">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h4 style="text-align:center;">KenyaEMR Routine Data Quality- Summary</h4>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm  rdqa_summary">
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
                     
     <div class="col-xl-12 col-md-12">
            <div class="card User-Activity table-card">
              <div class="card-header">
                <h4 style="text-align:center;">KenyaEMR Routine Data Quality- Indicators</h4>
              </div>
              <div class="card-body p-0">
                <div class="table-responsive">
                    
                  <div class="activity-scroll" style="width:100%;">
                    
                      
                     <table class="table table-sm  rdqadetails_summary">
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
                     
                     
          <!-- [ user activity section ] end -->
          
            <!-- mdt end -->

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
<!--<script src="rmc_assets/js/pages/chart-apex_1.js"></script>-->
<!--<script src="./rmc_assets/js/pages/chartsdata.js"></script>-->


<!-- custom-chart js -->
<!--<script src="rmc_assets/js/pages/dashboard-main.js"></script>-->



<script type="text/javascript">
    
    
    
    var fc="";
    var cnt="";
    var sct="";
    var rgn="";
    var sd="";
    var ed="";
    
         
                  
                       
    
    
      function loadDataOnly(action,sp)
{ 
  
    //alert("Called");
    
     if($("#mflcode").val()!==null){fc=$("#mflcode").val();}
    if($("#county").val()!==null){cnt=$("#county").val();}
    if($("#subcounty").val()!==null){sct=$("#subcounty").val();}
    if($("#Region").val()!==null){rgn=$("#Region").val();}
    
    if($("#startdate").val()!==null){sd=$("#startdate").val();} 
    if($("#enddate").val()!==null){ed=$("#enddate").val();} 
        
    
    //once the edit form is clicked, the assumption is that the add section will be loaded
    //$('#home-tab').click();
    //$('#home-tab').html("Edit Projects");
    
   
//    console.log("_"+fc+"vs"+dt);

            
           
            
            //now load the data
          $.ajax({
                    url:'dataPulls',                            
                    type:'post',  
                    data:
                    {
                        act:action,
                        fc:fc,
                        cnt:cnt,
                        sct:sct,
                        rgn:rgn,
                        sd:sd,
                        ed:ed,
                        sp:sp
                    },
                    dataType: 'json',  
                    success: function(data) 
                    {
                        console.log("data ni:"+data);
                  
//                      if I want to display ovc list only   
                      var rdlitems=['ovc_enrolled_perc','otz_enrolled_perc'];  
                        
                        
                        console.log(data);
                        
                        const keys = Object.keys(data[0]);  
                        console.log(keys);
for (const key in data[0]) 
{  
    
    
      const radialdt=[];
      const radiallabel=[];
    
    
    var ky=key;
    var vl=data[0][key];
    
    
    vl=vl.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    
    if(rdlitems.includes(ky)){
        
     
     var percent_numeric=vl.replace('%','');
     radialdt.push(percent_numeric);
     radiallabel.push(ky.replace('_',' ').replace('_perc',''));
     
     
     loadRadialChart(ky+"_rd",percent_numeric,radialdt,radiallabel);
     
 }
   console.log("The length of Radial Length is :"+radialdt.length) 
    //don't include 
    if($("."+ky)!==null){
     $("."+ky).html(vl);
 }
 
  if($("."+ky+"_pb")!==null){
     $("."+ky+"_pb").css("width",vl);
 }
  
}
                        

                    }  
//});
                        

   
                        
                        
                    });    
             
    
    
}
    
    
    
    
    
        function loadChart(action,sp,elementid,subjects,charttype)
{ 
  
    //alert("Called");
    
     if($("#mflcode").val()!==null){fc=$("#mflcode").val();}
    if($("#county").val()!==null){cnt=$("#county").val();}
    if($("#subcounty").val()!==null){sct=$("#subcounty").val();}
    if($("#Region").val()!==null){rgn=$("#Region").val();}
    
    if($("#startdate").val()!==null){sd=$("#startdate").val();} 
    if($("#enddate").val()!==null){ed=$("#enddate").val();} 
        
    
    //once the edit form is clicked, the assumption is that the add section will be loaded
    //$('#home-tab').click();
    //$('#home-tab').html("Edit Projects");
    
   
//    console.log("_"+fc+"vs"+dt);

            
           
            
            //now load the data
          $.ajax({
                    url:'dataPulls',                            
                    type:'post',  
                    data:
                    {
                        act:action,
                        fc:fc,
                        cnt:cnt,
                        sct:sct,
                        rgn:rgn,
                        sd:sd,
                        ed:ed,
                        sp:sp
                    },
                    dataType: 'json',  
                    success: function(data) 
                    {
                        console.log("data ni:"+data);
                        var ttl=data[0].title;
                        const dt=[];
                        const mns=[];
                        for(a=0;a<data.length;a++)
                        {
                          dt.push(data[a].value);
                          mns.push(data[a].months);
                           console.log(dt); 
                        }
                        
                        $(function() {
            var options = {
                chart: {
                    height: 300,
                    type: charttype,
                    zoom: {
                        enabled: false
                    }
                        },
                dataLabels: {
                    enabled: true,
                    width: 2
                },
                stroke: {
                    curve: 'straight',
                },
                colors: ["#1abc9c"],
                series: [{
                    name: subjects,
                    data: dt
                }],
                title: {
                    text: ttl,
                    align: 'center'
                },
                grid: {
                    row: {
                        colors: ['#f3f6ff', 'transparent'], // takes an array which will be repeated on columns
                        opacity: 0.5
                    },
                },
                xaxis: {
                    categories: mns,
                }
            }

            var chart = new ApexCharts(
                document.querySelector("#"+elementid),
                options
                                      );
   
            chart.render();
        });
        
        
              
                        
                        
                        for(var a=0;a<data.length;a++){
                            
                          console.log(data[a].Value);   
                          console.log(data[a].element);   
                            
                             if($("#"+data[a].element)!==null)
                             {
     $("#"+data[a].element).html(data[a].Value);
            }
                        }
                        

                    }  
//});
                        

   
                        
                        
                    });    
             
    
    
}
    
    
    
    
    
    
    
    
    function loadDonutChart(action,sp,elementid,subjects,charttype)
{ 

    if($("#mflcode").val()!==null){fc=$("#mflcode").val();}
    if($("#county").val()!==null){cnt=$("#county").val();}
    if($("#subcounty").val()!==null){sct=$("#subcounty").val();}
    if($("#Region").val()!==null){rgn=$("#Region").val();}
    
    if($("#startdate").val()!==null){sd=$("#startdate").val();} 
    if($("#enddate").val()!==null){ed=$("#enddate").val();} 
        
    
    //once the edit form is clicked, the assumption is that the add section will be loaded
    //$('#home-tab').click();
    //$('#home-tab').html("Edit Projects");
    
   
//    console.log("_"+fc+"vs"+dt);

            
           
            
            //now load the data
          $.ajax({
                    url:'dataPulls',                            
                    type:'post',  
                    data:
                    {
                        act:action,
                        fc:fc,
                        cnt:cnt,
                        sct:sct,
                        rgn:rgn,
                        sd:sd,
                        ed:ed,
                        sp:sp
                    },
                    dataType: 'json',  
                    success: function(data) 
                    {
                        console.log("data ni:"+data);
                        var ttl=data[0].title;
                        const dt=[];
                        const mns=[];
                        for(var a=0;a<data.length;a++)
                        {
                            var vl=data[a].value;
                          dt.push(parseInt(vl));
                          mns.push(data[a].dimension);
                           console.log(dt); 
                        }
                        
                       
               $(function() {
            var options = {
                chart: {
                    height: 320,
                    type: charttype
                },
                
    labels: mns,
                series: dt,
                colors: ["#1abc9c", "#0e9e4a", "#00acc1", "#f1c40f"],
                legend: {
                    show: true,
                    position: 'bottom'
                },
                 title: {
                    text: ttl,
                    align: 'center'
                },
                plotOptions: {
                    pie: {
                        donut: {
                            labels: {
                                show: true,
                                name: {
                                    show: true
                                },
                                value: {
                                    show: true
                                }
                            }
                        },
                        expandOnClick: true
                    }
                },
                dataLabels: {
                    enabled: true,
                    dropShadow: {
                        enabled: false,
                    }
                },
                responsive: [{
                    breakpoint: 480,
                    options: {          
                        legend: {
                            position: 'bottom'
                        }
                    }
                }]
            }
            var chart = new ApexCharts(
                document.querySelector("#"+elementid),
                options
            );
            chart.render();
        });
    
                        
                        
//                        for(var a=0;a<data.length;a++){
//                            
//                          console.log(data[a].Value);   
//                          console.log(data[a].element);   
//                            
//                             if($("#"+data[a].element)!==null){
//     $("#"+data[a].element).html(data[a].Value);
//            }
//                        }
                        

                    }  
//});
                        

   
                        
                        
                    });    
             
    
    
}
    
    
    function loadRadialChart(chartelement,val,series_ar,labels_ar){
        
//        console.log("Radial Chart Called:"+val+"~"+chartelement+"~"+series_ar+"~"+labels_ar);
        
      (function () {
    var options = {
      chart: {
        height: 290,
        type: 'radialBar'
      },
      plotOptions: {
        radialBar: {
          hollow: {
            size: series_ar
          }
        }
      },
      colors: ['#1abc9c'],
      series: series_ar,
      labels: labels_ar
    };
    $("#"+chartelement).html('');
    var chart = new ApexCharts(document.querySelector('#'+chartelement), options);
    chart.render();
 
        })();
    }
    
    
//    loadChart('getRamcahCharts','sp_Binti_Shujaa_monthly_enrollments','bintismonthlyenrollments','Bintis Enrolled','bar');
//    loadChart('getRamcahCharts','sp_Binti_Shujaa_monthly_followups','bintismonthlyfollowups','Bintis Followed up','line');
//    loadDonutChart('getRamcahCharts','sp_Binti_Shujaa_hiv_status_at_enrollment','bintishivstatusatenrollment','Bintis Tested','donut');
//     loadDataOnly('getEmrCascades','analytics_emr_cascades','','','');
</script>


</body>

</html>
