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
						<img class="img-radius" src="rmc_assets/images/user/avatar.png" alt="User-Profile-Image">
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
						<label>Data entry</label>
					</li>
					<li class="nav-item">
					    <a href="ramcahhome.jsp" class="nav-link "><span class="pcoded-micon"><i class="feather icon-home"></i></span><span class="pcoded-mtext">Home</span></a>
					</li>
                                        
                                         <li class="nav-item pcoded-hasmenu">
					    <a href="#!" class="nav-link "><span class="pcoded-micon"><i class="feather icon-tablet"></i></span><span class="pcoded-mtext">Data Entry Forms</span></a>
                                       <ul class="pcoded-submenu">
                                        <li class="nav-item">
					    <a href="indicatorssummary.jsp" class="nav-link "><span class="pcoded-micon"><i class="feather icon-award"></i></span><span class="pcoded-mtext">Indicators summary</span></a>
					</li>
                                         <li class="nav-item">
					    <a href="emonc.jsp" class="nav-link "><span class="pcoded-micon"><i class="feather icon-airplay"></i></span><span class="pcoded-mtext">EMonc</span></a>
					</li>
                                         <li class="nav-item">
					    <a href="wash.jsp" class="nav-link "><span class="pcoded-micon"><i class="feather icon-thumbs-up"></i></span><span class="pcoded-mtext">Wash Summary</span></a>
					</li>
                                         <li class="nav-item">
					    <a href="bintishujaa.jsp" class="nav-link "><span class="pcoded-micon"><i class="feather icon-award"></i></span><span class="pcoded-mtext">Binti Shujaa</span></a>
					</li>
                                         <li class="nav-item">
					    <a href="training.jsp" class="nav-link "><span class="pcoded-micon"><i class="feather icon-users"></i></span><span class="pcoded-mtext">Trainings</span></a>
					</li>
                                         <li class="nav-item">
					    <a href="training.jsp" class="nav-link "><span class="pcoded-micon"><i class="feather icon-user"></i></span><span class="pcoded-mtext">BFCI Form</span></a>
					</li>
                                         <li class="nav-item">
					    <a href="#" class="nav-link "><span class="pcoded-micon"><i class="feather icon-activity"></i></span><span class="pcoded-mtext">Outreach data reporting tool</span></a>
					</li>
                                         <li class="nav-item">
					    <a href="#" class="nav-link "><span class="pcoded-micon"><i class="feather icon-user"></i></span><span class="pcoded-mtext">Functionality Score Card</span></a>
					</li>
                                         <li class="nav-item">
					    <a href="#" class="nav-link "><span class="pcoded-micon"><i class="feather icon-user"></i></span><span class="pcoded-mtext">Data Verification</span></a>
					</li>
                                        </ul>
                                       </li>
                                        
<!--                                        <li class="nav-item">
					    <a href="index.html" class="nav-link "><span class="pcoded-micon"><i class="feather icon-bar-chart"></i></span><span class="pcoded-mtext">Dashboards</span></a>
					</li>-->
                                        
                                        
<!--					<li class="nav-item pcoded-hasmenu">
					    <a href="#!" class="nav-link "><span class="pcoded-micon"><i class="feather icon-layout"></i></span><span class="pcoded-mtext">Page layouts</span></a>
					    <ul class="pcoded-submenu">
					        <li><a href="layout-vertical.html" target="_blank">Vertical</a></li>
					        <li><a href="layout-horizontal.html" target="_blank">Horizontal</a></li>
					    </ul>
					</li>-->
					
					
					<li class="nav-item pcoded-menu-caption">
						<label>Reports</label>
					</li>
<!--					<li class="nav-item pcoded-hasmenu">
					    <a href="#!" class="nav-link "><span class="pcoded-micon"><i class="feather icon-lock"></i></span><span class="pcoded-mtext">Excel Based Reports</span></a>
					    <ul class="pcoded-submenu">
					        <li><a href="auth-signup.html" target="_blank">Sign up</a></li>
					        <li><a href="auth-signin.html" target="_blank">Sign in</a></li>
					    </ul>
					</li>-->
                                        
                                        
                                        <li class="nav-item pcoded-hasmenu">
					    <a href="#!" class="nav-link "><span class="pcoded-micon"><i class="feather icon-log-out"></i></span><span class="pcoded-mtext">Log Out</span></a>
					    <ul class="pcoded-submenu">
					        <li><a href="logout.jsp" target="_blank">Sign out</a></li>
					    </ul>
					</li>
                                        
                                        
					
				</ul>
				
				
				
			</div>
		</div>
	</nav>
