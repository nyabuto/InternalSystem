/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InternalSystem;

import dashboards.pullHts;
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
public class validatehts extends HttpServlet {
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
 String sdp="";
 String mwezi="";
 String mwakamwezi="";

//        if(achieved.equals("")){achieved="0";}
    if(session.getAttribute("userid")!=null){        
userid=session.getAttribute("userid").toString();
}

if(session.getAttribute("year")!=null){        
year=session.getAttribute("year").toString();
}
  if(session.getAttribute("monthid")!=null){        
month=session.getAttribute("monthid").toString();
if(month.length()==1){mwezi="0"+month;}
}

    if(session.getAttribute("facilityid")!=null){        
facil=session.getAttribute("facilityid").toString();
}
    
    if(session.getAttribute("activesdp")!=null)
       {        
sdp=session.getAttribute("activesdp").toString();
       }
    
    
    mwakamwezi=year+""+mwezi;
String couns_1_M="0";
String couns_1_F="0";
String couns_4_M="0";
String couns_4_F="0";
String couns_9_M="0";
String couns_9_F="0";
String couns_14_M="0";
String couns_14_F="0";
String couns_19_M="0";
String couns_19_F="0";
String couns_24_M="0";
String couns_24_F="0";
String couns_29_M="0";
String couns_29_F="0";
String couns_34_M="0";
String couns_34_F="0";
String couns_39_M="0";
String couns_39_F="0";
String couns_49_M="0";
String couns_49_F="0";
String couns_50_M="0";
String couns_50_F="0";
String couns_T_M="0";
String couns_T_F="0";
String couns_GT="0";
String tes_1_M="0";
String tes_1_F="0";
String tes_4_M="0";
String tes_4_F="0";
String tes_9_M="0";
String tes_9_F="0";
String tes_14_M="0";
String tes_14_F="0";
String tes_19_M="0";
String tes_19_F="0";
String tes_24_M="0";
String tes_24_F="0";
String tes_29_M="0";
String tes_29_F="0";
String tes_34_M="0";
String tes_34_F="0";
String tes_39_M="0";
String tes_39_F="0";
String tes_49_M="0";
String tes_49_F="0";
String tes_50_M="0";
String tes_50_F="0";
String tes_T_M="0";
String tes_T_F="0";
String tes_GT="0";
String tes_new_1_M="0";
String tes_new_1_F="0";
String tes_new_4_M="0";
String tes_new_4_F="0";
String tes_new_9_M="0";
String tes_new_9_F="0";
String tes_new_14_M="0";
String tes_new_14_F="0";
String tes_new_19_M="0";
String tes_new_19_F="0";
String tes_new_24_M="0";
String tes_new_24_F="0";
String tes_new_29_M="0";
String tes_new_29_F="0";
String tes_new_34_M="0";
String tes_new_34_F="0";
String tes_new_39_M="0";
String tes_new_39_F="0";
String tes_new_49_M="0";
String tes_new_49_F="0";
String tes_new_50_M="0";
String tes_new_50_F="0";
String tes_new_T_M="0";
String tes_new_T_F="0";
String tes_new_GT="0";
String res_1_M="0";
String res_1_F="0";
String res_4_M="0";
String res_4_F="0";
String res_9_M="0";
String res_9_F="0";
String res_14_M="0";
String res_14_F="0";
String res_19_M="0";
String res_19_F="0";
String res_24_M="0";
String res_24_F="0";
String res_29_M="0";
String res_29_F="0";
String res_34_M="0";
String res_34_F="0";
String res_39_M="0";
String res_39_F="0";
String res_49_M="0";
String res_49_F="0";
String res_50_M="0";
String res_50_F="0";
String res_T_M="0";
String res_T_F="0";
String res_GT="0";
String tes_new_res_1_M="0";
String tes_new_res_1_F="0";
String tes_new_res_4_M="0";
String tes_new_res_4_F="0";
String tes_new_res_9_M="0";
String tes_new_res_9_F="0";
String tes_new_res_14_M="0";
String tes_new_res_14_F="0";
String tes_new_res_19_M="0";
String tes_new_res_19_F="0";
String tes_new_res_24_M="0";
String tes_new_res_24_F="0";
String tes_new_res_29_M="0";
String tes_new_res_29_F="0";
String tes_new_res_34_M="0";
String tes_new_res_34_F="0";
String tes_new_res_39_M="0";
String tes_new_res_39_F="0";
String tes_new_res_49_M="0";
String tes_new_res_49_F="0";
String tes_new_res_50_M="0";
String tes_new_res_50_F="0";
String tes_new_res_T_M="0";
String tes_new_res_T_F="0";
String tes_new_res_GT="0";
String pos_1_M="0";
String pos_1_F="0";
String pos_4_M="0";
String pos_4_F="0";
String pos_9_M="0";
String pos_9_F="0";
String pos_14_M="0";
String pos_14_F="0";
String pos_19_M="0";
String pos_19_F="0";
String pos_24_M="0";
String pos_24_F="0";
String pos_29_M="0";
String pos_29_F="0";
String pos_34_M="0";
String pos_34_F="0";
String pos_39_M="0";
String pos_39_F="0";
String pos_49_M="0";
String pos_49_F="0";
String pos_50_M="0";
String pos_50_F="0";
String pos_T_M="0";
String pos_T_F="0";
String pos_GT="0";
String pos_tes_1_M="0";
String pos_tes_1_F="0";
String pos_tes_4_M="0";
String pos_tes_4_F="0";
String pos_tes_9_M="0";
String pos_tes_9_F="0";
String pos_tes_14_M="0";
String pos_tes_14_F="0";
String pos_tes_19_M="0";
String pos_tes_19_F="0";
String pos_tes_24_M="0";
String pos_tes_24_F="0";
String pos_tes_29_M="0";
String pos_tes_29_F="0";
String pos_tes_34_M="0";
String pos_tes_34_F="0";
String pos_tes_39_M="0";
String pos_tes_39_F="0";
String pos_tes_49_M="0";
String pos_tes_49_F="0";
String pos_tes_50_M="0";
String pos_tes_50_F="0";
String pos_tes_T_M="0";
String pos_tes_T_F="0";
String pos_tes_GT="0";
String ref_1_M="0";
String ref_1_F="0";
String ref_4_M="0";
String ref_4_F="0";
String ref_9_M="0";
String ref_9_F="0";
String ref_14_M="0";
String ref_14_F="0";
String ref_19_M="0";
String ref_19_F="0";
String ref_24_M="0";
String ref_24_F="0";
String ref_29_M="0";
String ref_29_F="0";
String ref_34_M="0";
String ref_34_F="0";
String ref_39_M="0";
String ref_39_F="0";
String ref_49_M="0";
String ref_49_F="0";
String ref_50_M="0";
String ref_50_F="0";
String ref_T_M="0";
String ref_T_F="0";
String ref_GT="0";
String tes_marps_1_M="0";
String tes_marps_1_F="0";
String tes_marps_4_M="0";
String tes_marps_4_F="0";
String tes_marps_9_M="0";
String tes_marps_9_F="0";
String tes_marps_14_M="0";
String tes_marps_14_F="0";
String tes_marps_19_M="0";
String tes_marps_19_F="0";
String tes_marps_24_M="0";
String tes_marps_24_F="0";
String tes_marps_29_M="0";
String tes_marps_29_F="0";
String tes_marps_34_M="0";
String tes_marps_34_F="0";
String tes_marps_39_M="0";
String tes_marps_39_F="0";
String tes_marps_49_M="0";
String tes_marps_49_F="0";
String tes_marps_50_M="0";
String tes_marps_50_F="0";
String tes_marps_T_M="0";
String tes_marps_T_F="0";
String tes_marps_GT="0";
String pos_marps_1_M="0";
String pos_marps_1_F="0";
String pos_marps_4_M="0";
String pos_marps_4_F="0";
String pos_marps_9_M="0";
String pos_marps_9_F="0";
String pos_marps_14_M="0";
String pos_marps_14_F="0";
String pos_marps_19_M="0";
String pos_marps_19_F="0";
String pos_marps_24_M="0";
String pos_marps_24_F="0";
String pos_marps_29_M="0";
String pos_marps_29_F="0";
String pos_marps_34_M="0";
String pos_marps_34_F="0";
String pos_marps_39_M="0";
String pos_marps_39_F="0";
String pos_marps_49_M="0";
String pos_marps_49_F="0";
String pos_marps_50_M="0";
String pos_marps_50_F="0";
String pos_marps_T_M="0";
String pos_marps_T_F="0";
String pos_marps_GT="0";
String tes_coup_1_M="0";
String tes_coup_1_F="0";
String tes_coup_4_M="0";
String tes_coup_4_F="0";
String tes_coup_9_M="0";
String tes_coup_9_F="0";
String tes_coup_14_M="0";
String tes_coup_14_F="0";
String tes_coup_19_M="0";
String tes_coup_19_F="0";
String tes_coup_24_M="0";
String tes_coup_24_F="0";
String tes_coup_29_M="0";
String tes_coup_29_F="0";
String tes_coup_34_M="0";
String tes_coup_34_F="0";
String tes_coup_39_M="0";
String tes_coup_39_F="0";
String tes_coup_49_M="0";
String tes_coup_49_F="0";
String tes_coup_50_M="0";
String tes_coup_50_F="0";
String tes_coup_T_M="0";
String tes_coup_T_F="0";
String tes_coup_GT="0";
String tes_coup_dis_1_M="0";
String tes_coup_dis_1_F="0";
String tes_coup_dis_4_M="0";
String tes_coup_dis_4_F="0";
String tes_coup_dis_9_M="0";
String tes_coup_dis_9_F="0";
String tes_coup_dis_14_M="0";
String tes_coup_dis_14_F="0";
String tes_coup_dis_19_M="0";
String tes_coup_dis_19_F="0";
String tes_coup_dis_24_M="0";
String tes_coup_dis_24_F="0";
String tes_coup_dis_29_M="0";
String tes_coup_dis_29_F="0";
String tes_coup_dis_34_M="0";
String tes_coup_dis_34_F="0";
String tes_coup_dis_39_M="0";
String tes_coup_dis_39_F="0";
String tes_coup_dis_49_M="0";
String tes_coup_dis_49_F="0";
String tes_coup_dis_50_M="0";
String tes_coup_dis_50_F="0";
String tes_coup_dis_T_M="0";
String tes_coup_dis_T_F="0";
String tes_coup_dis_GT="0";

String tk_negative1="0";
String tk_negative2="0";
String tk_positive1="0";
String tk_positive2="0";
String tk_invalid1="0";
String tk_invalid2="0";
String tk_wastage1="0";
String tk_wastage2="0";
String tk_total1="0";
String tk_total2="0";



 
 
           
String tableid=year+"_"+month+"_"+facil+"_"+sdp;

 
  if(request.getParameter("couns_1_M").trim().equals("")){couns_1_M="0";} else {couns_1_M=request.getParameter("couns_1_M");}
 if(request.getParameter("couns_1_F").trim().equals("")){couns_1_F="0";} else {couns_1_F=request.getParameter("couns_1_F");}
 if(request.getParameter("couns_4_M").trim().equals("")){couns_4_M="0";} else {couns_4_M=request.getParameter("couns_4_M");}
 if(request.getParameter("couns_4_F").trim().equals("")){couns_4_F="0";} else {couns_4_F=request.getParameter("couns_4_F");}
 if(request.getParameter("couns_9_M").trim().equals("")){couns_9_M="0";} else {couns_9_M=request.getParameter("couns_9_M");}
 if(request.getParameter("couns_9_F").trim().equals("")){couns_9_F="0";} else {couns_9_F=request.getParameter("couns_9_F");}
 if(request.getParameter("couns_14_M").trim().equals("")){couns_14_M="0";} else {couns_14_M=request.getParameter("couns_14_M");}
 if(request.getParameter("couns_14_F").trim().equals("")){couns_14_F="0";} else {couns_14_F=request.getParameter("couns_14_F");}
 if(request.getParameter("couns_19_M").trim().equals("")){couns_19_M="0";} else {couns_19_M=request.getParameter("couns_19_M");}
 if(request.getParameter("couns_19_F").trim().equals("")){couns_19_F="0";} else {couns_19_F=request.getParameter("couns_19_F");}
 if(request.getParameter("couns_24_M").trim().equals("")){couns_24_M="0";} else {couns_24_M=request.getParameter("couns_24_M");}
 if(request.getParameter("couns_24_F").trim().equals("")){couns_24_F="0";} else {couns_24_F=request.getParameter("couns_24_F");}
 if(request.getParameter("couns_29_M").trim().equals("")){couns_29_M="0";} else {couns_29_M=request.getParameter("couns_29_M");}
 if(request.getParameter("couns_29_F").trim().equals("")){couns_29_F="0";} else {couns_29_F=request.getParameter("couns_29_F");}
 if(request.getParameter("couns_34_M").trim().equals("")){couns_34_M="0";} else {couns_34_M=request.getParameter("couns_34_M");}
 if(request.getParameter("couns_34_F").trim().equals("")){couns_34_F="0";} else {couns_34_F=request.getParameter("couns_34_F");}
 if(request.getParameter("couns_39_M").trim().equals("")){couns_39_M="0";} else {couns_39_M=request.getParameter("couns_39_M");}
 if(request.getParameter("couns_39_F").trim().equals("")){couns_39_F="0";} else {couns_39_F=request.getParameter("couns_39_F");}
 if(request.getParameter("couns_49_M").trim().equals("")){couns_49_M="0";} else {couns_49_M=request.getParameter("couns_49_M");}
 if(request.getParameter("couns_49_F").trim().equals("")){couns_49_F="0";} else {couns_49_F=request.getParameter("couns_49_F");}
 if(request.getParameter("couns_50_M").trim().equals("")){couns_50_M="0";} else {couns_50_M=request.getParameter("couns_50_M");}
 if(request.getParameter("couns_50_F").trim().equals("")){couns_50_F="0";} else {couns_50_F=request.getParameter("couns_50_F");}
 if(request.getParameter("couns_T_M").trim().equals("")){couns_T_M="0";} else {couns_T_M=request.getParameter("couns_T_M");}
 if(request.getParameter("couns_T_F").trim().equals("")){couns_T_F="0";} else {couns_T_F=request.getParameter("couns_T_F");}
 if(request.getParameter("couns_GT").trim().equals("")){couns_GT="0";} else {couns_GT=request.getParameter("couns_GT");}
 if(request.getParameter("tes_1_M").trim().equals("")){tes_1_M="0";} else {tes_1_M=request.getParameter("tes_1_M");}
 if(request.getParameter("tes_1_F").trim().equals("")){tes_1_F="0";} else {tes_1_F=request.getParameter("tes_1_F");}
 if(request.getParameter("tes_4_M").trim().equals("")){tes_4_M="0";} else {tes_4_M=request.getParameter("tes_4_M");}
 if(request.getParameter("tes_4_F").trim().equals("")){tes_4_F="0";} else {tes_4_F=request.getParameter("tes_4_F");}
 if(request.getParameter("tes_9_M").trim().equals("")){tes_9_M="0";} else {tes_9_M=request.getParameter("tes_9_M");}
 if(request.getParameter("tes_9_F").trim().equals("")){tes_9_F="0";} else {tes_9_F=request.getParameter("tes_9_F");}
 if(request.getParameter("tes_14_M").trim().equals("")){tes_14_M="0";} else {tes_14_M=request.getParameter("tes_14_M");}
 if(request.getParameter("tes_14_F").trim().equals("")){tes_14_F="0";} else {tes_14_F=request.getParameter("tes_14_F");}
 if(request.getParameter("tes_19_M").trim().equals("")){tes_19_M="0";} else {tes_19_M=request.getParameter("tes_19_M");}
 if(request.getParameter("tes_19_F").trim().equals("")){tes_19_F="0";} else {tes_19_F=request.getParameter("tes_19_F");}
 if(request.getParameter("tes_24_M").trim().equals("")){tes_24_M="0";} else {tes_24_M=request.getParameter("tes_24_M");}
 if(request.getParameter("tes_24_F").trim().equals("")){tes_24_F="0";} else {tes_24_F=request.getParameter("tes_24_F");}
 if(request.getParameter("tes_29_M").trim().equals("")){tes_29_M="0";} else {tes_29_M=request.getParameter("tes_29_M");}
 if(request.getParameter("tes_29_F").trim().equals("")){tes_29_F="0";} else {tes_29_F=request.getParameter("tes_29_F");}
 if(request.getParameter("tes_34_M").trim().equals("")){tes_34_M="0";} else {tes_34_M=request.getParameter("tes_34_M");}
 if(request.getParameter("tes_34_F").trim().equals("")){tes_34_F="0";} else {tes_34_F=request.getParameter("tes_34_F");}
 if(request.getParameter("tes_39_M").trim().equals("")){tes_39_M="0";} else {tes_39_M=request.getParameter("tes_39_M");}
 if(request.getParameter("tes_39_F").trim().equals("")){tes_39_F="0";} else {tes_39_F=request.getParameter("tes_39_F");}
 if(request.getParameter("tes_49_M").trim().equals("")){tes_49_M="0";} else {tes_49_M=request.getParameter("tes_49_M");}
 if(request.getParameter("tes_49_F").trim().equals("")){tes_49_F="0";} else {tes_49_F=request.getParameter("tes_49_F");}
 if(request.getParameter("tes_50_M").trim().equals("")){tes_50_M="0";} else {tes_50_M=request.getParameter("tes_50_M");}
 if(request.getParameter("tes_50_F").trim().equals("")){tes_50_F="0";} else {tes_50_F=request.getParameter("tes_50_F");}
 if(request.getParameter("tes_T_M").trim().equals("")){tes_T_M="0";} else {tes_T_M=request.getParameter("tes_T_M");}
 if(request.getParameter("tes_T_F").trim().equals("")){tes_T_F="0";} else {tes_T_F=request.getParameter("tes_T_F");}
 if(request.getParameter("tes_GT").trim().equals("")){tes_GT="0";} else {tes_GT=request.getParameter("tes_GT");}
 if(request.getParameter("tes_new_1_M").trim().equals("")){tes_new_1_M="0";} else {tes_new_1_M=request.getParameter("tes_new_1_M");}
 if(request.getParameter("tes_new_1_F").trim().equals("")){tes_new_1_F="0";} else {tes_new_1_F=request.getParameter("tes_new_1_F");}
 if(request.getParameter("tes_new_4_M").trim().equals("")){tes_new_4_M="0";} else {tes_new_4_M=request.getParameter("tes_new_4_M");}
 if(request.getParameter("tes_new_4_F").trim().equals("")){tes_new_4_F="0";} else {tes_new_4_F=request.getParameter("tes_new_4_F");}
 if(request.getParameter("tes_new_9_M").trim().equals("")){tes_new_9_M="0";} else {tes_new_9_M=request.getParameter("tes_new_9_M");}
 if(request.getParameter("tes_new_9_F").trim().equals("")){tes_new_9_F="0";} else {tes_new_9_F=request.getParameter("tes_new_9_F");}
 if(request.getParameter("tes_new_14_M").trim().equals("")){tes_new_14_M="0";} else {tes_new_14_M=request.getParameter("tes_new_14_M");}
 if(request.getParameter("tes_new_14_F").trim().equals("")){tes_new_14_F="0";} else {tes_new_14_F=request.getParameter("tes_new_14_F");}
 if(request.getParameter("tes_new_19_M").trim().equals("")){tes_new_19_M="0";} else {tes_new_19_M=request.getParameter("tes_new_19_M");}
 if(request.getParameter("tes_new_19_F").trim().equals("")){tes_new_19_F="0";} else {tes_new_19_F=request.getParameter("tes_new_19_F");}
 if(request.getParameter("tes_new_24_M").trim().equals("")){tes_new_24_M="0";} else {tes_new_24_M=request.getParameter("tes_new_24_M");}
 if(request.getParameter("tes_new_24_F").trim().equals("")){tes_new_24_F="0";} else {tes_new_24_F=request.getParameter("tes_new_24_F");}
 if(request.getParameter("tes_new_29_M").trim().equals("")){tes_new_29_M="0";} else {tes_new_29_M=request.getParameter("tes_new_29_M");}
 if(request.getParameter("tes_new_29_F").trim().equals("")){tes_new_29_F="0";} else {tes_new_29_F=request.getParameter("tes_new_29_F");}
 if(request.getParameter("tes_new_34_M").trim().equals("")){tes_new_34_M="0";} else {tes_new_34_M=request.getParameter("tes_new_34_M");}
 if(request.getParameter("tes_new_34_F").trim().equals("")){tes_new_34_F="0";} else {tes_new_34_F=request.getParameter("tes_new_34_F");}
 if(request.getParameter("tes_new_39_M").trim().equals("")){tes_new_39_M="0";} else {tes_new_39_M=request.getParameter("tes_new_39_M");}
 if(request.getParameter("tes_new_39_F").trim().equals("")){tes_new_39_F="0";} else {tes_new_39_F=request.getParameter("tes_new_39_F");}
 if(request.getParameter("tes_new_49_M").trim().equals("")){tes_new_49_M="0";} else {tes_new_49_M=request.getParameter("tes_new_49_M");}
 if(request.getParameter("tes_new_49_F").trim().equals("")){tes_new_49_F="0";} else {tes_new_49_F=request.getParameter("tes_new_49_F");}
 if(request.getParameter("tes_new_50_M").trim().equals("")){tes_new_50_M="0";} else {tes_new_50_M=request.getParameter("tes_new_50_M");}
 if(request.getParameter("tes_new_50_F").trim().equals("")){tes_new_50_F="0";} else {tes_new_50_F=request.getParameter("tes_new_50_F");}
 if(request.getParameter("tes_new_T_M").trim().equals("")){tes_new_T_M="0";} else {tes_new_T_M=request.getParameter("tes_new_T_M");}
 if(request.getParameter("tes_new_T_F").trim().equals("")){tes_new_T_F="0";} else {tes_new_T_F=request.getParameter("tes_new_T_F");}
 if(request.getParameter("tes_new_GT").trim().equals("")){tes_new_GT="0";} else {tes_new_GT=request.getParameter("tes_new_GT");}
 if(request.getParameter("res_1_M").trim().equals("")){res_1_M="0";} else {res_1_M=request.getParameter("res_1_M");}
 if(request.getParameter("res_1_F").trim().equals("")){res_1_F="0";} else {res_1_F=request.getParameter("res_1_F");}
 if(request.getParameter("res_4_M").trim().equals("")){res_4_M="0";} else {res_4_M=request.getParameter("res_4_M");}
 if(request.getParameter("res_4_F").trim().equals("")){res_4_F="0";} else {res_4_F=request.getParameter("res_4_F");}
 if(request.getParameter("res_9_M").trim().equals("")){res_9_M="0";} else {res_9_M=request.getParameter("res_9_M");}
 if(request.getParameter("res_9_F").trim().equals("")){res_9_F="0";} else {res_9_F=request.getParameter("res_9_F");}
 if(request.getParameter("res_14_M").trim().equals("")){res_14_M="0";} else {res_14_M=request.getParameter("res_14_M");}
 if(request.getParameter("res_14_F").trim().equals("")){res_14_F="0";} else {res_14_F=request.getParameter("res_14_F");}
 if(request.getParameter("res_19_M").trim().equals("")){res_19_M="0";} else {res_19_M=request.getParameter("res_19_M");}
 if(request.getParameter("res_19_F").trim().equals("")){res_19_F="0";} else {res_19_F=request.getParameter("res_19_F");}
 if(request.getParameter("res_24_M").trim().equals("")){res_24_M="0";} else {res_24_M=request.getParameter("res_24_M");}
 if(request.getParameter("res_24_F").trim().equals("")){res_24_F="0";} else {res_24_F=request.getParameter("res_24_F");}
 if(request.getParameter("res_29_M").trim().equals("")){res_29_M="0";} else {res_29_M=request.getParameter("res_29_M");}
 if(request.getParameter("res_29_F").trim().equals("")){res_29_F="0";} else {res_29_F=request.getParameter("res_29_F");}
 if(request.getParameter("res_34_M").trim().equals("")){res_34_M="0";} else {res_34_M=request.getParameter("res_34_M");}
 if(request.getParameter("res_34_F").trim().equals("")){res_34_F="0";} else {res_34_F=request.getParameter("res_34_F");}
 if(request.getParameter("res_39_M").trim().equals("")){res_39_M="0";} else {res_39_M=request.getParameter("res_39_M");}
 if(request.getParameter("res_39_F").trim().equals("")){res_39_F="0";} else {res_39_F=request.getParameter("res_39_F");}
 if(request.getParameter("res_49_M").trim().equals("")){res_49_M="0";} else {res_49_M=request.getParameter("res_49_M");}
 if(request.getParameter("res_49_F").trim().equals("")){res_49_F="0";} else {res_49_F=request.getParameter("res_49_F");}
 if(request.getParameter("res_50_M").trim().equals("")){res_50_M="0";} else {res_50_M=request.getParameter("res_50_M");}
 if(request.getParameter("res_50_F").trim().equals("")){res_50_F="0";} else {res_50_F=request.getParameter("res_50_F");}
 if(request.getParameter("res_T_M").trim().equals("")){res_T_M="0";} else {res_T_M=request.getParameter("res_T_M");}
 if(request.getParameter("res_T_F").trim().equals("")){res_T_F="0";} else {res_T_F=request.getParameter("res_T_F");}
 if(request.getParameter("res_GT").trim().equals("")){res_GT="0";} else {res_GT=request.getParameter("res_GT");}
 if(request.getParameter("tes_new_res_1_M").trim().equals("")){tes_new_res_1_M="0";} else {tes_new_res_1_M=request.getParameter("tes_new_res_1_M");}
 if(request.getParameter("tes_new_res_1_F").trim().equals("")){tes_new_res_1_F="0";} else {tes_new_res_1_F=request.getParameter("tes_new_res_1_F");}
 if(request.getParameter("tes_new_res_4_M").trim().equals("")){tes_new_res_4_M="0";} else {tes_new_res_4_M=request.getParameter("tes_new_res_4_M");}
 if(request.getParameter("tes_new_res_4_F").trim().equals("")){tes_new_res_4_F="0";} else {tes_new_res_4_F=request.getParameter("tes_new_res_4_F");}
 if(request.getParameter("tes_new_res_9_M").trim().equals("")){tes_new_res_9_M="0";} else {tes_new_res_9_M=request.getParameter("tes_new_res_9_M");}
 if(request.getParameter("tes_new_res_9_F").trim().equals("")){tes_new_res_9_F="0";} else {tes_new_res_9_F=request.getParameter("tes_new_res_9_F");}
 if(request.getParameter("tes_new_res_14_M").trim().equals("")){tes_new_res_14_M="0";} else {tes_new_res_14_M=request.getParameter("tes_new_res_14_M");}
 if(request.getParameter("tes_new_res_14_F").trim().equals("")){tes_new_res_14_F="0";} else {tes_new_res_14_F=request.getParameter("tes_new_res_14_F");}
 if(request.getParameter("tes_new_res_19_M").trim().equals("")){tes_new_res_19_M="0";} else {tes_new_res_19_M=request.getParameter("tes_new_res_19_M");}
 if(request.getParameter("tes_new_res_19_F").trim().equals("")){tes_new_res_19_F="0";} else {tes_new_res_19_F=request.getParameter("tes_new_res_19_F");}
 if(request.getParameter("tes_new_res_24_M").trim().equals("")){tes_new_res_24_M="0";} else {tes_new_res_24_M=request.getParameter("tes_new_res_24_M");}
 if(request.getParameter("tes_new_res_24_F").trim().equals("")){tes_new_res_24_F="0";} else {tes_new_res_24_F=request.getParameter("tes_new_res_24_F");}
 if(request.getParameter("tes_new_res_29_M").trim().equals("")){tes_new_res_29_M="0";} else {tes_new_res_29_M=request.getParameter("tes_new_res_29_M");}
 if(request.getParameter("tes_new_res_29_F").trim().equals("")){tes_new_res_29_F="0";} else {tes_new_res_29_F=request.getParameter("tes_new_res_29_F");}
 if(request.getParameter("tes_new_res_34_M").trim().equals("")){tes_new_res_34_M="0";} else {tes_new_res_34_M=request.getParameter("tes_new_res_34_M");}
 if(request.getParameter("tes_new_res_34_F").trim().equals("")){tes_new_res_34_F="0";} else {tes_new_res_34_F=request.getParameter("tes_new_res_34_F");}
 if(request.getParameter("tes_new_res_39_M").trim().equals("")){tes_new_res_39_M="0";} else {tes_new_res_39_M=request.getParameter("tes_new_res_39_M");}
 if(request.getParameter("tes_new_res_39_F").trim().equals("")){tes_new_res_39_F="0";} else {tes_new_res_39_F=request.getParameter("tes_new_res_39_F");}
 if(request.getParameter("tes_new_res_49_M").trim().equals("")){tes_new_res_49_M="0";} else {tes_new_res_49_M=request.getParameter("tes_new_res_49_M");}
 if(request.getParameter("tes_new_res_49_F").trim().equals("")){tes_new_res_49_F="0";} else {tes_new_res_49_F=request.getParameter("tes_new_res_49_F");}
 if(request.getParameter("tes_new_res_50_M").trim().equals("")){tes_new_res_50_M="0";} else {tes_new_res_50_M=request.getParameter("tes_new_res_50_M");}
 if(request.getParameter("tes_new_res_50_F").trim().equals("")){tes_new_res_50_F="0";} else {tes_new_res_50_F=request.getParameter("tes_new_res_50_F");}
 if(request.getParameter("tes_new_res_T_M").trim().equals("")){tes_new_res_T_M="0";} else {tes_new_res_T_M=request.getParameter("tes_new_res_T_M");}
 if(request.getParameter("tes_new_res_T_F").trim().equals("")){tes_new_res_T_F="0";} else {tes_new_res_T_F=request.getParameter("tes_new_res_T_F");}
 if(request.getParameter("tes_new_res_GT").trim().equals("")){tes_new_res_GT="0";} else {tes_new_res_GT=request.getParameter("tes_new_res_GT");}
 if(request.getParameter("pos_1_M").trim().equals("")){pos_1_M="0";} else {pos_1_M=request.getParameter("pos_1_M");}
 if(request.getParameter("pos_1_F").trim().equals("")){pos_1_F="0";} else {pos_1_F=request.getParameter("pos_1_F");}
 if(request.getParameter("pos_4_M").trim().equals("")){pos_4_M="0";} else {pos_4_M=request.getParameter("pos_4_M");}
 if(request.getParameter("pos_4_F").trim().equals("")){pos_4_F="0";} else {pos_4_F=request.getParameter("pos_4_F");}
 if(request.getParameter("pos_9_M").trim().equals("")){pos_9_M="0";} else {pos_9_M=request.getParameter("pos_9_M");}
 if(request.getParameter("pos_9_F").trim().equals("")){pos_9_F="0";} else {pos_9_F=request.getParameter("pos_9_F");}
 if(request.getParameter("pos_14_M").trim().equals("")){pos_14_M="0";} else {pos_14_M=request.getParameter("pos_14_M");}
 if(request.getParameter("pos_14_F").trim().equals("")){pos_14_F="0";} else {pos_14_F=request.getParameter("pos_14_F");}
 if(request.getParameter("pos_19_M").trim().equals("")){pos_19_M="0";} else {pos_19_M=request.getParameter("pos_19_M");}
 if(request.getParameter("pos_19_F").trim().equals("")){pos_19_F="0";} else {pos_19_F=request.getParameter("pos_19_F");}
 if(request.getParameter("pos_24_M").trim().equals("")){pos_24_M="0";} else {pos_24_M=request.getParameter("pos_24_M");}
 if(request.getParameter("pos_24_F").trim().equals("")){pos_24_F="0";} else {pos_24_F=request.getParameter("pos_24_F");}
 if(request.getParameter("pos_29_M").trim().equals("")){pos_29_M="0";} else {pos_29_M=request.getParameter("pos_29_M");}
 if(request.getParameter("pos_29_F").trim().equals("")){pos_29_F="0";} else {pos_29_F=request.getParameter("pos_29_F");}
 if(request.getParameter("pos_34_M").trim().equals("")){pos_34_M="0";} else {pos_34_M=request.getParameter("pos_34_M");}
 if(request.getParameter("pos_34_F").trim().equals("")){pos_34_F="0";} else {pos_34_F=request.getParameter("pos_34_F");}
 if(request.getParameter("pos_39_M").trim().equals("")){pos_39_M="0";} else {pos_39_M=request.getParameter("pos_39_M");}
 if(request.getParameter("pos_39_F").trim().equals("")){pos_39_F="0";} else {pos_39_F=request.getParameter("pos_39_F");}
 if(request.getParameter("pos_49_M").trim().equals("")){pos_49_M="0";} else {pos_49_M=request.getParameter("pos_49_M");}
 if(request.getParameter("pos_49_F").trim().equals("")){pos_49_F="0";} else {pos_49_F=request.getParameter("pos_49_F");}
 if(request.getParameter("pos_50_M").trim().equals("")){pos_50_M="0";} else {pos_50_M=request.getParameter("pos_50_M");}
 if(request.getParameter("pos_50_F").trim().equals("")){pos_50_F="0";} else {pos_50_F=request.getParameter("pos_50_F");}
 if(request.getParameter("pos_T_M").trim().equals("")){pos_T_M="0";} else {pos_T_M=request.getParameter("pos_T_M");}
 if(request.getParameter("pos_T_F").trim().equals("")){pos_T_F="0";} else {pos_T_F=request.getParameter("pos_T_F");}
 if(request.getParameter("pos_GT").trim().equals("")){pos_GT="0";} else {pos_GT=request.getParameter("pos_GT");}
 if(request.getParameter("pos_tes_1_M").trim().equals("")){pos_tes_1_M="0";} else {pos_tes_1_M=request.getParameter("pos_tes_1_M");}
 if(request.getParameter("pos_tes_1_F").trim().equals("")){pos_tes_1_F="0";} else {pos_tes_1_F=request.getParameter("pos_tes_1_F");}
 if(request.getParameter("pos_tes_4_M").trim().equals("")){pos_tes_4_M="0";} else {pos_tes_4_M=request.getParameter("pos_tes_4_M");}
 if(request.getParameter("pos_tes_4_F").trim().equals("")){pos_tes_4_F="0";} else {pos_tes_4_F=request.getParameter("pos_tes_4_F");}
 if(request.getParameter("pos_tes_9_M").trim().equals("")){pos_tes_9_M="0";} else {pos_tes_9_M=request.getParameter("pos_tes_9_M");}
 if(request.getParameter("pos_tes_9_F").trim().equals("")){pos_tes_9_F="0";} else {pos_tes_9_F=request.getParameter("pos_tes_9_F");}
 if(request.getParameter("pos_tes_14_M").trim().equals("")){pos_tes_14_M="0";} else {pos_tes_14_M=request.getParameter("pos_tes_14_M");}
 if(request.getParameter("pos_tes_14_F").trim().equals("")){pos_tes_14_F="0";} else {pos_tes_14_F=request.getParameter("pos_tes_14_F");}
 if(request.getParameter("pos_tes_19_M").trim().equals("")){pos_tes_19_M="0";} else {pos_tes_19_M=request.getParameter("pos_tes_19_M");}
 if(request.getParameter("pos_tes_19_F").trim().equals("")){pos_tes_19_F="0";} else {pos_tes_19_F=request.getParameter("pos_tes_19_F");}
 if(request.getParameter("pos_tes_24_M").trim().equals("")){pos_tes_24_M="0";} else {pos_tes_24_M=request.getParameter("pos_tes_24_M");}
 if(request.getParameter("pos_tes_24_F").trim().equals("")){pos_tes_24_F="0";} else {pos_tes_24_F=request.getParameter("pos_tes_24_F");}
 if(request.getParameter("pos_tes_29_M").trim().equals("")){pos_tes_29_M="0";} else {pos_tes_29_M=request.getParameter("pos_tes_29_M");}
 if(request.getParameter("pos_tes_29_F").trim().equals("")){pos_tes_29_F="0";} else {pos_tes_29_F=request.getParameter("pos_tes_29_F");}
 if(request.getParameter("pos_tes_34_M").trim().equals("")){pos_tes_34_M="0";} else {pos_tes_34_M=request.getParameter("pos_tes_34_M");}
 if(request.getParameter("pos_tes_34_F").trim().equals("")){pos_tes_34_F="0";} else {pos_tes_34_F=request.getParameter("pos_tes_34_F");}
 if(request.getParameter("pos_tes_39_M").trim().equals("")){pos_tes_39_M="0";} else {pos_tes_39_M=request.getParameter("pos_tes_39_M");}
 if(request.getParameter("pos_tes_39_F").trim().equals("")){pos_tes_39_F="0";} else {pos_tes_39_F=request.getParameter("pos_tes_39_F");}
 if(request.getParameter("pos_tes_49_M").trim().equals("")){pos_tes_49_M="0";} else {pos_tes_49_M=request.getParameter("pos_tes_49_M");}
 if(request.getParameter("pos_tes_49_F").trim().equals("")){pos_tes_49_F="0";} else {pos_tes_49_F=request.getParameter("pos_tes_49_F");}
 if(request.getParameter("pos_tes_50_M").trim().equals("")){pos_tes_50_M="0";} else {pos_tes_50_M=request.getParameter("pos_tes_50_M");}
 if(request.getParameter("pos_tes_50_F").trim().equals("")){pos_tes_50_F="0";} else {pos_tes_50_F=request.getParameter("pos_tes_50_F");}
 if(request.getParameter("pos_tes_T_M").trim().equals("")){pos_tes_T_M="0";} else {pos_tes_T_M=request.getParameter("pos_tes_T_M");}
 if(request.getParameter("pos_tes_T_F").trim().equals("")){pos_tes_T_F="0";} else {pos_tes_T_F=request.getParameter("pos_tes_T_F");}
 if(request.getParameter("pos_tes_GT").trim().equals("")){pos_tes_GT="0";} else {pos_tes_GT=request.getParameter("pos_tes_GT");}
 if(request.getParameter("ref_1_M").trim().equals("")){ref_1_M="0";} else {ref_1_M=request.getParameter("ref_1_M");}
 if(request.getParameter("ref_1_F").trim().equals("")){ref_1_F="0";} else {ref_1_F=request.getParameter("ref_1_F");}
 if(request.getParameter("ref_4_M").trim().equals("")){ref_4_M="0";} else {ref_4_M=request.getParameter("ref_4_M");}
 if(request.getParameter("ref_4_F").trim().equals("")){ref_4_F="0";} else {ref_4_F=request.getParameter("ref_4_F");}
 if(request.getParameter("ref_9_M").trim().equals("")){ref_9_M="0";} else {ref_9_M=request.getParameter("ref_9_M");}
 if(request.getParameter("ref_9_F").trim().equals("")){ref_9_F="0";} else {ref_9_F=request.getParameter("ref_9_F");}
 if(request.getParameter("ref_14_M").trim().equals("")){ref_14_M="0";} else {ref_14_M=request.getParameter("ref_14_M");}
 if(request.getParameter("ref_14_F").trim().equals("")){ref_14_F="0";} else {ref_14_F=request.getParameter("ref_14_F");}
 if(request.getParameter("ref_19_M").trim().equals("")){ref_19_M="0";} else {ref_19_M=request.getParameter("ref_19_M");}
 if(request.getParameter("ref_19_F").trim().equals("")){ref_19_F="0";} else {ref_19_F=request.getParameter("ref_19_F");}
 if(request.getParameter("ref_24_M").trim().equals("")){ref_24_M="0";} else {ref_24_M=request.getParameter("ref_24_M");}
 if(request.getParameter("ref_24_F").trim().equals("")){ref_24_F="0";} else {ref_24_F=request.getParameter("ref_24_F");}
 if(request.getParameter("ref_29_M").trim().equals("")){ref_29_M="0";} else {ref_29_M=request.getParameter("ref_29_M");}
 if(request.getParameter("ref_29_F").trim().equals("")){ref_29_F="0";} else {ref_29_F=request.getParameter("ref_29_F");}
 if(request.getParameter("ref_34_M").trim().equals("")){ref_34_M="0";} else {ref_34_M=request.getParameter("ref_34_M");}
 if(request.getParameter("ref_34_F").trim().equals("")){ref_34_F="0";} else {ref_34_F=request.getParameter("ref_34_F");}
 if(request.getParameter("ref_39_M").trim().equals("")){ref_39_M="0";} else {ref_39_M=request.getParameter("ref_39_M");}
 if(request.getParameter("ref_39_F").trim().equals("")){ref_39_F="0";} else {ref_39_F=request.getParameter("ref_39_F");}
 if(request.getParameter("ref_49_M").trim().equals("")){ref_49_M="0";} else {ref_49_M=request.getParameter("ref_49_M");}
 if(request.getParameter("ref_49_F").trim().equals("")){ref_49_F="0";} else {ref_49_F=request.getParameter("ref_49_F");}
 if(request.getParameter("ref_50_M").trim().equals("")){ref_50_M="0";} else {ref_50_M=request.getParameter("ref_50_M");}
 if(request.getParameter("ref_50_F").trim().equals("")){ref_50_F="0";} else {ref_50_F=request.getParameter("ref_50_F");}
 if(request.getParameter("ref_T_M").trim().equals("")){ref_T_M="0";} else {ref_T_M=request.getParameter("ref_T_M");}
 if(request.getParameter("ref_T_F").trim().equals("")){ref_T_F="0";} else {ref_T_F=request.getParameter("ref_T_F");}
 if(request.getParameter("ref_GT").trim().equals("")){ref_GT="0";} else {ref_GT=request.getParameter("ref_GT");}
 if(request.getParameter("tes_marps_1_M").trim().equals("")){tes_marps_1_M="0";} else {tes_marps_1_M=request.getParameter("tes_marps_1_M");}
 if(request.getParameter("tes_marps_1_F").trim().equals("")){tes_marps_1_F="0";} else {tes_marps_1_F=request.getParameter("tes_marps_1_F");}
 if(request.getParameter("tes_marps_4_M").trim().equals("")){tes_marps_4_M="0";} else {tes_marps_4_M=request.getParameter("tes_marps_4_M");}
 if(request.getParameter("tes_marps_4_F").trim().equals("")){tes_marps_4_F="0";} else {tes_marps_4_F=request.getParameter("tes_marps_4_F");}
 if(request.getParameter("tes_marps_9_M").trim().equals("")){tes_marps_9_M="0";} else {tes_marps_9_M=request.getParameter("tes_marps_9_M");}
 if(request.getParameter("tes_marps_9_F").trim().equals("")){tes_marps_9_F="0";} else {tes_marps_9_F=request.getParameter("tes_marps_9_F");}
 if(request.getParameter("tes_marps_14_M").trim().equals("")){tes_marps_14_M="0";} else {tes_marps_14_M=request.getParameter("tes_marps_14_M");}
 if(request.getParameter("tes_marps_14_F").trim().equals("")){tes_marps_14_F="0";} else {tes_marps_14_F=request.getParameter("tes_marps_14_F");}
 if(request.getParameter("tes_marps_19_M").trim().equals("")){tes_marps_19_M="0";} else {tes_marps_19_M=request.getParameter("tes_marps_19_M");}
 if(request.getParameter("tes_marps_19_F").trim().equals("")){tes_marps_19_F="0";} else {tes_marps_19_F=request.getParameter("tes_marps_19_F");}
 if(request.getParameter("tes_marps_24_M").trim().equals("")){tes_marps_24_M="0";} else {tes_marps_24_M=request.getParameter("tes_marps_24_M");}
 if(request.getParameter("tes_marps_24_F").trim().equals("")){tes_marps_24_F="0";} else {tes_marps_24_F=request.getParameter("tes_marps_24_F");}
 if(request.getParameter("tes_marps_29_M").trim().equals("")){tes_marps_29_M="0";} else {tes_marps_29_M=request.getParameter("tes_marps_29_M");}
 if(request.getParameter("tes_marps_29_F").trim().equals("")){tes_marps_29_F="0";} else {tes_marps_29_F=request.getParameter("tes_marps_29_F");}
 if(request.getParameter("tes_marps_34_M").trim().equals("")){tes_marps_34_M="0";} else {tes_marps_34_M=request.getParameter("tes_marps_34_M");}
 if(request.getParameter("tes_marps_34_F").trim().equals("")){tes_marps_34_F="0";} else {tes_marps_34_F=request.getParameter("tes_marps_34_F");}
 if(request.getParameter("tes_marps_39_M").trim().equals("")){tes_marps_39_M="0";} else {tes_marps_39_M=request.getParameter("tes_marps_39_M");}
 if(request.getParameter("tes_marps_39_F").trim().equals("")){tes_marps_39_F="0";} else {tes_marps_39_F=request.getParameter("tes_marps_39_F");}
 if(request.getParameter("tes_marps_49_M").trim().equals("")){tes_marps_49_M="0";} else {tes_marps_49_M=request.getParameter("tes_marps_49_M");}
 if(request.getParameter("tes_marps_49_F").trim().equals("")){tes_marps_49_F="0";} else {tes_marps_49_F=request.getParameter("tes_marps_49_F");}
 if(request.getParameter("tes_marps_50_M").trim().equals("")){tes_marps_50_M="0";} else {tes_marps_50_M=request.getParameter("tes_marps_50_M");}
 if(request.getParameter("tes_marps_50_F").trim().equals("")){tes_marps_50_F="0";} else {tes_marps_50_F=request.getParameter("tes_marps_50_F");}
 if(request.getParameter("tes_marps_T_M").trim().equals("")){tes_marps_T_M="0";} else {tes_marps_T_M=request.getParameter("tes_marps_T_M");}
 if(request.getParameter("tes_marps_T_F").trim().equals("")){tes_marps_T_F="0";} else {tes_marps_T_F=request.getParameter("tes_marps_T_F");}
 if(request.getParameter("tes_marps_GT").trim().equals("")){tes_marps_GT="0";} else {tes_marps_GT=request.getParameter("tes_marps_GT");}
 if(request.getParameter("pos_marps_1_M").trim().equals("")){pos_marps_1_M="0";} else {pos_marps_1_M=request.getParameter("pos_marps_1_M");}
 if(request.getParameter("pos_marps_1_F").trim().equals("")){pos_marps_1_F="0";} else {pos_marps_1_F=request.getParameter("pos_marps_1_F");}
 if(request.getParameter("pos_marps_4_M").trim().equals("")){pos_marps_4_M="0";} else {pos_marps_4_M=request.getParameter("pos_marps_4_M");}
 if(request.getParameter("pos_marps_4_F").trim().equals("")){pos_marps_4_F="0";} else {pos_marps_4_F=request.getParameter("pos_marps_4_F");}
 if(request.getParameter("pos_marps_9_M").trim().equals("")){pos_marps_9_M="0";} else {pos_marps_9_M=request.getParameter("pos_marps_9_M");}
 if(request.getParameter("pos_marps_9_F").trim().equals("")){pos_marps_9_F="0";} else {pos_marps_9_F=request.getParameter("pos_marps_9_F");}
 if(request.getParameter("pos_marps_14_M").trim().equals("")){pos_marps_14_M="0";} else {pos_marps_14_M=request.getParameter("pos_marps_14_M");}
 if(request.getParameter("pos_marps_14_F").trim().equals("")){pos_marps_14_F="0";} else {pos_marps_14_F=request.getParameter("pos_marps_14_F");}
 if(request.getParameter("pos_marps_19_M").trim().equals("")){pos_marps_19_M="0";} else {pos_marps_19_M=request.getParameter("pos_marps_19_M");}
 if(request.getParameter("pos_marps_19_F").trim().equals("")){pos_marps_19_F="0";} else {pos_marps_19_F=request.getParameter("pos_marps_19_F");}
 if(request.getParameter("pos_marps_24_M").trim().equals("")){pos_marps_24_M="0";} else {pos_marps_24_M=request.getParameter("pos_marps_24_M");}
 if(request.getParameter("pos_marps_24_F").trim().equals("")){pos_marps_24_F="0";} else {pos_marps_24_F=request.getParameter("pos_marps_24_F");}
 if(request.getParameter("pos_marps_29_M").trim().equals("")){pos_marps_29_M="0";} else {pos_marps_29_M=request.getParameter("pos_marps_29_M");}
 if(request.getParameter("pos_marps_29_F").trim().equals("")){pos_marps_29_F="0";} else {pos_marps_29_F=request.getParameter("pos_marps_29_F");}
 if(request.getParameter("pos_marps_34_M").trim().equals("")){pos_marps_34_M="0";} else {pos_marps_34_M=request.getParameter("pos_marps_34_M");}
 if(request.getParameter("pos_marps_34_F").trim().equals("")){pos_marps_34_F="0";} else {pos_marps_34_F=request.getParameter("pos_marps_34_F");}
 if(request.getParameter("pos_marps_39_M").trim().equals("")){pos_marps_39_M="0";} else {pos_marps_39_M=request.getParameter("pos_marps_39_M");}
 if(request.getParameter("pos_marps_39_F").trim().equals("")){pos_marps_39_F="0";} else {pos_marps_39_F=request.getParameter("pos_marps_39_F");}
 if(request.getParameter("pos_marps_49_M").trim().equals("")){pos_marps_49_M="0";} else {pos_marps_49_M=request.getParameter("pos_marps_49_M");}
 if(request.getParameter("pos_marps_49_F").trim().equals("")){pos_marps_49_F="0";} else {pos_marps_49_F=request.getParameter("pos_marps_49_F");}
 if(request.getParameter("pos_marps_50_M").trim().equals("")){pos_marps_50_M="0";} else {pos_marps_50_M=request.getParameter("pos_marps_50_M");}
 if(request.getParameter("pos_marps_50_F").trim().equals("")){pos_marps_50_F="0";} else {pos_marps_50_F=request.getParameter("pos_marps_50_F");}
 if(request.getParameter("pos_marps_T_M").trim().equals("")){pos_marps_T_M="0";} else {pos_marps_T_M=request.getParameter("pos_marps_T_M");}
 if(request.getParameter("pos_marps_T_F").trim().equals("")){pos_marps_T_F="0";} else {pos_marps_T_F=request.getParameter("pos_marps_T_F");}
 if(request.getParameter("pos_marps_GT").trim().equals("")){pos_marps_GT="0";} else {pos_marps_GT=request.getParameter("pos_marps_GT");}
 if(request.getParameter("tes_coup_1_M").trim().equals("")){tes_coup_1_M="0";} else {tes_coup_1_M=request.getParameter("tes_coup_1_M");}
 if(request.getParameter("tes_coup_1_F").trim().equals("")){tes_coup_1_F="0";} else {tes_coup_1_F=request.getParameter("tes_coup_1_F");}
 if(request.getParameter("tes_coup_4_M").trim().equals("")){tes_coup_4_M="0";} else {tes_coup_4_M=request.getParameter("tes_coup_4_M");}
 if(request.getParameter("tes_coup_4_F").trim().equals("")){tes_coup_4_F="0";} else {tes_coup_4_F=request.getParameter("tes_coup_4_F");}
 if(request.getParameter("tes_coup_9_M").trim().equals("")){tes_coup_9_M="0";} else {tes_coup_9_M=request.getParameter("tes_coup_9_M");}
 if(request.getParameter("tes_coup_9_F").trim().equals("")){tes_coup_9_F="0";} else {tes_coup_9_F=request.getParameter("tes_coup_9_F");}
 if(request.getParameter("tes_coup_14_M").trim().equals("")){tes_coup_14_M="0";} else {tes_coup_14_M=request.getParameter("tes_coup_14_M");}
 if(request.getParameter("tes_coup_14_F").trim().equals("")){tes_coup_14_F="0";} else {tes_coup_14_F=request.getParameter("tes_coup_14_F");}
 if(request.getParameter("tes_coup_19_M").trim().equals("")){tes_coup_19_M="0";} else {tes_coup_19_M=request.getParameter("tes_coup_19_M");}
 if(request.getParameter("tes_coup_19_F").trim().equals("")){tes_coup_19_F="0";} else {tes_coup_19_F=request.getParameter("tes_coup_19_F");}
 if(request.getParameter("tes_coup_24_M").trim().equals("")){tes_coup_24_M="0";} else {tes_coup_24_M=request.getParameter("tes_coup_24_M");}
 if(request.getParameter("tes_coup_24_F").trim().equals("")){tes_coup_24_F="0";} else {tes_coup_24_F=request.getParameter("tes_coup_24_F");}
 if(request.getParameter("tes_coup_29_M").trim().equals("")){tes_coup_29_M="0";} else {tes_coup_29_M=request.getParameter("tes_coup_29_M");}
 if(request.getParameter("tes_coup_29_F").trim().equals("")){tes_coup_29_F="0";} else {tes_coup_29_F=request.getParameter("tes_coup_29_F");}
 if(request.getParameter("tes_coup_34_M").trim().equals("")){tes_coup_34_M="0";} else {tes_coup_34_M=request.getParameter("tes_coup_34_M");}
 if(request.getParameter("tes_coup_34_F").trim().equals("")){tes_coup_34_F="0";} else {tes_coup_34_F=request.getParameter("tes_coup_34_F");}
 if(request.getParameter("tes_coup_39_M").trim().equals("")){tes_coup_39_M="0";} else {tes_coup_39_M=request.getParameter("tes_coup_39_M");}
 if(request.getParameter("tes_coup_39_F").trim().equals("")){tes_coup_39_F="0";} else {tes_coup_39_F=request.getParameter("tes_coup_39_F");}
 if(request.getParameter("tes_coup_49_M").trim().equals("")){tes_coup_49_M="0";} else {tes_coup_49_M=request.getParameter("tes_coup_49_M");}
 if(request.getParameter("tes_coup_49_F").trim().equals("")){tes_coup_49_F="0";} else {tes_coup_49_F=request.getParameter("tes_coup_49_F");}
 if(request.getParameter("tes_coup_50_M").trim().equals("")){tes_coup_50_M="0";} else {tes_coup_50_M=request.getParameter("tes_coup_50_M");}
 if(request.getParameter("tes_coup_50_F").trim().equals("")){tes_coup_50_F="0";} else {tes_coup_50_F=request.getParameter("tes_coup_50_F");}
 if(request.getParameter("tes_coup_T_M").trim().equals("")){tes_coup_T_M="0";} else {tes_coup_T_M=request.getParameter("tes_coup_T_M");}
 if(request.getParameter("tes_coup_T_F").trim().equals("")){tes_coup_T_F="0";} else {tes_coup_T_F=request.getParameter("tes_coup_T_F");}
 if(request.getParameter("tes_coup_GT").trim().equals("")){tes_coup_GT="0";} else {tes_coup_GT=request.getParameter("tes_coup_GT");}
 if(request.getParameter("tes_coup_dis_1_M").trim().equals("")){tes_coup_dis_1_M="0";} else {tes_coup_dis_1_M=request.getParameter("tes_coup_dis_1_M");}
 if(request.getParameter("tes_coup_dis_1_F").trim().equals("")){tes_coup_dis_1_F="0";} else {tes_coup_dis_1_F=request.getParameter("tes_coup_dis_1_F");}
 if(request.getParameter("tes_coup_dis_4_M").trim().equals("")){tes_coup_dis_4_M="0";} else {tes_coup_dis_4_M=request.getParameter("tes_coup_dis_4_M");}
 if(request.getParameter("tes_coup_dis_4_F").trim().equals("")){tes_coup_dis_4_F="0";} else {tes_coup_dis_4_F=request.getParameter("tes_coup_dis_4_F");}
 if(request.getParameter("tes_coup_dis_9_M").trim().equals("")){tes_coup_dis_9_M="0";} else {tes_coup_dis_9_M=request.getParameter("tes_coup_dis_9_M");}
 if(request.getParameter("tes_coup_dis_9_F").trim().equals("")){tes_coup_dis_9_F="0";} else {tes_coup_dis_9_F=request.getParameter("tes_coup_dis_9_F");}
 if(request.getParameter("tes_coup_dis_14_M").trim().equals("")){tes_coup_dis_14_M="0";} else {tes_coup_dis_14_M=request.getParameter("tes_coup_dis_14_M");}
 if(request.getParameter("tes_coup_dis_14_F").trim().equals("")){tes_coup_dis_14_F="0";} else {tes_coup_dis_14_F=request.getParameter("tes_coup_dis_14_F");}
 if(request.getParameter("tes_coup_dis_19_M").trim().equals("")){tes_coup_dis_19_M="0";} else {tes_coup_dis_19_M=request.getParameter("tes_coup_dis_19_M");}
 if(request.getParameter("tes_coup_dis_19_F").trim().equals("")){tes_coup_dis_19_F="0";} else {tes_coup_dis_19_F=request.getParameter("tes_coup_dis_19_F");}
 if(request.getParameter("tes_coup_dis_24_M").trim().equals("")){tes_coup_dis_24_M="0";} else {tes_coup_dis_24_M=request.getParameter("tes_coup_dis_24_M");}
 if(request.getParameter("tes_coup_dis_24_F").trim().equals("")){tes_coup_dis_24_F="0";} else {tes_coup_dis_24_F=request.getParameter("tes_coup_dis_24_F");}
 if(request.getParameter("tes_coup_dis_29_M").trim().equals("")){tes_coup_dis_29_M="0";} else {tes_coup_dis_29_M=request.getParameter("tes_coup_dis_29_M");}
 if(request.getParameter("tes_coup_dis_29_F").trim().equals("")){tes_coup_dis_29_F="0";} else {tes_coup_dis_29_F=request.getParameter("tes_coup_dis_29_F");}
 if(request.getParameter("tes_coup_dis_34_M").trim().equals("")){tes_coup_dis_34_M="0";} else {tes_coup_dis_34_M=request.getParameter("tes_coup_dis_34_M");}
 if(request.getParameter("tes_coup_dis_34_F").trim().equals("")){tes_coup_dis_34_F="0";} else {tes_coup_dis_34_F=request.getParameter("tes_coup_dis_34_F");}
 if(request.getParameter("tes_coup_dis_39_M").trim().equals("")){tes_coup_dis_39_M="0";} else {tes_coup_dis_39_M=request.getParameter("tes_coup_dis_39_M");}
 if(request.getParameter("tes_coup_dis_39_F").trim().equals("")){tes_coup_dis_39_F="0";} else {tes_coup_dis_39_F=request.getParameter("tes_coup_dis_39_F");}
 if(request.getParameter("tes_coup_dis_49_M").trim().equals("")){tes_coup_dis_49_M="0";} else {tes_coup_dis_49_M=request.getParameter("tes_coup_dis_49_M");}
 if(request.getParameter("tes_coup_dis_49_F").trim().equals("")){tes_coup_dis_49_F="0";} else {tes_coup_dis_49_F=request.getParameter("tes_coup_dis_49_F");}
 if(request.getParameter("tes_coup_dis_50_M").trim().equals("")){tes_coup_dis_50_M="0";} else {tes_coup_dis_50_M=request.getParameter("tes_coup_dis_50_M");}
 if(request.getParameter("tes_coup_dis_50_F").trim().equals("")){tes_coup_dis_50_F="0";} else {tes_coup_dis_50_F=request.getParameter("tes_coup_dis_50_F");}
 if(request.getParameter("tes_coup_dis_T_M").trim().equals("")){tes_coup_dis_T_M="0";} else {tes_coup_dis_T_M=request.getParameter("tes_coup_dis_T_M");}
 if(request.getParameter("tes_coup_dis_T_F").trim().equals("")){tes_coup_dis_T_F="0";} else {tes_coup_dis_T_F=request.getParameter("tes_coup_dis_T_F");}
 if(request.getParameter("tes_coup_dis_GT").trim().equals("")){tes_coup_dis_GT="0";} else {tes_coup_dis_GT=request.getParameter("tes_coup_dis_GT");}
 
  if(request.getParameter("tk_negative1").trim().equals("")){tk_negative1="0";} else {tk_negative1=request.getParameter("tk_negative1");}
 if(request.getParameter("tk_negative2").trim().equals("")){tk_negative2="0";} else {tk_negative2=request.getParameter("tk_negative2");}
 if(request.getParameter("tk_positive1").trim().equals("")){tk_positive1="0";} else {tk_positive1=request.getParameter("tk_positive1");}
 if(request.getParameter("tk_positive2").trim().equals("")){tk_positive2="0";} else {tk_positive2=request.getParameter("tk_positive2");}
 if(request.getParameter("tk_invalid1").trim().equals("")){tk_invalid1="0";} else {tk_invalid1=request.getParameter("tk_invalid1");}
 if(request.getParameter("tk_invalid2").trim().equals("")){tk_invalid2="0";} else {tk_invalid2=request.getParameter("tk_invalid2");}
 if(request.getParameter("tk_wastage1").trim().equals("")){tk_wastage1="0";} else {tk_wastage1=request.getParameter("tk_wastage1");}
 if(request.getParameter("tk_wastage2").trim().equals("")){tk_wastage2="0";} else {tk_wastage2=request.getParameter("tk_wastage2");}
 if(request.getParameter("tk_total1").trim().equals("")){tk_total1="0";} else {tk_total1=request.getParameter("tk_total1");}
 if(request.getParameter("tk_total2").trim().equals("")){tk_total2="0";} else {tk_total2=request.getParameter("tk_total2");}


 
 
 
 
 //_______________________________________________________________Gender Totals___________________________________________________
 
 
couns_T_M= ""+ (new Integer(couns_1_M)+ new Integer(couns_4_M)+ new Integer(couns_9_M)+ new Integer(couns_14_M)+ new Integer(couns_19_M)+ new Integer(couns_24_M)+ new Integer(couns_29_M)+ new Integer(couns_34_M)+ new Integer(couns_39_M)+ new Integer(couns_49_M)+ new Integer(couns_50_M));
couns_T_F= ""+ (new Integer(couns_1_F)+ new Integer(couns_4_F)+ new Integer(couns_9_F)+ new Integer(couns_14_F)+ new Integer(couns_19_F)+ new Integer(couns_24_F)+ new Integer(couns_29_F)+ new Integer(couns_34_F)+ new Integer(couns_39_F)+ new Integer(couns_49_F)+ new Integer(couns_50_F));
tes_T_M= ""+ (new Integer(tes_1_M)+ new Integer(tes_4_M)+ new Integer(tes_9_M)+ new Integer(tes_14_M)+ new Integer(tes_19_M)+ new Integer(tes_24_M)+ new Integer(tes_29_M)+ new Integer(tes_34_M)+ new Integer(tes_39_M)+ new Integer(tes_49_M)+ new Integer(tes_50_M));
tes_T_F= ""+ (new Integer(tes_1_F)+ new Integer(tes_4_F)+ new Integer(tes_9_F)+ new Integer(tes_14_F)+ new Integer(tes_19_F)+ new Integer(tes_24_F)+ new Integer(tes_29_F)+ new Integer(tes_34_F)+ new Integer(tes_39_F)+ new Integer(tes_49_F)+ new Integer(tes_50_F));
tes_new_T_M= ""+ (new Integer(tes_new_1_M)+ new Integer(tes_new_4_M)+ new Integer(tes_new_9_M)+ new Integer(tes_new_14_M)+ new Integer(tes_new_19_M)+ new Integer(tes_new_24_M)+ new Integer(tes_new_29_M)+ new Integer(tes_new_34_M)+ new Integer(tes_new_39_M)+ new Integer(tes_new_49_M)+ new Integer(tes_new_50_M));
tes_new_T_F= ""+ (new Integer(tes_new_1_F)+ new Integer(tes_new_4_F)+ new Integer(tes_new_9_F)+ new Integer(tes_new_14_F)+ new Integer(tes_new_19_F)+ new Integer(tes_new_24_F)+ new Integer(tes_new_29_F)+ new Integer(tes_new_34_F)+ new Integer(tes_new_39_F)+ new Integer(tes_new_49_F)+ new Integer(tes_new_50_F));
res_T_M= ""+ (new Integer(res_1_M)+ new Integer(res_4_M)+ new Integer(res_9_M)+ new Integer(res_14_M)+ new Integer(res_19_M)+ new Integer(res_24_M)+ new Integer(res_29_M)+ new Integer(res_34_M)+ new Integer(res_39_M)+ new Integer(res_49_M)+ new Integer(res_50_M));
res_T_F= ""+ (new Integer(res_1_F)+ new Integer(res_4_F)+ new Integer(res_9_F)+ new Integer(res_14_F)+ new Integer(res_19_F)+ new Integer(res_24_F)+ new Integer(res_29_F)+ new Integer(res_34_F)+ new Integer(res_39_F)+ new Integer(res_49_F)+ new Integer(res_50_F));
tes_new_res_T_M= ""+ (new Integer(tes_new_res_1_M)+ new Integer(tes_new_res_4_M)+ new Integer(tes_new_res_9_M)+ new Integer(tes_new_res_14_M)+ new Integer(tes_new_res_19_M)+ new Integer(tes_new_res_24_M)+ new Integer(tes_new_res_29_M)+ new Integer(tes_new_res_34_M)+ new Integer(tes_new_res_39_M)+ new Integer(tes_new_res_49_M)+ new Integer(tes_new_res_50_M));
tes_new_res_T_F= ""+ (new Integer(tes_new_res_1_F)+ new Integer(tes_new_res_4_F)+ new Integer(tes_new_res_9_F)+ new Integer(tes_new_res_14_F)+ new Integer(tes_new_res_19_F)+ new Integer(tes_new_res_24_F)+ new Integer(tes_new_res_29_F)+ new Integer(tes_new_res_34_F)+ new Integer(tes_new_res_39_F)+ new Integer(tes_new_res_49_F)+ new Integer(tes_new_res_50_F));
pos_T_M= ""+ (new Integer(pos_1_M)+ new Integer(pos_4_M)+ new Integer(pos_9_M)+ new Integer(pos_14_M)+ new Integer(pos_19_M)+ new Integer(pos_24_M)+ new Integer(pos_29_M)+ new Integer(pos_34_M)+ new Integer(pos_39_M)+ new Integer(pos_49_M)+ new Integer(pos_50_M));
pos_T_F= ""+ (new Integer(pos_1_F)+ new Integer(pos_4_F)+ new Integer(pos_9_F)+ new Integer(pos_14_F)+ new Integer(pos_19_F)+ new Integer(pos_24_F)+ new Integer(pos_29_F)+ new Integer(pos_34_F)+ new Integer(pos_39_F)+ new Integer(pos_49_F)+ new Integer(pos_50_F));
pos_tes_T_M= ""+ (new Integer(pos_tes_1_M)+ new Integer(pos_tes_4_M)+ new Integer(pos_tes_9_M)+ new Integer(pos_tes_14_M)+ new Integer(pos_tes_19_M)+ new Integer(pos_tes_24_M)+ new Integer(pos_tes_29_M)+ new Integer(pos_tes_34_M)+ new Integer(pos_tes_39_M)+ new Integer(pos_tes_49_M)+ new Integer(pos_tes_50_M));
pos_tes_T_F= ""+ (new Integer(pos_tes_1_F)+ new Integer(pos_tes_4_F)+ new Integer(pos_tes_9_F)+ new Integer(pos_tes_14_F)+ new Integer(pos_tes_19_F)+ new Integer(pos_tes_24_F)+ new Integer(pos_tes_29_F)+ new Integer(pos_tes_34_F)+ new Integer(pos_tes_39_F)+ new Integer(pos_tes_49_F)+ new Integer(pos_tes_50_F));
ref_T_M= ""+ (new Integer(ref_1_M)+ new Integer(ref_4_M)+ new Integer(ref_9_M)+ new Integer(ref_14_M)+ new Integer(ref_19_M)+ new Integer(ref_24_M)+ new Integer(ref_29_M)+ new Integer(ref_34_M)+ new Integer(ref_39_M)+ new Integer(ref_49_M)+ new Integer(ref_50_M));
ref_T_F= ""+ (new Integer(ref_1_F)+ new Integer(ref_4_F)+ new Integer(ref_9_F)+ new Integer(ref_14_F)+ new Integer(ref_19_F)+ new Integer(ref_24_F)+ new Integer(ref_29_F)+ new Integer(ref_34_F)+ new Integer(ref_39_F)+ new Integer(ref_49_F)+ new Integer(ref_50_F));
tes_marps_T_M= ""+ (new Integer(tes_marps_1_M)+ new Integer(tes_marps_4_M)+ new Integer(tes_marps_9_M)+ new Integer(tes_marps_14_M)+ new Integer(tes_marps_19_M)+ new Integer(tes_marps_24_M)+ new Integer(tes_marps_29_M)+ new Integer(tes_marps_34_M)+ new Integer(tes_marps_39_M)+ new Integer(tes_marps_49_M)+ new Integer(tes_marps_50_M));
tes_marps_T_F= ""+ (new Integer(tes_marps_1_F)+ new Integer(tes_marps_4_F)+ new Integer(tes_marps_9_F)+ new Integer(tes_marps_14_F)+ new Integer(tes_marps_19_F)+ new Integer(tes_marps_24_F)+ new Integer(tes_marps_29_F)+ new Integer(tes_marps_34_F)+ new Integer(tes_marps_39_F)+ new Integer(tes_marps_49_F)+ new Integer(tes_marps_50_F));
pos_marps_T_M= ""+ (new Integer(pos_marps_1_M)+ new Integer(pos_marps_4_M)+ new Integer(pos_marps_9_M)+ new Integer(pos_marps_14_M)+ new Integer(pos_marps_19_M)+ new Integer(pos_marps_24_M)+ new Integer(pos_marps_29_M)+ new Integer(pos_marps_34_M)+ new Integer(pos_marps_39_M)+ new Integer(pos_marps_49_M)+ new Integer(pos_marps_50_M));
pos_marps_T_F= ""+ (new Integer(pos_marps_1_F)+ new Integer(pos_marps_4_F)+ new Integer(pos_marps_9_F)+ new Integer(pos_marps_14_F)+ new Integer(pos_marps_19_F)+ new Integer(pos_marps_24_F)+ new Integer(pos_marps_29_F)+ new Integer(pos_marps_34_F)+ new Integer(pos_marps_39_F)+ new Integer(pos_marps_49_F)+ new Integer(pos_marps_50_F));
//tes_coup_T_M= ""+ (new Integer(tes_coup_1_M)+ new Integer(tes_coup_4_M)+ new Integer(tes_coup_9_M)+ new Integer(tes_coup_14_M)+ new Integer(tes_coup_19_M)+ new Integer(tes_coup_24_M)+ new Integer(tes_coup_29_M)+ new Integer(tes_coup_34_M)+ new Integer(tes_coup_39_M)+ new Integer(tes_coup_49_M)+ new Integer(tes_coup_50_M));
//tes_coup_T_F= ""+ (new Integer(tes_coup_1_F)+ new Integer(tes_coup_4_F)+ new Integer(tes_coup_9_F)+ new Integer(tes_coup_14_F)+ new Integer(tes_coup_19_F)+ new Integer(tes_coup_24_F)+ new Integer(tes_coup_29_F)+ new Integer(tes_coup_34_F)+ new Integer(tes_coup_39_F)+ new Integer(tes_coup_49_F)+ new Integer(tes_coup_50_F));
//tes_coup_dis_T_M= ""+ (new Integer(tes_coup_dis_1_M)+ new Integer(tes_coup_dis_4_M)+ new Integer(tes_coup_dis_9_M)+ new Integer(tes_coup_dis_14_M)+ new Integer(tes_coup_dis_19_M)+ new Integer(tes_coup_dis_24_M)+ new Integer(tes_coup_dis_29_M)+ new Integer(tes_coup_dis_34_M)+ new Integer(tes_coup_dis_39_M)+ new Integer(tes_coup_dis_49_M)+ new Integer(tes_coup_dis_50_M));
//tes_coup_dis_T_F= ""+ (new Integer(tes_coup_dis_1_F)+ new Integer(tes_coup_dis_4_F)+ new Integer(tes_coup_dis_9_F)+ new Integer(tes_coup_dis_14_F)+ new Integer(tes_coup_dis_19_F)+ new Integer(tes_coup_dis_24_F)+ new Integer(tes_coup_dis_29_F)+ new Integer(tes_coup_dis_34_F)+ new Integer(tes_coup_dis_39_F)+ new Integer(tes_coup_dis_49_F)+ new Integer(tes_coup_dis_50_F));

 //_______________________________________________________________Gender Totals___________________________________________________
 
 
 //_______________________________________________________________Grand Totals___________________________________________________
 
 
tes_new_GT= ""+ (new Integer(tes_new_1_M)+ new Integer(tes_new_1_F)+ new Integer(tes_new_4_M)+ new Integer(tes_new_4_F)+ new Integer(tes_new_9_M)+ new Integer(tes_new_9_F)+ new Integer(tes_new_14_M)+ new Integer(tes_new_14_F)+ new Integer(tes_new_19_M)+ new Integer(tes_new_19_F)+ new Integer(tes_new_24_M)+ new Integer(tes_new_24_F)+ new Integer(tes_new_29_M)+ new Integer(tes_new_29_F)+ new Integer(tes_new_34_M)+ new Integer(tes_new_34_F)+ new Integer(tes_new_39_M)+ new Integer(tes_new_39_F)+ new Integer(tes_new_49_M)+ new Integer(tes_new_49_F)+ new Integer(tes_new_50_M)+ new Integer(tes_new_50_F));
res_GT= ""+ (new Integer(res_1_M)+ new Integer(res_1_F)+ new Integer(res_4_M)+ new Integer(res_4_F)+ new Integer(res_9_M)+ new Integer(res_9_F)+ new Integer(res_14_M)+ new Integer(res_14_F)+ new Integer(res_19_M)+ new Integer(res_19_F)+ new Integer(res_24_M)+ new Integer(res_24_F)+ new Integer(res_29_M)+ new Integer(res_29_F)+ new Integer(res_34_M)+ new Integer(res_34_F)+ new Integer(res_39_M)+ new Integer(res_39_F)+ new Integer(res_49_M)+ new Integer(res_49_F)+ new Integer(res_50_M)+ new Integer(res_50_F));
tes_new_res_GT= ""+ (new Integer(tes_new_res_1_M)+ new Integer(tes_new_res_1_F)+ new Integer(tes_new_res_4_M)+ new Integer(tes_new_res_4_F)+ new Integer(tes_new_res_9_M)+ new Integer(tes_new_res_9_F)+ new Integer(tes_new_res_14_M)+ new Integer(tes_new_res_14_F)+ new Integer(tes_new_res_19_M)+ new Integer(tes_new_res_19_F)+ new Integer(tes_new_res_24_M)+ new Integer(tes_new_res_24_F)+ new Integer(tes_new_res_29_M)+ new Integer(tes_new_res_29_F)+ new Integer(tes_new_res_34_M)+ new Integer(tes_new_res_34_F)+ new Integer(tes_new_res_39_M)+ new Integer(tes_new_res_39_F)+ new Integer(tes_new_res_49_M)+ new Integer(tes_new_res_49_F)+ new Integer(tes_new_res_50_M)+ new Integer(tes_new_res_50_F));
pos_GT= ""+ (new Integer(pos_1_M)+ new Integer(pos_1_F)+ new Integer(pos_4_M)+ new Integer(pos_4_F)+ new Integer(pos_9_M)+ new Integer(pos_9_F)+ new Integer(pos_14_M)+ new Integer(pos_14_F)+ new Integer(pos_19_M)+ new Integer(pos_19_F)+ new Integer(pos_24_M)+ new Integer(pos_24_F)+ new Integer(pos_29_M)+ new Integer(pos_29_F)+ new Integer(pos_34_M)+ new Integer(pos_34_F)+ new Integer(pos_39_M)+ new Integer(pos_39_F)+ new Integer(pos_49_M)+ new Integer(pos_49_F)+ new Integer(pos_50_M)+ new Integer(pos_50_F));
pos_tes_GT= ""+ (new Integer(pos_tes_1_M)+ new Integer(pos_tes_1_F)+ new Integer(pos_tes_4_M)+ new Integer(pos_tes_4_F)+ new Integer(pos_tes_9_M)+ new Integer(pos_tes_9_F)+ new Integer(pos_tes_14_M)+ new Integer(pos_tes_14_F)+ new Integer(pos_tes_19_M)+ new Integer(pos_tes_19_F)+ new Integer(pos_tes_24_M)+ new Integer(pos_tes_24_F)+ new Integer(pos_tes_29_M)+ new Integer(pos_tes_29_F)+ new Integer(pos_tes_34_M)+ new Integer(pos_tes_34_F)+ new Integer(pos_tes_39_M)+ new Integer(pos_tes_39_F)+ new Integer(pos_tes_49_M)+ new Integer(pos_tes_49_F)+ new Integer(pos_tes_50_M)+ new Integer(pos_tes_50_F));
ref_GT= ""+ (new Integer(ref_1_M)+ new Integer(ref_1_F)+ new Integer(ref_4_M)+ new Integer(ref_4_F)+ new Integer(ref_9_M)+ new Integer(ref_9_F)+ new Integer(ref_14_M)+ new Integer(ref_14_F)+ new Integer(ref_19_M)+ new Integer(ref_19_F)+ new Integer(ref_24_M)+ new Integer(ref_24_F)+ new Integer(ref_29_M)+ new Integer(ref_29_F)+ new Integer(ref_34_M)+ new Integer(ref_34_F)+ new Integer(ref_39_M)+ new Integer(ref_39_F)+ new Integer(ref_49_M)+ new Integer(ref_49_F)+ new Integer(ref_50_M)+ new Integer(ref_50_F));
tes_marps_GT= ""+ (new Integer(tes_marps_1_M)+ new Integer(tes_marps_1_F)+ new Integer(tes_marps_4_M)+ new Integer(tes_marps_4_F)+ new Integer(tes_marps_9_M)+ new Integer(tes_marps_9_F)+ new Integer(tes_marps_14_M)+ new Integer(tes_marps_14_F)+ new Integer(tes_marps_19_M)+ new Integer(tes_marps_19_F)+ new Integer(tes_marps_24_M)+ new Integer(tes_marps_24_F)+ new Integer(tes_marps_29_M)+ new Integer(tes_marps_29_F)+ new Integer(tes_marps_34_M)+ new Integer(tes_marps_34_F)+ new Integer(tes_marps_39_M)+ new Integer(tes_marps_39_F)+ new Integer(tes_marps_49_M)+ new Integer(tes_marps_49_F)+ new Integer(tes_marps_50_M)+ new Integer(tes_marps_50_F));
pos_marps_GT= ""+ (new Integer(pos_marps_1_M)+ new Integer(pos_marps_1_F)+ new Integer(pos_marps_4_M)+ new Integer(pos_marps_4_F)+ new Integer(pos_marps_9_M)+ new Integer(pos_marps_9_F)+ new Integer(pos_marps_14_M)+ new Integer(pos_marps_14_F)+ new Integer(pos_marps_19_M)+ new Integer(pos_marps_19_F)+ new Integer(pos_marps_24_M)+ new Integer(pos_marps_24_F)+ new Integer(pos_marps_29_M)+ new Integer(pos_marps_29_F)+ new Integer(pos_marps_34_M)+ new Integer(pos_marps_34_F)+ new Integer(pos_marps_39_M)+ new Integer(pos_marps_39_F)+ new Integer(pos_marps_49_M)+ new Integer(pos_marps_49_F)+ new Integer(pos_marps_50_M)+ new Integer(pos_marps_50_F));
//tes_coup_GT= ""+ (new Integer(tes_coup_1_M)+ new Integer(tes_coup_1_F)+ new Integer(tes_coup_4_M)+ new Integer(tes_coup_4_F)+ new Integer(tes_coup_9_M)+ new Integer(tes_coup_9_F)+ new Integer(tes_coup_14_M)+ new Integer(tes_coup_14_F)+ new Integer(tes_coup_19_M)+ new Integer(tes_coup_19_F)+ new Integer(tes_coup_24_M)+ new Integer(tes_coup_24_F)+ new Integer(tes_coup_29_M)+ new Integer(tes_coup_29_F)+ new Integer(tes_coup_34_M)+ new Integer(tes_coup_34_F)+ new Integer(tes_coup_39_M)+ new Integer(tes_coup_39_F)+ new Integer(tes_coup_49_M)+ new Integer(tes_coup_49_F)+ new Integer(tes_coup_50_M)+ new Integer(tes_coup_50_F));
//tes_coup_dis_GT= ""+ (new Integer(tes_coup_dis_1_M)+ new Integer(tes_coup_dis_1_F)+ new Integer(tes_coup_dis_4_M)+ new Integer(tes_coup_dis_4_F)+ new Integer(tes_coup_dis_9_M)+ new Integer(tes_coup_dis_9_F)+ new Integer(tes_coup_dis_14_M)+ new Integer(tes_coup_dis_14_F)+ new Integer(tes_coup_dis_19_M)+ new Integer(tes_coup_dis_19_F)+ new Integer(tes_coup_dis_24_M)+ new Integer(tes_coup_dis_24_F)+ new Integer(tes_coup_dis_29_M)+ new Integer(tes_coup_dis_29_F)+ new Integer(tes_coup_dis_34_M)+ new Integer(tes_coup_dis_34_F)+ new Integer(tes_coup_dis_39_M)+ new Integer(tes_coup_dis_39_F)+ new Integer(tes_coup_dis_49_M)+ new Integer(tes_coup_dis_49_F)+ new Integer(tes_coup_dis_50_M)+ new Integer(tes_coup_dis_50_F));

 //_______________________________________________________________Totals___________________________________________________
 
  
  
 String runvalidate="update hts set "
          
+ "couns_1_F='"+couns_1_F+"',"
+ "couns_4_M='"+couns_4_M+"',"
+ "couns_4_F='"+couns_4_F+"',"
+ "couns_9_M='"+couns_9_M+"',"
+ "couns_9_F='"+couns_9_F+"',"
+ "couns_14_M='"+couns_14_M+"',"
+ "couns_14_F='"+couns_14_F+"',"
+ "couns_19_M='"+couns_19_M+"',"
+ "couns_19_F='"+couns_19_F+"',"
+ "couns_24_M='"+couns_24_M+"',"
+ "couns_24_F='"+couns_24_F+"',"
+ "couns_29_M='"+couns_29_M+"',"
+ "couns_29_F='"+couns_29_F+"',"
+ "couns_34_M='"+couns_34_M+"',"
+ "couns_34_F='"+couns_34_F+"',"
+ "couns_39_M='"+couns_39_M+"',"
+ "couns_39_F='"+couns_39_F+"',"
+ "couns_49_M='"+couns_49_M+"',"
+ "couns_49_F='"+couns_49_F+"',"
+ "couns_50_M='"+couns_50_M+"',"
+ "couns_50_F='"+couns_50_F+"',"
+ "couns_T_M='"+couns_T_M+"',"
+ "couns_T_F='"+couns_T_F+"',"
+ "couns_GT='"+couns_GT+"',"
+ "tes_1_M='"+tes_1_M+"',"
+ "tes_1_F='"+tes_1_F+"',"
+ "tes_4_M='"+tes_4_M+"',"
+ "tes_4_F='"+tes_4_F+"',"
+ "tes_9_M='"+tes_9_M+"',"
+ "tes_9_F='"+tes_9_F+"',"
+ "tes_14_M='"+tes_14_M+"',"
+ "tes_14_F='"+tes_14_F+"',"
+ "tes_19_M='"+tes_19_M+"',"
+ "tes_19_F='"+tes_19_F+"',"
+ "tes_24_M='"+tes_24_M+"',"
+ "tes_24_F='"+tes_24_F+"',"
+ "tes_29_M='"+tes_29_M+"',"
+ "tes_29_F='"+tes_29_F+"',"
+ "tes_34_M='"+tes_34_M+"',"
+ "tes_34_F='"+tes_34_F+"',"
+ "tes_39_M='"+tes_39_M+"',"
+ "tes_39_F='"+tes_39_F+"',"
+ "tes_49_M='"+tes_49_M+"',"
+ "tes_49_F='"+tes_49_F+"',"
+ "tes_50_M='"+tes_50_M+"',"
+ "tes_50_F='"+tes_50_F+"',"
+ "tes_T_M='"+tes_T_M+"',"
+ "tes_T_F='"+tes_T_F+"',"
+ "tes_GT='"+tes_GT+"',"
+ "tes_new_1_M='"+tes_new_1_M+"',"
+ "tes_new_1_F='"+tes_new_1_F+"',"
+ "tes_new_4_M='"+tes_new_4_M+"',"
+ "tes_new_4_F='"+tes_new_4_F+"',"
+ "tes_new_9_M='"+tes_new_9_M+"',"
+ "tes_new_9_F='"+tes_new_9_F+"',"
+ "tes_new_14_M='"+tes_new_14_M+"',"
+ "tes_new_14_F='"+tes_new_14_F+"',"
+ "tes_new_19_M='"+tes_new_19_M+"',"
+ "tes_new_19_F='"+tes_new_19_F+"',"
+ "tes_new_24_M='"+tes_new_24_M+"',"
+ "tes_new_24_F='"+tes_new_24_F+"',"
+ "tes_new_29_M='"+tes_new_29_M+"',"
+ "tes_new_29_F='"+tes_new_29_F+"',"
+ "tes_new_34_M='"+tes_new_34_M+"',"
+ "tes_new_34_F='"+tes_new_34_F+"',"
+ "tes_new_39_M='"+tes_new_39_M+"',"
+ "tes_new_39_F='"+tes_new_39_F+"',"
+ "tes_new_49_M='"+tes_new_49_M+"',"
+ "tes_new_49_F='"+tes_new_49_F+"',"
+ "tes_new_50_M='"+tes_new_50_M+"',"
+ "tes_new_50_F='"+tes_new_50_F+"',"
+ "tes_new_T_M='"+tes_new_T_M+"',"
+ "tes_new_T_F='"+tes_new_T_F+"',"
+ "tes_new_GT='"+tes_new_GT+"',"
+ "res_1_M='"+res_1_M+"',"
+ "res_1_F='"+res_1_F+"',"
+ "res_4_M='"+res_4_M+"',"
+ "res_4_F='"+res_4_F+"',"
+ "res_9_M='"+res_9_M+"',"
+ "res_9_F='"+res_9_F+"',"
+ "res_14_M='"+res_14_M+"',"
+ "res_14_F='"+res_14_F+"',"
+ "res_19_M='"+res_19_M+"',"
+ "res_19_F='"+res_19_F+"',"
+ "res_24_M='"+res_24_M+"',"
+ "res_24_F='"+res_24_F+"',"
+ "res_29_M='"+res_29_M+"',"
+ "res_29_F='"+res_29_F+"',"
+ "res_34_M='"+res_34_M+"',"
+ "res_34_F='"+res_34_F+"',"
+ "res_39_M='"+res_39_M+"',"
+ "res_39_F='"+res_39_F+"',"
+ "res_49_M='"+res_49_M+"',"
+ "res_49_F='"+res_49_F+"',"
+ "res_50_M='"+res_50_M+"',"
+ "res_50_F='"+res_50_F+"',"
+ "res_T_M='"+res_T_M+"',"
+ "res_T_F='"+res_T_F+"',"
+ "res_GT='"+res_GT+"',"
+ "tes_new_res_1_M='"+tes_new_res_1_M+"',"
+ "tes_new_res_1_F='"+tes_new_res_1_F+"',"
+ "tes_new_res_4_M='"+tes_new_res_4_M+"',"
+ "tes_new_res_4_F='"+tes_new_res_4_F+"',"
+ "tes_new_res_9_M='"+tes_new_res_9_M+"',"
+ "tes_new_res_9_F='"+tes_new_res_9_F+"',"
+ "tes_new_res_14_M='"+tes_new_res_14_M+"',"
+ "tes_new_res_14_F='"+tes_new_res_14_F+"',"
+ "tes_new_res_19_M='"+tes_new_res_19_M+"',"
+ "tes_new_res_19_F='"+tes_new_res_19_F+"',"
+ "tes_new_res_24_M='"+tes_new_res_24_M+"',"
+ "tes_new_res_24_F='"+tes_new_res_24_F+"',"
+ "tes_new_res_29_M='"+tes_new_res_29_M+"',"
+ "tes_new_res_29_F='"+tes_new_res_29_F+"',"
+ "tes_new_res_34_M='"+tes_new_res_34_M+"',"
+ "tes_new_res_34_F='"+tes_new_res_34_F+"',"
+ "tes_new_res_39_M='"+tes_new_res_39_M+"',"
+ "tes_new_res_39_F='"+tes_new_res_39_F+"',"
+ "tes_new_res_49_M='"+tes_new_res_49_M+"',"
+ "tes_new_res_49_F='"+tes_new_res_49_F+"',"
+ "tes_new_res_50_M='"+tes_new_res_50_M+"',"
+ "tes_new_res_50_F='"+tes_new_res_50_F+"',"
+ "tes_new_res_T_M='"+tes_new_res_T_M+"',"
+ "tes_new_res_T_F='"+tes_new_res_T_F+"',"
+ "tes_new_res_GT='"+tes_new_res_GT+"',"
+ "pos_1_M='"+pos_1_M+"',"
+ "pos_1_F='"+pos_1_F+"',"
+ "pos_4_M='"+pos_4_M+"',"
+ "pos_4_F='"+pos_4_F+"',"
+ "pos_9_M='"+pos_9_M+"',"
+ "pos_9_F='"+pos_9_F+"',"
+ "pos_14_M='"+pos_14_M+"',"
+ "pos_14_F='"+pos_14_F+"',"
+ "pos_19_M='"+pos_19_M+"',"
+ "pos_19_F='"+pos_19_F+"',"
+ "pos_24_M='"+pos_24_M+"',"
+ "pos_24_F='"+pos_24_F+"',"
+ "pos_29_M='"+pos_29_M+"',"
+ "pos_29_F='"+pos_29_F+"',"
+ "pos_34_M='"+pos_34_M+"',"
+ "pos_34_F='"+pos_34_F+"',"
+ "pos_39_M='"+pos_39_M+"',"
+ "pos_39_F='"+pos_39_F+"',"
+ "pos_49_M='"+pos_49_M+"',"
+ "pos_49_F='"+pos_49_F+"',"
+ "pos_50_M='"+pos_50_M+"',"
+ "pos_50_F='"+pos_50_F+"',"
+ "pos_T_M='"+pos_T_M+"',"
+ "pos_T_F='"+pos_T_F+"',"
+ "pos_GT='"+pos_GT+"',"
+ "pos_tes_1_M='"+pos_tes_1_M+"',"
+ "pos_tes_1_F='"+pos_tes_1_F+"',"
+ "pos_tes_4_M='"+pos_tes_4_M+"',"
+ "pos_tes_4_F='"+pos_tes_4_F+"',"
+ "pos_tes_9_M='"+pos_tes_9_M+"',"
+ "pos_tes_9_F='"+pos_tes_9_F+"',"
+ "pos_tes_14_M='"+pos_tes_14_M+"',"
+ "pos_tes_14_F='"+pos_tes_14_F+"',"
+ "pos_tes_19_M='"+pos_tes_19_M+"',"
+ "pos_tes_19_F='"+pos_tes_19_F+"',"
+ "pos_tes_24_M='"+pos_tes_24_M+"',"
+ "pos_tes_24_F='"+pos_tes_24_F+"',"
+ "pos_tes_29_M='"+pos_tes_29_M+"',"
+ "pos_tes_29_F='"+pos_tes_29_F+"',"
+ "pos_tes_34_M='"+pos_tes_34_M+"',"
+ "pos_tes_34_F='"+pos_tes_34_F+"',"
+ "pos_tes_39_M='"+pos_tes_39_M+"',"
+ "pos_tes_39_F='"+pos_tes_39_F+"',"
+ "pos_tes_49_M='"+pos_tes_49_M+"',"
+ "pos_tes_49_F='"+pos_tes_49_F+"',"
+ "pos_tes_50_M='"+pos_tes_50_M+"',"
+ "pos_tes_50_F='"+pos_tes_50_F+"',"
+ "pos_tes_T_M='"+pos_tes_T_M+"',"
+ "pos_tes_T_F='"+pos_tes_T_F+"',"
+ "pos_tes_GT='"+pos_tes_GT+"',"
+ "ref_1_M='"+ref_1_M+"',"
+ "ref_1_F='"+ref_1_F+"',"
+ "ref_4_M='"+ref_4_M+"',"
+ "ref_4_F='"+ref_4_F+"',"
+ "ref_9_M='"+ref_9_M+"',"
+ "ref_9_F='"+ref_9_F+"',"
+ "ref_14_M='"+ref_14_M+"',"
+ "ref_14_F='"+ref_14_F+"',"
+ "ref_19_M='"+ref_19_M+"',"
+ "ref_19_F='"+ref_19_F+"',"
+ "ref_24_M='"+ref_24_M+"',"
+ "ref_24_F='"+ref_24_F+"',"
+ "ref_29_M='"+ref_29_M+"',"
+ "ref_29_F='"+ref_29_F+"',"
+ "ref_34_M='"+ref_34_M+"',"
+ "ref_34_F='"+ref_34_F+"',"
+ "ref_39_M='"+ref_39_M+"',"
+ "ref_39_F='"+ref_39_F+"',"
+ "ref_49_M='"+ref_49_M+"',"
+ "ref_49_F='"+ref_49_F+"',"
+ "ref_50_M='"+ref_50_M+"',"
+ "ref_50_F='"+ref_50_F+"',"
+ "ref_T_M='"+ref_T_M+"',"
+ "ref_T_F='"+ref_T_F+"',"
+ "ref_GT='"+ref_GT+"',"
+ "tes_marps_1_M='"+tes_marps_1_M+"',"
+ "tes_marps_1_F='"+tes_marps_1_F+"',"
+ "tes_marps_4_M='"+tes_marps_4_M+"',"
+ "tes_marps_4_F='"+tes_marps_4_F+"',"
+ "tes_marps_9_M='"+tes_marps_9_M+"',"
+ "tes_marps_9_F='"+tes_marps_9_F+"',"
+ "tes_marps_14_M='"+tes_marps_14_M+"',"
+ "tes_marps_14_F='"+tes_marps_14_F+"',"
+ "tes_marps_19_M='"+tes_marps_19_M+"',"
+ "tes_marps_19_F='"+tes_marps_19_F+"',"
+ "tes_marps_24_M='"+tes_marps_24_M+"',"
+ "tes_marps_24_F='"+tes_marps_24_F+"',"
+ "tes_marps_29_M='"+tes_marps_29_M+"',"
+ "tes_marps_29_F='"+tes_marps_29_F+"',"
+ "tes_marps_34_M='"+tes_marps_34_M+"',"
+ "tes_marps_34_F='"+tes_marps_34_F+"',"
+ "tes_marps_39_M='"+tes_marps_39_M+"',"
+ "tes_marps_39_F='"+tes_marps_39_F+"',"
+ "tes_marps_49_M='"+tes_marps_49_M+"',"
+ "tes_marps_49_F='"+tes_marps_49_F+"',"
+ "tes_marps_50_M='"+tes_marps_50_M+"',"
+ "tes_marps_50_F='"+tes_marps_50_F+"',"
+ "tes_marps_T_M='"+tes_marps_T_M+"',"
+ "tes_marps_T_F='"+tes_marps_T_F+"',"
+ "tes_marps_GT='"+tes_marps_GT+"',"
+ "pos_marps_1_M='"+pos_marps_1_M+"',"
+ "pos_marps_1_F='"+pos_marps_1_F+"',"
+ "pos_marps_4_M='"+pos_marps_4_M+"',"
+ "pos_marps_4_F='"+pos_marps_4_F+"',"
+ "pos_marps_9_M='"+pos_marps_9_M+"',"
+ "pos_marps_9_F='"+pos_marps_9_F+"',"
+ "pos_marps_14_M='"+pos_marps_14_M+"',"
+ "pos_marps_14_F='"+pos_marps_14_F+"',"
+ "pos_marps_19_M='"+pos_marps_19_M+"',"
+ "pos_marps_19_F='"+pos_marps_19_F+"',"
+ "pos_marps_24_M='"+pos_marps_24_M+"',"
+ "pos_marps_24_F='"+pos_marps_24_F+"',"
+ "pos_marps_29_M='"+pos_marps_29_M+"',"
+ "pos_marps_29_F='"+pos_marps_29_F+"',"
+ "pos_marps_34_M='"+pos_marps_34_M+"',"
+ "pos_marps_34_F='"+pos_marps_34_F+"',"
+ "pos_marps_39_M='"+pos_marps_39_M+"',"
+ "pos_marps_39_F='"+pos_marps_39_F+"',"
+ "pos_marps_49_M='"+pos_marps_49_M+"',"
+ "pos_marps_49_F='"+pos_marps_49_F+"',"
+ "pos_marps_50_M='"+pos_marps_50_M+"',"
+ "pos_marps_50_F='"+pos_marps_50_F+"',"
+ "pos_marps_T_M='"+pos_marps_T_M+"',"
+ "pos_marps_T_F='"+pos_marps_T_F+"',"
+ "pos_marps_GT='"+pos_marps_GT+"',"
+ "tes_coup_1_M='"+tes_coup_1_M+"',"
+ "tes_coup_1_F='"+tes_coup_1_F+"',"
+ "tes_coup_4_M='"+tes_coup_4_M+"',"
+ "tes_coup_4_F='"+tes_coup_4_F+"',"
+ "tes_coup_9_M='"+tes_coup_9_M+"',"
+ "tes_coup_9_F='"+tes_coup_9_F+"',"
+ "tes_coup_14_M='"+tes_coup_14_M+"',"
+ "tes_coup_14_F='"+tes_coup_14_F+"',"
+ "tes_coup_19_M='"+tes_coup_19_M+"',"
+ "tes_coup_19_F='"+tes_coup_19_F+"',"
+ "tes_coup_24_M='"+tes_coup_24_M+"',"
+ "tes_coup_24_F='"+tes_coup_24_F+"',"
+ "tes_coup_29_M='"+tes_coup_29_M+"',"
+ "tes_coup_29_F='"+tes_coup_29_F+"',"
+ "tes_coup_34_M='"+tes_coup_34_M+"',"
+ "tes_coup_34_F='"+tes_coup_34_F+"',"
+ "tes_coup_39_M='"+tes_coup_39_M+"',"
+ "tes_coup_39_F='"+tes_coup_39_F+"',"
+ "tes_coup_49_M='"+tes_coup_49_M+"',"
+ "tes_coup_49_F='"+tes_coup_49_F+"',"
+ "tes_coup_50_M='"+tes_coup_50_M+"',"
+ "tes_coup_50_F='"+tes_coup_50_F+"',"
+ "tes_coup_T_M='"+tes_coup_T_M+"',"
+ "tes_coup_T_F='"+tes_coup_T_F+"',"
+ "tes_coup_GT='"+tes_coup_GT+"',"
+ "tes_coup_dis_1_M='"+tes_coup_dis_1_M+"',"
+ "tes_coup_dis_1_F='"+tes_coup_dis_1_F+"',"
+ "tes_coup_dis_4_M='"+tes_coup_dis_4_M+"',"
+ "tes_coup_dis_4_F='"+tes_coup_dis_4_F+"',"
+ "tes_coup_dis_9_M='"+tes_coup_dis_9_M+"',"
+ "tes_coup_dis_9_F='"+tes_coup_dis_9_F+"',"
+ "tes_coup_dis_14_M='"+tes_coup_dis_14_M+"',"
+ "tes_coup_dis_14_F='"+tes_coup_dis_14_F+"',"
+ "tes_coup_dis_19_M='"+tes_coup_dis_19_M+"',"
+ "tes_coup_dis_19_F='"+tes_coup_dis_19_F+"',"
+ "tes_coup_dis_24_M='"+tes_coup_dis_24_M+"',"
+ "tes_coup_dis_24_F='"+tes_coup_dis_24_F+"',"
+ "tes_coup_dis_29_M='"+tes_coup_dis_29_M+"',"
+ "tes_coup_dis_29_F='"+tes_coup_dis_29_F+"',"
+ "tes_coup_dis_34_M='"+tes_coup_dis_34_M+"',"
+ "tes_coup_dis_34_F='"+tes_coup_dis_34_F+"',"
+ "tes_coup_dis_39_M='"+tes_coup_dis_39_M+"',"
+ "tes_coup_dis_39_F='"+tes_coup_dis_39_F+"',"
+ "tes_coup_dis_49_M='"+tes_coup_dis_49_M+"',"
+ "tes_coup_dis_49_F='"+tes_coup_dis_49_F+"',"
+ "tes_coup_dis_50_M='"+tes_coup_dis_50_M+"',"
+ "tes_coup_dis_50_F='"+tes_coup_dis_50_F+"',"
+ "tes_coup_dis_T_M='"+tes_coup_dis_T_M+"',"
+ "tes_coup_dis_T_F='"+tes_coup_dis_T_F+"',"
+ "tes_coup_dis_GT='"+tes_coup_dis_GT+"',"
+ "tk_negative1='"+tk_negative1+"',"
+ "tk_negative2='"+tk_negative2+"',"
+ "tk_positive1='"+tk_positive1+"',"
+ "tk_positive2='"+tk_positive2+"',"
+ "tk_invalid1='"+tk_invalid1+"',"
+ "tk_invalid2='"+tk_invalid2+"',"
+ "tk_wastage1='"+tk_wastage1+"',"
+ "tk_wastage2='"+tk_wastage2+"',"
+ "tk_total1='"+tk_total1+"',"
+ "tk_total2='"+tk_total2+"',"


+ "isValidated='1',sdp='"+sdp+"' where tableid='"+tableid+"'";        
System.out.println(runvalidate);  
   conn.st.executeUpdate(runvalidate);
    session.setAttribute("validatehts", "<font color=\"green\"><b>Form HTS Validated Successfully.</b></font>");
  
    
     int monthDiff=0;
     String getMonths="SELECT TIMESTAMPDIFF(MONTH, timestamp, now()) FROM hts WHERE tableid='"+tableid+"'";
     conn.rs1=conn.st1.executeQuery(getMonths);
     if(conn.rs1.next()==true){
         System.out.println("months are : "+conn.rs1.getString(1));
    monthDiff=conn.rs1.getInt(1);
     }
     if(monthDiff>0){
//      UPDATE THE COLUMN   
       java.util.Date date= new java.util.Date();
Timestamp lastUpdatedOn =new Timestamp(date.getTime()); 

     String updateLast="UPDATE hts SET updatedBy='"+userid+"', updatedOn='"+lastUpdatedOn+"' WHERE tableid='"+tableid+"'" ;   
       conn.st2.executeUpdate(updateLast);
     }
     
      pullHts hts= new pullHts();
    hts.hts_non731(mwakamwezi,mwakamwezi,facil); //stored procedure code 
   // hts.hts731( yearmonth, yearmonth, facilityId);
     
     
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
        
   
        response.sendRedirect("loadhts.jsp");
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
