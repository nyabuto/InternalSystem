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
public class loadhts extends HttpServlet {
HttpSession session;
String tableid,id,SubPartnerID,Mois,Annee,sdp,couns_1_M,couns_1_F,couns_4_M,couns_4_F,couns_9_M,couns_9_F,couns_14_M,couns_14_F,couns_19_M,couns_19_F,couns_24_M,couns_24_F,couns_29_M,couns_29_F,couns_34_M,couns_34_F,couns_39_M,couns_39_F,couns_49_M,couns_49_F,couns_50_M,couns_50_F,couns_T_M,couns_T_F,couns_GT,tes_1_M,tes_1_F,tes_4_M,tes_4_F,tes_9_M,tes_9_F,tes_14_M,tes_14_F,tes_19_M,tes_19_F,tes_24_M,tes_24_F,tes_29_M,tes_29_F,tes_34_M,tes_34_F,tes_39_M,tes_39_F,tes_49_M,tes_49_F,tes_50_M,tes_50_F,tes_T_M,tes_T_F,tes_GT,tes_new_1_M,tes_new_1_F,tes_new_4_M,tes_new_4_F,tes_new_9_M,tes_new_9_F,tes_new_14_M,tes_new_14_F,tes_new_19_M,tes_new_19_F,tes_new_24_M,tes_new_24_F,tes_new_29_M,tes_new_29_F,tes_new_34_M,tes_new_34_F,tes_new_39_M,tes_new_39_F,tes_new_49_M,tes_new_49_F,tes_new_50_M,tes_new_50_F,tes_new_T_M,tes_new_T_F,tes_new_GT,res_1_M,res_1_F,res_4_M,res_4_F,res_9_M,res_9_F,res_14_M,res_14_F,res_19_M,res_19_F,res_24_M,res_24_F,res_29_M,res_29_F,res_34_M,res_34_F,res_39_M,res_39_F,res_49_M,res_49_F,res_50_M,res_50_F,res_T_M,res_T_F,res_GT,tes_new_res_1_M,tes_new_res_1_F,tes_new_res_4_M,tes_new_res_4_F,tes_new_res_9_M,tes_new_res_9_F,tes_new_res_14_M,tes_new_res_14_F,tes_new_res_19_M,tes_new_res_19_F,tes_new_res_24_M,tes_new_res_24_F,tes_new_res_29_M,tes_new_res_29_F,tes_new_res_34_M,tes_new_res_34_F,tes_new_res_39_M,tes_new_res_39_F,tes_new_res_49_M,tes_new_res_49_F,tes_new_res_50_M,tes_new_res_50_F,tes_new_res_T_M,tes_new_res_T_F,tes_new_res_GT,pos_1_M,pos_1_F,pos_4_M,pos_4_F,pos_9_M,pos_9_F,pos_14_M,pos_14_F,pos_19_M,pos_19_F,pos_24_M,pos_24_F,pos_29_M,pos_29_F,pos_34_M,pos_34_F,pos_39_M,pos_39_F,pos_49_M,pos_49_F,pos_50_M,pos_50_F,pos_T_M,pos_T_F,pos_GT,pos_tes_1_M,pos_tes_1_F,pos_tes_4_M,pos_tes_4_F,pos_tes_9_M,pos_tes_9_F,pos_tes_14_M,pos_tes_14_F,pos_tes_19_M,pos_tes_19_F,pos_tes_24_M,pos_tes_24_F,pos_tes_29_M,pos_tes_29_F,pos_tes_34_M,pos_tes_34_F,pos_tes_39_M,pos_tes_39_F,pos_tes_49_M,pos_tes_49_F,pos_tes_50_M,pos_tes_50_F,pos_tes_T_M,pos_tes_T_F,pos_tes_GT,ref_1_M,ref_1_F,ref_4_M,ref_4_F,ref_9_M,ref_9_F,ref_14_M,ref_14_F,ref_19_M,ref_19_F,ref_24_M,ref_24_F,ref_29_M,ref_29_F,ref_34_M,ref_34_F,ref_39_M,ref_39_F,ref_49_M,ref_49_F,ref_50_M,ref_50_F,ref_T_M,ref_T_F,ref_GT,tes_marps_1_M,tes_marps_1_F,tes_marps_4_M,tes_marps_4_F,tes_marps_9_M,tes_marps_9_F,tes_marps_14_M,tes_marps_14_F,tes_marps_19_M,tes_marps_19_F,tes_marps_24_M,tes_marps_24_F,tes_marps_29_M,tes_marps_29_F,tes_marps_34_M,tes_marps_34_F,tes_marps_39_M,tes_marps_39_F,tes_marps_49_M,tes_marps_49_F,tes_marps_50_M,tes_marps_50_F,tes_marps_T_M,tes_marps_T_F,tes_marps_GT,pos_marps_1_M,pos_marps_1_F,pos_marps_4_M,pos_marps_4_F,pos_marps_9_M,pos_marps_9_F,pos_marps_14_M,pos_marps_14_F,pos_marps_19_M,pos_marps_19_F,pos_marps_24_M,pos_marps_24_F,pos_marps_29_M,pos_marps_29_F,pos_marps_34_M,pos_marps_34_F,pos_marps_39_M,pos_marps_39_F,pos_marps_49_M,pos_marps_49_F,pos_marps_50_M,pos_marps_50_F,pos_marps_T_M,pos_marps_T_F,pos_marps_GT,tes_coup_1_M,tes_coup_1_F,tes_coup_4_M,tes_coup_4_F,tes_coup_9_M,tes_coup_9_F,tes_coup_14_M,tes_coup_14_F,tes_coup_19_M,tes_coup_19_F,tes_coup_24_M,tes_coup_24_F,tes_coup_29_M,tes_coup_29_F,tes_coup_34_M,tes_coup_34_F,tes_coup_39_M,tes_coup_39_F,tes_coup_49_M,tes_coup_49_F,tes_coup_50_M,tes_coup_50_F,tes_coup_T_M,tes_coup_T_F,tes_coup_GT,tes_coup_dis_1_M,tes_coup_dis_1_F,tes_coup_dis_4_M,tes_coup_dis_4_F,tes_coup_dis_9_M,tes_coup_dis_9_F,tes_coup_dis_14_M,tes_coup_dis_14_F,tes_coup_dis_19_M,tes_coup_dis_19_F,tes_coup_dis_24_M,tes_coup_dis_24_F,tes_coup_dis_29_M,tes_coup_dis_29_F,tes_coup_dis_34_M,tes_coup_dis_34_F,tes_coup_dis_39_M,tes_coup_dis_39_F,tes_coup_dis_49_M,tes_coup_dis_49_F,tes_coup_dis_50_M,tes_coup_dis_50_F,tes_coup_dis_T_M,tes_coup_dis_T_F,tes_coup_dis_GT,tk_negative1,tk_negative2,tk_positive1,tk_positive2,tk_invalid1,tk_invalid2,tk_wastage1,tk_wastage2,tk_total1,tk_total2,reportedby,datereported,remarks,user_id,timestamp,isValidated,yearmonth,updatedBy,updatedOn,isLocked,quarter;
String output,lock,enterdby,validity,Header;
String A,B,C,D,E,F;
String year,month, selectedsdp;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           dbConn conn = new dbConn();
           session = request.getSession();
        tableid=id=SubPartnerID=Mois=Annee=sdp=couns_1_M=couns_1_F=couns_4_M=couns_4_F=couns_9_M=couns_9_F=couns_14_M=couns_14_F=couns_19_M=couns_19_F=couns_24_M=couns_24_F=couns_29_M=couns_29_F=couns_34_M=couns_34_F=couns_39_M=couns_39_F=couns_49_M=couns_49_F=couns_50_M=couns_50_F=couns_T_M=couns_T_F=couns_GT=tes_1_M=tes_1_F=tes_4_M=tes_4_F=tes_9_M=tes_9_F=tes_14_M=tes_14_F=tes_19_M=tes_19_F=tes_24_M=tes_24_F=tes_29_M=tes_29_F=tes_34_M=tes_34_F=tes_39_M=tes_39_F=tes_49_M=tes_49_F=tes_50_M=tes_50_F=tes_T_M=tes_T_F=tes_GT=tes_new_1_M=tes_new_1_F=tes_new_4_M=tes_new_4_F=tes_new_9_M=tes_new_9_F=tes_new_14_M=tes_new_14_F=tes_new_19_M=tes_new_19_F=tes_new_24_M=tes_new_24_F=tes_new_29_M=tes_new_29_F=tes_new_34_M=tes_new_34_F=tes_new_39_M=tes_new_39_F=tes_new_49_M=tes_new_49_F=tes_new_50_M=tes_new_50_F=tes_new_T_M=tes_new_T_F=tes_new_GT=res_1_M=res_1_F=res_4_M=res_4_F=res_9_M=res_9_F=res_14_M=res_14_F=res_19_M=res_19_F=res_24_M=res_24_F=res_29_M=res_29_F=res_34_M=res_34_F=res_39_M=res_39_F=res_49_M=res_49_F=res_50_M=res_50_F=res_T_M=res_T_F=res_GT=tes_new_res_1_M=tes_new_res_1_F=tes_new_res_4_M=tes_new_res_4_F=tes_new_res_9_M=tes_new_res_9_F=tes_new_res_14_M=tes_new_res_14_F=tes_new_res_19_M=tes_new_res_19_F=tes_new_res_24_M=tes_new_res_24_F=tes_new_res_29_M=tes_new_res_29_F=tes_new_res_34_M=tes_new_res_34_F=tes_new_res_39_M=tes_new_res_39_F=tes_new_res_49_M=tes_new_res_49_F=tes_new_res_50_M=tes_new_res_50_F=tes_new_res_T_M=tes_new_res_T_F=tes_new_res_GT=pos_1_M=pos_1_F=pos_4_M=pos_4_F=pos_9_M=pos_9_F=pos_14_M=pos_14_F=pos_19_M=pos_19_F=pos_24_M=pos_24_F=pos_29_M=pos_29_F=pos_34_M=pos_34_F=pos_39_M=pos_39_F=pos_49_M=pos_49_F=pos_50_M=pos_50_F=pos_T_M=pos_T_F=pos_GT=pos_tes_1_M=pos_tes_1_F=pos_tes_4_M=pos_tes_4_F=pos_tes_9_M=pos_tes_9_F=pos_tes_14_M=pos_tes_14_F=pos_tes_19_M=pos_tes_19_F=pos_tes_24_M=pos_tes_24_F=pos_tes_29_M=pos_tes_29_F=pos_tes_34_M=pos_tes_34_F=pos_tes_39_M=pos_tes_39_F=pos_tes_49_M=pos_tes_49_F=pos_tes_50_M=pos_tes_50_F=pos_tes_T_M=pos_tes_T_F=pos_tes_GT=ref_1_M=ref_1_F=ref_4_M=ref_4_F=ref_9_M=ref_9_F=ref_14_M=ref_14_F=ref_19_M=ref_19_F=ref_24_M=ref_24_F=ref_29_M=ref_29_F=ref_34_M=ref_34_F=ref_39_M=ref_39_F=ref_49_M=ref_49_F=ref_50_M=ref_50_F=ref_T_M=ref_T_F=ref_GT=tes_marps_1_M=tes_marps_1_F=tes_marps_4_M=tes_marps_4_F=tes_marps_9_M=tes_marps_9_F=tes_marps_14_M=tes_marps_14_F=tes_marps_19_M=tes_marps_19_F=tes_marps_24_M=tes_marps_24_F=tes_marps_29_M=tes_marps_29_F=tes_marps_34_M=tes_marps_34_F=tes_marps_39_M=tes_marps_39_F=tes_marps_49_M=tes_marps_49_F=tes_marps_50_M=tes_marps_50_F=tes_marps_T_M=tes_marps_T_F=tes_marps_GT=pos_marps_1_M=pos_marps_1_F=pos_marps_4_M=pos_marps_4_F=pos_marps_9_M=pos_marps_9_F=pos_marps_14_M=pos_marps_14_F=pos_marps_19_M=pos_marps_19_F=pos_marps_24_M=pos_marps_24_F=pos_marps_29_M=pos_marps_29_F=pos_marps_34_M=pos_marps_34_F=pos_marps_39_M=pos_marps_39_F=pos_marps_49_M=pos_marps_49_F=pos_marps_50_M=pos_marps_50_F=pos_marps_T_M=pos_marps_T_F=pos_marps_GT=tes_coup_1_M=tes_coup_1_F=tes_coup_4_M=tes_coup_4_F=tes_coup_9_M=tes_coup_9_F=tes_coup_14_M=tes_coup_14_F=tes_coup_19_M=tes_coup_19_F=tes_coup_24_M=tes_coup_24_F=tes_coup_29_M=tes_coup_29_F=tes_coup_34_M=tes_coup_34_F=tes_coup_39_M=tes_coup_39_F=tes_coup_49_M=tes_coup_49_F=tes_coup_50_M=tes_coup_50_F=tes_coup_T_M=tes_coup_T_F=tes_coup_GT=tes_coup_dis_1_M=tes_coup_dis_1_F=tes_coup_dis_4_M=tes_coup_dis_4_F=tes_coup_dis_9_M=tes_coup_dis_9_F=tes_coup_dis_14_M=tes_coup_dis_14_F=tes_coup_dis_19_M=tes_coup_dis_19_F=tes_coup_dis_24_M=tes_coup_dis_24_F=tes_coup_dis_29_M=tes_coup_dis_29_F=tes_coup_dis_34_M=tes_coup_dis_34_F=tes_coup_dis_39_M=tes_coup_dis_39_F=tes_coup_dis_49_M=tes_coup_dis_49_F=tes_coup_dis_50_M=tes_coup_dis_50_F=tes_coup_dis_T_M=tes_coup_dis_T_F=tes_coup_dis_GT=tk_negative1=tk_negative2=tk_positive1=tk_positive2=tk_invalid1=tk_invalid2=tk_wastage1=tk_wastage2=tk_total1=tk_total2=reportedby=datereported=remarks=user_id=timestamp=isValidated=yearmonth=updatedBy=updatedOn=isLocked=quarter=selectedsdp="";
//   tableid="2018_10_331";
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
        
int htsdone=0;
int htsundone=0;
int htsvalid=0;
int facilssupporting=0;
String distid="";

if(session.getAttribute("subcountyid")!=null){
distid=session.getAttribute("subcountyid").toString();
}


String htscounter="SELECT 1 FROM hts join subpartnera on hts.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and (tes_GT is not null || tes_GT!='') and sdp='"+selectedsdp+"'  ";
 conn.rs1 = conn.st1.executeQuery(htscounter);
 while(conn.rs1.next()){
 htsdone++;
  }
 
  String htsvalidatedcounter1="SELECT 1 FROM hts join subpartnera on hts.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and isValidated='0'  and sdp='"+selectedsdp+"' ";
 conn.rs1 = conn.st1.executeQuery(htsvalidatedcounter1);
 while(conn.rs1.next()){
 htsvalid++;
  }
 
            System.out.println(htscounter);
 
 String htscounter1="SELECT 1 FROM hts join subpartnera on hts.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and  isValidated='0' and sdp='"+selectedsdp+"' ";
 conn.rs1 = conn.st1.executeQuery(htscounter1);
 while(conn.rs1.next()){
 htsundone++;
  }
 
 
 
 
 String countpmctfacility="Select * from subpartnera where HTC ='1' and  DistrictID='"+distid+"'";
// String countfacility="Select * from subpartnera where FP='1' || PMTCT ='1' || Maternity='1' || HTC='1' ";
 conn.rs1 = conn.st1.executeQuery(countpmctfacility);
 while(conn.rs1.next()){
 facilssupporting++;
 }
 
String validated="&nbsp &nbsp Validated Form(s): <b>"+htsvalid+" </b>";
 String unvalidated="&nbsp &nbsp Unvalidated Form (s) <font color='black'><b>"+htsundone+"</b></font>";
 
 
  String unvalidatedLink="";int counter=0;
     if(htsundone>0){
     String getUnvalidated="SELECT hts.SubPartnerID,subpartnera.SubPartnerNom FROM hts JOIN subpartnera ON hts.SubPartnerID=subpartnera.SubPartnerID WHERE subpartnera.DistrictID='"+distid+"' AND hts.Mois='"+month+"' AND hts.Annee='"+year+"' AND hts.isValidated='0'  and sdp='"+selectedsdp+"'";
     conn.rs=conn.st.executeQuery(getUnvalidated);
     while(conn.rs.next()){
         counter++;
//     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=Form731.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=hts.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
                    }
    }
     
   if(counter>0){
    unvalidated="<button class='btn btn-primary btn-lg' data-target='#unvalidatedModal' style='width:auto; height:auto;' data-toggle='modal' type='button'> Unvalidated Form (s) <span class='badge badge-important'><b>"+htsundone+"</b></span></button>";
 
   }  
 
 String label="Record counter <font color='white'><b>"+htsdone+"<b></font>  out of <b>"+facilssupporting+"</b>"+validated+unvalidated;
 
         enterdby="";   
            String check_data="SELECT * FROM hts WHERE tableid=? ";
            conn.pst=conn.conn.prepareStatement(check_data);
            conn.pst.setString(1, tableid);
            conn.rs=conn.pst.executeQuery();
            if(conn.rs.next()){
                    tableid= conn.rs.getString("tableid");
                    SubPartnerID= conn.rs.getString("SubPartnerID");
                    Annee= conn.rs.getString("Annee");
                    Mois= conn.rs.getString("Mois");
                    
                    if(conn.rs.getString("couns_1_M")!=null){couns_1_M= conn.rs.getString("couns_1_M");}
if(conn.rs.getString("couns_1_F")!=null){couns_1_F= conn.rs.getString("couns_1_F");}
if(conn.rs.getString("couns_4_M")!=null){couns_4_M= conn.rs.getString("couns_4_M");}
if(conn.rs.getString("couns_4_F")!=null){couns_4_F= conn.rs.getString("couns_4_F");}
if(conn.rs.getString("couns_9_M")!=null){couns_9_M= conn.rs.getString("couns_9_M");}
if(conn.rs.getString("couns_9_F")!=null){couns_9_F= conn.rs.getString("couns_9_F");}
if(conn.rs.getString("couns_14_M")!=null){couns_14_M= conn.rs.getString("couns_14_M");}
if(conn.rs.getString("couns_14_F")!=null){couns_14_F= conn.rs.getString("couns_14_F");}
if(conn.rs.getString("couns_19_M")!=null){couns_19_M= conn.rs.getString("couns_19_M");}
if(conn.rs.getString("couns_19_F")!=null){couns_19_F= conn.rs.getString("couns_19_F");}
if(conn.rs.getString("couns_24_M")!=null){couns_24_M= conn.rs.getString("couns_24_M");}
if(conn.rs.getString("couns_24_F")!=null){couns_24_F= conn.rs.getString("couns_24_F");}
if(conn.rs.getString("couns_29_M")!=null){couns_29_M= conn.rs.getString("couns_29_M");}
if(conn.rs.getString("couns_29_F")!=null){couns_29_F= conn.rs.getString("couns_29_F");}
if(conn.rs.getString("couns_34_M")!=null){couns_34_M= conn.rs.getString("couns_34_M");}
if(conn.rs.getString("couns_34_F")!=null){couns_34_F= conn.rs.getString("couns_34_F");}
if(conn.rs.getString("couns_39_M")!=null){couns_39_M= conn.rs.getString("couns_39_M");}
if(conn.rs.getString("couns_39_F")!=null){couns_39_F= conn.rs.getString("couns_39_F");}
if(conn.rs.getString("couns_49_M")!=null){couns_49_M= conn.rs.getString("couns_49_M");}
if(conn.rs.getString("couns_49_F")!=null){couns_49_F= conn.rs.getString("couns_49_F");}
if(conn.rs.getString("couns_50_M")!=null){couns_50_M= conn.rs.getString("couns_50_M");}
if(conn.rs.getString("couns_50_F")!=null){couns_50_F= conn.rs.getString("couns_50_F");}
if(conn.rs.getString("couns_T_M")!=null){couns_T_M= conn.rs.getString("couns_T_M");}
if(conn.rs.getString("couns_T_F")!=null){couns_T_F= conn.rs.getString("couns_T_F");}
if(conn.rs.getString("couns_GT")!=null){couns_GT= conn.rs.getString("couns_GT");}
if(conn.rs.getString("tes_1_M")!=null){tes_1_M= conn.rs.getString("tes_1_M");}
if(conn.rs.getString("tes_1_F")!=null){tes_1_F= conn.rs.getString("tes_1_F");}
if(conn.rs.getString("tes_4_M")!=null){tes_4_M= conn.rs.getString("tes_4_M");}
if(conn.rs.getString("tes_4_F")!=null){tes_4_F= conn.rs.getString("tes_4_F");}
if(conn.rs.getString("tes_9_M")!=null){tes_9_M= conn.rs.getString("tes_9_M");}
if(conn.rs.getString("tes_9_F")!=null){tes_9_F= conn.rs.getString("tes_9_F");}
if(conn.rs.getString("tes_14_M")!=null){tes_14_M= conn.rs.getString("tes_14_M");}
if(conn.rs.getString("tes_14_F")!=null){tes_14_F= conn.rs.getString("tes_14_F");}
if(conn.rs.getString("tes_19_M")!=null){tes_19_M= conn.rs.getString("tes_19_M");}
if(conn.rs.getString("tes_19_F")!=null){tes_19_F= conn.rs.getString("tes_19_F");}
if(conn.rs.getString("tes_24_M")!=null){tes_24_M= conn.rs.getString("tes_24_M");}
if(conn.rs.getString("tes_24_F")!=null){tes_24_F= conn.rs.getString("tes_24_F");}
if(conn.rs.getString("tes_29_M")!=null){tes_29_M= conn.rs.getString("tes_29_M");}
if(conn.rs.getString("tes_29_F")!=null){tes_29_F= conn.rs.getString("tes_29_F");}
if(conn.rs.getString("tes_34_M")!=null){tes_34_M= conn.rs.getString("tes_34_M");}
if(conn.rs.getString("tes_34_F")!=null){tes_34_F= conn.rs.getString("tes_34_F");}
if(conn.rs.getString("tes_39_M")!=null){tes_39_M= conn.rs.getString("tes_39_M");}
if(conn.rs.getString("tes_39_F")!=null){tes_39_F= conn.rs.getString("tes_39_F");}
if(conn.rs.getString("tes_49_M")!=null){tes_49_M= conn.rs.getString("tes_49_M");}
if(conn.rs.getString("tes_49_F")!=null){tes_49_F= conn.rs.getString("tes_49_F");}
if(conn.rs.getString("tes_50_M")!=null){tes_50_M= conn.rs.getString("tes_50_M");}
if(conn.rs.getString("tes_50_F")!=null){tes_50_F= conn.rs.getString("tes_50_F");}
if(conn.rs.getString("tes_T_M")!=null){tes_T_M= conn.rs.getString("tes_T_M");}
if(conn.rs.getString("tes_T_F")!=null){tes_T_F= conn.rs.getString("tes_T_F");}
if(conn.rs.getString("tes_GT")!=null){tes_GT= conn.rs.getString("tes_GT");}
if(conn.rs.getString("tes_new_1_M")!=null){tes_new_1_M= conn.rs.getString("tes_new_1_M");}
if(conn.rs.getString("tes_new_1_F")!=null){tes_new_1_F= conn.rs.getString("tes_new_1_F");}
if(conn.rs.getString("tes_new_4_M")!=null){tes_new_4_M= conn.rs.getString("tes_new_4_M");}
if(conn.rs.getString("tes_new_4_F")!=null){tes_new_4_F= conn.rs.getString("tes_new_4_F");}
if(conn.rs.getString("tes_new_9_M")!=null){tes_new_9_M= conn.rs.getString("tes_new_9_M");}
if(conn.rs.getString("tes_new_9_F")!=null){tes_new_9_F= conn.rs.getString("tes_new_9_F");}
if(conn.rs.getString("tes_new_14_M")!=null){tes_new_14_M= conn.rs.getString("tes_new_14_M");}
if(conn.rs.getString("tes_new_14_F")!=null){tes_new_14_F= conn.rs.getString("tes_new_14_F");}
if(conn.rs.getString("tes_new_19_M")!=null){tes_new_19_M= conn.rs.getString("tes_new_19_M");}
if(conn.rs.getString("tes_new_19_F")!=null){tes_new_19_F= conn.rs.getString("tes_new_19_F");}
if(conn.rs.getString("tes_new_24_M")!=null){tes_new_24_M= conn.rs.getString("tes_new_24_M");}
if(conn.rs.getString("tes_new_24_F")!=null){tes_new_24_F= conn.rs.getString("tes_new_24_F");}
if(conn.rs.getString("tes_new_29_M")!=null){tes_new_29_M= conn.rs.getString("tes_new_29_M");}
if(conn.rs.getString("tes_new_29_F")!=null){tes_new_29_F= conn.rs.getString("tes_new_29_F");}
if(conn.rs.getString("tes_new_34_M")!=null){tes_new_34_M= conn.rs.getString("tes_new_34_M");}
if(conn.rs.getString("tes_new_34_F")!=null){tes_new_34_F= conn.rs.getString("tes_new_34_F");}
if(conn.rs.getString("tes_new_39_M")!=null){tes_new_39_M= conn.rs.getString("tes_new_39_M");}
if(conn.rs.getString("tes_new_39_F")!=null){tes_new_39_F= conn.rs.getString("tes_new_39_F");}
if(conn.rs.getString("tes_new_49_M")!=null){tes_new_49_M= conn.rs.getString("tes_new_49_M");}
if(conn.rs.getString("tes_new_49_F")!=null){tes_new_49_F= conn.rs.getString("tes_new_49_F");}
if(conn.rs.getString("tes_new_50_M")!=null){tes_new_50_M= conn.rs.getString("tes_new_50_M");}
if(conn.rs.getString("tes_new_50_F")!=null){tes_new_50_F= conn.rs.getString("tes_new_50_F");}
if(conn.rs.getString("tes_new_T_M")!=null){tes_new_T_M= conn.rs.getString("tes_new_T_M");}
if(conn.rs.getString("tes_new_T_F")!=null){tes_new_T_F= conn.rs.getString("tes_new_T_F");}
if(conn.rs.getString("tes_new_GT")!=null){tes_new_GT= conn.rs.getString("tes_new_GT");}
if(conn.rs.getString("res_1_M")!=null){res_1_M= conn.rs.getString("res_1_M");}
if(conn.rs.getString("res_1_F")!=null){res_1_F= conn.rs.getString("res_1_F");}
if(conn.rs.getString("res_4_M")!=null){res_4_M= conn.rs.getString("res_4_M");}
if(conn.rs.getString("res_4_F")!=null){res_4_F= conn.rs.getString("res_4_F");}
if(conn.rs.getString("res_9_M")!=null){res_9_M= conn.rs.getString("res_9_M");}
if(conn.rs.getString("res_9_F")!=null){res_9_F= conn.rs.getString("res_9_F");}
if(conn.rs.getString("res_14_M")!=null){res_14_M= conn.rs.getString("res_14_M");}
if(conn.rs.getString("res_14_F")!=null){res_14_F= conn.rs.getString("res_14_F");}
if(conn.rs.getString("res_19_M")!=null){res_19_M= conn.rs.getString("res_19_M");}
if(conn.rs.getString("res_19_F")!=null){res_19_F= conn.rs.getString("res_19_F");}
if(conn.rs.getString("res_24_M")!=null){res_24_M= conn.rs.getString("res_24_M");}
if(conn.rs.getString("res_24_F")!=null){res_24_F= conn.rs.getString("res_24_F");}
if(conn.rs.getString("res_29_M")!=null){res_29_M= conn.rs.getString("res_29_M");}
if(conn.rs.getString("res_29_F")!=null){res_29_F= conn.rs.getString("res_29_F");}
if(conn.rs.getString("res_34_M")!=null){res_34_M= conn.rs.getString("res_34_M");}
if(conn.rs.getString("res_34_F")!=null){res_34_F= conn.rs.getString("res_34_F");}
if(conn.rs.getString("res_39_M")!=null){res_39_M= conn.rs.getString("res_39_M");}
if(conn.rs.getString("res_39_F")!=null){res_39_F= conn.rs.getString("res_39_F");}
if(conn.rs.getString("res_49_M")!=null){res_49_M= conn.rs.getString("res_49_M");}
if(conn.rs.getString("res_49_F")!=null){res_49_F= conn.rs.getString("res_49_F");}
if(conn.rs.getString("res_50_M")!=null){res_50_M= conn.rs.getString("res_50_M");}
if(conn.rs.getString("res_50_F")!=null){res_50_F= conn.rs.getString("res_50_F");}
if(conn.rs.getString("res_T_M")!=null){res_T_M= conn.rs.getString("res_T_M");}
if(conn.rs.getString("res_T_F")!=null){res_T_F= conn.rs.getString("res_T_F");}
if(conn.rs.getString("res_GT")!=null){res_GT= conn.rs.getString("res_GT");}
if(conn.rs.getString("tes_new_res_1_M")!=null){tes_new_res_1_M= conn.rs.getString("tes_new_res_1_M");}
if(conn.rs.getString("tes_new_res_1_F")!=null){tes_new_res_1_F= conn.rs.getString("tes_new_res_1_F");}
if(conn.rs.getString("tes_new_res_4_M")!=null){tes_new_res_4_M= conn.rs.getString("tes_new_res_4_M");}
if(conn.rs.getString("tes_new_res_4_F")!=null){tes_new_res_4_F= conn.rs.getString("tes_new_res_4_F");}
if(conn.rs.getString("tes_new_res_9_M")!=null){tes_new_res_9_M= conn.rs.getString("tes_new_res_9_M");}
if(conn.rs.getString("tes_new_res_9_F")!=null){tes_new_res_9_F= conn.rs.getString("tes_new_res_9_F");}
if(conn.rs.getString("tes_new_res_14_M")!=null){tes_new_res_14_M= conn.rs.getString("tes_new_res_14_M");}
if(conn.rs.getString("tes_new_res_14_F")!=null){tes_new_res_14_F= conn.rs.getString("tes_new_res_14_F");}
if(conn.rs.getString("tes_new_res_19_M")!=null){tes_new_res_19_M= conn.rs.getString("tes_new_res_19_M");}
if(conn.rs.getString("tes_new_res_19_F")!=null){tes_new_res_19_F= conn.rs.getString("tes_new_res_19_F");}
if(conn.rs.getString("tes_new_res_24_M")!=null){tes_new_res_24_M= conn.rs.getString("tes_new_res_24_M");}
if(conn.rs.getString("tes_new_res_24_F")!=null){tes_new_res_24_F= conn.rs.getString("tes_new_res_24_F");}
if(conn.rs.getString("tes_new_res_29_M")!=null){tes_new_res_29_M= conn.rs.getString("tes_new_res_29_M");}
if(conn.rs.getString("tes_new_res_29_F")!=null){tes_new_res_29_F= conn.rs.getString("tes_new_res_29_F");}
if(conn.rs.getString("tes_new_res_34_M")!=null){tes_new_res_34_M= conn.rs.getString("tes_new_res_34_M");}
if(conn.rs.getString("tes_new_res_34_F")!=null){tes_new_res_34_F= conn.rs.getString("tes_new_res_34_F");}
if(conn.rs.getString("tes_new_res_39_M")!=null){tes_new_res_39_M= conn.rs.getString("tes_new_res_39_M");}
if(conn.rs.getString("tes_new_res_39_F")!=null){tes_new_res_39_F= conn.rs.getString("tes_new_res_39_F");}
if(conn.rs.getString("tes_new_res_49_M")!=null){tes_new_res_49_M= conn.rs.getString("tes_new_res_49_M");}
if(conn.rs.getString("tes_new_res_49_F")!=null){tes_new_res_49_F= conn.rs.getString("tes_new_res_49_F");}
if(conn.rs.getString("tes_new_res_50_M")!=null){tes_new_res_50_M= conn.rs.getString("tes_new_res_50_M");}
if(conn.rs.getString("tes_new_res_50_F")!=null){tes_new_res_50_F= conn.rs.getString("tes_new_res_50_F");}
if(conn.rs.getString("tes_new_res_T_M")!=null){tes_new_res_T_M= conn.rs.getString("tes_new_res_T_M");}
if(conn.rs.getString("tes_new_res_T_F")!=null){tes_new_res_T_F= conn.rs.getString("tes_new_res_T_F");}
if(conn.rs.getString("tes_new_res_GT")!=null){tes_new_res_GT= conn.rs.getString("tes_new_res_GT");}
if(conn.rs.getString("pos_1_M")!=null){pos_1_M= conn.rs.getString("pos_1_M");}
if(conn.rs.getString("pos_1_F")!=null){pos_1_F= conn.rs.getString("pos_1_F");}
if(conn.rs.getString("pos_4_M")!=null){pos_4_M= conn.rs.getString("pos_4_M");}
if(conn.rs.getString("pos_4_F")!=null){pos_4_F= conn.rs.getString("pos_4_F");}
if(conn.rs.getString("pos_9_M")!=null){pos_9_M= conn.rs.getString("pos_9_M");}
if(conn.rs.getString("pos_9_F")!=null){pos_9_F= conn.rs.getString("pos_9_F");}
if(conn.rs.getString("pos_14_M")!=null){pos_14_M= conn.rs.getString("pos_14_M");}
if(conn.rs.getString("pos_14_F")!=null){pos_14_F= conn.rs.getString("pos_14_F");}
if(conn.rs.getString("pos_19_M")!=null){pos_19_M= conn.rs.getString("pos_19_M");}
if(conn.rs.getString("pos_19_F")!=null){pos_19_F= conn.rs.getString("pos_19_F");}
if(conn.rs.getString("pos_24_M")!=null){pos_24_M= conn.rs.getString("pos_24_M");}
if(conn.rs.getString("pos_24_F")!=null){pos_24_F= conn.rs.getString("pos_24_F");}
if(conn.rs.getString("pos_29_M")!=null){pos_29_M= conn.rs.getString("pos_29_M");}
if(conn.rs.getString("pos_29_F")!=null){pos_29_F= conn.rs.getString("pos_29_F");}
if(conn.rs.getString("pos_34_M")!=null){pos_34_M= conn.rs.getString("pos_34_M");}
if(conn.rs.getString("pos_34_F")!=null){pos_34_F= conn.rs.getString("pos_34_F");}
if(conn.rs.getString("pos_39_M")!=null){pos_39_M= conn.rs.getString("pos_39_M");}
if(conn.rs.getString("pos_39_F")!=null){pos_39_F= conn.rs.getString("pos_39_F");}
if(conn.rs.getString("pos_49_M")!=null){pos_49_M= conn.rs.getString("pos_49_M");}
if(conn.rs.getString("pos_49_F")!=null){pos_49_F= conn.rs.getString("pos_49_F");}
if(conn.rs.getString("pos_50_M")!=null){pos_50_M= conn.rs.getString("pos_50_M");}
if(conn.rs.getString("pos_50_F")!=null){pos_50_F= conn.rs.getString("pos_50_F");}
if(conn.rs.getString("pos_T_M")!=null){pos_T_M= conn.rs.getString("pos_T_M");}
if(conn.rs.getString("pos_T_F")!=null){pos_T_F= conn.rs.getString("pos_T_F");}
if(conn.rs.getString("pos_GT")!=null){pos_GT= conn.rs.getString("pos_GT");}
if(conn.rs.getString("pos_tes_1_M")!=null){pos_tes_1_M= conn.rs.getString("pos_tes_1_M");}
if(conn.rs.getString("pos_tes_1_F")!=null){pos_tes_1_F= conn.rs.getString("pos_tes_1_F");}
if(conn.rs.getString("pos_tes_4_M")!=null){pos_tes_4_M= conn.rs.getString("pos_tes_4_M");}
if(conn.rs.getString("pos_tes_4_F")!=null){pos_tes_4_F= conn.rs.getString("pos_tes_4_F");}
if(conn.rs.getString("pos_tes_9_M")!=null){pos_tes_9_M= conn.rs.getString("pos_tes_9_M");}
if(conn.rs.getString("pos_tes_9_F")!=null){pos_tes_9_F= conn.rs.getString("pos_tes_9_F");}
if(conn.rs.getString("pos_tes_14_M")!=null){pos_tes_14_M= conn.rs.getString("pos_tes_14_M");}
if(conn.rs.getString("pos_tes_14_F")!=null){pos_tes_14_F= conn.rs.getString("pos_tes_14_F");}
if(conn.rs.getString("pos_tes_19_M")!=null){pos_tes_19_M= conn.rs.getString("pos_tes_19_M");}
if(conn.rs.getString("pos_tes_19_F")!=null){pos_tes_19_F= conn.rs.getString("pos_tes_19_F");}
if(conn.rs.getString("pos_tes_24_M")!=null){pos_tes_24_M= conn.rs.getString("pos_tes_24_M");}
if(conn.rs.getString("pos_tes_24_F")!=null){pos_tes_24_F= conn.rs.getString("pos_tes_24_F");}
if(conn.rs.getString("pos_tes_29_M")!=null){pos_tes_29_M= conn.rs.getString("pos_tes_29_M");}
if(conn.rs.getString("pos_tes_29_F")!=null){pos_tes_29_F= conn.rs.getString("pos_tes_29_F");}
if(conn.rs.getString("pos_tes_34_M")!=null){pos_tes_34_M= conn.rs.getString("pos_tes_34_M");}
if(conn.rs.getString("pos_tes_34_F")!=null){pos_tes_34_F= conn.rs.getString("pos_tes_34_F");}
if(conn.rs.getString("pos_tes_39_M")!=null){pos_tes_39_M= conn.rs.getString("pos_tes_39_M");}
if(conn.rs.getString("pos_tes_39_F")!=null){pos_tes_39_F= conn.rs.getString("pos_tes_39_F");}
if(conn.rs.getString("pos_tes_49_M")!=null){pos_tes_49_M= conn.rs.getString("pos_tes_49_M");}
if(conn.rs.getString("pos_tes_49_F")!=null){pos_tes_49_F= conn.rs.getString("pos_tes_49_F");}
if(conn.rs.getString("pos_tes_50_M")!=null){pos_tes_50_M= conn.rs.getString("pos_tes_50_M");}
if(conn.rs.getString("pos_tes_50_F")!=null){pos_tes_50_F= conn.rs.getString("pos_tes_50_F");}
if(conn.rs.getString("pos_tes_T_M")!=null){pos_tes_T_M= conn.rs.getString("pos_tes_T_M");}
if(conn.rs.getString("pos_tes_T_F")!=null){pos_tes_T_F= conn.rs.getString("pos_tes_T_F");}
if(conn.rs.getString("pos_tes_GT")!=null){pos_tes_GT= conn.rs.getString("pos_tes_GT");}
if(conn.rs.getString("ref_1_M")!=null){ref_1_M= conn.rs.getString("ref_1_M");}
if(conn.rs.getString("ref_1_F")!=null){ref_1_F= conn.rs.getString("ref_1_F");}
if(conn.rs.getString("ref_4_M")!=null){ref_4_M= conn.rs.getString("ref_4_M");}
if(conn.rs.getString("ref_4_F")!=null){ref_4_F= conn.rs.getString("ref_4_F");}
if(conn.rs.getString("ref_9_M")!=null){ref_9_M= conn.rs.getString("ref_9_M");}
if(conn.rs.getString("ref_9_F")!=null){ref_9_F= conn.rs.getString("ref_9_F");}
if(conn.rs.getString("ref_14_M")!=null){ref_14_M= conn.rs.getString("ref_14_M");}
if(conn.rs.getString("ref_14_F")!=null){ref_14_F= conn.rs.getString("ref_14_F");}
if(conn.rs.getString("ref_19_M")!=null){ref_19_M= conn.rs.getString("ref_19_M");}
if(conn.rs.getString("ref_19_F")!=null){ref_19_F= conn.rs.getString("ref_19_F");}
if(conn.rs.getString("ref_24_M")!=null){ref_24_M= conn.rs.getString("ref_24_M");}
if(conn.rs.getString("ref_24_F")!=null){ref_24_F= conn.rs.getString("ref_24_F");}
if(conn.rs.getString("ref_29_M")!=null){ref_29_M= conn.rs.getString("ref_29_M");}
if(conn.rs.getString("ref_29_F")!=null){ref_29_F= conn.rs.getString("ref_29_F");}
if(conn.rs.getString("ref_34_M")!=null){ref_34_M= conn.rs.getString("ref_34_M");}
if(conn.rs.getString("ref_34_F")!=null){ref_34_F= conn.rs.getString("ref_34_F");}
if(conn.rs.getString("ref_39_M")!=null){ref_39_M= conn.rs.getString("ref_39_M");}
if(conn.rs.getString("ref_39_F")!=null){ref_39_F= conn.rs.getString("ref_39_F");}
if(conn.rs.getString("ref_49_M")!=null){ref_49_M= conn.rs.getString("ref_49_M");}
if(conn.rs.getString("ref_49_F")!=null){ref_49_F= conn.rs.getString("ref_49_F");}
if(conn.rs.getString("ref_50_M")!=null){ref_50_M= conn.rs.getString("ref_50_M");}
if(conn.rs.getString("ref_50_F")!=null){ref_50_F= conn.rs.getString("ref_50_F");}
if(conn.rs.getString("ref_T_M")!=null){ref_T_M= conn.rs.getString("ref_T_M");}
if(conn.rs.getString("ref_T_F")!=null){ref_T_F= conn.rs.getString("ref_T_F");}
if(conn.rs.getString("ref_GT")!=null){ref_GT= conn.rs.getString("ref_GT");}
if(conn.rs.getString("tes_marps_1_M")!=null){tes_marps_1_M= conn.rs.getString("tes_marps_1_M");}
if(conn.rs.getString("tes_marps_1_F")!=null){tes_marps_1_F= conn.rs.getString("tes_marps_1_F");}
if(conn.rs.getString("tes_marps_4_M")!=null){tes_marps_4_M= conn.rs.getString("tes_marps_4_M");}
if(conn.rs.getString("tes_marps_4_F")!=null){tes_marps_4_F= conn.rs.getString("tes_marps_4_F");}
if(conn.rs.getString("tes_marps_9_M")!=null){tes_marps_9_M= conn.rs.getString("tes_marps_9_M");}
if(conn.rs.getString("tes_marps_9_F")!=null){tes_marps_9_F= conn.rs.getString("tes_marps_9_F");}
if(conn.rs.getString("tes_marps_14_M")!=null){tes_marps_14_M= conn.rs.getString("tes_marps_14_M");}
if(conn.rs.getString("tes_marps_14_F")!=null){tes_marps_14_F= conn.rs.getString("tes_marps_14_F");}
if(conn.rs.getString("tes_marps_19_M")!=null){tes_marps_19_M= conn.rs.getString("tes_marps_19_M");}
if(conn.rs.getString("tes_marps_19_F")!=null){tes_marps_19_F= conn.rs.getString("tes_marps_19_F");}
if(conn.rs.getString("tes_marps_24_M")!=null){tes_marps_24_M= conn.rs.getString("tes_marps_24_M");}
if(conn.rs.getString("tes_marps_24_F")!=null){tes_marps_24_F= conn.rs.getString("tes_marps_24_F");}
if(conn.rs.getString("tes_marps_29_M")!=null){tes_marps_29_M= conn.rs.getString("tes_marps_29_M");}
if(conn.rs.getString("tes_marps_29_F")!=null){tes_marps_29_F= conn.rs.getString("tes_marps_29_F");}
if(conn.rs.getString("tes_marps_34_M")!=null){tes_marps_34_M= conn.rs.getString("tes_marps_34_M");}
if(conn.rs.getString("tes_marps_34_F")!=null){tes_marps_34_F= conn.rs.getString("tes_marps_34_F");}
if(conn.rs.getString("tes_marps_39_M")!=null){tes_marps_39_M= conn.rs.getString("tes_marps_39_M");}
if(conn.rs.getString("tes_marps_39_F")!=null){tes_marps_39_F= conn.rs.getString("tes_marps_39_F");}
if(conn.rs.getString("tes_marps_49_M")!=null){tes_marps_49_M= conn.rs.getString("tes_marps_49_M");}
if(conn.rs.getString("tes_marps_49_F")!=null){tes_marps_49_F= conn.rs.getString("tes_marps_49_F");}
if(conn.rs.getString("tes_marps_50_M")!=null){tes_marps_50_M= conn.rs.getString("tes_marps_50_M");}
if(conn.rs.getString("tes_marps_50_F")!=null){tes_marps_50_F= conn.rs.getString("tes_marps_50_F");}
if(conn.rs.getString("tes_marps_T_M")!=null){tes_marps_T_M= conn.rs.getString("tes_marps_T_M");}
if(conn.rs.getString("tes_marps_T_F")!=null){tes_marps_T_F= conn.rs.getString("tes_marps_T_F");}
if(conn.rs.getString("tes_marps_GT")!=null){tes_marps_GT= conn.rs.getString("tes_marps_GT");}
if(conn.rs.getString("pos_marps_1_M")!=null){pos_marps_1_M= conn.rs.getString("pos_marps_1_M");}
if(conn.rs.getString("pos_marps_1_F")!=null){pos_marps_1_F= conn.rs.getString("pos_marps_1_F");}
if(conn.rs.getString("pos_marps_4_M")!=null){pos_marps_4_M= conn.rs.getString("pos_marps_4_M");}
if(conn.rs.getString("pos_marps_4_F")!=null){pos_marps_4_F= conn.rs.getString("pos_marps_4_F");}
if(conn.rs.getString("pos_marps_9_M")!=null){pos_marps_9_M= conn.rs.getString("pos_marps_9_M");}
if(conn.rs.getString("pos_marps_9_F")!=null){pos_marps_9_F= conn.rs.getString("pos_marps_9_F");}
if(conn.rs.getString("pos_marps_14_M")!=null){pos_marps_14_M= conn.rs.getString("pos_marps_14_M");}
if(conn.rs.getString("pos_marps_14_F")!=null){pos_marps_14_F= conn.rs.getString("pos_marps_14_F");}
if(conn.rs.getString("pos_marps_19_M")!=null){pos_marps_19_M= conn.rs.getString("pos_marps_19_M");}
if(conn.rs.getString("pos_marps_19_F")!=null){pos_marps_19_F= conn.rs.getString("pos_marps_19_F");}
if(conn.rs.getString("pos_marps_24_M")!=null){pos_marps_24_M= conn.rs.getString("pos_marps_24_M");}
if(conn.rs.getString("pos_marps_24_F")!=null){pos_marps_24_F= conn.rs.getString("pos_marps_24_F");}
if(conn.rs.getString("pos_marps_29_M")!=null){pos_marps_29_M= conn.rs.getString("pos_marps_29_M");}
if(conn.rs.getString("pos_marps_29_F")!=null){pos_marps_29_F= conn.rs.getString("pos_marps_29_F");}
if(conn.rs.getString("pos_marps_34_M")!=null){pos_marps_34_M= conn.rs.getString("pos_marps_34_M");}
if(conn.rs.getString("pos_marps_34_F")!=null){pos_marps_34_F= conn.rs.getString("pos_marps_34_F");}
if(conn.rs.getString("pos_marps_39_M")!=null){pos_marps_39_M= conn.rs.getString("pos_marps_39_M");}
if(conn.rs.getString("pos_marps_39_F")!=null){pos_marps_39_F= conn.rs.getString("pos_marps_39_F");}
if(conn.rs.getString("pos_marps_49_M")!=null){pos_marps_49_M= conn.rs.getString("pos_marps_49_M");}
if(conn.rs.getString("pos_marps_49_F")!=null){pos_marps_49_F= conn.rs.getString("pos_marps_49_F");}
if(conn.rs.getString("pos_marps_50_M")!=null){pos_marps_50_M= conn.rs.getString("pos_marps_50_M");}
if(conn.rs.getString("pos_marps_50_F")!=null){pos_marps_50_F= conn.rs.getString("pos_marps_50_F");}
if(conn.rs.getString("pos_marps_T_M")!=null){pos_marps_T_M= conn.rs.getString("pos_marps_T_M");}
if(conn.rs.getString("pos_marps_T_F")!=null){pos_marps_T_F= conn.rs.getString("pos_marps_T_F");}
if(conn.rs.getString("pos_marps_GT")!=null){pos_marps_GT= conn.rs.getString("pos_marps_GT");}
if(conn.rs.getString("tes_coup_1_M")!=null){tes_coup_1_M= conn.rs.getString("tes_coup_1_M");}
if(conn.rs.getString("tes_coup_1_F")!=null){tes_coup_1_F= conn.rs.getString("tes_coup_1_F");}
if(conn.rs.getString("tes_coup_4_M")!=null){tes_coup_4_M= conn.rs.getString("tes_coup_4_M");}
if(conn.rs.getString("tes_coup_4_F")!=null){tes_coup_4_F= conn.rs.getString("tes_coup_4_F");}
if(conn.rs.getString("tes_coup_9_M")!=null){tes_coup_9_M= conn.rs.getString("tes_coup_9_M");}
if(conn.rs.getString("tes_coup_9_F")!=null){tes_coup_9_F= conn.rs.getString("tes_coup_9_F");}
if(conn.rs.getString("tes_coup_14_M")!=null){tes_coup_14_M= conn.rs.getString("tes_coup_14_M");}
if(conn.rs.getString("tes_coup_14_F")!=null){tes_coup_14_F= conn.rs.getString("tes_coup_14_F");}
if(conn.rs.getString("tes_coup_19_M")!=null){tes_coup_19_M= conn.rs.getString("tes_coup_19_M");}
if(conn.rs.getString("tes_coup_19_F")!=null){tes_coup_19_F= conn.rs.getString("tes_coup_19_F");}
if(conn.rs.getString("tes_coup_24_M")!=null){tes_coup_24_M= conn.rs.getString("tes_coup_24_M");}
if(conn.rs.getString("tes_coup_24_F")!=null){tes_coup_24_F= conn.rs.getString("tes_coup_24_F");}
if(conn.rs.getString("tes_coup_29_M")!=null){tes_coup_29_M= conn.rs.getString("tes_coup_29_M");}
if(conn.rs.getString("tes_coup_29_F")!=null){tes_coup_29_F= conn.rs.getString("tes_coup_29_F");}
if(conn.rs.getString("tes_coup_34_M")!=null){tes_coup_34_M= conn.rs.getString("tes_coup_34_M");}
if(conn.rs.getString("tes_coup_34_F")!=null){tes_coup_34_F= conn.rs.getString("tes_coup_34_F");}
if(conn.rs.getString("tes_coup_39_M")!=null){tes_coup_39_M= conn.rs.getString("tes_coup_39_M");}
if(conn.rs.getString("tes_coup_39_F")!=null){tes_coup_39_F= conn.rs.getString("tes_coup_39_F");}
if(conn.rs.getString("tes_coup_49_M")!=null){tes_coup_49_M= conn.rs.getString("tes_coup_49_M");}
if(conn.rs.getString("tes_coup_49_F")!=null){tes_coup_49_F= conn.rs.getString("tes_coup_49_F");}
if(conn.rs.getString("tes_coup_50_M")!=null){tes_coup_50_M= conn.rs.getString("tes_coup_50_M");}
if(conn.rs.getString("tes_coup_50_F")!=null){tes_coup_50_F= conn.rs.getString("tes_coup_50_F");}
if(conn.rs.getString("tes_coup_T_M")!=null){tes_coup_T_M= conn.rs.getString("tes_coup_T_M");}
if(conn.rs.getString("tes_coup_T_F")!=null){tes_coup_T_F= conn.rs.getString("tes_coup_T_F");}
if(conn.rs.getString("tes_coup_GT")!=null){tes_coup_GT= conn.rs.getString("tes_coup_GT");}
if(conn.rs.getString("tes_coup_dis_1_M")!=null){tes_coup_dis_1_M= conn.rs.getString("tes_coup_dis_1_M");}
if(conn.rs.getString("tes_coup_dis_1_F")!=null){tes_coup_dis_1_F= conn.rs.getString("tes_coup_dis_1_F");}
if(conn.rs.getString("tes_coup_dis_4_M")!=null){tes_coup_dis_4_M= conn.rs.getString("tes_coup_dis_4_M");}
if(conn.rs.getString("tes_coup_dis_4_F")!=null){tes_coup_dis_4_F= conn.rs.getString("tes_coup_dis_4_F");}
if(conn.rs.getString("tes_coup_dis_9_M")!=null){tes_coup_dis_9_M= conn.rs.getString("tes_coup_dis_9_M");}
if(conn.rs.getString("tes_coup_dis_9_F")!=null){tes_coup_dis_9_F= conn.rs.getString("tes_coup_dis_9_F");}
if(conn.rs.getString("tes_coup_dis_14_M")!=null){tes_coup_dis_14_M= conn.rs.getString("tes_coup_dis_14_M");}
if(conn.rs.getString("tes_coup_dis_14_F")!=null){tes_coup_dis_14_F= conn.rs.getString("tes_coup_dis_14_F");}
if(conn.rs.getString("tes_coup_dis_19_M")!=null){tes_coup_dis_19_M= conn.rs.getString("tes_coup_dis_19_M");}
if(conn.rs.getString("tes_coup_dis_19_F")!=null){tes_coup_dis_19_F= conn.rs.getString("tes_coup_dis_19_F");}
if(conn.rs.getString("tes_coup_dis_24_M")!=null){tes_coup_dis_24_M= conn.rs.getString("tes_coup_dis_24_M");}
if(conn.rs.getString("tes_coup_dis_24_F")!=null){tes_coup_dis_24_F= conn.rs.getString("tes_coup_dis_24_F");}
if(conn.rs.getString("tes_coup_dis_29_M")!=null){tes_coup_dis_29_M= conn.rs.getString("tes_coup_dis_29_M");}
if(conn.rs.getString("tes_coup_dis_29_F")!=null){tes_coup_dis_29_F= conn.rs.getString("tes_coup_dis_29_F");}
if(conn.rs.getString("tes_coup_dis_34_M")!=null){tes_coup_dis_34_M= conn.rs.getString("tes_coup_dis_34_M");}
if(conn.rs.getString("tes_coup_dis_34_F")!=null){tes_coup_dis_34_F= conn.rs.getString("tes_coup_dis_34_F");}
if(conn.rs.getString("tes_coup_dis_39_M")!=null){tes_coup_dis_39_M= conn.rs.getString("tes_coup_dis_39_M");}
if(conn.rs.getString("tes_coup_dis_39_F")!=null){tes_coup_dis_39_F= conn.rs.getString("tes_coup_dis_39_F");}
if(conn.rs.getString("tes_coup_dis_49_M")!=null){tes_coup_dis_49_M= conn.rs.getString("tes_coup_dis_49_M");}
if(conn.rs.getString("tes_coup_dis_49_F")!=null){tes_coup_dis_49_F= conn.rs.getString("tes_coup_dis_49_F");}
if(conn.rs.getString("tes_coup_dis_50_M")!=null){tes_coup_dis_50_M= conn.rs.getString("tes_coup_dis_50_M");}
if(conn.rs.getString("tes_coup_dis_50_F")!=null){tes_coup_dis_50_F= conn.rs.getString("tes_coup_dis_50_F");}
if(conn.rs.getString("tes_coup_dis_T_M")!=null){tes_coup_dis_T_M= conn.rs.getString("tes_coup_dis_T_M");}
if(conn.rs.getString("tes_coup_dis_T_F")!=null){tes_coup_dis_T_F= conn.rs.getString("tes_coup_dis_T_F");}
if(conn.rs.getString("tes_coup_dis_GT")!=null){tes_coup_dis_GT= conn.rs.getString("tes_coup_dis_GT");}
if(conn.rs.getString("tk_negative1")!=null){tk_negative1= conn.rs.getString("tk_negative1");}
if(conn.rs.getString("tk_negative2")!=null){tk_negative2= conn.rs.getString("tk_negative2");}
if(conn.rs.getString("tk_positive1")!=null){tk_positive1= conn.rs.getString("tk_positive1");}
if(conn.rs.getString("tk_positive2")!=null){tk_positive2= conn.rs.getString("tk_positive2");}
if(conn.rs.getString("tk_invalid1")!=null){tk_invalid1= conn.rs.getString("tk_invalid1");}
if(conn.rs.getString("tk_invalid2")!=null){tk_invalid2= conn.rs.getString("tk_invalid2");}
if(conn.rs.getString("tk_wastage1")!=null){tk_wastage1= conn.rs.getString("tk_wastage1");}
if(conn.rs.getString("tk_wastage2")!=null){tk_wastage2= conn.rs.getString("tk_wastage2");}
if(conn.rs.getString("tk_total1")!=null){tk_total1= conn.rs.getString("tk_total1");}
if(conn.rs.getString("tk_total2")!=null){tk_total2= conn.rs.getString("tk_total2");}

                    sdp= conn.rs.getString("sdp");
                    user_id= conn.rs.getString("user_id");
                    isValidated= conn.rs.getString("isValidated");
                    isLocked= conn.rs.getString("isLocked");
                    updatedBy= conn.rs.getString("updatedBy");
                    updatedOn= conn.rs.getString("updatedOn");
                    yearmonth= conn.rs.getString("yearmonth");
                    timestamp= conn.rs.getString("timestamp");
       
                
                  if(isLocked.equals("1")){lock="disabled";}
        //get the name of the person who entered the form 
         enterdby="";
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
        
           enterdby=""; 
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
    
    // code to output the table
    
    
     Header = "<table border=\"1px;\">"
        + "<tr border=\"1px;\">"
        + "<td rowspan=\"2\" class=\"title\">SNO</td>"
        + "<td  rowspan=\"2\" class=\"title\">Indicators</td>"
 //       + "<td  rowspan=\"2\" class=\"title\">Patient Type</td>"
        
        + "<td colspan=\"2\"><1</td>"
        + "<td colspan=\"2\">1-4</td>"
        + "<td colspan=\"2\">5-9</td>"
        + "<td colspan=\"2\">10-14</td>"
        + "<td colspan=\"2\">15-19</td>"
        + "<td colspan=\"2\">20-24</td>"
        + "<td colspan=\"2\">25-29</td>"
        + "<td colspan=\"2\">30-34</td>"
        + "<td colspan=\"2\">35-39</td>"
        + "<td colspan=\"2\">40-49</td>"
        + "<td colspan=\"2\">50+</td>"
        + "<td colspan=\"2\">Sub Total</td>"
        + "<td rowspan=\"2\">Grand Total</td>"
        + "</tr>"
            
        + "<tr>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "</tr>";
    
    
    A = ""
        + "<tr border='1px;'>"
        + "<td  class='title' >1</td>"
        + "<td  class='title' >Number counseled </td>"
          
         //---------------------------------------------------------------------------------------------------------new row--------------------------------------------------------------------------------------   
           +"<td><input type=\"text\" name=\"couns_1_M\" id=\"couns_1_M\" value=\""+couns_1_M+"\"  onblur=\"autosave('"+sdp+"','couns_1_M');\"  oninput=\"A('couns_1_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_1_F\" id=\"couns_1_F\" value=\""+couns_1_F+"\"  onblur=\"autosave('"+sdp+"','couns_1_F');\"  oninput=\"A('couns_1_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_4_M\" id=\"couns_4_M\" value=\""+couns_4_M+"\"  onblur=\"autosave('"+sdp+"','couns_4_M');\"  oninput=\"A('couns_4_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_4_F\" id=\"couns_4_F\" value=\""+couns_4_F+"\"  onblur=\"autosave('"+sdp+"','couns_4_F');\"  oninput=\"A('couns_4_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_9_M\" id=\"couns_9_M\" value=\""+couns_9_M+"\"  onblur=\"autosave('"+sdp+"','couns_9_M');\"  oninput=\"A('couns_9_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_9_F\" id=\"couns_9_F\" value=\""+couns_9_F+"\"  onblur=\"autosave('"+sdp+"','couns_9_F');\"  oninput=\"A('couns_9_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_14_M\" id=\"couns_14_M\" value=\""+couns_14_M+"\"  onblur=\"autosave('"+sdp+"','couns_14_M');\"  oninput=\"A('couns_14_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_14_F\" id=\"couns_14_F\" value=\""+couns_14_F+"\"  onblur=\"autosave('"+sdp+"','couns_14_F');\"  oninput=\"A('couns_14_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_19_M\" id=\"couns_19_M\" value=\""+couns_19_M+"\"  onblur=\"autosave('"+sdp+"','couns_19_M');\"  oninput=\"A('couns_19_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_19_F\" id=\"couns_19_F\" value=\""+couns_19_F+"\"  onblur=\"autosave('"+sdp+"','couns_19_F');\"  oninput=\"A('couns_19_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_24_M\" id=\"couns_24_M\" value=\""+couns_24_M+"\"  onblur=\"autosave('"+sdp+"','couns_24_M');\"  oninput=\"A('couns_24_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_24_F\" id=\"couns_24_F\" value=\""+couns_24_F+"\"  onblur=\"autosave('"+sdp+"','couns_24_F');\"  oninput=\"A('couns_24_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_29_M\" id=\"couns_29_M\" value=\""+couns_29_M+"\"  onblur=\"autosave('"+sdp+"','couns_29_M');\"  oninput=\"A('couns_29_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_29_F\" id=\"couns_29_F\" value=\""+couns_29_F+"\"  onblur=\"autosave('"+sdp+"','couns_29_F');\"  oninput=\"A('couns_29_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_34_M\" id=\"couns_34_M\" value=\""+couns_34_M+"\"  onblur=\"autosave('"+sdp+"','couns_34_M');\"  oninput=\"A('couns_34_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_34_F\" id=\"couns_34_F\" value=\""+couns_34_F+"\"  onblur=\"autosave('"+sdp+"','couns_34_F');\"  oninput=\"A('couns_34_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_39_M\" id=\"couns_39_M\" value=\""+couns_39_M+"\"  onblur=\"autosave('"+sdp+"','couns_39_M');\"  oninput=\"A('couns_39_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_39_F\" id=\"couns_39_F\" value=\""+couns_39_F+"\"  onblur=\"autosave('"+sdp+"','couns_39_F');\"  oninput=\"A('couns_39_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_49_M\" id=\"couns_49_M\" value=\""+couns_49_M+"\"  onblur=\"autosave('"+sdp+"','couns_49_M');\"  oninput=\"A('couns_49_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_49_F\" id=\"couns_49_F\" value=\""+couns_49_F+"\"  onblur=\"autosave('"+sdp+"','couns_49_F');\"  oninput=\"A('couns_49_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_50_M\" id=\"couns_50_M\" value=\""+couns_50_M+"\"  onblur=\"autosave('"+sdp+"','couns_50_M');\"  oninput=\"A('couns_50_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_50_F\" id=\"couns_50_F\" value=\""+couns_50_F+"\"  onblur=\"autosave('"+sdp+"','couns_50_F');\"  oninput=\"A('couns_50_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_T_M\" id=\"couns_T_M\" value=\""+couns_T_M+"\"  onblur=\"autosave('"+sdp+"','couns_T_M');\"  oninput=\"A('couns_T_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_T_F\" id=\"couns_T_F\" value=\""+couns_T_F+"\"  onblur=\"autosave('"+sdp+"','couns_T_F');\"  oninput=\"A('couns_T_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"couns_GT\" id=\"couns_GT\" value=\""+couns_GT+"\"  onblur=\"autosave('"+sdp+"','couns_GT');\"  oninput=\"A('couns_GT'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
 
            
        + "</tr>"   
            //tes_new
             + "<tr border='1px;'>"
        + "<td  class='title' >2</td>"
        + "<td  class='title' >Number Tested</td>"
           
            
         //---------------------------------------------------------------------------------------------------------new row--------------------------------------------------------------------------------------   
                 +"<td><input type=\"text\" name=\"tes_1_M\" id=\"tes_1_M\" value=\""+tes_1_M+"\"  onblur=\"autosave('"+sdp+"','tes_1_M');\"  oninput=\"A('tes_1_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_1_F\" id=\"tes_1_F\" value=\""+tes_1_F+"\"  onblur=\"autosave('"+sdp+"','tes_1_F');\"  oninput=\"A('tes_1_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_4_M\" id=\"tes_4_M\" value=\""+tes_4_M+"\"  onblur=\"autosave('"+sdp+"','tes_4_M');\"  oninput=\"A('tes_4_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_4_F\" id=\"tes_4_F\" value=\""+tes_4_F+"\"  onblur=\"autosave('"+sdp+"','tes_4_F');\"  oninput=\"A('tes_4_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_9_M\" id=\"tes_9_M\" value=\""+tes_9_M+"\"  onblur=\"autosave('"+sdp+"','tes_9_M');\"  oninput=\"A('tes_9_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_9_F\" id=\"tes_9_F\" value=\""+tes_9_F+"\"  onblur=\"autosave('"+sdp+"','tes_9_F');\"  oninput=\"A('tes_9_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_14_M\" id=\"tes_14_M\" value=\""+tes_14_M+"\"  onblur=\"autosave('"+sdp+"','tes_14_M');\"  oninput=\"A('tes_14_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_14_F\" id=\"tes_14_F\" value=\""+tes_14_F+"\"  onblur=\"autosave('"+sdp+"','tes_14_F');\"  oninput=\"A('tes_14_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_19_M\" id=\"tes_19_M\" value=\""+tes_19_M+"\"  onblur=\"autosave('"+sdp+"','tes_19_M');\"  oninput=\"A('tes_19_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_19_F\" id=\"tes_19_F\" value=\""+tes_19_F+"\"  onblur=\"autosave('"+sdp+"','tes_19_F');\"  oninput=\"A('tes_19_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_24_M\" id=\"tes_24_M\" value=\""+tes_24_M+"\"  onblur=\"autosave('"+sdp+"','tes_24_M');\"  oninput=\"A('tes_24_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_24_F\" id=\"tes_24_F\" value=\""+tes_24_F+"\"  onblur=\"autosave('"+sdp+"','tes_24_F');\"  oninput=\"A('tes_24_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_29_M\" id=\"tes_29_M\" value=\""+tes_29_M+"\"  onblur=\"autosave('"+sdp+"','tes_29_M');\"  oninput=\"A('tes_29_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_29_F\" id=\"tes_29_F\" value=\""+tes_29_F+"\"  onblur=\"autosave('"+sdp+"','tes_29_F');\"  oninput=\"A('tes_29_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_34_M\" id=\"tes_34_M\" value=\""+tes_34_M+"\"  onblur=\"autosave('"+sdp+"','tes_34_M');\"  oninput=\"A('tes_34_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_34_F\" id=\"tes_34_F\" value=\""+tes_34_F+"\"  onblur=\"autosave('"+sdp+"','tes_34_F');\"  oninput=\"A('tes_34_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_39_M\" id=\"tes_39_M\" value=\""+tes_39_M+"\"  onblur=\"autosave('"+sdp+"','tes_39_M');\"  oninput=\"A('tes_39_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_39_F\" id=\"tes_39_F\" value=\""+tes_39_F+"\"  onblur=\"autosave('"+sdp+"','tes_39_F');\"  oninput=\"A('tes_39_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_49_M\" id=\"tes_49_M\" value=\""+tes_49_M+"\"  onblur=\"autosave('"+sdp+"','tes_49_M');\"  oninput=\"A('tes_49_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_49_F\" id=\"tes_49_F\" value=\""+tes_49_F+"\"  onblur=\"autosave('"+sdp+"','tes_49_F');\"  oninput=\"A('tes_49_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_50_M\" id=\"tes_50_M\" value=\""+tes_50_M+"\"  onblur=\"autosave('"+sdp+"','tes_50_M');\"  oninput=\"A('tes_50_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_50_F\" id=\"tes_50_F\" value=\""+tes_50_F+"\"  onblur=\"autosave('"+sdp+"','tes_50_F');\"  oninput=\"A('tes_50_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_T_M\" id=\"tes_T_M\" value=\""+tes_T_M+"\"  onblur=\"autosave('"+sdp+"','tes_T_M');\"  oninput=\"A('tes_T_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_T_F\" id=\"tes_T_F\" value=\""+tes_T_F+"\"  onblur=\"autosave('"+sdp+"','tes_T_F');\"  oninput=\"A('tes_T_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_GT\" id=\"tes_GT\" value=\""+tes_GT+"\"  onblur=\"autosave('"+sdp+"','tes_GT');\"  oninput=\"A('tes_GT'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
   
           
            
        + "</tr>" 
            
            
                 + "<tr border='1px;'>"
        + "<td  class='title' >3</td>"
        + "<td  class='title' >Number newly tested</td>"
          
         //---------------------------------------------------------------------------------------------------------new row--------------------------------------------------------------------------------------   
 +"<td><input type=\"text\" name=\"tes_new_1_M\" id=\"tes_new_1_M\" value=\""+tes_new_1_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_1_M');\"  oninput=\"A('tes_new_1_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_1_F\" id=\"tes_new_1_F\" value=\""+tes_new_1_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_1_F');\"  oninput=\"A('tes_new_1_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_4_M\" id=\"tes_new_4_M\" value=\""+tes_new_4_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_4_M');\"  oninput=\"A('tes_new_4_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_4_F\" id=\"tes_new_4_F\" value=\""+tes_new_4_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_4_F');\"  oninput=\"A('tes_new_4_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_9_M\" id=\"tes_new_9_M\" value=\""+tes_new_9_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_9_M');\"  oninput=\"A('tes_new_9_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_9_F\" id=\"tes_new_9_F\" value=\""+tes_new_9_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_9_F');\"  oninput=\"A('tes_new_9_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_14_M\" id=\"tes_new_14_M\" value=\""+tes_new_14_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_14_M');\"  oninput=\"A('tes_new_14_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_14_F\" id=\"tes_new_14_F\" value=\""+tes_new_14_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_14_F');\"  oninput=\"A('tes_new_14_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_19_M\" id=\"tes_new_19_M\" value=\""+tes_new_19_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_19_M');\"  oninput=\"A('tes_new_19_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_19_F\" id=\"tes_new_19_F\" value=\""+tes_new_19_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_19_F');\"  oninput=\"A('tes_new_19_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_24_M\" id=\"tes_new_24_M\" value=\""+tes_new_24_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_24_M');\"  oninput=\"A('tes_new_24_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_24_F\" id=\"tes_new_24_F\" value=\""+tes_new_24_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_24_F');\"  oninput=\"A('tes_new_24_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_29_M\" id=\"tes_new_29_M\" value=\""+tes_new_29_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_29_M');\"  oninput=\"A('tes_new_29_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_29_F\" id=\"tes_new_29_F\" value=\""+tes_new_29_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_29_F');\"  oninput=\"A('tes_new_29_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_34_M\" id=\"tes_new_34_M\" value=\""+tes_new_34_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_34_M');\"  oninput=\"A('tes_new_34_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_34_F\" id=\"tes_new_34_F\" value=\""+tes_new_34_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_34_F');\"  oninput=\"A('tes_new_34_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_39_M\" id=\"tes_new_39_M\" value=\""+tes_new_39_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_39_M');\"  oninput=\"A('tes_new_39_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_39_F\" id=\"tes_new_39_F\" value=\""+tes_new_39_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_39_F');\"  oninput=\"A('tes_new_39_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_49_M\" id=\"tes_new_49_M\" value=\""+tes_new_49_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_49_M');\"  oninput=\"A('tes_new_49_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_49_F\" id=\"tes_new_49_F\" value=\""+tes_new_49_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_49_F');\"  oninput=\"A('tes_new_49_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_50_M\" id=\"tes_new_50_M\" value=\""+tes_new_50_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_50_M');\"  oninput=\"A('tes_new_50_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_50_F\" id=\"tes_new_50_F\" value=\""+tes_new_50_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_50_F');\"  oninput=\"A('tes_new_50_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_T_M\" id=\"tes_new_T_M\" value=\""+tes_new_T_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_T_M');\"  oninput=\"A('tes_new_T_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_T_F\" id=\"tes_new_T_F\" value=\""+tes_new_T_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_T_F');\"  oninput=\"A('tes_new_T_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_GT\" id=\"tes_new_GT\" value=\""+tes_new_GT+"\"  onblur=\"autosave('"+sdp+"','tes_new_GT');\"  oninput=\"A('tes_new_GT'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
  
            
        + "</tr>" 
            
            
            
                 + "<tr border='1px;'>"
        + "<td  class='title' >4</td>"
        + "<td  class='title' >Number received result</td>"
          
         //---------------------------------------------------------------------------------------------------------new row--------------------------------------------------------------------------------------   
         +"<td><input type=\"text\" name=\"res_1_M\" id=\"res_1_M\" value=\""+res_1_M+"\"  onblur=\"autosave('"+sdp+"','res_1_M');\"  oninput=\"A('res_1_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_1_F\" id=\"res_1_F\" value=\""+res_1_F+"\"  onblur=\"autosave('"+sdp+"','res_1_F');\"  oninput=\"A('res_1_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_4_M\" id=\"res_4_M\" value=\""+res_4_M+"\"  onblur=\"autosave('"+sdp+"','res_4_M');\"  oninput=\"A('res_4_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_4_F\" id=\"res_4_F\" value=\""+res_4_F+"\"  onblur=\"autosave('"+sdp+"','res_4_F');\"  oninput=\"A('res_4_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_9_M\" id=\"res_9_M\" value=\""+res_9_M+"\"  onblur=\"autosave('"+sdp+"','res_9_M');\"  oninput=\"A('res_9_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_9_F\" id=\"res_9_F\" value=\""+res_9_F+"\"  onblur=\"autosave('"+sdp+"','res_9_F');\"  oninput=\"A('res_9_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_14_M\" id=\"res_14_M\" value=\""+res_14_M+"\"  onblur=\"autosave('"+sdp+"','res_14_M');\"  oninput=\"A('res_14_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_14_F\" id=\"res_14_F\" value=\""+res_14_F+"\"  onblur=\"autosave('"+sdp+"','res_14_F');\"  oninput=\"A('res_14_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_19_M\" id=\"res_19_M\" value=\""+res_19_M+"\"  onblur=\"autosave('"+sdp+"','res_19_M');\"  oninput=\"A('res_19_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_19_F\" id=\"res_19_F\" value=\""+res_19_F+"\"  onblur=\"autosave('"+sdp+"','res_19_F');\"  oninput=\"A('res_19_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_24_M\" id=\"res_24_M\" value=\""+res_24_M+"\"  onblur=\"autosave('"+sdp+"','res_24_M');\"  oninput=\"A('res_24_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_24_F\" id=\"res_24_F\" value=\""+res_24_F+"\"  onblur=\"autosave('"+sdp+"','res_24_F');\"  oninput=\"A('res_24_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_29_M\" id=\"res_29_M\" value=\""+res_29_M+"\"  onblur=\"autosave('"+sdp+"','res_29_M');\"  oninput=\"A('res_29_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_29_F\" id=\"res_29_F\" value=\""+res_29_F+"\"  onblur=\"autosave('"+sdp+"','res_29_F');\"  oninput=\"A('res_29_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_34_M\" id=\"res_34_M\" value=\""+res_34_M+"\"  onblur=\"autosave('"+sdp+"','res_34_M');\"  oninput=\"A('res_34_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_34_F\" id=\"res_34_F\" value=\""+res_34_F+"\"  onblur=\"autosave('"+sdp+"','res_34_F');\"  oninput=\"A('res_34_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_39_M\" id=\"res_39_M\" value=\""+res_39_M+"\"  onblur=\"autosave('"+sdp+"','res_39_M');\"  oninput=\"A('res_39_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_39_F\" id=\"res_39_F\" value=\""+res_39_F+"\"  onblur=\"autosave('"+sdp+"','res_39_F');\"  oninput=\"A('res_39_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_49_M\" id=\"res_49_M\" value=\""+res_49_M+"\"  onblur=\"autosave('"+sdp+"','res_49_M');\"  oninput=\"A('res_49_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_49_F\" id=\"res_49_F\" value=\""+res_49_F+"\"  onblur=\"autosave('"+sdp+"','res_49_F');\"  oninput=\"A('res_49_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_50_M\" id=\"res_50_M\" value=\""+res_50_M+"\"  onblur=\"autosave('"+sdp+"','res_50_M');\"  oninput=\"A('res_50_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_50_F\" id=\"res_50_F\" value=\""+res_50_F+"\"  onblur=\"autosave('"+sdp+"','res_50_F');\"  oninput=\"A('res_50_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_T_M\" id=\"res_T_M\" value=\""+res_T_M+"\"  onblur=\"autosave('"+sdp+"','res_T_M');\"  oninput=\"A('res_T_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_T_F\" id=\"res_T_F\" value=\""+res_T_F+"\"  onblur=\"autosave('"+sdp+"','res_T_F');\"  oninput=\"A('res_T_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"res_GT\" id=\"res_GT\" value=\""+res_GT+"\"  onblur=\"autosave('"+sdp+"','res_GT');\"  oninput=\"A('res_GT'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
   
            
        + "</tr>" 
            
                 + "<tr border='1px;'>"
        + "<td  class='title' >5</td>"
        + "<td  class='title' >Number newly tested and received test result</td>"
          
         //---------------------------------------------------------------------------------------------------------new row--------------------------------------------------------------------------------------   
         +"<td><input type=\"text\" name=\"tes_new_res_1_M\" id=\"tes_new_res_1_M\" value=\""+tes_new_res_1_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_1_M');\"  oninput=\"A('tes_new_res_1_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_1_F\" id=\"tes_new_res_1_F\" value=\""+tes_new_res_1_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_1_F');\"  oninput=\"A('tes_new_res_1_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_4_M\" id=\"tes_new_res_4_M\" value=\""+tes_new_res_4_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_4_M');\"  oninput=\"A('tes_new_res_4_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_4_F\" id=\"tes_new_res_4_F\" value=\""+tes_new_res_4_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_4_F');\"  oninput=\"A('tes_new_res_4_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_9_M\" id=\"tes_new_res_9_M\" value=\""+tes_new_res_9_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_9_M');\"  oninput=\"A('tes_new_res_9_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_9_F\" id=\"tes_new_res_9_F\" value=\""+tes_new_res_9_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_9_F');\"  oninput=\"A('tes_new_res_9_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_14_M\" id=\"tes_new_res_14_M\" value=\""+tes_new_res_14_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_14_M');\"  oninput=\"A('tes_new_res_14_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_14_F\" id=\"tes_new_res_14_F\" value=\""+tes_new_res_14_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_14_F');\"  oninput=\"A('tes_new_res_14_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_19_M\" id=\"tes_new_res_19_M\" value=\""+tes_new_res_19_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_19_M');\"  oninput=\"A('tes_new_res_19_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_19_F\" id=\"tes_new_res_19_F\" value=\""+tes_new_res_19_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_19_F');\"  oninput=\"A('tes_new_res_19_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_24_M\" id=\"tes_new_res_24_M\" value=\""+tes_new_res_24_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_24_M');\"  oninput=\"A('tes_new_res_24_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_24_F\" id=\"tes_new_res_24_F\" value=\""+tes_new_res_24_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_24_F');\"  oninput=\"A('tes_new_res_24_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_29_M\" id=\"tes_new_res_29_M\" value=\""+tes_new_res_29_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_29_M');\"  oninput=\"A('tes_new_res_29_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_29_F\" id=\"tes_new_res_29_F\" value=\""+tes_new_res_29_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_29_F');\"  oninput=\"A('tes_new_res_29_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_34_M\" id=\"tes_new_res_34_M\" value=\""+tes_new_res_34_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_34_M');\"  oninput=\"A('tes_new_res_34_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_34_F\" id=\"tes_new_res_34_F\" value=\""+tes_new_res_34_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_34_F');\"  oninput=\"A('tes_new_res_34_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_39_M\" id=\"tes_new_res_39_M\" value=\""+tes_new_res_39_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_39_M');\"  oninput=\"A('tes_new_res_39_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_39_F\" id=\"tes_new_res_39_F\" value=\""+tes_new_res_39_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_39_F');\"  oninput=\"A('tes_new_res_39_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_49_M\" id=\"tes_new_res_49_M\" value=\""+tes_new_res_49_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_49_M');\"  oninput=\"A('tes_new_res_49_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_49_F\" id=\"tes_new_res_49_F\" value=\""+tes_new_res_49_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_49_F');\"  oninput=\"A('tes_new_res_49_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_50_M\" id=\"tes_new_res_50_M\" value=\""+tes_new_res_50_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_50_M');\"  oninput=\"A('tes_new_res_50_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_50_F\" id=\"tes_new_res_50_F\" value=\""+tes_new_res_50_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_50_F');\"  oninput=\"A('tes_new_res_50_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_T_M\" id=\"tes_new_res_T_M\" value=\""+tes_new_res_T_M+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_T_M');\"  oninput=\"A('tes_new_res_T_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_T_F\" id=\"tes_new_res_T_F\" value=\""+tes_new_res_T_F+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_T_F');\"  oninput=\"A('tes_new_res_T_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_new_res_GT\" id=\"tes_new_res_GT\" value=\""+tes_new_res_GT+"\"  onblur=\"autosave('"+sdp+"','tes_new_res_GT');\"  oninput=\"A('tes_new_res_GT'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
   
            
        + "</tr>" 
            
            
                 + "<tr border='1px;'>"
        + "<td  class='title' >6</td>"
        + "<td  class='title' >Number positive</td>"
          
         //---------------------------------------------------------------------------------------------------------new row--------------------------------------------------------------------------------------   
        +"<td><input type=\"text\" name=\"pos_1_M\" id=\"pos_1_M\" value=\""+pos_1_M+"\"  onblur=\"autosave('"+sdp+"','pos_1_M');\"  oninput=\"A('pos_1_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_1_F\" id=\"pos_1_F\" value=\""+pos_1_F+"\"  onblur=\"autosave('"+sdp+"','pos_1_F');\"  oninput=\"A('pos_1_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_4_M\" id=\"pos_4_M\" value=\""+pos_4_M+"\"  onblur=\"autosave('"+sdp+"','pos_4_M');\"  oninput=\"A('pos_4_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_4_F\" id=\"pos_4_F\" value=\""+pos_4_F+"\"  onblur=\"autosave('"+sdp+"','pos_4_F');\"  oninput=\"A('pos_4_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_9_M\" id=\"pos_9_M\" value=\""+pos_9_M+"\"  onblur=\"autosave('"+sdp+"','pos_9_M');\"  oninput=\"A('pos_9_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_9_F\" id=\"pos_9_F\" value=\""+pos_9_F+"\"  onblur=\"autosave('"+sdp+"','pos_9_F');\"  oninput=\"A('pos_9_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_14_M\" id=\"pos_14_M\" value=\""+pos_14_M+"\"  onblur=\"autosave('"+sdp+"','pos_14_M');\"  oninput=\"A('pos_14_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_14_F\" id=\"pos_14_F\" value=\""+pos_14_F+"\"  onblur=\"autosave('"+sdp+"','pos_14_F');\"  oninput=\"A('pos_14_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_19_M\" id=\"pos_19_M\" value=\""+pos_19_M+"\"  onblur=\"autosave('"+sdp+"','pos_19_M');\"  oninput=\"A('pos_19_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_19_F\" id=\"pos_19_F\" value=\""+pos_19_F+"\"  onblur=\"autosave('"+sdp+"','pos_19_F');\"  oninput=\"A('pos_19_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_24_M\" id=\"pos_24_M\" value=\""+pos_24_M+"\"  onblur=\"autosave('"+sdp+"','pos_24_M');\"  oninput=\"A('pos_24_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_24_F\" id=\"pos_24_F\" value=\""+pos_24_F+"\"  onblur=\"autosave('"+sdp+"','pos_24_F');\"  oninput=\"A('pos_24_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_29_M\" id=\"pos_29_M\" value=\""+pos_29_M+"\"  onblur=\"autosave('"+sdp+"','pos_29_M');\"  oninput=\"A('pos_29_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_29_F\" id=\"pos_29_F\" value=\""+pos_29_F+"\"  onblur=\"autosave('"+sdp+"','pos_29_F');\"  oninput=\"A('pos_29_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_34_M\" id=\"pos_34_M\" value=\""+pos_34_M+"\"  onblur=\"autosave('"+sdp+"','pos_34_M');\"  oninput=\"A('pos_34_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_34_F\" id=\"pos_34_F\" value=\""+pos_34_F+"\"  onblur=\"autosave('"+sdp+"','pos_34_F');\"  oninput=\"A('pos_34_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_39_M\" id=\"pos_39_M\" value=\""+pos_39_M+"\"  onblur=\"autosave('"+sdp+"','pos_39_M');\"  oninput=\"A('pos_39_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_39_F\" id=\"pos_39_F\" value=\""+pos_39_F+"\"  onblur=\"autosave('"+sdp+"','pos_39_F');\"  oninput=\"A('pos_39_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_49_M\" id=\"pos_49_M\" value=\""+pos_49_M+"\"  onblur=\"autosave('"+sdp+"','pos_49_M');\"  oninput=\"A('pos_49_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_49_F\" id=\"pos_49_F\" value=\""+pos_49_F+"\"  onblur=\"autosave('"+sdp+"','pos_49_F');\"  oninput=\"A('pos_49_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_50_M\" id=\"pos_50_M\" value=\""+pos_50_M+"\"  onblur=\"autosave('"+sdp+"','pos_50_M');\"  oninput=\"A('pos_50_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_50_F\" id=\"pos_50_F\" value=\""+pos_50_F+"\"  onblur=\"autosave('"+sdp+"','pos_50_F');\"  oninput=\"A('pos_50_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_T_M\" id=\"pos_T_M\" value=\""+pos_T_M+"\"  onblur=\"autosave('"+sdp+"','pos_T_M');\"  oninput=\"A('pos_T_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_T_F\" id=\"pos_T_F\" value=\""+pos_T_F+"\"  onblur=\"autosave('"+sdp+"','pos_T_F');\"  oninput=\"A('pos_T_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_GT\" id=\"pos_GT\" value=\""+pos_GT+"\"  onblur=\"autosave('"+sdp+"','pos_GT');\"  oninput=\"A('pos_GT'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
    
            
        + "</tr>" 
            
            
                 + "<tr border='1px;'>"
        + "<td  class='title' >7</td>"
        + "<td  class='title' >Number positive amongst newly tested</td>"
          
         //---------------------------------------------------------------------------------------------------------new row--------------------------------------------------------------------------------------   
      +"<td><input type=\"text\" name=\"pos_tes_1_M\" id=\"pos_tes_1_M\" value=\""+pos_tes_1_M+"\"  onblur=\"autosave('"+sdp+"','pos_tes_1_M');\"  oninput=\"A('pos_tes_1_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_1_F\" id=\"pos_tes_1_F\" value=\""+pos_tes_1_F+"\"  onblur=\"autosave('"+sdp+"','pos_tes_1_F');\"  oninput=\"A('pos_tes_1_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_4_M\" id=\"pos_tes_4_M\" value=\""+pos_tes_4_M+"\"  onblur=\"autosave('"+sdp+"','pos_tes_4_M');\"  oninput=\"A('pos_tes_4_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_4_F\" id=\"pos_tes_4_F\" value=\""+pos_tes_4_F+"\"  onblur=\"autosave('"+sdp+"','pos_tes_4_F');\"  oninput=\"A('pos_tes_4_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_9_M\" id=\"pos_tes_9_M\" value=\""+pos_tes_9_M+"\"  onblur=\"autosave('"+sdp+"','pos_tes_9_M');\"  oninput=\"A('pos_tes_9_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_9_F\" id=\"pos_tes_9_F\" value=\""+pos_tes_9_F+"\"  onblur=\"autosave('"+sdp+"','pos_tes_9_F');\"  oninput=\"A('pos_tes_9_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_14_M\" id=\"pos_tes_14_M\" value=\""+pos_tes_14_M+"\"  onblur=\"autosave('"+sdp+"','pos_tes_14_M');\"  oninput=\"A('pos_tes_14_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_14_F\" id=\"pos_tes_14_F\" value=\""+pos_tes_14_F+"\"  onblur=\"autosave('"+sdp+"','pos_tes_14_F');\"  oninput=\"A('pos_tes_14_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_19_M\" id=\"pos_tes_19_M\" value=\""+pos_tes_19_M+"\"  onblur=\"autosave('"+sdp+"','pos_tes_19_M');\"  oninput=\"A('pos_tes_19_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_19_F\" id=\"pos_tes_19_F\" value=\""+pos_tes_19_F+"\"  onblur=\"autosave('"+sdp+"','pos_tes_19_F');\"  oninput=\"A('pos_tes_19_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_24_M\" id=\"pos_tes_24_M\" value=\""+pos_tes_24_M+"\"  onblur=\"autosave('"+sdp+"','pos_tes_24_M');\"  oninput=\"A('pos_tes_24_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_24_F\" id=\"pos_tes_24_F\" value=\""+pos_tes_24_F+"\"  onblur=\"autosave('"+sdp+"','pos_tes_24_F');\"  oninput=\"A('pos_tes_24_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_29_M\" id=\"pos_tes_29_M\" value=\""+pos_tes_29_M+"\"  onblur=\"autosave('"+sdp+"','pos_tes_29_M');\"  oninput=\"A('pos_tes_29_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_29_F\" id=\"pos_tes_29_F\" value=\""+pos_tes_29_F+"\"  onblur=\"autosave('"+sdp+"','pos_tes_29_F');\"  oninput=\"A('pos_tes_29_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_34_M\" id=\"pos_tes_34_M\" value=\""+pos_tes_34_M+"\"  onblur=\"autosave('"+sdp+"','pos_tes_34_M');\"  oninput=\"A('pos_tes_34_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_34_F\" id=\"pos_tes_34_F\" value=\""+pos_tes_34_F+"\"  onblur=\"autosave('"+sdp+"','pos_tes_34_F');\"  oninput=\"A('pos_tes_34_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_39_M\" id=\"pos_tes_39_M\" value=\""+pos_tes_39_M+"\"  onblur=\"autosave('"+sdp+"','pos_tes_39_M');\"  oninput=\"A('pos_tes_39_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_39_F\" id=\"pos_tes_39_F\" value=\""+pos_tes_39_F+"\"  onblur=\"autosave('"+sdp+"','pos_tes_39_F');\"  oninput=\"A('pos_tes_39_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_49_M\" id=\"pos_tes_49_M\" value=\""+pos_tes_49_M+"\"  onblur=\"autosave('"+sdp+"','pos_tes_49_M');\"  oninput=\"A('pos_tes_49_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_49_F\" id=\"pos_tes_49_F\" value=\""+pos_tes_49_F+"\"  onblur=\"autosave('"+sdp+"','pos_tes_49_F');\"  oninput=\"A('pos_tes_49_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_50_M\" id=\"pos_tes_50_M\" value=\""+pos_tes_50_M+"\"  onblur=\"autosave('"+sdp+"','pos_tes_50_M');\"  oninput=\"A('pos_tes_50_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_50_F\" id=\"pos_tes_50_F\" value=\""+pos_tes_50_F+"\"  onblur=\"autosave('"+sdp+"','pos_tes_50_F');\"  oninput=\"A('pos_tes_50_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_T_M\" id=\"pos_tes_T_M\" value=\""+pos_tes_T_M+"\"  onblur=\"autosave('"+sdp+"','pos_tes_T_M');\"  oninput=\"A('pos_tes_T_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_T_F\" id=\"pos_tes_T_F\" value=\""+pos_tes_T_F+"\"  onblur=\"autosave('"+sdp+"','pos_tes_T_F');\"  oninput=\"A('pos_tes_T_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_tes_GT\" id=\"pos_tes_GT\" value=\""+pos_tes_GT+"\"  onblur=\"autosave('"+sdp+"','pos_tes_GT');\"  oninput=\"A('pos_tes_GT'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
      
            
        + "</tr>" 
            
            
                 + "<tr border='1px;'>"
        + "<td  class='title' >8</td>"
        + "<td  class='title' >Number referred</td>"
          
         //---------------------------------------------------------------------------------------------------------new row--------------------------------------------------------------------------------------   
       +"<td><input type=\"text\" name=\"ref_1_M\" id=\"ref_1_M\" value=\""+ref_1_M+"\"  onblur=\"autosave('"+sdp+"','ref_1_M');\"  oninput=\"A('ref_1_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_1_F\" id=\"ref_1_F\" value=\""+ref_1_F+"\"  onblur=\"autosave('"+sdp+"','ref_1_F');\"  oninput=\"A('ref_1_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_4_M\" id=\"ref_4_M\" value=\""+ref_4_M+"\"  onblur=\"autosave('"+sdp+"','ref_4_M');\"  oninput=\"A('ref_4_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_4_F\" id=\"ref_4_F\" value=\""+ref_4_F+"\"  onblur=\"autosave('"+sdp+"','ref_4_F');\"  oninput=\"A('ref_4_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_9_M\" id=\"ref_9_M\" value=\""+ref_9_M+"\"  onblur=\"autosave('"+sdp+"','ref_9_M');\"  oninput=\"A('ref_9_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_9_F\" id=\"ref_9_F\" value=\""+ref_9_F+"\"  onblur=\"autosave('"+sdp+"','ref_9_F');\"  oninput=\"A('ref_9_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_14_M\" id=\"ref_14_M\" value=\""+ref_14_M+"\"  onblur=\"autosave('"+sdp+"','ref_14_M');\"  oninput=\"A('ref_14_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_14_F\" id=\"ref_14_F\" value=\""+ref_14_F+"\"  onblur=\"autosave('"+sdp+"','ref_14_F');\"  oninput=\"A('ref_14_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_19_M\" id=\"ref_19_M\" value=\""+ref_19_M+"\"  onblur=\"autosave('"+sdp+"','ref_19_M');\"  oninput=\"A('ref_19_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_19_F\" id=\"ref_19_F\" value=\""+ref_19_F+"\"  onblur=\"autosave('"+sdp+"','ref_19_F');\"  oninput=\"A('ref_19_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_24_M\" id=\"ref_24_M\" value=\""+ref_24_M+"\"  onblur=\"autosave('"+sdp+"','ref_24_M');\"  oninput=\"A('ref_24_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_24_F\" id=\"ref_24_F\" value=\""+ref_24_F+"\"  onblur=\"autosave('"+sdp+"','ref_24_F');\"  oninput=\"A('ref_24_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_29_M\" id=\"ref_29_M\" value=\""+ref_29_M+"\"  onblur=\"autosave('"+sdp+"','ref_29_M');\"  oninput=\"A('ref_29_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_29_F\" id=\"ref_29_F\" value=\""+ref_29_F+"\"  onblur=\"autosave('"+sdp+"','ref_29_F');\"  oninput=\"A('ref_29_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_34_M\" id=\"ref_34_M\" value=\""+ref_34_M+"\"  onblur=\"autosave('"+sdp+"','ref_34_M');\"  oninput=\"A('ref_34_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_34_F\" id=\"ref_34_F\" value=\""+ref_34_F+"\"  onblur=\"autosave('"+sdp+"','ref_34_F');\"  oninput=\"A('ref_34_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_39_M\" id=\"ref_39_M\" value=\""+ref_39_M+"\"  onblur=\"autosave('"+sdp+"','ref_39_M');\"  oninput=\"A('ref_39_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_39_F\" id=\"ref_39_F\" value=\""+ref_39_F+"\"  onblur=\"autosave('"+sdp+"','ref_39_F');\"  oninput=\"A('ref_39_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_49_M\" id=\"ref_49_M\" value=\""+ref_49_M+"\"  onblur=\"autosave('"+sdp+"','ref_49_M');\"  oninput=\"A('ref_49_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_49_F\" id=\"ref_49_F\" value=\""+ref_49_F+"\"  onblur=\"autosave('"+sdp+"','ref_49_F');\"  oninput=\"A('ref_49_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_50_M\" id=\"ref_50_M\" value=\""+ref_50_M+"\"  onblur=\"autosave('"+sdp+"','ref_50_M');\"  oninput=\"A('ref_50_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_50_F\" id=\"ref_50_F\" value=\""+ref_50_F+"\"  onblur=\"autosave('"+sdp+"','ref_50_F');\"  oninput=\"A('ref_50_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_T_M\" id=\"ref_T_M\" value=\""+ref_T_M+"\"  onblur=\"autosave('"+sdp+"','ref_T_M');\"  oninput=\"A('ref_T_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_T_F\" id=\"ref_T_F\" value=\""+ref_T_F+"\"  onblur=\"autosave('"+sdp+"','ref_T_F');\"  oninput=\"A('ref_T_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"ref_GT\" id=\"ref_GT\" value=\""+ref_GT+"\"  onblur=\"autosave('"+sdp+"','ref_GT');\"  oninput=\"A('ref_GT'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
     
            
        + "</tr>" 
            
                 + "<tr border='1px;'>"
        + "<td  class='title' >9</td>"
        + "<td  class='title' >MARPs tested</td>"
          
         //---------------------------------------------------------------------------------------------------------new row--------------------------------------------------------------------------------------   
          +"<td><input type=\"text\" name=\"tes_marps_1_M\" id=\"tes_marps_1_M\" value=\""+tes_marps_1_M+"\"  onblur=\"autosave('"+sdp+"','tes_marps_1_M');\"  oninput=\"A('tes_marps_1_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_1_F\" id=\"tes_marps_1_F\" value=\""+tes_marps_1_F+"\"  onblur=\"autosave('"+sdp+"','tes_marps_1_F');\"  oninput=\"A('tes_marps_1_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_4_M\" id=\"tes_marps_4_M\" value=\""+tes_marps_4_M+"\"  onblur=\"autosave('"+sdp+"','tes_marps_4_M');\"  oninput=\"A('tes_marps_4_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_4_F\" id=\"tes_marps_4_F\" value=\""+tes_marps_4_F+"\"  onblur=\"autosave('"+sdp+"','tes_marps_4_F');\"  oninput=\"A('tes_marps_4_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_9_M\" id=\"tes_marps_9_M\" value=\""+tes_marps_9_M+"\"  onblur=\"autosave('"+sdp+"','tes_marps_9_M');\"  oninput=\"A('tes_marps_9_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_9_F\" id=\"tes_marps_9_F\" value=\""+tes_marps_9_F+"\"  onblur=\"autosave('"+sdp+"','tes_marps_9_F');\"  oninput=\"A('tes_marps_9_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_14_M\" id=\"tes_marps_14_M\" value=\""+tes_marps_14_M+"\"  onblur=\"autosave('"+sdp+"','tes_marps_14_M');\"  oninput=\"A('tes_marps_14_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_14_F\" id=\"tes_marps_14_F\" value=\""+tes_marps_14_F+"\"  onblur=\"autosave('"+sdp+"','tes_marps_14_F');\"  oninput=\"A('tes_marps_14_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_19_M\" id=\"tes_marps_19_M\" value=\""+tes_marps_19_M+"\"  onblur=\"autosave('"+sdp+"','tes_marps_19_M');\"  oninput=\"A('tes_marps_19_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_19_F\" id=\"tes_marps_19_F\" value=\""+tes_marps_19_F+"\"  onblur=\"autosave('"+sdp+"','tes_marps_19_F');\"  oninput=\"A('tes_marps_19_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_24_M\" id=\"tes_marps_24_M\" value=\""+tes_marps_24_M+"\"  onblur=\"autosave('"+sdp+"','tes_marps_24_M');\"  oninput=\"A('tes_marps_24_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_24_F\" id=\"tes_marps_24_F\" value=\""+tes_marps_24_F+"\"  onblur=\"autosave('"+sdp+"','tes_marps_24_F');\"  oninput=\"A('tes_marps_24_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_29_M\" id=\"tes_marps_29_M\" value=\""+tes_marps_29_M+"\"  onblur=\"autosave('"+sdp+"','tes_marps_29_M');\"  oninput=\"A('tes_marps_29_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_29_F\" id=\"tes_marps_29_F\" value=\""+tes_marps_29_F+"\"  onblur=\"autosave('"+sdp+"','tes_marps_29_F');\"  oninput=\"A('tes_marps_29_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_34_M\" id=\"tes_marps_34_M\" value=\""+tes_marps_34_M+"\"  onblur=\"autosave('"+sdp+"','tes_marps_34_M');\"  oninput=\"A('tes_marps_34_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_34_F\" id=\"tes_marps_34_F\" value=\""+tes_marps_34_F+"\"  onblur=\"autosave('"+sdp+"','tes_marps_34_F');\"  oninput=\"A('tes_marps_34_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_39_M\" id=\"tes_marps_39_M\" value=\""+tes_marps_39_M+"\"  onblur=\"autosave('"+sdp+"','tes_marps_39_M');\"  oninput=\"A('tes_marps_39_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_39_F\" id=\"tes_marps_39_F\" value=\""+tes_marps_39_F+"\"  onblur=\"autosave('"+sdp+"','tes_marps_39_F');\"  oninput=\"A('tes_marps_39_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_49_M\" id=\"tes_marps_49_M\" value=\""+tes_marps_49_M+"\"  onblur=\"autosave('"+sdp+"','tes_marps_49_M');\"  oninput=\"A('tes_marps_49_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_49_F\" id=\"tes_marps_49_F\" value=\""+tes_marps_49_F+"\"  onblur=\"autosave('"+sdp+"','tes_marps_49_F');\"  oninput=\"A('tes_marps_49_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_50_M\" id=\"tes_marps_50_M\" value=\""+tes_marps_50_M+"\"  onblur=\"autosave('"+sdp+"','tes_marps_50_M');\"  oninput=\"A('tes_marps_50_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_50_F\" id=\"tes_marps_50_F\" value=\""+tes_marps_50_F+"\"  onblur=\"autosave('"+sdp+"','tes_marps_50_F');\"  oninput=\"A('tes_marps_50_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_T_M\" id=\"tes_marps_T_M\" value=\""+tes_marps_T_M+"\"  onblur=\"autosave('"+sdp+"','tes_marps_T_M');\"  oninput=\"A('tes_marps_T_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_T_F\" id=\"tes_marps_T_F\" value=\""+tes_marps_T_F+"\"  onblur=\"autosave('"+sdp+"','tes_marps_T_F');\"  oninput=\"A('tes_marps_T_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_marps_GT\" id=\"tes_marps_GT\" value=\""+tes_marps_GT+"\"  onblur=\"autosave('"+sdp+"','tes_marps_GT');\"  oninput=\"A('tes_marps_GT'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
  
            
        + "</tr>" 
            
            
                 + "<tr border='1px;'>"
        + "<td  class='title' >10</td>"
        + "<td  class='title' >MARPs positive</td>"
          
         //---------------------------------------------------------------------------------------------------------new row--------------------------------------------------------------------------------------   
        +"<td><input type=\"text\" name=\"pos_marps_1_M\" id=\"pos_marps_1_M\" value=\""+pos_marps_1_M+"\"  onblur=\"autosave('"+sdp+"','pos_marps_1_M');\"  oninput=\"A('pos_marps_1_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_1_F\" id=\"pos_marps_1_F\" value=\""+pos_marps_1_F+"\"  onblur=\"autosave('"+sdp+"','pos_marps_1_F');\"  oninput=\"A('pos_marps_1_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_4_M\" id=\"pos_marps_4_M\" value=\""+pos_marps_4_M+"\"  onblur=\"autosave('"+sdp+"','pos_marps_4_M');\"  oninput=\"A('pos_marps_4_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_4_F\" id=\"pos_marps_4_F\" value=\""+pos_marps_4_F+"\"  onblur=\"autosave('"+sdp+"','pos_marps_4_F');\"  oninput=\"A('pos_marps_4_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_9_M\" id=\"pos_marps_9_M\" value=\""+pos_marps_9_M+"\"  onblur=\"autosave('"+sdp+"','pos_marps_9_M');\"  oninput=\"A('pos_marps_9_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_9_F\" id=\"pos_marps_9_F\" value=\""+pos_marps_9_F+"\"  onblur=\"autosave('"+sdp+"','pos_marps_9_F');\"  oninput=\"A('pos_marps_9_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_14_M\" id=\"pos_marps_14_M\" value=\""+pos_marps_14_M+"\"  onblur=\"autosave('"+sdp+"','pos_marps_14_M');\"  oninput=\"A('pos_marps_14_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_14_F\" id=\"pos_marps_14_F\" value=\""+pos_marps_14_F+"\"  onblur=\"autosave('"+sdp+"','pos_marps_14_F');\"  oninput=\"A('pos_marps_14_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_19_M\" id=\"pos_marps_19_M\" value=\""+pos_marps_19_M+"\"  onblur=\"autosave('"+sdp+"','pos_marps_19_M');\"  oninput=\"A('pos_marps_19_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_19_F\" id=\"pos_marps_19_F\" value=\""+pos_marps_19_F+"\"  onblur=\"autosave('"+sdp+"','pos_marps_19_F');\"  oninput=\"A('pos_marps_19_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_24_M\" id=\"pos_marps_24_M\" value=\""+pos_marps_24_M+"\"  onblur=\"autosave('"+sdp+"','pos_marps_24_M');\"  oninput=\"A('pos_marps_24_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_24_F\" id=\"pos_marps_24_F\" value=\""+pos_marps_24_F+"\"  onblur=\"autosave('"+sdp+"','pos_marps_24_F');\"  oninput=\"A('pos_marps_24_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_29_M\" id=\"pos_marps_29_M\" value=\""+pos_marps_29_M+"\"  onblur=\"autosave('"+sdp+"','pos_marps_29_M');\"  oninput=\"A('pos_marps_29_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_29_F\" id=\"pos_marps_29_F\" value=\""+pos_marps_29_F+"\"  onblur=\"autosave('"+sdp+"','pos_marps_29_F');\"  oninput=\"A('pos_marps_29_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_34_M\" id=\"pos_marps_34_M\" value=\""+pos_marps_34_M+"\"  onblur=\"autosave('"+sdp+"','pos_marps_34_M');\"  oninput=\"A('pos_marps_34_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_34_F\" id=\"pos_marps_34_F\" value=\""+pos_marps_34_F+"\"  onblur=\"autosave('"+sdp+"','pos_marps_34_F');\"  oninput=\"A('pos_marps_34_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_39_M\" id=\"pos_marps_39_M\" value=\""+pos_marps_39_M+"\"  onblur=\"autosave('"+sdp+"','pos_marps_39_M');\"  oninput=\"A('pos_marps_39_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_39_F\" id=\"pos_marps_39_F\" value=\""+pos_marps_39_F+"\"  onblur=\"autosave('"+sdp+"','pos_marps_39_F');\"  oninput=\"A('pos_marps_39_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_49_M\" id=\"pos_marps_49_M\" value=\""+pos_marps_49_M+"\"  onblur=\"autosave('"+sdp+"','pos_marps_49_M');\"  oninput=\"A('pos_marps_49_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_49_F\" id=\"pos_marps_49_F\" value=\""+pos_marps_49_F+"\"  onblur=\"autosave('"+sdp+"','pos_marps_49_F');\"  oninput=\"A('pos_marps_49_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_50_M\" id=\"pos_marps_50_M\" value=\""+pos_marps_50_M+"\"  onblur=\"autosave('"+sdp+"','pos_marps_50_M');\"  oninput=\"A('pos_marps_50_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_50_F\" id=\"pos_marps_50_F\" value=\""+pos_marps_50_F+"\"  onblur=\"autosave('"+sdp+"','pos_marps_50_F');\"  oninput=\"A('pos_marps_50_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_T_M\" id=\"pos_marps_T_M\" value=\""+pos_marps_T_M+"\"  onblur=\"autosave('"+sdp+"','pos_marps_T_M');\"  oninput=\"A('pos_marps_T_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_T_F\" id=\"pos_marps_T_F\" value=\""+pos_marps_T_F+"\"  onblur=\"autosave('"+sdp+"','pos_marps_T_F');\"  oninput=\"A('pos_marps_T_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"pos_marps_GT\" id=\"pos_marps_GT\" value=\""+pos_marps_GT+"\"  onblur=\"autosave('"+sdp+"','pos_marps_GT');\"  oninput=\"A('pos_marps_GT'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
    
            
        + "</tr>" 
            
                 + "<tr border='1px;'>"
        + "<td  class='title' >11</td>"
        + "<td  class='title' >Number of couples tested</td>"
          
         //---------------------------------------------------------------------------------------------------------new row--------------------------------------------------------------------------------------   
        +"<td><input type=\"text\" name=\"tes_coup_1_M\" id=\"tes_coup_1_M\" value=\""+tes_coup_1_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_1_M');\"  oninput=\"A('tes_coup_1_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_1_F\" id=\"tes_coup_1_F\" value=\""+tes_coup_1_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_1_F');\"  oninput=\"A('tes_coup_1_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_4_M\" id=\"tes_coup_4_M\" value=\""+tes_coup_4_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_4_M');\"  oninput=\"A('tes_coup_4_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_4_F\" id=\"tes_coup_4_F\" value=\""+tes_coup_4_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_4_F');\"  oninput=\"A('tes_coup_4_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_9_M\" id=\"tes_coup_9_M\" value=\""+tes_coup_9_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_9_M');\"  oninput=\"A('tes_coup_9_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_9_F\" id=\"tes_coup_9_F\" value=\""+tes_coup_9_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_9_F');\"  oninput=\"A('tes_coup_9_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_14_M\" id=\"tes_coup_14_M\" value=\""+tes_coup_14_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_14_M');\"  oninput=\"A('tes_coup_14_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_14_F\" id=\"tes_coup_14_F\" value=\""+tes_coup_14_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_14_F');\"  oninput=\"A('tes_coup_14_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_19_M\" id=\"tes_coup_19_M\" value=\""+tes_coup_19_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_19_M');\"  oninput=\"A('tes_coup_19_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_19_F\" id=\"tes_coup_19_F\" value=\""+tes_coup_19_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_19_F');\"  oninput=\"A('tes_coup_19_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_24_M\" id=\"tes_coup_24_M\" value=\""+tes_coup_24_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_24_M');\"  oninput=\"A('tes_coup_24_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_24_F\" id=\"tes_coup_24_F\" value=\""+tes_coup_24_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_24_F');\"  oninput=\"A('tes_coup_24_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_29_M\" id=\"tes_coup_29_M\" value=\""+tes_coup_29_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_29_M');\"  oninput=\"A('tes_coup_29_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_29_F\" id=\"tes_coup_29_F\" value=\""+tes_coup_29_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_29_F');\"  oninput=\"A('tes_coup_29_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_34_M\" id=\"tes_coup_34_M\" value=\""+tes_coup_34_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_34_M');\"  oninput=\"A('tes_coup_34_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_34_F\" id=\"tes_coup_34_F\" value=\""+tes_coup_34_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_34_F');\"  oninput=\"A('tes_coup_34_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_39_M\" id=\"tes_coup_39_M\" value=\""+tes_coup_39_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_39_M');\"  oninput=\"A('tes_coup_39_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_39_F\" id=\"tes_coup_39_F\" value=\""+tes_coup_39_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_39_F');\"  oninput=\"A('tes_coup_39_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_49_M\" id=\"tes_coup_49_M\" value=\""+tes_coup_49_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_49_M');\"  oninput=\"A('tes_coup_49_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_49_F\" id=\"tes_coup_49_F\" value=\""+tes_coup_49_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_49_F');\"  oninput=\"A('tes_coup_49_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_50_M\" id=\"tes_coup_50_M\" value=\""+tes_coup_50_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_50_M');\"  oninput=\"A('tes_coup_50_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_50_F\" id=\"tes_coup_50_F\" value=\""+tes_coup_50_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_50_F');\"  oninput=\"A('tes_coup_50_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_T_M\" id=\"tes_coup_T_M\" value=\""+tes_coup_T_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_T_M');\"  oninput=\"A('tes_coup_T_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_T_F\" id=\"tes_coup_T_F\" value=\""+tes_coup_T_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_T_F');\"  oninput=\"A('tes_coup_T_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_GT\" id=\"tes_coup_GT\" value=\""+tes_coup_GT+"\"  onblur=\"autosave('"+sdp+"','tes_coup_GT');\"  oninput=\"A('tes_coup_GT'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
    
            
        + "</tr>" 
            
            
                 + "<tr border='1px;'>"
        + "<td  class='title' >12</td>"
        + "<td  class='title' >Number of couples tested with discordant results</td>"
          
         //---------------------------------------------------------------------------------------------------------new row--------------------------------------------------------------------------------------   
           +"<td><input type=\"text\" name=\"tes_coup_dis_1_M\" id=\"tes_coup_dis_1_M\" value=\""+tes_coup_dis_1_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_1_M');\"  oninput=\"A('tes_coup_dis_1_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_1_F\" id=\"tes_coup_dis_1_F\" value=\""+tes_coup_dis_1_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_1_F');\"  oninput=\"A('tes_coup_dis_1_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_4_M\" id=\"tes_coup_dis_4_M\" value=\""+tes_coup_dis_4_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_4_M');\"  oninput=\"A('tes_coup_dis_4_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_4_F\" id=\"tes_coup_dis_4_F\" value=\""+tes_coup_dis_4_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_4_F');\"  oninput=\"A('tes_coup_dis_4_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_9_M\" id=\"tes_coup_dis_9_M\" value=\""+tes_coup_dis_9_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_9_M');\"  oninput=\"A('tes_coup_dis_9_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_9_F\" id=\"tes_coup_dis_9_F\" value=\""+tes_coup_dis_9_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_9_F');\"  oninput=\"A('tes_coup_dis_9_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_14_M\" id=\"tes_coup_dis_14_M\" value=\""+tes_coup_dis_14_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_14_M');\"  oninput=\"A('tes_coup_dis_14_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_14_F\" id=\"tes_coup_dis_14_F\" value=\""+tes_coup_dis_14_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_14_F');\"  oninput=\"A('tes_coup_dis_14_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_19_M\" id=\"tes_coup_dis_19_M\" value=\""+tes_coup_dis_19_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_19_M');\"  oninput=\"A('tes_coup_dis_19_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_19_F\" id=\"tes_coup_dis_19_F\" value=\""+tes_coup_dis_19_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_19_F');\"  oninput=\"A('tes_coup_dis_19_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_24_M\" id=\"tes_coup_dis_24_M\" value=\""+tes_coup_dis_24_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_24_M');\"  oninput=\"A('tes_coup_dis_24_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_24_F\" id=\"tes_coup_dis_24_F\" value=\""+tes_coup_dis_24_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_24_F');\"  oninput=\"A('tes_coup_dis_24_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_29_M\" id=\"tes_coup_dis_29_M\" value=\""+tes_coup_dis_29_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_29_M');\"  oninput=\"A('tes_coup_dis_29_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_29_F\" id=\"tes_coup_dis_29_F\" value=\""+tes_coup_dis_29_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_29_F');\"  oninput=\"A('tes_coup_dis_29_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_34_M\" id=\"tes_coup_dis_34_M\" value=\""+tes_coup_dis_34_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_34_M');\"  oninput=\"A('tes_coup_dis_34_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_34_F\" id=\"tes_coup_dis_34_F\" value=\""+tes_coup_dis_34_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_34_F');\"  oninput=\"A('tes_coup_dis_34_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_39_M\" id=\"tes_coup_dis_39_M\" value=\""+tes_coup_dis_39_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_39_M');\"  oninput=\"A('tes_coup_dis_39_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_39_F\" id=\"tes_coup_dis_39_F\" value=\""+tes_coup_dis_39_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_39_F');\"  oninput=\"A('tes_coup_dis_39_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_49_M\" id=\"tes_coup_dis_49_M\" value=\""+tes_coup_dis_49_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_49_M');\"  oninput=\"A('tes_coup_dis_49_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_49_F\" id=\"tes_coup_dis_49_F\" value=\""+tes_coup_dis_49_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_49_F');\"  oninput=\"A('tes_coup_dis_49_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_50_M\" id=\"tes_coup_dis_50_M\" value=\""+tes_coup_dis_50_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_50_M');\"  oninput=\"A('tes_coup_dis_50_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_50_F\" id=\"tes_coup_dis_50_F\" value=\""+tes_coup_dis_50_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_50_F');\"  oninput=\"A('tes_coup_dis_50_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_T_M\" id=\"tes_coup_dis_T_M\" value=\""+tes_coup_dis_T_M+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_T_M');\"  oninput=\"A('tes_coup_dis_T_M'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_T_F\" id=\"tes_coup_dis_T_F\" value=\""+tes_coup_dis_T_F+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_T_F');\"  oninput=\"A('tes_coup_dis_T_F'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
+"<td><input type=\"text\" name=\"tes_coup_dis_GT\" id=\"tes_coup_dis_GT\" value=\""+tes_coup_dis_GT+"\"  onblur=\"autosave('"+sdp+"','tes_coup_dis_GT');\"  oninput=\"A('tes_coup_dis_GT'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
 
            
        + "</tr>" 
            
        + "</table>";  
    
    
    
    B="<table border='1'>"
            + "<tr><th rowspan='2'></th><th colspan='2'>Test kits</th></tr>"
            + "<tr><th>1</th><th>2</th></tr>"
            + "<tr><td>Negative</td>"
            +"<td><input type=\"text\" name=\"tk_negative1\" id=\"tk_negative1\" value=\""+tk_negative1+"\"  onblur=\"autosave('"+sdp+"','tk_negative1');\"  oninput=\"A('tk_negative1'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
            +"<td><input type=\"text\" name=\"tk_negative2\" id=\"tk_negative2\" value=\""+tk_negative2+"\"  onblur=\"autosave('"+sdp+"','tk_negative2');\"  oninput=\"A('tk_negative2'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"

            + "</tr>"
            + "<tr><td>Positive</td>"
            +"<td><input type=\"text\" name=\"tk_positive1\" id=\"tk_positive1\" value=\""+tk_positive1+"\"  onblur=\"autosave('"+sdp+"','tk_positive1');\"  oninput=\"A('tk_positive1'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
            +"<td><input type=\"text\" name=\"tk_positive2\" id=\"tk_positive2\" value=\""+tk_positive2+"\"  onblur=\"autosave('"+sdp+"','tk_positive2');\"  oninput=\"A('tk_positive2'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
            + "</tr>"
            
            +"<tr><td>Invalid</td>"
            +"<td><input type=\"text\" name=\"tk_invalid1\" id=\"tk_invalid1\" value=\""+tk_invalid1+"\"  onblur=\"autosave('"+sdp+"','tk_invalid1');\"  oninput=\"A('tk_invalid1'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
            +"<td><input type=\"text\" name=\"tk_invalid2\" id=\"tk_invalid2\" value=\""+tk_invalid2+"\"  onblur=\"autosave('"+sdp+"','tk_invalid2');\"  oninput=\"A('tk_invalid2'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
            + "</tr>"
            
            + "<tr><td>Wastage</td>"
            +"<td><input type=\"text\" name=\"tk_wastage1\" id=\"tk_wastage1\" value=\""+tk_wastage1+"\"  onblur=\"autosave('"+sdp+"','tk_wastage1');\"  oninput=\"A('tk_wastage1'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
            +"<td><input type=\"text\" name=\"tk_wastage2\" id=\"tk_wastage2\" value=\""+tk_wastage2+"\"  onblur=\"autosave('"+sdp+"','tk_wastage2');\"  oninput=\"A('tk_wastage2'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
            + "</tr>"
           
            +"<tr><td>Total</td>"
            +"<td><input type=\"text\" name=\"tk_total1\" id=\"tk_total1\" value=\""+tk_total1+"\"  onblur=\"autosave('"+sdp+"','tk_total1');\"  oninput=\"A('tk_total1'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
            +"<td><input type=\"text\" name=\"tk_total2\" id=\"tk_total2\" value=\""+tk_total2+"\"  onblur=\"autosave('"+sdp+"','tk_total2');\"  oninput=\"A('tk_total2'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\"onkeypress=\"return numbers(event)\" ></td>"
            +"</tr>"
            
            
            + "</table>";
    
    
    
    
    
    
     //end of code  
     
     
      if (session.getAttribute("forms_holder") != null) {
     if (session.getAttribute("forms_holder").toString().contains("HTC")) {
        output=enterdby+Header+A+B;
        if(isLocked.equals("1")){
            output += "<div class='form-actions' style=\"text-align:center;\"><input type='submit' disabled class='btn red' value='Form Locked' name='validate' id='validate'/></div><span id='formstatus' style='display:none;'>" + validity + " </span><span id='rc' style='display:none;'>"+label+" </span><span id='ufs' style='display:none;'>"+unvalidatedLink+"</span>";
        }
        else{
            output += "<div class='form-actions' style=\"text-align:center;\"><input type='submit' class='btn blue' value='Save HTS Data' name='validate' id='validate'/></div><span id='formstatus' style='display:none;'>" + validity + " </span><span id='rc' style='display:none;'>"+label+" </span><span id='ufs' style='display:none;'>"+unvalidatedLink+"</span>";
        }
        
        } else {
                    output = "<tr ><td colspan='4'><font color=\"red\" size=\"6px;\" style=\"margin-left: 0%;\"><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support  IPT module.</font></td></tr>";
                }
            }
        if (session.getAttribute("facilityid") != null) {
            }
        else {
                output = "<tr ><td colspan='4'><font color=\"red\" size=\"6px;\" style=\"margin-left: 0%;\"><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support  IPT module.</font></td></tr>";

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
        Logger.getLogger(loadIPT.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadIPT.class.getName()).log(Level.SEVERE, null, ex);
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
