/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportsAjax;

import General.IdGenerator;
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
public class loadDatimReportsList extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
            
            dbConn conn= new dbConn();
            
           String receivedyearmonth="";
           String receivedmaxyearmonth="";
           
           String year="2014";
           String month="01";
           
           
           String maxyear="2017";
           String maxmonth="03";
           
      
           if(request.getParameter("year")!=null && !request.getParameter("year").equals("NaN")){
               
           year=request.getParameter("year");
           
           month=request.getParameter("month");
           maxmonth=request.getParameter("maxmonth");
           maxyear=request.getParameter("maxyear");
           
           }
           
           receivedyearmonth=year+month;
           receivedmaxyearmonth=maxyear+maxmonth;
           
            
         //String getdata="select * from datim_reports_list where (max_yearmonth is null and ("+receivedyearmonth+" >= min_yearmonth)  ) or ( ("+receivedyearmonth+" >= min_yearmonth) and ( "+receivedyearmonth+" <= max_yearmonth)  ) order by id asc";
         String getdata="select * from datim_reports_list where (max_yearmonth is null and ("+receivedyearmonth+" >= min_yearmonth)  ) or ( "+receivedyearmonth+" between min_yearmonth and  max_yearmonth  ) or ( "+receivedmaxyearmonth+" between min_yearmonth and  max_yearmonth  ) order by id asc";
         
            System.out.println("loadDatimReportsList "+getdata); 
            
         IdGenerator IG = new IdGenerator();
            String currentyearmonth = IG.currentYear()+""+IG.CurrentMonth();
         
             String minyearmonth="201401";//default is 2014 jan
             String maxyearmonth=currentyearmonth;//default is current date
         String data="";
         conn.rs=conn.st.executeQuery(getdata);
         while(conn.rs.next())
         {
        
         if(conn.rs.getString("min_yearmonth")!=null){  minyearmonth=conn.rs.getString("min_yearmonth");}
         if(conn.rs.getString("max_yearmonth")!=null){  maxyearmonth=conn.rs.getString("max_yearmonth");}
             
             data+="<option  data-minimum='"+minyearmonth+"' data-maximum='"+maxyearmonth+"' title='"+conn.rs.getString("description")+"' value='"+conn.rs.getString("redirect_page")+"'>"+conn.rs.getString("report")+"</option>";
             
         
         }
            
            out.println(data);
            
            
        } finally 
        {
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
            Logger.getLogger(loadDatimReportsList.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(loadDatimReportsList.class.getName()).log(Level.SEVERE, null, ex);
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
