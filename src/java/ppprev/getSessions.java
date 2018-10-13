/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppprev;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author EKaunda
 */
public class getSessions extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           
            int lessons=7;
            
            String curriculumid="";
            String formid="";
            
            dbConn conn= new dbConn();
            
            if(request.getParameter("curriculumid")!=null){
                
            curriculumid=request.getParameter("curriculumid");  
            }
            
            String where=" where 1=1 and ";
            if(request.getParameter("formid")!=null){
                
            formid=request.getParameter("formid"); 
            where+=" formid='"+formid+"'";
            }
            
            
            
            
            if(request.getParameter("lessons")!=null){
            
            if(!request.getParameter("lessons").equals("") && !request.getParameter("lessons").equals("undefined") ){
            
            lessons=  new Integer(request.getParameter("lessons"));  
            
            }
            
            
            }
            
             String table="<table class='table table-bordered'>  <thead class='thead-dark'>";
             String sessiondetails="<tr><td>Session</td>";
             String topics="<tbody><tr><td>Topic</td>";
             String methods="<tr><td>Methods used</td>";
             String sessiondate="<tr><td>Date of session</td>";
             String time="<tr><td>Time taken in minutes</td>";
             String malecondoms="<tr><td>Number of male condoms distributed</td>";
             String femalecondoms="<tr><td>Number of female condoms distributed</td>";
             
             getTopics gt=new getTopics();
             getMethods gm=new getMethods();
             String items[]={"topic","method","date","time","malecondoms","femalecondoms"};   
             //build list of to be selected items
             String qry="select ";
             for (int a = 1; a <= lessons; a++) {

                for (int b = 0; b < items.length; b++) {
                    if (a == lessons && b == items.length - 1) {
                        qry += "s" + a + "_" + items[b] + " ";
                    } else {
                        qry += "s" + a + "_" + items[b] + ", ";
                    }

                }

            }
             
             qry+=" from hc_formdata_1 "+where;
             
             System.out.println(" looong qry ni "+qry);
             
             conn.rs=conn.st.executeQuery(qry);
             
             ResultSetMetaData rsmd = conn.rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
          
             int hasdata=0;
             ArrayList mycolumns1 = new ArrayList(); 
             HashMap<String,String> hm=new HashMap<String,String>();
             //
             while (conn.rs.next()) 
             {

            if (hasdata==0) 
            {
            for (int i = 1; i <=columnCount; i++) 
                {
                    mycolumns1.add(rsmd.getColumnLabel(i));
                }//end of for loop
                hasdata++;
            }//end of if
            //data rows     
            for (int a = 0; a < columnCount; a++) {
                 
               hm.put(""+mycolumns1.get(a),conn.rs.getString("" + mycolumns1.get(a)));
                }

            hasdata++;
             }
             
             if(hasdata>1){
                 
              for(int a=1;a<=lessons;a++){
                 // conn.rs.next();
                  
                  
                 String defaulttopic =hm.get("s"+a+"_topic");
                 String defaultmethod=","+hm.get("s"+a+"_method");
                 String defaultdate  =hm.get("s"+a+"_date");
                 String defaulttime  =hm.get("s"+a+"_time");
                 String defaultmcn   =hm.get("s"+a+"_malecondoms");
                 String defaultfcn   =hm.get("s"+a+"_femalecondoms");
                 
             sessiondetails+="<th><b>S"+a+"</th>";
             topics+="<td><select   class='span12 topic' required    id='topics"+a+"' name='topics"+a+"'>"+gt.Topics(conn, curriculumid, defaulttopic)+"</select></td>";
             methods+="<td><select multiple='true' style='height:200px;'  class='span12 method' required    id='methods"+a+"' name='methods"+a+"'> "+gm.methods(conn, defaultmethod)+" </select></td>";
             sessiondate+="<td><input  class='span12 tarehe1 sessiondate' required type='text' readonly value='"+defaultdate+"'    id='sessiondate"+a+"' name='sessiondate"+a+"'> </td>";
             time+="<td><input onkeypress='return numbers(event);' value='"+defaulttime+"'  class='span12 time' required type='text'     id='time"+a+"' name='time"+a+"'></td>";
             malecondoms+="<td><input onkeypress='return numbers(event);' value='"+defaultmcn+"'  class='span12 malecondom' required type='text'     id='malecondom"+a+"' name='malecondom"+a+"'> </td>";
             femalecondoms+="<td> <input onkeypress='return numbers(event);' value='"+defaultfcn+"'  class='span12 femalecondom' required type='text'     id='femalecondom"+a+"' name='femalecondom"+a+"'></td>";
             
                                         }
                 
             }
             else {
             
             for(int a=1;a<=lessons;a++){
             
             sessiondetails+="<th><b>S"+a+"</th>";
             topics+="<td><select   class='span12 topic' required    id='topics"+a+"' name='topics"+a+"'>"+gt.Topics(conn, curriculumid, "")+"</select></td>";
             methods+="<td><select multiple='true' style='height:200px;'  class='span12 method' required    id='methods"+a+"' name='methods"+a+"'>"+gm.methods(conn, "")+"</select></td>";
             sessiondate+="<td> <input  class='span12 tarehe1 sessiondate' required type='text' readonly    id='sessiondate"+a+"' name='sessiondate"+a+"'> </td>";
             time+="<td> <input onkeypress='return numbers(event);'  class='span12 time' required type='text'     id='time"+a+"' name='time"+a+"'></td>";
             malecondoms+="<td> <input onkeypress='return numbers(event);'  class='span12 malecondom' required type='text'     id='malecondom"+a+"' name='malecondom"+a+"'> </td>";
             femalecondoms+="<td> <input onkeypress='return numbers(event);'  class='span12 femalecondom' required type='text'     id='femalecondom"+a+"' name='femalecondom"+a+"'></td>";
             
             }
            
        }
             
 String completedata=table+sessiondetails+"</tr></thead>"+topics+"</tr>"+methods+"</tr>"+sessiondate+"</tr>"+time+"</tr>"+malecondoms+"</tr>"+femalecondoms+"</tr></tbody></table>";
            out.println(completedata);
         if(conn.rs!=null){conn.rs.close(); }   
         if(conn.st!=null){conn.st.close();  }   
         if(conn.conn!=null){conn.conn.close();  }
            
            
        } finally {
            out.close();
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(getSessions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(getSessions.class.getName()).log(Level.SEVERE, null, ex);
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

}
