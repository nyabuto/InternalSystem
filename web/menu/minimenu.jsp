





<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="assets/js/jquery-1.8.3.min.js"></script>  
        <title></title>
        
        <script>
            
            
            
   jQuery(document).ready(function() {       
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
     
     <br/>
     <br/>
     
            <li>
               <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
               <div class="sidebar-toggler hidden-phone"></div>
               <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
            </li>
            <li>
            
            </li>
            <li class="start " style="background-color: #4b8df8;">
               <a href="#">
             <span class="title" ><b>Hi, <%if(session.getAttribute("userid")!=null){out.println(session.getAttribute("fullname").toString());}%></b></span>
               </a>
                </li>
                <br/>
                 <br/>
                 <br/>
                 <br/>
                  <br/>
                 <br/>
                
                <li class="active" style="border-top: 1px solid #e2e2e2 !important;">
                <a href="home.jsp">
               <i class="icon-home"></i> 
               <span class="title">Data Entry</span>
               </a>
            </li>
           
           <li class="has-sub ">
               <a href="#">
               <i class="icon-signin"></i>
               <span class="title">Management</span>
               <span class="arrow "></span>
               </a>
               <ul class="sub">
     <% if(session.getAttribute("level")!=null){ if(!session.getAttribute("level").toString().equals("1")){  %>  
     <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",maintenance,")){%> 
                 <li ><a href="addUsers.jsp"><i class="icon-plus"></i>Add Users</a></li>
                 <li ><a href="editProfile.jsp"><i class="icon-edit"></i>Edit Your Account</a></li>
                 <li ><a href="Access_Rights.jsp"><i class="icon-plus"></i>Access Rights</a></li>
                 <li ><a href="LockData.jsp"><i class="icon-lock"></i>Lock  Data Editing </a></li>                  
                 <li ><a href="editFacility.jsp"><i class="icon-hospital"></i>Facility management</a></li>
                  <li ><a href="ManageRatios.jsp"><i class="icon-edit"></i>Manage Ratios</a></li>
                 <%}}}else{}}%>
                   <li ><a href="editProfile.jsp"><i class="icon-edit"></i>Edit Profile</a></li>
               </ul>
            </li>
            
            
             <% if(session.getAttribute("level")!=null){ if(!session.getAttribute("level").toString().equals("1")){  %>  
             <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",uploads,")){%> 
                 
               <li class="has-sub ">
               <a href="#">
               <i class="icon-upload"></i>
               <span class="title">Upload Data</span>
               <span class="arrow "></span>
               </a>
                <ul class="sub">
                    
                  <li ><a href="upload_tb_raw_data.jsp" Title="Tb raw data"><i class="icon-plus"></i>Tb Raw Data (.xls)</a></li>
                  <li ><a href="loadExcel.jsp"><i class="icon-plus"></i>PMTCT Data(.xls)</a></li>
                  <li ><a href="UploadVL.jsp"><i class="icon-plus"></i>Viral Load raw (.xlsx)</a></li>
                  <li ><a href="load_eid_positive.jsp"><i class="icon-plus"></i>EID Positive raw (.xlsx)</a></li>
                  <li ><a href="load_eid_tested.jsp"><i class="icon-plus"></i>EID tested raw (.xlsx)</a></li>
                  <li ><a href="UploadNewANCData.jsp"><i class="icon-plus"></i>New on ANC [MOH 711] (.xlsx)</a></li>
                  <li ><a href="UploadDHISData.jsp"><i class="icon-plus"></i>DHIS Data (.xlsx)</a></li>
               </ul>
            </li>
             <%}}}else{}}%>
            
             <li class="has-sub ">
               <a href="#">
               <i class="icon-edit"></i>
               <span class="title">Data Cleaning Module</span>
               <span class="arrow "></span>
               </a>
                <ul class="sub">
                    
                  <li ><a href="DataCleaner.jsp" Title="TB/VL/EID Clean data module"><i class="icon-plus"></i>TB/VL/EID Data(.xlsx/xls)</a></li>
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
                   <li><a href="staticFormData.jsp">Static Reports</a></li>
                   <li ><a href="sitestracker.jsp">Reports Tracker <i class="icon-star"></i></a></li>
                   <li><a href="datimReport.jsp">Datim Report</a></li>
                  <li ><a href="DQA.jsp">DQA per Form</a></li>
                  <li ><a href="masterlist.jsp">Master List</a></li>
                  <li ><a href="T1SummaryReports.jsp">Training Report</a></li>
                  <li ><a href="IMIS_DHIS_Validation.jsp">IMIS DHIS Report</a></li>
                  <li ><a href="IMIS_DHIS_Variances.jsp">DHIS IMIS Variances</a></li>
            
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
          
            
            <li class="has-sub">
                <a href="T1FORM.jsp">
                <i class="icon-trophy"></i>
               <span class="title">Training Module</span>
               </a>
            </li>
             <li class="has-sub">
                <a href="pprev_index.jsp">
                <i class="icon-stop"></i>
               <span class="title">PP PREV</span>
               </a>
            </li>
            <li class="">
               <a href="logout.jsp">
               <i class="icon-user"></i> 
               <span class="title">Log Out</span>
               </a>
            </li>
         </ul>
      
    </body>
</html>
