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
public class cleanCumulatives extends HttpServlet {
HttpSession session;
int HV0321,HV0322,HV0323,HV0324,HV0325;
int HV0340,HV0341,HV0342,HV0343,HV0344;
int HV0340_1,HV0341_1,HV0342_1,HV0343_1,HV0344_1;
String facilityId,id;
int prevData;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           session=request.getSession();
           dbConn conn= new dbConn();
           
           for(int i=201504;i<=201506;i++){
         String getFacility="SELECT SubPartnerID FROM subpartnera";
         conn.rs1=conn.st1.executeQuery(getFacility);
         while(conn.rs1.next()){
             facilityId=conn.rs1.getString(1);
               if(i>201504){
                   prevData=i-1;
          String gatmaxs="select HV0340 , HV0341 ,HV0342,HV0343,HV0344  from moh731 where yearmonth='"+prevData+"' and SubPartnerID='"+facilityId+"'";
    
      conn.rs=conn.st.executeQuery(gatmaxs);
    if(conn.rs.next()==true){
    
HV0340_1=conn.rs.getInt("HV0340");
HV0341_1=conn.rs.getInt("HV0341");
HV0342_1=conn.rs.getInt("HV0342");
HV0343_1=conn.rs.getInt("HV0343");
HV0344_1=conn.rs.getInt("HV0344");
}     
           }  
               
             
    else{
//        Get baselines
        String baselines="SELECT HV0340 , HV0341 ,HV0342,HV0343,HV0344 FROM moh731_baseline WHERE SubPartnerID='"+facilityId+"'";
       conn.rs=conn.st.executeQuery(baselines);
    if(conn.rs.next()==true){
    
HV0340_1=conn.rs.getInt(1);
HV0341_1=conn.rs.getInt(2);
HV0342_1=conn.rs.getInt(3);
HV0343_1=conn.rs.getInt(4);
HV0344_1=conn.rs.getInt(5);
}
 
    } 
String getCurrentData="SELECT HV0321,HV0322,HV0323,HV0324,HV0325,id FROM moh731 WHERE yearmonth='"+i+"' and SubPartnerID='"+facilityId+"'";            
conn.rs=conn.st.executeQuery(getCurrentData);
if(conn.rs.next()==true){
 HV0321=conn.rs.getInt(1);
 HV0322=conn.rs.getInt(2);
 HV0323=conn.rs.getInt(3);
 HV0324=conn.rs.getInt(4);
 HV0325=conn.rs.getInt(5);  
 id=conn.rs.getString(6);
 
 HV0340=HV0340_1+HV0321;
 HV0341=HV0341_1+HV0322;
 HV0342=HV0342_1+HV0323;
 HV0343=HV0343_1+HV0324;
 HV0344=HV0344_1+HV0325;
 
 String updator="UPDATE moh731 set HV0340='"+HV0340+"' , HV0341='"+HV0341+"' ,HV0342='"+HV0342+"',HV0343='"+HV0343+"',HV0344='"+HV0344+"' WHERE id='"+id+"'";
 conn.st2.executeUpdate(updator);
}

System.out.println("here ");
}
  
   }
            out.println("completed");
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
        Logger.getLogger(cleanCumulatives.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(cleanCumulatives.class.getName()).log(Level.SEVERE, null, ex);
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
