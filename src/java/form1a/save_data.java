/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form1a;

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
public class save_data extends HttpServlet {
HttpSession session;
String columns[] = {"m_uk","f_uk","m_1","f_1","m_4","f_4","m_9","f_9","m_14","f_14","m_19","f_19","m_24","f_24","m_29","f_29","m_34","f_34","m_39","f_39","m_44","f_44","m_49","f_49","m_50","f_50","total"};
String query="";
int num_indicators;
String year,month,facil,yearmonth,tableid;
String table_name="fas_art",indic_id="",value;
String user_id,user_pc;
int counted_values=0;
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           session = request.getSession();
           dbConn conn = new dbConn();
           
           num_indicators = Integer.parseInt(request.getParameter("num_indicators"));
           table_name = request.getParameter("table_name");
                       System.out.println("entered here: nm indics"+num_indicators+" table name : "+table_name);
//           num_indicators = 5;
          
            if (session.getAttribute("year") != null) {
                year = session.getAttribute("year").toString();
            }
            if (session.getAttribute("monthid") != null) {
                month = session.getAttribute("monthid").toString();
            }

            if (session.getAttribute("facilityid") != null) {
                facil = session.getAttribute("facilityid").toString();
            }


//          year="2018";
//          month="10";
//          facil = "383"; 
          
          
          user_id=user_pc="";
                  
        String tempmonth=month;
        int pepfaryear=Integer.parseInt(year);
        if(Integer.parseInt(month)<10){ tempmonth="0"+month; }
        else {pepfaryear--;}
        yearmonth=pepfaryear+""+tempmonth;
        
        
        for(int i=1;i<=num_indicators;i++){
            indic_id = request.getParameter("indic_pos_"+i);
            tableid = yearmonth+"_"+facil+"_"+indic_id; 
            
            counted_values=0;
        query = "REPLACE INTO "+table_name+" SET id='"+tableid+"',facility_id='"+facil+"',indicator_id='"+indic_id+"',yearmonth='"+yearmonth+"',user_id='"+user_id+"',user_pc='"+user_pc+"',";    
        for(String column_name:columns){
            value = request.getParameter(column_name+"_"+indic_id);
            if(value!=null){
            if(!value.equals("")){
             counted_values++;
             query+=" "+column_name+"="+value+",";  
           }
            }
        }
//         System.out.println("Query1 = "+query);
        //remove the last comma
        query = removeLast(query, 1);
        if(counted_values>0){
             System.out.println("Query = "+query);
            conn.st.executeUpdate(query);
        }
        
        }  
            
            
        response.sendRedirect("form1a.jsp");
//            out.println("</html>");
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
        Logger.getLogger(save_data.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(save_data.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}
   private static String removeLast(String str, int num) {
    return str.substring(0, str.length() - num);
}
}
