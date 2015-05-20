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
            <li class="has-sub ">
               <a href="load711.jsp">
               <!--<i class="icon-bookmark-empty"></i>--> 
               <span class="title">MOH 711A</span>
               <!--<span class="arrow "></span>-->
               </a>
              
            </li>
            <li class="active has-sub ">
               <a href="Form731.jsp">
               <!--<i class="icon-table"></i>--> 
               <span class="title">MOH 731</span>
               <!--<span class="selected"></span>-->
               <!--<span class="arrow open"></span>-->
               </a>
              
            </li>
            <li class="has-sub ">
               <a href="loadGender.jsp">
               <!--<i class="icon-th-list"></i>--> 
               <span class="title">Gender</span>
               <!--<span class="arrow "></span>-->
               </a>
              
            </li>
            <li class="has-sub ">
               <a href="loadVmmc.jsp">
               <!--<i class="icon-th-list"></i>--> 
               <span class="title">VMMC</span>
               <!--<span class="arrow "></span>-->
               </a>
              
            </li>
            <li class="has-sub ">
               <a href="loadNutrition.jsp">
               <!--<i class="icon-map-marker"></i>--> 
               <span class="title">Nutrition</span>
               <!--<span class="arrow "></span>-->
               </a>
              
            </li>
            <li class="">
               <a href="loadKmmp.jsp">
               <!--<i class="icon-bar-chart"></i>--> 
               <span class="title">KMMP</span>
               </a>
            </li>
            <li class="">
               <a href="loadTb.jsp">
               <!--<i class="icon-calendar"></i>--> 
               <span class="title">TB</span>
               </a>
            </li>
           <li class="has-sub ">
               <a href="#">
               <!--<i class="icon-th-list"></i>--> 
               <span class="title">Management</span>
               <span class="arrow "></span>
               </a>
               <ul class="sub">
                  <li ><a href="addUsers.jsp">Add Users</a></li>
                  <li ><a href="editProfile.jsp">Edit Profile</a></li>
           
               </ul>
            </li>
            <li class="has-sub ">
               <a href="#">
               <!--<i class="icon-th-list"></i>--> 
               <span class="title">reports</span>
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
