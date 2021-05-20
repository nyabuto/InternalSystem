 






<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="assets/js/jquery-1.8.3.min.js"></script>  
        <title></title>
        
        <script>
            
   jQuery(document).ready(function() 
   {       
         // initiate layout and plugins
       
    $("ul li").on("click", function() {
      $("ul li").removeClass("active");
      $(this).addClass("active");
    });
   });
            
        </script>
    </head>
    <body>

<ul>
            <li>
               <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
               <div class="sidebar-toggler hidden-phone"></div>
               <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
            </li>
            <li>
            
            </li>
            <li class="start " style="background-color: #69a4e0;">
               <a href="#">
              <%if(session.getAttribute("userid")!=null){out.println("<span><b>Hi ,"+session.getAttribute("fullname").toString()+"</span>");} else {%><a href="index.jsp" class="btn-info">Log in</a><%}%></b></span>
               </a>
                </li>
                <%if(session.getAttribute("userAccess")!=null){%>  
                <li>
                <a href="home.jsp">
               <i class="icon-home"></i> 
               <span class="title">Home</span>
               </a>
            </li>
            <%}%>
           
            <li class="has-sub ">
               <a href="#">
                <i class="icon-bar-chart"></i>
               <span class="title">Form 1A Excel</span>
               <span class="arrow "></span>
               </a>
               <ul class="sub">
                
                  <li > <a href="gettemplate.jsp"><i class="icon-download"></i>Download F1A Template</a></li>
                  
               <li >   <%if(session.getAttribute("userAccess")!=null){%>   
                  <a title="latest_version" href="uploadf1a.jsp"><i class="icon-upload"></i>Upload Form 1A</a>
                  
                  
                   <%} else {%> 
                   <a style="color:red;" href="index.jsp"><i class="icon-upload"></i><b>Login to access Form 1a upload module</b> </a> <%}%></li> 
                <li >   <%if(session.getAttribute("userAccess")!=null){%>   
                  <li ><a href="uploadf1av43.jsp"><i class="icon-upload"></i>Upload Form 1A V 4.0.3</a></li>
                  
                  
                   <%} else {%> 
                   <a style="color:red;" href="index.jsp"><i class="icon-upload"></i><b>Login to access Form 1a upload module</b> </a> <%}%></li> 
               
               <li >  <a href="Form1A_Tracker.jsp"><i class="icon-upload"></i>Form 1A Tracker</a></li>
               <!--<li >  <a href="form1agaps.jsp"><i class="icon-upload"></i>F1A Errors&Warnings</a></li>-->
            </ul>
            </li>
            <li class="has-sub ">
               <a href="#">
                <i class="icon-search"></i>
               <span class="title">FPT Excel</span>
               <span class="arrow "></span>
               </a>
               <ul class="sub">
                
                  <li > <a href="getFPTTemplate.jsp"><i class="icon-download"></i>Download FPT Template</a></li>
                  
               <li> <%if(session.getAttribute("userAccess")!=null){%>   
                  <a href="uploadhts_fpt.jsp"><i class="icon-upload"></i>Upload FPT Data</a>
                  
                   <%} else {%> 
                   <a style="color:red;" href="index.jsp"><i class="icon-upload"></i><b>Login to access FPT upload module</b> </a> <%}%></li> 
               <li >  <a href="FPT_Excel_Tracker.jsp"><i class="icon-upload"></i>FPT Excel Tracker</a></li>
               <li >  <a href="fptwebtracker.jsp"><i class="icon-upload"></i>FPT Web Tracker</a></li>
               <!--<li >  <a href="form1agaps.jsp"><i class="icon-upload"></i>F1A Errors&Warnings</a></li>-->
            </ul>
            </li>
            
            <li class="has-sub ">
               <a href="#">
                <i class="icon-search"></i>
               <span class="title">HTS Screening Excel</span>
               <span class="arrow "></span>
               </a>
               <ul class="sub">
                
                  <li > <a href="getHTSScreeningTemplate.jsp"><i class="icon-download"></i>Download HTS SCreening Template</a></li>
                  
               <li> <%if(session.getAttribute("userAccess")!=null){%>   
                  <a href="uploadhts_screening.jsp"><i class="icon-upload"></i>Upload HTS Screening Template</a>
                  
                   <%} else {%> 
                   <a style="color:red;" href="index.jsp"><i class="icon-upload"></i><b>Login to access HTS Screening upload module</b> </a> <%}%></li> 
               <!--<li >  <a href="HTS_Screening_Tracker.jsp"><i class="icon-upload"></i>HTS Screening Tracker</a></li>-->
               <!--<li >  <a href="form1agaps.jsp"><i class="icon-upload"></i>F1A Errors&Warnings</a></li>-->
            </ul>
            </li>
            
             <li class="has-sub ">
               <a href="#">
                <i class="icon-search"></i>
               <span class="title">TX_ML Excel</span>
               <span class="arrow "></span>
               </a>
               <ul class="sub">
                
                  <li > <a href="gettxmltemplate.jsp"><i class="icon-download"></i>Download TX_ML Template</a></li>
                  
               <li> <%if(session.getAttribute("userAccess")!=null){%>   
                  <a href="uploadtxml.jsp"><i class="icon-upload"></i>Upload TX_ML</a>
                  
                   <%} else {%> 
                   <a style="color:red;" href="index.jsp"><i class="icon-upload"></i><b>Login to access TxML upload module</b> </a> <%}%></li> 
               <li >  <a href="TXML_Tracker.jsp"><i class="icon-upload"></i>TX_ML Tracker</a></li>
               <!--<li >  <a href="form1agaps.jsp"><i class="icon-upload"></i>F1A Errors&Warnings</a></li>-->
            </ul>
            </li>
            
            
            
            <li class="has-sub ">
               <a href="#">
                <i class="icon-share"></i>
               <span class="title">DSD Excel</span>
               <span class="arrow "></span>
               </a>
               <ul class="sub">
                
                  <li > <a href="getDsdTemplate.jsp"><i class="icon-download"></i>Download DSD Template</a></li>
                  
               <li >   <%if(session.getAttribute("userAccess")!=null){%>   
                  <a href="uploaddsd.jsp"><i class="icon-upload"></i>Upload DSD</a>
                   <%} else {%> 
                   <a style="color:red;" href="index.jsp"><i class="icon-upload"></i><b>Login to access DSD upload module</b> </a> <%}%></li> 
               <!--<li >  <a href="Form1A_Tracker.jsp"><i class="icon-upload"></i>DSD Tracker</a></li>-->
               <!--<li >  <a href="form1agaps.jsp"><i class="icon-upload"></i>F1A Errors&Warnings</a></li>-->
            </ul>
            </li>
            
            
            <li class="has-sub ">
               <a href="#">
                <i class="icon-step-forward"></i>
               <span class="title">ACA/MCA Excel</span>
               <span class="arrow "></span>
               </a>
               <ul class="sub">
                
                <li> <a href="getCohorts.jsp"><i class="icon-signin"></i>Download ACA/MCA Template</a></li>
                  
               <li> <%if(session.getAttribute("userAccess")!=null){%>   
                  <a href="uploadacamca.jsp"><i class="icon-upload"></i>Upload ACA/MCA</a>
                   <%} else {%> 
                   <a style="color:red;" href="index.jsp"><i class="icon-upload"></i><b>Login to access ACA/MCA upload module</b> </a> <%}%></li> 
               <!--<li >  <a href="Form1A_Tracker.jsp"><i class="icon-upload"></i>DSD Tracker</a></li>-->
               <!--<li >  <a href="form1agaps.jsp"><i class="icon-upload"></i>F1A Errors&Warnings</a></li>-->
            </ul>
            </li>
            
            
            
            
            
            
            <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("year")!=null){%> 
            <li class="has-sub">
            
               <a href="form1a.jsp">
               <span class="title">Form 1A Web</span>
               </a>
            </li>
            <%}}%>
            
            
             <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",moh731,")){%> 
            <li class="has-sub" <% if(session.getAttribute("forms_holder")!=null){ if(!session.getAttribute("forms_holder").toString().contains("MOH 731 (New)")){  %> style="display:none;" <%} } else { %> style="display:none;"<%}%>>
            
               <a href="loadnew731.jsp">
               <span class="title">MOH 731 (New)</span>
               </a>
            </li>
            <%}}%>
            
             <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",vmmc,")){%> 
            <li class="has-sub" <% if(session.getAttribute("forms_holder")!=null){ if(!session.getAttribute("forms_holder").toString().contains("VMMC")){  %> style="display:none;" <%} } else { %> style="display:none;"<%}%>>
               <a href="loadVmmc.jsp">
               <span class="title">VMMC</span>
               </a>
              
            </li>
            <%}}%>
            
             <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",kmmp,")){%> 
            <li class=""  <% if(session.getAttribute("forms_holder")!=null){ if(!session.getAttribute("forms_holder").toString().contains("KMMP")){  %> style="display:none;" <%} } else { %> style="display:none;"<%} %>>
               <a href="loadKmmp.jsp">
               <span class="title">KMMP</span>
               </a>
            </li>
            <%}}%>
              <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",checklist,")){%> 
            <li class=""  <% if(session.getAttribute("forms_holder")!=null){ if(!session.getAttribute("forms_holder").toString().contains("DQA Checklist")){  %> style="display:none;" <%} } else { %> style="display:none;"<%} %>>
               <a href="checklist.jsp">
               <span class="title">DQA Checklist</span>
               </a>
            </li>
            <%}}%>
           
             <% if(session.getAttribute("level")!=null){ %>
                  <li class="has-sub ">
               <a href="#">
               <i class="icon-signin"></i>
               <span class="title">Management</span>
               <span class="arrow "></span>
               </a>
                <ul class="sub">
                
               <% if(!session.getAttribute("level").toString().equals("1")){  %>  
                 <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",maintenance,")){%>
            
          
                    
                  
                  <li ><a href="refreshDHIS2.jsp"><i class="icon-plus"></i>DHIS2 Data Refresh</a></li>
                  <li ><a href="Access_Rights.jsp"><i class="icon-plus"></i>Access Rights</a></li>
                  <li ><a href="datalock.jsp"><i class="icon-plus"></i>Lock / Un lock Editing </a></li>
                  <li ><a href="addUsers.jsp"><i class="icon-plus"></i>Add Users</a></li>
                  <li ><a href="editFacility.jsp"><i class="icon-edit"></i>facility management</a></li>
                  <li ><a href="ManageRatios.jsp"><i class="icon-edit"></i>Manage Ratios</a></li>
                  <li><a href="uploadgbv.jsp"><i class="icon-upload"></i>Upload GBV</a></li>
                 <li> <a href="VL_autoupdate_results.jsp"><i class="icon-upload"></i>Autoupdate VL Results</a></li>
                 <li> <a href="Sync_VL_Results.jsp"><i class="icon-upload"></i>Sync VL Results</a></li>
                 <li> <a href="Final_Sync_VL_Results.jsp"><i class="icon-upload"></i>Final Sync VL Results</a></li>
                
                  
                  

              
              <%}}}else{}%>
                <li ><a href="editProfile.jsp"><i class="icon-edit"></i>Edit Profile</a></li>
 </ul>
            </li>
<%}%>
            
            
             <% if(session.getAttribute("level")!=null){ if(!session.getAttribute("level").toString().equals("1")){  %>  
                 <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",uploads,")){%> 
                 
               <li class="has-sub ">
               <a href="#">
               <i class="icon-upload"></i>
               <span class="title">Upload Data</span>
               <span class="arrow "></span>
               </a>
                <ul class="sub">
                    
                  <!--<li ><a href="upload_tb_raw_data.jsp" Title="Tb raw data"><i class="icon-plus"></i>Tb Raw Data (.xls)</a></li>-->
                  <!--<li ><a href="loadExcel.jsp"><i class="icon-plus"></i>PMTCT Data(.xls)</a></li>-->
                
                  <li ><a href="UploadVL.jsp"><i class="icon-plus"></i>Viral Load raw (.xlsx)</a></li>
                  <li ><a href="uploadVL_Surge.jsp"><i class="icon-plus"></i>Viral Load raw Surge(.xlsx)</a></li>
                  <li ><a href="load_eid_positive.jsp"><i class="icon-plus"></i>NASCOP EID POS (.xlsx)</a></li>
                  <li ><a href="load_eid_tested.jsp"><i class="icon-plus"></i>NASCOP EID TST (.xlsx)</a></li>
                  <!--<li ><a href="UploadEID.jsp"><i class="icon-plus"></i>EID Data (.xlsx)</a></li>-->
                  <!--<li ><a href="Upload_CXCA.jsp"><i class="icon-plus"></i>Cervical Cancer (.xlsx)</a></li>-->
                  <!--<li ><a href="Upload_PrePNew.jsp"><i class="icon-plus"></i>Upload PrEP New (.xlsx)</a></li>-->
                  <!--<li ><a href="UploadDHISData.jsp"><i class="icon-plus"></i>DHIS Data (.xlsx)</a></li>-->
               </ul>
            </li>
             <%}}}else{}}%>
            
              <li class="has-sub ">
               <a href="#">
               <i class="icon-edit"></i>
               <span class="title">Viral Load</span>
               <span class="arrow "></span>
               </a>
                <ul class="sub">
                    <li ><a href="uploademrvl.jsp"><i class="icon-plus"></i>Upload Viral Load Kenyaemr(.xlsx)</a></li>  
                    <li><a href="Tracker_kenyaEMR.jsp"><i class="icon-download"></i>VL Reports Tracker</a></li>
                   <li ><a href="PullVlResults.jsp" Title="Clean VL data"><i class="icon-plus"></i>Check VL Results(.xlsx)</a></li>
                  <!--<li ><a href="DataCleaner.jsp" Title="EID Clean data module"><i class="icon-plus"></i>EID/TB Error Checking(.xlsx)</a></li>-->
                  <!--<li ><a href="EIDPrevData.jsp" Title="EID Clean data module"><i class="icon-plus"></i>Load <b>1&#189;</b> Yrs ago data(.xlsx)</a></li>-->
               </ul>
            </li>
            
          <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",reports,")){%>   
            <li class="has-sub ">
               <a href="#">
               <i class="icon-bar-chart"></i>
               <span class="title">Reports</span>
               <span class="arrow "></span>
               </a>
               <ul class="sub">
                  <li ><a href="rawdata.jsp">Facility raw data</a></li>
                  <!--<li><a href="staticFormData.jsp">Static Reports</a></li>-->
                  <!--<li ><a href="sitestracker.jsp">Reports Tracker <i class="icon-star"></i></a></li>-->
                   <!--<li><a href="datimReport.jsp">Datim Report</a></li>-->
                   <li><a href="DatimOutput.jsp">Datim/Raw Reports [New]</a></li>
                   <li><a href="DatimScreens.jsp">Datim Screens [Excel]</a></li>
                   <!--<li><a href="ewi.jsp">EWI Monthly Data</a></li>-->
                   <li><a href="RawQuery.jsp">Run Queries</a></li>
                  <li><a href="DQA_Reports.jsp">Data Verification Reports</a></li>
                  <!--<li ><a href="DQA.jsp">DQA per Form</a></li>-->
                  <li ><a href="masterlist.jsp">Master List</a></li>
                  <li ><a href="T1SummaryReports.jsp">Training Report</a></li>
                  <!--<li ><a href="IMIS_DHIS_Validation.jsp">IMIS DHIS Report</a></li>-->
                  <!--<li ><a href="IMIS_DHIS_Variances.jsp">DHIS IMIS Variances</a></li>-->
               </ul>
            </li>
            <%}}%>
            
              <li class="has-sub ">
               <a href="#">
               <i class="icon-envelope"></i>
               <span class="title">Gap Analysis</span>
               <span class="arrow "></span>
               </a>
               <ul class="sub">
                  <li ><a href="gapanalysis.jsp"> Generate Report</a></li>
                  
                   <%if(session.getAttribute("access_gapanalysis")!=null){if(session.getAttribute("access_gapanalysis").toString().equals("1")){%> 
                  <li><a href="UploadGaps.jsp">Upload Gaps</a></li>
                  <%}}%>
                   <%if(session.getAttribute("access_gapanalysis")!=null){%> 
                  <li><a href="ManageGaps.jsp">Manage Gaps</a></li>
                  <%}%>
                   <%if(session.getAttribute("access_gapanalysis")!=null){if(session.getAttribute("access_gapanalysis").toString().equals("1")){%>
                  <li ><a href="DownloadGaps.jsp">Download Verified Gaps</a></li>
                  <%}}%>
               </ul>
            </li>            
           <%if(session.getAttribute("userAccess")!=null){%> 
            <li class="has-sub">
                <a href="T1FORM.jsp">
                <i class="icon-trophy"></i>
               <span class="title">Training Module</span>
               </a>
            </li>
<li  class="has-sub">
                <a id="emrstatus" href="EMR.jsp">
                <i class="icon-align-justify"></i>
               <span class="title"><font color="red"><b>EMR Status</b></font></span>
               </a>
            </li>
            <li class="">
               <a href="logout.jsp">
               <i class="icon-user"></i> 
               <span class="title">Log Out</span>
               </a>
            </li>
            <%}%>
            
         </ul>
   </body>
</html>
