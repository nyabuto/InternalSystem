<%-- 
    Document   : filter_elements
    Created on : Mar 24, 2025, 8:16:02 PM
    Author     : Emmanuel Kaunda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="card">
      <div class="card-body">
                     <!--     <form>-->
                            <div class="row row-cols-lg-7">
                                <div class="col">
                                    <div class="form-group">
                                        <label class="floating-label" for="county">County</label>
                                        <select onchange="loadmdt(); updtimis();" required='true' class='form-control county'   ></select>
                                    </div>
                                </div>
                                
                                
                                  <div class="col">
                                    <div class="form-group">
                                        <label class="floating-label" for="mdt">Region</label>
                                        <select onchange="loadsubcounty();updtimis();" required='true' class='form-control mdt'   ></select>
                                    </div>
                                </div>
                                
                                
                                <div class="col">
                                    <div class="form-group">
                                        <label class="floating-label" for="subcounty">Sub-County</label>
                                        <select onchange="loadfacils();updtimis();" required='true' class='form-control subcounty'   ></select>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <label class="floating-label" for="facility">Facility</label>
                                         <select onchange="updtimis();" required='true' class='form-control facility'   ></select>
                                    </div>
                                </div>
                                 <div class="col">
                                    <div class="form-group">
                                        <label class="floating-label" for="startyearmonth">Start Period</label>
                                        <select onchange="updtimis();" required='true' class='form-control startdate'   ></select>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <label class="floating-label" for="facility">End Period</label>
                                         <select onchange="expectedperformance();updtimis();" required='true' class='form-control enddate'   ></select>
                                    </div>
                                </div>
                                 <div class="col">
                                    <div class="form-group">
                                        <label class="floating-label" for="facility">Region Grouping</label>
                                        <select onchange="updtimis();" required='true' class='form-control groupbyregion'   >
                                           <option value="`County` as Region,">County</option> 
                                            <option value="`mdt` as Region,">MDT</option>
                                            
                                            <option value="\'UTJ\' as Region,">Overall</option>
                                            <option value="`SubPartnerNom` as Region,">Facility (Discouraged)</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
<!--                        </form>-->
                    </div>
                </div>
