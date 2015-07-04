/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import com.mysql.jdbc.CallableStatement;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
 

/**
 *
 * @author Geofrey Nyabuto
 */
public final class dbConn{
private static Connection connection;
    public ResultSet rs0,rs, rs1, rs2, rs3, rs4, rs_1, rs_2, rs_3, rs_4, rs_5, rs_6, anc_sch_rs;
    public Statement st0,st, st1, st2, st3, st4, st_1, st_2, st_3, st_4, st_5, st_6, anc_scheduling_st;
  public  PreparedStatement pst,pst1,pst2,pst3,pst4,pst5;
  public  PreparedStatement prest,prest1,prest2,prest3,prest4,prest5;
  public  CallableStatement csmt,csmt1,csmt2,csmt3,csmt4;
    String mydrive = "";
    public static int issetdbcalled_file_exists = 2;
    public static int issetdbcalled_exception = 2;
    public static int issetdbcalled_wrongpword = 2;
   public  String dbsetup[] = new String[4];
public  Connection conn = null;

    public dbConn() {
        
        
        
         
        
        
        
        
        
        try {

            
             
             PoolProperties p= new PoolProperties();         
       
       // p.setDriverClassName("com.mysql.jdbc.Driver");
           
            
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhc","root", "");
           





            //if the saved host name is less than 2 letters long, then thats not a genuine host name

            URL location = dbConn.class.getProtectionDomain().getCodeSource().getLocation();


            mydrive = location.getFile().substring(1, 2);

            if (getdbsettings(mydrive) == true) {

                //String myfile=getServletContext().getRealPath("/dbsettings.txt");

                if (dbsetup[0] != null) {

                    if(dbsetup[3]==null){
               // DriverManager.getConnection("jdbc:mysql://" + dbsetup[0] + "/" + dbsetup[1], dbsetup[2],"");
                        p.setUrl("jdbc:mysql://" + dbsetup[0] + "/" + dbsetup[1]);
                        p.setDriverClassName("com.mysql.jdbc.Driver"); 
                        p.setUsername(dbsetup[2]);
                        p.setPassword("");
                    }
                    else{
                   // conn = DriverManager.getConnection("jdbc:mysql://" + dbsetup[0] + "/" + dbsetup[1], dbsetup[2],dbsetup[3]);
                        p.setUrl("jdbc:mysql://" + dbsetup[0] + "/" + dbsetup[1]);
                       
                       p.setDriverClassName("com.mysql.jdbc.Driver");
                        p.setUsername(dbsetup[2]);
                        p.setPassword(dbsetup[3]);
                    }


                } else {
                    //call the page thats sets up the database
                    //use if to avoid calling the db.jsp twice.
                    if (issetdbcalled_wrongpword %2== 0) {
                 
                        issetdbcalled_wrongpword ++;
                    }
                    else{
                     issetdbcalled_wrongpword ++;
                    }

                }


                //initialize this three values
                issetdbcalled_exception = 2;
                issetdbcalled_file_exists = 2;
                issetdbcalled_wrongpword = 2;

                
                
                
          p.setJmxEnabled(true);
          p.setTestWhileIdle(false);
          p.setTestOnBorrow(true);
          p.setValidationQuery("SELECT 1");
          p.setTestOnReturn(false);
          p.setValidationInterval(30000);
          p.setTimeBetweenEvictionRunsMillis(30000);
          p.setMaxActive(2000);
          
          p.setInitialSize(10);
          p.setMaxWait(10000);
          p.setRemoveAbandonedTimeout(60);
          p.setMinEvictableIdleTimeMillis(30000);
          p.setMinIdle(10);
          p.setLogAbandoned(true);
          p.setRemoveAbandoned(true);
          p.setJdbcInterceptors(
            "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
            "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
          DataSource datasource  = new DataSource();
          datasource.setPoolProperties(p);
                
                conn = datasource.getConnection();
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
                anc_scheduling_st = conn.createStatement();
            
             
             
              


            }


        } catch (Exception ex) {
            Logger.getLogger(dbConn.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR WHILE CONNECTING TO DATABASE. CHECK YOUR CONNECTION CREDENTIALS SETTINGS in dbConn.java");
            //error in dbase configuration 
            //call the jsp page that does configuration

            if (issetdbcalled_exception%2 == 0) {
            
                issetdbcalled_exception ++;
            }
            else{
            issetdbcalled_exception ++;
            }

        }
        
        finally {
        
       
        }
    }

    public final boolean getdbsettings(String drive) {
        boolean worked = true;

        try {



            String dbconnpath = drive + ":/APHIAPLUS/InternalSystem/DO_NOT_DELETE/_/_/./dbconnection.txt";

            //File file = new File("");
            // InputStream inStream = getClass().getResourceAsStream("Web-INF/classes/dbconnection.txt");  
            FileInputStream fstream = new FileInputStream(dbconnpath);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String stLine;
            //Read File Line By Line
            int count = 0;
            while ((stLine = br.readLine()) != null) {

                // Print the content on the console


                dbsetup[count] = stLine;


                if (count < 4) {
                    count++;
                }
            }
            //Close the input stream
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(dbConn.class.getName()).log(Level.SEVERE, null, ex);


            System.out.println("MY VALUE:" + issetdbcalled_file_exists);

            if (issetdbcalled_file_exists%2 == 0) {
            
                issetdbcalled_file_exists++;
            }
            else{
            issetdbcalled_file_exists++;
            }

            System.out.println("MY VALUE:" + issetdbcalled_file_exists);


            System.out.println("ERROR:      FILE NOT FOUND");
            worked = false;

        }

        return worked;

    }
    
    public  void attemptClose(ResultSet o)
    {
	try
	    { if (o != null) o.close();}
	catch (Exception e)
	    { e.printStackTrace();}
    }

    public  void attemptClose(Statement o)
    {
	try
	    { if (o != null) o.close();}
	catch (Exception e)
	    { e.printStackTrace();}
    }

    public  void attemptClose(Connection o)
    {
	try
	    { if (o != null) o.close();}
	catch (Exception e)
	    { e.printStackTrace();}
    }

    
    
}
