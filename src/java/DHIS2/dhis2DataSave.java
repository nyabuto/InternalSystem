/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHIS2;

import database.dbConn;
import form1a.ValidateExcelSL;
import form1a.uploadf1a;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class dhis2DataSave extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
            dbConn conn = new dbConn();            
       
           
            if(request.getParameter("elem")!=null)
                
            {
               
                if(request.getParameter("elem").equals("dataElements"))
                
            {
                String id=request.getParameter("id");
                String dn=request.getParameter("displayName");
                
                out.println("Save Element :"+dn+" :: "+saveDataElements(conn, id, dn));
            
            }
                
                  else if(request.getParameter("elem").equals("dataSets"))
                
            {
                String id=request.getParameter("id");
                String dn=request.getParameter("displayName");
                
                out.println("Save Dataset :"+dn+" :: "+saveDataSet(conn, id, dn));
            
            }
                  else if(request.getParameter("elem").equals("organisationUnitGroups"))
                
            {
                String id=request.getParameter("id");
                String dn=request.getParameter("displayName");
                
                out.println("Save Dataset :"+dn+" :: "+saveOrganisationUnitGroups(conn, id, dn));
            
            }
                
                
                  else if(request.getParameter("elem").equals("organisationUnits"))
                
            {
                String id=request.getParameter("id");
                String level=request.getParameter("level");
                String name=request.getParameter("name");
                String parent=request.getParameter("parent[id]");
                String path=request.getParameter("path");
                String shortName=request.getParameter("shortName");
                
            
                
                
                out.println("Saving Organization Unit :"+name+" :: "+saveOrganisationUnits(conn, id, level, name,parent,path,shortName));
            
            }
                
                      else if(request.getParameter("elem").equals("categoryOptionCombos"))
                
            {
                String id=request.getParameter("id");
                String dn=request.getParameter("displayName");
                
                out.println("Save Dataset :"+dn+" :: "+saveCategoryOptionCombination(conn, id, dn));
            
            }
                
                          else if(request.getParameter("elem").equals("Form1a"))
                
            {
                
                /**
                 * attributeOptionCombo: "HllvX50cXC0"
categoryOptionCombo: "RvU0yLMn22B"
created: "2020-09-29T06:16:19.381+0000"
dataElement: "YU9cMzNN5Z4"
followup: false
lastUpdated: "2020-09-29T06:16:19.381+0000"
orgUnit: "jaCs4CR5ZDN"
period: "202008"
storedBy: "ekaunda"
value: "1"
* HllvX50cXC0_RvU0yLMn22B_YU9cMzNN5Z4_jaCs4CR5ZDN_202008
                 */
                
                String attributeOptionCombo=request.getParameter("attributeOptionCombo");
                String categoryOptionCombo=request.getParameter("categoryOptionCombo");
                String dataElement=request.getParameter("dataElement");
                String followup=request.getParameter("followup");
                String lastUpdated=request.getParameter("lastUpdated");
                String orgUnit=request.getParameter("orgUnit");
                String period=request.getParameter("period");
                String storedBy=request.getParameter("storedBy");
                String value=request.getParameter("value");
                String datasetid=request.getParameter("datasetid");
                
                
                //id is period+dataElement+categoryOptionCombo+attributeOptionCombo+orgUnit
                String id=period+"_"+dataElement+"_"+categoryOptionCombo+"_"+attributeOptionCombo+"_"+orgUnit+"_"+datasetid;
                
                out.println("Save Form1a :"+id+" :: "+saveDataValue(conn, id, attributeOptionCombo,categoryOptionCombo,dataElement,followup,lastUpdated,orgUnit,period,storedBy,value,datasetid));
            
            }
                
                
                
                              else if(request.getParameter("elem").equals("DailyArt"))
                
            {
                
                /**
                 * attributeOptionCombo: "HllvX50cXC0"
categoryOptionCombo: "RvU0yLMn22B"
created: "2020-09-29T06:16:19.381+0000"
dataElement: "YU9cMzNN5Z4"
followup: false
lastUpdated: "2020-09-29T06:16:19.381+0000"
orgUnit: "jaCs4CR5ZDN"
period: "202008"
storedBy: "ekaunda"
value: "1"
* HllvX50cXC0_RvU0yLMn22B_YU9cMzNN5Z4_jaCs4CR5ZDN_202008
                 */
                
                String attributeOptionCombo=request.getParameter("attributeOptionCombo");
                String categoryOptionCombo=request.getParameter("categoryOptionCombo");
                String dataElement=request.getParameter("dataElement");
                String followup=request.getParameter("followup");
                String lastUpdated=request.getParameter("lastUpdated");
                String orgUnit=request.getParameter("orgUnit");
                String period=request.getParameter("period");
                String storedBy=request.getParameter("storedBy");
                String value=request.getParameter("value");
                String datasetid=request.getParameter("datasetid");
                
                
                //id is period+dataElement+categoryOptionCombo+attributeOptionCombo+orgUnit
                String id=period+"_"+dataElement+"_"+categoryOptionCombo+"_"+attributeOptionCombo+"_"+orgUnit+"_"+datasetid;
                
                out.println("Save Daily ART :"+id+" :: "+saveDataValue(conn, id, attributeOptionCombo,categoryOptionCombo,dataElement,followup,lastUpdated,orgUnit,period,storedBy,value,datasetid));
            
            }
                              else {
                              
                              refreshImisDatavalue_SP(conn, request);
                              }
                
                
            }
     
            
            try {
                
                
                if(conn.rs!=null){ conn.rs.close(); }
                 if(conn.rs1!=null){ conn.rs1.close(); }
                 if(conn.rs2!=null){ conn.rs2.close(); }
                 if(conn.rs3!=null){ conn.rs3.close(); }
                 if(conn.rs_6!=null){ conn.rs_6.close(); }
               if(conn.st!=null){ conn.st.close(); }
               if(conn.pst!=null){ conn.pst.close(); }
                 if(conn.st1!=null){ conn.st1.close(); }
                 if(conn.st2!=null){ conn.st2.close(); }
                 if(conn.st3!=null){ conn.st3.close(); }
                  if(conn.conn!=null){ conn.conn.close(); }
                
            } catch (SQLException ex) {
                Logger.getLogger(dhis2DataSave.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(dhis2DataSave.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(dhis2DataSave.class.getName()).log(Level.SEVERE, null, ex);
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

    
    
    
    //pullCategoryOptionCombination
          //pullDataElements
          //pullDataSet
          //pullOrgUnitGroups
          //pullOrgUnits
          //pullDailyArt
          //pullForm1a
    
    public String saveDataElements(dbConn conn ,String Id ,String DisplayName) 
    {
         String status="Completed";
        try {
           
            String ins = "Replace INTO dhis2_dataelement (dataelementid,dataelementname) VALUES (?,?)";
            conn.pst = conn.conn.prepareStatement(ins);
            
            conn.pst.setString(1, Id);
            
            conn.pst.setString(2, DisplayName);
            
            conn.pst.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(dhis2DataSave.class.getName()).log(Level.SEVERE, null, ex);
              status="Not Completed "+ex;
        }
       return status; 
    }
    
    public String saveDataSet(dbConn conn ,String Id ,String DisplayName) 
    {
         String status="Completed";
        try {
           
            String ins = "Replace INTO dhis2_dataset (datasetid,datasetname) VALUES (?,?)";
            conn.pst = conn.conn.prepareStatement(ins);
            
            conn.pst.setString(1, Id);
            
            conn.pst.setString(2, DisplayName);
            
            conn.pst.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(dhis2DataSave.class.getName()).log(Level.SEVERE, null, ex);
              status="Not Completed "+ex;
        }
       return status; 
    }
    
    
    public String saveOrganisationUnitGroups(dbConn conn ,String Id ,String DisplayName) 
    {
         String status="Completed";
        try {
           
            String ins = "Replace INTO dhis2_organizationunitgroup (orgunitgroupid,orgunitgroupname) VALUES (?,?)";
            conn.pst = conn.conn.prepareStatement(ins);
            
            conn.pst.setString(1, Id);
            
            conn.pst.setString(2, DisplayName);
            
            conn.pst.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(dhis2DataSave.class.getName()).log(Level.SEVERE, null, ex);
              status="Not Completed "+ex;
        }
       return status; 
    }
    
    public String saveOrganisationUnits(dbConn conn ,String Id ,String level,String name, String parent,String path,String shortName) 
    {
         String status="Completed";
        try {
           
            String ins = "Replace INTO dhis2_organizationunit (orgunitid,parentid,orgunitname,oulevel,oupath) VALUES (?,?,?,?,?)";
            conn.pst = conn.conn.prepareStatement(ins);
            
//            orgunitid varchar(12) PK 
//parentid varchar(12) 
//orgunitname varchar(200) 
//oulevel decimal(2,0) 
//lastmodifieddate timestamp 
//oupath
            
            conn.pst.setString(1, Id);
            
            conn.pst.setString(2, parent);
            conn.pst.setString(3, name);
            conn.pst.setString(4, level);
            conn.pst.setString(5, path);
            //conn.pst.setString(6, name);
            
            conn.pst.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(dhis2DataSave.class.getName()).log(Level.SEVERE, null, ex);
              status="Not Completed "+ex;
        }
       return status; 
    }
    
    public String saveCategoryOptionCombination(dbConn conn ,String Id ,String DisplayName) 
    {
         String status="Completed";
        try {
           
            String ins = "Replace INTO dhis2_categoryoptioncombo (categoryoptioncomboid,categoryoptioncomboname) VALUES (?,?)";
            conn.pst = conn.conn.prepareStatement(ins);
            
            conn.pst.setString(1, Id);
            
            conn.pst.setString(2, DisplayName);
            
            conn.pst.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(dhis2DataSave.class.getName()).log(Level.SEVERE, null, ex);
              status="Not Completed "+ex;
        }
       return status; 
    }
    
    
     public String saveDataValue(dbConn conn ,String Id ,String attributeOptionCombo,String categoryOptionCombo,String dataElement,String followup,String lastUpdated,String orgUnit,String period,String storedBy,String value,String datasetid) 
    {
         String status="Completed";
        try {
           
            String ins = "Replace INTO dhis2_datavalue (recordid,datasetid,dataelementid,orgunitid,categoryoptioncombo,dvalue,lastmodifieddate,period,lastupdated,attributeoptioncombo) VALUES (?,?,?,?,?,?,?,?,?,?)";
            conn.pst = conn.conn.prepareStatement(ins);
            
            conn.pst.setString(1, Id);            
            conn.pst.setString(2, datasetid);
            conn.pst.setString(3,dataElement );
            conn.pst.setString(4, orgUnit);
            conn.pst.setString(5, categoryOptionCombo);
            conn.pst.setString(6, value);
            conn.pst.setString(7, lastUpdated);
            conn.pst.setString(8, period);
            conn.pst.setString(9, lastUpdated);
            conn.pst.setString(10, attributeOptionCombo);
            
            System.out.println("insert status"+conn.pst+" Status: "+conn.pst.executeUpdate());
            
            
          
            
            
        } catch (SQLException ex) {
            Logger.getLogger(dhis2DataSave.class.getName()).log(Level.SEVERE, null, ex);
              status="Not Completed "+ex;
        }
       return status; 
    }
     
     
     
     
     
     
      
     public List createAfyaNyotaDataStructureFromDHIS2Datavalue( dbConn conn) throws IOException  
  {
      List idvList=new ArrayList();
      try
      {
          String period=null;
          String dataElement=null;
          String orgUnitId=null;
          List distinctPeriodList= new ArrayList();
          
          String distinctperiods="select distinct(period) from dhis2_datavalue;";
          
           conn.rs=conn.st.executeQuery(distinctperiods);
           while(conn.rs.next())
           {
           distinctPeriodList.add(conn.rs.getString(1));
           
           }
          
          List distinctDataElementList= new ArrayList();
          List distinctOrgunitList= new ArrayList();
          List dataValueList= new ArrayList();
          Datavalue dv=null;
          ImisDatavalue idv=null;
          DxOrganizationUnitMatch oum=null;
          if(distinctPeriodList !=null)
          {
              //System.err.println("distinctPeriodList is not null");
              for(int i=0; i<distinctPeriodList.size(); i++)
              {
                  if(distinctPeriodList.get(i) !=null)
                  {
                      
                    period=distinctPeriodList.get(i).toString();
                    //distinctDataElementList=DaoUtility.getDatavalueDaoInstance().getDistinctDataElementsByPeriodsFromDataValues(period);
                                        
                    String distinctelements="select distinct(dataelementid) from dhis2_datavalue where period='"+period+"'";
          
           conn.rs=conn.st.executeQuery(distinctelements);
           while(conn.rs.next())
           {
           distinctDataElementList.add(conn.rs.getString(1));
           
           }
                    
                    if(distinctDataElementList !=null)
                    {
                        //System.err.println("distinctDataElementList is not null");
                        for(int j=0; j<distinctDataElementList.size(); j++)
        {
             dataElement=distinctDataElementList.get(j).toString();
             
            // distinctOrgunitList=DaoUtility.getDatavalueDaoInstance().getDistinctOrganizationUnitsByDataElementAndPeriodFromDataValues(dataElement, period);
                            
            String distincteorgs="select distinct(orgunitid) from dhis2_datavalue where period='"+period+"' and dataelementid='"+dataElement+"' ";
          
               
           conn.rs=conn.st.executeQuery(distincteorgs);
           while(conn.rs.next())
           {
               
           distinctOrgunitList.add(conn.rs.getString(1));
           
           }
                            
                            
                            if(distinctOrgunitList !=null)
                            {
                                //System.err.println("distinctOrgunitList is not null");
                                for(int k=0; k<distinctOrgunitList.size(); k++)
                                {
                                    orgUnitId=distinctOrgunitList.get(k).toString();
                                   // oum=getOrganizationUnitMappingByConsumerOrgUnitIdAndProducerInstanceId(orgUnitId,imisInstanceId);
                                  //  dataValueList=DaoUtility.getDatavalueDaoInstance().getDatavaluesByOrganizationUnitsDataElementAndPeriod(orgUnitId, dataElement, period);
                    
                            String oum_pid="";      
                  String poid_qry="select producerorgunitid from dhis2_organizationunitmatch where consumerorgunitid ='"+orgUnitId+"'";
                conn.rs=conn.st.executeQuery(poid_qry);
           while(conn.rs.next())
           {
               
          oum_pid=conn.rs.getString(1);
           
           }                  
                                  
           String dataval="select dvalue from dhis2_datavalue where period='"+period+"' and dataelementid='"+dataElement+"' and orgunitid='"+orgUnitId+"'";
          
           conn.rs=conn.st.executeQuery(dataval);
           while(conn.rs.next())
           {
               
           dataValueList.add(conn.rs.getString(1));
           
           }
                                    
                                    
                                    if(dataValueList !=null)
                                    {
                                        //System.err.println("dataValueList is not null");
                                        idv=null;
                                        for(int l=0; l<dataValueList.size(); l++)
                                        {
                                            dv=(Datavalue)dataValueList.get(l);
                                           // BusinessRule dbr=DaoUtility.getBusinessRuleDaoInstance().getBusinessRuleByConsumerDataElementIdAndConsumerCatComboIdAndConsumerInstanceId(dataElement,dv.getCategoryOptionComboId(),dv.getDhisId());
                                           
                  String getbr_query="select producerdeid,producercatcomboname from dhis2_businessrule where producerdeid='"+dataElement+"' and producercatcomboid='"+dv.getCategoryOptionComboId()+"' ";
                                            
                         conn.rs=conn.st.executeQuery(getbr_query);
                         
                                            //if(dbr !=null)
                                            //{
                                                //System.err.println("dbr is not null");
                                                idv=new ImisDatavalue();
                                                idv.setDhisDataElementId(dv.getDataElementId());
                                                idv.setDhisFacilityId(dv.getOrgUnitId());
                                                if(oum_pid !=null)
                                                idv.setImisFacilityId(oum_pid);
                                                
                                                idv.setYearMonth(period);
                                                
                                                while(conn.rs.next())
                                                {
                                                //if(dbr.getProducerCatComboName() !=null)
                                                //{
                                                String catcomboname=conn.rs.getString("producercatcomboname");
                                                String prod_deid=conn.rs.getString("producerdeid");
                                                
                                                    //System.err.println("dbr.getProducerCatComboName() is not null");
                                                    idv.setImisDataElementId(prod_deid);
                                                    if(catcomboname.equals("f_1"))
                                                    idv.setF_1(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("f_4"))
                                                    idv.setF_4(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("f_9"))
                                                    idv.setF_9(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("f_14"))
                                                    idv.setF_14(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("f_19"))
                                                    idv.setF_19(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("f_24"))
                                                    idv.setF_24(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("f_29"))
                                                    idv.setF_29(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("f_34"))
                                                    idv.setF_34(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("f_39"))
                                                    idv.setF_39(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("f_44"))
                                                    idv.setF_44(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("f_49"))
                                                    idv.setF_49(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("f_50"))
                                                    idv.setF_50(Integer.parseInt(dv.getDvalue()));
                                                    
                                                    //Do same for male
                                                    else if(catcomboname.equals("m_1"))
                                                    idv.setM_1(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("m_4"))
                                                    idv.setM_4(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("m_9"))
                                                    idv.setM_9(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("m_14"))
                                                    idv.setM_14(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("m_19"))
                                                    idv.setM_19(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("m_24"))
                                                    idv.setM_24(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("m_29"))
                                                    idv.setM_29(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("m_34"))
                                                    idv.setM_34(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("m_39"))
                                                    idv.setM_39(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("m_44"))
                                                    idv.setM_44(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("m_49"))
                                                    idv.setM_49(Integer.parseInt(dv.getDvalue()));
                                                    else if(catcomboname.equals("m_50"))
                                                    idv.setM_50(Integer.parseInt(dv.getDvalue()));
                                                    System.err.println("dbr.getProducerCatComboName() is "+catcomboname);
                                                //}
                                           // }
                                           
                                           
                         }
                                        }
                                        if(idv !=null)
                                        {
                                            idv.setRecordId(idv.getYearMonth()+"_"+idv.getImisFacilityId()+"_"+idv.getImisDataElementId());
                                            idvList.add(idv);
                                           saveOrUpdateImisDatavalue(conn,idv);
                                        }
                                    }
                                }
                            }
                        }
                    }
                  }
              }
          }
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }
      return idvList;
  }
     
    public String refreshImisDatavalue_SP(dbConn conn, HttpServletRequest request) throws SQLException{
     
        String status="Completed";
        
        
        
        //refresh staging imisdata value table
        String qry="call internal_system.dhis2_refresh_imisdatavalue();";
        
        conn.st.executeUpdate(qry);
        //refresh the fas table
        qry="call internal_system.dhis2_refreshFasTemp();";
        
        conn.st.executeUpdate(qry);
        
        
        
        String getuniquedetails="select group_concat(distinct(facility_id)) as facilid, group_concat(distinct(yearmonth)) as yms from fas_temp ";
        
        
        String yms="",unique_subpartner="",unique_ym="";
        
         conn.rs=conn.st.executeQuery(getuniquedetails);  
         
          if (conn.rs.next())
        {
            
            
        unique_subpartner=conn.rs.getString("facilid");
        unique_ym=conn.rs.getString("yms");
        }
         
        
        String getuniqueyms="select distinct(concat(yearmonth,'_',facility_id)) as ym_facilid from fas_temp ";
      
        conn.rs=conn.st.executeQuery(getuniqueyms);  
        
        while (conn.rs.next())
        {
            //call data transfer
            
           JSONObject obj;
            ValidateExcelSL vExcel = new ValidateExcelSL();
            obj = vExcel.validate(unique_subpartner.split(","), unique_ym.split(","),"fas_temp",request);
            
            int error_per_sheet,total_errors = 0,warnings;
            String warning;
            warning = "";
            
            
            if(obj.containsKey("output")){
                
              JSONArray jarray = (JSONArray)obj.get("output");
                
              
              for(int i=0;i<jarray.size();i++)
              {
               error_per_sheet =  vExcel.error_per_sheet((JSONObject)jarray.get(i));
               if(error_per_sheet==0){ // send this data to main tables facilityid, yearmonth
//transfer
      String facilid=((JSONObject)jarray.get(i)).get("facility_id").toString();
      String yearm=((JSONObject)jarray.get(i)).get("yearmonth").toString();
      
     
      
      if(i==jarray.size()-1)
      {
      //last row
     
      //System.out.println("to transfer ni: "+totransferymf);
       
       transferdata( conn, conn.rs.getString(1));
      }
//      else 
//      {
//      totransferymf+="'"+yearm+"_"+facilid+"',";
//      }
       String tx = "Sucess: Uploaded excel for Facility "+getFacilityname(conn,facilid)+" " + yearm.substring(0, 4) + " , " + yearm.substring(4) + " \n ";
                   System.out.println(""+tx);
                
              
                 
                                     }
               else
               {// increment errors
                total_errors+=error_per_sheet;
                
                 String yearm=((JSONObject)jarray.get(i)).get("yearmonth").toString();
                 String facilid=((JSONObject)jarray.get(i)).get("facility_id").toString();
                 
                  String tx = "Failed: Failed Validation for Facility "+getFacilityname(conn,facilid)+" " + yearm.substring(0, 4) + " , " + yearm.substring(4) + " \n ";
                                  System.out.println(""+tx);
                
                   
               }
              }
          }  
            
            
            
       
        
        }
        return status;
        
    }
    
    public void saveOrUpdateImisDatavalue(dbConn conn, ImisDatavalue idv) throws SQLException{
    
    
    
    String savedata="replace into (recordid,imisfacilityid,dhisfacilityid,imisdataelementid,dhisdataelementid,yearmonth,m_uk,f_uk,m_1,f_1,m_4,f_4,m_9,f_9,m_14,f_14,m_19,f_19,m_24,f_24,m_29,f_29,m_34,f_34,m_39,f_39,m_44,f_44,m_49,f_49,m_50,f_50,m_total,f_total,gtotal) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
    
     conn.pst = conn.conn.prepareStatement(savedata);
            
/**recordid,*/ conn.pst.setString(1, idv.getRecordId()); 
/**imisfacilityid,*/ conn.pst.setString(2, idv.getImisFacilityId()); 
/**dhisfacilityid,*/ conn.pst.setString(3, idv.getDhisFacilityId()); 
/**imisdataelementid,*/ conn.pst.setString(4, idv.getImisDataElementId()); 
/**dhisdataelementid,*/ conn.pst.setString(5, idv.getDhisDataElementId()); 
/**yearmonth,*/ conn.pst.setString(6, idv.getYearMonth()); 
/**m_uk,*/ conn.pst.setInt(7, idv.getM_uk()); 
/**f_uk,*/ conn.pst.setInt(8, idv.getF_uk()); 
/**m_1,*/ conn.pst.setInt(9, idv.getM_1()); 
/**f_1,*/ conn.pst.setInt(10, idv.getF_1()); 
/**m_4,*/ conn.pst.setInt(11, idv.getM_4()); 
/**f_4,*/ conn.pst.setInt(12, idv.getF_4()); 
/**m_9,*/ conn.pst.setInt(13, idv.getM_9()); 
/**f_9,*/ conn.pst.setInt(14, idv.getF_9()); 
/**m_14,*/ conn.pst.setInt(15, idv.getM_14()); 
/**f_14,*/ conn.pst.setInt(16, idv.getF_14()); 
/**m_19,*/ conn.pst.setInt(17, idv.getM_19()); 
/**f_19,*/ conn.pst.setInt(18, idv.getF_19()); 
/**m_24,*/ conn.pst.setInt(19, idv.getM_24()); 
/**f_24,*/ conn.pst.setInt(20, idv.getF_24()); 
/**m_29,*/ conn.pst.setInt(21, idv.getM_29()); 
/**f_29,*/ conn.pst.setInt(22, idv.getF_29()); 
/**m_34,*/ conn.pst.setInt(23, idv.getM_34()); 
/**f_34,*/ conn.pst.setInt(24, idv.getF_34()); 
/**m_39,*/ conn.pst.setInt(25, idv.getM_39()); 
/**f_39,*/ conn.pst.setInt(26, idv.getF_39()); 
/**m_44,*/ conn.pst.setInt(27, idv.getM_44()); 
/**f_44,*/ conn.pst.setInt(28, idv.getF_44()); 
/**m_49,*/ conn.pst.setInt(29, idv.getM_49()); 
/**f_49,*/ conn.pst.setInt(30, idv.getF_49()); 
/**m_50,*/ conn.pst.setInt(31, idv.getM_50()); 
/**f_50,*/ conn.pst.setInt(32, idv.getF_50()); 
/**m_total,*/ conn.pst.setInt(33, idv.getM_total()); 
/**f_total,*/ conn.pst.setInt(34, idv.getF_total()); 
/**gtotal*/ conn.pst.setInt(35, idv.getGtotal()); 

            
            conn.pst.executeUpdate();
      
    
    }
     
     
     
 
    
    public boolean transferdata(dbConn conn, String yearmonth_subpartnerid) 
    {
boolean retvalue=true;
        try {
            //sample yearmonth_subpartnerid   '201901_226','201902_226','201903_226'
            
            String qry = "select * from fas_temp where concat(yearmonth,'_',facility_id) in (" + yearmonth_subpartnerid + ") group by destination_table";
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

String deleteqry=" delete from "+destinationtable+" where concat(yearmonth,'_',facility_id) in (" + yearmonth_subpartnerid + ")";

               // System.out.println(" To delete column: "+deleteqry);

                conn.st_1.executeUpdate(deleteqry);
                
 String skipblanks=" and concat_ws(',',m_uk,f_uk,m_1,f_1,m_4,f_4,m_9,f_9,m_14,f_14,m_19,f_19,m_24,f_24,m_29,f_29,m_34,f_34,m_39,f_39,m_44,f_44,m_49,f_49,m_50,f_50,total) !='0' && concat_ws(',',m_uk,f_uk,m_1,f_1,m_4,f_4,m_9,f_9,m_14,f_14,m_19,f_19,m_24,f_24,m_29,f_29,m_34,f_34,m_39,f_39,m_44,f_44,m_49,f_49,m_50,f_50,total) !='' ";    

 replaceqry = "Replace  " + destinationtable + " select " + colstomigrate + " from fas_temp where destination_table='" + destinationtable + "' and concat(yearmonth,'_',facility_id) in (" + yearmonth_subpartnerid + ")  "+skipblanks+" ";

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
   
    
    
    
    public String getFacilityname(dbConn conn, String facilityid) throws SQLException{
     String facility="unkown";
     
     conn.rs_6=conn.st_6.executeQuery("select subpartnernom from subpartnera where subpartnerid='"+facilityid+"'");
     
     while(conn.rs_6.next())
     {
     facility=conn.rs_6.getString(1);
     }
     
     return facility;
     
     }
    
    
}
