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
String checkedReport,checkedManagement,checkedUploads,checkedmoh731,checked711A,checkedTB,checkedGender,checkedNutrition,checkedKMMP,checkedVMMC;
String data;
int report,management,moh711,moh731,tb,gender,nutrition,kmmp,position,accessLevel,vmmc,uploads;
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
+ "<th>Access MOH711</th>"
+ "<th>Access MOH731</th>"
+ "<th>Access Gender</th>"
+ "<th>Access Nutrition</th>"
+ "<th>Access KMMP</th>"
+ "<th>Access TB</th>"
+ "<th>Access VMMC</th>"
+ "</tr></thead>";
         
          String getAllUsers="SELECT * FROM user";
          conn.rs=conn.st.executeQuery(getAllUsers);
          while(conn.rs.next()){
checkedReport=checkedManagement=checkedmoh731=checked711A=checkedTB=checkedGender=checkedNutrition=checkedUploads=checkedKMMP="";
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
        moh711=conn.rs.getInt("access_moh711");
        moh731=conn.rs.getInt("access_moh731");
        tb=conn.rs.getInt("access_tb");
        gender=conn.rs.getInt("access_gender");
        nutrition=conn.rs.getInt("access_nutrition");
        kmmp=conn.rs.getInt("access_kmmp");
        vmmc=conn.rs.getInt("access_vmmc");
        uploads=conn.rs.getInt("access_uploads");
        
        if(report==1){checkedReport="checked";}      
        if(management==1){checkedManagement="checked";}      
        if(moh711==1){checked711A="checked";}      
        if(moh731==1){checkedmoh731="checked";}
        if(tb==1){checkedTB="checked";}
        if(gender==1){checkedGender="checked";}
        if(nutrition==1){checkedNutrition="checked";}
        if(kmmp==1){checkedKMMP="checked";}
        if(vmmc==1){checkedVMMC="checked";}
        if(uploads==1){checkedUploads="checked";}

 data+="<tr id="+userid+">"
         + "<input type=\"hidden\" name=\"userid_"+position+"\" value=\""+userid+"\" id=\"userid_"+position+"\">";       
data+="<td>"+position+"</td>"; 
data+="<td>"+fullname+"</td>"; 
data+="<td>"+username+"</td>"; 
data+="<td>"+Access+"</td>"; 
data+="<td><input type=\"checkbox\" name=\"access_reports_"+position+"\" onchange=\"return updator('access_reports##"+position+"');\" id=\"access_reports##"+position+"\" style=\"width:20px;\" "+checkedReport+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_maintenance_"+position+"\" onchange=\"return updator('access_maintenance##"+position+"');\" id=\"access_maintenance##"+position+"\" style=\"width:20px;\" "+checkedManagement+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_uploads_"+position+"\" onchange=\"return updator('access_uploads##"+position+"');\" id=\"access_uploads##"+position+"\" style=\"width:20px;\" "+checkedUploads+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_moh711_"+position+"\" onchange=\"return updator('access_moh711##"+position+"');\" id=\"access_moh711##"+position+"\" style=\"width:20px;\" "+checked711A+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_moh731_"+position+"\" onchange=\"return updator('access_moh731##"+position+"');\" id=\"access_moh731##"+position+"\" style=\"width:20px;\" "+checkedmoh731+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_gender_"+position+"\" onchange=\"return updator('access_gender##"+position+"');\" id=\"access_gender##"+position+"\" style=\"width:20px;\" "+checkedGender+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_nutrition_"+position+"\" onchange=\"return updator('access_nutrition##"+position+"');\" id=\"access_nutrition##"+position+"\" style=\"width:20px;\" "+checkedNutrition+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_kmmp_"+position+"\" onchange=\"return updator('access_kmmp##"+position+"');\" id=\"access_kmmp##"+position+"\" style=\"width:20px;\" "+checkedKMMP+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_tb_"+position+"\" onchange=\"return updator('access_tb##"+position+"');\" id=\"access_tb##"+position+"\" style=\"width:20px;\" "+checkedTB+" ></td>"; 
data+="<td><input type=\"checkbox\" name=\"access_vmmc_"+position+"\" onchange=\"return updator('access_vmmc##"+position+"');\" id=\"access_vmmc##"+position+"\" style=\"width:20px;\" "+checkedVMMC+" ></td>"; 
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
