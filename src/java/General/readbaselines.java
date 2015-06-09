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

/**
 *
 * @author Geofrey Nyabuto
 */
public class readbaselines extends HttpServlet {
String mflcode,facilname,HV0340,HV0341,HV0342,HV0343,HV0344,facilityID;
int found=0,added=0,updated=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       dbConn conn = new dbConn();
      added=0;updated=0;
       String geBaselines="SELECT subpartnera.SubPartnerID,baselinedummy.mflcode,baselinedummy.HV0340,"
+ "baselinedummy.HV0341,baselinedummy.HV0342,baselinedummy.HV0343,baselinedummy.HV0344 "
 + "FROM baselinedummy JOIN subpartnera ON subpartnera.CentreSanteId=baselinedummy.mflcode";
       conn.rs=conn.st.executeQuery(geBaselines);
       while(conn.rs.next()){
       facilityID=conn.rs.getString(1);
       mflcode=conn.rs.getString(2);
       HV0340=conn.rs.getString(3);
       HV0341=conn.rs.getString(4);
       HV0342=conn.rs.getString(5);
       HV0343=conn.rs.getString(6);
       HV0344=conn.rs.getString(7);
         
//        mflcode=facilname=facilityID="";
        found=0;
        
        
        String checkExistence="SELECT COUNT(SubPartnerID) FROM moh731_baseline WHERE SubPartnerID='"+facilityID+"'";
        conn.rs1=conn.st1.executeQuery(checkExistence);
        if(conn.rs1.next()==true){
            found=conn.rs1.getInt(1);
        }
        
        if(found==0){
            String inserter="INSERT INTO moh731_baseline (SubPartnerID,mflcode,HV0340,HV0341,HV0342,HV0343,HV0344) "
                    + "VALUES('"+facilityID+"','"+mflcode+"','"+HV0340+"','"+HV0341+"','"+HV0342+"','"+HV0343+"','"+HV0344+"')";
            conn.st1.executeUpdate(inserter);
       added++;
        }
        else{ 
            String update_baselines="UPDATE moh731_baseline SET "
       + " HV0340='"+HV0340+"',HV0341='"+HV0341+"',HV0342='"+HV0342+"',HV0343='"+HV0343+"',HV0344='"+HV0344+"' WHERE facilityID='"+facilityID+"'";
         conn.st1.executeUpdate(update_baselines);    
       updated++;
        }
       }
       
       
   System.out.println(added+" baselines added : "+updated+" baselines updated.");
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
            Logger.getLogger(readbaselines.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(readbaselines.class.getName()).log(Level.SEVERE, null, ex);
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
