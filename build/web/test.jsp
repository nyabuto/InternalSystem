<%-- 
    Document   : Form731
    Created on : May 11, 2015, 10:09:28 AM
    Author     : Maureen
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>TEST</title>
     <link rel="shortcut icon" href="images/logo.jpg"/>
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
           <div class="control-group">
                             <div style="float:right;"> 
                                 
                                 <font color="white" size="5px"><b>Year: </b></font>  
                                   <font color="green" size="5px"><b><%if(session.getAttribute("year")!=null){out.println(session.getAttribute("year").toString()+" | ");}%></b></font>
                                 
                                    <font color="white" size="5px"><b>Month: </b></font>  
                                   <font color="green" size="5px"><b><%if(session.getAttribute("monthname")!=null){out.println(session.getAttribute("monthname").toString()+" | ");}%></b></font>
                                 
                                   
                                   <font color="white" size="5px" margin-left="3px"><b>            Activity Site : </b></font>
                              
                                 <select style="width:240px;float:right;color:black;" data-placeholder="Facility" required class="chosen-with-diselect span6" tabindex="-1"  id="facility" name="facility">
                                    <option value=""></option>
                                 </select></div>
                              
                           </div>
            <!-- END LOGO -->
            <!-- BEGIN RESPONSIVE MENU TOGGLER -->
            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
            <img src="assets/img/menu-toggler.png" alt="" />
            </a>          
            <!-- END RESPONSIVE MENU TOGGLER -->            
            <!-- BEGIN TOP NAVIGATION MENU -->              
            <ul class="nav pull-left">
              
               <li class="dropdown user">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                 
                  <span class="username">Welcome</span>
                  <i class="icon-angle-down"></i>
                  </a>
                  <ul class="dropdown-menu">
                     <li><a href="userProfile.html"><i class="icon-user"></i>User Profile</a></li>
                   
                     <li class="divider"></li>
                     <li><a href="logout.jsp"><i class="icon-key"></i> Log Out</a></li>
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
       <%@include file="/menu/menu.jsp"%>
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
                  <h3 class="page-title" style="text-align: center;">
                    
             
                  </h3>
                  <ul class="breadcrumb">
                     <li>
                        <i class="icon-home"></i>
                        <font color="#4b8df8"> Kenya Mentor Mother Program (KMMP) output Data</font>
                        
                     </li>
           
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
                        <h4><i class="icon-reorder"></i></h4>
                        <div class="tools">
                           <a href="javascript:;" class="collapse"></a>
                           <a href="#portlet-config" data-toggle="modal" class="config"></a>
                           <a href="javascript:;" class="reload"></a>
                           <a href="javascript:;" class="remove"></a>
                        </div>
                     </div>
                     <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <form action="sessionsHolder" class="form-horizontal">
                          
                         
                         
                            <%
                            String islocked="";
          String PMCTA_1stVisit_ANC="1";
          String PMCTA_ReVisit_ANC="2";
          String PMCTIPT1="3";
          String PMCTIPT2="4";
          String PMCTHB11="5";
          String PMCTANCClients4="6";
          String PMCTITN1="7";
          String PMCTITN="8";
          String PMTCTSYPHILISTES="9";
          String PMTCTSYPHILISPOS="10";
          String PMTCTCOUNSELLEDFEED="11";
          String PMTCTBREAST="12";
          String PMTCTEXERCISE="13";
          String PMTCTPREG10_14="14";
          String PMTCTPREG15_19="15";
          String PMTCTIRON="16";
          String PMTCTFOLIC="17";
          String PMTCTFERROUS="18";
          
          
          
         String  MATNormalDelivery="1";
String MATCSection="2";
String MATBreech="3";
String MATAssistedVag="4";
String MATDeliveryT="5";
String MATLiveBirth="6";
String MATFreshStillBirth="7";
String MATMeceratedStillBirth="8";
String MATDeformities="9";
String MATLowAPGAR="10";
String MATWeight2500="11";
String MATTetracycline="12";
String MATPreTerm="13";
String MATDischargealive="14";
String MATbreastfeeding1="15";
String MATDeliveriesPos="16";
String MATNeoNatalD="17";
String MATMaternalD10_19="18";
String MATMaternalD="19";
String MATMaternalDAudited="20";
String MATAPHAlive="21";
String MATAPHDead="22";
String MATPPHAlive="23";
String MATPPHDead="24";
String MATEclampAlive="25";
String MATEclampDead="26";
String MATRupUtAlive="27";
String MATRupUtDead="28";
String MATObstrLaborAlive="29";
String MATObstrLaborDead="30";
String MATSepsisAlive="31";
String MATSepsisDead="32";
String MATREFFromOtherFacility="33";
String MATREFFromCU="34";
String MATREFToOtherFacility="35";
String MATREFToCU="36";

          
          
           String ancpmct= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;'> A: ANC/PMTCT </b> </legend>"
              + " <table frame='box' cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
              + "<tr><td colspan='3' class=\"form-actions\"><b> </b></td>"            
              + "<td class=\"form-actions\"> <b>TOTAL </b></td>"
              + "</tr>"
                   
              + "<tr> <td>1</td> <td rowspan='2'> No. of ANC Clients </td> <td>New</td><td> <input type='text' "+islocked+" name='PMCTA_1stVisit_ANC' id='PMCTA_1stVisit_ANC' value='"+PMCTA_1stVisit_ANC+"' autocomplete='off'  onblur='autosave('PMCTA_1stVisit_ANC');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>2</td>  <td>Re-Visit</td><td> <input type='text' "+islocked+" name='PMCTA_ReVisit_ANC' id='PMCTA_ReVisit_ANC' value='"+PMCTA_ReVisit_ANC+"' autocomplete='off'  onblur='autosave('PMCTA_ReVisit_ANC');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>3</td> <td colspan='2'> No of clients with IPT (1st Dose ) </td><td> <input type='text' "+islocked+" name='PMCTIPT1' id='PMCTIPT1' value='"+PMCTIPT1+"' autocomplete='off'  onblur='autosave('PMCTIPT1');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>4</td> <td colspan='2'> No of clients with IPT (2nd Dose ) </td><td> <input type='text' "+islocked+" name='PMCTIPT2' id='PMCTIPT2' value='"+PMCTIPT2+"' autocomplete='off'  onblur='autosave('PMCTIPT2');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>5</td> <td colspan='2'> No of clients with  with Hb < 11 g/dl </td><td> <input type='text' "+islocked+" name='PMCTHB11' id='PMCTHB11' value='"+PMCTHB11+"' autocomplete='off'  onblur='autosave('PMCTHB11');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>6</td> <td colspan='2'> No of clients completed 4th antenatal care  visit </td><td> <input type='text' "+islocked+" name='PMCTANCClients4' id='PMCTANCClients4' value='"+PMCTANCClients4+"' autocomplete='off'  onblur='autosave('PMCTANCClients4');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>7</td> <td colspan='2'> No.LLITNs distributed to under 1 year </td><td> <input type='text' "+islocked+" name='PMCTITN1' id='PMCTITN1' value='"+PMCTITN1+"' autocomplete='off'  onblur='autosave('PMCTITN1');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>8</td> <td colspan='2'> No of ITNs distributed to ANC Clients  </td><td> <input type='text' "+islocked+" name='PMCTITN' id='PMCTITN' value='"+PMCTITN+"' autocomplete='off'  onblur='autosave('PMCTITN');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>9</td> <td rowspan='2'> No. of  Clients </td> <td>Tested for Syphilis</td><td> <input type='text' "+islocked+" name='PMTCTSYPHILISTES' id='PMTCTSYPHILISTES' value='"+PMTCTSYPHILISTES+"' autocomplete='off'  onblur='autosave('PMTCTSYPHILISTES');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>10</td> <td>Positive (+ve)</td><td> <input type='text' "+islocked+" name='PMTCTSYPHILISPOS' id='PMTCTSYPHILISPOS' value='"+PMTCTSYPHILISPOS+"' autocomplete='off'  onblur='autosave('PMTCTSYPHILISPOS');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>11</td> <td colspan='2'>No. of mothers counselled on infant feeding options  </td><td> <input type='text' "+islocked+" name='PMTCTCOUNSELLEDFEED' id='PMTCTCOUNSELLEDFEED' value='"+PMTCTCOUNSELLEDFEED+"' autocomplete='off'  onblur='autosave('PMTCTCOUNSELLEDFEED');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>12</td> <td colspan='2'>Total women done breast examination  </td><td> <input type='text' "+islocked+" name='PMTCTBREAST' id='PMTCTBREAST' value='"+PMTCTBREAST+"' autocomplete='off'  onblur='autosave('PMTCTBREAST');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>13</td> <td colspan='2'>Total women given exercise  </td><td> <input type='text' "+islocked+" name='PMTCTEXERCISE' id='PMTCTEXERCISE' value='"+PMTCTEXERCISE+"' autocomplete='off'  onblur='autosave('PMTCTEXERCISE');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>14</td> <td colspan='2'>No. of adolescents (10-14 years) presenting with pregnancy  </td><td> <input type='text' "+islocked+" name='PMTCTPREG10_14' id='PMTCTPREG10_14' value='"+PMTCTPREG10_14+"' autocomplete='off'  onblur='autosave('PMTCTPREG10_14');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>15</td> <td colspan='2'>No. of adolescents (15-19 years) presenting with pregnancy  </td><td> <input type='text' "+islocked+" name='PMTCTPREG15_19' id='PMTCTPREG15_19' value='"+PMTCTPREG15_19+"' autocomplete='off'  onblur='autosave('PMTCTPREG15_19');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>16</td> <td colspan='2'>No. of clients issued with Iron  </td><td> <input type='text' "+islocked+" name='PMTCTIRON' id='PMTCTIRON' value='"+PMTCTIRON+"' autocomplete='off'  onblur='autosave('PMTCTIRON');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>17</td> <td colspan='2'>No. of clients issued with Folic  </td><td> <input type='text' "+islocked+" name='PMTCTFOLIC' id='PMTCTFOLIC' value='"+PMTCTFOLIC+"' autocomplete='off'  onblur='autosave('PMTCTFOLIC');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>18</td> <td colspan='2'>No. of clients issued with Combined Ferrous Folate  </td><td> <input type='text' "+islocked+" name='PMTCTFERROUS' id='PMTCTFERROUS' value='"+PMTCTFERROUS+"' autocomplete='off'  onblur='autosave('PMTCTFERROUS');' maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "</table> </fieldset>";           
             
           
           
           
          String maternity_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'> B. Maternity and Delivery </b></legend> "+
             "<table frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + "<tr> <td colspan='3' class='form-actions'><b></b></td><td  colspan='2' class=\"form-actions\"> <b>Total  </b></td></tr>"               
            + "<tr><td>1.</td><td colspan='2' >Normal Deliveries </td> <td  colspan='2'><input type='text' "+islocked+" name='MATNormalDelivery' id='MATNormalDelivery' value='"+MATNormalDelivery+"' autocomplete='off' onblur=\"autosave('MATNormalDelivery');autosum('MATNormalDelivery@MATCSection@MATBreech@MATAssistedVag','MATDeliveryT');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>2.</td><td colspan='2' >Caesarian Section </td> <td  colspan='2'><input type='text' "+islocked+" name='MATCSection' id='MATCSection' value='"+MATCSection+"' autocomplete='off' onblur=\"autosave('MATCSection');autosum('MATNormalDelivery@MATCSection@MATBreech@MATAssistedVag','MATDeliveryT');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>3.</td><td colspan='2' >Breech Delivery </td> <td  colspan='2'><input type='text' "+islocked+" name='MATBreech' id='MATBreech' value='"+MATBreech+"' autocomplete='off' onblur=\"autosave('MATBreech');autosum('MATNormalDelivery@MATCSection@MATBreech@MATAssistedVag','MATDeliveryT');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>4.</td><td colspan='2' >Assisted Vaginal Delivery  </td> <td  colspan='2'><input type='text' "+islocked+" name='MATAssistedVag' id='MATAssistedVag' value='"+MATAssistedVag+"' autocomplete='off' onblur=\"autosave('MATAssistedVag');autosum('MATNormalDelivery@MATCSection@MATBreech@MATAssistedVag','MATDeliveryT');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>5.</td><td colspan='2' ><b>Total Deliveries </b> </td> <td  colspan='2'><input type='text'  readonly='true' name='MATDeliveryT' id='MATDeliveryT' value='"+MATDeliveryT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>6.</td><td colspan='2' >Live Births  </td> <td  colspan='2'><input type='text' "+islocked+"  name='MATLiveBirth' id='MATLiveBirth' value='"+MATLiveBirth+"' onblur=\"autosave('');\" autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>7.</td><td colspan='2' >Fresh still Birth  </td> <td  colspan='2'><input type='text' "+islocked+"  name='MATFreshStillBirth' id='MATFreshStillBirth'  value='"+MATFreshStillBirth+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>8.</td><td colspan='2' >Macerated Still Births  </td> <td  colspan='2'><input type='text' "+islocked+"  name='MATMeceratedStillBirth' id='MATMeceratedStillBirth' value='"+MATMeceratedStillBirth+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>9.</td><td colspan='2' >Birth with Deformities  </td> <td  colspan='2'><input type='text' "+islocked+" name='MATDeformities' id='MATDeformities' value='"+MATDeformities+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>10.</td><td colspan='2' >No. with Low APGAR Score  </td> <td  colspan='2'><input type='text' "+islocked+" name='MATLowAPGAR' id='MATLowAPGAR' value='"+MATLowAPGAR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>11.</td><td colspan='2' >No. of Low birth weight Babies (below 2500 grams)  </td> <td  colspan='2'><input "+islocked+" type='text'  name='MATWeight2500' id='MATWeight2500' value='"+MATWeight2500+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>12.</td><td colspan='2' >No. of babies given tetracycline at birth  </td> <td  colspan='2'><input type='text' "+islocked+"  name='MATTetracycline' id='MATTetracycline' value='"+MATTetracycline+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>13.</td><td colspan='2' >Pre-Term babies  </td> <td  colspan='2'><input type='text'  "+islocked+" name='MATPreTerm' id='MATPreTerm' value='"+MATPreTerm+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>14.</td><td colspan='2' >No. of babies discharged alive  </td> <td  colspan='2'><input type='text' "+islocked+"  name='MATDischargealive' id='MATDischargealive' value='"+MATDischargealive+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>15.</td><td colspan='2' >No. of Infants initiatied on breastfeeding within 1 hour after birth  </td> <td  colspan='2'><input "+islocked+" type='text'  name='MATbreastfeeding1' id='MATbreastfeeding1' value='"+MATbreastfeeding1+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>16.</td><td colspan='2' >Total Deliveries from HIV +ve women  </td> <td  colspan='2'><input "+islocked+" type='text'  name='MATDeliveriesPos' id='MATDeliveriesPos' value='"+MATDeliveriesPos+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>17.</td><td colspan='2' >Neonatal Deaths  </td> <td  colspan='2'><input type='text' "+islocked+"  name='MATNeoNatalD' id='MATNeoNatalD' value='"+MATNeoNatalD+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>18.</td><td colspan='2' >No. of adolescents (10-19YRS) Maternal Deaths  </td> <td  colspan='2'><input "+islocked+" type='text'  name='MATMaternalD10_19' id='MATMaternalD10_19' value='"+MATMaternalD10_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>19.</td><td colspan='2' >Maternal Deaths  </td> <td  colspan='2'><input type='text' "+islocked+" r name='MATMaternalD' id='MATMaternalD' value='"+MATMaternalD+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>20.</td><td colspan='2' >Maternal deaths audited  </td> <td  colspan='2'><input type='text' "+islocked+"  name='MATMaternalDAudited' id='MATMaternalDAudited' value='"+MATMaternalDAudited+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"            
            + "<tr> <td colspan='3' class='form-actions'><b>Maternal complications</b></td> <td   class=\"form-actions\"> <b>Alive  </b></td> <td   class=\"form-actions\"> <b>Deaths  </b></td> </tr>"  
            + "<tr><td>21.</td><td colspan='2' >A.P.H (Anter Partum Haemorrhage)  </td> <td ><input "+islocked+" type='text'  name='MATAPHAlive' id='MATAPHAlive' value='"+MATAPHAlive+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> <td ><input "+islocked+" type='text'  name='MATAPHDead' id='MATAPHDead' value='"+MATAPHDead+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"
            + "<tr><td>22.</td><td colspan='2' >P.P.H. (Post Partum Haemorrhage)  </td> <td ><input "+islocked+" type='text'  name='MATPPHAlive' id='MATPPHAlive' value='"+MATPPHAlive+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> <td ><input "+islocked+" type='text'  name='MATPPHDead' id='MATPPHDead' value='"+MATPPHDead+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"
            + "<tr><td>23.</td><td colspan='2' >Eclampsia  </td> <td ><input "+islocked+" type='text'  name='MATEclampAlive' id='MATEclampAlive' value='"+MATEclampAlive+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> <td ><input "+islocked+" type='text'  name='MATEclampDead' id='MATEclampDead' value='"+MATEclampDead+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"
            + "<tr><td>24.</td><td colspan='2' >Ruptured Uterus  </td> <td ><input "+islocked+" type='text'  name='MATRupUtAlive' id='MATRupUtAlive' value='"+MATRupUtAlive+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> <td ><input  "+islocked+" type='text'  name='MATRupUtDead' id='MATRupUtDead' value='"+MATRupUtDead+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"
            + "<tr><td>25.</td><td colspan='2' >Obstructed Labour  </td> <td ><input "+islocked+" type='text'  name='MATObstrLaborAlive' id='MATObstrLaborAlive' value='"+MATObstrLaborAlive+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> <td ><input "+islocked+" type='text'  name='MATObstrLaborDead' id='MATObstrLaborDead' value='"+MATObstrLaborDead+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"
            + "<tr><td>26.</td><td colspan='2' >Sepsis  </td> <td ><input "+islocked+" type='text'  name='MATSepsisAlive' id='MATSepsisAlive' value='"+MATSepsisAlive+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> <td ><input "+islocked+" type='text'  name='MATSepsisDead' id='MATSepsisDead' value='"+MATSepsisDead+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"             
            + "<tr> <td colspan='2' class='form-actions'><b></b></td> <td  class='form-actions'><b></b></td>  <td colspan='2'   class=\"form-actions\"> <b>Total  </b></td> </tr>"        
            + "<tr> <td class='form-actions'>27</td> <td  class='form-actions' rowspan='4'>No. of Referrals</td>  <td  class='form-actions'>From Other Health Facility</td> <td  colspan='2'><input "+islocked+" type='text'  name='MATREFFromOtherFacility' id='MATREFFromOtherFacility' value='"+MATREFFromOtherFacility+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"        
            + "<tr> <td class='form-actions'>28</td>   <td  class='form-actions'>From Community Unit</td> <td  colspan='2'><input "+islocked+" type='text'  name='MATREFFromCU' id='MATREFFromCU' value='"+MATREFFromCU+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"        
            + "<tr> <td class='form-actions'>29</td>   <td  class='form-actions'>To Other Health Facility</td> <td  colspan='2'><input "+islocked+" type='text'  name='MATREFToOtherFacility' id='MATREFToOtherFacility' value='"+MATREFToOtherFacility+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"        
            + "<tr> <td class='form-actions'>30</td>   <td  class='form-actions'>To Community Unit</td> <td  colspan='2'><input "+islocked+" type='text'  name='MATREFToCU' id='MATREFToCU' value='"+MATREFToCU+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"        
            + " </table> </fieldset> ";          
            
         
          
 String  SGBVRape72_0_9="1";
 String  SGBVRape72_10_17="2";
 String  SGBVRape72_18_49="3";
 String  SGBVRape72_50="4";
 String  SGBVRape72T="5";
 String  SGBVinitPEP0_9="6";
 String  SGBVinitPEP10_17="7";
 String  SGBVinitPEP18_49="8";
 String  SGBVinitPEP50="9";
 String  SGBVinitPEPT="10";
 String  SGBVcompPEP0_9="11";
 String  SGBVcompPEP10_17="12";
 String  SGBVcompPEP18_49="13";
 String  SGBVcompPEP50="14";
 String  SGBVcompPEPT="15";
 String  SGBVPregnant0_9="16";
 String  SGBVPregnant10_17="17";
 String  SGBVPregnant18_49="18";
 String  SGBVPregnant50="19";
 String  SGBVPregnantT="20";
 String  SGBVseroconverting0_9="21";
 String  SGBVseroconverting10_17="22";
 String  SGBVseroconverting18_49="23";
 String  SGBVseroconverting50="24";
 String  SGBVseroconvertingT="25";
 String  SGBVsurvivors0_9="26";
 String  SGBVsurvivors10_17="27";
 String  SGBVsurvivors18_49="28";
 String  SGBVsurvivors50="29";
 String  SGBVsurvivorsT="30";

          

          
          
 //SGBV 
          String sgbv_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'> C. Sexual and Gender Based Violence (SGBV) </b></legend> "+
             "<table frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + "<tr> <td colspan='2' class='form-actions'><b></b></td> <td class=\"form-actions\"> <b>0-9 yrs</b></td> <td class=\"form-actions\"> <b>10-17 yrs</b></td> <td class=\"form-actions\"> <b>18-49 yrs</b></td> <td class='form-actions'> <b>50 yrs</b></td><td class='form-actions'> <b>Total</b> </td> </tr>"               
            + "<tr> <td >1</td> <td > No. of Rape Cases presenting within 72 hrs </td>"
                  + " <td > <input "+islocked+" type='text'  name='SGBVRape72_0_9' id='SGBVRape72_0_9' onblur=\"autosave('SGBVRape72_0_9');autosum('SGBVRape72_0_9@SGBVRape72_10_17@SGBVRape72_18_49@SGBVRape72_50','SGBVRape72T');\" value='"+SGBVRape72_0_9+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVRape72_10_17' id='SGBVRape72_10_17' onblur=\"autosave('SGBVRape72_10_17');autosum('SGBVRape72_0_9@SGBVRape72_10_17@SGBVRape72_18_49@SGBVRape72_50','SGBVRape72T');\" value='"+SGBVRape72_10_17+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVRape72_18_49' id='SGBVRape72_18_49' onblur=\"autosave('SGBVRape72_18_49');autosum('SGBVRape72_0_9@SGBVRape72_10_17@SGBVRape72_18_49@SGBVRape72_50','SGBVRape72T');\" value='"+SGBVRape72_18_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td>"
                  + " <td >  <input "+islocked+" type='text'  name='SGBVRape72_50' id='SGBVRape72_50' onblur=\"autosave('SGBVRape72_50');autosum('SGBVRape72_0_9@SGBVRape72_10_17@SGBVRape72_18_49@SGBVRape72_50','SGBVRape72T');\" value='"+SGBVRape72_50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "               
                  + " <td >  <input  type='text'  name='SGBVRape72T' readonly onblur=\"autosave('SGBVRape72_50');autosum('SGBVRape72_0_9@SGBVRape72_10_17@SGBVRape72_18_49@SGBVRape72_50','SGBVRape72T');\" id='SGBVRape72T' value='"+SGBVRape72T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
           
                  + "<tr> <td >2</td> <td >No. of Rape Cases initiating PEP </td>"
                  + " <td > <input "+islocked+" type='text'  name='SGBVinitPEP0_9' id='SGBVinitPEP0_9' onblur=\"autosave('SGBVinitPEP0_9');autosum('SGBVinitPEP0_9@SGBVinitPEP10_17@SGBVinitPEP10_17@SGBVinitPEP50','SGBVinitPEPT');\" value='"+SGBVinitPEP0_9+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVinitPEP10_17' id='SGBVinitPEP10_17' onblur=\"autosave('SGBVinitPEP10_17');autosum('SGBVinitPEP0_9@SGBVinitPEP10_17@SGBVinitPEP18_49@SGBVinitPEP50','SGBVinitPEPT');\" value='"+SGBVinitPEP10_17+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVinitPEP18_49' id='SGBVinitPEP18_49' onblur=\"autosave('SGBVinitPEP18_49');autosum('SGBVinitPEP0_9@SGBVinitPEP10_17@SGBVinitPEP18_49@SGBVinitPEP50','SGBVinitPEPT');\" value='"+SGBVinitPEP18_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td>"
                  + " <td >  <input "+islocked+" type='text'  name='SGBVinitPEP50' id='SGBVinitPEP50' onblur=\"autosave('SGBVinitPEP50');autosum('SGBVinitPEP0_9@SGBVinitPEP10_17@SGBVinitPEP18_49@SGBVinitPEP50','SGBVinitPEPT');\" value='"+SGBVinitPEP50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "               
                  + " <td >  <input  type='text'  name='SGBVinitPEPT' readonly onblur=\"autosave('SGBVinitPEP50');autosum('SGBVinitPEP0_9@SGBVinitPEP10_17@SGBVinitPEP10_17@SGBVinitPEP50','SGBVinitPEPT');\" id='SGBVinitPEPT' value='"+SGBVinitPEPT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
           
                    + "<tr> <td >3</td> <td >No. Completed PEP </td>"
                  + " <td > <input "+islocked+" type='text'  name='SGBVcompPEP0_9' id='SGBVcompPEP0_9' onblur=\"autosave('SGBVcompPEP0_9');autosum('SGBVcompPEP0_9@SGBVcompPEP10_17@SGBVcompPEP10_17@SGBVcompPEP50','SGBVcompPEPT');\" value='"+SGBVcompPEP0_9+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVcompPEP10_17' id='SGBVcompPEP10_17' onblur=\"autosave('SGBVcompPEP10_17');autosum('SGBVcompPEP0_9@SGBVcompPEP10_17@SGBVcompPEP18_49@SGBVcompPEP50','SGBVcompPEPT');\" value='"+SGBVcompPEP10_17+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVcompPEP18_49' id='SGBVcompPEP18_49' onblur=\"autosave('SGBVcompPEP18_49');autosum('SGBVcompPEP0_9@SGBVcompPEP10_17@SGBVcompPEP18_49@SGBVcompPEP50','SGBVcompPEPT');\" value='"+SGBVcompPEP18_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td>"
                  + " <td >  <input "+islocked+" type='text'  name='SGBVcompPEP50' id='SGBVcompPEP50' onblur=\"autosave('SGBVcompPEP50');autosum('SGBVcompPEP0_9@SGBVcompPEP10_17@SGBVcompPEP18_49@SGBVcompPEP50','SGBVcompPEPT');\" value='"+SGBVcompPEP50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "               
                  + " <td >  <input  type='text'  name='SGBVcompPEPT' readonly onblur=\"autosave('SGBVcompPEP50');autosum('SGBVcompPEP0_9@SGBVcompPEP10_17@SGBVcompPEP10_17@SGBVcompPEP50','SGBVcompPEPT');\" id='SGBVcompPEPT' value='"+SGBVcompPEPT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
           
                   + "<tr> <td >4</td> <td >No. of Survivors Pregrant 4weeks after Exposure </td>"
                  + " <td > <input "+islocked+" type='text'  name='SGBVPregnant0_9' id='SGBVPregnant0_9' onblur=\"autosave('SGBVPregnant0_9');autosum('SGBVPregnant0_9@SGBVPregnant10_17@SGBVPregnant10_17@SGBVPregnant50','SGBVPregnantT');\" value='"+SGBVPregnant0_9+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVPregnant10_17' id='SGBVPregnant10_17' onblur=\"autosave('SGBVPregnant10_17');autosum('SGBVPregnant0_9@SGBVPregnant10_17@SGBVPregnant18_49@SGBVPregnant50','SGBVPregnantT');\" value='"+SGBVPregnant10_17+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVPregnant18_49' id='SGBVPregnant18_49' onblur=\"autosave('SGBVPregnant18_49');autosum('SGBVPregnant0_9@SGBVPregnant10_17@SGBVPregnant18_49@SGBVPregnant50','SGBVPregnantT');\" value='"+SGBVPregnant18_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td>"
                  + " <td >  <input "+islocked+" type='text'  name='SGBVPregnant50' id='SGBVPregnant50' onblur=\"autosave('SGBVPregnant50');autosum('SGBVPregnant0_9@SGBVPregnant10_17@SGBVPregnant18_49@SGBVPregnant50','SGBVPregnantT');\" value='"+SGBVPregnant50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "               
                  + " <td >  <input  type='text'  name='SGBVPregnantT' readonly onblur=\"autosave('SGBVPregnant50');autosum('SGBVPregnant0_9@SGBVPregnant10_17@SGBVPregnant10_17@SGBVPregnant50','SGBVPregnantT');\" id='SGBVPregnantT' value='"+SGBVPregnantT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
             
                     + "<tr> <td >5</td> <td > No. of survivors seroconverting 3months after exposure </td>"
                  + " <td > <input "+islocked+" type='text'  name='SGBVseroconverting0_9' id='SGBVseroconverting0_9' onblur=\"autosave('SGBVseroconverting0_9');autosum('SGBVseroconverting0_9@SGBVseroconverting10_17@SGBVseroconverting10_17@SGBVseroconverting50','SGBVseroconvertingT');\" value='"+SGBVseroconverting0_9+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVseroconverting10_17' id='SGBVseroconverting10_17' onblur=\"autosave('SGBVseroconverting10_17');autosum('SGBVseroconverting0_9@SGBVseroconverting10_17@SGBVseroconverting18_49@SGBVseroconverting50','SGBVseroconvertingT');\" value='"+SGBVseroconverting10_17+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVseroconverting18_49' id='SGBVseroconverting18_49' onblur=\"autosave('SGBVseroconverting18_49');autosum('SGBVseroconverting0_9@SGBVseroconverting10_17@SGBVseroconverting18_49@SGBVseroconverting50','SGBVseroconvertingT');\" value='"+SGBVseroconverting18_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td>"
                  + " <td >  <input "+islocked+" type='text'  name='SGBVseroconverting50' id='SGBVseroconverting50' onblur=\"autosave('SGBVseroconverting50');autosum('SGBVseroconverting0_9@SGBVseroconverting10_17@SGBVseroconverting18_49@SGBVseroconverting50','SGBVseroconvertingT');\" value='"+SGBVseroconverting50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "               
                  + " <td >  <input  type='text'  name='SGBVseroconvertingT' readonly onblur=\"autosave('SGBVseroconverting50');autosum('SGBVseroconverting0_9@SGBVseroconverting10_17@SGBVseroconverting10_17@SGBVseroconverting50','SGBVseroconvertingT');\" id='SGBVseroconvertingT' value='"+SGBVseroconvertingT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
             
                  + " <tr> <td >6</td> <td > Total Survivors seen </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVsurvivors0_9' id='SGBVsurvivors0_9' onblur=\"autosave('SGBVsurvivors0_9');autosum('SGBVsurvivors0_9@SGBVsurvivors10_17@SGBVsurvivors10_17@SGBVsurvivors50','SGBVsurvivorsT');\" value='"+SGBVsurvivors0_9+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVsurvivors10_17' id='SGBVsurvivors10_17' onblur=\"autosave('SGBVsurvivors10_17');autosum('SGBVsurvivors0_9@SGBVsurvivors10_17@SGBVsurvivors18_49@SGBVsurvivors50','SGBVsurvivorsT');\" value='"+SGBVsurvivors10_17+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVsurvivors18_49' id='SGBVsurvivors18_49' onblur=\"autosave('SGBVsurvivors18_49');autosum('SGBVsurvivors0_9@SGBVsurvivors10_17@SGBVsurvivors18_49@SGBVsurvivors50','SGBVsurvivorsT');\" value='"+SGBVsurvivors18_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td>"
                  + " <td >  <input "+islocked+" type='text'  name='SGBVsurvivors50' id='SGBVsurvivors50' onblur=\"autosave('SGBVsurvivors50');autosum('SGBVsurvivors0_9@SGBVsurvivors10_17@SGBVsurvivors18_49@SGBVsurvivors50','SGBVsurvivorsT');\" value='"+SGBVsurvivors50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "               
                  + " <td >  <input  type='text'  name='SGBVsurvivorsT' readonly onblur=\"autosave('SGBVsurvivors50');autosum('SGBVsurvivors0_9@SGBVsurvivors10_17@SGBVsurvivors10_17@SGBVsurvivors50','SGBVsurvivorsT');\" id='SGBVsurvivorsT' value='"+SGBVsurvivorsT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
                  + " </table> </fieldset> ";   
           
          
String   FPProgestinN="1";
String FPProgestinR="2";
String FPProgestinT="3";
String FPCocN="4";
String FPCocR="5";
String FPCocT="6";
String FPEcpN="7";
String FPEcpR="8";
String FPEcpT="9";
String FPINJECTABLESN="10";
String FPINJECTABLESR="11";
String FPINJECTABLEST="12";
String FPINJECTIONSN="13";
String FPINJECTIONSR="14";
String FPINJECTIONST="15";
String FPIUCDN="16";
String FPIUCDR="17";
String FPIUCDT="18";
String FPIMPLANTSN="19";
String FPIMPLANTSR="20";
String FPIMPLANTST="21";
String FPBTLN="22";
String FPBTLR="23";
String FPBTLT="24";
String FPVasectomyN="25";
String FPVasectomyR="26";
String FPVasectomyT="27";
String FPCONDOMSMN="28";
String FPCONDOMSFN="29";
String FPCONDOMST="30";
String FPNaturalN="31";
String FPNaturalR="32";
String FPNaturalT="33";
String FPCLIENTSN="34";
String FPCLIENTSR="35";
String FPCLIENTST="36";
String FPADOLESCENT10_14N="37";
String FPADOLESCENT10_14R="38";
String FPADOLESCENT10_14T="39";
String FPADOLESCENT15_19N="40";
String FPADOLESCENT15_19R="41";
String FPADOLESCENT15_19T="42";
String FPADOLESCENT20_24N="43";
String FPADOLESCENT20_24R="44";
String FPADOLESCENT20_24T="45";
String FPIUCDRemoval="46";
String FPIMPLANTSRemoval="47";

          
          
              String FamilyPlanninng_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'> D. Family Planning (Number of clients Issued with contraceptives) </b></legend> "+
              "<table frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + "<tr> <td colspan='3' class='form-actions'><b></b></td> <td class=\"form-actions\"> <b>NEW</b></td> <td class=\"form-actions\"> <b>RE-VISITS</b></td> <td class=\"form-actions\"> <b>TOTAL</b></td> </tr>"               
            
            + "<tr> <td >1</td> <td rowspan='3' >Pills</td> <td>Progestin only pills </td> "
            + "<td > <input "+islocked+" type='text'  name='FPProgestinN'  onblur=\"autosave('FPProgestinN');autosum('FPProgestinN@FPProgestinR','FPProgestinT');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPProgestinN' value='"+FPProgestinN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPProgestinR'  onblur=\"autosave('FPProgestinR');autosum('FPProgestinN@FPProgestinR','FPProgestinT');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPProgestinR' value='"+FPProgestinR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly tabindex='-1' type='text'  name='FPProgestinT'   id='FPProgestinT' value='"+FPProgestinT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
            
            + "<tr> <td >2</td>   <td>Combined Oral Contraceptive pills </td> "
            + "<td > <input "+islocked+" type='text'  name='FPCocN'  onblur=\"autosave('FPCocN');autosum('FPCocN@FPCocR','FPCocT');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPCocN' value='"+FPCocN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPCocR'  onblur=\"autosave('FPCocR');autosum('FPCocN@FPCocR','FPCocT');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPCocR' value='"+FPCocR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1' type='text'  name='FPCocT'   id='FPCocT' value='"+FPCocT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
                      
            + "<tr> <td >3</td>   <td>Emergency contraceptive pill </td> "
            + "<td > <input "+islocked+" type='text'  name='FPEcpN'  onblur=\"autosave('FPEcpN');autosum('FPEcpN@FPEcpR','FPEcpT');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPEcpN' value='"+FPEcpN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPEcpR'  onblur=\"autosave('FPEcpR');autosum('FPCocN@FPEcpR','FPEcpT');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPEcpR' value='"+FPEcpR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1' type='text'  name='FPEcpT'   id='FPEcpT' value='"+FPEcpT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
           
//            + "<tr> <td >4</td> <td>  </td> <td>Injectables </td> "
//            + "<td > <input "+islocked+" type='text'  name='FPINJECTABLESN'  onblur=\"autosave('FPINJECTABLESN');autosum('FPINJECTABLESN@FPINJECTABLESR','FPINJECTABLEST');\" id='FPINJECTABLESN' value='"+FPINJECTABLESN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
//            + "<td > <input "+islocked+" type='text'  name='FPINJECTABLESR'  onblur=\"autosave('FPINJECTABLESR');autosum('FPINJECTABLESN@FPINJECTABLESR','FPINJECTABLEST');\" id='FPINJECTABLESR' value='"+FPINJECTABLESR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
//            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPINJECTABLEST' readonly onblur=\"autosave('FPINJECTABLEST');autosum('FPINJECTABLESN@FPINJECTABLESR','FPINJECTABLEST');\" id='FPINJECTABLEST' value='"+FPINJECTABLEST+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
//                      
            + "<tr> <td >5</td> <td> Injections </td> <td>Injectables </td> "
            + "<td > <input "+islocked+" type='text'  name='FPINJECTIONSN'  onblur=\"autosave('FPINJECTIONSN');autosum('FPINJECTIONSN@FPINJECTIONSR','FPINJECTIONST');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPINJECTIONSN' value='"+FPINJECTIONSN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPINJECTIONSR'  onblur=\"autosave('FPINJECTIONSR');autosum('FPINJECTIONSN@FPINJECTIONSR','FPINJECTIONST');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPINJECTIONSR' value='"+FPINJECTIONSR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPINJECTIONST'   id='FPINJECTIONST' value='"+FPINJECTIONST+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
                
                      
             + "<tr> <td >6</td> <td> I.U.C.D </td> <td>Insertion </td> "
            + "<td > <input "+islocked+" type='text'  name='FPIUCDN'  onblur=\"autosave('FPIUCDN');autosum('FPIUCDN@FPIUCDR','FPIUCDT');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPIUCDN' value='"+FPIUCDN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPIUCDR'  onblur=\"autosave('FPIUCDR');autosum('FPIUCDN@FPIUCDR','FPIUCDT');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPIUCDR' value='"+FPIUCDR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPIUCDT'   id='FPIUCDT' value='"+FPIUCDT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
            
              + "<tr> <td >7</td> <td> Implants </td> <td>Insertion </td> "
            + "<td > <input "+islocked+" type='text'  name='FPIMPLANTSN'  onblur=\"autosave('FPIMPLANTSN');autosum('FPIMPLANTSN@FPIMPLANTSR','FPIMPLANTST');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPIMPLANTSN' value='"+FPIMPLANTSN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPIMPLANTSR'  onblur=\"autosave('FPIMPLANTSR');autosum('FPIMPLANTSN@FPIMPLANTSR','FPIMPLANTST');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPIMPLANTSR' value='"+FPIMPLANTSR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPIMPLANTST'   id='FPIMPLANTST' value='"+FPIMPLANTST+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
                        
           
            + "<tr> <td >8</td> <td> Implants </td> <td>BTL</td> "
            + "<td > <input "+islocked+" type='text'  name='FPBTLN'  onblur=\"autosave('FPBTLN');autosum('FPBTLN@FPBTLR','FPBTLT');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPBTLN' value='"+FPBTLN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPBTLR'  onblur=\"autosave('FPBTLR');autosum('FPBTLN@FPBTLR','FPBTLT');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPBTLR' value='"+FPBTLR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPBTLT'   id='FPBTLT' value='"+FPBTLT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
                        
             + "<tr> <td >9</td> <td> Sterilization </td> <td>Vasectomy</td> "
            + "<td > <input "+islocked+" type='text'  name='FPVasectomyN'  onblur=\"autosave('FPVasectomyN');autosum('FPVasectomyN@FPVasectomyR','FPVasectomyT');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPVasectomyN' value='"+FPVasectomyN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPVasectomyR'  onblur=\"autosave('FPVasectomyR');autosum('FPVasectomyN@FPVasectomyR','FPVasectomyT');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPVasectomyR' value='"+FPVasectomyR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly tabindex='-1'    type='text'  name='FPVasectomyT'   id='FPVasectomyT' value='"+FPVasectomyT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
                                 
            
            + "<tr> <td >10</td> <td rowspan='2'> Condoms </td> <td>No.of clients received (Male condoms)</td> "
            + "<td > <input "+islocked+" type='text'  name='FPCONDOMSMN'  onblur=\"autosave('FPCONDOMSMN');autosum('FPCONDOMSMN@FPCONDOMSFN','FPCONDOMST');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPCONDOMSMN' value='"+FPCONDOMSMN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > </td> "
            + "<td > </td> </tr>"               
                         
            + "<tr> <td >11</td>  <td>No.of clients received (Female condoms)</td> "
            + "<td > <input "+islocked+" type='text'  name='FPCONDOMSFN'  onblur=\"autosave('FPCONDOMSFN');autosum('FPCONDOMSMN@FPCONDOMSFN','FPCONDOMST');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPCONDOMSFN' value='"+FPCONDOMSFN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > </td> "
            + "<td ><input readonly tabindex='-1' type='hidden'  name='FPCONDOMST'   id='FPCONDOMST' value='"+FPCONDOMST+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td> </tr>"            
            
            + "<tr> <td >12</td> <td colspan='2'>Natural Family Planning</td> "
            + "<td > <input "+islocked+" type='text'  name='FPNaturalN'  onblur=\"autosave('FPNaturalN');autosum('FPNaturalN@FPNaturalR','FPNaturalT');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPNaturalN' value='"+FPNaturalN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPNaturalR'  onblur=\"autosave('FPNaturalR');autosum('FPNaturalN@FPNaturalR','FPNaturalT');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPNaturalR' value='"+FPNaturalR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPNaturalT'   id='FPNaturalT' value='"+FPNaturalT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
             
            + "<tr> <td >13</td> <td colspan='2'>Total No of Clients</td> "
            + "<td > <input readonly tabindex='-1' type='text'  name='FPCLIENTSN'   id='FPCLIENTSN' value='"+FPCLIENTSN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly tabindex='-1' type='text'  name='FPCLIENTSR'   id='FPCLIENTSR' value='"+FPCLIENTSR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPCLIENTST'   id='FPCLIENTST' value='"+FPCLIENTST+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
             
                      
             + "<tr> <td >14</td> <td colspan='2'>Total adolescent clients (10-14YRS) receiving FP Services</td> "
            + "<td > <input "+islocked+" type='text'  name='FPADOLESCENT10_14N'  onblur=\"autosave('FPADOLESCENT10_14N');autosum('FPADOLESCENT10_14N@FPADOLESCENT10_14R','FPADOLESCENT10_14T');\" id='FPADOLESCENT10_14N' value='"+FPADOLESCENT10_14N+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPADOLESCENT10_14R'  onblur=\"autosave('FPADOLESCENT10_14R');autosum('FPADOLESCENT10_14N@FPADOLESCENT10_14R','FPADOLESCENT10_14T');\" id='FPADOLESCENT10_14R' value='"+FPADOLESCENT10_14R+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly   tabindex='-1'  type='text'  name='FPADOLESCENT10_14T'   id='FPADOLESCENT10_14T' value='"+FPADOLESCENT10_14T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
                       
              + "<tr> <td >15</td> <td colspan='2'>Total adolescent clients (15-19YRS) receiving FP Services</td> "
            + "<td > <input "+islocked+" type='text'  name='FPADOLESCENT15_19N'  onblur=\"autosave('FPADOLESCENT15_19N');autosum('FPADOLESCENT15_19N@FPADOLESCENT15_19R','FPADOLESCENT15_19T');\" id='FPADOLESCENT15_19N' value='"+FPADOLESCENT15_19N+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPADOLESCENT15_19R'  onblur=\"autosave('FPADOLESCENT15_19R');autosum('FPADOLESCENT15_19N@FPADOLESCENT15_19R','FPADOLESCENT15_19T');\" id='FPADOLESCENT15_19R' value='"+FPADOLESCENT15_19R+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPADOLESCENT15_19T'   id='FPADOLESCENT15_19T' value='"+FPADOLESCENT15_19T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
             
              + "<tr> <td >16</td> <td colspan='2'>Total youth clients (20-24YRS) receiving FP Services</td> "
            + "<td > <input "+islocked+" type='text'  name='FPADOLESCENT20_24N'  onblur=\"autosave('FPADOLESCENT20_24N');autosum('FPADOLESCENT20_24N@FPADOLESCENT20_24R','FPADOLESCENT20_24T');\" id='FPADOLESCENT20_24N' value='"+FPADOLESCENT20_24N+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPADOLESCENT20_24R'  onblur=\"autosave('FPADOLESCENT20_24R');autosum('FPADOLESCENT20_24N@FPADOLESCENT20_24R','FPADOLESCENT20_24T');\" id='FPADOLESCENT20_24R' value='"+FPADOLESCENT20_24R+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPADOLESCENT20_24T'   id='FPADOLESCENT20_24T' value='"+FPADOLESCENT20_24T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
            
             + "<tr> <td >10</td> <td rowspan='2'> Removals </td> <td>I.U.C.D</td> "
            + "<td > <input "+islocked+" type='text'  name='FPIUCDRemoval'  onblur=\"autosave('FPIUCDRemoval');\" id='FPIUCDRemoval' value='"+FPIUCDRemoval+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > </td> "
            + "<td > </td> </tr>"               
                         
            + "<tr> <td >11</td>  <td>Implants</td> "
            + "<td > <input "+islocked+" type='text'  name='FPIMPLANTSRemoval'  onblur=\"autosave('FPIMPLANTSRemoval');\" id='FPIMPLANTSRemoval' value='"+FPIMPLANTSRemoval+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > </td> "
            + "<td > </td> </tr>"            
          + " </table> </fieldset> ";
              
        String  PAC10_19="";
        String PACT="";
    
              
         String  pac_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'>E. Post Abortal Care (PAC) Services </b></legend> "+
             "<table frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + "<tr> <td colspan='2' class='form-actions'><b></b></td><td   class=\"form-actions\"> <b>Total  </b></td></tr>"               
            
            + "<tr> <td >1</td>   <td  >Adolescent (10-19 years) Accessing PAC Services</td> <td  ><input "+islocked+" type='text' autosave('PAC10_19');  name='PAC10_19' id='PAC10_19' value='"+PAC10_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"           
            + "<tr> <td >2</td>   <td  >Total Receiving PAC Services</td> <td  ><input "+islocked+" type='text' autosave('PACT');  name='PACT' id='PACT' value='"+PACT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"           
          + " </table> </fieldset> "; 
         
        
String CHANIS0_5NormalweightF="1";
String CHANIS0_5NormalweightM="2";
String CHANIS0_5NormalweightT="3";
String CHANIS0_5UnderweightF="4";
String CHANIS0_5UnderweightM="5";
String CHANIS0_5UnderweightT="6";
String CHANIS0_5sevUnderweightF="7";
String CHANIS0_5sevUnderweightM="8";
String CHANIS0_5sevUnderweightT="9";
String CHANIS0_5OverweightF="10";
String CHANIS0_5OverweightM="11";
String CHANIS0_5OverweightT="12";
String CHANIS0_5ObeseF="13";
String CHANIS0_5ObeseM="14";
String CHANIS0_5ObeseT="15";
String CHANIS0_5TWF="16";
String CHANIS0_5TWM="17";
String CHANIS0_5TW="18";
String CHANIS6_23NormalweightF="19";
String CHANIS6_23NormalweightM="20";
String CHANIS6_23NormalweightT="21";
String CHANIS6_23UnderweightF="22";
String CHANIS6_23UnderweightM="23";
String CHANIS6_23UnderweightT="24";
String CHANIS6_23sevUnderweightF="25";
String CHANIS6_23sevUnderweightM="26";
String CHANIS6_23sevUnderweightT="27";
String CHANIS6_23OverweightF="28";
String CHANIS6_23OverweightM="29";
String CHANIS6_23OverweightT="30";
String CHANIS6_23ObeseF="31";
String CHANIS6_23ObeseM="32";
String CHANIS6_23ObeseT="33";
String CHANIS6_23TWF="34";
String CHANIS6_23TWM="35";
String CHANIS6_23TW="36";
String CHANIS24_59NormalweightF="37";
String CHANIS24_59NormalweightM="38";
String CHANIS24_59NormalweightT="39";
String CHANIS24_59UnderweightF="40";
String CHANIS24_59UnderweightM="41";
String CHANIS24_59UnderweightT="42";
String CHANIS24_59sevUnderweightF="43";
String CHANIS24_59sevUnderweightM="44";
String CHANIS24_59sevUnderweightT="45";
String CHANIS24_59OverweightF="46";
String CHANIS24_59OverweightM="47";
String CHANIS24_59OverweightT="48";
String CHANIS24_59ObeseF="49";
String CHANIS24_59ObeseM="50";
String CHANIS24_59ObeseT="51";
String CHANIS24_59TWF="52";
String CHANIS24_59TWM="53";
String CHANIS24_59TW="54";
String CHANISMUACNormalF="55";
String CHANISMUACNormalM="56";
String CHANISMUACNormalT="57";
String CHANISMUACModerateF="58";
String CHANISMUACModerateM="59";
String CHANISMUACModerateT="60";
String CHANISMUACSevereF="61";
String CHANISMUACSevereM="62";
String CHANISMUACSevereT="64";
String CHANISMUACMeasuredF="65";
String CHANISMUACMeasuredM="66";
String CHANISMUACMeasuredT="67";
String CHANIS0_5NormalHeightF="68";
String CHANIS0_5NormalHeightM="69";
String CHANIS0_5NormalHeightT="70";
String CHANIS0_5StuntedF="71";
String CHANIS0_5StuntedM="72";
String CHANIS0_5StuntedT="73";
String CHANIS0_5sevStuntedF="74";
String CHANIS0_5sevStuntedM="75";
String CHANIS0_5sevStuntedT="76";
String CHANIS0_5TMeasF="77";
String CHANIS0_5TMeasM="78";
String CHANIS0_5TMeas="79";
String CHANIS6_23NormalHeightF="80";
String CHANIS6_23NormalHeightM="81";
String CHANIS6_23NormalHeightT="82";
String CHANIS6_23StuntedF="83";
String CHANIS6_23StuntedM="84";
String CHANIS6_23StuntedT="85";
String CHANIS6_23sevStuntedF="86";
String CHANIS6_23sevStuntedM="87";
String CHANIS6_23sevStuntedT="88";
String CHANIS6_23TMeasF="89";
String CHANIS6_23TMeasM="90";
String CHANIS6_23TMeas="91";
String CHANIS24_59NormalHeightF="92";
String CHANIS24_59NormalHeightM="93";
String CHANIS24_59NormalHeightT="94";
String CHANIS24_59StuntedF="95";
String CHANIS24_59StuntedM="96";
String CHANIS24_59StuntedT="97";
String CHANIS24_59sevStuntedF="98";
String CHANIS24_59sevStuntedM="99";
String CHANIS24_59sevStuntedT="100";
String CHANIS24_59TMeasF="101";
String CHANIS24_59TMeasM="102";
String CHANIS24_59TMeas="103";
String CHANIS0_59NewVisitsF="104";
String CHANIS0_59NewVisitsM="105";
String CHANIS0_59NewVisitsT="106";
String CHANIS0_59KwashiakorF="107";
String CHANIS0_59KwashiakorM="108";
String CHANIS0_59KwashiakorT="109";
String CHANIS0_59MarasmusF="110";
String CHANIS0_59MarasmusM="111";
String CHANIS0_59MarasmusT="112";
String CHANIS0_59FalgrowthF="113";
String CHANIS0_59FalgrowthM="114";
String CHANIS0_59FalgrowthT="115";
String CHANIS0_59F="116";
String CHANIS0_59M="117";
String CHANIS0_59T="118";
String CHANIS0_5EXCLBreastF="119";
String CHANIS0_5EXCLBreastM="120";
String CHANIS0_5EXCLBreastT="121";
String CHANIS12_59DewormedF="122";
String CHANIS12_59DewormedM="123";
String CHANIS12_59DewormedT="124";
String CHANIS6_23MNPsF="125";
String CHANIS6_23MNPsM="126";
String CHANIS6_23MNPsT="127";
String CHANIS0_59DisabilityF="128";
String CHANIS0_59DisabilityM="129";
String CHANIS0_59DisabilityT="130";

         
         
          String  chanisweight_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'>F. Child Health and Nutrition Information System(CHANIS)</b></legend> "+
             "<table frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + " <tr> <td colspan='3' class='form-actions'><b>Weight for Age</b></td><td   class=\"form-actions\"> <b>F</b></td><td   class=\"form-actions\"> <b>M  </b></td><td   class=\"form-actions\"> <b>Total  </b></td></tr>"  
           
            + " <tr> <td >1</td><td rowspan='6' > 0-< 6 months </td> <td>Normal Weight for Age </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5NormalweightF');\"  name='CHANIS0_5NormalweightF' id='CHANIS0_5NormalweightF' value='"+CHANIS0_5NormalweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5NormalweightM');autosum('CHANIS0_5NormalweightF@CHANIS0_5NormalweightM','CHANIS0_5NormalweightT');\"  name='CHANIS0_5NormalweightM' id='CHANIS0_5NormalweightM' value='"+CHANIS0_5NormalweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5NormalweightT');autosum('CHANIS0_5NormalweightF@CHANIS0_5NormalweightM','CHANIS0_5NormalweightT');\"  name='CHANIS0_5NormalweightT' id='CHANIS0_5NormalweightT' value='"+CHANIS0_5NormalweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                  
            + " <tr> <td >2</td><td>Underweight </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5UnderweightF');\"  name='CHANIS0_5UnderweightF' id='CHANIS0_5UnderweightF' value='"+CHANIS0_5UnderweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5UnderweightM');autosum('CHANIS0_5UnderweightF@CHANIS0_5UnderweightM','CHANIS0_5UnderweightT');\"  name='CHANIS0_5UnderweightM' id='CHANIS0_5UnderweightM' value='"+CHANIS0_5UnderweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5UnderweightT');autosum('CHANIS0_5UnderweightF@CHANIS0_5UnderweightM','CHANIS0_5UnderweightT');\"  name='CHANIS0_5UnderweightT' id='CHANIS0_5UnderweightT' value='"+CHANIS0_5UnderweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
              
                  
            + " <tr> <td >3</td><td>Severe Underweight </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5sevUnderweightF');\"  name='CHANIS0_5sevUnderweightF' id='CHANIS0_5sevUnderweightF' value='"+CHANIS0_5sevUnderweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5sevUnderweightM');autosum('CHANIS0_5sevUnderweightF@CHANIS0_5sevUnderweightM','CHANIS0_5sevUnderweightT');\"  name='CHANIS0_5sevUnderweightM' id='CHANIS0_5sevUnderweightM' value='"+CHANIS0_5sevUnderweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5sevUnderweightT');autosum('CHANIS0_5sevUnderweightF@CHANIS0_5sevUnderweightM','CHANIS0_5sevUnderweightT');\"  name='CHANIS0_5sevUnderweightT' id='CHANIS0_5sevUnderweightT' value='"+CHANIS0_5sevUnderweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >4</td><td>Overweight </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5OverweightF');\"  name='CHANIS0_5OverweightF' id='CHANIS0_5OverweightF' value='"+CHANIS0_5OverweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5OverweightM');autosum('CHANIS0_5OverweightF@CHANIS0_5OverweightM','CHANIS0_5OverweightT');\"  name='CHANIS0_5OverweightM' id='CHANIS0_5OverweightM' value='"+CHANIS0_5OverweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5OverweightT');autosum('CHANIS0_5OverweightF@CHANIS0_5OverweightM','CHANIS0_5OverweightT');\"  name='CHANIS0_5OverweightT' id='CHANIS0_5OverweightT' value='"+CHANIS0_5OverweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >5</td><td>Obese </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5ObeseF');\"  name='CHANIS0_5ObeseF' id='CHANIS0_5ObeseF' value='"+CHANIS0_5ObeseF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5ObeseM');autosum('CHANIS0_5ObeseF@CHANIS0_5ObeseM','CHANIS0_5ObeseT');\"  name='CHANIS0_5ObeseM' id='CHANIS0_5ObeseM' value='"+CHANIS0_5ObeseM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5ObeseT');autosum('CHANIS0_5ObeseF@CHANIS0_5ObeseM','CHANIS0_5ObeseT');\"  name='CHANIS0_5ObeseT' id='CHANIS0_5ObeseT' value='"+CHANIS0_5ObeseT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >6</td><td>Total Weighed </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5TWF');\"  name='CHANIS0_5TWF' id='CHANIS0_5TWF' value='"+CHANIS0_5TWF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5TWM');autosum('CHANIS0_5TWF@CHANIS0_5TWM','CHANIS0_5TWT');\"  name='CHANIS0_5TWM' id='CHANIS0_5TWM' value='"+CHANIS0_5TWM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5TW');autosum('CHANIS0_5TWF@CHANIS0_5TWM','CHANIS0_5TW');\"  name='CHANIS0_5TW' id='CHANIS0_5TW' value='"+CHANIS0_5TW+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                   
            
                  
                  
            + " <tr> <td >7</td><td rowspan='6' > 6-23 months </td> <td>Normal Weight for Age </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23NormalweightF');\"  name='CHANIS6_23NormalweightF' id='CHANIS6_23NormalweightF' value='"+CHANIS6_23NormalweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23NormalweightM');autosum('CHANIS6_23NormalweightF@CHANIS6_23NormalweightM','CHANIS6_23NormalweightT');\"  name='CHANIS6_23NormalweightM' id='CHANIS6_23NormalweightM' value='"+CHANIS6_23NormalweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23NormalweightT');autosum('CHANIS6_23NormalweightF@CHANIS6_23NormalweightM','CHANIS6_23NormalweightT');\"  name='CHANIS6_23NormalweightT' id='CHANIS6_23NormalweightT' value='"+CHANIS6_23NormalweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                  
            + " <tr> <td >8</td><td>Underweight </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23UnderweightF');\"  name='CHANIS6_23UnderweightF' id='CHANIS6_23UnderweightF' value='"+CHANIS6_23UnderweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23UnderweightM');autosum('CHANIS6_23UnderweightF@CHANIS6_23UnderweightM','CHANIS6_23UnderweightT');\"  name='CHANIS6_23UnderweightM' id='CHANIS6_23UnderweightM' value='"+CHANIS6_23UnderweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23UnderweightT');autosum('CHANIS6_23UnderweightF@CHANIS6_23UnderweightM','CHANIS6_23UnderweightT');\"  name='CHANIS6_23UnderweightT' id='CHANIS6_23UnderweightT' value='"+CHANIS6_23UnderweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
              
                  
            + " <tr> <td >9</td><td>Severe Underweight </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23sevUnderweightF');\"  name='CHANIS6_23sevUnderweightF' id='CHANIS6_23sevUnderweightF' value='"+CHANIS6_23sevUnderweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23sevUnderweightM');autosum('CHANIS6_23sevUnderweightF@CHANIS6_23sevUnderweightM','CHANIS6_23sevUnderweightT');\"  name='CHANIS6_23sevUnderweightM' id='CHANIS6_23sevUnderweightM' value='"+CHANIS6_23sevUnderweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23sevUnderweightT');autosum('CHANIS6_23sevUnderweightF@CHANIS6_23sevUnderweightM','CHANIS6_23sevUnderweightT');\"  name='CHANIS6_23sevUnderweightT' id='CHANIS6_23sevUnderweightT' value='"+CHANIS6_23sevUnderweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >10</td><td>Overweight </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23OverweightF');\"  name='CHANIS6_23OverweightF' id='CHANIS6_23OverweightF' value='"+CHANIS6_23OverweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23OverweightM');autosum('CHANIS6_23OverweightF@CHANIS6_23OverweightM','CHANIS6_23OverweightT');\"  name='CHANIS6_23OverweightM' id='CHANIS6_23OverweightM' value='"+CHANIS6_23OverweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23OverweightT');autosum('CHANIS6_23OverweightF@CHANIS6_23OverweightM','CHANIS6_23OverweightT');\"  name='CHANIS6_23OverweightT' id='CHANIS6_23OverweightT' value='"+CHANIS6_23OverweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >11</td><td>Obese </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23ObeseF');\"  name='CHANIS6_23ObeseF' id='CHANIS6_23ObeseF' value='"+CHANIS6_23ObeseF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23ObeseM');autosum('CHANIS6_23ObeseF@CHANIS6_23ObeseM','CHANIS6_23ObeseT');\"  name='CHANIS6_23ObeseM' id='CHANIS6_23ObeseM' value='"+CHANIS6_23ObeseM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23ObeseT');autosum('CHANIS6_23ObeseF@CHANIS6_23ObeseM','CHANIS6_23ObeseT');\"  name='CHANIS6_23ObeseT' id='CHANIS6_23ObeseT' value='"+CHANIS6_23ObeseT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >12</td><td>Total Weighed </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23TWF');\"  name='CHANIS6_23TWF' id='CHANIS6_23TWF' value='"+CHANIS6_23TWF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23TWM');autosum('CHANIS6_23TWF@CHANIS6_23TWM','CHANIS6_23TWT');\"  name='CHANIS6_23TWM' id='CHANIS6_23TWM' value='"+CHANIS6_23TWM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23TW');autosum('CHANIS6_23TWF@CHANIS6_23TWM','CHANIS6_23TW');\"  name='CHANIS6_23TW' id='CHANIS6_23TW' value='"+CHANIS6_23TW+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                   
                   
            + " <tr> <td >13</td><td rowspan='6' > 24-59 months </td> <td>Normal Weight for Age </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59NormalweightF');\"  name='CHANIS24_59NormalweightF' id='CHANIS24_59NormalweightF' value='"+CHANIS24_59NormalweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59NormalweightM');autosum('CHANIS24_59NormalweightF@CHANIS24_59NormalweightM','CHANIS24_59NormalweightT');\"  name='CHANIS24_59NormalweightM' id='CHANIS24_59NormalweightM' value='"+CHANIS24_59NormalweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59NormalweightT');autosum('CHANIS24_59NormalweightF@CHANIS24_59NormalweightM','CHANIS24_59NormalweightT');\"  name='CHANIS24_59NormalweightT' id='CHANIS24_59NormalweightT' value='"+CHANIS24_59NormalweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                  
            + " <tr> <td >14</td><td>Underweight </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59UnderweightF');\"  name='CHANIS24_59UnderweightF' id='CHANIS24_59UnderweightF' value='"+CHANIS24_59UnderweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59UnderweightM');autosum('CHANIS24_59UnderweightF@CHANIS24_59UnderweightM','CHANIS24_59UnderweightT');\"  name='CHANIS24_59UnderweightM' id='CHANIS24_59UnderweightM' value='"+CHANIS24_59UnderweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59UnderweightT');autosum('CHANIS24_59UnderweightF@CHANIS24_59UnderweightM','CHANIS24_59UnderweightT');\"  name='CHANIS24_59UnderweightT' id='CHANIS24_59UnderweightT' value='"+CHANIS24_59UnderweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
              
                  
            + " <tr> <td >15</td><td>Severe Underweight </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59sevUnderweightF');\"  name='CHANIS24_59sevUnderweightF' id='CHANIS24_59sevUnderweightF' value='"+CHANIS24_59sevUnderweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59sevUnderweightM');autosum('CHANIS24_59sevUnderweightF@CHANIS24_59sevUnderweightM','CHANIS24_59sevUnderweightT');\"  name='CHANIS24_59sevUnderweightM' id='CHANIS24_59sevUnderweightM' value='"+CHANIS24_59sevUnderweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59sevUnderweightT');autosum('CHANIS24_59sevUnderweightF@CHANIS24_59sevUnderweightM','CHANIS24_59sevUnderweightT');\"  name='CHANIS24_59sevUnderweightT' id='CHANIS24_59sevUnderweightT' value='"+CHANIS24_59sevUnderweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >16</td><td>Overweight </td>"
            + " <td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59OverweightF');\"  name='CHANIS24_59OverweightF' id='CHANIS24_59OverweightF' value='"+CHANIS24_59OverweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + " <td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59OverweightM');autosum('CHANIS24_59OverweightF@CHANIS24_59OverweightM','CHANIS24_59OverweightT');\"  name='CHANIS24_59OverweightM' id='CHANIS24_59OverweightM' value='"+CHANIS24_59OverweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + " <td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59OverweightT');autosum('CHANIS24_59OverweightF@CHANIS24_59OverweightM','CHANIS24_59OverweightT');\"  name='CHANIS24_59OverweightT' id='CHANIS24_59OverweightT' value='"+CHANIS24_59OverweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >17</td><td>Obese </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59ObeseF');\"  name='CHANIS24_59ObeseF' id='CHANIS24_59ObeseF' value='"+CHANIS24_59ObeseF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59ObeseM');autosum('CHANIS24_59ObeseF@CHANIS24_59ObeseM','CHANIS24_59ObeseT');\"  name='CHANIS24_59ObeseM' id='CHANIS24_59ObeseM' value='"+CHANIS24_59ObeseM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59ObeseT');autosum('CHANIS24_59ObeseF@CHANIS24_59ObeseM','CHANIS24_59ObeseT');\"  name='CHANIS24_59ObeseT' id='CHANIS24_59ObeseT' value='"+CHANIS24_59ObeseT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >18</td><td>Total Weighed </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59TWF');\"  name='CHANIS24_59TWF' id='CHANIS24_59TWF' value='"+CHANIS24_59TWF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59TWM');autosum('CHANIS24_59TWF@CHANIS24_59TWM','CHANIS24_59TWT');\"  name='CHANIS24_59TWM' id='CHANIS24_59TWM' value='"+CHANIS24_59TWM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59TW');autosum('CHANIS24_59TWF@CHANIS24_59TWM','CHANIS24_59TW');\"  name='CHANIS24_59TW' id='CHANIS24_59TW' value='"+CHANIS24_59TW+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
           
                  
                  
            
             + " <tr> <td >19</td><td rowspan='6' > MUAC 6-59 months </td> <td>Normal(Green) </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANISMUACNormalF');\"  name='CHANISMUACNormalF' id='CHANISMUACNormalF' value='"+CHANISMUACNormalF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANISMUACNormalM');autosum('CHANISMUACNormalF@CHANISMUACNormalM','CHANISMUACNormalT');\"  name='CHANISMUACNormalM' id='CHANISMUACNormalM' value='"+CHANISMUACNormalM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANISMUACNormalT');autosum('CHANISMUACNormalF@CHANISMUACNormalM','CHANISMUACNormalT');\"  name='CHANISMUACNormalT' id='CHANISMUACNormalT' value='"+CHANISMUACNormalT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                  
            + " <tr> <td >20</td><td> Moderate (Yellow) </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANISMUACModerateF');\"  name='CHANISMUACModerateF' id='CHANISMUACModerateF' value='"+CHANISMUACModerateF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANISMUACModerateM');autosum('CHANISMUACModerateF@CHANISMUACModerateM','CHANISMUACModerateT');\"  name='CHANISMUACModerateM' id='CHANISMUACModerateM' value='"+CHANISMUACModerateM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANISMUACModerateT');autosum('CHANISMUACModerateF@CHANISMUACModerateM','CHANISMUACModerateT');\"  name='CHANISMUACModerateT' id='CHANISMUACModerateT' value='"+CHANISMUACModerateT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
              
                  
            + " <tr> <td >21</td><td> Severe (Red) </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANISMUACSevereF');\"  name='CHANISMUACSevereF' id='CHANISMUACSevereF' value='"+CHANISMUACSevereF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANISMUACSevereM');autosum('CHANISMUACSevereF@CHANISMUACSevereM','CHANISMUACSevereT');\"  name='CHANISMUACSevereM' id='CHANISMUACSevereM' value='"+CHANISMUACSevereM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANISMUACSevereT');autosum('CHANISMUACSevereF@CHANISMUACSevereM','CHANISMUACSevereT');\"  name='CHANISMUACSevereT' id='CHANISMUACSevereT' value='"+CHANISMUACSevereT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
           
                  
            + " <tr> <td > 22</td><td>Total Measured </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANISMUACMeasuredF');\"  name='CHANISMUACMeasuredF' id='CHANISMUACMeasuredF' value='"+CHANISMUACMeasuredF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANISMUACMeasuredM');autosum('CHANISMUACMeasuredF@CHANISMUACMeasuredM','CHANISMUACMeasuredT');\"  name='CHANISMUACMeasuredM' id='CHANISMUACMeasuredM' value='"+CHANISMUACMeasuredM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANISMUACMeasuredT');autosum('CHANISMUACMeasuredF@CHANISMUACMeasuredM','CHANISMUACMeasuredT');\"  name='CHANISMUACMeasuredT' id='CHANISMUACMeasuredT' value='"+CHANISMUACMeasuredT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                 
          + " </table> </fieldset> "; 
          
          
          
          
          String  chanisheight_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'>F. Child Health and Nutrition Information System(CHANIS)</b></legend> "+
             "<table frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + " <tr> <td colspan='3' class='form-actions'><b>Height for Age</b></td><td   class='form-actions'> <b>F</b></td><td   class=\"form-actions\"> <b>M  </b></td><td   class=\"form-actions\"> <b>Total  </b></td></tr>"  
           
            + "<tr> <td >23</td><td rowspan='4' style=' vertical-align:top;' > 0-< 6 months </td> <td>Normal Height for Age </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5NormalHeightF');\"  name='CHANIS0_5NormalHeightF' id='CHANIS0_5NormalHeightF' value='"+CHANIS0_5NormalHeightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5NormalHeightM');autosum('CHANIS0_5NormalHeightF@CHANIS0_5NormalHeightM','CHANIS0_5NormalHeightT');\"  name='CHANIS0_5NormalHeightM' id='CHANIS0_5NormalHeightM' value='"+CHANIS0_5NormalHeightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5NormalHeightT');autosum('CHANIS0_5NormalHeightF@CHANIS0_5NormalHeightM','CHANIS0_5NormalHeightT');\"  name='CHANIS0_5NormalHeightT' id='CHANIS0_5NormalHeightT' value='"+CHANIS0_5NormalHeightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
             
          
            + " <tr> <td >24</td><td>Stunted </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5StuntedF');\"  name='CHANIS0_5StuntedF' id='CHANIS0_5StuntedF' value='"+CHANIS0_5StuntedF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5StuntedM');autosum('CHANIS0_5StuntedF@CHANIS0_5StuntedM','CHANIS0_5StuntedT');\"  name='CHANIS0_5StuntedM' id='CHANIS0_5StuntedM' value='"+CHANIS0_5StuntedM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5StuntedT');autosum('CHANIS0_5StuntedF@CHANIS0_5StuntedM','CHANIS0_5StuntedT');\"  name='CHANIS0_5StuntedT' id='CHANIS0_5StuntedT' value='"+CHANIS0_5StuntedT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >25</td><td>Severely Stunted </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5sevStuntedF');\"  name='CHANIS0_5sevStuntedF' id='CHANIS0_5sevStuntedF' value='"+CHANIS0_5sevStuntedF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5sevStuntedM');autosum('CHANIS0_5sevStuntedF@CHANIS0_5sevStuntedM','CHANIS0_5sevStuntedT');\"  name='CHANIS0_5sevStuntedM' id='CHANIS0_5sevStuntedM' value='"+CHANIS0_5sevStuntedM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5sevStuntedT');autosum('CHANIS0_5sevStuntedF@CHANIS0_5sevStuntedM','CHANIS0_5sevStuntedT');\"  name='CHANIS0_5sevStuntedT' id='CHANIS0_5sevStuntedT' value='"+CHANIS0_5sevStuntedT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >26</td><td>Total Measured </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5TMeasF');\"  name='CHANIS0_5TMeasF' id='CHANIS0_5TMeasF' value='"+CHANIS0_5TMeasF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5TMeasM');autosum('CHANIS0_5TMeasF@CHANIS0_5TMeasM','CHANIS0_5TMeasT');\"  name='CHANIS0_5TMeasM' id='CHANIS0_5TMeasM' value='"+CHANIS0_5TMeasM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5TMeas');autosum('CHANIS0_5TMeasF@CHANIS0_5TMeasM','CHANIS0_5TMeas');\"  name='CHANIS0_5TMeas' id='CHANIS0_5TMeas' value='"+CHANIS0_5TMeas+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                    
            + "<tr> <td >27</td><td rowspan='4' style=' vertical-align:top;' > 6-23 months </td> <td>Normal Height for Age </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23NormalHeightF');\"  name='CHANIS6_23NormalHeightF' id='CHANIS6_23NormalHeightF' value='"+CHANIS6_23NormalHeightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23NormalHeightM');autosum('CHANIS6_23NormalHeightF@CHANIS6_23NormalHeightM','CHANIS6_23NormalHeightT');\"  name='CHANIS6_23NormalHeightM' id='CHANIS6_23NormalHeightM' value='"+CHANIS6_23NormalHeightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23NormalHeightT');autosum('CHANIS6_23NormalHeightF@CHANIS6_23NormalHeightM','CHANIS6_23NormalHeightT');\"  name='CHANIS6_23NormalHeightT' id='CHANIS6_23NormalHeightT' value='"+CHANIS6_23NormalHeightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
             
          
            + " <tr> <td >28</td><td>Stunted </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23StuntedF');\"  name='CHANIS6_23StuntedF' id='CHANIS6_23StuntedF' value='"+CHANIS6_23StuntedF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23StuntedM');autosum('CHANIS6_23StuntedF@CHANIS6_23StuntedM','CHANIS6_23StuntedT');\"  name='CHANIS6_23StuntedM' id='CHANIS6_23StuntedM' value='"+CHANIS6_23StuntedM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23StuntedT');autosum('CHANIS6_23StuntedF@CHANIS6_23StuntedM','CHANIS6_23StuntedT');\"  name='CHANIS6_23StuntedT' id='CHANIS6_23StuntedT' value='"+CHANIS6_23StuntedT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >29</td><td>Severely Stunted </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23sevStuntedF');\"  name='CHANIS6_23sevStuntedF' id='CHANIS6_23sevStuntedF' value='"+CHANIS6_23sevStuntedF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23sevStuntedM');autosum('CHANIS6_23sevStuntedF@CHANIS6_23sevStuntedM','CHANIS6_23sevStuntedT');\"  name='CHANIS6_23sevStuntedM' id='CHANIS6_23sevStuntedM' value='"+CHANIS6_23sevStuntedM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23sevStuntedT');autosum('CHANIS6_23sevStuntedF@CHANIS6_23sevStuntedM','CHANIS6_23sevStuntedT');\"  name='CHANIS6_23sevStuntedT' id='CHANIS6_23sevStuntedT' value='"+CHANIS6_23sevStuntedT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >30</td><td>Total Measured </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23TMeasF');\"  name='CHANIS6_23TMeasF' id='CHANIS6_23TMeasF' value='"+CHANIS6_23TMeasF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23TMeasM');autosum('CHANIS6_23TMeasF@CHANIS6_23TMeasM','CHANIS6_23TMeasT');\"  name='CHANIS6_23TMeasM' id='CHANIS6_23TMeasM' value='"+CHANIS6_23TMeasM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23TMeas');autosum('CHANIS6_23TMeasF@CHANIS6_23TMeasM','CHANIS6_23TMeas');\"  name='CHANIS6_23TMeas' id='CHANIS6_23TMeas' value='"+CHANIS6_23TMeas+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                    
              + "<tr> <td >31</td><td rowspan='4' style=' vertical-align:top;' > 24-59 months </td> <td>Normal Height for Age </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59NormalHeightF');\"  name='CHANIS24_59NormalHeightF' id='CHANIS24_59NormalHeightF' value='"+CHANIS24_59NormalHeightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59NormalHeightM');autosum('CHANIS24_59NormalHeightF@CHANIS24_59NormalHeightM','CHANIS24_59NormalHeightT');\"  name='CHANIS24_59NormalHeightM' id='CHANIS24_59NormalHeightM' value='"+CHANIS24_59NormalHeightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59NormalHeightT');autosum('CHANIS24_59NormalHeightF@CHANIS24_59NormalHeightM','CHANIS24_59NormalHeightT');\"  name='CHANIS24_59NormalHeightT' id='CHANIS24_59NormalHeightT' value='"+CHANIS24_59NormalHeightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
             
          
            + " <tr> <td >32</td><td>Stunted </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59StuntedF');\"  name='CHANIS24_59StuntedF' id='CHANIS24_59StuntedF' value='"+CHANIS24_59StuntedF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59StuntedM');autosum('CHANIS24_59StuntedF@CHANIS24_59StuntedM','CHANIS24_59StuntedT');\"  name='CHANIS24_59StuntedM' id='CHANIS24_59StuntedM' value='"+CHANIS24_59StuntedM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59StuntedT');autosum('CHANIS24_59StuntedF@CHANIS24_59StuntedM','CHANIS24_59StuntedT');\"  name='CHANIS24_59StuntedT' id='CHANIS24_59StuntedT' value='"+CHANIS24_59StuntedT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >33</td><td>Severely Stunted </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59sevStuntedF');\"  name='CHANIS24_59sevStuntedF' id='CHANIS24_59sevStuntedF' value='"+CHANIS24_59sevStuntedF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59sevStuntedM');autosum('CHANIS24_59sevStuntedF@CHANIS24_59sevStuntedM','CHANIS24_59sevStuntedT');\"  name='CHANIS24_59sevStuntedM' id='CHANIS24_59sevStuntedM' value='"+CHANIS24_59sevStuntedM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59sevStuntedT');autosum('CHANIS24_59sevStuntedF@CHANIS24_59sevStuntedM','CHANIS24_59sevStuntedT');\"  name='CHANIS24_59sevStuntedT' id='CHANIS24_59sevStuntedT' value='"+CHANIS24_59sevStuntedT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >34</td><td>Total Measured </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59TMeasF');\"  name='CHANIS24_59TMeasF' id='CHANIS24_59TMeasF' value='"+CHANIS24_59TMeasF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59TMeasM');autosum('CHANIS24_59TMeasF@CHANIS24_59TMeasM','CHANIS24_59TMeasT');\"  name='CHANIS24_59TMeasM' id='CHANIS24_59TMeasM' value='"+CHANIS24_59TMeasM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59TMeas');autosum('CHANIS24_59TMeasF@CHANIS24_59TMeasM','CHANIS24_59TMeas');\"  name='CHANIS24_59TMeas' id='CHANIS24_59TMeas' value='"+CHANIS24_59TMeas+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
            +"<tr><td colspan='3' class='form-actions'><b>OTHER</b></td><td class='form-actions'><b>F</b></td><td class='form-actions'><b>M</b></td><td class='form-actions'><b>TOTAL</b></td></tr>"          
            
                  
            + " <tr> <td >35</td><td rowspan='5' > 0-59 months </td> <td> New Visits </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59NewVisitsF');\"  name='CHANIS0_59NewVisitsF' id='CHANIS0_59NewVisitsF' value='"+CHANIS0_59NewVisitsF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59NewVisitsM');autosum('CHANIS0_59NewVisitsF@CHANIS0_59NewVisitsM','CHANIS0_59NewVisitsT');\"  name='CHANIS0_59NewVisitsM' id='CHANIS0_59NewVisitsM' value='"+CHANIS0_59NewVisitsM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_59NewVisitsT');autosum('CHANIS0_59NewVisitsF@CHANIS0_59NewVisitsM','CHANIS0_59NewVisitsT');\"  name='CHANIS0_59NewVisitsT' id='CHANIS0_59NewVisitsT' value='"+CHANIS0_59NewVisitsT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                  
            + " <tr> <td >36</td><td>Kwashiakor </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59KwashiakorF');\"  name='CHANIS0_59KwashiakorF' id='CHANIS0_59KwashiakorF' value='"+CHANIS0_59KwashiakorF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59KwashiakorM');autosum('CHANIS0_59KwashiakorF@CHANIS0_59KwashiakorM','CHANIS0_59KwashiakorT');\"  name='CHANIS0_59KwashiakorM' id='CHANIS0_59KwashiakorM' value='"+CHANIS0_59KwashiakorM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_59KwashiakorT');autosum('CHANIS0_59KwashiakorF@CHANIS0_59KwashiakorM','CHANIS0_59KwashiakorT');\"  name='CHANIS0_59KwashiakorT' id='CHANIS0_59KwashiakorT' value='"+CHANIS0_59KwashiakorT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
              
                  
            + " <tr> <td >37</td><td>Marasmus</td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59MarasmusF');\"  name='CHANIS0_59MarasmusF' id='CHANIS0_59MarasmusF' value='"+CHANIS0_59MarasmusF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59MarasmusM');autosum('CHANIS0_59MarasmusF@CHANIS0_59MarasmusM','CHANIS0_59MarasmusT');\"  name='CHANIS0_59MarasmusM' id='CHANIS0_59MarasmusM' value='"+CHANIS0_59MarasmusM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_59MarasmusT');autosum('CHANIS0_59MarasmusF@CHANIS0_59MarasmusM','CHANIS0_59MarasmusT');\"  name='CHANIS0_59MarasmusT' id='CHANIS0_59MarasmusT' value='"+CHANIS0_59MarasmusT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >38</td><td>Faltering growth </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59FalgrowthF');\"  name='CHANIS0_59FalgrowthF' id='CHANIS0_59FalgrowthF' value='"+CHANIS0_59FalgrowthF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59FalgrowthM');autosum('CHANIS0_59FalgrowthF@CHANIS0_59FalgrowthM','CHANIS0_59FalgrowthT');\"  name='CHANIS0_59FalgrowthM' id='CHANIS0_59FalgrowthM' value='"+CHANIS0_59FalgrowthM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_59FalgrowthT');autosum('CHANIS0_59FalgrowthF@CHANIS0_59FalgrowthM','CHANIS0_59FalgrowthT');\"  name='CHANIS0_59FalgrowthT' id='CHANIS0_59FalgrowthT' value='"+CHANIS0_59FalgrowthT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >39</td><td>Total </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59F');\"  name='CHANIS0_59F' id='CHANIS0_59F' value='"+CHANIS0_59F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59M');autosum('CHANIS0_59F@CHANIS0_59M','CHANIS0_59T');\"  name='CHANIS0_59M' id='CHANIS0_59M' value='"+CHANIS0_59M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_59T');autosum('CHANIS0_59F@CHANIS0_59M','CHANIS0_59T');\"  name='CHANIS0_59T' id='CHANIS0_59T' value='"+CHANIS0_59T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
           + " <tr> <td >40</td><td>0 <-6 months </td><td>Exclusive breast feeding </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5EXCLBreastF');\"  name='CHANIS0_5EXCLBreastF' id='CHANIS0_5EXCLBreastF' value='"+CHANIS0_5EXCLBreastF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5EXCLBreastM');autosum('CHANIS0_5EXCLBreastF@CHANIS0_5EXCLBreastM','CHANIS0_5EXCLBreastT');\"  name='CHANIS0_5EXCLBreastM' id='CHANIS0_5EXCLBreastM' value='"+CHANIS0_5EXCLBreastM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5EXCLBreastT');autosum('CHANIS0_5EXCLBreastF@CHANIS0_5EXCLBreastM','CHANIS0_5EXCLBreastT');\"  name='CHANIS0_5EXCLBreastT' id='CHANIS0_5EXCLBreastT' value='"+CHANIS0_5EXCLBreastT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                   
            + " <tr> <td >41</td><td>12-59 months </td><td>Dewormed </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS12_59DewormedF');\"  name='CHANIS12_59DewormedF' id='CHANIS12_59DewormedF' value='"+CHANIS12_59DewormedF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS12_59DewormedM');autosum('CHANIS12_59DewormedF@CHANIS12_59DewormedM','CHANIS12_59DewormedT');\"  name='CHANIS12_59DewormedM' id='CHANIS12_59DewormedM' value='"+CHANIS12_59DewormedM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS12_59DewormedT');autosum('CHANIS12_59DewormedF@CHANIS12_59DewormedM','CHANIS12_59DewormedT');\"  name='CHANIS12_59DewormedT' id='CHANIS12_59DewormedT' value='"+CHANIS12_59DewormedT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                    
          + " <tr> <td >42</td><td>6-23 months </td><td>MNPs Supplementation </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23MNPsF');\"  name='CHANIS6_23MNPsF' id='CHANIS6_23MNPsF' value='"+CHANIS6_23MNPsF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23MNPsM');autosum('CHANIS6_23MNPsF@CHANIS6_23MNPsM','CHANIS6_23MNPsT');\"  name='CHANIS6_23MNPsM' id='CHANIS6_23MNPsM' value='"+CHANIS6_23MNPsM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23MNPsT');autosum('CHANIS6_23MNPsF@CHANIS6_23MNPsM','CHANIS6_23MNPsT');\"  name='CHANIS6_23MNPsT' id='CHANIS6_23MNPsT' value='"+CHANIS6_23MNPsT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                    
           + " <tr> <td >43</td><td>0-59 months </td><td>Any Disability </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59DisabilityF');\"  name='CHANIS0_59DisabilityF' id='CHANIS0_59DisabilityF' value='"+CHANIS0_59DisabilityF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59DisabilityM');autosum('CHANIS0_59DisabilityF@CHANIS0_59DisabilityM','CHANIS0_59DisabilityT');\"  name='CHANIS0_59DisabilityM' id='CHANIS0_59DisabilityM' value='"+CHANIS0_59DisabilityM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_59DisabilityT');autosum('CHANIS0_59DisabilityF@CHANIS0_59DisabilityM','CHANIS0_59DisabilityT');\"  name='CHANIS0_59DisabilityT' id='CHANIS0_59DisabilityT' value='"+CHANIS0_59DisabilityT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                     
          + " </table> </fieldset> ";   
          
       
String CCSVVH24="1";
String CCSVVH25_49="2";
String CCSVVH50="3";
String CCSPAPSMEAR24="4";
String CCSPAPSMEAR25_49="5";
String CCSPAPSMEAR50="6";
String CCSHPV24="7";
String CCSHPV25_49="8";
String CCSHPV50="9";
String CCSVIAVILIPOS24="10";
String CCSVIAVILIPOS25_49="11";
String CCSVIAVILIPOS50="12";

String CCSCYTOLPOS24="13";
String CCSCYTOLPOS25_49="14";
String CCSCYTOLPOS50="15";

String CCSSUSPICIOUSLES24="13";
String CCSSUSPICIOUSLES25_49="14";
String CCSSUSPICIOUSLES50="15";

String CCSHPVPOS24="16";
String CCSHPVPOS25_49="17";
String CCSHPVPOS50="18";
String CCSCryotherapy24="19";
String CCSCryotherapy25_49="20";
String CCSCryotherapy50="21";
String CCSLEEP24="22";
String CCSLEEP25_49="23";
String CCSLEEP50="24";
String CCSHIVPOSSCREENED24="25";
String CCSHIVPOSSCREENED25_49="26";
String CCSHIVPOSSCREENED50="27";

          
          
            String  ccs_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'>G. Cervical cancer screening</b></legend> "+
             "<table frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + " <tr> <td colspan='2' class='form-actions'><b></b></td><td   class='form-actions'> <b> < 25yrs</b></td><td   class=\"form-actions\"> <b>25-49yrs </b></td><td   class=\"form-actions\"> <b>50+yrs </b></td></tr>"  
             
            + " <tr> <td >1</td><td>No. of Client receiving VIA /VILI /HPV VILI / HPV </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSVVH24');\"  name='CCSVVH24' id='CCSVVH24' value='"+CCSVVH24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSVVH25_49');\"  name='CCSVVH25_49' id='CCSVVH25_49' value='"+CCSVVH25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSVVH50');\"  name='CCSVVH50' id='CCSVVH50' value='"+CCSVVH50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
           
                    + " <tr> <td >2</td><td>No.Screened for Pap smear </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSPAPSMEAR24');\"  name='CCSPAPSMEAR24' id='CCSPAPSMEAR24' value='"+CCSPAPSMEAR24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSPAPSMEAR25_49');\"  name='CCSPAPSMEAR25_49' id='CCSPAPSMEAR25_49' value='"+CCSPAPSMEAR25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSPAPSMEAR50');\"  name='CCSPAPSMEAR50' id='CCSPAPSMEAR50' value='"+CCSPAPSMEAR50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
             
                    + " <tr> <td >3</td><td>No.Screened for HPV test </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHPV24');\"  name='CCSHPV24' id='CCSHPV24' value='"+CCSHPV24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHPV25_49');\"  name='CCSHPV25_49' id='CCSHPV25_49' value='"+CCSHPV25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHPV50');\"  name='CCSHPV50' id='CCSHPV50' value='"+CCSHPV50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
             
            + " <tr> <td >4</td><td>Number of clients with Positive VIA/VILI result  </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSVIAVILIPOS24');\"  name='CCSVIAVILIPOS24' id='CCSVIAVILIPOS24' value='"+CCSVIAVILIPOS24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSVIAVILIPOS25_49');\"  name='CCSVIAVILIPOS25_49' id='CCSVIAVILIPOS25_49' value='"+CCSVIAVILIPOS25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSVIAVILIPOS50');\"  name='CCSVIAVILIPOS50' id='CCSVIAVILIPOS50' value='"+CCSVIAVILIPOS50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                    
            + " <tr> <td >5</td><td>Number of clients with Positive Cytology </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSCYTOLPOS24');\"  name='CCSCYTOLPOS24' id='CCSCYTOLPOS24' value='"+CCSCYTOLPOS24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSCYTOLPOS25_49');\"  name='CCSCYTOLPOS25_49' id='CCSCYTOLPOS25_49' value='"+CCSCYTOLPOS25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSCYTOLPOS50');\"  name='CCSCYTOLPOS50' id='CCSCYTOLPOS50' value='"+CCSCYTOLPOS50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                
            + " <tr> <td >6</td><td>Number of clients with Positive HPV result</td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHPVPOS24');\"  name='CCSHPVPOS24' id='CCSHPVPOS24' value='"+CCSHPVPOS24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHPVPOS25_49');\"  name='CCSHPVPOS25_49' id='CCSHPVPOS25_49' value='"+CCSHPVPOS25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHPVPOS50');\"  name='CCSHPVPOS50' id='CCSHPVPOS50' value='"+CCSHPVPOS50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                       
            + " <tr> <td >7</td><td>Number of clients with suspicious cancer lesions</td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSSUSPICIOUSLES24');\"  name='CCSSUSPICIOUSLES24' id='CCSSUSPICIOUSLES24' value='"+CCSSUSPICIOUSLES24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSSUSPICIOUSLES25_49');\"  name='CCSSUSPICIOUSLES25_49' id='CCSSUSPICIOUSLES25_49' value='"+CCSSUSPICIOUSLES25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSSUSPICIOUSLES50');\"  name='CCSSUSPICIOUSLES50' id='CCSSUSPICIOUSLES50' value='"+CCSSUSPICIOUSLES50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                       
             + " <tr> <td >8</td><td>Number of clients treated using Cryotherapy</td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSCryotherapy24');\"  name='CCSCryotherapy24' id='CCSCryotherapy24' value='"+CCSCryotherapy24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSCryotherapy25_49');\"  name='CCSCryotherapy25_49' id='CCSCryotherapy25_49' value='"+CCSCryotherapy25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSCryotherapy50');\"  name='CCSCryotherapy50' id='CCSCryotherapy50' value='"+CCSCryotherapy50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
           
             + " <tr> <td >9</td><td>Number of clients treated using LEEP</td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSLEEP24');\"  name='CCSLEEP24' id='CCSLEEP24' value='"+CCSLEEP24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSLEEP25_49');\"  name='CCSLEEP25_49' id='CCSLEEP25_49' value='"+CCSLEEP25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSLEEP50');\"  name='CCSLEEP50' id='CCSLEEP50' value='"+CCSLEEP50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
           
            + " <tr> <td >10</td><td>Number of HIV positive clients screened</td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHIVPOSSCREENED24');\"  name='CCSHIVPOSSCREENED24' id='CCSHIVPOSSCREENED24' value='"+CCSHIVPOSSCREENED24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHIVPOSSCREENED25_49');\"  name='CCSHIVPOSSCREENED25_49' id='CCSHIVPOSSCREENED25_49' value='"+CCSHIVPOSSCREENED25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHIVPOSSCREENED50');\"  name='CCSHIVPOSSCREENED50' id='CCSHIVPOSSCREENED50' value='"+CCSHIVPOSSCREENED50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                      
          + " </table> </fieldset> ";
       
String PNCBreastExam="1";
String PNCCounselled="2";
String PNCFistula="3";
String PNCExerNegative="4";
String PNCExerPositive="5";
String PNCCCSsuspect="6";
String PNCmotherspostpartum2_3="7";
String PNCmotherspostpartum6="8";
String PNCinfantspostpartum2_3="9";
String PNCinfantspostpartum6="10";
String PNCreferralsfromotherHF="11";
String PNCreferralsfromotherCU="12";
String PNCreferralsTootherHF="13";
String PNCreferralsTootherCU="14";

            
            
     String  pnc_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'>H. Post Natal Care (PNC)</b></legend> "+
             "<table frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + " <tr> <td colspan='3' class='form-actions'><b></b></td><td   class=\"form-actions\"> <b>Total </b></td></tr>"  
            
             + " <tr> <td >1</td><td colspan='2'>Breast Exam</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCBreastExam');\"  name='PNCBreastExam' id='PNCBreastExam' value='"+PNCBreastExam+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
             + " <tr> <td >2</td><td colspan='2'>No of Women counselled</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCCounselled');\"  name='PNCCounselled' id='PNCCounselled' value='"+PNCCounselled+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
               
            + " <tr> <td >3</td><td colspan='2'>Number of Cases of Fistula</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCFistula');\"  name='PNCFistula' id='PNCFistula' value='"+PNCFistula+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
             + " <tr> <td >4</td><td rowspan='2' >PNC Given Exercise</td><td >Negative</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCExerNegative');\"  name='PNCExerNegative' id='PNCExerNegative' value='"+PNCExerNegative+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
               
             + " <tr> <td >5</td><td >Positive</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCExerPositive');\"  name='PNCExerPositive' id='PNCExerPositive' value='"+PNCExerPositive+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
              + " <tr> <td >6</td><td  >Cervical cancer screening </td><td >Suspect Cancer</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCCCSsuspect');\"  name='PNCCCSsuspect' id='PNCCCSsuspect' value='"+PNCCCSsuspect+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
             
             
             + " <tr> <td >7</td><td rowspan='2' >No. of Mothers received Postpartum Care</td><td >within 2-3 days</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCmotherspostpartum2_3');\"  name='PNCmotherspostpartum2_3' id='PNCmotherspostpartum2_3' value='"+PNCmotherspostpartum2_3+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
               
             + " <tr> <td >8</td><td > 6 days</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCmotherspostpartum6');\"  name='PNCmotherspostpartum6' id='PNCmotherspostpartum6' value='"+PNCmotherspostpartum6+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
             + " <tr> <td >9</td><td rowspan='2' >No. of Infants received Postpartum Care </td><td >within 2-3 days</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCinfantspostpartum2_3');\"  name='PNCinfantspostpartum2_3' id='PNCinfantspostpartum2_3' value='"+PNCinfantspostpartum2_3+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
               
             + " <tr> <td >10</td><td > 6 days</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCinfantspostpartum6');\"  name='PNCinfantspostpartum6' id='PNCinfantspostpartum6' value='"+PNCinfantspostpartum6+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
            
             + " <tr> <td >11</td><td rowspan='4' >No. of Referrals </td><td > From Other Health Facility</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCreferralsfromotherHF');\"  name='PNCreferralsfromotherHF' id='PNCreferralsfromotherHF' value='"+PNCreferralsfromotherHF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
               
             + " <tr> <td >12</td><td > From Community Unit</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCreferralsfromotherCU');\"  name='PNCreferralsfromotherCU' id='PNCreferralsfromotherCU' value='"+PNCreferralsfromotherCU+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
             + " <tr> <td >13</td><td > To Other Health Facility</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCreferralsTootherHF');\"  name='PNCreferralsTootherHF' id='PNCreferralsTootherHF' value='"+PNCreferralsTootherHF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
             + " <tr> <td >14</td><td > To Community Unit</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCreferralsTootherCU');\"  name='PNCreferralsTootherCU' id='PNCreferralsTootherCU' value='"+PNCreferralsTootherCU+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
             
          + " </table> </fieldset> ";
     
     
String RsAssessed="1";
String Rstreated="2";
String RsRehabilitated="3";
String Rsreffered="4";
String RsIntergrated="5";

     
     
          String  rs_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'>I. Rehabilitation Services</b></legend> "+
             "<table frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + " <tr> <td colspan='2' class='form-actions'><b></b></td><td   class=\"form-actions\"> <b>Total </b></td></tr>"  
           
            + " <tr> <td >1</td><td > Number Assessed</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('RsAssessed');\"  name='RsAssessed' id='RsAssessed' value='"+RsAssessed+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
           
                   + " <tr> <td >2</td><td > Number Assessed</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('Rstreated');\"  name='Rstreated' id='Rstreated' value='"+Rstreated+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
              
                    + " <tr> <td >3</td><td >Number Rehabilitated</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('RsRehabilitated');\"  name='RsRehabilitated' id='RsRehabilitated' value='"+RsRehabilitated+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                      
             + " <tr> <td >4</td><td >Number Referred for further Interventions</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('Rsreffered');\"  name='Rsreffered' id='Rsreffered' value='"+Rsreffered+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                      
             + " <tr> <td >5</td><td >Number Integrated to Communities</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('RsIntergrated');\"  name='RsIntergrated' id='RsIntergrated' value='"+RsIntergrated+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                      
          + " </table> </fieldset> "; 
          
       
          
String MSWpscounselling="1";
String MSWdrugabuse="2";
String MSWMental="3";
String MSWAdolescent="4";
String MSWPsAsses="5";
String MSWsocialinv="6";
String MSWsocialRehab="7";
String MSWoutreach="8";
String MSWreferrals="9";
String MSWwaivedpatients="10";

          
          
          
          String  msw_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'>J. Medical Social Work</b></legend> "+
             "<table frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + " <tr> <td colspan='2' class='form-actions'><b></b></td><td   class=\"form-actions\"> <b>Total </b></td></tr>"  
            
            + " <tr> <td >1</td><td > Psycho-Social Counselling</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWpscounselling');\"  name='MSWpscounselling' id='MSWpscounselling' value='"+MSWpscounselling+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
           
            + " <tr> <td >2</td><td > Alcohol and Drug Abuse</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWdrugabuse');\"  name='MSWdrugabuse' id='MSWpscounselling' value='"+MSWdrugabuse+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
            + " <tr> <td >3</td><td > Mental Illness</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWMental');\"  name='MSWdrugabuse' id='MSWMental' value='"+MSWMental+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
             + " <tr> <td >4</td><td > Adolescent Issues</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWAdolescent');\"  name='MSWAdolescent' id='MSWAdolescent' value='"+MSWAdolescent+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
             + " <tr> <td >5</td><td > Psycho-Social Assessments (psycho, social and economic)</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWPsAsses');\"  name='MSWPsAsses' id='MSWPsAsses' value='"+MSWPsAsses+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
             + " <tr> <td >6</td><td > Social investigations  (Home visits / Follow ups)</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWsocialinv');\"  name='MSWsocialinv' id='MSWsocialinv' value='"+MSWsocialinv+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
             + " <tr> <td >7</td><td >Social Rehabilitation</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWsocialRehab');\"  name='MSWsocialRehab' id='MSWsocialRehab' value='"+MSWsocialRehab+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
             + " <tr> <td >8</td><td >Referrals</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWoutreach');\"  name='MSWoutreach' id='MSWoutreach' value='"+MSWoutreach+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
             + " <tr> <td >9</td><td >Outreach Services / Health Talks</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWreferrals');\"  name='MSWreferrals' id='MSWreferrals' value='"+MSWreferrals+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
             + " <tr> <td >10</td><td >Number of waived patients</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWwaivedpatients');\"  name='MSWwaivedpatients' id='MSWwaivedpatients' value='"+MSWwaivedpatients+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
          + " </table> </fieldset> ";   
          
      
         String PsPWDOPD4="1";
String PsPWDOPD5_19="2";
String PsPWDOPD20="3";
String PsPWDinpatient4="4";
String PsPWDinpatient5_19="5";
String PsPWDinpatient20="6";
String PsTreatments4="7";
String PsTreatments5_19="8";
String PsTreatments20="9";
String PsAssessed4="10";
String PsAssessed5_19="11";
String PsAssessed20="12";
String PsServices4="13";
String PsServices5_19="14";
String PsServices20="15";
String PsANCCounsel5_19="16";
String PsANCCounsel20="17";
String PsExercise5_19="18";
String PsExercise20="19";
String PsFIFcollected5_19="20";
String PsFIFcollected20="21";
String PsFIFwaived5_19="22";
String PsFIFwaived20="23";
String PsFIFexempted4="23";
String PsFIFexempted5_19="24";
String PsFIFexempted20="25";
String PsDiasbilitymeeting4="26";
String PsDiasbilitymeeting5_19="27";
String PsDiasbilitymeeting20="28";
 
String PsotherOPD4="29";
String PsotherOPD5_19="30";
String PsotherOPD20="31";
String Psotherinpatient4="32";
String Psotherinpatient5_19="33";
String Psotherinpatient20="34";


          
          
       String physiotherapy_ext = "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'>K. Physiotherapy Service</b></legend> "+
             "<table frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
             + " <tr> <td colspan='3' class='form-actions'><b></b></td><td   class=\"form-actions\"> <b> < 5yrs </b></td><td   class=\"form-actions\"> <b>5-19yrs </b></td><td   class=\"form-actions\"> <b>20 yrs +</b></td></tr>"  
            
             + " <tr> <td >1</td><td rowspan='2' > Number of PWDs identified and receiving physiotherapy </td><td>OPD</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsPWDOPD4');\"  name='PsPWDOPD4' id='PsPWDOPD4' value='"+PsPWDOPD4+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsPWDOPD5_19');\"  name='PsPWDOPD5_19' id='PsPWDOPD5_19' value='"+PsPWDOPD5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsPWDOPD20');\"  name='PsPWDOPD20' id='PsPWDOPD20' value='"+PsPWDOPD20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>" 
             
               + " <tr> <td >2</td><td>In-Patient</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsPWDinpatient4');\"  name='PsPWDinpatient4' id='PsPWDinpatient4' value='"+PsPWDinpatient4+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsPWDinpatient5_19');\"  name='PsPWDinpatient5_19' id='PsPWDinpatient5_19' value='"+PsPWDinpatient5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsPWDinpatient20');\"  name='PsPWDinpatient20' id='PsPWDinpatient20' value='"+PsPWDinpatient20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>" 
               
             + " <tr> <td >3</td><td rowspan='2' > Number of other clients/patients receiving physiotherapy </td><td>OPD</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsotherOPD4');\"  name='PsotherOPD4' id='PsotherOPD4' value='"+PsotherOPD4+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsotherOPD5_19');\"  name='PsotherOPD5_19' id='PsotherOPD5_19' value='"+PsotherOPD5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsotherOPD20');\"  name='PsotherOPD20' id='PsotherOPD20' value='"+PsotherOPD20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>" 
             
            + " <tr> <td >4</td><td>In-Patient</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('Psotherinpatient4');\"  name='Psotherinpatient4' id='Psotherinpatient4' value='"+Psotherinpatient4+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('Psotherinpatient5_19');\"  name='Psotherinpatient5_19' id='Psotherinpatient5_19' value='"+Psotherinpatient5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('Psotherinpatient20');\"  name='Psotherinpatient20' id='Psotherinpatient20' value='"+Psotherinpatient20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
            + " <tr> <td >5</td><td colspan='2'>Total Number of treatments </td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsTreatments4');\"  name='PsTreatments4' id='PsTreatments4' value='"+PsTreatments4+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsTreatments5_19');\"  name='PsTreatments5_19' id='PsTreatments5_19' value='"+PsTreatments5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsTreatments20');\"  name='PsTreatments20' id='PsTreatments20' value='"+PsTreatments20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
            + " <tr> <td >6</td><td colspan='2'>PWDs assessed for registration </td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsAssessed4');\"  name='PsAssessed4' id='PsAssessed4' value='"+PsAssessed4+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsAssessed5_19');\"  name='PsAssessed5_19' id='PsAssessed5_19' value='"+PsAssessed5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsAssessed20');\"  name='PsAssessed20' id='PsAssessed20' value='"+PsAssessed20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
            + " <tr> <td >7</td><td colspan='2'>Number of clients receiving out/in reach services </td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsServices4');\"  name='PsServices4' id='PsServices4' value='"+PsServices4+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsServices5_19');\"  name='PsServices5_19' id='PsServices5_19' value='"+PsServices5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsServices20');\"  name='PsServices20' id='PsServices20' value='"+PsServices20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
            + " <tr> <td >8</td><td colspan='2'>Number of pregnant women attending ANC receiving counselling </td>"
             + "<td class='form-controls' > </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsANCCounsel5_19');\"  name='PsANCCounsel5_19' id='PsANCCounsel5_19' value='"+PsANCCounsel5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsANCCounsel20');\"  name='PsANCCounsel20' id='PsANCCounsel20' value='"+PsANCCounsel20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
            + " <tr> <td >9</td><td colspan='2'>WRA receiving prenatal/postnatal exercises </td>"
             + "<td class='form-controls'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsExercise5_19');\"  name='PsExercise5_19' id='PsExercise5_19' value='"+PsExercise5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsExercise20');\"  name='PsExercise20' id='PsExercise20' value='"+PsExercise20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
            + " <tr> <td >10</td><td colspan='2'>Amount of FIF collected </td>"
             + "<td class='form-controls'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsFIFcollected5_19');\"  name='PsFIFcollected5_19' id='PsFIFcollected5_19' value='"+PsFIFcollected5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsFIFcollected20');\"  name='PsFIFcollected20' id='PsFIFcollected20' value='"+PsFIFcollected20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
            + " <tr> <td >11</td><td colspan='2'>Amount of FIF waived </td>"
             + "<td class='form-controls'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsFIFwaived5_19');\"  name='PsFIFwaived5_19' id='PsFIFwaived5_19' value='"+PsFIFwaived5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsFIFwaived20');\"  name='PsFIFwaived20' id='PsFIFwaived20' value='"+PsFIFwaived20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
            + " <tr> <td >12</td><td colspan='2'>Amount of FIF exempted </td>"
             + "<td class='form-controls'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsFIFexempted5_19');\"  name='PsFIFexempted5_19' id='PsFIFexempted5_19' value='"+PsFIFexempted5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsFIFexempted20');\"  name='PsFIFexempted20' id='PsFIFexempted20' value='"+PsFIFexempted20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
            + " <tr> <td >13</td><td colspan='2'>Number of Disability committee meetings </td>"
             + "<td class='form-controls'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsDiasbilitymeeting5_19');\"  name='PsDiasbilitymeeting5_19' id='PsDiasbilitymeeting5_19' value='"+PsDiasbilitymeeting5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsDiasbilitymeeting20');\"  name='PsDiasbilitymeeting20' id='PsDiasbilitymeeting20' value='"+PsDiasbilitymeeting20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
             + " </table> </fieldset> ";
       
       
          out.println(physiotherapy_ext);
          %>
                           
                            
                             
                           
                            
                         
                           <div class="form-actions">
                              <button type="submit" class="btn blue">Go to Form</button>
<!--                              <button type="button" class="btn">Cancel</button>-->
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
       <%

              Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);       
%>
       
       &copy; APHIAplus | USAID <%=year%>.
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
         
                    $.ajax({
url:'loadFacilities',
type:'post',
dataType:'html',
success:function (data){
       $("#facility").html(data);
     
       App.init();   
}


}); 
         
         
         $.ajax({
            url:'loadKmmp',
            type:'post',
            dataType:'html',
            success:function (data){
                $("#form").html(data);
                
              //  App.init();   
            }
            
            
        }); 
       
         
//$.ajax({
//    url:'loadYear',
//    type:'post',
//    dataType:'html',
//    success:function (data){
//        // $("#year").html(data);
//        document.getElementById("year").innerHTML=data;
//        
//    }
//    
//    
//}); 
//               
//      });
      
      
      
//      function loadmonths(){
//      
//      var yr=document.getElementById("year").value;
//      
//              $.ajax({
//url:'loadMonth?year='+yr,
//type:'post',
//dataType:'html',
//success:function (data){
//    $("#month").html(data);     
//    
//       //document.getElementById("month").innerHTML=data;
//      // App.init();  
//        
//}
//
//
//});  
//      
//      
//      }
      
   </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>

