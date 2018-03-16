/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoadExcels;

import database.dbConn;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author GNyabuto
 */
public class DHIS_IMIS {
 String year,month; 
 dbConn conn = new dbConn();
    public void get_params(String yr, String mn, Workbook wb){
      year = yr;
      month = mn;
      
      
      
    }
}
