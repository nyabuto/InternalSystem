/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VL;

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
public class vl_txcurr_txpvls extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
            
            
            dbConn conn = new dbConn();
            
//            Age bands
//            Sex
//            MFLCodes

//



       String agebands[]={"<1","<1","1-4","1-4","5-9","5-9","10-14","10-14","15-19","15-19","20-24","20-24","25-29","25-29","30-34","30-34","35-39","35-39","40-44","40-44","45-49","45-49","50+","50+"};
//    String agebands_[]={"<1","<1","1-4","1-4","5-9","5-9","10-14","10-14","15-19","15-19","20-24","20-24","25-29","25-29","30-34","30-34","35-39","35-39","40-44","40-44","45-49","45-49","50+","50+"};
    String ageband_mysql[]={"m_1","f_1","m_4","f_4","m_9","f_9","m_14","f_14","m_19","f_19","m_24","f_24","m_29","f_29","m_34","f_34","m_39","f_39","m_44","f_44","m_49","f_49","m_50","f_50"};
    
             String sexs[]={"M","F"};
             String sexmysql[]={"M","F"};
             String end_date="2020-12-31";
             String eligibledate="2020-12-31";
             String ym=end_date.replace("-","").substring(0, 6);
             
             String eligibleym=eligibledate.replace("-","").substring(0, 6);
             
             System.out.println("eligible Yearmonth is :"+eligibleym);
             
             String selectcurr="call sp_getTXCurr_finegae('"+eligibleym+"')";
            
             
             conn.rs=conn.st.executeQuery(selectcurr);
             int rownumber=0;
             while(conn.rs.next())
             {
             rownumber++;
             
             
             String mflcode=conn.rs.getString("mflcode");  
             
             for(int a=0;a<ageband_mysql.length;a++){
             
             
                 String agebandname=ageband_mysql[a];
                 
                 String sex="";
                 if(agebandname.startsWith("m")){
                     sex="M";                    
                 } 
                 else {
                 sex="F";
                      }
                 String ab=agebands[a];
                 
                 int limit=conn.rs.getInt(agebandname);
                 
                 if(limit!=0)
                 {
                     
                 String finalqr="call sp_vl_insert_agegendersite('"+end_date+"',"+limit+",'"+ab+"','"+sex+"',"+mflcode+");";
                     
                 System.out.println("Row "+rownumber+": "+finalqr);
                 
                 conn.st1.executeUpdate(finalqr);
                 
                  }  
//SET @param_agebracket=pr_ab;
//SET @param_sex=pr_sx;
//SET @param_mfl=pr_mfl;
//SET @param_limit=myrowslimit;
//SET @end_date=End_Date;
                 
             }
                 
            
                 
//               County, District, Facilityname, mflcode, indicator_id, yearmonth, m_1, f_1, m_4, f_4, m_9, f_9, m_14, f_14, m_19, f_19, m_24, f_24, m_29, f_29, m_34, f_34, m_39, f_39, m_44, f_44, m_49, f_49, m_50, f_50  
             
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
            Logger.getLogger(vl_txcurr_txpvls.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(vl_txcurr_txpvls.class.getName()).log(Level.SEVERE, null, ex);
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
