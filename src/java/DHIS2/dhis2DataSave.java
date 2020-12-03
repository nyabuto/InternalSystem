/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHIS2;

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
import org.json.simple.JSONObject;

/**
 *
 * @author EKaunda
 */
public class dhis2DataSave extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
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
                
                
            }
     
            
            try {
                
                
               if(conn.conn!=null){ conn.conn.close(); }
               if(conn.pst!=null){ conn.pst.close(); }
                
                
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
            
            conn.pst.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(dhis2DataSave.class.getName()).log(Level.SEVERE, null, ex);
              status="Not Completed "+ex;
        }
       return status; 
    }
    
}
