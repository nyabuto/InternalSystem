/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripts;


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
public class dataPulls extends HttpServlet {

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
            
            String fac="";
            
            //below variable will tell this servlet what to do
            //it will call various methods and return different values in json format
            
            
            String userregion="";
            
           
            
            String act="";
            String loadmtrs_sel_val="";
            String fm="";
            String table_docker="";
            String tablepkid="";
            String pkidval="";
            String maintablename="";
            String vwtable="";
            //loadmtrs_sel_val,act=loadmothers,fac
            
            if(request.getParameter("act")!=null){act=request.getParameter("act");}
            if(request.getParameter("fac")!=null){fac=request.getParameter("fac");}
            if(request.getParameter("pkid")!=null){tablepkid=request.getParameter("pkid");}
            if(request.getParameter("tablename")!=null){maintablename=request.getParameter("tablename");}
            if(request.getParameter("vwtable")!=null){vwtable=request.getParameter("vwtable");}
            if(request.getParameter("pkidval")!=null){pkidval=request.getParameter("pkidval");}
           
            if(request.getParameter("loadmtrs_sel_val")!=null){loadmtrs_sel_val=request.getParameter("loadmtrs_sel_val");}
            
            
            if(act.equals("loadmothers")){out.println(buildoptsFromDbResultSet(pullAddedMothers(conn, fac),loadmtrs_sel_val));}
            
             if(request.getParameter("fm")!=null){fm=request.getParameter("fm");}
             if(request.getParameter("table_docker")!=null){table_docker=request.getParameter("table_docker");}
             
             //A table will load both headers and data values dynamically
            if(act.equals("loadedits"))
            {               
             //The idea here is to load data from multiple datatables dynamically into a web view. We are working with an assumption that each table has a unique Primary key id called tablepkid. We also have an assumption that the main table where the data is saved might be different from the view . 
               // For that reason we are sourcing for two tables/sources , 1 view for pulling preview data and a table which will be used as a destination
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"select * from "+vwtable);
                System.out.println("______Pulling Data from Grants");
                out.println(buildDataTable(conn,rs1,table_docker,tablepkid,maintablename));                                               
    
            }
            
            
            //get
            if(act.equals("loadrecordx"))
            {               
             //The idea here is to load data from multiple datatables dynamically into a web view. We are working with an assumption that each table has a unique Primary key id called tablepkid. We also have an assumption that the main table where the data is saved might be different from the view . 
               // For that reason we are sourcing for two tables/sources , 1 view for pulling preview data and a table which will be used as a destination
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"select * from "+maintablename+" where "+tablepkid+"='"+pkidval+"' limit 1");
                System.out.println("______Pulling Data from "+maintablename);
                out.println(toJsonFormatDynamic(rs1));                                               
    
            }
            
            
            
           
            
            //________________________________________Solicitation Forms_________________________________________
            if(act.equals("getDataSets"))
            {               
                
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"select concat(orgunit,',',upper(orgunit)) as rcd from ramcah_sum_indicators where is_active=1 group by orgunit ; ");

                out.println(buildoptsFromDbResultSet(rs1,""));                                               
    
            }
            
            
            
              if(act.equals("getNakuruDistricts"))
            {               
                
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"select concat(DistrictID,',',DistrictNom) as rcd from internal_system.district where active=1 and CountyID=1 order by DistrictNom; ");

                out.println(buildoptsFromDbResultSet(rs1,""));                                               
    
            }
              if(act.equals("getNakuruWards"))
            {               
                
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"select concat(ward_id,',',Ward) as rcd from internal_system.ward where subcountyid in (select DistrictId from district where CountyId=1 and active=1) order by Ward; ");

                out.println(buildoptsFromDbResultSet(rs1,""));                                               
    
            }
               if(act.equals("getNakuruSites"))
            {               
                
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"select concat(SubPartnerID,',',khisname,'-',CentreSanteId) as rcd from internal_system.subpartnera where DistrictID in (select DistrictId from district where CountyId=1 and active=1) order by khisname; ");

                out.println(buildoptsFromDbResultSet(rs1,""));                                               
    
            }
            
            
            if(act.equals("getgrant"))
            {               
                
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"select concat(grant_id,',',mechanism_name,' [',prime_award_number,'] ') as granti from grants.grants_infor");

                out.println(buildoptsFromDbResultSet(rs1,""));                                               
    
            }
            if(act.equals("dlt"))
            {               
             //This act deletes data that is shared per row id and per table
                System.out.println("Deleting data for "+pkidval);
                out.println(deleteRow(conn,pkidval,tablepkid,maintablename));  
                
                //Log the delete action into the Logs table
                  if (sess.getAttribute("kd_session") != null) {

                    HashMap<String, String> hm = new HashMap<>();

                    hm = (HashMap<String, String>) sess.getAttribute("kd_session");

                   // AuditTrail ad = new AuditTrail();
                   // ad.addTrail(conn, hm, "Deleted a Record "+pkidval+" from the table "+ " " + maintablename);

                }
                
    
            }
            //________________________________________________Applicant Details__________________________________
            
             if(act.equals("getorgtypes"))
            {               
                
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"select concat(id,',',organizationtypes) as rcd from grants.opts_organizationtypes where active=1; ");

                out.println(buildoptsFromDbResultSet(rs1,""));                                               
    
            }
             
             
              if(act.equals("getsolicitation"))
            {               
                
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"select concat(table_id,',',award_mechanism,' [',nofo_number,'] ') as nofo from grants.solicitation_infor");

                out.println(buildoptsFromDbResultSet(rs1,""));                                               
    
            }
              if(act.equals("getapplicants"))
            {               
                
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"select concat(table_id,',',organization_name) as applicant from grants.applicants_details");

                out.println(buildoptsFromDbResultSet(rs1,""));                                               
    
            }
              if(act.equals("getrecomendations"))
            {               
                
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"select concat(id,',',recommendation) as recommends from grants.opts_recommendation");

                out.println(buildoptsFromDbResultSet(rs1,""));                                               
    
            }
              
              
               if(act.equals("getDatimPeriod"))
            {               
                
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"select concat(quarterid,',',quarter) as prd from internal_system.vw_datimperiod order by startmonth DESC");

                out.println(buildoptsFromDbResultSet(rs1,""));                                               
    
            }
                   if(act.equals("getDatimSites"))
            {               
                
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"select concat(datimname,',',datimname,'-',CentreSanteID) as site from internal_system.subpartnera   where active =1  order by datimname ASC ");

                out.println(buildoptsFromDbResultSet(rs1,""));                                               
    
            }
            
            
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
            Logger.getLogger(dataPulls.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(dataPulls.class.getName()).log(Level.SEVERE, null, ex);
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

    
    public ResultSet pullAddedMothers(dbConn conn, String facilid) throws SQLException 
{
    
    //This method gets data that belongs to a specific form only
    String where="";

    
    
String qry="call internal_system.sp_pmtct_ovc_pull_mothers('"+facilid+"');";

    System.out.println(""+qry);
conn.rs=conn.st.executeQuery(qry);


return conn.rs;

}

    
    
        
       public ResultSet pullDataFromDbGivenQuery(dbConn conn,String qr) throws SQLException 
{
    
    //This method gets data from db


    
    
String qry=qr;

    System.out.println("_called_query:"+qry);
conn.rs=conn.st.executeQuery(qry);


return conn.rs;

}
    
    
       public ResultSet pullAddedDataPerFormForEditing(dbConn conn,String form, String facilid,String sp) throws SQLException 
{
    
    //This method gets data that belongs to a specific form only


    
    
String qry="call internal_system."+sp+"('"+facilid+"','"+form+"');";

    System.out.println("_called_query:"+qry);
conn.rs=conn.st.executeQuery(qry);


return conn.rs;

}
       public ResultSet pullFormElementdeadersForEditing(dbConn conn,String form) throws SQLException 
{
    
    //This method gets data that belongs to a specific form only
    String where="";

    
    
String qry="call internal_system.sp_pmtct_ovc_pull_form_headers_for_editing('"+form+"');";

    System.out.println(""+qry);
conn.rs=conn.st.executeQuery(qry);


return conn.rs;

}


public  String buildoptsFromDbResultSet(ResultSet res, String selectedvalue){

    
     String finalopts="<option value=''>select option</option>";
        try {
            //this method gets data from db and converts it to jsonArray, the from JSON array, get the JSONObjects in place
            
     

while(res.next()){
    
    //System.out.println("__*****"+res.getString(1));
    String valkey_in[]=res.getString(1).split(",");
    
  
    String selected="";
    if(selectedvalue.equals(valkey_in[0])){selected="selected";}
    
    finalopts+="<option "+selected+" value=\""+valkey_in[0]+"\">"+valkey_in[1]+"</option>";
  
    
                             }




        } catch (SQLException ex) {
            Logger.getLogger(dataPulls.class.getName()).log(Level.SEVERE, null, ex);
        }
  return finalopts;  
    
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



public String buildDataTable(dbConn con, ResultSet res, String elementtoappend,String tablecolid,String sourcetable) throws SQLException{
    
String finaltbl="";
String hdslist_html="";
String dtlist_html="";

String primarykeycolumnname="";



 
int count1=0;

  ResultSetMetaData metaData = res.getMetaData();
        int columnCount = metaData.getColumnCount();

         
        int count = count1;
        ArrayList mycolumns = new ArrayList();
    
    

while(res.next())
{
    
     if (count == (count1)) 
     {

                for (int i = 1; i <= columnCount; i++) 
                {
                    if(i==1){primarykeycolumnname=metaData.getColumnLabel(i);}
                       mycolumns.add(metaData.getColumnLabel(i));             
                     hdslist_html+="<th>"+metaData.getColumnLabel(i)+"</th>";
                }//end of for loop
                count++;
     }//end of if
     else { }
    
    


for(int c=0;c<mycolumns.size();c++)
{
    
      

      String id="";
      if(c==0){id="id='"+res.getString(mycolumns.get(c).toString())+"'";   dtlist_html+="<tr "+id+">";}
      dtlist_html+="<td>"+res.getString(mycolumns.get(c).toString())+"</td>";
      if(c==mycolumns.size()-1){ dtlist_html+="<td><label onclick='loadExistingData(\""+res.getString(tablecolid)+"\",\""+sourcetable+"\",\""+primarykeycolumnname+"\");' class='btn btn-info'>Edit</label></td><td><label onclick='dltrw(\""+res.getString(tablecolid)+"\",\""+sourcetable+"\",\"dlt\",\""+primarykeycolumnname+"\");' class='btn btn-danger'>Delete</label></td></tr>";}
//dltrw
}
    



count++;
    
}




finaltbl= "<table id='searchtable_"+elementtoappend+"' class='table table-striped table-bordered' border='1px'><thead><tr>"+hdslist_html+"<th>Edit</th><th>Delete</th></tr></thead><tbody>"+dtlist_html+"</tbody></table>";




return finaltbl;
}
    


public int getRandNo(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }



public String deleteRow(dbConn con, String rowid,String primarykeycolumn, String tbl)
{
     String status="Data deleted";
        try {
           
            
            String qry="delete from "+tbl+" where "+primarykeycolumn+"="+rowid;
            
            con.st.executeUpdate(qry);
            
            
           
        } catch (SQLException ex) {
            Logger.getLogger(dataPulls.class.getName()).log(Level.SEVERE, null, ex);
            
              status="Error while deleting data:"+ex;
        }
        
         return status;
}
}
