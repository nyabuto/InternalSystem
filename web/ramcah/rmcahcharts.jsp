<!DOCTYPE html>
<html lang="en">

<head>
    <title>RAMCAH-Dashboard</title>
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
                                        <select onchange="loadmdt(); updt();" required='true' class='form-control' id='county' name='county'  ></select>
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
                                        <select onchange="updt();" required='true' class='form-control' id='sd' name='sd'  ></select>
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <div class="form-group">
                                        <label class="floating-label" for="facility">End Period</label>
                                         <select onchange="updt();" required='true' class='form-control' id='ed' name='ed'  ></select>
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
            <div class="col-md-12 col-xl-3">
                <div class="card flat-card">
                    <div class="row-table">
                        <div class="col-sm-12 card-body br">
                            <div class="row">
                                <div class="col-sm-2">
                                    <i class="icon feather icon-users text-c-green mb-1 d-block"></i>
                                </div>
                                <div class="col-sm-10 text-md-center">
                                    <h2 id="totalgroups"></h2>
                                    <span>Formed Groups</span>
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
                            <h4 id="bintinhif"></h4>
                            <h6>Bintis in NHIF</h6>
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
                                    <h2 id="bintisanc1"></h2>
                                    <span>Bintis Attended ANC1</span>
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
                                    <img style="width:40px;" src="./images/icons/hcw_female.PNG">
                                </div>
                                <div class="col-sm-10 text-md-center">
                                    <h2 id="totalbintishujaas"></h2>
                                    <span>Total Binti Shujaas</span>
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
                            <h4 id="bintisfollowup"></h4>
                            <h6>Bintis Followed up</h6>
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
                                    <h2 id="bintisanc4"></h2>
                                    <span>Bintis attended ANC 4</span>
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
                                    <h2 id="enrolledbintis"></h2>
                                    <span>Enrolled Bintis</span>
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
                            <h4 id="bintispregnant"></h4>
                            <h6>Bintis are Pregnant</h6>
                        </div>
                    </div>
                </div>
                
                
                <!-- widget-success-card end -->
                
                
                   <div class="card flat-card">
                   
                        
                         <div class="row-table">
                        <div class="col-sm-12 card-body br">
                            <div class="row">
                                <div class="col-sm-2">
                                    <img style="width:50px;" src="./images/icons/pmtct.png">
                                </div>
                                <div class="col-sm-10 text-md-center">
                                    <h2 id="bintispnc"></h2>
                                    <span>Bintis on PNC Services</span>
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
                                   <img style="width:50px;" src="./images/icons/hiv_blue.png">
                                </div>
                                <div class="col-sm-10 text-md-center">
                                    <h2 id="hivpos"></h2>
                                    <span>Bintis are HIV +ve</span>
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
                            <h4 id="bintiswithbaby"></h4>
                            <h6>Bintis with Babies</h6>
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
                                    <h2 id="exitedbintis"></h2>
                                    <span>Bintis have exited</span>
                                </div>
                            </div>
                        </div>
                       
                    </div>
                    
                    
                </div>
                
                
            </div>
            <!-- table card-4 end -->
            
            
            <hr/>
            <div class="col-xl-12 col-md-12">
                <h5 class="" style="text-align: center;">Charts</h5>
            </div>
            
           
            
            <!-- prject ,team member start -->
            
            
            
            
            <div class="col-xl-4 col-md-12">
                <div class="card latest-update-card">
                    <div class="card-header">
                        <h5 style="text-align: center;">Bintis HIV Enrollment Trends</h5>
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
                    <div class="card-body">
                       
                       <div id="bintismonthlyenrollments"></div>
                        
                        <!--Enter Content here-->
                        
                       
                    </div>
                </div>
            </div>
            
            
            
            <div class="col-xl-4 col-md-12">
                <div class="card latest-update-card">
                    <div class="card-header">
                        <h5 style="text-align: center;">Bintis HIV Status at Enrollment</h5>
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
                    <div class="card-body">
                       
                       
                        
                        <!--Enter Content here-->
                        
                         <div id="bintishivstatusatenrollment"></div>
                        
                        
                    </div>
                </div>
            </div>
            
            
            
              <div class="col-xl-4 col-md-12">
                <div class="card latest-update-card">
                    <div class="card-header">
                        <h5 style="text-align: center;">Bintis Follow Up By Month</h5>
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
                    <div class="card-body">
                       
                       
                        
                        <!--Enter Content here-->
                        
                        <div id="bintismonthlyfollowups"></div>
                        
                        
                        
                        
                        
                    </div>
                </div>
            </div>
            
            <!-- prject ,team member start -->
            <!-- seo start -->
            
           
          
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
<script src="./rmc_assets/js/pages/chartsdata.js"></script>


<!-- custom-chart js -->
<script src="rmc_assets/js/pages/dashboard-main.js"></script>



<script type="text/javascript">
    
    
    
    var fc="";
    var cnt="";
    var sct="";
    var rgn="";
    var sd="";
    var ed="";
    
    
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
    
    loadChart('getRamcahCharts','sp_Binti_Shujaa_monthly_enrollments','bintismonthlyenrollments','Bintis Enrolled','bar');
    loadChart('getRamcahCharts','sp_Binti_Shujaa_monthly_followups','bintismonthlyfollowups','Bintis Followed up','line');
    loadDonutChart('getRamcahCharts','sp_Binti_Shujaa_hiv_status_at_enrollment','bintishivstatusatenrollment','Bintis Tested','donut');
     
</script>


</body>

</html>
