/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalSystem;

import database.dbConn;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GNyabuto
 */
public class validate733B extends HttpServlet {
HttpSession session;
String CS_New_Pos_Male_Adult, CS_New_Pos_Male_15_17, CS_New_Pos_Male_Postnatal, CS_New_Pos_Male_0_59M, 
        CS_New_Pos_Male_5_15, CS_New_Pos_Female_Adult, CS_New_Pos_Female_15_17, CS_New_Pos_Female_Postnatal, 
        CS_New_Pos_Female_0_59M, CS_New_Pos_Female_5_15, CS_New_Pos_Readmission, CS_New_Pos_Relapse, 
        CS_New_Pos_LinkedOVC, CS_New_Neg_Male_Adult, CS_New_Neg_Male_15_17, CS_New_Neg_Male_Postnatal, 
        CS_New_Neg_Male_0_59M, CS_New_Neg_Male_5_15, CS_New_Neg_Female_Adult, CS_New_Neg_Female_15_17, 
        CS_New_Neg_Female_Postnatal, CS_New_Neg_Female_0_59M, CS_New_Neg_Female_5_15, CS_New_Neg_Readmission, 
        CS_New_Neg_Relapse, CS_New_Neg_LinkedOVC, CS_Revisit_Pos_Adult, CS_Revisit_Pos_15_17, 
        CS_Revisit_Pos_Postnatal, CS_Revisit_Pos_0_59M, CS_Revisit_Pos_5_15, CS_Revisit_Pos_Readmission, 
        CS_Revisit_Pos_Relapse, CS_Revisit_Pos_Linked_OVC, CS_Revisit_Neg_Adult, CS_Revisit_Neg_15_17, 
        CS_Revisit_Neg_Postnatal, CS_Revisit_Neg_0_59M, CS_Revisit_Neg_5_15, CS_Revisit_Neg_Readmission, 
        CS_Revisit_Neg_Relapse, CS_Revisit_Neg_LinkedOVC, CS_Total_Adult, CS_Total_15_17, 
        CS_Total_Postnatal, CS_Total_0_59M, CS_Total_5_15, CS_Total_Readmission, 
        CS_Total_Relapse, CS_Total_LinkedOVC, 
        
        NH1_Pos_SAM_Adult, NH1_Pos_SAM_15_17, 
        NH1_Pos_SAM_Postnatal, NH1_Pos_SAM_0_59M, NH1_Pos_SAM_5_15, NH1_Pos_SAM_Total, NH1_Pos_MAM_Adult, 
        NH1_Pos_MAM_15_17, NH1_Pos_MAM_Postnatal, NH1_Pos_MAM_0_59M, NH1_Pos_MAM_5_15, NH1_Pos_MAM_Total, 
        NH1_Neg_SAM_Adult, NH1_Neg_SAM_15_17, NH1_Neg_SAM_Postnatal, NH1_Neg_SAM_0_59M, NH1_Neg_SAM_5_15, 
        NH1_Neg_SAM_Total, NH1_Neg_MAM_Adult, NH1_Neg_MAM_15_17, NH1_Neg_MAM_Postnatal, NH1_Neg_MAM_0_59M, 
        NH1_Neg_MAM_5_15, NH1_Neg_MAM_Total, NH1_Total_Adult, NH1_Total_15_17, NH1_Total_Postnatal, NH1_Total_0_59M, 
        NH1_Total_5_15, NH1_Grand_Total, 
        
        IFP_0_6_EBF_Pos, IFP_0_6_EBF_Neg, 
        IFP_0_6_ERF_Pos, IFP_0_6_ERF_Neg, IFP_0_6_MF_Pos, IFP_0_6_MF_Neg, IFP_0_6_SubTotal_Pos, IFP_0_6_SubTotal_Neg, IFP_6_12_BF_Pos, 
        IFP_6_12_BF_Neg, IFP_6_12_NBF_Pos,IFP_6_12_NBF_Neg, IFP_6_12_NotKnown_Pos, IFP_6_12_NotKnown_Neg, IFP_6_12_BCF_Pos, IFP_6_12_BCF_Neg, 
        IFP_6_12_SubTotal_Pos, IFP_6_12_SubTotal_Neg,
        
        NH2_TCM_Adults15_17_Pos, 
        NH2_TCM_Postnatal_Pos, NH2_TCM_AllAdults_Neg, NH2_TCM_0_59M_Pos, NH2_TCM_5_15_Pos, NH2_TCM_0_59M_Neg, NH2_TCM_5_15_Neg,
        NH2_TCM_Total, NH2_RUTF_Adults15_17_Pos, NH2_RUTF_Postnatal_Pos, NH2_RUTF_AllAdults_Neg, NH2_RUTF_0_59M_Pos,
        NH2_RUTF_5_15_Pos, NH2_RUTF_0_59M_Neg, NH2_RUTF_5_15_Neg, NH2_RUTF_Total, NH2_RUSF_Adults15_17_Pos, NH2_RUSF_Postnatal_Pos, 
        NH2_RUSF_AllAdults_Neg, NH2_RUSF_0_59M_Pos, NH2_RUSF_5_15_Pos, NH2_RUSF_0_59M_Neg, NH2_RUSF_5_15_Neg, NH2_RUSF_Total, 
        NH2_FBFCSB_Adults15_17_Pos, NH2_FBFCSB_Postnatal_Pos, NH2_FBFCSB_AllAdults_Neg, NH2_FBFCSB_0_59M_Pos, NH2_FBFCSB_5_15_Pos, 
        NH2_FBFCSB_0_59M_Neg, NH2_FBFCSB_5_15_Neg, NH2_FBFCSB_Total, NH2_LNS_Adults15_17_Pos, NH2_LNS_Postnatal_Pos, NH2_LNS_AllAdults_Neg, 
        NH2_LNS_0_59M_Pos, NH2_LNS_5_15_Pos, NH2_LNS_0_59M_Neg, NH2_LNS_5_15_Neg, NH2_LNS_Total, NH2_Micronutrients_Adults15_17_Pos, 
        NH2_Micronutrients_Postnatal_Pos, NH2_Micronutrients_AllAdults_Neg, NH2_Micronutrients_0_59M_Pos, NH2_Micronutrients_5_15_Pos, 
        NH2_Micronutrients_0_59M_Neg, NH2_Micronutrients_5_15_Neg, NH2_Micronutrients_Total, NH2_Others_Adults15_17_Pos, NH2_Others_Postnatal_Pos, 
        NH2_Others_AllAdults_Neg, NH2_Others_0_59M_Pos, NH2_Others_5_15_Pos, NH2_Others_0_59M_Neg, NH2_Others_5_15_Neg, NH2_Others_Total, 
        NH2_Total_Adults15_17_Pos, NH2_Total_Postnatal_Pos, NH2_Total_AllAdults_Neg, NH2_Total_0_59M_Pos, NH2_Total_5_15_Pos, NH2_Total_0_59M_Neg, 
        NH2_Total_5_15_Neg, NH2_Grand_Total, 
        
        
        LTF_Adult_15_17, LTF_Pregnant, LTF_Postnatal,LTF_0_59M, LTF_5_15, 
        
        RM_Adult_15_17, RM_Pregnant, RM_Postnatal, RM_0_59M, RM_5_15,reporting_officer,designation,reporting_date;

int CS_New_Pos_Male_Adult_1, CS_New_Pos_Male_15_17_1, CS_New_Pos_Male_Postnatal_1, CS_New_Pos_Male_0_59M_1, 
        CS_New_Pos_Male_5_15_1, CS_New_Pos_Female_Adult_1, CS_New_Pos_Female_15_17_1, CS_New_Pos_Female_Postnatal_1, 
        CS_New_Pos_Female_0_59M_1, CS_New_Pos_Female_5_15_1, CS_New_Pos_Readmission_1, CS_New_Pos_Relapse_1, 
        CS_New_Pos_LinkedOVC_1, CS_New_Neg_Male_Adult_1, CS_New_Neg_Male_15_17_1, CS_New_Neg_Male_Postnatal_1, 
        CS_New_Neg_Male_0_59M_1, CS_New_Neg_Male_5_15_1, CS_New_Neg_Female_Adult_1, CS_New_Neg_Female_15_17_1, 
        CS_New_Neg_Female_Postnatal_1, CS_New_Neg_Female_0_59M_1, CS_New_Neg_Female_5_15_1, CS_New_Neg_Readmission_1, 
        CS_New_Neg_Relapse_1, CS_New_Neg_LinkedOVC_1, CS_Revisit_Pos_Adult_1, CS_Revisit_Pos_15_17_1, 
        CS_Revisit_Pos_Postnatal_1, CS_Revisit_Pos_0_59M_1, CS_Revisit_Pos_5_15_1, CS_Revisit_Pos_Readmission_1, 
        CS_Revisit_Pos_Relapse_1, CS_Revisit_Pos_Linked_OVC_1, CS_Revisit_Neg_Adult_1, CS_Revisit_Neg_15_17_1, 
        CS_Revisit_Neg_Postnatal_1, CS_Revisit_Neg_0_59M_1, CS_Revisit_Neg_5_15_1, CS_Revisit_Neg_Readmission_1, 
        CS_Revisit_Neg_Relapse_1, CS_Revisit_Neg_LinkedOVC_1, CS_Total_Adult_1, CS_Total_15_17_1, 
        CS_Total_Postnatal_1, CS_Total_0_59M_1, CS_Total_5_15_1, CS_Total_Readmission_1, 
        CS_Total_Relapse_1, CS_Total_LinkedOVC_1, 
        
        NH1_Pos_SAM_Adult_1, NH1_Pos_SAM_15_17_1, 
        NH1_Pos_SAM_Postnatal_1, NH1_Pos_SAM_0_59M_1, NH1_Pos_SAM_5_15_1, NH1_Pos_SAM_Total_1, NH1_Pos_MAM_Adult_1, 
        NH1_Pos_MAM_15_17_1, NH1_Pos_MAM_Postnatal_1, NH1_Pos_MAM_0_59M_1, NH1_Pos_MAM_5_15_1, NH1_Pos_MAM_Total_1, 
        NH1_Neg_SAM_Adult_1, NH1_Neg_SAM_15_17_1, NH1_Neg_SAM_Postnatal_1, NH1_Neg_SAM_0_59M_1, NH1_Neg_SAM_5_15_1, 
        NH1_Neg_SAM_Total_1, NH1_Neg_MAM_Adult_1, NH1_Neg_MAM_15_17_1, NH1_Neg_MAM_Postnatal_1, NH1_Neg_MAM_0_59M_1, 
        NH1_Neg_MAM_5_15_1, NH1_Neg_MAM_Total_1, NH1_Total_Adult_1, NH1_Total_15_17_1, NH1_Total_Postnatal_1, NH1_Total_0_59M_1, 
        NH1_Total_5_15_1, NH1_Grand_Total_1, 
        
        IFP_0_6_EBF_Pos_1, IFP_0_6_EBF_Neg_1, 
        IFP_0_6_ERF_Pos_1, IFP_0_6_ERF_Neg_1, IFP_0_6_MF_Pos_1, IFP_0_6_MF_Neg_1, IFP_0_6_SubTotal_Pos_1, IFP_0_6_SubTotal_Neg_1, IFP_6_12_BF_Pos_1, 
        IFP_6_12_BF_Neg_1, IFP_6_12_NBF_Pos_1,IFP_6_12_NBF_Neg_1, IFP_6_12_NotKnown_Pos_1, IFP_6_12_NotKnown_Neg_1, IFP_6_12_BCF_Pos_1, IFP_6_12_BCF_Neg_1, 
        IFP_6_12_SubTotal_Pos_1, IFP_6_12_SubTotal_Neg_1,
        
        NH2_TCM_Adults15_17_Pos_1, 
        NH2_TCM_Postnatal_Pos_1, NH2_TCM_AllAdults_Neg_1, NH2_TCM_0_59M_Pos_1, NH2_TCM_5_15_Pos_1, NH2_TCM_0_59M_Neg_1, NH2_TCM_5_15_Neg_1,
        NH2_TCM_Total_1, NH2_RUTF_Adults15_17_Pos_1, NH2_RUTF_Postnatal_Pos_1, NH2_RUTF_AllAdults_Neg_1, NH2_RUTF_0_59M_Pos_1,
        NH2_RUTF_5_15_Pos_1, NH2_RUTF_0_59M_Neg_1, NH2_RUTF_5_15_Neg_1, NH2_RUTF_Total_1, NH2_RUSF_Adults15_17_Pos_1, NH2_RUSF_Postnatal_Pos_1, 
        NH2_RUSF_AllAdults_Neg_1, NH2_RUSF_0_59M_Pos_1, NH2_RUSF_5_15_Pos_1, NH2_RUSF_0_59M_Neg_1, NH2_RUSF_5_15_Neg_1, NH2_RUSF_Total_1, 
        NH2_FBFCSB_Adults15_17_Pos_1, NH2_FBFCSB_Postnatal_Pos_1, NH2_FBFCSB_AllAdults_Neg_1, NH2_FBFCSB_0_59M_Pos_1, NH2_FBFCSB_5_15_Pos_1, 
        NH2_FBFCSB_0_59M_Neg_1, NH2_FBFCSB_5_15_Neg_1, NH2_FBFCSB_Total_1, NH2_LNS_Adults15_17_Pos_1, NH2_LNS_Postnatal_Pos_1, NH2_LNS_AllAdults_Neg_1, 
        NH2_LNS_0_59M_Pos_1, NH2_LNS_5_15_Pos_1, NH2_LNS_0_59M_Neg_1, NH2_LNS_5_15_Neg_1, NH2_LNS_Total_1, NH2_Micronutrients_Adults15_17_Pos_1, 
        NH2_Micronutrients_Postnatal_Pos_1, NH2_Micronutrients_AllAdults_Neg_1, NH2_Micronutrients_0_59M_Pos_1, NH2_Micronutrients_5_15_Pos_1, 
        NH2_Micronutrients_0_59M_Neg_1, NH2_Micronutrients_5_15_Neg_1, NH2_Micronutrients_Total_1, NH2_Others_Adults15_17_Pos_1, NH2_Others_Postnatal_Pos_1, 
        NH2_Others_AllAdults_Neg_1, NH2_Others_0_59M_Pos_1, NH2_Others_5_15_Pos_1, NH2_Others_0_59M_Neg_1, NH2_Others_5_15_Neg_1, NH2_Others_Total_1, 
        NH2_Total_Adults15_17_Pos_1, NH2_Total_Postnatal_Pos_1, NH2_Total_AllAdults_Neg_1, NH2_Total_0_59M_Pos_1, NH2_Total_5_15_Pos_1, NH2_Total_0_59M_Neg_1, 
        NH2_Total_5_15_Neg_1, NH2_Grand_Total_1, 
        
        LTF_Adult_15_17_1, LTF_Pregnant_1, LTF_Postnatal_1,LTF_0_59M_1, LTF_5_15_1, 
        
        RM_Adult_15_17_1, RM_Pregnant_1, RM_Postnatal_1, RM_0_59M_1, RM_5_15_1;



    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
      session = request.getSession();
      dbConn conn = new dbConn();
      
String data,id = "";
String facilityId,year,month;
year=month=facilityId="";
    
    if(session.getAttribute("year")!=null){        
   year=session.getAttribute("year").toString();
    }
      if(session.getAttribute("monthid")!=null){        
   month=session.getAttribute("monthid").toString();
    }
   
        if(session.getAttribute("facilityid")!=null){        
   facilityId=session.getAttribute("facilityid").toString();
    }
       
    id=year+"_"+month+"_"+facilityId;    
    
        IFP_0_6_EBF_Pos = IFP_0_6_EBF_Neg= 
        IFP_0_6_ERF_Pos= IFP_0_6_ERF_Neg= IFP_0_6_MF_Pos= IFP_0_6_MF_Neg= IFP_0_6_SubTotal_Pos= IFP_0_6_SubTotal_Neg= IFP_6_12_BF_Pos= 
        IFP_6_12_BF_Neg= IFP_6_12_NBF_Pos=IFP_6_12_NBF_Neg= IFP_6_12_NotKnown_Pos= IFP_6_12_NotKnown_Neg= IFP_6_12_BCF_Pos= IFP_6_12_BCF_Neg= 
        IFP_6_12_SubTotal_Pos= IFP_6_12_SubTotal_Neg=null;
        
        if(request.getParameter("CS_New_Pos_Male_Adult").equals("")) {CS_New_Pos_Male_Adult  = null; } else{CS_New_Pos_Male_Adult  = request.getParameter("CS_New_Pos_Male_Adult"); }
        if(request.getParameter("CS_New_Pos_Male_15_17").equals("")) {CS_New_Pos_Male_15_17  = null;} else{CS_New_Pos_Male_15_17  = request.getParameter("CS_New_Pos_Male_15_17"); }
        if(request.getParameter("CS_New_Pos_Male_Postnatal").equals("")) {CS_New_Pos_Male_Postnatal  = null;} else{CS_New_Pos_Male_Postnatal  = request.getParameter("CS_New_Pos_Male_Postnatal"); }
        if(request.getParameter("CS_New_Pos_Male_0_59M").equals("")) { CS_New_Pos_Male_0_59M=null;} else{CS_New_Pos_Male_0_59M  = request.getParameter("CS_New_Pos_Male_0_59M"); }
        if(request.getParameter("CS_New_Pos_Male_5_15").equals("")) { CS_New_Pos_Male_5_15=null;} else{CS_New_Pos_Male_5_15  = request.getParameter("CS_New_Pos_Male_5_15"); }
        if(request.getParameter("CS_New_Pos_Female_Adult").equals("")) { CS_New_Pos_Female_Adult=null;} else{CS_New_Pos_Female_Adult  = request.getParameter("CS_New_Pos_Female_Adult"); }
        if(request.getParameter("CS_New_Pos_Female_15_17").equals("")) { CS_New_Pos_Female_15_17=null;} else{CS_New_Pos_Female_15_17  = request.getParameter("CS_New_Pos_Female_15_17"); }
        if(request.getParameter("CS_New_Pos_Female_Postnatal").equals("")) { CS_New_Pos_Female_Postnatal=null;} else{CS_New_Pos_Female_Postnatal  = request.getParameter("CS_New_Pos_Female_Postnatal"); }
        if(request.getParameter("CS_New_Pos_Female_0_59M").equals("")) { CS_New_Pos_Female_0_59M=null;} else{CS_New_Pos_Female_0_59M  = request.getParameter("CS_New_Pos_Female_0_59M"); }
        if(request.getParameter("CS_New_Pos_Female_5_15").equals("")) { CS_New_Pos_Female_5_15=null;} else{CS_New_Pos_Female_5_15  = request.getParameter("CS_New_Pos_Female_5_15"); }
        if(request.getParameter("CS_New_Pos_Readmission").equals("")) { CS_New_Pos_Readmission=null;} else{CS_New_Pos_Readmission  = request.getParameter("CS_New_Pos_Readmission"); }
        if(request.getParameter("CS_New_Pos_Relapse").equals("")) { CS_New_Pos_Relapse=null;} else{CS_New_Pos_Relapse  = request.getParameter("CS_New_Pos_Relapse"); }
        if(request.getParameter("CS_New_Pos_LinkedOVC").equals("")) { CS_New_Pos_LinkedOVC=null;} else{CS_New_Pos_LinkedOVC  = request.getParameter("CS_New_Pos_LinkedOVC"); }
        if(request.getParameter("CS_New_Neg_Male_Adult").equals("")) { CS_New_Neg_Male_Adult=null;} else{CS_New_Neg_Male_Adult  = request.getParameter("CS_New_Neg_Male_Adult"); }
        if(request.getParameter("CS_New_Neg_Male_15_17").equals("")) { CS_New_Neg_Male_15_17=null;} else{CS_New_Neg_Male_15_17  = request.getParameter("CS_New_Neg_Male_15_17"); }
        if(request.getParameter("CS_New_Neg_Male_Postnatal").equals("")) { CS_New_Neg_Male_Postnatal=null;} else{CS_New_Neg_Male_Postnatal  = request.getParameter("CS_New_Neg_Male_Postnatal"); }
        if(request.getParameter("CS_New_Neg_Male_0_59M").equals("")) { CS_New_Neg_Male_0_59M=null;} else{CS_New_Neg_Male_0_59M  = request.getParameter("CS_New_Neg_Male_0_59M"); }
        if(request.getParameter("CS_New_Neg_Male_5_15").equals("")) { CS_New_Neg_Male_5_15=null;} else{CS_New_Neg_Male_5_15  = request.getParameter("CS_New_Neg_Male_5_15"); }
        if(request.getParameter("CS_New_Neg_Female_Adult").equals("")) { CS_New_Neg_Female_Adult=null;} else{CS_New_Neg_Female_Adult  = request.getParameter("CS_New_Neg_Female_Adult"); }
        if(request.getParameter("CS_New_Neg_Female_15_17").equals("")) { CS_New_Neg_Female_15_17=null;} else{CS_New_Neg_Female_15_17  = request.getParameter("CS_New_Neg_Female_15_17"); }
        if(request.getParameter("CS_New_Neg_Female_Postnatal").equals("")) { CS_New_Neg_Female_Postnatal=null;} else{CS_New_Neg_Female_Postnatal  = request.getParameter("CS_New_Neg_Female_Postnatal"); }
        if(request.getParameter("CS_New_Neg_Female_0_59M").equals("")) { CS_New_Neg_Female_0_59M=null;} else{CS_New_Neg_Female_0_59M  = request.getParameter("CS_New_Neg_Female_0_59M"); }
        if(request.getParameter("CS_New_Neg_Female_5_15").equals("")) { CS_New_Neg_Female_5_15=null;} else{CS_New_Neg_Female_5_15  = request.getParameter("CS_New_Neg_Female_5_15"); }
        if(request.getParameter("CS_New_Neg_Readmission").equals("")) { CS_New_Neg_Readmission=null;} else{CS_New_Neg_Readmission  = request.getParameter("CS_New_Neg_Readmission"); }
        if(request.getParameter("CS_New_Neg_Relapse").equals("")) { CS_New_Neg_Relapse=null;} else{CS_New_Neg_Relapse  = request.getParameter("CS_New_Neg_Relapse"); }
        if(request.getParameter("CS_New_Neg_LinkedOVC").equals("")) { CS_New_Neg_LinkedOVC=null;} else{CS_New_Neg_LinkedOVC  = request.getParameter("CS_New_Neg_LinkedOVC"); }
        if(request.getParameter("CS_Revisit_Pos_Adult").equals("")) { CS_Revisit_Pos_Adult=null;} else{CS_Revisit_Pos_Adult  = request.getParameter("CS_Revisit_Pos_Adult"); }
        if(request.getParameter("CS_Revisit_Pos_15_17").equals("")) { CS_Revisit_Pos_15_17=null;} else{CS_Revisit_Pos_15_17  = request.getParameter("CS_Revisit_Pos_15_17"); }
        if(request.getParameter("CS_Revisit_Pos_Postnatal").equals("")) { CS_Revisit_Pos_Postnatal=null;} else{CS_Revisit_Pos_Postnatal  = request.getParameter("CS_Revisit_Pos_Postnatal"); }
        if(request.getParameter("CS_Revisit_Pos_0_59M").equals("")) { CS_Revisit_Pos_0_59M=null;} else{CS_Revisit_Pos_0_59M  = request.getParameter("CS_Revisit_Pos_0_59M"); }
        if(request.getParameter("CS_Revisit_Pos_5_15").equals("")) { CS_Revisit_Pos_5_15=null;} else{CS_Revisit_Pos_5_15  = request.getParameter("CS_Revisit_Pos_5_15"); }
//        if(request.getParameter("CS_Revisit_Pos_Readmission").equals("")) { CS_Revisit_Pos_Readmission=null;} else{CS_Revisit_Pos_Readmission  = request.getParameter("CS_Revisit_Pos_Readmission"); }
//        if(request.getParameter("CS_Revisit_Pos_Relapse").equals("")) { CS_Revisit_Pos_Relapse=null;} else{CS_Revisit_Pos_Relapse  = request.getParameter("CS_Revisit_Pos_Relapse"); }
//        if(request.getParameter("CS_Revisit_Pos_Linked_OVC").equals("")) { CS_Revisit_Pos_Linked_OVC=null;} else{CS_Revisit_Pos_Linked_OVC  = request.getParameter("CS_Revisit_Pos_Linked_OVC"); }
        if(request.getParameter("CS_Revisit_Neg_Adult").equals("")) { CS_Revisit_Neg_Adult=null;} else{CS_Revisit_Neg_Adult  = request.getParameter("CS_Revisit_Neg_Adult"); }
        if(request.getParameter("CS_Revisit_Neg_15_17").equals("")) { CS_Revisit_Neg_15_17=null;} else{CS_Revisit_Neg_15_17  = request.getParameter("CS_Revisit_Neg_15_17"); }
        if(request.getParameter("CS_Revisit_Neg_Postnatal").equals("")) { CS_Revisit_Neg_Postnatal=null;} else{CS_Revisit_Neg_Postnatal  = request.getParameter("CS_Revisit_Neg_Postnatal"); }
        if(request.getParameter("CS_Revisit_Neg_0_59M").equals("")) { CS_Revisit_Neg_0_59M=null;} else{CS_Revisit_Neg_0_59M  = request.getParameter("CS_Revisit_Neg_0_59M"); }
        if(request.getParameter("CS_Revisit_Neg_5_15").equals("")) { CS_Revisit_Neg_5_15=null;} else{CS_Revisit_Neg_5_15  = request.getParameter("CS_Revisit_Neg_5_15"); }
//        if(request.getParameter("CS_Revisit_Neg_Readmission").equals("")) { CS_Revisit_Neg_Readmission=null;} else{CS_Revisit_Neg_Readmission  = request.getParameter("CS_Revisit_Neg_Readmission"); }
//        if(request.getParameter("CS_Revisit_Neg_Relapse").equals("")) { CS_Revisit_Neg_Relapse=null;} else{CS_Revisit_Neg_Relapse  = request.getParameter("CS_Revisit_Neg_Relapse"); }
//        if(request.getParameter("CS_Revisit_Neg_LinkedOVC").equals("")) { CS_Revisit_Neg_LinkedOVC=null;} else{CS_Revisit_Neg_LinkedOVC  = request.getParameter("CS_Revisit_Neg_LinkedOVC"); }
        if(request.getParameter("CS_Total_Adult").equals("")) { CS_Total_Adult=null;} else{CS_Total_Adult  = request.getParameter("CS_Total_Adult"); }
        if(request.getParameter("CS_Total_15_17").equals("")) { CS_Total_15_17=null;} else{CS_Total_15_17  = request.getParameter("CS_Total_15_17"); }
        if(request.getParameter("CS_Total_Postnatal").equals("")) { CS_Total_Postnatal=null;} else{CS_Total_Postnatal  = request.getParameter("CS_Total_Postnatal"); }
        if(request.getParameter("CS_Total_0_59M").equals("")) { CS_Total_0_59M=null;} else{CS_Total_0_59M  = request.getParameter("CS_Total_0_59M"); }
        if(request.getParameter("CS_Total_5_15").equals("")) { CS_Total_5_15=null;} else{CS_Total_5_15  = request.getParameter("CS_Total_5_15"); }
        if(request.getParameter("CS_Total_Readmission").equals("")) { CS_Total_Readmission=null;} else{CS_Total_Readmission  = request.getParameter("CS_Total_Readmission"); }
        if(request.getParameter("CS_Total_Relapse").equals("")) { CS_Total_Relapse=null;} else{CS_Total_Relapse  = request.getParameter("CS_Total_Relapse"); }
        if(request.getParameter("CS_Total_LinkedOVC").equals("")) { CS_Total_LinkedOVC=null;} else{CS_Total_LinkedOVC  = request.getParameter("CS_Total_LinkedOVC"); }
        if(request.getParameter("NH1_Pos_SAM_Adult").equals("")) { NH1_Pos_SAM_Adult=null;} else{NH1_Pos_SAM_Adult  = request.getParameter("NH1_Pos_SAM_Adult"); }
        if(request.getParameter("NH1_Pos_SAM_15_17").equals("")) { NH1_Pos_SAM_15_17=null;} else{NH1_Pos_SAM_15_17  = request.getParameter("NH1_Pos_SAM_15_17"); }
        if(request.getParameter("NH1_Pos_SAM_Postnatal").equals("")) { NH1_Pos_SAM_Postnatal=null;} else{NH1_Pos_SAM_Postnatal  = request.getParameter("NH1_Pos_SAM_Postnatal"); }
        if(request.getParameter("NH1_Pos_SAM_0_59M").equals("")) { NH1_Pos_SAM_0_59M=null;} else{NH1_Pos_SAM_0_59M  = request.getParameter("NH1_Pos_SAM_0_59M"); }
        if(request.getParameter("NH1_Pos_SAM_5_15").equals("")) { NH1_Pos_SAM_5_15=null;} else{NH1_Pos_SAM_5_15  = request.getParameter("NH1_Pos_SAM_5_15"); }
        if(request.getParameter("NH1_Pos_SAM_Total").equals("")) { NH1_Pos_SAM_Total=null;} else{NH1_Pos_SAM_Total  = request.getParameter("NH1_Pos_SAM_Total"); }
        if(request.getParameter("NH1_Pos_MAM_Adult").equals("")) { NH1_Pos_MAM_Adult=null;} else{NH1_Pos_MAM_Adult  = request.getParameter("NH1_Pos_MAM_Adult"); }
        if(request.getParameter("NH1_Pos_MAM_15_17").equals("")) { NH1_Pos_MAM_15_17=null;} else{NH1_Pos_MAM_15_17  = request.getParameter("NH1_Pos_MAM_15_17"); }
        if(request.getParameter("NH1_Pos_MAM_Postnatal").equals("")) { NH1_Pos_MAM_15_17=null;} else{NH1_Pos_MAM_Postnatal  = request.getParameter("NH1_Pos_MAM_Postnatal"); }
        if(request.getParameter("NH1_Pos_MAM_0_59M").equals("")) { NH1_Pos_MAM_0_59M=null;} else{NH1_Pos_MAM_0_59M  = request.getParameter("NH1_Pos_MAM_0_59M"); }
        if(request.getParameter("NH1_Pos_MAM_5_15").equals("")) { NH1_Pos_MAM_5_15=null;} else{NH1_Pos_MAM_5_15  = request.getParameter("NH1_Pos_MAM_5_15"); }
        if(request.getParameter("NH1_Pos_MAM_Total").equals("")) { NH1_Pos_MAM_Total=null;} else{NH1_Pos_MAM_Total  = request.getParameter("NH1_Pos_MAM_Total"); }
        if(request.getParameter("NH1_Neg_SAM_Adult").equals("")) { NH1_Neg_SAM_Adult=null;} else{NH1_Neg_SAM_Adult  = request.getParameter("NH1_Neg_SAM_Adult"); }
        if(request.getParameter("NH1_Neg_SAM_15_17").equals("")) { NH1_Neg_SAM_15_17=null;} else{NH1_Neg_SAM_15_17  = request.getParameter("NH1_Neg_SAM_15_17"); }
        if(request.getParameter("NH1_Neg_SAM_Postnatal").equals("")) { NH1_Neg_SAM_Postnatal=null;} else{NH1_Neg_SAM_Postnatal  = request.getParameter("NH1_Neg_SAM_Postnatal"); }
        if(request.getParameter("NH1_Neg_SAM_0_59M").equals("")) { NH1_Neg_SAM_0_59M=null;} else{NH1_Neg_SAM_0_59M  = request.getParameter("NH1_Neg_SAM_0_59M"); }
        if(request.getParameter("NH1_Neg_SAM_5_15").equals("")) { NH1_Neg_SAM_5_15=null;} else{NH1_Neg_SAM_5_15  = request.getParameter("NH1_Neg_SAM_5_15"); }
        if(request.getParameter("NH1_Neg_SAM_Total").equals("")) { NH1_Neg_SAM_Total=null;} else{NH1_Neg_SAM_Total  = request.getParameter("NH1_Neg_SAM_Total"); }
        if(request.getParameter("NH1_Neg_MAM_Adult").equals("")) { NH1_Neg_MAM_Adult=null;} else{NH1_Neg_MAM_Adult  = request.getParameter("NH1_Neg_MAM_Adult"); }
        if(request.getParameter("NH1_Neg_MAM_15_17").equals("")) { NH1_Neg_MAM_15_17=null;} else{NH1_Neg_MAM_15_17  = request.getParameter("NH1_Neg_MAM_15_17"); }
        if(request.getParameter("NH1_Neg_MAM_Postnatal").equals("")) { NH1_Neg_MAM_Postnatal=null;} else{NH1_Neg_MAM_Postnatal  = request.getParameter("NH1_Neg_MAM_Postnatal"); }
        if(request.getParameter("NH1_Neg_MAM_0_59M").equals("")) { NH1_Neg_MAM_0_59M=null;} else{NH1_Neg_MAM_0_59M  = request.getParameter("NH1_Neg_MAM_0_59M"); }
        if(request.getParameter("NH1_Neg_MAM_5_15").equals("")) { NH1_Neg_MAM_5_15=null;} else{NH1_Neg_MAM_5_15  = request.getParameter("NH1_Neg_MAM_5_15"); }
        if(request.getParameter("NH1_Neg_MAM_Total").equals("")) { NH1_Neg_MAM_Total=null;} else{NH1_Neg_MAM_Total  = request.getParameter("NH1_Neg_MAM_Total"); }
        
        if(request.getParameter("NH1_Total_Adult").equals("")) { NH1_Total_Adult=null;} else{NH1_Total_Adult  = request.getParameter("NH1_Total_Adult"); }
        if(request.getParameter("NH1_Total_15_17").equals("")) { NH1_Total_15_17=null;} else{NH1_Total_15_17  = request.getParameter("NH1_Total_15_17"); }
        if(request.getParameter("NH1_Total_Postnatal").equals("")) { NH1_Total_Postnatal=null;} else{NH1_Total_Postnatal  = request.getParameter("NH1_Total_Postnatal"); }
        if(request.getParameter("NH1_Total_0_59M").equals("")) { NH1_Total_0_59M=null;} else{NH1_Total_0_59M  = request.getParameter("NH1_Total_0_59M"); }
        if(request.getParameter("NH1_Total_5_15").equals("")) { NH1_Total_5_15=null;} else{NH1_Total_5_15  = request.getParameter("NH1_Total_5_15"); }
        if(request.getParameter("NH1_Grand_Total").equals("")) { NH1_Grand_Total=null;} else{NH1_Grand_Total  = request.getParameter("NH1_Grand_Total"); }
        
//        if(request.getParameter("IFP_0_6_EBF_Pos").equals("")) { IFP_0_6_EBF_Pos=null;} else{IFP_0_6_EBF_Pos  = request.getParameter("IFP_0_6_EBF_Pos"); }
//        if(request.getParameter("IFP_0_6_EBF_Neg").equals("")) { IFP_0_6_EBF_Neg=null;} else{IFP_0_6_EBF_Neg  = request.getParameter("IFP_0_6_EBF_Neg"); }
//        if(request.getParameter("IFP_0_6_ERF_Pos").equals("")) { IFP_0_6_ERF_Pos=null;} else{IFP_0_6_ERF_Pos  = request.getParameter("IFP_0_6_ERF_Pos"); }
//        if(request.getParameter("IFP_0_6_ERF_Neg").equals("")) { IFP_0_6_ERF_Neg=null;} else{IFP_0_6_ERF_Neg  = request.getParameter("IFP_0_6_ERF_Neg"); }
//        if(request.getParameter("IFP_0_6_MF_Pos").equals("")) { IFP_0_6_MF_Pos=null;} else{IFP_0_6_MF_Pos  = request.getParameter("IFP_0_6_MF_Pos"); }
//        if(request.getParameter("IFP_0_6_MF_Neg").equals("")) { IFP_0_6_MF_Neg=null;} else{IFP_0_6_MF_Neg  = request.getParameter("IFP_0_6_MF_Neg"); }
//        if(request.getParameter("IFP_0_6_SubTotal_Pos").equals("")) { IFP_0_6_SubTotal_Pos=null;} else{IFP_0_6_SubTotal_Pos  = request.getParameter("IFP_0_6_SubTotal_Pos"); }
//        if(request.getParameter("IFP_0_6_SubTotal_Neg").equals("")) { IFP_0_6_SubTotal_Neg=null;} else{IFP_0_6_SubTotal_Neg  = request.getParameter("IFP_0_6_SubTotal_Neg"); }
//        if(request.getParameter("IFP_6_12_BF_Pos").equals("")) { IFP_6_12_BF_Pos=null;} else{IFP_6_12_BF_Pos  = request.getParameter("IFP_6_12_BF_Pos"); }
//        if(request.getParameter("IFP_6_12_BF_Neg").equals("")) { IFP_6_12_BF_Neg=null;} else{IFP_6_12_BF_Neg  = request.getParameter("IFP_6_12_BF_Neg"); }
//        if(request.getParameter("IFP_6_12_NBF_Pos").equals("")) { IFP_6_12_NBF_Pos=null;} else{IFP_6_12_NBF_Pos  = request.getParameter("IFP_6_12_NBF_Pos"); }
//        if(request.getParameter("IFP_6_12_NBF_Neg").equals("")) { IFP_6_12_NBF_Neg=null;} else{IFP_6_12_NBF_Neg  = request.getParameter("IFP_6_12_NBF_Neg"); }
//        if(request.getParameter("IFP_6_12_NotKnown_Pos").equals("")) { IFP_6_12_NotKnown_Pos=null;} else{IFP_6_12_NotKnown_Pos  = request.getParameter("IFP_6_12_NotKnown_Pos"); }
//        if(request.getParameter("IFP_6_12_NotKnown_Neg").equals("")) { IFP_6_12_NotKnown_Neg=null;} else{IFP_6_12_NotKnown_Neg  = request.getParameter("IFP_6_12_NotKnown_Neg"); }
//        if(request.getParameter("IFP_6_12_BCF_Pos").equals("")) { IFP_6_12_BCF_Pos=null;} else{IFP_6_12_BCF_Pos  = request.getParameter("IFP_6_12_BCF_Pos"); }
//        if(request.getParameter("IFP_6_12_BCF_Neg").equals("")) { IFP_6_12_BCF_Neg=null;} else{IFP_6_12_BCF_Neg  = request.getParameter("IFP_6_12_BCF_Neg"); }
//        if(request.getParameter("IFP_6_12_SubTotal_Pos").equals("")) { IFP_6_12_SubTotal_Pos=null;} else{IFP_6_12_SubTotal_Pos  = request.getParameter("IFP_6_12_SubTotal_Pos"); }
//        if(request.getParameter("IFP_6_12_SubTotal_Neg").equals("")) { IFP_6_12_SubTotal_Neg=null;} else{ IFP_6_12_SubTotal_Neg  = request.getParameter("IFP_6_12_SubTotal_Neg"); }
        
        if(request.getParameter("NH2_TCM_Adults15_17_Pos").equals("")) { NH2_TCM_Adults15_17_Pos=null;} else{NH2_TCM_Adults15_17_Pos  = request.getParameter("NH2_TCM_Adults15_17_Pos"); }
        if(request.getParameter("NH2_TCM_Postnatal_Pos").equals("")) { NH2_TCM_Postnatal_Pos=null;} else{NH2_TCM_Postnatal_Pos  = request.getParameter("NH2_TCM_Postnatal_Pos"); }
        if(request.getParameter("NH2_TCM_AllAdults_Neg").equals("")) { NH2_TCM_AllAdults_Neg=null;} else{NH2_TCM_AllAdults_Neg  = request.getParameter("NH2_TCM_AllAdults_Neg"); }
        if(request.getParameter("NH2_TCM_0_59M_Pos").equals("")) { NH2_TCM_0_59M_Pos=null;} else{NH2_TCM_0_59M_Pos  = request.getParameter("NH2_TCM_0_59M_Pos"); }
        if(request.getParameter("NH2_TCM_5_15_Pos").equals("")) { NH2_TCM_5_15_Pos=null;} else{NH2_TCM_5_15_Pos  = request.getParameter("NH2_TCM_5_15_Pos"); }
        if(request.getParameter("NH2_TCM_0_59M_Neg").equals("")) { NH2_TCM_0_59M_Neg=null;} else{NH2_TCM_0_59M_Neg  = request.getParameter("NH2_TCM_0_59M_Neg"); }
        if(request.getParameter("NH2_TCM_5_15_Neg").equals("")) { NH2_TCM_5_15_Neg=null;} else{NH2_TCM_5_15_Neg = request.getParameter("NH2_TCM_5_15_Neg"); }
        if(request.getParameter("NH2_TCM_Total").equals("")) { NH2_TCM_Total=null;} else{NH2_TCM_Total  = request.getParameter("NH2_TCM_Total"); }
        if(request.getParameter("NH2_RUTF_Adults15_17_Pos").equals("")) { NH2_RUTF_Adults15_17_Pos=null;} else{NH2_RUTF_Adults15_17_Pos  = request.getParameter("NH2_RUTF_Adults15_17_Pos"); }
        if(request.getParameter("NH2_RUTF_Postnatal_Pos").equals("")) { NH2_RUTF_Postnatal_Pos=null;} else{NH2_RUTF_Postnatal_Pos  = request.getParameter("NH2_RUTF_Postnatal_Pos"); }
        if(request.getParameter("NH2_RUTF_AllAdults_Neg").equals("")) { NH2_RUTF_AllAdults_Neg=null;} else{NH2_RUTF_AllAdults_Neg  = request.getParameter("NH2_RUTF_AllAdults_Neg"); }
        if(request.getParameter("NH2_RUTF_0_59M_Pos").equals("")) { NH2_RUTF_0_59M_Pos=null;} else{NH2_RUTF_0_59M_Pos = request.getParameter("NH2_RUTF_0_59M_Pos"); }
        if(request.getParameter("NH2_RUTF_5_15_Pos").equals("")) { NH2_RUTF_5_15_Pos=null;} else{NH2_RUTF_5_15_Pos  = request.getParameter("NH2_RUTF_5_15_Pos"); }
        if(request.getParameter("NH2_RUTF_0_59M_Neg").equals("")) { NH2_RUTF_0_59M_Neg=null;} else{NH2_RUTF_0_59M_Neg  = request.getParameter("NH2_RUTF_0_59M_Neg"); }
        if(request.getParameter("NH2_RUTF_5_15_Neg").equals("")) { NH2_RUTF_5_15_Neg=null;} else{NH2_RUTF_5_15_Neg  = request.getParameter("NH2_RUTF_5_15_Neg"); }
        if(request.getParameter("NH2_RUTF_Total").equals("")) { NH2_RUTF_Total=null;} else{NH2_RUTF_Total  = request.getParameter("NH2_RUTF_Total"); }
        if(request.getParameter("NH2_RUSF_Adults15_17_Pos").equals("")) { NH2_RUSF_Adults15_17_Pos=null;} else{NH2_RUSF_Adults15_17_Pos  = request.getParameter("NH2_RUSF_Adults15_17_Pos"); }
        if(request.getParameter("NH2_RUSF_Postnatal_Pos").equals("")) { NH2_RUSF_Postnatal_Pos=null;} else{NH2_RUSF_Postnatal_Pos  = request.getParameter("NH2_RUSF_Postnatal_Pos"); }
        if(request.getParameter("NH2_RUSF_AllAdults_Neg").equals("")) { NH2_RUSF_AllAdults_Neg=null;} else{NH2_RUSF_AllAdults_Neg  = request.getParameter("NH2_RUSF_AllAdults_Neg"); }
        if(request.getParameter("NH2_RUSF_0_59M_Pos").equals("")) { NH2_RUSF_0_59M_Pos=null;} else{NH2_RUSF_0_59M_Pos  = request.getParameter("NH2_RUSF_0_59M_Pos"); }
        if(request.getParameter("NH2_RUSF_5_15_Pos").equals("")) { NH2_RUSF_5_15_Pos=null;} else{NH2_RUSF_5_15_Pos  = request.getParameter("NH2_RUSF_5_15_Pos"); }
        if(request.getParameter("NH2_RUSF_0_59M_Neg").equals("")) { NH2_RUSF_0_59M_Neg=null;} else{NH2_RUSF_0_59M_Neg  = request.getParameter("NH2_RUSF_0_59M_Neg"); }
        if(request.getParameter("NH2_RUSF_5_15_Neg").equals("")) { NH2_RUSF_5_15_Neg=null;} else{NH2_RUSF_5_15_Neg  = request.getParameter("NH2_RUSF_5_15_Neg"); }
        if(request.getParameter("NH2_RUSF_Total").equals("")) { NH2_RUSF_Total=null;} else{NH2_RUSF_Total  = request.getParameter("NH2_RUSF_Total"); }
        if(request.getParameter("NH2_FBFCSB_Adults15_17_Pos").equals("")) { NH2_FBFCSB_Adults15_17_Pos=null;} else{NH2_FBFCSB_Adults15_17_Pos  = request.getParameter("NH2_FBFCSB_Adults15_17_Pos"); }
        if(request.getParameter("NH2_FBFCSB_Postnatal_Pos").equals("")) { NH2_FBFCSB_Postnatal_Pos=null;} else{NH2_FBFCSB_Postnatal_Pos  = request.getParameter("NH2_FBFCSB_Postnatal_Pos"); }
        if(request.getParameter("NH2_FBFCSB_AllAdults_Neg").equals("")) { NH2_FBFCSB_AllAdults_Neg=null;} else{NH2_FBFCSB_AllAdults_Neg  = request.getParameter("NH2_FBFCSB_AllAdults_Neg"); }
        if(request.getParameter("NH2_FBFCSB_0_59M_Pos").equals("")) { NH2_FBFCSB_0_59M_Pos=null;} else{NH2_FBFCSB_0_59M_Pos  = request.getParameter("NH2_FBFCSB_0_59M_Pos"); }
        if(request.getParameter("NH2_FBFCSB_5_15_Pos").equals("")) { NH2_FBFCSB_5_15_Pos=null;} else{NH2_FBFCSB_5_15_Pos  = request.getParameter("NH2_FBFCSB_5_15_Pos"); }
        if(request.getParameter("NH2_FBFCSB_0_59M_Neg").equals("")) { NH2_FBFCSB_0_59M_Neg=null;} else{NH2_FBFCSB_0_59M_Neg  = request.getParameter("NH2_FBFCSB_0_59M_Neg"); }
        if(request.getParameter("NH2_FBFCSB_5_15_Neg").equals("")) { NH2_FBFCSB_5_15_Neg=null;} else{NH2_FBFCSB_5_15_Neg  = request.getParameter("NH2_FBFCSB_5_15_Neg"); }
        if(request.getParameter("NH2_FBFCSB_Total").equals("")) { NH2_FBFCSB_Total=null;} else{NH2_FBFCSB_Total  = request.getParameter("NH2_FBFCSB_Total"); }
        if(request.getParameter("NH2_LNS_Adults15_17_Pos").equals("")) { NH2_LNS_Adults15_17_Pos=null;} else{NH2_LNS_Adults15_17_Pos  = request.getParameter("NH2_LNS_Adults15_17_Pos"); }
        if(request.getParameter("NH2_LNS_Postnatal_Pos").equals("")) { NH2_LNS_Postnatal_Pos=null;} else{NH2_LNS_Postnatal_Pos  = request.getParameter("NH2_LNS_Postnatal_Pos"); }
        if(request.getParameter("NH2_LNS_AllAdults_Neg").equals("")) { NH2_LNS_AllAdults_Neg=null;} else{NH2_LNS_AllAdults_Neg  = request.getParameter("NH2_LNS_AllAdults_Neg"); }
        if(request.getParameter("NH2_LNS_0_59M_Pos").equals("")) { NH2_LNS_0_59M_Pos=null;} else{NH2_LNS_0_59M_Pos  = request.getParameter("NH2_LNS_0_59M_Pos"); }
        if(request.getParameter("NH2_LNS_5_15_Pos").equals("")) { NH2_LNS_5_15_Pos=null;} else{NH2_LNS_5_15_Pos  = request.getParameter("NH2_LNS_5_15_Pos"); }
        if(request.getParameter("NH2_LNS_0_59M_Neg").equals("")) { NH2_LNS_0_59M_Neg=null;} else{NH2_LNS_0_59M_Neg  = request.getParameter("NH2_LNS_0_59M_Neg"); }
        if(request.getParameter("NH2_LNS_5_15_Neg").equals("")) { NH2_LNS_5_15_Neg=null;} else{NH2_LNS_5_15_Neg  = request.getParameter("NH2_LNS_5_15_Neg"); }
        if(request.getParameter("NH2_LNS_Total").equals("")) { NH2_LNS_Total=null;} else{NH2_LNS_Total  = request.getParameter("NH2_LNS_Total"); }
        if(request.getParameter("NH2_Micronutrients_Adults15_17_Pos").equals("")) { NH2_Micronutrients_Adults15_17_Pos=null;} else{NH2_Micronutrients_Adults15_17_Pos  = request.getParameter("NH2_Micronutrients_Adults15_17_Pos"); }
        if(request.getParameter("NH2_Micronutrients_Postnatal_Pos").equals("")) { NH2_Micronutrients_Postnatal_Pos=null;} else{NH2_Micronutrients_Postnatal_Pos  = request.getParameter("NH2_Micronutrients_Postnatal_Pos"); }
        if(request.getParameter("NH2_Micronutrients_AllAdults_Neg").equals("")) { NH2_Micronutrients_AllAdults_Neg=null;} else{NH2_Micronutrients_AllAdults_Neg  = request.getParameter("NH2_Micronutrients_AllAdults_Neg"); }
        if(request.getParameter("NH2_Micronutrients_0_59M_Pos").equals("")) { NH2_Micronutrients_0_59M_Pos=null;} else{NH2_Micronutrients_0_59M_Pos  = request.getParameter("NH2_Micronutrients_0_59M_Pos"); }
        if(request.getParameter("NH2_Micronutrients_5_15_Pos").equals("")) { NH2_Micronutrients_5_15_Pos=null;} else{NH2_Micronutrients_5_15_Pos  = request.getParameter("NH2_Micronutrients_5_15_Pos"); }
        if(request.getParameter("NH2_Micronutrients_0_59M_Neg").equals("")) { NH2_Micronutrients_0_59M_Neg=null;} else{NH2_Micronutrients_0_59M_Neg  = request.getParameter("NH2_Micronutrients_0_59M_Neg"); }
        if(request.getParameter("NH2_Micronutrients_5_15_Neg").equals("")) { NH2_Micronutrients_5_15_Neg=null;} else{NH2_Micronutrients_5_15_Neg  = request.getParameter("NH2_Micronutrients_5_15_Neg"); }
        if(request.getParameter("NH2_Micronutrients_Total").equals("")) { NH2_Micronutrients_Total=null;} else{NH2_Micronutrients_Total  = request.getParameter("NH2_Micronutrients_Total"); }
        if(request.getParameter("NH2_Others_Adults15_17_Pos").equals("")) { NH2_Others_Adults15_17_Pos=null;} else{NH2_Others_Adults15_17_Pos  = request.getParameter("NH2_Others_Adults15_17_Pos"); }
        if(request.getParameter("NH2_Others_Postnatal_Pos").equals("")) { NH2_Others_Postnatal_Pos=null;} else{NH2_Others_Postnatal_Pos  = request.getParameter("NH2_Others_Postnatal_Pos"); }
        if(request.getParameter("NH2_Others_AllAdults_Neg").equals("")) { NH2_Others_AllAdults_Neg=null;} else{NH2_Others_AllAdults_Neg  = request.getParameter("NH2_Others_AllAdults_Neg"); }
        if(request.getParameter("NH2_Others_0_59M_Pos").equals("")) { NH2_Others_0_59M_Pos=null;} else{NH2_Others_0_59M_Pos  = request.getParameter("NH2_Others_0_59M_Pos"); }
        if(request.getParameter("NH2_Others_5_15_Pos").equals("")) { NH2_Others_5_15_Pos=null;} else{NH2_Others_5_15_Pos  = request.getParameter("NH2_Others_5_15_Pos"); }
        if(request.getParameter("NH2_Others_0_59M_Neg").equals("")) { NH2_Others_0_59M_Neg=null;} else{NH2_Others_0_59M_Neg  = request.getParameter("NH2_Others_0_59M_Neg"); }
        if(request.getParameter("NH2_Others_5_15_Neg").equals("")) { NH2_Others_5_15_Neg=null;} else{NH2_Others_5_15_Neg  = request.getParameter("NH2_Others_5_15_Neg"); }
        if(request.getParameter("NH2_Others_Total").equals("")) { NH2_Others_Total=null;} else{NH2_Others_Total  = request.getParameter("NH2_Others_Total"); }
        if(request.getParameter("NH2_Total_Adults15_17_Pos").equals("")) { NH2_Total_Adults15_17_Pos=null;} else{NH2_Total_Adults15_17_Pos  = request.getParameter("NH2_Total_Adults15_17_Pos"); }
        if(request.getParameter("NH2_Total_Postnatal_Pos").equals("")) { NH2_Total_Postnatal_Pos=null;} else{NH2_Total_Postnatal_Pos  = request.getParameter("NH2_Total_Postnatal_Pos"); }
        if(request.getParameter("NH2_Total_AllAdults_Neg").equals("")) { NH2_Total_AllAdults_Neg=null;} else{NH2_Total_AllAdults_Neg  = request.getParameter("NH2_Total_AllAdults_Neg"); }
        if(request.getParameter("NH2_Total_0_59M_Pos").equals("")) { NH2_Total_0_59M_Pos=null;} else{NH2_Total_0_59M_Pos  = request.getParameter("NH2_Total_0_59M_Pos"); }
        if(request.getParameter("NH2_Total_5_15_Pos").equals("")) { NH2_Total_5_15_Pos=null;} else{NH2_Total_5_15_Pos  = request.getParameter("NH2_Total_5_15_Pos"); }
        if(request.getParameter("NH2_Total_0_59M_Neg").equals("")) { NH2_Total_0_59M_Neg=null;} else{NH2_Total_0_59M_Neg  = request.getParameter("NH2_Total_0_59M_Neg"); }
        if(request.getParameter("NH2_Total_5_15_Neg").equals("")) { NH2_Total_5_15_Neg=null;} else{NH2_Total_5_15_Neg  = request.getParameter("NH2_Total_5_15_Neg"); }
        if(request.getParameter("NH2_Grand_Total").equals("")) { NH2_Grand_Total=null;} else{NH2_Grand_Total  = request.getParameter("NH2_Grand_Total"); }
        
        if(request.getParameter("LTF_Adult_15_17").equals("")) { LTF_Adult_15_17=null;} else{LTF_Adult_15_17  = request.getParameter("LTF_Adult_15_17"); }
        if(request.getParameter("LTF_Pregnant").equals("")) { LTF_Pregnant=null;} else{LTF_Pregnant  = request.getParameter("LTF_Pregnant"); }
        if(request.getParameter("LTF_Postnatal").equals("")) { LTF_Postnatal=null;} else{LTF_Postnatal  = request.getParameter("LTF_Postnatal"); }
        if(request.getParameter("LTF_0_59M").equals("")) { LTF_0_59M=null;} else{LTF_0_59M  = request.getParameter("LTF_0_59M"); }
        if(request.getParameter("LTF_5_15").equals("")) { LTF_5_15=null;} else{LTF_5_15  = request.getParameter("LTF_5_15"); }
        if(request.getParameter("RM_Adult_15_17").equals("")) { RM_Adult_15_17=null;} else{RM_Adult_15_17  = request.getParameter("RM_Adult_15_17"); }
        if(request.getParameter("RM_Pregnant").equals("")) { RM_Pregnant=null;} else{RM_Pregnant  = request.getParameter("RM_Pregnant"); }
        if(request.getParameter("RM_Postnatal").equals("")) { RM_Postnatal=null;} else{RM_Postnatal  = request.getParameter("RM_Postnatal"); }
        if(request.getParameter("RM_0_59M").equals("")) { RM_0_59M=null;} else{RM_0_59M  = request.getParameter("RM_0_59M"); }
        if(request.getParameter("RM_5_15").equals("")) {RM_5_15=null;} else{RM_5_15= request.getParameter("RM_5_15"); } 
        
        reporting_officer= request.getParameter("reporting_officer");
        designation= request.getParameter("designation"); 
        reporting_date= request.getParameter("reporting_date");  
        
        
      if(CS_New_Pos_Male_Adult==null){CS_New_Pos_Male_Adult_1=0;} else{CS_New_Pos_Male_Adult_1=Integer.parseInt(CS_New_Pos_Male_Adult);}
      if(CS_New_Pos_Male_15_17==null){CS_New_Pos_Male_15_17_1=0;} else{CS_New_Pos_Male_15_17_1=Integer.parseInt(CS_New_Pos_Male_15_17);}
      if(CS_New_Pos_Male_Postnatal==null){CS_New_Pos_Male_Postnatal_1=0;} else{CS_New_Pos_Male_Postnatal_1=Integer.parseInt(CS_New_Pos_Male_Postnatal);}
      if(CS_New_Pos_Male_0_59M==null){CS_New_Pos_Male_0_59M_1=0;} else{CS_New_Pos_Male_0_59M_1=Integer.parseInt(CS_New_Pos_Male_0_59M);}
      if(CS_New_Pos_Male_5_15==null){CS_New_Pos_Male_5_15_1=0;} else{CS_New_Pos_Male_5_15_1=Integer.parseInt(CS_New_Pos_Male_5_15);}
      if(CS_New_Pos_Female_Adult==null){CS_New_Pos_Female_Adult_1=0;} else{CS_New_Pos_Female_Adult_1=Integer.parseInt(CS_New_Pos_Female_Adult);}
      if(CS_New_Pos_Female_15_17==null){CS_New_Pos_Female_15_17_1=0;} else{CS_New_Pos_Female_15_17_1=Integer.parseInt(CS_New_Pos_Female_15_17);}
      if(CS_New_Pos_Female_Postnatal==null){CS_New_Pos_Female_Postnatal_1=0;} else{CS_New_Pos_Female_Postnatal_1=Integer.parseInt(CS_New_Pos_Female_Postnatal);}
      if(CS_New_Pos_Female_0_59M==null){CS_New_Pos_Female_0_59M_1=0;} else{CS_New_Pos_Female_0_59M_1=Integer.parseInt(CS_New_Pos_Female_0_59M);}
      if(CS_New_Pos_Female_5_15==null){CS_New_Pos_Female_5_15_1=0;} else{CS_New_Pos_Female_5_15_1=Integer.parseInt(CS_New_Pos_Female_5_15);}
      if(CS_New_Pos_Readmission==null){CS_New_Pos_Readmission_1=0;} else{CS_New_Pos_Readmission_1=Integer.parseInt(CS_New_Pos_Readmission);}
      if(CS_New_Pos_Relapse==null){CS_New_Pos_Relapse_1=0;} else{CS_New_Pos_Relapse_1=Integer.parseInt(CS_New_Pos_Relapse);}
      if(CS_New_Pos_LinkedOVC==null){CS_New_Pos_LinkedOVC_1=0;} else{CS_New_Pos_LinkedOVC_1=Integer.parseInt(CS_New_Pos_LinkedOVC);}
      if(CS_New_Neg_Male_Adult==null){CS_New_Neg_Male_Adult_1=0;} else{CS_New_Neg_Male_Adult_1=Integer.parseInt(CS_New_Neg_Male_Adult);}
      if(CS_New_Neg_Male_15_17==null){CS_New_Neg_Male_15_17_1=0;} else{CS_New_Neg_Male_15_17_1=Integer.parseInt(CS_New_Neg_Male_15_17);}
      if(CS_New_Neg_Male_Postnatal==null){CS_New_Neg_Male_Postnatal_1=0;} else{CS_New_Neg_Male_Postnatal_1=Integer.parseInt(CS_New_Neg_Male_Postnatal);}
      if(CS_New_Neg_Male_0_59M==null){CS_New_Neg_Male_0_59M_1=0;} else{CS_New_Neg_Male_0_59M_1=Integer.parseInt(CS_New_Neg_Male_0_59M);}
      if(CS_New_Neg_Male_5_15==null){CS_New_Neg_Male_5_15_1=0;} else{CS_New_Neg_Male_5_15_1=Integer.parseInt(CS_New_Neg_Male_5_15);}
      if(CS_New_Neg_Female_Adult==null){CS_New_Neg_Female_Adult_1=0;} else{CS_New_Neg_Female_Adult_1=Integer.parseInt(CS_New_Neg_Female_Adult);}
      if(CS_New_Neg_Female_15_17==null){CS_New_Neg_Female_15_17_1=0;} else{CS_New_Neg_Female_15_17_1=Integer.parseInt(CS_New_Neg_Female_15_17);}
      if(CS_New_Neg_Female_Postnatal==null){CS_New_Neg_Female_Postnatal_1=0;} else{CS_New_Neg_Female_Postnatal_1=Integer.parseInt(CS_New_Neg_Female_Postnatal);}
      if(CS_New_Neg_Female_0_59M==null){CS_New_Neg_Female_0_59M_1=0;} else{CS_New_Neg_Female_0_59M_1=Integer.parseInt(CS_New_Neg_Female_0_59M);}
      if(CS_New_Neg_Female_5_15==null){CS_New_Neg_Female_5_15_1=0;} else{CS_New_Neg_Female_5_15_1=Integer.parseInt(CS_New_Neg_Female_5_15);}
      if(CS_New_Neg_Readmission==null){CS_New_Neg_Readmission_1=0;} else{CS_New_Neg_Readmission_1=Integer.parseInt(CS_New_Neg_Readmission);}
      if(CS_New_Neg_Relapse==null){CS_New_Neg_Relapse_1=0;} else{CS_New_Neg_Relapse_1=Integer.parseInt(CS_New_Neg_Relapse);}
      if(CS_New_Neg_LinkedOVC==null){CS_New_Neg_LinkedOVC_1=0;} else{CS_New_Neg_LinkedOVC_1=Integer.parseInt(CS_New_Neg_LinkedOVC);}
      if(CS_Revisit_Pos_Adult==null){CS_Revisit_Pos_Adult_1=0;} else{CS_Revisit_Pos_Adult_1=Integer.parseInt(CS_Revisit_Pos_Adult);}
      if(CS_Revisit_Pos_15_17==null){CS_Revisit_Pos_15_17_1=0;} else{CS_Revisit_Pos_15_17_1=Integer.parseInt(CS_Revisit_Pos_15_17);}
      if(CS_Revisit_Pos_Postnatal==null){CS_Revisit_Pos_Postnatal_1=0;} else{CS_Revisit_Pos_Postnatal_1=Integer.parseInt(CS_Revisit_Pos_Postnatal);}
      if(CS_Revisit_Pos_0_59M==null){CS_Revisit_Pos_0_59M_1=0;} else{CS_Revisit_Pos_0_59M_1=Integer.parseInt(CS_Revisit_Pos_0_59M);}
      if(CS_Revisit_Pos_5_15==null){CS_Revisit_Pos_5_15_1=0;} else{CS_Revisit_Pos_5_15_1=Integer.parseInt(CS_Revisit_Pos_5_15);}
      if(CS_Revisit_Neg_Adult==null){CS_Revisit_Neg_Adult_1=0;} else{CS_Revisit_Neg_Adult_1=Integer.parseInt(CS_Revisit_Neg_Adult);}
      if(CS_Revisit_Neg_15_17==null){CS_Revisit_Neg_15_17_1=0;} else{CS_Revisit_Neg_15_17_1=Integer.parseInt(CS_Revisit_Neg_15_17);}
      if(CS_Revisit_Neg_Postnatal==null){CS_Revisit_Neg_Postnatal_1=0;} else{CS_Revisit_Neg_Postnatal_1=Integer.parseInt(CS_Revisit_Neg_Postnatal);}
      if(CS_Revisit_Neg_0_59M==null){CS_Revisit_Neg_0_59M_1=0;} else{CS_Revisit_Neg_0_59M_1=Integer.parseInt(CS_Revisit_Neg_0_59M);}
      if(CS_Revisit_Neg_5_15==null){CS_Revisit_Neg_5_15_1=0;} else{CS_Revisit_Neg_5_15_1=Integer.parseInt(CS_Revisit_Neg_5_15);}
        
        //redo the summations here
//        CS
      CS_Total_Adult = ""+(CS_New_Pos_Male_Adult_1+CS_New_Pos_Female_Adult_1+CS_New_Neg_Male_Adult_1+CS_New_Neg_Female_Adult_1+ CS_Revisit_Pos_Adult_1+CS_Revisit_Neg_Adult_1);
      
      CS_Total_15_17 = ""+(CS_New_Pos_Male_15_17_1+CS_New_Pos_Female_15_17_1+CS_New_Neg_Male_15_17_1+CS_New_Neg_Female_15_17_1+CS_Revisit_Pos_15_17_1+CS_Revisit_Neg_15_17_1);
      
      CS_Total_Postnatal = ""+(CS_New_Pos_Male_Postnatal_1+CS_New_Pos_Female_Postnatal_1+CS_New_Neg_Male_Postnatal_1+CS_New_Neg_Female_Postnatal_1+CS_Revisit_Pos_Postnatal_1+CS_Revisit_Neg_Postnatal_1);
      
      CS_Total_0_59M = ""+(CS_New_Pos_Male_0_59M_1+CS_New_Pos_Female_0_59M_1+CS_New_Neg_Male_0_59M_1+CS_New_Neg_Female_0_59M_1+CS_Revisit_Pos_0_59M_1+CS_Revisit_Neg_0_59M_1);
      
      CS_Total_5_15 = ""+(CS_New_Pos_Male_5_15_1+CS_New_Pos_Female_5_15_1+CS_New_Neg_Male_5_15_1+CS_New_Neg_Female_5_15_1+CS_Revisit_Pos_5_15_1+CS_Revisit_Neg_5_15_1);
      
      CS_Total_Readmission = ""+(CS_New_Pos_Readmission_1+CS_New_Neg_Readmission_1);
      
      CS_Total_Relapse = ""+(CS_New_Pos_Relapse_1+CS_New_Neg_Relapse_1);
      
      CS_Total_LinkedOVC = ""+(CS_New_Pos_LinkedOVC_1+CS_New_Neg_LinkedOVC_1);  
        
//     NH1
      
        if( NH1_Pos_SAM_Adult==null){ NH1_Pos_SAM_Adult_1=0;} else{ NH1_Pos_SAM_Adult_1=Integer.parseInt(NH1_Pos_SAM_Adult); }
        if( NH1_Pos_SAM_15_17==null){ NH1_Pos_SAM_15_17_1=0;} else{ NH1_Pos_SAM_15_17_1=Integer.parseInt(NH1_Pos_SAM_15_17); }
        if( NH1_Pos_SAM_Postnatal==null){ NH1_Pos_SAM_Postnatal_1=0;} else{ NH1_Pos_SAM_Postnatal_1=Integer.parseInt(NH1_Pos_SAM_Postnatal); }
        if( NH1_Pos_SAM_0_59M==null){ NH1_Pos_SAM_0_59M_1=0;} else{ NH1_Pos_SAM_0_59M_1=Integer.parseInt(NH1_Pos_SAM_0_59M); }
        if( NH1_Pos_SAM_5_15==null){ NH1_Pos_SAM_5_15_1=0;} else{ NH1_Pos_SAM_5_15_1=Integer.parseInt(NH1_Pos_SAM_5_15); }
        
        
        if( NH1_Pos_MAM_Adult==null){ NH1_Pos_MAM_Adult_1=0;} else{ NH1_Pos_MAM_Adult_1=Integer.parseInt(NH1_Pos_MAM_Adult); }
        if( NH1_Pos_MAM_15_17==null){ NH1_Pos_MAM_15_17_1=0;} else{ NH1_Pos_MAM_15_17_1=Integer.parseInt(NH1_Pos_MAM_15_17); }
        if( NH1_Pos_MAM_Postnatal==null){ NH1_Pos_MAM_Postnatal_1=0;} else{ NH1_Pos_MAM_Postnatal_1=Integer.parseInt(NH1_Pos_MAM_Postnatal); }
        if( NH1_Pos_MAM_0_59M==null){ NH1_Pos_MAM_0_59M_1=0;} else{ NH1_Pos_MAM_0_59M_1=Integer.parseInt(NH1_Pos_MAM_0_59M); }
        if( NH1_Pos_MAM_5_15==null){ NH1_Pos_MAM_5_15_1=0;} else{ NH1_Pos_MAM_5_15_1=Integer.parseInt(NH1_Pos_MAM_5_15); }
        
        if( NH1_Neg_SAM_Adult==null){ NH1_Neg_SAM_Adult_1=0;} else{ NH1_Neg_SAM_Adult_1=Integer.parseInt(NH1_Neg_SAM_Adult); }
        if( NH1_Neg_SAM_15_17==null){ NH1_Neg_SAM_15_17_1=0;} else{ NH1_Neg_SAM_15_17_1=Integer.parseInt(NH1_Neg_SAM_15_17); }
        if( NH1_Neg_SAM_Postnatal==null){ NH1_Neg_SAM_Postnatal_1=0;} else{ NH1_Neg_SAM_Postnatal_1=Integer.parseInt(NH1_Neg_SAM_Postnatal); }
        if( NH1_Neg_SAM_0_59M==null){ NH1_Neg_SAM_0_59M_1=0;} else{ NH1_Neg_SAM_0_59M_1=Integer.parseInt(NH1_Neg_SAM_0_59M); }
        if( NH1_Neg_SAM_5_15==null){ NH1_Neg_SAM_5_15_1=0;} else{ NH1_Neg_SAM_5_15_1=Integer.parseInt(NH1_Neg_SAM_5_15); }
        
        if( NH1_Neg_MAM_Adult==null){ NH1_Neg_MAM_Adult_1=0;} else{ NH1_Neg_MAM_Adult_1=Integer.parseInt(NH1_Neg_MAM_Adult); }
        if( NH1_Neg_MAM_15_17==null){ NH1_Neg_MAM_15_17_1=0;} else{ NH1_Neg_MAM_15_17_1=Integer.parseInt(NH1_Neg_MAM_15_17); }
        if( NH1_Neg_MAM_Postnatal==null){ NH1_Neg_MAM_Postnatal_1=0;} else{ NH1_Neg_MAM_Postnatal_1=Integer.parseInt(NH1_Neg_MAM_Postnatal); }
        if( NH1_Neg_MAM_0_59M==null){ NH1_Neg_MAM_0_59M_1=0;} else{ NH1_Neg_MAM_0_59M_1=Integer.parseInt(NH1_Neg_MAM_0_59M); }
        if( NH1_Neg_MAM_5_15==null){ NH1_Neg_MAM_5_15_1=0;} else{ NH1_Neg_MAM_5_15_1=Integer.parseInt(NH1_Neg_MAM_5_15); }   
        
        
         NH1_Pos_SAM_Total  = ""+(NH1_Pos_SAM_Adult_1+NH1_Pos_SAM_15_17_1+NH1_Pos_SAM_Postnatal_1+NH1_Pos_SAM_0_59M_1+NH1_Pos_SAM_5_15_1);
         NH1_Pos_MAM_Total  = ""+(NH1_Pos_MAM_Adult_1+NH1_Pos_MAM_15_17_1+NH1_Pos_MAM_Postnatal_1+NH1_Pos_MAM_0_59M_1+NH1_Pos_MAM_5_15_1);
         NH1_Neg_SAM_Total  = ""+(NH1_Neg_SAM_Adult_1+NH1_Neg_SAM_15_17_1+NH1_Neg_SAM_Postnatal_1+NH1_Neg_SAM_0_59M_1+NH1_Neg_SAM_5_15_1);
         NH1_Neg_MAM_Total  = ""+(NH1_Neg_MAM_Adult_1+NH1_Neg_MAM_15_17_1+NH1_Neg_MAM_Postnatal_1+NH1_Neg_MAM_0_59M_1+NH1_Neg_MAM_5_15_1);
        
         
        NH1_Total_Adult = ""+(NH1_Pos_SAM_Adult_1+NH1_Pos_MAM_Adult_1+NH1_Neg_SAM_Adult_1+NH1_Neg_MAM_Adult_1);
        NH1_Total_15_17 = ""+(NH1_Pos_SAM_15_17_1+NH1_Pos_MAM_15_17_1+NH1_Neg_SAM_15_17_1+NH1_Neg_MAM_15_17_1);
        NH1_Total_Postnatal = ""+(NH1_Pos_SAM_Postnatal_1+NH1_Pos_MAM_Postnatal_1+NH1_Neg_SAM_Postnatal_1+NH1_Neg_MAM_Postnatal_1);
        NH1_Total_0_59M = ""+(NH1_Pos_SAM_0_59M_1+NH1_Pos_MAM_0_59M_1+NH1_Neg_SAM_0_59M_1+NH1_Neg_MAM_0_59M_1);
        NH1_Total_5_15 = ""+(NH1_Pos_SAM_5_15_1+NH1_Pos_MAM_5_15_1+NH1_Neg_SAM_5_15_1+NH1_Neg_MAM_5_15_1);
        
        NH1_Grand_Total = ""+(Integer.parseInt(NH1_Total_Adult)+Integer.parseInt(NH1_Total_15_17)+Integer.parseInt(NH1_Total_Postnatal)+Integer.parseInt(NH1_Total_0_59M)+Integer.parseInt(NH1_Total_5_15));

        if( IFP_0_6_EBF_Pos==null) { IFP_0_6_EBF_Pos_1=0;} else{ IFP_0_6_EBF_Pos_1=Integer.parseInt(IFP_0_6_EBF_Pos); }
        if( IFP_0_6_EBF_Neg==null) { IFP_0_6_EBF_Neg_1=0;} else{ IFP_0_6_EBF_Neg_1=Integer.parseInt(IFP_0_6_EBF_Neg); }
        if( IFP_0_6_ERF_Pos==null) { IFP_0_6_ERF_Pos_1=0;} else{ IFP_0_6_ERF_Pos_1=Integer.parseInt(IFP_0_6_ERF_Pos); }
        if( IFP_0_6_ERF_Neg==null) { IFP_0_6_ERF_Neg_1=0;} else{ IFP_0_6_ERF_Neg_1=Integer.parseInt(IFP_0_6_ERF_Neg); }
        if( IFP_0_6_MF_Pos==null) { IFP_0_6_MF_Pos_1=0;} else{ IFP_0_6_MF_Pos_1=Integer.parseInt(IFP_0_6_MF_Pos); }
        if( IFP_0_6_MF_Neg==null) { IFP_0_6_MF_Neg_1=0;} else{ IFP_0_6_MF_Neg_1=Integer.parseInt(IFP_0_6_MF_Neg); }
        
        if( IFP_6_12_BF_Pos==null) { IFP_6_12_BF_Pos_1=0;} else{ IFP_6_12_BF_Pos_1=Integer.parseInt(IFP_6_12_BF_Pos); }
        if( IFP_6_12_BF_Neg==null) { IFP_6_12_BF_Neg_1=0;} else{ IFP_6_12_BF_Neg_1=Integer.parseInt(IFP_6_12_BF_Neg); }
        if( IFP_6_12_NBF_Pos==null) { IFP_6_12_NBF_Pos_1=0;} else{ IFP_6_12_NBF_Pos_1=Integer.parseInt(IFP_6_12_NBF_Pos); }
        if( IFP_6_12_NBF_Neg==null) { IFP_6_12_NBF_Neg_1=0;} else{ IFP_6_12_NBF_Neg_1=Integer.parseInt(IFP_6_12_NBF_Neg); }
        if( IFP_6_12_NotKnown_Pos==null) { IFP_6_12_NotKnown_Pos_1=0;} else{ IFP_6_12_NotKnown_Pos_1=Integer.parseInt(IFP_6_12_NotKnown_Pos); }
        if( IFP_6_12_NotKnown_Neg==null) { IFP_6_12_NotKnown_Neg_1=0;} else{ IFP_6_12_NotKnown_Neg_1=Integer.parseInt(IFP_6_12_NotKnown_Neg); }
        if( IFP_6_12_BCF_Pos==null) { IFP_6_12_BCF_Pos_1=0;} else{ IFP_6_12_BCF_Pos_1=Integer.parseInt(IFP_6_12_BCF_Pos); }
        if( IFP_6_12_BCF_Neg==null) { IFP_6_12_BCF_Neg_1=0;} else{ IFP_6_12_BCF_Neg_1=Integer.parseInt(IFP_6_12_BCF_Neg); }
        
         IFP_0_6_SubTotal_Pos  = ""+(IFP_0_6_EBF_Pos_1+ IFP_0_6_ERF_Pos_1+ IFP_0_6_MF_Pos_1);
         IFP_0_6_SubTotal_Neg  = ""+( IFP_0_6_EBF_Neg_1+ IFP_0_6_ERF_Neg_1+ IFP_0_6_MF_Neg_1);
         IFP_6_12_SubTotal_Pos  = ""+( IFP_6_12_BF_Pos_1+ IFP_6_12_NBF_Pos_1+ IFP_6_12_NotKnown_Pos_1+ IFP_6_12_BCF_Pos_1);
         IFP_6_12_SubTotal_Neg  = ""+( IFP_6_12_BF_Neg_1+ IFP_6_12_NBF_Neg_1+ IFP_6_12_NotKnown_Neg_1+ IFP_6_12_BCF_Neg_1);
//NH2

        if(NH2_TCM_Adults15_17_Pos==null){ NH2_TCM_Adults15_17_Pos_1=0;} else{ NH2_TCM_Adults15_17_Pos_1=Integer.parseInt(NH2_TCM_Adults15_17_Pos); }
        if(NH2_TCM_Postnatal_Pos==null){ NH2_TCM_Postnatal_Pos_1=0;} else{ NH2_TCM_Postnatal_Pos_1=Integer.parseInt(NH2_TCM_Postnatal_Pos); }
        if(NH2_TCM_AllAdults_Neg==null){ NH2_TCM_AllAdults_Neg_1=0;} else{ NH2_TCM_AllAdults_Neg_1=Integer.parseInt(NH2_TCM_AllAdults_Neg); }
        if(NH2_TCM_0_59M_Pos==null){ NH2_TCM_0_59M_Pos_1=0;} else{ NH2_TCM_0_59M_Pos_1=Integer.parseInt(NH2_TCM_0_59M_Pos); }
        if(NH2_TCM_5_15_Pos==null){ NH2_TCM_5_15_Pos_1=0;} else{ NH2_TCM_5_15_Pos_1=Integer.parseInt(NH2_TCM_5_15_Pos); }
        if(NH2_TCM_0_59M_Neg==null){ NH2_TCM_0_59M_Neg_1=0;} else{ NH2_TCM_0_59M_Neg_1=Integer.parseInt(NH2_TCM_0_59M_Neg); }
        if(NH2_TCM_5_15_Neg==null){ NH2_TCM_5_15_Neg_1=0;} else{ NH2_TCM_5_15_Neg_1=Integer.parseInt(NH2_TCM_5_15_Neg); }
        
        if(NH2_RUTF_Adults15_17_Pos==null){ NH2_RUTF_Adults15_17_Pos_1=0;} else{ NH2_RUTF_Adults15_17_Pos_1=Integer.parseInt(NH2_RUTF_Adults15_17_Pos); }
        if(NH2_RUTF_Postnatal_Pos==null){ NH2_RUTF_Postnatal_Pos_1=0;} else{ NH2_RUTF_Postnatal_Pos_1=Integer.parseInt(NH2_RUTF_Postnatal_Pos); }
        if(NH2_RUTF_AllAdults_Neg==null){ NH2_RUTF_AllAdults_Neg_1=0;} else{ NH2_RUTF_AllAdults_Neg_1=Integer.parseInt(NH2_RUTF_AllAdults_Neg); }
        if(NH2_RUTF_0_59M_Pos==null){ NH2_RUTF_0_59M_Pos_1=0;} else{ NH2_RUTF_0_59M_Pos_1=Integer.parseInt(NH2_RUTF_0_59M_Pos); }
        if(NH2_RUTF_5_15_Pos==null){ NH2_RUTF_5_15_Pos_1=0;} else{ NH2_RUTF_5_15_Pos_1=Integer.parseInt(NH2_RUTF_5_15_Pos); }
        if(NH2_RUTF_0_59M_Neg==null){ NH2_RUTF_0_59M_Neg_1=0;} else{ NH2_RUTF_0_59M_Neg_1=Integer.parseInt(NH2_RUTF_0_59M_Neg); }
        if(NH2_RUTF_5_15_Neg==null){ NH2_RUTF_5_15_Neg_1=0;} else{ NH2_RUTF_5_15_Neg_1=Integer.parseInt(NH2_RUTF_5_15_Neg); }
        
        if(NH2_RUSF_Adults15_17_Pos==null){ NH2_RUSF_Adults15_17_Pos_1=0;} else{ NH2_RUSF_Adults15_17_Pos_1=Integer.parseInt(NH2_RUSF_Adults15_17_Pos); }
        if(NH2_RUSF_Postnatal_Pos==null){ NH2_RUSF_Postnatal_Pos_1=0;} else{ NH2_RUSF_Postnatal_Pos_1=Integer.parseInt(NH2_RUSF_Postnatal_Pos); }
        if(NH2_RUSF_AllAdults_Neg==null){ NH2_RUSF_AllAdults_Neg_1=0;} else{ NH2_RUSF_AllAdults_Neg_1=Integer.parseInt(NH2_RUSF_AllAdults_Neg); }
        if(NH2_RUSF_0_59M_Pos==null){ NH2_RUSF_0_59M_Pos_1=0;} else{ NH2_RUSF_0_59M_Pos_1=Integer.parseInt(NH2_RUSF_0_59M_Pos); }
        if(NH2_RUSF_5_15_Pos==null){ NH2_RUSF_5_15_Pos_1=0;} else{ NH2_RUSF_5_15_Pos_1=Integer.parseInt(NH2_RUSF_5_15_Pos); }
        if(NH2_RUSF_0_59M_Neg==null){ NH2_RUSF_0_59M_Neg_1=0;} else{ NH2_RUSF_0_59M_Neg_1=Integer.parseInt(NH2_RUSF_0_59M_Neg); }
        if(NH2_RUSF_5_15_Neg==null){ NH2_RUSF_5_15_Neg_1=0;} else{ NH2_RUSF_5_15_Neg_1=Integer.parseInt(NH2_RUSF_5_15_Neg); }
        
        if(NH2_FBFCSB_Adults15_17_Pos==null){ NH2_FBFCSB_Adults15_17_Pos_1=0;} else{ NH2_FBFCSB_Adults15_17_Pos_1=Integer.parseInt(NH2_FBFCSB_Adults15_17_Pos); }
        if(NH2_FBFCSB_Postnatal_Pos==null){ NH2_FBFCSB_Postnatal_Pos_1=0;} else{ NH2_FBFCSB_Postnatal_Pos_1=Integer.parseInt(NH2_FBFCSB_Postnatal_Pos); }
        if(NH2_FBFCSB_AllAdults_Neg==null){ NH2_FBFCSB_AllAdults_Neg_1=0;} else{ NH2_FBFCSB_AllAdults_Neg_1=Integer.parseInt(NH2_FBFCSB_AllAdults_Neg); }
        if(NH2_FBFCSB_0_59M_Pos==null){ NH2_FBFCSB_0_59M_Pos_1=0;} else{ NH2_FBFCSB_0_59M_Pos_1=Integer.parseInt(NH2_FBFCSB_0_59M_Pos); }
        if(NH2_FBFCSB_5_15_Pos==null){ NH2_FBFCSB_5_15_Pos_1=0;} else{ NH2_FBFCSB_5_15_Pos_1=Integer.parseInt(NH2_FBFCSB_5_15_Pos); }
        if(NH2_FBFCSB_0_59M_Neg==null){ NH2_FBFCSB_0_59M_Neg_1=0;} else{ NH2_FBFCSB_0_59M_Neg_1=Integer.parseInt(NH2_FBFCSB_0_59M_Neg); }
        if(NH2_FBFCSB_5_15_Neg==null){ NH2_FBFCSB_5_15_Neg_1=0;} else{ NH2_FBFCSB_5_15_Neg_1=Integer.parseInt(NH2_FBFCSB_5_15_Neg); }
        
        if(NH2_LNS_Adults15_17_Pos==null){ NH2_LNS_Adults15_17_Pos_1=0;} else{ NH2_LNS_Adults15_17_Pos_1=Integer.parseInt(NH2_LNS_Adults15_17_Pos); }
        if(NH2_LNS_Postnatal_Pos==null){ NH2_LNS_Postnatal_Pos_1=0;} else{ NH2_LNS_Postnatal_Pos_1=Integer.parseInt(NH2_LNS_Postnatal_Pos); }
        if(NH2_LNS_AllAdults_Neg==null){ NH2_LNS_AllAdults_Neg_1=0;} else{ NH2_LNS_AllAdults_Neg_1=Integer.parseInt(NH2_LNS_AllAdults_Neg); }
        if(NH2_LNS_0_59M_Pos==null){ NH2_LNS_0_59M_Pos_1=0;} else{ NH2_LNS_0_59M_Pos_1=Integer.parseInt(NH2_LNS_0_59M_Pos); }
        if(NH2_LNS_5_15_Pos==null){ NH2_LNS_5_15_Pos_1=0;} else{ NH2_LNS_5_15_Pos_1=Integer.parseInt(NH2_LNS_5_15_Pos); }
        if(NH2_LNS_0_59M_Neg==null){ NH2_LNS_0_59M_Neg_1=0;} else{ NH2_LNS_0_59M_Neg_1=Integer.parseInt(NH2_LNS_0_59M_Neg); }
        if(NH2_LNS_5_15_Neg==null){ NH2_LNS_5_15_Neg_1=0;} else{ NH2_LNS_5_15_Neg_1=Integer.parseInt(NH2_LNS_5_15_Neg); }
        
        if(NH2_Micronutrients_Adults15_17_Pos==null){ NH2_Micronutrients_Adults15_17_Pos_1=0;} else{ NH2_Micronutrients_Adults15_17_Pos_1=Integer.parseInt(NH2_Micronutrients_Adults15_17_Pos); }
        if(NH2_Micronutrients_Postnatal_Pos==null){ NH2_Micronutrients_Postnatal_Pos_1=0;} else{ NH2_Micronutrients_Postnatal_Pos_1=Integer.parseInt(NH2_Micronutrients_Postnatal_Pos); }
        if(NH2_Micronutrients_AllAdults_Neg==null){ NH2_Micronutrients_AllAdults_Neg_1=0;} else{NH2_Micronutrients_AllAdults_Neg_1 =Integer.parseInt(NH2_Micronutrients_AllAdults_Neg); }
        if(NH2_Micronutrients_0_59M_Pos==null){ NH2_Micronutrients_0_59M_Pos_1=0;} else{ NH2_Micronutrients_0_59M_Pos_1=Integer.parseInt(NH2_Micronutrients_0_59M_Pos); }
        if(NH2_Micronutrients_5_15_Pos==null){ NH2_Micronutrients_5_15_Pos_1=0;} else{ NH2_Micronutrients_5_15_Pos_1=Integer.parseInt(NH2_Micronutrients_5_15_Pos); }
        if(NH2_Micronutrients_0_59M_Neg==null){ NH2_Micronutrients_0_59M_Neg_1=0;} else{ NH2_Micronutrients_0_59M_Neg_1=Integer.parseInt(NH2_Micronutrients_0_59M_Neg); }
        if(NH2_Micronutrients_5_15_Neg==null){ NH2_Micronutrients_5_15_Neg_1=0;} else{ NH2_Micronutrients_5_15_Neg_1=Integer.parseInt(NH2_Micronutrients_5_15_Neg); }
        
        if(NH2_Others_Adults15_17_Pos==null){ NH2_Others_Adults15_17_Pos_1=0;} else{ NH2_Others_Adults15_17_Pos_1=Integer.parseInt(NH2_Others_Adults15_17_Pos); }
        if(NH2_Others_Postnatal_Pos==null){ NH2_Others_Postnatal_Pos_1=0;} else{ NH2_Others_Postnatal_Pos_1=Integer.parseInt(NH2_Others_Postnatal_Pos); }
        if(NH2_Others_AllAdults_Neg==null){ NH2_Others_AllAdults_Neg_1=0;} else{ NH2_Others_AllAdults_Neg_1=Integer.parseInt(NH2_Others_AllAdults_Neg); }
        if(NH2_Others_0_59M_Pos==null){ NH2_Others_0_59M_Pos_1=0;} else{ NH2_Others_0_59M_Pos_1=Integer.parseInt(NH2_Others_0_59M_Pos); }
        if(NH2_Others_5_15_Pos==null){ NH2_Others_5_15_Pos_1=0;} else{ NH2_Others_5_15_Pos_1=Integer.parseInt(NH2_Others_5_15_Pos); }
        if(NH2_Others_0_59M_Neg==null){ NH2_Others_0_59M_Neg_1=0;} else{ NH2_Others_0_59M_Neg_1=Integer.parseInt(NH2_Others_0_59M_Neg); }
        if(NH2_Others_5_15_Neg==null){ NH2_Others_5_15_Neg_1=0;} else{ NH2_Others_5_15_Neg_1=Integer.parseInt(NH2_Others_5_15_Neg); }

         NH2_TCM_Total  = ""+(NH2_TCM_Adults15_17_Pos_1+ NH2_TCM_Postnatal_Pos_1+ NH2_TCM_AllAdults_Neg_1+ NH2_TCM_0_59M_Pos_1+ NH2_TCM_5_15_Pos_1+ NH2_TCM_0_59M_Neg_1+ NH2_TCM_5_15_Neg_1);
         NH2_RUTF_Total  = ""+( NH2_RUTF_Adults15_17_Pos_1+ NH2_RUTF_Postnatal_Pos_1+ NH2_RUTF_AllAdults_Neg_1+ NH2_RUTF_0_59M_Pos_1+ NH2_RUTF_5_15_Pos_1+ NH2_RUTF_0_59M_Neg_1+ NH2_RUTF_5_15_Neg_1);
         NH2_RUSF_Total  = ""+( NH2_RUSF_Adults15_17_Pos_1+ NH2_RUSF_Postnatal_Pos_1+ NH2_RUSF_AllAdults_Neg_1+ NH2_RUSF_0_59M_Pos_1+ NH2_RUSF_5_15_Pos_1+ NH2_RUSF_0_59M_Neg_1+ NH2_RUSF_5_15_Neg_1);
         NH2_FBFCSB_Total  = ""+( NH2_FBFCSB_Adults15_17_Pos_1+ NH2_FBFCSB_Postnatal_Pos_1+ NH2_FBFCSB_AllAdults_Neg_1+ NH2_FBFCSB_0_59M_Pos_1+ NH2_FBFCSB_5_15_Pos_1+ NH2_FBFCSB_0_59M_Neg_1+ NH2_FBFCSB_5_15_Neg_1);
         NH2_LNS_Total  = ""+( NH2_LNS_Adults15_17_Pos_1+ NH2_LNS_Postnatal_Pos_1+ NH2_LNS_AllAdults_Neg_1+ NH2_LNS_0_59M_Pos_1+ NH2_LNS_5_15_Pos_1+ NH2_LNS_0_59M_Neg_1+ NH2_LNS_5_15_Neg_1);
         NH2_Micronutrients_Total  = ""+( NH2_Micronutrients_Adults15_17_Pos_1+ NH2_Micronutrients_Postnatal_Pos_1+ NH2_Micronutrients_AllAdults_Neg_1+ NH2_Micronutrients_0_59M_Pos_1+ NH2_Micronutrients_5_15_Pos_1+ NH2_Micronutrients_0_59M_Neg_1+ NH2_Micronutrients_5_15_Neg_1);
         NH2_Others_Total  = ""+( NH2_Others_Adults15_17_Pos_1+ NH2_Others_Postnatal_Pos_1+ NH2_Others_AllAdults_Neg_1+ NH2_Others_0_59M_Pos_1+ NH2_Others_5_15_Pos_1+ NH2_Others_0_59M_Neg_1+ NH2_Others_5_15_Neg_1);
        
         NH2_Total_Adults15_17_Pos  = ""+(NH2_TCM_Adults15_17_Pos_1+NH2_RUTF_Adults15_17_Pos_1+NH2_RUSF_Adults15_17_Pos_1+NH2_FBFCSB_Adults15_17_Pos_1+NH2_LNS_Adults15_17_Pos_1+NH2_Micronutrients_Adults15_17_Pos_1+NH2_Others_Adults15_17_Pos_1);
         NH2_Total_Postnatal_Pos  = ""+(NH2_TCM_Postnatal_Pos_1+NH2_RUTF_Postnatal_Pos_1+NH2_RUSF_Postnatal_Pos_1+NH2_FBFCSB_Postnatal_Pos_1+NH2_LNS_Postnatal_Pos_1+NH2_Micronutrients_Postnatal_Pos_1+NH2_Others_Postnatal_Pos_1);
         NH2_Total_AllAdults_Neg  = ""+(NH2_TCM_AllAdults_Neg_1+NH2_RUTF_AllAdults_Neg_1+NH2_RUSF_AllAdults_Neg_1+NH2_FBFCSB_AllAdults_Neg_1+NH2_LNS_AllAdults_Neg_1+NH2_Micronutrients_AllAdults_Neg_1+NH2_Others_AllAdults_Neg_1);
         NH2_Total_0_59M_Pos  = ""+(NH2_TCM_0_59M_Pos_1+NH2_RUTF_0_59M_Pos_1+NH2_RUSF_0_59M_Pos_1+NH2_FBFCSB_0_59M_Pos_1+NH2_LNS_0_59M_Pos_1+NH2_Micronutrients_0_59M_Pos_1+NH2_Others_0_59M_Pos_1);
         NH2_Total_5_15_Pos  = ""+(NH2_TCM_5_15_Pos_1+NH2_RUTF_5_15_Pos_1+NH2_RUSF_5_15_Pos_1+NH2_FBFCSB_5_15_Pos_1+NH2_LNS_5_15_Pos_1+NH2_Micronutrients_5_15_Pos_1+NH2_Others_5_15_Pos_1);
         NH2_Total_0_59M_Neg  = ""+(NH2_TCM_0_59M_Neg_1+NH2_RUTF_0_59M_Neg_1+NH2_RUSF_0_59M_Neg_1+NH2_FBFCSB_0_59M_Neg_1+NH2_LNS_0_59M_Neg_1+NH2_Micronutrients_0_59M_Neg_1+NH2_Others_0_59M_Neg_1);
         NH2_Total_5_15_Neg  = ""+(NH2_TCM_5_15_Neg_1+NH2_RUTF_5_15_Neg_1+NH2_RUSF_5_15_Neg_1+NH2_FBFCSB_5_15_Neg_1+NH2_LNS_5_15_Neg_1+NH2_Micronutrients_5_15_Neg_1+NH2_Others_5_15_Neg_1);
         NH2_Grand_Total  = ""+(Integer.parseInt(NH2_Total_Adults15_17_Pos)+Integer.parseInt(NH2_Total_Postnatal_Pos)+Integer.parseInt(NH2_Total_AllAdults_Neg)+Integer.parseInt(NH2_Total_0_59M_Pos)+Integer.parseInt(NH2_Total_5_15_Pos)+Integer.parseInt(NH2_Total_0_59M_Neg)+Integer.parseInt(NH2_Total_5_15_Neg));
        
//        end of summations
        
            
        String validator = "UPDATE moh733b SET CS_New_Pos_Male_Adult =?, CS_New_Pos_Male_15_17 =?, CS_New_Pos_Male_Postnatal =?, CS_New_Pos_Male_0_59M =?, "+
        "CS_New_Pos_Male_5_15 =?, CS_New_Pos_Female_Adult =?, CS_New_Pos_Female_15_17 =?, CS_New_Pos_Female_Postnatal =?,"
        +"CS_New_Pos_Female_0_59M =?, CS_New_Pos_Female_5_15 =?, CS_New_Pos_Readmission =?, CS_New_Pos_Relapse =?,"+ 
        "CS_New_Pos_LinkedOVC =?, CS_New_Neg_Male_Adult =?, CS_New_Neg_Male_15_17 =?, CS_New_Neg_Male_Postnatal =?, "+
        "CS_New_Neg_Male_0_59M =?, CS_New_Neg_Male_5_15 =?, CS_New_Neg_Female_Adult =?, CS_New_Neg_Female_15_17 =?, "+
        "CS_New_Neg_Female_Postnatal =?, CS_New_Neg_Female_0_59M =?, CS_New_Neg_Female_5_15 =?, CS_New_Neg_Readmission =?, "+
        "CS_New_Neg_Relapse =?, CS_New_Neg_LinkedOVC =?, CS_Revisit_Pos_Adult =?, CS_Revisit_Pos_15_17 =?, "+
        "CS_Revisit_Pos_Postnatal =?, CS_Revisit_Pos_0_59M =?, CS_Revisit_Pos_5_15 =?, CS_Revisit_Pos_Readmission =?, "+
        "CS_Revisit_Pos_Relapse =?, CS_Revisit_Pos_LinkedOVC =?, CS_Revisit_Neg_Adult =?, CS_Revisit_Neg_15_17 =?, "+
        "CS_Revisit_Neg_Postnatal =?, CS_Revisit_Neg_0_59M =?, CS_Revisit_Neg_5_15 =?, CS_Revisit_Neg_Readmission =?, "+
        "CS_Revisit_Neg_Relapse =?, CS_Revisit_Neg_LinkedOVC =?, CS_Total_Adult =?, CS_Total_15_17 =?,"+ 
        "CS_Total_Postnatal =?, CS_Total_0_59M =?, CS_Total_5_15 =?, CS_Total_Readmission =?, "+
        "CS_Total_Relapse =?, CS_Total_LinkedOVC =?, "+
        "NH1_Pos_SAM_Adult =?, NH1_Pos_SAM_15_17 =?, "+
        "NH1_Pos_SAM_Postnatal =?, NH1_Pos_SAM_0_59M =?, NH1_Pos_SAM_5_15 =?, NH1_Pos_SAM_Total =?, NH1_Pos_MAM_Adult =?, "+
        "NH1_Pos_MAM_15_17 =?, NH1_Pos_MAM_Postnatal =?, NH1_Pos_MAM_0_59M =?, NH1_Pos_MAM_5_15 =?, NH1_Pos_MAM_Total =?, "+
        "NH1_Neg_SAM_Adult =?, NH1_Neg_SAM_15_17 =?, NH1_Neg_SAM_Postnatal =?, NH1_Neg_SAM_0_59M =?, NH1_Neg_SAM_5_15 =?, "+
        "NH1_Neg_SAM_Total =?, NH1_Neg_MAM_Adult =?, NH1_Neg_MAM_15_17 =?, NH1_Neg_MAM_Postnatal =?, NH1_Neg_MAM_0_59M =?, "+
        "NH1_Neg_MAM_5_15 =?, NH1_Neg_MAM_Total =?, "+
        "IFP_0_6_EBF_Pos =?, IFP_0_6_EBF_Neg =?, "+
        "IFP_0_6_ERF_Pos =?, IFP_0_6_ERF_Neg =?, IFP_0_6_MF_Pos =?, IFP_0_6_MF_Neg =?, IFP_0_6_SubTotal_Pos =?, IFP_0_6_SubTotal_Neg =?, IFP_6_12_BF_Pos =?, "+
        "IFP_6_12_BF_Neg =?, IFP_6_12_NBF_Pos =?, IFP_6_12_NotKnown_Pos =?, IFP_6_12_NotKnown_Neg =?, IFP_6_12_BCF_Pos =?, IFP_6_12_BCF_Neg =?, "+
        "IFP_6_12_SubTotal_Pos =?, IFP_6_12_SubTotal_Neg =?,NH2_TCM_Adults15_17_Pos =?, "+
        "NH2_TCM_Postnatal_Pos =?, NH2_TCM_AllAdults_Neg =?, NH2_TCM_0_59M_Pos =?, NH2_TCM_5_15_Pos =?, NH2_TCM_0_59M_Neg =?, NH2_TCM_5_15_Neg=?, "+
        "NH2_TCM_Total =?, NH2_RUTF_Adults15_17_Pos =?, NH2_RUTF_Postnatal_Pos =?, NH2_RUTF_AllAdults_Neg =?, NH2_RUTF_0_59M_Pos=?, "+
        "NH2_RUTF_5_15_Pos =?, NH2_RUTF_0_59M_Neg =?, NH2_RUTF_5_15_Neg =?, NH2_RUTF_Total =?, NH2_RUSF_Adults15_17_Pos =?, NH2_RUSF_Postnatal_Pos =?,"+ 
        "NH2_RUSF_AllAdults_Neg =?, NH2_RUSF_0_59M_Pos =?, NH2_RUSF_5_15_Pos =?, NH2_RUSF_0_59M_Neg =?, NH2_RUSF_5_15_Neg =?, NH2_RUSF_Total =?, "+
        "NH2_FBFCSB_Adults15_17_Pos =?, NH2_FBFCSB_Postnatal_Pos =?, NH2_FBFCSB_AllAdults_Neg =?, NH2_FBFCSB_0_59M_Pos =?, NH2_FBFCSB_5_15_Pos =?, "+
        "NH2_FBFCSB_0_59M_Neg =?, NH2_FBFCSB_5_15_Neg =?, NH2_FBFCSB_Total =?, NH2_LNS_Adults15_17_Pos =?, NH2_LNS_Postnatal_Pos =?, NH2_LNS_AllAdults_Neg =?, "+
        "NH2_LNS_0_59M_Pos =?, NH2_LNS_5_15_Pos =?, NH2_LNS_0_59M_Neg =?, NH2_LNS_5_15_Neg =?, NH2_LNS_Total =?, NH2_Micronutrients_Adults15_17_Pos =?, "+
        "NH2_Micronutrients_Postnatal_Pos =?, NH2_Micronutrients_AllAdults_Neg =?, NH2_Micronutrients_0_59M_Pos =?, NH2_Micronutrients_5_15_Pos =?, "+
        "NH2_Micronutrients_0_59M_Neg =?, NH2_Micronutrients_5_15_Neg =?, NH2_Micronutrients_Total =?, NH2_Others_Adults15_17_Pos =?, NH2_Others_Postnatal_Pos =?, "+
        "NH2_Others_AllAdults_Neg =?, NH2_Others_0_59M_Pos =?, NH2_Others_5_15_Pos =?, NH2_Others_0_59M_Neg =?, NH2_Others_5_15_Neg =?, NH2_Others_Total =?,"+ 
        "NH2_Total_Adults15_17_Pos =?, NH2_Total_Postnatal_Pos =?, NH2_Total_AllAdults_Neg =?, NH2_Total_0_59M_Pos =?, NH2_Total_5_15_Pos =?, NH2_Total_0_59M_Neg =?, "+
        "NH2_Total_5_15_Neg =?, NH2_Grand_Total =?,LTF_Adult_15_17 =?, LTF_Pregnant =?, LTF_Postnatal =?, "+
        "LTF_0_59M =?, LTF_5_15 =?, RM_Adult_15_17 =?, RM_Pregnant =?, RM_Postnatal =?, RM_0_59M =?, RM_5_15=?,NH1_Total_Adult=?,NH1_Total_15_17=?,"+
        "NH1_Total_Postnatal=?,NH1_Total_0_59M=?,NH1_Total_5_15=?,NH1_Grand_Total=?,IFP_6_12_NBF_Neg=?, "+
        "isValidated=?, reporting_officer=?, designation=?, reporting_date=? "+
        "WHERE id=?";
        
       conn.pst=conn.conn.prepareStatement(validator); 
        
        conn.pst.setString(1 ,CS_New_Pos_Male_Adult);
        conn.pst.setString(2 , CS_New_Pos_Male_15_17);
        conn.pst.setString(3 , CS_New_Pos_Male_Postnatal);
        conn.pst.setString(4 , CS_New_Pos_Male_0_59M);
        conn.pst.setString(5 , CS_New_Pos_Male_5_15);
        conn.pst.setString(6 , CS_New_Pos_Female_Adult);
        conn.pst.setString(7 , CS_New_Pos_Female_15_17);
        conn.pst.setString(8 , CS_New_Pos_Female_Postnatal);
        conn.pst.setString(9 , CS_New_Pos_Female_0_59M);
        conn.pst.setString(10 , CS_New_Pos_Female_5_15);
        
        conn.pst.setString(11 , CS_New_Pos_Readmission);
        conn.pst.setString(12 , CS_New_Pos_Relapse);
        conn.pst.setString(13 , CS_New_Pos_LinkedOVC);
        conn.pst.setString(14 , CS_New_Neg_Male_Adult);
        conn.pst.setString(15 , CS_New_Neg_Male_15_17);
        conn.pst.setString(16 , CS_New_Neg_Male_Postnatal);
        conn.pst.setString(17 , CS_New_Neg_Male_0_59M);
        conn.pst.setString(18 , CS_New_Neg_Male_5_15);
        conn.pst.setString(19 , CS_New_Neg_Female_Adult);
        conn.pst.setString(20 , CS_New_Neg_Female_15_17);
        
        conn.pst.setString(21 , CS_New_Neg_Female_Postnatal);
        conn.pst.setString(22 , CS_New_Neg_Female_0_59M);
        conn.pst.setString(23 , CS_New_Neg_Female_5_15);
        conn.pst.setString(24 , CS_New_Neg_Readmission);
        conn.pst.setString(25 , CS_New_Neg_Relapse);
        conn.pst.setString(26 , CS_New_Neg_LinkedOVC);
        conn.pst.setString(27 , CS_Revisit_Pos_Adult);
        conn.pst.setString(28 , CS_Revisit_Pos_15_17);
        conn.pst.setString(29 , CS_Revisit_Pos_Postnatal);
        conn.pst.setString(30 , CS_Revisit_Pos_0_59M);
        
        conn.pst.setString(31 , CS_Revisit_Pos_5_15);
        conn.pst.setString(32 , CS_Revisit_Pos_Readmission);
        conn.pst.setString(33 , CS_Revisit_Pos_Relapse);
        conn.pst.setString(34 , CS_Revisit_Pos_Linked_OVC);
        conn.pst.setString(35 , CS_Revisit_Neg_Adult);
        conn.pst.setString(36 , CS_Revisit_Neg_15_17);
        conn.pst.setString(37 , CS_Revisit_Neg_Postnatal);
        conn.pst.setString(38 , CS_Revisit_Neg_0_59M);
        conn.pst.setString(39 , CS_Revisit_Neg_5_15);
        conn.pst.setString(40 , CS_Revisit_Neg_Readmission);
        
        conn.pst.setString(41 , CS_Revisit_Neg_Relapse);
        conn.pst.setString(42 , CS_Revisit_Neg_LinkedOVC);
        conn.pst.setString(43 , CS_Total_Adult);
        conn.pst.setString(44 , CS_Total_15_17);
        conn.pst.setString(45 , CS_Total_Postnatal);
        conn.pst.setString(46 , CS_Total_0_59M);
        conn.pst.setString(47 , CS_Total_5_15);
        conn.pst.setString(48 , CS_Total_Readmission);
        conn.pst.setString(49 , CS_Total_Relapse);
        conn.pst.setString(50 , CS_Total_LinkedOVC);
        
        conn.pst.setString(51 , NH1_Pos_SAM_Adult);
        conn.pst.setString(52 , NH1_Pos_SAM_15_17);
        conn.pst.setString(53 , NH1_Pos_SAM_Postnatal);
        conn.pst.setString(54 , NH1_Pos_SAM_0_59M);
        conn.pst.setString( 55, NH1_Pos_SAM_5_15);
        conn.pst.setString( 56, NH1_Pos_SAM_Total);
        conn.pst.setString( 57, NH1_Pos_MAM_Adult);
        conn.pst.setString( 58, NH1_Pos_MAM_15_17);
        conn.pst.setString( 59, NH1_Pos_MAM_Postnatal);
        conn.pst.setString( 60, NH1_Pos_MAM_0_59M);
        
        conn.pst.setString( 61, NH1_Pos_MAM_5_15);
        conn.pst.setString( 62, NH1_Pos_MAM_Total);
        conn.pst.setString( 63, NH1_Neg_SAM_Adult);
        conn.pst.setString( 64, NH1_Neg_SAM_15_17);
        conn.pst.setString( 65, NH1_Neg_SAM_Postnatal);
        conn.pst.setString( 66, NH1_Neg_SAM_0_59M);
        conn.pst.setString( 67, NH1_Neg_SAM_5_15);
        conn.pst.setString( 68, NH1_Neg_SAM_Total);
        conn.pst.setString( 69, NH1_Neg_MAM_Adult);
        conn.pst.setString( 70, NH1_Neg_MAM_15_17);
        
        conn.pst.setString( 71, NH1_Neg_MAM_Postnatal);
        conn.pst.setString( 72, NH1_Neg_MAM_0_59M);
        conn.pst.setString( 73, NH1_Neg_MAM_5_15);
        conn.pst.setString( 74, NH1_Neg_MAM_Total);
        conn.pst.setString( 75, IFP_0_6_EBF_Pos);
        conn.pst.setString( 76, IFP_0_6_EBF_Neg);
        conn.pst.setString( 77, IFP_0_6_ERF_Pos);
        conn.pst.setString( 78, IFP_0_6_ERF_Neg);
        conn.pst.setString( 79, IFP_0_6_MF_Pos);
        conn.pst.setString( 80, IFP_0_6_MF_Neg);
        
        conn.pst.setString( 81, IFP_0_6_SubTotal_Pos);
        conn.pst.setString( 82, IFP_0_6_SubTotal_Neg);
        conn.pst.setString( 83, IFP_6_12_BF_Pos);
        conn.pst.setString( 84, IFP_6_12_BF_Neg);
        conn.pst.setString( 85, IFP_6_12_NBF_Pos);
        conn.pst.setString( 86, IFP_6_12_NotKnown_Pos);
        conn.pst.setString( 87, IFP_6_12_NotKnown_Neg);
        conn.pst.setString( 88, IFP_6_12_BCF_Pos);
        conn.pst.setString( 89, IFP_6_12_BCF_Neg);
        conn.pst.setString( 90, IFP_6_12_SubTotal_Pos);
        
        conn.pst.setString( 91, IFP_6_12_SubTotal_Neg);
        conn.pst.setString( 92, NH2_TCM_Adults15_17_Pos);
        conn.pst.setString( 93, NH2_TCM_Postnatal_Pos);
        conn.pst.setString( 94, NH2_TCM_AllAdults_Neg);
        conn.pst.setString( 95, NH2_TCM_0_59M_Pos);
        conn.pst.setString( 96, NH2_TCM_5_15_Pos);
        conn.pst.setString( 97, NH2_TCM_0_59M_Neg);
        conn.pst.setString( 98, NH2_TCM_5_15_Neg);
        conn.pst.setString( 99, NH2_TCM_Total);
        conn.pst.setString( 100, NH2_RUTF_Adults15_17_Pos);
        
        conn.pst.setString( 101, NH2_RUTF_Postnatal_Pos);
        conn.pst.setString( 102, NH2_RUTF_AllAdults_Neg);
        conn.pst.setString( 103, NH2_RUTF_0_59M_Pos);
        conn.pst.setString( 104, NH2_RUTF_5_15_Pos);
        conn.pst.setString( 105, NH2_RUTF_0_59M_Neg);
        conn.pst.setString( 106, NH2_RUTF_5_15_Neg);
        conn.pst.setString( 107, NH2_RUTF_Total);
        conn.pst.setString( 108, NH2_RUSF_Adults15_17_Pos);
        conn.pst.setString( 109, NH2_RUSF_Postnatal_Pos);
        conn.pst.setString( 110, NH2_RUSF_AllAdults_Neg);
        
        conn.pst.setString( 111, NH2_RUSF_0_59M_Pos);
        conn.pst.setString( 112, NH2_RUSF_5_15_Pos);
        conn.pst.setString( 113, NH2_RUSF_0_59M_Neg);
        conn.pst.setString( 114, NH2_RUSF_5_15_Neg);
        conn.pst.setString( 115, NH2_RUSF_Total);
        conn.pst.setString( 116, NH2_FBFCSB_Adults15_17_Pos);
        conn.pst.setString( 117, NH2_FBFCSB_Postnatal_Pos);
        conn.pst.setString( 118, NH2_FBFCSB_AllAdults_Neg);
        conn.pst.setString( 119, NH2_FBFCSB_0_59M_Pos);
        conn.pst.setString( 120, NH2_FBFCSB_5_15_Pos);
        
        conn.pst.setString( 121, NH2_FBFCSB_0_59M_Neg);
        conn.pst.setString( 122, NH2_FBFCSB_5_15_Neg);
        conn.pst.setString( 123, NH2_FBFCSB_Total);
        conn.pst.setString( 124, NH2_LNS_Adults15_17_Pos);
        conn.pst.setString( 125, NH2_LNS_Postnatal_Pos);
        conn.pst.setString( 126, NH2_LNS_AllAdults_Neg);
        conn.pst.setString( 127, NH2_LNS_0_59M_Pos);
        conn.pst.setString( 128, NH2_LNS_5_15_Pos);
         conn.pst.setString( 129, NH2_LNS_0_59M_Neg);
         conn.pst.setString( 130, NH2_LNS_5_15_Neg);
         
         conn.pst.setString( 131, NH2_LNS_Total);
         conn.pst.setString( 132, NH2_Micronutrients_Adults15_17_Pos);
         conn.pst.setString( 133, NH2_Micronutrients_Postnatal_Pos);
         conn.pst.setString( 134, NH2_Micronutrients_AllAdults_Neg);
         conn.pst.setString( 135, NH2_Micronutrients_0_59M_Pos);
         conn.pst.setString( 136, NH2_Micronutrients_5_15_Pos);
         conn.pst.setString( 137, NH2_Micronutrients_0_59M_Neg);
         conn.pst.setString( 138, NH2_Micronutrients_5_15_Neg);
         conn.pst.setString( 139, NH2_Micronutrients_Total);
         conn.pst.setString( 140, NH2_Others_Adults15_17_Pos);
         
         conn.pst.setString( 141, NH2_Others_Postnatal_Pos);
         conn.pst.setString( 142, NH2_Others_AllAdults_Neg);
         conn.pst.setString( 143, NH2_Others_0_59M_Pos);
         conn.pst.setString( 144, NH2_Others_5_15_Pos);
         conn.pst.setString( 145, NH2_Others_0_59M_Neg);
         conn.pst.setString( 146, NH2_Others_5_15_Neg);
         conn.pst.setString( 147, NH2_Others_Total);
         conn.pst.setString( 148, NH2_Total_Adults15_17_Pos);
         conn.pst.setString( 149, NH2_Total_Postnatal_Pos);
         conn.pst.setString( 150, NH2_Total_AllAdults_Neg);
         
         conn.pst.setString( 151, NH2_Total_0_59M_Pos);
         conn.pst.setString( 152, NH2_Total_5_15_Pos);
         conn.pst.setString( 153, NH2_Total_0_59M_Neg);
         conn.pst.setString( 154, NH2_Total_5_15_Neg);
         conn.pst.setString( 155, NH2_Grand_Total);
         conn.pst.setString( 156, LTF_Adult_15_17);
         conn.pst.setString( 157, LTF_Pregnant);
         conn.pst.setString( 158, LTF_Postnatal);
         conn.pst.setString( 159, LTF_0_59M);
         conn.pst.setString( 160, LTF_5_15);
         
         conn.pst.setString( 161, RM_Adult_15_17);
         conn.pst.setString( 162, RM_Pregnant);
         conn.pst.setString( 163, RM_Postnatal);
         conn.pst.setString( 164, RM_0_59M);
         conn.pst.setString( 165, RM_5_15);
         conn.pst.setString( 166, NH1_Total_Adult);
         conn.pst.setString( 167, NH1_Total_15_17);
         conn.pst.setString( 168, NH1_Total_Postnatal);
         conn.pst.setString( 169, NH1_Total_0_59M);
         conn.pst.setString( 170, NH1_Total_5_15);
         
         conn.pst.setString( 171, NH1_Grand_Total);
         conn.pst.setString( 172, IFP_6_12_NBF_Neg); 
         conn.pst.setInt( 173,1);
         conn.pst.setString( 174,reporting_officer);
         conn.pst.setString( 175,designation);
         conn.pst.setString( 176,reporting_date);
         
         conn.pst.setString( 177,id);
        
        
        
        conn.pst.executeUpdate();
        
        
     response.sendRedirect("load733B.jsp");    
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
        Logger.getLogger(validate733B.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(validate733B.class.getName()).log(Level.SEVERE, null, ex);
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
