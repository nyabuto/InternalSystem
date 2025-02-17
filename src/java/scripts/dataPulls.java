/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripts;





import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.LinkedHashMap;
import java.util.Map;
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
            String datimindicswhr="";
            //loadmtrs_sel_val,act=loadmothers,fac
            
            
            
            String cnt="";
            String sct="";
            String rgn="";
            String fc="";
            String orgunitfilter="";
            
            String sd="";
            String ed="";
            String full_sd="";
            String full_ed="";
            String groupby="";
            String groupbyorgunit="";
            
            
            
            String ramcahstoredprocedure="";
            
            String qtr="";
            
            
            
            if(request.getParameter("act")!=null){act=request.getParameter("act");}
            if(request.getParameter("fc")!=null){fc=request.getParameter("fc");}
            if(request.getParameter("cnt")!=null){cnt=request.getParameter("cnt");}
            if(request.getParameter("mdt")!=null){rgn=request.getParameter("mdt");}
            if(request.getParameter("sct")!=null){sct=request.getParameter("sct");}
            if(request.getParameter("sd")!=null){sd=request.getParameter("sd");}
            if(request.getParameter("ed")!=null){ed=request.getParameter("ed");}
            if(request.getParameter("full_ed")!=null){full_ed=request.getParameter("full_ed");}
            if(request.getParameter("full_sd")!=null){full_sd=request.getParameter("full_sd");}
            if(request.getParameter("groupby")!=null){groupby=request.getParameter("groupby");}
            if(request.getParameter("groupbyorgunit")!=null){groupbyorgunit=request.getParameter("groupbyorgunit");}
            
            
            
            if(request.getParameter("sp")!=null){ramcahstoredprocedure=request.getParameter("sp");}
            
            
             String mywhere=" and 1=1 ";
             
             if(!fc.equals("")){mywhere+=" and sa.SubPartnerID in (\""+fc+"\") ";}
             else if(!sct.equals("")){mywhere+=" and sa.DistrictID in (\""+sct+"\") ";}
             else if(!rgn.equals("")){mywhere+=" and mdt in (\""+rgn+"\") ";}
             else if(!cnt.equals("")){mywhere+=" and ds.CountyID in (\""+cnt+"\") ";}
             //else if(!ed.equals("")){mywhere=" and cd.linelisting_month <= \""+ed+"\" ";}
             //else if(!sd.equals("")){mywhere=" and cd.linelisting_month >= \""+sd+"\" ";}


            
            if(request.getParameter("act")!=null){act=request.getParameter("act");}
            if(request.getParameter("fac")!=null){fac=request.getParameter("fac");}
            if(request.getParameter("pkid")!=null){tablepkid=request.getParameter("pkid");}
            if(request.getParameter("tablename")!=null){maintablename=request.getParameter("tablename");}
            if(request.getParameter("vwtable")!=null){vwtable=request.getParameter("vwtable");}
            if(request.getParameter("pkidval")!=null){pkidval=request.getParameter("pkidval");}
            if(request.getParameter("qtr")!=null){qtr=request.getParameter("qtr");}
           
            if(request.getParameter("loadmtrs_sel_val")!=null){loadmtrs_sel_val=request.getParameter("loadmtrs_sel_val");}
            
            
            if(act.equals("loadmothers")){out.println(buildoptsFromDbResultSet(pullAddedMothers(conn, fac),loadmtrs_sel_val));}
            
             if(request.getParameter("fm")!=null){fm=request.getParameter("fm");}
             if(request.getParameter("table_docker")!=null){table_docker=request.getParameter("table_docker");}
             if(request.getParameter("datimindicswhr")!=null){
                 
                 String prmsv[]=request.getParameter("datimindicswhr").split(",");
                 String scts="";
                 for(int cx=0;cx<prmsv.length;cx++){if(cx<prmsv.length-1){scts+="'"+prmsv[cx]+"',";} else {scts+="'"+prmsv[cx]+"'";}}
                 
                 String frqs="'QA'";
                 
                 if(qtr.contains("Q1")){frqs+=",'SA'";}
                 if(qtr.contains("Q3")){frqs+=",'AN','SA'";}
                 
                 datimindicswhr=" and Section in ("+scts+") and frequency in ("+frqs+") ";
             
             }
             System.out.println("Built Where ="+datimindicswhr);
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
            if(act.equals("getDatimSections"))
            {               
                
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"select concat(Section,',',Section) as rcd from datimbotqueries where active=1 group by Section order by `order` ; ");

                out.println(buildoptsFromDbResultSet(rs1,""));                                               
    
            }
            
            if(act.equals("getDatimIndicators"))
            {               
                
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"select concat(spname,',',Indicator) as rcd from datimbotqueries where active=1 "+datimindicswhr+" group by id order by `order`; ");

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
               if(act.equals("getNakuruCus"))
            {               
                
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"SELECT concat(cuid,',',cu,'-',CentreSanteID) as rcd FROM internal_system.cus join subpartnera sp on sp.CentreSanteId=cus.Mflcode;");

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
                //String siteswhere=" and subpartnera.DistrictID in (select DistrictId from district where CountyID in (1) )";
                String siteswhere="";
                 
                //siteswhere=" and subpartnera.CentreSanteID in (SELECT distinct(facility_id) FROM internal_system.fas_hypertension where yearmonth='202403' and total>0) ";
               // siteswhere=" and subpartnera.CentreSanteID in (14432,14607,15138,14477,20005,14609,15174,15305,15502,10890,15325,15339,15170,15266,10672,14404,15417,15589,14836,14431,16683,15398,15280,14805,14845,14551,15108,14801,14802,15406,25155,18009,15009,14265,15008,14207,14733,15365,14263,14177,20137,14224,14611,14223,14424,15678,16390,15495,15372,15331,15156,15358,15013,15200,14426,15126,14943,15768,15682,14212) ";
                 
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"select concat(datimname,',',datimname,'-',CentreSanteID) as site from internal_system.subpartnera   where active =1 "+siteswhere+" and datimname is not null  order by datimname ASC ");

                out.println(buildoptsFromDbResultSet(rs1,""));                                               
    
            }
                   
                    if(act.equals("getMonthlyBintiEnrollmentsChart"))
            {               
             //The idea here is to load data from multiple datatables dynamically into a web view. We are working with an assumption that each table has a unique Primary key id called tablepkid. We also have an assumption that the main table where the data is saved might be different from the view . 
               // For that reason we are sourcing for two tables/sources , 1 view for pulling preview data and a table which will be used as a destination
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"call sp_Binti_Shujaa_monthly_enrollments('"+mywhere+"')");
                System.out.println("______Pulling Data from "+maintablename);
                out.println(toJsonFormatDynamic(rs1));                                               
    
            }
                    
                            if(act.equals("getRamcahCharts"))
            {               
             //The idea here is to load data from multiple datatables dynamically into a web view. We are working with an assumption that each table has a unique Primary key id called tablepkid. We also have an assumption that the main table where the data is saved might be different from the view . 
               // For that reason we are sourcing for two tables/sources , 1 view for pulling preview data and a table which will be used as a destination
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"call "+ramcahstoredprocedure+"('"+mywhere+"')");
                System.out.println("______Pulling Data from "+maintablename);
                out.println(toJsonFormatDynamic(rs1));                                             
    
            }
                            if(act.equals("getEmrCascades"))
            {               
             //The idea here is to load data from multiple datatables dynamically into a web view. We are working with an assumption that each table has a unique Primary key id called tablepkid. We also have an assumption that the main table where the data is saved might be different from the view . 
               // For that reason we are sourcing for two tables/sources , 1 view for pulling preview data and a table which will be used as a destination
               ResultSet rs1=pullDataFromDbGivenQuery(conn,"call analytics_emr_cascades('"+mywhere+"', '"+full_sd+"', '"+full_ed+"', '"+groupbyorgunit+"', '"+groupby+"')");
                System.out.println("______Pulling Data from "+maintablename);
                out.println(toJsonFormatDynamic(rs1));                                             
    
            }
                            if(act.equals("getEmrCascadesReadable"))
            {               
             //The idea here is to load data from multiple datatables dynamically into a web view. We are working with an assumption that each table has a unique Primary key id called tablepkid. We also have an assumption that the main table where the data is saved might be different from the view . 
               // For that reason we are sourcing for two tables/sources , 1 view for pulling preview data and a table which will be used as a destination
               ResultSet rs3=pullDataFromDbGivenQuery(conn,"call analytics_emr_cascades_mdt('"+mywhere+"', '"+full_sd+"', '"+full_ed+"', '"+groupbyorgunit+"', '"+groupby+"')");
             
                
                
//                JSONArray ja= toJsonFormatDynamic(rs3);
//                System.out.println("______Pulling Data from "+ja);
//                
                out.println(toOrderedJsonFormatDynamic(rs3)); 
    
            }
                            if(act.equals("getEmrRdqaData"))
            {               
             //The idea here is to load data from multiple datatables dynamically into a web view. We are working with an assumption that each table has a unique Primary key id called tablepkid. We also have an assumption that the main table where the data is saved might be different from the view . 
               // For that reason we are sourcing for two tables/sources , 1 view for pulling preview data and a table which will be used as a destination
               ResultSet rs3=pullDataFromDbGivenQuery(conn,"call analytics_emr_rdqa('"+mywhere+"', '"+full_sd+"', '2024-11-30', '"+groupbyorgunit+"', '"+groupby+"')");
             
                
                
//                JSONArray ja= toJsonFormatDynamic(rs3);
              System.out.println("call analytics_emr_rdqa('"+mywhere+"', '"+full_sd+"', '"+full_ed+"', '"+groupbyorgunit+"', '"+groupby+"')");
//                
                out.println(toOrderedJsonFormatDynamic(rs3)); 
    
            }
            
            
                   
                   if(conn.rs!=null){conn.rs.close();}
                   if(conn.rs1!=null){conn.rs1.close();}
                   if(conn.st!=null){conn.st.close();}
                   if(conn.st1!=null){conn.st1.close();}
                   if(conn.conn!=null){conn.conn.close();}
                   
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
Map<String, Object> orderedMap = new LinkedHashMap<>();

for(int c=0;c<mycolumns.size();c++){
    String vl="";
    if(res.getString(mycolumns.get(c).toString())!=null){vl=res.getString(mycolumns.get(c).toString());}
jo.put(mycolumns.get(c).toString(),vl);
    
}

//System.out.println("On result set  to Json Format Conversion, the JSON Object data is: "+orderedMap);


jo2.put(jo);
    
count++;
    
}
    
return jo2;    
}


public JSONArray toOrderedJsonFormatDynamic(ResultSet res) 
        throws SQLException, JsonProcessingException
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
    
    
    JSONArray jo3 = new JSONArray();
    
JSONObject jo = new JSONObject(); 

LinkedHashMap<String, String> orderedMap = new LinkedHashMap<String,String>();

for(int c=0;c<mycolumns.size();c++)
{
    String vl="";
    if(res.getString(mycolumns.get(c).toString())!=null){vl=res.getString(mycolumns.get(c).toString());}
    
    
orderedMap.put(mycolumns.get(c).toString(),vl);
jo.put(mycolumns.get(c).toString(),vl);
   



}

ObjectMapper om = new ObjectMapper();
       String  json;
   json = om.writerWithDefaultPrettyPrinter().writeValueAsString(orderedMap);

   
    String underscore="\"";
   String underscore1="\"\"";
   
    System.out.println("underscore:"+underscore+":underscore1"+underscore1);
   
  json = json.replace("\r\n", "");
  json = json.replace("\"",underscore);
  json = json.replace("\",  \"","\",\"");
  json = json.replace(" : ",":");
  json = json.replace("{  \"","{\"");
//json = json.replaceAll("'", "\"");
     //ObjectMapper objectMapper = new ObjectMapper();
        //String unescaped = objectMapper.readValue(json, String.class);
//    Object jsonObject = om.readValue(json, Object.class);
  
jo=  new JSONObject(orderedMap);
System.out.println("Ordered Map is is:: "+json+"");





jo2.put(json+"");
    
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
