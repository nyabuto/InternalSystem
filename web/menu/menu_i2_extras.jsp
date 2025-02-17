<%@page import="java.util.HashMap"%>
<div class="row">
    <!-- [ card ] start -->
<%

String un="";
String pw="";

if(session.getAttribute("kd_session")!=null)
{

HashMap<String,String> us= new HashMap();
    
us=(HashMap)session.getAttribute("kd_session");

un=us.get("username");
pw=us.get("password");

}

%>

    <head>

        <style>

            .formcss 
            {                                    
                text-align: center;
                color:#4b8df8;
                border-color:#4b8df8;
            }
        </style>

    </head>

    <div class="col-xl-12">
        <div  class="row">
            
            
            <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=dailyart.jsp" class="btn btn-light">
                            <img style="width:130px;"  src="images/icons/tx.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>Daily ART</b></h5>
                <hr>
                </div>
            </div>
            
            <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=importDtriangulation.jsp" class="btn btn-light">
                            <img style="width:130px;" title="daily Data Triangulation"  src="images/icons/verification.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>Daily Triangulation</b></h5>
                <hr>
                </div>
            </div>
            
            
           
            
            <div class="col-sm-12 col-md-2">
               
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="hts_rri_login.jsp?rn=0.7820723379294034" class="btn btn-light">
                            <img style="width:130px;" title="live version of htr rri which shows data from all sites at the imis server"  src="images/htsrri.png"/></a>
                        

                    </div>
                     
                <h5 class="formcss"><b>HTS RRI LIVE</b></h5>
                <hr>
                </div>
            </div>
            
            
            
            <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=importpns.jsp" class="btn btn-light">
                            <img style="width:130px;"  src="images/pns.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>Daily PNS</b></h5>
                <hr>
                </div>
            </div>
            

         <div class="col-sm-12 col-md-2">
               
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/htsrri/index.jsp?p=909090" class="btn btn-light">
                            <img style="width:130px;"  src="images/htsrri.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>HTS RRI Offline</b></h5>
                <hr>
                </div>
              
            </div>

            
            
            <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/htsrri/hts_self.jsp" class="btn btn-light">
                            <img style="width:130px;" title="HTS Self summary"  src="images/htsself.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>HTS Self Testing</b></h5>
                <hr>
                </div>
            </div>
            
            
           
           


        </div>


    </div>


  <div class="col-xl-12">
        <div class="row">
            
             <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=hfr.jsp" class="btn btn-light">
                            <img style="width:130px;"  src="images/hfr.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>HFR</b></h5>
                <hr>
                </div>
            </div>
            
            <div class="col-sm-12 col-md-2">
               
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=rri_gaps_main.jsp" class="btn btn-light">
                            <img style="width:130px;" title="Service Gaps like COT tracker, Reasons for declining Prep, STF"  src="images/rri.png"/></a>
                        

                    </div>
                     <h5 class="formcss"><b>RRI Service Gaps</b></h5>
                <hr>
                </div>
            </div>
            
            <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=me_matrix_main.jsp" class="btn btn-light">
                            <img title="M&E Matrix" style="width:130px;"  src="images/me_matrix.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>M&E Matrix</b></h5>
                <hr>
                </div>
            </div>
            
             <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/VL" class="btn btn-light">
                            <img title="vlmis" style="width:130px;"  src="images/vl.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>VLMIS</b></h5>
                <hr>
                </div>
            </div>
            

           <div class="col-sm-12 col-md-2">
                
             
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="courses.jsp" class="btn btn-light">
                            <img style="width:130px;" title="" src="images/courses.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>Course Completion</b></h5>
                   <hr>
                </div>
                 
                
            </div>

            
            
            <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=upi_main.jsp" class="btn btn-light">
                            <img title="UPI Weekly Progress" style="width:130px;"  src="./images/icons/kenyaemr.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>UPI Weekly Progress</b></h5>
                <hr>
                </div>
            </div>
            
            
            
            
           
           


        </div>


    </div>

 <div class="col-xl-12">
        <div class="row">
            
             
            
            
            
            
             <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=mortality_audit.jsp" class="btn btn-light">
                            <img style="width:130px;"  src="images/mot_audit.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>Mortality Audit</b></h5>
                <hr>
                </div>
            </div>
            
            
            <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=ahd_main.jsp" class="btn btn-light">
                            <img style="width:130px;"  src="images/ahd.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>Advanced HIV Disease (AHD)</b></h5>
                <hr>
                </div>
            </div>
            
              <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=hpdm_main.jsp" class="btn btn-light">
                            <img style="width:130px;"  src="images/hpdm.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>Hypertension & Diabetes </b></h5>
                <hr>
                </div>
            </div>
            
             <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=clinicalforms.jsp?frm=hpv_vaccination&act=9595" class="btn btn-light">
                            <img style="width:130px;"  src="images/hpv.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>HPV Vaccination</b></h5>
                <hr>
                </div>
            </div>
            
            <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=clinicalforms.jsp?frm=cxca_pos&act=9595" class="btn btn-light">
                            <img style="width:130px;"  src="images/cxca_screen.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>CXCA Positives Followup</b></h5>
                <hr>
                </div>
            </div> 
            
            
            <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=clinicalforms.jsp?frm=client_exit_form&act=9595" class="btn btn-light">
                            <img style="width:130px;"  src="images/client_exit.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>Client Exit Interview</b></h5>
                <hr>
                </div>
            </div> 
            
           

</div>
            </div>

    
     <div class="col-xl-12">
        <div class="row">
            
            
            
            
             <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=retention.jsp" class="btn btn-light">
                    <img title="Retention Audit Module" style="width:130px;"  src="images/retention.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>Retention Audit</b></h5>
                <hr>
                </div>
            </div>
            
            
            
          <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=hei_weekly_main.jsp" class="btn btn-light">
                            <img style="width:130px;"  src="images/hei_weekly.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>HEI Weekly Form</b></h5>
                <hr>
                </div>
            </div>   
            
            
            
            <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=clinicalforms.jsp?frm=hei_audit&&act=9595" class="btn btn-light">
                            <img style="width:130px;"  src="images/heiaudit.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>HEI Audit</b></h5>
                <hr>
                </div>
            </div>
            
            <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=stockverification.jsp" class="btn btn-light">
                            <img style="width:130px;"  src="images/stocks.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>Stock Verification</b></h5>
                <hr>
                </div>
            </div>
            
            <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/cqi" class="btn btn-light">
                            <img style="width:130px;"  src="images/icons/cqi.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>UTJ CQI </b></h5>
                <hr>
                </div>
            </div>
            
            <div class="col-sm-12 col-md-2">
                
                <div class="card text-left">
                    <div class="card-body">



                        <a target="_blank" href="https://usaidtujengejamii.org:8443/Cohorts/index.jsp?ac=<%=un%>&p=<%=pw%>&rd=clinicalforms.jsp?frm=mother&act=9595" class="btn btn-light">
                            <img style="width:130px;"  src="images/pmtct_ovc.png"/></a>
                        

                    </div>
                    <h5 class="formcss"><b>PMTCT OVC Forms</b></h5>
                <hr>
                </div>
            </div> 
            
            
 </div>
            </div>


    <!-- [ card ] end -->
</div>