/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalSystem;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GNyabuto
 */
public class loadART extends HttpServlet {
HttpSession session;
String output,lock,enterdby,validity,Header;
String newART,currentART;
String year,month;
String tableid,SubPartnerID,Annee,Mois,new_1m,new_1f,new_4m,new_4f,new_9m,new_9f,new_14m,new_14f,new_19m,new_19f,new_24m,new_24f,new_29m,new_29f,new_34m,new_34f,new_39m,new_39f,new_49m,new_49f,new_50m,new_50f,new_STm,new_STf,new_GT,current_1m,current_1f,current_4m,current_4f,current_9m,current_9f,current_14m,current_14f,current_19m,current_19f,current_24m,current_24f,current_29m,current_29f,current_34m,current_34f,current_39m,current_39f,current_49m,current_49f,current_50m,current_50f,current_STm,current_STf,current_GT,user_id,isValidated,isLocked,updatedBy,updatedOn,yearmonth,timestamp;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            tableid=SubPartnerID=Annee=Mois=new_1m=new_1f=new_4m=new_4f=new_9m=new_9f=new_14m=new_14f=new_19m=new_19f=new_24m=new_24f=new_29m=new_29f=new_34m=new_34f=new_39m=new_39f=new_49m=new_49f=new_50m=new_50f=new_STm=new_STf=new_GT=current_1m=current_1f=current_4m=current_4f=current_9m=current_9f=current_14m=current_14f=current_19m=current_19f=current_24m=current_24f=current_29m=current_29f=current_34m=current_34f=current_39m=current_39f=current_49m=current_49f=current_50m=current_50f=current_STm=current_STf=current_GT=user_id=isValidated=isLocked=updatedBy=updatedOn=yearmonth=timestamp="";
           dbConn conn = new dbConn();
           session = request.getSession();
           //   tableid="2018_10_331";
        lock="";
            String facil = "";

            validity="<b><font color='#4b8df8'>New Entry</font></b>";

            if (session.getAttribute("year") != null) {
                year = session.getAttribute("year").toString();
            }
            if (session.getAttribute("monthid") != null) {
                month = session.getAttribute("monthid").toString();
            }

            if (session.getAttribute("facilityid") != null) {
                facil = session.getAttribute("facilityid").toString();
            }
            tableid = year + "_" + month + "_" + facil;
        
int ARTdone=0;
int ARTundone=0;
int ARTvalid=0;
int facilssupporting=0;
String distid="";

if(session.getAttribute("subcountyid")!=null){
distid=session.getAttribute("subcountyid").toString();
}


String ARTcounter="SELECT COUNT(tableid) FROM art join subpartnera on art.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and (new_GT is not null ||current_GT!='')  ";
 conn.rs1 = conn.st1.executeQuery(ARTcounter);
 if(conn.rs1.next()){
 ARTdone=conn.rs1.getInt(1);
  }
 
  String ARTvalidatedcounter1="SELECT COUNT(tableid) FROM art join subpartnera on art.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and isValidated='0' ";
 conn.rs1 = conn.st1.executeQuery(ARTvalidatedcounter1);
 if(conn.rs1.next()){
 ARTvalid=conn.rs1.getInt(1);
  }
 
            System.out.println(ARTcounter);
 
 String ARTcounter1="SELECT COUNT(tableid) FROM art join subpartnera on art.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and  isValidated='0' ";
 conn.rs1 = conn.st1.executeQuery(ARTcounter1);
 if(conn.rs1.next()){
 ARTundone=conn.rs1.getInt(1);
  }
 
 
 
 
 String countSupportedfacility="Select COUNT(SubPartnerID) from subpartnera where ART ='1' and  DistrictID='"+distid+"'";
// String countfacility="Select * from subpartnera where FP='1' || PMTCT ='1' || Maternity='1' || HTC='1' ";
 conn.rs1 = conn.st1.executeQuery(countSupportedfacility);
 if(conn.rs1.next()){
 facilssupporting=conn.rs1.getInt(1);
 }
 
String validated="&nbsp &nbsp Validated Form(s): <b>"+ARTvalid+" </b>";
 String unvalidated="&nbsp &nbsp Unvalidated Form (s) <font color='black'><b>"+ARTundone+"</b></font>";
 
 
  String unvalidatedLink="";int counter=0;
     if(ARTundone>0){
     String getUnvalidated="SELECT art.SubPartnerID,subpartnera.SubPartnerNom FROM art JOIN subpartnera ON art.SubPartnerID=subpartnera.SubPartnerID WHERE subpartnera.DistrictID='"+distid+"' AND art.Mois='"+month+"' AND art.Annee='"+year+"' AND art.isValidated='0'";
     conn.rs=conn.st.executeQuery(getUnvalidated);
     while(conn.rs.next()){
         counter++;
//     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=Form731.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=loadART.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
                    }
    }
     
   if(counter>0){
    unvalidated="<button class='btn btn-primary btn-lg' data-target='#unvalidatedModal' style='width:auto; height:auto;' data-toggle='modal' type='button'> Unvalidated Form (s) <span class='badge badge-important'><b>"+ARTundone+"</b></span></button>";
 
   }  
 
 String label="Record counter <font color='white'><b>"+ARTdone+"<b></font>  out of <b>"+facilssupporting+"</b>"+validated+unvalidated;
 
        yearmonth="";
        String tempmonth=month;
        int pepfaryear=Integer.parseInt(year);
        if(Integer.parseInt(month)<10){ tempmonth="0"+month; }
        else {pepfaryear--;}


        yearmonth=pepfaryear+""+tempmonth;
    String locked_DATA = "SELECT id FROM locked_data WHERE yearmonth=? AND art=?";
    conn.pst = conn.conn.prepareStatement(locked_DATA);
    conn.pst.setString(1, yearmonth);
    conn.pst.setInt(2, 1);
    conn.rs = conn.pst.executeQuery();
    if(conn.rs.next()){
      isLocked= "1";
      lock="disabled";
    }
    
 
         enterdby="";   
            String check_data="SELECT * FROM art WHERE tableid=?";
            conn.pst=conn.conn.prepareStatement(check_data);
            conn.pst.setString(1, tableid);
            conn.rs=conn.pst.executeQuery();
            if(conn.rs.next()){
                    tableid= conn.rs.getString("tableid");
                    SubPartnerID= conn.rs.getString("SubPartnerID");
                    Annee= conn.rs.getString("Annee");
                    Mois= conn.rs.getString("Mois");
                    if(conn.rs.getString("new_1m")!=null){new_1m= conn.rs.getString("new_1m");}
                    if(conn.rs.getString("new_1f")!=null){new_1f= conn.rs.getString("new_1f");}
                    if(conn.rs.getString("new_4m")!=null){new_4m= conn.rs.getString("new_4m");}
                    if(conn.rs.getString("new_4f")!=null){new_4f= conn.rs.getString("new_4f");}
                    if(conn.rs.getString("new_9m")!=null){new_9m= conn.rs.getString("new_9m");}
                    if(conn.rs.getString("new_9f")!=null){new_9f= conn.rs.getString("new_9f");}
                    if(conn.rs.getString("new_14m")!=null){new_14m= conn.rs.getString("new_14m");}
                    if(conn.rs.getString("new_14f")!=null){new_14f= conn.rs.getString("new_14f");}
                    if(conn.rs.getString("new_19m")!=null){new_19m= conn.rs.getString("new_19m");}
                    if(conn.rs.getString("new_19f")!=null){new_19f= conn.rs.getString("new_19f");}
                    if(conn.rs.getString("new_24m")!=null){new_24m= conn.rs.getString("new_24m");}
                    if(conn.rs.getString("new_24f")!=null){new_24f= conn.rs.getString("new_24f");}
                    if(conn.rs.getString("new_29m")!=null){new_29m= conn.rs.getString("new_29m");}
                    if(conn.rs.getString("new_29f")!=null){new_29f= conn.rs.getString("new_29f");}
                    if(conn.rs.getString("new_34m")!=null){new_34m= conn.rs.getString("new_34m");}
                    if(conn.rs.getString("new_34f")!=null){new_34f= conn.rs.getString("new_34f");}
                    if(conn.rs.getString("new_39m")!=null){new_39m= conn.rs.getString("new_39m");}
                    if(conn.rs.getString("new_39f")!=null){new_39f= conn.rs.getString("new_39f");}
                    if(conn.rs.getString("new_49m")!=null){new_49m= conn.rs.getString("new_49m");}
                   if(conn.rs.getString("new_49f")!=null){ new_49f= conn.rs.getString("new_49f");}
                    if(conn.rs.getString("new_50m")!=null){new_50m= conn.rs.getString("new_50m");}
                    if(conn.rs.getString("new_50f")!=null){new_50f= conn.rs.getString("new_50f");}
                    if(conn.rs.getString("new_STm")!=null){new_STm= conn.rs.getString("new_STm");}
                    if(conn.rs.getString("new_STf")!=null){new_STf= conn.rs.getString("new_STf");}
                    if(conn.rs.getString("new_GT")!=null){new_GT= conn.rs.getString("new_GT");}


                    
                    if(conn.rs.getString("current_1m")!=null){current_1m= conn.rs.getString("current_1m");}
                    if(conn.rs.getString("current_1f")!=null){current_1f= conn.rs.getString("current_1f");}
                   if(conn.rs.getString("current_4m")!=null){ current_4m= conn.rs.getString("current_4m");}
                    if(conn.rs.getString("current_4f")!=null){current_4f= conn.rs.getString("current_4f");}
                    if(conn.rs.getString("current_9m")!=null){current_9m= conn.rs.getString("current_9m");}
                    if(conn.rs.getString("current_9f")!=null){current_9f= conn.rs.getString("current_9f");}
                    if(conn.rs.getString("current_14m")!=null){current_14m= conn.rs.getString("current_14m");}
                    if(conn.rs.getString("current_14f")!=null){current_14f= conn.rs.getString("current_14f");}
                    if(conn.rs.getString("current_19m")!=null){current_19m= conn.rs.getString("current_19m");}
                    if(conn.rs.getString("current_19f")!=null){current_19f= conn.rs.getString("current_19f");}
                   if(conn.rs.getString("current_24m")!=null){ current_24m= conn.rs.getString("current_24m");}
                   if(conn.rs.getString("current_24f")!=null){ current_24f= conn.rs.getString("current_24f");}
                   if(conn.rs.getString("current_29m")!=null){current_29m= conn.rs.getString("current_29m");}
                   if(conn.rs.getString("current_29f")!=null){ current_29f= conn.rs.getString("current_29f");}
                   if(conn.rs.getString("current_34m")!=null){ current_34m= conn.rs.getString("current_34m");}
                   if(conn.rs.getString("current_34f")!=null){ current_34f= conn.rs.getString("current_34f");}
                   if(conn.rs.getString("current_39m")!=null){ current_39m= conn.rs.getString("current_39m");}
                   if(conn.rs.getString("current_39f")!=null){ current_39f= conn.rs.getString("current_39f");}
                   if(conn.rs.getString("current_49m")!=null){ current_49m= conn.rs.getString("current_49m");}
                    if(conn.rs.getString("current_49f")!=null){current_49f= conn.rs.getString("current_49f");}
                    if(conn.rs.getString("current_50m")!=null){current_50m= conn.rs.getString("current_50m");}
                    if(conn.rs.getString("current_50f")!=null){current_50f= conn.rs.getString("current_50f");}
                    if(conn.rs.getString("current_STm")!=null){current_STm= conn.rs.getString("current_STm");}
                    if(conn.rs.getString("current_STf")!=null){current_STf= conn.rs.getString("current_STf");}
                    if(conn.rs.getString("current_GT")!=null){current_GT= conn.rs.getString("current_GT");}


                 
                    
                    user_id= conn.rs.getString("user_id");
                    isValidated= conn.rs.getString("isValidated");
                    isLocked= conn.rs.getString("isLocked");
                    updatedBy= conn.rs.getString("updatedBy");
                    updatedOn= conn.rs.getString("updatedOn");
                    yearmonth= conn.rs.getString("yearmonth");
                    timestamp= conn.rs.getString("timestamp");
       
                
                  if(isLocked.equals("1")){lock="disabled";}
        //get the name of the person who entered the form 
        enterdby="";
        String enterer="select * from user where userid='"+conn.rs.getString("user_id") +"'";
        
        conn.rs1=conn.st1.executeQuery(enterer);
        //add details of person who entered
        if(conn.rs1.next()){
        enterdby="<font color='green'>Data 1st entered by:   <b> "+conn.rs1.getString("fname")+" "+conn.rs1.getString("mname")+" "+conn.rs1.getString("lname")+"</b>  on  <b>"+conn.rs.getString("timestamp") +"</b></font>";
        }
        
		
		//now check if form was updated and if its one month after data entry
        
        if(conn.rs.getString("updatedOn")!=null){
        //get difference in months between entered date and updated date
        String compdate="SELECT TIMESTAMPDIFF(MONTH,'"+conn.rs.getString("timestamp") +"','"+conn.rs.getString("updatedOn") +"')";
        conn.rs2=conn.st2.executeQuery(compdate);
        if (conn.rs2.next()){
            //now get the details of the person who updated the form
        //if the difference is greater than or equal to one, 
        
           enterdby=""; 
            if(conn.rs2.getInt(1)>=1){
        String updater="select * from user where userid='"+conn.rs.getString("updatedBy") +"'";
        
        conn.rs1=conn.st1.executeQuery(updater);
        //add details of person who entered
        if(conn.rs1.next()){
            enterdby += "<span style='margin-left:30%;'><font color='red'>   Updated  by:   <b> " + conn.rs1.getString("fname") + " " + conn.rs1.getString("mname") + " " + conn.rs1.getString("lname") + "</b>  on  <b>" + conn.rs.getString("updatedOn") + "</b></font></span>";
         }
        } //end of if month >=1 
        }//end of date comparison if 
        
        }//end of if updated !=null
                     
            }
            
              System.out.println("Validity checker : "+isValidated);
      if(isValidated.equals("0")){
     validity="<font color='red'><b>Form Not Validated.<img width='20px' height='20px' src='images/notValidated.jpg' style='margin-left:5px;'></b></font>";
    }
        else if(isValidated.equals("1")){
       validity="<font color='green'><b>Form Validated.<img width='20px' height='20px' src='images/validated.jpg' style='margin-left:5px;'></b></font>";
    }
      else{
        validity="<font color=\"blue\"><b>New entry form.</b></font>"  ;          
              }

    session.setAttribute("isValidated", validity);
    
    // code to output the table
    
    
     Header = "<table border=\"1px;\">"
        + "<tr border=\"1px;\">"
        + "<td rowspan=\"2\" class=\"title\">SNO</td>"
        + "<td  rowspan=\"2\" class=\"title\">Indicator</td>"
        
        + "<td colspan=\"2\"><1</td>"
        + "<td colspan=\"2\">1-4</td>"
        + "<td colspan=\"2\">5-9</td>"
        + "<td colspan=\"2\">10-14</td>"
        + "<td colspan=\"2\">15-19</td>"
        + "<td colspan=\"2\">20-24</td>"
        + "<td colspan=\"2\">25-29</td>"
        + "<td colspan=\"2\">30-34</td>"
        + "<td colspan=\"2\">35-39</td>"
        + "<td colspan=\"2\">40-49</td>"
        + "<td colspan=\"2\">50+</td>"
        + "<td colspan=\"2\">Sub Total</td>"
        + "<td rowspan=\"2\">Grand Total</td>"
        + "</tr>"
            
        + "<tr>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "</tr>";
    
    
    newART = ""
        + "<tr border=\"1px;\">"
        + "<td class=\"title\">A</td>"
        + "<td class=\"title indicator\">New on ART</td>"
            
        + "<td><input type=\"text\" name=\"new_1m\" id=\"new_1m\" value=\""+new_1m+"\"  onblur=\"autosave('new_1m');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_1f\" id=\"new_1f\" value=\""+new_1f+"\"  onblur=\"autosave('new_1f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_4m\" id=\"new_4m\" value=\""+new_4m+"\"  onblur=\"autosave('new_4m');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_4f\" id=\"new_4f\" value=\""+new_4f+"\"  onblur=\"autosave('new_4f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_9m\" id=\"new_9m\" value=\""+new_9m+"\"  onblur=\"autosave('new_9m');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_9f\" id=\"new_9f\" value=\""+new_9f+"\"  onblur=\"autosave('new_9f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_14m\" id=\"new_14m\" value=\""+new_14m+"\" onblur=\"autosave('new_14m');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_14f\" id=\"new_14f\" value=\""+new_14f+"\" onblur=\"autosave('new_14f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_19m\" id=\"new_19m\" value=\""+new_19m+"\"  onblur=\"autosave('new_19m');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_19f\" id=\"new_19f\" value=\""+new_19f+"\"  onblur=\"autosave('new_19f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_24m\" id=\"new_24m\" value=\""+new_24m+"\"  onblur=\"autosave('new_24m');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_24f\" id=\"new_24f\" value=\""+new_24f+"\"  onblur=\"autosave('new_24f');\"  oninput=\"newART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_29m\" id=\"new_29m\" value=\""+new_29m+"\"  onblur=\"autosave('new_29m');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_29f\" id=\"new_29f\" value=\""+new_29f+"\"  onblur=\"autosave('new_29f');\"  oninput=\"newART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_34m\" id=\"new_34m\" value=\""+new_34m+"\"  onblur=\"autosave('new_34m');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_34f\" id=\"new_34f\" value=\""+new_34f+"\"  onblur=\"autosave('new_34f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_39m\" id=\"new_39m\" value=\""+new_39m+"\"  onblur=\"autosave('new_39m');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_39f\" id=\"new_39f\" value=\""+new_39f+"\"  onblur=\"autosave('new_39f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_49m\" id=\"new_49m\" value=\""+new_49m+"\"  onblur=\"autosave('new_49m');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_49f\" id=\"new_49f\" value=\""+new_49f+"\"  onblur=\"autosave('new_49f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_50m\" id=\"new_50m\" value=\""+new_50m+"\"  onblur=\"autosave('new_50m');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_50f\" id=\"new_50f\" value=\""+new_50f+"\"  onblur=\"autosave('new_50f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"new_STm\" id=\"new_STm\" value=\""+new_STm+"\" tabindex=\"-1\"  onblur=\"autosave('new_STm');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"new_STf\" id=\"new_STf\" value=\""+new_STf+"\" tabindex=\"-1\"  onblur=\"autosave('new_STf');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"new_GT\" id=\"new_GT\" value=\""+new_GT+"\" tabindex=\"-1\"  onblur=\"autosave('new_GT');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>";
            
    currentART = "" 
        + "<tr border=\"1px;\">"
        + "<td class=\"title\">B</td>"
        + "<td class=\"title indicator\">Current on ART</td>"
         
        + "<td><input type=\"text\" name=\"current_1m\" id=\"current_1m\" value=\""+current_1m+"\"  onblur=\"autosave('current_1m');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_1f\" id=\"current_1f\" value=\""+current_1f+"\"  onblur=\"autosave('current_1f');\"  oninput=\"currentART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_4m\" id=\"current_4m\" value=\""+current_4m+"\"  onblur=\"autosave('current_4m');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_4f\" id=\"current_4f\" value=\""+current_4f+"\"  onblur=\"autosave('current_4f');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_9m\" id=\"current_9m\" value=\""+current_9m+"\"  onblur=\"autosave('current_9m');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_9f\" id=\"current_9f\" value=\""+current_9f+"\"  onblur=\"autosave('current_9f');\"  oninput=\"currentART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_14m\" id=\"current_14m\" value=\""+current_14m+"\" onblur=\"autosave('current_14m');\"  oninput=\"currentART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_14f\" id=\"current_14f\" value=\""+current_14f+"\" onblur=\"autosave('current_14f');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_19m\" id=\"current_19m\" value=\""+current_19m+"\"  onblur=\"autosave('current_19m');\"  oninput=\"currentART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_19f\" id=\"current_19f\" value=\""+current_19f+"\"  onblur=\"autosave('current_19f');\"  oninput=\"currentART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_24m\" id=\"current_24m\" value=\""+current_24m+"\"  onblur=\"autosave('current_24m');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_24f\" id=\"current_24f\" value=\""+current_24f+"\"  onblur=\"autosave('current_24f');\"  oninput=\"currentART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_29m\" id=\"current_29m\" value=\""+current_29m+"\"  onblur=\"autosave('current_29m');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_29f\" id=\"current_29f\" value=\""+current_29f+"\"  onblur=\"autosave('current_29f');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_34m\" id=\"current_34m\" value=\""+current_34m+"\"  onblur=\"autosave('current_34m');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_34f\" id=\"current_34f\" value=\""+current_34f+"\"  onblur=\"autosave('current_34f');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_39m\" id=\"current_39m\" value=\""+current_39m+"\"  onblur=\"autosave('current_39m');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_39f\" id=\"current_39f\" value=\""+current_39f+"\"  onblur=\"autosave('current_39f');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_49m\" id=\"current_49m\" value=\""+current_49m+"\"  onblur=\"autosave('current_49m');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_49f\" id=\"current_49f\" value=\""+current_49f+"\"  onblur=\"autosave('current_49f');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_50m\" id=\"current_50m\" value=\""+current_50m+"\"  onblur=\"autosave('current_50m');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_50f\" id=\"current_50f\" value=\""+current_50f+"\"  onblur=\"autosave('current_50f');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"current_STm\" id=\"current_STm\" value=\""+current_STm+"\" tabindex=\"-1\"  onblur=\"autosave('current_STm');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"current_STf\" id=\"current_STf\" value=\""+current_STf+"\" tabindex=\"-1\"  onblur=\"autosave('current_STf');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"current_GT\" id=\"current_GT\" value=\""+current_GT+"\" tabindex=\"-1\"  onblur=\"autosave('current_GT');\"  oninput=\"currentART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
        + "" 
        + "</table>";  
    
    

    
     //end of code  
     
     
      if (session.getAttribute("forms_holder") != null) {
     if (session.getAttribute("forms_holder").toString().contains("ART")) {
        output=enterdby+Header+newART+currentART;
        if(isLocked.equals("1")){
            output += "<div class='form-actions' style=\"text-align:center;\"><input type='submit' disabled class='btn red' value='Form Locked' name='validate' id='validate'/></div><span id='formstatus' style='display:none;'>" + validity + " </span><span id='rc' style='display:none;'>"+label+" </span><span id='ufs' style='display:none;'>"+unvalidatedLink+"</span>";
        }
        else{
            output += "<div class='form-actions' style=\"text-align:center;\"><input type='submit' class='btn blue' value='Save ART Data' name='validate' id='validate'/></div><span id='formstatus' style='display:none;'>" + validity + " </span><span id='rc' style='display:none;'>"+label+" </span><span id='ufs' style='display:none;'>"+unvalidatedLink+"</span>";
        }
        
        } else {
                    output = "<tr ><td colspan='4'><font color=\"red\" size=\"6px;\" style=\"margin-left: 0%;\"><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support ART Module.</font></td></tr>";
                }
            }
        if (session.getAttribute("facilityid") != null) {
            }
        else {
                output = "<tr ><td colspan='4'><font color=\"red\" size=\"6px;\" style=\"margin-left: 0%;\"><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support ART Module.</font></td></tr>";

            }
        
        out.println(output);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(loadART.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(loadART.class.getName()).log(Level.SEVERE, null, ex);
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
