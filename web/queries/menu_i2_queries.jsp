<%-- 
    Document   : bfci_tracker_modal
    Created on : Oct 15, 2023, 1:53:20 PM
    Author     : Emmanuel Kaunda
--%>

<%@page import="General.IdGenerator2"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>





          
   <div class="row">
<div class="col-xl-12 col-md-12">
				<div class="card">
					

                                    <div class="row">
            <!-- [ sample-page ] start -->
            <div class="col-sm-12">
                <div class="card">

                    <div class="card-header">
                        <h6 class=" btn-primary " style="text-align: center;padding-top:6px;padding-bottom:6px;">Run Queries:  <span style="color:yellow;">You can now use date pickers to specify query date range.</span></h6>
                        <div class="card-header-right">
                            <div class="btn-group card-option">
                                <button type="button" class="btn dropdown-toggle btn-icon" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="feather icon-more-horizontal"></i>
                                </button>
                                <ul class="list-unstyled card-option dropdown-menu dropdown-menu-right">
                                    <!--<li class="dropdown-item full-card"><a href="#!"><span><i class="feather icon-maximize"></i>maximize</span><span style="display:none"><i class="feather icon-minimize"></i> Restore</span></a></li>-->
                                    <!--<li class="dropdown-item minimize-card"><a href="#!"><span><i class="feather icon-minus"></i> collapse</span><span style="display:none"><i class="feather icon-plus"></i> expand</span></a></li>-->
                                    <li class="dropdown-item reload-card"><a href="#!"><i class="feather icon-refresh-cw"></i> reload</a></li>
                                    <!--<li class="dropdown-item close-card"><a href="#!"><i class="feather icon-trash"></i> remove</a></li>-->
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                    
                        
                        
                        <div class="row">
                        
                        
                         <form action="RawQuery" method="post"   >
                       <div  class="portlet-body form" >
                           
                           <input type='hidden' name='qname' id='qname'>
                            <div class="form-inline">
                            
                                    
                                        <label class="form-group col-md-1 floating-label" for="facility"> <b>Queries</b> </label>
                                        <% IdGenerator2 ig = new IdGenerator2();
                                        
                                        %>
                                        
                                         <select style="width:20%;"  class='form-group  col-lg' name="queryhistory" id="queryhistory"  onchange="showqry();replaceStartDate();replaceEndDate();"  ></select>
                                          <div style="display:none" class="form-group startdatediv">
                                         <label class="form-group col-md-2 floating-label"><b>Start Date:</b></label>
                                         <input readonly onchange="replaceStartDate();"  type="text" title="" value="<%=ig.LastQuarterStartDate(ig.LastMonthStartDate())%>" class="form-control input-lg tarehe" name="startdate" autocomplete="off" id="startdate">
                                          <input type="hidden" name="sd" id="sd">
                                          </div>
                                         
                                         <div style="display:none" class="form-group enddatediv">
                                         <label class="form-group col-md-2 floating-label"><b>End Date Date:</b></label>
                                         <input readonly onchange="replaceEndDate();"  type="text" title="" value="<%=ig.LastMonthEndDate()%>" class="form-control input-lg tarehe" name="enddate" id="enddate" autocomplete="off">
                                        <input type="hidden" name="ed" id="ed">
                                         </div>
                                
                                
                            <button type="submit" class="btn btn-success mx-sm-3 mb-2" style="font-weight: bolder;">Execute Query</button>
                            
                           </div>
<!--                           <select class="form-group" name="queryhistory" id="queryhistory" style='width:80%;'>
                               <option value=''>Query History</option>
                               
                           </select> -->
                          <hr/>
                    </div> 
                      
                     
                     <div class="portlet-body form"  id="upload_area">
                        <!-- BEGIN FORM-->
                        
                            <textarea    <% if(session.getAttribute("kd_session")!=null){  if(!session.getAttribute("level").toString().equals("1")){  %> <%if(session.getAttribute("userAccess")!=null){ if(session.getAttribute("userAccess").toString().contains(",maintenance,")){%>  <%} else {%>   readonly    <%}}}}%>     name="query" id="query" value="" class="form-control" rows="29" cols="40" spellcheck="false" placeholder="Enter your query here" required style="background-color:#080808;color:white;border-radius:20px ;"><%if (session.getAttribute("query") != null) { out.println(session.getAttribute("query"));  session.removeAttribute("query");}%></textarea>   
                        <br><br><br><br>
                        
                        
                        
                        <div class="form-actions">
                            <button type="submit" class="btn btn-success" style="font-weight: bolder;">Execute Query</button>

                           </div>
                       <div class="card">
                           <br>
                           <h4>Note:</h4>
                        <ul>
                            <li class="btn  btn-outline-primary">This module is only used to generate reports by Running queries. Please note that we only run stored procedures and select queries. Other commands like insert,update,replace into will NOT be EXECUTED.</li>
                        </ul>
                        </div>
                        </div>
                        </form>
                        
                          <div style="color: red; font-weight: bold; font-size: 20px;">
          <%if (session.getAttribute("errors") != null) {
            out.println(session.getAttribute("errors"));  
            session.removeAttribute("errors");
             }
         %>
         
          </div>
         
         </div>
                        
                    </div>
                </div>
            </div>
            <!-- [ sample-page ] end -->
        </div>

	






</div>
</div>
			
</div>
         
   
       
            

