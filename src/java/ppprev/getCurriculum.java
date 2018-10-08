/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppprev;

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
 * @author EKaunda
 */
public class getCurriculum extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
              String popid="";
                        dbConn conn = new dbConn();
            
          
          
          if(request.getParameter("targetpop")!=null){
           
          popid=request.getParameter("targetpop");
           }
          
           
            
           
            
            
            out.println(curriculum(conn, popid, "20"));
         if(conn.rs!=null){conn.rs.close(); }   
         if(conn.st!=null){conn.st.close();  }   
         if(conn.conn!=null){conn.conn.close();  }
            
        } catch (SQLException ex) {
            Logger.getLogger(getPartner.class.getName()).log(Level.SEVERE, null, ex);
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



public String curriculum(dbConn conn,String populationid, String curselect) throws SQLException{

  
          
String wherestring="";
           
           
           if(!populationid.equals(""))
           {
           wherestring=" where 1=1 ";
           wherestring+=" and target_id='"+populationid+"' ";
           }
           
           

            
            String data="<option value=''>Select Curriculum</option>";
            String qry="select * from hc_curriculum "+wherestring;
            
            conn.rs= conn.st.executeQuery(qry);
            while(conn.rs.next()){
                
                 String selected="";
                if(curselect.equals(conn.rs.getString(1))){selected=" selected ";}
            
            data+="<option "+selected+" data-lessons='"+conn.rs.getString(3)+"' value='"+conn.rs.getString(1)+"'>"+conn.rs.getString(2)+"</option>";
            
            }



return data;
}


}
