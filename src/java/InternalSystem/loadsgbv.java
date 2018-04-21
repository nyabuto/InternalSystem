/*
 * To change this license header= choose License Headers in Project Properties.
 * To change this template file= choose Tools | Templates
 * and open the template in the editor.
 */

package InternalSystem;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class loadsgbv extends HttpServlet {
HttpSession session;//201605
    
 
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
String data = null,id;//201605
String facilityId = null,year = null,month = null;//201605







String isValidated="";//201605
String validity="";//201605
String Userid="";//201605



String SectionA,SectionB; //201605

String subcountyid="";//201605
String invalidSGBVTXT="";
        
 int expectedSGBV=0;

 int validSGBV=0;

 int  invalidSGBV = 0,totalSGBV;
   
            dbConn conn = new dbConn();
       
            String validitychecker="";
            session=request.getSession();
            String SECTIONA_TAB="";
            String SECTION_TAB="";
            
            //define tab variables            
            String sectiona_tab="";//2016
            String sectionb_tab="";//2016
            
               String enterdby="";
               //if the session is still active
               
           if(session.getAttribute("forms_holder")!=null && !(session.getAttribute("forms_holder").toString().equals(","))){
            try {
                //data holds all the results that will be displayed in the interface
                data="";
                if(session.getAttribute("year")!=null){
                    year=session.getAttribute("year").toString();
                }
                if(session.getAttribute("monthid")!=null){
                    month=session.getAttribute("monthid").toString();
                }
                
                if(session.getAttribute("facilityid")!=null){
                    facilityId=session.getAttribute("facilityid").toString();
                }
                if(session.getAttribute("subcountyid")!=null){
                    subcountyid=session.getAttribute("subcountyid").toString();
                }
                id=year+"_"+month+"_"+facilityId;
                     
                invalidSGBVTXT="";
//          id="2015_1_14498";
                
                
                String sectionapane="";
                String sectionbpane="";
                
                
                
                String activeclass="";
                activeclass="";
                String activeclassname="";
                
                int counter=0;
                isValidated="";
                validity="";
                expectedSGBV=0;
                validSGBV=0;
                invalidSGBV=0;
                totalSGBV=0;
                
                String getExpectedForms="SELECT SUM(Gender) FROM subpartnera WHERE subpartnera.DistrictID='"+subcountyid+"'" ;
                conn.rs1=conn.st1.executeQuery(getExpectedForms);
                if(conn.rs1.next()==true){
//       System.out.println("pmtct : "+conn.rs1.getString(1)+"  care : "+conn.rs1.getInt(2)+" pep : "+conn.rs1.getInt(3));
                    expectedSGBV=conn.rs1.getInt(1);
                    
                }
                validSGBV=invalidSGBV=totalSGBV=0;
               
                
                String getEntered=" SELECT sgbv.isValidated,SUM(subpartnera.Gender)  FROM subpartnera JOIN sgbv ON subpartnera.SubPartnerID=sgbv.SubPartnerID WHERE "
                        + "sgbv.Mois='"+month+"' AND sgbv.Annee='"+year+"' AND subpartnera.DistrictID='"+subcountyid+"' GROUP BY sgbv.isValidated";
                conn.rs1=conn.st1.executeQuery(getEntered);
                
                while(conn.rs1.next()){
                    System.out.println("isvalidated : "+conn.rs1.getInt(1)+"  num : "+conn.rs1.getInt(2));
                    if(conn.rs1.getInt(1)==1){
                        validSGBV=conn.rs1.getInt(2);
                    }
                    if(conn.rs1.getInt(1)==0){
                        invalidSGBV=conn.rs1.getInt(2);
                        
                    }
                }
                totalSGBV=validSGBV+invalidSGBV;
                
                
// invalidPMTCTTXT="Unvalidated Form(s) : "+invalidPMTCT;
// invalidCARETXT="Unvalidated Form(s) : "+invalidCARE;
// invalidPEPTXT="Unvalidated Form(s) : "+invalidPEP;
                
                invalidSGBVTXT=" Unvalidated Form(s) : 0";
                
                
                if(invalidSGBV>0){
                    invalidSGBVTXT="<button type=\"button\" class=\"btn btn-primary btn-lg\" data-toggle=\"modal\" style=\"width:auto; height:auto;\" data-target=\"#unvalidatedModal\"> Unvalidated Form(s) : <span class=\"badge badge-important\">"+invalidSGBV+"</span></button>";
                }
                
                
                
                
                String ul="  <ul class=\"nav nav-tabs\">\n" ;
                
                
                
                
                //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                //create a variable that will be used to determine if the new tables creted in new 711 will be active. This will be set to a default of 2(diabled).. to make all new tables active, it should be et to a value of 1
                int newtablesactive=2;
                //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                
                
                
                if(session.getAttribute("forms_holder").toString().contains(",SGBV,"))
                {
                    counter++;
                    
                    if(counter==1)
                    {
                        activeclassname="active";
                        activeclass="class='active'";
                    }
                    
                    sectionapane+=" <li "+activeclass+" ><a class='advance_form_with_chosen_element' href='#tab_1' data-toggle='tab'>SECTION A </a></li>" ;
                    
                    sectiona_tab="<div class='tab-pane "+activeclassname+"' id='tab_1'><div class='portlet box blue'> " +
                            "<div class='portlet-title'><h4 style='margin-left:20%;'>SECTION A</h4><b  style='color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;'>Record Counter:"+validSGBV+" out of "+expectedSGBV+" &nbsp; "+invalidSGBVTXT+"</b> " +
                            "</div><div class='portlet-body form'>";
                    activeclassname="";
                    activeclass="";
                }
                else{sectionapane="";}
                
                
                
                
                //SGBV
                
                
                
                if(session.getAttribute("forms_holder").toString().contains(",SGBV,"))
                {
                    counter++;
                    
                    if(counter==1)
                    {
                        activeclassname="active";
                        activeclass="class='active'";
                    }
                    
                    sectionbpane+=" <li "+activeclass+" ><a class='advance_form_with_chosen_element' href='#tab_2' data-toggle='tab'>SECTION B </a></li>" ;
                    
                    sectionb_tab="<div class='tab-pane "+activeclassname+"' id='tab_2'><div class='portlet box blue'> " +
                            "<div class='portlet-title'><h4 style='margin-left:20%;'>COHORT SUMMARY  &nbsp </h4><b  style='color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;'>Record Counter:"+validSGBV+" out of "+expectedSGBV+" &nbsp; "+invalidSGBVTXT+"</b> " +
                            "</div><div class='portlet-body form'>";
                    activeclassname="";
                    activeclass="";
                }
                else{sectionapane="";}
                
                
                //-----------------------------------------
                
                
                
                String validatepane=" <li style=\"margin-left:150px;\" id=\"isValidated\"></li>                    </ul> </div>\n" ;
                String mainpane="<div class=\"tab-content\" >";
                
                
                
                
                
                
                
                // INITIALIZING VARIABLES
                
                
                String visit1_11_M="";
                String visit2_11_M="";
                String visit3_11_M="";
                String visit4_11_M="";
                String visit5_11_M="";
                String completedpep_11_M="";
                String seroconverted_11_M="";
                String counselling_11_M="";
                String visit1_11_F="";
                String visit2_11_F="";
                String visit3_11_F="";
                String visit4_11_F="";
                String visit5_11_F="";
                String completedpep_11_F="";
                String seroconverted_11_F="";
                String Pregnant_11="";
                String counselling_11_F="";
                String visit1_17_M="";
                String visit2_17_M="";
                String visit3_17_M="";
                String visit4_17_M="";
                String visit5_17_M="";
                String completedpep_17_M="";
                String seroconverted_17_M="";
                String counselling_17_M="";
                String visit1_17_F="";
                String visit2_17_F="";
                String visit3_17_F="";
                String visit4_17_F="";
                String visit5_17_F="";
                String completedpep_17_F="";
                String seroconverted_17_F="";
                String Pregnant_17="";
                String counselling_17_F="";
                String visit1_49_M="";
                String visit2_49_M="";
                String visit3_49_M="";
                String visit4_49_M="";
                String visit5_49_M="";
                String completedpep_49_M="";
                String seroconverted_49_M="";
                String counselling_49_M="";
                String visit1_49_F="";
                String visit2_49_F="";
                String visit3_49_F="";
                String visit4_49_F="";
                String visit5_49_F="";
                String completedpep_49_F="";
                String seroconverted_49_F="";
                String Pregnant_49="";
                String counselling_49_F="";
                String visit1_50_M="";
                String visit2_50_M="";
                String visit3_50_M="";
                String visit4_50_M="";
                String visit5_50_M="";
                String completedpep_50_M="";
                String seroconverted_50_M="";
                String counselling_50_M="";
                String visit1_50_F="";
                String visit2_50_F="";
                String visit3_50_F="";
                String visit4_50_F="";
                String visit5_50_F="";
                String completedpep_50_F="";
                String seroconverted_50_F="";
                String Pregnant_50="";
                String counselling_50_F="";
                String visit1_M="";
                String visit2_M="";
                String visit3_M="";
                String visit4_M="";
                String visit5_M="";
                String completedpep_M="";
                String seroconverted_M="";
                String counselling_M="";
                String visit1_F="";
                String visit2_F="";
                String visit3_F="";
                String visit4_F="";
                String visit5_F="";
                String completedpep_F="";
                String seroconverted_F="";
                String Pregnant="";
                String counselling_F="";
                String visit1_T="";
                String visit2_T="";
                String visit3_T="";
                String visit4_T="";
                String visit5_T="";
                String completedpep_T="";
                String seroconverted_T="";
                String Pregnant_T="";
                String counselling_T="";
                String rapesurvivor_11_M="";
                String presenting_72hr_11_M="";
                String initiatedpep_11_M="";
                String sti_11_M="";
                String tested_11_M="";
                String disability_11_M="";
                String positive_11_M="";
                String perpetrators_11_M="";
                String rapesurvivor_11_F="";
                String presenting_72hr_11_F="";
                String initiatedpep_11_F="";
                String sti_11_F="";
                String ecp_11="";
                String pill_11="";
                String tested_11_F="";
                String disability_11_F="";
                String positive_11_F="";
                String perpetrators_11_F="";
                String rapesurvivor_17_M="";
                String presenting_72hr_17_M="";
                String initiatedpep_17_M="";
                String sti_17_M="";
                String tested_17_M="";
                String disability_17_M="";
                String positive_17_M="";
                String perpetrators_17_M="";
                String rapesurvivor_17_F="";
                String presenting_72hr_17_F="";
                String initiatedpep_17_F="";
                String sti_17_F="";
                String ecp_17="";
                String pill_17="";
                String tested_17_F="";
                String disability_17_F="";
                String positive_17_F="";
                String perpetrators_17_F="";
                String rapesurvivor_49_M="";
                String presenting_72hr_49_M="";
                String initiatedpep_49_M="";
                String sti_49_M="";
                String tested_49_M="";
                String disability_49_M="";
                String positive_49_M="";
                String perpetrators_49_M="";
                String rapesurvivor_49_F="";
                String presenting_72hr_49_F="";
                String initiatedpep_49_F="";
                String sti_49_F="";
                String ecp_49="";
                String pill_49="";
                String tested_49_F="";
                String disability_49_F="";
                String positive_49_F="";
                String perpetrators_49_F="";
                String rapesurvivor_50_M="";
                String presenting_72hr_50_M="";
                String initiatedpep_50_M="";
                String sti_50_M="";
                String tested_50_M="";
                String disability_50_M="";
                String positive_50_M="";
                String perpetrators_50_M="";
                String rapesurvivor_50_F="";
                String presenting_72hr_50_F="";
                String initiatedpep_50_F="";
                String sti_50_F="";
                String ecp_50="";
                String pill_50="";
                String tested_50_F="";
                String disability_50_F="";
                String positive_50_F="";
                String perpetrators_50_F="";
                String rapesurvivor_M="";
                String presenting_72hr_M="";
                String initiatedpep_M="";
                String sti_M="";
                String tested_M="";
                String disability_M="";
                String positive_M="";
                String perpetrators_M="";
                String rapesurvivor_F="";
                String presenting_72hr_F="";
                String initiatedpep_F="";
                String sti_F="";
                String ecp="";
                String pill="";
                String tested_F="";
                String disability_F="";
                String positive_F="";
                String perpetrators_F="";
                String rapesurvivor_T="";
                String presenting_72hr_T="";
                String initiatedpep_T="";
                String sti_T="";
                String ecp_T="";
                String pill_T="";
                String tested_T="";
                String disability_T="";
                String positive_T="";
                String perpetrators_T="";
                
                
                
                
                
                
                
                String checker="SELECT * FROM sgbv WHERE id=?" ;
                conn.pst=conn.conn.prepareStatement(checker);
                conn.pst.setString(1, id);
                conn.rs=conn.pst.executeQuery();
                String islocked="";
                
                
                       String yearmonth="";
        String tempmonth=month;
        int pepfaryear=Integer.parseInt(year);
        if(Integer.parseInt(month)<10){ tempmonth="0"+month; }
        else {pepfaryear--;}


        yearmonth=pepfaryear+""+tempmonth;
    String locked_DATA = "SELECT id FROM locked_data WHERE yearmonth=? AND sgbv=?";
    conn.pst = conn.conn.prepareStatement(locked_DATA);
    conn.pst.setString(1, yearmonth);
    conn.pst.setInt(2, 1);
    conn.rs = conn.pst.executeQuery();
    if(conn.rs.next()){
      islocked= "disabled='true'";
    }
    
                if(conn.rs.next()==true){
                    
                  
                    
                    if(conn.rs.getString("isLocked").equals("1")){islocked="disabled='true'";}
                    if(conn.rs.getString("isLocked").equals("0")){islocked="";}
                   
                    
                    if(conn.rs.getString("visit1_11_M")!=null){ visit1_11_M=conn.rs.getString("visit1_11_M");}else{ visit1_11_M="";}
                    if(conn.rs.getString("visit2_11_M")!=null){ visit2_11_M=conn.rs.getString("visit2_11_M");}else{ visit2_11_M="";}
                    if(conn.rs.getString("visit3_11_M")!=null){ visit3_11_M=conn.rs.getString("visit3_11_M");}else{ visit3_11_M="";}
                    if(conn.rs.getString("visit4_11_M")!=null){ visit4_11_M=conn.rs.getString("visit4_11_M");}else{ visit4_11_M="";}
                    if(conn.rs.getString("visit5_11_M")!=null){ visit5_11_M=conn.rs.getString("visit5_11_M");}else{ visit5_11_M="";}
                    if(conn.rs.getString("completedpep_11_M")!=null){ completedpep_11_M=conn.rs.getString("completedpep_11_M");}else{ completedpep_11_M="";}
                    if(conn.rs.getString("seroconverted_11_M")!=null){ seroconverted_11_M=conn.rs.getString("seroconverted_11_M");}else{ seroconverted_11_M="";}
                    if(conn.rs.getString("counselling_11_M")!=null){ counselling_11_M=conn.rs.getString("counselling_11_M");}else{ counselling_11_M="";}
                    if(conn.rs.getString("visit1_11_F")!=null){ visit1_11_F=conn.rs.getString("visit1_11_F");}else{ visit1_11_F="";}
                    if(conn.rs.getString("visit2_11_F")!=null){ visit2_11_F=conn.rs.getString("visit2_11_F");}else{ visit2_11_F="";}
                    if(conn.rs.getString("visit3_11_F")!=null){ visit3_11_F=conn.rs.getString("visit3_11_F");}else{ visit3_11_F="";}
                    if(conn.rs.getString("visit4_11_F")!=null){ visit4_11_F=conn.rs.getString("visit4_11_F");}else{ visit4_11_F="";}
                    if(conn.rs.getString("visit5_11_F")!=null){ visit5_11_F=conn.rs.getString("visit5_11_F");}else{ visit5_11_F="";}
                    if(conn.rs.getString("completedpep_11_F")!=null){ completedpep_11_F=conn.rs.getString("completedpep_11_F");}else{ completedpep_11_F="";}
                    if(conn.rs.getString("seroconverted_11_F")!=null){ seroconverted_11_F=conn.rs.getString("seroconverted_11_F");}else{ seroconverted_11_F="";}
                    if(conn.rs.getString("Pregnant_11")!=null){ Pregnant_11=conn.rs.getString("Pregnant_11");}else{ Pregnant_11="";}
                    if(conn.rs.getString("counselling_11_F")!=null){ counselling_11_F=conn.rs.getString("counselling_11_F");}else{ counselling_11_F="";}
                    if(conn.rs.getString("visit1_17_M")!=null){ visit1_17_M=conn.rs.getString("visit1_17_M");}else{ visit1_17_M="";}
                    if(conn.rs.getString("visit2_17_M")!=null){ visit2_17_M=conn.rs.getString("visit2_17_M");}else{ visit2_17_M="";}
                    if(conn.rs.getString("visit3_17_M")!=null){ visit3_17_M=conn.rs.getString("visit3_17_M");}else{ visit3_17_M="";}
                    if(conn.rs.getString("visit4_17_M")!=null){ visit4_17_M=conn.rs.getString("visit4_17_M");}else{ visit4_17_M="";}
                    if(conn.rs.getString("visit5_17_M")!=null){ visit5_17_M=conn.rs.getString("visit5_17_M");}else{ visit5_17_M="";}
                    if(conn.rs.getString("completedpep_17_M")!=null){ completedpep_17_M=conn.rs.getString("completedpep_17_M");}else{ completedpep_17_M="";}
                    if(conn.rs.getString("seroconverted_17_M")!=null){ seroconverted_17_M=conn.rs.getString("seroconverted_17_M");}else{ seroconverted_17_M="";}
                    if(conn.rs.getString("counselling_17_M")!=null){ counselling_17_M=conn.rs.getString("counselling_17_M");}else{ counselling_17_M="";}
                    if(conn.rs.getString("visit1_17_F")!=null){ visit1_17_F=conn.rs.getString("visit1_17_F");}else{ visit1_17_F="";}
                    if(conn.rs.getString("visit2_17_F")!=null){ visit2_17_F=conn.rs.getString("visit2_17_F");}else{ visit2_17_F="";}
                    if(conn.rs.getString("visit3_17_F")!=null){ visit3_17_F=conn.rs.getString("visit3_17_F");}else{ visit3_17_F="";}
                    if(conn.rs.getString("visit4_17_F")!=null){ visit4_17_F=conn.rs.getString("visit4_17_F");}else{ visit4_17_F="";}
                    if(conn.rs.getString("visit5_17_F")!=null){ visit5_17_F=conn.rs.getString("visit5_17_F");}else{ visit5_17_F="";}
                    if(conn.rs.getString("completedpep_17_F")!=null){ completedpep_17_F=conn.rs.getString("completedpep_17_F");}else{ completedpep_17_F="";}
                    if(conn.rs.getString("seroconverted_17_F")!=null){ seroconverted_17_F=conn.rs.getString("seroconverted_17_F");}else{ seroconverted_17_F="";}
                    if(conn.rs.getString("Pregnant_17")!=null){ Pregnant_17=conn.rs.getString("Pregnant_17");}else{ Pregnant_17="";}
                    if(conn.rs.getString("counselling_17_F")!=null){ counselling_17_F=conn.rs.getString("counselling_17_F");}else{ counselling_17_F="";}
                    if(conn.rs.getString("visit1_49_M")!=null){ visit1_49_M=conn.rs.getString("visit1_49_M");}else{ visit1_49_M="";}
                    if(conn.rs.getString("visit2_49_M")!=null){ visit2_49_M=conn.rs.getString("visit2_49_M");}else{ visit2_49_M="";}
                    if(conn.rs.getString("visit3_49_M")!=null){ visit3_49_M=conn.rs.getString("visit3_49_M");}else{ visit3_49_M="";}
                    if(conn.rs.getString("visit4_49_M")!=null){ visit4_49_M=conn.rs.getString("visit4_49_M");}else{ visit4_49_M="";}
                    if(conn.rs.getString("visit5_49_M")!=null){ visit5_49_M=conn.rs.getString("visit5_49_M");}else{ visit5_49_M="";}
                    if(conn.rs.getString("completedpep_49_M")!=null){ completedpep_49_M=conn.rs.getString("completedpep_49_M");}else{ completedpep_49_M="";}
                    if(conn.rs.getString("seroconverted_49_M")!=null){ seroconverted_49_M=conn.rs.getString("seroconverted_49_M");}else{ seroconverted_49_M="";}
                    if(conn.rs.getString("counselling_49_M")!=null){ counselling_49_M=conn.rs.getString("counselling_49_M");}else{ counselling_49_M="";}
                    if(conn.rs.getString("visit1_49_F")!=null){ visit1_49_F=conn.rs.getString("visit1_49_F");}else{ visit1_49_F="";}
                    if(conn.rs.getString("visit2_49_F")!=null){ visit2_49_F=conn.rs.getString("visit2_49_F");}else{ visit2_49_F="";}
                    if(conn.rs.getString("visit3_49_F")!=null){ visit3_49_F=conn.rs.getString("visit3_49_F");}else{ visit3_49_F="";}
                    if(conn.rs.getString("visit4_49_F")!=null){ visit4_49_F=conn.rs.getString("visit4_49_F");}else{ visit4_49_F="";}
                    if(conn.rs.getString("visit5_49_F")!=null){ visit5_49_F=conn.rs.getString("visit5_49_F");}else{ visit5_49_F="";}
                    if(conn.rs.getString("completedpep_49_F")!=null){ completedpep_49_F=conn.rs.getString("completedpep_49_F");}else{ completedpep_49_F="";}
                    if(conn.rs.getString("seroconverted_49_F")!=null){ seroconverted_49_F=conn.rs.getString("seroconverted_49_F");}else{ seroconverted_49_F="";}
                    if(conn.rs.getString("Pregnant_49")!=null){ Pregnant_49=conn.rs.getString("Pregnant_49");}else{ Pregnant_49="";}
                    if(conn.rs.getString("counselling_49_F")!=null){ counselling_49_F=conn.rs.getString("counselling_49_F");}else{ counselling_49_F="";}
                    if(conn.rs.getString("visit1_50_M")!=null){ visit1_50_M=conn.rs.getString("visit1_50_M");}else{ visit1_50_M="";}
                    if(conn.rs.getString("visit2_50_M")!=null){ visit2_50_M=conn.rs.getString("visit2_50_M");}else{ visit2_50_M="";}
                    if(conn.rs.getString("visit3_50_M")!=null){ visit3_50_M=conn.rs.getString("visit3_50_M");}else{ visit3_50_M="";}
                    if(conn.rs.getString("visit4_50_M")!=null){ visit4_50_M=conn.rs.getString("visit4_50_M");}else{ visit4_50_M="";}
                    if(conn.rs.getString("visit5_50_M")!=null){ visit5_50_M=conn.rs.getString("visit5_50_M");}else{ visit5_50_M="";}
                    if(conn.rs.getString("completedpep_50_M")!=null){ completedpep_50_M=conn.rs.getString("completedpep_50_M");}else{ completedpep_50_M="";}
                    if(conn.rs.getString("seroconverted_50_M")!=null){ seroconverted_50_M=conn.rs.getString("seroconverted_50_M");}else{ seroconverted_50_M="";}
                    if(conn.rs.getString("counselling_50_M")!=null){ counselling_50_M=conn.rs.getString("counselling_50_M");}else{ counselling_50_M="";}
                    if(conn.rs.getString("visit1_50_F")!=null){ visit1_50_F=conn.rs.getString("visit1_50_F");}else{ visit1_50_F="";}
                    if(conn.rs.getString("visit2_50_F")!=null){ visit2_50_F=conn.rs.getString("visit2_50_F");}else{ visit2_50_F="";}
                    if(conn.rs.getString("visit3_50_F")!=null){ visit3_50_F=conn.rs.getString("visit3_50_F");}else{ visit3_50_F="";}
                    if(conn.rs.getString("visit4_50_F")!=null){ visit4_50_F=conn.rs.getString("visit4_50_F");}else{ visit4_50_F="";}
                    if(conn.rs.getString("visit5_50_F")!=null){ visit5_50_F=conn.rs.getString("visit5_50_F");}else{ visit5_50_F="";}
                    if(conn.rs.getString("completedpep_50_F")!=null){ completedpep_50_F=conn.rs.getString("completedpep_50_F");}else{ completedpep_50_F="";}
                    if(conn.rs.getString("seroconverted_50_F")!=null){ seroconverted_50_F=conn.rs.getString("seroconverted_50_F");}else{ seroconverted_50_F="";}
                    if(conn.rs.getString("Pregnant_50")!=null){ Pregnant_50=conn.rs.getString("Pregnant_50");}else{ Pregnant_50="";}
                    if(conn.rs.getString("counselling_50_F")!=null){ counselling_50_F=conn.rs.getString("counselling_50_F");}else{ counselling_50_F="";}
                    if(conn.rs.getString("visit1_M")!=null){ visit1_M=conn.rs.getString("visit1_M");}else{ visit1_M="";}
                    if(conn.rs.getString("visit2_M")!=null){ visit2_M=conn.rs.getString("visit2_M");}else{ visit2_M="";}
                    if(conn.rs.getString("visit3_M")!=null){ visit3_M=conn.rs.getString("visit3_M");}else{ visit3_M="";}
                    if(conn.rs.getString("visit4_M")!=null){ visit4_M=conn.rs.getString("visit4_M");}else{ visit4_M="";}
                    if(conn.rs.getString("visit5_M")!=null){ visit5_M=conn.rs.getString("visit5_M");}else{ visit5_M="";}
                    if(conn.rs.getString("completedpep_M")!=null){ completedpep_M=conn.rs.getString("completedpep_M");}else{ completedpep_M="";}
                    if(conn.rs.getString("seroconverted_M")!=null){ seroconverted_M=conn.rs.getString("seroconverted_M");}else{ seroconverted_M="";}
                    if(conn.rs.getString("counselling_M")!=null){ counselling_M=conn.rs.getString("counselling_M");}else{ counselling_M="";}
                    if(conn.rs.getString("visit1_F")!=null){ visit1_F=conn.rs.getString("visit1_F");}else{ visit1_F="";}
                    if(conn.rs.getString("visit2_F")!=null){ visit2_F=conn.rs.getString("visit2_F");}else{ visit2_F="";}
                    if(conn.rs.getString("visit3_F")!=null){ visit3_F=conn.rs.getString("visit3_F");}else{ visit3_F="";}
                    if(conn.rs.getString("visit4_F")!=null){ visit4_F=conn.rs.getString("visit4_F");}else{ visit4_F="";}
                    if(conn.rs.getString("visit5_F")!=null){ visit5_F=conn.rs.getString("visit5_F");}else{ visit5_F="";}
                    if(conn.rs.getString("completedpep_F")!=null){ completedpep_F=conn.rs.getString("completedpep_F");}else{ completedpep_F="";}
                    if(conn.rs.getString("seroconverted_F")!=null){ seroconverted_F=conn.rs.getString("seroconverted_F");}else{ seroconverted_F="";}
                    if(conn.rs.getString("Pregnant")!=null){ Pregnant=conn.rs.getString("Pregnant");}else{ Pregnant="";}
                    if(conn.rs.getString("counselling_F")!=null){ counselling_F=conn.rs.getString("counselling_F");}else{ counselling_F="";}
                    if(conn.rs.getString("visit1_T")!=null){ visit1_T=conn.rs.getString("visit1_T");}else{ visit1_T="";}
                    if(conn.rs.getString("visit2_T")!=null){ visit2_T=conn.rs.getString("visit2_T");}else{ visit2_T="";}
                    if(conn.rs.getString("visit3_T")!=null){ visit3_T=conn.rs.getString("visit3_T");}else{ visit3_T="";}
                    if(conn.rs.getString("visit4_T")!=null){ visit4_T=conn.rs.getString("visit4_T");}else{ visit4_T="";}
                    if(conn.rs.getString("visit5_T")!=null){ visit5_T=conn.rs.getString("visit5_T");}else{ visit5_T="";}
                    if(conn.rs.getString("completedpep_T")!=null){ completedpep_T=conn.rs.getString("completedpep_T");}else{ completedpep_T="";}
                    if(conn.rs.getString("seroconverted_T")!=null){ seroconverted_T=conn.rs.getString("seroconverted_T");}else{ seroconverted_T="";}
                    if(conn.rs.getString("Pregnant_T")!=null){ Pregnant_T=conn.rs.getString("Pregnant_T");}else{ Pregnant_T="";}
                    if(conn.rs.getString("counselling_T")!=null){ counselling_T=conn.rs.getString("counselling_T");}else{ counselling_T="";}
                    if(conn.rs.getString("rapesurvivor_11_M")!=null){ rapesurvivor_11_M=conn.rs.getString("rapesurvivor_11_M");}else{ rapesurvivor_11_M="";}
                    if(conn.rs.getString("presenting_72hr_11_M")!=null){ presenting_72hr_11_M=conn.rs.getString("presenting_72hr_11_M");}else{ presenting_72hr_11_M="";}
                    if(conn.rs.getString("initiatedpep_11_M")!=null){ initiatedpep_11_M=conn.rs.getString("initiatedpep_11_M");}else{ initiatedpep_11_M="";}
                    if(conn.rs.getString("sti_11_M")!=null){ sti_11_M=conn.rs.getString("sti_11_M");}else{ sti_11_M="";}
                    if(conn.rs.getString("tested_11_M")!=null){ tested_11_M=conn.rs.getString("tested_11_M");}else{ tested_11_M="";}
                    if(conn.rs.getString("disability_11_M")!=null){ disability_11_M=conn.rs.getString("disability_11_M");}else{ disability_11_M="";}
                    if(conn.rs.getString("positive_11_M")!=null){ positive_11_M=conn.rs.getString("positive_11_M");}else{ positive_11_M="";}
                    if(conn.rs.getString("perpetrators_11_M")!=null){ perpetrators_11_M=conn.rs.getString("perpetrators_11_M");}else{ perpetrators_11_M="";}
                    if(conn.rs.getString("rapesurvivor_11_F")!=null){ rapesurvivor_11_F=conn.rs.getString("rapesurvivor_11_F");}else{ rapesurvivor_11_F="";}
                    if(conn.rs.getString("presenting_72hr_11_F")!=null){ presenting_72hr_11_F=conn.rs.getString("presenting_72hr_11_F");}else{ presenting_72hr_11_F="";}
                    if(conn.rs.getString("initiatedpep_11_F")!=null){ initiatedpep_11_F=conn.rs.getString("initiatedpep_11_F");}else{ initiatedpep_11_F="";}
                    if(conn.rs.getString("sti_11_F")!=null){ sti_11_F=conn.rs.getString("sti_11_F");}else{ sti_11_F="";}
                    if(conn.rs.getString("ecp_11")!=null){ ecp_11=conn.rs.getString("ecp_11");}else{ ecp_11="";}
                    if(conn.rs.getString("pill_11")!=null){ pill_11=conn.rs.getString("pill_11");}else{ pill_11="";}
                    if(conn.rs.getString("tested_11_F")!=null){ tested_11_F=conn.rs.getString("tested_11_F");}else{ tested_11_F="";}
                    if(conn.rs.getString("disability_11_F")!=null){ disability_11_F=conn.rs.getString("disability_11_F");}else{ disability_11_F="";}
                    if(conn.rs.getString("positive_11_F")!=null){ positive_11_F=conn.rs.getString("positive_11_F");}else{ positive_11_F="";}
                    if(conn.rs.getString("perpetrators_11_F")!=null){ perpetrators_11_F=conn.rs.getString("perpetrators_11_F");}else{ perpetrators_11_F="";}
                    if(conn.rs.getString("rapesurvivor_17_M")!=null){ rapesurvivor_17_M=conn.rs.getString("rapesurvivor_17_M");}else{ rapesurvivor_17_M="";}
                    if(conn.rs.getString("presenting_72hr_17_M")!=null){ presenting_72hr_17_M=conn.rs.getString("presenting_72hr_17_M");}else{ presenting_72hr_17_M="";}
                    if(conn.rs.getString("initiatedpep_17_M")!=null){ initiatedpep_17_M=conn.rs.getString("initiatedpep_17_M");}else{ initiatedpep_17_M="";}
                    if(conn.rs.getString("sti_17_M")!=null){ sti_17_M=conn.rs.getString("sti_17_M");}else{ sti_17_M="";}
                    if(conn.rs.getString("tested_17_M")!=null){ tested_17_M=conn.rs.getString("tested_17_M");}else{ tested_17_M="";}
                    if(conn.rs.getString("disability_17_M")!=null){ disability_17_M=conn.rs.getString("disability_17_M");}else{ disability_17_M="";}
                    if(conn.rs.getString("positive_17_M")!=null){ positive_17_M=conn.rs.getString("positive_17_M");}else{ positive_17_M="";}
                    if(conn.rs.getString("perpetrators_17_M")!=null){ perpetrators_17_M=conn.rs.getString("perpetrators_17_M");}else{ perpetrators_17_M="";}
                    if(conn.rs.getString("rapesurvivor_17_F")!=null){ rapesurvivor_17_F=conn.rs.getString("rapesurvivor_17_F");}else{ rapesurvivor_17_F="";}
                    if(conn.rs.getString("presenting_72hr_17_F")!=null){ presenting_72hr_17_F=conn.rs.getString("presenting_72hr_17_F");}else{ presenting_72hr_17_F="";}
                    if(conn.rs.getString("initiatedpep_17_F")!=null){ initiatedpep_17_F=conn.rs.getString("initiatedpep_17_F");}else{ initiatedpep_17_F="";}
                    if(conn.rs.getString("sti_17_F")!=null){ sti_17_F=conn.rs.getString("sti_17_F");}else{ sti_17_F="";}
                    if(conn.rs.getString("ecp_17")!=null){ ecp_17=conn.rs.getString("ecp_17");}else{ ecp_17="";}
                    if(conn.rs.getString("pill_17")!=null){ pill_17=conn.rs.getString("pill_17");}else{ pill_17="";}
                    if(conn.rs.getString("tested_17_F")!=null){ tested_17_F=conn.rs.getString("tested_17_F");}else{ tested_17_F="";}
                    if(conn.rs.getString("disability_17_F")!=null){ disability_17_F=conn.rs.getString("disability_17_F");}else{ disability_17_F="";}
                    if(conn.rs.getString("positive_17_F")!=null){ positive_17_F=conn.rs.getString("positive_17_F");}else{ positive_17_F="";}
                    if(conn.rs.getString("perpetrators_17_F")!=null){ perpetrators_17_F=conn.rs.getString("perpetrators_17_F");}else{ perpetrators_17_F="";}
                    if(conn.rs.getString("rapesurvivor_49_M")!=null){ rapesurvivor_49_M=conn.rs.getString("rapesurvivor_49_M");}else{ rapesurvivor_49_M="";}
                    if(conn.rs.getString("presenting_72hr_49_M")!=null){ presenting_72hr_49_M=conn.rs.getString("presenting_72hr_49_M");}else{ presenting_72hr_49_M="";}
                    if(conn.rs.getString("initiatedpep_49_M")!=null){ initiatedpep_49_M=conn.rs.getString("initiatedpep_49_M");}else{ initiatedpep_49_M="";}
                    if(conn.rs.getString("sti_49_M")!=null){ sti_49_M=conn.rs.getString("sti_49_M");}else{ sti_49_M="";}
                    if(conn.rs.getString("tested_49_M")!=null){ tested_49_M=conn.rs.getString("tested_49_M");}else{ tested_49_M="";}
                    if(conn.rs.getString("disability_49_M")!=null){ disability_49_M=conn.rs.getString("disability_49_M");}else{ disability_49_M="";}
                    if(conn.rs.getString("positive_49_M")!=null){ positive_49_M=conn.rs.getString("positive_49_M");}else{ positive_49_M="";}
                    if(conn.rs.getString("perpetrators_49_M")!=null){ perpetrators_49_M=conn.rs.getString("perpetrators_49_M");}else{ perpetrators_49_M="";}
                    if(conn.rs.getString("rapesurvivor_49_F")!=null){ rapesurvivor_49_F=conn.rs.getString("rapesurvivor_49_F");}else{ rapesurvivor_49_F="";}
                    if(conn.rs.getString("presenting_72hr_49_F")!=null){ presenting_72hr_49_F=conn.rs.getString("presenting_72hr_49_F");}else{ presenting_72hr_49_F="";}
                    if(conn.rs.getString("initiatedpep_49_F")!=null){ initiatedpep_49_F=conn.rs.getString("initiatedpep_49_F");}else{ initiatedpep_49_F="";}
                    if(conn.rs.getString("sti_49_F")!=null){ sti_49_F=conn.rs.getString("sti_49_F");}else{ sti_49_F="";}
                    if(conn.rs.getString("ecp_49")!=null){ ecp_49=conn.rs.getString("ecp_49");}else{ ecp_49="";}
                    if(conn.rs.getString("pill_49")!=null){ pill_49=conn.rs.getString("pill_49");}else{ pill_49="";}
                    if(conn.rs.getString("tested_49_F")!=null){ tested_49_F=conn.rs.getString("tested_49_F");}else{ tested_49_F="";}
                    if(conn.rs.getString("disability_49_F")!=null){ disability_49_F=conn.rs.getString("disability_49_F");}else{ disability_49_F="";}
                    if(conn.rs.getString("positive_49_F")!=null){ positive_49_F=conn.rs.getString("positive_49_F");}else{ positive_49_F="";}
                    if(conn.rs.getString("perpetrators_49_F")!=null){ perpetrators_49_F=conn.rs.getString("perpetrators_49_F");}else{ perpetrators_49_F="";}
                    if(conn.rs.getString("rapesurvivor_50_M")!=null){ rapesurvivor_50_M=conn.rs.getString("rapesurvivor_50_M");}else{ rapesurvivor_50_M="";}
                    if(conn.rs.getString("presenting_72hr_50_M")!=null){ presenting_72hr_50_M=conn.rs.getString("presenting_72hr_50_M");}else{ presenting_72hr_50_M="";}
                    if(conn.rs.getString("initiatedpep_50_M")!=null){ initiatedpep_50_M=conn.rs.getString("initiatedpep_50_M");}else{ initiatedpep_50_M="";}
                    if(conn.rs.getString("sti_50_M")!=null){ sti_50_M=conn.rs.getString("sti_50_M");}else{ sti_50_M="";}
                    if(conn.rs.getString("tested_50_M")!=null){ tested_50_M=conn.rs.getString("tested_50_M");}else{ tested_50_M="";}
                    if(conn.rs.getString("disability_50_M")!=null){ disability_50_M=conn.rs.getString("disability_50_M");}else{ disability_50_M="";}
                    if(conn.rs.getString("positive_50_M")!=null){ positive_50_M=conn.rs.getString("positive_50_M");}else{ positive_50_M="";}
                    if(conn.rs.getString("perpetrators_50_M")!=null){ perpetrators_50_M=conn.rs.getString("perpetrators_50_M");}else{ perpetrators_50_M="";}
                    if(conn.rs.getString("rapesurvivor_50_F")!=null){ rapesurvivor_50_F=conn.rs.getString("rapesurvivor_50_F");}else{ rapesurvivor_50_F="";}
                    if(conn.rs.getString("presenting_72hr_50_F")!=null){ presenting_72hr_50_F=conn.rs.getString("presenting_72hr_50_F");}else{ presenting_72hr_50_F="";}
                    if(conn.rs.getString("initiatedpep_50_F")!=null){ initiatedpep_50_F=conn.rs.getString("initiatedpep_50_F");}else{ initiatedpep_50_F="";}
                    if(conn.rs.getString("sti_50_F")!=null){ sti_50_F=conn.rs.getString("sti_50_F");}else{ sti_50_F="";}
                    if(conn.rs.getString("ecp_50")!=null){ ecp_50=conn.rs.getString("ecp_50");}else{ ecp_50="";}
                    if(conn.rs.getString("pill_50")!=null){ pill_50=conn.rs.getString("pill_50");}else{ pill_50="";}
                    if(conn.rs.getString("tested_50_F")!=null){ tested_50_F=conn.rs.getString("tested_50_F");}else{ tested_50_F="";}
                    if(conn.rs.getString("disability_50_F")!=null){ disability_50_F=conn.rs.getString("disability_50_F");}else{ disability_50_F="";}
                    if(conn.rs.getString("positive_50_F")!=null){ positive_50_F=conn.rs.getString("positive_50_F");}else{ positive_50_F="";}
                    if(conn.rs.getString("perpetrators_50_F")!=null){ perpetrators_50_F=conn.rs.getString("perpetrators_50_F");}else{ perpetrators_50_F="";}
                    if(conn.rs.getString("rapesurvivor_M")!=null){ rapesurvivor_M=conn.rs.getString("rapesurvivor_M");}else{ rapesurvivor_M="";}
                    if(conn.rs.getString("presenting_72hr_M")!=null){ presenting_72hr_M=conn.rs.getString("presenting_72hr_M");}else{ presenting_72hr_M="";}
                    if(conn.rs.getString("initiatedpep_M")!=null){ initiatedpep_M=conn.rs.getString("initiatedpep_M");}else{ initiatedpep_M="";}
                    if(conn.rs.getString("sti_M")!=null){ sti_M=conn.rs.getString("sti_M");}else{ sti_M="";}
                    if(conn.rs.getString("tested_M")!=null){ tested_M=conn.rs.getString("tested_M");}else{ tested_M="";}
                    if(conn.rs.getString("disability_M")!=null){ disability_M=conn.rs.getString("disability_M");}else{ disability_M="";}
                    if(conn.rs.getString("positive_M")!=null){ positive_M=conn.rs.getString("positive_M");}else{ positive_M="";}
                    if(conn.rs.getString("perpetrators_M")!=null){ perpetrators_M=conn.rs.getString("perpetrators_M");}else{ perpetrators_M="";}
                    if(conn.rs.getString("rapesurvivor_F")!=null){ rapesurvivor_F=conn.rs.getString("rapesurvivor_F");}else{ rapesurvivor_F="";}
                    if(conn.rs.getString("presenting_72hr_F")!=null){ presenting_72hr_F=conn.rs.getString("presenting_72hr_F");}else{ presenting_72hr_F="";}
                    if(conn.rs.getString("initiatedpep_F")!=null){ initiatedpep_F=conn.rs.getString("initiatedpep_F");}else{ initiatedpep_F="";}
                    if(conn.rs.getString("sti_F")!=null){ sti_F=conn.rs.getString("sti_F");}else{ sti_F="";}
                    if(conn.rs.getString("ecp")!=null){ ecp=conn.rs.getString("ecp");}else{ ecp="";}
                    if(conn.rs.getString("pill")!=null){ pill=conn.rs.getString("pill");}else{ pill="";}
                    if(conn.rs.getString("tested_F")!=null){ tested_F=conn.rs.getString("tested_F");}else{ tested_F="";}
                    if(conn.rs.getString("disability_F")!=null){ disability_F=conn.rs.getString("disability_F");}else{ disability_F="";}
                    if(conn.rs.getString("positive_F")!=null){ positive_F=conn.rs.getString("positive_F");}else{ positive_F="";}
                    if(conn.rs.getString("perpetrators_F")!=null){ perpetrators_F=conn.rs.getString("perpetrators_F");}else{ perpetrators_F="";}
                    if(conn.rs.getString("rapesurvivor_T")!=null){ rapesurvivor_T=conn.rs.getString("rapesurvivor_T");}else{ rapesurvivor_T="";}
                    if(conn.rs.getString("presenting_72hr_T")!=null){ presenting_72hr_T=conn.rs.getString("presenting_72hr_T");}else{ presenting_72hr_T="";}
                    if(conn.rs.getString("initiatedpep_T")!=null){ initiatedpep_T=conn.rs.getString("initiatedpep_T");}else{ initiatedpep_T="";}
                    if(conn.rs.getString("sti_T")!=null){ sti_T=conn.rs.getString("sti_T");}else{ sti_T="";}
                    if(conn.rs.getString("ecp_T")!=null){ ecp_T=conn.rs.getString("ecp_T");}else{ ecp_T="";}
                    if(conn.rs.getString("pill_T")!=null){ pill_T=conn.rs.getString("pill_T");}else{ pill_T="";}
                    if(conn.rs.getString("tested_T")!=null){ tested_T=conn.rs.getString("tested_T");}else{ tested_T="";}
                    if(conn.rs.getString("disability_T")!=null){ disability_T=conn.rs.getString("disability_T");}else{ disability_T="";}
                    if(conn.rs.getString("positive_T")!=null){ positive_T=conn.rs.getString("positive_T");}else{ positive_T="";}
                    if(conn.rs.getString("perpetrators_T")!=null){ perpetrators_T=conn.rs.getString("perpetrators_T");}else{ perpetrators_T="";}
                    
                 
                    //================================================================================================================================================
                    
                    
                    
                    if(conn.rs.getString("isValidated")!=null){
                        isValidated=conn.rs.getString("isValidated");
                    }
                    else{isValidated="";}
                    //get the name of the person who entered the form
                    
                    String enterer = "select * from user where userid='" + conn.rs.getString("userid") + "'";
                    
                    
                    
                    conn.rs1 = conn.st1.executeQuery(enterer);
                    //add details of person who entered
                    if (conn.rs1.next()) {
                        enterdby = "<font color='green'>Data 1st entered by:   <b> " + conn.rs1.getString("fname") + " " + conn.rs1.getString("mname") + " " + conn.rs1.getString("lname") + "</b>  on  <b>" + conn.rs.getString("timestamp") + "</b></font>";
                    }
                    
                    
                    //now check if form was updated and if its one month after data entry
                    
                    if (conn.rs.getString("updatedOn") != null) {
                        //get difference in months between entered date and updated date
                        String compdate = "SELECT TIMESTAMPDIFF(MONTH,'" + conn.rs.getString("timestamp") + "','" + conn.rs.getString("updatedOn") + "')";
                        conn.rs2 = conn.st2.executeQuery(compdate);
                        if (conn.rs2.next()) {
                            //now get the details of the person who updated the form
                            //if the difference is greater than or equal to one,
                            
                            
                            if (conn.rs2.getInt(1) >= 1) {
                                String updater = "select * from user where userid='" + conn.rs.getString("updatedBy") + "'";
                                
                                conn.rs1 = conn.st1.executeQuery(updater);
                                //add details of person who entered
                                if (conn.rs1.next()) {
                                    enterdby += "<span style='margin-left:30%;'><font color='red'>   Updated  by:   <b> " + conn.rs1.getString("fname") + " " + conn.rs1.getString("mname") + " " + conn.rs1.getString("lname") + "</b>  on  <b>" + conn.rs.getString("updatedOn") + "</b></font></span>";
                                }
                            } //end of if month >=1
                        }//end of date comparison if
                        
                    }//end of if updated !=null
                
                    
                }
             
                if(isValidated.equals("0")){
                    validity="<font color=\"red\"><b>Form Not Validated.<img style=\"margin-left:10px;\" src=\"images/notValidated.jpg\" width=\"20px\" height=\"20px\"></b></font>"  ;
                }
                else if(isValidated.equals("1")){
                    validity="<font color=\"green\"><b>Form Validated.<img style=\"margin-left:10px;\" src=\"images/validated.jpg\" width=\"20px\" height=\"20px\"></b></font>"  ;
                }
                else{
                    
                    validity="<font color=\"blue\"><b>New Entry</b></font>"  ;
                }
                
                // FamilyPlanninng=pmct=maternity=vct=dtc="";
                
                
                String sectiona_ext="";//2016
                String sectionb_ext="";//2016
                
                
                
                sectiona_tab+="";
                
                validitychecker+="<p id=\"checkValidity\" hidden=\"hidden\">"+validity+"</p>";
                
                
                sectiona_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'> SECTION A </b></legend> "+
                        "<table frame='box' border='1'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:15px; width:98%;font-family:cambria;'>"
                        + "<tr> <td  class='form-actions'><b>Indicator</b></td> "
                        + "<td colspan='2' class='form-actions'><b>0-11 Yrs</b></td> "
                        + "<td colspan='2' class='form-actions'><b>12-17 Yrs</b></td> "
                        + "<td colspan='2' class='form-actions'><b>18-49 Yrs</b></td> "
                        + "<td colspan='2' class='form-actions'><b>50+ Yrs</b></td>  "
                        + "<td colspan='2' class='form-actions'><b>Total</b></td> "
                        + "<td  class='form-actions'><b>Grand Total</b></td></tr>"
                        
                        //herder 2
                        
                        + "<tr> <td  class='form-actions'><b></b></td> "
                        + "<td  class='form-actions'><b>M</b></td> "
                        + "<td  class='form-actions'><b>F</b></td> "
                        
                        + "<td  class='form-actions'><b>M</b></td> "
                        + "<td  class='form-actions'><b>F</b></td> "
                        
                        + "<td  class='form-actions'><b>M</b></td> "
                        + "<td  class='form-actions'><b>F</b></td> "
                        
                        + "<td  class='form-actions'><b>M</b></td> "
                        + "<td  class='form-actions'><b>F</b></td> "
                        
                        + "<td  class='form-actions'><b>M</b></td> "
                        + "<td  class='form-actions'><b>F</b></td> "
                        
                        + "<td  class='form-actions'><b>Grand Total</b></td></tr>"
                        
                        //rape survivors
                        + "<tr> "
                        + "<td  class='form-actions'><b>Number of rape survivors</b></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='rapesurvivor_11_M'  onblur=\"autosave('rapesurvivor_11_M');autosum('rapesurvivor_11_M@rapesurvivor_17_M@rapesurvivor_49_M@rapesurvivor_50_M','rapesurvivor_M');autosum('rapesurvivor_11_M@rapesurvivor_17_M@rapesurvivor_49_M@rapesurvivor_50_M@rapesurvivor_11_F@rapesurvivor_17_F@rapesurvivor_49_F@rapesurvivor_50_F','rapesurvivor_T');\" id='rapesurvivor_11_M' value='"+rapesurvivor_11_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='rapesurvivor_11_F'  onblur=\"autosave('rapesurvivor_11_F');autosum('rapesurvivor_11_F@rapesurvivor_17_F@rapesurvivor_49_F@rapesurvivor_50_F','rapesurvivor_F');autosum('rapesurvivor_11_M@rapesurvivor_17_M@rapesurvivor_49_M@rapesurvivor_50_M@rapesurvivor_11_F@rapesurvivor_17_F@rapesurvivor_49_F@rapesurvivor_50_F','rapesurvivor_T');\" id='rapesurvivor_11_F' value='"+rapesurvivor_11_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='rapesurvivor_17_M'  onblur=\"autosave('rapesurvivor_17_M');autosum('rapesurvivor_11_M@rapesurvivor_17_M@rapesurvivor_49_M@rapesurvivor_50_M','rapesurvivor_M');autosum('rapesurvivor_11_M@rapesurvivor_17_M@rapesurvivor_49_M@rapesurvivor_50_M@rapesurvivor_11_F@rapesurvivor_17_F@rapesurvivor_49_F@rapesurvivor_50_F','rapesurvivor_T');\" id='rapesurvivor_17_M' value='"+rapesurvivor_17_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='rapesurvivor_17_F'  onblur=\"autosave('rapesurvivor_17_F');autosum('rapesurvivor_11_F@rapesurvivor_17_F@rapesurvivor_49_F@rapesurvivor_50_F','rapesurvivor_F');autosum('rapesurvivor_11_M@rapesurvivor_17_M@rapesurvivor_49_M@rapesurvivor_50_M@rapesurvivor_11_F@rapesurvivor_17_F@rapesurvivor_49_F@rapesurvivor_50_F','rapesurvivor_T');\" id='rapesurvivor_17_F' value='"+rapesurvivor_17_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='rapesurvivor_49_M'  onblur=\"autosave('rapesurvivor_49_M');autosum('rapesurvivor_11_M@rapesurvivor_17_M@rapesurvivor_49_M@rapesurvivor_50_M','rapesurvivor_M');autosum('rapesurvivor_11_M@rapesurvivor_17_M@rapesurvivor_49_M@rapesurvivor_50_M@rapesurvivor_11_F@rapesurvivor_17_F@rapesurvivor_49_F@rapesurvivor_50_F','rapesurvivor_T');\" id='rapesurvivor_49_M' value='"+rapesurvivor_49_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='rapesurvivor_49_F'  onblur=\"autosave('rapesurvivor_49_F');autosum('rapesurvivor_11_F@rapesurvivor_17_F@rapesurvivor_49_F@rapesurvivor_50_F','rapesurvivor_F');autosum('rapesurvivor_11_M@rapesurvivor_17_M@rapesurvivor_49_M@rapesurvivor_50_M@rapesurvivor_11_F@rapesurvivor_17_F@rapesurvivor_49_F@rapesurvivor_50_F','rapesurvivor_T');\" id='rapesurvivor_49_F' value='"+rapesurvivor_49_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='rapesurvivor_50_M'  onblur=\"autosave('rapesurvivor_50_M');autosum('rapesurvivor_11_M@rapesurvivor_17_M@rapesurvivor_49_M@rapesurvivor_50_M','rapesurvivor_M');autosum('rapesurvivor_11_M@rapesurvivor_17_M@rapesurvivor_49_M@rapesurvivor_50_M@rapesurvivor_11_F@rapesurvivor_17_F@rapesurvivor_49_F@rapesurvivor_50_F','rapesurvivor_T');\" id='rapesurvivor_50_M' value='"+rapesurvivor_50_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='rapesurvivor_50_F'  onblur=\"autosave('rapesurvivor_50_F');autosum('rapesurvivor_11_F@rapesurvivor_17_F@rapesurvivor_49_F@rapesurvivor_50_F','rapesurvivor_F');autosum('rapesurvivor_11_M@rapesurvivor_17_M@rapesurvivor_49_M@rapesurvivor_50_M@rapesurvivor_11_F@rapesurvivor_17_F@rapesurvivor_49_F@rapesurvivor_50_F','rapesurvivor_T');\" id='rapesurvivor_50_F' value='"+rapesurvivor_50_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1' type='text'  name='rapesurvivor_M'  id='rapesurvivor_M' value='"+rapesurvivor_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='rapesurvivor_F'  id='rapesurvivor_F' value='"+rapesurvivor_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='rapesurvivor_T'  id='rapesurvivor_T' value='"+rapesurvivor_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>"
                        
                        
                        //presenting 72 hrs
                        
                        
                        + "<tr> <td  class='form-actions'><b>Number presenting within 72 hours</b></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='presenting_72hr_11_M'  onblur=\"autosave('presenting_72hr_11_M');autosum('presenting_72hr_11_M@presenting_72hr_17_M@presenting_72hr_49_M@presenting_72hr_50_M','presenting_72hr_M');autosum('presenting_72hr_11_M@presenting_72hr_17_M@presenting_72hr_49_M@presenting_72hr_50_M@presenting_72hr_11_F@presenting_72hr_17_F@presenting_72hr_49_F@presenting_72hr_50_F','presenting_72hr_T');\" id='presenting_72hr_11_M' value='"+presenting_72hr_11_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='presenting_72hr_11_F'  onblur=\"autosave('presenting_72hr_11_F');autosum('presenting_72hr_11_F@presenting_72hr_17_F@presenting_72hr_49_F@presenting_72hr_50_F','presenting_72hr_F');autosum('presenting_72hr_11_M@presenting_72hr_17_M@presenting_72hr_49_M@presenting_72hr_50_M@presenting_72hr_11_F@presenting_72hr_17_F@presenting_72hr_49_F@presenting_72hr_50_F','presenting_72hr_T');\" id='presenting_72hr_11_F' value='"+presenting_72hr_11_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='presenting_72hr_17_M'  onblur=\"autosave('presenting_72hr_17_M');autosum('presenting_72hr_11_M@presenting_72hr_17_M@presenting_72hr_49_M@presenting_72hr_50_M','presenting_72hr_M');autosum('presenting_72hr_11_M@presenting_72hr_17_M@presenting_72hr_49_M@presenting_72hr_50_M@presenting_72hr_11_F@presenting_72hr_17_F@presenting_72hr_49_F@presenting_72hr_50_F','presenting_72hr_T');\" id='presenting_72hr_17_M' value='"+presenting_72hr_17_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='presenting_72hr_17_F'  onblur=\"autosave('presenting_72hr_17_F');autosum('presenting_72hr_11_F@presenting_72hr_17_F@presenting_72hr_49_F@presenting_72hr_50_F','presenting_72hr_F');autosum('presenting_72hr_11_M@presenting_72hr_17_M@presenting_72hr_49_M@presenting_72hr_50_M@presenting_72hr_11_F@presenting_72hr_17_F@presenting_72hr_49_F@presenting_72hr_50_F','presenting_72hr_T');\" id='presenting_72hr_17_F' value='"+presenting_72hr_17_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='presenting_72hr_49_M'  onblur=\"autosave('presenting_72hr_49_M');autosum('presenting_72hr_11_M@presenting_72hr_17_M@presenting_72hr_49_M@presenting_72hr_50_M','presenting_72hr_M');autosum('presenting_72hr_11_M@presenting_72hr_17_M@presenting_72hr_49_M@presenting_72hr_50_M@presenting_72hr_11_F@presenting_72hr_17_F@presenting_72hr_49_F@presenting_72hr_50_F','presenting_72hr_T');\" id='presenting_72hr_49_M' value='"+presenting_72hr_49_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='presenting_72hr_49_F'  onblur=\"autosave('presenting_72hr_49_F');autosum('presenting_72hr_11_F@presenting_72hr_17_F@presenting_72hr_49_F@presenting_72hr_50_F','presenting_72hr_F');autosum('presenting_72hr_11_M@presenting_72hr_17_M@presenting_72hr_49_M@presenting_72hr_50_M@presenting_72hr_11_F@presenting_72hr_17_F@presenting_72hr_49_F@presenting_72hr_50_F','presenting_72hr_T');\" id='presenting_72hr_49_F' value='"+presenting_72hr_49_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='presenting_72hr_50_M'  onblur=\"autosave('presenting_72hr_50_M');autosum('presenting_72hr_11_M@presenting_72hr_17_M@presenting_72hr_49_M@presenting_72hr_50_M','presenting_72hr_M');autosum('presenting_72hr_11_M@presenting_72hr_17_M@presenting_72hr_49_M@presenting_72hr_50_M@presenting_72hr_11_F@presenting_72hr_17_F@presenting_72hr_49_F@presenting_72hr_50_F','presenting_72hr_T');\" id='presenting_72hr_50_M' value='"+presenting_72hr_50_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='presenting_72hr_50_F'  onblur=\"autosave('presenting_72hr_50_F');autosum('presenting_72hr_11_F@presenting_72hr_17_F@presenting_72hr_49_F@presenting_72hr_50_F','presenting_72hr_F');autosum('presenting_72hr_11_M@presenting_72hr_17_M@presenting_72hr_49_M@presenting_72hr_50_M@presenting_72hr_11_F@presenting_72hr_17_F@presenting_72hr_49_F@presenting_72hr_50_F','presenting_72hr_T');\" id='presenting_72hr_50_F' value='"+presenting_72hr_50_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='presenting_72hr_M'  id='presenting_72hr_M' value='"+presenting_72hr_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1' type='text'  name='presenting_72hr_F'  id='presenting_72hr_F' value='"+presenting_72hr_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='presenting_72hr_T'  id='presenting_72hr_T' value='"+presenting_72hr_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>"
                        
                        
                        
                        //Number initiated Pep
                        
                        
                        + "<tr> <td  class='form-actions'><b>Number initiated PEP</b></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='initiatedpep_11_M'  onblur=\"autosave('initiatedpep_11_M');autosum('initiatedpep_11_M@initiatedpep_17_M@initiatedpep_49_M@initiatedpep_50_M','initiatedpep_M');autosum('initiatedpep_11_M@initiatedpep_17_M@initiatedpep_49_M@initiatedpep_50_M@initiatedpep_11_F@initiatedpep_17_F@initiatedpep_49_F@initiatedpep_50_F','initiatedpep_T');\" id='initiatedpep_11_M' value='"+initiatedpep_11_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='initiatedpep_11_F'  onblur=\"autosave('initiatedpep_11_F');autosum('initiatedpep_11_F@initiatedpep_17_F@initiatedpep_49_F@initiatedpep_50_F','initiatedpep_F');autosum('initiatedpep_11_M@initiatedpep_17_M@initiatedpep_49_M@initiatedpep_50_M@initiatedpep_11_F@initiatedpep_17_F@initiatedpep_49_F@initiatedpep_50_F','initiatedpep_T');\" id='initiatedpep_11_F' value='"+initiatedpep_11_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='initiatedpep_17_M'  onblur=\"autosave('initiatedpep_17_M');autosum('initiatedpep_11_M@initiatedpep_17_M@initiatedpep_49_M@initiatedpep_50_M','initiatedpep_M');autosum('initiatedpep_11_M@initiatedpep_17_M@initiatedpep_49_M@initiatedpep_50_M@initiatedpep_11_F@initiatedpep_17_F@initiatedpep_49_F@initiatedpep_50_F','initiatedpep_T');\" id='initiatedpep_17_M' value='"+initiatedpep_17_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='initiatedpep_17_F'  onblur=\"autosave('initiatedpep_17_F');autosum('initiatedpep_11_F@initiatedpep_17_F@initiatedpep_49_F@initiatedpep_50_F','initiatedpep_F');autosum('initiatedpep_11_M@initiatedpep_17_M@initiatedpep_49_M@initiatedpep_50_M@initiatedpep_11_F@initiatedpep_17_F@initiatedpep_49_F@initiatedpep_50_F','initiatedpep_T');\" id='initiatedpep_17_F' value='"+initiatedpep_17_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='initiatedpep_49_M'  onblur=\"autosave('initiatedpep_49_M');autosum('initiatedpep_11_M@initiatedpep_17_M@initiatedpep_49_M@initiatedpep_50_M','initiatedpep_M');autosum('initiatedpep_11_M@initiatedpep_17_M@initiatedpep_49_M@initiatedpep_50_M@initiatedpep_11_F@initiatedpep_17_F@initiatedpep_49_F@initiatedpep_50_F','initiatedpep_T');\" id='initiatedpep_49_M' value='"+initiatedpep_49_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='initiatedpep_49_F'  onblur=\"autosave('initiatedpep_49_F');autosum('initiatedpep_11_F@initiatedpep_17_F@initiatedpep_49_F@initiatedpep_50_F','initiatedpep_F');autosum('initiatedpep_11_M@initiatedpep_17_M@initiatedpep_49_M@initiatedpep_50_M@initiatedpep_11_F@initiatedpep_17_F@initiatedpep_49_F@initiatedpep_50_F','initiatedpep_T');\" id='initiatedpep_49_F' value='"+initiatedpep_49_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='initiatedpep_50_M'  onblur=\"autosave('initiatedpep_50_M');autosum('initiatedpep_11_M@initiatedpep_17_M@initiatedpep_49_M@initiatedpep_50_M','initiatedpep_M');autosum('initiatedpep_11_M@initiatedpep_17_M@initiatedpep_49_M@initiatedpep_50_M@initiatedpep_11_F@initiatedpep_17_F@initiatedpep_49_F@initiatedpep_50_F','initiatedpep_T');\" id='initiatedpep_50_M' value='"+initiatedpep_50_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='initiatedpep_50_F'  onblur=\"autosave('initiatedpep_50_F');autosum('initiatedpep_11_F@initiatedpep_17_F@initiatedpep_49_F@initiatedpep_50_F','initiatedpep_F');autosum('initiatedpep_11_M@initiatedpep_17_M@initiatedpep_49_M@initiatedpep_50_M@initiatedpep_11_F@initiatedpep_17_F@initiatedpep_49_F@initiatedpep_50_F','initiatedpep_T');\" id='initiatedpep_50_F' value='"+initiatedpep_50_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1' type='text'  name='initiatedpep_M'  id='initiatedpep_M' value='"+initiatedpep_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1' type='text'  name='initiatedpep_F'  id='initiatedpep_F' value='"+initiatedpep_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='initiatedpep_T'  id='initiatedpep_T' value='"+initiatedpep_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>"
                        
                        
                        //Number given STI treatment
                        
                        + "<tr> <td  class='form-actions'><b>Number given STI treatment</b></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='sti_11_M'  onblur=\"autosave('sti_11_M');autosum('sti_11_M@sti_17_M@sti_49_M@sti_50_M','sti_M');autosum('sti_11_M@sti_17_M@sti_49_M@sti_50_M@sti_11_F@sti_17_F@sti_49_F@sti_50_F','sti_T');\" id='sti_11_M' value='"+sti_11_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='sti_11_F'  onblur=\"autosave('sti_11_F');autosum('sti_11_F@sti_17_F@sti_49_F@sti_50_F','sti_F');autosum('sti_11_M@sti_17_M@sti_49_M@sti_50_M@sti_11_F@sti_17_F@sti_49_F@sti_50_F','sti_T');\" id='sti_11_F' value='"+sti_11_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='sti_17_M'  onblur=\"autosave('sti_17_M');autosum('sti_11_M@sti_17_M@sti_49_M@sti_50_M','sti_M');autosum('sti_11_M@sti_17_M@sti_49_M@sti_50_M@sti_11_F@sti_17_F@sti_49_F@sti_50_F','sti_T');\" id='sti_17_M' value='"+sti_17_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='sti_17_F'  onblur=\"autosave('sti_17_F');autosum('sti_11_F@sti_17_F@sti_49_F@sti_50_F','sti_F');autosum('sti_11_M@sti_17_M@sti_49_M@sti_50_M@sti_11_F@sti_17_F@sti_49_F@sti_50_F','sti_T');\" id='sti_17_F' value='"+sti_17_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='sti_49_M'  onblur=\"autosave('sti_49_M');autosum('sti_11_M@sti_17_M@sti_49_M@sti_50_M','sti_M');autosum('sti_11_M@sti_17_M@sti_49_M@sti_50_M@sti_11_F@sti_17_F@sti_49_F@sti_50_F','sti_T');\" id='sti_49_M' value='"+sti_49_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='sti_49_F'  onblur=\"autosave('sti_49_F');autosum('sti_11_F@sti_17_F@sti_49_F@sti_50_F','sti_F');autosum('sti_11_M@sti_17_M@sti_49_M@sti_50_M@sti_11_F@sti_17_F@sti_49_F@sti_50_F','sti_T');\" id='sti_49_F' value='"+sti_49_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='sti_50_M'  onblur=\"autosave('sti_50_M');autosum('sti_11_M@sti_17_M@sti_49_M@sti_50_M','sti_M');autosum('sti_11_M@sti_17_M@sti_49_M@sti_50_M@sti_11_F@sti_17_F@sti_49_F@sti_50_F','sti_T');\" id='sti_50_M' value='"+sti_50_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='sti_50_F'  onblur=\"autosave('sti_50_F');autosum('sti_11_F@sti_17_F@sti_49_F@sti_50_F','sti_F');autosum('sti_11_M@sti_17_M@sti_49_M@sti_50_M@sti_11_F@sti_17_F@sti_49_F@sti_50_F','sti_T');\" id='sti_50_F' value='"+sti_50_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1' type='text'  name='sti_M'  id='sti_M' value='"+sti_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1' type='text'  name='sti_F'  id='sti_F' value='"+sti_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='sti_T'  id='sti_T' value='"+sti_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>"
                        
                        
                        //Emergency contraceptive
                        
                       
                        + "<tr> <td  class='form-actions'><b>Number eligible for Emergency Contraceptive Pill</b></td> "
                        
                        + "<td  class='form-actions'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='ecp_11'  onblur=\"autosave('ecp_11');autosum('ecp_11@ecp_17@ecp_49@ecp_50','ecp');autosum('ecp_11@ecp_17@ecp_49@ecp_50','ecp_T');\" id='ecp_11' value='"+ecp_11+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='ecp_17'  onblur=\"autosave('ecp_17');autosum('ecp_11@ecp_17@ecp_49@ecp_50','ecp');autosum('ecp_11@ecp_17@ecp_49@ecp_50','ecp_T');\" id='ecp_17' value='"+ecp_17+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='ecp_49'  onblur=\"autosave('ecp_49');autosum('ecp_11@ecp_17@ecp_49@ecp_50','ecp');autosum('ecp_11@ecp_17@ecp_49@ecp_50','ecp_T');\" id='ecp_49' value='"+ecp_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='ecp_50'  onblur=\"autosave('ecp_50');autosum('ecp_11@ecp_17@ecp_49@ecp_50','ecp');autosum('ecp_11@ecp_17@ecp_49@ecp_50','ecp_T');\" id='ecp_50' value='"+ecp_50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='ecp'  id='ecp' value='"+ecp+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='ecp_T'  id='ecp_T' value='"+ecp_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>" 
                        
                        
                        //given pill
                        
                        
                        
                        + "<tr> <td  class='form-actions'><b>Number given Emergency Contraceptive Pill</b></td> "
                        
                        + "<td  class='form-actions'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='pill_11'  onblur=\"autosave('pill_11');autosum('pill_11@pill_17@pill_49@pill_50','pill');autosum('pill_11@pill_17@pill_49@pill_50','pill_T');\" id='pill_11' value='"+pill_11+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='pill_17'  onblur=\"autosave('pill_17');autosum('pill_11@pill_17@pill_49@pill_50','pill');autosum('pill_11@pill_17@pill_49@pill_50','pill_T');\" id='pill_17' value='"+pill_17+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='pill_49'  onblur=\"autosave('pill_49');autosum('pill_11@pill_17@pill_49@pill_50','pill');autosum('pill_11@pill_17@pill_49@pill_50','pill_T');\" id='pill_49' value='"+pill_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='pill_50'  onblur=\"autosave('pill_50');autosum('pill_11@pill_17@pill_49@pill_50','pill');autosum('pill_11@pill_17@pill_49@pill_50','pill_T');\" id='pill_50' value='"+pill_50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='pill'  id='pill' value='"+pill+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='pill_T'  id='pill_T' value='"+pill_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>" 
                        
                        


//tested
                        
                     + "<tr> <td  class='form-actions'><b>Number tested for HIV</b></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='tested_11_M'  onblur=\"autosave('tested_11_M');autosum('tested_11_M@tested_17_M@tested_49_M@tested_50_M','tested_M');autosum('tested_11_M@tested_17_M@tested_49_M@tested_50_M@tested_11_F@tested_17_F@tested_49_F@tested_50_F','tested_T');\" id='tested_11_M' value='"+tested_11_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='tested_11_F'  onblur=\"autosave('tested_11_F');autosum('tested_11_F@tested_17_F@tested_49_F@tested_50_F','tested_F');autosum('tested_11_M@tested_17_M@tested_49_M@tested_50_M@tested_11_F@tested_17_F@tested_49_F@tested_50_F','tested_T');\" id='tested_11_F' value='"+tested_11_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='tested_17_M'  onblur=\"autosave('tested_17_M');autosum('tested_11_M@tested_17_M@tested_49_M@tested_50_M','tested_M');autosum('tested_11_M@tested_17_M@tested_49_M@tested_50_M@tested_11_F@tested_17_F@tested_49_F@tested_50_F','tested_T');\" id='tested_17_M' value='"+tested_17_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='tested_17_F'  onblur=\"autosave('tested_17_F');autosum('tested_11_F@tested_17_F@tested_49_F@tested_50_F','tested_F');autosum('tested_11_M@tested_17_M@tested_49_M@tested_50_M@tested_11_F@tested_17_F@tested_49_F@tested_50_F','tested_T');\" id='tested_17_F' value='"+tested_17_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='tested_49_M'  onblur=\"autosave('tested_49_M');autosum('tested_11_M@tested_17_M@tested_49_M@tested_50_M','tested_M');autosum('tested_11_M@tested_17_M@tested_49_M@tested_50_M@tested_11_F@tested_17_F@tested_49_F@tested_50_F','tested_T');\" id='tested_49_M' value='"+tested_49_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='tested_49_F'  onblur=\"autosave('tested_49_F');autosum('tested_11_F@tested_17_F@tested_49_F@tested_50_F','tested_F');autosum('tested_11_M@tested_17_M@tested_49_M@tested_50_M@tested_11_F@tested_17_F@tested_49_F@tested_50_F','tested_T');\" id='tested_49_F' value='"+tested_49_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='tested_50_M'  onblur=\"autosave('tested_50_M');autosum('tested_11_M@tested_17_M@tested_49_M@tested_50_M','tested_M');autosum('tested_11_M@tested_17_M@tested_49_M@tested_50_M@tested_11_F@tested_17_F@tested_49_F@tested_50_F','tested_T');\" id='tested_50_M' value='"+tested_50_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='tested_50_F'  onblur=\"autosave('tested_50_F');autosum('tested_11_F@tested_17_F@tested_49_F@tested_50_F','tested_F');autosum('tested_11_M@tested_17_M@tested_49_M@tested_50_M@tested_11_F@tested_17_F@tested_49_F@tested_50_F','tested_T');\" id='tested_50_F' value='"+tested_50_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='tested_M'  id='tested_M' value='"+tested_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='tested_F'  id='tested_F' value='"+tested_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='tested_T'  id='tested_T' value='"+tested_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>"
                                                
                     //positive at first visit
                        
                        
                        
                     + "<tr> <td  class='form-actions'><b>Number HIV positive at 1st visit</b></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='positive_11_M'  onblur=\"autosave('positive_11_M');autosum('positive_11_M@positive_17_M@positive_49_M@positive_50_M','positive_M');autosum('positive_11_M@positive_17_M@positive_49_M@positive_50_M@positive_11_F@positive_17_F@positive_49_F@positive_50_F','positive_T');\" id='positive_11_M' value='"+positive_11_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='positive_11_F'  onblur=\"autosave('positive_11_F');autosum('positive_11_F@positive_17_F@positive_49_F@positive_50_F','positive_F');autosum('positive_11_M@positive_17_M@positive_49_M@positive_50_M@positive_11_F@positive_17_F@positive_49_F@positive_50_F','positive_T');\" id='positive_11_F' value='"+positive_11_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='positive_17_M'  onblur=\"autosave('positive_17_M');autosum('positive_11_M@positive_17_M@positive_49_M@positive_50_M','positive_M');autosum('positive_11_M@positive_17_M@positive_49_M@positive_50_M@positive_11_F@positive_17_F@positive_49_F@positive_50_F','positive_T');\" id='positive_17_M' value='"+positive_17_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='positive_17_F'  onblur=\"autosave('positive_17_F');autosum('positive_11_F@positive_17_F@positive_49_F@positive_50_F','positive_F');autosum('positive_11_M@positive_17_M@positive_49_M@positive_50_M@positive_11_F@positive_17_F@positive_49_F@positive_50_F','positive_T');\" id='positive_17_F' value='"+positive_17_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='positive_49_M'  onblur=\"autosave('positive_49_M');autosum('positive_11_M@positive_17_M@positive_49_M@positive_50_M','positive_M');autosum('positive_11_M@positive_17_M@positive_49_M@positive_50_M@positive_11_F@positive_17_F@positive_49_F@positive_50_F','positive_T');\" id='positive_49_M' value='"+positive_49_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='positive_49_F'  onblur=\"autosave('positive_49_F');autosum('positive_11_F@positive_17_F@positive_49_F@positive_50_F','positive_F');autosum('positive_11_M@positive_17_M@positive_49_M@positive_50_M@positive_11_F@positive_17_F@positive_49_F@positive_50_F','positive_T');\" id='positive_49_F' value='"+positive_49_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='positive_50_M'  onblur=\"autosave('positive_50_M');autosum('positive_11_M@positive_17_M@positive_49_M@positive_50_M','positive_M');autosum('positive_11_M@positive_17_M@positive_49_M@positive_50_M@positive_11_F@positive_17_F@positive_49_F@positive_50_F','positive_T');\" id='positive_50_M' value='"+positive_50_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='positive_50_F'  onblur=\"autosave('positive_50_F');autosum('positive_11_F@positive_17_F@positive_49_F@positive_50_F','positive_F');autosum('positive_11_M@positive_17_M@positive_49_M@positive_50_M@positive_11_F@positive_17_F@positive_49_F@positive_50_F','positive_T');\" id='positive_50_F' value='"+positive_50_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='positive_M'  id='positive_M' value='"+positive_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='positive_F'  id='positive_F' value='"+positive_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='positive_T'  id='positive_T' value='"+positive_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>"
                        
                       
                        //survivors with disability
                        
                        + "<tr> <td  class='form-actions'><b>Total survivors with disability</b></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='disability_11_M'  onblur=\"autosave('disability_11_M');autosum('disability_11_M@disability_17_M@disability_49_M@disability_50_M','disability_M');autosum('disability_11_M@disability_17_M@disability_49_M@disability_50_M@disability_11_F@disability_17_F@disability_49_F@disability_50_F','disability_T');\" id='disability_11_M' value='"+disability_11_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='disability_11_F'  onblur=\"autosave('disability_11_F');autosum('disability_11_F@disability_17_F@disability_49_F@disability_50_F','disability_F');autosum('disability_11_M@disability_17_M@disability_49_M@disability_50_M@disability_11_F@disability_17_F@disability_49_F@disability_50_F','disability_T');\" id='disability_11_F' value='"+disability_11_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='disability_17_M'  onblur=\"autosave('disability_17_M');autosum('disability_11_M@disability_17_M@disability_49_M@disability_50_M','disability_M');autosum('disability_11_M@disability_17_M@disability_49_M@disability_50_M@disability_11_F@disability_17_F@disability_49_F@disability_50_F','disability_T');\" id='disability_17_M' value='"+disability_17_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='disability_17_F'  onblur=\"autosave('disability_17_F');autosum('disability_11_F@disability_17_F@disability_49_F@disability_50_F','disability_F');autosum('disability_11_M@disability_17_M@disability_49_M@disability_50_M@disability_11_F@disability_17_F@disability_49_F@disability_50_F','disability_T');\" id='disability_17_F' value='"+disability_17_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "                        
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='disability_49_M'  onblur=\"autosave('disability_49_M');autosum('disability_11_M@disability_17_M@disability_49_M@disability_50_M','disability_M');autosum('disability_11_M@disability_17_M@disability_49_M@disability_50_M@disability_11_F@disability_17_F@disability_49_F@disability_50_F','disability_T');\" id='disability_49_M' value='"+disability_49_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='disability_49_F'  onblur=\"autosave('disability_49_F');autosum('disability_11_F@disability_17_F@disability_49_F@disability_50_F','disability_F');autosum('disability_11_M@disability_17_M@disability_49_M@disability_50_M@disability_11_F@disability_17_F@disability_49_F@disability_50_F','disability_T');\" id='disability_49_F' value='"+disability_49_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='disability_50_M'  onblur=\"autosave('disability_50_M');autosum('disability_11_M@disability_17_M@disability_49_M@disability_50_M','disability_M');autosum('disability_11_M@disability_17_M@disability_49_M@disability_50_M@disability_11_F@disability_17_F@disability_49_F@disability_50_F','disability_T');\" id='disability_50_M' value='"+disability_50_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='disability_50_F'  onblur=\"autosave('disability_50_F');autosum('disability_11_F@disability_17_F@disability_49_F@disability_50_F','disability_F');autosum('disability_11_M@disability_17_M@disability_49_M@disability_50_M@disability_11_F@disability_17_F@disability_49_F@disability_50_F','disability_T');\" id='disability_50_F' value='"+disability_50_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                                               
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='disability_M'  id='disability_M' value='"+disability_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='disability_F'  id='disability_F' value='"+disability_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='disability_T'  id='disability_T' value='"+disability_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>"
                        
                        //perpetrators
                        
                       + "<tr> <td  class='form-actions'><b>Number of perpetrators</b></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='perpetrators_11_M'  onblur=\"autosave('perpetrators_11_M');autosum('perpetrators_11_M@perpetrators_17_M@perpetrators_49_M@perpetrators_50_M','perpetrators_M');autosum('perpetrators_11_M@perpetrators_17_M@perpetrators_49_M@perpetrators_50_M@perpetrators_11_F@perpetrators_17_F@perpetrators_49_F@perpetrators_50_F','perpetrators_T');\" id='perpetrators_11_M' value='"+perpetrators_11_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='perpetrators_11_F'  onblur=\"autosave('perpetrators_11_F');autosum('perpetrators_11_F@perpetrators_17_F@perpetrators_49_F@perpetrators_50_F','perpetrators_F');autosum('perpetrators_11_M@perpetrators_17_M@perpetrators_49_M@perpetrators_50_M@perpetrators_11_F@perpetrators_17_F@perpetrators_49_F@perpetrators_50_F','perpetrators_T');\" id='perpetrators_11_F' value='"+perpetrators_11_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='perpetrators_17_M'  onblur=\"autosave('perpetrators_17_M');autosum('perpetrators_11_M@perpetrators_17_M@perpetrators_49_M@perpetrators_50_M','perpetrators_M');autosum('perpetrators_11_M@perpetrators_17_M@perpetrators_49_M@perpetrators_50_M@perpetrators_11_F@perpetrators_17_F@perpetrators_49_F@perpetrators_50_F','perpetrators_T');\" id='perpetrators_17_M' value='"+perpetrators_17_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='perpetrators_17_F'  onblur=\"autosave('perpetrators_17_F');autosum('perpetrators_11_F@perpetrators_17_F@perpetrators_49_F@perpetrators_50_F','perpetrators_F');autosum('perpetrators_11_M@perpetrators_17_M@perpetrators_49_M@perpetrators_50_M@perpetrators_11_F@perpetrators_17_F@perpetrators_49_F@perpetrators_50_F','perpetrators_T');\" id='perpetrators_17_F' value='"+perpetrators_17_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "                        
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='perpetrators_49_M'  onblur=\"autosave('perpetrators_49_M');autosum('perpetrators_11_M@perpetrators_17_M@perpetrators_49_M@perpetrators_50_M','perpetrators_M');autosum('perpetrators_11_M@perpetrators_17_M@perpetrators_49_M@perpetrators_50_M@perpetrators_11_F@perpetrators_17_F@perpetrators_49_F@perpetrators_50_F','perpetrators_T');\" id='perpetrators_49_M' value='"+perpetrators_49_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='perpetrators_49_F'  onblur=\"autosave('perpetrators_49_F');autosum('perpetrators_11_F@perpetrators_17_F@perpetrators_49_F@perpetrators_50_F','perpetrators_F');autosum('perpetrators_11_M@perpetrators_17_M@perpetrators_49_M@perpetrators_50_M@perpetrators_11_F@perpetrators_17_F@perpetrators_49_F@perpetrators_50_F','perpetrators_T');\" id='perpetrators_49_F' value='"+perpetrators_49_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='perpetrators_50_M'  onblur=\"autosave('perpetrators_50_M');autosum('perpetrators_11_M@perpetrators_17_M@perpetrators_49_M@perpetrators_50_M','perpetrators_M');autosum('perpetrators_11_M@perpetrators_17_M@perpetrators_49_M@perpetrators_50_M@perpetrators_11_F@perpetrators_17_F@perpetrators_49_F@perpetrators_50_F','perpetrators_T');\" id='perpetrators_50_M' value='"+perpetrators_50_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='perpetrators_50_F'  onblur=\"autosave('perpetrators_50_F');autosum('perpetrators_11_F@perpetrators_17_F@perpetrators_49_F@perpetrators_50_F','perpetrators_F');autosum('perpetrators_11_M@perpetrators_17_M@perpetrators_49_M@perpetrators_50_M@perpetrators_11_F@perpetrators_17_F@perpetrators_49_F@perpetrators_50_F','perpetrators_T');\" id='perpetrators_50_F' value='"+perpetrators_50_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                                               
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='perpetrators_M'  id='perpetrators_M' value='"+perpetrators_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='perpetrators_F'  id='perpetrators_F' value='"+perpetrators_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='perpetrators_T'  id='perpetrators_T' value='"+perpetrators_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>" 
                        
                        
                        
                        + " </table> </fieldset> ";
                sectiona_tab+=sectiona_ext;
                // PMCTA_1stVisit_ANC=PMCTA_ReVisit_ANC=PMCTANCClientsT=PMCTHB7=PMCTIPT1=PMCTIPT2=PMCTANCClients4=PMCTITN=MATNormalDelivery=MATCSection="";
                
                
                
                
                
                
                
                
                //SectionB
                sectionb_tab+="";
                
                sectionb_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'> SECTION B </b></legend> "+
                        "<table frame='box' border='1'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:15px; width:98%;font-family:cambria;'>"
                        + "<tr> <td  class='form-actions'><b>Indicator</b></td> "
                        + "<td colspan='2' class='form-actions'><b>0-11 Yrs</b></td> "
                        + "<td colspan='2' class='form-actions'><b>12-17 Yrs</b></td> "
                        + "<td colspan='2' class='form-actions'><b>18-49 Yrs</b></td> "
                        + "<td colspan='2' class='form-actions'><b>50+ Yrs</b></td>  "
                        + "<td colspan='2' class='form-actions'><b>Total</b></td> "
                        + "<td  class='form-actions'><b>Grand Total</b></td></tr>"
                        
                        
                        
                        //herder 2
                        
                        + "<tr> <td  class='form-actions'><b></b></td> "
                        + "<td  class='form-actions'><b>M</b></td> "
                        + "<td  class='form-actions'><b>F</b></td> "
                        
                        + "<td  class='form-actions'><b>M</b></td> "
                        + "<td  class='form-actions'><b>F</b></td> "
                        
                        + "<td  class='form-actions'><b>M</b></td> "
                        + "<td  class='form-actions'><b>F</b></td> "
                        
                        + "<td  class='form-actions'><b>M</b></td> "
                        + "<td  class='form-actions'><b>F</b></td> "
                        
                        + "<td  class='form-actions'><b>M</b></td> "
                        + "<td  class='form-actions'><b>F</b></td> "
                        
                        + "<td  class='form-actions'><b>Grand Total</b></td></tr>"
                        
                        //1st visit
                        + "<tr> <td  class='form-actions'><b>1<sup>st</sup> Visit</b></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit1_11_M'  onblur=\"autosave('visit1_11_M');autosum('visit1_11_M@visit1_17_M@visit1_49_M@visit1_50_M','visit1_M');autosum('visit1_11_M@visit1_17_M@visit1_49_M@visit1_50_M@visit1_11_F@visit1_17_F@visit1_49_F@visit1_50_F','visit1_T');\" id='visit1_11_M' value='"+visit1_11_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit1_11_F'  onblur=\"autosave('visit1_11_F');autosum('visit1_11_F@visit1_17_F@visit1_49_F@visit1_50_F','visit1_F');autosum('visit1_11_M@visit1_17_M@visit1_49_M@visit1_50_M@visit1_11_F@visit1_17_F@visit1_49_F@visit1_50_F','visit1_T');\" id='visit1_11_F' value='"+visit1_11_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit1_17_M'  onblur=\"autosave('visit1_17_M');autosum('visit1_11_M@visit1_17_M@visit1_49_M@visit1_50_M','visit1_M');autosum('visit1_11_M@visit1_17_M@visit1_49_M@visit1_50_M@visit1_11_F@visit1_17_F@visit1_49_F@visit1_50_F','visit1_T');\" id='visit1_17_M' value='"+visit1_17_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit1_17_F'  onblur=\"autosave('visit1_17_F');autosum('visit1_11_F@visit1_17_F@visit1_49_F@visit1_50_F','visit1_F');autosum('visit1_11_M@visit1_17_M@visit1_49_M@visit1_50_M@visit1_11_F@visit1_17_F@visit1_49_F@visit1_50_F','visit1_T');\" id='visit1_17_F' value='"+visit1_17_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit1_49_M'  onblur=\"autosave('visit1_49_M');autosum('visit1_11_M@visit1_17_M@visit1_49_M@visit1_50_M','visit1_M');autosum('visit1_11_M@visit1_17_M@visit1_49_M@visit1_50_M@visit1_11_F@visit1_17_F@visit1_49_F@visit1_50_F','visit1_T');\" id='visit1_49_M' value='"+visit1_49_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit1_49_F'  onblur=\"autosave('visit1_49_F');autosum('visit1_11_F@visit1_17_F@visit1_49_F@visit1_50_F','visit1_F');autosum('visit1_11_M@visit1_17_M@visit1_49_M@visit1_50_M@visit1_11_F@visit1_17_F@visit1_49_F@visit1_50_F','visit1_T');\" id='visit1_49_F' value='"+visit1_49_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit1_50_M'  onblur=\"autosave('visit1_50_M');autosum('visit1_11_M@visit1_17_M@visit1_49_M@visit1_50_M','visit1_M');autosum('visit1_11_M@visit1_17_M@visit1_49_M@visit1_50_M@visit1_11_F@visit1_17_F@visit1_49_F@visit1_50_F','visit1_T');\" id='visit1_50_M' value='"+visit1_50_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit1_50_F'  onblur=\"autosave('visit1_50_F');autosum('visit1_11_F@visit1_17_F@visit1_49_F@visit1_50_F','visit1_F');autosum('visit1_11_M@visit1_17_M@visit1_49_M@visit1_50_M@visit1_11_F@visit1_17_F@visit1_49_F@visit1_50_F','visit1_T');\" id='visit1_50_F' value='"+visit1_50_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='visit1_M'  id='visit1_M' value='"+visit1_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='visit1_F'  id='visit1_F' value='"+visit1_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='visit1_T'  id='visit1_T' value='"+visit1_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>"
                        
                        //2nd visit
                        
                        
                        + "<tr> <td  class='form-actions'><b>2<sup>nd</sup> Visit</b></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit2_11_M'  onblur=\"autosave('visit2_11_M');autosum('visit2_11_M@visit2_17_M@visit2_49_M@visit2_50_M','visit2_M');autosum('visit2_11_M@visit2_17_M@visit2_49_M@visit2_50_M@visit2_11_F@visit2_17_F@visit2_49_F@visit2_50_F','visit2_T');\" id='visit2_11_M' value='"+visit2_11_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit2_11_F'  onblur=\"autosave('visit2_11_F');autosum('visit2_11_F@visit2_17_F@visit2_49_F@visit2_50_F','visit2_F');autosum('visit2_11_M@visit2_17_M@visit2_49_M@visit2_50_M@visit2_11_F@visit2_17_F@visit2_49_F@visit2_50_F','visit2_T');\" id='visit2_11_F' value='"+visit2_11_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit2_17_M'  onblur=\"autosave('visit2_17_M');autosum('visit2_11_M@visit2_17_M@visit2_49_M@visit2_50_M','visit2_M');autosum('visit2_11_M@visit2_17_M@visit2_49_M@visit2_50_M@visit2_11_F@visit2_17_F@visit2_49_F@visit2_50_F','visit2_T');\" id='visit2_17_M' value='"+visit2_17_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit2_17_F'  onblur=\"autosave('visit2_17_F');autosum('visit2_11_F@visit2_17_F@visit2_49_F@visit2_50_F','visit2_F');autosum('visit2_11_M@visit2_17_M@visit2_49_M@visit2_50_M@visit2_11_F@visit2_17_F@visit2_49_F@visit2_50_F','visit2_T');\" id='visit2_17_F' value='"+visit2_17_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit2_49_M'  onblur=\"autosave('visit2_49_M');autosum('visit2_11_M@visit2_17_M@visit2_49_M@visit2_50_M','visit2_M');autosum('visit2_11_M@visit2_17_M@visit2_49_M@visit2_50_M@visit2_11_F@visit2_17_F@visit2_49_F@visit2_50_F','visit2_T');\" id='visit2_49_M' value='"+visit2_49_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit2_49_F'  onblur=\"autosave('visit2_49_F');autosum('visit2_11_F@visit2_17_F@visit2_49_F@visit2_50_F','visit2_F');autosum('visit2_11_M@visit2_17_M@visit2_49_M@visit2_50_M@visit2_11_F@visit2_17_F@visit2_49_F@visit2_50_F','visit2_T');\" id='visit2_49_F' value='"+visit2_49_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit2_50_M'  onblur=\"autosave('visit2_50_M');autosum('visit2_11_M@visit2_17_M@visit2_49_M@visit2_50_M','visit2_M');autosum('visit2_11_M@visit2_17_M@visit2_49_M@visit2_50_M@visit2_11_F@visit2_17_F@visit2_49_F@visit2_50_F','visit2_T');\" id='visit2_50_M' value='"+visit2_50_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit2_50_F'  onblur=\"autosave('visit2_50_F');autosum('visit2_11_F@visit2_17_F@visit2_49_F@visit2_50_F','visit2_F');autosum('visit2_11_M@visit2_17_M@visit2_49_M@visit2_50_M@visit2_11_F@visit2_17_F@visit2_49_F@visit2_50_F','visit2_T');\" id='visit2_50_F' value='"+visit2_50_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='visit2_M'  id='visit2_M' value='"+visit2_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='visit2_F'  id='visit2_F' value='"+visit2_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='visit2_T'  id='visit2_T' value='"+visit2_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>"
                        
                        
                        //3rd visit
                        
                         + "<tr> <td  class='form-actions'><b>3<sup>rd</sup> Visit</b></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit3_11_M'  onblur=\"autosave('visit3_11_M');autosum('visit3_11_M@visit3_17_M@visit3_49_M@visit3_50_M','visit3_M');autosum('visit3_11_M@visit3_17_M@visit3_49_M@visit3_50_M@visit3_11_F@visit3_17_F@visit3_49_F@visit3_50_F','visit3_T');\" id='visit3_11_M' value='"+visit3_11_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit3_11_F'  onblur=\"autosave('visit3_11_F');autosum('visit3_11_F@visit3_17_F@visit3_49_F@visit3_50_F','visit3_F');autosum('visit3_11_M@visit3_17_M@visit3_49_M@visit3_50_M@visit3_11_F@visit3_17_F@visit3_49_F@visit3_50_F','visit3_T');\" id='visit3_11_F' value='"+visit3_11_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit3_17_M'  onblur=\"autosave('visit3_17_M');autosum('visit3_11_M@visit3_17_M@visit3_49_M@visit3_50_M','visit3_M');autosum('visit3_11_M@visit3_17_M@visit3_49_M@visit3_50_M@visit3_11_F@visit3_17_F@visit3_49_F@visit3_50_F','visit3_T');\" id='visit3_17_M' value='"+visit3_17_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit3_17_F'  onblur=\"autosave('visit3_17_F');autosum('visit3_11_F@visit3_17_F@visit3_49_F@visit3_50_F','visit3_F');autosum('visit3_11_M@visit3_17_M@visit3_49_M@visit3_50_M@visit3_11_F@visit3_17_F@visit3_49_F@visit3_50_F','visit3_T');\" id='visit3_17_F' value='"+visit3_17_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit3_49_M'  onblur=\"autosave('visit3_49_M');autosum('visit3_11_M@visit3_17_M@visit3_49_M@visit3_50_M','visit3_M');autosum('visit3_11_M@visit3_17_M@visit3_49_M@visit3_50_M@visit3_11_F@visit3_17_F@visit3_49_F@visit3_50_F','visit3_T');\" id='visit3_49_M' value='"+visit3_49_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit3_49_F'  onblur=\"autosave('visit3_49_F');autosum('visit3_11_F@visit3_17_F@visit3_49_F@visit3_50_F','visit3_F');autosum('visit3_11_M@visit3_17_M@visit3_49_M@visit3_50_M@visit3_11_F@visit3_17_F@visit3_49_F@visit3_50_F','visit3_T');\" id='visit3_49_F' value='"+visit3_49_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit3_50_M'  onblur=\"autosave('visit3_50_M');autosum('visit3_11_M@visit3_17_M@visit3_49_M@visit3_50_M','visit3_M');autosum('visit3_11_M@visit3_17_M@visit3_49_M@visit3_50_M@visit3_11_F@visit3_17_F@visit3_49_F@visit3_50_F','visit3_T');\" id='visit3_50_M' value='"+visit3_50_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit3_50_F'  onblur=\"autosave('visit3_50_F');autosum('visit3_11_F@visit3_17_F@visit3_49_F@visit3_50_F','visit3_F');autosum('visit3_11_M@visit3_17_M@visit3_49_M@visit3_50_M@visit3_11_F@visit3_17_F@visit3_49_F@visit3_50_F','visit3_T');\" id='visit3_50_F' value='"+visit3_50_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='visit3_M'  id='visit3_M' value='"+visit3_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='visit3_F'  id='visit3_F' value='"+visit3_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='visit3_T'  id='visit3_T' value='"+visit3_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>"
                        
                        
                        //4th visit
                        
                        
                         + "<tr> <td  class='form-actions'><b>4<sup>th</sup> Visit</b></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit4_11_M'  onblur=\"autosave('visit4_11_M');autosum('visit4_11_M@visit4_17_M@visit4_49_M@visit4_50_M','visit4_M');autosum('visit4_11_M@visit4_17_M@visit4_49_M@visit4_50_M@visit4_11_F@visit4_17_F@visit4_49_F@visit4_50_F','visit4_T');\" id='visit4_11_M' value='"+visit4_11_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit4_11_F'  onblur=\"autosave('visit4_11_F');autosum('visit4_11_F@visit4_17_F@visit4_49_F@visit4_50_F','visit4_F');autosum('visit4_11_M@visit4_17_M@visit4_49_M@visit4_50_M@visit4_11_F@visit4_17_F@visit4_49_F@visit4_50_F','visit4_T');\" id='visit4_11_F' value='"+visit4_11_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit4_17_M'  onblur=\"autosave('visit4_17_M');autosum('visit4_11_M@visit4_17_M@visit4_49_M@visit4_50_M','visit4_M');autosum('visit4_11_M@visit4_17_M@visit4_49_M@visit4_50_M@visit4_11_F@visit4_17_F@visit4_49_F@visit4_50_F','visit4_T');\" id='visit4_17_M' value='"+visit4_17_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit4_17_F'  onblur=\"autosave('visit4_17_F');autosum('visit4_11_F@visit4_17_F@visit4_49_F@visit4_50_F','visit4_F');autosum('visit4_11_M@visit4_17_M@visit4_49_M@visit4_50_M@visit4_11_F@visit4_17_F@visit4_49_F@visit4_50_F','visit4_T');\" id='visit4_17_F' value='"+visit4_17_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit4_49_M'  onblur=\"autosave('visit4_49_M');autosum('visit4_11_M@visit4_17_M@visit4_49_M@visit4_50_M','visit4_M');autosum('visit4_11_M@visit4_17_M@visit4_49_M@visit4_50_M@visit4_11_F@visit4_17_F@visit4_49_F@visit4_50_F','visit4_T');\" id='visit4_49_M' value='"+visit4_49_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit4_49_F'  onblur=\"autosave('visit4_49_F');autosum('visit4_11_F@visit4_17_F@visit4_49_F@visit4_50_F','visit4_F');autosum('visit4_11_M@visit4_17_M@visit4_49_M@visit4_50_M@visit4_11_F@visit4_17_F@visit4_49_F@visit4_50_F','visit4_T');\" id='visit4_49_F' value='"+visit4_49_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit4_50_M'  onblur=\"autosave('visit4_50_M');autosum('visit4_11_M@visit4_17_M@visit4_49_M@visit4_50_M','visit4_M');autosum('visit4_11_M@visit4_17_M@visit4_49_M@visit4_50_M@visit4_11_F@visit4_17_F@visit4_49_F@visit4_50_F','visit4_T');\" id='visit4_50_M' value='"+visit4_50_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit4_50_F'  onblur=\"autosave('visit4_50_F');autosum('visit4_11_F@visit4_17_F@visit4_49_F@visit4_50_F','visit4_F');autosum('visit4_11_M@visit4_17_M@visit4_49_M@visit4_50_M@visit4_11_F@visit4_17_F@visit4_49_F@visit4_50_F','visit4_T');\" id='visit4_50_F' value='"+visit4_50_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='visit4_M'  id='visit4_M' value='"+visit4_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='visit4_F'  id='visit4_F' value='"+visit4_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='visit4_T'  id='visit4_T' value='"+visit4_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>"
                        
                        
                        //visit5
                        
                         + "<tr> <td  class='form-actions'><b>5<sup>th</sup> Visit</b></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit5_11_M'  onblur=\"autosave('visit5_11_M');autosum('visit5_11_M@visit5_17_M@visit5_49_M@visit5_50_M','visit5_M');autosum('visit5_11_M@visit5_17_M@visit5_49_M@visit5_50_M@visit5_11_F@visit5_17_F@visit5_49_F@visit5_50_F','visit5_T');\" id='visit5_11_M' value='"+visit5_11_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit5_11_F'  onblur=\"autosave('visit5_11_F');autosum('visit5_11_F@visit5_17_F@visit5_49_F@visit5_50_F','visit5_F');autosum('visit5_11_M@visit5_17_M@visit5_49_M@visit5_50_M@visit5_11_F@visit5_17_F@visit5_49_F@visit5_50_F','visit5_T');\" id='visit5_11_F' value='"+visit5_11_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit5_17_M'  onblur=\"autosave('visit5_17_M');autosum('visit5_11_M@visit5_17_M@visit5_49_M@visit5_50_M','visit5_M');autosum('visit5_11_M@visit5_17_M@visit5_49_M@visit5_50_M@visit5_11_F@visit5_17_F@visit5_49_F@visit5_50_F','visit5_T');\" id='visit5_17_M' value='"+visit5_17_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit5_17_F'  onblur=\"autosave('visit5_17_F');autosum('visit5_11_F@visit5_17_F@visit5_49_F@visit5_50_F','visit5_F');autosum('visit5_11_M@visit5_17_M@visit5_49_M@visit5_50_M@visit5_11_F@visit5_17_F@visit5_49_F@visit5_50_F','visit5_T');\" id='visit5_17_F' value='"+visit5_17_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit5_49_M'  onblur=\"autosave('visit5_49_M');autosum('visit5_11_M@visit5_17_M@visit5_49_M@visit5_50_M','visit5_M');autosum('visit5_11_M@visit5_17_M@visit5_49_M@visit5_50_M@visit5_11_F@visit5_17_F@visit5_49_F@visit5_50_F','visit5_T');\" id='visit5_49_M' value='"+visit5_49_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit5_49_F'  onblur=\"autosave('visit5_49_F');autosum('visit5_11_F@visit5_17_F@visit5_49_F@visit5_50_F','visit5_F');autosum('visit5_11_M@visit5_17_M@visit5_49_M@visit5_50_M@visit5_11_F@visit5_17_F@visit5_49_F@visit5_50_F','visit5_T');\" id='visit5_49_F' value='"+visit5_49_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit5_50_M'  onblur=\"autosave('visit5_50_M');autosum('visit5_11_M@visit5_17_M@visit5_49_M@visit5_50_M','visit5_M');autosum('visit5_11_M@visit5_17_M@visit5_49_M@visit5_50_M@visit5_11_F@visit5_17_F@visit5_49_F@visit5_50_F','visit5_T');\" id='visit5_50_M' value='"+visit5_50_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='visit5_50_F'  onblur=\"autosave('visit5_50_F');autosum('visit5_11_F@visit5_17_F@visit5_49_F@visit5_50_F','visit5_F');autosum('visit5_11_M@visit5_17_M@visit5_49_M@visit5_50_M@visit5_11_F@visit5_17_F@visit5_49_F@visit5_50_F','visit5_T');\" id='visit5_50_F' value='"+visit5_50_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='visit5_M'  id='visit5_M' value='"+visit5_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='visit5_F'  id='visit5_F' value='"+visit5_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='visit5_T'  id='visit5_T' value='"+visit5_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>"
                        
                        //Number completed PEP
                        
                           + "<tr> <td  class='form-actions'><b>Number completed PEP</b></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='completedpep_11_M'  onblur=\"autosave('completedpep_11_M');autosum('completedpep_11_M@completedpep_17_M@completedpep_49_M@completedpep_50_M','completedpep_M');autosum('completedpep_11_M@completedpep_17_M@completedpep_49_M@completedpep_50_M@completedpep_11_F@completedpep_17_F@completedpep_49_F@completedpep_50_F','completedpep_T');\" id='completedpep_11_M' value='"+completedpep_11_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='completedpep_11_F'  onblur=\"autosave('completedpep_11_F');autosum('completedpep_11_F@completedpep_17_F@completedpep_49_F@completedpep_50_F','completedpep_F');autosum('completedpep_11_M@completedpep_17_M@completedpep_49_M@completedpep_50_M@completedpep_11_F@completedpep_17_F@completedpep_49_F@completedpep_50_F','completedpep_T');\" id='completedpep_11_F' value='"+completedpep_11_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='completedpep_17_M'  onblur=\"autosave('completedpep_17_M');autosum('completedpep_11_M@completedpep_17_M@completedpep_49_M@completedpep_50_M','completedpep_M');autosum('completedpep_11_M@completedpep_17_M@completedpep_49_M@completedpep_50_M@completedpep_11_F@completedpep_17_F@completedpep_49_F@completedpep_50_F','completedpep_T');\" id='completedpep_17_M' value='"+completedpep_17_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='completedpep_17_F'  onblur=\"autosave('completedpep_17_F');autosum('completedpep_11_F@completedpep_17_F@completedpep_49_F@completedpep_50_F','completedpep_F');autosum('completedpep_11_M@completedpep_17_M@completedpep_49_M@completedpep_50_M@completedpep_11_F@completedpep_17_F@completedpep_49_F@completedpep_50_F','completedpep_T');\" id='completedpep_17_F' value='"+completedpep_17_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='completedpep_49_M'  onblur=\"autosave('completedpep_49_M');autosum('completedpep_11_M@completedpep_17_M@completedpep_49_M@completedpep_50_M','completedpep_M');autosum('completedpep_11_M@completedpep_17_M@completedpep_49_M@completedpep_50_M@completedpep_11_F@completedpep_17_F@completedpep_49_F@completedpep_50_F','completedpep_T');\" id='completedpep_49_M' value='"+completedpep_49_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='completedpep_49_F'  onblur=\"autosave('completedpep_49_F');autosum('completedpep_11_F@completedpep_17_F@completedpep_49_F@completedpep_50_F','completedpep_F');autosum('completedpep_11_M@completedpep_17_M@completedpep_49_M@completedpep_50_M@completedpep_11_F@completedpep_17_F@completedpep_49_F@completedpep_50_F','completedpep_T');\" id='completedpep_49_F' value='"+completedpep_49_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='completedpep_50_M'  onblur=\"autosave('completedpep_50_M');autosum('completedpep_11_M@completedpep_17_M@completedpep_49_M@completedpep_50_M','completedpep_M');autosum('completedpep_11_M@completedpep_17_M@completedpep_49_M@completedpep_50_M@completedpep_11_F@completedpep_17_F@completedpep_49_F@completedpep_50_F','completedpep_T');\" id='completedpep_50_M' value='"+completedpep_50_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='completedpep_50_F'  onblur=\"autosave('completedpep_50_F');autosum('completedpep_11_F@completedpep_17_F@completedpep_49_F@completedpep_50_F','completedpep_F');autosum('completedpep_11_M@completedpep_17_M@completedpep_49_M@completedpep_50_M@completedpep_11_F@completedpep_17_F@completedpep_49_F@completedpep_50_F','completedpep_T');\" id='completedpep_50_F' value='"+completedpep_50_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='completedpep_M'  id='completedpep_M' value='"+completedpep_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='completedpep_F'  id='completedpep_F' value='"+completedpep_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='completedpep_T'  id='completedpep_T' value='"+completedpep_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>"
                        
                        //Number seroconverted
                         + "<tr> <td  class='form-actions'><b>Number seroconverted</b></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='seroconverted_11_M'  onblur=\"autosave('seroconverted_11_M');autosum('seroconverted_11_M@seroconverted_17_M@seroconverted_49_M@seroconverted_50_M','seroconverted_M');autosum('seroconverted_11_M@seroconverted_17_M@seroconverted_49_M@seroconverted_50_M@seroconverted_11_F@seroconverted_17_F@seroconverted_49_F@seroconverted_50_F','seroconverted_T');\" id='seroconverted_11_M' value='"+seroconverted_11_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='seroconverted_11_F'  onblur=\"autosave('seroconverted_11_F');autosum('seroconverted_11_F@seroconverted_17_F@seroconverted_49_F@seroconverted_50_F','seroconverted_F');autosum('seroconverted_11_M@seroconverted_17_M@seroconverted_49_M@seroconverted_50_M@seroconverted_11_F@seroconverted_17_F@seroconverted_49_F@seroconverted_50_F','seroconverted_T');\" id='seroconverted_11_F' value='"+seroconverted_11_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='seroconverted_17_M'  onblur=\"autosave('seroconverted_17_M');autosum('seroconverted_11_M@seroconverted_17_M@seroconverted_49_M@seroconverted_50_M','seroconverted_M');autosum('seroconverted_11_M@seroconverted_17_M@seroconverted_49_M@seroconverted_50_M@seroconverted_11_F@seroconverted_17_F@seroconverted_49_F@seroconverted_50_F','seroconverted_T');\" id='seroconverted_17_M' value='"+seroconverted_17_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='seroconverted_17_F'  onblur=\"autosave('seroconverted_17_F');autosum('seroconverted_11_F@seroconverted_17_F@seroconverted_49_F@seroconverted_50_F','seroconverted_F');autosum('seroconverted_11_M@seroconverted_17_M@seroconverted_49_M@seroconverted_50_M@seroconverted_11_F@seroconverted_17_F@seroconverted_49_F@seroconverted_50_F','seroconverted_T');\" id='seroconverted_17_F' value='"+seroconverted_17_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='seroconverted_49_M'  onblur=\"autosave('seroconverted_49_M');autosum('seroconverted_11_M@seroconverted_17_M@seroconverted_49_M@seroconverted_50_M','seroconverted_M');autosum('seroconverted_11_M@seroconverted_17_M@seroconverted_49_M@seroconverted_50_M@seroconverted_11_F@seroconverted_17_F@seroconverted_49_F@seroconverted_50_F','seroconverted_T');\" id='seroconverted_49_M' value='"+seroconverted_49_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='seroconverted_49_F'  onblur=\"autosave('seroconverted_49_F');autosum('seroconverted_11_F@seroconverted_17_F@seroconverted_49_F@seroconverted_50_F','seroconverted_F');autosum('seroconverted_11_M@seroconverted_17_M@seroconverted_49_M@seroconverted_50_M@seroconverted_11_F@seroconverted_17_F@seroconverted_49_F@seroconverted_50_F','seroconverted_T');\" id='seroconverted_49_F' value='"+seroconverted_49_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='seroconverted_50_M'  onblur=\"autosave('seroconverted_50_M');autosum('seroconverted_11_M@seroconverted_17_M@seroconverted_49_M@seroconverted_50_M','seroconverted_M');autosum('seroconverted_11_M@seroconverted_17_M@seroconverted_49_M@seroconverted_50_M@seroconverted_11_F@seroconverted_17_F@seroconverted_49_F@seroconverted_50_F','seroconverted_T');\" id='seroconverted_50_M' value='"+seroconverted_50_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='seroconverted_50_F'  onblur=\"autosave('seroconverted_50_F');autosum('seroconverted_11_F@seroconverted_17_F@seroconverted_49_F@seroconverted_50_F','seroconverted_F');autosum('seroconverted_11_M@seroconverted_17_M@seroconverted_49_M@seroconverted_50_M@seroconverted_11_F@seroconverted_17_F@seroconverted_49_F@seroconverted_50_F','seroconverted_T');\" id='seroconverted_50_F' value='"+seroconverted_50_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='seroconverted_M'  id='seroconverted_M' value='"+seroconverted_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='seroconverted_F'  id='seroconverted_F' value='"+seroconverted_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='seroconverted_T'  id='seroconverted_T' value='"+seroconverted_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>"
                        
                        
                        
                        //Number pregnant
                        
                        
                        
                         + "<tr> <td  class='form-actions'><b>Number pregnant</b></td> "
                        
                        + "<td  class='form-actions'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='Pregnant_11'  onblur=\"autosave('Pregnant_11');autosum('Pregnant_11@Pregnant_17@Pregnant_49@Pregnant_50','Pregnant');autosum('Pregnant_11@Pregnant_17@Pregnant_49@Pregnant_50','Pregnant_T');\" id='Pregnant_11' value='"+Pregnant_11+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='Pregnant_17'  onblur=\"autosave('Pregnant_17');autosum('Pregnant_11@Pregnant_17@Pregnant_49@Pregnant_50','Pregnant');autosum('Pregnant_11@Pregnant_17@Pregnant_49@Pregnant_50','Pregnant_T');\" id='Pregnant_17' value='"+Pregnant_17+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='Pregnant_49'  onblur=\"autosave('Pregnant_49');autosum('Pregnant_11@Pregnant_17@Pregnant_49@Pregnant_50','Pregnant');autosum('Pregnant_11@Pregnant_17@Pregnant_49@Pregnant_50','Pregnant_T');\" id='Pregnant_49' value='"+Pregnant_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='Pregnant_50'  onblur=\"autosave('Pregnant_50');autosum('Pregnant_11@Pregnant_17@Pregnant_49@Pregnant_50','Pregnant');autosum('Pregnant_11@Pregnant_17@Pregnant_49@Pregnant_50','Pregnant_T');\" id='Pregnant_50' value='"+Pregnant_50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='Pregnant'  id='Pregnant' value='"+Pregnant+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='Pregnant_T'  id='Pregnant_T' value='"+Pregnant_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>" 
                       
                        
                        
                        
                        
                        
                        
                        //trauma counselling
                        
                         + "<tr> <td  class='form-actions'><b>Number completed trauma counseling </b></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='counselling_11_M'  onblur=\"autosave('counselling_11_M');autosum('counselling_11_M@counselling_17_M@counselling_49_M@counselling_50_M','counselling_M');autosum('counselling_11_M@counselling_17_M@counselling_49_M@counselling_50_M@counselling_11_F@counselling_17_F@counselling_49_F@counselling_50_F','counselling_T');\" id='counselling_11_M' value='"+counselling_11_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='counselling_11_F'  onblur=\"autosave('counselling_11_F');autosum('counselling_11_F@counselling_17_F@counselling_49_F@counselling_50_F','counselling_F');autosum('counselling_11_M@counselling_17_M@counselling_49_M@counselling_50_M@counselling_11_F@counselling_17_F@counselling_49_F@counselling_50_F','counselling_T');\" id='counselling_11_F' value='"+counselling_11_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='counselling_17_M'  onblur=\"autosave('counselling_17_M');autosum('counselling_11_M@counselling_17_M@counselling_49_M@counselling_50_M','counselling_M');autosum('counselling_11_M@counselling_17_M@counselling_49_M@counselling_50_M@counselling_11_F@counselling_17_F@counselling_49_F@counselling_50_F','counselling_T');\" id='counselling_17_M' value='"+counselling_17_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='counselling_17_F'  onblur=\"autosave('counselling_17_F');autosum('counselling_11_F@counselling_17_F@counselling_49_F@counselling_50_F','counselling_F');autosum('counselling_11_M@counselling_17_M@counselling_49_M@counselling_50_M@counselling_11_F@counselling_17_F@counselling_49_F@counselling_50_F','counselling_T');\" id='counselling_17_F' value='"+counselling_17_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='counselling_49_M'  onblur=\"autosave('counselling_49_M');autosum('counselling_11_M@counselling_17_M@counselling_49_M@counselling_50_M','counselling_M');autosum('counselling_11_M@counselling_17_M@counselling_49_M@counselling_50_M@counselling_11_F@counselling_17_F@counselling_49_F@counselling_50_F','counselling_T');\" id='counselling_49_M' value='"+counselling_49_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='counselling_49_F'  onblur=\"autosave('counselling_49_F');autosum('counselling_11_F@counselling_17_F@counselling_49_F@counselling_50_F','counselling_F');autosum('counselling_11_M@counselling_17_M@counselling_49_M@counselling_50_M@counselling_11_F@counselling_17_F@counselling_49_F@counselling_50_F','counselling_T');\" id='counselling_49_F' value='"+counselling_49_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='counselling_50_M'  onblur=\"autosave('counselling_50_M');autosum('counselling_11_M@counselling_17_M@counselling_49_M@counselling_50_M','counselling_M');autosum('counselling_11_M@counselling_17_M@counselling_49_M@counselling_50_M@counselling_11_F@counselling_17_F@counselling_49_F@counselling_50_F','counselling_T');\" id='counselling_50_M' value='"+counselling_50_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input "+islocked+" type='text'  name='counselling_50_F'  onblur=\"autosave('counselling_50_F');autosum('counselling_11_F@counselling_17_F@counselling_49_F@counselling_50_F','counselling_F');autosum('counselling_11_M@counselling_17_M@counselling_49_M@counselling_50_M@counselling_11_F@counselling_17_F@counselling_49_F@counselling_50_F','counselling_T');\" id='counselling_50_F' value='"+counselling_50_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='counselling_M'  id='counselling_M' value='"+counselling_M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "<td  class='form-actions'><input readonly tabindex='-1' type='text'  name='counselling_F'  id='counselling_F' value='"+counselling_F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        
                        
                        + "<td  class='form-actions'><input readonly tabindex='-1'  type='text'  name='counselling_T'  id='counselling_T' value='"+counselling_T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> "
                        + "</tr>"
                        
                        
                        + " </table> </fieldset> ";
                sectionb_tab+=sectionb_ext;
                
                
                
                
                
                
                sectiona_tab+="</div></div></div>";
                sectionb_tab+="</div></div></div>";
                
                
                data+=validitychecker+" "+ ul+" "+sectionapane+""+sectionbpane+validatepane+" "+mainpane;
                //add the headers based on if the header is active
                
                if(session.getAttribute("forms_holder").toString().contains(",SGBV")){
                    data+=sectiona_tab;
                }
                
                if(session.getAttribute("forms_holder").toString().contains(",SGBV")){
                    data+=sectionb_tab;
                }
                
                
                
                
                data+="<div class='form-actions'>" +
                        "                              <button type='submit' class='btn blue'>Run Validation</button>" +
                        "                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   "+ enterdby+"" +
                        "                           </div>";
                data+="  </div> ";
            } catch (SQLException ex) {
                Logger.getLogger(loadsgbv.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
           else{
               data="<font color=\"red\" size=\"6px;\" ><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support SGBV data.</font>";
               }
          //  System.out.println("**"+data+"elkaaaaaaaaaant");
           
              String unvalidatedLink="";int counter=0;
     if(invalidSGBV>0 ){
            try {
                String getUnvalidated="SELECT sgbv.SubPartnerID,subpartnera.SubPartnerNom FROM sgbv JOIN subpartnera ON sgbv.SubPartnerID=subpartnera.SubPartnerID WHERE subpartnera.DistrictID='"+subcountyid+"' AND sgbv.Mois='"+month+"' AND sgbv.Annee='"+year+"' AND sgbv.isValidated='0'";
                conn.rs=conn.st.executeQuery(getUnvalidated);
                while(conn.rs.next()){
                    counter++;
//     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=Form731.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;
                    unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=loadsgbv.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;
                }      } catch (SQLException ex) {
                Logger.getLogger(loadsgbv.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
     
    data+="<p hidden=\"true\" id=\"invalidatedData\">"+unvalidatedLink+"</p>"; 
     
       

            out.println(data);
         try{
               if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}
         if(conn.rs1!=null){ conn.rs1.close();}
         if(conn.rs2!=null){ conn.rs2.close();}
         if(conn.st!=null){ conn.st.close();}
         if(conn.st2!=null){ conn.st2.close();}
        } 
          catch (SQLException ex) {
                      Logger.getLogger(loadsgbv.class.getName()).log(Level.SEVERE, null, ex);
                  }
         
         finally {
            
            out.close();
        }
        
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * FPCocRparam request servlet request
     * FPCocRparam response servlet response
     * FPCocRthrows ServletException if a servlet-specific error occurs
     * FPCocRthrows IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * FPCocRparam request servlet request
     * FPCocRparam response servlet response
     * FPCocRthrows ServletException if a servlet-specific error occurs
     * FPCocRthrows IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * FPCocRreturn a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
