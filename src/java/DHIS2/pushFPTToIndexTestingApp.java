/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHIS2;

import database.dbConn;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author EKaunda
 */
public class pushFPTToIndexTestingApp extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
      
        /**
        dbConn conn= new dbConn();
       dhisconfig dc = new dhisconfig();
       
       //Get this from a DB
       dc.setDhis2_username("Rosoti");
       dc.setDhis2_Password("ORobert2021!");       
       
        //getDistinctColumns(conn,"Form 1a");
       ArrayList cols=getDistinctF1aColumns(conn,"Form 1 A");
       ResultSet f1adata=GetForm1aData(conn, "202102", "134", cols);
        
      try {
            JSONObject jo=toJsonString(conn,f1adata, cols, dc.getDhis2_username());
            System.out.println(""+jo);
            UploadF1aToServer(jo,dc.getDhis2_username(),dc.getDhis2_Password() );            
            
        } catch (SQLException ex) 
        {
            Logger.getLogger(pushDataToDHIS2.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      **/
        
        
        
        out.println("Data import process");
        out.close();
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

public ResultSet getDistinctColumns(dbConn conn, String Formname)
{
    ArrayList  distinctcatcombos= new ArrayList(); 
        try {
                     
            String getDistinctCatcombos="SELECT distinct(producercatcomboname) as f1acol, consumercatcomboid as khis_column FROM dhis2_businessrule_hit  where dataset ='"+Formname+"'" ;
            
            conn.rs=conn.st.executeQuery(getDistinctCatcombos);
            
        } catch (SQLException ex) {
            Logger.getLogger(pushDataToDHIS2.class.getName()).log(Level.SEVERE, null, ex);
        }

return conn.rs;
}

public HashMap getAllowedColumnsElementCombinatin(dbConn conn, String Formname)
{
    ArrayList  distinctcatcombos= new ArrayList(); 
   
    HashMap<String, String> hm= new HashMap<String, String>();
    
   
    
        try {
                     
            String getDistinctCatcombos="SELECT concat(producerdeid,producercatcomboname)  as f1acol, concat(consumerdeid,consumercatcomboid) as khis_column FROM dhis2_businessrule_hit  where dataset ='"+Formname+"'" ;
            
            conn.rs=conn.st.executeQuery(getDistinctCatcombos);
            while(conn.rs.next()){
                
            hm.put(conn.rs.getString(2),conn.rs.getString(2));
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(pushDataToDHIS2.class.getName()).log(Level.SEVERE, null, ex);
        }

return hm;
}

public ArrayList getDistinctFormColumns(dbConn conn, String Formname)
{
   ArrayList imis_dhis_al= new ArrayList();
    
        try {
                     
            String getDistinctCatcombos="SELECT consumercatcomboid as khis_column , producercatcomboname as f1acol  FROM dhis2_businessrule_hit  where dataset ='"+Formname+"' group by consumercatcomboid" ;
            System.out.println(""+getDistinctCatcombos);
            conn.rs_1=conn.st_5.executeQuery(getDistinctCatcombos);
            while(conn.rs_1.next())
            {
            
              imis_dhis_al.add(conn.rs_1.getString(2)+" "+conn.rs_1.getString(1));
            
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(pushDataToDHIS2.class.getName()).log(Level.SEVERE, null, ex);
        }

return imis_dhis_al;
}

public ResultSet GetFormData(dbConn conn, String tablename, String Yearmonth_coma_separated, String facilityid, ArrayList colnames){

    
        try {
            String where = "";
            
            if (!Yearmonth_coma_separated.equals("")) {
                
                where = " and yearmonth in (" + Yearmonth_coma_separated + ") ";
            }
            
            String facilwhere = "";
            
            if (!Yearmonth_coma_separated.equals(""))
            {
                
                where += " and facility_id in (" + facilityid + ") ";
            }
            
            
            String qry="Select * from "+tablename+" where 1=1  "+where;
            
            
            conn.rs=conn.st.executeQuery(qry);
        } catch (SQLException ex) {
            Logger.getLogger(pushDataToDHIS2.class.getName()).log(Level.SEVERE, null, ex);
        }


        return conn.rs;
        
}




public ResultSet GetForm1aData(dbConn conn, String Yearmonth_coma_separated, String facilityid){
    
    //We are working with Form 1a , which is our main form
    
    //in this phase, we are pulling data from three main tables namely fpt (which has all the data for fpt) subpartnera (which has data including orgunit names in ANYB KHIS instance and)
    
    
            String maintablename="fpt_baseline";
    
            try {
            String where = "";
            
            if (!Yearmonth_coma_separated.equals("")) 
            {   
                where = " and yearmonth in ("+Yearmonth_coma_separated + ") ";
            }
            
    
            
            if (!facilityid.equals(""))
            {                
                where += " and facility_id in (" + facilityid + ") ";
            }
            
            
            String qry="Select "
                    + " mt.id as datarecordid, "
                    + "facility_id, "
                    + "indicator_id, "
                    + "yearmonth, " 
                    
                    +"case when producercatcomboname='m_uk' then IFNULL(m_uk, '0') \n" +
"     when producercatcomboname='f_uk' then IFNULL(f_uk,'0')  \n" +
"     when producercatcomboname='m_1' then IFNULL(m_1, '0') \n" +
"     when producercatcomboname='f_1' then IFNULL(f_1, '0') \n" +
"     when producercatcomboname='m_4' then IFNULL(m_4, '0') \n" +
"     when producercatcomboname='f_4' then IFNULL(f_4, '0') \n" +
"     when producercatcomboname='m_9' then IFNULL(m_9, '0') \n" +
"     when producercatcomboname='f_9' then IFNULL(f_9, '0') \n" +
"     when producercatcomboname='m_14' then IFNULL(m_14, '0') \n" +
"     when producercatcomboname='f_14' then IFNULL(f_14, '0') \n" +
"     when producercatcomboname='m_17' then IFNULL(m_17, '0') \n" +
"     when producercatcomboname='f_17' then IFNULL(f_17, '0') \n" +
"     when producercatcomboname='m_18' then IFNULL(m_18, '0') \n" +
"     when producercatcomboname='f_18' then IFNULL(f_18, '0')\n" +
"     when producercatcomboname='total' then IFNULL(total, '0')     \n" +
"     else '00' end as value,"
+ " concat(main_indicator,' ',indicator) as producerdename, "
                   
                    + "user_id, "
                      +" substring(timestamp,1,10) as completedate ,"              
                    //Subpartner A Begins Here
                    
                    + "SubPartnerID, "
                    + "SubPartnerNom,  "
                    + "CentreSanteId, "
                    + "sp.HTC,"
                    + "sp.PMTCT, "
                    + "sp.ART,"
                   // + "ward, "
                    + "active, "
                   // + "datimward, "
                   // + "datimid, "
                  //  + "datimname, "
                    + "khisid, "
                    + "khisname, "
                    // Business Rule table begins here                    
                    + "recordid, "
                    + "producerdeid, "
                    + "consumerdeid,  "
                    + "producerdename, "
                    + "consumerdename, "
                    + "br.dataset, "
                    + "attributeoptioncomboid, "
                    + "attributeoptioncomboname , producercatcomboname ,consumercatcomboid"
                    + " from "+maintablename+" mt "
                    + " join subpartnera_vw sp on sp.SubPartnerID = mt.facility_id"
                    +" left join dhis2_businessrule_hit br on br.producerdeid=mt.indicator_id  "
                    + " left join fpt_indicators fpi on fpi.id=mt.indicator_id "
                    +"   where sp.active>=1  "+where+" and br.dataset='Clinical & OVC Index Testing Reporting' and producerdeid is not null   group by mt.indicator_id,consumercatcomboid ";
            
                System.out.println(""+qry);
            
            conn.rs1=conn.st1.executeQuery(qry);
        } catch (SQLException ex) {
            Logger.getLogger(pushDataToDHIS2.class.getName()).log(Level.SEVERE, null, ex);
        }


        return conn.rs1;
        
}



public JSONArray toJsonArray (ResultSet res) throws SQLException{

    
    
JSONArray jo2 = new JSONArray();
int count=0;

while(res.next())
{
    
JSONObject jo = new JSONObject(); 
String id="";
String indicator_code="";
String active="";
String indicatorname="";
String orodha="";

    id =res.getString("id");
    indicator_code =res.getString("indicator_code");
    indicatorname =res.getString("indicatorname");
    orodha =res.getString("orodha");

    jo.put("id",id);
    jo.put("indicator_code",indicator_code);
    jo.put("indicatorname",indicatorname);
    jo.put("orodha",orodha);
    jo2.put(jo);
    
    count++;
    
}


    
    
return jo2;    
}



public JSONObject toJsonString (dbConn conn,ResultSet datavaluesres, ArrayList categres, String sendername, HashMap dvlsob) throws SQLException{
      
            conn.rs1=datavaluesres;
           
            
            int count=0;
            JSONArray ja = new JSONArray();
            JSONObject mainjo = new JSONObject();
            JSONObject minijo = new JSONObject();
                   
            
            String dv="";
            while(conn.rs1.next())
            {                
                int colpos=0;
               // System.out.println(" /** row number "+count+" **/");
                if(count==0){
                    
                    String compdate=conn.rs1.getString("completedate");
                    String ym=conn.rs1.getString("yearmonth");
                    String orgunitid=conn.rs1.getString("khisid");
                    
                     mainjo.put("dataSet","veM8P1BF9Yq");                   
                     mainjo.put("completeDate",compdate);
                     mainjo.put("period",ym);
                     mainjo.put("orgUnit",orgunitid);
                     mainjo.put("attributeOptionCombo","ffOSsK1od9t");
                     
                 
                }
                
                
                for( int x=0;x<categres.size();x++)  {
            JSONObject smalljo = new JSONObject();         
                    
                    colpos++;
                    //loop each row
                    //categres is an arraylist containing both imis column name and DHIS2 category option combo 
                    String[] cols=categres.get(x).toString().split(" ");
                    
                    String deid=conn.rs1.getString("consumerdeid");
                    String cat_opt_combo=cols[1];
                    String f1acol=cols[0];
                    String val=conn.rs1.getString(f1acol);
                   // System.out.print(" |"+colpos+"= "+val+" |");
                   //here , only put values for allowed data element id and category option combo
                   String keyv=deid+cat_opt_combo;
                   
                   if(val!=null ){
                        //f1acol
                     if(dvlsob.get(keyv)!=null )
                      {
                    smalljo.put("dataElement", deid);
                    smalljo.put("categoryOptionCombo", cat_opt_combo);
                    smalljo.put("value", val);
                    smalljo.put("storedby", sendername);                    
                    ja.put(smalljo);
                   }
                     else {
                         System.out.println("Skipped:"+keyv);
                     }
                    }
                    
                }
              
                
                count++;
                
                
            }
             mainjo.put("dataValues",ja);
                   
                          
           
       
         return mainjo;
}
public JSONObject toJsonString (dbConn conn,ResultSet datavaluesres, String sendername) throws SQLException{
      
            conn.rs1=datavaluesres;
           
            
            int count=0;
            JSONArray ja = new JSONArray();
            JSONObject mainjo = new JSONObject();
            JSONObject minijo = new JSONObject();
                   
            
            String dv="";
            while(conn.rs1.next())
            {                
                int colpos=0;
               // System.out.println(" /** row number "+count+" **/");
                if(count==0){
                    
                    String compdate=conn.rs1.getString("completedate");
                    String ym=conn.rs1.getString("yearmonth");
                    String orgunitid=conn.rs1.getString("khisid");
                    
                     mainjo.put("dataSet","veM8P1BF9Yq");                   
                     mainjo.put("completeDate",compdate);
                     mainjo.put("period",ym);
                     mainjo.put("orgUnit",orgunitid);
                   //mainjo.put("attributeOptionCombo","ffOSsK1od9t");
                     mainjo.put("attributeOptionCombo","F61aytoeI2h");
                     
                 
                }
                
                
                
            JSONObject smalljo = new JSONObject();         
                    
                    colpos++;
                    //loop each row
                    //categres is an arraylist containing both imis column name and DHIS2 category option combo 
                   
                    
                    String deid=conn.rs1.getString("consumerdeid");                    
                    String cat_opt_combo=conn.rs1.getString("consumercatcomboid");
                    String val=conn.rs1.getString("value");
                   // System.out.print(" |"+colpos+"= "+val+" |");
                   //here , only put values for allowed data element id and category option combo
                   String keyv=deid+cat_opt_combo;
                   
                   if(val!=null ){
                        //f1acol
                     
                    smalljo.put("dataElement", deid);
                    smalljo.put("categoryOptionCombo", cat_opt_combo);
                    smalljo.put("value", val);
                    smalljo.put("storedby", sendername);                    
                    ja.put(smalljo);
                   
                    }
                    
                
              
                
                count++;
                
                
            }
             mainjo.put("dataValues",ja);
                   
                          
           
       
         return mainjo;
}

public JSONObject UploadFPTToServer( JSONObject jd, String user,String password ){

    JSONObject jo = new JSONObject();
    
    
    System.out.println("User ni:"+user);
    System.out.println("Password ni:"+password);
        try {           
            
            URL url = new URL ("https://partnermanagementsystem.uonbi.ac.ke/api/dataValueSets");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");            
            con.setDoOutput(true);
            //authentication
String auth = user + ":" + password;
byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
String authHeaderValue = "Basic " + new String(encodedAuth);
con.setRequestProperty("Authorization", authHeaderValue);

Authenticator.setDefault(new BasicAuthenticator());

            //String jsonInputString = "{\"dataValues\":[{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"YU9cMzNN5Z4\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"YU9cMzNN5Z4\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"YU9cMzNN5Z4\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"YU9cMzNN5Z4\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"YU9cMzNN5Z4\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"YU9cMzNN5Z4\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"YU9cMzNN5Z4\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"YU9cMzNN5Z4\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"YU9cMzNN5Z4\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"YU9cMzNN5Z4\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"ROTXrfnTyUj\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"ROTXrfnTyUj\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"ROTXrfnTyUj\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"ROTXrfnTyUj\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"ROTXrfnTyUj\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"z3jWpezu0wD\",\"dataElement\":\"ROTXrfnTyUj\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"ROTXrfnTyUj\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"ROTXrfnTyUj\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"ROTXrfnTyUj\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"TYbTfNn058m\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"TYbTfNn058m\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"TYbTfNn058m\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"TYbTfNn058m\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"TYbTfNn058m\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"TYbTfNn058m\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"TYbTfNn058m\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"LkhWCPBKeAf\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"LkhWCPBKeAf\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"LkhWCPBKeAf\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"LkhWCPBKeAf\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"LkhWCPBKeAf\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"LkhWCPBKeAf\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"LkhWCPBKeAf\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"zI1KEmQtY1q\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"zI1KEmQtY1q\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"zI1KEmQtY1q\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"zI1KEmQtY1q\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"zI1KEmQtY1q\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"zI1KEmQtY1q\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"zI1KEmQtY1q\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"iTA6zSadQHy\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"iTA6zSadQHy\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"iTA6zSadQHy\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"iTA6zSadQHy\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"iTA6zSadQHy\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"r4wyuMDbNEr\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"r4wyuMDbNEr\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"r4wyuMDbNEr\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"r4wyuMDbNEr\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"r4wyuMDbNEr\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"XpPlStG5Kfl\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"XpPlStG5Kfl\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"XpPlStG5Kfl\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"XpPlStG5Kfl\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"XpPlStG5Kfl\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"XpPlStG5Kfl\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"XpPlStG5Kfl\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"BbZAyepmK0t\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"BbZAyepmK0t\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"BbZAyepmK0t\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"gWdWllmDt4s\",\"value\":\"29\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"gWdWllmDt4s\",\"value\":\"54\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"gWdWllmDt4s\",\"value\":\"54\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"gWdWllmDt4s\",\"value\":\"20\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"gWdWllmDt4s\",\"value\":\"8\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"gWdWllmDt4s\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"gWdWllmDt4s\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"gWdWllmDt4s\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"gWdWllmDt4s\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"V24PhCCz2tl\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"V24PhCCz2tl\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"V24PhCCz2tl\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"iFCdq3lRwfI\",\"value\":\"29\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"iFCdq3lRwfI\",\"value\":\"54\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"iFCdq3lRwfI\",\"value\":\"54\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"iFCdq3lRwfI\",\"value\":\"21\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"iFCdq3lRwfI\",\"value\":\"8\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"iFCdq3lRwfI\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"iFCdq3lRwfI\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"iFCdq3lRwfI\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"iFCdq3lRwfI\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"J6ZTnJhGQ0L\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"J6ZTnJhGQ0L\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"J6ZTnJhGQ0L\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"PVbpglponXZ\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"PVbpglponXZ\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"PVbpglponXZ\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"huKjiZb68as\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"14\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"eZkyt4uefJr\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"5\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"15\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"14\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"19\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"9\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"12\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"8\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"17\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"6\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"11\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"7\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"14\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"21\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"N8UaCDXKaqd\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"MytyTEVeC7d\",\"value\":\"5\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"MytyTEVeC7d\",\"value\":\"11\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"MytyTEVeC7d\",\"value\":\"11\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"MytyTEVeC7d\",\"value\":\"18\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"MytyTEVeC7d\",\"value\":\"8\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"MytyTEVeC7d\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"MytyTEVeC7d\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"MytyTEVeC7d\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"MytyTEVeC7d\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"aIe6T5RhMD1\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"Pl8w8L1coCc\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"huKjiZb68as\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"hes20IFrxEi\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"eZkyt4uefJr\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"z3jWpezu0wD\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"WF2FtxV0q6G\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"aIe6T5RhMD1\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"Pl8w8L1coCc\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"18\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"20\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"huKjiZb68as\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"hes20IFrxEi\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"7\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"22\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"eZkyt4uefJr\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"13\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"22\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"8\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"44\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"11\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"76\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"19\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"68\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"32\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"90\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"38\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"71\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"46\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"145\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"77\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"z3jWpezu0wD\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"8\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"11\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"nHZg3jOrbYu\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"aIe6T5RhMD1\",\"dataElement\":\"FInmhZZay69\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"Pl8w8L1coCc\",\"dataElement\":\"FInmhZZay69\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"FInmhZZay69\",\"value\":\"18\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"FInmhZZay69\",\"value\":\"20\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"huKjiZb68as\",\"dataElement\":\"FInmhZZay69\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"hes20IFrxEi\",\"dataElement\":\"FInmhZZay69\",\"value\":\"7\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"FInmhZZay69\",\"value\":\"22\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"eZkyt4uefJr\",\"dataElement\":\"FInmhZZay69\",\"value\":\"13\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"FInmhZZay69\",\"value\":\"22\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"FInmhZZay69\",\"value\":\"8\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"FInmhZZay69\",\"value\":\"44\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"FInmhZZay69\",\"value\":\"11\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"FInmhZZay69\",\"value\":\"76\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"FInmhZZay69\",\"value\":\"19\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"FInmhZZay69\",\"value\":\"68\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"FInmhZZay69\",\"value\":\"32\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"FInmhZZay69\",\"value\":\"90\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"FInmhZZay69\",\"value\":\"38\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"FInmhZZay69\",\"value\":\"71\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"FInmhZZay69\",\"value\":\"46\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"FInmhZZay69\",\"value\":\"145\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"FInmhZZay69\",\"value\":\"77\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"z3jWpezu0wD\",\"dataElement\":\"FInmhZZay69\",\"value\":\"8\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"FInmhZZay69\",\"value\":\"11\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"FInmhZZay69\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"FInmhZZay69\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"JDSO97Tb8rX\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"JDSO97Tb8rX\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"JDSO97Tb8rX\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"JDSO97Tb8rX\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"fDxZbUApjYg\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"fDxZbUApjYg\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"fDxZbUApjYg\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"fDxZbUApjYg\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"pfVeFedz3cG\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"pfVeFedz3cG\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"pfVeFedz3cG\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"pfVeFedz3cG\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"pfVeFedz3cG\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"pfVeFedz3cG\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"pfVeFedz3cG\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"pfVeFedz3cG\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"pfVeFedz3cG\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"pfVeFedz3cG\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"rzxdWweN46k\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"rzxdWweN46k\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"rzxdWweN46k\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"rzxdWweN46k\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"rzxdWweN46k\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"rzxdWweN46k\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"rzxdWweN46k\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"rzxdWweN46k\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"rzxdWweN46k\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"rzxdWweN46k\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"eZkyt4uefJr\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"7\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"h7CZlKL0inz\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"GL3EcT3ZxID\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"GL3EcT3ZxID\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"GL3EcT3ZxID\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"GL3EcT3ZxID\",\"value\":\"7\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"GL3EcT3ZxID\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"GL3EcT3ZxID\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"GL3EcT3ZxID\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"GL3EcT3ZxID\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"GL3EcT3ZxID\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"GL3EcT3ZxID\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"GL3EcT3ZxID\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"GL3EcT3ZxID\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"GL3EcT3ZxID\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"GL3EcT3ZxID\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"aIe6T5RhMD1\",\"dataElement\":\"qv5A55tA7gj\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"Pl8w8L1coCc\",\"dataElement\":\"qv5A55tA7gj\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"qv5A55tA7gj\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"qv5A55tA7gj\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"aIe6T5RhMD1\",\"dataElement\":\"hfKD5tnckMv\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"Pl8w8L1coCc\",\"dataElement\":\"hfKD5tnckMv\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"hfKD5tnckMv\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"hfKD5tnckMv\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"aIe6T5RhMD1\",\"dataElement\":\"Xt4oqM3I3rH\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"Pl8w8L1coCc\",\"dataElement\":\"Xt4oqM3I3rH\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"Xt4oqM3I3rH\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"Xt4oqM3I3rH\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"hSf6j2rqFSq\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"hSf6j2rqFSq\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"hSf6j2rqFSq\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"hSf6j2rqFSq\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"hSf6j2rqFSq\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"hSf6j2rqFSq\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"hSf6j2rqFSq\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"hSf6j2rqFSq\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"hSf6j2rqFSq\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"hSf6j2rqFSq\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"SVDGzLqhzlj\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"SVDGzLqhzlj\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"SVDGzLqhzlj\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"SVDGzLqhzlj\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"SVDGzLqhzlj\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"SVDGzLqhzlj\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"SVDGzLqhzlj\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"Pl8w8L1coCc\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"5\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"eZkyt4uefJr\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"5\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"8\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"9\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"5\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"6\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"5\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"5\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"5\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"E6yHKPrvgoB\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"MS3Yd2eUhAn\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"MS3Yd2eUhAn\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"MS3Yd2eUhAn\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"MS3Yd2eUhAn\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"MS3Yd2eUhAn\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"MS3Yd2eUhAn\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"MS3Yd2eUhAn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"MS3Yd2eUhAn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"aIe6T5RhMD1\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"Pl8w8L1coCc\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"huKjiZb68as\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"hes20IFrxEi\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"eZkyt4uefJr\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"z3jWpezu0wD\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"hIiE7MbBOZV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"CEropoSJImM\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"CEropoSJImM\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"CEropoSJImM\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"CEropoSJImM\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"CEropoSJImM\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"CEropoSJImM\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"CEropoSJImM\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"18\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"20\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"huKjiZb68as\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"hes20IFrxEi\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"7\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"22\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"eZkyt4uefJr\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"13\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"22\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"8\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"41\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"11\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"74\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"19\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"68\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"29\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"89\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"36\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"69\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"46\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"142\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"76\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"z3jWpezu0wD\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"8\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"11\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"uJcfy4IEbkU\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"aIe6T5RhMD1\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"Pl8w8L1coCc\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"huKjiZb68as\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"hes20IFrxEi\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"eZkyt4uefJr\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"z3jWpezu0wD\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"RQJfNVToQp9\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"aIe6T5RhMD1\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"Pl8w8L1coCc\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"huKjiZb68as\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"hes20IFrxEi\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"eZkyt4uefJr\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"z3jWpezu0wD\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"mH3TodgebfA\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"huKjiZb68as\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"hes20IFrxEi\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"eZkyt4uefJr\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"z3jWpezu0wD\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"EW5QUimZlky\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"huKjiZb68as\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"hes20IFrxEi\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"eZkyt4uefJr\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"z3jWpezu0wD\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"wPDujpKEejn\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"KTQIlMiugpJ\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"KTQIlMiugpJ\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"huKjiZb68as\",\"dataElement\":\"KTQIlMiugpJ\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"hes20IFrxEi\",\"dataElement\":\"KTQIlMiugpJ\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"KTQIlMiugpJ\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"KTQIlMiugpJ\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"KTQIlMiugpJ\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"KTQIlMiugpJ\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"KTQIlMiugpJ\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"KTQIlMiugpJ\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"KTQIlMiugpJ\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"KTQIlMiugpJ\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"z3jWpezu0wD\",\"dataElement\":\"KTQIlMiugpJ\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"KTQIlMiugpJ\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"KTQIlMiugpJ\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"KTQIlMiugpJ\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"13\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"7\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"huKjiZb68as\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"9\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"hes20IFrxEi\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"13\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"26\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"eZkyt4uefJr\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"8\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"95\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"26\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"107\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"28\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"56\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"19\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"31\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"13\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"21\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"11\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"5\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"7\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"17\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"19\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"z3jWpezu0wD\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"5\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"7\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"SHw30VfVQ4w\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"TNMF9IgU2AV\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"TNMF9IgU2AV\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"TNMF9IgU2AV\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"TNMF9IgU2AV\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"TNMF9IgU2AV\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"TNMF9IgU2AV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"TNMF9IgU2AV\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"KmyFPkTsRIs\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"KmyFPkTsRIs\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"KmyFPkTsRIs\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"eMUrfBhuSPo\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"eMUrfBhuSPo\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"eMUrfBhuSPo\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"eMUrfBhuSPo\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"eMUrfBhuSPo\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"z3jWpezu0wD\",\"dataElement\":\"eMUrfBhuSPo\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"eMUrfBhuSPo\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"eMUrfBhuSPo\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"OlRLOK5wB0H\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"OlRLOK5wB0H\",\"value\":\"8\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"OlRLOK5wB0H\",\"value\":\"6\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"OlRLOK5wB0H\",\"value\":\"9\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"OlRLOK5wB0H\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"OlRLOK5wB0H\",\"value\":\"8\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"OlRLOK5wB0H\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"OlRLOK5wB0H\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"OlRLOK5wB0H\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"OlRLOK5wB0H\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"OlRLOK5wB0H\",\"value\":\"7\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"OlRLOK5wB0H\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"OlRLOK5wB0H\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"eZkyt4uefJr\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"aajzgQyAXPh\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"Bj9qZ7ofdTw\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"Bj9qZ7ofdTw\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"Bj9qZ7ofdTw\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"Bj9qZ7ofdTw\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"Bj9qZ7ofdTw\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"Bj9qZ7ofdTw\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"Bj9qZ7ofdTw\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"Bj9qZ7ofdTw\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"Bj9qZ7ofdTw\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"Bj9qZ7ofdTw\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"xSo5JVGgxZd\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"xSo5JVGgxZd\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"xSo5JVGgxZd\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"xSo5JVGgxZd\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"xSo5JVGgxZd\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"xSo5JVGgxZd\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"xSo5JVGgxZd\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"xSo5JVGgxZd\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"xSo5JVGgxZd\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"xSo5JVGgxZd\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"eZkyt4uefJr\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"QIbA2xE2LEH\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"GWvPuPkOfnM\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"EAYKTftuGde\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"Pd3cHDymqYM\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"VX81WBcijxm\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"VX81WBcijxm\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"VX81WBcijxm\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"VX81WBcijxm\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"H4TbjH7s6Tk\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RjcA5HVcLE9\",\"dataElement\":\"H4TbjH7s6Tk\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"H4TbjH7s6Tk\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"H4TbjH7s6Tk\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"TxYugma37Ks\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"TxYugma37Ks\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"TxYugma37Ks\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"kIrz7Z2i4L1\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"kIrz7Z2i4L1\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"kIrz7Z2i4L1\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"gLwXfkvRunX\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"gLwXfkvRunX\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"gLwXfkvRunX\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"jqVyh3Ygt1V\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"jqVyh3Ygt1V\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"jqVyh3Ygt1V\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"lGv4xqMDAXo\",\"value\":\"4\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"lGv4xqMDAXo\",\"value\":\"18\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"RvU0yLMn22B\",\"dataElement\":\"lGv4xqMDAXo\",\"value\":\"16\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"lGv4xqMDAXo\",\"value\":\"19\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"lGv4xqMDAXo\",\"value\":\"23\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"lGv4xqMDAXo\",\"value\":\"19\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"lGv4xqMDAXo\",\"value\":\"32\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"lGv4xqMDAXo\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"lGv4xqMDAXo\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"GYgFSOCHuT5\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"UB3pFULJ73c\",\"dataElement\":\"GYgFSOCHuT5\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"GYgFSOCHuT5\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tXTQ61K1KoG\",\"dataElement\":\"GYgFSOCHuT5\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"tkhx795suAZ\",\"dataElement\":\"GYgFSOCHuT5\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"GYgFSOCHuT5\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"GYgFSOCHuT5\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"dfVtvINl233\",\"dataElement\":\"jwuXNDsS9ZE\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"lecG5Vk5DyA\",\"dataElement\":\"jwuXNDsS9ZE\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"huKjiZb68as\",\"dataElement\":\"jwuXNDsS9ZE\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"hes20IFrxEi\",\"dataElement\":\"jwuXNDsS9ZE\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"PeJqk8U9UIR\",\"dataElement\":\"jwuXNDsS9ZE\",\"value\":\"2\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"soEeiHrxjmj\",\"dataElement\":\"jwuXNDsS9ZE\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"haNgZqcrBjw\",\"dataElement\":\"jwuXNDsS9ZE\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"n3V2SQpNfHH\",\"dataElement\":\"jwuXNDsS9ZE\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"ClUDIo0lomS\",\"dataElement\":\"jwuXNDsS9ZE\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"U7TymMXMhAk\",\"dataElement\":\"jwuXNDsS9ZE\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"z3jWpezu0wD\",\"dataElement\":\"jwuXNDsS9ZE\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"XJti2nQFQvL\",\"dataElement\":\"jwuXNDsS9ZE\",\"value\":\"3\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"jwuXNDsS9ZE\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"jwuXNDsS9ZE\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"BTaJZyeiDqC\",\"dataElement\":\"GhVEsC0C70t\",\"value\":\"1\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"bnwh9Vk0DA2\",\"dataElement\":\"GhVEsC0C70t\",\"value\":\"0\"},{\"storedby\":\"ROsoti\",\"categoryOptionCombo\":\"xgUF501gmXL\",\"dataElement\":\"GhVEsC0C70t\",\"value\":\"0\"}],\"period\":\"202102\",\"orgUnit\":\"tGLKciw2MGf\",\"attributeOptionCombo\":\"HllvX50cXC0\",\"dataSet\":\"kwoxlVdwR8d\",\"completeDate\":\"2021-03-06\"}";
  String jsonInputString = jd.toString();
            
  OutputStream os = con.getOutputStream();
    byte[] input = jsonInputString.getBytes("utf-8");
    os.write(input, 0, input.length);
    
    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
   
    StringBuilder response = new StringBuilder();
    String responseLine = null;
    while ((responseLine = br.readLine()) != null) {
        response.append(responseLine.trim());
    }
    System.out.println(response.toString());
    jo.put("majibu",response);
//
//int responseCode = con.getResponseCode();
//System.out.println("Response code"+responseCode);
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(pushDataToDHIS2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(pushDataToDHIS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jo;
}


private final class BasicAuthenticator extends Authenticator {
@Override
protected PasswordAuthentication getPasswordAuthentication() {
    dhisconfig dc = new dhisconfig();
return new PasswordAuthentication(dc.getDhis2_username(), dc.getDhis2_Password().toCharArray());
}
}

}




