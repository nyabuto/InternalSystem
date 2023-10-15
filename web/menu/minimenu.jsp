





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
