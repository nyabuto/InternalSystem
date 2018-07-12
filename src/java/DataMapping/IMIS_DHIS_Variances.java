/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMapping;

import database.dbConn;
import java.sql.SQLException;

/**
 *
 * @author GNyabuto
 */
public class IMIS_DHIS_Variances {
    
        dbConn conn = new dbConn();
    public void get_data(String yearmonth) throws SQLException{
    int has_new_anc=0;
    
    int upload_no = get_record_count(yearmonth);
    
String imis,dhis,query="SELECT SubPartnerNom AS facility,county.County AS county, DistrictNom AS sub_county,subpartnera.ward AS ward,CentreSanteID AS mfl_code,dhis_data.Annee AS year,dhis_data.Mois AS month,moh731.yearmonth AS yearmonth, ";
    String get_active_elements = "SELECT imis_element, dhis_element FROM imis_dhis_mapping WHERE active=1";
    conn.rs = conn.st.executeQuery(get_active_elements);
    while(conn.rs.next()){
      imis = conn.rs.getString(1);
      dhis = conn.rs.getString(2);
      
      if(!imis.contains(".")){
      query += " IF(IFNULL((moh731."+imis+"-dhis_data."+dhis+"),0)!=0,1,0) AS "+dhis+", ";   
      }
      else{
      query += " IF(IFNULL(("+imis+"-dhis_data."+dhis+"),0)!=0,1,0) AS "+dhis+", "; 
        has_new_anc=1;      
      }
     }
    
    query += "'Round-"+upload_no+"' AS upload_no ";
    
    query+=" FROM moh731 LEFT JOIN dhis_data ON moh731.id=dhis_data.id ";
    if(has_new_anc>0){
    query+= " LEFT JOIN new_anc ON new_anc.id=dhis_data.id ";
    }
    
    query+=" LEFT JOIN subpartnera  on moh731.SubPartnerID=subpartnera.SubPartnerID  "
         + " LEFT JOIN district ON subpartnera.DistrictID=district.DistrictID "
         + " LEFT JOIN county ON county.CountyID=district.CountyID ";

    query+=" WHERE dhis_data.yearmonth='"+yearmonth+"'";
    System.out.println("final query : "+query);
    
    String inserter = "INSERT INTO dhis_731_variances (facility,county,sub_county,ward,mfl_code,year,month,yearmonth,hv0101,hv0102,hv0103,hv0105,hv0106,hv0107,hv0108,hv0109,hv0110,hv0111,hv0112,hv0113,hv0114,hv0115,hv0201,hv0202,hv0203,hv0204,hv0205,hv0206,hv0207,hv0208,hv0209,hv0211,hv0212,hv0213,hv0214,hv0215,hv0216,hv0217,hv0218,hv0219,hv0220,hv0224,hv0225,hv0226,hv0227,hv0228,hv0229,hv0230,hv0231,hv0232,hv0233,hv0234,hv0235,hv0236,hv0237,hv0238,hv0239,hv0240,hv0241,hv0242,hv0243,hv0244,hv0301,hv0302,hv0307,hv0313,hv0319,hv0320,hv0321,hv0322,hv0323,hv0324,hv0325,hv0326,hv0327,hv0333,hv0334,hv0335,hv0336,hv0337,hv0338,hv0339,hv0344,hv0345,hv0346,hv0347,hv0348,hv0349,hv0354,hv0355,hv0370,hv0371,hv0372,hv0373,hv0904,hv0905,new_anc,upload_no) "+query;
    int records = conn.st.executeUpdate(inserter);
    System.out.println("Records added : "+records);
    
}

    
    public int get_record_count(String yearmonth) throws SQLException{
        int records = 0;
        String getyearM = "SELECT COUNT(DISTINCT(upload_no)) from dhis_731_variances WHERE yearmonth='"+yearmonth+"'";
        conn.rs = conn.st.executeQuery(getyearM);
        if(conn.rs.next()){
           records = conn.rs.getInt(1);
        }
        records++;
        
        return records;
    }



private static String removeLastChars(String str, int num) {
    return str.substring(0, str.length() - num);
}
}
