/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataCleaning;

import database.dbConn;
import java.sql.SQLException;

/**
 *
 * @author GNyabuto
 */
public class WhoCleaned {
  dbConn conn = new dbConn();
  
  public String save_started_cleaning(String fullname,String UserID,String date_accessed) throws SQLException{
  String add = "INSERT INTO data_cleaning_access (userid,fullname,date_accessed,status) VALUES(?,?,?,?)";
     conn.pst = conn.conn.prepareStatement(add);
     conn.pst.setString(1, UserID);
     conn.pst.setString(2, fullname);
     conn.pst.setString(3, date_accessed);
     conn.pst.setInt(4, 0);
     
     conn.pst.executeUpdate();
     return get_id(UserID);
  }
  
  private String get_id(String UserID) throws SQLException{
     String id="";
     
     String checker = "SELECT MAX(id) FROM data_cleaning_access WHERE userid=?";
     conn.pst = conn.conn.prepareStatement(checker);
     conn.pst.setString(1, UserID);
     
     conn.rs = conn.pst.executeQuery();
     if(conn.rs.next()){
         id = conn.rs.getString(1);
     }
     return id;  
  }
  
  public void update_status(String id) throws SQLException{
    String update = "UPDATE data_cleaning_access SET status=? WHERE id=?";
     conn.pst = conn.conn.prepareStatement(update);
     conn.pst.setInt(1,1);
     conn.pst.setString(2,id);
     
     conn.pst.executeUpdate();  
  }
}
