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
public class LockData extends HttpServlet {
HttpSession session;
int updated;
String facilityIds,year,month,lockStatus,updateStatus,lockText,monthname;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
       dbConn conn = new dbConn();
       
        year=request.getParameter("year");
        month=request.getParameter("month");
        lockStatus=request.getParameter("lock_status");
        
        if(lockStatus.equals("1")){lockText="Locked";}
        else{lockText="Un locked";}
        
        updateStatus="";
        monthname="";
        facilityIds="(";
      if(request.getParameter("facility")!=null && !request.getParameter("facility").equals("")){
      facilityIds+=" SubPartnerID='"+request.getParameter("facility")+"') && ";    
         
      }  
        
        else{     
   if(request.getParameter("subcounty")!=null && !request.getParameter("subcounty").equals(""))   {
         String subcounty=request.getParameter("subcounty");
    String getDist="SELECT subpartnera.SubPartnerID FROM subpartnera "
    + "JOIN district ON subpartnera.DistrictID=district.DistrictID "
     + "WHERE district.DistrictID='"+subcounty+"'" ;
    conn.rs=conn.st.executeQuery(getDist);
    while(conn.rs.next()){
     facilityIds+=" SubPartnerID='"+conn.rs.getString(1)+"' || ";
    }
      facilityIds = facilityIds.substring(0, facilityIds.length()-3);
     facilityIds+=") && ";   
     } 
     else{
        if(request.getParameter("county")!=null && !request.getParameter("county").equals(""))   {  
         String county=request.getParameter("county");
         String getCounty="SELECT subpartnera.SubPartnerID FROM subpartnera "
    + "JOIN district ON subpartnera.DistrictID=district.DistrictID "
     + "JOIN county ON district.CountyID=county.CountyID WHERE county.CountyID='"+county+"'" ;
    conn.rs=conn.st.executeQuery(getCounty);
    while(conn.rs.next()){
     facilityIds+=" SubPartnerID='"+conn.rs.getString(1)+"' || ";
    }
    facilityIds = facilityIds.substring(0, facilityIds.length()-3);
     facilityIds+=") && ";         
     }
       
        else{
       facilityIds="";      
        }   
        
     }
      
    }
    String getMonth="SELECT name FROM month WHERE id='"+month+"'";
    conn.rs=conn.st.executeQuery(getMonth);
    if(conn.rs.next()==true){
        monthname=conn.rs.getString(1);
    }
      
        System.out.println("facili : "+facilityIds);  
     updateStatus="<p style=\"text-align: center;\">Data entry Module for pepfar year "+year+" and Month "+monthname+" has been <font color=\"black\"><b> "+lockText+" </b></font> successfully as follows :</p> " ;
      updateStatus+="<p style=\"text-align: center;\">";
     String getForms [] =request.getParameterValues("form");
     for(String form:getForms){
      if(!form.equals("")){
//      CODE HERE TO UPDATE STATUS=========================================
          
      String update=" UPDATE "+form+" SET isLocked='"+lockStatus+"' WHERE "+facilityIds+" Annee='"+year+"' && Mois='"+month+"'";
     updated= conn.st.executeUpdate(update);
     updateStatus+=" <font color=\"blue\">"+updated+"</font> . Health facilities  in "+form+". <br> ";     
          System.out.println(""+update);   
      }
         
     }
     
     updateStatus+="</p>";
    String status="<div class=\"alert alert-success\" id=\"alert\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\"></a> "+updateStatus+"</div>";
       session.setAttribute("locked", status);
       
    System.out.println("status here : "+updateStatus);
    response.sendRedirect("LockData.jsp");
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
        Logger.getLogger(LockData.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(LockData.class.getName()).log(Level.SEVERE, null, ex);
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
