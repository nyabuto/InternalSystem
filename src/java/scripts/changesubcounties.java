/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripts;

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
 * @author ekaunda
 */
public class changesubcounties extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           dbConn conn= new dbConn();
           
           
           String getdata="select * from district";
           
           conn.rs=conn.st.executeQuery(getdata);
           
           while(conn.rs.next()){
               
               String val=conn.rs.getString("DistrictNom").trim();
            
               String distname=val.toUpperCase().substring(0,1)+val.toLowerCase().substring(1);
               if(val.contains(" ")){
                distname=val.toUpperCase().substring(0,1)+val.toLowerCase().substring(1,val.indexOf(" "))+val.toUpperCase().substring(val.indexOf(" "),val.indexOf(" ")+2)+val.toLowerCase().substring(val.indexOf(" ")+2);
              
              
               }
               
              
               
               //get data from komens edits and update 
               
               String insertcode="Update district set DistrictNom='"+distname+"' where DistrictId='"+conn.rs.getString("DistrictID")+"'";
               
              // conn.st1.executeUpdate(insertcode);
               
               //System.out.println(insertcode);
               
                               }
           
           String getexelist="Select SubPartnerID,SubPartnerNom, facilitychanges.Districtnom from facilitychanges join district on facilitychanges.Districtnom=district.Districtnom ";
           
           //compare the complete details from imis and those from komens db
           
           conn.rs=conn.st.executeQuery(getexelist);
           
           while(conn.rs.next()){
           //
               
               String getimis=" Select SubPartnerID,subPartnerNom, Districtnom from subpartnera join district on subpartnera.DistrictID=district.DistrictID where Districtnom like '"+conn.rs.getString("Districtnom")+"' && subPartnerID like '"+conn.rs.getString("subPartnerID")+"' ";
              conn.rs1=conn.st1.executeQuery(getimis);
              
               if(!conn.rs1.next()){
               
                   
                   System.out.print("Facility: "+conn.rs.getString("subPartnerNom"));
                   
               //update the imis districtid with the correct one from komen changes
             String gecorrectimisdist=" select DistrictID,DistrictNom from district where Districtnom like '"+conn.rs.getString("Districtnom")+"'";
             
             conn.rs2=conn.st2.executeQuery(gecorrectimisdist);
             if(conn.rs2.next()){
             
                 System.out.print(" Correct District: "+conn.rs2.getString(2)+"\n");
                 
                 //write an update code
                 
                 String updatecode="update subpartnera set  DistrictID='"+conn.rs2.getString("DistrictID")+"' where subPartnerID like '"+conn.rs.getString("subPartnerID")+"'";
             
                 
                 System.out.println(updatecode);
                //conn.st4.executeUpdate(updatecode);
             }
             
             
             
                   
               } 
               
           
           }
           
            out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(changesubcounties.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
