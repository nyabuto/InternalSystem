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
                                                               
                                                                <a target="_blank" href="refreshDHIS2.jsp" class="btn btn-light"><i class="feather icon-refresh-cw"></i>Refresh PPMS Data Sets</a>
                                                                <a target="_blank" href="addUsers.jsp" class="btn btn-light"><i class="feather icon-watch"></i>Create User Accounts</a>
                                                                <a target="_blank" href="Access_Rights.jsp" class="btn btn-light"><i class="feather icon-watch"></i>Manage User Rights</a>
                                                                <a target="_blank" href="editFacility.jsp" class="btn btn-light"><i class="feather icon-watch"></i>Facility Management</a>
                                                                <a target="_blank" href="datalock.jsp" class="btn btn-light"><i class="feather icon-watch"></i>Lock/Unlock Data Entry</a>
                                                               
                                                               
                                                                
                                                                
                                                                  <%}}%>
                                                                  
                                                              <a target="_blank" href="editProfile.jsp" class="btn btn-light"><i class="feather icon-watch"></i>Edit Profile</a>     

<%}%>
								
							</div>
						</div>
					</div>
					
                                  	
				
					
                                  	
				</div>
                                                                  
                            
                                                                
			</div>
          
                        
		
			<!-- [ card ] end -->
		</div>