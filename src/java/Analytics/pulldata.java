/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analytics;


import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Emmanuel Kaunda
 */
public class pulldata extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            HttpSession sess=request.getSession(true);
            
            dbConn conn = new dbConn();
            
            
            
            //below variable will tell this servlet what to do
            //it will call various methods and return different values in json format
            
            String userregion="";
            
            String act="";
            String county="";
            String subcounty="";
            String fac="";
            String startdate="";
            String enddate="";
           
          
            //loadmtrs_sel_val,act=loadmothers,fac
            
            if(request.getParameter("act")!=null){act=request.getParameter("act");}
            if(request.getParameter("fac")!=null){fac=request.getParameter("fac");}
            if(request.getParameter("ct")!=null){county=request.getParameter("ct");}
            if(request.getParameter("sct")!=null){subcounty=request.getParameter("sct");}
            if(request.getParameter("sd")!=null){startdate=request.getParameter("sd");}
            if(request.getParameter("ed")!=null){enddate=request.getParameter("ed");}
             
             
             //A table will load both headers and data values dynamically
          
            //get
            if(act.equals("getSitesSummary"))
            {               
             //The idea here is to load data from multiple datatables dynamically into a web view. We are working with an assumption that each table has a unique Primary key id called tablepkid. We also have an assumption that the main table where the data is saved might be different from the view . 
               // For that reason we are sourcing for two tables/sources , 1 view for pulling preview data and a table which will be used as a destination
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"call internal_system.analytics_sites_summary(\"\");");
                System.out.println("______Pulling Data from "+act);
                out.println(toJsonFormatDynamic(rs1));                                               
    
            }
            
            
        
            
//         
//            if(act.equals("dlt"))
//            {               
//             //This act deletes data that is shared per row id and per table
//                System.out.println("Deleting data for "+pkidval);
//                out.println(deleteRow(conn,pkidval,tablepkid,maintablename));  
//                
//                //Log the delete action into the Logs table
//                  if (sess.getAttribute("kd_session") != null) {
//
//                    HashMap<String, String> hm = new HashMap<>();
//
//                    hm = (HashMap<String, String>) sess.getAttribute("kd_session");
//
//                   // AuditTrail ad = new AuditTrail();
//                   // ad.addTrail(conn, hm, "Deleted a Record "+pkidval+" from the table "+ " " + maintablename);
//
//                }
//                
//    
//            }
           
        
            
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
            Logger.getLogger(pulldata.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(pulldata.class.getName()).log(Level.SEVERE, null, ex);
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

    

    
    
        
       public ResultSet pullDataFromDbGivenQuery(dbConn conn,String qr) throws SQLException 
{
    
    //This method gets data from db


    
    
String qry=qr;

    System.out.println("_called_query:"+qry);
conn.rs=conn.st.executeQuery(qry);


return conn.rs;

}
    




  



public JSONArray toJsonFormatDynamic(ResultSet res) 
        throws SQLException
{

    
int count1=0;

  ResultSetMetaData metaData = res.getMetaData();
        int columnCount = metaData.getColumnCount();

         
        int count = count1;
        ArrayList mycolumns = new ArrayList();
    
    
    
JSONArray jo2 = new JSONArray();




while(res.next())
{
    
     if (count == (count1)) 
     {

                for (int i = 1; i <= columnCount; i++) {
                    mycolumns.add(metaData.getColumnLabel(i));                    
                  
                }//end of for loop
                count++;
            }//end of if
    
    
    
JSONObject jo = new JSONObject(); 

for(int c=0;c<mycolumns.size();c++){
    String vl="";
    if(res.getString(mycolumns.get(c).toString())!=null){vl=res.getString(mycolumns.get(c).toString());}
jo.put(mycolumns.get(c).toString(),vl);

    
}



jo2.put(jo);
    
count++;
    
}
    
return jo2;    
}


//[{"Tablecolumn1":"Row1Value1","Tablecolumn2":"Row1Value2"},{"Tablecolumn1":"Row2Value1","Tablecolumn2":"Row2Value2"},{"Tablecolumn1":"Row2Value1","Tablecolumn2":"Row2Value2"}]






private int getRandNo(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }



}
