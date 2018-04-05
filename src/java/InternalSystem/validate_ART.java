/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalSystem;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
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
public class validate_ART extends HttpServlet {
HttpSession session;
String facilityID,year,month,userid,tableid;
String labels[] = {"new_1m","new_1f","new_4m","new_4f","new_9m","new_9f","new_14m","new_14f","new_19m","new_19f","new_24m","new_24f","new_29m","new_29f","new_34m","new_34f","new_39m","new_39f","new_49m","new_49f","new_50m","new_50f","new_STm","new_STf","new_GT","current_1m","current_1f","current_4m","current_4f","current_9m","current_9f","current_14m","current_14f","current_19m","current_19f","current_24m","current_24f","current_29m","current_29f","current_34m","current_34f","current_39m","current_39f","current_49m","current_49f","current_50m","current_50f","current_STm","current_STf","current_GT"};
String query = "",value,yearmonth;
int quarter;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           dbConn conn = new dbConn();
            session=request.getSession();
            
           if(session.getAttribute("year")!=null){        
   year=session.getAttribute("year").toString();
    }
      if(session.getAttribute("monthid")!=null){        
   month=session.getAttribute("monthid").toString();
    }
   
        if(session.getAttribute("facilityid")!=null){        
   facilityID=session.getAttribute("facilityid").toString();
    }
       
    tableid=year+"_"+month+"_"+facilityID; 

        
        yearmonth="";
        quarter= 0;
        String tempmonth=month;
        int pepfaryear=Integer.parseInt(year);
        if(Integer.parseInt(month)<10){ tempmonth="0"+month; }
        else {pepfaryear--;}


        if(Integer.parseInt(month)>=10 && Integer.parseInt(month)<=12){
            quarter=1;
        }
        else if(Integer.parseInt(month)>=1 && Integer.parseInt(month)<=3){
            quarter=2;
        }
        else if(Integer.parseInt(month)>=4 && Integer.parseInt(month)<=6){
            quarter=3;
        }
        else if(Integer.parseInt(month)>=7 && Integer.parseInt(month)<=9){
            quarter=4;
        }

        yearmonth=pepfaryear+""+tempmonth;
 
if(userid==null){
    userid="";
}
query = "REPLACE INTO art  set SubPartnerID='"+facilityID+"',Annee='"+year+"',Mois='"+month+"', tableid='"+tableid+"', user_id='"+userid+"', yearmonth='"+yearmonth+"',isValidated=1";
for (String label:labels){
    value = request.getParameter(label);
    if(value!=null){
        if(!value.equals("")){
          query += ","+label+"='"+value+"'";  
        }
        else{
            value=null;
            query += ","+label+"="+value;
        }
            }
    else{
     query += ","+label+"="+value;   
    }
}

    System.out.println("query : "+query);
    conn.st.executeUpdate(query);

     session.setAttribute("validateart", "<font color=\"green\"><b>ART Validated Successfully.</b></font>");
    int monthDiff=0;
     String getMonths="SELECT TIMESTAMPDIFF(MONTH, timestamp, now()) FROM art WHERE tableid='"+tableid+"'";
     conn.rs1=conn.st1.executeQuery(getMonths);
     if(conn.rs1.next()==true){
         System.out.println("months are : "+conn.rs1.getString(1));
    monthDiff=conn.rs1.getInt(1);
     }
     if(monthDiff>0){
//      UPDATE THE COLUMN   
       java.util.Date date= new java.util.Date();
Timestamp lastUpdatedOn =new Timestamp(date.getTime()); 
userid=session.getAttribute("userid").toString();
     String updateLast="UPDATE art SET updatedBy='"+userid+"', updatedOn='"+lastUpdatedOn+"' WHERE tableid='"+tableid+"'" ;   
       conn.st2.executeUpdate(updateLast);
     }
     
     if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
     if(conn.st2!=null){conn.st2.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.pst!=null){conn.pst.close();}
     if(conn.conn!=null){conn.conn.close();}
      
     response.sendRedirect("loadART.jsp");
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
        Logger.getLogger(validate_ART.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(validate_ART.class.getName()).log(Level.SEVERE, null, ex);
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
