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
public class loadUsers extends HttpServlet {
HttpSession session;
String checkedReport,checkedManagement,checkedUploads,checkedmoh731,checkedactive,checkedTB,checkedGender,checkedNutrition,checkedKMMP,checkedVMMC,checkedGapanalysis,checkedHTS,checkedART,checkedPMTCT,checkedF1a,checkedF1a_readonly;
String data;
int report,management,isactive,moh731,tb,gender,nutrition,kmmp,position,accessLevel,vmmc,uploads,gapanalysis,hts,art,pmtct,f1a,f1a_readonly;
String userid,fname,mname,lname,username,fullname,Access;
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          session=request.getSession();
          dbConn conn = new dbConn();
          position=0;
          data="<table>";
         data+="<thead><tr>"
+ "<th>No.</th>"
+ "<th>Full Name</th>"
+ "<th>Username</th>"
+ "<th>Access Level</th>"
 + "<th>Access Reports</th>"
+ "<th>Access Management</th>" 
+ "<th>Access Upload Excel</th>"
//+ "<th>Access MOH711A</th>"

+ "<th>Access MOH731</th>"
+ "<th>Access Gender</th>"
+ "<th>Access Nutrition</th>"
+ "<th>Access KMMP</th>"
+ "<th>Access F1</th>"
+ "<th>Access VMMC</th>"
+ "<th>Gap Analysis</th>"
+ "<th>HTS</th>"
+ "<th>ART</th>"
+ "<th>F1 ReadOnly</th>"
+ "<th>Activate Account</th>"
+ "</tr></thead>";
         
          String getAllUsers="SELECT * FROM user";
          conn.rs=conn.st.executeQuery(getAllUsers);
          while(conn.rs.next()){
checkedReport=checkedManagement=checkedmoh731=checkedactive=checkedTB=checkedGender=checkedNutrition=checkedUploads=checkedKMMP=checkedGapanalysis=checkedHTS=checkedART=checkedPMTCT=checkedF1a_readonly=checkedF1a="";
         position++;
         
        userid=conn.rs.getString("userid");
        fname=conn.rs.getString("fname");
        mname=conn.rs.getString("mname");
        lname=conn.rs.getString("lname");
        username=conn.rs.getString("username");
        accessLevel=conn.rs.getInt("level");
        fullname=fname+" "+mname+" "+lname;
         
        if(accessLevel==3){Access="Administrator";}
        else{Access="User";}
        
        report=conn.rs.getInt("access_reports");
        management=conn.rs.getInt("access_maintenance");
        isactive=conn.rs.getInt("isactive");
        moh731=conn.rs.getInt("access_moh731");
        f1a=conn.rs.getInt("access_form1a");
        gender=conn.rs.getInt("access_gender");
        nutrition=conn.rs.getInt("access_nutrition");
        kmmp=conn.rs.getInt("access_kmmp");
        vmmc=conn.rs.getInt("access_vmmc");
        uploads=conn.rs.getInt("access_uploads");
        gapanalysis=conn.rs.getInt("gapanalysis");
        hts=conn.rs.getInt("access_hts");
        art=conn.rs.getInt("access_art");
        f1a_readonly=conn.rs.getInt("readonly_form1a");
        
        if(report==1){checkedReport="checked";}      
        if(management==1){checkedManagement="checked";}      
        if(isactive==1){checkedactive="checked";}      
        if(moh731==1){checkedmoh731="checked";}
        if(f1a==1){checkedF1a="checked";}
        if(gender==1){checkedGender="checked";}
        if(nutrition==1){checkedNutrition="checked";}
        if(kmmp==1){checkedKMMP="checked";}
        if(vmmc==1){checkedVMMC="checked";}
        if(uploads==1){checkedUploads="checked";}
        if(gapanalysis==1){checkedGapanalysis="checked";}
        if(hts==1){checkedHTS="checked";}
        if(art==1){checkedART="checked";}
        if(f1a_readonly==1){checkedF1a_readonly="checked";}

 data+="<tr id="+userid+">"
         + "<input type=\"hidden\" name=\"userid_"+position+"\" value=\""+userid+"\" id=\"userid_"+position+"\">";       
data+="<td>"+position+"</td>"; 
data+="<td>"+fullname+"</td>"; 
data+="<td>"+username+"</td>"; 
data+="<td>"+Access+"</td>"; 
data+="<td><input type=\"checkbox\" name=\"access_reports_"+position+"\" onchange=\"return updator('access_reports##"+position+"');\" id=\"access_reports##"+position+"\" style=\"width:20px;\" "+checkedReport+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_maintenance_"+position+"\" onchange=\"return updator('access_maintenance##"+position+"');\" id=\"access_maintenance##"+position+"\" style=\"width:20px;\" "+checkedManagement+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_uploads_"+position+"\" onchange=\"return updator('access_uploads##"+position+"');\" id=\"access_uploads##"+position+"\" style=\"width:20px;\" "+checkedUploads+" ></td>"; 
 
data+="<td><input type=\"checkbox\" name=\"access_moh731_"+position+"\" onchange=\"return updator('access_moh731##"+position+"');\" id=\"access_moh731##"+position+"\" style=\"width:20px;\" "+checkedmoh731+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_gender_"+position+"\" onchange=\"return updator('access_gender##"+position+"');\" id=\"access_gender##"+position+"\" style=\"width:20px;\" "+checkedGender+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_nutrition_"+position+"\" onchange=\"return updator('access_nutrition##"+position+"');\" id=\"access_nutrition##"+position+"\" style=\"width:20px;\" "+checkedNutrition+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_kmmp_"+position+"\" onchange=\"return updator('access_kmmp##"+position+"');\" id=\"access_kmmp##"+position+"\" style=\"width:20px;\" "+checkedKMMP+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_form1a_"+position+"\" onchange=\"return updator('access_form1a##"+position+"');\" id=\"access_form1a##"+position+"\" style=\"width:20px;\" "+checkedF1a+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_vmmc_"+position+"\" onchange=\"return updator('access_vmmc##"+position+"');\" id=\"access_vmmc##"+position+"\" style=\"width:20px;\" "+checkedVMMC+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"gapanalysis_"+position+"\" onchange=\"return updator('gapanalysis##"+position+"');\" id=\"gapanalysis##"+position+"\" style=\"width:20px;\" "+checkedGapanalysis+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_hts_"+position+"\" onchange=\"return updator('access_hts##"+position+"');\" id=\"access_hts##"+position+"\" style=\"width:20px;\" "+checkedHTS+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_art_"+position+"\" onchange=\"return updator('access_art##"+position+"');\" id=\"access_art##"+position+"\" style=\"width:20px;\" "+checkedART+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"readonly_form1a_"+position+"\" onchange=\"return updator('readonly_form1a##"+position+"');\" id=\"readonly_form1a##"+position+"\" style=\"width:20px;\" "+checkedF1a_readonly+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"isactive_"+position+"\" onchange=\"return updator('isactive##"+position+"');\" id=\"isactive##"+position+"\" style=\"width:20px;\" "+checkedactive+" ></td>";
data+="</tr>";

    }
      
    if(conn.st!=null){conn.st.close();}
    if(conn.rs!=null){conn.rs.close();}
    if(conn.conn!=null){conn.conn.close();}
     
   data+="<input type=\"text\" name=\"total\" value=\""+position+"\" style=\"display: none;\"></table>";
            out.println(data);    
          
            out.println();
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
        Logger.getLogger(loadUsers.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadUsers.class.getName()).log(Level.SEVERE, null, ex);
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
