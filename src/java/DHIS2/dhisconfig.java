/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHIS2;

/**
 *
 * @author EKaunda
 */



public class dhisconfig {
  
String dhis2_Name =null;
String dhis2_Home_URL =null;

    
String dhis2_username=null; 
String dhis2_Password=null;

String  dhis2_API_URL=null; 

String  dhis2_API_URL_orgUnits=null;  
String  dhis2_API_URL_dataElements=null;  
String  dhis2_API_URL_CategoryCombinations=null;  
String  dhis2_API_URL_Dataset=null;  
String  dhis2_API_URL_OrgunitGroup=null;  
String  dhis2_API_URL_F1a=null;  
String  dhis2_API_URL_DailyART=null;  
String  dhis2_API_URL_UploadF1a=null;  
String  gmail_pass=null;  



String utj_email=null;
String utj_pass=null;

  public dhisconfig()
  {
  
  dhis2_Name ="USAID Tujenge Jamii";
  //dhis2_Home_URL ="https://dhis-afyanyota.fhi360.org/";
  //dhis2_Home_URL ="https://hiskenya.org/";
 dhis2_Home_URL ="https://partnermanagementsystem.uonbi.ac.ke/";
  

  dhis2_username="";
  dhis2_Password="!";
  //dhis2_username="";

  dhis2_username="ekaunda";
  dhis2_Password="123456@Ab";
  gmail_pass="jndhardsfmnmgfmx";
  
  
   utj_pass="cxjkkhzlwcksftrr";
  utj_email="utjdata@usaidtujengejamii.org";
  
  //dhis2_username="emmanuelkaunda";

  
 // dhis2_Password="P@ssw0rd";
  dhis2_API_URL="api/";
  
  dhis2_API_URL_orgUnits="organisationUnits.json?fields=id,name,level,parent,path,shortName&paging=false&links=false";  
  dhis2_API_URL_dataElements="dataElements.json?paging=false&links=false";  
  dhis2_API_URL_CategoryCombinations="categoryOptionCombos.json?paging=false&links=false";  
  dhis2_API_URL_Dataset="dataSets.json?paging=false&links=false";  
  dhis2_API_URL_OrgunitGroup="organisationUnitGroups.json?paging=false&links=false";  
  dhis2_API_URL_F1a="dataValueSets.json?dataSet=kwoxlVdwR8d&orgUnitGroup=lWgRU0sXJ87&links=false&paging=false&startDate=2020-03-01&endDate=2020-03-31";
  //dhis2_API_URL_DailyART="dataValueSets.json?dataSet=LFO4FCdCY4g&orgUnitGroup=lWgRU0sXJ87&links=false&paging=false&startDate=2020-03-01&endDate=2020-03-31";
  dhis2_API_URL_DailyART="dataValueSets.json?dataSet=veM8P1BF9Yq&orgUnit=HfVjCurKxh2&links=false&paging=false&startDate=2020-03-01&endDate=2021-07-31";
  dhis2_API_URL_UploadF1a="dataValueSets";
  
  }  
  
  
  
  
    public String getUtj_email() {
        return utj_email;
    }

    public void setUtj_email(String utj_email) {
        this.utj_email = utj_email;
    }

    public String getUtj_pass() {
        return utj_pass;
    }

    public void setUtj_pass(String utj_pass) {
        this.utj_pass = utj_pass;
    }
  
public String getDhis2_username() {
        return dhis2_username;
    }

    public void setDhis2_username(String dhis2_username) {
        this.dhis2_username = dhis2_username;
    }

    public String getDhis2_Password() {
        return dhis2_Password;
    }

    public void setDhis2_Password(String dhis2_Password) {
        this.dhis2_Password = dhis2_Password;
    }

    public String getGmail_pass() {
        return gmail_pass;
    }

    public void setGmail_pass(String gmail_pass) {
        this.gmail_pass = gmail_pass;
    }
  
}
