/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InternalSystem;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emmanuel Kaunda
 */
public class validatesgbv_new extends HttpServlet {
HttpSession session=null;

String Userid;
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       String data_elements,description=""; 
    response.setContentType("text/html;charset=UTF-8");
  try{
    session =request.getSession();
        PrintWriter out = response.getWriter();
    
//    String col=request.getParameter("col");
//    String achieved=request.getParameter("achieved");
//    
    
    dbConn conn=new dbConn();
//get the existing data for the month, year and facility that is already on session

String month="";      
String year="";      
String facil="";
String userid="unknown";
 String error="";

//        if(achieved.equals("")){achieved="0";}
    if(session.getAttribute("userid")!=null){        
userid=session.getAttribute("userid").toString();
}

if(session.getAttribute("year")!=null){        
year=session.getAttribute("year").toString();
}
  if(session.getAttribute("monthid")!=null){        
month=session.getAttribute("monthid").toString();
}

    if(session.getAttribute("facilityid")!=null){        
facil=session.getAttribute("facilityid").toString();
}
    

  
String rapesurvivor_1_M="0";
String rapesurvivor_1_F="0";
String rapesurvivor_4_M="0";
String rapesurvivor_4_F="0";
String rapesurvivor_9_M="0";
String rapesurvivor_9_F="0";
String rapesurvivor_14_M="0";
String rapesurvivor_14_F="0";
String rapesurvivor_19_M="0";
String rapesurvivor_19_F="0";
String rapesurvivor_24_M="0";
String rapesurvivor_24_F="0";
String rapesurvivor_29_M="0";
String rapesurvivor_29_F="0";
String rapesurvivor_34_M="0";
String rapesurvivor_34_F="0";
String rapesurvivor_39_M="0";
String rapesurvivor_39_F="0";
String rapesurvivor_49_M="0";
String rapesurvivor_49_F="0";
String rapesurvivor_50_M="0";
String rapesurvivor_50_F="0";
String rapesurvivor_M="0";
String rapesurvivor_F="0";
String rapesurvivor_T="0";
String presenting_72hr_1_M="0";
String presenting_72hr_1_F="0";
String presenting_72hr_4_M="0";
String presenting_72hr_4_F="0";
String presenting_72hr_9_M="0";
String presenting_72hr_9_F="0";
String presenting_72hr_14_M="0";
String presenting_72hr_14_F="0";
String presenting_72hr_19_M="0";
String presenting_72hr_19_F="0";
String presenting_72hr_24_M="0";
String presenting_72hr_24_F="0";
String presenting_72hr_29_M="0";
String presenting_72hr_29_F="0";
String presenting_72hr_34_M="0";
String presenting_72hr_34_F="0";
String presenting_72hr_39_M="0";
String presenting_72hr_39_F="0";
String presenting_72hr_49_M="0";
String presenting_72hr_49_F="0";
String presenting_72hr_50_M="0";
String presenting_72hr_50_F="0";
String presenting_72hr_M="0";
String presenting_72hr_F="0";
String presenting_72hr_T="0";
String initiatedpep_1_M="0";
String initiatedpep_1_F="0";
String initiatedpep_4_M="0";
String initiatedpep_4_F="0";
String initiatedpep_9_M="0";
String initiatedpep_9_F="0";
String initiatedpep_14_M="0";
String initiatedpep_14_F="0";
String initiatedpep_19_M="0";
String initiatedpep_19_F="0";
String initiatedpep_24_M="0";
String initiatedpep_24_F="0";
String initiatedpep_29_M="0";
String initiatedpep_29_F="0";
String initiatedpep_34_M="0";
String initiatedpep_34_F="0";
String initiatedpep_39_M="0";
String initiatedpep_39_F="0";
String initiatedpep_49_M="0";
String initiatedpep_49_F="0";
String initiatedpep_50_M="0";
String initiatedpep_50_F="0";
String initiatedpep_M="0";
String initiatedpep_F="0";
String initiatedpep_T="0";
String sti_1_M="0";
String sti_1_F="0";
String sti_4_M="0";
String sti_4_F="0";
String sti_9_M="0";
String sti_9_F="0";
String sti_14_M="0";
String sti_14_F="0";
String sti_19_M="0";
String sti_19_F="0";
String sti_24_M="0";
String sti_24_F="0";
String sti_29_M="0";
String sti_29_F="0";
String sti_34_M="0";
String sti_34_F="0";
String sti_39_M="0";
String sti_39_F="0";
String sti_49_M="0";
String sti_49_F="0";
String sti_50_M="0";
String sti_50_F="0";
String sti_M="0";
String sti_F="0";
String sti_T="0";
String ecp_14_F="0";
String ecp_19_F="0";
String ecp_24_F="0";
String ecp_29_F="0";
String ecp_34_F="0";
String ecp_39_F="0";
String ecp_49_F="0";
String ecp_50_F="0";
String ecp_F="0";
String ecp_T="0";
String pill_14_F="0";
String pill_19_F="0";
String pill_24_F="0";
String pill_29_F="0";
String pill_34_F="0";
String pill_39_F="0";
String pill_49_F="0";
String pill_50_F="0";
String pill_F="0";
String pill_T="0";
String tested_1_M="0";
String tested_1_F="0";
String tested_4_M="0";
String tested_4_F="0";
String tested_9_M="0";
String tested_9_F="0";
String tested_14_M="0";
String tested_14_F="0";
String tested_19_M="0";
String tested_19_F="0";
String tested_24_M="0";
String tested_24_F="0";
String tested_29_M="0";
String tested_29_F="0";
String tested_34_M="0";
String tested_34_F="0";
String tested_39_M="0";
String tested_39_F="0";
String tested_49_M="0";
String tested_49_F="0";
String tested_50_M="0";
String tested_50_F="0";
String tested_M="0";
String tested_F="0";
String tested_T="0";
String positive_1_M="0";
String positive_1_F="0";
String positive_4_M="0";
String positive_4_F="0";
String positive_9_M="0";
String positive_9_F="0";
String positive_14_M="0";
String positive_14_F="0";
String positive_19_M="0";
String positive_19_F="0";
String positive_24_M="0";
String positive_24_F="0";
String positive_29_M="0";
String positive_29_F="0";
String positive_34_M="0";
String positive_34_F="0";
String positive_39_M="0";
String positive_39_F="0";
String positive_49_M="0";
String positive_49_F="0";
String positive_50_M="0";
String positive_50_F="0";
String positive_M="0";
String positive_F="0";
String positive_T="0";
String disability_1_M="0";
String disability_1_F="0";
String disability_4_M="0";
String disability_4_F="0";
String disability_9_M="0";
String disability_9_F="0";
String disability_14_M="0";
String disability_14_F="0";
String disability_19_M="0";
String disability_19_F="0";
String disability_24_M="0";
String disability_24_F="0";
String disability_29_M="0";
String disability_29_F="0";
String disability_34_M="0";
String disability_34_F="0";
String disability_39_M="0";
String disability_39_F="0";
String disability_49_M="0";
String disability_49_F="0";
String disability_50_M="0";
String disability_50_F="0";
String disability_M="0";
String disability_F="0";
String disability_T="0";
String perpetrators_1_M="0";
String perpetrators_1_F="0";
String perpetrators_4_M="0";
String perpetrators_4_F="0";
String perpetrators_9_M="0";
String perpetrators_9_F="0";
String perpetrators_14_M="0";
String perpetrators_14_F="0";
String perpetrators_19_M="0";
String perpetrators_19_F="0";
String perpetrators_24_M="0";
String perpetrators_24_F="0";
String perpetrators_29_M="0";
String perpetrators_29_F="0";
String perpetrators_34_M="0";
String perpetrators_34_F="0";
String perpetrators_39_M="0";
String perpetrators_39_F="0";
String perpetrators_49_M="0";
String perpetrators_49_F="0";
String perpetrators_50_M="0";
String perpetrators_50_F="0";
String perpetrators_M="0";
String perpetrators_F="0";
String perpetrators_T="0";
String visit1_1_M="0";
String visit1_1_F="0";
String visit1_4_M="0";
String visit1_4_F="0";
String visit1_9_M="0";
String visit1_9_F="0";
String visit1_14_M="0";
String visit1_14_F="0";
String visit1_19_M="0";
String visit1_19_F="0";
String visit1_24_M="0";
String visit1_24_F="0";
String visit1_29_M="0";
String visit1_29_F="0";
String visit1_34_M="0";
String visit1_34_F="0";
String visit1_39_M="0";
String visit1_39_F="0";
String visit1_49_M="0";
String visit1_49_F="0";
String visit1_50_M="0";
String visit1_50_F="0";
String visit1_M="0";
String visit1_F="0";
String visit1_T="0";
String visit2_1_M="0";
String visit2_1_F="0";
String visit2_4_M="0";
String visit2_4_F="0";
String visit2_9_M="0";
String visit2_9_F="0";
String visit2_14_M="0";
String visit2_14_F="0";
String visit2_19_M="0";
String visit2_19_F="0";
String visit2_24_M="0";
String visit2_24_F="0";
String visit2_29_M="0";
String visit2_29_F="0";
String visit2_34_M="0";
String visit2_34_F="0";
String visit2_39_M="0";
String visit2_39_F="0";
String visit2_49_M="0";
String visit2_49_F="0";
String visit2_50_M="0";
String visit2_50_F="0";
String visit2_M="0";
String visit2_F="0";
String visit2_T="0";
String visit3_1_M="0";
String visit3_1_F="0";
String visit3_4_M="0";
String visit3_4_F="0";
String visit3_9_M="0";
String visit3_9_F="0";
String visit3_14_M="0";
String visit3_14_F="0";
String visit3_19_M="0";
String visit3_19_F="0";
String visit3_24_M="0";
String visit3_24_F="0";
String visit3_29_M="0";
String visit3_29_F="0";
String visit3_34_M="0";
String visit3_34_F="0";
String visit3_39_M="0";
String visit3_39_F="0";
String visit3_49_M="0";
String visit3_49_F="0";
String visit3_50_M="0";
String visit3_50_F="0";
String visit3_M="0";
String visit3_F="0";
String visit3_T="0";
String visit4_1_M="0";
String visit4_1_F="0";
String visit4_4_M="0";
String visit4_4_F="0";
String visit4_9_M="0";
String visit4_9_F="0";
String visit4_14_M="0";
String visit4_14_F="0";
String visit4_19_M="0";
String visit4_19_F="0";
String visit4_24_M="0";
String visit4_24_F="0";
String visit4_29_M="0";
String visit4_29_F="0";
String visit4_34_M="0";
String visit4_34_F="0";
String visit4_39_M="0";
String visit4_39_F="0";
String visit4_49_M="0";
String visit4_49_F="0";
String visit4_50_M="0";
String visit4_50_F="0";
String visit4_M="0";
String visit4_F="0";
String visit4_T="0";
String visit5_1_M="0";
String visit5_1_F="0";
String visit5_4_M="0";
String visit5_4_F="0";
String visit5_9_M="0";
String visit5_9_F="0";
String visit5_14_M="0";
String visit5_14_F="0";
String visit5_19_M="0";
String visit5_19_F="0";
String visit5_24_M="0";
String visit5_24_F="0";
String visit5_29_M="0";
String visit5_29_F="0";
String visit5_34_M="0";
String visit5_34_F="0";
String visit5_39_M="0";
String visit5_39_F="0";
String visit5_49_M="0";
String visit5_49_F="0";
String visit5_50_M="0";
String visit5_50_F="0";
String visit5_M="0";
String visit5_F="0";
String visit5_T="0";
String completedpep_1_M="0";
String completedpep_1_F="0";
String completedpep_4_M="0";
String completedpep_4_F="0";
String completedpep_9_M="0";
String completedpep_9_F="0";
String completedpep_14_M="0";
String completedpep_14_F="0";
String completedpep_19_M="0";
String completedpep_19_F="0";
String completedpep_24_M="0";
String completedpep_24_F="0";
String completedpep_29_M="0";
String completedpep_29_F="0";
String completedpep_34_M="0";
String completedpep_34_F="0";
String completedpep_39_M="0";
String completedpep_39_F="0";
String completedpep_49_M="0";
String completedpep_49_F="0";
String completedpep_50_M="0";
String completedpep_50_F="0";
String completedpep_M="0";
String completedpep_F="0";
String completedpep_T="0";
String seroconverted_1_M="0";
String seroconverted_1_F="0";
String seroconverted_4_M="0";
String seroconverted_4_F="0";
String seroconverted_9_M="0";
String seroconverted_9_F="0";
String seroconverted_14_M="0";
String seroconverted_14_F="0";
String seroconverted_19_M="0";
String seroconverted_19_F="0";
String seroconverted_24_M="0";
String seroconverted_24_F="0";
String seroconverted_29_M="0";
String seroconverted_29_F="0";
String seroconverted_34_M="0";
String seroconverted_34_F="0";
String seroconverted_39_M="0";
String seroconverted_39_F="0";
String seroconverted_49_M="0";
String seroconverted_49_F="0";
String seroconverted_50_M="0";
String seroconverted_50_F="0";
String seroconverted_M="0";
String seroconverted_F="0";
String seroconverted_T="0";
String Pregnant_14_F="0";
String Pregnant_19_F="0";
String Pregnant_24_F="0";
String Pregnant_29_F="0";
String Pregnant_34_F="0";
String Pregnant_39_F="0";
String Pregnant_49_F="0";
String Pregnant_50_F="0";
String Pregnant_F="0";
String Pregnant_T="0";
String counselling_1_M="0";
String counselling_1_F="0";
String counselling_4_M="0";
String counselling_4_F="0";
String counselling_9_M="0";
String counselling_9_F="0";
String counselling_14_M="0";
String counselling_14_F="0";
String counselling_19_M="0";
String counselling_19_F="0";
String counselling_24_M="0";
String counselling_24_F="0";
String counselling_29_M="0";
String counselling_29_F="0";
String counselling_34_M="0";
String counselling_34_F="0";
String counselling_39_M="0";
String counselling_39_F="0";
String counselling_49_M="0";
String counselling_49_F="0";
String counselling_50_M="0";
String counselling_50_F="0";
String counselling_M="0";
String counselling_F="0";
String counselling_T="0";


 
 
           
String tableid=year+"_"+month+"_"+facil;

 
 if(request.getParameter("rapesurvivor_1_M").trim().equals("")){rapesurvivor_1_M="0";} else {rapesurvivor_1_M=request.getParameter("rapesurvivor_1_M");}
 if(request.getParameter("rapesurvivor_1_F").trim().equals("")){rapesurvivor_1_F="0";} else {rapesurvivor_1_F=request.getParameter("rapesurvivor_1_F");}
 if(request.getParameter("rapesurvivor_4_M").trim().equals("")){rapesurvivor_4_M="0";} else {rapesurvivor_4_M=request.getParameter("rapesurvivor_4_M");}
 if(request.getParameter("rapesurvivor_4_F").trim().equals("")){rapesurvivor_4_F="0";} else {rapesurvivor_4_F=request.getParameter("rapesurvivor_4_F");}
 if(request.getParameter("rapesurvivor_9_M").trim().equals("")){rapesurvivor_9_M="0";} else {rapesurvivor_9_M=request.getParameter("rapesurvivor_9_M");}
 if(request.getParameter("rapesurvivor_9_F").trim().equals("")){rapesurvivor_9_F="0";} else {rapesurvivor_9_F=request.getParameter("rapesurvivor_9_F");}
 if(request.getParameter("rapesurvivor_14_M").trim().equals("")){rapesurvivor_14_M="0";} else {rapesurvivor_14_M=request.getParameter("rapesurvivor_14_M");}
 if(request.getParameter("rapesurvivor_14_F").trim().equals("")){rapesurvivor_14_F="0";} else {rapesurvivor_14_F=request.getParameter("rapesurvivor_14_F");}
 if(request.getParameter("rapesurvivor_19_M").trim().equals("")){rapesurvivor_19_M="0";} else {rapesurvivor_19_M=request.getParameter("rapesurvivor_19_M");}
 if(request.getParameter("rapesurvivor_19_F").trim().equals("")){rapesurvivor_19_F="0";} else {rapesurvivor_19_F=request.getParameter("rapesurvivor_19_F");}
 if(request.getParameter("rapesurvivor_24_M").trim().equals("")){rapesurvivor_24_M="0";} else {rapesurvivor_24_M=request.getParameter("rapesurvivor_24_M");}
 if(request.getParameter("rapesurvivor_24_F").trim().equals("")){rapesurvivor_24_F="0";} else {rapesurvivor_24_F=request.getParameter("rapesurvivor_24_F");}
 if(request.getParameter("rapesurvivor_29_M").trim().equals("")){rapesurvivor_29_M="0";} else {rapesurvivor_29_M=request.getParameter("rapesurvivor_29_M");}
 if(request.getParameter("rapesurvivor_29_F").trim().equals("")){rapesurvivor_29_F="0";} else {rapesurvivor_29_F=request.getParameter("rapesurvivor_29_F");}
 if(request.getParameter("rapesurvivor_34_M").trim().equals("")){rapesurvivor_34_M="0";} else {rapesurvivor_34_M=request.getParameter("rapesurvivor_34_M");}
 if(request.getParameter("rapesurvivor_34_F").trim().equals("")){rapesurvivor_34_F="0";} else {rapesurvivor_34_F=request.getParameter("rapesurvivor_34_F");}
 if(request.getParameter("rapesurvivor_39_M").trim().equals("")){rapesurvivor_39_M="0";} else {rapesurvivor_39_M=request.getParameter("rapesurvivor_39_M");}
 if(request.getParameter("rapesurvivor_39_F").trim().equals("")){rapesurvivor_39_F="0";} else {rapesurvivor_39_F=request.getParameter("rapesurvivor_39_F");}
 if(request.getParameter("rapesurvivor_49_M").trim().equals("")){rapesurvivor_49_M="0";} else {rapesurvivor_49_M=request.getParameter("rapesurvivor_49_M");}
 if(request.getParameter("rapesurvivor_49_F").trim().equals("")){rapesurvivor_49_F="0";} else {rapesurvivor_49_F=request.getParameter("rapesurvivor_49_F");}
 if(request.getParameter("rapesurvivor_50_M").trim().equals("")){rapesurvivor_50_M="0";} else {rapesurvivor_50_M=request.getParameter("rapesurvivor_50_M");}
 if(request.getParameter("rapesurvivor_50_F").trim().equals("")){rapesurvivor_50_F="0";} else {rapesurvivor_50_F=request.getParameter("rapesurvivor_50_F");}
 if(request.getParameter("rapesurvivor_M").trim().equals("")){rapesurvivor_M="0";} else {rapesurvivor_M=request.getParameter("rapesurvivor_M");}
 if(request.getParameter("rapesurvivor_F").trim().equals("")){rapesurvivor_F="0";} else {rapesurvivor_F=request.getParameter("rapesurvivor_F");}
 if(request.getParameter("rapesurvivor_T").trim().equals("")){rapesurvivor_T="0";} else {rapesurvivor_T=request.getParameter("rapesurvivor_T");}
 if(request.getParameter("presenting_72hr_1_M").trim().equals("")){presenting_72hr_1_M="0";} else {presenting_72hr_1_M=request.getParameter("presenting_72hr_1_M");}
 if(request.getParameter("presenting_72hr_1_F").trim().equals("")){presenting_72hr_1_F="0";} else {presenting_72hr_1_F=request.getParameter("presenting_72hr_1_F");}
 if(request.getParameter("presenting_72hr_4_M").trim().equals("")){presenting_72hr_4_M="0";} else {presenting_72hr_4_M=request.getParameter("presenting_72hr_4_M");}
 if(request.getParameter("presenting_72hr_4_F").trim().equals("")){presenting_72hr_4_F="0";} else {presenting_72hr_4_F=request.getParameter("presenting_72hr_4_F");}
 if(request.getParameter("presenting_72hr_9_M").trim().equals("")){presenting_72hr_9_M="0";} else {presenting_72hr_9_M=request.getParameter("presenting_72hr_9_M");}
 if(request.getParameter("presenting_72hr_9_F").trim().equals("")){presenting_72hr_9_F="0";} else {presenting_72hr_9_F=request.getParameter("presenting_72hr_9_F");}
 if(request.getParameter("presenting_72hr_14_M").trim().equals("")){presenting_72hr_14_M="0";} else {presenting_72hr_14_M=request.getParameter("presenting_72hr_14_M");}
 if(request.getParameter("presenting_72hr_14_F").trim().equals("")){presenting_72hr_14_F="0";} else {presenting_72hr_14_F=request.getParameter("presenting_72hr_14_F");}
 if(request.getParameter("presenting_72hr_19_M").trim().equals("")){presenting_72hr_19_M="0";} else {presenting_72hr_19_M=request.getParameter("presenting_72hr_19_M");}
 if(request.getParameter("presenting_72hr_19_F").trim().equals("")){presenting_72hr_19_F="0";} else {presenting_72hr_19_F=request.getParameter("presenting_72hr_19_F");}
 if(request.getParameter("presenting_72hr_24_M").trim().equals("")){presenting_72hr_24_M="0";} else {presenting_72hr_24_M=request.getParameter("presenting_72hr_24_M");}
 if(request.getParameter("presenting_72hr_24_F").trim().equals("")){presenting_72hr_24_F="0";} else {presenting_72hr_24_F=request.getParameter("presenting_72hr_24_F");}
 if(request.getParameter("presenting_72hr_29_M").trim().equals("")){presenting_72hr_29_M="0";} else {presenting_72hr_29_M=request.getParameter("presenting_72hr_29_M");}
 if(request.getParameter("presenting_72hr_29_F").trim().equals("")){presenting_72hr_29_F="0";} else {presenting_72hr_29_F=request.getParameter("presenting_72hr_29_F");}
 if(request.getParameter("presenting_72hr_34_M").trim().equals("")){presenting_72hr_34_M="0";} else {presenting_72hr_34_M=request.getParameter("presenting_72hr_34_M");}
 if(request.getParameter("presenting_72hr_34_F").trim().equals("")){presenting_72hr_34_F="0";} else {presenting_72hr_34_F=request.getParameter("presenting_72hr_34_F");}
 if(request.getParameter("presenting_72hr_39_M").trim().equals("")){presenting_72hr_39_M="0";} else {presenting_72hr_39_M=request.getParameter("presenting_72hr_39_M");}
 if(request.getParameter("presenting_72hr_39_F").trim().equals("")){presenting_72hr_39_F="0";} else {presenting_72hr_39_F=request.getParameter("presenting_72hr_39_F");}
 if(request.getParameter("presenting_72hr_49_M").trim().equals("")){presenting_72hr_49_M="0";} else {presenting_72hr_49_M=request.getParameter("presenting_72hr_49_M");}
 if(request.getParameter("presenting_72hr_49_F").trim().equals("")){presenting_72hr_49_F="0";} else {presenting_72hr_49_F=request.getParameter("presenting_72hr_49_F");}
 if(request.getParameter("presenting_72hr_50_M").trim().equals("")){presenting_72hr_50_M="0";} else {presenting_72hr_50_M=request.getParameter("presenting_72hr_50_M");}
 if(request.getParameter("presenting_72hr_50_F").trim().equals("")){presenting_72hr_50_F="0";} else {presenting_72hr_50_F=request.getParameter("presenting_72hr_50_F");}
 if(request.getParameter("presenting_72hr_M").trim().equals("")){presenting_72hr_M="0";} else {presenting_72hr_M=request.getParameter("presenting_72hr_M");}
 if(request.getParameter("presenting_72hr_F").trim().equals("")){presenting_72hr_F="0";} else {presenting_72hr_F=request.getParameter("presenting_72hr_F");}
 if(request.getParameter("presenting_72hr_T").trim().equals("")){presenting_72hr_T="0";} else {presenting_72hr_T=request.getParameter("presenting_72hr_T");}
 if(request.getParameter("initiatedpep_1_M").trim().equals("")){initiatedpep_1_M="0";} else {initiatedpep_1_M=request.getParameter("initiatedpep_1_M");}
 if(request.getParameter("initiatedpep_1_F").trim().equals("")){initiatedpep_1_F="0";} else {initiatedpep_1_F=request.getParameter("initiatedpep_1_F");}
 if(request.getParameter("initiatedpep_4_M").trim().equals("")){initiatedpep_4_M="0";} else {initiatedpep_4_M=request.getParameter("initiatedpep_4_M");}
 if(request.getParameter("initiatedpep_4_F").trim().equals("")){initiatedpep_4_F="0";} else {initiatedpep_4_F=request.getParameter("initiatedpep_4_F");}
 if(request.getParameter("initiatedpep_9_M").trim().equals("")){initiatedpep_9_M="0";} else {initiatedpep_9_M=request.getParameter("initiatedpep_9_M");}
 if(request.getParameter("initiatedpep_9_F").trim().equals("")){initiatedpep_9_F="0";} else {initiatedpep_9_F=request.getParameter("initiatedpep_9_F");}
 if(request.getParameter("initiatedpep_14_M").trim().equals("")){initiatedpep_14_M="0";} else {initiatedpep_14_M=request.getParameter("initiatedpep_14_M");}
 if(request.getParameter("initiatedpep_14_F").trim().equals("")){initiatedpep_14_F="0";} else {initiatedpep_14_F=request.getParameter("initiatedpep_14_F");}
 if(request.getParameter("initiatedpep_19_M").trim().equals("")){initiatedpep_19_M="0";} else {initiatedpep_19_M=request.getParameter("initiatedpep_19_M");}
 if(request.getParameter("initiatedpep_19_F").trim().equals("")){initiatedpep_19_F="0";} else {initiatedpep_19_F=request.getParameter("initiatedpep_19_F");}
 if(request.getParameter("initiatedpep_24_M").trim().equals("")){initiatedpep_24_M="0";} else {initiatedpep_24_M=request.getParameter("initiatedpep_24_M");}
 if(request.getParameter("initiatedpep_24_F").trim().equals("")){initiatedpep_24_F="0";} else {initiatedpep_24_F=request.getParameter("initiatedpep_24_F");}
 if(request.getParameter("initiatedpep_29_M").trim().equals("")){initiatedpep_29_M="0";} else {initiatedpep_29_M=request.getParameter("initiatedpep_29_M");}
 if(request.getParameter("initiatedpep_29_F").trim().equals("")){initiatedpep_29_F="0";} else {initiatedpep_29_F=request.getParameter("initiatedpep_29_F");}
 if(request.getParameter("initiatedpep_34_M").trim().equals("")){initiatedpep_34_M="0";} else {initiatedpep_34_M=request.getParameter("initiatedpep_34_M");}
 if(request.getParameter("initiatedpep_34_F").trim().equals("")){initiatedpep_34_F="0";} else {initiatedpep_34_F=request.getParameter("initiatedpep_34_F");}
 if(request.getParameter("initiatedpep_39_M").trim().equals("")){initiatedpep_39_M="0";} else {initiatedpep_39_M=request.getParameter("initiatedpep_39_M");}
 if(request.getParameter("initiatedpep_39_F").trim().equals("")){initiatedpep_39_F="0";} else {initiatedpep_39_F=request.getParameter("initiatedpep_39_F");}
 if(request.getParameter("initiatedpep_49_M").trim().equals("")){initiatedpep_49_M="0";} else {initiatedpep_49_M=request.getParameter("initiatedpep_49_M");}
 if(request.getParameter("initiatedpep_49_F").trim().equals("")){initiatedpep_49_F="0";} else {initiatedpep_49_F=request.getParameter("initiatedpep_49_F");}
 if(request.getParameter("initiatedpep_50_M").trim().equals("")){initiatedpep_50_M="0";} else {initiatedpep_50_M=request.getParameter("initiatedpep_50_M");}
 if(request.getParameter("initiatedpep_50_F").trim().equals("")){initiatedpep_50_F="0";} else {initiatedpep_50_F=request.getParameter("initiatedpep_50_F");}
 if(request.getParameter("initiatedpep_M").trim().equals("")){initiatedpep_M="0";} else {initiatedpep_M=request.getParameter("initiatedpep_M");}
 if(request.getParameter("initiatedpep_F").trim().equals("")){initiatedpep_F="0";} else {initiatedpep_F=request.getParameter("initiatedpep_F");}
 if(request.getParameter("initiatedpep_T").trim().equals("")){initiatedpep_T="0";} else {initiatedpep_T=request.getParameter("initiatedpep_T");}
 if(request.getParameter("sti_1_M").trim().equals("")){sti_1_M="0";} else {sti_1_M=request.getParameter("sti_1_M");}
 if(request.getParameter("sti_1_F").trim().equals("")){sti_1_F="0";} else {sti_1_F=request.getParameter("sti_1_F");}
 if(request.getParameter("sti_4_M").trim().equals("")){sti_4_M="0";} else {sti_4_M=request.getParameter("sti_4_M");}
 if(request.getParameter("sti_4_F").trim().equals("")){sti_4_F="0";} else {sti_4_F=request.getParameter("sti_4_F");}
 if(request.getParameter("sti_9_M").trim().equals("")){sti_9_M="0";} else {sti_9_M=request.getParameter("sti_9_M");}
 if(request.getParameter("sti_9_F").trim().equals("")){sti_9_F="0";} else {sti_9_F=request.getParameter("sti_9_F");}
 if(request.getParameter("sti_14_M").trim().equals("")){sti_14_M="0";} else {sti_14_M=request.getParameter("sti_14_M");}
 if(request.getParameter("sti_14_F").trim().equals("")){sti_14_F="0";} else {sti_14_F=request.getParameter("sti_14_F");}
 if(request.getParameter("sti_19_M").trim().equals("")){sti_19_M="0";} else {sti_19_M=request.getParameter("sti_19_M");}
 if(request.getParameter("sti_19_F").trim().equals("")){sti_19_F="0";} else {sti_19_F=request.getParameter("sti_19_F");}
 if(request.getParameter("sti_24_M").trim().equals("")){sti_24_M="0";} else {sti_24_M=request.getParameter("sti_24_M");}
 if(request.getParameter("sti_24_F").trim().equals("")){sti_24_F="0";} else {sti_24_F=request.getParameter("sti_24_F");}
 if(request.getParameter("sti_29_M").trim().equals("")){sti_29_M="0";} else {sti_29_M=request.getParameter("sti_29_M");}
 if(request.getParameter("sti_29_F").trim().equals("")){sti_29_F="0";} else {sti_29_F=request.getParameter("sti_29_F");}
 if(request.getParameter("sti_34_M").trim().equals("")){sti_34_M="0";} else {sti_34_M=request.getParameter("sti_34_M");}
 if(request.getParameter("sti_34_F").trim().equals("")){sti_34_F="0";} else {sti_34_F=request.getParameter("sti_34_F");}
 if(request.getParameter("sti_39_M").trim().equals("")){sti_39_M="0";} else {sti_39_M=request.getParameter("sti_39_M");}
 if(request.getParameter("sti_39_F").trim().equals("")){sti_39_F="0";} else {sti_39_F=request.getParameter("sti_39_F");}
 if(request.getParameter("sti_49_M").trim().equals("")){sti_49_M="0";} else {sti_49_M=request.getParameter("sti_49_M");}
 if(request.getParameter("sti_49_F").trim().equals("")){sti_49_F="0";} else {sti_49_F=request.getParameter("sti_49_F");}
 if(request.getParameter("sti_50_M").trim().equals("")){sti_50_M="0";} else {sti_50_M=request.getParameter("sti_50_M");}
 if(request.getParameter("sti_50_F").trim().equals("")){sti_50_F="0";} else {sti_50_F=request.getParameter("sti_50_F");}
 if(request.getParameter("sti_M").trim().equals("")){sti_M="0";} else {sti_M=request.getParameter("sti_M");}
 if(request.getParameter("sti_F").trim().equals("")){sti_F="0";} else {sti_F=request.getParameter("sti_F");}
 if(request.getParameter("sti_T").trim().equals("")){sti_T="0";} else {sti_T=request.getParameter("sti_T");}
 if(request.getParameter("ecp_14_F").trim().equals("")){ecp_14_F="0";} else {ecp_14_F=request.getParameter("ecp_14_F");}
 if(request.getParameter("ecp_19_F").trim().equals("")){ecp_19_F="0";} else {ecp_19_F=request.getParameter("ecp_19_F");}
 if(request.getParameter("ecp_24_F").trim().equals("")){ecp_24_F="0";} else {ecp_24_F=request.getParameter("ecp_24_F");}
 if(request.getParameter("ecp_29_F").trim().equals("")){ecp_29_F="0";} else {ecp_29_F=request.getParameter("ecp_29_F");}
 if(request.getParameter("ecp_34_F").trim().equals("")){ecp_34_F="0";} else {ecp_34_F=request.getParameter("ecp_34_F");}
 if(request.getParameter("ecp_39_F").trim().equals("")){ecp_39_F="0";} else {ecp_39_F=request.getParameter("ecp_39_F");}
 if(request.getParameter("ecp_49_F").trim().equals("")){ecp_49_F="0";} else {ecp_49_F=request.getParameter("ecp_49_F");}
 if(request.getParameter("ecp_50_F").trim().equals("")){ecp_50_F="0";} else {ecp_50_F=request.getParameter("ecp_50_F");}
 if(request.getParameter("ecp_F").trim().equals("")){ecp_F="0";} else {ecp_F=request.getParameter("ecp_F");}
 if(request.getParameter("ecp_T").trim().equals("")){ecp_T="0";} else {ecp_T=request.getParameter("ecp_T");}
 if(request.getParameter("pill_14_F").trim().equals("")){pill_14_F="0";} else {pill_14_F=request.getParameter("pill_14_F");}
 if(request.getParameter("pill_19_F").trim().equals("")){pill_19_F="0";} else {pill_19_F=request.getParameter("pill_19_F");}
 if(request.getParameter("pill_24_F").trim().equals("")){pill_24_F="0";} else {pill_24_F=request.getParameter("pill_24_F");}
 if(request.getParameter("pill_29_F").trim().equals("")){pill_29_F="0";} else {pill_29_F=request.getParameter("pill_29_F");}
 if(request.getParameter("pill_34_F").trim().equals("")){pill_34_F="0";} else {pill_34_F=request.getParameter("pill_34_F");}
 if(request.getParameter("pill_39_F").trim().equals("")){pill_39_F="0";} else {pill_39_F=request.getParameter("pill_39_F");}
 if(request.getParameter("pill_49_F").trim().equals("")){pill_49_F="0";} else {pill_49_F=request.getParameter("pill_49_F");}
 if(request.getParameter("pill_50_F").trim().equals("")){pill_50_F="0";} else {pill_50_F=request.getParameter("pill_50_F");}
 if(request.getParameter("pill_F").trim().equals("")){pill_F="0";} else {pill_F=request.getParameter("pill_F");}
 if(request.getParameter("pill_T").trim().equals("")){pill_T="0";} else {pill_T=request.getParameter("pill_T");}
 if(request.getParameter("tested_1_M").trim().equals("")){tested_1_M="0";} else {tested_1_M=request.getParameter("tested_1_M");}
 if(request.getParameter("tested_1_F").trim().equals("")){tested_1_F="0";} else {tested_1_F=request.getParameter("tested_1_F");}
 if(request.getParameter("tested_4_M").trim().equals("")){tested_4_M="0";} else {tested_4_M=request.getParameter("tested_4_M");}
 if(request.getParameter("tested_4_F").trim().equals("")){tested_4_F="0";} else {tested_4_F=request.getParameter("tested_4_F");}
 if(request.getParameter("tested_9_M").trim().equals("")){tested_9_M="0";} else {tested_9_M=request.getParameter("tested_9_M");}
 if(request.getParameter("tested_9_F").trim().equals("")){tested_9_F="0";} else {tested_9_F=request.getParameter("tested_9_F");}
 if(request.getParameter("tested_14_M").trim().equals("")){tested_14_M="0";} else {tested_14_M=request.getParameter("tested_14_M");}
 if(request.getParameter("tested_14_F").trim().equals("")){tested_14_F="0";} else {tested_14_F=request.getParameter("tested_14_F");}
 if(request.getParameter("tested_19_M").trim().equals("")){tested_19_M="0";} else {tested_19_M=request.getParameter("tested_19_M");}
 if(request.getParameter("tested_19_F").trim().equals("")){tested_19_F="0";} else {tested_19_F=request.getParameter("tested_19_F");}
 if(request.getParameter("tested_24_M").trim().equals("")){tested_24_M="0";} else {tested_24_M=request.getParameter("tested_24_M");}
 if(request.getParameter("tested_24_F").trim().equals("")){tested_24_F="0";} else {tested_24_F=request.getParameter("tested_24_F");}
 if(request.getParameter("tested_29_M").trim().equals("")){tested_29_M="0";} else {tested_29_M=request.getParameter("tested_29_M");}
 if(request.getParameter("tested_29_F").trim().equals("")){tested_29_F="0";} else {tested_29_F=request.getParameter("tested_29_F");}
 if(request.getParameter("tested_34_M").trim().equals("")){tested_34_M="0";} else {tested_34_M=request.getParameter("tested_34_M");}
 if(request.getParameter("tested_34_F").trim().equals("")){tested_34_F="0";} else {tested_34_F=request.getParameter("tested_34_F");}
 if(request.getParameter("tested_39_M").trim().equals("")){tested_39_M="0";} else {tested_39_M=request.getParameter("tested_39_M");}
 if(request.getParameter("tested_39_F").trim().equals("")){tested_39_F="0";} else {tested_39_F=request.getParameter("tested_39_F");}
 if(request.getParameter("tested_49_M").trim().equals("")){tested_49_M="0";} else {tested_49_M=request.getParameter("tested_49_M");}
 if(request.getParameter("tested_49_F").trim().equals("")){tested_49_F="0";} else {tested_49_F=request.getParameter("tested_49_F");}
 if(request.getParameter("tested_50_M").trim().equals("")){tested_50_M="0";} else {tested_50_M=request.getParameter("tested_50_M");}
 if(request.getParameter("tested_50_F").trim().equals("")){tested_50_F="0";} else {tested_50_F=request.getParameter("tested_50_F");}
 if(request.getParameter("tested_M").trim().equals("")){tested_M="0";} else {tested_M=request.getParameter("tested_M");}
 if(request.getParameter("tested_F").trim().equals("")){tested_F="0";} else {tested_F=request.getParameter("tested_F");}
 if(request.getParameter("tested_T").trim().equals("")){tested_T="0";} else {tested_T=request.getParameter("tested_T");}
 if(request.getParameter("positive_1_M").trim().equals("")){positive_1_M="0";} else {positive_1_M=request.getParameter("positive_1_M");}
 if(request.getParameter("positive_1_F").trim().equals("")){positive_1_F="0";} else {positive_1_F=request.getParameter("positive_1_F");}
 if(request.getParameter("positive_4_M").trim().equals("")){positive_4_M="0";} else {positive_4_M=request.getParameter("positive_4_M");}
 if(request.getParameter("positive_4_F").trim().equals("")){positive_4_F="0";} else {positive_4_F=request.getParameter("positive_4_F");}
 if(request.getParameter("positive_9_M").trim().equals("")){positive_9_M="0";} else {positive_9_M=request.getParameter("positive_9_M");}
 if(request.getParameter("positive_9_F").trim().equals("")){positive_9_F="0";} else {positive_9_F=request.getParameter("positive_9_F");}
 if(request.getParameter("positive_14_M").trim().equals("")){positive_14_M="0";} else {positive_14_M=request.getParameter("positive_14_M");}
 if(request.getParameter("positive_14_F").trim().equals("")){positive_14_F="0";} else {positive_14_F=request.getParameter("positive_14_F");}
 if(request.getParameter("positive_19_M").trim().equals("")){positive_19_M="0";} else {positive_19_M=request.getParameter("positive_19_M");}
 if(request.getParameter("positive_19_F").trim().equals("")){positive_19_F="0";} else {positive_19_F=request.getParameter("positive_19_F");}
 if(request.getParameter("positive_24_M").trim().equals("")){positive_24_M="0";} else {positive_24_M=request.getParameter("positive_24_M");}
 if(request.getParameter("positive_24_F").trim().equals("")){positive_24_F="0";} else {positive_24_F=request.getParameter("positive_24_F");}
 if(request.getParameter("positive_29_M").trim().equals("")){positive_29_M="0";} else {positive_29_M=request.getParameter("positive_29_M");}
 if(request.getParameter("positive_29_F").trim().equals("")){positive_29_F="0";} else {positive_29_F=request.getParameter("positive_29_F");}
 if(request.getParameter("positive_34_M").trim().equals("")){positive_34_M="0";} else {positive_34_M=request.getParameter("positive_34_M");}
 if(request.getParameter("positive_34_F").trim().equals("")){positive_34_F="0";} else {positive_34_F=request.getParameter("positive_34_F");}
 if(request.getParameter("positive_39_M").trim().equals("")){positive_39_M="0";} else {positive_39_M=request.getParameter("positive_39_M");}
 if(request.getParameter("positive_39_F").trim().equals("")){positive_39_F="0";} else {positive_39_F=request.getParameter("positive_39_F");}
 if(request.getParameter("positive_49_M").trim().equals("")){positive_49_M="0";} else {positive_49_M=request.getParameter("positive_49_M");}
 if(request.getParameter("positive_49_F").trim().equals("")){positive_49_F="0";} else {positive_49_F=request.getParameter("positive_49_F");}
 if(request.getParameter("positive_50_M").trim().equals("")){positive_50_M="0";} else {positive_50_M=request.getParameter("positive_50_M");}
 if(request.getParameter("positive_50_F").trim().equals("")){positive_50_F="0";} else {positive_50_F=request.getParameter("positive_50_F");}
 if(request.getParameter("positive_M").trim().equals("")){positive_M="0";} else {positive_M=request.getParameter("positive_M");}
 if(request.getParameter("positive_F").trim().equals("")){positive_F="0";} else {positive_F=request.getParameter("positive_F");}
 if(request.getParameter("positive_T").trim().equals("")){positive_T="0";} else {positive_T=request.getParameter("positive_T");}
 if(request.getParameter("disability_1_M").trim().equals("")){disability_1_M="0";} else {disability_1_M=request.getParameter("disability_1_M");}
 if(request.getParameter("disability_1_F").trim().equals("")){disability_1_F="0";} else {disability_1_F=request.getParameter("disability_1_F");}
 if(request.getParameter("disability_4_M").trim().equals("")){disability_4_M="0";} else {disability_4_M=request.getParameter("disability_4_M");}
 if(request.getParameter("disability_4_F").trim().equals("")){disability_4_F="0";} else {disability_4_F=request.getParameter("disability_4_F");}
 if(request.getParameter("disability_9_M").trim().equals("")){disability_9_M="0";} else {disability_9_M=request.getParameter("disability_9_M");}
 if(request.getParameter("disability_9_F").trim().equals("")){disability_9_F="0";} else {disability_9_F=request.getParameter("disability_9_F");}
 if(request.getParameter("disability_14_M").trim().equals("")){disability_14_M="0";} else {disability_14_M=request.getParameter("disability_14_M");}
 if(request.getParameter("disability_14_F").trim().equals("")){disability_14_F="0";} else {disability_14_F=request.getParameter("disability_14_F");}
 if(request.getParameter("disability_19_M").trim().equals("")){disability_19_M="0";} else {disability_19_M=request.getParameter("disability_19_M");}
 if(request.getParameter("disability_19_F").trim().equals("")){disability_19_F="0";} else {disability_19_F=request.getParameter("disability_19_F");}
 if(request.getParameter("disability_24_M").trim().equals("")){disability_24_M="0";} else {disability_24_M=request.getParameter("disability_24_M");}
 if(request.getParameter("disability_24_F").trim().equals("")){disability_24_F="0";} else {disability_24_F=request.getParameter("disability_24_F");}
 if(request.getParameter("disability_29_M").trim().equals("")){disability_29_M="0";} else {disability_29_M=request.getParameter("disability_29_M");}
 if(request.getParameter("disability_29_F").trim().equals("")){disability_29_F="0";} else {disability_29_F=request.getParameter("disability_29_F");}
 if(request.getParameter("disability_34_M").trim().equals("")){disability_34_M="0";} else {disability_34_M=request.getParameter("disability_34_M");}
 if(request.getParameter("disability_34_F").trim().equals("")){disability_34_F="0";} else {disability_34_F=request.getParameter("disability_34_F");}
 if(request.getParameter("disability_39_M").trim().equals("")){disability_39_M="0";} else {disability_39_M=request.getParameter("disability_39_M");}
 if(request.getParameter("disability_39_F").trim().equals("")){disability_39_F="0";} else {disability_39_F=request.getParameter("disability_39_F");}
 if(request.getParameter("disability_49_M").trim().equals("")){disability_49_M="0";} else {disability_49_M=request.getParameter("disability_49_M");}
 if(request.getParameter("disability_49_F").trim().equals("")){disability_49_F="0";} else {disability_49_F=request.getParameter("disability_49_F");}
 if(request.getParameter("disability_50_M").trim().equals("")){disability_50_M="0";} else {disability_50_M=request.getParameter("disability_50_M");}
 if(request.getParameter("disability_50_F").trim().equals("")){disability_50_F="0";} else {disability_50_F=request.getParameter("disability_50_F");}
 if(request.getParameter("disability_M").trim().equals("")){disability_M="0";} else {disability_M=request.getParameter("disability_M");}
 if(request.getParameter("disability_F").trim().equals("")){disability_F="0";} else {disability_F=request.getParameter("disability_F");}
 if(request.getParameter("disability_T").trim().equals("")){disability_T="0";} else {disability_T=request.getParameter("disability_T");}
 if(request.getParameter("perpetrators_1_M").trim().equals("")){perpetrators_1_M="0";} else {perpetrators_1_M=request.getParameter("perpetrators_1_M");}
 if(request.getParameter("perpetrators_1_F").trim().equals("")){perpetrators_1_F="0";} else {perpetrators_1_F=request.getParameter("perpetrators_1_F");}
 if(request.getParameter("perpetrators_4_M").trim().equals("")){perpetrators_4_M="0";} else {perpetrators_4_M=request.getParameter("perpetrators_4_M");}
 if(request.getParameter("perpetrators_4_F").trim().equals("")){perpetrators_4_F="0";} else {perpetrators_4_F=request.getParameter("perpetrators_4_F");}
 if(request.getParameter("perpetrators_9_M").trim().equals("")){perpetrators_9_M="0";} else {perpetrators_9_M=request.getParameter("perpetrators_9_M");}
 if(request.getParameter("perpetrators_9_F").trim().equals("")){perpetrators_9_F="0";} else {perpetrators_9_F=request.getParameter("perpetrators_9_F");}
 if(request.getParameter("perpetrators_14_M").trim().equals("")){perpetrators_14_M="0";} else {perpetrators_14_M=request.getParameter("perpetrators_14_M");}
 if(request.getParameter("perpetrators_14_F").trim().equals("")){perpetrators_14_F="0";} else {perpetrators_14_F=request.getParameter("perpetrators_14_F");}
 if(request.getParameter("perpetrators_19_M").trim().equals("")){perpetrators_19_M="0";} else {perpetrators_19_M=request.getParameter("perpetrators_19_M");}
 if(request.getParameter("perpetrators_19_F").trim().equals("")){perpetrators_19_F="0";} else {perpetrators_19_F=request.getParameter("perpetrators_19_F");}
 if(request.getParameter("perpetrators_24_M").trim().equals("")){perpetrators_24_M="0";} else {perpetrators_24_M=request.getParameter("perpetrators_24_M");}
 if(request.getParameter("perpetrators_24_F").trim().equals("")){perpetrators_24_F="0";} else {perpetrators_24_F=request.getParameter("perpetrators_24_F");}
 if(request.getParameter("perpetrators_29_M").trim().equals("")){perpetrators_29_M="0";} else {perpetrators_29_M=request.getParameter("perpetrators_29_M");}
 if(request.getParameter("perpetrators_29_F").trim().equals("")){perpetrators_29_F="0";} else {perpetrators_29_F=request.getParameter("perpetrators_29_F");}
 if(request.getParameter("perpetrators_34_M").trim().equals("")){perpetrators_34_M="0";} else {perpetrators_34_M=request.getParameter("perpetrators_34_M");}
 if(request.getParameter("perpetrators_34_F").trim().equals("")){perpetrators_34_F="0";} else {perpetrators_34_F=request.getParameter("perpetrators_34_F");}
 if(request.getParameter("perpetrators_39_M").trim().equals("")){perpetrators_39_M="0";} else {perpetrators_39_M=request.getParameter("perpetrators_39_M");}
 if(request.getParameter("perpetrators_39_F").trim().equals("")){perpetrators_39_F="0";} else {perpetrators_39_F=request.getParameter("perpetrators_39_F");}
 if(request.getParameter("perpetrators_49_M").trim().equals("")){perpetrators_49_M="0";} else {perpetrators_49_M=request.getParameter("perpetrators_49_M");}
 if(request.getParameter("perpetrators_49_F").trim().equals("")){perpetrators_49_F="0";} else {perpetrators_49_F=request.getParameter("perpetrators_49_F");}
 if(request.getParameter("perpetrators_50_M").trim().equals("")){perpetrators_50_M="0";} else {perpetrators_50_M=request.getParameter("perpetrators_50_M");}
 if(request.getParameter("perpetrators_50_F").trim().equals("")){perpetrators_50_F="0";} else {perpetrators_50_F=request.getParameter("perpetrators_50_F");}
 if(request.getParameter("perpetrators_M").trim().equals("")){perpetrators_M="0";} else {perpetrators_M=request.getParameter("perpetrators_M");}
 if(request.getParameter("perpetrators_F").trim().equals("")){perpetrators_F="0";} else {perpetrators_F=request.getParameter("perpetrators_F");}
 if(request.getParameter("perpetrators_T").trim().equals("")){perpetrators_T="0";} else {perpetrators_T=request.getParameter("perpetrators_T");}
 if(request.getParameter("visit1_1_M").trim().equals("")){visit1_1_M="0";} else {visit1_1_M=request.getParameter("visit1_1_M");}
 if(request.getParameter("visit1_1_F").trim().equals("")){visit1_1_F="0";} else {visit1_1_F=request.getParameter("visit1_1_F");}
 if(request.getParameter("visit1_4_M").trim().equals("")){visit1_4_M="0";} else {visit1_4_M=request.getParameter("visit1_4_M");}
 if(request.getParameter("visit1_4_F").trim().equals("")){visit1_4_F="0";} else {visit1_4_F=request.getParameter("visit1_4_F");}
 if(request.getParameter("visit1_9_M").trim().equals("")){visit1_9_M="0";} else {visit1_9_M=request.getParameter("visit1_9_M");}
 if(request.getParameter("visit1_9_F").trim().equals("")){visit1_9_F="0";} else {visit1_9_F=request.getParameter("visit1_9_F");}
 if(request.getParameter("visit1_14_M").trim().equals("")){visit1_14_M="0";} else {visit1_14_M=request.getParameter("visit1_14_M");}
 if(request.getParameter("visit1_14_F").trim().equals("")){visit1_14_F="0";} else {visit1_14_F=request.getParameter("visit1_14_F");}
 if(request.getParameter("visit1_19_M").trim().equals("")){visit1_19_M="0";} else {visit1_19_M=request.getParameter("visit1_19_M");}
 if(request.getParameter("visit1_19_F").trim().equals("")){visit1_19_F="0";} else {visit1_19_F=request.getParameter("visit1_19_F");}
 if(request.getParameter("visit1_24_M").trim().equals("")){visit1_24_M="0";} else {visit1_24_M=request.getParameter("visit1_24_M");}
 if(request.getParameter("visit1_24_F").trim().equals("")){visit1_24_F="0";} else {visit1_24_F=request.getParameter("visit1_24_F");}
 if(request.getParameter("visit1_29_M").trim().equals("")){visit1_29_M="0";} else {visit1_29_M=request.getParameter("visit1_29_M");}
 if(request.getParameter("visit1_29_F").trim().equals("")){visit1_29_F="0";} else {visit1_29_F=request.getParameter("visit1_29_F");}
 if(request.getParameter("visit1_34_M").trim().equals("")){visit1_34_M="0";} else {visit1_34_M=request.getParameter("visit1_34_M");}
 if(request.getParameter("visit1_34_F").trim().equals("")){visit1_34_F="0";} else {visit1_34_F=request.getParameter("visit1_34_F");}
 if(request.getParameter("visit1_39_M").trim().equals("")){visit1_39_M="0";} else {visit1_39_M=request.getParameter("visit1_39_M");}
 if(request.getParameter("visit1_39_F").trim().equals("")){visit1_39_F="0";} else {visit1_39_F=request.getParameter("visit1_39_F");}
 if(request.getParameter("visit1_49_M").trim().equals("")){visit1_49_M="0";} else {visit1_49_M=request.getParameter("visit1_49_M");}
 if(request.getParameter("visit1_49_F").trim().equals("")){visit1_49_F="0";} else {visit1_49_F=request.getParameter("visit1_49_F");}
 if(request.getParameter("visit1_50_M").trim().equals("")){visit1_50_M="0";} else {visit1_50_M=request.getParameter("visit1_50_M");}
 if(request.getParameter("visit1_50_F").trim().equals("")){visit1_50_F="0";} else {visit1_50_F=request.getParameter("visit1_50_F");}
 if(request.getParameter("visit1_M").trim().equals("")){visit1_M="0";} else {visit1_M=request.getParameter("visit1_M");}
 if(request.getParameter("visit1_F").trim().equals("")){visit1_F="0";} else {visit1_F=request.getParameter("visit1_F");}
 if(request.getParameter("visit1_T").trim().equals("")){visit1_T="0";} else {visit1_T=request.getParameter("visit1_T");}
 if(request.getParameter("visit2_1_M").trim().equals("")){visit2_1_M="0";} else {visit2_1_M=request.getParameter("visit2_1_M");}
 if(request.getParameter("visit2_1_F").trim().equals("")){visit2_1_F="0";} else {visit2_1_F=request.getParameter("visit2_1_F");}
 if(request.getParameter("visit2_4_M").trim().equals("")){visit2_4_M="0";} else {visit2_4_M=request.getParameter("visit2_4_M");}
 if(request.getParameter("visit2_4_F").trim().equals("")){visit2_4_F="0";} else {visit2_4_F=request.getParameter("visit2_4_F");}
 if(request.getParameter("visit2_9_M").trim().equals("")){visit2_9_M="0";} else {visit2_9_M=request.getParameter("visit2_9_M");}
 if(request.getParameter("visit2_9_F").trim().equals("")){visit2_9_F="0";} else {visit2_9_F=request.getParameter("visit2_9_F");}
 if(request.getParameter("visit2_14_M").trim().equals("")){visit2_14_M="0";} else {visit2_14_M=request.getParameter("visit2_14_M");}
 if(request.getParameter("visit2_14_F").trim().equals("")){visit2_14_F="0";} else {visit2_14_F=request.getParameter("visit2_14_F");}
 if(request.getParameter("visit2_19_M").trim().equals("")){visit2_19_M="0";} else {visit2_19_M=request.getParameter("visit2_19_M");}
 if(request.getParameter("visit2_19_F").trim().equals("")){visit2_19_F="0";} else {visit2_19_F=request.getParameter("visit2_19_F");}
 if(request.getParameter("visit2_24_M").trim().equals("")){visit2_24_M="0";} else {visit2_24_M=request.getParameter("visit2_24_M");}
 if(request.getParameter("visit2_24_F").trim().equals("")){visit2_24_F="0";} else {visit2_24_F=request.getParameter("visit2_24_F");}
 if(request.getParameter("visit2_29_M").trim().equals("")){visit2_29_M="0";} else {visit2_29_M=request.getParameter("visit2_29_M");}
 if(request.getParameter("visit2_29_F").trim().equals("")){visit2_29_F="0";} else {visit2_29_F=request.getParameter("visit2_29_F");}
 if(request.getParameter("visit2_34_M").trim().equals("")){visit2_34_M="0";} else {visit2_34_M=request.getParameter("visit2_34_M");}
 if(request.getParameter("visit2_34_F").trim().equals("")){visit2_34_F="0";} else {visit2_34_F=request.getParameter("visit2_34_F");}
 if(request.getParameter("visit2_39_M").trim().equals("")){visit2_39_M="0";} else {visit2_39_M=request.getParameter("visit2_39_M");}
 if(request.getParameter("visit2_39_F").trim().equals("")){visit2_39_F="0";} else {visit2_39_F=request.getParameter("visit2_39_F");}
 if(request.getParameter("visit2_49_M").trim().equals("")){visit2_49_M="0";} else {visit2_49_M=request.getParameter("visit2_49_M");}
 if(request.getParameter("visit2_49_F").trim().equals("")){visit2_49_F="0";} else {visit2_49_F=request.getParameter("visit2_49_F");}
 if(request.getParameter("visit2_50_M").trim().equals("")){visit2_50_M="0";} else {visit2_50_M=request.getParameter("visit2_50_M");}
 if(request.getParameter("visit2_50_F").trim().equals("")){visit2_50_F="0";} else {visit2_50_F=request.getParameter("visit2_50_F");}
 if(request.getParameter("visit2_M").trim().equals("")){visit2_M="0";} else {visit2_M=request.getParameter("visit2_M");}
 if(request.getParameter("visit2_F").trim().equals("")){visit2_F="0";} else {visit2_F=request.getParameter("visit2_F");}
 if(request.getParameter("visit2_T").trim().equals("")){visit2_T="0";} else {visit2_T=request.getParameter("visit2_T");}
 if(request.getParameter("visit3_1_M").trim().equals("")){visit3_1_M="0";} else {visit3_1_M=request.getParameter("visit3_1_M");}
 if(request.getParameter("visit3_1_F").trim().equals("")){visit3_1_F="0";} else {visit3_1_F=request.getParameter("visit3_1_F");}
 if(request.getParameter("visit3_4_M").trim().equals("")){visit3_4_M="0";} else {visit3_4_M=request.getParameter("visit3_4_M");}
 if(request.getParameter("visit3_4_F").trim().equals("")){visit3_4_F="0";} else {visit3_4_F=request.getParameter("visit3_4_F");}
 if(request.getParameter("visit3_9_M").trim().equals("")){visit3_9_M="0";} else {visit3_9_M=request.getParameter("visit3_9_M");}
 if(request.getParameter("visit3_9_F").trim().equals("")){visit3_9_F="0";} else {visit3_9_F=request.getParameter("visit3_9_F");}
 if(request.getParameter("visit3_14_M").trim().equals("")){visit3_14_M="0";} else {visit3_14_M=request.getParameter("visit3_14_M");}
 if(request.getParameter("visit3_14_F").trim().equals("")){visit3_14_F="0";} else {visit3_14_F=request.getParameter("visit3_14_F");}
 if(request.getParameter("visit3_19_M").trim().equals("")){visit3_19_M="0";} else {visit3_19_M=request.getParameter("visit3_19_M");}
 if(request.getParameter("visit3_19_F").trim().equals("")){visit3_19_F="0";} else {visit3_19_F=request.getParameter("visit3_19_F");}
 if(request.getParameter("visit3_24_M").trim().equals("")){visit3_24_M="0";} else {visit3_24_M=request.getParameter("visit3_24_M");}
 if(request.getParameter("visit3_24_F").trim().equals("")){visit3_24_F="0";} else {visit3_24_F=request.getParameter("visit3_24_F");}
 if(request.getParameter("visit3_29_M").trim().equals("")){visit3_29_M="0";} else {visit3_29_M=request.getParameter("visit3_29_M");}
 if(request.getParameter("visit3_29_F").trim().equals("")){visit3_29_F="0";} else {visit3_29_F=request.getParameter("visit3_29_F");}
 if(request.getParameter("visit3_34_M").trim().equals("")){visit3_34_M="0";} else {visit3_34_M=request.getParameter("visit3_34_M");}
 if(request.getParameter("visit3_34_F").trim().equals("")){visit3_34_F="0";} else {visit3_34_F=request.getParameter("visit3_34_F");}
 if(request.getParameter("visit3_39_M").trim().equals("")){visit3_39_M="0";} else {visit3_39_M=request.getParameter("visit3_39_M");}
 if(request.getParameter("visit3_39_F").trim().equals("")){visit3_39_F="0";} else {visit3_39_F=request.getParameter("visit3_39_F");}
 if(request.getParameter("visit3_49_M").trim().equals("")){visit3_49_M="0";} else {visit3_49_M=request.getParameter("visit3_49_M");}
 if(request.getParameter("visit3_49_F").trim().equals("")){visit3_49_F="0";} else {visit3_49_F=request.getParameter("visit3_49_F");}
 if(request.getParameter("visit3_50_M").trim().equals("")){visit3_50_M="0";} else {visit3_50_M=request.getParameter("visit3_50_M");}
 if(request.getParameter("visit3_50_F").trim().equals("")){visit3_50_F="0";} else {visit3_50_F=request.getParameter("visit3_50_F");}
 if(request.getParameter("visit3_M").trim().equals("")){visit3_M="0";} else {visit3_M=request.getParameter("visit3_M");}
 if(request.getParameter("visit3_F").trim().equals("")){visit3_F="0";} else {visit3_F=request.getParameter("visit3_F");}
 if(request.getParameter("visit3_T").trim().equals("")){visit3_T="0";} else {visit3_T=request.getParameter("visit3_T");}
 if(request.getParameter("visit4_1_M").trim().equals("")){visit4_1_M="0";} else {visit4_1_M=request.getParameter("visit4_1_M");}
 if(request.getParameter("visit4_1_F").trim().equals("")){visit4_1_F="0";} else {visit4_1_F=request.getParameter("visit4_1_F");}
 if(request.getParameter("visit4_4_M").trim().equals("")){visit4_4_M="0";} else {visit4_4_M=request.getParameter("visit4_4_M");}
 if(request.getParameter("visit4_4_F").trim().equals("")){visit4_4_F="0";} else {visit4_4_F=request.getParameter("visit4_4_F");}
 if(request.getParameter("visit4_9_M").trim().equals("")){visit4_9_M="0";} else {visit4_9_M=request.getParameter("visit4_9_M");}
 if(request.getParameter("visit4_9_F").trim().equals("")){visit4_9_F="0";} else {visit4_9_F=request.getParameter("visit4_9_F");}
 if(request.getParameter("visit4_14_M").trim().equals("")){visit4_14_M="0";} else {visit4_14_M=request.getParameter("visit4_14_M");}
 if(request.getParameter("visit4_14_F").trim().equals("")){visit4_14_F="0";} else {visit4_14_F=request.getParameter("visit4_14_F");}
 if(request.getParameter("visit4_19_M").trim().equals("")){visit4_19_M="0";} else {visit4_19_M=request.getParameter("visit4_19_M");}
 if(request.getParameter("visit4_19_F").trim().equals("")){visit4_19_F="0";} else {visit4_19_F=request.getParameter("visit4_19_F");}
 if(request.getParameter("visit4_24_M").trim().equals("")){visit4_24_M="0";} else {visit4_24_M=request.getParameter("visit4_24_M");}
 if(request.getParameter("visit4_24_F").trim().equals("")){visit4_24_F="0";} else {visit4_24_F=request.getParameter("visit4_24_F");}
 if(request.getParameter("visit4_29_M").trim().equals("")){visit4_29_M="0";} else {visit4_29_M=request.getParameter("visit4_29_M");}
 if(request.getParameter("visit4_29_F").trim().equals("")){visit4_29_F="0";} else {visit4_29_F=request.getParameter("visit4_29_F");}
 if(request.getParameter("visit4_34_M").trim().equals("")){visit4_34_M="0";} else {visit4_34_M=request.getParameter("visit4_34_M");}
 if(request.getParameter("visit4_34_F").trim().equals("")){visit4_34_F="0";} else {visit4_34_F=request.getParameter("visit4_34_F");}
 if(request.getParameter("visit4_39_M").trim().equals("")){visit4_39_M="0";} else {visit4_39_M=request.getParameter("visit4_39_M");}
 if(request.getParameter("visit4_39_F").trim().equals("")){visit4_39_F="0";} else {visit4_39_F=request.getParameter("visit4_39_F");}
 if(request.getParameter("visit4_49_M").trim().equals("")){visit4_49_M="0";} else {visit4_49_M=request.getParameter("visit4_49_M");}
 if(request.getParameter("visit4_49_F").trim().equals("")){visit4_49_F="0";} else {visit4_49_F=request.getParameter("visit4_49_F");}
 if(request.getParameter("visit4_50_M").trim().equals("")){visit4_50_M="0";} else {visit4_50_M=request.getParameter("visit4_50_M");}
 if(request.getParameter("visit4_50_F").trim().equals("")){visit4_50_F="0";} else {visit4_50_F=request.getParameter("visit4_50_F");}
 if(request.getParameter("visit4_M").trim().equals("")){visit4_M="0";} else {visit4_M=request.getParameter("visit4_M");}
 if(request.getParameter("visit4_F").trim().equals("")){visit4_F="0";} else {visit4_F=request.getParameter("visit4_F");}
 if(request.getParameter("visit4_T").trim().equals("")){visit4_T="0";} else {visit4_T=request.getParameter("visit4_T");}
 if(request.getParameter("visit5_1_M").trim().equals("")){visit5_1_M="0";} else {visit5_1_M=request.getParameter("visit5_1_M");}
 if(request.getParameter("visit5_1_F").trim().equals("")){visit5_1_F="0";} else {visit5_1_F=request.getParameter("visit5_1_F");}
 if(request.getParameter("visit5_4_M").trim().equals("")){visit5_4_M="0";} else {visit5_4_M=request.getParameter("visit5_4_M");}
 if(request.getParameter("visit5_4_F").trim().equals("")){visit5_4_F="0";} else {visit5_4_F=request.getParameter("visit5_4_F");}
 if(request.getParameter("visit5_9_M").trim().equals("")){visit5_9_M="0";} else {visit5_9_M=request.getParameter("visit5_9_M");}
 if(request.getParameter("visit5_9_F").trim().equals("")){visit5_9_F="0";} else {visit5_9_F=request.getParameter("visit5_9_F");}
 if(request.getParameter("visit5_14_M").trim().equals("")){visit5_14_M="0";} else {visit5_14_M=request.getParameter("visit5_14_M");}
 if(request.getParameter("visit5_14_F").trim().equals("")){visit5_14_F="0";} else {visit5_14_F=request.getParameter("visit5_14_F");}
 if(request.getParameter("visit5_19_M").trim().equals("")){visit5_19_M="0";} else {visit5_19_M=request.getParameter("visit5_19_M");}
 if(request.getParameter("visit5_19_F").trim().equals("")){visit5_19_F="0";} else {visit5_19_F=request.getParameter("visit5_19_F");}
 if(request.getParameter("visit5_24_M").trim().equals("")){visit5_24_M="0";} else {visit5_24_M=request.getParameter("visit5_24_M");}
 if(request.getParameter("visit5_24_F").trim().equals("")){visit5_24_F="0";} else {visit5_24_F=request.getParameter("visit5_24_F");}
 if(request.getParameter("visit5_29_M").trim().equals("")){visit5_29_M="0";} else {visit5_29_M=request.getParameter("visit5_29_M");}
 if(request.getParameter("visit5_29_F").trim().equals("")){visit5_29_F="0";} else {visit5_29_F=request.getParameter("visit5_29_F");}
 if(request.getParameter("visit5_34_M").trim().equals("")){visit5_34_M="0";} else {visit5_34_M=request.getParameter("visit5_34_M");}
 if(request.getParameter("visit5_34_F").trim().equals("")){visit5_34_F="0";} else {visit5_34_F=request.getParameter("visit5_34_F");}
 if(request.getParameter("visit5_39_M").trim().equals("")){visit5_39_M="0";} else {visit5_39_M=request.getParameter("visit5_39_M");}
 if(request.getParameter("visit5_39_F").trim().equals("")){visit5_39_F="0";} else {visit5_39_F=request.getParameter("visit5_39_F");}
 if(request.getParameter("visit5_49_M").trim().equals("")){visit5_49_M="0";} else {visit5_49_M=request.getParameter("visit5_49_M");}
 if(request.getParameter("visit5_49_F").trim().equals("")){visit5_49_F="0";} else {visit5_49_F=request.getParameter("visit5_49_F");}
 if(request.getParameter("visit5_50_M").trim().equals("")){visit5_50_M="0";} else {visit5_50_M=request.getParameter("visit5_50_M");}
 if(request.getParameter("visit5_50_F").trim().equals("")){visit5_50_F="0";} else {visit5_50_F=request.getParameter("visit5_50_F");}
 if(request.getParameter("visit5_M").trim().equals("")){visit5_M="0";} else {visit5_M=request.getParameter("visit5_M");}
 if(request.getParameter("visit5_F").trim().equals("")){visit5_F="0";} else {visit5_F=request.getParameter("visit5_F");}
 if(request.getParameter("visit5_T").trim().equals("")){visit5_T="0";} else {visit5_T=request.getParameter("visit5_T");}
 if(request.getParameter("completedpep_1_M").trim().equals("")){completedpep_1_M="0";} else {completedpep_1_M=request.getParameter("completedpep_1_M");}
 if(request.getParameter("completedpep_1_F").trim().equals("")){completedpep_1_F="0";} else {completedpep_1_F=request.getParameter("completedpep_1_F");}
 if(request.getParameter("completedpep_4_M").trim().equals("")){completedpep_4_M="0";} else {completedpep_4_M=request.getParameter("completedpep_4_M");}
 if(request.getParameter("completedpep_4_F").trim().equals("")){completedpep_4_F="0";} else {completedpep_4_F=request.getParameter("completedpep_4_F");}
 if(request.getParameter("completedpep_9_M").trim().equals("")){completedpep_9_M="0";} else {completedpep_9_M=request.getParameter("completedpep_9_M");}
 if(request.getParameter("completedpep_9_F").trim().equals("")){completedpep_9_F="0";} else {completedpep_9_F=request.getParameter("completedpep_9_F");}
 if(request.getParameter("completedpep_14_M").trim().equals("")){completedpep_14_M="0";} else {completedpep_14_M=request.getParameter("completedpep_14_M");}
 if(request.getParameter("completedpep_14_F").trim().equals("")){completedpep_14_F="0";} else {completedpep_14_F=request.getParameter("completedpep_14_F");}
 if(request.getParameter("completedpep_19_M").trim().equals("")){completedpep_19_M="0";} else {completedpep_19_M=request.getParameter("completedpep_19_M");}
 if(request.getParameter("completedpep_19_F").trim().equals("")){completedpep_19_F="0";} else {completedpep_19_F=request.getParameter("completedpep_19_F");}
 if(request.getParameter("completedpep_24_M").trim().equals("")){completedpep_24_M="0";} else {completedpep_24_M=request.getParameter("completedpep_24_M");}
 if(request.getParameter("completedpep_24_F").trim().equals("")){completedpep_24_F="0";} else {completedpep_24_F=request.getParameter("completedpep_24_F");}
 if(request.getParameter("completedpep_29_M").trim().equals("")){completedpep_29_M="0";} else {completedpep_29_M=request.getParameter("completedpep_29_M");}
 if(request.getParameter("completedpep_29_F").trim().equals("")){completedpep_29_F="0";} else {completedpep_29_F=request.getParameter("completedpep_29_F");}
 if(request.getParameter("completedpep_34_M").trim().equals("")){completedpep_34_M="0";} else {completedpep_34_M=request.getParameter("completedpep_34_M");}
 if(request.getParameter("completedpep_34_F").trim().equals("")){completedpep_34_F="0";} else {completedpep_34_F=request.getParameter("completedpep_34_F");}
 if(request.getParameter("completedpep_39_M").trim().equals("")){completedpep_39_M="0";} else {completedpep_39_M=request.getParameter("completedpep_39_M");}
 if(request.getParameter("completedpep_39_F").trim().equals("")){completedpep_39_F="0";} else {completedpep_39_F=request.getParameter("completedpep_39_F");}
 if(request.getParameter("completedpep_49_M").trim().equals("")){completedpep_49_M="0";} else {completedpep_49_M=request.getParameter("completedpep_49_M");}
 if(request.getParameter("completedpep_49_F").trim().equals("")){completedpep_49_F="0";} else {completedpep_49_F=request.getParameter("completedpep_49_F");}
 if(request.getParameter("completedpep_50_M").trim().equals("")){completedpep_50_M="0";} else {completedpep_50_M=request.getParameter("completedpep_50_M");}
 if(request.getParameter("completedpep_50_F").trim().equals("")){completedpep_50_F="0";} else {completedpep_50_F=request.getParameter("completedpep_50_F");}
 if(request.getParameter("completedpep_M").trim().equals("")){completedpep_M="0";} else {completedpep_M=request.getParameter("completedpep_M");}
 if(request.getParameter("completedpep_F").trim().equals("")){completedpep_F="0";} else {completedpep_F=request.getParameter("completedpep_F");}
 if(request.getParameter("completedpep_T").trim().equals("")){completedpep_T="0";} else {completedpep_T=request.getParameter("completedpep_T");}
 if(request.getParameter("seroconverted_1_M").trim().equals("")){seroconverted_1_M="0";} else {seroconverted_1_M=request.getParameter("seroconverted_1_M");}
 if(request.getParameter("seroconverted_1_F").trim().equals("")){seroconverted_1_F="0";} else {seroconverted_1_F=request.getParameter("seroconverted_1_F");}
 if(request.getParameter("seroconverted_4_M").trim().equals("")){seroconverted_4_M="0";} else {seroconverted_4_M=request.getParameter("seroconverted_4_M");}
 if(request.getParameter("seroconverted_4_F").trim().equals("")){seroconverted_4_F="0";} else {seroconverted_4_F=request.getParameter("seroconverted_4_F");}
 if(request.getParameter("seroconverted_9_M").trim().equals("")){seroconverted_9_M="0";} else {seroconverted_9_M=request.getParameter("seroconverted_9_M");}
 if(request.getParameter("seroconverted_9_F").trim().equals("")){seroconverted_9_F="0";} else {seroconverted_9_F=request.getParameter("seroconverted_9_F");}
 if(request.getParameter("seroconverted_14_M").trim().equals("")){seroconverted_14_M="0";} else {seroconverted_14_M=request.getParameter("seroconverted_14_M");}
 if(request.getParameter("seroconverted_14_F").trim().equals("")){seroconverted_14_F="0";} else {seroconverted_14_F=request.getParameter("seroconverted_14_F");}
 if(request.getParameter("seroconverted_19_M").trim().equals("")){seroconverted_19_M="0";} else {seroconverted_19_M=request.getParameter("seroconverted_19_M");}
 if(request.getParameter("seroconverted_19_F").trim().equals("")){seroconverted_19_F="0";} else {seroconverted_19_F=request.getParameter("seroconverted_19_F");}
 if(request.getParameter("seroconverted_24_M").trim().equals("")){seroconverted_24_M="0";} else {seroconverted_24_M=request.getParameter("seroconverted_24_M");}
 if(request.getParameter("seroconverted_24_F").trim().equals("")){seroconverted_24_F="0";} else {seroconverted_24_F=request.getParameter("seroconverted_24_F");}
 if(request.getParameter("seroconverted_29_M").trim().equals("")){seroconverted_29_M="0";} else {seroconverted_29_M=request.getParameter("seroconverted_29_M");}
 if(request.getParameter("seroconverted_29_F").trim().equals("")){seroconverted_29_F="0";} else {seroconverted_29_F=request.getParameter("seroconverted_29_F");}
 if(request.getParameter("seroconverted_34_M").trim().equals("")){seroconverted_34_M="0";} else {seroconverted_34_M=request.getParameter("seroconverted_34_M");}
 if(request.getParameter("seroconverted_34_F").trim().equals("")){seroconverted_34_F="0";} else {seroconverted_34_F=request.getParameter("seroconverted_34_F");}
 if(request.getParameter("seroconverted_39_M").trim().equals("")){seroconverted_39_M="0";} else {seroconverted_39_M=request.getParameter("seroconverted_39_M");}
 if(request.getParameter("seroconverted_39_F").trim().equals("")){seroconverted_39_F="0";} else {seroconverted_39_F=request.getParameter("seroconverted_39_F");}
 if(request.getParameter("seroconverted_49_M").trim().equals("")){seroconverted_49_M="0";} else {seroconverted_49_M=request.getParameter("seroconverted_49_M");}
 if(request.getParameter("seroconverted_49_F").trim().equals("")){seroconverted_49_F="0";} else {seroconverted_49_F=request.getParameter("seroconverted_49_F");}
 if(request.getParameter("seroconverted_50_M").trim().equals("")){seroconverted_50_M="0";} else {seroconverted_50_M=request.getParameter("seroconverted_50_M");}
 if(request.getParameter("seroconverted_50_F").trim().equals("")){seroconverted_50_F="0";} else {seroconverted_50_F=request.getParameter("seroconverted_50_F");}
 if(request.getParameter("seroconverted_M").trim().equals("")){seroconverted_M="0";} else {seroconverted_M=request.getParameter("seroconverted_M");}
 if(request.getParameter("seroconverted_F").trim().equals("")){seroconverted_F="0";} else {seroconverted_F=request.getParameter("seroconverted_F");}
 if(request.getParameter("seroconverted_T").trim().equals("")){seroconverted_T="0";} else {seroconverted_T=request.getParameter("seroconverted_T");}
 if(request.getParameter("Pregnant_14_F").trim().equals("")){Pregnant_14_F="0";} else {Pregnant_14_F=request.getParameter("Pregnant_14_F");}
 if(request.getParameter("Pregnant_19_F").trim().equals("")){Pregnant_19_F="0";} else {Pregnant_19_F=request.getParameter("Pregnant_19_F");}
 if(request.getParameter("Pregnant_24_F").trim().equals("")){Pregnant_24_F="0";} else {Pregnant_24_F=request.getParameter("Pregnant_24_F");}
 if(request.getParameter("Pregnant_29_F").trim().equals("")){Pregnant_29_F="0";} else {Pregnant_29_F=request.getParameter("Pregnant_29_F");}
 if(request.getParameter("Pregnant_34_F").trim().equals("")){Pregnant_34_F="0";} else {Pregnant_34_F=request.getParameter("Pregnant_34_F");}
 if(request.getParameter("Pregnant_39_F").trim().equals("")){Pregnant_39_F="0";} else {Pregnant_39_F=request.getParameter("Pregnant_39_F");}
 if(request.getParameter("Pregnant_49_F").trim().equals("")){Pregnant_49_F="0";} else {Pregnant_49_F=request.getParameter("Pregnant_49_F");}
 if(request.getParameter("Pregnant_50_F").trim().equals("")){Pregnant_50_F="0";} else {Pregnant_50_F=request.getParameter("Pregnant_50_F");}
 if(request.getParameter("Pregnant_F").trim().equals("")){Pregnant_F="0";} else {Pregnant_F=request.getParameter("Pregnant_F");}
 if(request.getParameter("Pregnant_T").trim().equals("")){Pregnant_T="0";} else {Pregnant_T=request.getParameter("Pregnant_T");}
 if(request.getParameter("counselling_1_M").trim().equals("")){counselling_1_M="0";} else {counselling_1_M=request.getParameter("counselling_1_M");}
 if(request.getParameter("counselling_1_F").trim().equals("")){counselling_1_F="0";} else {counselling_1_F=request.getParameter("counselling_1_F");}
 if(request.getParameter("counselling_4_M").trim().equals("")){counselling_4_M="0";} else {counselling_4_M=request.getParameter("counselling_4_M");}
 if(request.getParameter("counselling_4_F").trim().equals("")){counselling_4_F="0";} else {counselling_4_F=request.getParameter("counselling_4_F");}
 if(request.getParameter("counselling_9_M").trim().equals("")){counselling_9_M="0";} else {counselling_9_M=request.getParameter("counselling_9_M");}
 if(request.getParameter("counselling_9_F").trim().equals("")){counselling_9_F="0";} else {counselling_9_F=request.getParameter("counselling_9_F");}
 if(request.getParameter("counselling_14_M").trim().equals("")){counselling_14_M="0";} else {counselling_14_M=request.getParameter("counselling_14_M");}
 if(request.getParameter("counselling_14_F").trim().equals("")){counselling_14_F="0";} else {counselling_14_F=request.getParameter("counselling_14_F");}
 if(request.getParameter("counselling_19_M").trim().equals("")){counselling_19_M="0";} else {counselling_19_M=request.getParameter("counselling_19_M");}
 if(request.getParameter("counselling_19_F").trim().equals("")){counselling_19_F="0";} else {counselling_19_F=request.getParameter("counselling_19_F");}
 if(request.getParameter("counselling_24_M").trim().equals("")){counselling_24_M="0";} else {counselling_24_M=request.getParameter("counselling_24_M");}
 if(request.getParameter("counselling_24_F").trim().equals("")){counselling_24_F="0";} else {counselling_24_F=request.getParameter("counselling_24_F");}
 if(request.getParameter("counselling_29_M").trim().equals("")){counselling_29_M="0";} else {counselling_29_M=request.getParameter("counselling_29_M");}
 if(request.getParameter("counselling_29_F").trim().equals("")){counselling_29_F="0";} else {counselling_29_F=request.getParameter("counselling_29_F");}
 if(request.getParameter("counselling_34_M").trim().equals("")){counselling_34_M="0";} else {counselling_34_M=request.getParameter("counselling_34_M");}
 if(request.getParameter("counselling_34_F").trim().equals("")){counselling_34_F="0";} else {counselling_34_F=request.getParameter("counselling_34_F");}
 if(request.getParameter("counselling_39_M").trim().equals("")){counselling_39_M="0";} else {counselling_39_M=request.getParameter("counselling_39_M");}
 if(request.getParameter("counselling_39_F").trim().equals("")){counselling_39_F="0";} else {counselling_39_F=request.getParameter("counselling_39_F");}
 if(request.getParameter("counselling_49_M").trim().equals("")){counselling_49_M="0";} else {counselling_49_M=request.getParameter("counselling_49_M");}
 if(request.getParameter("counselling_49_F").trim().equals("")){counselling_49_F="0";} else {counselling_49_F=request.getParameter("counselling_49_F");}
 if(request.getParameter("counselling_50_M").trim().equals("")){counselling_50_M="0";} else {counselling_50_M=request.getParameter("counselling_50_M");}
 if(request.getParameter("counselling_50_F").trim().equals("")){counselling_50_F="0";} else {counselling_50_F=request.getParameter("counselling_50_F");}
 if(request.getParameter("counselling_M").trim().equals("")){counselling_M="0";} else {counselling_M=request.getParameter("counselling_M");}
 if(request.getParameter("counselling_F").trim().equals("")){counselling_F="0";} else {counselling_F=request.getParameter("counselling_F");}
 if(request.getParameter("counselling_T").trim().equals("")){counselling_T="0";} else {counselling_T=request.getParameter("counselling_T");}
 
  
  
  String runvalidate="update sgbv_new set "
          + "rapesurvivor_1_M='"+rapesurvivor_1_M+"',"
+ "rapesurvivor_1_F='"+rapesurvivor_1_F+"',"
+ "rapesurvivor_4_M='"+rapesurvivor_4_M+"',"
+ "rapesurvivor_4_F='"+rapesurvivor_4_F+"',"
+ "rapesurvivor_9_M='"+rapesurvivor_9_M+"',"
+ "rapesurvivor_9_F='"+rapesurvivor_9_F+"',"
+ "rapesurvivor_14_M='"+rapesurvivor_14_M+"',"
+ "rapesurvivor_14_F='"+rapesurvivor_14_F+"',"
+ "rapesurvivor_19_M='"+rapesurvivor_19_M+"',"
+ "rapesurvivor_19_F='"+rapesurvivor_19_F+"',"
+ "rapesurvivor_24_M='"+rapesurvivor_24_M+"',"
+ "rapesurvivor_24_F='"+rapesurvivor_24_F+"',"
+ "rapesurvivor_29_M='"+rapesurvivor_29_M+"',"
+ "rapesurvivor_29_F='"+rapesurvivor_29_F+"',"
+ "rapesurvivor_34_M='"+rapesurvivor_34_M+"',"
+ "rapesurvivor_34_F='"+rapesurvivor_34_F+"',"
+ "rapesurvivor_39_M='"+rapesurvivor_39_M+"',"
+ "rapesurvivor_39_F='"+rapesurvivor_39_F+"',"
+ "rapesurvivor_49_M='"+rapesurvivor_49_M+"',"
+ "rapesurvivor_49_F='"+rapesurvivor_49_F+"',"
+ "rapesurvivor_50_M='"+rapesurvivor_50_M+"',"
+ "rapesurvivor_50_F='"+rapesurvivor_50_F+"',"
+ "rapesurvivor_M='"+rapesurvivor_M+"',"
+ "rapesurvivor_F='"+rapesurvivor_F+"',"
+ "rapesurvivor_T='"+rapesurvivor_T+"',"
+ "presenting_72hr_1_M='"+presenting_72hr_1_M+"',"
+ "presenting_72hr_1_F='"+presenting_72hr_1_F+"',"
+ "presenting_72hr_4_M='"+presenting_72hr_4_M+"',"
+ "presenting_72hr_4_F='"+presenting_72hr_4_F+"',"
+ "presenting_72hr_9_M='"+presenting_72hr_9_M+"',"
+ "presenting_72hr_9_F='"+presenting_72hr_9_F+"',"
+ "presenting_72hr_14_M='"+presenting_72hr_14_M+"',"
+ "presenting_72hr_14_F='"+presenting_72hr_14_F+"',"
+ "presenting_72hr_19_M='"+presenting_72hr_19_M+"',"
+ "presenting_72hr_19_F='"+presenting_72hr_19_F+"',"
+ "presenting_72hr_24_M='"+presenting_72hr_24_M+"',"
+ "presenting_72hr_24_F='"+presenting_72hr_24_F+"',"
+ "presenting_72hr_29_M='"+presenting_72hr_29_M+"',"
+ "presenting_72hr_29_F='"+presenting_72hr_29_F+"',"
+ "presenting_72hr_34_M='"+presenting_72hr_34_M+"',"
+ "presenting_72hr_34_F='"+presenting_72hr_34_F+"',"
+ "presenting_72hr_39_M='"+presenting_72hr_39_M+"',"
+ "presenting_72hr_39_F='"+presenting_72hr_39_F+"',"
+ "presenting_72hr_49_M='"+presenting_72hr_49_M+"',"
+ "presenting_72hr_49_F='"+presenting_72hr_49_F+"',"
+ "presenting_72hr_50_M='"+presenting_72hr_50_M+"',"
+ "presenting_72hr_50_F='"+presenting_72hr_50_F+"',"
+ "presenting_72hr_M='"+presenting_72hr_M+"',"
+ "presenting_72hr_F='"+presenting_72hr_F+"',"
+ "presenting_72hr_T='"+presenting_72hr_T+"',"
+ "initiatedpep_1_M='"+initiatedpep_1_M+"',"
+ "initiatedpep_1_F='"+initiatedpep_1_F+"',"
+ "initiatedpep_4_M='"+initiatedpep_4_M+"',"
+ "initiatedpep_4_F='"+initiatedpep_4_F+"',"
+ "initiatedpep_9_M='"+initiatedpep_9_M+"',"
+ "initiatedpep_9_F='"+initiatedpep_9_F+"',"
+ "initiatedpep_14_M='"+initiatedpep_14_M+"',"
+ "initiatedpep_14_F='"+initiatedpep_14_F+"',"
+ "initiatedpep_19_M='"+initiatedpep_19_M+"',"
+ "initiatedpep_19_F='"+initiatedpep_19_F+"',"
+ "initiatedpep_24_M='"+initiatedpep_24_M+"',"
+ "initiatedpep_24_F='"+initiatedpep_24_F+"',"
+ "initiatedpep_29_M='"+initiatedpep_29_M+"',"
+ "initiatedpep_29_F='"+initiatedpep_29_F+"',"
+ "initiatedpep_34_M='"+initiatedpep_34_M+"',"
+ "initiatedpep_34_F='"+initiatedpep_34_F+"',"
+ "initiatedpep_39_M='"+initiatedpep_39_M+"',"
+ "initiatedpep_39_F='"+initiatedpep_39_F+"',"
+ "initiatedpep_49_M='"+initiatedpep_49_M+"',"
+ "initiatedpep_49_F='"+initiatedpep_49_F+"',"
+ "initiatedpep_50_M='"+initiatedpep_50_M+"',"
+ "initiatedpep_50_F='"+initiatedpep_50_F+"',"
+ "initiatedpep_M='"+initiatedpep_M+"',"
+ "initiatedpep_F='"+initiatedpep_F+"',"
+ "initiatedpep_T='"+initiatedpep_T+"',"
+ "sti_1_M='"+sti_1_M+"',"
+ "sti_1_F='"+sti_1_F+"',"
+ "sti_4_M='"+sti_4_M+"',"
+ "sti_4_F='"+sti_4_F+"',"
+ "sti_9_M='"+sti_9_M+"',"
+ "sti_9_F='"+sti_9_F+"',"
+ "sti_14_M='"+sti_14_M+"',"
+ "sti_14_F='"+sti_14_F+"',"
+ "sti_19_M='"+sti_19_M+"',"
+ "sti_19_F='"+sti_19_F+"',"
+ "sti_24_M='"+sti_24_M+"',"
+ "sti_24_F='"+sti_24_F+"',"
+ "sti_29_M='"+sti_29_M+"',"
+ "sti_29_F='"+sti_29_F+"',"
+ "sti_34_M='"+sti_34_M+"',"
+ "sti_34_F='"+sti_34_F+"',"
+ "sti_39_M='"+sti_39_M+"',"
+ "sti_39_F='"+sti_39_F+"',"
+ "sti_49_M='"+sti_49_M+"',"
+ "sti_49_F='"+sti_49_F+"',"
+ "sti_50_M='"+sti_50_M+"',"
+ "sti_50_F='"+sti_50_F+"',"
+ "sti_M='"+sti_M+"',"
+ "sti_F='"+sti_F+"',"
+ "sti_T='"+sti_T+"',"
+ "ecp_14_F='"+ecp_14_F+"',"
+ "ecp_19_F='"+ecp_19_F+"',"
+ "ecp_24_F='"+ecp_24_F+"',"
+ "ecp_29_F='"+ecp_29_F+"',"
+ "ecp_34_F='"+ecp_34_F+"',"
+ "ecp_39_F='"+ecp_39_F+"',"
+ "ecp_49_F='"+ecp_49_F+"',"
+ "ecp_50_F='"+ecp_50_F+"',"
+ "ecp_F='"+ecp_F+"',"
+ "ecp_T='"+ecp_T+"',"
+ "pill_14_F='"+pill_14_F+"',"
+ "pill_19_F='"+pill_19_F+"',"
+ "pill_24_F='"+pill_24_F+"',"
+ "pill_29_F='"+pill_29_F+"',"
+ "pill_34_F='"+pill_34_F+"',"
+ "pill_39_F='"+pill_39_F+"',"
+ "pill_49_F='"+pill_49_F+"',"
+ "pill_50_F='"+pill_50_F+"',"
+ "pill_F='"+pill_F+"',"
+ "pill_T='"+pill_T+"',"
+ "tested_1_M='"+tested_1_M+"',"
+ "tested_1_F='"+tested_1_F+"',"
+ "tested_4_M='"+tested_4_M+"',"
+ "tested_4_F='"+tested_4_F+"',"
+ "tested_9_M='"+tested_9_M+"',"
+ "tested_9_F='"+tested_9_F+"',"
+ "tested_14_M='"+tested_14_M+"',"
+ "tested_14_F='"+tested_14_F+"',"
+ "tested_19_M='"+tested_19_M+"',"
+ "tested_19_F='"+tested_19_F+"',"
+ "tested_24_M='"+tested_24_M+"',"
+ "tested_24_F='"+tested_24_F+"',"
+ "tested_29_M='"+tested_29_M+"',"
+ "tested_29_F='"+tested_29_F+"',"
+ "tested_34_M='"+tested_34_M+"',"
+ "tested_34_F='"+tested_34_F+"',"
+ "tested_39_M='"+tested_39_M+"',"
+ "tested_39_F='"+tested_39_F+"',"
+ "tested_49_M='"+tested_49_M+"',"
+ "tested_49_F='"+tested_49_F+"',"
+ "tested_50_M='"+tested_50_M+"',"
+ "tested_50_F='"+tested_50_F+"',"
+ "tested_M='"+tested_M+"',"
+ "tested_F='"+tested_F+"',"
+ "tested_T='"+tested_T+"',"
+ "positive_1_M='"+positive_1_M+"',"
+ "positive_1_F='"+positive_1_F+"',"
+ "positive_4_M='"+positive_4_M+"',"
+ "positive_4_F='"+positive_4_F+"',"
+ "positive_9_M='"+positive_9_M+"',"
+ "positive_9_F='"+positive_9_F+"',"
+ "positive_14_M='"+positive_14_M+"',"
+ "positive_14_F='"+positive_14_F+"',"
+ "positive_19_M='"+positive_19_M+"',"
+ "positive_19_F='"+positive_19_F+"',"
+ "positive_24_M='"+positive_24_M+"',"
+ "positive_24_F='"+positive_24_F+"',"
+ "positive_29_M='"+positive_29_M+"',"
+ "positive_29_F='"+positive_29_F+"',"
+ "positive_34_M='"+positive_34_M+"',"
+ "positive_34_F='"+positive_34_F+"',"
+ "positive_39_M='"+positive_39_M+"',"
+ "positive_39_F='"+positive_39_F+"',"
+ "positive_49_M='"+positive_49_M+"',"
+ "positive_49_F='"+positive_49_F+"',"
+ "positive_50_M='"+positive_50_M+"',"
+ "positive_50_F='"+positive_50_F+"',"
+ "positive_M='"+positive_M+"',"
+ "positive_F='"+positive_F+"',"
+ "positive_T='"+positive_T+"',"
+ "disability_1_M='"+disability_1_M+"',"
+ "disability_1_F='"+disability_1_F+"',"
+ "disability_4_M='"+disability_4_M+"',"
+ "disability_4_F='"+disability_4_F+"',"
+ "disability_9_M='"+disability_9_M+"',"
+ "disability_9_F='"+disability_9_F+"',"
+ "disability_14_M='"+disability_14_M+"',"
+ "disability_14_F='"+disability_14_F+"',"
+ "disability_19_M='"+disability_19_M+"',"
+ "disability_19_F='"+disability_19_F+"',"
+ "disability_24_M='"+disability_24_M+"',"
+ "disability_24_F='"+disability_24_F+"',"
+ "disability_29_M='"+disability_29_M+"',"
+ "disability_29_F='"+disability_29_F+"',"
+ "disability_34_M='"+disability_34_M+"',"
+ "disability_34_F='"+disability_34_F+"',"
+ "disability_39_M='"+disability_39_M+"',"
+ "disability_39_F='"+disability_39_F+"',"
+ "disability_49_M='"+disability_49_M+"',"
+ "disability_49_F='"+disability_49_F+"',"
+ "disability_50_M='"+disability_50_M+"',"
+ "disability_50_F='"+disability_50_F+"',"
+ "disability_M='"+disability_M+"',"
+ "disability_F='"+disability_F+"',"
+ "disability_T='"+disability_T+"',"
+ "perpetrators_1_M='"+perpetrators_1_M+"',"
+ "perpetrators_1_F='"+perpetrators_1_F+"',"
+ "perpetrators_4_M='"+perpetrators_4_M+"',"
+ "perpetrators_4_F='"+perpetrators_4_F+"',"
+ "perpetrators_9_M='"+perpetrators_9_M+"',"
+ "perpetrators_9_F='"+perpetrators_9_F+"',"
+ "perpetrators_14_M='"+perpetrators_14_M+"',"
+ "perpetrators_14_F='"+perpetrators_14_F+"',"
+ "perpetrators_19_M='"+perpetrators_19_M+"',"
+ "perpetrators_19_F='"+perpetrators_19_F+"',"
+ "perpetrators_24_M='"+perpetrators_24_M+"',"
+ "perpetrators_24_F='"+perpetrators_24_F+"',"
+ "perpetrators_29_M='"+perpetrators_29_M+"',"
+ "perpetrators_29_F='"+perpetrators_29_F+"',"
+ "perpetrators_34_M='"+perpetrators_34_M+"',"
+ "perpetrators_34_F='"+perpetrators_34_F+"',"
+ "perpetrators_39_M='"+perpetrators_39_M+"',"
+ "perpetrators_39_F='"+perpetrators_39_F+"',"
+ "perpetrators_49_M='"+perpetrators_49_M+"',"
+ "perpetrators_49_F='"+perpetrators_49_F+"',"
+ "perpetrators_50_M='"+perpetrators_50_M+"',"
+ "perpetrators_50_F='"+perpetrators_50_F+"',"
+ "perpetrators_M='"+perpetrators_M+"',"
+ "perpetrators_F='"+perpetrators_F+"',"
+ "perpetrators_T='"+perpetrators_T+"',"
+ "visit1_1_M='"+visit1_1_M+"',"
+ "visit1_1_F='"+visit1_1_F+"',"
+ "visit1_4_M='"+visit1_4_M+"',"
+ "visit1_4_F='"+visit1_4_F+"',"
+ "visit1_9_M='"+visit1_9_M+"',"
+ "visit1_9_F='"+visit1_9_F+"',"
+ "visit1_14_M='"+visit1_14_M+"',"
+ "visit1_14_F='"+visit1_14_F+"',"
+ "visit1_19_M='"+visit1_19_M+"',"
+ "visit1_19_F='"+visit1_19_F+"',"
+ "visit1_24_M='"+visit1_24_M+"',"
+ "visit1_24_F='"+visit1_24_F+"',"
+ "visit1_29_M='"+visit1_29_M+"',"
+ "visit1_29_F='"+visit1_29_F+"',"
+ "visit1_34_M='"+visit1_34_M+"',"
+ "visit1_34_F='"+visit1_34_F+"',"
+ "visit1_39_M='"+visit1_39_M+"',"
+ "visit1_39_F='"+visit1_39_F+"',"
+ "visit1_49_M='"+visit1_49_M+"',"
+ "visit1_49_F='"+visit1_49_F+"',"
+ "visit1_50_M='"+visit1_50_M+"',"
+ "visit1_50_F='"+visit1_50_F+"',"
+ "visit1_M='"+visit1_M+"',"
+ "visit1_F='"+visit1_F+"',"
+ "visit1_T='"+visit1_T+"',"
+ "visit2_1_M='"+visit2_1_M+"',"
+ "visit2_1_F='"+visit2_1_F+"',"
+ "visit2_4_M='"+visit2_4_M+"',"
+ "visit2_4_F='"+visit2_4_F+"',"
+ "visit2_9_M='"+visit2_9_M+"',"
+ "visit2_9_F='"+visit2_9_F+"',"
+ "visit2_14_M='"+visit2_14_M+"',"
+ "visit2_14_F='"+visit2_14_F+"',"
+ "visit2_19_M='"+visit2_19_M+"',"
+ "visit2_19_F='"+visit2_19_F+"',"
+ "visit2_24_M='"+visit2_24_M+"',"
+ "visit2_24_F='"+visit2_24_F+"',"
+ "visit2_29_M='"+visit2_29_M+"',"
+ "visit2_29_F='"+visit2_29_F+"',"
+ "visit2_34_M='"+visit2_34_M+"',"
+ "visit2_34_F='"+visit2_34_F+"',"
+ "visit2_39_M='"+visit2_39_M+"',"
+ "visit2_39_F='"+visit2_39_F+"',"
+ "visit2_49_M='"+visit2_49_M+"',"
+ "visit2_49_F='"+visit2_49_F+"',"
+ "visit2_50_M='"+visit2_50_M+"',"
+ "visit2_50_F='"+visit2_50_F+"',"
+ "visit2_M='"+visit2_M+"',"
+ "visit2_F='"+visit2_F+"',"
+ "visit2_T='"+visit2_T+"',"
+ "visit3_1_M='"+visit3_1_M+"',"
+ "visit3_1_F='"+visit3_1_F+"',"
+ "visit3_4_M='"+visit3_4_M+"',"
+ "visit3_4_F='"+visit3_4_F+"',"
+ "visit3_9_M='"+visit3_9_M+"',"
+ "visit3_9_F='"+visit3_9_F+"',"
+ "visit3_14_M='"+visit3_14_M+"',"
+ "visit3_14_F='"+visit3_14_F+"',"
+ "visit3_19_M='"+visit3_19_M+"',"
+ "visit3_19_F='"+visit3_19_F+"',"
+ "visit3_24_M='"+visit3_24_M+"',"
+ "visit3_24_F='"+visit3_24_F+"',"
+ "visit3_29_M='"+visit3_29_M+"',"
+ "visit3_29_F='"+visit3_29_F+"',"
+ "visit3_34_M='"+visit3_34_M+"',"
+ "visit3_34_F='"+visit3_34_F+"',"
+ "visit3_39_M='"+visit3_39_M+"',"
+ "visit3_39_F='"+visit3_39_F+"',"
+ "visit3_49_M='"+visit3_49_M+"',"
+ "visit3_49_F='"+visit3_49_F+"',"
+ "visit3_50_M='"+visit3_50_M+"',"
+ "visit3_50_F='"+visit3_50_F+"',"
+ "visit3_M='"+visit3_M+"',"
+ "visit3_F='"+visit3_F+"',"
+ "visit3_T='"+visit3_T+"',"
+ "visit4_1_M='"+visit4_1_M+"',"
+ "visit4_1_F='"+visit4_1_F+"',"
+ "visit4_4_M='"+visit4_4_M+"',"
+ "visit4_4_F='"+visit4_4_F+"',"
+ "visit4_9_M='"+visit4_9_M+"',"
+ "visit4_9_F='"+visit4_9_F+"',"
+ "visit4_14_M='"+visit4_14_M+"',"
+ "visit4_14_F='"+visit4_14_F+"',"
+ "visit4_19_M='"+visit4_19_M+"',"
+ "visit4_19_F='"+visit4_19_F+"',"
+ "visit4_24_M='"+visit4_24_M+"',"
+ "visit4_24_F='"+visit4_24_F+"',"
+ "visit4_29_M='"+visit4_29_M+"',"
+ "visit4_29_F='"+visit4_29_F+"',"
+ "visit4_34_M='"+visit4_34_M+"',"
+ "visit4_34_F='"+visit4_34_F+"',"
+ "visit4_39_M='"+visit4_39_M+"',"
+ "visit4_39_F='"+visit4_39_F+"',"
+ "visit4_49_M='"+visit4_49_M+"',"
+ "visit4_49_F='"+visit4_49_F+"',"
+ "visit4_50_M='"+visit4_50_M+"',"
+ "visit4_50_F='"+visit4_50_F+"',"
+ "visit4_M='"+visit4_M+"',"
+ "visit4_F='"+visit4_F+"',"
+ "visit4_T='"+visit4_T+"',"
+ "visit5_1_M='"+visit5_1_M+"',"
+ "visit5_1_F='"+visit5_1_F+"',"
+ "visit5_4_M='"+visit5_4_M+"',"
+ "visit5_4_F='"+visit5_4_F+"',"
+ "visit5_9_M='"+visit5_9_M+"',"
+ "visit5_9_F='"+visit5_9_F+"',"
+ "visit5_14_M='"+visit5_14_M+"',"
+ "visit5_14_F='"+visit5_14_F+"',"
+ "visit5_19_M='"+visit5_19_M+"',"
+ "visit5_19_F='"+visit5_19_F+"',"
+ "visit5_24_M='"+visit5_24_M+"',"
+ "visit5_24_F='"+visit5_24_F+"',"
+ "visit5_29_M='"+visit5_29_M+"',"
+ "visit5_29_F='"+visit5_29_F+"',"
+ "visit5_34_M='"+visit5_34_M+"',"
+ "visit5_34_F='"+visit5_34_F+"',"
+ "visit5_39_M='"+visit5_39_M+"',"
+ "visit5_39_F='"+visit5_39_F+"',"
+ "visit5_49_M='"+visit5_49_M+"',"
+ "visit5_49_F='"+visit5_49_F+"',"
+ "visit5_50_M='"+visit5_50_M+"',"
+ "visit5_50_F='"+visit5_50_F+"',"
+ "visit5_M='"+visit5_M+"',"
+ "visit5_F='"+visit5_F+"',"
+ "visit5_T='"+visit5_T+"',"
+ "completedpep_1_M='"+completedpep_1_M+"',"
+ "completedpep_1_F='"+completedpep_1_F+"',"
+ "completedpep_4_M='"+completedpep_4_M+"',"
+ "completedpep_4_F='"+completedpep_4_F+"',"
+ "completedpep_9_M='"+completedpep_9_M+"',"
+ "completedpep_9_F='"+completedpep_9_F+"',"
+ "completedpep_14_M='"+completedpep_14_M+"',"
+ "completedpep_14_F='"+completedpep_14_F+"',"
+ "completedpep_19_M='"+completedpep_19_M+"',"
+ "completedpep_19_F='"+completedpep_19_F+"',"
+ "completedpep_24_M='"+completedpep_24_M+"',"
+ "completedpep_24_F='"+completedpep_24_F+"',"
+ "completedpep_29_M='"+completedpep_29_M+"',"
+ "completedpep_29_F='"+completedpep_29_F+"',"
+ "completedpep_34_M='"+completedpep_34_M+"',"
+ "completedpep_34_F='"+completedpep_34_F+"',"
+ "completedpep_39_M='"+completedpep_39_M+"',"
+ "completedpep_39_F='"+completedpep_39_F+"',"
+ "completedpep_49_M='"+completedpep_49_M+"',"
+ "completedpep_49_F='"+completedpep_49_F+"',"
+ "completedpep_50_M='"+completedpep_50_M+"',"
+ "completedpep_50_F='"+completedpep_50_F+"',"
+ "completedpep_M='"+completedpep_M+"',"
+ "completedpep_F='"+completedpep_F+"',"
+ "completedpep_T='"+completedpep_T+"',"
+ "seroconverted_1_M='"+seroconverted_1_M+"',"
+ "seroconverted_1_F='"+seroconverted_1_F+"',"
+ "seroconverted_4_M='"+seroconverted_4_M+"',"
+ "seroconverted_4_F='"+seroconverted_4_F+"',"
+ "seroconverted_9_M='"+seroconverted_9_M+"',"
+ "seroconverted_9_F='"+seroconverted_9_F+"',"
+ "seroconverted_14_M='"+seroconverted_14_M+"',"
+ "seroconverted_14_F='"+seroconverted_14_F+"',"
+ "seroconverted_19_M='"+seroconverted_19_M+"',"
+ "seroconverted_19_F='"+seroconverted_19_F+"',"
+ "seroconverted_24_M='"+seroconverted_24_M+"',"
+ "seroconverted_24_F='"+seroconverted_24_F+"',"
+ "seroconverted_29_M='"+seroconverted_29_M+"',"
+ "seroconverted_29_F='"+seroconverted_29_F+"',"
+ "seroconverted_34_M='"+seroconverted_34_M+"',"
+ "seroconverted_34_F='"+seroconverted_34_F+"',"
+ "seroconverted_39_M='"+seroconverted_39_M+"',"
+ "seroconverted_39_F='"+seroconverted_39_F+"',"
+ "seroconverted_49_M='"+seroconverted_49_M+"',"
+ "seroconverted_49_F='"+seroconverted_49_F+"',"
+ "seroconverted_50_M='"+seroconverted_50_M+"',"
+ "seroconverted_50_F='"+seroconverted_50_F+"',"
+ "seroconverted_M='"+seroconverted_M+"',"
+ "seroconverted_F='"+seroconverted_F+"',"
+ "seroconverted_T='"+seroconverted_T+"',"
+ "Pregnant_14_F='"+Pregnant_14_F+"',"
+ "Pregnant_19_F='"+Pregnant_19_F+"',"
+ "Pregnant_24_F='"+Pregnant_24_F+"',"
+ "Pregnant_29_F='"+Pregnant_29_F+"',"
+ "Pregnant_34_F='"+Pregnant_34_F+"',"
+ "Pregnant_39_F='"+Pregnant_39_F+"',"
+ "Pregnant_49_F='"+Pregnant_49_F+"',"
+ "Pregnant_50_F='"+Pregnant_50_F+"',"
+ "Pregnant_F='"+Pregnant_F+"',"
+ "Pregnant_T='"+Pregnant_T+"',"
+ "counselling_1_M='"+counselling_1_M+"',"
+ "counselling_1_F='"+counselling_1_F+"',"
+ "counselling_4_M='"+counselling_4_M+"',"
+ "counselling_4_F='"+counselling_4_F+"',"
+ "counselling_9_M='"+counselling_9_M+"',"
+ "counselling_9_F='"+counselling_9_F+"',"
+ "counselling_14_M='"+counselling_14_M+"',"
+ "counselling_14_F='"+counselling_14_F+"',"
+ "counselling_19_M='"+counselling_19_M+"',"
+ "counselling_19_F='"+counselling_19_F+"',"
+ "counselling_24_M='"+counselling_24_M+"',"
+ "counselling_24_F='"+counselling_24_F+"',"
+ "counselling_29_M='"+counselling_29_M+"',"
+ "counselling_29_F='"+counselling_29_F+"',"
+ "counselling_34_M='"+counselling_34_M+"',"
+ "counselling_34_F='"+counselling_34_F+"',"
+ "counselling_39_M='"+counselling_39_M+"',"
+ "counselling_39_F='"+counselling_39_F+"',"
+ "counselling_49_M='"+counselling_49_M+"',"
+ "counselling_49_F='"+counselling_49_F+"',"
+ "counselling_50_M='"+counselling_50_M+"',"
+ "counselling_50_F='"+counselling_50_F+"',"
+ "counselling_M='"+counselling_M+"',"
+ "counselling_F='"+counselling_F+"',"
+ "counselling_T='"+counselling_T+"',"

+ "isValidated='1' where id='"+tableid+"'";        
System.out.println(runvalidate);  
   conn.st.executeUpdate(runvalidate);
    session.setAttribute("validatesgbv", "<font color=\"green\"><b>Form SGBV Validated Successfully.</b></font>");
  
    
     int monthDiff=0;
     String getMonths="SELECT TIMESTAMPDIFF(MONTH, timestamp, now()) FROM sgbv_new WHERE id='"+tableid+"'";
     conn.rs1=conn.st1.executeQuery(getMonths);
     if(conn.rs1.next()==true){
         System.out.println("months are : "+conn.rs1.getString(1));
    monthDiff=conn.rs1.getInt(1);
     }
     if(monthDiff>0){
//      UPDATE THE COLUMN   
       java.util.Date date= new java.util.Date();
Timestamp lastUpdatedOn =new Timestamp(date.getTime()); 

     String updateLast="UPDATE sgbv_new SET updatedBy='"+userid+"', updatedOn='"+lastUpdatedOn+"' WHERE id='"+tableid+"'" ;   
       conn.st2.executeUpdate(updateLast);
     }
     
     
     
     
     if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
     if(conn.st2!=null){conn.st2.close();}
     if(conn.st3!=null){conn.st3.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.rs3!=null){conn.rs3.close();}
     if(conn.conn!=null){conn.conn.close();}
   
    
    
}
        
  finally {
           // out.close();
        }
        
   
        response.sendRedirect("loadsgbv_new.jsp");
}

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(validatesgbv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(validatesgbv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
