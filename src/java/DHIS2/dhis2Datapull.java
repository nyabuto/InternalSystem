/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHIS2;

import database.dbConn;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author EKaunda
 */
public class dhis2Datapull {
    
    
    public List pullCategoryOptionCombination(dbConn conn, dhisconfig dc, UrlResourceManager urm){
   
    String url=dc.dhis2_Home_URL+dc.dhis2_API_URL+dc.dhis2_API_URL_CategoryCombinations;
    String uname=dc.dhis2_username;
    String pwd=dc.dhis2_Password;
    
       
    return urm.getUrlResource(url,uname,pwd);
    }
    
    
     public List pullDataElements(dbConn conn, dhisconfig dc, UrlResourceManager urm)
     {
     String url=dc.dhis2_Home_URL+dc.dhis2_API_URL+dc.dhis2_API_URL_dataElements;
    String uname=dc.dhis2_username;
    String pwd=dc.dhis2_Password;
    
       
    return urm.getUrlResource(url,uname,pwd);
    }
     
      public List pullOrgUnits(dbConn conn , dhisconfig dc, UrlResourceManager urm)
    {
    String url=dc.dhis2_Home_URL+dc.dhis2_API_URL+dc.dhis2_API_URL_orgUnits;
    String uname=dc.dhis2_username;
    String pwd=dc.dhis2_Password;
    
    return urm.getUrlResource(url,uname,pwd);
    }
      
public List pullOrgUnitGroups(dbConn conn, dhisconfig dc, UrlResourceManager urm)
       {
    String url=dc.dhis2_Home_URL+dc.dhis2_API_URL+dc.dhis2_API_URL_OrgunitGroup;
    String uname=dc.dhis2_username;
    String pwd=dc.dhis2_Password;    
    return urm.getUrlResource(url,uname,pwd);
       }
public List pullDataSet(dbConn conn, dhisconfig dc, UrlResourceManager urm)
       {
    String url=dc.dhis2_Home_URL+dc.dhis2_API_URL+dc.dhis2_API_URL_Dataset;
    String uname=dc.dhis2_username;
    String pwd=dc.dhis2_Password;    
    return urm.getUrlResource(url,uname,pwd);
       }
public List pullForm1a(dbConn conn, dhisconfig dc, UrlResourceManager urm)
       {
    String url=dc.dhis2_Home_URL+dc.dhis2_API_URL+dc.dhis2_API_URL_F1a;
    String uname=dc.dhis2_username;
    String pwd=dc.dhis2_Password;    
    return urm.getUrlResource(url,uname,pwd);
       }
public List pullDailyArt(dbConn conn, dhisconfig dc, UrlResourceManager urm)
       {
    String url=dc.dhis2_Home_URL+dc.dhis2_API_URL+dc.dhis2_API_URL_DailyART;
    String uname=dc.dhis2_username;
    String pwd=dc.dhis2_Password;    
    return urm.getUrlResource(url,uname,pwd);
       }
    
}
