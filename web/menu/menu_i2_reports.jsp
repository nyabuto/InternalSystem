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
					<div class="col-sm-12 col-md-6">
						<h5 class="formcss"><b><img style="width:150px;" src="./images/icons/datim.png" class="img img-circle" />DATIM</b></h5>
						<hr>
						
						<div class="card text-left">
							<div class="card-body">
								
							
								<%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",reports,")){%> 
                                                                
                                                                <a  href="DatimOutput.jsp" class="btn btn-light"><i class="feather icon-step-forward"></i>Datim Raw Reports</a>
                                                                <a  href="DatimScreens.jsp" class="btn btn-light"><i class="feather icon-clipboard"></i>DATIM Entry Screen (Excel)</a>
                                                                
                                                               
	
                                                                
                                                                  <%}}%>
								
							</div>
						</div>
					</div>
					
                                  	
				
					<div class="col-sm-12 col-md-6">
						<h5 class="formcss"><b><img style="width:150px;" src="./images/icons/xls.png" class="img img-circle" />Template Reports</b></h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								
							
								<%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",reports,")){%>  
                                                                <a  href="templatereports.jsp" class="btn btn-light"><i class="feather icon-cloud-upload"></i>Template Reports (Excel)</a>
                                                                
                                                               
	 
     
                                                                
                                                                
                                                                  <%}}%>
								
							</div>
						</div>
					</div>
					
                                  	
				</div>
                                                                  
                                                             
                                              
                         
                                                                
 
                                                                
                                                             
       
                                                                
                                                                
                                                                
 
                                                                
                                                                
			</div>
			<div class="col-xl-12">
				<div class="row">
					<div class="col-sm-12 col-md-6">
						
                                                <h5 class="formcss"><b><img style="width:150px;" src="./images/icons/site.png" class="img img-circle" />Master Facility List</b></h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								
							
								 <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",reports,")){%> 
                                                               
                                                                <a  href="masterlist.jsp" class="btn btn-light"><i class="feather icon-watch"></i>Master Facility List</a>
                                                                
                                                                
                                                                  <%}}%>
								
							</div>
						</div>
					</div>
					
                                  	
				
					<div class="col-sm-12 col-md-6">
						
                                                 <h5 class="formcss"><b><img style="width:150px;" src="./images/icons/data_comparison.png" class="img img-circle" />Training Reports</b></h5>
						
						<hr>
						<div class="card text-left">
							<div class="card-body">
								
							
								  <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",reports,")){%> 
                                                                <a  href="T1SummaryReports.jsp" class="btn btn-light"><i class="feather icon-refresh-ccw"></i>Training Summary Report</a>
                                                               
       
                                                                
                                                                
                                                                  <%}}%>
								
							</div>
						</div>
					</div>
					
                                  	
				</div>
                                                                  
                            
                                                                
			</div>
                        
                      
                                                                  
        <div class="col-xl-12">
				<div class="row">
					<div class="col-sm-12 col-md-6">
						 <h5 class="formcss"><b><img style="width:150px;" src="./images/icons/surge.png" class="img img-circle" />Surge Reports</b></h5>
						
						<hr>
						<div class="card text-left">
							<div class="card-body">
								
							
								 <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",reports,")){%> 
                                                               
                                                                <a  href="../Cohorts/surgereports.jsp" class="btn btn-light"><i class="feather icon-watch"></i>Surge Reports</a>
                                                                
                                                                
                                                                  <%}}%>
								
							</div>
						</div>
					</div>
					
                                  	
				
					<div class="col-sm-12 col-md-6">
						
                                          
                                                 <h5 class="formcss"><b><img style="width:150px;" src="./images/hfr.png" class="img img-circle" />HFR Reports</b></h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								
							
								  <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",reports,")){%> 
                                                                <a  href="../Cohorts/hfrreports.jsp" class="btn btn-light"><i class="feather icon-refresh-ccw"></i>HFR Reports</a>
                                                               
       
                                                                
                                                                
                                                                  <%}}%>
								
							</div>
						</div>
					</div>
					
                                  	
				</div>
                                                                  
                            
                                                                
			</div>
                                                                  
                                                                   <div class="col-xl-12">
				<div class="row">
					<div class="col-sm-12 col-md-12">
						
                                                  <h5 class="formcss"><b><img style="width:150px;" src="./images/icons/reports.png" class="img img-circle" />Other Reports</b></h5>
                                                                
						<hr>
						<div class="card text-left">
							<div class="card-body">
								
							
								 <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",reports,")){%> 
                                                               
                                                                <a  href="../Cohorts/rri_gaps_main.jsp" class="btn btn-light"><i class="feather icon-watch"></i>Service Gap Reports</a>
                                                                <a  href="../Cohorts/retention.jsp" class="btn btn-light"><i class="feather icon-watch"></i>Retention Audit Reports</a>
                                                                <a  href="../Cohorts/otz_main.jsp" class="btn btn-light"><i class="feather icon-watch"></i>OTZ Reports</a>
                                                                <a  href="../Cohorts/ahd_main.jsp" class="btn btn-light"><i class="feather icon-watch"></i>AHD Reports</a>
                                                                <a  href="../Cohorts/hei_weekly_main.jsp" class="btn btn-light"><i class="feather icon-watch"></i>HEI Weekly Reports</a>
                                                                <a  href="../Cohorts/me_matrix_main.jsp" class="btn btn-light"><i class="feather icon-watch"></i>M&E Matrix Reports</a>
                                                                <a  href="../Cohorts/stockverification.jsp" class="btn btn-light"><i class="feather icon-watch"></i>Stock Verification Reports</a>
                                                                <a  href="../Cohorts/upi_main.jsp" class="btn btn-light"><i class="feather icon-watch"></i>NUPI & Covid Reports</a>
                                                                
                                                                
                                                                  <%}}%>
								
							</div>
						</div>
					</div>
					
                                  	
				
					
                                  	
				</div>
                                                                  
                            
                                                                
			</div>
          
                        
		
			<!-- [ card ] end -->
		</div>