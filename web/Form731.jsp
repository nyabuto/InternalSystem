<%-- 
    Document   : Form731
    Created on : May 11, 2015, 10:09:28 AM
    Author     : Maureen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>Metronic | Form Stuff - Form Components</title>
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
   <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
   <link href="assets/css/metro.css" rel="stylesheet" />
   <link href="assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
   <link href="assets/bootstrap-fileupload/bootstrap-fileupload.css" rel="stylesheet" />
   <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
   <link href="assets/css/style.css" rel="stylesheet" />
   <link href="assets/css/style_responsive.css" rel="stylesheet" />
   <link href="assets/css/style_default.css" rel="stylesheet" id="style_color" />
   <link rel="stylesheet" type="text/css" href="assets/gritter/css/jquery.gritter.css" />
   <link rel="stylesheet" type="text/css" href="assets/chosen-bootstrap/chosen/chosen.css" />
   <link rel="stylesheet" type="text/css" href="assets/jquery-tags-input/jquery.tagsinput.css" />
   <link rel="stylesheet" type="text/css" href="assets/clockface/css/clockface.css" />
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-wysihtml5/bootstrap-wysihtml5.css" />
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-datepicker/css/datepicker.css" />
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-timepicker/compiled/timepicker.css" />
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-colorpicker/css/colorpicker.css" />
   <link rel="stylesheet" href="assets/bootstrap-toggle-buttons/static/stylesheets/bootstrap-toggle-buttons.css" />
   <link rel="stylesheet" href="assets/data-tables/DT_bootstrap.css" />
   <link rel="stylesheet" type="text/css" href="assets/bootstrap-daterangepicker/daterangepicker.css" />
   <link rel="stylesheet" type="text/css" href="assets/uniform/css/uniform.default.css" />
   <link rel="shortcut icon" href="favicon.ico" />
  
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">
   <!-- BEGIN HEADER -->
   <div class="header navbar navbar-inverse navbar-fixed-top">
      <!-- BEGIN TOP NAVIGATION BAR -->
      <div class="navbar-inner">
         <div class="container-fluid">
            <!-- BEGIN LOGO -->
            <a class="brand" href="index.html">
            <img src="assets/img/logo.png" alt="logo" />
            </a>
            <!-- END LOGO -->
            <!-- BEGIN RESPONSIVE MENU TOGGLER -->
            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
            <img src="assets/img/menu-toggler.png" alt="" />
            </a>          
            <!-- END RESPONSIVE MENU TOGGLER -->            
            <!-- BEGIN TOP NAVIGATION MENU -->              
            <ul class="nav pull-right">
              
               <!-- END NOTIFICATION DROPDOWN -->
               <!-- BEGIN INBOX DROPDOWN -->
             
             
               <!-- END TODO DROPDOWN -->
               <!-- BEGIN USER LOGIN DROPDOWN -->
               <li class="dropdown user">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                 
                  <span class="username">Welcome User</span>
                  <i class="icon-angle-down"></i>
                  </a>
                  <ul class="dropdown-menu">
                     <li><a href="extra_profile.html"><i class="icon-user"></i> My Profile</a></li>
                     <li><a href="calendar.html"><i class="icon-calendar"></i> My Calendar</a></li>
                     <li><a href="#"><i class="icon-tasks"></i> My Tasks</a></li>
                     <li class="divider"></li>
                     <li><a href="login.html"><i class="icon-key"></i> Log Out</a></li>
                  </ul>
               </li>
               <!-- END USER LOGIN DROPDOWN -->
            </ul>
            <!-- END TOP NAVIGATION MENU --> 
         </div>
      </div>
      <!-- END TOP NAVIGATION BAR -->
   </div>
   <!-- END HEADER -->
   <!-- BEGIN CONTAINER -->
   <div class="page-container row-fluid">
      <!-- BEGIN SIDEBAR -->
      <div class="page-sidebar nav-collapse collapse">
         <!-- BEGIN SIDEBAR MENU -->         
       <%@include file="/menu/menu.html"%>
         <!-- END SIDEBAR MENU -->
      </div>
      <!-- END SIDEBAR -->
      <!-- BEGIN PAGE -->  
      <div class="page-content">
         <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
         <div id="portlet-config" class="modal hide">
            <div class="modal-header">
               <button data-dismiss="modal" class="close" type="button"></button>
               <h3>portlet Settings</h3>
            </div>
            <div class="modal-body">
               <p>Here will be a configuration form</p>
            </div>
         </div>
         <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
         <!-- BEGIN PAGE CONTAINER-->
         <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->   
            <div class="row-fluid">
               <div class="span12">
                  <!-- BEGIN STYLE CUSTOMIZER -->
               
                  <!-- END BEGIN STYLE CUSTOMIZER -->   
                  <h3 class="page-title">
                     Form Components
                     <small>form components and widgets</small>
                  </h3>
                  <ul class="breadcrumb">
                     <li>
                        <i class="icon-home"></i>
                        <a href="index.html">Home</a> 
                        <span class="icon-angle-right"></span>
                     </li>
                     <li>
                        <a href="#">Form Stuff</a>
                        <span class="icon-angle-right"></span>
                     </li>
                     <li><a href="#">Form Components</a></li>
                  </ul>
               </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
               <div class="span12">
                  <!-- BEGIN SAMPLE FORM PORTLET-->   
                  <div class="portlet box blue">
                     <div class="portlet-title">
                        <h4><i class="icon-reorder"></i>Sample Form</h4>
                        <div class="tools">
                           <a href="javascript:;" class="collapse"></a>
                           <a href="#portlet-config" data-toggle="modal" class="config"></a>
                           <a href="javascript:;" class="reload"></a>
                           <a href="javascript:;" class="remove"></a>
                        </div>
                     </div>
                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <form action="#" class="form-horizontal">
                           <div class="control-group">
                              <label class="control-label">Input</label>
                              <div class="controls">
                                 <input type="text" class="span6 m-wrap" />
                                 <span class="help-inline">Some hint here</span>
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Disabled Input</label>
                              <div class="controls">
                                 <input class="span6 m-wrap" type="text" placeholder="Disabled input here..." disabled />
                                 <span class="help-inline">Some hint here</span>
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Readonly Input</label>
                              <div class="controls">
                                 <input class="span6 m-wrap" type="text" placeholder="Readonly input here..." disabled />
                                 <span class="help-inline">Some hint here</span>
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Input with Popover</label>
                              <div class="controls">
                                 <input type="text" class="span6 m-wrap popovers" data-trigger="hover" data-content="Popover body goes here. Popover body goes here." data-original-title="Popover header" />
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Input with Tooltip</label>
                              <div class="controls">
                                 <input type="text" class="span6 m-wrap tooltips" data-trigger="hover" data-original-title="Tooltip text goes here. Tooltip text goes here." />                       
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Auto Complete</label>
                              <div class="controls">
                                 <input type="text" class="span6 m-wrap" style="margin: 0 auto;" data-provide="typeahead" data-items="4" data-source="[&quot;Alabama&quot;,&quot;Alaska&quot;,&quot;Arizona&quot;,&quot;Arkansas&quot;,&quot;California&quot;,&quot;Colorado&quot;,&quot;Connecticut&quot;,&quot;Delaware&quot;,&quot;Florida&quot;,&quot;Georgia&quot;,&quot;Hawaii&quot;,&quot;Idaho&quot;,&quot;Illinois&quot;,&quot;Indiana&quot;,&quot;Iowa&quot;,&quot;Kansas&quot;,&quot;Kentucky&quot;,&quot;Louisiana&quot;,&quot;Maine&quot;,&quot;Maryland&quot;,&quot;Massachusetts&quot;,&quot;Michigan&quot;,&quot;Minnesota&quot;,&quot;Mississippi&quot;,&quot;Missouri&quot;,&quot;Montana&quot;,&quot;Nebraska&quot;,&quot;Nevada&quot;,&quot;New Hampshire&quot;,&quot;New Jersey&quot;,&quot;New Mexico&quot;,&quot;New York&quot;,&quot;North Dakota&quot;,&quot;North Carolina&quot;,&quot;Ohio&quot;,&quot;Oklahoma&quot;,&quot;Oregon&quot;,&quot;Pennsylvania&quot;,&quot;Rhode Island&quot;,&quot;South Carolina&quot;,&quot;South Dakota&quot;,&quot;Tennessee&quot;,&quot;Texas&quot;,&quot;Utah&quot;,&quot;Vermont&quot;,&quot;Virginia&quot;,&quot;Washington&quot;,&quot;West Virginia&quot;,&quot;Wisconsin&quot;,&quot;Wyoming&quot;]" />
                                 <p class="help-block">Start typing to auto complete!. E.g: California</p>
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Email Address Input</label>
                              <div class="controls">
                                 <div class="input-prepend">
                                    <span class="add-on">@</span><input class="m-wrap " type="text" placeholder="Email Address" />
                                 </div>
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Email Address Input</label>
                              <div class="controls">
                                 <div class="input-icon left">
                                    <i class="icon-envelope"></i>
                                    <input class="m-wrap " type="text" placeholder="Email Address" />    
                                 </div>
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Currency Input</label>
                              <div class="controls">
                                 <div class="input-prepend input-append">
                                    <span class="add-on">$</span><input class="m-wrap " type="text" /><span class="add-on">.00</span>
                                 </div>
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Default Dropdown</label>
                              <div class="controls">
                                 <select class="span6 m-wrap" data-placeholder="Choose a Category" tabindex="1">
                                    <option value="">Select...</option>
                                    <option value="Category 1">Category 1</option>
                                    <option value="Category 2">Category 2</option>
                                    <option value="Category 3">Category 5</option>
                                    <option value="Category 4">Category 4</option>
                                 </select>
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Default Dropdown(Multiple)</label>
                              <div class="controls">
                                 <select class="span6 m-wrap" multiple="multiple" data-placeholder="Choose a Category" tabindex="1">
                                    <option value="Category 1">Category 1</option>
                                    <option value="Category 2">Category 2</option>
                                    <option value="Category 3">Category 5</option>
                                    <option value="Category 4">Category 4</option>
                                    <option value="Category 3">Category 6</option>
                                    <option value="Category 4">Category 7</option>
                                    <option value="Category 3">Category 8</option>
                                    <option value="Category 4">Category 9</option>
                                 </select>
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Custom Dropdown</label>
                              <div class="controls">
                                 <select class="span6 chosen" data-placeholder="Choose a Category" tabindex="1">
                                    <option value=""></option>
                                    <option value="Category 1">Category 1</option>
                                    <option value="Category 2">Category 2</option>
                                    <option value="Category 3">Category 5</option>
                                    <option value="Category 4">Category 4</option>
                                 </select>
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Grouped Custom Dropdown</label>
                              <div class="controls">
                                 <select data-placeholder="Your Favorite Football Team" class="chosen span6" tabindex="-1" id="selS0V">
                                    <option value=""></option>
                                    <optgroup label="NFC EAST">
                                       <option>Dallas Cowboys</option>
                                       <option>New York Giants</option>
                                       <option>Philadelphia Eagles</option>
                                       <option>Washington Redskins</option>
                                    </optgroup>
                                    <optgroup label="NFC NORTH">
                                       <option>Chicago Bears</option>
                                       <option>Detroit Lions</option>
                                       <option>Green Bay Packers</option>
                                       <option>Minnesota Vikings</option>
                                    </optgroup>
                                    <optgroup label="NFC SOUTH">
                                       <option>Atlanta Falcons</option>
                                       <option>Carolina Panthers</option>
                                       <option>New Orleans Saints</option>
                                       <option>Tampa Bay Buccaneers</option>
                                    </optgroup>
                                    <optgroup label="NFC WEST">
                                       <option>Arizona Cardinals</option>
                                       <option>St. Louis Rams</option>
                                       <option>San Francisco 49ers</option>
                                       <option>Seattle Seahawks</option>
                                    </optgroup>
                                    <optgroup label="AFC EAST">
                                       <option>Buffalo Bills</option>
                                       <option>Miami Dolphins</option>
                                       <option>New England Patriots</option>
                                       <option>New York Jets</option>
                                    </optgroup>
                                    <optgroup label="AFC NORTH">
                                       <option>Baltimore Ravens</option>
                                       <option>Cincinnati Bengals</option>
                                       <option>Cleveland Browns</option>
                                       <option>Pittsburgh Steelers</option>
                                    </optgroup>
                                    <optgroup label="AFC SOUTH">
                                       <option>Houston Texans</option>
                                       <option>Indianapolis Colts</option>
                                       <option>Jacksonville Jaguars</option>
                                       <option>Tennessee Titans</option>
                                    </optgroup>
                                    <optgroup label="AFC WEST">
                                       <option>Denver Broncos</option>
                                       <option>Kansas City Chiefs</option>
                                       <option>Oakland Raiders</option>
                                       <option>San Diego Chargers</option>
                                    </optgroup>
                                 </select>
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Custom Dropdown Multiple Select</label>
                              <div class="controls">
                                 <select data-placeholder="Your Favorite Football Teams" class="chosen span6" multiple="multiple" tabindex="6">
                                    <option value=""></option>
                                    <optgroup label="NFC EAST">
                                       <option>Dallas Cowboys</option>
                                       <option>New York Giants</option>
                                       <option>Philadelphia Eagles</option>
                                       <option>Washington Redskins</option>
                                    </optgroup>
                                    <optgroup label="NFC NORTH">
                                       <option selected>Chicago Bears</option>
                                       <option>Detroit Lions</option>
                                       <option>Green Bay Packers</option>
                                       <option>Minnesota Vikings</option>
                                    </optgroup>
                                    <optgroup label="NFC SOUTH">
                                       <option>Atlanta Falcons</option>
                                       <option selected>Carolina Panthers</option>
                                       <option>New Orleans Saints</option>
                                       <option>Tampa Bay Buccaneers</option>
                                    </optgroup>
                                    <optgroup label="NFC WEST">
                                       <option>Arizona Cardinals</option>
                                       <option>St. Louis Rams</option>
                                       <option>San Francisco 49ers</option>
                                       <option>Seattle Seahawks</option>
                                    </optgroup>
                                    <optgroup label="AFC EAST">
                                       <option>Buffalo Bills</option>
                                       <option>Miami Dolphins</option>
                                       <option>New England Patriots</option>
                                       <option>New York Jets</option>
                                    </optgroup>
                                    <optgroup label="AFC NORTH">
                                       <option>Baltimore Ravens</option>
                                       <option>Cincinnati Bengals</option>
                                       <option>Cleveland Browns</option>
                                       <option>Pittsburgh Steelers</option>
                                    </optgroup>
                                    <optgroup label="AFC SOUTH">
                                       <option>Houston Texans</option>
                                       <option>Indianapolis Colts</option>
                                       <option>Jacksonville Jaguars</option>
                                       <option>Tennessee Titans</option>
                                    </optgroup>
                                    <optgroup label="AFC WEST">
                                       <option>Denver Broncos</option>
                                       <option>Kansas City Chiefs</option>
                                       <option>Oakland Raiders</option>
                                       <option>San Diego Chargers</option>
                                    </optgroup>
                                 </select>
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Custom Dropdown Diselect</label>
                              <div class="controls">
                                 <select data-placeholder="Your Favorite Type of Bear" class="chosen-with-diselect span6" tabindex="-1" id="selCSI">
                                    <option value=""></option>
                                    <option>American Black Bear</option>
                                    <option>Asiatic Black Bear</option>
                                    <option>Brown Bear</option>
                                    <option>Giant Panda</option>
                                    <option selected="">Sloth Bear</option>
                                    <option>Sun Bear</option>
                                    <option>Polar Bear</option>
                                    <option>Spectacled Bear</option>
                                 </select>
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Radio Buttons</label>
                              <div class="controls">
                                 <label class="radio">
                                 <input type="radio" name="optionsRadios1" value="option1" />
                                 Option 1
                                 </label>
                                 <label class="radio">
                                 <input type="radio" name="optionsRadios1" value="option2" checked />
                                 Option 2
                                 </label>  
                                 <label class="radio">
                                 <input type="radio" name="optionsRadios1" value="option2" />
                                 Option 3
                                 </label>  
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Radio Buttons</label>
                              <div class="controls">
                                 <label class="radio line">
                                 <input type="radio" name="optionsRadios2" value="option1" />
                                 Option 1
                                 </label>
                                 <label class="radio line">
                                 <input type="radio" name="optionsRadios2" value="option2" checked />
                                 Option 2
                                 </label>  
                                 <label class="radio line">
                                 <input type="radio" name="optionsRadios2" value="option2" />
                                 Option 3
                                 </label>  
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Checkbox</label>
                              <div class="controls">
                                 <label class="checkbox">
                                 <input type="checkbox" value="" /> Checkbox 1
                                 </label>
                                 <label class="checkbox">
                                 <input type="checkbox" value="" /> Checkbox 2
                                 </label>
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Checkbox</label>
                              <div class="controls">
                                 <label class="checkbox line">
                                 <input type="checkbox" value="" /> Checkbox 1
                                 </label>
                                 <label class="checkbox line">
                                 <input type="checkbox" value="" /> Checkbox 2
                                 </label>
                              </div>
                           </div>
                           <div class="control-group">
                              <label class="control-label">Textarea</label>
                              <div class="controls">
                                 <textarea class="span6 m-wrap" rows="3"></textarea>
                              </div>
                           </div>
                           <div class="form-actions">
                              <button type="submit" class="btn blue">Submit</button>
                              <button type="button" class="btn">Cancel</button>
                           </div>
                        </form>
                        <!-- END FORM-->           
                     </div>
                  </div>
                  <!-- END SAMPLE FORM PORTLET-->
               </div>
            </div>
       
          
         
          
           
         
          
            <!-- END PAGE CONTENT-->         
         </div>
         <!-- END PAGE CONTAINER-->
      </div>
      <!-- END PAGE -->  
   </div>
   <!-- END CONTAINER -->
   <!-- BEGIN FOOTER -->
   <div class="footer">
      2013 &copy; Metronic by keenthemes.
      <div class="span pull-right">
         <span class="go-top"><i class="icon-angle-up"></i></span>
      </div>
   </div>
   <!-- END FOOTER -->
   <!-- BEGIN JAVASCRIPTS -->    
   <!-- Load javascripts at bottom, this will reduce page load time -->
   <script src="assets/js/jquery-1.8.3.min.js"></script>    
   <script type="text/javascript" src="assets/ckeditor/ckeditor.js"></script>  
   <script src="assets/breakpoints/breakpoints.js"></script>       
   <script src="assets/bootstrap/js/bootstrap.min.js"></script>   
   <script type="text/javascript" src="assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
   <script src="assets/js/jquery.blockui.js"></script>
   <script src="assets/js/jquery.cookie.js"></script>
   <!-- ie8 fixes -->
   <!--[if lt IE 9]>
   <script src="assets/js/excanvas.js"></script>
   <script src="assets/js/respond.js"></script>
   <![endif]-->
   <script type="text/javascript" src="assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
   <script type="text/javascript" src="assets/uniform/jquery.uniform.min.js"></script>
   <script type="text/javascript" src="assets/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script> 
   <script type="text/javascript" src="assets/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
   <script type="text/javascript" src="assets/jquery-tags-input/jquery.tagsinput.min.js"></script>
   <script type="text/javascript" src="assets/bootstrap-toggle-buttons/static/js/jquery.toggle.buttons.js"></script>
   <script type="text/javascript" src="assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
   <script type="text/javascript" src="assets/clockface/js/clockface.js"></script>
   <script type="text/javascript" src="assets/bootstrap-daterangepicker/date.js"></script>
   <script type="text/javascript" src="assets/bootstrap-daterangepicker/daterangepicker.js"></script> 
   <script type="text/javascript" src="assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>  
   <script type="text/javascript" src="assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
   <script src="assets/js/app.js"></script>     
   <script>
      jQuery(document).ready(function() {       
         // initiate layout and plugins
         App.init();
      });
   </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>

