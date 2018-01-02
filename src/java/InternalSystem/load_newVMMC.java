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
 * Used as from Oct 2017, 2018 financial year.
 */
public class load_newVMMC extends HttpServlet {
HttpSession session;
String tableid,SubPartnerID,Annee,Mois,site_type,v1_60d,v1_4y,v1_9y,v1_14y,v1_19y,v1_24y,v1_29y,v1_34y,v1_39y,v1_49y,v1_50y,v1_total,v2_dc_m_60d,v2_dc_m_4y,v2_dc_m_9y,v2_dc_m_14y,v2_dc_m_19y,v2_dc_m_24y,v2_dc_m_29y,v2_dc_m_34y,v2_dc_m_39y,v2_dc_m_49y,v2_dc_m_50y,v2_dc_m_total,v2_dc_s_60d,v2_dc_s_4y,v2_dc_s_9y,v2_dc_s_14y,v2_dc_s_19y,v2_dc_s_24y,v2_dc_s_29y,v2_dc_s_34y,v2_dc_s_39y,v2_dc_s_49y,v2_dc_s_50y,v2_dc_s_total,v2_dc_t_60d,v2_dc_t_4y,v2_dc_t_9y,v2_dc_t_14y,v2_dc_t_19y,v2_dc_t_24y,v2_dc_t_29y,v2_dc_t_34y,v2_dc_t_39y,v2_dc_t_49y,v2_dc_t_50y,v2_dc_t_total,v2_pc_m_60d,v2_pc_m_4y,v2_pc_m_9y,v2_pc_m_14y,v2_pc_m_19y,v2_pc_m_24y,v2_pc_m_29y,v2_pc_m_34y,v2_pc_m_39y,v2_pc_m_49y,v2_pc_m_50y,v2_pc_m_total,v2_pc_s_60d,v2_pc_s_4y,v2_pc_s_9y,v2_pc_s_14y,v2_pc_s_19y,v2_pc_s_24y,v2_pc_s_29y,v2_pc_s_34y,v2_pc_s_39y,v2_pc_s_49y,v2_pc_s_50y,v2_pc_s_total,v2_pc_t_60d,v2_pc_t_4y,v2_pc_t_9y,v2_pc_t_14y,v2_pc_t_19y,v2_pc_t_24y,v2_pc_t_29y,v2_pc_t_34y,v2_pc_t_39y,v2_pc_t_49y,v2_pc_t_50y,v2_pc_t_total,v2_60d_total,v2_4y_total,v2_9y_total,v2_14y_total,v2_19y_total,v2_24y_total,v2_29y_total,v2_34y_total,v2_39y_total,v2_49y_total,v2_50y_total,v2_total,v3_tp_60d,v3_tp_4y,v3_tp_9y,v3_tp_14y,v3_tp_19y,v3_tp_24y,v3_tp_29y,v3_tp_34y,v3_tp_39y,v3_tp_49y,v3_tp_50y,v3_tp_total,v3_srp_60d,v3_srp_4y,v3_srp_9y,v3_srp_14y,v3_srp_19y,v3_srp_24y,v3_srp_29y,v3_srp_34y,v3_srp_39y,v3_srp_49y,v3_srp_50y,v3_srp_total,v3_tn_60d,v3_tn_4y,v3_tn_9y,v3_tn_14y,v3_tn_19y,v3_tn_24y,v3_tn_29y,v3_tn_34y,v3_tn_39y,v3_tn_49y,v3_tn_50y,v3_tn_total,v3_nt_60d,v3_nt_4y,v3_nt_9y,v3_nt_14y,v3_nt_19y,v3_nt_24y,v3_nt_29y,v3_nt_34y,v3_nt_39y,v3_nt_49y,v3_nt_50y,v3_nt_total,v3_us_60d,v3_us_4y,v3_us_9y,v3_us_14y,v3_us_19y,v3_us_24y,v3_us_29y,v3_us_34y,v3_us_39y,v3_us_49y,v3_us_50y,v3_us_total,v3_srn_60d,v3_srn_4y,v3_srn_9y,v3_srn_14y,v3_srn_19y,v3_srn_24y,v3_srn_29y,v3_srn_34y,v3_srn_39y,v3_srn_49y,v3_srn_50y,v3_srn_total,v3_t_60d,v3_t_4y,v3_t_9y,v3_t_14y,v3_t_19y,v3_t_24y,v3_t_29y,v3_t_34y,v3_t_39y,v3_t_49y,v3_t_50y,v3_t_total,v4_s_vmmc_60d,v4_s_vmmc_4y,v4_s_vmmc_9y,v4_s_vmmc_14y,v4_s_vmmc_19y,v4_s_vmmc_24y,v4_s_vmmc_29y,v4_s_vmmc_34y,v4_s_vmmc_39y,v4_s_vmmc_49y,v4_s_vmmc_50y,v4_s_vmmc_total,v4_db_vmmc_60d,v4_db_vmmc_4y,v4_db_vmmc_9y,v4_db_vmmc_14y,v4_db_vmmc_19y,v4_db_vmmc_24y,v4_db_vmmc_29y,v4_db_vmmc_34y,v4_db_vmmc_39y,v4_db_vmmc_49y,v4_db_vmmc_50y,v4_db_vmmc_total,v4_t_vmmc_60d,v4_t_vmmc_4y,v4_t_vmmc_9y,v4_t_vmmc_14y,v4_t_vmmc_19y,v4_t_vmmc_24y,v4_t_vmmc_29y,v4_t_vmmc_34y,v4_t_vmmc_39y,v4_t_vmmc_49y,v4_t_vmmc_50y,v4_t_vmmc_total,v5_followup_60d,v5_followup_4y,v5_followup_9y,v5_followup_14y,v5_followup_19y,v5_followup_24y,v5_followup_29y,v5_followup_34y,v5_followup_39y,v5_followup_49y,v5_followup_50y,v5_followup_total,v6_nofollowup_60d,v6_nofollowup_4y,v6_nofollowup_9y,v6_nofollowup_14y,v6_nofollowup_19y,v6_nofollowup_24y,v6_nofollowup_29y,v6_nofollowup_34y,v6_nofollowup_39y,v6_nofollowup_49y,v6_nofollowup_50y,v6_nofollowup_total,isValidated,isLocked,updatedBy,updatedOn,yearmonth,timestamp;
String output,lock,enterdby,validity,Header;
String v1,v2,v3,v4,v5,v6;
String year,month;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
           session = request.getSession();
           
        v1_60d=v1_4y=v1_9y=v1_14y=v1_19y=v1_24y=v1_29y=v1_34y=v1_39y=v1_49y=v1_50y=v1_total=v2_dc_m_60d=v2_dc_m_4y=v2_dc_m_9y=v2_dc_m_14y=v2_dc_m_19y=v2_dc_m_24y=v2_dc_m_29y=v2_dc_m_34y=v2_dc_m_39y=v2_dc_m_49y=v2_dc_m_50y=v2_dc_m_total=v2_dc_s_60d=v2_dc_s_4y=v2_dc_s_9y=v2_dc_s_14y=v2_dc_s_19y=v2_dc_s_24y=v2_dc_s_29y=v2_dc_s_34y=v2_dc_s_39y=v2_dc_s_49y=v2_dc_s_50y=v2_dc_s_total=v2_dc_t_60d=v2_dc_t_4y=v2_dc_t_9y=v2_dc_t_14y=v2_dc_t_19y=v2_dc_t_24y=v2_dc_t_29y=v2_dc_t_34y=v2_dc_t_39y=v2_dc_t_49y=v2_dc_t_50y=v2_dc_t_total=v2_pc_m_60d=v2_pc_m_4y=v2_pc_m_9y=v2_pc_m_14y=v2_pc_m_19y=v2_pc_m_24y=v2_pc_m_29y=v2_pc_m_34y=v2_pc_m_39y=v2_pc_m_49y=v2_pc_m_50y=v2_pc_m_total=v2_pc_s_60d=v2_pc_s_4y=v2_pc_s_9y=v2_pc_s_14y=v2_pc_s_19y=v2_pc_s_24y=v2_pc_s_29y=v2_pc_s_34y=v2_pc_s_39y=v2_pc_s_49y=v2_pc_s_50y=v2_pc_s_total=v2_pc_t_60d=v2_pc_t_4y=v2_pc_t_9y=v2_pc_t_14y=v2_pc_t_19y=v2_pc_t_24y=v2_pc_t_29y=v2_pc_t_34y=v2_pc_t_39y=v2_pc_t_49y=v2_pc_t_50y=v2_pc_t_total=v2_60d_total=v2_4y_total=v2_9y_total=v2_14y_total=v2_19y_total=v2_24y_total=v2_29y_total=v2_34y_total=v2_39y_total=v2_49y_total=v2_50y_total=v2_total=v3_tp_60d=v3_tp_4y=v3_tp_9y=v3_tp_14y=v3_tp_19y=v3_tp_24y=v3_tp_29y=v3_tp_34y=v3_tp_39y=v3_tp_49y=v3_tp_50y=v3_tp_total=v3_srp_60d=v3_srp_4y=v3_srp_9y=v3_srp_14y=v3_srp_19y=v3_srp_24y=v3_srp_29y=v3_srp_34y=v3_srp_39y=v3_srp_49y=v3_srp_50y=v3_srp_total=v3_tn_60d=v3_tn_4y=v3_tn_9y=v3_tn_14y=v3_tn_19y=v3_tn_24y=v3_tn_29y=v3_tn_34y=v3_tn_39y=v3_tn_49y=v3_tn_50y=v3_tn_total=v3_nt_60d=v3_nt_4y=v3_nt_9y=v3_nt_14y=v3_nt_19y=v3_nt_24y=v3_nt_29y=v3_nt_34y=v3_nt_39y=v3_nt_49y=v3_nt_50y=v3_nt_total=v3_us_60d=v3_us_4y=v3_us_9y=v3_us_14y=v3_us_19y=v3_us_24y=v3_us_29y=v3_us_34y=v3_us_39y=v3_us_49y=v3_us_50y=v3_us_total=v3_srn_60d=v3_srn_4y=v3_srn_9y=v3_srn_14y=v3_srn_19y=v3_srn_24y=v3_srn_29y=v3_srn_34y=v3_srn_39y=v3_srn_49y=v3_srn_50y=v3_srn_total=v3_t_60d=v3_t_4y=v3_t_9y=v3_t_14y=v3_t_19y=v3_t_24y=v3_t_29y=v3_t_34y=v3_t_39y=v3_t_49y=v3_t_50y=v3_t_total=v4_s_vmmc_60d=v4_s_vmmc_4y=v4_s_vmmc_9y=v4_s_vmmc_14y=v4_s_vmmc_19y=v4_s_vmmc_24y=v4_s_vmmc_29y=v4_s_vmmc_34y=v4_s_vmmc_39y=v4_s_vmmc_49y=v4_s_vmmc_50y=v4_s_vmmc_total=v4_db_vmmc_60d=v4_db_vmmc_4y=v4_db_vmmc_9y=v4_db_vmmc_14y=v4_db_vmmc_19y=v4_db_vmmc_24y=v4_db_vmmc_29y=v4_db_vmmc_34y=v4_db_vmmc_39y=v4_db_vmmc_49y=v4_db_vmmc_50y=v4_db_vmmc_total=v4_t_vmmc_60d=v4_t_vmmc_4y=v4_t_vmmc_9y=v4_t_vmmc_14y=v4_t_vmmc_19y=v4_t_vmmc_24y=v4_t_vmmc_29y=v4_t_vmmc_34y=v4_t_vmmc_39y=v4_t_vmmc_49y=v4_t_vmmc_50y=v4_t_vmmc_total=v5_followup_60d=v5_followup_4y=v5_followup_9y=v5_followup_14y=v5_followup_19y=v5_followup_24y=v5_followup_29y=v5_followup_34y=v5_followup_39y=v5_followup_49y=v5_followup_50y=v5_followup_total=v6_nofollowup_60d=v6_nofollowup_4y=v6_nofollowup_9y=v6_nofollowup_14y=v6_nofollowup_19y=v6_nofollowup_24y=v6_nofollowup_29y=v6_nofollowup_34y=v6_nofollowup_39y=v6_nofollowup_49y=v6_nofollowup_50y=v6_nofollowup_total=isValidated=isLocked=updatedBy=updatedOn=yearmonth="";
        
        tableid="2018_10_331";
        lock="";
            String facil = "";

            validity="<b><font color='#4b8df8'>New Entry</font></b>";

            if (session.getAttribute("year") != null) {
                year = session.getAttribute("year").toString();
            }
            if (session.getAttribute("monthid") != null) {
                month = session.getAttribute("monthid").toString();
            }

            if (session.getAttribute("facilityid") != null) {
                facil = session.getAttribute("facilityid").toString();
            }
            tableid = year + "_" + month + "_" + facil;
        
        int kmmpdone=0;
int kmmpundone=0;
int kmmpvalid=0;
int facilssupporting=0;
String distid="";

if(session.getAttribute("subcountyid")!=null){
distid=session.getAttribute("subcountyid").toString();
}


String kmmpcounter="SELECT 1 FROM vmmc_new join subpartnera on vmmc_new.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and (v1_total is not null ||v1_total!='')  ";
 conn.rs1 = conn.st1.executeQuery(kmmpcounter);
 while(conn.rs1.next()){
 kmmpdone++;
  }
 
  String kmmpvalidatedcounter1="SELECT 1 FROM vmmc_new join subpartnera on vmmc_new.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and isValidated='0' ";
 conn.rs1 = conn.st1.executeQuery(kmmpvalidatedcounter1);
 while(conn.rs1.next()){
 kmmpvalid++;
  }
 
            System.out.println(kmmpcounter);
 
 String kmmpcounter1="SELECT 1 FROM vmmc_new join subpartnera on vmmc_new.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and  isValidated='0' ";
 conn.rs1 = conn.st1.executeQuery(kmmpcounter1);
 while(conn.rs1.next()){
 kmmpundone++;
  }
 
 
 
 
 String countpmctfacility="Select * from subpartnera where VMMC ='1' and  DistrictID='"+distid+"'";
// String countfacility="Select * from subpartnera where FP='1' || PMTCT ='1' || Maternity='1' || HTC='1' ";
 conn.rs1 = conn.st1.executeQuery(countpmctfacility);
 while(conn.rs1.next()){
 facilssupporting++;
 }
 
String validated="&nbsp &nbsp Validated Form(s): <b>"+kmmpvalid+" </b>";
 String unvalidated="&nbsp &nbsp Unvalidated Form (s) <font color='black'><b>"+kmmpundone+"</b></font>";
 
 
  String unvalidatedLink="";int counter=0;
     if(kmmpundone>0){
     String getUnvalidated="SELECT vmmc_new.SubPartnerID,subpartnera.SubPartnerNom FROM vmmc_new JOIN subpartnera ON vmmc_new.SubPartnerID=subpartnera.SubPartnerID WHERE subpartnera.DistrictID='"+distid+"' AND vmmc_new.Mois='"+month+"' AND vmmc_new.Annee='"+year+"' AND vmmc_new.isValidated='0'";
     conn.rs=conn.st.executeQuery(getUnvalidated);
     while(conn.rs.next()){
         counter++;
//     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=Form731.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=loadVmmc.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
                    }
    }
     
   if(counter>0){
    unvalidated="<button class='btn btn-primary btn-lg' data-target='#unvalidatedModal' style='width:auto; height:auto;' data-toggle='modal' type='button'> Unvalidated Form (s) <span class='badge badge-important'><b>"+kmmpundone+"</b></span></button>";
 
   }  
 
 String label="Record counter <font color='white'><b>"+kmmpdone+"<b></font>  out of <b>"+facilssupporting+"</b>"+validated+unvalidated;
 
 
 
            String check_data="SELECT * FROM vmmc_new WHERE tableid=?";
            conn.pst=conn.conn.prepareStatement(check_data);
            conn.pst.setString(1, tableid);
            conn.rs=conn.pst.executeQuery();
            if(conn.rs.next()){
                     v1_60d=conn.rs.getString("v1_60d");
                     v1_4y=conn.rs.getString("v1_4y");
                     v1_9y=conn.rs.getString("v1_9y");
                     v1_14y=conn.rs.getString("v1_14y");
                     v1_19y=conn.rs.getString("v1_19y");
                     v1_24y=conn.rs.getString("v1_24y");
                     v1_29y=conn.rs.getString("v1_29y");
                     v1_34y=conn.rs.getString("v1_34y");
                     v1_39y=conn.rs.getString("v1_39y");
                     v1_49y=conn.rs.getString("v1_49y");
                     v1_50y=conn.rs.getString("v1_50y");
                     v1_total=conn.rs.getString("v1_total");
                     v2_dc_m_60d=conn.rs.getString("v2_dc_m_60d");
                     v2_dc_m_4y=conn.rs.getString("v2_dc_m_4y");
                     v2_dc_m_9y=conn.rs.getString("v2_dc_m_9y");
                     v2_dc_m_14y=conn.rs.getString("v2_dc_m_14y");
                     v2_dc_m_19y=conn.rs.getString("v2_dc_m_19y");
                     v2_dc_m_24y=conn.rs.getString("v2_dc_m_24y");
                     v2_dc_m_29y=conn.rs.getString("v2_dc_m_29y");
                     v2_dc_m_34y=conn.rs.getString("v2_dc_m_34y");
                     v2_dc_m_39y=conn.rs.getString("v2_dc_m_39y");
                     v2_dc_m_49y=conn.rs.getString("v2_dc_m_49y");
                     v2_dc_m_50y=conn.rs.getString("v2_dc_m_50y");
                     v2_dc_m_total=conn.rs.getString("v2_dc_m_total");
                     v2_dc_s_60d=conn.rs.getString("v2_dc_s_60d");
                     v2_dc_s_4y=conn.rs.getString("v2_dc_s_4y");
                     v2_dc_s_9y=conn.rs.getString("v2_dc_s_9y");
                     v2_dc_s_14y=conn.rs.getString("v2_dc_s_14y");
                     v2_dc_s_19y=conn.rs.getString("v2_dc_s_19y");
                     v2_dc_s_24y=conn.rs.getString("v2_dc_s_24y");
                     v2_dc_s_29y=conn.rs.getString("v2_dc_s_29y");
                     v2_dc_s_34y=conn.rs.getString("v2_dc_s_34y");
                     v2_dc_s_39y=conn.rs.getString("v2_dc_s_39y");
                     v2_dc_s_49y=conn.rs.getString("v2_dc_s_49y");
                     v2_dc_s_50y=conn.rs.getString("v2_dc_s_50y");
                     v2_dc_s_total=conn.rs.getString("v2_dc_s_total");
                     v2_dc_t_60d=conn.rs.getString("v2_dc_t_60d");
                     v2_dc_t_4y=conn.rs.getString("v2_dc_t_4y");
                     v2_dc_t_9y=conn.rs.getString("v2_dc_t_9y");
                     v2_dc_t_14y=conn.rs.getString("v2_dc_t_14y");
                     v2_dc_t_19y=conn.rs.getString("v2_dc_t_19y");
                     v2_dc_t_24y=conn.rs.getString("v2_dc_t_24y");
                     v2_dc_t_29y=conn.rs.getString("v2_dc_t_29y");
                     v2_dc_t_34y=conn.rs.getString("v2_dc_t_34y");
                     v2_dc_t_39y=conn.rs.getString("v2_dc_t_39y");
                     v2_dc_t_49y=conn.rs.getString("v2_dc_t_49y");
                     v2_dc_t_50y=conn.rs.getString("v2_dc_t_50y");
                     v2_dc_t_total=conn.rs.getString("v2_dc_t_total");
                     v2_pc_m_60d=conn.rs.getString("v2_pc_m_60d");
                     v2_pc_m_4y=conn.rs.getString("v2_pc_m_4y");
                     v2_pc_m_9y=conn.rs.getString("v2_pc_m_9y");
                     v2_pc_m_14y=conn.rs.getString("v2_pc_m_14y");
                     v2_pc_m_19y=conn.rs.getString("v2_pc_m_19y");
                     v2_pc_m_24y=conn.rs.getString("v2_pc_m_24y");
                     v2_pc_m_29y=conn.rs.getString("v2_pc_m_29y");
                     v2_pc_m_34y=conn.rs.getString("v2_pc_m_34y");
                     v2_pc_m_39y=conn.rs.getString("v2_pc_m_39y");
                     v2_pc_m_49y=conn.rs.getString("v2_pc_m_49y");
                     v2_pc_m_50y=conn.rs.getString("v2_pc_m_50y");
                     v2_pc_m_total=conn.rs.getString("v2_pc_m_total");
                     v2_pc_s_60d=conn.rs.getString("v2_pc_s_60d");
                     v2_pc_s_4y=conn.rs.getString("v2_pc_s_4y");
                     v2_pc_s_9y=conn.rs.getString("v2_pc_s_9y");
                     v2_pc_s_14y=conn.rs.getString("v2_pc_s_14y");
                     v2_pc_s_19y=conn.rs.getString("v2_pc_s_19y");
                     v2_pc_s_24y=conn.rs.getString("v2_pc_s_24y");
                     v2_pc_s_29y=conn.rs.getString("v2_pc_s_29y");
                     v2_pc_s_34y=conn.rs.getString("v2_pc_s_34y");
                     v2_pc_s_39y=conn.rs.getString("v2_pc_s_39y");
                     v2_pc_s_49y=conn.rs.getString("v2_pc_s_49y");
                     v2_pc_s_50y=conn.rs.getString("v2_pc_s_50y");
                     v2_pc_s_total=conn.rs.getString("v2_pc_s_total");
                     v2_pc_t_60d=conn.rs.getString("v2_pc_t_60d");
                     v2_pc_t_4y=conn.rs.getString("v2_pc_t_4y");
                     v2_pc_t_9y=conn.rs.getString("v2_pc_t_9y");
                     v2_pc_t_14y=conn.rs.getString("v2_pc_t_14y");
                     v2_pc_t_19y=conn.rs.getString("v2_pc_t_19y");
                     v2_pc_t_24y=conn.rs.getString("v2_pc_t_24y");
                     v2_pc_t_29y=conn.rs.getString("v2_pc_t_29y");
                     v2_pc_t_34y=conn.rs.getString("v2_pc_t_34y");
                     v2_pc_t_39y=conn.rs.getString("v2_pc_t_39y");
                     v2_pc_t_49y=conn.rs.getString("v2_pc_t_49y");
                     v2_pc_t_50y=conn.rs.getString("v2_pc_t_50y");
                     v2_pc_t_total=conn.rs.getString("v2_pc_t_total");
                     v2_60d_total=conn.rs.getString("v2_60d_total");
                     v2_4y_total=conn.rs.getString("v2_4y_total");
                     v2_9y_total=conn.rs.getString("v2_9y_total");
                     v2_14y_total=conn.rs.getString("v2_14y_total");
                     v2_19y_total=conn.rs.getString("v2_19y_total");
                     v2_24y_total=conn.rs.getString("v2_24y_total");
                     v2_29y_total=conn.rs.getString("v2_29y_total");
                     v2_34y_total=conn.rs.getString("v2_34y_total");
                     v2_39y_total=conn.rs.getString("v2_39y_total");
                     v2_49y_total=conn.rs.getString("v2_49y_total");
                     v2_50y_total=conn.rs.getString("v2_50y_total");
                     v2_total=conn.rs.getString("v2_total");
                     v3_tp_60d=conn.rs.getString("v3_tp_60d");
                     v3_tp_4y=conn.rs.getString("v3_tp_4y");
                     v3_tp_9y=conn.rs.getString("v3_tp_9y");
                     v3_tp_14y=conn.rs.getString("v3_tp_14y");
                     v3_tp_19y=conn.rs.getString("v3_tp_19y");
                     v3_tp_24y=conn.rs.getString("v3_tp_24y");
                     v3_tp_29y=conn.rs.getString("v3_tp_29y");
                     v3_tp_34y=conn.rs.getString("v3_tp_34y");
                     v3_tp_39y=conn.rs.getString("v3_tp_39y");
                     v3_tp_49y=conn.rs.getString("v3_tp_49y");
                     v3_tp_50y=conn.rs.getString("v3_tp_50y");
                     v3_tp_total=conn.rs.getString("v3_tp_total");
                     v3_srp_60d=conn.rs.getString("v3_srp_60d");
                     v3_srp_4y=conn.rs.getString("v3_srp_4y");
                     v3_srp_9y=conn.rs.getString("v3_srp_9y");
                     v3_srp_14y=conn.rs.getString("v3_srp_14y");
                     v3_srp_19y=conn.rs.getString("v3_srp_19y");
                     v3_srp_24y=conn.rs.getString("v3_srp_24y");
                     v3_srp_29y=conn.rs.getString("v3_srp_29y");
                     v3_srp_34y=conn.rs.getString("v3_srp_34y");
                     v3_srp_39y=conn.rs.getString("v3_srp_39y");
                     v3_srp_49y=conn.rs.getString("v3_srp_49y");
                     v3_srp_50y=conn.rs.getString("v3_srp_50y");
                     v3_srp_total=conn.rs.getString("v3_srp_total");
                     v3_tn_60d=conn.rs.getString("v3_tn_60d");
                     v3_tn_4y=conn.rs.getString("v3_tn_4y");
                     v3_tn_9y=conn.rs.getString("v3_tn_9y");
                     v3_tn_14y=conn.rs.getString("v3_tn_14y");
                     v3_tn_19y=conn.rs.getString("v3_tn_19y");
                     v3_tn_24y=conn.rs.getString("v3_tn_24y");
                     v3_tn_29y=conn.rs.getString("v3_tn_29y");
                     v3_tn_34y=conn.rs.getString("v3_tn_34y");
                     v3_tn_39y=conn.rs.getString("v3_tn_39y");
                     v3_tn_49y=conn.rs.getString("v3_tn_49y");
                     v3_tn_50y=conn.rs.getString("v3_tn_50y");
                     v3_tn_total=conn.rs.getString("v3_tn_total");
                     v3_nt_60d=conn.rs.getString("v3_nt_60d");
                     v3_nt_4y=conn.rs.getString("v3_nt_4y");
                     v3_nt_9y=conn.rs.getString("v3_nt_9y");
                     v3_nt_14y=conn.rs.getString("v3_nt_14y");
                     v3_nt_19y=conn.rs.getString("v3_nt_19y");
                     v3_nt_24y=conn.rs.getString("v3_nt_24y");
                     v3_nt_29y=conn.rs.getString("v3_nt_29y");
                     v3_nt_34y=conn.rs.getString("v3_nt_34y");
                     v3_nt_39y=conn.rs.getString("v3_nt_39y");
                     v3_nt_49y=conn.rs.getString("v3_nt_49y");
                     v3_nt_50y=conn.rs.getString("v3_nt_50y");
                     v3_nt_total=conn.rs.getString("v3_nt_total");
                     v3_us_60d=conn.rs.getString("v3_us_60d");
                     v3_us_4y=conn.rs.getString("v3_us_4y");
                     v3_us_9y=conn.rs.getString("v3_us_9y");
                     v3_us_14y=conn.rs.getString("v3_us_14y");
                     v3_us_19y=conn.rs.getString("v3_us_19y");
                     v3_us_24y=conn.rs.getString("v3_us_24y");
                     v3_us_29y=conn.rs.getString("v3_us_29y");
                     v3_us_34y=conn.rs.getString("v3_us_34y");
                     v3_us_39y=conn.rs.getString("v3_us_39y");
                     v3_us_49y=conn.rs.getString("v3_us_49y");
                     v3_us_50y=conn.rs.getString("v3_us_50y");
                     v3_us_total=conn.rs.getString("v3_us_total");
                     v3_srn_60d=conn.rs.getString("v3_srn_60d");
                     v3_srn_4y=conn.rs.getString("v3_srn_4y");
                     v3_srn_9y=conn.rs.getString("v3_srn_9y");
                     v3_srn_14y=conn.rs.getString("v3_srn_14y");
                     v3_srn_19y=conn.rs.getString("v3_srn_19y");
                     v3_srn_24y=conn.rs.getString("v3_srn_24y");
                     v3_srn_29y=conn.rs.getString("v3_srn_29y");
                     v3_srn_34y=conn.rs.getString("v3_srn_34y");
                     v3_srn_39y=conn.rs.getString("v3_srn_39y");
                     v3_srn_49y=conn.rs.getString("v3_srn_49y");
                     v3_srn_50y=conn.rs.getString("v3_srn_50y");
                     v3_srn_total=conn.rs.getString("v3_srn_total");
                     v3_t_60d=conn.rs.getString("v3_t_60d");
                     v3_t_4y=conn.rs.getString("v3_t_4y");
                     v3_t_9y=conn.rs.getString("v3_t_9y");
                     v3_t_14y=conn.rs.getString("v3_t_14y");
                     v3_t_19y=conn.rs.getString("v3_t_19y");
                     v3_t_24y=conn.rs.getString("v3_t_24y");
                     v3_t_29y=conn.rs.getString("v3_t_29y");
                     v3_t_34y=conn.rs.getString("v3_t_34y");
                     v3_t_39y=conn.rs.getString("v3_t_39y");
                     v3_t_49y=conn.rs.getString("v3_t_49y");
                     v3_t_50y=conn.rs.getString("v3_t_50y");
                     v3_t_total=conn.rs.getString("v3_t_total");
                     v4_s_vmmc_60d=conn.rs.getString("v4_s_vmmc_60d");
                     v4_s_vmmc_4y=conn.rs.getString("v4_s_vmmc_4y");
                     v4_s_vmmc_9y=conn.rs.getString("v4_s_vmmc_9y");
                     v4_s_vmmc_14y=conn.rs.getString("v4_s_vmmc_14y");
                     v4_s_vmmc_19y=conn.rs.getString("v4_s_vmmc_19y");
                     v4_s_vmmc_24y=conn.rs.getString("v4_s_vmmc_24y");
                     v4_s_vmmc_29y=conn.rs.getString("v4_s_vmmc_29y");
                     v4_s_vmmc_34y=conn.rs.getString("v4_s_vmmc_34y");
                     v4_s_vmmc_39y=conn.rs.getString("v4_s_vmmc_39y");
                     v4_s_vmmc_49y=conn.rs.getString("v4_s_vmmc_49y");
                     v4_s_vmmc_50y=conn.rs.getString("v4_s_vmmc_50y");
                     v4_s_vmmc_total=conn.rs.getString("v4_s_vmmc_total");
                     v4_db_vmmc_60d=conn.rs.getString("v4_db_vmmc_60d");
                     v4_db_vmmc_4y=conn.rs.getString("v4_db_vmmc_4y");
                     v4_db_vmmc_9y=conn.rs.getString("v4_db_vmmc_9y");
                     v4_db_vmmc_14y=conn.rs.getString("v4_db_vmmc_14y");
                     v4_db_vmmc_19y=conn.rs.getString("v4_db_vmmc_19y");
                     v4_db_vmmc_24y=conn.rs.getString("v4_db_vmmc_24y");
                     v4_db_vmmc_29y=conn.rs.getString("v4_db_vmmc_29y");
                     v4_db_vmmc_34y=conn.rs.getString("v4_db_vmmc_34y");
                     v4_db_vmmc_39y=conn.rs.getString("v4_db_vmmc_39y");
                     v4_db_vmmc_49y=conn.rs.getString("v4_db_vmmc_49y");
                     v4_db_vmmc_50y=conn.rs.getString("v4_db_vmmc_50y");
                     v4_db_vmmc_total=conn.rs.getString("v4_db_vmmc_total");
                     v4_t_vmmc_60d=conn.rs.getString("v4_t_vmmc_60d");
                     v4_t_vmmc_4y=conn.rs.getString("v4_t_vmmc_4y");
                     v4_t_vmmc_9y=conn.rs.getString("v4_t_vmmc_9y");
                     v4_t_vmmc_14y=conn.rs.getString("v4_t_vmmc_14y");
                     v4_t_vmmc_19y=conn.rs.getString("v4_t_vmmc_19y");
                     v4_t_vmmc_24y=conn.rs.getString("v4_t_vmmc_24y");
                     v4_t_vmmc_29y=conn.rs.getString("v4_t_vmmc_29y");
                     v4_t_vmmc_34y=conn.rs.getString("v4_t_vmmc_34y");
                     v4_t_vmmc_39y=conn.rs.getString("v4_t_vmmc_39y");
                     v4_t_vmmc_49y=conn.rs.getString("v4_t_vmmc_49y");
                     v4_t_vmmc_50y=conn.rs.getString("v4_t_vmmc_50y");
                     v4_t_vmmc_total=conn.rs.getString("v4_t_vmmc_total");
                     v5_followup_60d=conn.rs.getString("v5_followup_60d");
                     v5_followup_4y=conn.rs.getString("v5_followup_4y");
                     v5_followup_9y=conn.rs.getString("v5_followup_9y");
                     v5_followup_14y=conn.rs.getString("v5_followup_14y");
                     v5_followup_19y=conn.rs.getString("v5_followup_19y");
                     v5_followup_24y=conn.rs.getString("v5_followup_24y");
                     v5_followup_29y=conn.rs.getString("v5_followup_29y");
                     v5_followup_34y=conn.rs.getString("v5_followup_34y");
                     v5_followup_39y=conn.rs.getString("v5_followup_39y");
                     v5_followup_49y=conn.rs.getString("v5_followup_49y");
                     v5_followup_50y=conn.rs.getString("v5_followup_50y");
                     v5_followup_total=conn.rs.getString("v5_followup_total");
                     v6_nofollowup_60d=conn.rs.getString("v6_nofollowup_60d");
                     v6_nofollowup_4y=conn.rs.getString("v6_nofollowup_4y");
                     v6_nofollowup_9y=conn.rs.getString("v6_nofollowup_9y");
                     v6_nofollowup_14y=conn.rs.getString("v6_nofollowup_14y");
                     v6_nofollowup_19y=conn.rs.getString("v6_nofollowup_19y");
                     v6_nofollowup_24y=conn.rs.getString("v6_nofollowup_24y");
                     v6_nofollowup_29y=conn.rs.getString("v6_nofollowup_29y");
                     v6_nofollowup_34y=conn.rs.getString("v6_nofollowup_34y");
                     v6_nofollowup_39y=conn.rs.getString("v6_nofollowup_39y");
                     v6_nofollowup_49y=conn.rs.getString("v6_nofollowup_49y");
                     v6_nofollowup_50y=conn.rs.getString("v6_nofollowup_50y");
                     v6_nofollowup_total=conn.rs.getString("v6_nofollowup_total");
                     isValidated=conn.rs.getString("isValidated");
                     isLocked=conn.rs.getString("isLocked");
                     updatedBy=conn.rs.getString("updatedBy");
                     updatedOn=conn.rs.getString("updatedOn");
                     yearmonth=conn.rs.getString("yearmonth");
                     
                     if(isLocked.equals("1")){lock="disabled";}
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
                     
            }
            //desgin entry screen.
            //      to display
 
     System.out.println("Validity checker : "+isValidated);
      if(isValidated.equals("0")){
     validity="<font color='red'><b>Form Not Validated.<img width='20px' height='20px' src='images/notValidated.jpg' style='margin-left:5px;'></b></font>";
    }
        else if(isValidated.equals("1")){
       validity="<font color='green'><b>Form Validated.<img width='20px' height='20px' src='images/validated.jpg' style='margin-left:5px;'></b></font>";
    }
      else{
        validity="<font color=\"blue\"><b>New entry form.</b></font>"  ;          
              }

    session.setAttribute("isValidated", validity);
      
    
    v1 = "<table border=\"1px;\">"
        + "<tr border=\"1px;\">"
        + "<td colspan=\"13\" class=\"title\">1. Number of Males circumcised as part of the minimum package of VMMC for HIV prevention services:</td>"
        + "</tr>"

        +""
        + "<tr border=\"1px;\">"
        + "<td>Age</td>"
        + "<td><60 Days</td>"
        + "<td>2 Months - 4 Years</td>"
        + "<td>5-9</td>"
        + "<td>10-14</td>"
        + "<td>15-19</td>"
        + "<td>20-24</td>"
        + "<td>25-29</td>"
        + "<td>30-34</td>"
        + "<td>35-39</td>"
        + "<td>40-49</td>"
        + "<td>50+</td>"
        + "<td>Totals</td>"
        + "</tr>"
        
        + "<tr>"
        + "<td></td>"
        + "<td><input type=\"text\" name=\"v1_60d\" id=\"v1_60d\" value=\""+v1_60d+"\"  onblur=\"autosave('v1_60d');\"  oninput=\"v1('v1_60d'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v1_4y\" id=\"v1_4y\" value=\""+v1_4y+"\" onblur=\"autosave('v1_4y');\"  oninput=\"v1('v1_4y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v1_9y\" id=\"v1_9y\" value=\""+v1_9y+"\"  onblur=\"autosave('v1_9y');\"  oninput=\"v1('v1_9y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v1_14y\" id=\"v1_14y\" value=\""+v1_14y+"\"  onblur=\"autosave('v1_14y');\"  oninput=\"v1('v1_14y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v1_19y\" id=\"v1_19y\" value=\""+v1_19y+"\" onblur=\"autosave('v1_19y');\"  oninput=\"v1('v1_19y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v1_24y\" id=\"v1_24y\" value=\""+v1_24y+"\" onblur=\"autosave('v1_24y');\"  oninput=\"v1('v1_24y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v1_29y\" id=\"v1_29y\" value=\""+v1_29y+"\" onblur=\"autosave('v1_29y');\"  oninput=\"v1('v1_29y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v1_34y\" id=\"v1_34y\" value=\""+v1_34y+"\" onblur=\"autosave('v1_34y');\"  oninput=\"v1('v1_34y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v1_39y\" id=\"v1_39y\" value=\""+v1_39y+"\"  onblur=\"autosave('v1_39y');\"  oninput=\"v1('v1_39y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v1_49y\" id=\"v1_49y\" value=\""+v1_49y+"\"  onblur=\"autosave('v1_49y');\"  oninput=\"v1('v1_49y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v1_50y\" id=\"v1_50y\" value=\""+v1_50y+"\"  onblur=\"autosave('v1_50y');\"  oninput=\"v1('v1_50y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v1_total\" id=\"v1_total\" value=\""+v1_total+"\" tabindex=\"-1\"  onblur=\"autosave('v1_total');\"  oninput=\"v1('v1_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
            + "</table>";
    
    v2 = "<table border=\"1px;\">"
        + "<tr border=\"1px;\">"
        + "<td colspan=\"13\" class=\"title\">2. Number of Males circumcised as part of the minimum package of VMMC for HIV prevention services:</td>"
        + "</tr>"

        +""
        + "<tr border=\"1px;\">"
        + "<td>Age</td>"
        + "<td><60 Days</td>"
        + "<td>2 Months - 4 Years</td>"
        + "<td>5-9</td>"
        + "<td>10-14</td>"
        + "<td>15-19</td>"
        + "<td>20-24</td>"
        + "<td>25-29</td>"
        + "<td>30-34</td>"
        + "<td>35-39</td>"
        + "<td>40-49</td>"
        + "<td>50+</td>"
        + "<td>Totals</td>"
        + "</tr>"
        
        + "<tr border=\"1px;\">"
        + "<td colspan=\"13\" class=\"title\">During circumcision</td>"
        + "</tr>"
                + "<tr>"
        + "<td>Moderate</td>"
        + "<td><input type=\"text\" name=\"v2_dc_m_60d\" id=\"v2_dc_m_60d\" value=\""+v2_dc_m_60d+"\" onblur=\"autosave('v2_dc_m_60d');\"  oninput=\"v2_dc('v2_dc_m_60d'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_m_4y\" id=\"v2_dc_m_4y\" value=\""+v2_dc_m_4y+"\" onblur=\"autosave('v2_dc_m_4y');\"  oninput=\"v2_dc('v2_dc_m_4y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_m_9y\" id=\"v2_dc_m_9y\" value=\""+v2_dc_m_9y+"\" onblur=\"autosave('v2_dc_m_9y');\"  oninput=\"v2_dc('v2_dc_m_9y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_m_14y\" id=\"v2_dc_m_14y\" value=\""+v2_dc_m_14y+"\" onblur=\"autosave('v2_dc_m_14y');\"  oninput=\"v2_dc('v2_dc_m_14y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_m_19y\" id=\"v2_dc_m_19y\" value=\""+v2_dc_m_19y+"\" onblur=\"autosave('v2_dc_m_19y');\"  oninput=\"v2_dc('v2_dc_m_19y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_m_24y\" id=\"v2_dc_m_24y\" value=\""+v2_dc_m_24y+"\" onblur=\"autosave('v2_dc_m_24y');\"  oninput=\"v2_dc('v2_dc_m_24y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_m_29y\" id=\"v2_dc_m_29y\" value=\""+v2_dc_m_29y+"\" onblur=\"autosave('v2_dc_m_29y');\"  oninput=\"v2_dc('v2_dc_m_29y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_m_34y\" id=\"v2_dc_m_34y\" value=\""+v2_dc_m_34y+"\" onblur=\"autosave('v2_dc_m_34y');\"  oninput=\"v2_dc('v2_dc_m_34y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_m_39y\" id=\"v2_dc_m_39y\" value=\""+v2_dc_m_39y+"\" onblur=\"autosave('v2_dc_m_39y');\"  oninput=\"v2_dc('v2_dc_m_39y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_m_49y\" id=\"v2_dc_m_49y\" value=\""+v2_dc_m_49y+"\"  onblur=\"autosave('v2_dc_m_49y');\"  oninput=\"v2_dc('v2_dc_m_49y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_m_50y\" id=\"v2_dc_m_50y\" value=\""+v2_dc_m_50y+"\" onblur=\"autosave('v2_dc_m_50y');\"  oninput=\"v2_dc('v2_dc_m_50y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_m_total\" id=\"v2_dc_m_total\" value=\""+v2_dc_m_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_dc_m_total');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
		
	+ "<tr>"
        + "<td>Severe</td>"
        + "<td><input type=\"text\" name=\"v2_dc_s_60d\" id=\"v2_dc_s_60d\" value=\""+v2_dc_s_60d+"\" onblur=\"autosave('v2_dc_s_60d');\"  oninput=\"v2_dc('v2_dc_s_60d'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_s_4y\" id=\"v2_dc_s_4y\" value=\""+v2_dc_s_4y+"\" onblur=\"autosave('v2_dc_s_4y');\"  oninput=\"v2_dc('v2_dc_s_4y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_s_9y\" id=\"v2_dc_s_9y\" value=\""+v2_dc_s_9y+"\" onblur=\"autosave('v2_dc_s_9y');\"  oninput=\"v2_dc('v2_dc_s_9y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_s_14y\" id=\"v2_dc_s_14y\" value=\""+v2_dc_s_14y+"\" onblur=\"autosave('v2_dc_s_14y');\"  oninput=\"v2_dc('v2_dc_s_14y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_s_19y\" id=\"v2_dc_s_19y\" value=\""+v2_dc_s_19y+"\" onblur=\"autosave('v2_dc_s_19y');\"  oninput=\"v2_dc('v2_dc_s_19y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_s_24y\" id=\"v2_dc_s_24y\" value=\""+v2_dc_s_24y+"\" onblur=\"autosave('v2_dc_s_24y');\"  oninput=\"v2_dc('v2_dc_s_24y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_s_29y\" id=\"v2_dc_s_29y\" value=\""+v2_dc_s_29y+"\" onblur=\"autosave('v2_dc_s_29y');\"  oninput=\"v2_dc('v2_dc_s_29y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_s_34y\" id=\"v2_dc_s_34y\" value=\""+v2_dc_s_34y+"\" onblur=\"autosave('v2_dc_s_34y');\"  oninput=\"v2_dc('v2_dc_s_34y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_s_39y\" id=\"v2_dc_s_39y\" value=\""+v2_dc_s_39y+"\" onblur=\"autosave('v2_dc_s_39y');\"  oninput=\"v2_dc('v2_dc_s_39y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_s_49y\" id=\"v2_dc_s_49y\" value=\""+v2_dc_s_49y+"\" onblur=\"autosave('v2_dc_s_49y');\"  oninput=\"v2_dc('v2_dc_s_49y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_s_50y\" id=\"v2_dc_s_50y\" value=\""+v2_dc_s_50y+"\" onblur=\"autosave('v2_dc_s_50y');\"  oninput=\"v2_dc('v2_dc_s_50y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_s_total\" id=\"v2_dc_s_total\" value=\""+v2_dc_s_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_dc_s_total');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
			
	+ "<tr>"
        + "<td>Total</td>"
        + "<td><input type=\"text\" name=\"v2_dc_t_60d\" id=\"v2_dc_t_60d\" value=\""+v2_dc_t_60d+"\" tabindex=\"-1\"  onblur=\"autosave('v2_dc_t_60d');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_t_4y\" id=\"v2_dc_t_4y\" value=\""+v2_dc_t_4y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_dc_t_4y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_t_9y\" id=\"v2_dc_t_9y\" value=\""+v2_dc_t_9y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_dc_t_9y');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_t_14y\" id=\"v2_dc_t_14y\" value=\""+v2_dc_t_14y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_dc_t_14y');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_t_19y\" id=\"v2_dc_t_19y\" value=\""+v2_dc_t_19y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_dc_t_19y');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_t_24y\" id=\"v2_dc_t_24y\" value=\""+v2_dc_t_24y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_dc_t_24y');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_t_29y\" id=\"v2_dc_t_29y\" value=\""+v2_dc_t_29y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_dc_t_29y');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_t_34y\" id=\"v2_dc_t_34y\" value=\""+v2_dc_t_34y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_dc_t_34y');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_t_39y\" id=\"v2_dc_t_39y\" value=\""+v2_dc_t_39y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_dc_t_39y');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_t_49y\" id=\"v2_dc_t_49y\" value=\""+v2_dc_t_49y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_dc_t_49y');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_t_50y\" id=\"v2_dc_t_50y\" value=\""+v2_dc_t_50y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_dc_t_50y');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_dc_t_total\" id=\"v2_dc_t_total\" value=\""+v2_dc_t_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_dc_t_total');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
        
	+ "<tr border=\"1px;\">"
        + "<td colspan=\"13\" class=\"title\">Post circumcision</td>"
        + "</tr>"
            
	+ "<tr>"
        + "<td>Moderate</td>"
        + "<td><input type=\"text\" name=\"v2_pc_m_60d\" id=\"v2_pc_m_60d\" value=\""+v2_pc_m_60d+"\"  onblur=\"autosave('v2_pc_m_60d');\"  oninput=\"v2_pc('v2_pc_m_60d'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_m_4y\" id=\"v2_pc_m_4y\" value=\""+v2_pc_m_4y+"\"  onblur=\"autosave('v2_pc_m_4y');\"  oninput=\"v2_pc('v2_pc_m_4y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_m_9y\" id=\"v2_pc_m_9y\" value=\""+v2_pc_m_9y+"\"  onblur=\"autosave('v2_pc_m_9y');\"  oninput=\"v2_pc('v2_pc_m_9y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_m_14y\" id=\"v2_pc_m_14y\" value=\""+v2_pc_m_14y+"\" onblur=\"autosave('v2_pc_m_14y');\"  oninput=\"v2_pc('v2_pc_m_14y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_m_19y\" id=\"v2_pc_m_19y\" value=\""+v2_pc_m_19y+"\"  onblur=\"autosave('v2_pc_m_19y');\"  oninput=\"v2_pc('v2_pc_m_19y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_m_24y\" id=\"v2_pc_m_24y\" value=\""+v2_pc_m_24y+"\" onblur=\"autosave('v2_pc_m_24y');\"  oninput=\"v2_pc('v2_pc_m_24y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_m_29y\" id=\"v2_pc_m_29y\" value=\""+v2_pc_m_29y+"\" onblur=\"autosave('v2_pc_m_29y');\"  oninput=\"v2_pc('v2_pc_m_29y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_m_34y\" id=\"v2_pc_m_34y\" value=\""+v2_pc_m_34y+"\"  onblur=\"autosave('v2_pc_m_34y');\"  oninput=\"v2_pc('v2_pc_m_34y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_m_39y\" id=\"v2_pc_m_39y\" value=\""+v2_pc_m_39y+"\" onblur=\"autosave('v2_pc_m_39y');\"  oninput=\"v2_pc('v2_pc_m_39y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_m_49y\" id=\"v2_pc_m_49y\" value=\""+v2_pc_m_49y+"\"  onblur=\"autosave('v2_pc_m_49y');\"  oninput=\"v2_pc('v2_pc_m_49y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_m_50y\" id=\"v2_pc_m_50y\" value=\""+v2_pc_m_50y+"\"  onblur=\"autosave('v2_pc_m_50y');\"  oninput=\"v2_pc('v2_pc_m_50y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_m_total\" id=\"v2_pc_m_total\" value=\""+v2_pc_m_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_pc_m_total');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
			
	+ "<tr>"
        + "<td>Severe</td>"
        + "<td><input type=\"text\" name=\"v2_pc_s_60d\" id=\"v2_pc_s_60d\" value=\""+v2_pc_s_60d+"\"  onblur=\"autosave('v2_pc_s_60d');\"  oninput=\"v2_pc('v2_pc_s_60d'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_s_4y\" id=\"v2_pc_s_4y\" value=\""+v2_pc_s_4y+"\"  onblur=\"autosave('v2_pc_s_4y');\"  oninput=\"v2_pc('v2_pc_s_4y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_s_9y\" id=\"v2_pc_s_9y\" value=\""+v2_pc_s_9y+"\"  onblur=\"autosave('v2_pc_s_9y');\"  oninput=\"v2_pc('v2_pc_s_9y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_s_14y\" id=\"v2_pc_s_14y\" value=\""+v2_pc_s_14y+"\" onblur=\"autosave('v2_pc_s_14y');\"  oninput=\"v2_pc('v2_pc_s_14y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_s_19y\" id=\"v2_pc_s_19y\" value=\""+v2_pc_s_19y+"\" onblur=\"autosave('v2_pc_s_19y');\"  oninput=\"v2_pc('v2_pc_s_19y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_s_24y\" id=\"v2_pc_s_24y\" value=\""+v2_pc_s_24y+"\" onblur=\"autosave('v2_pc_s_24y');\"  oninput=\"v2_pc('v2_pc_s_24y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_s_29y\" id=\"v2_pc_s_29y\" value=\""+v2_pc_s_29y+"\" onblur=\"autosave('v2_pc_s_29y');\"  oninput=\"v2_pc('v2_pc_s_29y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_s_34y\" id=\"v2_pc_s_34y\" value=\""+v2_pc_s_34y+"\" onblur=\"autosave('v2_pc_s_34y');\"  oninput=\"v2_pc('v2_pc_s_34y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_s_39y\" id=\"v2_pc_s_39y\" value=\""+v2_pc_s_39y+"\" onblur=\"autosave('v2_pc_s_39y');\"  oninput=\"v2_pc('v2_pc_s_39y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_s_49y\" id=\"v2_pc_s_49y\" value=\""+v2_pc_s_49y+"\" onblur=\"autosave('v2_pc_s_49y');\"  oninput=\"v2_pc('v2_pc_s_49y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_s_50y\" id=\"v2_pc_s_50y\" value=\""+v2_pc_s_50y+"\" onblur=\"autosave('v2_pc_s_50y');\"  oninput=\"v2_pc('v2_pc_s_50y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_s_total\" id=\"v2_pc_s_total\" value=\""+v2_pc_s_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_pc_s_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
			
	+ "<tr>"
        + "<td>Total</td>"
        + "<td><input type=\"text\" name=\"v2_pc_t_60d\" id=\"v2_pc_t_60d\" value=\""+v2_pc_t_60d+"\" tabindex=\"-1\"  onblur=\"autosave('v2_pc_t_60d');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_t_4y\" id=\"v2_pc_t_4y\" value=\""+v2_pc_t_4y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_pc_t_4y');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_t_9y\" id=\"v2_pc_t_9y\" value=\""+v2_pc_t_9y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_pc_t_9y');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_t_14y\" id=\"v2_pc_t_14y\" value=\""+v2_pc_t_14y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_pc_t_14y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_t_19y\" id=\"v2_pc_t_19y\" value=\""+v2_pc_t_19y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_pc_t_19y');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_t_24y\" id=\"v2_pc_t_24y\" value=\""+v2_pc_t_24y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_pc_t_24y');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_t_29y\" id=\"v2_pc_t_29y\" value=\""+v2_pc_t_29y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_pc_t_29y');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_t_34y\" id=\"v2_pc_t_34y\" value=\""+v2_pc_t_34y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_pc_t_34y');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_t_39y\" id=\"v2_pc_t_39y\" value=\""+v2_pc_t_39y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_pc_t_39y');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_t_49y\" id=\"v2_pc_t_49y\" value=\""+v2_pc_t_49y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_pc_t_49y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_t_50y\" id=\"v2_pc_t_50y\" value=\""+v2_pc_t_50y+"\" tabindex=\"-1\"  onblur=\"autosave('v2_pc_t_50y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_pc_t_total\" id=\"v2_pc_t_total\" value=\""+v2_pc_t_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_pc_t_total');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
	+ "<tr>"
        + "<td class=\"title\">Total adverse events</td>"
        + "<td><input type=\"text\" name=\"v2_60d_total\" id=\"v2_60d_total\" value=\""+v2_60d_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_60d_total');\"  oninput=\"v2('v2_60d_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_4y_total\" id=\"v2_4y_total\" value=\""+v2_4y_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_4y_total');\"  oninput=\"v2('v2_4y_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_9y_total\" id=\"v2_9y_total\" value=\""+v2_9y_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_9y_total');\"  oninput=\"v2('v2_9y_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_14y_total\" id=\"v2_14y_total\" value=\""+v2_14y_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_14y_total');\"  oninput=\"v2('v2_14y_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_19y_total\" id=\"v2_19y_total\" value=\""+v2_19y_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_19y_total');\"  oninput=\"v2('v2_19y_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_24y_total\" id=\"v2_24y_total\" value=\""+v2_24y_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_24y_total');\"  oninput=\"v2('v2_24y_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_29y_total\" id=\"v2_29y_total\" value=\""+v2_29y_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_29y_total');\"  oninput=\"v2('v2_29y_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_34y_total\" id=\"v2_34y_total\" value=\""+v2_34y_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_34y_total');\"  oninput=\"v2('v2_34y_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_39y_total\" id=\"v2_39y_total\" value=\""+v2_39y_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_39y_total');\"  oninput=\"v2('v2_39y_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_49y_total\" id=\"v2_49y_total\" value=\""+v2_49y_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_49y_total');\"  oninput=\"v2('v2_49y_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_50y_total\" id=\"v2_50y_total\" value=\""+v2_50y_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_50y_total');\"  oninput=\"v2('v2_50y_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v2_total\" id=\"v2_total\" value=\""+v2_total+"\" tabindex=\"-1\"  onblur=\"autosave('v2_total');\"  oninput=\"v2('v2_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
            + "</table>";
    
    v3 = "<table border=\"1px;\">"
        + "<tr border=\"1px;\">"
        + "<td colspan=\"13\" class=\"title\">3. HIV Status of VMMC clients:</td>"
        + "</tr>"

        +""
        + "<tr border=\"1px;\">"
        + "<td>Age</td>"
        + "<td><60 Days</td>"
        + "<td>2 Months - 4 Years</td>"
        + "<td>5-9</td>"
        + "<td>10-14</td>"
        + "<td>15-19</td>"
        + "<td>20-24</td>"
        + "<td>25-29</td>"
        + "<td>30-34</td>"
        + "<td>35-39</td>"
        + "<td>40-49</td>"
        + "<td>50+</td>"
        + "<td>Totals</td>"
        + "</tr>"
        
               
        + "<tr>"
        + "<td>Tested Positive</td>"
        + "<td><input type=\"text\" name=\"v3_tp_60d\" id=\"v3_tp_60d\" value=\""+v3_tp_60d+"\"  onblur=\"autosave('v3_tp_60d');\"  oninput=\"v3('v3_tp_60d'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tp_4y\" id=\"v3_tp_4y\" value=\""+v3_tp_4y+"\"  onblur=\"autosave('v3_tp_4y');\"  oninput=\"v3('v3_tp_4y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tp_9y\" id=\"v3_tp_9y\" value=\""+v3_tp_9y+"\"  onblur=\"autosave('v3_tp_9y');\"  oninput=\"v3('v3_tp_9y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tp_14y\" id=\"v3_tp_14y\" value=\""+v3_tp_14y+"\" onblur=\"autosave('v3_tp_14y');\"  oninput=\"v3('v3_tp_14y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tp_19y\" id=\"v3_tp_19y\" value=\""+v3_tp_19y+"\" onblur=\"autosave('v3_tp_19y');\"  oninput=\"v3('v3_tp_19y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tp_24y\" id=\"v3_tp_24y\" value=\""+v3_tp_24y+"\" onblur=\"autosave('v3_tp_24y');\"  oninput=\"v3('v3_tp_24y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tp_29y\" id=\"v3_tp_29y\" value=\""+v3_tp_29y+"\"onblur=\"autosave('v3_tp_29y');\"  oninput=\"v3('v3_tp_29y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tp_34y\" id=\"v3_tp_34y\" value=\""+v3_tp_34y+"\" onblur=\"autosave('v3_tp_34y');\"  oninput=\"v3('v3_tp_34y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tp_39y\" id=\"v3_tp_39y\" value=\""+v3_tp_39y+"\" onblur=\"autosave('v3_tp_39y');\"  oninput=\"v3('v3_tp_39y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tp_49y\" id=\"v3_tp_49y\" value=\""+v3_tp_49y+"\" onblur=\"autosave('v3_tp_49y');\"  oninput=\"v3('v3_tp_49y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tp_50y\" id=\"v3_tp_50y\" value=\""+v3_tp_50y+"\" onblur=\"autosave('v3_tp_50y');\"  oninput=\"v3('v3_tp_50y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tp_total\" id=\"v3_tp_total\" value=\""+v3_tp_total+"\" tabindex=\"-1\"  onblur=\"autosave('v3_tp_total');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
                    
        + "<tr>"
        + "<td>Self-reported positive</td>"
        + "<td><input type=\"text\" name=\"v3_srp_60d\" id=\"v3_srp_60d\" value=\""+v3_srp_60d+"\"  onblur=\"autosave('v3_srp_60d');\"  oninput=\"v3('v3_srp_60d'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srp_4y\" id=\"v3_srp_4y\" value=\""+v3_srp_4y+"\"  onblur=\"autosave('v3_srp_4y');\"  oninput=\"v3('v3_srp_4y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srp_9y\" id=\"v3_srp_9y\" value=\""+v3_srp_9y+"\"  onblur=\"autosave('v3_srp_9y');\"  oninput=\"v3('v3_srp_9y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srp_14y\" id=\"v3_srp_14y\" value=\""+v3_srp_14y+"\"  onblur=\"autosave('v3_srp_14y');\"  oninput=\"v3('v3_srp_14y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srp_19y\" id=\"v3_srp_19y\" value=\""+v3_srp_19y+"\"  onblur=\"autosave('v3_srp_19y');\"  oninput=\"v3('v3_srp_19y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srp_24y\" id=\"v3_srp_24y\" value=\""+v3_srp_24y+"\"  onblur=\"autosave('v3_srp_24y');\"  oninput=\"v3('v3_srp_24y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srp_29y\" id=\"v3_srp_29y\" value=\""+v3_srp_29y+"\"  onblur=\"autosave('v3_srp_29y');\"  oninput=\"v3('v3_srp_29y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srp_34y\" id=\"v3_srp_34y\" value=\""+v3_srp_34y+"\"  onblur=\"autosave('v3_srp_34y');\"  oninput=\"v3('v3_srp_34y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srp_39y\" id=\"v3_srp_39y\" value=\""+v3_srp_39y+"\"  onblur=\"autosave('v3_srp_39y');\"  oninput=\"v3('v3_srp_39y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srp_49y\" id=\"v3_srp_49y\" value=\""+v3_srp_49y+"\"  onblur=\"autosave('v3_srp_49y');\"  oninput=\"v3('v3_srp_49y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srp_50y\" id=\"v3_srp_50y\" value=\""+v3_srp_50y+"\"   onblur=\"autosave('v3_srp_50y');\"  oninput=\"v3('v3_srp_50y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srp_total\" id=\"v3_srp_total\" value=\""+v3_srp_total+"\" tabindex=\"-1\"  onblur=\"autosave('v3_srp_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
                    
        + "<tr>"
        + "<td>Tested negative</td>"
        + "<td><input type=\"text\" name=\"v3_tn_60d\" id=\"v3_tn_60d\" value=\""+v3_tn_60d+"\" onblur=\"autosave('v3_tn_60d');\"  oninput=\"v3('v3_tn_60d'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tn_4y\" id=\"v3_tn_4y\" value=\""+v3_tn_4y+"\" onblur=\"autosave('v3_tn_4y');\"  oninput=\"v3('v3_tn_4y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tn_9y\" id=\"v3_tn_9y\" value=\""+v3_tn_9y+"\"  onblur=\"autosave('v3_tn_9y');\"  oninput=\"v3('v3_tn_9y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tn_14y\" id=\"v3_tn_14y\" value=\""+v3_tn_14y+"\" onblur=\"autosave('v3_tn_14y');\"  oninput=\"v3('v3_tn_14y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tn_19y\" id=\"v3_tn_19y\" value=\""+v3_tn_19y+"\" onblur=\"autosave('v3_tn_19y');\"  oninput=\"v3('v3_tn_19y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tn_24y\" id=\"v3_tn_24y\" value=\""+v3_tn_24y+"\" onblur=\"autosave('v3_tn_24y');\"  oninput=\"v3('v3_tn_24y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tn_29y\" id=\"v3_tn_29y\" value=\""+v3_tn_29y+"\" onblur=\"autosave('v3_tn_29y');\"  oninput=\"v3('v3_tn_29y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tn_34y\" id=\"v3_tn_34y\" value=\""+v3_tn_34y+"\"  onblur=\"autosave('v3_tn_34y');\"  oninput=\"v3('v3_tn_34y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tn_39y\" id=\"v3_tn_39y\" value=\""+v3_tn_39y+"\" onblur=\"autosave('v3_tn_39y');\"  oninput=\"v3('v3_tn_39y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tn_49y\" id=\"v3_tn_49y\" value=\""+v3_tn_49y+"\" onblur=\"autosave('v3_tn_49y');\"  oninput=\"v3('v3_tn_49y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tn_50y\" id=\"v3_tn_50y\" value=\""+v3_tn_50y+"\" onblur=\"autosave('v3_tn_50y');\"  oninput=\"v3('v3_tn_50y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_tn_total\" id=\"v3_tn_total\" value=\""+v3_tn_total+"\" tabindex=\"-1\"  onblur=\"autosave('v3_tn_total');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
                    
        + "<tr>"
        + "<td>Indeternimate HIV status or not tested for HIV at site</td>"
        + "<td><input type=\"text\" name=\"v3_nt_60d\" id=\"v3_nt_60d\" value=\""+v3_nt_60d+"\" onblur=\"autosave('v3_nt_60d');\"  oninput=\"v3('v3_nt_60d'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_nt_4y\" id=\"v3_nt_4y\" value=\""+v3_nt_4y+"\" onblur=\"autosave('v3_nt_4y');\"  oninput=\"v3('v3_nt_4y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_nt_9y\" id=\"v3_nt_9y\" value=\""+v3_nt_9y+"\" onblur=\"autosave('v3_nt_9y');\"  oninput=\"v3('v3_nt_9y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_nt_14y\" id=\"v3_nt_14y\" value=\""+v3_nt_14y+"\" onblur=\"autosave('v3_nt_14y');\"  oninput=\"v3('v3_nt_14y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_nt_19y\" id=\"v3_nt_19y\" value=\""+v3_nt_19y+"\" onblur=\"autosave('v3_nt_19y');\"  oninput=\"v3('v3_nt_19y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_nt_24y\" id=\"v3_nt_24y\" value=\""+v3_nt_24y+"\" onblur=\"autosave('v3_nt_24y');\"  oninput=\"v3('v3_nt_24y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_nt_29y\" id=\"v3_nt_29y\" value=\""+v3_nt_29y+"\" onblur=\"autosave('v3_nt_29y');\"  oninput=\"v3('v3_nt_29y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_nt_34y\" id=\"v3_nt_34y\" value=\""+v3_nt_34y+"\" onblur=\"autosave('v3_nt_34y');\"  oninput=\"v3('v3_nt_34y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_nt_39y\" id=\"v3_nt_39y\" value=\""+v3_nt_39y+"\" onblur=\"autosave('v3_nt_39y');\"  oninput=\"v3('v3_nt_39y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_nt_49y\" id=\"v3_nt_49y\" value=\""+v3_nt_49y+"\" onblur=\"autosave('v3_nt_49y');\"  oninput=\"v3('v3_nt_49y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_nt_50y\" id=\"v3_nt_50y\" value=\""+v3_nt_50y+"\" onblur=\"autosave('v3_nt_50y');\"  oninput=\"v3('v3_nt_50y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_nt_total\" id=\"v3_nt_total\" value=\""+v3_nt_total+"\" tabindex=\"-1\"  onblur=\"autosave('v3_nt_total');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
                    
        + "<tr>"
        + "<td>Unknown Status</td>"
        + "<td><input type=\"text\" name=\"v3_us_60d\" id=\"v3_us_60d\" value=\""+v3_us_60d+"\" onblur=\"autosave('v3_us_60d');\"  oninput=\"v3('v3_us_60d'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_us_4y\" id=\"v3_us_4y\" value=\""+v3_us_4y+"\" onblur=\"autosave('v3_us_4y');\"  oninput=\"v3('v3_us_4y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_us_9y\" id=\"v3_us_9y\" value=\""+v3_us_9y+"\" onblur=\"autosave('v3_us_9y');\"  oninput=\"v3('v3_us_9y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_us_14y\" id=\"v3_us_14y\" value=\""+v3_us_14y+"\" onblur=\"autosave('v3_us_14y');\"  oninput=\"v3('v3_us_14y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_us_19y\" id=\"v3_us_19y\" value=\""+v3_us_19y+"\" onblur=\"autosave('v3_us_19y');\"  oninput=\"v3('v3_us_19y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_us_24y\" id=\"v3_us_24y\" value=\""+v3_us_24y+"\" onblur=\"autosave('v3_us_24y');\"  oninput=\"v3('v3_us_24y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_us_29y\" id=\"v3_us_29y\" value=\""+v3_us_29y+"\" onblur=\"autosave('v3_us_29y');\"  oninput=\"v3('v3_us_29y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_us_34y\" id=\"v3_us_34y\" value=\""+v3_us_34y+"\" onblur=\"autosave('v3_us_34y');\"  oninput=\"v3('v3_us_34y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_us_39y\" id=\"v3_us_39y\" value=\""+v3_us_39y+"\" onblur=\"autosave('v3_us_39y');\"  oninput=\"v3('v3_us_39y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_us_49y\" id=\"v3_us_49y\" value=\""+v3_us_49y+"\" onblur=\"autosave('v3_us_49y');\"  oninput=\"v3('v3_us_49y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_us_50y\" id=\"v3_us_50y\" value=\""+v3_us_50y+"\" onblur=\"autosave('v3_us_50y');\"  oninput=\"v3('v3_us_50y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_us_total\" id=\"v3_us_total\" value=\""+v3_us_total+"\" tabindex=\"-1\"  onblur=\"autosave('v3_us_total');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
                    
        + "<tr>"
        + "<td>Self-reported negative</td>"
        + "<td><input type=\"text\" name=\"v3_srn_60d\" id=\"v3_srn_60d\" value=\""+v3_srn_60d+"\" onblur=\"autosave('v3_srn_60d');\"  oninput=\"v3('v3_srn_60d'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srn_4y\" id=\"v3_srn_4y\" value=\""+v3_srn_4y+"\" onblur=\"autosave('v3_srn_4y');\"  oninput=\"v3('v3_srn_4y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srn_9y\" id=\"v3_srn_9y\" value=\""+v3_srn_9y+"\" onblur=\"autosave('v3_srn_9y');\"  oninput=\"v3('v3_srn_9y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srn_14y\" id=\"v3_srn_14y\" value=\""+v3_srn_14y+"\" onblur=\"autosave('v3_srn_14y');\"  oninput=\"v3('v3_srn_14y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srn_19y\" id=\"v3_srn_19y\" value=\""+v3_srn_19y+"\" onblur=\"autosave('v3_srn_19y');\"  oninput=\"v3('v3_srn_19y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srn_24y\" id=\"v3_srn_24y\" value=\""+v3_srn_24y+"\" onblur=\"autosave('v3_srn_24y');\"  oninput=\"v3('v3_srn_24y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srn_29y\" id=\"v3_srn_29y\" value=\""+v3_srn_29y+"\" onblur=\"autosave('v3_srn_29y');\"  oninput=\"v3('v3_srn_29y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srn_34y\" id=\"v3_srn_34y\" value=\""+v3_srn_34y+"\" onblur=\"autosave('v3_srn_34y');\"  oninput=\"v3('v3_srn_34y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srn_39y\" id=\"v3_srn_39y\" value=\""+v3_srn_39y+"\" onblur=\"autosave('v3_srn_39y');\"  oninput=\"v3('v3_srn_39y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srn_49y\" id=\"v3_srn_49y\" value=\""+v3_srn_49y+"\" onblur=\"autosave('v3_srn_49y');\"  oninput=\"v3('v3_srn_49y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srn_50y\" id=\"v3_srn_50y\" value=\""+v3_srn_50y+"\" onblur=\"autosave('v3_srn_50y');\"  oninput=\"v3('v3_srn_50y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v3_srn_total\" id=\"v3_srn_total\" value=\""+v3_srn_total+"\" tabindex=\"-1\"  onblur=\"autosave('v3_srn_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
                    
        + "<tr>"
        + "<td class=\"title\">Total</td>"
        + "<td><input type=\"text\" name=\"v3_t_60d\" id=\"v3_t_60d\" value=\""+v3_t_60d+"\" tabindex=\"-1\"  onblur=\"autosave('v3_t_60d');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v3_t_4y\" id=\"v3_t_4y\" value=\""+v3_t_4y+"\" tabindex=\"-1\"  onblur=\"autosave('v3_t_4y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v3_t_9y\" id=\"v3_t_9y\" value=\""+v3_t_9y+"\" tabindex=\"-1\"  onblur=\"autosave('v3_t_9y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v3_t_14y\" id=\"v3_t_14y\" value=\""+v3_t_14y+"\" tabindex=\"-1\"  onblur=\"autosave('v3_t_14y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"   ></td>"
        + "<td><input type=\"text\" name=\"v3_t_19y\" id=\"v3_t_19y\" value=\""+v3_t_19y+"\" tabindex=\"-1\"  onblur=\"autosave('v3_t_19y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v3_t_24y\" id=\"v3_t_24y\" value=\""+v3_t_24y+"\" tabindex=\"-1\"  onblur=\"autosave('v3_t_24y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v3_t_29y\" id=\"v3_t_29y\" value=\""+v3_t_29y+"\" tabindex=\"-1\"  onblur=\"autosave('v3_t_29y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v3_t_34y\" id=\"v3_t_34y\" value=\""+v3_t_34y+"\" tabindex=\"-1\"  onblur=\"autosave('v3_t_34y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v3_t_39y\" id=\"v3_t_39y\" value=\""+v3_t_39y+"\" tabindex=\"-1\"  onblur=\"autosave('v3_t_39y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v3_t_49y\" id=\"v3_t_49y\" value=\""+v3_t_49y+"\" tabindex=\"-1\"  onblur=\"autosave('v3_t_49y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v3_t_50y\" id=\"v3_t_50y\" value=\""+v3_t_50y+"\" tabindex=\"-1\"  onblur=\"autosave('v3_t_50y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v3_t_total\" id=\"v3_t_total\" value=\""+v3_t_total+"\" tabindex=\"-1\"  onblur=\"autosave('v3_t_total');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
            
            
            + "</table>";
    
    v4 = "<table border=\"1px;\">"
        + "<tr border=\"1px;\">"
        + "<td colspan=\"13\" class=\"title\">4. Circumcision Technique</td>"
        + "</tr>"

        +""
        + "<tr border=\"1px;\">"
        + "<td>Age</td>"
        + "<td><60 Days</td>"
        + "<td>2 Months - 4 Years</td>"
        + "<td>5-9</td>"
        + "<td>10-14</td>"
        + "<td>15-19</td>"
        + "<td>20-24</td>"
        + "<td>25-29</td>"
        + "<td>30-34</td>"
        + "<td>35-39</td>"
        + "<td>40-49</td>"
        + "<td>50+</td>"
        + "<td>Totals</td>"
        + "</tr>"
        
        + "<tr>"
        + "<td>Surgical VMMC</td>"
        + "<td><input type=\"text\" name=\"v4_s_vmmc_60d\" id=\"v4_s_vmmc_60d\" value=\""+v4_s_vmmc_60d+"\"  onblur=\"autosave('v4_s_vmmc_60d');\"  oninput=\"v4('v4_s_vmmc_60d'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_s_vmmc_4y\" id=\"v4_s_vmmc_4y\" value=\""+v4_s_vmmc_4y+"\"  onblur=\"autosave('v4_s_vmmc_4y');\"  oninput=\"v4('v4_s_vmmc_4y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_s_vmmc_9y\" id=\"v4_s_vmmc_9y\" value=\""+v4_s_vmmc_9y+"\"  onblur=\"autosave('v4_s_vmmc_9y');\"  oninput=\"v4('v4_s_vmmc_9y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_s_vmmc_14y\" id=\"v4_s_vmmc_14y\" value=\""+v4_s_vmmc_14y+"\"  onblur=\"autosave('v4_s_vmmc_14y');\"  oninput=\"v4('v4_s_vmmc_14y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_s_vmmc_19y\" id=\"v4_s_vmmc_19y\" value=\""+v4_s_vmmc_19y+"\"  onblur=\"autosave('v4_s_vmmc_19y');\"  oninput=\"v4('v4_s_vmmc_19y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_s_vmmc_24y\" id=\"v4_s_vmmc_24y\" value=\""+v4_s_vmmc_24y+"\"  onblur=\"autosave('v4_s_vmmc_24y');\"  oninput=\"v4('v4_s_vmmc_24y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_s_vmmc_29y\" id=\"v4_s_vmmc_29y\" value=\""+v4_s_vmmc_29y+"\"  onblur=\"autosave('v4_s_vmmc_29y');\"  oninput=\"v4('v4_s_vmmc_29y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_s_vmmc_34y\" id=\"v4_s_vmmc_34y\" value=\""+v4_s_vmmc_34y+"\"  onblur=\"autosave('v4_s_vmmc_34y');\"  oninput=\"v4('v4_s_vmmc_34y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_s_vmmc_39y\" id=\"v4_s_vmmc_39y\" value=\""+v4_s_vmmc_39y+"\"  onblur=\"autosave('v4_s_vmmc_39y');\"  oninput=\"v4('v4_s_vmmc_39y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_s_vmmc_49y\" id=\"v4_s_vmmc_49y\" value=\""+v4_s_vmmc_49y+"\" onblur=\"autosave('v4_s_vmmc_49y');\"  oninput=\"v4('v4_s_vmmc_49y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_s_vmmc_50y\" id=\"v4_s_vmmc_50y\" value=\""+v4_s_vmmc_50y+"\" onblur=\"autosave('v4_s_vmmc_50y');\"  oninput=\"v4('v4_s_vmmc_50y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_s_vmmc_total\" id=\"v4_s_vmmc_total\" value=\""+v4_s_vmmc_total+"\" tabindex=\"-1\"  onblur=\"autosave('v4_s_vmmc_total');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
            + "<tr>"
        + "<td>Device Based VMMC</td>"
        + "<td><input type=\"text\" name=\"v4_db_vmmc_60d\" id=\"v4_db_vmmc_60d\" value=\""+v4_db_vmmc_60d+"\"  onblur=\"autosave('v4_db_vmmc_60d');\"  oninput=\"v4('v4_db_vmmc_60d'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_db_vmmc_4y\" id=\"v4_db_vmmc_4y\" value=\""+v4_db_vmmc_4y+"\"  onblur=\"autosave('v4_db_vmmc_4y');\"  oninput=\"v4('v4_db_vmmc_4y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_db_vmmc_9y\" id=\"v4_db_vmmc_9y\" value=\""+v4_db_vmmc_9y+"\"  onblur=\"autosave('v4_db_vmmc_9y');\"  oninput=\"v4('v4_db_vmmc_9y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_db_vmmc_14y\" id=\"v4_db_vmmc_14y\" value=\""+v4_db_vmmc_14y+"\"  onblur=\"autosave('v4_db_vmmc_14y');\"  oninput=\"v4('v4_db_vmmc_14y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_db_vmmc_19y\" id=\"v4_db_vmmc_19y\" value=\""+v4_db_vmmc_19y+"\"  onblur=\"autosave('v4_db_vmmc_19y');\"  oninput=\"v4('v4_db_vmmc_19y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_db_vmmc_24y\" id=\"v4_db_vmmc_24y\" value=\""+v4_db_vmmc_24y+"\"  onblur=\"autosave('v4_db_vmmc_24y');\"  oninput=\"v4('v4_db_vmmc_24y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_db_vmmc_29y\" id=\"v4_db_vmmc_29y\" value=\""+v4_db_vmmc_29y+"\"  onblur=\"autosave('v4_db_vmmc_29y');\"  oninput=\"v4('v4_db_vmmc_29y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_db_vmmc_34y\" id=\"v4_db_vmmc_34y\" value=\""+v4_db_vmmc_34y+"\" onblur=\"autosave('v4_db_vmmc_34y');\"  oninput=\"v4('v4_db_vmmc_34y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_db_vmmc_39y\" id=\"v4_db_vmmc_39y\" value=\""+v4_db_vmmc_39y+"\"  onblur=\"autosave('v4_db_vmmc_39y');\"  oninput=\"v4('v4_db_vmmc_39y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_db_vmmc_49y\" id=\"v4_db_vmmc_49y\" value=\""+v4_db_vmmc_49y+"\"  onblur=\"autosave('v4_db_vmmc_49y');\"  oninput=\"v4('v4_db_vmmc_49y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_db_vmmc_50y\" id=\"v4_db_vmmc_50y\" value=\""+v4_db_vmmc_50y+"\"  onblur=\"autosave('v4_db_vmmc_50y');\"  oninput=\"v4('v4_db_vmmc_50y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v4_db_vmmc_total\" id=\"v4_db_vmmc_total\" value=\""+v4_db_vmmc_total+"\" tabindex=\"-1\"  onblur=\"autosave('v4_db_vmmc_total');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
            + "<tr>"
        + "<td class=\"title\">Total</td>"
        + "<td><input type=\"text\" name=\"v4_t_vmmc_60d\" id=\"v4_t_vmmc_60d\" value=\""+v4_t_vmmc_60d+"\" tabindex=\"-1\"  onblur=\"autosave('v4_t_vmmc_60d');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v4_t_vmmc_4y\" id=\"v4_t_vmmc_4y\" value=\""+v4_t_vmmc_4y+"\" tabindex=\"-1\"  onblur=\"autosave('v4_t_vmmc_4y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v4_t_vmmc_9y\" id=\"v4_t_vmmc_9y\" value=\""+v4_t_vmmc_9y+"\" tabindex=\"-1\"  onblur=\"autosave('v4_t_vmmc_9y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v4_t_vmmc_14y\" id=\"v4_t_vmmc_14y\" value=\""+v4_t_vmmc_14y+"\" tabindex=\"-1\"  onblur=\"autosave('v4_t_vmmc_14y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v4_t_vmmc_19y\" id=\"v4_t_vmmc_19y\" value=\""+v4_t_vmmc_19y+"\" tabindex=\"-1\"  onblur=\"autosave('v4_t_vmmc_19y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v4_t_vmmc_24y\" id=\"v4_t_vmmc_24y\" value=\""+v4_t_vmmc_24y+"\" tabindex=\"-1\"  onblur=\"autosave('v4_t_vmmc_24y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v4_t_vmmc_29y\" id=\"v4_t_vmmc_29y\" value=\""+v4_t_vmmc_29y+"\" tabindex=\"-1\"  onblur=\"autosave('v4_t_vmmc_29y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v4_t_vmmc_34y\" id=\"v4_t_vmmc_34y\" value=\""+v4_t_vmmc_34y+"\" tabindex=\"-1\"  onblur=\"autosave('v4_t_vmmc_34y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v4_t_vmmc_39y\" id=\"v4_t_vmmc_39y\" value=\""+v4_t_vmmc_39y+"\" tabindex=\"-1\"  onblur=\"autosave('v4_t_vmmc_39y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v4_t_vmmc_49y\" id=\"v4_t_vmmc_49y\" value=\""+v4_t_vmmc_49y+"\" tabindex=\"-1\"  onblur=\"autosave('v4_t_vmmc_49y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v4_t_vmmc_50y\" id=\"v4_t_vmmc_50y\" value=\""+v4_t_vmmc_50y+"\" tabindex=\"-1\"  onblur=\"autosave('v4_t_vmmc_50y');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"v4_t_vmmc_total\" id=\"v4_t_vmmc_total\" value=\""+v4_t_vmmc_total+"\" tabindex=\"-1\"  onblur=\"autosave('v4_t_vmmc_total');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
            + "</table>";
                    
                     
    v5 = "<table border=\"1px;\">"
        + "<tr border=\"1px;\">"
        + "<td colspan=\"13\" class=\"title\">5. Number of Males circumcised within the reporting period who <u>return</u> atleast for postoperative follow-up care (routine or emergent) within 14 days of surgery:</td>"
        + "</tr>"

        +""
        + "<tr border=\"3px;\">"
        + "<td>Age</td>"
        + "<td><60 Days</td>"
        + "<td>2 Months - 4 Years</td>"
        + "<td>5-9</td>"
        + "<td>10-14</td>"
        + "<td>15-19</td>"
        + "<td>20-24</td>"
        + "<td>25-29</td>"
        + "<td>30-34</td>"
        + "<td>35-39</td>"
        + "<td>40-49</td>"
        + "<td>50+</td>"
        + "<td>Totals</td>"
        + "</tr>"
        
        + "<tr>"
        + "<td></td>"
        + "<td><input type=\"text\" name=\"v5_followup_60d\" id=\"v5_followup_60d\" value=\""+v5_followup_60d+"\"  onblur=\"autosave('v5_followup_60d');\"  oninput=\"v5_followup('v5_followup_60d'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v5_followup_4y\" id=\"v5_followup_4y\" value=\""+v5_followup_4y+"\" onblur=\"autosave('v5_followup_4y');\"  oninput=\"v5_followup('v5_followup_4y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v5_followup_9y\" id=\"v5_followup_9y\" value=\""+v5_followup_9y+"\" onblur=\"autosave('v5_followup_9y');\"  oninput=\"v5_followup('v5_followup_9y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v5_followup_14y\" id=\"v5_followup_14y\" value=\""+v5_followup_14y+"\"  onblur=\"autosave('v5_followup_14y');\"  oninput=\"v5_followup('v5_followup_14y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v5_followup_19y\" id=\"v5_followup_19y\" value=\""+v5_followup_19y+"\" onblur=\"autosave('v5_followup_19y');\"  oninput=\"v5_followup('v5_followup_19y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v5_followup_24y\" id=\"v5_followup_24y\" value=\""+v5_followup_24y+"\" onblur=\"autosave('v5_followup_24y');\"  oninput=\"v5_followup('v5_followup_24y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v5_followup_29y\" id=\"v5_followup_29y\" value=\""+v5_followup_29y+"\" onblur=\"autosave('v5_followup_29y');\"  oninput=\"v5_followup('v5_followup_29y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v5_followup_34y\" id=\"v5_followup_34y\" value=\""+v5_followup_34y+"\"  onblur=\"autosave('v5_followup_34y');\"  oninput=\"v5_followup('v5_followup_34y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v5_followup_39y\" id=\"v5_followup_39y\" value=\""+v5_followup_39y+"\"  onblur=\"autosave('v5_followup_39y');\"  oninput=\"v5_followup('v5_followup_39y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v5_followup_49y\" id=\"v5_followup_49y\" value=\""+v5_followup_49y+"\" onblur=\"autosave('v5_followup_49y');\"  oninput=\"v5_followup('v5_followup_49y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v5_followup_50y\" id=\"v5_followup_50y\" value=\""+v5_followup_50y+"\" onblur=\"autosave('v5_followup_50y');\"  oninput=\"v5_followup('v5_followup_50y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v5_followup_total\" id=\"v5_followup_total\" value=\""+v5_followup_total+"\" tabindex=\"-1\"  onblur=\"autosave('v5_followup_total');\"  oninput=\"v5_followup('v5_followup_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
            + "</table>";
    
                     
    v6 = "<table border=\"1px;\">"
        + "<tr border=\"1px;\">"
        + "<td colspan=\"13\" class=\"title\">6. Number of Males circumcised within the reporting period who <u>have not returned</u> for postoperative follow-up care (routine or emergent) within 14 days of surgery:</td>"
        + "</tr>"

        +""
        + "<tr border=\"1px;\">"
        + "<td>Age</td>"
        + "<td><60 Days</td>"
        + "<td>2 Months - 4 Years</td>"
        + "<td>5-9</td>"
        + "<td>10-14</td>"
        + "<td>15-19</td>"
        + "<td>20-24</td>"
        + "<td>25-29</td>"
        + "<td>30-34</td>"
        + "<td>35-39</td>"
        + "<td>40-49</td>"
        + "<td>50+</td>"
        + "<td>Totals</td>"
        + "</tr>"
        
        + "<tr>"
        + "<td></td>"
        + "<td><input type=\"text\" name=\"v6_nofollowup_60d\" id=\"v6_nofollowup_60d\" value=\""+v6_nofollowup_60d+"\"  onblur=\"autosave('v6_nofollowup_60d');\"  oninput=\"v6_nofollowup('v6_nofollowup_60d'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v6_nofollowup_4y\" id=\"v6_nofollowup_4y\" value=\""+v6_nofollowup_4y+"\"  onblur=\"autosave('v6_nofollowup_4y');\"  oninput=\"v6_nofollowup('v6_nofollowup_4y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v6_nofollowup_9y\" id=\"v6_nofollowup_9y\" value=\""+v6_nofollowup_9y+"\"  onblur=\"autosave('v6_nofollowup_9y');\"  oninput=\"v6_nofollowup('v6_nofollowup_9y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v6_nofollowup_14y\" id=\"v6_nofollowup_14y\" value=\""+v6_nofollowup_14y+"\"  onblur=\"autosave('v6_nofollowup_14y');\"  oninput=\"v6_nofollowup('v6_nofollowup_14y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v6_nofollowup_19y\" id=\"v6_nofollowup_19y\" value=\""+v6_nofollowup_19y+"\"  onblur=\"autosave('v6_nofollowup_19y');\"  oninput=\"v6_nofollowup('v6_nofollowup_19y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v6_nofollowup_24y\" id=\"v6_nofollowup_24y\" value=\""+v6_nofollowup_24y+"\"  onblur=\"autosave('v6_nofollowup_24y');\"  oninput=\"v6_nofollowup('v6_nofollowup_24y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v6_nofollowup_29y\" id=\"v6_nofollowup_29y\" value=\""+v6_nofollowup_29y+"\"  onblur=\"autosave('v6_nofollowup_29y');\"  oninput=\"v6_nofollowup('v6_nofollowup_29y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v6_nofollowup_34y\" id=\"v6_nofollowup_34y\" value=\""+v6_nofollowup_34y+"\"  onblur=\"autosave('v6_nofollowup_34y');\"  oninput=\"v6_nofollowup('v6_nofollowup_34y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v6_nofollowup_39y\" id=\"v6_nofollowup_39y\" value=\""+v6_nofollowup_39y+"\"  onblur=\"autosave('v6_nofollowup_39y');\"  oninput=\"v6_nofollowup('v6_nofollowup_39y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v6_nofollowup_49y\" id=\"v6_nofollowup_49y\" value=\""+v6_nofollowup_49y+"\"  onblur=\"autosave('v6_nofollowup_49y');\"  oninput=\"v6_nofollowup('v6_nofollowup_49y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v6_nofollowup_50y\" id=\"v6_nofollowup_50y\" value=\""+v6_nofollowup_50y+"\"  onblur=\"autosave('v6_nofollowup_50y');\"  oninput=\"v6_nofollowup('v6_nofollowup_50y'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"v6_nofollowup_total\" id=\"v6_nofollowup_total\" value=\""+v6_nofollowup_total+"\" tabindex=\"-1\"  onblur=\"autosave('v6_nofollowup_total');\"  oninput=\"v6_nofollowup('v6_nofollowup_total'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
            + "</table>";
    
      if (session.getAttribute("forms_holder") != null) {
     if (session.getAttribute("forms_holder").toString().contains("VMMC")) {
        output=enterdby+v1+"<br>"+v2+"<br>"+v3+"<br>"+v4+"<br>"+v5+"<br>"+v6;
        if(isLocked.equals("1")){
            output += "<div class='form-actions' style=\"text-align:center;\"><input type='submit' disabled class='btn red' value='Form Locked' name='validate' id='validate'/></div><span id='formstatus' style='display:none;'>" + validity + " </span><span id='rc' style='display:none;'>"+label+" </span><span id='ufs' style='display:none;'>"+unvalidatedLink+"</span>";
        }
        else{
            output += "<div class='form-actions' style=\"text-align:center;\"><input type='submit' class='btn blue' value='Save VMMC Data' name='validate' id='validate'/></div><span id='formstatus' style='display:none;'>" + validity + " </span><span id='rc' style='display:none;'>"+label+" </span><span id='ufs' style='display:none;'>"+unvalidatedLink+"</span>";
        }
        
        } else {
                    output = "<tr ><td colspan='4'><font color=\"red\" size=\"6px;\" style=\"margin-left: 0%;\"><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support  VMMC module.</font></td></tr>";
                }
            }
        if (session.getAttribute("facilityid") != null) {
            }
        else {
                output = "<tr ><td colspan='4'><font color=\"red\" size=\"6px;\" style=\"margin-left: 0%;\"><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support  VMMC module.</font></td></tr>";

            }
        
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
        Logger.getLogger(load_newVMMC.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(load_newVMMC.class.getName()).log(Level.SEVERE, null, ex);
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
