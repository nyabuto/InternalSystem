<div class="row">
			<!-- [ card ] start -->
			
                        
                        <head>
                            
                            <style>
                                
                                .formcss{                                    
                                    text-align: center;
                                    color:#4b8df8;
                                    border-color:#4b8df8;
                                }
                            </style>
                            
                        </head>
                        
			<div class="col-xl-12">
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<h5 class="formcss"><b>Service Gaps Module</b></h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								
								<a href="gapanalysis.jsp" class="btn btn-light"><i class="feather icon-save"></i>Export Gaps ( Excel)</a>
								<%if(session.getAttribute("access_gapanalysis")!=null){if(session.getAttribute("access_gapanalysis").toString().equals("1")){%>
                                                                <a href="uploadf1a.jsp" class="btn btn-light"><i class="feather icon-upload"></i>Upload Gaps</a>
                                                                <a style="margin: 5px;" href="ManageGaps.jsp" class="btn btn-light"><i class="feather icon-settings"></i> Account for / Approve Gaps</a>
                                                                <a target="_blank" style="margin: 5px;" href="../Cohorts/rri_gaps_main.jsp" class="btn btn-light"><i class="feather icon-align-justify"></i> RRI Service Gaps</a>
                                                                
                                                                
                                                                <%}}%>
								
							</div>
						</div>
					</div>
					
                                  	
				</div>
                              
                                                                
			</div>
  
		
			<!-- [ card ] end -->
		</div>