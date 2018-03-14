 






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
            <li>
               <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
               <div class="sidebar-toggler hidden-phone"></div>
               <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
            </li>
            <li>
            
            </li>
            <li class="start " style="background-color: #69a4e0;">
               <a href="#">
             <span class="title" ><b>Hi, <%if(session.getAttribute("userid")!=null){out.println(session.getAttribute("fullname").toString());}%></b></span>
               </a>
                </li>
                <li>
                <a href="home.jsp">
               <i class="icon-home"></i> 
               <span class="title">Home</span>
               </a>
            </li>
             <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",moh711,")){%> 
            <li class="has-sub" <%   /**if(session.getAttribute("forms_holder")!=null)  */ if(1==2){ if(!session.getAttribute("forms_holder").toString().contains("MOH 711 (New)")){  %> style="display:none;" <%} } else { %> style="display:none;"<%}%>>
           
           
              
                <a href="load711.jsp">
               <!--<i class="icon-bookmark-empty"></i>--> 
               <span class="title">MOH 711 A</span>
               <!--<span class="arrow "></span>-->
               </a>
                
            </li>
            <%}}%>
             <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",moh731,")){%> 
            <li class="has-sub" <% if(session.getAttribute("forms_holder")!=null){ if(!session.getAttribute("forms_holder").toString().contains("MOH 731 (New)")){  %> style="display:none;" <%} } else { %> style="display:none;"<%}%>>
            
               <a href="loadnew731.jsp">
               <!--<i class="icon-table"></i>--> 
               <span class="title">MOH 731 (New)</span>
               <!--<span class="selected"></span>-->
               <!--<span class="arrow open"></span>-->
               </a>
<!--              forms_holder-->
            </li>
            
            <li class="has-sub" <% if(session.getAttribute("forms_holder")!=null){ if(!session.getAttribute("forms_holder").toString().contains("MOH 731")){  %> style="display:none;" <%} } else { %> style="display:none;"<%}%>>
            
               <a href="Form731.jsp">
               <!--<i class="icon-table"></i>--> 
               <span class="title">MOH 731</span>
               <!--<span class="selected"></span>-->
               <!--<span class="arrow open"></span>-->
               </a>
<!--              forms_holder-->
            </li>
            <%}}%>
             <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",gender,")){%> 
            <li class="has-sub" <% if(session.getAttribute("forms_holder")!=null){ if(!session.getAttribute("forms_holder").toString().contains("Gender")){out.println("____hey");  %> style="display:none;" <%} } else { %> style="display:none;"<%}%> >
               <a href="loadGender.jsp">
               <!--<i class="icon-th-list"></i>--> 
               <span class="title">Gender</span>
               <!--<span class="arrow "></span>-->
               </a>
              
            </li>
            <%}}%>
             <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",vmmc,")){%> 
            <li class="has-sub" <% if(session.getAttribute("forms_holder")!=null){ if(!session.getAttribute("forms_holder").toString().contains("VMMC")){  %> style="display:none;" <%} } else { %> style="display:none;"<%}%>>
               <a href="loadVmmc.jsp">
               <!--<i class="icon-th-list"></i>--> 
               <span class="title">VMMC</span>
               <!--<span class="arrow "></span>-->
               </a>
              
            </li>
            <%}}%>
             <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",nutrition,")){%> 
            <li class="has-sub"  <% if(session.getAttribute("forms_holder")!=null){ if(!session.getAttribute("forms_holder").toString().contains("Nutrition")){  %> style="display:none;" <%} } else { %> style="display:none;"<% } %>>
               <a href="load733B.jsp">
               <!--<i class="icon-map-marker"></i>--> 
               <span class="title">733B (Nutrition)</span>
               <!--<span class="arrow "></span>-->
               </a>
              
            </li>
            <%}}%>
             <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",kmmp,")){%> 
            <li class=""  <% if(session.getAttribute("forms_holder")!=null){ if(!session.getAttribute("forms_holder").toString().contains("KMMP")){  %> style="display:none;" <%} } else { %> style="display:none;"<%} %>>
             
                <a href="loadKmmp.jsp">
               <!--<i class="icon-bar-chart"></i>--> 
               <span class="title">KMMP</span>
               </a>
            </li>
            <%}}%>
           <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",tb,")){%>  
            <li class="" <% if(session.getAttribute("forms_holder")!=null){ if(!session.getAttribute("forms_holder").toString().contains("TB")){  %> style="display:none;" <%} } else { %> style="display:none;"<%} %>>
               <a href="loadTb.jsp">
               <!--<i class="icon-calendar"></i>--> 
               <span class="title">TB</span>
               </a>
            </li>
            <%}}%>
            
             <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",moh711,")){%> 
            <li class="has-sub" <% if(session.getAttribute("forms_holder")!=null){ if(!session.getAttribute("forms_holder").toString().contains("MOH 711 (New)")){  %> style="display:none;" <%} } else { %> style="display:none;"<%}%>>
           
             <a href="loadnew711.jsp">
               <!--<i class="icon-bookmark-empty"></i>--> 
               <span class="title">MOH 711 (New)</span>
               <!--<span class="arrow "></span>-->
               </a> 
              
               
                
            </li>
            <%}}%>
            
             <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",gender,")){%> 
            <li class="has-sub" <% if(session.getAttribute("forms_holder")!=null){ if(!session.getAttribute("forms_holder").toString().contains("Gender")){ %> style="display:none;" <%} } else { %> style="display:none;"<%}%> >
               <a href="loadsgbv.jsp">
               <!--<i class="icon-th-list"></i>--> 
               <span class="title">SGBV</span>
               <!--<span class="arrow "></span>-->
               </a>
              
            </li>
            <%}}%>
            
             <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",gender,")){%> 
            <li class="has-sub" <% if(session.getAttribute("forms_holder")!=null){ if(!session.getAttribute("forms_holder").toString().contains("IPT")){  %> style="display:none;" <%} } else { %> style="display:none;"<%}%> >
               <a href="IPT.jsp">
               <!--<i class="icon-th-list"></i>--> 
               <span class="title">IPT</span>
               <!--<span class="arrow "></span>-->
               </a>
              
            </li>
            <%}}%>
            
             <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",gender,")){%> 
            <li class="has-sub" <% if(session.getAttribute("forms_holder")!=null){ if(!session.getAttribute("forms_holder").toString().contains("Index Testing")){  %> style="display:none;" <%} } else { %> style="display:none;"<%}%> >
               <a href="loadIndexTesting.jsp">
               <!--<i class="icon-th-list"></i>--> 
               <span class="title">Index Testing</span>
               <!--<span class="arrow "></span>-->
               </a>
              
            </li>
            <%}}%>
            
            
            
            
            
<!--             <li class="">
               <a href="#">
               <i class="icon-calendar"></i> 
               <span class="title">HEI</span>
               </a>
            </li>-->
           <li class="has-sub ">
               <a href="#">
               <i class="icon-signin"></i>
               <span class="title">Management</span>
               <span class="arrow "></span>
               </a>
                <ul class="sub">
                    
                  <% if(session.getAttribute("level")!=null){ if(!session.getAttribute("level").toString().equals("1")){  %>  
                 <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",maintenance,")){%> 
                  <li ><a href="Access_Rights.jsp"><i class="icon-plus"></i>Access Rights</a></li>
                  <li ><a href="LockData.jsp"><i class="icon-plus"></i>Lock / Un lock Editing </a></li>
                  <li ><a href="addUsers.jsp"><i class="icon-plus"></i>Add Users</a></li>
                  <li ><a href="editFacility.jsp"><i class="icon-edit"></i>facility management</a></li>
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
                  <li ><a href="loadviralloadraw.jsp"><i class="icon-plus"></i>Viral Load raw (.xls)</a></li>
                  <li ><a href="load_eid_positive.jsp"><i class="icon-plus"></i>EID Positive raw (.xlsx)</a></li>
                  <li ><a href="load_eid_tested.jsp"><i class="icon-plus"></i>EID tested raw (.xlsx)</a></li>
                  <li ><a href="UploadNewANCData.jsp"><i class="icon-plus"></i>New on ANC [MOH 711] (.xlsx)</a></li>
                  
                   <!--<li ><a href="loadKEPMS.jsp"><i class="icon-plus"></i>Kepms Access Data (.mdb)</a></li>-->
               </ul>
            </li>
             <%}}}else{}}%>
            
            
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
                  <li><a href="reportsTracker.jsp">Reports Tracker</a></li>
                   <li><a href="datimReport.jsp">Datim Report</a></li>
                  <li ><a href="DQA.jsp">DQA per Form</a></li>
                  <li ><a href="gapanalysis.jsp">Gap Analysis</a></li>
                  <li ><a href="masterlist.jsp">Master List</a></li>
                  <li ><a href="analysisreports.jsp">Analysis Report</a></li>
                  <li ><a href="T1SummaryReports.jsp">Training Report</a></li>
                 
                  <!--<li ><a href="#">Edit Profile</a></li>-->
           
               </ul>
            </li>
            <%}}%>
            <li class="has-sub">
                <i class="glyphicon glyphicon-education"></i> 
                <a href="T1FORM.jsp">
               <span class="title">Trainings</span>
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
