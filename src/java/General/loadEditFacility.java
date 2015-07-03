/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package General;

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
 * @author Geofrey Nyabuto
 */
public class loadEditFacility extends HttpServlet {
HttpSession session;
String id,name,county,sub_county,mfl,sp_id,htc,fp,pmtct,eid,art,vmmc,nutrition,gsn,lab,fp_integration,
        care_dsd,art_dsd,maternity,art_support,pmtct_support,htc_support1,kmmp,gender,pep,blood_safety,tb;
String data;
String [] ones={"0","1"};
String [] dsd_ta={"DSD","TA"};
String data_htc,data_fp,data_pmtct,data_eid,data_art,data_vmmc,data_nutrition,data_gsn,data_lab,data_fp_integration,
        data_care_dsd,data_art_dsd,data_maternity,data_art_support,data_pmtct_support,data_htc_support1,data_kmmp,data_gender,data_pep,data_blood_safety,data_tb;

String htc_checker,fp_checker,pmtct_checker,eid_checker,art_checker,vmmc_checker,nutrition_checker,gsn_checker,lab_checker,fp_integration_checker,
        care_dsd_checker,art_dsd_checker,maternity_checker,art_support_checker,pmtct_support_checker,htc_support1_checker,kmmp_checker,gender_checker,pep_checker,blood_safety_checker,tb_checker;

String oneslabel;
int position;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          session=request.getSession();
          dbConn conn = new dbConn();
          
          data="<table>";position=0;
          
          
data+="<thead><tr>"
+ "<th>NO</th>"
+ "<th>County</th>"
+ "<th>District</th>"
+ "<th style=\"width: 150px;\">Facility</th>"
 + "<th>MFL Code</th>"
+ "<th>SP ID</th>"               
+ "<th>HTC</th>"
+ "<th>FP</th>"
+ "<th>PMTCT</th>"
+ "<th>EID</th>"
+ "<th>ART</th>"
+ "<th>VMMC</th>"
+ "<th>Nutrition</th>"
+ "<th>Care DSD</th>"
+ "<th>ART DSD</th>"
+ "<th>KMMP</th>"
+ "<th>Gender</th>"
+ "<th>PEP</th>"
//+ "<th>Blood Safety</th>"
+ "<th>TB</th>"
+ "<th>GSN</th>"
+ "<th>Lab</th>"
+ "<th>FP Integ<br>ration</th>"
+ "<th>MATER<br>NITY</th>"
+ "<th>ART Support</th>"
+ "<th>PMTCT Support</th>"
+ "<th>HTC Support 1</th>"
        + "</tr></thead>";
          
          
          
          
          String getFacilities="SELECT county.County,district.DistrictNom,subpartnera.SubPartnerID,subpartnera.subPartnerNom,subpartnera.CentreSanteId,"
  + "subpartnera.SP_ID,subpartnera.HTC,subpartnera.FP,subpartnera.PMTCT,subpartnera.EID,subpartnera.ART,subpartnera.VMMC,"
  + "subpartnera.Nutrition,subpartnera.GSN,subpartnera.Lab,subpartnera.FP_Integration,subpartnera.Care_DSD,subpartnera.ART_DSD,"
  + "subpartnera.Maternity,subpartnera.ART_Support,subpartnera.PMTCT_Support,subpartnera.HTC_Support1,subpartnera.KMMP,"
  + "subpartnera.Gender,subpartnera.PEP,subpartnera.Blood_Safety,subpartnera.TB"
                  + ""
                  + ""
+ " FROM subpartnera JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON district.countyID=county.CountyID "
                  + " ORDER BY subpartnera.subPartnerNom ";
            
 conn.rs=conn.st.executeQuery(getFacilities);
 while(conn.rs.next()){
  htc_checker=fp_checker=pmtct_checker=eid_checker=art_checker=vmmc_checker=nutrition_checker=gsn_checker=lab_checker=fp_integration_checker=
        care_dsd_checker=art_dsd_checker=maternity_checker=kmmp_checker=gender_checker=pep_checker=blood_safety_checker=tb_checker="";
     
  data_htc=data_fp=data_pmtct=data_eid=data_art=data_vmmc=data_nutrition=data_gsn=data_lab=data_fp_integration=
        data_care_dsd=data_art_dsd=data_maternity=data_art_support=data_pmtct_support=data_htc_support1=
        data_kmmp=data_gender=data_pep=data_blood_safety=data_tb="";
  position++;   
county=conn.rs.getString(1);
sub_county=conn.rs.getString(2);
id=conn.rs.getString(3);
name=conn.rs.getString(4);
mfl=conn.rs.getString(5);
sp_id=conn.rs.getString(6);
htc=conn.rs.getString(7);
fp=conn.rs.getString(8);
pmtct=conn.rs.getString(9);
eid=conn.rs.getString(10);
art=conn.rs.getString(11);
vmmc=conn.rs.getString(12);
nutrition=conn.rs.getString(13);
gsn=conn.rs.getString(14);
lab=conn.rs.getString(15);
fp_integration=conn.rs.getString(16);
care_dsd=conn.rs.getString(17);
art_dsd=conn.rs.getString(18);
maternity=conn.rs.getString(19);
art_support=conn.rs.getString(20);
pmtct_support=conn.rs.getString(21);
htc_support1=conn.rs.getString(22);
kmmp=conn.rs.getString(23);
gender=conn.rs.getString(24);
pep=conn.rs.getString(25);
blood_safety=conn.rs.getString(26);
tb = conn.rs.getString(27);
 if(htc!=null && htc.equals("1")){htc_checker="checked";} else{htc_checker="";}
data_htc+="<input type=\"checkbox\" onchange=\"return updator('htc##"+position+"');\" name=\"htc_"+position+"\" id=\"htc##"+position+"\" style=\"width:20px;\" "+htc_checker+" >"; 
data_htc+="<input type=\"text\" name=\"id_"+position+"\" id=\"id_"+position+"\" value=\""+id+"\" style=\"display: none;\">";
 if(fp!=null && fp.equals("1")){fp_checker="checked";} else{fp_checker="";}
data_fp+="<input type=\"checkbox\" name=\"fp_"+position+"\" onchange=\"return updator('fp##"+position+"');\" id=\"fp##"+position+"\" style=\"width:20px;\" "+fp_checker+" >"; 

 if(pmtct!=null && pmtct.equals("1")){pmtct_checker="checked";} else{pmtct_checker="";}
data_pmtct+="<input type=\"checkbox\" name=\"pmtct_"+position+"\" onchange=\"return updator('pmtct##"+position+"');\" id=\"pmtct##"+position+"\" style=\"width:20px;\" "+pmtct_checker+" >"; 

 if(eid!=null && eid.equals("1")){eid_checker="checked";} else{eid_checker="";}
data_eid+="<input type=\"checkbox\" name=\"eid_"+position+"\" onchange=\"return updator('eid##"+position+"');\" id=\"eid##"+position+"\" style=\"width:20px;\" "+eid_checker+" >"; 

 if(art!=null && art.equals("1")){art_checker="checked";} else{art_checker="";}
data_art+="<input type=\"checkbox\" name=\"art_"+position+"\" onchange=\"return updator('art##"+position+"');\" id=\"art##"+position+"\" style=\"width:20px;\" "+art_checker+" >"; 

 if(vmmc!=null && vmmc.equals("1")){vmmc_checker="checked";} else{vmmc_checker="";}
data_vmmc+="<input type=\"checkbox\" name=\"vmmc_"+position+"\" onchange=\"return updator('vmmc##"+position+"');\" id=\"vmmc##"+position+"\" style=\"width:20px;\" "+vmmc_checker+" >"; 

 if(nutrition!=null && nutrition.equals("1")){nutrition_checker="checked";} else{nutrition_checker="";}
data_nutrition+="<input type=\"checkbox\" name=\"nutrition_"+position+"\" onchange=\"return updator('nutrition##"+position+"');\" id=\"nutrition##"+position+"\" style=\"width:20px;\"  "+nutrition_checker+" >"; 

 if(gsn!=null && gsn.equals("1")){gsn_checker="checked";} else{gsn_checker="";}
data_gsn+="<input type=\"checkbox\" name=\"gsn_"+position+"\" onchange=\"return updator('gsn##"+position+"');\" id=\"gsn##"+position+"\" style=\"width:20px;\"  "+gsn_checker+" >"; 

 if(lab!=null && lab.equals("1")){lab_checker="checked";} else{lab_checker="";}
data_lab+="<input type=\"checkbox\" name=\"lab_"+position+"\" onchange=\"return updator('lab##"+position+"');\" id=\"lab##"+position+"\" style=\"width:20px;\" "+lab_checker+" >"; 

 if(fp_integration!=null && fp_integration.equals("1")){fp_integration_checker="checked";} else{fp_integration_checker="";}
data_fp_integration+="<input type=\"checkbox\" name=\"fp_integration_"+position+"\" onchange=\"return updator('fp_integration##"+position+"');\" style=\"width:20px;\" id=\"fp##integration_"+position+"\" "+fp_integration_checker+" >"; 

 if(care_dsd!=null && care_dsd.equals("1")){care_dsd_checker="checked";} else{care_dsd_checker="";}
data_care_dsd+="<input type=\"checkbox\" name=\"care_dsd_"+position+"\" onchange=\"return updator('care_dsd##"+position+"');\" style=\"width:20px;\" id=\"care_dsd##"+position+"\" "+care_dsd_checker+" >"; 

 if(art_dsd!=null && art_dsd.equals("1")){art_dsd_checker="checked";} else{art_dsd_checker="";}
data_art_dsd+="<input type=\"checkbox\" name=\"art_dsd_"+position+"\" onchange=\"return updator('art_dsd##"+position+"');\" style=\"width:20px;\"  id=\"art_dsd##"+position+"\" "+art_dsd_checker+" >"; 

 if(maternity!=null && maternity.equals("1")){maternity_checker="checked";} else{maternity_checker="";}
data_maternity+="<input type=\"checkbox\" name=\"maternity_"+position+"\" onchange=\"return updator('maternity##"+position+"');\" style=\"width:20px;\" id=\"maternity##"+position+"\" "+maternity_checker+" >"; 

 if(kmmp!=null && kmmp.equals("1")){kmmp_checker="checked";} else{kmmp_checker="";}
data_kmmp+="<input type=\"checkbox\" name=\"kmmp_"+position+"\" onchange=\"return updator('kmmp##"+position+"');\" style=\"width:20px;\" id=\"kmmp##"+position+"\" "+kmmp_checker+" >"; 

 if(gender!=null && gender.equals("1")){gender_checker="checked";} else{gender_checker="";}
data_gender+="<input type=\"checkbox\" name=\"gender_"+position+"\" onchange=\"return updator('gender##"+position+"');\" style=\"width:20px;\" id=\"gender##"+position+"\" "+gender_checker+" >"; 

 if(pep!=null && pep.equals("1")){pep_checker="checked";} else{pep_checker="";}
data_pep+="<input type=\"checkbox\" name=\"pep_"+position+"\" onchange=\"return updator('pep##"+position+"');\" style=\"width:20px;\" id=\"pep##"+position+"\" "+pep_checker+" >"; 

 if(blood_safety!=null && blood_safety.equals("1")){blood_safety_checker="checked";} else{blood_safety_checker="";}
data_blood_safety+="<input type=\"checkbox\" name=\"blood_safety_"+position+"\" onchange=\"return updator('blood_safety##"+position+"');\" style=\"width:20px;\" id=\"blood_safety##"+position+"\" "+blood_safety_checker+" >"; 

 if(tb!=null && tb.equals("1")){tb_checker="checked";} else{tb_checker="";}
data_tb+="<input type=\"checkbox\" name=\"tb_"+position+"\" onchange=\"return updator('tb##"+position+"');\" id=\"tb##"+position+"\" style=\"width:20px;\" "+tb_checker+" >"; 

 data_art_support="<select style=\"width:65px;\" name=\"art_support_"+position+"\" id=\"art_support##"+position+"\" onchange=\"return updatorSelect('art_support##"+position+"');\">";
 data_pmtct_support="<select style=\"width:65px;\" name=\"pmtct_support_"+position+"\" id=\"pmtct_support##"+position+"\" onchange=\"return updatorSelect('pmtct_support##"+position+"');\">";
 data_htc_support1="<select style=\"width:65px;\" name=\"htc_support1_"+position+"\" id=\"htc_support1##"+position+"\" onchange=\"return updatorSelect('htc_support1##"+position+"');\">";

  data_art_support+="<option value=\"\"></option>";
 data_pmtct_support+="<option value=\"\"></option>";
 data_htc_support1+="<option value=\"\"></option>";
for(String onesvalue : dsd_ta){
    if(onesvalue!=null && !onesvalue.equals("")){
   
if(onesvalue.equals(art_support)) {
 data_art_support+="<option value=\""+onesvalue+"\" selected>"+onesvalue+"</option>"; 
}
else{
data_art_support+="<option value=\""+onesvalue+"\">"+onesvalue+"</option>";     
}
 
if(onesvalue.equals(pmtct_support)) {
 data_pmtct_support+="<option value=\""+onesvalue+"\" selected>"+onesvalue+"</option>"; 
}
else{
data_pmtct_support+="<option value=\""+onesvalue+"\">"+onesvalue+"</option>";     
}

if(onesvalue.equals(htc_support1)) {
 data_htc_support1+="<option value=\""+onesvalue+"\" selected>"+onesvalue+"</option>"; 
}
else{
data_htc_support1+="<option value=\""+onesvalue+"\">"+onesvalue+"</option>";     
}



}

}
 data_art_support+="</select>";
 data_pmtct_support+="</select>";
 data_htc_support1+="</select>";
 
 
data+="<tr id=\""+id+"\">"
+ "<td>"+position+"</td>"
+ "<td>"+county+"</td>"
+ "<td>"+sub_county+"</td>"
+ "<td style=\"width: 150px;\">"+name+"</td>"
+ "<td>"+mfl+"</td>"
+ "<td>"+sp_id+"</td>"
+ "<td>"+data_htc+"</td>"
+ "<td>"+data_fp+"</td>"
+ "<td>"+data_pmtct+"</td>"
+ "<td>"+data_eid+"</td>"
+ "<td>"+data_art+"</td>"
+ "<td>"+data_vmmc+"</td>"
+ "<td>"+data_nutrition+"</td>"
+ "<td>"+data_care_dsd+"</td>"
+ "<td>"+data_art_dsd+"</td>"
+ "<td>"+data_kmmp+"</td>"
+ "<td>"+data_gender+"</td>"
+ "<td>"+data_pep+"</td>"
//+ "<td>"+data_blood_safety+"</td>"
+ "<td>"+data_tb+"</td>"
+ "<td>"+data_gsn+"</td>"
+ "<td>"+data_lab+"</td>"
+ "<td>"+data_fp_integration+"</td>"
+ "<td>"+data_maternity+"</td>"
+ "<td>"+data_art_support+"</td>"
+ "<td>"+data_pmtct_support+"</td>"
+ "<td>"+data_htc_support1+"</td>"

        + "</tr>";


System.out.println("data : "+position);
 }

   data+="<input type=\"text\" name=\"total\" value=\""+position+"\" style=\"display: none;\"></table>";
            out.println(data);
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
        Logger.getLogger(loadEditFacility.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadEditFacility.class.getName()).log(Level.SEVERE, null, ex);
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
