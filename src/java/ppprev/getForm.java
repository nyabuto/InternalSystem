/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppprev;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author EKaunda
 */
public class getForm extends HttpServlet {
HttpSession session=null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
        session=request.getSession();
            dbConn conn= new dbConn();
            
            //group details
     
int lessons=7;
int totalrows=1;
            
            if (request.getParameter("lessons") != null && !request.getParameter("lessons").equals("")) {
               lessons = new Integer(request.getParameter("lessons"));}
            
            if(request.getParameter("totalrows")!=null && !request.getParameter("totalrows").equals("")){
            totalrows=new Integer(request.getParameter("totalrows"))-1;
            }
          
            
String formdatainsert=" replace into hc_formdata_1 ";
String formdatacolumns="(";
String formdatavalues=") values (";

      String groupid="";
      
      
     String variablesform[]={"id","formid","partner","subcounty","targetpop","curriculum","group","lessons","facilitator","cofacilitator","startdate","enddate","agegroup"};       
     String variablesformname[]={"id","formid","partner","subcounty","targetpop","curriculum","kundi","lessons","facilitator","cofacilitator","startdate","enddate","agegroup"};       

     
    //_____________________    creates a query starting part similar to |          replace into hc_facilitator(facilitator_id,first_name)  values (?,?)
    //__________________________Do that for section 1__________________________________________________________________________________________________
            for (int p = 0; p < variablesform.length; p++) 
            {
                if(variablesformname[p].equals("kundi")){groupid=request.getParameter(variablesform[p]);}
                formdatacolumns += variablesformname[p] + ",";
                formdatavalues += "?,";
            }
            
            
//________________________________________Do that for session details section ( Wizard page 2)      __________________________________________________ 

 String variablessessions[]={"topic","method","date","time","malecondoms","femalecondoms"};       
 String variablessessionname[]={"topics","methods","sessiondate","time","malecondom","femalecondom"};       
//___________________________________________Session details____________________________________________________
for(int a=0;a<lessons;a++){
for(int p=0;p<variablessessions.length;p++){
//s1_topic   
//if (p==variablessessions.length-1){
//  formdatacolumns +="s"+(a+1)+variablessessions[p] + "";
//   formdatavalues += "?";
//}
//else{
if(1==1){
 formdatacolumns +="s"+(a+1)+"_"+variablessessions[p] + ",";
   formdatavalues += "?,";
}

}
}
          
  //________________________    join the query parts    __________________________
  
formdatainsert+=formdatacolumns+formdatavalues+") ";

formdatainsert=formdatainsert.replace("?,)", "?)");
formdatainsert=formdatainsert.replace(",)", ")");

//System.out.println(""+formdatainsert); 

conn.pst1=conn.conn.prepareStatement(formdatainsert);  
 //_________________________   append the data values into the prepared statement  ______________________
              int count=1;
            for (int p = 0; p < variablesform.length; p++) {
                
                String vals = "";
                if (request.getParameter(variablesform[p]) != null) 
                {
                vals = request.getParameter(variablesform[p]);
                conn.pst1.setString(count,vals);
                count++;
                }
                                                           }
            
//___________________________________________Session details____________________________________________________
for(int a=0;a<lessons;a++){
for(int p=0;p<variablessessions.length;p++){  
                String vals = "";
                //for multiselect data
                if(variablessessionname[p].equals("method")){
                     if (request.getParameterValues(variablessessionname[p]+(a+1)) != null) 
                {
                String [] valsi=request.getParameterValues(variablessessionname[p]+(a+1));
                   
                for(int v=0;v<valsi.length;v++)
                {
                vals+=valsi[v]+",";
                }
                conn.pst1.setString(count,vals);
                 count++;
                }
                                                         }
                else {
if (request.getParameter(variablessessionname[p]+(a+1)) != null) {
    
 vals = request.getParameter(variablessessionname[p]+(a+1));
                
  conn.pst1.setString(count,vals);
   count++;
                 
                }
}
}
}

            System.out.println(""+conn.pst1.toString());
conn.pst1.executeUpdate();     
            
/**
 id       
**/      

count=1;



 //______________________________save wizard page 3 onwards___________________________________
 
 String section3[]={"wardid","lessons","formid","enddate","group","id","firstname","middlename","lastname","age","sex"};
 String section3data[]={"wardid","expectedsessions","formid","enddate","group_id","id","fname","mname","sname","age","sex"};
//Participant details____________________________________________________


for(int a=0;a<totalrows;a++){
   
    String formdata2insert=" replace into hc_participants ";
String formdata2columns="(";
String formdata2values=") values (";
String form2insert="";

    for(int p=0;p<section3data.length;p++){
   formdata2columns +=""+section3data[p]+",";
   formdata2values += "?,";

}

//___________________________________________Session atendance____________________________________________________

   for(int x=0;x<lessons;x++){
    
 count=1;
   
//   if(x==lessons-1){
//   formdata2columns +="s"+(x+1)+ "";
//   formdata2values += "?";
//   
//   }
   if(1==1) {
     formdata2columns +="s"+(x+1)+ ",";
   formdata2values += "?,"; 
   }
   
 }
   
   formdata2insert+=formdata2columns+formdata2values+") ";
   
   formdata2insert=formdata2insert.replace("?,)", "?)");
formdata2insert=formdata2insert.replace(",)", ")");
   
   
conn.pst1=conn.conn.prepareStatement(formdata2insert);     
   
   //___________________values in section 2_____________________________
   
   for(int p=0;p<section3.length;p++){
    
  String vals="";
  //getgroupid
  if(section3[p].equals("wardid") || section3[p].equals("group") || section3[p].equals("enddate") || section3[p].equals("formid")|| section3[p].equals("lessons")){
  if (request.getParameter(section3[p]) != null)
    {
    vals = request.getParameter(section3[p]);
    conn.pst1.setString(count,""+vals);
    count++;
    }
   }
  
  else {
     if (request.getParameter(section3[p]+""+(a+1)) != null)
    {
    vals = request.getParameter(section3[p]+(a+1));
    conn.pst1.setString(count,""+vals);
    count++;
    }
  }
   
}
   for(int p=0;p<lessons;p++){
       
   String vals="";
 
    if (request.getParameter("s"+(a+1)+"_"+(p+1)) != null)
    {
        
    vals = request.getParameter("s"+(a+1)+"_"+(p+1));
    conn.pst1.setString(count,""+vals);
        System.out.println("conn.pst1.setString("+count+","+vals+");");
    count++;
    }
   

}
   
   System.out.println(""+conn.pst1.toString());
conn.pst1.executeUpdate();   

}//end of total rows string





//know the name of the last group
String returnnmsg="";
String groupname=getGroupName(groupid, conn);
            System.out.println(" Group Name"+groupname+" Group Id"+groupid);
if(!groupname.equals("")){
returnnmsg="<font color=\'green\'><b>Last saved group name is "+groupname+"</b></font>";

}


session.setAttribute("msg", returnnmsg);

out.println(returnnmsg);

   if(conn.rs!=null){conn.rs.close();}
   if(conn.rs1!=null){conn.rs1.close();}
   if(conn.st!=null){conn.st.close();}
   if(conn.st1!=null){conn.st1.close();}
   if(conn.pst1!=null){conn.pst1.close();}
      if(conn.conn!=null){conn.conn.close();}
   
    
           
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
            Logger.getLogger(getForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(getForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public String getGroupName(String groupid, dbConn conn) throws SQLException{
        String groupname="";
    String qry=" Select group_name from hc_groups where group_id ='"+groupid+"'";
    conn.rs=conn.st.executeQuery(qry);
    while(conn.rs.next()){
     groupname=conn.rs.getString(1);
    
    }
    
    
    return groupname;
    }
    
    
}
