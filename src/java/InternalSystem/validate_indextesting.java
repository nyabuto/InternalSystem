/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalSystem;

import dashboards.pullHts;
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
public class validate_indextesting extends HttpServlet {
HttpSession session;
String facilityID,year,month,userid,tableid;
String labels[] = {"clientsListed_1m","clientsListed_1f","clientsListed_4m","clientsListed_4f","clientsListed_9m","clientsListed_9f","clientsListed_14m","clientsListed_14f","clientsListed_19m","clientsListed_19f","clientsListed_24m","clientsListed_24f","clientsListed_29m","clientsListed_29f","clientsListed_34m","clientsListed_34f","clientsListed_39m","clientsListed_39f","clientsListed_49m","clientsListed_49f","clientsListed_50m","clientsListed_50f","clientsListed_STm","clientsListed_STf","clientsListed_GT","clientStartedART_1m","clientStartedART_1f","clientStartedART_4m","clientStartedART_4f","clientStartedART_9m","clientStartedART_9f","clientStartedART_14m","clientStartedART_14f","clientStartedART_19m","clientStartedART_19f","clientStartedART_24m","clientStartedART_24f","clientStartedART_29m","clientStartedART_29f","clientStartedART_34m","clientStartedART_34f","clientStartedART_39m","clientStartedART_39f","clientStartedART_49m","clientStartedART_49f","clientStartedART_50m","clientStartedART_50f","clientStartedART_STm","clientStartedART_STf","clientStartedART_GT","contactsListed_1m","contactsListed_1f","contactsListed_4m","contactsListed_4f","contactsListed_9m","contactsListed_9f","contactsListed_14m","contactsListed_14f","contactsListed_19m","contactsListed_19f","contactsListed_24m","contactsListed_24f","contactsListed_29m","contactsListed_29f","contactsListed_34m","contactsListed_34f","contactsListed_39m","contactsListed_39f","contactsListed_49m","contactsListed_49f","contactsListed_50m","contactsListed_50f","contactsListed_STm","contactsListed_STf","contactsListed_GT","newlyTested_1m","newlyTested_1f","newlyTested_4m","newlyTested_4f","newlyTested_9m","newlyTested_9f","newlyTested_14m","newlyTested_14f","newlyTested_19m","newlyTested_19f","newlyTested_24m","newlyTested_24f","newlyTested_29m","newlyTested_29f","newlyTested_34m","newlyTested_34f","newlyTested_39m","newlyTested_39f","newlyTested_49m","newlyTested_49f","newlyTested_50m","newlyTested_50f","newlyTested_STm","newlyTested_STf","newlyTested_GT","identifiedPOS_1m","identifiedPOS_1f","identifiedPOS_4m","identifiedPOS_4f","identifiedPOS_9m","identifiedPOS_9f","identifiedPOS_14m","identifiedPOS_14f","identifiedPOS_19m","identifiedPOS_19f","identifiedPOS_24m","identifiedPOS_24f","identifiedPOS_29m","identifiedPOS_29f","identifiedPOS_34m","identifiedPOS_34f","identifiedPOS_39m","identifiedPOS_39f","identifiedPOS_49m","identifiedPOS_49f","identifiedPOS_50m","identifiedPOS_50f","identifiedPOS_STm","identifiedPOS_STf","identifiedPOS_GT","contactStartedART_1m","contactStartedART_1f","contactStartedART_4m","contactStartedART_4f","contactStartedART_9m","contactStartedART_9f","contactStartedART_14m","contactStartedART_14f","contactStartedART_19m","contactStartedART_19f","contactStartedART_24m","contactStartedART_24f","contactStartedART_29m","contactStartedART_29f","contactStartedART_34m","contactStartedART_34f","contactStartedART_39m","contactStartedART_39f","contactStartedART_49m","contactStartedART_49f","contactStartedART_50m","contactStartedART_50f","contactStartedART_STm","contactStartedART_STf","contactStartedART_GT"};
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
query = "REPLACE INTO index_testing  set SubPartnerID='"+facilityID+"',Annee='"+year+"',Mois='"+month+"', tableid='"+tableid+"', user_id='"+userid+"', yearmonth='"+yearmonth+"',isValidated=1";
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

     session.setAttribute("validateindextesting", "<font color=\"green\"><b>Index testing Validated Successfully.</b></font>");
    int monthDiff=0;
     String getMonths="SELECT TIMESTAMPDIFF(MONTH, timestamp, now()) FROM index_testing WHERE tableid='"+tableid+"'";
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
     String updateLast="UPDATE index_testing SET updatedBy='"+userid+"', updatedOn='"+lastUpdatedOn+"' WHERE tableid='"+tableid+"'" ;   
       conn.st2.executeUpdate(updateLast);
     }
     
     
    pullHts hts= new pullHts();
    hts.hts_non731(yearmonth,yearmonth,facilityID); //stored procedure code 
   // hts.hts731( yearmonth, yearmonth, facilityId);
     
     if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
     if(conn.st2!=null){conn.st2.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.pst!=null){conn.pst.close();}
     if(conn.conn!=null){conn.conn.close();}
      
     response.sendRedirect("loadIndexTesting.jsp");
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
            Logger.getLogger(validate_indextesting.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(validate_indextesting.class.getName()).log(Level.SEVERE, null, ex);
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
//    public String removeLast(String str) {
//    if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
//        str = str.substring(0, str.length() - 1);
//    }
//    return str;
//}
}
