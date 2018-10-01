/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboards;

import database.dbConn;
import database.dbConnDash;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

/**
 *
 * @author EKaunda
 */
public class pullTb extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */

          //tbDashboard("20180701","20180930");

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
//20171001
    //20180331
    public String tbDashboard(String startdate, String enddate) {

        try {
           
            
            //_______________________________OVERVIEW______________________________
            //We are pulling data from tibu_tb_raw table using a stored procedure( tb_dashboards ) into dashboards table3
            //The  assumption here is that the stored procedure will come in structure similar to the one for table3
            //any column output in the tb_dashboards stored procedure should also exist in table3
            //columns are fetched dynamically and then the respective data insert using a loop into the table  
            //the insert code is excecuted at the end of the loop
            
            dbConnDash conndb = new dbConnDash();
            dbConn conn = new dbConn();

            String facilitiestable = "subpartnera";
            String [] procs={"tb_dashboard"};//,"tbclinics_dashboard"
            String [] desttable={"table3"};//,"table2"
            
for(int c=0;c<procs.length;c++){
            
            int count1 = 1;

            String insertqry = " REPLACE INTO dashboards."+desttable[c]+" SET ";

            // String qry1 = "call tb_dashboard('2015-10-01','2016-09-30','')";
            String qry1 = "call "+procs[c]+"(\"" + startdate + "\",\"" + enddate + "\",\"" + facilitiestable + "\")";
            conn.rs = conn.st.executeQuery(qry1);

            ResultSetMetaData metaData = conn.rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            ArrayList mycolumns1 = new ArrayList();

            while (conn.rs.next()) {

                if (count1 == 1) {
//headers only

                    for (int i = 1; i <= columnCount; i++) {

                        if (i < columnCount) {

                            mycolumns1.add(metaData.getColumnLabel(i));
                            //build column
                            insertqry += " `dashboards`.`"+desttable[c]+"`.`" + metaData.getColumnLabel(i) + "`=?, ";

                        } else {
                            
                 //last column we dont need a coma at the end of the column name
                 //also initialize a prepared statement at this level
                 
                            mycolumns1.add(metaData.getColumnLabel(i));
                          
                            insertqry += " `dashboards`.`"+desttable[c]+"`.`" + metaData.getColumnLabel(i) + "`=? ";
                            // valuesqry+=" ? )";
                            conndb.pst = conn.conn.prepareStatement(insertqry);

                        }

                    }//end of for loop
                    count1++;
                }//end of if
                //data rows     

                for (int a = 0; a < columnCount; a++) {

                    conndb.pst.setString(a + 1, conn.rs.getString(mycolumns1.get(a).toString()));

                    if (a == (columnCount - 1)) {
                        conndb.pst.executeUpdate();
                        System.out.println("" + conndb.pst);
                    }
                }

                count1++;
            }

        }// end of for loop
        } catch (SQLException ex) {
            Logger.getLogger(pullTb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Data pulled";
    }

}
