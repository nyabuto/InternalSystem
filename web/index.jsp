<!DOCTYPE html>
<html lang="en">

<head>

	<title>IMIS</title>


	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="description" content="" />
	<meta name="keywords" content="">
	<meta name="author" content="Emmanuel Kaunda" />
	<!-- Favicon icon -->
        <link rel="icon" href="images/imis.png" type="image/x-icon">

	<!-- vendor css -->
	<link rel="stylesheet" href="rmc_assets/css/style.css">
	
	


</head>
<form action="login">
<!-- [ auth-signin ] start -->
<div class="auth-wrapper">
	<div class="auth-content text-center">
		<img style="border-radius:8px;" src="rmc_assets/images/aphia_logo.png" alt="" class="img-fluid mb-4">
		<div class="card borderless">
			<div class="row align-items-center ">
				<div class="col-md-12">
					<div class="card-body">
						<h4 class="mb-3 f-w-400"><img src="images/imis_banner.JPG"  style="height:70px;"></h4>
						<hr>
						<div class="form-group mb-3">
							<input type="text" class="form-control" name="username" id="username" placeholder="Username">
						</div>
						<div class="form-group mb-4">
							<input type="password" class="form-control"name ="password" id="Password" placeholder="Password">
                                                        
                                                        <input type="hidden" name="ipv4ad" id="ipv4ad"/>
                                                        
						</div>
                                                
                                                <div class="g-recaptcha" data-sitekey="6LeYh0AqAAAAAOPpTdxB49Tqsm3PT6xf6JVY5iWG"></div>
<!--						<div class="custom-control custom-checkbox text-left mb-4 mt-2">
							<input type="checkbox" class="custom-control-input" id="customCheck1">
							<label class="custom-control-label" for="customCheck1">Save credentials.</label>
						</div>-->
						<button class="btn btn-block btn-primary mb-4">Sign in</button>
                                                
                                                <div class="form-group mb-4">
                                                    
                                                    <div class="toast-body"><p class='ujumbe'></p></div>


                                                    <p class='callalert' onclick="$('.toast-3s').toast('show')">.</p>   
                                                </div>
						<hr>
						<!--<p class="mb-2 text-muted">Forgot password? <a href="auth-reset-password.html" class="f-w-400">Reset</a></p>-->
						<!--<p class="mb-0 text-muted">Don?t have an account? <a href="auth-signup.html" class="f-w-400">Signup</a></p>-->
					</div>
				</div>
                             <img alt="" style='height:85px;' src="rmc_assets/img/aphia_logo.png">
			</div>
		</div>
	</div>
</div>



</form>
<!-- [ auth-signin ] end -->

<!-- Required Js -->
<script src="rmc_assets/js/vendor-all.min.js"></script>
<script src="rmc_assets/js/plugins/bootstrap.min.js"></script>

<script src="rmc_assets/js/pcoded.min.js"></script>

<%if(session.getAttribute("kd_session")!=null){  response.sendRedirect("imishome.jsp");%><%} else { }%>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>


<script type="text/javascript">
    
    $.getJSON('https://api.ipify.org?format=json', function(data) {
    console.log("Client's IP address is: " + data.ip);
    $("#ipv4ad").val(data.ip);
    
});
    
    
    
    
    
  
    
    
    
    
    </script>
    
    <%if (session.getAttribute("login") != null) { %>
   <script type="text/javascript"> 
                    
                    var uju='<%=session.getAttribute("login")%>';
                    $('.ujumbe').html(uju);
                    $('.callalert').click();
                      
                    
                </script> <%
                session.removeAttribute("login");
                            }

    %>
      
     

</html>



         
   