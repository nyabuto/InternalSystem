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
						<h5 class="formcss" > EMR Status</h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								
								
								
								<%if(session.getAttribute("userAccess")!=null){%>        
                                                                
                                                                <a href="EMR.jsp" class="btn btn-light"><img style="width:140px;" src="./images/icons/dataentry.png" class="img img-circle" /> EMR Status Form</a>
                                                                <a style="margin: 5px;" href="emr_status_tracker.jsp" class="btn btn-light"><img style="width:140px;" src="./images/icons/tracker_1.png" class="img img-circle" /> EMR Status Missing Reports</a>
                                                                <a style="margin: 5px;" href="emr_status_report.jsp" class="btn btn-light"><img style="width:140px;" src="./images/icons/reports.png" class="img img-circle" />EMR Status Submitted Reports</a>
                                                                <a style="margin: 5px;" href="uploadrdqa.jsp" class="btn btn-light"><img style="width:140px;" src="./images/icons/export.png" class="img img-circle" />EMR RDQA Upload</a>
                                                                
                                                                
                                                                <%}%>
								
							
								
							</div>
						</div>
					</div>
                                  	
				</div>  
                                                                
                                                                
			</div>
                        
                      
                      
                        
                      
                        
                        
                        
                        
                        
                      
                        
                        
                        
                        
		
			<!-- [ card ] end -->
		</div>