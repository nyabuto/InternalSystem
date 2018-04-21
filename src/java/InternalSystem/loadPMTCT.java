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
public class loadPMTCT extends HttpServlet {
HttpSession session;
String output,lock,enterdby,validity,Header;
String Testing,KP,NP,newART,alreadyonART;
String year,month;
String tableid,SubPartnerID,Annee,Mois,Testing_1f,Testing_4f,Testing_9f,Testing_14f,Testing_19f,Testing_24f,Testing_29f,Testing_34f,Testing_39f,Testing_49f,Testing_50f,Testing_GT,KP_1f,KP_4f,KP_9f,KP_14f,KP_19f,KP_24f,KP_29f,KP_34f,KP_39f,KP_49f,KP_50f,KP_GT,NP_1f,NP_4f,NP_9f,NP_14f,NP_19f,NP_24f,NP_29f,NP_34f,NP_39f,NP_49f,NP_50f,NP_GT,newART_1f,newART_4f,newART_9f,newART_14f,newART_19f,newART_24f,newART_29f,newART_34f,newART_39f,newART_49f,newART_50f,newART_GT,alreadyonART_1f,alreadyonART_4f,alreadyonART_9f,alreadyonART_14f,alreadyonART_19f,alreadyonART_24f,alreadyonART_29f,alreadyonART_34f,alreadyonART_39f,alreadyonART_49f,alreadyonART_50f,alreadyonART_GT,user_id,isValidated,isLocked,updatedBy,updatedOn,yearmonth,timestamp;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          tableid=SubPartnerID=Annee=Mois=Testing_1f=Testing_4f=Testing_9f=Testing_14f=Testing_19f=Testing_24f=Testing_29f=Testing_34f=Testing_39f=Testing_49f=Testing_50f=Testing_GT=KP_1f=KP_4f=KP_9f=KP_14f=KP_19f=KP_24f=KP_29f=KP_34f=KP_39f=KP_49f=KP_50f=KP_GT=NP_1f=NP_4f=NP_9f=NP_14f=NP_19f=NP_24f=NP_29f=NP_34f=NP_39f=NP_49f=NP_50f=NP_GT=newART_1f=newART_4f=newART_9f=newART_14f=newART_19f=newART_24f=newART_29f=newART_34f=newART_39f=newART_49f=newART_50f=newART_GT=alreadyonART_1f=alreadyonART_4f=alreadyonART_9f=alreadyonART_14f=alreadyonART_19f=alreadyonART_24f=alreadyonART_29f=alreadyonART_34f=alreadyonART_39f=alreadyonART_49f=alreadyonART_50f=alreadyonART_GT=user_id=isValidated=isLocked=updatedBy=updatedOn=yearmonth=timestamp="";  
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
        
int PMTCTdone=0;
int PMTCTundone=0;
int PMTCTvalid=0;
int facilssupporting=0;
String distid="";

if(session.getAttribute("subcountyid")!=null){
distid=session.getAttribute("subcountyid").toString();
}


String ARTcounter="SELECT COUNT(tableid) FROM pmtct join subpartnera on pmtct.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and (Testing_GT is not null ||Testing_GT!='' || KP_GT is not null || KP_GT!='' || NP_GT is not null || NP_GT!='' || newART_GT is not null || newART_GT!='' || alreadyonART_GT is not null || alreadyonART_GT!='')  ";
 conn.rs1 = conn.st1.executeQuery(ARTcounter);
 if(conn.rs1.next()){
 PMTCTdone=conn.rs1.getInt(1);
  }
 
  String ARTvalidatedcounter1="SELECT COUNT(tableid) FROM pmtct join subpartnera on pmtct.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and isValidated='0' ";
 conn.rs1 = conn.st1.executeQuery(ARTvalidatedcounter1);
 if(conn.rs1.next()){
 PMTCTvalid=conn.rs1.getInt(1);
  }
 
            System.out.println(ARTcounter);
 
 String ARTcounter1="SELECT COUNT(tableid) FROM pmtct join subpartnera on pmtct.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and  isValidated='0' ";
 conn.rs1 = conn.st1.executeQuery(ARTcounter1);
 if(conn.rs1.next()){
 PMTCTundone=conn.rs1.getInt(1);
  }
 
 
 
 
 String countSupportedfacility="Select COUNT(SubPartnerID) from subpartnera where PMTCT ='1' and  DistrictID='"+distid+"'";
// String countfacility="Select * from subpartnera where FP='1' || PMTCT ='1' || Maternity='1' || HTC='1' ";
 conn.rs1 = conn.st1.executeQuery(countSupportedfacility);
 if(conn.rs1.next()){
 facilssupporting=conn.rs1.getInt(1);
 }
 
String validated="&nbsp &nbsp Validated Form(s): <b>"+PMTCTvalid+" </b>";
 String unvalidated="&nbsp &nbsp Unvalidated Form (s) <font color='black'><b>"+PMTCTundone+"</b></font>";
 
 
  String unvalidatedLink="";int counter=0;
     if(PMTCTundone>0){
     String getUnvalidated="SELECT pmtct.SubPartnerID,subpartnera.SubPartnerNom FROM pmtct JOIN subpartnera ON pmtct.SubPartnerID=subpartnera.SubPartnerID WHERE subpartnera.DistrictID='"+distid+"' AND art.Mois='"+month+"' AND pmtct.Annee='"+year+"' AND pmtct.isValidated='0'";
     conn.rs=conn.st.executeQuery(getUnvalidated);
     while(conn.rs.next()){
         counter++;
//     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=Form731.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=loadART.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
                    }
    }
     
   if(counter>0){
    unvalidated="<button class='btn btn-primary btn-lg' data-target='#unvalidatedModal' style='width:auto; height:auto;' data-toggle='modal' type='button'> Unvalidated Form (s) <span class='badge badge-important'><b>"+PMTCTundone+"</b></span></button>";
 
   }  
 
 String label="Record counter <font color='white'><b>"+PMTCTdone+"<b></font>  out of <b>"+facilssupporting+"</b>"+validated+unvalidated;
 
 
         yearmonth="";
        String tempmonth=month;
        int pepfaryear=Integer.parseInt(year);
        if(Integer.parseInt(month)<10){ tempmonth="0"+month; }
        else {pepfaryear--;}


        yearmonth=pepfaryear+""+tempmonth;
    String locked_DATA = "SELECT id FROM locked_data WHERE yearmonth=? AND pmtct=?";
    conn.pst = conn.conn.prepareStatement(locked_DATA);
    conn.pst.setString(1, yearmonth);
    conn.pst.setInt(2, 1);
    conn.rs = conn.pst.executeQuery();
    if(conn.rs.next()){
      isLocked= "1";
      lock="disabled";
    } 
    
         enterdby="";   
            String check_data="SELECT * FROM pmtct WHERE tableid=?";
            conn.pst=conn.conn.prepareStatement(check_data);
            conn.pst.setString(1, tableid);
            conn.rs=conn.pst.executeQuery();
            if(conn.rs.next()){
                    tableid= conn.rs.getString("tableid");
                    SubPartnerID= conn.rs.getString("SubPartnerID");
                    Annee= conn.rs.getString("Annee");
                    Mois= conn.rs.getString("Mois");
                    if(conn.rs.getString("Testing_1f")!=null){Testing_1f= conn.rs.getString("Testing_1f");}
                    if(conn.rs.getString("Testing_4f")!=null){Testing_4f= conn.rs.getString("Testing_4f");}
                    if(conn.rs.getString("Testing_9f")!=null){Testing_9f= conn.rs.getString("Testing_9f");}
                    if(conn.rs.getString("Testing_14f")!=null){Testing_14f= conn.rs.getString("Testing_14f");}
                    if(conn.rs.getString("Testing_19f")!=null){Testing_19f= conn.rs.getString("Testing_19f");}
                    if(conn.rs.getString("Testing_24f")!=null){Testing_24f= conn.rs.getString("Testing_24f");}
                    if(conn.rs.getString("Testing_29f")!=null){Testing_29f= conn.rs.getString("Testing_29f");}
                    if(conn.rs.getString("Testing_34f")!=null){Testing_34f= conn.rs.getString("Testing_34f");}
                    if(conn.rs.getString("Testing_39f")!=null){Testing_39f= conn.rs.getString("Testing_39f");}
                    if(conn.rs.getString("Testing_49f")!=null){ Testing_49f= conn.rs.getString("Testing_49f");}
                    if(conn.rs.getString("Testing_50f")!=null){Testing_50f= conn.rs.getString("Testing_50f");}
                    if(conn.rs.getString("Testing_GT")!=null){Testing_GT= conn.rs.getString("Testing_GT");}


                    
                    if(conn.rs.getString("KP_1f")!=null){KP_1f= conn.rs.getString("KP_1f");}
                    if(conn.rs.getString("KP_4f")!=null){KP_4f= conn.rs.getString("KP_4f");}
                    if(conn.rs.getString("KP_9f")!=null){KP_9f= conn.rs.getString("KP_9f");}
                    if(conn.rs.getString("KP_14f")!=null){KP_14f= conn.rs.getString("KP_14f");}
                    if(conn.rs.getString("KP_19f")!=null){KP_19f= conn.rs.getString("KP_19f");}
                   if(conn.rs.getString("KP_24f")!=null){ KP_24f= conn.rs.getString("KP_24f");}
                   if(conn.rs.getString("KP_29f")!=null){ KP_29f= conn.rs.getString("KP_29f");}
                   if(conn.rs.getString("KP_34f")!=null){ KP_34f= conn.rs.getString("KP_34f");}
                   if(conn.rs.getString("KP_39f")!=null){ KP_39f= conn.rs.getString("KP_39f");}
                    if(conn.rs.getString("KP_49f")!=null){KP_49f= conn.rs.getString("KP_49f");}
                    if(conn.rs.getString("KP_50f")!=null){KP_50f= conn.rs.getString("KP_50f");}
                    if(conn.rs.getString("KP_GT")!=null){KP_GT= conn.rs.getString("KP_GT");}


                    
                    if(conn.rs.getString("NP_1f")!=null){NP_1f= conn.rs.getString("NP_1f");}
                    if(conn.rs.getString("NP_4f")!=null){NP_4f= conn.rs.getString("NP_4f");}
                    if(conn.rs.getString("NP_9f")!=null){NP_9f= conn.rs.getString("NP_9f");}
                    if(conn.rs.getString("NP_14f")!=null){NP_14f= conn.rs.getString("NP_14f");}
                    if(conn.rs.getString("NP_19f")!=null){NP_19f= conn.rs.getString("NP_19f");}
                    if(conn.rs.getString("NP_24f")!=null){NP_24f= conn.rs.getString("NP_24f");}
                   if(conn.rs.getString("NP_29f")!=null){ NP_29f= conn.rs.getString("NP_29f");}
                   if(conn.rs.getString("NP_34f")!=null){ NP_34f= conn.rs.getString("NP_34f");}
                   if(conn.rs.getString("NP_39f")!=null){ NP_39f= conn.rs.getString("NP_39f");}
                   if(conn.rs.getString("NP_49f")!=null){ NP_49f= conn.rs.getString("NP_49f");}
                   if(conn.rs.getString("NP_50f")!=null){ NP_50f= conn.rs.getString("NP_50f");}
                   if(conn.rs.getString("NP_GT")!=null){ NP_GT= conn.rs.getString("NP_GT");}


                    
                    if(conn.rs.getString("newART_1f")!=null){newART_1f= conn.rs.getString("newART_1f");}
                   if(conn.rs.getString("newART_4f")!=null){ newART_4f= conn.rs.getString("newART_4f");}
                   if(conn.rs.getString("newART_9f")!=null){ newART_9f= conn.rs.getString("newART_9f");}
                   if(conn.rs.getString("newART_14f")!=null){ newART_14f= conn.rs.getString("newART_14f");}
                   if(conn.rs.getString("newART_19f")!=null){ newART_19f= conn.rs.getString("newART_19f");}
                    if(conn.rs.getString("newART_24f")!=null){newART_24f= conn.rs.getString("newART_24f");}
                    if(conn.rs.getString("newART_29f")!=null){newART_29f= conn.rs.getString("newART_29f");}
                    if(conn.rs.getString("newART_34f")!=null){newART_34f= conn.rs.getString("newART_34f");}
                   if(conn.rs.getString("newART_39f")!=null){ newART_39f= conn.rs.getString("newART_39f");}
                    if(conn.rs.getString("newART_49f")!=null){newART_49f= conn.rs.getString("newART_49f");}
                   if(conn.rs.getString("newART_50f")!=null){ newART_50f= conn.rs.getString("newART_50f");}
                    if(conn.rs.getString("newART_GT")!=null){newART_GT= conn.rs.getString("newART_GT");}


                    
                    if(conn.rs.getString("alreadyonART_1f")!=null){alreadyonART_1f= conn.rs.getString("alreadyonART_1f");}
                    if(conn.rs.getString("alreadyonART_4f")!=null){alreadyonART_4f= conn.rs.getString("alreadyonART_4f");}
                    if(conn.rs.getString("alreadyonART_9f")!=null){alreadyonART_9f= conn.rs.getString("alreadyonART_9f");}
                   if(conn.rs.getString("alreadyonART_14f")!=null){ alreadyonART_14f= conn.rs.getString("alreadyonART_14f");}
                   if(conn.rs.getString("alreadyonART_19f")!=null){ alreadyonART_19f= conn.rs.getString("alreadyonART_19f");}
                   if(conn.rs.getString("alreadyonART_24f")!=null){ alreadyonART_24f= conn.rs.getString("alreadyonART_24f");}
                  if(conn.rs.getString("alreadyonART_29f")!=null){  alreadyonART_29f= conn.rs.getString("alreadyonART_29f");}
                   if(conn.rs.getString("alreadyonART_34f")!=null){ alreadyonART_34f= conn.rs.getString("alreadyonART_34f");}
                  if(conn.rs.getString("alreadyonART_39f")!=null){  alreadyonART_39f= conn.rs.getString("alreadyonART_39f");}
                   if(conn.rs.getString("alreadyonART_49f")!=null){ alreadyonART_49f= conn.rs.getString("alreadyonART_49f");}
                   if(conn.rs.getString("alreadyonART_50f")!=null){ alreadyonART_50f= conn.rs.getString("alreadyonART_50f");}
                   if(conn.rs.getString("alreadyonART_GT")!=null){ alreadyonART_GT= conn.rs.getString("alreadyonART_GT");}

             
                    
                    user_id= conn.rs.getString("user_id");
                    isValidated= conn.rs.getString("isValidated");
                    isLocked= conn.rs.getString("isLocked");
                    updatedBy= conn.rs.getString("updatedBy");
                    updatedOn= conn.rs.getString("updatedOn");
                    yearmonth= conn.rs.getString("yearmonth");
                    timestamp= conn.rs.getString("timestamp");
       
                
                  if(isLocked.equals("1")){lock="disabled";}
                  else if(isLocked.equals("0")){lock="";}
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
        + "<td class=\"title\">Code</td>"
        + "<td class=\"title\">Indicator</td>"
        
        + "<td ><1</td>"
        + "<td >1-4</td>"
        + "<td >5-9</td>"
        + "<td >10-14</td>"
        + "<td >15-19</td>"
        + "<td >20-24</td>"
        + "<td >25-29</td>"
        + "<td >30-34</td>"
        + "<td >35-39</td>"
        + "<td >40-49</td>"
        + "<td >50+</td>"
        + "<td >Total</td>"
        + "</tr>";
    
    
    Testing = ""
        + "<tr border=\"1px;\">"
        + "<td class=\"title\">1</td>"
        + "<td class=\"title indicator\">HIV Testing at ANC</td>"
            
        + "<td><input type=\"text\" name=\"Testing_1f\" id=\"Testing_1f\" value=\""+Testing_1f+"\"  tabindex=\"-1\"  onblur=\"autosave('Testing_1f');\"  oninput=\"Testing(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"Testing_4f\" id=\"Testing_4f\" value=\""+Testing_4f+"\"  tabindex=\"-1\"  onblur=\"autosave('Testing_4f');\"  oninput=\"Testing(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"Testing_9f\" id=\"Testing_9f\" value=\""+Testing_9f+"\"  tabindex=\"-1\"  onblur=\"autosave('Testing_9f');\"  oninput=\"Testing(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"Testing_14f\" id=\"Testing_14f\" value=\""+Testing_14f+"\" onblur=\"autosave('Testing_14f');\"  oninput=\"Testing(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"Testing_19f\" id=\"Testing_19f\" value=\""+Testing_19f+"\"  onblur=\"autosave('Testing_19f');\"  oninput=\"Testing(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"Testing_24f\" id=\"Testing_24f\" value=\""+Testing_24f+"\"  onblur=\"autosave('Testing_24f');\"  oninput=\"Testing();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"Testing_29f\" id=\"Testing_29f\" value=\""+Testing_29f+"\"  onblur=\"autosave('Testing_29f');\"  oninput=\"Testing();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"Testing_34f\" id=\"Testing_34f\" value=\""+Testing_34f+"\"  onblur=\"autosave('Testing_34f');\"  oninput=\"Testing(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"Testing_39f\" id=\"Testing_39f\" value=\""+Testing_39f+"\"  onblur=\"autosave('Testing_39f');\"  oninput=\"Testing(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"Testing_49f\" id=\"Testing_49f\" value=\""+Testing_49f+"\"  onblur=\"autosave('Testing_49f');\"  oninput=\"Testing(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"Testing_50f\" id=\"Testing_50f\" value=\""+Testing_50f+"\"  onblur=\"autosave('Testing_50f');\"  oninput=\"Testing(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"Testing_GT\" id=\"Testing_GT\" value=\""+Testing_GT+"\" tabindex=\"-1\"  onblur=\"autosave('Testing_GT');\"  oninput=\"Testing(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>";
            
    KP = "" 
        + "<tr border=\"1px;\">"
        + "<td class=\"title\">2</td>"
        + "<td class=\"title indicator\">Known Positive at entry in ANC</td>"
         
        + "<td><input type=\"text\" name=\"KP_1f\" id=\"KP_1f\" value=\""+KP_1f+"\"  tabindex=\"-1\"  onblur=\"autosave('KP_1f');\"  oninput=\"KP();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"KP_4f\" id=\"KP_4f\" value=\""+KP_4f+"\"  tabindex=\"-1\"  onblur=\"autosave('KP_4f');\"  oninput=\"KP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"KP_9f\" id=\"KP_9f\" value=\""+KP_9f+"\"  tabindex=\"-1\"  onblur=\"autosave('KP_9f');\"  oninput=\"KP();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"KP_14f\" id=\"KP_14f\" value=\""+KP_14f+"\" onblur=\"autosave('KP_14f');\"  oninput=\"KP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"KP_19f\" id=\"KP_19f\" value=\""+KP_19f+"\"  onblur=\"autosave('KP_19f');\"  oninput=\"KP();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"KP_24f\" id=\"KP_24f\" value=\""+KP_24f+"\"  onblur=\"autosave('KP_24f');\"  oninput=\"KP();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"KP_29f\" id=\"KP_29f\" value=\""+KP_29f+"\"  onblur=\"autosave('KP_29f');\"  oninput=\"KP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"KP_34f\" id=\"KP_34f\" value=\""+KP_34f+"\"  onblur=\"autosave('KP_34f');\"  oninput=\"KP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"KP_39f\" id=\"KP_39f\" value=\""+KP_39f+"\"  onblur=\"autosave('KP_39f');\"  oninput=\"KP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"KP_49f\" id=\"KP_49f\" value=\""+KP_49f+"\"  onblur=\"autosave('KP_49f');\"  oninput=\"KP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"KP_50f\" id=\"KP_50f\" value=\""+KP_50f+"\"  onblur=\"autosave('KP_50f');\"  oninput=\"KP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"KP_GT\" id=\"KP_GT\" value=\""+KP_GT+"\" tabindex=\"-1\"  onblur=\"autosave('KP_GT');\"  oninput=\"KP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + "";  
    
    
    
    
    NP = ""
        + "<tr border=\"1px;\">"
        + "<td class=\"title\">3</td>"
        + "<td class=\"title indicator\">Newly tested positive at ANC</td>"
         
        + "<td><input type=\"text\" name=\"NP_1f\" id=\"NP_1f\" value=\""+NP_1f+"\"  tabindex=\"-1\"  onblur=\"autosave('NP_1f');\"  oninput=\"NP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"NP_4f\" id=\"NP_4f\" value=\""+NP_4f+"\"  tabindex=\"-1\"  onblur=\"autosave('NP_4f');\"  oninput=\"NP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"NP_9f\" id=\"NP_9f\" value=\""+NP_9f+"\"  tabindex=\"-1\"  onblur=\"autosave('NP_9f');\"  oninput=\"NP();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"NP_14f\" id=\"NP_14f\" value=\""+NP_14f+"\" onblur=\"autosave('NP_14f');\"  oninput=\"NP();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NP_19f\" id=\"NP_19f\" value=\""+NP_19f+"\"  onblur=\"autosave('NP_19f');\"  oninput=\"NP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NP_24f\" id=\"NP_24f\" value=\""+NP_24f+"\"  onblur=\"autosave('NP_24f');\"  oninput=\"NP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NP_29f\" id=\"NP_29f\" value=\""+NP_29f+"\"  onblur=\"autosave('NP_29f');\"  oninput=\"NP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NP_34f\" id=\"NP_34f\" value=\""+NP_34f+"\"  onblur=\"autosave('NP_34f');\"  oninput=\"NP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NP_39f\" id=\"NP_39f\" value=\""+NP_39f+"\"  onblur=\"autosave('NP_39f');\"  oninput=\"NP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NP_49f\" id=\"NP_49f\" value=\""+NP_49f+"\"  onblur=\"autosave('NP_49f');\"  oninput=\"NP();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NP_50f\" id=\"NP_50f\" value=\""+NP_50f+"\"  onblur=\"autosave('NP_50f');\"  oninput=\"NP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"NP_GT\" id=\"NP_GT\" value=\""+NP_GT+"\" tabindex=\"-1\"  onblur=\"autosave('NP_GT');\"  oninput=\"NP(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>";
            
    newART = ""
         + "<tr border=\"1px;\">"
        + "<td class=\"title\">4</td>"
        + "<td class=\"title indicator\">PMTCT new on ART</td>"
         
        + "<td><input type=\"text\" name=\"newART_1f\" id=\"newART_1f\" value=\""+newART_1f+"\"  tabindex=\"-1\"  onblur=\"autosave('newART_1f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"newART_4f\" id=\"newART_4f\" value=\""+newART_4f+"\"  tabindex=\"-1\"  onblur=\"autosave('newART_4f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"newART_9f\" id=\"newART_9f\" value=\""+newART_9f+"\"  tabindex=\"-1\"  onblur=\"autosave('newART_9f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"newART_14f\" id=\"newART_14f\" value=\""+newART_14f+"\" onblur=\"autosave('newART_14f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newART_19f\" id=\"newART_19f\" value=\""+newART_19f+"\"  onblur=\"autosave('newART_19f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newART_24f\" id=\"newART_24f\" value=\""+newART_24f+"\"  onblur=\"autosave('newART_24f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newART_29f\" id=\"newART_29f\" value=\""+newART_29f+"\"  onblur=\"autosave('newART_29f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newART_34f\" id=\"newART_34f\" value=\""+newART_34f+"\"  onblur=\"autosave('newART_34f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newART_39f\" id=\"newART_39f\" value=\""+newART_39f+"\"  onblur=\"autosave('newART_39f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newART_49f\" id=\"newART_49f\" value=\""+newART_49f+"\"  onblur=\"autosave('newART_49f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newART_50f\" id=\"newART_50f\" value=\""+newART_50f+"\"  onblur=\"autosave('newART_50f');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newART_GT\" id=\"newART_GT\" value=\""+newART_GT+"\" tabindex=\"-1\"  onblur=\"autosave('newART_GT');\"  oninput=\"newART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + "";
    
    alreadyonART = ""
         + "<tr border=\"1px;\">"
        + "<td class=\"title\">5</td>"
        + "<td class=\"title indicator\">PMTCT already on ART</td>"
         
        + "<td><input type=\"text\" name=\"alreadyonART_1f\" id=\"alreadyonART_1f\" value=\""+alreadyonART_1f+"\"  tabindex=\"-1\"  onblur=\"autosave('alreadyonART_1f');\"  oninput=\"alreadyonART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"alreadyonART_4f\" id=\"alreadyonART_4f\" value=\""+alreadyonART_4f+"\"  tabindex=\"-1\"  onblur=\"autosave('alreadyonART_4f');\"  oninput=\"alreadyonART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"alreadyonART_9f\" id=\"alreadyonART_9f\" value=\""+alreadyonART_9f+"\"  tabindex=\"-1\"  onblur=\"autosave('alreadyonART_9f');\"  oninput=\"alreadyonART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\" ></td>"
        + "<td><input type=\"text\" name=\"alreadyonART_14f\" id=\"alreadyonART_14f\" value=\""+alreadyonART_14f+"\" onblur=\"autosave('alreadyonART_14f');\"  oninput=\"alreadyonART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"alreadyonART_19f\" id=\"alreadyonART_19f\" value=\""+alreadyonART_19f+"\"  onblur=\"autosave('alreadyonART_19f');\"  oninput=\"alreadyonART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"alreadyonART_24f\" id=\"alreadyonART_24f\" value=\""+alreadyonART_24f+"\"  onblur=\"autosave('alreadyonART_24f');\"  oninput=\"alreadyonART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"alreadyonART_29f\" id=\"alreadyonART_29f\" value=\""+alreadyonART_29f+"\"  onblur=\"autosave('alreadyonART_29f');\"  oninput=\"alreadyonART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"alreadyonART_34f\" id=\"alreadyonART_34f\" value=\""+alreadyonART_34f+"\"  onblur=\"autosave('alreadyonART_34f');\"  oninput=\"alreadyonART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"alreadyonART_39f\" id=\"alreadyonART_39f\" value=\""+alreadyonART_39f+"\"  onblur=\"autosave('alreadyonART_39f');\"  oninput=\"alreadyonART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"alreadyonART_49f\" id=\"alreadyonART_49f\" value=\""+alreadyonART_49f+"\"  onblur=\"autosave('alreadyonART_49f');\"  oninput=\"alreadyonART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"alreadyonART_50f\" id=\"alreadyonART_50f\" value=\""+alreadyonART_50f+"\"  onblur=\"autosave('alreadyonART_50f');\"  oninput=\"alreadyonART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"alreadyonART_GT\" id=\"alreadyonART_GT\" value=\""+alreadyonART_GT+"\" tabindex=\"-1\"  onblur=\"autosave('alreadyonART_GT');\"  oninput=\"alreadyonART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
        + " "
        + "</table>";  
    
    

    
     //end of code  
     
     
      if (session.getAttribute("forms_holder") != null) {
     if (session.getAttribute("forms_holder").toString().contains("PMTCT")) {
        output=enterdby+Header+Testing+KP+NP+newART+alreadyonART;
        if(isLocked.equals("1")){
            output += "<div class='form-actions' style=\"text-align:center;\"><input type='submit' disabled class='btn red' value='Form Locked' name='validate' id='validate'/></div><span id='formstatus' style='display:none;'>" + validity + " </span><span id='rc' style='display:none;'>"+label+" </span><span id='ufs' style='display:none;'>"+unvalidatedLink+"</span>";
        }
        else{
            output += "<div class='form-actions' style=\"text-align:center;\"><input type='submit' class='btn blue' value='Save PMTCT Data' name='validate' id='validate'/></div><span id='formstatus' style='display:none;'>" + validity + " </span><span id='rc' style='display:none;'>"+label+" </span><span id='ufs' style='display:none;'>"+unvalidatedLink+"</span>";
        }
        
        } else {
                    output = "<tr ><td colspan='4'><font color=\"red\" size=\"6px;\" style=\"margin-left: 0%;\"><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support PMTCT Module.</font></td></tr>";
                }
            }
        if (session.getAttribute("facilityid") != null) {
            }
        else {
                output = "<tr ><td colspan='4'><font color=\"red\" size=\"6px;\" style=\"margin-left: 0%;\"><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support PMTCT Module.</font></td></tr>";

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
        Logger.getLogger(loadPMTCT.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadPMTCT.class.getName()).log(Level.SEVERE, null, ex);
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
