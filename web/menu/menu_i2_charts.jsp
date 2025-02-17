<!DOCTYPE html>
                        <head>
                            
                            <style>
                                
                                .formcss{                                    
                                    text-align: center;
                                    color:#4b8df8;
                                    border-color:#4b8df8;
                                }
                            </style>
                            
                             <link rel="stylesheet" href="/select2/css/select2.css"/>
 
                            
                        </head>
                        
                        
                 
                        
                        
<div class="card">
					<div class="card-body" style="background-color:#E9E9E9;">
                                            
                                            
                    
                                            
                                            
						<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
						 <li class="nav-item">
								<a class="nav-link active" id="pills-emr-tab" data-toggle="pill" href="#pills-emrcharts" role="tab" aria-controls="pills-emrcharts" aria-selected="false">  <span class="pcoded-micon"><i class="feather icon-link"></i></span>  EMR</a>
							</li>	
                                                    
                                                    <li class="nav-item">
								<a class="nav-link" id="pills-summary-tab" data-toggle="pill" href="#pills-summary" role="tab" aria-controls="pills-summary" aria-selected="true"> <span class="pcoded-micon"><i class="feather icon-bar-chart"></i></span> Summary</a>
							</li>
                                                       
							<li class="nav-item">
								<a class="nav-link" id="pills-prevention-tab" data-toggle="pill" href="#pills-prevention" role="tab" aria-controls="pills-prevention" aria-selected="false"> <span class="pcoded-micon"><i class="feather icon-info"></i></span>Prevention</a>
							</li>
                                                        <li class="nav-item">
								<a class="nav-link" id="pills-testing-tab" data-toggle="pill" href="#pills-testing" role="tab" aria-controls="pills-testing" aria-selected="false"> <span class="pcoded-micon"><i class="feather icon-clock"></i></span>HIV Testing</a>
							</li>
                                                        
                                                        <li class="nav-item">
								<a class="nav-link" id="pills-treatment-tab" data-toggle="pill" href="#pills-treatment" role="tab" aria-controls="pills-treatment" aria-selected="false"> <span class="pcoded-micon"><i class="feather icon-activity"></i></span>Treatment</a>
							</li>
                                                        
                             
                                                        
                                                         <li class="nav-item">
								<a class="nav-link" id="pills-vl-tab" data-toggle="pill" href="#pills-vl" role="tab" aria-controls="pills-vl" aria-selected="false">  <span class="pcoded-micon"><i class="feather icon-cast"></i></span>Viral Suppression</a>
							</li>
                                                         
                                                        
                                                        <li class="nav-item">
								<a class="nav-link" id="pills-surge-tab" data-toggle="pill" href="#pills-surge" role="tab" aria-controls="pills-surge" aria-selected="false">  <span class="pcoded-micon"><i class="feather icon-trending-up"></i></span>  Surge</a>
							</li>
							

						</ul>
						<div class="tab-content" id="pills-tabContent1">
                                                    
                                                        <div class="tab-pane fade show active" id="pills-emrcharts" role="tabpanel" aria-labelledby="pills-emrcharts-tab">
                                                            <!----EMR---->
                                                            
                                                            <%@include file="../analytics/imisemr.jsp" %> 
                                                            
							</div>  
                                                    
							<div class="tab-pane fade " id="pills-summary" role="tabpanel" aria-labelledby="pills-summary-tab">
								<!----Summary---->
                                                            
                                                                <%@include file="../analytics/imisdashboards.jsp" %> 
                                                            
                                                            
							</div>
							<div class="tab-pane fade" id="pills-prevention" role="tabpanel" aria-labelledby="pills-prevention-tab">
								                           <!----Prevention---->
                                                                                           <%@include file="../analytics/imischarts.jsp" %> 
                                                            
                                                            
							</div>
							<div class="tab-pane fade" id="pills-testing" role="tabpanel" aria-labelledby="pills-testing-tab">
                                                          <!----Testing---->
							</div>
                                                            
                           <div class="tab-pane fade" id="pills-treatment" role="tabpanel" aria-labelledby="pills-treatment-tab">
                                                            <!----Treatment---->
                                                           
							</div>
                         <div class="tab-pane fade" id="pills-vl" role="tabpanel" aria-labelledby="pills-vl-tab">
                                                            <!----VL---->
                                                         
					     </div>
                        
                                                          
                                                             <div class="tab-pane fade" id="pills-surge" role="tabpanel" aria-labelledby="pills-surge-tab">
                                                            <!----Surge---->
                                                            
                                                           
                                                            
							</div>  
                                                            
						</div>
					</div>
				</div>
                                                           

                                                           
                                                           