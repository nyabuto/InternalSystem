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
public class loadIPT extends HttpServlet {
HttpSession session;
String tableid,SubPartnerID,Annee,Mois,m1_A_New_ART,m1_A_Prev_ART,m4_A_New_ART,m4_A_Prev_ART,m9_A_New_ART,m9_A_Prev_ART,m14_A_New_ART,m14_A_Prev_ART,m19_A_New_ART,m19_A_Prev_ART,m24_A_New_ART,m24_A_Prev_ART,m29_A_New_ART,m29_A_Prev_ART,m34_A_New_ART,m34_A_Prev_ART,m39_A_New_ART,m39_A_Prev_ART,m49_A_New_ART,m49_A_Prev_ART,m50_A_New_ART,m50_A_Prev_ART,tm_A_New_ART,tm_A_Prev_ART,f1_A_New_ART,f1_A_Prev_ART,f4_A_New_ART,f4_A_Prev_ART,f9_A_New_ART,f9_A_Prev_ART,f14_A_New_ART,f14_A_Prev_ART,f19_A_New_ART,f19_A_Prev_ART,f24_A_New_ART,f24_A_Prev_ART,f29_A_New_ART,f29_A_Prev_ART,f34_A_New_ART,f34_A_Prev_ART,f39_A_New_ART,f39_A_Prev_ART,f49_A_New_ART,f49_A_Prev_ART,f50_A_New_ART,f50_A_Prev_ART,tf_A_New_ART,tf_A_Prev_ART,GT_A_New_ART,GT_A_Prev_ART,m1_B_New_ART,m1_B_Prev_ART,m4_B_New_ART,m4_B_Prev_ART,m9_B_New_ART,m9_B_Prev_ART,m14_B_New_ART,m14_B_Prev_ART,m19_B_New_ART,m19_B_Prev_ART,m24_B_New_ART,m24_B_Prev_ART,m29_B_New_ART,m29_B_Prev_ART,m34_B_New_ART,m34_B_Prev_ART,m39_B_New_ART,m39_B_Prev_ART,m49_B_New_ART,m49_B_Prev_ART,m50_B_New_ART,m50_B_Prev_ART,tm_B_New_ART,tm_B_Prev_ART,f1_B_New_ART,f1_B_Prev_ART,f4_B_New_ART,f4_B_Prev_ART,f9_B_New_ART,f9_B_Prev_ART,f14_B_New_ART,f14_B_Prev_ART,f19_B_New_ART,f19_B_Prev_ART,f24_B_New_ART,f24_B_Prev_ART,f29_B_New_ART,f29_B_Prev_ART,f34_B_New_ART,f34_B_Prev_ART,f39_B_New_ART,f39_B_Prev_ART,f49_B_New_ART,f49_B_Prev_ART,f50_B_New_ART,f50_B_Prev_ART,tf_B_New_ART,tf_B_Prev_ART,GT_B_New_ART,GT_B_Prev_ART,m1_C_New_ART,m1_C_Prev_ART,m4_C_New_ART,m4_C_Prev_ART,m9_C_New_ART,m9_C_Prev_ART,m14_C_New_ART,m14_C_Prev_ART,m19_C_New_ART,m19_C_Prev_ART,m24_C_New_ART,m24_C_Prev_ART,m29_C_New_ART,m29_C_Prev_ART,m34_C_New_ART,m34_C_Prev_ART,m39_C_New_ART,m39_C_Prev_ART,m49_C_New_ART,m49_C_Prev_ART,m50_C_New_ART,m50_C_Prev_ART,tm_C_New_ART,tm_C_Prev_ART,f1_C_New_ART,f1_C_Prev_ART,f4_C_New_ART,f4_C_Prev_ART,f9_C_New_ART,f9_C_Prev_ART,f14_C_New_ART,f14_C_Prev_ART,f19_C_New_ART,f19_C_Prev_ART,f24_C_New_ART,f24_C_Prev_ART,f29_C_New_ART,f29_C_Prev_ART,f34_C_New_ART,f34_C_Prev_ART,f39_C_New_ART,f39_C_Prev_ART,f49_C_New_ART,f49_C_Prev_ART,f50_C_New_ART,f50_C_Prev_ART,tf_C_New_ART,tf_C_Prev_ART,GT_C_New_ART,GT_C_Prev_ART,m1_D_New_ART,m1_D_Prev_ART,m4_D_New_ART,m4_D_Prev_ART,m9_D_New_ART,m9_D_Prev_ART,m14_D_New_ART,m14_D_Prev_ART,m19_D_New_ART,m19_D_Prev_ART,m24_D_New_ART,m24_D_Prev_ART,m29_D_New_ART,m29_D_Prev_ART,m34_D_New_ART,m34_D_Prev_ART,m39_D_New_ART,m39_D_Prev_ART,m49_D_New_ART,m49_D_Prev_ART,m50_D_New_ART,m50_D_Prev_ART,tm_D_New_ART,tm_D_Prev_ART,f1_D_New_ART,f1_D_Prev_ART,f4_D_New_ART,f4_D_Prev_ART,f9_D_New_ART,f9_D_Prev_ART,f14_D_New_ART,f14_D_Prev_ART,f19_D_New_ART,f19_D_Prev_ART,f24_D_New_ART,f24_D_Prev_ART,f29_D_New_ART,f29_D_Prev_ART,f34_D_New_ART,f34_D_Prev_ART,f39_D_New_ART,f39_D_Prev_ART,f49_D_New_ART,f49_D_Prev_ART,f50_D_New_ART,f50_D_Prev_ART,tf_D_New_ART,tf_D_Prev_ART,GT_D_New_ART,GT_D_Prev_ART,m1_E_New_ART,m1_E_Prev_ART,m4_E_New_ART,m4_E_Prev_ART,m9_E_New_ART,m9_E_Prev_ART,m14_E_New_ART,m14_E_Prev_ART,m19_E_New_ART,m19_E_Prev_ART,m24_E_New_ART,m24_E_Prev_ART,m29_E_New_ART,m29_E_Prev_ART,m34_E_New_ART,m34_E_Prev_ART,m39_E_New_ART,m39_E_Prev_ART,m49_E_New_ART,m49_E_Prev_ART,m50_E_New_ART,m50_E_Prev_ART,tm_E_New_ART,tm_E_Prev_ART,f1_E_New_ART,f1_E_Prev_ART,f4_E_New_ART,f4_E_Prev_ART,f9_E_New_ART,f9_E_Prev_ART,f14_E_New_ART,f14_E_Prev_ART,f19_E_New_ART,f19_E_Prev_ART,f24_E_New_ART,f24_E_Prev_ART,f29_E_New_ART,f29_E_Prev_ART,f34_E_New_ART,f34_E_Prev_ART,f39_E_New_ART,f39_E_Prev_ART,f49_E_New_ART,f49_E_Prev_ART,f50_E_New_ART,f50_E_Prev_ART,tf_E_New_ART,tf_E_Prev_ART,GT_E_New_ART,GT_E_Prev_ART,m1_F_New_ART,m1_F_Prev_ART,m4_F_New_ART,m4_F_Prev_ART,m9_F_New_ART,m9_F_Prev_ART,m14_F_New_ART,m14_F_Prev_ART,m19_F_New_ART,m19_F_Prev_ART,m24_F_New_ART,m24_F_Prev_ART,m29_F_New_ART,m29_F_Prev_ART,m34_F_New_ART,m34_F_Prev_ART,m39_F_New_ART,m39_F_Prev_ART,m49_F_New_ART,m49_F_Prev_ART,m50_F_New_ART,m50_F_Prev_ART,tm_F_New_ART,tm_F_Prev_ART,f1_F_New_ART,f1_F_Prev_ART,f4_F_New_ART,f4_F_Prev_ART,f9_F_New_ART,f9_F_Prev_ART,f14_F_New_ART,f14_F_Prev_ART,f19_F_New_ART,f19_F_Prev_ART,f24_F_New_ART,f24_F_Prev_ART,f29_F_New_ART,f29_F_Prev_ART,f34_F_New_ART,f34_F_Prev_ART,f39_F_New_ART,f39_F_Prev_ART,f49_F_New_ART,f49_F_Prev_ART,f50_F_New_ART,f50_F_Prev_ART,tf_F_New_ART,tf_F_Prev_ART,GT_F_New_ART,GT_F_Prev_ART,user_id,isValidated,isLocked,updatedBy,updatedOn,yearmonth,timestamp;
String output,lock,enterdby,validity,Header;
String A,B,C,D,E,F;
String year,month;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           dbConn conn = new dbConn();
           session = request.getSession();
        tableid=SubPartnerID=Annee=Mois=m1_A_New_ART=m1_A_Prev_ART=m4_A_New_ART=m4_A_Prev_ART=m9_A_New_ART=m9_A_Prev_ART=m14_A_New_ART=m14_A_Prev_ART=m19_A_New_ART=m19_A_Prev_ART=m24_A_New_ART=m24_A_Prev_ART=m29_A_New_ART=m29_A_Prev_ART=m34_A_New_ART=m34_A_Prev_ART=m39_A_New_ART=m39_A_Prev_ART=m49_A_New_ART=m49_A_Prev_ART=m50_A_New_ART=m50_A_Prev_ART=tm_A_New_ART=tm_A_Prev_ART=f1_A_New_ART=f1_A_Prev_ART=f4_A_New_ART=f4_A_Prev_ART=f9_A_New_ART=f9_A_Prev_ART=f14_A_New_ART=f14_A_Prev_ART=f19_A_New_ART=f19_A_Prev_ART=f24_A_New_ART=f24_A_Prev_ART=f29_A_New_ART=f29_A_Prev_ART=f34_A_New_ART=f34_A_Prev_ART=f39_A_New_ART=f39_A_Prev_ART=f49_A_New_ART=f49_A_Prev_ART=f50_A_New_ART=f50_A_Prev_ART=tf_A_New_ART=tf_A_Prev_ART=GT_A_New_ART=GT_A_Prev_ART=m1_B_New_ART=m1_B_Prev_ART=m4_B_New_ART=m4_B_Prev_ART=m9_B_New_ART=m9_B_Prev_ART=m14_B_New_ART=m14_B_Prev_ART=m19_B_New_ART=m19_B_Prev_ART=m24_B_New_ART=m24_B_Prev_ART=m29_B_New_ART=m29_B_Prev_ART=m34_B_New_ART=m34_B_Prev_ART=m39_B_New_ART=m39_B_Prev_ART=m49_B_New_ART=m49_B_Prev_ART=m50_B_New_ART=m50_B_Prev_ART=tm_B_New_ART=tm_B_Prev_ART=f1_B_New_ART=f1_B_Prev_ART=f4_B_New_ART=f4_B_Prev_ART=f9_B_New_ART=f9_B_Prev_ART=f14_B_New_ART=f14_B_Prev_ART=f19_B_New_ART=f19_B_Prev_ART=f24_B_New_ART=f24_B_Prev_ART=f29_B_New_ART=f29_B_Prev_ART=f34_B_New_ART=f34_B_Prev_ART=f39_B_New_ART=f39_B_Prev_ART=f49_B_New_ART=f49_B_Prev_ART=f50_B_New_ART=f50_B_Prev_ART=tf_B_New_ART=tf_B_Prev_ART=GT_B_New_ART=GT_B_Prev_ART=m1_C_New_ART=m1_C_Prev_ART=m4_C_New_ART=m4_C_Prev_ART=m9_C_New_ART=m9_C_Prev_ART=m14_C_New_ART=m14_C_Prev_ART=m19_C_New_ART=m19_C_Prev_ART=m24_C_New_ART=m24_C_Prev_ART=m29_C_New_ART=m29_C_Prev_ART=m34_C_New_ART=m34_C_Prev_ART=m39_C_New_ART=m39_C_Prev_ART=m49_C_New_ART=m49_C_Prev_ART=m50_C_New_ART=m50_C_Prev_ART=tm_C_New_ART=tm_C_Prev_ART=f1_C_New_ART=f1_C_Prev_ART=f4_C_New_ART=f4_C_Prev_ART=f9_C_New_ART=f9_C_Prev_ART=f14_C_New_ART=f14_C_Prev_ART=f19_C_New_ART=f19_C_Prev_ART=f24_C_New_ART=f24_C_Prev_ART=f29_C_New_ART=f29_C_Prev_ART=f34_C_New_ART=f34_C_Prev_ART=f39_C_New_ART=f39_C_Prev_ART=f49_C_New_ART=f49_C_Prev_ART=f50_C_New_ART=f50_C_Prev_ART=tf_C_New_ART=tf_C_Prev_ART=GT_C_New_ART=GT_C_Prev_ART=m1_D_New_ART=m1_D_Prev_ART=m4_D_New_ART=m4_D_Prev_ART=m9_D_New_ART=m9_D_Prev_ART=m14_D_New_ART=m14_D_Prev_ART=m19_D_New_ART=m19_D_Prev_ART=m24_D_New_ART=m24_D_Prev_ART=m29_D_New_ART=m29_D_Prev_ART=m34_D_New_ART=m34_D_Prev_ART=m39_D_New_ART=m39_D_Prev_ART=m49_D_New_ART=m49_D_Prev_ART=m50_D_New_ART=m50_D_Prev_ART=tm_D_New_ART=tm_D_Prev_ART=f1_D_New_ART=f1_D_Prev_ART=f4_D_New_ART=f4_D_Prev_ART=f9_D_New_ART=f9_D_Prev_ART=f14_D_New_ART=f14_D_Prev_ART=f19_D_New_ART=f19_D_Prev_ART=f24_D_New_ART=f24_D_Prev_ART=f29_D_New_ART=f29_D_Prev_ART=f34_D_New_ART=f34_D_Prev_ART=f39_D_New_ART=f39_D_Prev_ART=f49_D_New_ART=f49_D_Prev_ART=f50_D_New_ART=f50_D_Prev_ART=tf_D_New_ART=tf_D_Prev_ART=GT_D_New_ART=GT_D_Prev_ART=m1_E_New_ART=m1_E_Prev_ART=m4_E_New_ART=m4_E_Prev_ART=m9_E_New_ART=m9_E_Prev_ART=m14_E_New_ART=m14_E_Prev_ART=m19_E_New_ART=m19_E_Prev_ART=m24_E_New_ART=m24_E_Prev_ART=m29_E_New_ART=m29_E_Prev_ART=m34_E_New_ART=m34_E_Prev_ART=m39_E_New_ART=m39_E_Prev_ART=m49_E_New_ART=m49_E_Prev_ART=m50_E_New_ART=m50_E_Prev_ART=tm_E_New_ART=tm_E_Prev_ART=f1_E_New_ART=f1_E_Prev_ART=f4_E_New_ART=f4_E_Prev_ART=f9_E_New_ART=f9_E_Prev_ART=f14_E_New_ART=f14_E_Prev_ART=f19_E_New_ART=f19_E_Prev_ART=f24_E_New_ART=f24_E_Prev_ART=f29_E_New_ART=f29_E_Prev_ART=f34_E_New_ART=f34_E_Prev_ART=f39_E_New_ART=f39_E_Prev_ART=f49_E_New_ART=f49_E_Prev_ART=f50_E_New_ART=f50_E_Prev_ART=tf_E_New_ART=tf_E_Prev_ART=GT_E_New_ART=GT_E_Prev_ART=m1_F_New_ART=m1_F_Prev_ART=m4_F_New_ART=m4_F_Prev_ART=m9_F_New_ART=m9_F_Prev_ART=m14_F_New_ART=m14_F_Prev_ART=m19_F_New_ART=m19_F_Prev_ART=m24_F_New_ART=m24_F_Prev_ART=m29_F_New_ART=m29_F_Prev_ART=m34_F_New_ART=m34_F_Prev_ART=m39_F_New_ART=m39_F_Prev_ART=m49_F_New_ART=m49_F_Prev_ART=m50_F_New_ART=m50_F_Prev_ART=tm_F_New_ART=tm_F_Prev_ART=f1_F_New_ART=f1_F_Prev_ART=f4_F_New_ART=f4_F_Prev_ART=f9_F_New_ART=f9_F_Prev_ART=f14_F_New_ART=f14_F_Prev_ART=f19_F_New_ART=f19_F_Prev_ART=f24_F_New_ART=f24_F_Prev_ART=f29_F_New_ART=f29_F_Prev_ART=f34_F_New_ART=f34_F_Prev_ART=f39_F_New_ART=f39_F_Prev_ART=f49_F_New_ART=f49_F_Prev_ART=f50_F_New_ART=f50_F_Prev_ART=tf_F_New_ART=tf_F_Prev_ART=GT_F_New_ART=GT_F_Prev_ART=user_id=isValidated=isLocked=updatedBy=updatedOn=yearmonth=timestamp="";
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
        
int iptdone=0;
int iptundone=0;
int iptvalid=0;
int facilssupporting=0;
String distid="";

if(session.getAttribute("subcountyid")!=null){
distid=session.getAttribute("subcountyid").toString();
}


String iptcounter="SELECT 1 FROM ipt join subpartnera on ipt.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and (GT_A_New_ART is not null ||GT_A_Prev_ART!='')  ";
 conn.rs1 = conn.st1.executeQuery(iptcounter);
 while(conn.rs1.next()){
 iptdone++;
  }
 
  String iptvalidatedcounter1="SELECT 1 FROM ipt join subpartnera on ipt.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and isValidated='0' ";
 conn.rs1 = conn.st1.executeQuery(iptvalidatedcounter1);
 while(conn.rs1.next()){
 iptvalid++;
  }
 
            System.out.println(iptcounter);
 
 String iptcounter1="SELECT 1 FROM ipt join subpartnera on ipt.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and  isValidated='0' ";
 conn.rs1 = conn.st1.executeQuery(iptcounter1);
 while(conn.rs1.next()){
 iptundone++;
  }
 
 
 
 
 String countpmctfacility="Select * from subpartnera where ART ='1' and  DistrictID='"+distid+"'";
// String countfacility="Select * from subpartnera where FP='1' || PMTCT ='1' || Maternity='1' || HTC='1' ";
 conn.rs1 = conn.st1.executeQuery(countpmctfacility);
 while(conn.rs1.next()){
 facilssupporting++;
 }
 
String validated="&nbsp &nbsp Validated Form(s): <b>"+iptvalid+" </b>";
 String unvalidated="&nbsp &nbsp Unvalidated Form (s) <font color='black'><b>"+iptundone+"</b></font>";
 
 
  String unvalidatedLink="";int counter=0;
     if(iptundone>0){
     String getUnvalidated="SELECT ipt.SubPartnerID,subpartnera.SubPartnerNom FROM ipt JOIN subpartnera ON ipt.SubPartnerID=subpartnera.SubPartnerID WHERE subpartnera.DistrictID='"+distid+"' AND vmmc_new.Mois='"+month+"' AND ipt.Annee='"+year+"' AND ipt.isValidated='0'";
     conn.rs=conn.st.executeQuery(getUnvalidated);
     while(conn.rs.next()){
         counter++;
//     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=Form731.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=IPT.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
                    }
    }
     
   if(counter>0){
    unvalidated="<button class='btn btn-primary btn-lg' data-target='#unvalidatedModal' style='width:auto; height:auto;' data-toggle='modal' type='button'> Unvalidated Form (s) <span class='badge badge-important'><b>"+iptundone+"</b></span></button>";
 
   }  
 
 String label="Record counter <font color='white'><b>"+iptdone+"<b></font>  out of <b>"+facilssupporting+"</b>"+validated+unvalidated;
 
         enterdby="";   
            String check_data="SELECT * FROM ipt WHERE tableid=?";
            conn.pst=conn.conn.prepareStatement(check_data);
            conn.pst.setString(1, tableid);
            conn.rs=conn.pst.executeQuery();
            if(conn.rs.next()){
                    tableid= conn.rs.getString("tableid");
                    SubPartnerID= conn.rs.getString("SubPartnerID");
                    Annee= conn.rs.getString("Annee");
                    Mois= conn.rs.getString("Mois");
                    
                    if(conn.rs.getString("m1_A_New_ART")!=null){m1_A_New_ART= conn.rs.getString("m1_A_New_ART");}
                    if(conn.rs.getString("m1_A_Prev_ART")!=null){m1_A_Prev_ART= conn.rs.getString("m1_A_Prev_ART");}
                    if(conn.rs.getString("m4_A_New_ART")!=null){m4_A_New_ART= conn.rs.getString("m4_A_New_ART");}
                    if(conn.rs.getString("m4_A_Prev_ART")!=null){m4_A_Prev_ART= conn.rs.getString("m4_A_Prev_ART");}
                    if(conn.rs.getString("m9_A_New_ART")!=null){m9_A_New_ART= conn.rs.getString("m9_A_New_ART");}
                    if(conn.rs.getString("m9_A_Prev_ART")!=null){m9_A_Prev_ART= conn.rs.getString("m9_A_Prev_ART");}
                    if(conn.rs.getString("m14_A_New_ART")!=null){m14_A_New_ART= conn.rs.getString("m14_A_New_ART");}
                    if(conn.rs.getString("m14_A_Prev_ART")!=null){m14_A_Prev_ART= conn.rs.getString("m14_A_Prev_ART");}
                    if(conn.rs.getString("m19_A_New_ART")!=null){m19_A_New_ART= conn.rs.getString("m19_A_New_ART");}
                    if(conn.rs.getString("m19_A_Prev_ART")!=null){m19_A_Prev_ART= conn.rs.getString("m19_A_Prev_ART");}
                    if(conn.rs.getString("m24_A_New_ART")!=null){m24_A_New_ART= conn.rs.getString("m24_A_New_ART");}
                    if(conn.rs.getString("m24_A_Prev_ART")!=null){m24_A_Prev_ART= conn.rs.getString("m24_A_Prev_ART");}
                    if(conn.rs.getString("m29_A_New_ART")!=null){m29_A_New_ART= conn.rs.getString("m29_A_New_ART");}
                    if(conn.rs.getString("m29_A_Prev_ART")!=null){m29_A_Prev_ART= conn.rs.getString("m29_A_Prev_ART");}
                    if(conn.rs.getString("m34_A_New_ART")!=null){m34_A_New_ART= conn.rs.getString("m34_A_New_ART");}
                    if(conn.rs.getString("m34_A_Prev_ART")!=null){m34_A_Prev_ART= conn.rs.getString("m34_A_Prev_ART");}
                    if(conn.rs.getString("m39_A_New_ART")!=null){m39_A_New_ART= conn.rs.getString("m39_A_New_ART");}
                    if(conn.rs.getString("m39_A_Prev_ART")!=null){m39_A_Prev_ART= conn.rs.getString("m39_A_Prev_ART");}
                    if(conn.rs.getString("m49_A_New_ART")!=null){m49_A_New_ART= conn.rs.getString("m49_A_New_ART");}
                    if(conn.rs.getString("m49_A_Prev_ART")!=null){m49_A_Prev_ART= conn.rs.getString("m49_A_Prev_ART");}
                    if(conn.rs.getString("m50_A_New_ART")!=null){m50_A_New_ART= conn.rs.getString("m50_A_New_ART");}
                    if(conn.rs.getString("m50_A_Prev_ART")!=null){m50_A_Prev_ART= conn.rs.getString("m50_A_Prev_ART");}
                    if(conn.rs.getString("tm_A_New_ART")!=null){tm_A_New_ART= conn.rs.getString("tm_A_New_ART");}
                    if(conn.rs.getString("tm_A_Prev_ART")!=null){tm_A_Prev_ART= conn.rs.getString("tm_A_Prev_ART");}
                    
                    if(conn.rs.getString("f1_A_New_ART")!=null){f1_A_New_ART= conn.rs.getString("f1_A_New_ART");}
                    if(conn.rs.getString("f1_A_Prev_ART")!=null){f1_A_Prev_ART= conn.rs.getString("f1_A_Prev_ART");}
                    if(conn.rs.getString("f4_A_New_ART")!=null){f4_A_New_ART= conn.rs.getString("f4_A_New_ART");}
                    if(conn.rs.getString("f4_A_Prev_ART")!=null){f4_A_Prev_ART= conn.rs.getString("f4_A_Prev_ART");}
                    if(conn.rs.getString("f9_A_New_ART")!=null){f9_A_New_ART= conn.rs.getString("f9_A_New_ART");}
                    if(conn.rs.getString("f9_A_Prev_ART")!=null){f9_A_Prev_ART= conn.rs.getString("f9_A_Prev_ART");}
                    if(conn.rs.getString("f14_A_New_ART")!=null){f14_A_New_ART= conn.rs.getString("f14_A_New_ART");}
                    if(conn.rs.getString("f14_A_Prev_ART")!=null){f14_A_Prev_ART= conn.rs.getString("f14_A_Prev_ART");}
                    if(conn.rs.getString("f19_A_New_ART")!=null){f19_A_New_ART= conn.rs.getString("f19_A_New_ART");}
                    if(conn.rs.getString("f19_A_Prev_ART")!=null){f19_A_Prev_ART= conn.rs.getString("f19_A_Prev_ART");}
                    if(conn.rs.getString("f24_A_New_ART")!=null){f24_A_New_ART= conn.rs.getString("f24_A_New_ART");}
                    if(conn.rs.getString("f24_A_Prev_ART")!=null){f24_A_Prev_ART= conn.rs.getString("f24_A_Prev_ART");}
                    if(conn.rs.getString("f29_A_New_ART")!=null){f29_A_New_ART= conn.rs.getString("f29_A_New_ART");}
                    if(conn.rs.getString("f29_A_Prev_ART")!=null){f29_A_Prev_ART= conn.rs.getString("f29_A_Prev_ART");}
                    if(conn.rs.getString("f34_A_New_ART")!=null){f34_A_New_ART= conn.rs.getString("f34_A_New_ART");}
                    if(conn.rs.getString("f34_A_Prev_ART")!=null){f34_A_Prev_ART= conn.rs.getString("f34_A_Prev_ART");}
                    if(conn.rs.getString("f39_A_New_ART")!=null){f39_A_New_ART= conn.rs.getString("f39_A_New_ART");}
                    if(conn.rs.getString("f39_A_Prev_ART")!=null){f39_A_Prev_ART= conn.rs.getString("f39_A_Prev_ART");}
                    if(conn.rs.getString("f49_A_New_ART")!=null){f49_A_New_ART= conn.rs.getString("f49_A_New_ART");}
                    if(conn.rs.getString("f49_A_Prev_ART")!=null){f49_A_Prev_ART= conn.rs.getString("f49_A_Prev_ART");}
                    if(conn.rs.getString("f50_A_New_ART")!=null){f50_A_New_ART= conn.rs.getString("f50_A_New_ART");}
                    if(conn.rs.getString("f50_A_Prev_ART")!=null){f50_A_Prev_ART= conn.rs.getString("f50_A_Prev_ART");}
                    if(conn.rs.getString("tf_A_New_ART")!=null){tf_A_New_ART= conn.rs.getString("tf_A_New_ART");}
                    if(conn.rs.getString("tf_A_Prev_ART")!=null){tf_A_Prev_ART= conn.rs.getString("tf_A_Prev_ART");}
                    if(conn.rs.getString("GT_A_New_ART")!=null){GT_A_New_ART= conn.rs.getString("GT_A_New_ART");}
                    if(conn.rs.getString("GT_A_Prev_ART")!=null){GT_A_Prev_ART= conn.rs.getString("GT_A_Prev_ART");}
                    
                    if(conn.rs.getString("m1_B_New_ART")!=null){m1_B_New_ART= conn.rs.getString("m1_B_New_ART");}
                    if(conn.rs.getString("m1_B_Prev_ART")!=null){m1_B_Prev_ART= conn.rs.getString("m1_B_Prev_ART");}
                    if(conn.rs.getString("m4_B_New_ART")!=null){m4_B_New_ART= conn.rs.getString("m4_B_New_ART");}
                    if(conn.rs.getString("m4_B_Prev_ART")!=null){m4_B_Prev_ART= conn.rs.getString("m4_B_Prev_ART");}
                    if(conn.rs.getString("m9_B_New_ART")!=null){m9_B_New_ART= conn.rs.getString("m9_B_New_ART");}
                    if(conn.rs.getString("m9_B_Prev_ART")!=null){m9_B_Prev_ART= conn.rs.getString("m9_B_Prev_ART");}
                    if(conn.rs.getString("m14_B_New_ART")!=null){m14_B_New_ART= conn.rs.getString("m14_B_New_ART");}
                    if(conn.rs.getString("m14_B_Prev_ART")!=null){m14_B_Prev_ART= conn.rs.getString("m14_B_Prev_ART");}
                    if(conn.rs.getString("m19_B_New_ART")!=null){m19_B_New_ART= conn.rs.getString("m19_B_New_ART");}
                    if(conn.rs.getString("m19_B_Prev_ART")!=null){m19_B_Prev_ART= conn.rs.getString("m19_B_Prev_ART");}
                    if(conn.rs.getString("m24_B_New_ART")!=null){m24_B_New_ART= conn.rs.getString("m24_B_New_ART");}
                    if(conn.rs.getString("m24_B_Prev_ART")!=null){m24_B_Prev_ART= conn.rs.getString("m24_B_Prev_ART");}
                    if(conn.rs.getString("m29_B_New_ART")!=null){m29_B_New_ART= conn.rs.getString("m29_B_New_ART");}
                    if(conn.rs.getString("m29_B_Prev_ART")!=null){m29_B_Prev_ART= conn.rs.getString("m29_B_Prev_ART");}
                    if(conn.rs.getString("m34_B_New_ART")!=null){m34_B_New_ART= conn.rs.getString("m34_B_New_ART");}
                    if(conn.rs.getString("m34_B_Prev_ART")!=null){m34_B_Prev_ART= conn.rs.getString("m34_B_Prev_ART");}
                    if(conn.rs.getString("m39_B_New_ART")!=null){m39_B_New_ART= conn.rs.getString("m39_B_New_ART");}
                    if(conn.rs.getString("m39_B_Prev_ART")!=null){m39_B_Prev_ART= conn.rs.getString("m39_B_Prev_ART");}
                    if(conn.rs.getString("m49_B_New_ART")!=null){m49_B_New_ART= conn.rs.getString("m49_B_New_ART");}
                    if(conn.rs.getString("m49_B_Prev_ART")!=null){m49_B_Prev_ART= conn.rs.getString("m49_B_Prev_ART");}
                    if(conn.rs.getString("m50_B_New_ART")!=null){m50_B_New_ART= conn.rs.getString("m50_B_New_ART");}
                    if(conn.rs.getString("m50_B_Prev_ART")!=null){m50_B_Prev_ART= conn.rs.getString("m50_B_Prev_ART");}
                    if(conn.rs.getString("tm_B_New_ART")!=null){tm_B_New_ART= conn.rs.getString("tm_B_New_ART");}
                    if(conn.rs.getString("tm_B_Prev_ART")!=null){tm_B_Prev_ART= conn.rs.getString("tm_B_Prev_ART");}
                    
                    if(conn.rs.getString("f1_B_New_ART")!=null){f1_B_New_ART= conn.rs.getString("f1_B_New_ART");}
                    if(conn.rs.getString("f1_B_Prev_ART")!=null){f1_B_Prev_ART= conn.rs.getString("f1_B_Prev_ART");}
                    if(conn.rs.getString("f4_B_New_ART")!=null){f4_B_New_ART= conn.rs.getString("f4_B_New_ART");}
                    if(conn.rs.getString("f4_B_Prev_ART")!=null){f4_B_Prev_ART= conn.rs.getString("f4_B_Prev_ART");}
                    if(conn.rs.getString("f9_B_New_ART")!=null){f9_B_New_ART= conn.rs.getString("f9_B_New_ART");}
                    if(conn.rs.getString("f9_B_Prev_ART")!=null){f9_B_Prev_ART= conn.rs.getString("f9_B_Prev_ART");}
                    if(conn.rs.getString("f14_B_New_ART")!=null){f14_B_New_ART= conn.rs.getString("f14_B_New_ART");}
                    if(conn.rs.getString("f14_B_Prev_ART")!=null){f14_B_Prev_ART= conn.rs.getString("f14_B_Prev_ART");}
                    if(conn.rs.getString("f19_B_New_ART")!=null){f19_B_New_ART= conn.rs.getString("f19_B_New_ART");}
                    if(conn.rs.getString("f19_B_Prev_ART")!=null){f19_B_Prev_ART= conn.rs.getString("f19_B_Prev_ART");}
                    if(conn.rs.getString("f24_B_New_ART")!=null){f24_B_New_ART= conn.rs.getString("f24_B_New_ART");}
                    if(conn.rs.getString("f24_B_Prev_ART")!=null){f24_B_Prev_ART= conn.rs.getString("f24_B_Prev_ART");}
                    if(conn.rs.getString("f29_B_New_ART")!=null){f29_B_New_ART= conn.rs.getString("f29_B_New_ART");}
                    if(conn.rs.getString("f29_B_Prev_ART")!=null){f29_B_Prev_ART= conn.rs.getString("f29_B_Prev_ART");}
                    if(conn.rs.getString("f34_B_New_ART")!=null){f34_B_New_ART= conn.rs.getString("f34_B_New_ART");}
                    if(conn.rs.getString("f34_B_Prev_ART")!=null){f34_B_Prev_ART= conn.rs.getString("f34_B_Prev_ART");}
                    if(conn.rs.getString("f39_B_New_ART")!=null){f39_B_New_ART= conn.rs.getString("f39_B_New_ART");}
                    if(conn.rs.getString("f39_B_Prev_ART")!=null){f39_B_Prev_ART= conn.rs.getString("f39_B_Prev_ART");}
                    if(conn.rs.getString("f49_B_New_ART")!=null){f49_B_New_ART= conn.rs.getString("f49_B_New_ART");}
                    if(conn.rs.getString("f49_B_Prev_ART")!=null){f49_B_Prev_ART= conn.rs.getString("f49_B_Prev_ART");}
                    if(conn.rs.getString("f50_B_New_ART")!=null){f50_B_New_ART= conn.rs.getString("f50_B_New_ART");}
                    if(conn.rs.getString("f50_B_Prev_ART")!=null){f50_B_Prev_ART= conn.rs.getString("f50_B_Prev_ART");}
                    if(conn.rs.getString("tf_B_New_ART")!=null){tf_B_New_ART= conn.rs.getString("tf_B_New_ART");}
                    if(conn.rs.getString("tf_B_Prev_ART")!=null){tf_B_Prev_ART= conn.rs.getString("tf_B_Prev_ART");}
                    if(conn.rs.getString("GT_B_New_ART")!=null){GT_B_New_ART= conn.rs.getString("GT_B_New_ART");}
                    if(conn.rs.getString("GT_B_Prev_ART")!=null){GT_B_Prev_ART= conn.rs.getString("GT_B_Prev_ART");}
                    
                    if(conn.rs.getString("m1_C_New_ART")!=null){m1_C_New_ART= conn.rs.getString("m1_C_New_ART");}
                    if(conn.rs.getString("m1_C_Prev_ART")!=null){m1_C_Prev_ART= conn.rs.getString("m1_C_Prev_ART");}
                    if(conn.rs.getString("m4_C_New_ART")!=null){m4_C_New_ART= conn.rs.getString("m4_C_New_ART");}
                    if(conn.rs.getString("m4_C_Prev_ART")!=null){m4_C_Prev_ART= conn.rs.getString("m4_C_Prev_ART");}
                    if(conn.rs.getString("m9_C_New_ART")!=null){m9_C_New_ART= conn.rs.getString("m9_C_New_ART");}
                    if(conn.rs.getString("m9_C_Prev_ART")!=null){m9_C_Prev_ART= conn.rs.getString("m9_C_Prev_ART");}
                    if(conn.rs.getString("m14_C_New_ART")!=null){m14_C_New_ART= conn.rs.getString("m14_C_New_ART");}
                    if(conn.rs.getString("m14_C_Prev_ART")!=null){m14_C_Prev_ART= conn.rs.getString("m14_C_Prev_ART");}
                    if(conn.rs.getString("m19_C_New_ART")!=null){m19_C_New_ART= conn.rs.getString("m19_C_New_ART");}
                    if(conn.rs.getString("m19_C_Prev_ART")!=null){m19_C_Prev_ART= conn.rs.getString("m19_C_Prev_ART");}
                    if(conn.rs.getString("m24_C_New_ART")!=null){m24_C_New_ART= conn.rs.getString("m24_C_New_ART");}
                    if(conn.rs.getString("m24_C_Prev_ART")!=null){m24_C_Prev_ART= conn.rs.getString("m24_C_Prev_ART");}
                    if(conn.rs.getString("m29_C_New_ART")!=null){m29_C_New_ART= conn.rs.getString("m29_C_New_ART");}
                    if(conn.rs.getString("m29_C_Prev_ART")!=null){m29_C_Prev_ART= conn.rs.getString("m29_C_Prev_ART");}
                    if(conn.rs.getString("m34_C_New_ART")!=null){m34_C_New_ART= conn.rs.getString("m34_C_New_ART");}
                    if(conn.rs.getString("m34_C_Prev_ART")!=null){m34_C_Prev_ART= conn.rs.getString("m34_C_Prev_ART");}
                    if(conn.rs.getString("m39_C_New_ART")!=null){m39_C_New_ART= conn.rs.getString("m39_C_New_ART");}
                    if(conn.rs.getString("m39_C_Prev_ART")!=null){m39_C_Prev_ART= conn.rs.getString("m39_C_Prev_ART");}
                    if(conn.rs.getString("m49_C_New_ART")!=null){m49_C_New_ART= conn.rs.getString("m49_C_New_ART");}
                    if(conn.rs.getString("m49_C_Prev_ART")!=null){m49_C_Prev_ART= conn.rs.getString("m49_C_Prev_ART");}
                    if(conn.rs.getString("m50_C_New_ART")!=null){m50_C_New_ART= conn.rs.getString("m50_C_New_ART");}
                    if(conn.rs.getString("m50_C_Prev_ART")!=null){m50_C_Prev_ART= conn.rs.getString("m50_C_Prev_ART");}
                    if(conn.rs.getString("tm_C_New_ART")!=null){tm_C_New_ART= conn.rs.getString("tm_C_New_ART");}
                    if(conn.rs.getString("tm_C_Prev_ART")!=null){tm_C_Prev_ART= conn.rs.getString("tm_C_Prev_ART");}
                    
                    if(conn.rs.getString("f1_C_New_ART")!=null){f1_C_New_ART= conn.rs.getString("f1_C_New_ART");}
                    if(conn.rs.getString("f1_C_Prev_ART")!=null){f1_C_Prev_ART= conn.rs.getString("f1_C_Prev_ART");}
                    if(conn.rs.getString("f4_C_New_ART")!=null){f4_C_New_ART= conn.rs.getString("f4_C_New_ART");}
                    if(conn.rs.getString("f4_C_Prev_ART")!=null){f4_C_Prev_ART= conn.rs.getString("f4_C_Prev_ART");}
                    if(conn.rs.getString("f9_C_New_ART")!=null){f9_C_New_ART= conn.rs.getString("f9_C_New_ART");}
                    if(conn.rs.getString("f9_C_Prev_ART")!=null){f9_C_Prev_ART= conn.rs.getString("f9_C_Prev_ART");}
                    if(conn.rs.getString("f14_C_New_ART")!=null){f14_C_New_ART= conn.rs.getString("f14_C_New_ART");}
                    if(conn.rs.getString("f14_C_Prev_ART")!=null){f14_C_Prev_ART= conn.rs.getString("f14_C_Prev_ART");}
                    if(conn.rs.getString("f19_C_New_ART")!=null){f19_C_New_ART= conn.rs.getString("f19_C_New_ART");}
                    if(conn.rs.getString("f19_C_Prev_ART")!=null){f19_C_Prev_ART= conn.rs.getString("f19_C_Prev_ART");}
                    if(conn.rs.getString("f24_C_New_ART")!=null){f24_C_New_ART= conn.rs.getString("f24_C_New_ART");}
                    if(conn.rs.getString("f24_C_Prev_ART")!=null){f24_C_Prev_ART= conn.rs.getString("f24_C_Prev_ART");}
                    if(conn.rs.getString("f29_C_New_ART")!=null){f29_C_New_ART= conn.rs.getString("f29_C_New_ART");}
                    if(conn.rs.getString("f29_C_Prev_ART")!=null){f29_C_Prev_ART= conn.rs.getString("f29_C_Prev_ART");}
                    if(conn.rs.getString("f34_C_New_ART")!=null){f34_C_New_ART= conn.rs.getString("f34_C_New_ART");}
                    if(conn.rs.getString("f34_C_Prev_ART")!=null){f34_C_Prev_ART= conn.rs.getString("f34_C_Prev_ART");}
                    if(conn.rs.getString("f39_C_New_ART")!=null){f39_C_New_ART= conn.rs.getString("f39_C_New_ART");}
                    if(conn.rs.getString("f39_C_Prev_ART")!=null){f39_C_Prev_ART= conn.rs.getString("f39_C_Prev_ART");}
                    if(conn.rs.getString("f49_C_New_ART")!=null){f49_C_New_ART= conn.rs.getString("f49_C_New_ART");}
                    if(conn.rs.getString("f49_C_Prev_ART")!=null){f49_C_Prev_ART= conn.rs.getString("f49_C_Prev_ART");}
                    if(conn.rs.getString("f50_C_New_ART")!=null){f50_C_New_ART= conn.rs.getString("f50_C_New_ART");}
                    if(conn.rs.getString("f50_C_Prev_ART")!=null){f50_C_Prev_ART= conn.rs.getString("f50_C_Prev_ART");}
                    if(conn.rs.getString("tf_C_New_ART")!=null){tf_C_New_ART= conn.rs.getString("tf_C_New_ART");}
                    if(conn.rs.getString("tf_C_Prev_ART")!=null){tf_C_Prev_ART= conn.rs.getString("tf_C_Prev_ART");}
                    if(conn.rs.getString("GT_C_New_ART")!=null){GT_C_New_ART= conn.rs.getString("GT_C_New_ART");}
                    if(conn.rs.getString("GT_C_Prev_ART")!=null){GT_C_Prev_ART= conn.rs.getString("GT_C_Prev_ART");}
                    
                    if(conn.rs.getString("m1_D_New_ART")!=null){m1_D_New_ART= conn.rs.getString("m1_D_New_ART");}
                    if(conn.rs.getString("m1_D_Prev_ART")!=null){m1_D_Prev_ART= conn.rs.getString("m1_D_Prev_ART");}
                    if(conn.rs.getString("m4_D_New_ART")!=null){m4_D_New_ART= conn.rs.getString("m4_D_New_ART");}
                    if(conn.rs.getString("m4_D_Prev_ART")!=null){m4_D_Prev_ART= conn.rs.getString("m4_D_Prev_ART");}
                    if(conn.rs.getString("m9_D_New_ART")!=null){m9_D_New_ART= conn.rs.getString("m9_D_New_ART");}
                    if(conn.rs.getString("m9_D_Prev_ART")!=null){m9_D_Prev_ART= conn.rs.getString("m9_D_Prev_ART");}
                    if(conn.rs.getString("m14_D_New_ART")!=null){m14_D_New_ART= conn.rs.getString("m14_D_New_ART");}
                    if(conn.rs.getString("m14_D_Prev_ART")!=null){m14_D_Prev_ART= conn.rs.getString("m14_D_Prev_ART");}
                    if(conn.rs.getString("m19_D_New_ART")!=null){m19_D_New_ART= conn.rs.getString("m19_D_New_ART");}
                    if(conn.rs.getString("m19_D_Prev_ART")!=null){m19_D_Prev_ART= conn.rs.getString("m19_D_Prev_ART");}
                    if(conn.rs.getString("m24_D_New_ART")!=null){m24_D_New_ART= conn.rs.getString("m24_D_New_ART");}
                    if(conn.rs.getString("m24_D_Prev_ART")!=null){m24_D_Prev_ART= conn.rs.getString("m24_D_Prev_ART");}
                    if(conn.rs.getString("m29_D_New_ART")!=null){m29_D_New_ART= conn.rs.getString("m29_D_New_ART");}
                    if(conn.rs.getString("m29_D_Prev_ART")!=null){m29_D_Prev_ART= conn.rs.getString("m29_D_Prev_ART");}
                    if(conn.rs.getString("m34_D_New_ART")!=null){m34_D_New_ART= conn.rs.getString("m34_D_New_ART");}
                    if(conn.rs.getString("m34_D_Prev_ART")!=null){m34_D_Prev_ART= conn.rs.getString("m34_D_Prev_ART");}
                    if(conn.rs.getString("m39_D_New_ART")!=null){m39_D_New_ART= conn.rs.getString("m39_D_New_ART");}
                    if(conn.rs.getString("m39_D_Prev_ART")!=null){m39_D_Prev_ART= conn.rs.getString("m39_D_Prev_ART");}
                    if(conn.rs.getString("m49_D_New_ART")!=null){m49_D_New_ART= conn.rs.getString("m49_D_New_ART");}
                    if(conn.rs.getString("m49_D_Prev_ART")!=null){m49_D_Prev_ART= conn.rs.getString("m49_D_Prev_ART");}
                    if(conn.rs.getString("m50_D_New_ART")!=null){m50_D_New_ART= conn.rs.getString("m50_D_New_ART");}
                    if(conn.rs.getString("m50_D_Prev_ART")!=null){m50_D_Prev_ART= conn.rs.getString("m50_D_Prev_ART");}
                    if(conn.rs.getString("tm_D_New_ART")!=null){tm_D_New_ART= conn.rs.getString("tm_D_New_ART");}
                    if(conn.rs.getString("tm_D_Prev_ART")!=null){tm_D_Prev_ART= conn.rs.getString("tm_D_Prev_ART");}
                    
                    if(conn.rs.getString("f1_D_New_ART")!=null){f1_D_New_ART= conn.rs.getString("f1_D_New_ART");}
                    if(conn.rs.getString("f1_D_Prev_ART")!=null){f1_D_Prev_ART= conn.rs.getString("f1_D_Prev_ART");}
                    if(conn.rs.getString("f4_D_New_ART")!=null){f4_D_New_ART= conn.rs.getString("f4_D_New_ART");}
                    if(conn.rs.getString("f4_D_Prev_ART")!=null){f4_D_Prev_ART= conn.rs.getString("f4_D_Prev_ART");}
                    if(conn.rs.getString("f9_D_New_ART")!=null){f9_D_New_ART= conn.rs.getString("f9_D_New_ART");}
                    if(conn.rs.getString("f9_D_Prev_ART")!=null){f9_D_Prev_ART= conn.rs.getString("f9_D_Prev_ART");}
                    if(conn.rs.getString("f14_D_New_ART")!=null){f14_D_New_ART= conn.rs.getString("f14_D_New_ART");}
                    if(conn.rs.getString("f14_D_Prev_ART")!=null){f14_D_Prev_ART= conn.rs.getString("f14_D_Prev_ART");}
                    if(conn.rs.getString("f19_D_New_ART")!=null){f19_D_New_ART= conn.rs.getString("f19_D_New_ART");}
                    if(conn.rs.getString("f19_D_Prev_ART")!=null){f19_D_Prev_ART= conn.rs.getString("f19_D_Prev_ART");}
                    if(conn.rs.getString("f24_D_New_ART")!=null){f24_D_New_ART= conn.rs.getString("f24_D_New_ART");}
                    if(conn.rs.getString("f24_D_Prev_ART")!=null){f24_D_Prev_ART= conn.rs.getString("f24_D_Prev_ART");}
                    if(conn.rs.getString("f29_D_New_ART")!=null){f29_D_New_ART= conn.rs.getString("f29_D_New_ART");}
                    if(conn.rs.getString("f29_D_Prev_ART")!=null){f29_D_Prev_ART= conn.rs.getString("f29_D_Prev_ART");}
                    if(conn.rs.getString("f34_D_New_ART")!=null){f34_D_New_ART= conn.rs.getString("f34_D_New_ART");}
                    if(conn.rs.getString("f34_D_Prev_ART")!=null){f34_D_Prev_ART= conn.rs.getString("f34_D_Prev_ART");}
                    if(conn.rs.getString("f39_D_New_ART")!=null){f39_D_New_ART= conn.rs.getString("f39_D_New_ART");}
                    if(conn.rs.getString("f39_D_Prev_ART")!=null){f39_D_Prev_ART= conn.rs.getString("f39_D_Prev_ART");}
                    if(conn.rs.getString("f49_D_New_ART")!=null){f49_D_New_ART= conn.rs.getString("f49_D_New_ART");}
                    if(conn.rs.getString("f49_D_Prev_ART")!=null){f49_D_Prev_ART= conn.rs.getString("f49_D_Prev_ART");}
                    if(conn.rs.getString("f50_D_New_ART")!=null){f50_D_New_ART= conn.rs.getString("f50_D_New_ART");}
                    if(conn.rs.getString("f50_D_Prev_ART")!=null){f50_D_Prev_ART= conn.rs.getString("f50_D_Prev_ART");}
                    if(conn.rs.getString("tf_D_New_ART")!=null){tf_D_New_ART= conn.rs.getString("tf_D_New_ART");}
                    if(conn.rs.getString("tf_D_Prev_ART")!=null){tf_D_Prev_ART= conn.rs.getString("tf_D_Prev_ART");}
                    if(conn.rs.getString("GT_D_New_ART")!=null){GT_D_New_ART= conn.rs.getString("GT_D_New_ART");}
                    if(conn.rs.getString("GT_D_Prev_ART")!=null){GT_D_Prev_ART= conn.rs.getString("GT_D_Prev_ART");}
                    
                    if(conn.rs.getString("m1_E_New_ART")!=null){m1_E_New_ART= conn.rs.getString("m1_E_New_ART");}
                    if(conn.rs.getString("m1_E_Prev_ART")!=null){m1_E_Prev_ART= conn.rs.getString("m1_E_Prev_ART");}
                    if(conn.rs.getString("m4_E_New_ART")!=null){m4_E_New_ART= conn.rs.getString("m4_E_New_ART");}
                    if(conn.rs.getString("m4_E_Prev_ART")!=null){m4_E_Prev_ART= conn.rs.getString("m4_E_Prev_ART");}
                    if(conn.rs.getString("m9_E_New_ART")!=null){m9_E_New_ART= conn.rs.getString("m9_E_New_ART");}
                    if(conn.rs.getString("m9_E_Prev_ART")!=null){m9_E_Prev_ART= conn.rs.getString("m9_E_Prev_ART");}
                    if(conn.rs.getString("m14_E_New_ART")!=null){m14_E_New_ART= conn.rs.getString("m14_E_New_ART");}
                    if(conn.rs.getString("m14_E_Prev_ART")!=null){m14_E_Prev_ART= conn.rs.getString("m14_E_Prev_ART");}
                    if(conn.rs.getString("m19_E_New_ART")!=null){m19_E_New_ART= conn.rs.getString("m19_E_New_ART");}
                    if(conn.rs.getString("m19_E_Prev_ART")!=null){m19_E_Prev_ART= conn.rs.getString("m19_E_Prev_ART");}
                    if(conn.rs.getString("m24_E_New_ART")!=null){m24_E_New_ART= conn.rs.getString("m24_E_New_ART");}
                    if(conn.rs.getString("m24_E_Prev_ART")!=null){m24_E_Prev_ART= conn.rs.getString("m24_E_Prev_ART");}
                    if(conn.rs.getString("m29_E_New_ART")!=null){m29_E_New_ART= conn.rs.getString("m29_E_New_ART");}
                    if(conn.rs.getString("m29_E_Prev_ART")!=null){m29_E_Prev_ART= conn.rs.getString("m29_E_Prev_ART");}
                    if(conn.rs.getString("m34_E_New_ART")!=null){m34_E_New_ART= conn.rs.getString("m34_E_New_ART");}
                    if(conn.rs.getString("m34_E_Prev_ART")!=null){m34_E_Prev_ART= conn.rs.getString("m34_E_Prev_ART");}
                    if(conn.rs.getString("m39_E_New_ART")!=null){m39_E_New_ART= conn.rs.getString("m39_E_New_ART");}
                    if(conn.rs.getString("m39_E_Prev_ART")!=null){m39_E_Prev_ART= conn.rs.getString("m39_E_Prev_ART");}
                    if(conn.rs.getString("m49_E_New_ART")!=null){m49_E_New_ART= conn.rs.getString("m49_E_New_ART");}
                    if(conn.rs.getString("m49_E_Prev_ART")!=null){m49_E_Prev_ART= conn.rs.getString("m49_E_Prev_ART");}
                    if(conn.rs.getString("m50_E_New_ART")!=null){m50_E_New_ART= conn.rs.getString("m50_E_New_ART");}
                    if(conn.rs.getString("m50_E_Prev_ART")!=null){m50_E_Prev_ART= conn.rs.getString("m50_E_Prev_ART");}
                    if(conn.rs.getString("tm_E_New_ART")!=null){tm_E_New_ART= conn.rs.getString("tm_E_New_ART");}
                    if(conn.rs.getString("tm_E_Prev_ART")!=null){tm_E_Prev_ART= conn.rs.getString("tm_E_Prev_ART");}
                    
                    if(conn.rs.getString("f1_E_New_ART")!=null){f1_E_New_ART= conn.rs.getString("f1_E_New_ART");}
                    if(conn.rs.getString("f1_E_Prev_ART")!=null){f1_E_Prev_ART= conn.rs.getString("f1_E_Prev_ART");}
                    if(conn.rs.getString("f4_E_New_ART")!=null){f4_E_New_ART= conn.rs.getString("f4_E_New_ART");}
                    if(conn.rs.getString("f4_E_Prev_ART")!=null){f4_E_Prev_ART= conn.rs.getString("f4_E_Prev_ART");}
                    if(conn.rs.getString("f9_E_New_ART")!=null){f9_E_New_ART= conn.rs.getString("f9_E_New_ART");}
                    if(conn.rs.getString("f9_E_Prev_ART")!=null){f9_E_Prev_ART= conn.rs.getString("f9_E_Prev_ART");}
                    if(conn.rs.getString("f14_E_New_ART")!=null){f14_E_New_ART= conn.rs.getString("f14_E_New_ART");}
                    if(conn.rs.getString("f14_E_Prev_ART")!=null){f14_E_Prev_ART= conn.rs.getString("f14_E_Prev_ART");}
                    if(conn.rs.getString("f19_E_New_ART")!=null){f19_E_New_ART= conn.rs.getString("f19_E_New_ART");}
                    if(conn.rs.getString("f19_E_Prev_ART")!=null){f19_E_Prev_ART= conn.rs.getString("f19_E_Prev_ART");}
                    if(conn.rs.getString("f24_E_New_ART")!=null){f24_E_New_ART= conn.rs.getString("f24_E_New_ART");}
                    if(conn.rs.getString("f24_E_Prev_ART")!=null){f24_E_Prev_ART= conn.rs.getString("f24_E_Prev_ART");}
                    if(conn.rs.getString("f29_E_New_ART")!=null){f29_E_New_ART= conn.rs.getString("f29_E_New_ART");}
                    if(conn.rs.getString("f29_E_Prev_ART")!=null){f29_E_Prev_ART= conn.rs.getString("f29_E_Prev_ART");}
                    if(conn.rs.getString("f34_E_New_ART")!=null){f34_E_New_ART= conn.rs.getString("f34_E_New_ART");}
                    if(conn.rs.getString("f34_E_Prev_ART")!=null){f34_E_Prev_ART= conn.rs.getString("f34_E_Prev_ART");}
                    if(conn.rs.getString("f39_E_New_ART")!=null){f39_E_New_ART= conn.rs.getString("f39_E_New_ART");}
                    if(conn.rs.getString("f39_E_Prev_ART")!=null){f39_E_Prev_ART= conn.rs.getString("f39_E_Prev_ART");}
                    if(conn.rs.getString("f49_E_New_ART")!=null){f49_E_New_ART= conn.rs.getString("f49_E_New_ART");}
                    if(conn.rs.getString("f49_E_Prev_ART")!=null){f49_E_Prev_ART= conn.rs.getString("f49_E_Prev_ART");}
                    if(conn.rs.getString("f50_E_New_ART")!=null){f50_E_New_ART= conn.rs.getString("f50_E_New_ART");}
                    if(conn.rs.getString("f50_E_Prev_ART")!=null){f50_E_Prev_ART= conn.rs.getString("f50_E_Prev_ART");}
                    if(conn.rs.getString("tf_E_New_ART")!=null){tf_E_New_ART= conn.rs.getString("tf_E_New_ART");}
                    if(conn.rs.getString("tf_E_Prev_ART")!=null){tf_E_Prev_ART= conn.rs.getString("tf_E_Prev_ART");}
                    if(conn.rs.getString("GT_E_New_ART")!=null){GT_E_New_ART= conn.rs.getString("GT_E_New_ART");}
                    if(conn.rs.getString("GT_E_Prev_ART")!=null){GT_E_Prev_ART= conn.rs.getString("GT_E_Prev_ART");}
                    
                    if(conn.rs.getString("m1_F_New_ART")!=null){m1_F_New_ART= conn.rs.getString("m1_F_New_ART");}
                    if(conn.rs.getString("m1_F_Prev_ART")!=null){m1_F_Prev_ART= conn.rs.getString("m1_F_Prev_ART");}
                    if(conn.rs.getString("m4_F_New_ART")!=null){m4_F_New_ART= conn.rs.getString("m4_F_New_ART");}
                    if(conn.rs.getString("m4_F_Prev_ART")!=null){m4_F_Prev_ART= conn.rs.getString("m4_F_Prev_ART");}
                    if(conn.rs.getString("m9_F_New_ART")!=null){m9_F_New_ART= conn.rs.getString("m9_F_New_ART");}
                    if(conn.rs.getString("m9_F_Prev_ART")!=null){m9_F_Prev_ART= conn.rs.getString("m9_F_Prev_ART");}
                    if(conn.rs.getString("m14_F_New_ART")!=null){m14_F_New_ART= conn.rs.getString("m14_F_New_ART");}
                    if(conn.rs.getString("m14_F_Prev_ART")!=null){m14_F_Prev_ART= conn.rs.getString("m14_F_Prev_ART");}
                    if(conn.rs.getString("m19_F_New_ART")!=null){m19_F_New_ART= conn.rs.getString("m19_F_New_ART");}
                    if(conn.rs.getString("m19_F_Prev_ART")!=null){m19_F_Prev_ART= conn.rs.getString("m19_F_Prev_ART");}
                    if(conn.rs.getString("m24_F_New_ART")!=null){m24_F_New_ART= conn.rs.getString("m24_F_New_ART");}
                    if(conn.rs.getString("m24_F_Prev_ART")!=null){m24_F_Prev_ART= conn.rs.getString("m24_F_Prev_ART");}
                    if(conn.rs.getString("m29_F_New_ART")!=null){m29_F_New_ART= conn.rs.getString("m29_F_New_ART");}
                    if(conn.rs.getString("m29_F_Prev_ART")!=null){m29_F_Prev_ART= conn.rs.getString("m29_F_Prev_ART");}
                    if(conn.rs.getString("m34_F_New_ART")!=null){m34_F_New_ART= conn.rs.getString("m34_F_New_ART");}
                    if(conn.rs.getString("m34_F_Prev_ART")!=null){m34_F_Prev_ART= conn.rs.getString("m34_F_Prev_ART");}
                    if(conn.rs.getString("m39_F_New_ART")!=null){m39_F_New_ART= conn.rs.getString("m39_F_New_ART");}
                    if(conn.rs.getString("m39_F_Prev_ART")!=null){m39_F_Prev_ART= conn.rs.getString("m39_F_Prev_ART");}
                    if(conn.rs.getString("m49_F_New_ART")!=null){m49_F_New_ART= conn.rs.getString("m49_F_New_ART");}
                    if(conn.rs.getString("m49_F_Prev_ART")!=null){m49_F_Prev_ART= conn.rs.getString("m49_F_Prev_ART");}
                    if(conn.rs.getString("m50_F_New_ART")!=null){m50_F_New_ART= conn.rs.getString("m50_F_New_ART");}
                    if(conn.rs.getString("m50_F_Prev_ART")!=null){m50_F_Prev_ART= conn.rs.getString("m50_F_Prev_ART");}
                    if(conn.rs.getString("tm_F_New_ART")!=null){tm_F_New_ART= conn.rs.getString("tm_F_New_ART");}
                    if(conn.rs.getString("tm_F_Prev_ART")!=null){tm_F_Prev_ART= conn.rs.getString("tm_F_Prev_ART");}
                    
                    if(conn.rs.getString("f1_F_New_ART")!=null){f1_F_New_ART= conn.rs.getString("f1_F_New_ART");}
                    if(conn.rs.getString("f1_F_Prev_ART")!=null){f1_F_Prev_ART= conn.rs.getString("f1_F_Prev_ART");}
                    if(conn.rs.getString("f4_F_New_ART")!=null){f4_F_New_ART= conn.rs.getString("f4_F_New_ART");}
                    if(conn.rs.getString("f4_F_Prev_ART")!=null){f4_F_Prev_ART= conn.rs.getString("f4_F_Prev_ART");}
                    if(conn.rs.getString("f9_F_New_ART")!=null){f9_F_New_ART= conn.rs.getString("f9_F_New_ART");}
                    if(conn.rs.getString("f9_F_Prev_ART")!=null){f9_F_Prev_ART= conn.rs.getString("f9_F_Prev_ART");}
                    if(conn.rs.getString("f14_F_New_ART")!=null){f14_F_New_ART= conn.rs.getString("f14_F_New_ART");}
                    if(conn.rs.getString("f14_F_Prev_ART")!=null){f14_F_Prev_ART= conn.rs.getString("f14_F_Prev_ART");}
                    if(conn.rs.getString("f19_F_New_ART")!=null){f19_F_New_ART= conn.rs.getString("f19_F_New_ART");}
                    if(conn.rs.getString("f19_F_Prev_ART")!=null){f19_F_Prev_ART= conn.rs.getString("f19_F_Prev_ART");}
                    if(conn.rs.getString("f24_F_New_ART")!=null){f24_F_New_ART= conn.rs.getString("f24_F_New_ART");}
                    if(conn.rs.getString("f24_F_Prev_ART")!=null){f24_F_Prev_ART= conn.rs.getString("f24_F_Prev_ART");}
                    if(conn.rs.getString("f29_F_New_ART")!=null){f29_F_New_ART= conn.rs.getString("f29_F_New_ART");}
                    if(conn.rs.getString("f29_F_Prev_ART")!=null){f29_F_Prev_ART= conn.rs.getString("f29_F_Prev_ART");}
                    if(conn.rs.getString("f34_F_New_ART")!=null){f34_F_New_ART= conn.rs.getString("f34_F_New_ART");}
                    if(conn.rs.getString("f34_F_Prev_ART")!=null){f34_F_Prev_ART= conn.rs.getString("f34_F_Prev_ART");}
                    if(conn.rs.getString("f39_F_New_ART")!=null){f39_F_New_ART= conn.rs.getString("f39_F_New_ART");}
                    if(conn.rs.getString("f39_F_Prev_ART")!=null){f39_F_Prev_ART= conn.rs.getString("f39_F_Prev_ART");}
                    if(conn.rs.getString("f49_F_New_ART")!=null){f49_F_New_ART= conn.rs.getString("f49_F_New_ART");}
                    if(conn.rs.getString("f49_F_Prev_ART")!=null){f49_F_Prev_ART= conn.rs.getString("f49_F_Prev_ART");}
                    if(conn.rs.getString("f50_F_New_ART")!=null){f50_F_New_ART= conn.rs.getString("f50_F_New_ART");}
                    if(conn.rs.getString("f50_F_Prev_ART")!=null){f50_F_Prev_ART= conn.rs.getString("f50_F_Prev_ART");}
                    if(conn.rs.getString("tf_F_New_ART")!=null){tf_F_New_ART= conn.rs.getString("tf_F_New_ART");}
                    if(conn.rs.getString("tf_F_Prev_ART")!=null){tf_F_Prev_ART= conn.rs.getString("tf_F_Prev_ART");}
                    if(conn.rs.getString("GT_F_New_ART")!=null){GT_F_New_ART= conn.rs.getString("GT_F_New_ART");}
                    if(conn.rs.getString("GT_F_Prev_ART")!=null){GT_F_Prev_ART= conn.rs.getString("GT_F_Prev_ART");}
                    
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
        + "<td  rowspan=\"2\" class=\"title\">Indicator</td>"
        + "<td  rowspan=\"2\" class=\"title\">Patient Type</td>"
        
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
        + "<tr border=\"1px;\">"
        + "<td rowspan=\"2\" class=\"title\">A</td>"
        + "<td  rowspan=\"2\" class=\"title\">Number initiated on IPT (6 Months ago)</td>"
        
        + "<td>New on ART</td>"
        + "<td><input type=\"text\" name=\"m1_A_New_ART\" id=\"m1_A_New_ART\" value=\""+m1_A_New_ART+"\"  onblur=\"autosave('m1_A_New_ART');\"  oninput=\"A('m1_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f1_A_New_ART\" id=\"f1_A_New_ART\" value=\""+f1_A_New_ART+"\"  onblur=\"autosave('f1_A_New_ART');\"  oninput=\"A('f1_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m4_A_New_ART\" id=\"m4_A_New_ART\" value=\""+m4_A_New_ART+"\"  onblur=\"autosave('m4_A_New_ART');\"  oninput=\"A('m4_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f4_A_New_ART\" id=\"f4_A_New_ART\" value=\""+f4_A_New_ART+"\"  onblur=\"autosave('f4_A_New_ART');\"  oninput=\"A('f4_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m9_A_New_ART\" id=\"m9_A_New_ART\" value=\""+m9_A_New_ART+"\"  onblur=\"autosave('m9_A_New_ART');\"  oninput=\"A('m9_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f9_A_New_ART\" id=\"f9_A_New_ART\" value=\""+f9_A_New_ART+"\"  onblur=\"autosave('f9_A_New_ART');\"  oninput=\"A('f9_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m14_A_New_ART\" id=\"m14_A_New_ART\" value=\""+m14_A_New_ART+"\" onblur=\"autosave('m14_A_New_ART');\"  oninput=\"A('m14_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f14_A_New_ART\" id=\"f14_A_New_ART\" value=\""+f14_A_New_ART+"\" onblur=\"autosave('f14_A_New_ART');\"  oninput=\"A('f14_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m19_A_New_ART\" id=\"m19_A_New_ART\" value=\""+m19_A_New_ART+"\"  onblur=\"autosave('m19_A_New_ART');\"  oninput=\"A('m19_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f19_A_New_ART\" id=\"f19_A_New_ART\" value=\""+f19_A_New_ART+"\"  onblur=\"autosave('f19_A_New_ART');\"  oninput=\"A('f19_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m24_A_New_ART\" id=\"m24_A_New_ART\" value=\""+m24_A_New_ART+"\"  onblur=\"autosave('m24_A_New_ART');\"  oninput=\"A('m24_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f24_A_New_ART\" id=\"f24_A_New_ART\" value=\""+f24_A_New_ART+"\"  onblur=\"autosave('f24_A_New_ART');\"  oninput=\"A('f24_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m29_A_New_ART\" id=\"m29_A_New_ART\" value=\""+m29_A_New_ART+"\"  onblur=\"autosave('m29_A_New_ART');\"  oninput=\"A('m29_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f29_A_New_ART\" id=\"f29_A_New_ART\" value=\""+f29_A_New_ART+"\"  onblur=\"autosave('f29_A_New_ART');\"  oninput=\"A('f29_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m34_A_New_ART\" id=\"m34_A_New_ART\" value=\""+m34_A_New_ART+"\"  onblur=\"autosave('m34_A_New_ART');\"  oninput=\"A('m34_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f34_A_New_ART\" id=\"f34_A_New_ART\" value=\""+f34_A_New_ART+"\"  onblur=\"autosave('f34_A_New_ART');\"  oninput=\"A('f34_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m39_A_New_ART\" id=\"m39_A_New_ART\" value=\""+m39_A_New_ART+"\"  onblur=\"autosave('m39_A_New_ART');\"  oninput=\"A('m39_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f39_A_New_ART\" id=\"f39_A_New_ART\" value=\""+f39_A_New_ART+"\"  onblur=\"autosave('f39_A_New_ART');\"  oninput=\"A('f39_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m49_A_New_ART\" id=\"m49_A_New_ART\" value=\""+m49_A_New_ART+"\"  onblur=\"autosave('m49_A_New_ART');\"  oninput=\"A('m49_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f49_A_New_ART\" id=\"f49_A_New_ART\" value=\""+f49_A_New_ART+"\"  onblur=\"autosave('f49_A_New_ART');\"  oninput=\"A('f49_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m50_A_New_ART\" id=\"m50_A_New_ART\" value=\""+m50_A_New_ART+"\"  onblur=\"autosave('m50_A_New_ART');\"  oninput=\"A('m50_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f50_A_New_ART\" id=\"f50_A_New_ART\" value=\""+f50_A_New_ART+"\"  onblur=\"autosave('f50_A_New_ART');\"  oninput=\"A('f50_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"tm_A_New_ART\" id=\"tm_A_New_ART\" value=\""+tm_A_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tm_A_New_ART');\"  oninput=\"A('tm_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"tf_A_New_ART\" id=\"tf_A_New_ART\" value=\""+tf_A_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tf_A_New_ART');\"  oninput=\"A('tf_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"GT_A_New_ART\" id=\"GT_A_New_ART\" value=\""+GT_A_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('GT_A_New_ART');\"  oninput=\"A('GT_A_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            
        + "<tr>"
        + "<td>Previously on ART</td>"
        + "<td><input type=\"text\" name=\"m1_A_Prev_ART\" id=\"m1_A_Prev_ART\" value=\""+m1_A_Prev_ART+"\"  onblur=\"autosave('m1_A_Prev_ART');\"  oninput=\"A('m1_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f1_A_Prev_ART\" id=\"f1_A_Prev_ART\" value=\""+f1_A_Prev_ART+"\"  onblur=\"autosave('f1_A_Prev_ART');\"  oninput=\"A('f1_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m4_A_Prev_ART\" id=\"m4_A_Prev_ART\" value=\""+m4_A_Prev_ART+"\"  onblur=\"autosave('m4_A_Prev_ART');\"  oninput=\"A('m4_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f4_A_Prev_ART\" id=\"f4_A_Prev_ART\" value=\""+f4_A_Prev_ART+"\"  onblur=\"autosave('f4_A_Prev_ART');\"  oninput=\"A('f4_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m9_A_Prev_ART\" id=\"m9_A_Prev_ART\" value=\""+m9_A_Prev_ART+"\"  onblur=\"autosave('m9_A_Prev_ART');\"  oninput=\"A('m9_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f9_A_Prev_ART\" id=\"f9_A_Prev_ART\" value=\""+f9_A_Prev_ART+"\"  onblur=\"autosave('f9_A_Prev_ART');\"  oninput=\"A('f9_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m14_A_Prev_ART\" id=\"m14_A_Prev_ART\" value=\""+m14_A_Prev_ART+"\" onblur=\"autosave('m14_A_Prev_ART');\"  oninput=\"A('m14_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f14_A_Prev_ART\" id=\"f14_A_Prev_ART\" value=\""+f14_A_Prev_ART+"\" onblur=\"autosave('f14_A_Prev_ART');\"  oninput=\"A('f14_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m19_A_Prev_ART\" id=\"m19_A_Prev_ART\" value=\""+m19_A_Prev_ART+"\"  onblur=\"autosave('m19_A_Prev_ART');\"  oninput=\"A('m19_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f19_A_Prev_ART\" id=\"f19_A_Prev_ART\" value=\""+f19_A_Prev_ART+"\"  onblur=\"autosave('f19_A_Prev_ART');\"  oninput=\"A('f19_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m24_A_Prev_ART\" id=\"m24_A_Prev_ART\" value=\""+m24_A_Prev_ART+"\"  onblur=\"autosave('m24_A_Prev_ART');\"  oninput=\"A('m24_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f24_A_Prev_ART\" id=\"f24_A_Prev_ART\" value=\""+f24_A_Prev_ART+"\"  onblur=\"autosave('f24_A_Prev_ART');\"  oninput=\"A('f24_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m29_A_Prev_ART\" id=\"m29_A_Prev_ART\" value=\""+m29_A_Prev_ART+"\"  onblur=\"autosave('m29_A_Prev_ART');\"  oninput=\"A('m29_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f29_A_Prev_ART\" id=\"f29_A_Prev_ART\" value=\""+f29_A_Prev_ART+"\"  onblur=\"autosave('f29_A_Prev_ART');\"  oninput=\"A('f29_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m34_A_Prev_ART\" id=\"m34_A_Prev_ART\" value=\""+m34_A_Prev_ART+"\"  onblur=\"autosave('m34_A_Prev_ART');\"  oninput=\"A('m34_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f34_A_Prev_ART\" id=\"f34_A_Prev_ART\" value=\""+f34_A_Prev_ART+"\"  onblur=\"autosave('f34_A_Prev_ART');\"  oninput=\"A('f34_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m39_A_Prev_ART\" id=\"m39_A_Prev_ART\" value=\""+m39_A_Prev_ART+"\"  onblur=\"autosave('m39_A_Prev_ART');\"  oninput=\"A('m39_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f39_A_Prev_ART\" id=\"f39_A_Prev_ART\" value=\""+f39_A_Prev_ART+"\"  onblur=\"autosave('f39_A_Prev_ART');\"  oninput=\"A('f39_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m49_A_Prev_ART\" id=\"m49_A_Prev_ART\" value=\""+m49_A_Prev_ART+"\"  onblur=\"autosave('m49_A_Prev_ART');\"  oninput=\"A('m49_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f49_A_Prev_ART\" id=\"f49_A_Prev_ART\" value=\""+f49_A_Prev_ART+"\"  onblur=\"autosave('f49_A_Prev_ART');\"  oninput=\"A('f49_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m50_A_Prev_ART\" id=\"m50_A_Prev_ART\" value=\""+m50_A_Prev_ART+"\"  onblur=\"autosave('m50_A_Prev_ART');\"  oninput=\"A('m50_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f50_A_Prev_ART\" id=\"f50_A_Prev_ART\" value=\""+f50_A_Prev_ART+"\"  onblur=\"autosave('f50_A_Prev_ART');\"  oninput=\"A('f50_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"tm_A_Prev_ART\" id=\"tm_A_Prev_ART\" value=\""+tm_A_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tm_A_Prev_ART');\"  oninput=\"A('tm_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"tf_A_Prev_ART\" id=\"tf_A_Prev_ART\" value=\""+tf_A_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tf_A_Prev_ART');\"  oninput=\"A('tf_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"GT_A_Prev_ART\" id=\"GT_A_Prev_ART\" value=\""+GT_A_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('GT_A_Prev_ART');\"  oninput=\"A('GT_A_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + "";  
    
    
    B = ""
        + "<tr border=\"1px;\">"
        + "<td rowspan=\"2\" class=\"title\">B</td>"
        + "<td  rowspan=\"2\" class=\"title\">Number completed treatment (of those that started IPT six months ago)</td>"
        
        + "<td>New on ART</td>"
        + "<td><input type=\"text\" name=\"m1_B_New_ART\" id=\"m1_B_New_ART\" value=\""+m1_B_New_ART+"\"  onblur=\"autosave('m1_B_New_ART');\"  oninput=\"B('m1_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f1_B_New_ART\" id=\"f1_B_New_ART\" value=\""+f1_B_New_ART+"\"  onblur=\"autosave('f1_B_New_ART');\"  oninput=\"B('f1_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m4_B_New_ART\" id=\"m4_B_New_ART\" value=\""+m4_B_New_ART+"\"  onblur=\"autosave('m4_B_New_ART');\"  oninput=\"B('m4_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f4_B_New_ART\" id=\"f4_B_New_ART\" value=\""+f4_B_New_ART+"\"  onblur=\"autosave('f4_B_New_ART');\"  oninput=\"B('f4_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m9_B_New_ART\" id=\"m9_B_New_ART\" value=\""+m9_B_New_ART+"\"  onblur=\"autosave('m9_B_New_ART');\"  oninput=\"B('m9_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f9_B_New_ART\" id=\"f9_B_New_ART\" value=\""+f9_B_New_ART+"\"  onblur=\"autosave('f9_B_New_ART');\"  oninput=\"B('f9_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m14_B_New_ART\" id=\"m14_B_New_ART\" value=\""+m14_B_New_ART+"\" onblur=\"autosave('m14_B_New_ART');\"  oninput=\"B('m14_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f14_B_New_ART\" id=\"f14_B_New_ART\" value=\""+f14_B_New_ART+"\" onblur=\"autosave('f14_B_New_ART');\"  oninput=\"B('f14_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m19_B_New_ART\" id=\"m19_B_New_ART\" value=\""+m19_B_New_ART+"\"  onblur=\"autosave('m19_B_New_ART');\"  oninput=\"B('m19_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f19_B_New_ART\" id=\"f19_B_New_ART\" value=\""+f19_B_New_ART+"\"  onblur=\"autosave('f19_B_New_ART');\"  oninput=\"B('f19_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m24_B_New_ART\" id=\"m24_B_New_ART\" value=\""+m24_B_New_ART+"\"  onblur=\"autosave('m24_B_New_ART');\"  oninput=\"B('m24_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f24_B_New_ART\" id=\"f24_B_New_ART\" value=\""+f24_B_New_ART+"\"  onblur=\"autosave('f24_B_New_ART');\"  oninput=\"B('f24_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m29_B_New_ART\" id=\"m29_B_New_ART\" value=\""+m29_B_New_ART+"\"  onblur=\"autosave('m29_B_New_ART');\"  oninput=\"B('m29_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f29_B_New_ART\" id=\"f29_B_New_ART\" value=\""+f29_B_New_ART+"\"  onblur=\"autosave('f29_B_New_ART');\"  oninput=\"B('f29_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m34_B_New_ART\" id=\"m34_B_New_ART\" value=\""+m34_B_New_ART+"\"  onblur=\"autosave('m34_B_New_ART');\"  oninput=\"B('m34_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f34_B_New_ART\" id=\"f34_B_New_ART\" value=\""+f34_B_New_ART+"\"  onblur=\"autosave('f34_B_New_ART');\"  oninput=\"B('f34_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m39_B_New_ART\" id=\"m39_B_New_ART\" value=\""+m39_B_New_ART+"\"  onblur=\"autosave('m39_B_New_ART');\"  oninput=\"B('m39_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f39_B_New_ART\" id=\"f39_B_New_ART\" value=\""+f39_B_New_ART+"\"  onblur=\"autosave('f39_B_New_ART');\"  oninput=\"B('f39_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m49_B_New_ART\" id=\"m49_B_New_ART\" value=\""+m49_B_New_ART+"\"  onblur=\"autosave('m49_B_New_ART');\"  oninput=\"B('m49_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f49_B_New_ART\" id=\"f49_B_New_ART\" value=\""+f49_B_New_ART+"\"  onblur=\"autosave('f49_B_New_ART');\"  oninput=\"B('f49_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m50_B_New_ART\" id=\"m50_B_New_ART\" value=\""+m50_B_New_ART+"\"  onblur=\"autosave('m50_B_New_ART');\"  oninput=\"B('m50_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f50_B_New_ART\" id=\"f50_B_New_ART\" value=\""+f50_B_New_ART+"\"  onblur=\"autosave('f50_B_New_ART');\"  oninput=\"B('f50_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"tm_B_New_ART\" id=\"tm_B_New_ART\" value=\""+tm_B_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tm_B_New_ART');\"  oninput=\"B('tm_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"tf_B_New_ART\" id=\"tf_B_New_ART\" value=\""+tf_B_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tf_B_New_ART');\"  oninput=\"B('tf_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"GT_B_New_ART\" id=\"GT_B_New_ART\" value=\""+GT_B_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('GT_B_New_ART');\"  oninput=\"B('GT_B_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            
        + "<tr>"
        + "<td>Previously on ART</td>"
        + "<td><input type=\"text\" name=\"m1_B_Prev_ART\" id=\"m1_B_Prev_ART\" value=\""+m1_B_Prev_ART+"\"  onblur=\"autosave('m1_B_Prev_ART');\"  oninput=\"B('m1_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f1_B_Prev_ART\" id=\"f1_B_Prev_ART\" value=\""+f1_B_Prev_ART+"\"  onblur=\"autosave('f1_B_Prev_ART');\"  oninput=\"B('f1_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m4_B_Prev_ART\" id=\"m4_B_Prev_ART\" value=\""+m4_B_Prev_ART+"\"  onblur=\"autosave('m4_B_Prev_ART');\"  oninput=\"B('m4_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f4_B_Prev_ART\" id=\"f4_B_Prev_ART\" value=\""+f4_B_Prev_ART+"\"  onblur=\"autosave('f4_B_Prev_ART');\"  oninput=\"B('f4_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m9_B_Prev_ART\" id=\"m9_B_Prev_ART\" value=\""+m9_B_Prev_ART+"\"  onblur=\"autosave('m9_B_Prev_ART');\"  oninput=\"B('m9_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f9_B_Prev_ART\" id=\"f9_B_Prev_ART\" value=\""+f9_B_Prev_ART+"\"  onblur=\"autosave('f9_B_Prev_ART');\"  oninput=\"B('f9_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m14_B_Prev_ART\" id=\"m14_B_Prev_ART\" value=\""+m14_B_Prev_ART+"\" onblur=\"autosave('m14_B_Prev_ART');\"  oninput=\"B('m14_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f14_B_Prev_ART\" id=\"f14_B_Prev_ART\" value=\""+f14_B_Prev_ART+"\" onblur=\"autosave('f14_B_Prev_ART');\"  oninput=\"B('f14_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m19_B_Prev_ART\" id=\"m19_B_Prev_ART\" value=\""+m19_B_Prev_ART+"\"  onblur=\"autosave('m19_B_Prev_ART');\"  oninput=\"B('m19_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f19_B_Prev_ART\" id=\"f19_B_Prev_ART\" value=\""+f19_B_Prev_ART+"\"  onblur=\"autosave('f19_B_Prev_ART');\"  oninput=\"B('f19_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m24_B_Prev_ART\" id=\"m24_B_Prev_ART\" value=\""+m24_B_Prev_ART+"\"  onblur=\"autosave('m24_B_Prev_ART');\"  oninput=\"B('m24_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f24_B_Prev_ART\" id=\"f24_B_Prev_ART\" value=\""+f24_B_Prev_ART+"\"  onblur=\"autosave('f24_B_Prev_ART');\"  oninput=\"B('f24_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m29_B_Prev_ART\" id=\"m29_B_Prev_ART\" value=\""+m29_B_Prev_ART+"\"  onblur=\"autosave('m29_B_Prev_ART');\"  oninput=\"B('m29_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f29_B_Prev_ART\" id=\"f29_B_Prev_ART\" value=\""+f29_B_Prev_ART+"\"  onblur=\"autosave('f29_B_Prev_ART');\"  oninput=\"B('f29_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m34_B_Prev_ART\" id=\"m34_B_Prev_ART\" value=\""+m34_B_Prev_ART+"\"  onblur=\"autosave('m34_B_Prev_ART');\"  oninput=\"B('m34_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f34_B_Prev_ART\" id=\"f34_B_Prev_ART\" value=\""+f34_B_Prev_ART+"\"  onblur=\"autosave('f34_B_Prev_ART');\"  oninput=\"B('f34_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m39_B_Prev_ART\" id=\"m39_B_Prev_ART\" value=\""+m39_B_Prev_ART+"\"  onblur=\"autosave('m39_B_Prev_ART');\"  oninput=\"B('m39_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f39_B_Prev_ART\" id=\"f39_B_Prev_ART\" value=\""+f39_B_Prev_ART+"\"  onblur=\"autosave('f39_B_Prev_ART');\"  oninput=\"B('f39_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m49_B_Prev_ART\" id=\"m49_B_Prev_ART\" value=\""+m49_B_Prev_ART+"\"  onblur=\"autosave('m49_B_Prev_ART');\"  oninput=\"B('m49_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f49_B_Prev_ART\" id=\"f49_B_Prev_ART\" value=\""+f49_B_Prev_ART+"\"  onblur=\"autosave('f49_B_Prev_ART');\"  oninput=\"B('f49_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m50_B_Prev_ART\" id=\"m50_B_Prev_ART\" value=\""+m50_B_Prev_ART+"\"  onblur=\"autosave('m50_B_Prev_ART');\"  oninput=\"B('m50_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f50_B_Prev_ART\" id=\"f50_B_Prev_ART\" value=\""+f50_B_Prev_ART+"\"  onblur=\"autosave('f50_B_Prev_ART');\"  oninput=\"B('f50_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"tm_B_Prev_ART\" id=\"tm_B_Prev_ART\" value=\""+tm_B_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tm_B_Prev_ART');\"  oninput=\"B('tm_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"tf_B_Prev_ART\" id=\"tf_B_Prev_ART\" value=\""+tf_B_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tf_B_Prev_ART');\"  oninput=\"B('tf_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"GT_B_Prev_ART\" id=\"GT_B_Prev_ART\" value=\""+GT_B_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('GT_B_Prev_ART');\"  oninput=\"B('GT_B_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + "";
    C = ""
        + "<tr border=\"1px;\">"
        + "<td rowspan=\"2\" class=\"title\">C</td>"
        + "<td  rowspan=\"2\" class=\"title\">Number discontinued [TNC]</td>"
        
        + "<td>New on ART</td>"
        + "<td><input type=\"text\" name=\"m1_C_New_ART\" id=\"m1_C_New_ART\" value=\""+m1_C_New_ART+"\"  onblur=\"autosave('m1_C_New_ART');\"  oninput=\"C('m1_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f1_C_New_ART\" id=\"f1_C_New_ART\" value=\""+f1_C_New_ART+"\"  onblur=\"autosave('f1_C_New_ART');\"  oninput=\"C('f1_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m4_C_New_ART\" id=\"m4_C_New_ART\" value=\""+m4_C_New_ART+"\"  onblur=\"autosave('m4_C_New_ART');\"  oninput=\"C('m4_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f4_C_New_ART\" id=\"f4_C_New_ART\" value=\""+f4_C_New_ART+"\"  onblur=\"autosave('f4_C_New_ART');\"  oninput=\"C('f4_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m9_C_New_ART\" id=\"m9_C_New_ART\" value=\""+m9_C_New_ART+"\"  onblur=\"autosave('m9_C_New_ART');\"  oninput=\"C('m9_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f9_C_New_ART\" id=\"f9_C_New_ART\" value=\""+f9_C_New_ART+"\"  onblur=\"autosave('f9_C_New_ART');\"  oninput=\"C('f9_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m14_C_New_ART\" id=\"m14_C_New_ART\" value=\""+m14_C_New_ART+"\" onblur=\"autosave('m14_C_New_ART');\"  oninput=\"C('m14_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f14_C_New_ART\" id=\"f14_C_New_ART\" value=\""+f14_C_New_ART+"\" onblur=\"autosave('f14_C_New_ART');\"  oninput=\"C('f14_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m19_C_New_ART\" id=\"m19_C_New_ART\" value=\""+m19_C_New_ART+"\"  onblur=\"autosave('m19_C_New_ART');\"  oninput=\"C('m19_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f19_C_New_ART\" id=\"f19_C_New_ART\" value=\""+f19_C_New_ART+"\"  onblur=\"autosave('f19_C_New_ART');\"  oninput=\"C('f19_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m24_C_New_ART\" id=\"m24_C_New_ART\" value=\""+m24_C_New_ART+"\"  onblur=\"autosave('m24_C_New_ART');\"  oninput=\"C('m24_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f24_C_New_ART\" id=\"f24_C_New_ART\" value=\""+f24_C_New_ART+"\"  onblur=\"autosave('f24_C_New_ART');\"  oninput=\"C('f24_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m29_C_New_ART\" id=\"m29_C_New_ART\" value=\""+m29_C_New_ART+"\"  onblur=\"autosave('m29_C_New_ART');\"  oninput=\"C('m29_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f29_C_New_ART\" id=\"f29_C_New_ART\" value=\""+f29_C_New_ART+"\"  onblur=\"autosave('f29_C_New_ART');\"  oninput=\"C('f29_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m34_C_New_ART\" id=\"m34_C_New_ART\" value=\""+m34_C_New_ART+"\"  onblur=\"autosave('m34_C_New_ART');\"  oninput=\"C('m34_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f34_C_New_ART\" id=\"f34_C_New_ART\" value=\""+f34_C_New_ART+"\"  onblur=\"autosave('f34_C_New_ART');\"  oninput=\"C('f34_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m39_C_New_ART\" id=\"m39_C_New_ART\" value=\""+m39_C_New_ART+"\"  onblur=\"autosave('m39_C_New_ART');\"  oninput=\"C('m39_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f39_C_New_ART\" id=\"f39_C_New_ART\" value=\""+f39_C_New_ART+"\"  onblur=\"autosave('f39_C_New_ART');\"  oninput=\"C('f39_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m49_C_New_ART\" id=\"m49_C_New_ART\" value=\""+m49_C_New_ART+"\"  onblur=\"autosave('m49_C_New_ART');\"  oninput=\"C('m49_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f49_C_New_ART\" id=\"f49_C_New_ART\" value=\""+f49_C_New_ART+"\"  onblur=\"autosave('f49_C_New_ART');\"  oninput=\"C('f49_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m50_C_New_ART\" id=\"m50_C_New_ART\" value=\""+m50_C_New_ART+"\"  onblur=\"autosave('m50_C_New_ART');\"  oninput=\"C('m50_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f50_C_New_ART\" id=\"f50_C_New_ART\" value=\""+f50_C_New_ART+"\"  onblur=\"autosave('f50_C_New_ART');\"  oninput=\"C('f50_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"tm_C_New_ART\" id=\"tm_C_New_ART\" value=\""+tm_C_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tm_C_New_ART');\"  oninput=\"C('tm_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"tf_C_New_ART\" id=\"tf_C_New_ART\" value=\""+tf_C_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tf_C_New_ART');\"  oninput=\"C('tf_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"GT_C_New_ART\" id=\"GT_C_New_ART\" value=\""+GT_C_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('GT_C_New_ART');\"  oninput=\"C('GT_C_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            
        + "<tr>"
        + "<td>Previously on ART</td>"
        + "<td><input type=\"text\" name=\"m1_C_Prev_ART\" id=\"m1_C_Prev_ART\" value=\""+m1_C_Prev_ART+"\"  onblur=\"autosave('m1_C_Prev_ART');\"  oninput=\"C('m1_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f1_C_Prev_ART\" id=\"f1_C_Prev_ART\" value=\""+f1_C_Prev_ART+"\"  onblur=\"autosave('f1_C_Prev_ART');\"  oninput=\"C('f1_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m4_C_Prev_ART\" id=\"m4_C_Prev_ART\" value=\""+m4_C_Prev_ART+"\"  onblur=\"autosave('m4_C_Prev_ART');\"  oninput=\"C('m4_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f4_C_Prev_ART\" id=\"f4_C_Prev_ART\" value=\""+f4_C_Prev_ART+"\"  onblur=\"autosave('f4_C_Prev_ART');\"  oninput=\"C('f4_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m9_C_Prev_ART\" id=\"m9_C_Prev_ART\" value=\""+m9_C_Prev_ART+"\"  onblur=\"autosave('m9_C_Prev_ART');\"  oninput=\"C('m9_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f9_C_Prev_ART\" id=\"f9_C_Prev_ART\" value=\""+f9_C_Prev_ART+"\"  onblur=\"autosave('f9_C_Prev_ART');\"  oninput=\"C('f9_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m14_C_Prev_ART\" id=\"m14_C_Prev_ART\" value=\""+m14_C_Prev_ART+"\" onblur=\"autosave('m14_C_Prev_ART');\"  oninput=\"C('m14_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f14_C_Prev_ART\" id=\"f14_C_Prev_ART\" value=\""+f14_C_Prev_ART+"\" onblur=\"autosave('f14_C_Prev_ART');\"  oninput=\"C('f14_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m19_C_Prev_ART\" id=\"m19_C_Prev_ART\" value=\""+m19_C_Prev_ART+"\"  onblur=\"autosave('m19_C_Prev_ART');\"  oninput=\"C('m19_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f19_C_Prev_ART\" id=\"f19_C_Prev_ART\" value=\""+f19_C_Prev_ART+"\"  onblur=\"autosave('f19_C_Prev_ART');\"  oninput=\"C('f19_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m24_C_Prev_ART\" id=\"m24_C_Prev_ART\" value=\""+m24_C_Prev_ART+"\"  onblur=\"autosave('m24_C_Prev_ART');\"  oninput=\"C('m24_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f24_C_Prev_ART\" id=\"f24_C_Prev_ART\" value=\""+f24_C_Prev_ART+"\"  onblur=\"autosave('f24_C_Prev_ART');\"  oninput=\"C('f24_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m29_C_Prev_ART\" id=\"m29_C_Prev_ART\" value=\""+m29_C_Prev_ART+"\"  onblur=\"autosave('m29_C_Prev_ART');\"  oninput=\"C('m29_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f29_C_Prev_ART\" id=\"f29_C_Prev_ART\" value=\""+f29_C_Prev_ART+"\"  onblur=\"autosave('f29_C_Prev_ART');\"  oninput=\"C('f29_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m34_C_Prev_ART\" id=\"m34_C_Prev_ART\" value=\""+m34_C_Prev_ART+"\"  onblur=\"autosave('m34_C_Prev_ART');\"  oninput=\"C('m34_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f34_C_Prev_ART\" id=\"f34_C_Prev_ART\" value=\""+f34_C_Prev_ART+"\"  onblur=\"autosave('f34_C_Prev_ART');\"  oninput=\"C('f34_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m39_C_Prev_ART\" id=\"m39_C_Prev_ART\" value=\""+m39_C_Prev_ART+"\"  onblur=\"autosave('m39_C_Prev_ART');\"  oninput=\"C('m39_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f39_C_Prev_ART\" id=\"f39_C_Prev_ART\" value=\""+f39_C_Prev_ART+"\"  onblur=\"autosave('f39_C_Prev_ART');\"  oninput=\"C('f39_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m49_C_Prev_ART\" id=\"m49_C_Prev_ART\" value=\""+m49_C_Prev_ART+"\"  onblur=\"autosave('m49_C_Prev_ART');\"  oninput=\"C('m49_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f49_C_Prev_ART\" id=\"f49_C_Prev_ART\" value=\""+f49_C_Prev_ART+"\"  onblur=\"autosave('f49_C_Prev_ART');\"  oninput=\"C('f49_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m50_C_Prev_ART\" id=\"m50_C_Prev_ART\" value=\""+m50_C_Prev_ART+"\"  onblur=\"autosave('m50_C_Prev_ART');\"  oninput=\"C('m50_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f50_C_Prev_ART\" id=\"f50_C_Prev_ART\" value=\""+f50_C_Prev_ART+"\"  onblur=\"autosave('f50_C_Prev_ART');\"  oninput=\"C('f50_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"tm_C_Prev_ART\" id=\"tm_C_Prev_ART\" value=\""+tm_C_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tm_C_Prev_ART');\"  oninput=\"C('tm_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"tf_C_Prev_ART\" id=\"tf_C_Prev_ART\" value=\""+tf_C_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tf_C_Prev_ART');\"  oninput=\"C('tf_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"GT_C_Prev_ART\" id=\"GT_C_Prev_ART\" value=\""+GT_C_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('GT_C_Prev_ART');\"  oninput=\"C('GT_C_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + "";
    D = ""
        + "<tr border=\"1px;\">"
        + "<td rowspan=\"2\" class=\"title\">D</td>"
        + "<td  rowspan=\"2\" class=\"title\">Number lost to follow up [LTFU]</td>"
        
        + "<td>New on ART</td>"
        + "<td><input type=\"text\" name=\"m1_D_New_ART\" id=\"m1_D_New_ART\" value=\""+m1_D_New_ART+"\"  onblur=\"autosave('m1_D_New_ART');\"  oninput=\"D('m1_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f1_D_New_ART\" id=\"f1_D_New_ART\" value=\""+f1_D_New_ART+"\"  onblur=\"autosave('f1_D_New_ART');\"  oninput=\"D('f1_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m4_D_New_ART\" id=\"m4_D_New_ART\" value=\""+m4_D_New_ART+"\"  onblur=\"autosave('m4_D_New_ART');\"  oninput=\"D('m4_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f4_D_New_ART\" id=\"f4_D_New_ART\" value=\""+f4_D_New_ART+"\"  onblur=\"autosave('f4_D_New_ART');\"  oninput=\"D('f4_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m9_D_New_ART\" id=\"m9_D_New_ART\" value=\""+m9_D_New_ART+"\"  onblur=\"autosave('m9_D_New_ART');\"  oninput=\"D('m9_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f9_D_New_ART\" id=\"f9_D_New_ART\" value=\""+f9_D_New_ART+"\"  onblur=\"autosave('f9_D_New_ART');\"  oninput=\"D('f9_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m14_D_New_ART\" id=\"m14_D_New_ART\" value=\""+m14_D_New_ART+"\" onblur=\"autosave('m14_D_New_ART');\"  oninput=\"D('m14_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f14_D_New_ART\" id=\"f14_D_New_ART\" value=\""+f14_D_New_ART+"\" onblur=\"autosave('f14_D_New_ART');\"  oninput=\"D('f14_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m19_D_New_ART\" id=\"m19_D_New_ART\" value=\""+m19_D_New_ART+"\"  onblur=\"autosave('m19_D_New_ART');\"  oninput=\"D('m19_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f19_D_New_ART\" id=\"f19_D_New_ART\" value=\""+f19_D_New_ART+"\"  onblur=\"autosave('f19_D_New_ART');\"  oninput=\"D('f19_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m24_D_New_ART\" id=\"m24_D_New_ART\" value=\""+m24_D_New_ART+"\"  onblur=\"autosave('m24_D_New_ART');\"  oninput=\"D('m24_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f24_D_New_ART\" id=\"f24_D_New_ART\" value=\""+f24_D_New_ART+"\"  onblur=\"autosave('f24_D_New_ART');\"  oninput=\"D('f24_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m29_D_New_ART\" id=\"m29_D_New_ART\" value=\""+m29_D_New_ART+"\"  onblur=\"autosave('m29_D_New_ART');\"  oninput=\"D('m29_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f29_D_New_ART\" id=\"f29_D_New_ART\" value=\""+f29_D_New_ART+"\"  onblur=\"autosave('f29_D_New_ART');\"  oninput=\"D('f29_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m34_D_New_ART\" id=\"m34_D_New_ART\" value=\""+m34_D_New_ART+"\"  onblur=\"autosave('m34_D_New_ART');\"  oninput=\"D('m34_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f34_D_New_ART\" id=\"f34_D_New_ART\" value=\""+f34_D_New_ART+"\"  onblur=\"autosave('f34_D_New_ART');\"  oninput=\"D('f34_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m39_D_New_ART\" id=\"m39_D_New_ART\" value=\""+m39_D_New_ART+"\"  onblur=\"autosave('m39_D_New_ART');\"  oninput=\"D('m39_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f39_D_New_ART\" id=\"f39_D_New_ART\" value=\""+f39_D_New_ART+"\"  onblur=\"autosave('f39_D_New_ART');\"  oninput=\"D('f39_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m49_D_New_ART\" id=\"m49_D_New_ART\" value=\""+m49_D_New_ART+"\"  onblur=\"autosave('m49_D_New_ART');\"  oninput=\"D('m49_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f49_D_New_ART\" id=\"f49_D_New_ART\" value=\""+f49_D_New_ART+"\"  onblur=\"autosave('f49_D_New_ART');\"  oninput=\"D('f49_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m50_D_New_ART\" id=\"m50_D_New_ART\" value=\""+m50_D_New_ART+"\"  onblur=\"autosave('m50_D_New_ART');\"  oninput=\"D('m50_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f50_D_New_ART\" id=\"f50_D_New_ART\" value=\""+f50_D_New_ART+"\"  onblur=\"autosave('f50_D_New_ART');\"  oninput=\"D('f50_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"tm_D_New_ART\" id=\"tm_D_New_ART\" value=\""+tm_D_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tm_D_New_ART');\"  oninput=\"D('tm_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"tf_D_New_ART\" id=\"tf_D_New_ART\" value=\""+tf_D_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tf_D_New_ART');\"  oninput=\"D('tf_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"GT_D_New_ART\" id=\"GT_D_New_ART\" value=\""+GT_D_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('GT_D_New_ART');\"  oninput=\"D('GT_D_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            
        + "<tr>"
        + "<td>Previously on ART</td>"
        + "<td><input type=\"text\" name=\"m1_D_Prev_ART\" id=\"m1_D_Prev_ART\" value=\""+m1_D_Prev_ART+"\"  onblur=\"autosave('m1_D_Prev_ART');\"  oninput=\"D('m1_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f1_D_Prev_ART\" id=\"f1_D_Prev_ART\" value=\""+f1_D_Prev_ART+"\"  onblur=\"autosave('f1_D_Prev_ART');\"  oninput=\"D('f1_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m4_D_Prev_ART\" id=\"m4_D_Prev_ART\" value=\""+m4_D_Prev_ART+"\"  onblur=\"autosave('m4_D_Prev_ART');\"  oninput=\"D('m4_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f4_D_Prev_ART\" id=\"f4_D_Prev_ART\" value=\""+f4_D_Prev_ART+"\"  onblur=\"autosave('f4_D_Prev_ART');\"  oninput=\"D('f4_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m9_D_Prev_ART\" id=\"m9_D_Prev_ART\" value=\""+m9_D_Prev_ART+"\"  onblur=\"autosave('m9_D_Prev_ART');\"  oninput=\"D('m9_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f9_D_Prev_ART\" id=\"f9_D_Prev_ART\" value=\""+f9_D_Prev_ART+"\"  onblur=\"autosave('f9_D_Prev_ART');\"  oninput=\"D('f9_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m14_D_Prev_ART\" id=\"m14_D_Prev_ART\" value=\""+m14_D_Prev_ART+"\" onblur=\"autosave('m14_D_Prev_ART');\"  oninput=\"D('m14_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f14_D_Prev_ART\" id=\"f14_D_Prev_ART\" value=\""+f14_D_Prev_ART+"\" onblur=\"autosave('f14_D_Prev_ART');\"  oninput=\"D('f14_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m19_D_Prev_ART\" id=\"m19_D_Prev_ART\" value=\""+m19_D_Prev_ART+"\"  onblur=\"autosave('m19_D_Prev_ART');\"  oninput=\"D('m19_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f19_D_Prev_ART\" id=\"f19_D_Prev_ART\" value=\""+f19_D_Prev_ART+"\"  onblur=\"autosave('f19_D_Prev_ART');\"  oninput=\"D('f19_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m24_D_Prev_ART\" id=\"m24_D_Prev_ART\" value=\""+m24_D_Prev_ART+"\"  onblur=\"autosave('m24_D_Prev_ART');\"  oninput=\"D('m24_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f24_D_Prev_ART\" id=\"f24_D_Prev_ART\" value=\""+f24_D_Prev_ART+"\"  onblur=\"autosave('f24_D_Prev_ART');\"  oninput=\"D('f24_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m29_D_Prev_ART\" id=\"m29_D_Prev_ART\" value=\""+m29_D_Prev_ART+"\"  onblur=\"autosave('m29_D_Prev_ART');\"  oninput=\"D('m29_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f29_D_Prev_ART\" id=\"f29_D_Prev_ART\" value=\""+f29_D_Prev_ART+"\"  onblur=\"autosave('f29_D_Prev_ART');\"  oninput=\"D('f29_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m34_D_Prev_ART\" id=\"m34_D_Prev_ART\" value=\""+m34_D_Prev_ART+"\"  onblur=\"autosave('m34_D_Prev_ART');\"  oninput=\"D('m34_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f34_D_Prev_ART\" id=\"f34_D_Prev_ART\" value=\""+f34_D_Prev_ART+"\"  onblur=\"autosave('f34_D_Prev_ART');\"  oninput=\"D('f34_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m39_D_Prev_ART\" id=\"m39_D_Prev_ART\" value=\""+m39_D_Prev_ART+"\"  onblur=\"autosave('m39_D_Prev_ART');\"  oninput=\"D('m39_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f39_D_Prev_ART\" id=\"f39_D_Prev_ART\" value=\""+f39_D_Prev_ART+"\"  onblur=\"autosave('f39_D_Prev_ART');\"  oninput=\"D('f39_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m49_D_Prev_ART\" id=\"m49_D_Prev_ART\" value=\""+m49_D_Prev_ART+"\"  onblur=\"autosave('m49_D_Prev_ART');\"  oninput=\"D('m49_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f49_D_Prev_ART\" id=\"f49_D_Prev_ART\" value=\""+f49_D_Prev_ART+"\"  onblur=\"autosave('f49_D_Prev_ART');\"  oninput=\"D('f49_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m50_D_Prev_ART\" id=\"m50_D_Prev_ART\" value=\""+m50_D_Prev_ART+"\"  onblur=\"autosave('m50_D_Prev_ART');\"  oninput=\"D('m50_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f50_D_Prev_ART\" id=\"f50_D_Prev_ART\" value=\""+f50_D_Prev_ART+"\"  onblur=\"autosave('f50_D_Prev_ART');\"  oninput=\"D('f50_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"tm_D_Prev_ART\" id=\"tm_D_Prev_ART\" value=\""+tm_D_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tm_D_Prev_ART');\"  oninput=\"D('tm_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"tf_D_Prev_ART\" id=\"tf_D_Prev_ART\" value=\""+tf_D_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tf_D_Prev_ART');\"  oninput=\"D('tf_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"GT_D_Prev_ART\" id=\"GT_D_Prev_ART\" value=\""+GT_D_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('GT_D_Prev_ART');\"  oninput=\"D('GT_D_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + "";
    E = ""
        + "<tr border=\"1px;\">"
        + "<td rowspan=\"2\" class=\"title\">E</td>"
        + "<td  rowspan=\"2\" class=\"title\">Number died [D]</td>"
        
        + "<td>New on ART</td>"
        + "<td><input type=\"text\" name=\"m1_D_New_ART\" id=\"m1_D_New_ART\" value=\""+m1_D_New_ART+"\"  onblur=\"autosave('m1_D_New_ART');\"  oninput=\"E('m1_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f1_D_New_ART\" id=\"f1_D_New_ART\" value=\""+f1_D_New_ART+"\"  onblur=\"autosave('f1_D_New_ART');\"  oninput=\"E('f1_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m4_D_New_ART\" id=\"m4_D_New_ART\" value=\""+m4_D_New_ART+"\"  onblur=\"autosave('m4_D_New_ART');\"  oninput=\"E('m4_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f4_D_New_ART\" id=\"f4_D_New_ART\" value=\""+f4_D_New_ART+"\"  onblur=\"autosave('f4_D_New_ART');\"  oninput=\"E('f4_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m9_D_New_ART\" id=\"m9_D_New_ART\" value=\""+m9_D_New_ART+"\"  onblur=\"autosave('m9_D_New_ART');\"  oninput=\"E('m9_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f9_D_New_ART\" id=\"f9_D_New_ART\" value=\""+f9_D_New_ART+"\"  onblur=\"autosave('f9_D_New_ART');\"  oninput=\"E('f9_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m14_D_New_ART\" id=\"m14_D_New_ART\" value=\""+m14_D_New_ART+"\" onblur=\"autosave('m14_D_New_ART');\"  oninput=\"E('m14_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f14_D_New_ART\" id=\"f14_D_New_ART\" value=\""+f14_D_New_ART+"\" onblur=\"autosave('f14_D_New_ART');\"  oninput=\"E('f14_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m19_D_New_ART\" id=\"m19_D_New_ART\" value=\""+m19_D_New_ART+"\"  onblur=\"autosave('m19_D_New_ART');\"  oninput=\"E('m19_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f19_D_New_ART\" id=\"f19_D_New_ART\" value=\""+f19_D_New_ART+"\"  onblur=\"autosave('f19_D_New_ART');\"  oninput=\"E('f19_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m24_D_New_ART\" id=\"m24_D_New_ART\" value=\""+m24_D_New_ART+"\"  onblur=\"autosave('m24_D_New_ART');\"  oninput=\"E('m24_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f24_D_New_ART\" id=\"f24_D_New_ART\" value=\""+f24_D_New_ART+"\"  onblur=\"autosave('f24_D_New_ART');\"  oninput=\"E('f24_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m29_D_New_ART\" id=\"m29_D_New_ART\" value=\""+m29_D_New_ART+"\"  onblur=\"autosave('m29_D_New_ART');\"  oninput=\"E('m29_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f29_D_New_ART\" id=\"f29_D_New_ART\" value=\""+f29_D_New_ART+"\"  onblur=\"autosave('f29_D_New_ART');\"  oninput=\"E('f29_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m34_D_New_ART\" id=\"m34_D_New_ART\" value=\""+m34_D_New_ART+"\"  onblur=\"autosave('m34_D_New_ART');\"  oninput=\"E('m34_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f34_D_New_ART\" id=\"f34_D_New_ART\" value=\""+f34_D_New_ART+"\"  onblur=\"autosave('f34_D_New_ART');\"  oninput=\"E('f34_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m39_D_New_ART\" id=\"m39_D_New_ART\" value=\""+m39_D_New_ART+"\"  onblur=\"autosave('m39_D_New_ART');\"  oninput=\"E('m39_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f39_D_New_ART\" id=\"f39_D_New_ART\" value=\""+f39_D_New_ART+"\"  onblur=\"autosave('f39_D_New_ART');\"  oninput=\"E('f39_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m49_D_New_ART\" id=\"m49_D_New_ART\" value=\""+m49_D_New_ART+"\"  onblur=\"autosave('m49_D_New_ART');\"  oninput=\"E('m49_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f49_D_New_ART\" id=\"f49_D_New_ART\" value=\""+f49_D_New_ART+"\"  onblur=\"autosave('f49_D_New_ART');\"  oninput=\"E('f49_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m50_D_New_ART\" id=\"m50_D_New_ART\" value=\""+m50_D_New_ART+"\"  onblur=\"autosave('m50_D_New_ART');\"  oninput=\"E('m50_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f50_D_New_ART\" id=\"f50_D_New_ART\" value=\""+f50_D_New_ART+"\"  onblur=\"autosave('f50_D_New_ART');\"  oninput=\"E('f50_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"tm_D_New_ART\" id=\"tm_D_New_ART\" value=\""+tm_D_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tm_D_New_ART');\"  oninput=\"E('tm_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"tf_D_New_ART\" id=\"tf_D_New_ART\" value=\""+tf_D_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tf_D_New_ART');\"  oninput=\"E('tf_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"GT_D_New_ART\" id=\"GT_D_New_ART\" value=\""+GT_D_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('GT_D_New_ART');\"  oninput=\"E('GT_E_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            
        + "<tr>"
        + "<td>Previously on ART</td>"
        + "<td><input type=\"text\" name=\"m1_D_Prev_ART\" id=\"m1_D_Prev_ART\" value=\""+m1_D_Prev_ART+"\"  onblur=\"autosave('m1_D_Prev_ART');\"  oninput=\"E('m1_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f1_D_Prev_ART\" id=\"f1_D_Prev_ART\" value=\""+f1_D_Prev_ART+"\"  onblur=\"autosave('f1_D_Prev_ART');\"  oninput=\"E('f1_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m4_D_Prev_ART\" id=\"m4_D_Prev_ART\" value=\""+m4_D_Prev_ART+"\"  onblur=\"autosave('m4_D_Prev_ART');\"  oninput=\"E('m4_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f4_D_Prev_ART\" id=\"f4_D_Prev_ART\" value=\""+f4_D_Prev_ART+"\"  onblur=\"autosave('f4_D_Prev_ART');\"  oninput=\"E('f4_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m9_D_Prev_ART\" id=\"m9_D_Prev_ART\" value=\""+m9_D_Prev_ART+"\"  onblur=\"autosave('m9_D_Prev_ART');\"  oninput=\"E('m9_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f9_D_Prev_ART\" id=\"f9_D_Prev_ART\" value=\""+f9_D_Prev_ART+"\"  onblur=\"autosave('f9_D_Prev_ART');\"  oninput=\"E('f9_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m14_D_Prev_ART\" id=\"m14_D_Prev_ART\" value=\""+m14_D_Prev_ART+"\" onblur=\"autosave('m14_D_Prev_ART');\"  oninput=\"E('m14_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f14_D_Prev_ART\" id=\"f14_D_Prev_ART\" value=\""+f14_D_Prev_ART+"\" onblur=\"autosave('f14_D_Prev_ART');\"  oninput=\"E('f14_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m19_D_Prev_ART\" id=\"m19_D_Prev_ART\" value=\""+m19_D_Prev_ART+"\"  onblur=\"autosave('m19_D_Prev_ART');\"  oninput=\"E('m19_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f19_D_Prev_ART\" id=\"f19_D_Prev_ART\" value=\""+f19_D_Prev_ART+"\"  onblur=\"autosave('f19_D_Prev_ART');\"  oninput=\"E('f19_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m24_D_Prev_ART\" id=\"m24_D_Prev_ART\" value=\""+m24_D_Prev_ART+"\"  onblur=\"autosave('m24_D_Prev_ART');\"  oninput=\"E('m24_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f24_D_Prev_ART\" id=\"f24_D_Prev_ART\" value=\""+f24_D_Prev_ART+"\"  onblur=\"autosave('f24_D_Prev_ART');\"  oninput=\"E('f24_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m29_D_Prev_ART\" id=\"m29_D_Prev_ART\" value=\""+m29_D_Prev_ART+"\"  onblur=\"autosave('m29_D_Prev_ART');\"  oninput=\"E('m29_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f29_D_Prev_ART\" id=\"f29_D_Prev_ART\" value=\""+f29_D_Prev_ART+"\"  onblur=\"autosave('f29_D_Prev_ART');\"  oninput=\"E('f29_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m34_D_Prev_ART\" id=\"m34_D_Prev_ART\" value=\""+m34_D_Prev_ART+"\"  onblur=\"autosave('m34_D_Prev_ART');\"  oninput=\"E('m34_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f34_D_Prev_ART\" id=\"f34_D_Prev_ART\" value=\""+f34_D_Prev_ART+"\"  onblur=\"autosave('f34_D_Prev_ART');\"  oninput=\"E('f34_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m39_D_Prev_ART\" id=\"m39_D_Prev_ART\" value=\""+m39_D_Prev_ART+"\"  onblur=\"autosave('m39_D_Prev_ART');\"  oninput=\"E('m39_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f39_D_Prev_ART\" id=\"f39_D_Prev_ART\" value=\""+f39_D_Prev_ART+"\"  onblur=\"autosave('f39_D_Prev_ART');\"  oninput=\"E('f39_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m49_D_Prev_ART\" id=\"m49_D_Prev_ART\" value=\""+m49_D_Prev_ART+"\"  onblur=\"autosave('m49_D_Prev_ART');\"  oninput=\"E('m49_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f49_D_Prev_ART\" id=\"f49_D_Prev_ART\" value=\""+f49_D_Prev_ART+"\"  onblur=\"autosave('f49_D_Prev_ART');\"  oninput=\"E('f49_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m50_D_Prev_ART\" id=\"m50_D_Prev_ART\" value=\""+m50_D_Prev_ART+"\"  onblur=\"autosave('m50_D_Prev_ART');\"  oninput=\"E('m50_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f50_D_Prev_ART\" id=\"f50_D_Prev_ART\" value=\""+f50_D_Prev_ART+"\"  onblur=\"autosave('f50_D_Prev_ART');\"  oninput=\"E('f50_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"tm_D_Prev_ART\" id=\"tm_D_Prev_ART\" value=\""+tm_D_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tm_D_Prev_ART');\"  oninput=\"E('tm_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"tf_D_Prev_ART\" id=\"tf_D_Prev_ART\" value=\""+tf_D_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tf_D_Prev_ART');\"  oninput=\"E('tf_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"GT_D_Prev_ART\" id=\"GT_D_Prev_ART\" value=\""+GT_D_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('GT_D_Prev_ART');\"  oninput=\"E('GT_E_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + "";
    F = ""
        + "<tr border=\"1px;\">"
        + "<td rowspan=\"2\" class=\"title\">F</td>"
        + "<td  rowspan=\"2\" class=\"title\">Number transferred out [TO]</td>"
        
        + "<td>New on ART</td>"
        + "<td><input type=\"text\" name=\"m1_F_New_ART\" id=\"m1_F_New_ART\" value=\""+m1_F_New_ART+"\"  onblur=\"autosave('m1_F_New_ART');\"  oninput=\"F('m1_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f1_F_New_ART\" id=\"f1_F_New_ART\" value=\""+f1_F_New_ART+"\"  onblur=\"autosave('f1_F_New_ART');\"  oninput=\"F('f1_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m4_F_New_ART\" id=\"m4_F_New_ART\" value=\""+m4_F_New_ART+"\"  onblur=\"autosave('m4_F_New_ART');\"  oninput=\"F('m4_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f4_F_New_ART\" id=\"f4_F_New_ART\" value=\""+f4_F_New_ART+"\"  onblur=\"autosave('f4_F_New_ART');\"  oninput=\"F('f4_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m9_F_New_ART\" id=\"m9_F_New_ART\" value=\""+m9_F_New_ART+"\"  onblur=\"autosave('m9_F_New_ART');\"  oninput=\"F('m9_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f9_F_New_ART\" id=\"f9_F_New_ART\" value=\""+f9_F_New_ART+"\"  onblur=\"autosave('f9_F_New_ART');\"  oninput=\"F('f9_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m14_F_New_ART\" id=\"m14_F_New_ART\" value=\""+m14_F_New_ART+"\" onblur=\"autosave('m14_F_New_ART');\"  oninput=\"F('m14_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f14_F_New_ART\" id=\"f14_F_New_ART\" value=\""+f14_F_New_ART+"\" onblur=\"autosave('f14_F_New_ART');\"  oninput=\"F('f14_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m19_F_New_ART\" id=\"m19_F_New_ART\" value=\""+m19_F_New_ART+"\"  onblur=\"autosave('m19_F_New_ART');\"  oninput=\"F('m19_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f19_F_New_ART\" id=\"f19_F_New_ART\" value=\""+f19_F_New_ART+"\"  onblur=\"autosave('f19_F_New_ART');\"  oninput=\"F('f19_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m24_F_New_ART\" id=\"m24_F_New_ART\" value=\""+m24_F_New_ART+"\"  onblur=\"autosave('m24_F_New_ART');\"  oninput=\"F('m24_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f24_F_New_ART\" id=\"f24_F_New_ART\" value=\""+f24_F_New_ART+"\"  onblur=\"autosave('f24_F_New_ART');\"  oninput=\"F('f24_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m29_F_New_ART\" id=\"m29_F_New_ART\" value=\""+m29_F_New_ART+"\"  onblur=\"autosave('m29_F_New_ART');\"  oninput=\"F('m29_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f29_F_New_ART\" id=\"f29_F_New_ART\" value=\""+f29_F_New_ART+"\"  onblur=\"autosave('f29_F_New_ART');\"  oninput=\"F('f29_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m34_F_New_ART\" id=\"m34_F_New_ART\" value=\""+m34_F_New_ART+"\"  onblur=\"autosave('m34_F_New_ART');\"  oninput=\"F('m34_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f34_F_New_ART\" id=\"f34_F_New_ART\" value=\""+f34_F_New_ART+"\"  onblur=\"autosave('f34_F_New_ART');\"  oninput=\"F('f34_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m39_F_New_ART\" id=\"m39_F_New_ART\" value=\""+m39_F_New_ART+"\"  onblur=\"autosave('m39_F_New_ART');\"  oninput=\"F('m39_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f39_F_New_ART\" id=\"f39_F_New_ART\" value=\""+f39_F_New_ART+"\"  onblur=\"autosave('f39_F_New_ART');\"  oninput=\"F('f39_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m49_F_New_ART\" id=\"m49_F_New_ART\" value=\""+m49_F_New_ART+"\"  onblur=\"autosave('m49_F_New_ART');\"  oninput=\"F('m49_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f49_F_New_ART\" id=\"f49_F_New_ART\" value=\""+f49_F_New_ART+"\"  onblur=\"autosave('f49_F_New_ART');\"  oninput=\"F('f49_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m50_F_New_ART\" id=\"m50_F_New_ART\" value=\""+m50_F_New_ART+"\"  onblur=\"autosave('m50_F_New_ART');\"  oninput=\"F('m50_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f50_F_New_ART\" id=\"f50_F_New_ART\" value=\""+f50_F_New_ART+"\"  onblur=\"autosave('f50_F_New_ART');\"  oninput=\"F('f50_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"tm_F_New_ART\" id=\"tm_F_New_ART\" value=\""+tm_F_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tm_F_New_ART');\"  oninput=\"F('tm_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"tf_F_New_ART\" id=\"tf_F_New_ART\" value=\""+tf_F_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tf_F_New_ART');\"  oninput=\"F('tf_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"GT_F_New_ART\" id=\"GT_F_New_ART\" value=\""+GT_F_New_ART+"\" tabindex=\"-1\"  onblur=\"autosave('GT_F_New_ART');\"  oninput=\"F('GT_F_New_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            
        + "<tr>"
        + "<td>Previously on ART</td>"
        + "<td><input type=\"text\" name=\"m1_F_Prev_ART\" id=\"m1_F_Prev_ART\" value=\""+m1_F_Prev_ART+"\"  onblur=\"autosave('m1_F_Prev_ART');\"  oninput=\"F('m1_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f1_F_Prev_ART\" id=\"f1_F_Prev_ART\" value=\""+f1_F_Prev_ART+"\"  onblur=\"autosave('f1_F_Prev_ART');\"  oninput=\"F('f1_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m4_F_Prev_ART\" id=\"m4_F_Prev_ART\" value=\""+m4_F_Prev_ART+"\"  onblur=\"autosave('m4_F_Prev_ART');\"  oninput=\"F('m4_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f4_F_Prev_ART\" id=\"f4_F_Prev_ART\" value=\""+f4_F_Prev_ART+"\"  onblur=\"autosave('f4_F_Prev_ART');\"  oninput=\"F('f4_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m9_F_Prev_ART\" id=\"m9_F_Prev_ART\" value=\""+m9_F_Prev_ART+"\"  onblur=\"autosave('m9_F_Prev_ART');\"  oninput=\"F('m9_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f9_F_Prev_ART\" id=\"f9_F_Prev_ART\" value=\""+f9_F_Prev_ART+"\"  onblur=\"autosave('f9_F_Prev_ART');\"  oninput=\"F('f9_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m14_F_Prev_ART\" id=\"m14_F_Prev_ART\" value=\""+m14_F_Prev_ART+"\" onblur=\"autosave('m14_F_Prev_ART');\"  oninput=\"F('m14_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f14_F_Prev_ART\" id=\"f14_F_Prev_ART\" value=\""+f14_F_Prev_ART+"\" onblur=\"autosave('f14_F_Prev_ART');\"  oninput=\"F('f14_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m19_F_Prev_ART\" id=\"m19_F_Prev_ART\" value=\""+m19_F_Prev_ART+"\"  onblur=\"autosave('m19_F_Prev_ART');\"  oninput=\"F('m19_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f19_F_Prev_ART\" id=\"f19_F_Prev_ART\" value=\""+f19_F_Prev_ART+"\"  onblur=\"autosave('f19_F_Prev_ART');\"  oninput=\"F('f19_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m24_F_Prev_ART\" id=\"m24_F_Prev_ART\" value=\""+m24_F_Prev_ART+"\"  onblur=\"autosave('m24_F_Prev_ART');\"  oninput=\"F('m24_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f24_F_Prev_ART\" id=\"f24_F_Prev_ART\" value=\""+f24_F_Prev_ART+"\"  onblur=\"autosave('f24_F_Prev_ART');\"  oninput=\"F('f24_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m29_F_Prev_ART\" id=\"m29_F_Prev_ART\" value=\""+m29_F_Prev_ART+"\"  onblur=\"autosave('m29_F_Prev_ART');\"  oninput=\"F('m29_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f29_F_Prev_ART\" id=\"f29_F_Prev_ART\" value=\""+f29_F_Prev_ART+"\"  onblur=\"autosave('f29_F_Prev_ART');\"  oninput=\"F('f29_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m34_F_Prev_ART\" id=\"m34_F_Prev_ART\" value=\""+m34_F_Prev_ART+"\"  onblur=\"autosave('m34_F_Prev_ART');\"  oninput=\"F('m34_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f34_F_Prev_ART\" id=\"f34_F_Prev_ART\" value=\""+f34_F_Prev_ART+"\"  onblur=\"autosave('f34_F_Prev_ART');\"  oninput=\"F('f34_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m39_F_Prev_ART\" id=\"m39_F_Prev_ART\" value=\""+m39_F_Prev_ART+"\"  onblur=\"autosave('m39_F_Prev_ART');\"  oninput=\"F('m39_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f39_F_Prev_ART\" id=\"f39_F_Prev_ART\" value=\""+f39_F_Prev_ART+"\"  onblur=\"autosave('f39_F_Prev_ART');\"  oninput=\"F('f39_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m49_F_Prev_ART\" id=\"m49_F_Prev_ART\" value=\""+m49_F_Prev_ART+"\"  onblur=\"autosave('m49_F_Prev_ART');\"  oninput=\"F('m49_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f49_F_Prev_ART\" id=\"f49_F_Prev_ART\" value=\""+f49_F_Prev_ART+"\"  onblur=\"autosave('f49_F_Prev_ART');\"  oninput=\"F('f49_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"m50_F_Prev_ART\" id=\"m50_F_Prev_ART\" value=\""+m50_F_Prev_ART+"\"  onblur=\"autosave('m50_F_Prev_ART');\"  oninput=\"F('m50_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"f50_F_Prev_ART\" id=\"f50_F_Prev_ART\" value=\""+f50_F_Prev_ART+"\"  onblur=\"autosave('f50_F_Prev_ART');\"  oninput=\"F('f50_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"tm_F_Prev_ART\" id=\"tm_F_Prev_ART\" value=\""+tm_F_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tm_F_Prev_ART');\"  oninput=\"F('tm_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"tf_F_Prev_ART\" id=\"tf_F_Prev_ART\" value=\""+tf_F_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('tf_F_Prev_ART');\"  oninput=\"F('tf_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"GT_F_Prev_ART\" id=\"GT_F_Prev_ART\" value=\""+GT_F_Prev_ART+"\" tabindex=\"-1\"  onblur=\"autosave('GT_F_Prev_ART');\"  oninput=\"F('GT_F_Prev_ART'); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
            + "</table>";  
    
    
    
    
    
    
    
    
    
    
     //end of code  
     
     
      if (session.getAttribute("forms_holder") != null) {
     if (session.getAttribute("forms_holder").toString().contains("TB")) {
        output=enterdby+Header+A+B+C+D+E+F;
        if(isLocked.equals("1")){
            output += "<div class='form-actions' style=\"text-align:center;\"><input type='submit' disabled class='btn red' value='Form Locked' name='validate' id='validate'/></div><span id='formstatus' style='display:none;'>" + validity + " </span><span id='rc' style='display:none;'>"+label+" </span><span id='ufs' style='display:none;'>"+unvalidatedLink+"</span>";
        }
        else{
            output += "<div class='form-actions' style=\"text-align:center;\"><input type='submit' class='btn blue' value='Save IPT Data' name='validate' id='validate'/></div><span id='formstatus' style='display:none;'>" + validity + " </span><span id='rc' style='display:none;'>"+label+" </span><span id='ufs' style='display:none;'>"+unvalidatedLink+"</span>";
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
