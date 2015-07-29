 






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
            <li class="has-sub" <% if(session.getAttribute("forms_holder")!=null){ if(!session.getAttribute("forms_holder").toString().contains("MOH 711A")){  %> style="display:none;" <%} } else { %> style="display:none;"<%}%>>
           
               <a href="load711.jsp">
               <!--<i class="icon-bookmark-empty"></i>--> 
               <span class="title">MOH 711A</span>
               <!--<span class="arrow "></span>-->
               </a>
              
            </li>
            <%}}%>
             <%if(session.getAttribute("userAccess")!=null){if(session.getAttribute("userAccess").toString().contains(",moh731,")){%> 
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
               <a href="loadNutrition.jsp">
               <!--<i class="icon-map-marker"></i>--> 
               <span class="title">Nutrition</span>
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
                  <li ><a href="addUsers.jsp"><i class="icon-plus"></i>Add Users</a></li>
                  <li ><a href="editFacility.jsp"><i class="icon-edit"></i>facility management</a></li>
                  <%}}}else{}}%>
                  
                  <li ><a href="editProfile.jsp"><i class="icon-edit"></i>Edit Profile</a></li>
                  
                 
                 
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
                  <li><a href="reportsTracker.jsp">Reports Tracker</a></li>
                   <li><a href="datimReport.jsp">Datim Report</a></li>
                  <li ><a href="DQA.jsp">DQA</a></li>
                 
                  <!--<li ><a href="#">Edit Profile</a></li>-->
           
               </ul>
            </li>
            <%}}%>
            <li class="">
               <a href="logout.jsp">
               <i class="icon-user"></i> 
               <span class="title">Log Out</span>
               </a>
            </li>
         </ul>
   </body>
</html>
