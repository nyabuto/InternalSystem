/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHIS2;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author EKaunda
 */
public class UploadMonthlyFPT extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
            String yearmonth="202107";
            
            
            if(request.getParameter("yearmonth")!=null){
            yearmonth=request.getParameter("yearmonth");                
            }
            
            dbConn conn = new dbConn();
            
           String uniquefacils="select distinct(facility_id) as facilid, "
                   + " 'tujengejamii' as dhis_uname ,"
                   + " 'Usaidtujengejamii21!' as dhis2_pword "
                   + " from fpt_latest ft "
                   + " join subpartnera_vw sp on sp.subpartnerid=ft.facility_id "
                   + " join district dis on dis.DistrictID=sp.DistrictID"
                   + " join county ct on ct.CountyID=dis.CountyID"
                   + " where yearmonth='"+yearmonth+"'   ";
          
           dhisconfig dc = new dhisconfig();
           conn.rs=conn.st.executeQuery(uniquefacils);
           
           
           while(conn.rs.next())
           {
         

       String fc=conn.rs.getString(1);
       String un=conn.rs.getString(2);
       String pw=conn.rs.getString(3);
       
       //Get this from a DB
       dc.setDhis2_username(un);
       dc.setDhis2_Password(pw);       
       
    pushFPTToIndexTestingApp pd= new pushFPTToIndexTestingApp();
        //getDistinctColumns(conn,"Form 1a");
       ResultSet f1adata=pd.GetForm1aData(conn, yearmonth, fc);
        
      try {
            JSONObject jo=pd.toJsonString(conn,f1adata, dc.getDhis2_username());
            System.out.println(""+jo);
           out.print("<br/><br/>  "+pd.UploadFPTToServer(jo,dc.getDhis2_username(),dc.getDhis2_Password() ));            
            
        } catch (SQLException ex) 
        {
            Logger.getLogger(pushFPTToIndexTestingApp.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     
            
           }
            
            
           
           
         
        } catch (SQLException ex) {
            Logger.getLogger(pushFPTToIndexTestingApp.class.getName()).log(Level.SEVERE, null, ex);
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
