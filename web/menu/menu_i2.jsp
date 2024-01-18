<%-- 
    Document   : user
    Created on : Feb 27, 2023, 12:12:13 PM
    Author     : Emmanuel Kaunda
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="pcoded-navbar  ">
		<div class="navbar-wrapper  ">
			<div class="navbar-content scroll-div " >
				
				<div class="">
					<div class="main-menu-header">
                                            <img class="img-radius" src="images/imis.png" alt="User-Profile-Image">
						<div class="user-details">
                                                   <%if(session.getAttribute("kd_session")!=null){
                                                       
                                                       HashMap <String,String>ks=(HashMap<String, String>)session.getAttribute("kd_session");
                                                       
                                                       %>
                                                       <span> <b> Hi <%=ks.get("fname").toString()%></b> </span>
                                                       <%
                                                       
                                                                                                 } 
                                                               else {%><a href="index.jsp" class="btn-info">Log in</a><%}%>
                                               </div>
					</div>
					<div class="collapse" id="nav-user-link">
						<ul class="list-unstyled">
							<li class="list-group-item"><a href="user-profile.html"><i class="feather icon-user m-r-5"></i>View Profile</a></li>
							<li class="list-group-item"><a href="#!"><i class="feather icon-settings m-r-5"></i>Settings</a></li>
							<li class="list-group-item"><a href="auth-normal-sign-in.html"><i class="feather icon-log-out m-r-5"></i>Logout</a></li>
						</ul>
					</div>
				</div>
				
				<ul class="nav pcoded-inner-navbar ">
					<li class="nav-item pcoded-menu-caption">
						<!--<label>Data entry</label>-->
					</li>
					<li class="nav-item">
					    <a href="imishome.jsp" class="nav-link "><span class="pcoded-micon"><i class="feather icon-home"></i></span><span class="pcoded-mtext">Home</span></a>
					</li>
                                        
<!--                                         <li class="nav-item pcoded-hasmenu">
					    <a href="#!" class="nav-link "><span class="pcoded-micon"><i class="feather icon-tablet"></i></span><span class="pcoded-mtext">Data Entry Forms</span></a>
                                       <ul class="pcoded-submenu">
                                        <li class="nav-item">
					    <a href="indicatorssummary.jsp" class="nav-link "><span class="pcoded-micon"><i class="feather icon-award"></i></span><span class="pcoded-mtext">Indicators summary</span></a>
					</li>
                                         
                                         <li class="nav-item">
					    <a href="training.jsp" class="nav-link "><span class="pcoded-micon"><i class="feather icon-users"></i></span><span class="pcoded-mtext">User Training</span></a>
					</li>
                                         
                                        
                                         
                                        </ul>
                                       </li>-->
                                        
<!--                                        <li class="nav-item">
					    <a href="index.html" class="nav-link "><span class="pcoded-micon"><i class="feather icon-bar-chart"></i></span><span class="pcoded-mtext">Dashboards</span></a>
					</li>-->
                                        
                                        
<!--					<li class="nav-item pcoded-hasmenu">
					    <a href="#!" class="nav-link "><span class="pcoded-micon"><i class="feather icon-layout"></i></span><span class="pcoded-mtext">Page layouts</span></a>
					    <ul class="pcoded-submenu">
					        <li><a href="layout-vertical.html" >Vertical</a></li>
					        <li><a href="layout-horizontal.html" >Horizontal</a></li>
					    </ul>
					</li>-->
					
				
<!--					<li class="nav-item pcoded-hasmenu">
					    <a href="#!" class="nav-link "><span class="pcoded-micon"><i class="feather icon-lock"></i></span><span class="pcoded-mtext">Excel Based Reports</span></a>
					    <ul class="pcoded-submenu">
					        <li><a href="auth-signup.html" >Sign up</a></li>
					        <li><a href="auth-signin.html" >Sign in</a></li>
					    </ul>
					</li>-->
                                        
                                        
                                        <li class="nav-item pcoded-hasmenu">
					    <a href="logout.jsp" class="nav-link "><span class="pcoded-micon"><i class="feather icon-log-out"></i></span><span class="pcoded-mtext">Log Out</span></a>
					    <ul class="pcoded-submenu">
					        <li><a href="logout.jsp" >Sign out</a></li>
					    </ul>
					</li>
                                        
                                        
					
				</ul>
				
				
				
			</div>
		</div>
	</nav>
