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
public class loadmoh733B extends HttpServlet {
HttpSession session;
String id,facility_id,year,month,output;

String CS_New_Pos_Male_Adult, CS_New_Pos_Male_15_17, CS_New_Pos_Male_Postnatal, CS_New_Pos_Male_0_59M, 
        CS_New_Pos_Male_5_15, CS_New_Pos_Female_Adult, CS_New_Pos_Female_15_17, CS_New_Pos_Female_Postnatal, 
        CS_New_Pos_Female_0_59M, CS_New_Pos_Female_5_15, CS_New_Pos_Readmission, CS_New_Pos_Relapse, 
        CS_New_Pos_LinkedOVC, CS_New_Neg_Male_Adult, CS_New_Neg_Male_15_17, CS_New_Neg_Male_Postnatal, 
        CS_New_Neg_Male_0_59M, CS_New_Neg_Male_5_15, CS_New_Neg_Female_Adult, CS_New_Neg_Female_15_17, 
        CS_New_Neg_Female_Postnatal, CS_New_Neg_Female_0_59M, CS_New_Neg_Female_5_15, CS_New_Neg_Readmission, 
        CS_New_Neg_Relapse, CS_New_Neg_LinkedOVC, CS_Revisit_Pos_Adult, CS_Revisit_Pos_15_17, 
        CS_Revisit_Pos_Postnatal, CS_Revisit_Pos_0_59M, CS_Revisit_Pos_5_15, CS_Revisit_Pos_Readmission, 
        CS_Revisit_Pos_Relapse, CS_Revisit_Pos_LinkedOVC, CS_Revisit_Neg_Adult, CS_Revisit_Neg_15_17, 
        CS_Revisit_Neg_Postnatal, CS_Revisit_Neg_0_59M, CS_Revisit_Neg_5_15, CS_Revisit_Neg_Readmission, 
        CS_Revisit_Neg_Relapse, CS_Revisit_Neg_LinkedOVC, CS_Total_Adult, CS_Total_15_17, 
        CS_Total_Postnatal, CS_Total_0_59M, CS_Total_5_15, CS_Total_Readmission, 
        CS_Total_Relapse, CS_Total_LinkedOVC, NH1_Pos_SAM_Adult, NH1_Pos_SAM_15_17, 
        NH1_Pos_SAM_Postnatal, NH1_Pos_SAM_0_59M, NH1_Pos_SAM_5_15, NH1_Pos_SAM_Total, NH1_Pos_MAM_Adult, 
        NH1_Pos_MAM_15_17, NH1_Pos_MAM_Postnatal, NH1_Pos_MAM_0_59M, NH1_Pos_MAM_5_15, NH1_Pos_MAM_Total, 
        NH1_Neg_SAM_Adult, NH1_Neg_SAM_15_17, NH1_Neg_SAM_Postnatal, NH1_Neg_SAM_0_59M, NH1_Neg_SAM_5_15, 
        NH1_Neg_SAM_Total, NH1_Neg_MAM_Adult, NH1_Neg_MAM_15_17, NH1_Neg_MAM_Postnatal, NH1_Neg_MAM_0_59M, 
        NH1_Neg_MAM_5_15, NH1_Neg_MAM_Total, NH1_Total_Adult, NH1_Total_15_17, NH1_Total_Postnatal, NH1_Total_0_59M, 
        NH1_Total_5_15, NH1_Grand_Total,IFP_0_6_EBF_Pos, IFP_0_6_EBF_Neg, 
        IFP_0_6_ERF_Pos, IFP_0_6_ERF_Neg, IFP_0_6_MF_Pos, IFP_0_6_MF_Neg, IFP_0_6_SubTotal_Pos, IFP_0_6_SubTotal_Neg, IFP_6_12_BF_Pos, 
        IFP_6_12_BF_Neg, IFP_6_12_NBF_Pos,IFP_6_12_NBF_Neg, IFP_6_12_NotKnown_Pos, IFP_6_12_NotKnown_Neg, IFP_6_12_BCF_Pos, IFP_6_12_BCF_Neg, 
        IFP_6_12_SubTotal_Pos, IFP_6_12_SubTotal_Neg,NH2_TCM_Adults15_17_Pos, 
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
        NH2_Total_5_15_Neg, NH2_Grand_Total,LTF_Adult_15_17, LTF_Pregnant, LTF_Postnatal, 
        LTF_0_59M, LTF_5_15, RM_Adult_15_17, RM_Pregnant, RM_Postnatal, RM_0_59M, RM_5_15,reporting_officer,designation,reporting_date ;

int has_data=0;
String enterdby,isValidated;
int isLocked;
String RM,LTF,RT,TO,NH2,NI,IFP,Anaemia,ART,NH1,CS,Footer,Header;
String lock;
String facilityId,subcountyid,validated,validity;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        dbConn conn = new dbConn();
        session = request.getSession();
        output="";
        RM=LTF=RT=TO=NH2=NI=IFP=Anaemia=ART=NH1=CS="";
        
        isValidated=id=enterdby=lock="";
        has_data=isLocked=0;
        facilityId=subcountyid="";
        
        CS_New_Pos_Male_Adult =CS_New_Pos_Male_15_17 =CS_New_Pos_Male_Postnatal =CS_New_Pos_Male_0_59M =
        CS_New_Pos_Male_5_15 =CS_New_Pos_Female_Adult =CS_New_Pos_Female_15_17 =CS_New_Pos_Female_Postnatal =
        CS_New_Pos_Female_0_59M =CS_New_Pos_Female_5_15 =CS_New_Pos_Readmission =CS_New_Pos_Relapse =
        CS_New_Pos_LinkedOVC =CS_New_Neg_Male_Adult =CS_New_Neg_Male_15_17 =CS_New_Neg_Male_Postnatal =
        CS_New_Neg_Male_0_59M =CS_New_Neg_Male_5_15 =CS_New_Neg_Female_Adult =CS_New_Neg_Female_15_17 =
        CS_New_Neg_Female_Postnatal =CS_New_Neg_Female_0_59M =CS_New_Neg_Female_5_15 =CS_New_Neg_Readmission =
        CS_New_Neg_Relapse =CS_New_Neg_LinkedOVC =CS_Revisit_Pos_Adult =CS_Revisit_Pos_15_17 =
        CS_Revisit_Pos_Postnatal =CS_Revisit_Pos_0_59M =CS_Revisit_Pos_5_15 =CS_Revisit_Pos_Readmission =
        CS_Revisit_Pos_Relapse =CS_Revisit_Pos_LinkedOVC =CS_Revisit_Neg_Adult =CS_Revisit_Neg_15_17 =
        CS_Revisit_Neg_Postnatal =CS_Revisit_Neg_0_59M =CS_Revisit_Neg_5_15 =CS_Revisit_Neg_Readmission =
        CS_Revisit_Neg_Relapse =CS_Revisit_Neg_LinkedOVC =CS_Total_Adult =CS_Total_15_17 =
        CS_Total_Postnatal =CS_Total_0_59M =CS_Total_5_15 =CS_Total_Readmission =
        CS_Total_Relapse =CS_Total_LinkedOVC =NH1_Pos_SAM_Adult =NH1_Pos_SAM_15_17 =
        NH1_Pos_SAM_Postnatal =NH1_Pos_SAM_0_59M =NH1_Pos_SAM_5_15 =NH1_Pos_SAM_Total =NH1_Pos_MAM_Adult =
        NH1_Pos_MAM_15_17 =NH1_Pos_MAM_Postnatal =NH1_Pos_MAM_0_59M =NH1_Pos_MAM_5_15 =NH1_Pos_MAM_Total =
        NH1_Neg_SAM_Adult =NH1_Neg_SAM_15_17 =NH1_Neg_SAM_Postnatal =NH1_Neg_SAM_0_59M =NH1_Neg_SAM_5_15 =
        NH1_Neg_SAM_Total =NH1_Neg_MAM_Adult =NH1_Neg_MAM_15_17 =NH1_Neg_MAM_Postnatal =NH1_Neg_MAM_0_59M =
        NH1_Neg_MAM_5_15 =NH1_Neg_MAM_Total=NH1_Total_Adult =NH1_Total_15_17 =NH1_Total_Postnatal =NH1_Total_0_59M =
        NH1_Total_5_15 =NH1_Grand_Total=IFP_0_6_EBF_Pos =IFP_0_6_EBF_Neg =
        IFP_0_6_ERF_Pos =IFP_0_6_ERF_Neg =IFP_0_6_MF_Pos =IFP_0_6_MF_Neg =IFP_0_6_SubTotal_Pos =IFP_0_6_SubTotal_Neg =IFP_6_12_BF_Pos =
        IFP_6_12_BF_Neg =IFP_6_12_NBF_Pos =IFP_6_12_NBF_Neg =IFP_6_12_NotKnown_Pos =IFP_6_12_NotKnown_Neg =IFP_6_12_BCF_Pos =IFP_6_12_BCF_Neg =
        IFP_6_12_SubTotal_Pos =IFP_6_12_SubTotal_Neg=NH2_TCM_Adults15_17_Pos =
        NH2_TCM_Postnatal_Pos =NH2_TCM_AllAdults_Neg =NH2_TCM_0_59M_Pos =NH2_TCM_5_15_Pos =NH2_TCM_0_59M_Neg =NH2_TCM_5_15_Neg=
        NH2_TCM_Total =NH2_RUTF_Adults15_17_Pos =NH2_RUTF_Postnatal_Pos =NH2_RUTF_AllAdults_Neg =NH2_RUTF_0_59M_Pos=
        NH2_RUTF_5_15_Pos =NH2_RUTF_0_59M_Neg =NH2_RUTF_5_15_Neg =NH2_RUTF_Total =NH2_RUSF_Adults15_17_Pos =NH2_RUSF_Postnatal_Pos =
        NH2_RUSF_AllAdults_Neg =NH2_RUSF_0_59M_Pos =NH2_RUSF_5_15_Pos =NH2_RUSF_0_59M_Neg =NH2_RUSF_5_15_Neg =NH2_RUSF_Total =
        NH2_FBFCSB_Adults15_17_Pos =NH2_FBFCSB_Postnatal_Pos =NH2_FBFCSB_AllAdults_Neg =NH2_FBFCSB_0_59M_Pos =NH2_FBFCSB_5_15_Pos =
        NH2_FBFCSB_0_59M_Neg =NH2_FBFCSB_5_15_Neg =NH2_FBFCSB_Total =NH2_LNS_Adults15_17_Pos =NH2_LNS_Postnatal_Pos =NH2_LNS_AllAdults_Neg =
        NH2_LNS_0_59M_Pos =NH2_LNS_5_15_Pos =NH2_LNS_0_59M_Neg =NH2_LNS_5_15_Neg =NH2_LNS_Total =NH2_Micronutrients_Adults15_17_Pos =
        NH2_Micronutrients_Postnatal_Pos =NH2_Micronutrients_AllAdults_Neg =NH2_Micronutrients_0_59M_Pos =NH2_Micronutrients_5_15_Pos =
        NH2_Micronutrients_0_59M_Neg =NH2_Micronutrients_5_15_Neg =NH2_Micronutrients_Total =NH2_Others_Adults15_17_Pos =NH2_Others_Postnatal_Pos =
        NH2_Others_AllAdults_Neg =NH2_Others_0_59M_Pos =NH2_Others_5_15_Pos =NH2_Others_0_59M_Neg =NH2_Others_5_15_Neg =NH2_Others_Total =
        NH2_Total_Adults15_17_Pos =NH2_Total_Postnatal_Pos =NH2_Total_AllAdults_Neg =NH2_Total_0_59M_Pos =NH2_Total_5_15_Pos =NH2_Total_0_59M_Neg =
        NH2_Total_5_15_Neg =NH2_Grand_Total=LTF_Adult_15_17 =LTF_Pregnant =LTF_Postnatal =
        LTF_0_59M =LTF_5_15 =RM_Adult_15_17 =RM_Pregnant =RM_Postnatal =RM_0_59M =RM_5_15= reporting_officer=designation=reporting_date ="";     
        
        
        
        //clear cached current tableid session
    session.removeAttribute("table_id");

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
     
         
         System.out.println("id is : "+id); 
               String  yearmonth="";
        String tempmonth=month;
        int pepfaryear=Integer.parseInt(year);
        if(Integer.parseInt(month)<10){ tempmonth="0"+month; }
        else {pepfaryear--;}


        yearmonth=pepfaryear+""+tempmonth;
    String locked_DATA = "SELECT id FROM locked_data WHERE yearmonth=? AND nutrition=?";
    conn.pst = conn.conn.prepareStatement(locked_DATA);
    conn.pst.setString(1, yearmonth);
    conn.pst.setInt(2, 1);
    conn.rs = conn.pst.executeQuery();
    if(conn.rs.next()){
      isLocked= 1;
      lock="disabled";
    }
    
        String query="SELECT * FROM moh733b WHERE id='"+id+"'"; 
        conn.rs=conn.st.executeQuery(query);
        if(conn.rs.next()){
            session.setAttribute("table_id", id);
         //get basic and common data
         facility_id = conn.rs.getString("SubPartnerID");
         String  Mois = conn.rs.getString("Mois");
         String  Annee = conn.rs.getString("Annee");
         yearmonth = conn.rs.getString("yearmonth");
         isValidated = conn.rs.getString("isValidated");
         int isLocked = conn.rs.getInt("isLocked");
         if(isLocked==1){lock="disabled";}
        //get the name of the person who entered the form 
        
        String enterer="select * from user where userid='"+conn.rs.getString("user_id") +"'";
        
        conn.rs1=conn.st1.executeQuery(enterer);
        //add details of person who entered
        if(conn.rs1.next()){
        enterdby="<font color='green'>Data 1st entered by:   <b> "+conn.rs1.getString("fname")+" "+conn.rs1.getString("mname")+" "+conn.rs1.getString("lname")+"</b>  on  <b>"+conn.rs.getString("timestamp") +"</b></font>";
        }
        
		
		//now check if form was updated and if its one month after data entry
        
        if(conn.rs.getString("updatedOn")!=null){
        //get difference in months between entered date and updated date
        String compdate="SELECT TIMESTAMPDIFF(MONTH,'"+conn.rs.getString("timestamp") +"','"+conn.rs.getString("updatedOn") +"')";
        conn.rs2=conn.st2.executeQuery(compdate);
        if (conn.rs2.next()){
            //now get the details of the person who updated the form
        //if the difference is greater than or equal to one, 
        
            
            if(conn.rs2.getInt(1)>=1){
        String updater="select * from user where userid='"+conn.rs.getString("updatedBy") +"'";
        
        conn.rs1=conn.st1.executeQuery(updater);
        //add details of person who entered
        if(conn.rs1.next()){
            enterdby += "<span style='margin-left:30%;'><font color='red'>   Updated  by:   <b> " + conn.rs1.getString("fname") + " " + conn.rs1.getString("mname") + " " + conn.rs1.getString("lname") + "</b>  on  <b>" + conn.rs.getString("updatedOn") + "</b></font></span>";
         }
        } //end of if month >=1 
        }//end of date comparison if 
        
        }//end of if updated !=null
        
        if(conn.rs.getString("CS_New_Pos_Male_Adult")!=null){CS_New_Pos_Male_Adult  = conn.rs.getString("CS_New_Pos_Male_Adult");}
        if(conn.rs.getString("CS_New_Pos_Male_15_17")!=null){CS_New_Pos_Male_15_17  = conn.rs.getString("CS_New_Pos_Male_15_17");}
        if(conn.rs.getString("CS_New_Pos_Male_Postnatal")!=null){CS_New_Pos_Male_Postnatal  = conn.rs.getString("CS_New_Pos_Male_Postnatal");}
        if(conn.rs.getString("CS_New_Pos_Male_0_59M")!=null){CS_New_Pos_Male_0_59M  = conn.rs.getString("CS_New_Pos_Male_0_59M");}
        if(conn.rs.getString("CS_New_Pos_Male_5_15")!=null){CS_New_Pos_Male_5_15  = conn.rs.getString("CS_New_Pos_Male_5_15");}
        if(conn.rs.getString("CS_New_Pos_Female_Adult")!=null){CS_New_Pos_Female_Adult  = conn.rs.getString("CS_New_Pos_Female_Adult");}
        if(conn.rs.getString("CS_New_Pos_Female_15_17")!=null){CS_New_Pos_Female_15_17  = conn.rs.getString("CS_New_Pos_Female_15_17");}
        if(conn.rs.getString("CS_New_Pos_Female_Postnatal")!=null){CS_New_Pos_Female_Postnatal  = conn.rs.getString("CS_New_Pos_Female_Postnatal");}
        if(conn.rs.getString("CS_New_Pos_Female_0_59M")!=null){CS_New_Pos_Female_0_59M  = conn.rs.getString("CS_New_Pos_Female_0_59M");}
        if(conn.rs.getString("CS_New_Pos_Female_5_15")!=null){CS_New_Pos_Female_5_15  = conn.rs.getString("CS_New_Pos_Female_5_15");}
        if(conn.rs.getString("CS_New_Pos_Readmission")!=null){CS_New_Pos_Readmission  = conn.rs.getString("CS_New_Pos_Readmission");}
        if(conn.rs.getString("CS_New_Pos_Relapse")!=null){CS_New_Pos_Relapse  = conn.rs.getString("CS_New_Pos_Relapse");}
        if(conn.rs.getString("CS_New_Pos_LinkedOVC")!=null){CS_New_Pos_LinkedOVC  = conn.rs.getString("CS_New_Pos_LinkedOVC");}
        if(conn.rs.getString("CS_New_Neg_Male_Adult")!=null){CS_New_Neg_Male_Adult  = conn.rs.getString("CS_New_Neg_Male_Adult");}
        if(conn.rs.getString("CS_New_Neg_Male_15_17")!=null){CS_New_Neg_Male_15_17  = conn.rs.getString("CS_New_Neg_Male_15_17");}
        if(conn.rs.getString("CS_New_Neg_Male_Postnatal")!=null){CS_New_Neg_Male_Postnatal  = conn.rs.getString("CS_New_Neg_Male_Postnatal");}
        if(conn.rs.getString("CS_New_Neg_Male_0_59M")!=null){CS_New_Neg_Male_0_59M  = conn.rs.getString("CS_New_Neg_Male_0_59M");}
        if(conn.rs.getString("CS_New_Neg_Male_5_15")!=null){CS_New_Neg_Male_5_15  = conn.rs.getString("CS_New_Neg_Male_5_15");}
        if(conn.rs.getString("CS_New_Neg_Female_Adult")!=null){CS_New_Neg_Female_Adult  = conn.rs.getString("CS_New_Neg_Female_Adult");}
        if(conn.rs.getString("CS_New_Neg_Female_15_17")!=null){CS_New_Neg_Female_15_17  = conn.rs.getString("CS_New_Neg_Female_15_17");}
        if(conn.rs.getString("CS_New_Neg_Female_Postnatal")!=null){CS_New_Neg_Female_Postnatal  = conn.rs.getString("CS_New_Neg_Female_Postnatal");}
        if(conn.rs.getString("CS_New_Neg_Female_0_59M")!=null){CS_New_Neg_Female_0_59M  = conn.rs.getString("CS_New_Neg_Female_0_59M");}
        if(conn.rs.getString("CS_New_Neg_Female_5_15")!=null){CS_New_Neg_Female_5_15  = conn.rs.getString("CS_New_Neg_Female_5_15");}
        if(conn.rs.getString("CS_New_Neg_Readmission")!=null){CS_New_Neg_Readmission  = conn.rs.getString("CS_New_Neg_Readmission");}
        if(conn.rs.getString("CS_New_Neg_Relapse")!=null){CS_New_Neg_Relapse  = conn.rs.getString("CS_New_Neg_Relapse");}
        if(conn.rs.getString("CS_New_Neg_LinkedOVC")!=null){CS_New_Neg_LinkedOVC  = conn.rs.getString("CS_New_Neg_LinkedOVC");}
        if(conn.rs.getString("CS_Revisit_Pos_Adult")!=null){CS_Revisit_Pos_Adult  = conn.rs.getString("CS_Revisit_Pos_Adult");}
        if(conn.rs.getString("CS_Revisit_Pos_15_17")!=null){CS_Revisit_Pos_15_17  = conn.rs.getString("CS_Revisit_Pos_15_17");}
        if(conn.rs.getString("CS_Revisit_Pos_Postnatal")!=null){CS_Revisit_Pos_Postnatal  = conn.rs.getString("CS_Revisit_Pos_Postnatal");}
        if(conn.rs.getString("CS_Revisit_Pos_0_59M")!=null){CS_Revisit_Pos_0_59M  = conn.rs.getString("CS_Revisit_Pos_0_59M");}
        if(conn.rs.getString("CS_Revisit_Pos_5_15")!=null){CS_Revisit_Pos_5_15  = conn.rs.getString("CS_Revisit_Pos_5_15");}
        if(conn.rs.getString("CS_Revisit_Pos_Readmission")!=null){CS_Revisit_Pos_Readmission  = conn.rs.getString("CS_Revisit_Pos_Readmission");}
        if(conn.rs.getString("CS_Revisit_Pos_Relapse")!=null){CS_Revisit_Pos_Relapse  = conn.rs.getString("CS_Revisit_Pos_Relapse");}
        if(conn.rs.getString("CS_Revisit_Pos_LinkedOVC")!=null){CS_Revisit_Pos_LinkedOVC  = conn.rs.getString("CS_Revisit_Pos_LinkedOVC");}
        if(conn.rs.getString("CS_Revisit_Neg_Adult")!=null){CS_Revisit_Neg_Adult  = conn.rs.getString("CS_Revisit_Neg_Adult");}
        if(conn.rs.getString("CS_Revisit_Neg_15_17")!=null){CS_Revisit_Neg_15_17  = conn.rs.getString("CS_Revisit_Neg_15_17");}
        if(conn.rs.getString("CS_Revisit_Neg_Postnatal")!=null){CS_Revisit_Neg_Postnatal  = conn.rs.getString("CS_Revisit_Neg_Postnatal");}
        if(conn.rs.getString("CS_Revisit_Neg_0_59M")!=null){CS_Revisit_Neg_0_59M  = conn.rs.getString("CS_Revisit_Neg_0_59M");}
        if(conn.rs.getString("CS_Revisit_Neg_5_15")!=null){CS_Revisit_Neg_5_15  = conn.rs.getString("CS_Revisit_Neg_5_15");}
        if(conn.rs.getString("CS_Revisit_Neg_Readmission")!=null){CS_Revisit_Neg_Readmission  = conn.rs.getString("CS_Revisit_Neg_Readmission");}
        if(conn.rs.getString("CS_Revisit_Neg_Relapse")!=null){CS_Revisit_Neg_Relapse  = conn.rs.getString("CS_Revisit_Neg_Relapse");}
        if(conn.rs.getString("CS_Revisit_Neg_LinkedOVC")!=null){CS_Revisit_Neg_LinkedOVC  = conn.rs.getString("CS_Revisit_Neg_LinkedOVC");}
        if(conn.rs.getString("CS_Total_Adult")!=null){CS_Total_Adult  = conn.rs.getString("CS_Total_Adult");}
        if(conn.rs.getString("CS_Total_15_17")!=null){CS_Total_15_17  = conn.rs.getString("CS_Total_15_17");}
        if(conn.rs.getString("CS_Total_Postnatal")!=null){CS_Total_Postnatal  = conn.rs.getString("CS_Total_Postnatal");}
        if(conn.rs.getString("CS_Total_0_59M")!=null){CS_Total_0_59M  = conn.rs.getString("CS_Total_0_59M");}
        if(conn.rs.getString("CS_Total_5_15")!=null){CS_Total_5_15  = conn.rs.getString("CS_Total_5_15");}
        if(conn.rs.getString("CS_Total_Readmission")!=null){CS_Total_Readmission  = conn.rs.getString("CS_Total_Readmission");}
        if(conn.rs.getString("CS_Total_Relapse")!=null){CS_Total_Relapse  = conn.rs.getString("CS_Total_Relapse");}
        if(conn.rs.getString("CS_Total_LinkedOVC")!=null){CS_Total_LinkedOVC  = conn.rs.getString("CS_Total_LinkedOVC");}
        if(conn.rs.getString("NH1_Pos_SAM_Adult")!=null){NH1_Pos_SAM_Adult  = conn.rs.getString("NH1_Pos_SAM_Adult");}
        if(conn.rs.getString("NH1_Pos_SAM_15_17")!=null){NH1_Pos_SAM_15_17  = conn.rs.getString("NH1_Pos_SAM_15_17");}
        if(conn.rs.getString("NH1_Pos_SAM_Postnatal")!=null){NH1_Pos_SAM_Postnatal  = conn.rs.getString("NH1_Pos_SAM_Postnatal");}
        if(conn.rs.getString("NH1_Pos_SAM_0_59M")!=null){NH1_Pos_SAM_0_59M  = conn.rs.getString("NH1_Pos_SAM_0_59M");}
        if(conn.rs.getString("NH1_Pos_SAM_5_15")!=null){NH1_Pos_SAM_5_15  = conn.rs.getString("NH1_Pos_SAM_5_15");}
        if(conn.rs.getString("NH1_Pos_SAM_Total")!=null){NH1_Pos_SAM_Total  = conn.rs.getString("NH1_Pos_SAM_Total");}
        if(conn.rs.getString("NH1_Pos_MAM_Adult")!=null){NH1_Pos_MAM_Adult  = conn.rs.getString("NH1_Pos_MAM_Adult");}
        if(conn.rs.getString("NH1_Pos_MAM_15_17")!=null){NH1_Pos_MAM_15_17  = conn.rs.getString("NH1_Pos_MAM_15_17");}
        if(conn.rs.getString("NH1_Pos_MAM_Postnatal")!=null){NH1_Pos_MAM_Postnatal  = conn.rs.getString("NH1_Pos_MAM_Postnatal");}
        if(conn.rs.getString("NH1_Pos_MAM_0_59M")!=null){NH1_Pos_MAM_0_59M  = conn.rs.getString("NH1_Pos_MAM_0_59M");}
        if(conn.rs.getString("NH1_Pos_MAM_5_15")!=null){NH1_Pos_MAM_5_15  = conn.rs.getString("NH1_Pos_MAM_5_15");}
        if(conn.rs.getString("NH1_Pos_MAM_Total")!=null){NH1_Pos_MAM_Total  = conn.rs.getString("NH1_Pos_MAM_Total");}
        if(conn.rs.getString("NH1_Neg_SAM_Adult")!=null){NH1_Neg_SAM_Adult  = conn.rs.getString("NH1_Neg_SAM_Adult");}
        if(conn.rs.getString("NH1_Neg_SAM_15_17")!=null){NH1_Neg_SAM_15_17  = conn.rs.getString("NH1_Neg_SAM_15_17");}
        if(conn.rs.getString("NH1_Neg_SAM_Postnatal")!=null){NH1_Neg_SAM_Postnatal  = conn.rs.getString("NH1_Neg_SAM_Postnatal");}
        if(conn.rs.getString("NH1_Neg_SAM_0_59M")!=null){NH1_Neg_SAM_0_59M  = conn.rs.getString("NH1_Neg_SAM_0_59M");}
        if(conn.rs.getString("NH1_Neg_SAM_5_15")!=null){NH1_Neg_SAM_5_15  = conn.rs.getString("NH1_Neg_SAM_5_15");}
        if(conn.rs.getString("NH1_Neg_SAM_Total")!=null){NH1_Neg_SAM_Total  = conn.rs.getString("NH1_Neg_SAM_Total");}
        if(conn.rs.getString("NH1_Neg_MAM_Adult")!=null){NH1_Neg_MAM_Adult  = conn.rs.getString("NH1_Neg_MAM_Adult");}
        if(conn.rs.getString("NH1_Neg_MAM_15_17")!=null){NH1_Neg_MAM_15_17  = conn.rs.getString("NH1_Neg_MAM_15_17");}
        if(conn.rs.getString("NH1_Neg_MAM_Postnatal")!=null){NH1_Neg_MAM_Postnatal  = conn.rs.getString("NH1_Neg_MAM_Postnatal");}
        if(conn.rs.getString("NH1_Neg_MAM_0_59M")!=null){NH1_Neg_MAM_0_59M  = conn.rs.getString("NH1_Neg_MAM_0_59M");}
        if(conn.rs.getString("NH1_Neg_MAM_5_15")!=null){NH1_Neg_MAM_5_15  = conn.rs.getString("NH1_Neg_MAM_5_15");}
        if(conn.rs.getString("NH1_Neg_MAM_Total")!=null){NH1_Neg_MAM_Total  = conn.rs.getString("NH1_Neg_MAM_Total");}
        if(conn.rs.getString("NH1_Total_Adult")!=null){NH1_Total_Adult  = conn.rs.getString("NH1_Total_Adult");}
        if(conn.rs.getString("NH1_Total_15_17")!=null){NH1_Total_15_17  = conn.rs.getString("NH1_Total_15_17");}
        if(conn.rs.getString("NH1_Total_Postnatal")!=null){NH1_Total_Postnatal  = conn.rs.getString("NH1_Total_Postnatal");}
        if(conn.rs.getString("NH1_Total_0_59M")!=null){NH1_Total_0_59M  = conn.rs.getString("NH1_Total_0_59M");}
        if(conn.rs.getString("NH1_Total_5_15")!=null){NH1_Total_5_15  = conn.rs.getString("NH1_Total_5_15");}
        if(conn.rs.getString("NH1_Grand_Total")!=null){NH1_Grand_Total  = conn.rs.getString("NH1_Grand_Total");}
        
        
        if(conn.rs.getString("IFP_0_6_EBF_Pos")!=null){IFP_0_6_EBF_Pos  = conn.rs.getString("IFP_0_6_EBF_Pos");}
        if(conn.rs.getString("IFP_0_6_EBF_Neg")!=null){IFP_0_6_EBF_Neg  = conn.rs.getString("IFP_0_6_EBF_Neg");}
        if(conn.rs.getString("IFP_0_6_ERF_Pos")!=null){IFP_0_6_ERF_Pos  = conn.rs.getString("IFP_0_6_ERF_Pos");}
        if(conn.rs.getString("IFP_0_6_ERF_Neg")!=null){IFP_0_6_ERF_Neg  = conn.rs.getString("IFP_0_6_ERF_Neg");}
        if(conn.rs.getString("IFP_0_6_MF_Pos")!=null){IFP_0_6_MF_Pos  = conn.rs.getString("IFP_0_6_MF_Pos");}
        if(conn.rs.getString("IFP_0_6_MF_Neg")!=null){IFP_0_6_MF_Neg  = conn.rs.getString("IFP_0_6_MF_Neg");}
        if(conn.rs.getString("IFP_0_6_SubTotal_Pos")!=null){IFP_0_6_SubTotal_Pos  = conn.rs.getString("IFP_0_6_SubTotal_Pos");}
        if(conn.rs.getString("IFP_0_6_SubTotal_Neg")!=null){IFP_0_6_SubTotal_Neg  = conn.rs.getString("IFP_0_6_SubTotal_Neg");}
        if(conn.rs.getString("IFP_6_12_BF_Pos")!=null){IFP_6_12_BF_Pos  = conn.rs.getString("IFP_6_12_BF_Pos");}
        if(conn.rs.getString("IFP_6_12_BF_Neg")!=null){IFP_6_12_BF_Neg  = conn.rs.getString("IFP_6_12_BF_Neg");}
        if(conn.rs.getString("IFP_6_12_NBF_Pos")!=null){IFP_6_12_NBF_Pos  = conn.rs.getString("IFP_6_12_NBF_Pos");}
        if(conn.rs.getString("IFP_6_12_NBF_Neg")!=null){IFP_6_12_NBF_Neg  = conn.rs.getString("IFP_6_12_NBF_Neg");}
        if(conn.rs.getString("IFP_6_12_NotKnown_Pos")!=null){IFP_6_12_NotKnown_Pos  = conn.rs.getString("IFP_6_12_NotKnown_Pos");}
        if(conn.rs.getString("IFP_6_12_NotKnown_Neg")!=null){IFP_6_12_NotKnown_Neg  = conn.rs.getString("IFP_6_12_NotKnown_Neg");}
        if(conn.rs.getString("IFP_6_12_BCF_Pos")!=null){IFP_6_12_BCF_Pos  = conn.rs.getString("IFP_6_12_BCF_Pos");}
        if(conn.rs.getString("IFP_6_12_BCF_Neg")!=null){IFP_6_12_BCF_Neg  = conn.rs.getString("IFP_6_12_BCF_Neg");}
        if(conn.rs.getString("IFP_6_12_SubTotal_Pos")!=null){IFP_6_12_SubTotal_Pos  = conn.rs.getString("IFP_6_12_SubTotal_Pos");}
        if(conn.rs.getString("IFP_6_12_SubTotal_Neg")!=null){IFP_6_12_SubTotal_Neg  = conn.rs.getString("IFP_6_12_SubTotal_Neg");}
        
        
        if(conn.rs.getString("NH2_TCM_Adults15_17_Pos")!=null){NH2_TCM_Adults15_17_Pos  = conn.rs.getString("NH2_TCM_Adults15_17_Pos");}
        if(conn.rs.getString("NH2_TCM_Postnatal_Pos")!=null){NH2_TCM_Postnatal_Pos  = conn.rs.getString("NH2_TCM_Postnatal_Pos");}
        if(conn.rs.getString("NH2_TCM_AllAdults_Neg")!=null){NH2_TCM_AllAdults_Neg  = conn.rs.getString("NH2_TCM_AllAdults_Neg");}
        if(conn.rs.getString("NH2_TCM_0_59M_Pos")!=null){NH2_TCM_0_59M_Pos  = conn.rs.getString("NH2_TCM_0_59M_Pos");}
        if(conn.rs.getString("NH2_TCM_5_15_Pos")!=null){NH2_TCM_5_15_Pos  = conn.rs.getString("NH2_TCM_5_15_Pos");}
        if(conn.rs.getString("NH2_TCM_0_59M_Neg")!=null){NH2_TCM_0_59M_Neg  = conn.rs.getString("NH2_TCM_0_59M_Neg");}
        if(conn.rs.getString("NH2_TCM_5_15_Neg")!=null){NH2_TCM_5_15_Neg = conn.rs.getString("NH2_TCM_5_15_Neg");}
        if(conn.rs.getString("NH2_TCM_Total")!=null){NH2_TCM_Total  = conn.rs.getString("NH2_TCM_Total");}
        if(conn.rs.getString("NH2_RUTF_Adults15_17_Pos")!=null){NH2_RUTF_Adults15_17_Pos  = conn.rs.getString("NH2_RUTF_Adults15_17_Pos");}
        if(conn.rs.getString("NH2_RUTF_Postnatal_Pos")!=null){NH2_RUTF_Postnatal_Pos  = conn.rs.getString("NH2_RUTF_Postnatal_Pos");}
        if(conn.rs.getString("NH2_RUTF_AllAdults_Neg")!=null){NH2_RUTF_AllAdults_Neg  = conn.rs.getString("NH2_RUTF_AllAdults_Neg");}
        if(conn.rs.getString("NH2_RUTF_0_59M_Pos")!=null){NH2_RUTF_0_59M_Pos = conn.rs.getString("NH2_RUTF_0_59M_Pos");}
        if(conn.rs.getString("NH2_RUTF_5_15_Pos")!=null){NH2_RUTF_5_15_Pos  = conn.rs.getString("NH2_RUTF_5_15_Pos");}
        if(conn.rs.getString("NH2_RUTF_0_59M_Neg")!=null){NH2_RUTF_0_59M_Neg  = conn.rs.getString("NH2_RUTF_0_59M_Neg");}
        if(conn.rs.getString("NH2_RUTF_5_15_Neg")!=null){NH2_RUTF_5_15_Neg  = conn.rs.getString("NH2_RUTF_5_15_Neg");}
        if(conn.rs.getString("NH2_RUTF_Total")!=null){NH2_RUTF_Total  = conn.rs.getString("NH2_RUTF_Total");}
        if(conn.rs.getString("NH2_RUSF_Adults15_17_Pos")!=null){NH2_RUSF_Adults15_17_Pos  = conn.rs.getString("NH2_RUSF_Adults15_17_Pos");}
        if(conn.rs.getString("NH2_RUSF_Postnatal_Pos")!=null){NH2_RUSF_Postnatal_Pos  = conn.rs.getString("NH2_RUSF_Postnatal_Pos");}
        if(conn.rs.getString("NH2_RUSF_AllAdults_Neg")!=null){NH2_RUSF_AllAdults_Neg  = conn.rs.getString("NH2_RUSF_AllAdults_Neg");}
        if(conn.rs.getString("NH2_RUSF_0_59M_Pos")!=null){NH2_RUSF_0_59M_Pos  = conn.rs.getString("NH2_RUSF_0_59M_Pos");}
        if(conn.rs.getString("NH2_RUSF_5_15_Pos")!=null){NH2_RUSF_5_15_Pos  = conn.rs.getString("NH2_RUSF_5_15_Pos");}
        if(conn.rs.getString("NH2_RUSF_0_59M_Neg")!=null){NH2_RUSF_0_59M_Neg  = conn.rs.getString("NH2_RUSF_0_59M_Neg");}
        if(conn.rs.getString("NH2_RUSF_5_15_Neg")!=null){NH2_RUSF_5_15_Neg  = conn.rs.getString("NH2_RUSF_5_15_Neg");}
        if(conn.rs.getString("NH2_RUSF_Total")!=null){NH2_RUSF_Total  = conn.rs.getString("NH2_RUSF_Total");}
        if(conn.rs.getString("NH2_FBFCSB_Adults15_17_Pos")!=null){NH2_FBFCSB_Adults15_17_Pos  = conn.rs.getString("NH2_FBFCSB_Adults15_17_Pos");}
        if(conn.rs.getString("NH2_FBFCSB_Postnatal_Pos")!=null){NH2_FBFCSB_Postnatal_Pos  = conn.rs.getString("NH2_FBFCSB_Postnatal_Pos");}
        if(conn.rs.getString("NH2_FBFCSB_AllAdults_Neg")!=null){NH2_FBFCSB_AllAdults_Neg  = conn.rs.getString("NH2_FBFCSB_AllAdults_Neg");}
        if(conn.rs.getString("NH2_FBFCSB_0_59M_Pos")!=null){NH2_FBFCSB_0_59M_Pos  = conn.rs.getString("NH2_FBFCSB_0_59M_Pos");}
        if(conn.rs.getString("NH2_FBFCSB_5_15_Pos")!=null){NH2_FBFCSB_5_15_Pos  = conn.rs.getString("NH2_FBFCSB_5_15_Pos");}
        if(conn.rs.getString("NH2_FBFCSB_0_59M_Neg")!=null){NH2_FBFCSB_0_59M_Neg  = conn.rs.getString("NH2_FBFCSB_0_59M_Neg");}
        if(conn.rs.getString("NH2_FBFCSB_5_15_Neg")!=null){NH2_FBFCSB_5_15_Neg  = conn.rs.getString("NH2_FBFCSB_5_15_Neg");}
        if(conn.rs.getString("NH2_FBFCSB_Total")!=null){NH2_FBFCSB_Total  = conn.rs.getString("NH2_FBFCSB_Total");}
        if(conn.rs.getString("NH2_LNS_Adults15_17_Pos")!=null){NH2_LNS_Adults15_17_Pos  = conn.rs.getString("NH2_LNS_Adults15_17_Pos");}
        if(conn.rs.getString("NH2_LNS_Postnatal_Pos")!=null){NH2_LNS_Postnatal_Pos  = conn.rs.getString("NH2_LNS_Postnatal_Pos");}
        if(conn.rs.getString("NH2_LNS_AllAdults_Neg")!=null){NH2_LNS_AllAdults_Neg  = conn.rs.getString("NH2_LNS_AllAdults_Neg");}
        if(conn.rs.getString("NH2_LNS_0_59M_Pos")!=null){NH2_LNS_0_59M_Pos  = conn.rs.getString("NH2_LNS_0_59M_Pos");}
        if( conn.rs.getString("NH2_LNS_5_15_Pos")!=null){NH2_LNS_5_15_Pos  = conn.rs.getString("NH2_LNS_5_15_Pos");}
        if(conn.rs.getString("NH2_LNS_0_59M_Neg")!=null){NH2_LNS_0_59M_Neg  = conn.rs.getString("NH2_LNS_0_59M_Neg");}
        if(conn.rs.getString("NH2_LNS_5_15_Neg")!=null){NH2_LNS_5_15_Neg  = conn.rs.getString("NH2_LNS_5_15_Neg");}
        if(conn.rs.getString("NH2_LNS_Total")!=null){NH2_LNS_Total  = conn.rs.getString("NH2_LNS_Total");}
        if(conn.rs.getString("NH2_Micronutrients_Adults15_17_Pos")!=null){NH2_Micronutrients_Adults15_17_Pos  = conn.rs.getString("NH2_Micronutrients_Adults15_17_Pos");}
        if(conn.rs.getString("NH2_Micronutrients_Postnatal_Pos")!=null){NH2_Micronutrients_Postnatal_Pos  = conn.rs.getString("NH2_Micronutrients_Postnatal_Pos");}
        if(conn.rs.getString("NH2_Micronutrients_AllAdults_Neg")!=null){ NH2_Micronutrients_AllAdults_Neg  = conn.rs.getString("NH2_Micronutrients_AllAdults_Neg");}
        if(conn.rs.getString("NH2_Micronutrients_0_59M_Pos")!=null){NH2_Micronutrients_0_59M_Pos  = conn.rs.getString("NH2_Micronutrients_0_59M_Pos");}
        if(conn.rs.getString("NH2_Micronutrients_5_15_Pos")!=null){ NH2_Micronutrients_5_15_Pos  = conn.rs.getString("NH2_Micronutrients_5_15_Pos");}
        if(conn.rs.getString("NH2_Micronutrients_0_59M_Neg")!=null){NH2_Micronutrients_0_59M_Neg  = conn.rs.getString("NH2_Micronutrients_0_59M_Neg");}
        if(conn.rs.getString("NH2_Micronutrients_5_15_Neg")!=null){NH2_Micronutrients_5_15_Neg  = conn.rs.getString("NH2_Micronutrients_5_15_Neg");}
        if(conn.rs.getString("NH2_Micronutrients_Total")!=null){NH2_Micronutrients_Total  = conn.rs.getString("NH2_Micronutrients_Total");}
        if(conn.rs.getString("NH2_Others_Adults15_17_Pos")!=null){NH2_Others_Adults15_17_Pos  = conn.rs.getString("NH2_Others_Adults15_17_Pos");}
        if(conn.rs.getString("NH2_Others_Postnatal_Pos")!=null){NH2_Others_Postnatal_Pos  = conn.rs.getString("NH2_Others_Postnatal_Pos");}
        if(conn.rs.getString("NH2_Others_AllAdults_Neg")!=null){NH2_Others_AllAdults_Neg  = conn.rs.getString("NH2_Others_AllAdults_Neg");}
        if(conn.rs.getString("NH2_Others_0_59M_Pos")!=null){NH2_Others_0_59M_Pos  = conn.rs.getString("NH2_Others_0_59M_Pos");}
        if(conn.rs.getString("NH2_Others_5_15_Pos")!=null){NH2_Others_5_15_Pos  = conn.rs.getString("NH2_Others_5_15_Pos");}
        if(conn.rs.getString("NH2_Others_0_59M_Neg")!=null){NH2_Others_0_59M_Neg  = conn.rs.getString("NH2_Others_0_59M_Neg");}
        if(conn.rs.getString("NH2_Others_5_15_Neg")!=null){NH2_Others_5_15_Neg  = conn.rs.getString("NH2_Others_5_15_Neg");}
        if(conn.rs.getString("NH2_Others_Total")!=null){NH2_Others_Total  = conn.rs.getString("NH2_Others_Total");}
        if(conn.rs.getString("NH2_Total_Adults15_17_Pos")!=null){NH2_Total_Adults15_17_Pos  = conn.rs.getString("NH2_Total_Adults15_17_Pos");}
        if(conn.rs.getString("NH2_Total_Postnatal_Pos")!=null){NH2_Total_Postnatal_Pos  = conn.rs.getString("NH2_Total_Postnatal_Pos");}
        if(conn.rs.getString("NH2_Total_AllAdults_Neg")!=null){NH2_Total_AllAdults_Neg  = conn.rs.getString("NH2_Total_AllAdults_Neg");}
        if(conn.rs.getString("NH2_Total_0_59M_Pos")!=null){NH2_Total_0_59M_Pos  = conn.rs.getString("NH2_Total_0_59M_Pos");}
        if(conn.rs.getString("NH2_Total_5_15_Pos")!=null){NH2_Total_5_15_Pos  = conn.rs.getString("NH2_Total_5_15_Pos");}
        if(conn.rs.getString("NH2_Total_0_59M_Neg")!=null){NH2_Total_0_59M_Neg  = conn.rs.getString("NH2_Total_0_59M_Neg");}
        if(conn.rs.getString("NH2_Total_5_15_Neg")!=null){NH2_Total_5_15_Neg  = conn.rs.getString("NH2_Total_5_15_Neg");}
        if(conn.rs.getString("NH2_Grand_Total")!=null){NH2_Grand_Total  = conn.rs.getString("NH2_Grand_Total");}
        
        if(conn.rs.getString("LTF_Adult_15_17")!=null){LTF_Adult_15_17  = conn.rs.getString("LTF_Adult_15_17");}
        if(conn.rs.getString("LTF_Pregnant")!=null){LTF_Pregnant  = conn.rs.getString("LTF_Pregnant");}
        if(conn.rs.getString("LTF_Postnatal")!=null){LTF_Postnatal  = conn.rs.getString("LTF_Postnatal");}
        if(conn.rs.getString("LTF_0_59M")!=null){LTF_0_59M  = conn.rs.getString("LTF_0_59M");}
        if(conn.rs.getString("LTF_5_15")!=null){LTF_5_15  = conn.rs.getString("LTF_5_15");}
        if(conn.rs.getString("RM_Adult_15_17")!=null){RM_Adult_15_17  = conn.rs.getString("RM_Adult_15_17");}
        if(conn.rs.getString("RM_Pregnant")!=null){RM_Pregnant  = conn.rs.getString("RM_Pregnant");}
        if(conn.rs.getString("RM_Postnatal")!=null){RM_Postnatal  = conn.rs.getString("RM_Postnatal");}
        if(conn.rs.getString("RM_0_59M")!=null){RM_0_59M  = conn.rs.getString("RM_0_59M");}
        if(conn.rs.getString("RM_5_15")!=null){RM_5_15= conn.rs.getString("RM_5_15");} 
        
          if(conn.rs.getString("reporting_officer")!=null){reporting_officer  = conn.rs.getString("reporting_officer");} 
          if(conn.rs.getString("designation")!=null){designation  = conn.rs.getString("designation");}
          if(conn.rs.getString("reporting_date")!=null){reporting_date  = conn.rs.getString("reporting_date");}  
                 
        }
//      to display

     System.out.println("Validity checker : "+isValidated);
      if(isValidated.equals("0")){
     validity="<font color=\"red\"><b>Form Not Validated.<img style=\"margin-left:10px;\" src=\"images/notValidated.jpg\" width=\"20px\" height=\"20px\"></b></font>"  ;
    }
          else if(isValidated.equals("1")){
       validity="<font color=\"green\"><b>Form Validated.<img style=\"margin-left:10px;\" src=\"images/validated.jpg\" width=\"20px\" height=\"20px\"></b></font>"  ;  
    }
      else{
        validity="<font color=\"blue\"><b>New entry form.</b></font>"  ;          
              }

    session.setAttribute("isValidated", validity);
    Header="<p id=\"isValidated\">"+validity+"</p><br>";

CS = "<table border=\"1px;\">"
            + "<tr border=\"1px;\">"
            + "<td rowspan=\"8\"> <p class =\"rotate\" >Number of Clients Served</p></td>"
            + "<td></td>"
            + "<td></td>"
            + "<td></td>"
            + "<td>Adults</td>"
            + "<td>15-17 Years</td>"
            + "<td>Pregnant /Postnatal</td>"
            + "<td>0-59 Months</td>"
            + "<td>5-15 Years</td>"
            + "<td>Re-admission</td>"
            + "<td>Relapse</td>"
            + "<td>Linked to OVC</td>"
            + "</tr>"
        
        + "<tr>"
            + "<td rowspan=\"4\"><p>NEW</p></td>"
            + "<td rowspan=\"2\"><p>HIV Positive</p></td>"
            + "<td>Male</td>"
            + "<td><input type=\"text\" name=\"CS_New_Pos_Male_Adult\" id=\"CS_New_Pos_Male_Adult\" value=\""+CS_New_Pos_Male_Adult+"\" tabindex=\"1\"  onblur=\"autosave('CS_New_Pos_Male_Adult');\"  oninput=\"NumberOfClientsServed('CS_New_Pos_Male_Adult'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_New_Pos_Male_15_17\" id=\"CS_New_Pos_Male_15_17\" value=\""+CS_New_Pos_Male_15_17+"\" tabindex=\"2\"  onblur=\"autosave('CS_New_Pos_Male_15_17');\"  oninput=\"NumberOfClientsServed('CS_New_Pos_Male_15_17'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_New_Pos_Male_Postnatal\" id=\"CS_New_Pos_Male_Postnatal\" value=\""+CS_New_Pos_Male_Postnatal+"\" tabindex=\"3\"  onblur=\"autosave('CS_New_Pos_Male_Postnatal');\"  oninput=\"NumberOfClientsServed('CS_New_Pos_Male_Postnatal'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_New_Pos_Male_0_59M\" id=\"CS_New_Pos_Male_0_59M\" value=\""+CS_New_Pos_Male_0_59M+"\" tabindex=\"4\"  onblur=\"autosave('CS_New_Pos_Male_0_59M');\"  oninput=\"NumberOfClientsServed('CS_New_Pos_Male_0_59M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_New_Pos_Male_5_15\" id=\"CS_New_Pos_Male_5_15\" value=\""+CS_New_Pos_Male_5_15+"\" tabindex=\"5\"  onblur=\"autosave('CS_New_Pos_Male_5_15');\"  oninput=\"NumberOfClientsServed('CS_New_Pos_Male_5_15'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td rowspan=\"2\"><input type=\"text\" name=\"CS_New_Pos_Readmission\" id=\"CS_New_Pos_Readmission\" value=\""+CS_New_Pos_Readmission+"\" tabindex=\"11\"  onblur=\"autosave('CS_New_Pos_Readmission');\"  oninput=\"NumberOfClientsServed('CS_New_Pos_Readmission'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td rowspan=\"2\"><input type=\"text\" name=\"CS_New_Pos_Relapse\" id=\"CS_New_Pos_Relapse\" value=\""+CS_New_Pos_Relapse+"\" tabindex=\"12\"  onblur=\"autosave('CS_New_Pos_Relapse');\"  oninput=\"NumberOfClientsServed('CS_New_Pos_Relapse'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td rowspan=\"2\"><input type=\"text\" name=\"CS_New_Pos_LinkedOVC\" id=\"CS_New_Pos_LinkedOVC\" value=\""+CS_New_Pos_LinkedOVC+"\" tabindex=\"13\"  onblur=\"autosave('CS_New_Pos_LinkedOVC');\"  oninput=\"NumberOfClientsServed('CS_New_Pos_LinkedOVC'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "</tr>"
        
        + "<tr>"
            + "<td>Female</td>"
            + "<td><input type=\"text\" name=\"CS_New_Pos_Female_Adult\" id=\"CS_New_Pos_Female_Adult\" value=\""+CS_New_Pos_Female_Adult+"\" tabindex=\"6\"  onblur=\"autosave('CS_New_Pos_Female_Adult');\"  oninput=\"NumberOfClientsServed('CS_New_Pos_Female_Adult'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_New_Pos_Female_15_17\" id=\"CS_New_Pos_Female_15_17\" value=\""+CS_New_Pos_Female_15_17+"\" tabindex=\"7\"  onblur=\"autosave('CS_New_Pos_Female_15_17');\"  oninput=\"NumberOfClientsServed('CS_New_Pos_Female_15_17'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_New_Pos_Female_Postnatal\" id=\"CS_New_Pos_Female_Postnatal\" value=\""+CS_New_Pos_Female_Postnatal+"\" tabindex=\"8\"  onblur=\"autosave('CS_New_Pos_Female_Postnatal');\"  oninput=\"NumberOfClientsServed('CS_New_Pos_Female_Postnatal'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_New_Pos_Female_0_59M\" id=\"CS_New_Pos_Female_0_59M\" value=\""+CS_New_Pos_Female_0_59M+"\" tabindex=\"9\"  onblur=\"autosave('CS_New_Pos_Female_0_59M');\"  oninput=\"NumberOfClientsServed('CS_New_Pos_Female_0_59M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_New_Pos_Female_5_15\" id=\"CS_New_Pos_Female_5_15\" value=\""+CS_New_Pos_Female_5_15+"\" tabindex=\"10\"  onblur=\"autosave('CS_New_Pos_Female_5_15');\"  oninput=\"NumberOfClientsServed('CS_New_Pos_Female_5_15'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "</tr>"
        
        + "<tr>"
            + "<td rowspan=\"2\">HIV Negative</td>"
            + "<td>Male</td>"
            + "<td><input type=\"text\" name=\"CS_New_Neg_Male_Adult\" id=\"CS_New_Neg_Male_Adult\" value=\""+CS_New_Neg_Male_Adult+"\" tabindex=\"14\"  onblur=\"autosave('CS_New_Neg_Male_Adult');\"  oninput=\"NumberOfClientsServed('CS_New_Neg_Male_Adult'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_New_Neg_Male_15_17\" id=\"CS_New_Neg_Male_15_17\" value=\""+CS_New_Neg_Male_15_17+"\" tabindex=\"15\"  onblur=\"autosave('CS_New_Neg_Male_15_17');\"  oninput=\"NumberOfClientsServed('CS_New_Neg_Male_15_17'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_New_Neg_Male_Postnatal\" id=\"CS_New_Neg_Male_Postnatal\" value=\""+CS_New_Neg_Male_Postnatal+"\" tabindex=\"16\"  onblur=\"autosave('CS_New_Neg_Male_Postnatal');\"  oninput=\"NumberOfClientsServed('CS_New_Neg_Male_Postnatal'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_New_Neg_Male_0_59M\" id=\"CS_New_Neg_Male_0_59M\" value=\""+CS_New_Neg_Male_0_59M+"\" tabindex=\"17\"  onblur=\"autosave('CS_New_Neg_Male_0_59M');\"  oninput=\"NumberOfClientsServed('CS_New_Neg_Male_0_59M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_New_Neg_Male_5_15\" id=\"CS_New_Neg_Male_5_15\" value=\""+CS_New_Neg_Male_5_15+"\" tabindex=\"18\"  onblur=\"autosave('CS_New_Neg_Male_5_15');\"  oninput=\"NumberOfClientsServed('CS_New_Neg_Male_5_15'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td rowspan=\"2\"><input type=\"text\" name=\"CS_New_Neg_Readmission\" id=\"CS_New_Neg_Readmission\" value=\""+CS_New_Neg_Readmission+"\" tabindex=\"24\"  onblur=\"autosave('CS_New_Neg_Readmission');\"  oninput=\"NumberOfClientsServed('CS_New_Neg_Readmission'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td rowspan=\"2\"><input type=\"text\" name=\"CS_New_Neg_Relapse\" id=\"CS_New_Neg_Relapse\" value=\""+CS_New_Neg_Relapse+"\" tabindex=\"25\"  onblur=\"autosave('CS_New_Neg_Relapse');\"  oninput=\"NumberOfClientsServed('CS_New_Neg_Relapse'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td rowspan=\"2\"><input type=\"text\" name=\"CS_New_Neg_LinkedOVC\" id=\"CS_New_Neg_LinkedOVC\" value=\""+CS_New_Neg_LinkedOVC+"\" tabindex=\"26\"  onblur=\"autosave('CS_New_Neg_LinkedOVC');\"  oninput=\"NumberOfClientsServed('CS_New_Neg_LinkedOVC'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "</tr>"
        
        + "<tr>"
            + "<td>Female</td>"
            + "<td><input type=\"text\" name=\"CS_New_Neg_Female_Adult\" id=\"CS_New_Neg_Female_Adult\" value=\""+CS_New_Neg_Female_Adult+"\" tabindex=\"19\"  onblur=\"autosave('CS_New_Neg_Female_Adult');\"  oninput=\"NumberOfClientsServed('CS_New_Neg_Female_Adult'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_New_Neg_Female_15_17\" id=\"CS_New_Neg_Female_15_17\" value=\""+CS_New_Neg_Female_15_17+"\" tabindex=\"20\"  onblur=\"autosave('CS_New_Neg_Female_15_17');\"  oninput=\"NumberOfClientsServed('CS_New_Neg_Female_15_17'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_New_Neg_Female_Postnatal\" id=\"CS_New_Neg_Female_Postnatal\" value=\""+CS_New_Neg_Female_Postnatal+"\" tabindex=\"21\"  onblur=\"autosave('CS_New_Neg_Female_Postnatal');\"  oninput=\"NumberOfClientsServed(''); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_New_Neg_Female_0_59M\" id=\"CS_New_Neg_Female_0_59M\" value=\""+CS_New_Neg_Female_0_59M+"\" tabindex=\"22\"  onblur=\"autosave('CS_New_Neg_Female_0_59M');\"  oninput=\"NumberOfClientsServed('CS_New_Neg_Female_Postnatal'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_New_Neg_Female_5_15\" id=\"CS_New_Neg_Female_5_15\" value=\""+CS_New_Neg_Female_5_15+"\" tabindex=\"23\"  onblur=\"autosave('CS_New_Neg_Female_5_15');\"  oninput=\"NumberOfClientsServed('CS_New_Neg_Female_5_15'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "</tr>"
        
        + "<tr>"
            + "<td rowspan=\"2\">REVISITING</td>"
            + "<td colspan=\"2\">HIV Positive</td>"
            + "<td><input type=\"text\" name=\"CS_Revisit_Pos_Adult\" id=\"CS_Revisit_Pos_Adult\" value=\""+CS_Revisit_Pos_Adult+"\" tabindex=\"27\"  onblur=\"autosave('CS_Revisit_Pos_Adult');\"  oninput=\"NumberOfClientsServed('CS_Revisit_Pos_Adult'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_Revisit_Pos_15_17\" id=\"CS_Revisit_Pos_15_17\" value=\""+CS_Revisit_Pos_15_17+"\" tabindex=\"28\"  onblur=\"autosave('CS_Revisit_Pos_15_17');\"  oninput=\"NumberOfClientsServed('CS_Revisit_Pos_15_17'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_Revisit_Pos_Postnatal\" id=\"CS_Revisit_Pos_Postnatal\" value=\""+CS_Revisit_Pos_Postnatal+"\" tabindex=\"29\"  onblur=\"autosave('CS_Revisit_Pos_Postnatal');\"  oninput=\"NumberOfClientsServed('CS_Revisit_Pos_Postnatal'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_Revisit_Pos_0_59M\" id=\"CS_Revisit_Pos_0_59M\" value=\""+CS_Revisit_Pos_0_59M+"\" tabindex=\"30\"  onblur=\"autosave('CS_Revisit_Pos_0_59M');\"  oninput=\"NumberOfClientsServed('CS_Revisit_Pos_0_59M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_Revisit_Pos_5_15\" id=\"CS_Revisit_Pos_5_15\" value=\""+CS_Revisit_Pos_5_15+"\" tabindex=\"31\"  onblur=\"autosave('CS_Revisit_Pos_5_15');\"  oninput=\"NumberOfClientsServed('CS_Revisit_Pos_5_15'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td></td>"
            + "<td></td>"
            + "<td></td>"
            + "</tr>"
        
        + "<tr>"

            + "<td colspan=\"2\">HIV Negative</td>"
            + "<td><input type=\"text\" name=\"CS_Revisit_Neg_Adult\" id=\"CS_Revisit_Neg_Adult\" value=\""+CS_Revisit_Neg_Adult+"\" tabindex=\"32\"  onblur=\"autosave('CS_Revisit_Neg_Adult');\"  oninput=\"NumberOfClientsServed('CS_Revisit_Neg_Adult'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_Revisit_Neg_15_17\" id=\"CS_Revisit_Neg_15_17\" value=\""+CS_Revisit_Neg_15_17+"\" tabindex=\"33\"  onblur=\"autosave('CS_Revisit_Neg_15_17');\"  oninput=\"NumberOfClientsServed('CS_Revisit_Neg_15_17'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_Revisit_Neg_Postnatal\" id=\"CS_Revisit_Neg_Postnatal\" value=\""+CS_Revisit_Neg_Postnatal+"\" tabindex=\"34\"  onblur=\"autosave('CS_Revisit_Neg_Postnatal');\"  oninput=\"NumberOfClientsServed('CS_Revisit_Neg_Postnatal'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_Revisit_Neg_0_59M\" id=\"CS_Revisit_Neg_0_59M\" value=\""+CS_Revisit_Neg_0_59M+"\" tabindex=\"35\"  onblur=\"autosave('CS_Revisit_Neg_0_59M');\"  oninput=\"NumberOfClientsServed('CS_Revisit_Neg_0_59M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td><input type=\"text\" name=\"CS_Revisit_Neg_5_15\" id=\"CS_Revisit_Neg_5_15\" value=\""+CS_Revisit_Neg_5_15+"\" tabindex=\"36\"  onblur=\"autosave('CS_Revisit_Neg_5_15');\"  oninput=\"NumberOfClientsServed('CS_Revisit_Neg_5_15'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
            + "<td></td>"
            + "<td></td>"
            + "<td></td>"
            + "</tr>"
        
        + "<tr class=\"totals\">"
            + "<td colspan=\"3\">Total</td>"
            + "<td><input type=\"text\" name=\"CS_Total_Adult\" id=\"CS_Total_Adult\" value=\""+CS_Total_Adult+"\"   onblur=\"autosave('CS_Total_Adult');\"  oninput=\"NumberOfClientsServed('CS_Total_Adult'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
            + "<td><input type=\"text\" name=\"CS_Total_15_17\" id=\"CS_Total_15_17\" value=\""+CS_Total_15_17+"\"   onblur=\"autosave('CS_Total_15_17');\"  oninput=\"NumberOfClientsServed('CS_Total_15_17'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
            + "<td><input type=\"text\" name=\"CS_Total_Postnatal\" id=\"CS_Total_Postnatal\" value=\""+CS_Total_Postnatal+"\"   onblur=\"autosave('CS_Total_Postnatal');\"  oninput=\"NumberOfClientsServed('CS_Total_Postnatal'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
            + "<td><input type=\"text\" name=\"CS_Total_0_59M\" id=\"CS_Total_0_59M\" value=\""+CS_Total_0_59M+"\"   onblur=\"autosave('CS_Total_0_59M');\"  oninput=\"NumberOfClientsServed('CS_Total_0_59M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
            + "<td><input type=\"text\" name=\"CS_Total_5_15\" id=\"CS_Total_5_15\" value=\""+CS_Total_5_15+"\"   onblur=\"autosave('CS_Total_5_15');\"  oninput=\"NumberOfClientsServed('CS_Total_5_15'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
            + "<td><input type=\"text\" name=\"CS_Total_Readmission\" id=\"CS_Total_Readmission\" value=\""+CS_Total_Readmission+"\"   onblur=\"autosave('CS_Total_Readmission');\"  oninput=\"NumberOfClientsServed('CS_Total_Readmission'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
            + "<td><input type=\"text\" name=\"CS_Total_Relapse\" id=\"CS_Total_Relapse\" value=\""+CS_Total_Relapse+"\"   onblur=\"autosave('CS_Total_Relapse');\"  oninput=\"NumberOfClientsServed('CS_Total_Relapse'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
            + "<td><input type=\"text\" name=\"CS_Total_LinkedOVC\" id=\"CS_Total_LinkedOVC\" value=\""+CS_Total_LinkedOVC+"\"   onblur=\"autosave('CS_Total_LinkedOVC');\"  oninput=\"NumberOfClientsServed('CS_Total_LinkedOVC'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
            + "</tr>"
        + "<tr><td colspan=\"12\"></tr>";
//            + "</table>"
//        + "<br>";
//        end tab index 36


NH1 =  ""
//        + "<table>"
        + "<tr>"
        + "<td rowspan=\"2\"></td>"
        + "<td rowspan=\"2\">HIV Positive</td>"
        + "<td>SAM</td>"
        + "<td></td>"
        + "<td><input type=\"text\" name=\"NH1_Pos_SAM_Adult\" id=\"NH1_Pos_SAM_Adult\" value=\""+NH1_Pos_SAM_Adult+"\" tabindex=\"37\"  onblur=\"autosave('NH1_Pos_SAM_Adult');\"  oninput=\"NH1('NH1_Pos_SAM_Adult'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH1_Pos_SAM_15_17\" id=\"NH1_Pos_SAM_15_17\" value=\""+NH1_Pos_SAM_15_17+"\" tabindex=\"38\"  onblur=\"autosave('NH1_Pos_SAM_15_17');\"  oninput=\"NH1('NH1_Pos_SAM_15_17'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH1_Pos_SAM_Postnatal\" id=\"NH1_Pos_SAM_Postnatal\" value=\""+NH1_Pos_SAM_Postnatal+"\" tabindex=\"39\"  onblur=\"autosave('NH1_Pos_SAM_Postnatal');\"  oninput=\"NH1('NH1_Pos_SAM_Postnatal'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH1_Pos_SAM_0_59M\" id=\"NH1_Pos_SAM_0_59M\" value=\""+NH1_Pos_SAM_0_59M+"\" tabindex=\"40\"  onblur=\"autosave('NH1_Pos_SAM_0_59M');\"  oninput=\"NH1('NH1_Pos_SAM_0_59M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH1_Pos_SAM_5_15\" id=\"NH1_Pos_SAM_5_15\" value=\""+NH1_Pos_SAM_5_15+"\" tabindex=\"41\"  onblur=\"autosave('NH1_Pos_SAM_5_15');\"  oninput=\"NH1('NH1_Pos_SAM_5_15'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td class=\"totals\"><input type=\"text\" name=\"NH1_Pos_SAM_Total\" id=\"NH1_Pos_SAM_Total\" value=\""+NH1_Pos_SAM_Total+"\"   onblur=\"autosave('NH1_Pos_SAM_Total');\"  oninput=\"NH1('NH1_Pos_SAM_Total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "</tr>"
        
        + "<tr>"
        + "<td>MAM</td>"
         + "<td></td>"
        + "<td><input type=\"text\" name=\"NH1_Pos_MAM_Adult\" id=\"NH1_Pos_MAM_Adult\" value=\""+NH1_Pos_MAM_Adult+"\" tabindex=\"42\"  onblur=\"autosave('NH1_Pos_MAM_Adult');\"  oninput=\"NH1('NH1_Pos_MAM_Adult'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH1_Pos_MAM_15_17\" id=\"NH1_Pos_MAM_15_17\" value=\""+NH1_Pos_MAM_15_17+"\" tabindex=\"43\"  onblur=\"autosave('NH1_Pos_MAM_15_17');\"  oninput=\"NH1('NH1_Pos_MAM_15_17'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH1_Pos_MAM_Postnatal\" id=\"NH1_Pos_MAM_Postnatal\" value=\""+NH1_Pos_MAM_Postnatal+"\" tabindex=\"44\"  onblur=\"autosave('NH1_Pos_MAM_Postnatal');\"  oninput=\"NH1('NH1_Pos_MAM_Postnatal'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH1_Pos_MAM_0_59M\" id=\"NH1_Pos_MAM_0_59M\" value=\""+NH1_Pos_MAM_0_59M+"\" tabindex=\"45\"  onblur=\"autosave('NH1_Pos_MAM_0_59M');\"  oninput=\"NH1('NH1_Pos_MAM_0_59M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH1_Pos_MAM_5_15\" id=\"NH1_Pos_MAM_5_15\" value=\""+NH1_Pos_MAM_5_15+"\" tabindex=\"46\"  onblur=\"autosave('NH1_Pos_MAM_5_15');\"  oninput=\"NH1('NH1_Pos_MAM_5_15'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td class=\"totals\"><input type=\"text\" name=\"NH1_Pos_MAM_Total\" id=\"NH1_Pos_MAM_Total\" value=\""+NH1_Pos_MAM_Total+"\"   onblur=\"autosave('NH1_Pos_MAM_Total');\"  oninput=\"NH1('NH1_Pos_MAM_Total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "</tr>"
        
        + "<tr>"
        + "<td rowspan=\"3\"></td>"
        + "<td  rowspan=\"2\">HIV Negative</td>"
        + "<td>SAM</td>"
         + "<td></td>"
        + "<td><input type=\"text\" name=\"NH1_Neg_SAM_Adult\" id=\"NH1_Neg_SAM_Adult\" value=\""+NH1_Neg_SAM_Adult+"\" tabindex=\"47\"  onblur=\"autosave('NH1_Neg_SAM_Adult');\"  oninput=\"NH1('NH1_Neg_SAM_Adult'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH1_Neg_SAM_15_17\" id=\"NH1_Neg_SAM_15_17\" value=\""+NH1_Neg_SAM_15_17+"\" tabindex=\"48\"  onblur=\"autosave('NH1_Neg_SAM_15_17');\"  oninput=\"NH1('NH1_Neg_SAM_15_17'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH1_Neg_SAM_Postnatal\" id=\"NH1_Neg_SAM_Postnatal\" value=\""+NH1_Neg_SAM_Postnatal+"\" tabindex=\"49\"  onblur=\"autosave('NH1_Neg_SAM_Postnatal');\"  oninput=\"NH1('NH1_Neg_SAM_Postnatal'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH1_Neg_SAM_0_59M\" id=\"NH1_Neg_SAM_0_59M\" value=\""+NH1_Neg_SAM_0_59M+"\" tabindex=\"50\"  onblur=\"autosave('NH1_Neg_SAM_0_59M');\"  oninput=\"NH1('NH1_Neg_SAM_0_59M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH1_Neg_SAM_5_15\" id=\"NH1_Neg_SAM_5_15\" value=\""+NH1_Neg_SAM_5_15+"\" tabindex=\"51\"  onblur=\"autosave('NH1_Neg_SAM_5_15');\"  oninput=\"NH1('NH1_Neg_SAM_5_15'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td class=\"totals\"><input type=\"text\" name=\"NH1_Neg_SAM_Total\" id=\"NH1_Neg_SAM_Total\" value=\""+NH1_Neg_SAM_Total+"\"   onblur=\"autosave('NH1_Neg_SAM_Total');\"  oninput=\"NH1('NH1_Neg_SAM_Total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "</tr>"
        
        + "<tr>"
        + "<td>MAM</td>"
         + "<td></td>"
        + "<td><input type=\"text\" name=\"NH1_Neg_MAM_Adult\" id=\"NH1_Neg_MAM_Adult\" value=\""+NH1_Neg_MAM_Adult+"\" tabindex=\"52\"  onblur=\"autosave('NH1_Neg_MAM_Adult');\"  oninput=\"NH1('NH1_Neg_MAM_Adult'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH1_Neg_MAM_15_17\" id=\"NH1_Neg_MAM_15_17\" value=\""+NH1_Neg_MAM_15_17+"\" tabindex=\"53\"  onblur=\"autosave('NH1_Neg_MAM_15_17');\"  oninput=\"NH1('NH1_Neg_MAM_15_17'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH1_Neg_MAM_Postnatal\" id=\"NH1_Neg_MAM_Postnatal\" value=\""+NH1_Neg_MAM_Postnatal+"\" tabindex=\"54\"  onblur=\"autosave('NH1_Neg_MAM_Postnatal');\"  oninput=\"NH1('NH1_Neg_MAM_Postnatal'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH1_Neg_MAM_0_59M\" id=\"NH1_Neg_MAM_0_59M\" value=\""+NH1_Neg_MAM_0_59M+"\" tabindex=\"55\"  onblur=\"autosave('NH1_Neg_MAM_0_59M');\"  oninput=\"NH1('NH1_Neg_MAM_0_59M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH1_Neg_MAM_5_15\" id=\"NH1_Neg_MAM_5_15\" value=\""+NH1_Neg_MAM_5_15+"\" tabindex=\"56\"  onblur=\"autosave('NH1_Neg_MAM_5_15');\"  oninput=\"NH1('NH1_Neg_MAM_5_15'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td class=\"totals\"><input type=\"text\" name=\"NH1_Neg_MAM_Total\" id=\"NH1_Neg_MAM_Total\" value=\""+NH1_Neg_MAM_Total+"\"   onblur=\"autosave('NH1_Neg_MAM_Total');\"  oninput=\"NH1('NH1_Neg_MAM_Total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "</tr>"
        
        + "<tr class=\"totals\">"
        + "<td colspan=\"3\">Total</td>"
       + "<td><input type=\"text\" name=\"NH1_Total_Adult\" id=\"NH1_Total_Adult\" value=\""+NH1_Total_Adult+"\"   onblur=\"autosave('NH1_Total_Adult');\"  oninput=\"NH1('NH1_Total_Adult'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
       + "<td><input type=\"text\" name=\"NH1_Total_15_17\" id=\"NH1_Total_15_17\" value=\""+NH1_Total_15_17+"\"   onblur=\"autosave('NH1_Total_15_17');\"  oninput=\"NH1('NH1_Total_15_17'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
       + "<td><input type=\"text\" name=\"NH1_Total_Postnatal\" id=\"NH1_Total_Postnatal\" value=\""+NH1_Total_Postnatal+"\"   onblur=\"autosave('NH1_Total_Postnatal');\"  oninput=\"NH1('NH1_Total_Postnatal'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
       + "<td><input type=\"text\" name=\"NH1_Total_0_59M\" id=\"NH1_Total_0_59M\" value=\""+NH1_Total_0_59M+"\"   onblur=\"autosave('NH1_Total_0_59M');\"  oninput=\"NH1('NH1_Total_0_59M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
       + "<td><input type=\"text\" name=\"NH1_Total_5_15\" id=\"NH1_Total_5_15\" value=\""+NH1_Total_5_15+"\"   onblur=\"autosave('NH1_Total_5_15');\"  oninput=\"NH1('NH1_Total_5_15'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
       + "<td ><input type=\"text\" name=\"NH1_Grand_Total\" id=\"NH1_Grand_Total\" value=\""+NH1_Grand_Total+"\"   onblur=\"autosave('NH1_Grand_Total');\"  oninput=\"NH1('NH1_Grand_Total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
       + "</tr>"
        + "<tr><td colspan=\"12\"></tr>";

//ENDS AT 56


IFP =   "<tr>"
        + "<td rowspan=\"6\"><p  class =\"rotate\" >Infant Feeding Practices</p></td>"
        + "<td colspan=\"3\">First 6 months</td>"
        + "<td>Positive</td>"
        + "<td>Negative</td>"
        + "<td></td>"
        + "<td colspan=\"3\">6 to 12 months</td>"
        + "<td>Positive</td>"
        + "<td>Negative</td>"
        + ""
        + "</tr>"
        
        
        + "<tr>"
        + "<td colspan=\"3\">EBF</td>"
        + "<td><input type=\"text\" name=\"IFP_0_6_EBF_Pos\" id=\"IFP_0_6_EBF_Pos\" value=\""+IFP_0_6_EBF_Pos+"\" onblur=\"autosave('IFP_0_6_EBF_Pos');\"  tabindex=\"109\" oninput=\"InfantFeedingPractices('IFP_0_6_EBF_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"IFP_0_6_EBF_Neg\" id=\"IFP_0_6_EBF_Neg\" value=\""+IFP_0_6_EBF_Neg+"\" onblur=\"autosave('IFP_0_6_EBF_Neg');\"  tabindex=\"110\" oninput=\"InfantFeedingPractices('IFP_0_6_EBF_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td></td>"
        + "<td colspan=\"3\">Breast feeding</td>"
        + "<td><input type=\"text\" name=\"IFP_6_12_BF_Pos\" id=\"IFP_6_12_BF_Pos\" value=\""+IFP_6_12_BF_Pos+"\" onblur=\"autosave('IFP_6_12_BF_Pos');\"  tabindex=\"115\" oninput=\"InfantFeedingPractices('IFP_6_12_BF_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"IFP_6_12_BF_Neg\" id=\"IFP_6_12_BF_Neg\" value=\""+IFP_6_12_BF_Neg+"\" onblur=\"autosave('IFP_6_12_BF_Neg');\"  tabindex=\"116\" oninput=\"InfantFeedingPractices('IFP_6_12_BF_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "</tr>"
        
        + "<tr>"
        + "<td colspan=\"3\">ERF</td>"
        + "<td><input type=\"text\" name=\"IFP_0_6_ERF_Pos\" id=\"IFP_0_6_ERF_Pos\" value=\""+IFP_0_6_ERF_Pos+"\" onblur=\"autosave('IFP_0_6_ERF_Pos');\"  tabindex=\"111\" oninput=\" InfantFeedingPractices('IFP_0_6_ERF_Pos');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"IFP_0_6_ERF_Neg\" id=\"IFP_0_6_ERF_Neg\" value=\""+IFP_0_6_ERF_Neg+"\" onblur=\"autosave('IFP_0_6_ERF_Neg');\"  tabindex=\"112\" oninput=\" InfantFeedingPractices('IFP_0_6_ERF_Neg');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td></td>"
        + "<td colspan=\"3\">Not Breat Feeding</td>"
        + "<td><input type=\"text\" name=\"IFP_6_12_NBF_Pos\" id=\"IFP_6_12_NBF_Pos\" value=\""+IFP_6_12_NBF_Pos+"\" onblur=\"autosave('IFP_6_12_NBF_Pos');\"  tabindex=\"117\" oninput=\"InfantFeedingPractices('IFP_6_12_NBF_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"IFP_6_12_NBF_Neg\" id=\"IFP_6_12_NBF_Neg\" value=\""+IFP_6_12_NBF_Neg+"\" onblur=\"autosave('IFP_6_12_NBF_Neg');\"  tabindex=\"118\" oninput=\"InfantFeedingPractices('IFP_6_12_NBF_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "</tr>"
        
        + "<tr>"
        + "<td colspan=\"3\"  rowspan=\"2\">MF</td>"
        + "<td rowspan=\"2\"><input type=\"text\" name=\"IFP_0_6_MF_Pos\" id=\"IFP_0_6_MF_Pos\" value=\""+IFP_0_6_MF_Pos+"\" onblur=\"autosave('IFP_0_6_MF_Pos');\"  tabindex=\"113\" oninput=\"InfantFeedingPractices('IFP_0_6_MF_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td rowspan=\"2\"><input type=\"text\" name=\"IFP_0_6_MF_Neg\" id=\"IFP_0_6_MF_Neg\" value=\""+IFP_0_6_MF_Neg+"\" onblur=\"autosave('IFP_0_6_MF_Neg');\"  tabindex=\"114\" oninput=\" InfantFeedingPractices('IFP_0_6_MF_Neg');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td></td>"
        + "<td colspan=\"3\">Not Known</td>"
        + "<td><input type=\"text\" name=\"IFP_6_12_NotKnown_Pos\" id=\"IFP_6_12_NotKnown_Pos\" value=\""+IFP_6_12_NotKnown_Pos+"\" onblur=\"autosave('IFP_6_12_NotKnown_Pos');\"  tabindex=\"119\" oninput=\"InfantFeedingPractices('IFP_6_12_NotKnown_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"IFP_6_12_NotKnown_Neg\" id=\"IFP_6_12_NotKnown_Neg\" value=\""+IFP_6_12_NotKnown_Neg+"\" onblur=\"autosave('IFP_6_12_NotKnown_Neg');\"  tabindex=\"120\" oninput=\"InfantFeedingPractices('IFP_6_12_NotKnown_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "</tr>"
        
        + "<tr>"
        + "<td></td>"
        + "<td colspan=\"3\">Began Complementary feeding</td>"
        + "<td><input type=\"text\" name=\"IFP_6_12_BCF_Pos\" id=\"IFP_6_12_BCF_Pos\" value=\""+IFP_6_12_BCF_Pos+"\" onblur=\"autosave('IFP_6_12_BCF_Pos');\"  tabindex=\"121\" oninput=\" InfantFeedingPractices('IFP_6_12_BCF_Pos');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"IFP_6_12_BCF_Neg\" id=\"IFP_6_12_BCF_Neg\" value=\""+IFP_6_12_BCF_Neg+"\" onblur=\"autosave('IFP_6_12_BCF_Neg');\"  tabindex=\"122\" oninput=\"InfantFeedingPractices('IFP_6_12_BCF_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "</tr>"
        
        + "<tr class=\"totals\">"
        + "<td colspan=\"3\">Sub Total</td>"
        + "<td><input type=\"text\" name=\"IFP_0_6_SubTotal_Pos\" id=\"IFP_0_6_SubTotal_Pos\" value=\""+IFP_0_6_SubTotal_Pos+"\"   onblur=\"autosave('IFP_0_6_SubTotal_Pos');\"  oninput=\" InfantFeedingPractices('IFP_0_6_SubTotal_Pos');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"IFP_0_6_SubTotal_Neg\" id=\"IFP_0_6_SubTotal_Neg\" value=\""+IFP_0_6_SubTotal_Neg+"\"   onblur=\"autosave('IFP_0_6_SubTotal_Neg');\"  oninput=\"InfantFeedingPractices('IFP_0_6_SubTotal_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td></td>"
        + "<td colspan=\"3\">Sub Total</td>"
        + "<td><input type=\"text\" name=\"IFP_6_12_SubTotal_Pos\" id=\"IFP_6_12_SubTotal_Pos\" value=\""+IFP_6_12_SubTotal_Pos+"\"   onblur=\"autosave('IFP_6_12_SubTotal_Pos');\"  oninput=\"InfantFeedingPractices('IFP_6_12_SubTotal_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"IFP_6_12_SubTotal_Neg\" id=\"IFP_6_12_SubTotal_Neg\" value=\""+IFP_6_12_SubTotal_Neg+"\"   onblur=\"autosave('IFP_6_12_SubTotal_Neg');\"  oninput=\" InfantFeedingPractices('IFP_6_12_SubTotal_Neg');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "</tr>"
        
        
        + ""
        + "<tr><td colspan=\"12\"></tr>"
        + "";

NI =     "<tr>"
        + "<td rowspan=\"9\"> <p class=\"rotate\"> Nutrition Interventions</p></td>"
        + "<td colspan=\"4\"></td>"
        + "<td>Adult/ 15-17 yrs (Positive)</td>"
        + "<td>Pregnant/Postnatal (Positive)</td>"
        + "<td>All Adult Negative</td>"
        + "<td>0-59 months (Positive)</td>"
        + "<td>5-15 years (Positive)</td>"
        + "<td>0-59 months (Negative)</td>"
        + "<td>5-15 years (Negative)</td>"
        + "<td>Total</td>"
        + "</tr>"
    
//                end of NI
         + "<tr>"
        + "<td rowspan=\"8\"></td>"
        + "<td colspan=\"3\">Therapeutic diet milk (F75/F100) </td>"
        + "<td><input type=\"text\" name=\"NH2_TCM_Adults15_17_Pos\" id=\"NH2_TCM_Adults15_17_Pos\" value=\""+NH2_TCM_Adults15_17_Pos+"\" onblur=\"autosave('NH2_TCM_Adults15_17_Pos');\"  tabindex=\"158\" oninput=\"NH2('NH2_TCM_Adults15_17_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_TCM_Postnatal_Pos\" id=\"NH2_TCM_Postnatal_Pos\" value=\""+NH2_TCM_Postnatal_Pos+"\" onblur=\"autosave('NH2_TCM_Postnatal_Pos');\"  tabindex=\"159\" oninput=\"NH2('NH2_TCM_Postnatal_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_TCM_AllAdults_Neg\" id=\"NH2_TCM_AllAdults_Neg\" value=\""+NH2_TCM_AllAdults_Neg+"\" onblur=\"autosave('NH2_TCM_AllAdults_Neg');\"  tabindex=\"160\" oninput=\"NH2('NH2_TCM_AllAdults_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_TCM_0_59M_Pos\" id=\"NH2_TCM_0_59M_Pos\" value=\""+NH2_TCM_0_59M_Pos+"\" onblur=\"autosave('NH2_TCM_0_59M_Pos');\"  tabindex=\"161\" oninput=\"NH2('NH2_TCM_0_59M_Pos');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_TCM_5_15_Pos\" id=\"NH2_TCM_5_15_Pos\" value=\""+NH2_TCM_5_15_Pos+"\" onblur=\"autosave('NH2_TCM_5_15_Pos');\"  tabindex=\"162\" oninput=\"NH2('NH2_TCM_5_15_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_TCM_0_59M_Neg\" id=\"NH2_TCM_0_59M_Neg\" value=\""+NH2_TCM_0_59M_Neg+"\" onblur=\"autosave('NH2_TCM_0_59M_Neg');\"  tabindex=\"163\" oninput=\"NH2('NH2_TCM_0_59M_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_TCM_5_15_Neg\" id=\"NH2_TCM_5_15_Neg\" value=\""+NH2_TCM_5_15_Neg+"\" onblur=\"autosave('NH2_TCM_5_15_Neg');\"  tabindex=\"164\" oninput=\"NH2('NH2_TCM_5_15_Neg');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td class=\"totals\"><input type=\"text\" name=\"NH2_TCM_Total\" id=\"NH2_TCM_Total\" value=\""+NH2_TCM_Total+"\"   onblur=\"autosave('NH2_TCM_Total');\"  oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "</tr>"
        
                
         + "<tr>"
        + "<td colspan=\"3\">Ready to Use Therapeutic Foods (RUTFS)</td>"
        + "<td><input type=\"text\" name=\"NH2_RUTF_Adults15_17_Pos\" id=\"NH2_RUTF_Adults15_17_Pos\" value=\""+NH2_RUTF_Adults15_17_Pos+"\" onblur=\"autosave('NH2_RUTF_Adults15_17_Pos');\"  tabindex=\"165\" oninput=\"NH2('NH2_RUTF_Adults15_17_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_RUTF_Postnatal_Pos\" id=\"NH2_RUTF_Postnatal_Pos\" value=\""+NH2_RUTF_Postnatal_Pos+"\" onblur=\"autosave('NH2_RUTF_Postnatal_Pos');\"  tabindex=\"166\" oninput=\"NH2('NH2_RUTF_Postnatal_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_RUTF_AllAdults_Neg\" id=\"NH2_RUTF_AllAdults_Neg\" value=\""+NH2_RUTF_AllAdults_Neg+"\" onblur=\"autosave('NH2_RUTF_AllAdults_Neg');\"  tabindex=\"167\" oninput=\"NH2('NH2_RUTF_AllAdults_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_RUTF_0_59M_Pos\" id=\"NH2_RUTF_0_59M_Pos\" value=\""+NH2_RUTF_0_59M_Pos+"\" onblur=\"autosave('NH2_RUTF_0_59M_Pos');\"  tabindex=\"168\" oninput=\"NH2('NH2_RUTF_0_59M_Pos');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_RUTF_5_15_Pos\" id=\"NH2_RUTF_5_15_Pos\" value=\""+NH2_RUTF_5_15_Pos+"\" onblur=\"autosave('NH2_RUTF_5_15_Pos');\"  tabindex=\"169\" oninput=\"NH2('NH2_RUTF_5_15_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_RUTF_0_59M_Neg\" id=\"NH2_RUTF_0_59M_Neg\" value=\""+NH2_RUTF_0_59M_Neg+"\" onblur=\"autosave('NH2_RUTF_0_59M_Neg');\"  tabindex=\"170\" oninput=\"NH2('NH2_RUTF_0_59M_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_RUTF_5_15_Neg\" id=\"NH2_RUTF_5_15_Neg\" value=\""+NH2_RUTF_5_15_Neg+"\" onblur=\"autosave('NH2_RUTF_5_15_Neg');\"  tabindex=\"171\" oninput=\"NH2('NH2_RUTF_5_15_Neg');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td class=\"totals\"><input type=\"text\" name=\"NH2_RUTF_Total\" id=\"NH2_RUTF_Total\" value=\""+NH2_RUTF_Total+"\"   onblur=\"autosave('NH2_RUTF_Total');\"  oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "</tr>"
        
                
         + "<tr>"
        + "<td colspan=\"3\">Ready to Use Supplemental Foods (RUSF)</td>"
        + "<td><input type=\"text\" name=\"NH2_RUSF_Adults15_17_Pos\" id=\"NH2_RUSF_Adults15_17_Pos\" value=\""+NH2_RUSF_Adults15_17_Pos+"\" onblur=\"autosave('NH2_RUSF_Adults15_17_Pos');\"  tabindex=\"172\" oninput=\"NH2('NH2_RUSF_Adults15_17_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_RUSF_Postnatal_Pos\" id=\"NH2_RUSF_Postnatal_Pos\" value=\""+NH2_RUSF_Postnatal_Pos+"\" onblur=\"autosave('NH2_RUSF_Postnatal_Pos');\"  tabindex=\"173\" oninput=\"NH2('NH2_RUSF_Postnatal_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_RUSF_AllAdults_Neg\" id=\"NH2_RUSF_AllAdults_Neg\" value=\""+NH2_RUSF_AllAdults_Neg+"\" onblur=\"autosave('NH2_RUSF_AllAdults_Neg');\"  tabindex=\"174\" oninput=\"NH2(''); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_RUSF_0_59M_Pos\" id=\"NH2_RUSF_0_59M_Pos\" value=\""+NH2_RUSF_0_59M_Pos+"\" onblur=\"autosave('NH2_RUSF_0_59M_Pos');\"  tabindex=\"175\" oninput=\"NH2('NH2_RUSF_AllAdults_Neg');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_RUSF_5_15_Pos\" id=\"NH2_RUSF_5_15_Pos\" value=\""+NH2_RUSF_5_15_Pos+"\" onblur=\"autosave('NH2_RUSF_5_15_Pos');\"  tabindex=\"176\" oninput=\"NH2('NH2_RUSF_5_15_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_RUSF_0_59M_Neg\" id=\"NH2_RUSF_0_59M_Neg\" value=\""+NH2_RUSF_0_59M_Neg+"\" onblur=\"autosave('NH2_RUSF_0_59M_Neg');\"  tabindex=\"177\" oninput=\"NH2('NH2_RUSF_0_59M_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_RUSF_5_15_Neg\" id=\"NH2_RUSF_5_15_Neg\" value=\""+NH2_RUSF_5_15_Neg+"\" onblur=\"autosave('NH2_RUSF_5_15_Neg');\"  tabindex=\"178\" oninput=\"NH2('NH2_RUSF_5_15_Neg');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td class=\"totals\"><input type=\"text\" name=\"NH2_RUSF_Total\" id=\"NH2_RUSF_Total\" value=\""+NH2_RUSF_Total+"\"   onblur=\"autosave('NH2_RUSF_Total');\"  oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "</tr>"
        
                
         + "<tr>"
        + "<td colspan=\"3\">FBF/CSB</td>"
        + "<td><input type=\"text\" name=\"NH2_FBFCSB_Adults15_17_Pos\" id=\"NH2_FBFCSB_Adults15_17_Pos\" value=\""+NH2_FBFCSB_Adults15_17_Pos+"\" onblur=\"autosave('NH2_FBFCSB_Adults15_17_Pos');\"  tabindex=\"179\" oninput=\"NH2('NH2_FBFCSB_Adults15_17_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_FBFCSB_Postnatal_Pos\" id=\"NH2_FBFCSB_Postnatal_Pos\" value=\""+NH2_FBFCSB_Postnatal_Pos+"\" onblur=\"autosave('NH2_FBFCSB_Postnatal_Pos');\"  tabindex=\"180\" oninput=\"NH2('NH2_FBFCSB_Postnatal_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_FBFCSB_AllAdults_Neg\" id=\"NH2_FBFCSB_AllAdults_Neg\" value=\""+NH2_FBFCSB_AllAdults_Neg+"\" onblur=\"autosave('NH2_FBFCSB_AllAdults_Neg');\"  tabindex=\"181\" oninput=\"NH2('NH2_FBFCSB_AllAdults_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_FBFCSB_0_59M_Pos\" id=\"NH2_FBFCSB_0_59M_Pos\" value=\""+NH2_FBFCSB_0_59M_Pos+"\" onblur=\"autosave('NH2_FBFCSB_0_59M_Pos');\"  tabindex=\"182\" oninput=\"NH2('NH2_FBFCSB_0_59M_Pos');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_FBFCSB_5_15_Pos\" id=\"NH2_FBFCSB_5_15_Pos\" value=\""+NH2_FBFCSB_5_15_Pos+"\" onblur=\"autosave('NH2_FBFCSB_5_15_Pos');\"  tabindex=\"183\" oninput=\"NH2('NH2_FBFCSB_5_15_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_FBFCSB_0_59M_Neg\" id=\"NH2_FBFCSB_0_59M_Neg\" value=\""+NH2_FBFCSB_0_59M_Neg+"\" onblur=\"autosave('NH2_FBFCSB_0_59M_Neg');\"  tabindex=\"184\" oninput=\"NH2('NH2_FBFCSB_0_59M_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_FBFCSB_5_15_Neg\" id=\"NH2_FBFCSB_5_15_Neg\" value=\""+NH2_FBFCSB_5_15_Neg+"\" onblur=\"autosave('NH2_FBFCSB_5_15_Neg');\"  tabindex=\"185\" oninput=\"NH2('NH2_FBFCSB_5_15_Neg');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td class=\"totals\"><input type=\"text\" name=\"NH2_FBFCSB_Total\" id=\"NH2_FBFCSB_Total\" value=\""+NH2_FBFCSB_Total+"\"   onblur=\"autosave('NH2_FBFCSB_Total');\"  oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "</tr>"
        
                
         + "<tr>"
        + "<td colspan=\"3\">Liquid Nutrition supplements</td>"
        + "<td><input type=\"text\" name=\"NH2_LNS_Adults15_17_Pos\" id=\"NH2_LNS_Adults15_17_Pos\" value=\""+NH2_LNS_Adults15_17_Pos+"\" onblur=\"autosave('NH2_LNS_Adults15_17_Pos');\"  tabindex=\"186\" oninput=\"NH2('NH2_LNS_Adults15_17_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_LNS_Postnatal_Pos\" id=\"NH2_LNS_Postnatal_Pos\" value=\""+NH2_LNS_Postnatal_Pos+"\" onblur=\"autosave('NH2_LNS_Postnatal_Pos');\"  tabindex=\"187\" oninput=\"NH2('NH2_LNS_Postnatal_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_LNS_AllAdults_Neg\" id=\"NH2_LNS_AllAdults_Neg\" value=\""+NH2_LNS_AllAdults_Neg+"\" onblur=\"autosave('NH2_LNS_AllAdults_Neg');\"  tabindex=\"188\" oninput=\"NH2('NH2_LNS_AllAdults_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_LNS_0_59M_Pos\" id=\"NH2_LNS_0_59M_Pos\" value=\""+NH2_LNS_0_59M_Pos+"\" onblur=\"autosave('NH2_LNS_0_59M_Pos');\"  tabindex=\"189\" oninput=\"NH2('NH2_LNS_0_59M_Pos');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_LNS_5_15_Pos\" id=\"NH2_LNS_5_15_Pos\" value=\""+NH2_LNS_5_15_Pos+"\" onblur=\"autosave('NH2_LNS_5_15_Pos');\"  tabindex=\"190\" oninput=\"NH2('NH2_LNS_5_15_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_LNS_0_59M_Neg\" id=\"NH2_LNS_0_59M_Neg\" value=\""+NH2_LNS_0_59M_Neg+"\" onblur=\"autosave('NH2_LNS_0_59M_Neg');\"  tabindex=\"191\" oninput=\"NH2('NH2_LNS_0_59M_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_LNS_5_15_Neg\" id=\"NH2_LNS_5_15_Neg\" value=\""+NH2_LNS_5_15_Neg+"\" onblur=\"autosave('NH2_LNS_5_15_Neg');\"  tabindex=\"192\" oninput=\"NH2('NH2_LNS_5_15_Neg');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td class=\"totals\"><input type=\"text\" name=\"NH2_LNS_Total\" id=\"NH2_LNS_Total\" value=\""+NH2_LNS_Total+"\"   onblur=\"autosave('NH2_LNS_Total');\"  oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "</tr>"
        
                
         + "<tr>"
        + "<td colspan=\"3\">Micronutrients</td>"
        + "<td><input type=\"text\" name=\"NH2_Micronutrients_Adults15_17_Pos\" id=\"NH2_Micronutrients_Adults15_17_Pos\" value=\""+NH2_Micronutrients_Adults15_17_Pos+"\" onblur=\"autosave('NH2_Micronutrients_Adults15_17_Pos');\"  tabindex=\"193\" oninput=\"NH2('NH2_Micronutrients_Adults15_17_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Micronutrients_Postnatal_Pos\" id=\"NH2_Micronutrients_Postnatal_Pos\" value=\""+NH2_Micronutrients_Postnatal_Pos+"\" onblur=\"autosave('NH2_Micronutrients_Postnatal_Pos');\"  tabindex=\"194\" oninput=\"NH2('NH2_Micronutrients_Postnatal_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Micronutrients_AllAdults_Neg\" id=\"NH2_Micronutrients_AllAdults_Neg\" value=\""+NH2_Micronutrients_AllAdults_Neg+"\" onblur=\"autosave('NH2_Micronutrients_AllAdults_Neg');\"  tabindex=\"195\" oninput=\"NH2('NH2_Micronutrients_AllAdults_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Micronutrients_0_59M_Pos\" id=\"NH2_Micronutrients_0_59M_Pos\" value=\""+NH2_Micronutrients_0_59M_Pos+"\" onblur=\"autosave('NH2_Micronutrients_0_59M_Pos');\"  tabindex=\"196\" oninput=\"NH2('NH2_Micronutrients_0_59M_Pos');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Micronutrients_5_15_Pos\" id=\"NH2_Micronutrients_5_15_Pos\" value=\""+NH2_Micronutrients_5_15_Pos+"\" onblur=\"autosave('NH2_Micronutrients_5_15_Pos');\"  tabindex=\"197\" oninput=\"NH2('NH2_Micronutrients_5_15_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Micronutrients_0_59M_Neg\" id=\"NH2_Micronutrients_0_59M_Neg\" value=\""+NH2_Micronutrients_0_59M_Neg+"\" onblur=\"autosave('NH2_Micronutrients_0_59M_Neg');\"  tabindex=\"198\" oninput=\"NH2('NH2_Micronutrients_0_59M_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Micronutrients_5_15_Neg\" id=\"NH2_Micronutrients_5_15_Neg\" value=\""+NH2_Micronutrients_5_15_Neg+"\" onblur=\"autosave('NH2_Micronutrients_5_15_Neg');\"  tabindex=\"199\" oninput=\"NH2('NH2_Micronutrients_5_15_Neg');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td class=\"totals\"><input type=\"text\" name=\"NH2_Micronutrients_Total\" id=\"NH2_Micronutrients_Total\" value=\""+NH2_Micronutrients_Total+"\"   onblur=\"autosave('NH2_Micronutrients_Total');\"  oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "</tr>"
        
                
         + "<tr>"
        + "<td colspan=\"3\">Others</td>"
        + "<td><input type=\"text\" name=\"NH2_Others_Adults15_17_Pos\" id=\"NH2_Others_Adults15_17_Pos\" value=\""+NH2_Others_Adults15_17_Pos+"\" onblur=\"autosave('NH2_Others_Adults15_17_Pos');\"  tabindex=\"200\" oninput=\"NH2('NH2_Others_Adults15_17_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Others_Postnatal_Pos\" id=\"NH2_Others_Postnatal_Pos\" value=\""+NH2_Others_Postnatal_Pos+"\" onblur=\"autosave('NH2_Others_Postnatal_Pos');\"  tabindex=\"201\" oninput=\"NH2('NH2_Others_Postnatal_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Others_AllAdults_Neg\" id=\"NH2_Others_AllAdults_Neg\" value=\""+NH2_Others_AllAdults_Neg+"\" onblur=\"autosave('NH2_Others_AllAdults_Neg');\"  tabindex=\"202\" oninput=\"NH2('NH2_Others_AllAdults_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Others_0_59M_Pos\" id=\"NH2_Others_0_59M_Pos\" value=\""+NH2_Others_0_59M_Pos+"\" onblur=\"autosave('NH2_Others_0_59M_Pos');\"  tabindex=\"203\" oninput=\"NH2('NH2_Others_0_59M_Pos');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Others_5_15_Pos\" id=\"NH2_Others_5_15_Pos\" value=\""+NH2_Others_5_15_Pos+"\" onblur=\"autosave('NH2_Others_5_15_Pos');\"  tabindex=\"204\" oninput=\"NH2('NH2_Others_5_15_Pos'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Others_0_59M_Neg\" id=\"NH2_Others_0_59M_Neg\" value=\""+NH2_Others_0_59M_Neg+"\" onblur=\"autosave('NH2_Others_0_59M_Neg');\"  tabindex=\"205\" oninput=\"NH2('NH2_Others_0_59M_Neg'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Others_5_15_Neg\" id=\"NH2_Others_5_15_Neg\" value=\""+NH2_Others_5_15_Neg+"\" onblur=\"autosave('NH2_Others_5_15_Neg');\"  tabindex=\"206\" oninput=\"NH2('NH2_Others_5_15_Neg');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td class=\"totals\"><input type=\"text\" name=\"NH2_Others_Total\" id=\"NH2_Others_Total\" value=\""+NH2_Others_Total+"\"   onblur=\"autosave('NH2_Others_Total');\"  oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "</tr>"
        
         
        + "<tr class=\"totals\">"
        + "<td colspan=\"3\">Totals</td>"
        + "<td><input type=\"text\" name=\"NH2_Total_Adults15_17_Pos\" id=\"NH2_Total_Adults15_17_Pos\" value=\""+NH2_Total_Adults15_17_Pos+"\"   onblur=\"autosave('NH2_Total_Adults15_17_Pos');\"  oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Total_Postnatal_Pos\" id=\"NH2_Total_Postnatal_Pos\" value=\""+NH2_Total_Postnatal_Pos+"\"   onblur=\"autosave('NH2_Total_Postnatal_Pos');\"  oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Total_AllAdults_Neg\" id=\"NH2_Total_AllAdults_Neg\" value=\""+NH2_Total_AllAdults_Neg+"\"   onblur=\"autosave('NH2_Total_AllAdults_Neg');\"  oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Total_0_59M_Pos\" id=\"NH2_Total_0_59M_Pos\" value=\""+NH2_Total_0_59M_Pos+"\"   onblur=\"autosave('NH2_Total_0_59M_Pos');\"  oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Total_5_15_Pos\" id=\"NH2_Total_5_15_Pos\" value=\""+NH2_Total_5_15_Pos+"\"   onblur=\"autosave('NH2_Total_5_15_Pos');\"  oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Total_0_59M_Neg\" id=\"NH2_Total_0_59M_Neg\" value=\""+NH2_Total_0_59M_Neg+"\"   onblur=\"autosave('NH2_Total_0_59M_Neg');\"  oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Total_5_15_Neg\" id=\"NH2_Total_5_15_Neg\" value=\""+NH2_Total_5_15_Neg+"\"   onblur=\"autosave('NH2_Total_5_15_Neg');\"  oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"NH2_Grand_Total\" id=\"NH2_Grand_Total\" value=\""+NH2_Grand_Total+"\"   onblur=\"autosave('NH2_Grand_Total');\"  oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "</tr>"
                
        + "</table>"
        + "<br><br><br>";   
//ended tab at 206

              
//ENDS AT TAB 269

LTF =  "<p class=\"lower-section\"> Additional information (Obtained from client appointment book)</p><br>"
        + "<table  class=\"lower-section\">"
        + "<tr>"
        + "<td></td>"
        + "<td>Adult PLHIV/15-17 yrs</td>"
        + "<td>Pregnant</td>"
        + "<td>Post-natal</td>"
        + "<td>0-59 months</td>"
        + "<td>5-15 yrs</td>"
        + "</tr>"
        
        + "<tr>"
        + "<td>Loss to followup(LTF)</td>"
        + "<td><input type=\"text\" name=\"LTF_Adult_15_17\" id=\"LTF_Adult_15_17\" value=\""+LTF_Adult_15_17+"\" onblur=\"autosave('LTF_Adult_15_17');\"  tabindex=\"270\" oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"LTF_Pregnant\" id=\"LTF_Pregnant\" value=\""+LTF_Pregnant+"\" onblur=\"autosave('LTF_Pregnant');\"  tabindex=\"271\" oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"LTF_Postnatal\" id=\"LTF_Postnatal\" value=\""+LTF_Postnatal+"\" onblur=\"autosave('LTF_Postnatal');\"  tabindex=\"272\" oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"LTF_0_59M\" id=\"LTF_0_59M\" value=\""+LTF_0_59M+"\" onblur=\"autosave('LTF_0_59M');\"  tabindex=\"273\" oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"LTF_5_15\" id=\"LTF_5_15\" value=\""+LTF_5_15+"\" onblur=\"autosave('LTF_5_15');\"  tabindex=\"274\" oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "</tr>";

RM =    "<tr>"
        + "<td>Loss to followup(LTF)</td>"
        + "<td><input type=\"text\" name=\"RM_Adult_15_17\" id=\"RM_Adult_15_17\" value=\""+RM_Adult_15_17+"\" onblur=\"autosave('RM_Adult_15_17');\"  tabindex=\"275\" oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"RM_Pregnant\" id=\"RM_Pregnant\" value=\""+RM_Pregnant+"\" onblur=\"autosave('RM_Pregnant');\"  tabindex=\"276\" oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"RM_Postnatal\" id=\"RM_Postnatal\" value=\""+RM_Postnatal+"\" onblur=\"autosave('RM_Postnatal');\"  tabindex=\"277\" oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"RM_0_59M\" id=\"RM_0_59M\" value=\""+RM_0_59M+"\" onblur=\"autosave('RM_0_59M');\"  tabindex=\"278\" oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"RM_5_15\" id=\"RM_5_15\" value=\""+RM_5_15+"\" onblur=\"autosave('RM_5_15');\"  tabindex=\"279\" oninput=\" \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "</tr>"
        + "</table>";


        String validationText="",buttonColor="", titleText="";
        if(isLocked==0){validationText="Run Validation";buttonColor="blue"; titleText="";}
        else if(isLocked==1){validationText="<b>Data Locked !</b>";buttonColor="red";titleText="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b style=\"color:red;\">Sorry: Data editing has been locked for this health facility, year and month.</b>";}
        else{}
        
Footer="<br><br>"
        + "<p class=\"lower-section\">"
        + "<b>Report prepared by: </b>"
        + "<input type=\"text\" name=\"reporting_officer\" id=\"reporting_officer\" value=\""+reporting_officer+"\" tabindex=\"280\" class=\"prepared_by\" placeholder=\"Name of reporting officer\" "+lock+"   autocomplete=\"off\" maxLength=\"50\">"
        + "<input type=\"text\" name=\"designation\" id=\"designation\" value=\""+designation+"\" tabindex=\"280\" class=\"prepared_by\" placeholder=\"Designation\"  autocomplete=\"off\" "+lock+"   maxLength=\"50\">"
        + "<input type=\"text\" name=\"reporting_date\" id=\"reporting_date\" value=\""+reporting_date+"\"  readonly  tabindex=\"280\" class=\"prepared_by\" placeholder=\"Date\"  autocomplete=\"off\" "+lock+"   maxLength=\"50\">"
        + ""
        + "</p>"
        + "<div class=\"form-actions\">"
        + "<button type=\"submit\" style=\"margin-left: 30%;\" class=\"btn "+buttonColor+"\" "+lock+" >"+validationText+"</button>"
        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </div>"
        + "</>"
        + "</form>";

//        output =Header+""+CS+""+NH1+""+ART+""+Anaemia+""+IFP+""+NI+""+NH2+""+TO+""+RT+""+LTF+""+RM+""+Footer;
        output =Header+""+CS+""+NH1+""+ART+""+Anaemia+""+NI+""+NH2+""+TO+""+RT+""+LTF+""+RM+""+Footer;
        
            out.println(output);
        } finally {
            out.close();
        }
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
        Logger.getLogger(loadmoh733B.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadmoh733B.class.getName()).log(Level.SEVERE, null, ex);
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
