/*
 * To change this template, choose Tools | Templates
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
 * @author Elkant
 */
public class updatefacilitysession extends HttpServlet {
   HttpSession session=null;
   String active;
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    
    
    session=request.getSession();
    dbConn conn=new dbConn();
    session.removeAttribute("forms_holder");
    //receive the facility id a get the facility name
    String facility=request.getParameter("facil");
    
     String getfacilname="select * from subpartnera join district on subpartnera.DistrictID=district.DistrictID  where SubPartnerId='"+facility+"'";
        
     String f731="";
     String f711="";
     String f711new="";
     
        conn.rs=conn.st.executeQuery(getfacilname);
        while(conn.rs.next()){
         active=","; 
        if(conn.rs.getInt("HTC")==1){active+="HTC,";f711="MOH 711A,";f731="MOH 731,"; f711new="MOH 711 (New)";}
        if(conn.rs.getInt("FP")==1){active+="FP,";f711="MOH 711A,";f711new="MOH 711 (New)";}
        if(conn.rs.getInt("PMTCT")==1){active+="PMTCT,";f731="MOH 731,";f711="MOH 711A,";f711new="MOH 711 (New)";}
        if(conn.rs.getInt("EID")==1){active+="EID,";}
        if(conn.rs.getInt("ART")==1){active+="ART,";f731="MOH 731,";}
        if(conn.rs.getInt("VMMC")==1){active+="VMMC,";}
         if(conn.rs.getInt("Nutrition")==1){active+="Nutrition,";}
         if(conn.rs.getInt("GSN")==1){active+="GSN,";}
         if(conn.rs.getInt("Lab")==1){active+="Lab,";}
         if(conn.rs.getInt("FP_Integration")==1){active+="FP_Integration,";}
         if(conn.rs.getInt("Care_DSD")==1){active+="Care_DSD,";f731="MOH 731,";}
         if(conn.rs.getInt("ART_DSD")==1){active+="ART_DSD,";}
         if(conn.rs.getInt("Maternity")==1){active+="Maternity,";f711="MOH 711A,";f711new="MOH 711 (New)";}
//         if(conn.rs.getString("ART_Support").equals("")){active+="ART_Support,";}
//         if(conn.rs.getString("PMTCT_Support").equals("")){active+="PMTCT_Support,";}
//         if(conn.rs.getString("HTC_Support1").equals("")){active+="HTC_Support1,";}
         if(conn.rs.getInt("KMMP")==1){active+="KMMP,";}
         if(conn.rs.getInt("Gender")==1){active+="Gender,";}
         if(conn.rs.getInt("PEP")==1){active+="PEP,";f731="MOH 731,";}
         if(conn.rs.getInt("Blood_Safety")==1){active+="Blood_Safety,";}
         if(conn.rs.getInt("TB")==1){active+="TB,";}
        
         active+=f731;
         active+=f711;
         active+=f711new;
         
        session.setAttribute("forms_holder", active);
        session.setAttribute("facilityname", conn.rs.getString("SubPartnerNom"));
        
        session.setAttribute("subcountyid",conn.rs.getString("district.DistrictID"));
        session.setAttribute("countyid",conn.rs.getString("CountyID"));
          
          
        }
        
        
        
      session.setAttribute("facilityid", facility);
      
    PrintWriter out = response.getWriter();
    try {
        
        
        
        
       
    } finally {            
        out.close();
         if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}
         if(conn.st!=null){ conn.st.close();}
    }
}       catch (SQLException ex) {
            Logger.getLogger(updatefacilitysession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
