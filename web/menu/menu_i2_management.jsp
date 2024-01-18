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
			
                                                                  
                                                                   <div class="col-xl-12">
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<h5 class="formcss"><b>Management</b></h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								
							
		 <% if(!session.getAttribute("level").toString().equals("1")){  %>  
                 <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",maintenance,")){%>
                                                               
                                                                <a  href="refreshDHIS2.jsp" class="btn btn-light"><i class="feather icon-refresh-cw"></i>Refresh PPMS Data Sets</a>
                                                                <a  href="addUsers.jsp" class="btn btn-light"><i class="feather icon-watch"></i>Create User Accounts</a>
                                                                <a  href="Access_Rights.jsp" class="btn btn-light"><i class="feather icon-watch"></i>Manage User Rights</a>
                                                                <a  href="editFacility.jsp" class="btn btn-light"><i class="feather icon-watch"></i>Facility Management</a>
                                                                <a  href="datalock.jsp" class="btn btn-light"><i class="feather icon-watch"></i>Lock/Unlock Data Entry</a>
                                                                <a  href="https://usaidtujengejamii.org:8443/Cohorts/rri_gaps_load_baselines.jsp" class="btn btn-light"><i class="feather icon-refresh-cw"></i>Refresh Service Gaps Baselines</a>
                                                               
                                                               
                                                                
                                                                
                                                                  <%}}%>
                                                                  
                                                              <a  href="editProfile.jsp" class="btn btn-light"><i class="feather icon-watch"></i>Edit Profile</a>     

<%}%>
								
							</div>
						</div>
					</div>
					
                                  	
				
					
                                  	
				</div>
                                                                  
                            
                                                                
			</div>
          
                        
		
			<!-- [ card ] end -->
		</div>