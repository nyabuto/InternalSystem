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
import javax.servlet.http.HttpSession;

/**
 *
 * @author GNyabuto
 */
public class UpdateBaselines extends HttpServlet {
HttpSession session;
int HV0340,HV0341,HV0342,HV0343,HV0344;
int HV0340_1,HV0341_1,HV0342_1,HV0343_1,HV0344_1;
String SubPartnerID,mflcode;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           dbConn conn = new dbConn();
           session = request.getSession();
           
           HV0340=HV0341=HV0342=HV0343=HV0344=0;
           
//           get baselines
                   String getBaselines = "SELECT * FROM moh731_baseline WHERE (SubPartnerID = 374 OR SubPartnerID = 602 OR SubPartnerID = 513 OR SubPartnerID = 171 OR SubPartnerID = 290 OR SubPartnerID = 300 OR SubPartnerID = 393 OR SubPartnerID = 301 OR SubPartnerID = 518 OR SubPartnerID = 539 OR SubPartnerID = 387 OR SubPartnerID = 603 OR SubPartnerID = 251 OR SubPartnerID = 148 ) ";
                   conn.rs=conn.st.executeQuery(getBaselines);
                   while(conn.rs.next()){
                    SubPartnerID = conn.rs.getString("SubPartnerID");
                    mflcode = conn.rs.getString("mflcode");
                    HV0340= conn.rs.getInt("HV0340");
                    HV0341= conn.rs.getInt("HV0341");
                    HV0342= conn.rs.getInt("HV0342");
                    HV0343= conn.rs.getInt("HV0343");
                    HV0344= conn.rs.getInt("HV0344");
                    
                       System.out.println("Baselines : facility : "+SubPartnerID+", HV0340='"+HV0340+"',HV0341='"+HV0341+"',HV0342='"+HV0342+"',HV0343='"+HV0343+"',HV0344='"+HV0344+"'");
//                    loop through
                    // check for faclility data
                    int i=2016;
                    String reporting_id="";
                    int reporting_year,reporting_month;
                    
                    
                    while(i<=2017){
                        reporting_year=i;
                       int k=1;
                       if(i==2016  && k<9){
                        k=9;   
                       }
                       while(k<=12){
                        int mn_=0;
                        if(k==12){
                        mn_=1;
                        }
                        else{
                        mn_=k+1;    
                        }
                        
                        if(k>=9){
                            reporting_year=i+1;   
                        }
                        reporting_month=mn_;
//                        2010_10_400

                     reporting_id = reporting_year+"_"+reporting_month+"_"+SubPartnerID;
                     
                        int HV0321,HV0322,HV0323,HV0324,HV0325;
                    String getLastMonthData="SELECT  HV0321,HV0322,HV0323,HV0324,HV0325 FROM moh731 where id='"+reporting_id+"'";
                            System.out.println("started art : "+getLastMonthData);
                    conn.rs1=conn.st1.executeQuery(getLastMonthData);
                    if(conn.rs1.next()){
                       HV0321=HV0322=HV0323=HV0324=HV0325=0;
                     HV0321 = conn.rs1.getInt("HV0321");
                     HV0322 = conn.rs1.getInt("HV0322");
                     HV0323 = conn.rs1.getInt("HV0323");
                     HV0324 = conn.rs1.getInt("HV0324");
                     HV0325 = conn.rs1.getInt("HV0325"); 
                     
//                     Cumulatives
                    HV0340 += HV0321;
                    HV0341 += HV0322;
                    HV0342 += HV0323;
                    HV0343 += HV0324;
                    HV0344 += HV0325; 

                    System.out.println("new on ART  HV0321:"+HV0321+",HV0322:"+HV0322+",HV0323:"+HV0323+",HV0324:"+HV0324+",HV0325:"+HV0325+"");
                    //update for the next month
                    String update_baseline = "UPDATE moh731 SET HV0340='"+HV0340+"',HV0341='"+HV0341+"',HV0342='"+HV0342+"',HV0343='"+HV0343+"',HV0344='"+HV0344+"' WHERE id='"+reporting_id+"' ";
                        System.out.println("update query: "+update_baseline);
                    conn.st2.executeUpdate(update_baseline);
                    }
                    
                     k++; 
                     if(i==2017 && k>=9){
                        break;
                       }
//                     end of looping month
                    }
                    i++;
//                    end of year 2017
                    }
                    
                   }
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
        Logger.getLogger(UpdateBaselines.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(UpdateBaselines.class.getName()).log(Level.SEVERE, null, ex);
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
