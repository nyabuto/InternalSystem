/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;
import java.sql.*;

public final class dbConnAccess {
    public ResultSet rs0,rs, rs1, rs2, rs3, rs4, rs_1, rs_2, rs_3, rs_4, rs_5, rs_6, anc_sch_rs;
    public Statement st0,st, st1, st2, st3, st4, st_1, st_2, st_3, st_4, st_5, st_6, anc_scheduling_st;
  public  PreparedStatement pst,pst1,pst2,pst3,pst4,pst5;
  public  PreparedStatement prest,prest1,prest2,prest3,prest4,prest5;
  public  com.mysql.jdbc.CallableStatement csmt,csmt1,csmt2,csmt3,csmt4;
  public  Connection conn = null;
  
   public dbConnAccess(){

        try {

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            String accessFileName = "D:/SYSTEM UPDATES/Internal System 25 Nov 2014";

            String connURL="jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+accessFileName+".mdb;";

             conn = DriverManager.getConnection(connURL, "","");

                st0 = conn.createStatement();
                st = conn.createStatement();
                st1 = conn.createStatement();
                st2 = conn.createStatement();
                st3 = conn.createStatement();
                st4 = conn.createStatement();


                st_1 = conn.createStatement();
                st_2 = conn.createStatement();
                st_3 = conn.createStatement();
                st_4 = conn.createStatement();
                st_5 = conn.createStatement();
                st_6 = conn.createStatement();
                
            }
            catch (Exception err) {
                System.out.println("ERROR: " + err);
            }
    }  
}
