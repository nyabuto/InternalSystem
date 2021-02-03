/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHIS2;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSetMetaData;
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
public class test extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
            out.println("</html>");
            
            dhis2DataSave ds= new dhis2DataSave();
            
            dbConn conn= new dbConn();
            
           // ds.createAfyaNyotaDataStructureFromDHIS2Datavalue(conn);
          
           
           String getuniqueyms="select distinct(concat(yearmonth,'_',facility_id)) as ym_facilid from fas_temp ";
      
        conn.rs=conn.st.executeQuery(getuniqueyms);  
        
        while (conn.rs.next())
        {
            
          transferdata( conn, conn.rs.getString(1));  
            
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
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
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

    
    
    
       public boolean transferdata(dbConn conn, String yearmonth_subpartnerid) 
    {
boolean retvalue=true;
        try {
            //sample yearmonth_subpartnerid   '201901_226','201902_226','201903_226'
            
            String qry = "select * from fas_temp where concat(yearmonth,'_',facility_id) = '"+yearmonth_subpartnerid+"' group by destination_table;";
            System.out.println(""+qry);
            
            conn.rs3 = conn.st3.executeQuery(qry);
            
            String destinationtable = "";
            
            String colstomigrate = "";
            String replaceqry = "";
            
            ResultSetMetaData metaData = conn.rs3.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            metaData = conn.rs3.getMetaData();
            columnCount = metaData.getColumnCount();
            int count = 0;
            
            while (conn.rs3.next()) {
                
                destinationtable = conn.rs3.getString("destination_table");
                
                if (count == 0)
                {
                    
                    //______Note, for the below qry to work, the last column must contain the destination table__
                    for (int i = 1; i <= columnCount; i++) {
                        //in the the last column
                        if (i == columnCount) {
                            
                        } else if (i == columnCount - 1) {
                            colstomigrate += " " + metaData.getColumnLabel(i) + " ";
                        } else {
                            colstomigrate += " " + metaData.getColumnLabel(i) + ", ";
                        }
                        
                    }//end of for loop
                    count++;
                }//end of if
                //data rows
                // we are only geting the distinct cols
// sample qry REPLACE fas_hts SELECT `id`,`facility_id`,`indicator_id`,`yearmonth`,`m_uk`,`f_uk`,`m_1`,`f_1`,`m_4`,`f_4`,`m_9`,`f_9`,`m_14`,`f_14`,`m_19`,`f_19`,`m_24`,`f_24`,`m_29`,`f_29`,`m_34`,`f_34`,`m_39`,`f_39`,`m_44`,`f_44`,`m_49`,`f_49`,`m_50`,`f_50`,`f_total`,`m_total`,`total`,`is_locked`,`user_id`,`user_pc`,`timestamp` FROM fas_temp where destination_table='fas_hts';

//delete the existing data first

String deleteqry=" delete from "+destinationtable+" where concat(yearmonth,'_',facility_id) = '"+yearmonth_subpartnerid+"' ";

               // System.out.println(" To delete column: "+deleteqry);

                conn.st_1.executeUpdate(deleteqry);
                
 String skipblanks=" and concat_ws(',',m_uk,f_uk,m_1,f_1,m_4,f_4,m_9,f_9,m_14,f_14,m_19,f_19,m_24,f_24,m_29,f_29,m_34,f_34,m_39,f_39,m_44,f_44,m_49,f_49,m_50,f_50,total) !='0' && concat_ws(',',m_uk,f_uk,m_1,f_1,m_4,f_4,m_9,f_9,m_14,f_14,m_19,f_19,m_24,f_24,m_29,f_29,m_34,f_34,m_39,f_39,m_44,f_44,m_49,f_49,m_50,f_50,total) !='' ";    

 replaceqry = "Replace  " + destinationtable + " select " + colstomigrate + " from fas_temp where destination_table='" + destinationtable + "' and concat(yearmonth,'_',facility_id) ='" + yearmonth_subpartnerid+"'  "+skipblanks+" ";

//System.out.println(""+replaceqry);
conn.st_1.executeUpdate(replaceqry);

count++;

            }
            
           
       } catch (SQLException ex) {
            Logger.getLogger(dhis2DataSave.class.getName()).log(Level.SEVERE, null, ex);
            retvalue=false;
        }
         return retvalue;
    }
   
    
}
