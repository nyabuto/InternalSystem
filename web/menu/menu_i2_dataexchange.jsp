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
						
                                                <h5 class="formcss"> 
                                                    <img style="width:150px;" src="./images/icons/eid.png" class="img img-circle" /><b>NACSOP EID Results</b></h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								
							
								  <% if(session.getAttribute("level")!=null){ if(!session.getAttribute("level").toString().equals("1")){  %>  
                 <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",uploads,")){%> 
                                                                
                                                                <a  href="load_eid_tested.jsp" class="btn btn-light"><i class="feather icon-cloud-snow"></i>Upload EID Tested Results (.xlsx)</a>
                                                                <a  href="load_eid_positive.jsp" class="btn btn-light"><i class="feather icon-cloud-rain"></i>Upload EID Positive Results (.xlsx)</a>
                                                                
                                                                
                                                                
                                                                
                                                                  <%}}}else{}}%>
								
							</div>
						</div>
					</div>
					
                                  	
				
					<div class="col-sm-12 col-md-6">
						
                                                 <h5 class="formcss"> 
                                                    <img style="width:150px;" src="./images/icons/vl_1.png" class="img img-circle" /><b>NACSOP VL Results</b></h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								
							
								  <% if(session.getAttribute("level")!=null){ if(!session.getAttribute("level").toString().equals("1")){  %>  
                   <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",uploads,")){%> 
                                                                <a  href="uploadVL_Surge.jsp" class="btn btn-light"><i class="feather icon-cloud-upload"></i>Upload VL Results (.xlsx)</a>
                                                                
                                                                
                                                                
                                                                
                                                                  <%}}}else{}}%>
								
							</div>
						</div>
					</div>
					
                                  	
				</div>
                                                                  
                                                             
                                                                
                         
                                                                
 
                                                                
                                                             
       
                                                                
                                                                
                                                                
 
                                                                
                                                                
			</div>
			<div class="col-xl-12">
				<div class="row">
					<div class="col-sm-12 col-md-6">
						
                                                <h5 class="formcss"> 
                                                    <img style="width:150px;" src="./images/icons/refresh.png" class="img img-circle" /><b>VL Data Refresh</b></h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								
							
								  <% if(session.getAttribute("level")!=null){ if(!session.getAttribute("level").toString().equals("1")){  %>  
                 <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",uploads,")){%> 
                                                                
                                                                <a  href="VL_autoupdate_results.jsp" class="btn btn-light"><i class="feather icon-cloud-snow"></i>Autoupdate VL Results</a>
                                                                <a  href="Sync_VL_Results.jsp" class="btn btn-light"><i class="feather icon-cloud-rain"></i>Sync VL Results</a>
                                                                <a  href="Final_Sync_VL_Results.jsp" class="btn btn-light"><i class="feather icon-cloud-rain"></i>Final Sync VL Results</a>
                                                                
                                                                
                                                                  <%}}}else{}}%>
								
							</div>
						</div>
					</div>
					
                                  	
				
					<div class="col-sm-12 col-md-6">
                                            <h5 class="formcss"> 
                                                    <img style="width:150px;" src="./images/icons/exchange.png" class="img img-circle" /><b>Data Push to PPMS</b></h5>
						
						<hr>
						<div class="card text-left">
							<div class="card-body">
								
							
								  <% if(session.getAttribute("level")!=null){ if(!session.getAttribute("level").toString().equals("1")){  %>  
                   <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",uploads,")){%> 
                                                                <a  href="fpt_tobaselines_refresh.jsp" class="btn btn-light"><i class="feather icon-refresh-ccw"></i>Refresh FPT table </a>
                                                                <a  href="fpt_sendDataToDHIS.jsp" class="btn btn-light"><i class="feather icon-bluetooth"></i>Push Data to PPMS</a>
                                                                <a  href="../Cohorts/hfrreports.jsp" class="btn btn-light"><i class="feather icon-clipboard"></i>Push HFR & JMW to PPMS</a>
                                                                
                                                                
       
                                                                
                                                                
                                                                  <%}}}else{}}%>
								
							</div>
						</div>
					</div>
					
                                  	
				</div>
                                                                  
                                                             
                                                                
                         
                                                                
 
                                                                
                                                             
       
                                                                
                                                                
                                                                
 
                                                                
                                                                
			</div>
                        
                      
                                                                  
                                                                  <div class="col-xl-12">
				<div class="row">
					<div class="col-sm-12 col-md-6">
                                            <h5 class="formcss"> 
                                                    <img style="width:150px;" src="./images/icons/bot.png" class="img img-circle" /><b>Datim Data Push(Bot)</b></h5>
						
						<hr>
						<div class="card text-left">
							<div class="card-body">
								
							
								  <% if(session.getAttribute("level")!=null){ if(!session.getAttribute("level").toString().equals("1")){  %>  
                 <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",uploads,")){%> 
                                                                
                                                                <a  href="DatimEntryAuto.jsp" class="btn btn-light"><i class="feather icon-cloud-snow"></i>Datim Data Entry (Bot)</a>
                                                                
                                                                
                                                                
                                                                  <%}}}else{}}%>
								
							</div>
						</div>
					</div>
					
                                  	
				
					<div class="col-sm-12 col-md-6">
                                            <h5 class="formcss"> 
                                                    <img style="width:150px;" src="./images/icons/emr.png" class="img img-circle" /><b>EMR Key Indicators Refresh(NDWH)</b></h5>
						
						<hr>
						<div class="card text-left">
							<div class="card-body">
								
							
								  <% if(session.getAttribute("level")!=null){ if(!session.getAttribute("level").toString().equals("1")){  %>  
                   <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",uploads,")){%> 
                                                                <a  href="ndwh_superset_template.xlsx" class="btn btn-light"><i class="feather icon-save"></i>Download Template</a>
                                                                <a  href="afyastat_verification.jsp" class="btn btn-light"><i class="feather icon-clipboard"></i>EMR Key Indicators Refresh</a>
                                                                
                                                                
       
                                                                
                                                                
                                                                  <%}}}else{}}%>
								
							</div>
						</div>
					</div>
					
                                  	
				</div>
                                                                  
                                                             
                                                                
                         
                                                                
 
                                                                
         <div class="row">
					
					
                                  	
				
					<div class="col-sm-12 col-md-12">
						
                                                <h5 class="formcss"> 
                                                    <img style="width:150px;" src="./images/icons/favorite.png" class="img img-circle" /><b>KHIS Data Favorites for Supply Chain, Data Verification and NDWH</b></h5>
						<hr>
						<div class="card text-left">
							<div class="card-body">
								
							
								  <% if(session.getAttribute("level")!=null){ if(!session.getAttribute("level").toString().equals("1")){  %>  
                   <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",uploads,")){%> 
                                                                <a  href="khis_to_imis_template.xlsx" class="btn btn-light"><i class="feather icon-save"></i>Download KHIS Template</a>
                                                                <a  href="https://hiskenya.org/dhis-web-data-visualizer/index.html#/WOIdhoNIYB8" class="btn btn-light"><i class="feather icon-save"></i>UTJ Monthly Favorite</a>
                                                               
                                                                <a  href="sc_arv_disp_curr_template.xlsx" class="btn btn-light"><i class="feather icon-save"></i>Download SC_ARV & SC_Curr Template</a>
                                                               
                                                                <a  href="https://hiskenya.org/dhis-web-data-visualizer/#/gdHXLyD2uvC" class="btn btn-light"><i class="feather icon-save"></i>SC_CURR_Favorite</a>
                                                                <a  href="https://hiskenya.org/dhis-web-data-visualizer/index.html#/VE4otzwd5Qm" class="btn btn-light"><i class="feather icon-save"></i>SC_ARV_Disp Favorite</a>
                                                                <!--<a  href="afyastat_verification.jsp" class="btn btn-light"><i class="feather icon-clipboard"></i>Data Refresh</a>-->
                                                                
                                                                
       
                                                                
                                                                
                                                                  <%}}}else{}}%>
								
							</div>
						</div>
					</div>
					
                                  	
				</div>                                                    
       
                                                                
                                                                
                                                                
 
                                                                
                                                                
			</div>
                      
                        
                      
                        
                        
                        
                        
                        
                      
                        
                        
                        
                        
		
			<!-- [ card ] end -->
		</div>