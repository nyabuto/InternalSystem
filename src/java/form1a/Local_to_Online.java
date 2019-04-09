/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form1a;

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
import javax.servlet.http.HttpSession;

/**
 *
 * @author GNyabuto
 */
public class Local_to_Online extends HttpServlet {
HttpSession session;
String tables[] = {"fas_art","fas_cxca","fas_gend_gbv","fas_hts","fas_hts_pns","fas_hts_recent","fas_hts_self","fas_ipt","fas_pmtct","fas_pmtct_art","fas_prep","fas_tx_ml","moh731_new"};
//String tables[] = {"fas_art","fas_cxca","fas_gend_gbv"};
String output="";
String source_table_name,destination_table_name,id,query_inserter;
int scanned,added,skipped;
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
            session = request.getSession();
            output="";
            
//      get all database tables
            for (String table : tables) {
                scanned=added=skipped=0;
                destination_table_name = table;
                source_table_name = "_"+destination_table_name;
                //get data
                String query_getter = "SELECT * FROM "+source_table_name;
                conn.rs = conn.st.executeQuery(query_getter);
                ResultSetMetaData metaData = conn.rs.getMetaData();
                int col_count = metaData.getColumnCount(); //number of column
                
                    System.out.println("query : "+query_getter+" no.columns :"+col_count);
                while(conn.rs.next()){
                    scanned++;
                    id = conn.rs.getString("id");
                    String checker = "SELECT id FROM "+destination_table_name+" WHERE id='"+id+"'";
                    //System.out.println("query checker:"+checker);
                    conn.rs1 = conn.st1.executeQuery(checker);
                    if(conn.rs1.next()){
                    skipped++;    
                    }
                    else{ // move the record to the destination table
                      query_inserter="INSERT INTO "+destination_table_name+" SET ";
                         for(int j=0;j<col_count;j++){
                            //System.out.println("count is :"+col_count);
                            //System.out.println("col name :"+metaData.getColumnLabel(+1));
                            if(conn.rs.getString(metaData.getColumnLabel(j+1))!=null){
                        //System.out.println("j is:"+j+" value: "+conn.rs.getString(metaData.getColumnLabel(j+1)));
                        query_inserter+=metaData.getColumnName(j+1)+"='"+conn.rs.getString(metaData.getColumnLabel(j+1))+"',";
                            }
                         }
                        query_inserter = removeLast(query_inserter, 1);
                        System.out.println("inser query: "+query_inserter);
                        conn.st1.executeUpdate(query_inserter);
                        added++;
                    }
                }
                output+="Table Name :"+destination_table_name+" Total Records :"+scanned+" Added Records :"+added+" Skipped :"+skipped+"<br><br>";     
                System.out.println("Table Name :"+destination_table_name+" Total Records :"+scanned+" Added Records :"+added+" Skipped :"+skipped+"\n");    
            }
            
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Data Uploading Status</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Data uploading completed</h1>");
            out.println("<h5>"+output+"</h5>");
            out.println("</body>");
            out.println("</html>");
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
        Logger.getLogger(Local_to_Online.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(Local_to_Online.class.getName()).log(Level.SEVERE, null, ex);
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

  private static String removeLast(String str, int num) {
    return str.substring(0, str.length() - num);
}   
}
