





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
                 <%}}}else{}}%>
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
                  <li ><a href="loadExcel.jsp"><i class="icon-plus"></i>PMTCT Fo Data(.xls)</a></li>
                  <li ><a href="loadviralloadraw.jsp"><i class="icon-plus"></i>Viral Load raw (.xls)</a></li>
                  <li ><a href="load_eid_positive.jsp"><i class="icon-plus"></i>EID Positive raw (.xlsx)</a></li>
                  <li ><a href="load_eid_tested.jsp"><i class="icon-plus"></i>EID tested raw (.xlsx)</a></li>
                  
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
                  <!--<li ><a href="#">Edit Profile</a></li>-->
           
               </ul>
            </li>
            <%}}%>
            <li class="has-sub" style="display:none;" style="display:none;">
                <a href="../T1FORM.jsp">
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
