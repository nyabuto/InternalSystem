





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
               <span class="title">Home</span>
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
                  <li ><a href="addUsers.jsp"><i class="icon-plus"></i>Add Users</a></li>
                  <li ><a href="editFacility.jsp"><i class="icon-edit"></i>facility management</a></li>
                  <%}else{}}%>
                  
                  <li ><a href="editProfile.jsp"><i class="icon-edit"></i>Edit Profile</a></li>
           
               </ul>
            </li>
            <li class="has-sub ">
               <a href="#">
                <i class="icon-bar-chart"></i>
               <span class="title">Reports</span>
               <!--<span class="arrow "></span>-->
               </a>
<!--               <ul class="sub">
                  <li ><a href="#">Add Users</a></li>
                  <li ><a href="#">Edit Profile</a></li>
           
               </ul>-->
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
