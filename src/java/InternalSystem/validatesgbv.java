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
public class validatesgbv extends HttpServlet {
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
    

  
String rapesurvivor_11_M,rapesurvivor_11_F,rapesurvivor_17_M,rapesurvivor_17_F,rapesurvivor_49_M,rapesurvivor_49_F,rapesurvivor_50_M,rapesurvivor_50_F,rapesurvivor_M,rapesurvivor_F,rapesurvivor_T;
String presenting_72hr_11_M,presenting_72hr_11_F,presenting_72hr_17_M,presenting_72hr_17_F,presenting_72hr_49_M,presenting_72hr_49_F,presenting_72hr_50_M,presenting_72hr_50_F,presenting_72hr_M,presenting_72hr_F,presenting_72hr_T;
String initiatedpep_11_M,initiatedpep_11_F,initiatedpep_17_M,initiatedpep_17_F,initiatedpep_49_M,initiatedpep_49_F,initiatedpep_50_M,initiatedpep_50_F,initiatedpep_M,initiatedpep_F,initiatedpep_T;
String sti_11_M,sti_11_F,sti_17_M,sti_17_F,sti_49_M,sti_49_F,sti_50_M,sti_50_F,sti_M,sti_F,sti_T;
String ecp_11,ecp_17,ecp_49,ecp_50,ecp,ecp_T;
String pill_11,pill_17,pill_49,pill_50,pill,pill_T;
String tested_11_M,tested_11_F,tested_17_M,tested_17_F,tested_49_M,tested_49_F,tested_50_M,tested_50_F,tested_M,tested_F,tested_T;
String positive_11_M,positive_11_F,positive_17_M,positive_17_F,positive_49_M,positive_49_F,positive_50_M,positive_50_F,positive_M,positive_F,positive_T;
String disability_11_M,disability_11_F,disability_17_M,disability_17_F,disability_49_M,disability_49_F,disability_50_M,disability_50_F,disability_M,disability_F,disability_T;
String perpetrators_11_M,perpetrators_11_F,perpetrators_17_M,perpetrators_17_F,perpetrators_49_M,perpetrators_49_F,perpetrators_50_M,perpetrators_50_F,perpetrators_M,perpetrators_F,perpetrators_T; 
String visit1_11_M,visit1_11_F,visit1_17_M,visit1_17_F,visit1_49_M,visit1_49_F,visit1_50_M,visit1_50_F,visit1_M,visit1_F,visit1_T;
String visit2_11_M,visit2_11_F,visit2_17_M,visit2_17_F,visit2_49_M,visit2_49_F,visit2_50_M,visit2_50_F,visit2_M,visit2_F,visit2_T;
String visit3_11_M,visit3_11_F,visit3_17_M,visit3_17_F,visit3_49_M,visit3_49_F,visit3_50_M,visit3_50_F,visit3_M,visit3_F,visit3_T;
String visit4_11_M,visit4_11_F,visit4_17_M,visit4_17_F,visit4_49_M,visit4_49_F,visit4_50_M,visit4_50_F,visit4_M,visit4_F,visit4_T;
String visit5_11_M,visit5_11_F,visit5_17_M,visit5_17_F,visit5_49_M,visit5_49_F,visit5_50_M,visit5_50_F,visit5_M,visit5_F,visit5_T;
String completedpep_11_M,completedpep_11_F,completedpep_17_M,completedpep_17_F,completedpep_49_M,completedpep_49_F,completedpep_50_M,completedpep_50_F,completedpep_M,completedpep_F,completedpep_T;
String seroconverted_11_M,seroconverted_11_F,seroconverted_17_M,seroconverted_17_F,seroconverted_49_M,seroconverted_49_F,seroconverted_50_M,seroconverted_50_F,seroconverted_M,seroconverted_F,seroconverted_T;
String Pregnant_11,Pregnant_17,Pregnant_49,Pregnant_50,Pregnant,Pregnant_T;
String counselling_11_M,counselling_11_F,counselling_17_M,counselling_17_F,counselling_49_M,counselling_49_F,counselling_50_M,counselling_50_F,counselling_M,counselling_F,counselling_T;






	
        


//initialize variables
 rapesurvivor_11_M=rapesurvivor_11_F=rapesurvivor_17_M=rapesurvivor_17_F=rapesurvivor_49_M=rapesurvivor_49_F=rapesurvivor_50_M=rapesurvivor_50_F=rapesurvivor_M=rapesurvivor_F=rapesurvivor_T="0";
 presenting_72hr_11_M=presenting_72hr_11_F=presenting_72hr_17_M=presenting_72hr_17_F=presenting_72hr_49_M=presenting_72hr_49_F=presenting_72hr_50_M=presenting_72hr_50_F=presenting_72hr_M=presenting_72hr_F=presenting_72hr_T="0";
 initiatedpep_11_M=initiatedpep_11_F=initiatedpep_17_M=initiatedpep_17_F=initiatedpep_49_M=initiatedpep_49_F=initiatedpep_50_M=initiatedpep_50_F=initiatedpep_M=initiatedpep_F=initiatedpep_T="0";
 sti_11_M=sti_11_F=sti_17_M=sti_17_F=sti_49_M=sti_49_F=sti_50_M=sti_50_F=sti_M=sti_F=sti_T="0";
 ecp_11=ecp_17=ecp_49=ecp_50=ecp=ecp_T="0";
 pill_11=pill_17=pill_49=pill_50=pill=pill_T="0";
 tested_11_M=tested_11_F=tested_17_M=tested_17_F=tested_49_M=tested_49_F=tested_50_M=tested_50_F=tested_M=tested_F=tested_T="0";
 positive_11_M=positive_11_F=positive_17_M=positive_17_F=positive_49_M=positive_49_F=positive_50_M=positive_50_F=positive_M=positive_F=positive_T="0";
 disability_11_M=disability_11_F=disability_17_M=disability_17_F=disability_49_M=disability_49_F=disability_50_M=disability_50_F=disability_M=disability_F=disability_T="0";
 perpetrators_11_M=perpetrators_11_F=perpetrators_17_M=perpetrators_17_F=perpetrators_49_M=perpetrators_49_F=perpetrators_50_M=perpetrators_50_F=perpetrators_M=perpetrators_F=perpetrators_T="0"; 
 visit1_11_M=visit1_11_F=visit1_17_M=visit1_17_F=visit1_49_M=visit1_49_F=visit1_50_M=visit1_50_F=visit1_M=visit1_F=visit1_T="0";
 visit2_11_M=visit2_11_F=visit2_17_M=visit2_17_F=visit2_49_M=visit2_49_F=visit2_50_M=visit2_50_F=visit2_M=visit2_F=visit2_T="0";
 visit3_11_M=visit3_11_F=visit3_17_M=visit3_17_F=visit3_49_M=visit3_49_F=visit3_50_M=visit3_50_F=visit3_M=visit3_F=visit3_T="0";
 visit4_11_M=visit4_11_F=visit4_17_M=visit4_17_F=visit4_49_M=visit4_49_F=visit4_50_M=visit4_50_F=visit4_M=visit4_F=visit4_T="0";
 visit5_11_M=visit5_11_F=visit5_17_M=visit5_17_F=visit5_49_M=visit5_49_F=visit5_50_M=visit5_50_F=visit5_M=visit5_F=visit5_T="0";
 completedpep_11_M=completedpep_11_F=completedpep_17_M=completedpep_17_F=completedpep_49_M=completedpep_49_F=completedpep_50_M=completedpep_50_F=completedpep_M=completedpep_F=completedpep_T="0";
 seroconverted_11_M=seroconverted_11_F=seroconverted_17_M=seroconverted_17_F=seroconverted_49_M=seroconverted_49_F=seroconverted_50_M=seroconverted_50_F=seroconverted_M=seroconverted_F=seroconverted_T="0";
 Pregnant_11=Pregnant_17=Pregnant_49=Pregnant_50=Pregnant=Pregnant_T="0";
 counselling_11_M=counselling_11_F=counselling_17_M=counselling_17_F=counselling_49_M=counselling_49_F=counselling_50_M=counselling_50_F=counselling_M=counselling_F=counselling_T="0";


 
 
           
String tableid=year+"_"+month+"_"+facil;

 
  if(request.getParameter("visit1_11_M").trim().equals("")){visit1_11_M="0";} else { visit1_11_M=request.getParameter("visit1_11_M");}
  if(request.getParameter("visit2_11_M").trim().equals("")){visit2_11_M="0";} else { visit2_11_M=request.getParameter("visit2_11_M");}
  if(request.getParameter("visit3_11_M").trim().equals("")){visit3_11_M="0";} else { visit3_11_M=request.getParameter("visit3_11_M");}
  if(request.getParameter("visit4_11_M").trim().equals("")){visit4_11_M="0";} else { visit4_11_M=request.getParameter("visit4_11_M");}
  if(request.getParameter("visit5_11_M").trim().equals("")){visit5_11_M="0";} else { visit5_11_M=request.getParameter("visit5_11_M");}
  if(request.getParameter("completedpep_11_M").trim().equals("")){completedpep_11_M="0";} else { completedpep_11_M=request.getParameter("completedpep_11_M");}
  if(request.getParameter("seroconverted_11_M").trim().equals("")){seroconverted_11_M="0";} else { seroconverted_11_M=request.getParameter("seroconverted_11_M");}
  if(request.getParameter("counselling_11_M").trim().equals("")){counselling_11_M="0";} else { counselling_11_M=request.getParameter("counselling_11_M");}
  if(request.getParameter("visit1_11_F").trim().equals("")){visit1_11_F="0";} else { visit1_11_F=request.getParameter("visit1_11_F");}
  if(request.getParameter("visit2_11_F").trim().equals("")){visit2_11_F="0";} else { visit2_11_F=request.getParameter("visit2_11_F");}
  if(request.getParameter("visit3_11_F").trim().equals("")){visit3_11_F="0";} else { visit3_11_F=request.getParameter("visit3_11_F");}
  if(request.getParameter("visit4_11_F").trim().equals("")){visit4_11_F="0";} else { visit4_11_F=request.getParameter("visit4_11_F");}
  if(request.getParameter("visit5_11_F").trim().equals("")){visit5_11_F="0";} else { visit5_11_F=request.getParameter("visit5_11_F");}
  if(request.getParameter("completedpep_11_F").trim().equals("")){completedpep_11_F="0";} else { completedpep_11_F=request.getParameter("completedpep_11_F");}
  if(request.getParameter("seroconverted_11_F").trim().equals("")){seroconverted_11_F="0";} else { seroconverted_11_F=request.getParameter("seroconverted_11_F");}
  if(request.getParameter("Pregnant_11").trim().equals("")){Pregnant_11="0";} else { Pregnant_11=request.getParameter("Pregnant_11");}
  if(request.getParameter("counselling_11_F").trim().equals("")){counselling_11_F="0";} else { counselling_11_F=request.getParameter("counselling_11_F");}
  if(request.getParameter("visit1_17_M").trim().equals("")){visit1_17_M="0";} else { visit1_17_M=request.getParameter("visit1_17_M");}
  if(request.getParameter("visit2_17_M").trim().equals("")){visit2_17_M="0";} else { visit2_17_M=request.getParameter("visit2_17_M");}
  if(request.getParameter("visit3_17_M").trim().equals("")){visit3_17_M="0";} else { visit3_17_M=request.getParameter("visit3_17_M");}
  if(request.getParameter("visit4_17_M").trim().equals("")){visit4_17_M="0";} else { visit4_17_M=request.getParameter("visit4_17_M");}
  if(request.getParameter("visit5_17_M").trim().equals("")){visit5_17_M="0";} else { visit5_17_M=request.getParameter("visit5_17_M");}
  if(request.getParameter("completedpep_17_M").trim().equals("")){completedpep_17_M="0";} else { completedpep_17_M=request.getParameter("completedpep_17_M");}
  if(request.getParameter("seroconverted_17_M").trim().equals("")){seroconverted_17_M="0";} else { seroconverted_17_M=request.getParameter("seroconverted_17_M");}
  if(request.getParameter("counselling_17_M").trim().equals("")){counselling_17_M="0";} else { counselling_17_M=request.getParameter("counselling_17_M");}
  if(request.getParameter("visit1_17_F").trim().equals("")){visit1_17_F="0";} else { visit1_17_F=request.getParameter("visit1_17_F");}
  if(request.getParameter("visit2_17_F").trim().equals("")){visit2_17_F="0";} else { visit2_17_F=request.getParameter("visit2_17_F");}
  if(request.getParameter("visit3_17_F").trim().equals("")){visit3_17_F="0";} else { visit3_17_F=request.getParameter("visit3_17_F");}
  if(request.getParameter("visit4_17_F").trim().equals("")){visit4_17_F="0";} else { visit4_17_F=request.getParameter("visit4_17_F");}
  if(request.getParameter("visit5_17_F").trim().equals("")){visit5_17_F="0";} else { visit5_17_F=request.getParameter("visit5_17_F");}
  if(request.getParameter("completedpep_17_F").trim().equals("")){completedpep_17_F="0";} else { completedpep_17_F=request.getParameter("completedpep_17_F");}
  if(request.getParameter("seroconverted_17_F").trim().equals("")){seroconverted_17_F="0";} else { seroconverted_17_F=request.getParameter("seroconverted_17_F");}
  if(request.getParameter("Pregnant_17").trim().equals("")){Pregnant_17="0";} else { Pregnant_17=request.getParameter("Pregnant_17");}
  if(request.getParameter("counselling_17_F").trim().equals("")){counselling_17_F="0";} else { counselling_17_F=request.getParameter("counselling_17_F");}
  if(request.getParameter("visit1_49_M").trim().equals("")){visit1_49_M="0";} else { visit1_49_M=request.getParameter("visit1_49_M");}
  if(request.getParameter("visit2_49_M").trim().equals("")){visit2_49_M="0";} else { visit2_49_M=request.getParameter("visit2_49_M");}
  if(request.getParameter("visit3_49_M").trim().equals("")){visit3_49_M="0";} else { visit3_49_M=request.getParameter("visit3_49_M");}
  if(request.getParameter("visit4_49_M").trim().equals("")){visit4_49_M="0";} else { visit4_49_M=request.getParameter("visit4_49_M");}
  if(request.getParameter("visit5_49_M").trim().equals("")){visit5_49_M="0";} else { visit5_49_M=request.getParameter("visit5_49_M");}
  if(request.getParameter("completedpep_49_M").trim().equals("")){completedpep_49_M="0";} else { completedpep_49_M=request.getParameter("completedpep_49_M");}
  if(request.getParameter("seroconverted_49_M").trim().equals("")){seroconverted_49_M="0";} else { seroconverted_49_M=request.getParameter("seroconverted_49_M");}
  if(request.getParameter("counselling_49_M").trim().equals("")){counselling_49_M="0";} else { counselling_49_M=request.getParameter("counselling_49_M");}
  if(request.getParameter("visit1_49_F").trim().equals("")){visit1_49_F="0";} else { visit1_49_F=request.getParameter("visit1_49_F");}
  if(request.getParameter("visit2_49_F").trim().equals("")){visit2_49_F="0";} else { visit2_49_F=request.getParameter("visit2_49_F");}
  if(request.getParameter("visit3_49_F").trim().equals("")){visit3_49_F="0";} else { visit3_49_F=request.getParameter("visit3_49_F");}
  if(request.getParameter("visit4_49_F").trim().equals("")){visit4_49_F="0";} else { visit4_49_F=request.getParameter("visit4_49_F");}
  if(request.getParameter("visit5_49_F").trim().equals("")){visit5_49_F="0";} else { visit5_49_F=request.getParameter("visit5_49_F");}
  if(request.getParameter("completedpep_49_F").trim().equals("")){completedpep_49_F="0";} else { completedpep_49_F=request.getParameter("completedpep_49_F");}
  if(request.getParameter("seroconverted_49_F").trim().equals("")){seroconverted_49_F="0";} else { seroconverted_49_F=request.getParameter("seroconverted_49_F");}
  if(request.getParameter("Pregnant_49").trim().equals("")){Pregnant_49="0";} else { Pregnant_49=request.getParameter("Pregnant_49");}
  if(request.getParameter("counselling_49_F").trim().equals("")){counselling_49_F="0";} else { counselling_49_F=request.getParameter("counselling_49_F");}
  if(request.getParameter("visit1_50_M").trim().equals("")){visit1_50_M="0";} else { visit1_50_M=request.getParameter("visit1_50_M");}
  if(request.getParameter("visit2_50_M").trim().equals("")){visit2_50_M="0";} else { visit2_50_M=request.getParameter("visit2_50_M");}
  if(request.getParameter("visit3_50_M").trim().equals("")){visit3_50_M="0";} else { visit3_50_M=request.getParameter("visit3_50_M");}
  if(request.getParameter("visit4_50_M").trim().equals("")){visit4_50_M="0";} else { visit4_50_M=request.getParameter("visit4_50_M");}
  if(request.getParameter("visit5_50_M").trim().equals("")){visit5_50_M="0";} else { visit5_50_M=request.getParameter("visit5_50_M");}
  if(request.getParameter("completedpep_50_M").trim().equals("")){completedpep_50_M="0";} else { completedpep_50_M=request.getParameter("completedpep_50_M");}
  if(request.getParameter("seroconverted_50_M").trim().equals("")){seroconverted_50_M="0";} else { seroconverted_50_M=request.getParameter("seroconverted_50_M");}
  if(request.getParameter("counselling_50_M").trim().equals("")){counselling_50_M="0";} else { counselling_50_M=request.getParameter("counselling_50_M");}
  if(request.getParameter("visit1_50_F").trim().equals("")){visit1_50_F="0";} else { visit1_50_F=request.getParameter("visit1_50_F");}
  if(request.getParameter("visit2_50_F").trim().equals("")){visit2_50_F="0";} else { visit2_50_F=request.getParameter("visit2_50_F");}
  if(request.getParameter("visit3_50_F").trim().equals("")){visit3_50_F="0";} else { visit3_50_F=request.getParameter("visit3_50_F");}
  if(request.getParameter("visit4_50_F").trim().equals("")){visit4_50_F="0";} else { visit4_50_F=request.getParameter("visit4_50_F");}
  if(request.getParameter("visit5_50_F").trim().equals("")){visit5_50_F="0";} else { visit5_50_F=request.getParameter("visit5_50_F");}
  if(request.getParameter("completedpep_50_F").trim().equals("")){completedpep_50_F="0";} else { completedpep_50_F=request.getParameter("completedpep_50_F");}
  if(request.getParameter("seroconverted_50_F").trim().equals("")){seroconverted_50_F="0";} else { seroconverted_50_F=request.getParameter("seroconverted_50_F");}
  if(request.getParameter("Pregnant_50").trim().equals("")){Pregnant_50="0";} else { Pregnant_50=request.getParameter("Pregnant_50");}
  if(request.getParameter("counselling_50_F").trim().equals("")){counselling_50_F="0";} else { counselling_50_F=request.getParameter("counselling_50_F");}
  if(request.getParameter("visit1_M").trim().equals("")){visit1_M="0";} else { visit1_M=request.getParameter("visit1_M");}
  if(request.getParameter("visit2_M").trim().equals("")){visit2_M="0";} else { visit2_M=request.getParameter("visit2_M");}
  if(request.getParameter("visit3_M").trim().equals("")){visit3_M="0";} else { visit3_M=request.getParameter("visit3_M");}
  if(request.getParameter("visit4_M").trim().equals("")){visit4_M="0";} else { visit4_M=request.getParameter("visit4_M");}
  if(request.getParameter("visit5_M").trim().equals("")){visit5_M="0";} else { visit5_M=request.getParameter("visit5_M");}
  if(request.getParameter("completedpep_M").trim().equals("")){completedpep_M="0";} else { completedpep_M=request.getParameter("completedpep_M");}
  if(request.getParameter("seroconverted_M").trim().equals("")){seroconverted_M="0";} else { seroconverted_M=request.getParameter("seroconverted_M");}
  if(request.getParameter("counselling_M").trim().equals("")){counselling_M="0";} else { counselling_M=request.getParameter("counselling_M");}
  if(request.getParameter("visit1_F").trim().equals("")){visit1_F="0";} else { visit1_F=request.getParameter("visit1_F");}
  if(request.getParameter("visit2_F").trim().equals("")){visit2_F="0";} else { visit2_F=request.getParameter("visit2_F");}
  if(request.getParameter("visit3_F").trim().equals("")){visit3_F="0";} else { visit3_F=request.getParameter("visit3_F");}
  if(request.getParameter("visit4_F").trim().equals("")){visit4_F="0";} else { visit4_F=request.getParameter("visit4_F");}
  if(request.getParameter("visit5_F").trim().equals("")){visit5_F="0";} else { visit5_F=request.getParameter("visit5_F");}
  if(request.getParameter("completedpep_F").trim().equals("")){completedpep_F="0";} else { completedpep_F=request.getParameter("completedpep_F");}
  if(request.getParameter("seroconverted_F").trim().equals("")){seroconverted_F="0";} else { seroconverted_F=request.getParameter("seroconverted_F");}
  if(request.getParameter("Pregnant").trim().equals("")){Pregnant="0";} else { Pregnant=request.getParameter("Pregnant");}
  if(request.getParameter("counselling_F").trim().equals("")){counselling_F="0";} else { counselling_F=request.getParameter("counselling_F");}
  if(request.getParameter("visit1_T").trim().equals("")){visit1_T="0";} else { visit1_T=request.getParameter("visit1_T");}
  if(request.getParameter("visit2_T").trim().equals("")){visit2_T="0";} else { visit2_T=request.getParameter("visit2_T");}
  if(request.getParameter("visit3_T").trim().equals("")){visit3_T="0";} else { visit3_T=request.getParameter("visit3_T");}
  if(request.getParameter("visit4_T").trim().equals("")){visit4_T="0";} else { visit4_T=request.getParameter("visit4_T");}
  if(request.getParameter("visit5_T").trim().equals("")){visit5_T="0";} else { visit5_T=request.getParameter("visit5_T");}
  if(request.getParameter("completedpep_T").trim().equals("")){completedpep_T="0";} else { completedpep_T=request.getParameter("completedpep_T");}
  if(request.getParameter("seroconverted_T").trim().equals("")){seroconverted_T="0";} else { seroconverted_T=request.getParameter("seroconverted_T");}
  if(request.getParameter("Pregnant_T").trim().equals("")){Pregnant_T="0";} else { Pregnant_T=request.getParameter("Pregnant_T");}
  if(request.getParameter("counselling_T").trim().equals("")){counselling_T="0";} else { counselling_T=request.getParameter("counselling_T");}
  if(request.getParameter("rapesurvivor_11_M").trim().equals("")){rapesurvivor_11_M="0";} else { rapesurvivor_11_M=request.getParameter("rapesurvivor_11_M");}
  if(request.getParameter("presenting_72hr_11_M").trim().equals("")){presenting_72hr_11_M="0";} else { presenting_72hr_11_M=request.getParameter("presenting_72hr_11_M");}
  if(request.getParameter("initiatedpep_11_M").trim().equals("")){initiatedpep_11_M="0";} else { initiatedpep_11_M=request.getParameter("initiatedpep_11_M");}
  if(request.getParameter("sti_11_M").trim().equals("")){sti_11_M="0";} else { sti_11_M=request.getParameter("sti_11_M");}
  if(request.getParameter("tested_11_M").trim().equals("")){tested_11_M="0";} else { tested_11_M=request.getParameter("tested_11_M");}
  if(request.getParameter("disability_11_M").trim().equals("")){disability_11_M="0";} else { disability_11_M=request.getParameter("disability_11_M");}
  if(request.getParameter("positive_11_M").trim().equals("")){positive_11_M="0";} else { positive_11_M=request.getParameter("positive_11_M");}
  if(request.getParameter("perpetrators_11_M").trim().equals("")){perpetrators_11_M="0";} else { perpetrators_11_M=request.getParameter("perpetrators_11_M");}
  if(request.getParameter("rapesurvivor_11_F").trim().equals("")){rapesurvivor_11_F="0";} else { rapesurvivor_11_F=request.getParameter("rapesurvivor_11_F");}
  if(request.getParameter("presenting_72hr_11_F").trim().equals("")){presenting_72hr_11_F="0";} else { presenting_72hr_11_F=request.getParameter("presenting_72hr_11_F");}
  if(request.getParameter("initiatedpep_11_F").trim().equals("")){initiatedpep_11_F="0";} else { initiatedpep_11_F=request.getParameter("initiatedpep_11_F");}
  if(request.getParameter("sti_11_F").trim().equals("")){sti_11_F="0";} else { sti_11_F=request.getParameter("sti_11_F");}
  if(request.getParameter("ecp_11").trim().equals("")){ecp_11="0";} else { ecp_11=request.getParameter("ecp_11");}
  if(request.getParameter("pill_11").trim().equals("")){pill_11="0";} else { pill_11=request.getParameter("pill_11");}
  if(request.getParameter("tested_11_F").trim().equals("")){tested_11_F="0";} else { tested_11_F=request.getParameter("tested_11_F");}
  if(request.getParameter("disability_11_F").trim().equals("")){disability_11_F="0";} else { disability_11_F=request.getParameter("disability_11_F");}
  if(request.getParameter("positive_11_F").trim().equals("")){positive_11_F="0";} else { positive_11_F=request.getParameter("positive_11_F");}
  if(request.getParameter("perpetrators_11_F").trim().equals("")){perpetrators_11_F="0";} else { perpetrators_11_F=request.getParameter("perpetrators_11_F");}
  if(request.getParameter("rapesurvivor_17_M").trim().equals("")){rapesurvivor_17_M="0";} else { rapesurvivor_17_M=request.getParameter("rapesurvivor_17_M");}
  if(request.getParameter("presenting_72hr_17_M").trim().equals("")){presenting_72hr_17_M="0";} else { presenting_72hr_17_M=request.getParameter("presenting_72hr_17_M");}
  if(request.getParameter("initiatedpep_17_M").trim().equals("")){initiatedpep_17_M="0";} else { initiatedpep_17_M=request.getParameter("initiatedpep_17_M");}
  if(request.getParameter("sti_17_M").trim().equals("")){sti_17_M="0";} else { sti_17_M=request.getParameter("sti_17_M");}
  if(request.getParameter("tested_17_M").trim().equals("")){tested_17_M="0";} else { tested_17_M=request.getParameter("tested_17_M");}
  if(request.getParameter("disability_17_M").trim().equals("")){disability_17_M="0";} else { disability_17_M=request.getParameter("disability_17_M");}
  if(request.getParameter("positive_17_M").trim().equals("")){positive_17_M="0";} else { positive_17_M=request.getParameter("positive_17_M");}
  if(request.getParameter("perpetrators_17_M").trim().equals("")){perpetrators_17_M="0";} else { perpetrators_17_M=request.getParameter("perpetrators_17_M");}
  if(request.getParameter("rapesurvivor_17_F").trim().equals("")){rapesurvivor_17_F="0";} else { rapesurvivor_17_F=request.getParameter("rapesurvivor_17_F");}
  if(request.getParameter("presenting_72hr_17_F").trim().equals("")){presenting_72hr_17_F="0";} else { presenting_72hr_17_F=request.getParameter("presenting_72hr_17_F");}
  if(request.getParameter("initiatedpep_17_F").trim().equals("")){initiatedpep_17_F="0";} else { initiatedpep_17_F=request.getParameter("initiatedpep_17_F");}
  if(request.getParameter("sti_17_F").trim().equals("")){sti_17_F="0";} else { sti_17_F=request.getParameter("sti_17_F");}
  if(request.getParameter("ecp_17").trim().equals("")){ecp_17="0";} else { ecp_17=request.getParameter("ecp_17");}
  if(request.getParameter("pill_17").trim().equals("")){pill_17="0";} else { pill_17=request.getParameter("pill_17");}
  if(request.getParameter("tested_17_F").trim().equals("")){tested_17_F="0";} else { tested_17_F=request.getParameter("tested_17_F");}
  if(request.getParameter("disability_17_F").trim().equals("")){disability_17_F="0";} else { disability_17_F=request.getParameter("disability_17_F");}
  if(request.getParameter("positive_17_F").trim().equals("")){positive_17_F="0";} else { positive_17_F=request.getParameter("positive_17_F");}
  if(request.getParameter("perpetrators_17_F").trim().equals("")){perpetrators_17_F="0";} else { perpetrators_17_F=request.getParameter("perpetrators_17_F");}
  if(request.getParameter("rapesurvivor_49_M").trim().equals("")){rapesurvivor_49_M="0";} else { rapesurvivor_49_M=request.getParameter("rapesurvivor_49_M");}
  if(request.getParameter("presenting_72hr_49_M").trim().equals("")){presenting_72hr_49_M="0";} else { presenting_72hr_49_M=request.getParameter("presenting_72hr_49_M");}
  if(request.getParameter("initiatedpep_49_M").trim().equals("")){initiatedpep_49_M="0";} else { initiatedpep_49_M=request.getParameter("initiatedpep_49_M");}
  if(request.getParameter("sti_49_M").trim().equals("")){sti_49_M="0";} else { sti_49_M=request.getParameter("sti_49_M");}
  if(request.getParameter("tested_49_M").trim().equals("")){tested_49_M="0";} else { tested_49_M=request.getParameter("tested_49_M");}
  if(request.getParameter("disability_49_M").trim().equals("")){disability_49_M="0";} else { disability_49_M=request.getParameter("disability_49_M");}
  if(request.getParameter("positive_49_M").trim().equals("")){positive_49_M="0";} else { positive_49_M=request.getParameter("positive_49_M");}
  if(request.getParameter("perpetrators_49_M").trim().equals("")){perpetrators_49_M="0";} else { perpetrators_49_M=request.getParameter("perpetrators_49_M");}
  if(request.getParameter("rapesurvivor_49_F").trim().equals("")){rapesurvivor_49_F="0";} else { rapesurvivor_49_F=request.getParameter("rapesurvivor_49_F");}
  if(request.getParameter("presenting_72hr_49_F").trim().equals("")){presenting_72hr_49_F="0";} else { presenting_72hr_49_F=request.getParameter("presenting_72hr_49_F");}
  if(request.getParameter("initiatedpep_49_F").trim().equals("")){initiatedpep_49_F="0";} else { initiatedpep_49_F=request.getParameter("initiatedpep_49_F");}
  if(request.getParameter("sti_49_F").trim().equals("")){sti_49_F="0";} else { sti_49_F=request.getParameter("sti_49_F");}
  if(request.getParameter("ecp_49").trim().equals("")){ecp_49="0";} else { ecp_49=request.getParameter("ecp_49");}
  if(request.getParameter("pill_49").trim().equals("")){pill_49="0";} else { pill_49=request.getParameter("pill_49");}
  if(request.getParameter("tested_49_F").trim().equals("")){tested_49_F="0";} else { tested_49_F=request.getParameter("tested_49_F");}
  if(request.getParameter("disability_49_F").trim().equals("")){disability_49_F="0";} else { disability_49_F=request.getParameter("disability_49_F");}
  if(request.getParameter("positive_49_F").trim().equals("")){positive_49_F="0";} else { positive_49_F=request.getParameter("positive_49_F");}
  if(request.getParameter("perpetrators_49_F").trim().equals("")){perpetrators_49_F="0";} else { perpetrators_49_F=request.getParameter("perpetrators_49_F");}
  if(request.getParameter("rapesurvivor_50_M").trim().equals("")){rapesurvivor_50_M="0";} else { rapesurvivor_50_M=request.getParameter("rapesurvivor_50_M");}
  if(request.getParameter("presenting_72hr_50_M").trim().equals("")){presenting_72hr_50_M="0";} else { presenting_72hr_50_M=request.getParameter("presenting_72hr_50_M");}
  if(request.getParameter("initiatedpep_50_M").trim().equals("")){initiatedpep_50_M="0";} else { initiatedpep_50_M=request.getParameter("initiatedpep_50_M");}
  if(request.getParameter("sti_50_M").trim().equals("")){sti_50_M="0";} else { sti_50_M=request.getParameter("sti_50_M");}
  if(request.getParameter("tested_50_M").trim().equals("")){tested_50_M="0";} else { tested_50_M=request.getParameter("tested_50_M");}
  if(request.getParameter("disability_50_M").trim().equals("")){disability_50_M="0";} else { disability_50_M=request.getParameter("disability_50_M");}
  if(request.getParameter("positive_50_M").trim().equals("")){positive_50_M="0";} else { positive_50_M=request.getParameter("positive_50_M");}
  if(request.getParameter("perpetrators_50_M").trim().equals("")){perpetrators_50_M="0";} else { perpetrators_50_M=request.getParameter("perpetrators_50_M");}
  if(request.getParameter("rapesurvivor_50_F").trim().equals("")){rapesurvivor_50_F="0";} else { rapesurvivor_50_F=request.getParameter("rapesurvivor_50_F");}
  if(request.getParameter("presenting_72hr_50_F").trim().equals("")){presenting_72hr_50_F="0";} else { presenting_72hr_50_F=request.getParameter("presenting_72hr_50_F");}
  if(request.getParameter("initiatedpep_50_F").trim().equals("")){initiatedpep_50_F="0";} else { initiatedpep_50_F=request.getParameter("initiatedpep_50_F");}
  if(request.getParameter("sti_50_F").trim().equals("")){sti_50_F="0";} else { sti_50_F=request.getParameter("sti_50_F");}
  if(request.getParameter("ecp_50").trim().equals("")){ecp_50="0";} else { ecp_50=request.getParameter("ecp_50");}
  if(request.getParameter("pill_50").trim().equals("")){pill_50="0";} else { pill_50=request.getParameter("pill_50");}
  if(request.getParameter("tested_50_F").trim().equals("")){tested_50_F="0";} else { tested_50_F=request.getParameter("tested_50_F");}
  if(request.getParameter("disability_50_F").trim().equals("")){disability_50_F="0";} else { disability_50_F=request.getParameter("disability_50_F");}
  if(request.getParameter("positive_50_F").trim().equals("")){positive_50_F="0";} else { positive_50_F=request.getParameter("positive_50_F");}
  if(request.getParameter("perpetrators_50_F").trim().equals("")){perpetrators_50_F="0";} else { perpetrators_50_F=request.getParameter("perpetrators_50_F");}
  if(request.getParameter("rapesurvivor_M").trim().equals("")){rapesurvivor_M="0";} else { rapesurvivor_M=request.getParameter("rapesurvivor_M");}
  if(request.getParameter("presenting_72hr_M").trim().equals("")){presenting_72hr_M="0";} else { presenting_72hr_M=request.getParameter("presenting_72hr_M");}
  if(request.getParameter("initiatedpep_M").trim().equals("")){initiatedpep_M="0";} else { initiatedpep_M=request.getParameter("initiatedpep_M");}
  if(request.getParameter("sti_M").trim().equals("")){sti_M="0";} else { sti_M=request.getParameter("sti_M");}
  if(request.getParameter("tested_M").trim().equals("")){tested_M="0";} else { tested_M=request.getParameter("tested_M");}
  if(request.getParameter("disability_M").trim().equals("")){disability_M="0";} else { disability_M=request.getParameter("disability_M");}
  if(request.getParameter("positive_M").trim().equals("")){positive_M="0";} else { positive_M=request.getParameter("positive_M");}
  if(request.getParameter("perpetrators_M").trim().equals("")){perpetrators_M="0";} else { perpetrators_M=request.getParameter("perpetrators_M");}
  if(request.getParameter("rapesurvivor_F").trim().equals("")){rapesurvivor_F="0";} else { rapesurvivor_F=request.getParameter("rapesurvivor_F");}
  if(request.getParameter("presenting_72hr_F").trim().equals("")){presenting_72hr_F="0";} else { presenting_72hr_F=request.getParameter("presenting_72hr_F");}
  if(request.getParameter("initiatedpep_F").trim().equals("")){initiatedpep_F="0";} else { initiatedpep_F=request.getParameter("initiatedpep_F");}
  if(request.getParameter("sti_F").trim().equals("")){sti_F="0";} else { sti_F=request.getParameter("sti_F");}
  if(request.getParameter("ecp").trim().equals("")){ecp="0";} else { ecp=request.getParameter("ecp");}
  if(request.getParameter("pill").trim().equals("")){pill="0";} else { pill=request.getParameter("pill");}
  if(request.getParameter("tested_F").trim().equals("")){tested_F="0";} else { tested_F=request.getParameter("tested_F");}
  if(request.getParameter("disability_F").trim().equals("")){disability_F="0";} else { disability_F=request.getParameter("disability_F");}
  if(request.getParameter("positive_F").trim().equals("")){positive_F="0";} else { positive_F=request.getParameter("positive_F");}
  if(request.getParameter("perpetrators_F").trim().equals("")){perpetrators_F="0";} else { perpetrators_F=request.getParameter("perpetrators_F");}
  if(request.getParameter("rapesurvivor_T").trim().equals("")){rapesurvivor_T="0";} else { rapesurvivor_T=request.getParameter("rapesurvivor_T");}
  if(request.getParameter("presenting_72hr_T").trim().equals("")){presenting_72hr_T="0";} else { presenting_72hr_T=request.getParameter("presenting_72hr_T");}
  if(request.getParameter("initiatedpep_T").trim().equals("")){initiatedpep_T="0";} else { initiatedpep_T=request.getParameter("initiatedpep_T");}
  if(request.getParameter("sti_T").trim().equals("")){sti_T="0";} else { sti_T=request.getParameter("sti_T");}
  if(request.getParameter("ecp_T").trim().equals("")){ecp_T="0";} else { ecp_T=request.getParameter("ecp_T");}
  if(request.getParameter("pill_T").trim().equals("")){pill_T="0";} else { pill_T=request.getParameter("pill_T");}
  if(request.getParameter("tested_T").trim().equals("")){tested_T="0";} else { tested_T=request.getParameter("tested_T");}
  if(request.getParameter("disability_T").trim().equals("")){disability_T="0";} else { disability_T=request.getParameter("disability_T");}
  if(request.getParameter("positive_T").trim().equals("")){positive_T="0";} else { positive_T=request.getParameter("positive_T");}
  if(request.getParameter("perpetrators_T").trim().equals("")){perpetrators_T="0";} else { perpetrators_T=request.getParameter("perpetrators_T");}
 
  
  
  String runvalidate="update sgbv set "
          + "visit1_11_M='"+visit1_11_M+"',"
          + "visit2_11_M='"+visit2_11_M+"',"
          + "visit3_11_M='"+visit3_11_M+"',"
          + "visit4_11_M='"+visit4_11_M+"',"
          + "visit5_11_M='"+visit5_11_M+"',"
          + "completedpep_11_M='"+completedpep_11_M+"',"
          + "seroconverted_11_M='"+seroconverted_11_M+"',"
          + "counselling_11_M='"+counselling_11_M+"',"
          + "visit1_11_F='"+visit1_11_F+"',"
          + "visit2_11_F='"+visit2_11_F+"',"
          + "visit3_11_F='"+visit3_11_F+"',"
          + "visit4_11_F='"+visit4_11_F+"',"
          + "visit5_11_F='"+visit5_11_F+"',"
          + "completedpep_11_F='"+completedpep_11_F+"',"
          + "seroconverted_11_F='"+seroconverted_11_F+"',"
          + "Pregnant_11='"+Pregnant_11+"',"
          + "counselling_11_F='"+counselling_11_F+"',"
          + "visit1_17_M='"+visit1_17_M+"',"
          + "visit2_17_M='"+visit2_17_M+"',"
          + "visit3_17_M='"+visit3_17_M+"',"
          + "visit4_17_M='"+visit4_17_M+"',"
          + "visit5_17_M='"+visit5_17_M+"',"
          + "completedpep_17_M='"+completedpep_17_M+"',"
          + "seroconverted_17_M='"+seroconverted_17_M+"',"
          + "counselling_17_M='"+counselling_17_M+"',"
          + "visit1_17_F='"+visit1_17_F+"',"
          + "visit2_17_F='"+visit2_17_F+"',"
          + "visit3_17_F='"+visit3_17_F+"',"
          + "visit4_17_F='"+visit4_17_F+"',"
          + "visit5_17_F='"+visit5_17_F+"',"
          + "completedpep_17_F='"+completedpep_17_F+"',"
          + "seroconverted_17_F='"+seroconverted_17_F+"',"
          + "Pregnant_17='"+Pregnant_17+"',"
          + "counselling_17_F='"+counselling_17_F+"',"
          + "visit1_49_M='"+visit1_49_M+"',"
          + "visit2_49_M='"+visit2_49_M+"',"
          + "visit3_49_M='"+visit3_49_M+"',"
          + "visit4_49_M='"+visit4_49_M+"',"
          + "visit5_49_M='"+visit5_49_M+"',"
          + "completedpep_49_M='"+completedpep_49_M+"',"
          + "seroconverted_49_M='"+seroconverted_49_M+"',"
          + "counselling_49_M='"+counselling_49_M+"',"
          + "visit1_49_F='"+visit1_49_F+"',"
          + "visit2_49_F='"+visit2_49_F+"',"
          + "visit3_49_F='"+visit3_49_F+"',"
          + "visit4_49_F='"+visit4_49_F+"',"
          + "visit5_49_F='"+visit5_49_F+"',"
          + "completedpep_49_F='"+completedpep_49_F+"',"
          + "seroconverted_49_F='"+seroconverted_49_F+"',"
          + "Pregnant_49='"+Pregnant_49+"',"
          + "counselling_49_F='"+counselling_49_F+"',"
          + "visit1_50_M='"+visit1_50_M+"',"
          + "visit2_50_M='"+visit2_50_M+"',"
          + "visit3_50_M='"+visit3_50_M+"',"
          + "visit4_50_M='"+visit4_50_M+"',"
          + "visit5_50_M='"+visit5_50_M+"',"
          + "completedpep_50_M='"+completedpep_50_M+"',"
          + "seroconverted_50_M='"+seroconverted_50_M+"',"
          + "counselling_50_M='"+counselling_50_M+"',"
          + "visit1_50_F='"+visit1_50_F+"',"
          + "visit2_50_F='"+visit2_50_F+"',"
          + "visit3_50_F='"+visit3_50_F+"',"
          + "visit4_50_F='"+visit4_50_F+"',"
          + "visit5_50_F='"+visit5_50_F+"',"
          + "completedpep_50_F='"+completedpep_50_F+"',"
          + "seroconverted_50_F='"+seroconverted_50_F+"',"
          + "Pregnant_50='"+Pregnant_50+"',"
          + "counselling_50_F='"+counselling_50_F+"',"
          + "visit1_M='"+visit1_M+"',"
          + "visit2_M='"+visit2_M+"',"
          + "visit3_M='"+visit3_M+"',"
          + "visit4_M='"+visit4_M+"',"
          + "visit5_M='"+visit5_M+"',"
          + "completedpep_M='"+completedpep_M+"',"
          + "seroconverted_M='"+seroconverted_M+"',"
          + "counselling_M='"+counselling_M+"',"
          + "visit1_F='"+visit1_F+"',"
          + "visit2_F='"+visit2_F+"',"
          + "visit3_F='"+visit3_F+"',"
          + "visit4_F='"+visit4_F+"',"
          + "visit5_F='"+visit5_F+"',"
          + "completedpep_F='"+completedpep_F+"',"
          + "seroconverted_F='"+seroconverted_F+"',"
          + "Pregnant='"+Pregnant+"',"
          + "counselling_F='"+counselling_F+"',"
          + "visit1_T='"+visit1_T+"',"
          + "visit2_T='"+visit2_T+"',"
          + "visit3_T='"+visit3_T+"',"
          + "visit4_T='"+visit4_T+"',"
          + "visit5_T='"+visit5_T+"',"
          + "completedpep_T='"+completedpep_T+"',"
          + "seroconverted_T='"+seroconverted_T+"',"
          + "Pregnant_T='"+Pregnant_T+"',"
          + "counselling_T='"+counselling_T+"',"
          + "rapesurvivor_11_M='"+rapesurvivor_11_M+"',"
          + "presenting_72hr_11_M='"+presenting_72hr_11_M+"',"
          + "initiatedpep_11_M='"+initiatedpep_11_M+"',"
          + "sti_11_M='"+sti_11_M+"',"
          + "tested_11_M='"+tested_11_M+"',"
          + "disability_11_M='"+disability_11_M+"',"
          + "positive_11_M='"+positive_11_M+"',"
          + "perpetrators_11_M='"+perpetrators_11_M+"',"
          + "rapesurvivor_11_F='"+rapesurvivor_11_F+"',"
          + "presenting_72hr_11_F='"+presenting_72hr_11_F+"',"
          + "initiatedpep_11_F='"+initiatedpep_11_F+"',"
          + "sti_11_F='"+sti_11_F+"',"
          + "ecp_11='"+ecp_11+"',"
          + "pill_11='"+pill_11+"',"
          + "tested_11_F='"+tested_11_F+"',"
          + "disability_11_F='"+disability_11_F+"',"
          + "positive_11_F='"+positive_11_F+"',"
          + "perpetrators_11_F='"+perpetrators_11_F+"',"
          + "rapesurvivor_17_M='"+rapesurvivor_17_M+"',"
          + "presenting_72hr_17_M='"+presenting_72hr_17_M+"',"
          + "initiatedpep_17_M='"+initiatedpep_17_M+"',"
          + "sti_17_M='"+sti_17_M+"',"
          + "tested_17_M='"+tested_17_M+"',"
          + "disability_17_M='"+disability_17_M+"',"
          + "positive_17_M='"+positive_17_M+"',"
          + "perpetrators_17_M='"+perpetrators_17_M+"',"
          + "rapesurvivor_17_F='"+rapesurvivor_17_F+"',"
          + "presenting_72hr_17_F='"+presenting_72hr_17_F+"',"
          + "initiatedpep_17_F='"+initiatedpep_17_F+"',"
          + "sti_17_F='"+sti_17_F+"',"
          + "ecp_17='"+ecp_17+"',"
          + "pill_17='"+pill_17+"',"
          + "tested_17_F='"+tested_17_F+"',"
          + "disability_17_F='"+disability_17_F+"',"
          + "positive_17_F='"+positive_17_F+"',"
          + "perpetrators_17_F='"+perpetrators_17_F+"',"
          + "rapesurvivor_49_M='"+rapesurvivor_49_M+"',"
          + "presenting_72hr_49_M='"+presenting_72hr_49_M+"',"
          + "initiatedpep_49_M='"+initiatedpep_49_M+"',"
          + "sti_49_M='"+sti_49_M+"',"
          + "tested_49_M='"+tested_49_M+"',"
          + "disability_49_M='"+disability_49_M+"',"
          + "positive_49_M='"+positive_49_M+"',"
          + "perpetrators_49_M='"+perpetrators_49_M+"',"
          + "rapesurvivor_49_F='"+rapesurvivor_49_F+"',"
          + "presenting_72hr_49_F='"+presenting_72hr_49_F+"',"
          + "initiatedpep_49_F='"+initiatedpep_49_F+"',"
          + "sti_49_F='"+sti_49_F+"',"
          + "ecp_49='"+ecp_49+"',"
          + "pill_49='"+pill_49+"',"
          + "tested_49_F='"+tested_49_F+"',"
          + "disability_49_F='"+disability_49_F+"',"
          + "positive_49_F='"+positive_49_F+"',"
          + "perpetrators_49_F='"+perpetrators_49_F+"',"
          + "rapesurvivor_50_M='"+rapesurvivor_50_M+"',"
          + "presenting_72hr_50_M='"+presenting_72hr_50_M+"',"
          + "initiatedpep_50_M='"+initiatedpep_50_M+"',"
          + "sti_50_M='"+sti_50_M+"',"
          + "tested_50_M='"+tested_50_M+"',"
          + "disability_50_M='"+disability_50_M+"',"
          + "positive_50_M='"+positive_50_M+"',"
          + "perpetrators_50_M='"+perpetrators_50_M+"',"
          + "rapesurvivor_50_F='"+rapesurvivor_50_F+"',"
          + "presenting_72hr_50_F='"+presenting_72hr_50_F+"',"
          + "initiatedpep_50_F='"+initiatedpep_50_F+"',"
          + "sti_50_F='"+sti_50_F+"',"
          + "ecp_50='"+ecp_50+"',"
          + "pill_50='"+pill_50+"',"
          + "tested_50_F='"+tested_50_F+"',"
          + "disability_50_F='"+disability_50_F+"',"
          + "positive_50_F='"+positive_50_F+"',"
          + "perpetrators_50_F='"+perpetrators_50_F+"',"
          + "rapesurvivor_M='"+rapesurvivor_M+"',"
          + "presenting_72hr_M='"+presenting_72hr_M+"',"
          + "initiatedpep_M='"+initiatedpep_M+"',"
          + "sti_M='"+sti_M+"',"
          + "tested_M='"+tested_M+"',"
          + "disability_M='"+disability_M+"',"
          + "positive_M='"+positive_M+"',"
          + "perpetrators_M='"+perpetrators_M+"',"
          + "rapesurvivor_F='"+rapesurvivor_F+"',"
          + "presenting_72hr_F='"+presenting_72hr_F+"',"
          + "initiatedpep_F='"+initiatedpep_F+"',"
          + "sti_F='"+sti_F+"',"
          + "ecp='"+ecp+"',"
          + "pill='"+pill+"',"
          + "tested_F='"+tested_F+"',"
          + "disability_F='"+disability_F+"',"
          + "positive_F='"+positive_F+"',"
          + "perpetrators_F='"+perpetrators_F+"',"
          + "rapesurvivor_T='"+rapesurvivor_T+"',"
          + "presenting_72hr_T='"+presenting_72hr_T+"',"
          + "initiatedpep_T='"+initiatedpep_T+"',"
          + "sti_T='"+sti_T+"',"
          + "ecp_T='"+ecp_T+"',"
          + "pill_T='"+pill_T+"',"
          + "tested_T='"+tested_T+"',"
          + "disability_T='"+disability_T+"',"
          + "positive_T='"+positive_T+"',"
          + "perpetrators_T='"+perpetrators_T+"'"
          
+ ",isValidated='1' where id='"+tableid+"'";        
System.out.println(runvalidate);  
   conn.st.executeUpdate(runvalidate);
    session.setAttribute("validatesgbv", "<font color=\"green\"><b>Form SGBV Validated Successfully.</b></font>");
  
    
     int monthDiff=0;
     String getMonths="SELECT TIMESTAMPDIFF(MONTH, timestamp, now()) FROM sgbv WHERE id='"+tableid+"'";
     conn.rs1=conn.st1.executeQuery(getMonths);
     if(conn.rs1.next()==true){
         System.out.println("months are : "+conn.rs1.getString(1));
    monthDiff=conn.rs1.getInt(1);
     }
     if(monthDiff>0){
//      UPDATE THE COLUMN   
       java.util.Date date= new java.util.Date();
Timestamp lastUpdatedOn =new Timestamp(date.getTime()); 

     String updateLast="UPDATE sgbv SET updatedBy='"+userid+"', updatedOn='"+lastUpdatedOn+"' WHERE id='"+tableid+"'" ;   
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
        
   
        response.sendRedirect("loadsgbv.jsp");
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
