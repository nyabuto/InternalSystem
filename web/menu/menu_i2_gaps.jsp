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
								
			    <a href="gapanalysis.jsp" class="btn btn-light"><img style="width:150px;" src="./images/icons/download.png" class="img img-circle" />Export Gaps ( Excel)</a>
                                                                
                                                             
                                                                
                            <a style="margin: 5px;" href="ManageGaps.jsp" class="btn btn-light"><img style="width:150px;" src="./images/icons/manage_gap.png" class="img img-circle" /> Account for / Approve Gaps</a>
                            
                            <a  style="margin: 5px;" href="../Cohorts/rri_gaps_main.jsp" class="btn btn-light"><img style="width:150px;" src="./images/icons/service_gap.png" class="img img-circle" /> RRI Service Gaps</a>
                                                                
								<%if(session.getAttribute("access_gapanalysis")!=null){if(session.getAttribute("access_gapanalysis").toString().equals("1")){%>
                                                                <a href="UploadGaps.jsp" class="btn btn-light"><img style="width:150px;" src="./images/icons/export.png" class="img img-circle" />Upload Gaps</a>
                                                                <a style="margin: 5px;" href="ManageGaps.jsp" class="btn btn-light"><img style="width:150px;" src="./images/icons/manage_gap.png" class="img img-circle" /> Account for / Approve Gaps</a>
                                                                
                                                                
                                                                <%}}%>
								
							</div>
						</div>
					</div>
					
                                  	
				</div>
                              
                                                                
			</div>
  
		
			<!-- [ card ] end -->
		</div>