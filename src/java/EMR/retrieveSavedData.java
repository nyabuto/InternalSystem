/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EMR;

import java.sql.ResultSetMetaData;
import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author EKaunda
 */
public class retrieveSavedData extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
          dbConn conn = new dbConn();
          
          if(request.getParameter("id")!=null){
                            
             String id=request.getParameter("id");
              
             if(id.contains("_"))
             {             
          out.println(getSavedEmrStatusDataJson(getSavedEmrStatusData(conn,id)));
             }
          }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(retrieveSavedData.class.getName()).log(Level.SEVERE, null, ex);
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


public ResultSet getSavedEmrStatusData(dbConn conn, String id) throws SQLException{

    ResultSet data=null;
    
    //id=yearmonth_facilitymfl    i.e 202104_15288
    
  String getdata="select * from emr_status_vw where id='"+id+"'";  
    
    conn.rs=conn.st.executeQuery(getdata);
    
    
return conn.rs;
}


public JSONObject getSavedEmrStatusDataJson (ResultSet rs) throws SQLException{

    JSONObject jo = new JSONObject();
    JSONArray ja=new JSONArray();


    ResultSetMetaData md=(ResultSetMetaData) rs.getMetaData();
   int rwcount=0;
    
 while(rs.next())
 { 
   
 JSONObject jot = new JSONObject();
  
 for(int a=1;a<=md.getColumnCount();a++)
 {
 String mdname=md.getColumnName(a);
     //System.out.println("metadataname"+mdname);
     
    jot.put(mdname, rs.getString(mdname));
      
 }
       
 rwcount++;
 ja.add(jot);
 
 }
 
  
//System.out.println(rwcount+"jsonArray"+ja.toString());
if(rwcount==0){
 
    //if no saved data,send a blank data file
JSONObject joti = new JSONObject();  
joti.put("id","0");

ja.add(joti);

jo.put("emr_data", ja);
   
              }
else {
jo.put("emr_data", ja);
     }

//System.out.println("Inside field:"+ja.toJSONString());

return jo;



}




}
