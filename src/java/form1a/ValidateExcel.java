/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form1a;

import database.dbConn;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author GNyabuto
 */
public class ValidateExcel {
    dbConn conn = new dbConn();
   
    public String validate(String facility_id,String yearmonth) throws SQLException{
        int error_counter=0;
        String errors="";
        String lhs,rhs,sign,query;
        String getRules="SELECT codes,validation,iscritical,message,fine_age FROM fas_validation WHERE active=1 AND source='excel'";
        conn.rs = conn.st.executeQuery(getRules);
        while(conn.rs.next()){
            if(conn.rs.getInt("iscritical")==1){
            //error indicators
             String[] Rules = extract_rule(conn.rs.getString("validation"));  
             lhs = Rules[0];
             rhs = Rules[1];
             sign = Rules[2];
            query = getQuery(conn.rs.getInt("fine_age"));
             
            query = query.replace("lhs", lhs);
            query = query.replace("rhs", rhs);
            query = query.replace("sign", sign);
            System.out.println("final query"+query);
                //run the validation query
                
                conn.rs1 = conn.st1.executeQuery(query);
                ResultSetMetaData metaData = conn.rs1.getMetaData();
                int column_count = metaData.getColumnCount(); //number of column
                if(conn.rs.next()){
                   // check and see the columns that have an issue 
                   int cols=1;
                   while(cols<=column_count) {
                    if(conn.rs.getInt(cols)==1) {
                        //archive this error
                       
                        error_counter++;
                        
                        errors+=error_counter+". "+conn.rs.getString("message")+" for "+metaData.getColumnLabel(cols)+" i.e "+conn.rs.getString("codes")+"<br>";
                         
                    }  
                       
                       cols++;
                   }
                    
                }
                
            }
            else{
               //warning indicators 
                
            }
        }
        
        return errors;
    }
    
    private String[] extract_rule(String rule){
      String sign="";
      if(rule.contains("<=")){sign="<=";}
      else if(rule.contains("<=")){sign="<=";}
      else if(rule.contains(">=")){sign=">=";}
      else if(rule.contains("=")){sign="=";}
      else if(rule.contains("<")){sign="<";}
      else if(rule.contains(">")){sign=">";}
      
      rule = rule.replace(sign, "#")+"#"+sign;
      return rule.split("#");
        
    }
    
    
    private String getQuery(int fine_age){
        String query = "";
       
        switch (fine_age) {
            case 1:// for all age sets
                query="SELECT\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN f_1 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN f_1 end,0))) AS '<1 F',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN m_1 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN m_1 end,0))) AS '<1 M',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN f_4 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN f_4 end,0))) AS '1-4 F',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN m_4 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN m_4 end,0))) AS '1-4 M',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN f_9 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN f_9 end,0))) AS '5-9 F',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN m_9 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN m_9 end,0))) AS '5-9 M',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN f_14 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN f_14 end,0))) AS '10-14 F',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN m_14 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN m_14 end,0))) AS '10-14 M',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN f_19 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN f_19 end,0))) AS '15-19 F',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN m_19 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN m_19 end,0))) AS '15-19 M',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN f_24 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN f_24 end,0))) AS '20-24 F',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN m_24 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN m_24 end,0))) AS '20-24 M',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN f_29 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN f_29 end,0))) AS '25-29 F',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN m_29 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN m_29 end,0))) AS '25-29 M',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN f_34 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN f_34 end,0))) AS '30-34 F',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN m_34 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN m_34 end,0))) AS '30-34 M',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN f_39 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN f_39 end,0))) AS '35-39 F',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN m_39 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN m_39 end,0))) AS '35-39 M',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN f_44 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN f_44 end,0))) AS '40-44 F',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN m_44 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN m_44 end,0))) AS '40-44 M',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN f_49 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN f_49 end,0))) AS '45-49 F',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN m_49 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN m_49 end,0))) AS '45-49 M',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN f_50 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN f_50 end,0))) AS '50+ F',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN m_50 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN m_50 end,0))) AS '50+ M',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN total END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN total end,0))) AS 'Totals'\n" +
                        "FROM fas_temp ";
                break;
            case 0:// for totals only
                query="SELECT\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN total END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN total end,0))) AS 'Totals'\n" +
                        "FROM fas_temp ";
                break;
            case 2:
                //only for under 1's
                query="SELECT\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN f_1 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN f_1 end,0))) AS '<1 F',\n" +
                        "(SUM(IFNULL(CASE WHEN indicator_id IN(lhs) THEN m_1 END,0)) sign SUM(IFNULL(CASE WHEN  indicator_id IN(rhs) THEN m_1 end,0))) AS '<1 M',\n" +
                        "FROM fas_temp ";
                break;
            default:
                break;
        }
    
    return query;
    }
}
