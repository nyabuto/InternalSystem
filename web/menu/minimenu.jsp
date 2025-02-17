





<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../assets/css/style.css" rel="stylesheet" />
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
   
  
    var elem =$('#emrstatus');
    setInterval(function() {
        if (elem.css('visibility') === 'hidden') {
            elem.css('visibility', 'visible');
        } else {
            elem.css('visibility', 'hidden');
        }    
    }, 500);


            
        </script>
        
        
               
          <script>
      
      function clicktb(elm){
           localStorage.setItem("lastpage", "#"+elm);
          window.location.href = "imishome.jsp";
         
         
          
      }
      
      
      
  
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
            <li class="start " style="background-color:#a3b0c9;">
               <a href="#">
             <span  ><b>Hi, <%if(session.getAttribute("userid")!=null){out.println(session.getAttribute("fullname").toString());}%></b></span>
               </a>
                </li>
                             
                <li class="active" style="border-top: 1px solid #e2e2e2 !important;">
                <a href="imishome.jsp">
               <i class="icon-home"></i> 
               <span class="title">Home</span>
               </a>
            </li>
            
           
 
              
                
         
                                            
                                        
                                                                           <%if(session.getAttribute("userAccess")!=null){%>
							
							<li class="nav-item">
								<a class="nav-link" onclick="clicktb('pills-forms-tab');"  href="#pills-forms" > <span class="pcoded-micon"><i class="feather icon-info"></i></span> Forms</a>
							</li>
                                                        <li class="nav-item">
								<a class="nav-link" onclick="clicktb('pills-reprates-tab');"  href="#pills-reprates" > <span class="pcoded-micon"><i class="feather icon-clock"></i></span>Reporting Rates</a>
							</li>
                                                        
                                                        <li class="nav-item">
								<a class="nav-link" onclick="clicktb('pills-gapanalysis-tab');"  href="#pills-gapanalysis" > <span class="pcoded-micon"><i class="feather icon-activity"></i></span>Gap Analysis</a>
							</li>
                                                        
                                                         
                                                        
                                                         <li class="nav-item">
								<a class="nav-link" onclick="clicktb('pills-dataexchange-tab');" data-toggle="pill" href="imishome.jsp#pills-dataexchange"  >  <span class="pcoded-micon"><i class="feather icon-cast"></i></span>Data Exchange</a>
							</li>
                                                         <li class="nav-item">
							<a class="nav-link" onclick="clicktb('pills-emr-tab');"  href="#pills-emr" >  <span class="pcoded-micon"><i class="feather icon-link"></i></span>  EMR</a>
							</li>
                                                        
							<li class="nav-item">
								<a class="nav-link" onclick="clicktb('pills-reports-tab');"  href="imishome.jsp#pills-reports" > <span class="pcoded-micon"><i class="feather icon-bar-chart-2"></i></span>   Excel Reports</a>
							</li>
                                                       
                                                        
                                                        <li class="nav-item">
								<a class="nav-link" onclick="clicktb('pills-contact-tab');" data-toggle="pill" href="#pills-queries" role="tab" > <span class="pcoded-micon"><i class="feather icon-command"></i></span> Queries</a>
							</li>
                                                        <li class="nav-item">
								<a class="nav-link" onclick="clicktb('pills-management-tab');" href="#pills-management"  > <span class="pcoded-micon"><i class="feather icon-settings"></i></span>  Management</a>
							</li>
<!--                                                         <li class="nav-item">
								<a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-resources" role="tab" aria-controls="pills-resources" aria-selected="false"> <span class="pcoded-micon"><i class="feather icon-external-link"></i></span>  Resources</a>
							</li>-->
                                                        <%%> <li class="nav-item">
								<a class="nav-link" onclick="clicktb('pills-moremodules-tab');"  href="#pills-moremodules" > <span class="pcoded-micon"><i class="feather icon-plus"></i></span>Extras</a>
							</li>
                                                        



  <%}%>
           
   
            
    
            
            
<!--            <li class="">
               <a href="https://usaidtujengejamii.org/" target="_blank"><i class="icon-plus"></i>More Reporting Modules</a>
            </li>-->
            
<!--             <li class="has-sub">
                <a href="pprev_index.jsp">
                <i class="icon-stop"></i>
               <span class="title">PP PREV</span>
               </a>
            </li>-->
            <li class="">
               <a href="logout.jsp">
               <i class="icon-user"></i> 
               <span class="title">Log Out</span>
               </a>
            </li>
         </ul>
      
    </body>
</html>
