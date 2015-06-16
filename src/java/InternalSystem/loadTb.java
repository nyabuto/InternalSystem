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
 * @author Geofrey Nyabuto
 */
public class loadTb extends HttpServlet {

    HttpSession session=null;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
             PrintWriter out = response.getWriter();
    response.setContentType("text/html;charset=UTF-8");
    session=request.getSession();
    
    
    dbConn conn=new dbConn();
    //get the existing data for the month, year and facility that is already on session
    String isValidated="";
    String validity="";
    String month="";      
    String year="";      
    String facil="";
    String invalidTBTXT="";
     if(session.getAttribute("forms_holder")!=null && (session.getAttribute("forms_holder").toString().contains(",TB,"))  ){ 
    if(session.getAttribute("year")!=null){        
   year=session.getAttribute("year").toString();
    }
      if(session.getAttribute("monthid")!=null){        
   month=session.getAttribute("monthid").toString();
    }
   
        if(session.getAttribute("facilityid")!=null){        
   facil=session.getAttribute("facilityid").toString();
    }
    String tableid=year+"_"+month+"_"+facil;
        
        
  
    
String C31D="";
String TB_STATN="";
String TB_STATD="";
String TB_STATP="";
String TB_IPTN="";
String TB_IPTD="";
String TB_IPTP="";
String TB_IPT1="";
String TB_IPT4="";
String TB_IPT9="";
String TB_IPT14="";
String TB_IPT19="";
String TB_IPT20="";
String TB_IPTM="";
String TB_IPTF="";
String TB_SCREENN="";
String TB_SCREEND="";
String TB_SCREENP="";
String TB_OUTCOME="";
String CARPCTHTMPR="";
String CARPCTHTFPR="";
String CARPCTHTTPR="";

String enterdby="";




 String subcountyid="";
    if(session.getAttribute("subcountyid")!=null){        
   subcountyid=session.getAttribute("subcountyid").toString();
    }    
        int validTB=0; 
        int invalidTB=0; 
int TotalTB=0; 
int expectedTB=0; 
String getExpectedForms="SELECT SUM(TB)FROM subpartnera WHERE subpartnera.DistrictID='"+subcountyid+"'" ;
   conn.rs1=conn.st1.executeQuery(getExpectedForms);
   if(conn.rs1.next()==true){
//       System.out.println("pmtct : "+conn.rs1.getString(1)+"  care : "+conn.rs1.getInt(2)+" pep : "+conn.rs1.getInt(3));
           expectedTB=conn.rs1.getInt(1);
          
   }
     
        String getEntered="SELECT tb.isValidated,SUM(subpartnera.TB)"
            + " FROM subpartnera JOIN tb ON subpartnera.SubPartnerID=tb.SubPartnerID WHERE "
            + "tb.Mois='"+month+"' AND tb.Annee='"+year+"' AND subpartnera.DistrictID='"+subcountyid+"' GROUP BY tb.isValidated";
    conn.rs1=conn.st1.executeQuery(getEntered);
    while(conn.rs1.next()){
        System.out.println("isvalidated : "+conn.rs1.getInt(1)+"  num : "+conn.rs1.getInt(2));
   if(conn.rs1.getInt(1)==1){
    validTB=conn.rs1.getInt(2);
   
   }
   if(conn.rs1.getInt(1)==0){
      invalidTB=conn.rs1.getInt(2);
 
      
   }
    }
    TotalTB=validTB+invalidTB;
 
 
  invalidTBTXT=" Unvalidated Form(s) : 0";
 
    if(invalidTB>0){
   invalidTBTXT="<button type=\"button\" class=\"btn btn-primary btn-lg\" data-toggle=\"modal\" style=\"width:auto; height:auto;\" data-target=\"#unvalidatedModal\"> Unvalidated Form(s) : <span class=\"badge badge-important\">"+invalidTB+"</span></button>";
    }

     String getexistingdata="select * from tb where ID='"+tableid+"'"; 
    conn.rs=conn.st.executeQuery(getexistingdata);
    while(conn.rs.next()){
    
        
        
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
        
        
        
        
        
        //now load the column values here
       
C31D=conn.rs.getString("C31D");
if(C31D==null){C31D=""; }

TB_STATN=conn.rs.getString("TB_STATN");
if(TB_STATN==null){TB_STATN=""; }

TB_STATD=conn.rs.getString("TB_STATD");
if(TB_STATD==null){TB_STATD=""; }

TB_STATP=conn.rs.getString("TB_STATP");
if(TB_STATP==null){TB_STATP=""; }

TB_IPTN=conn.rs.getString("TB_IPTN");
if(TB_IPTN==null){TB_IPTN=""; }

TB_IPTD=conn.rs.getString("TB_IPTD");
if(TB_IPTD==null){TB_IPTD=""; }

TB_IPTP=conn.rs.getString("TB_IPTP");
if(TB_IPTP==null){TB_IPTP=""; }

TB_IPT1=conn.rs.getString("TB_IPT1");
if(TB_IPT1==null){TB_IPT1=""; }

TB_IPT4=conn.rs.getString("TB_IPT4");
if(TB_IPT4==null){TB_IPT4=""; }

TB_IPT9=conn.rs.getString("TB_IPT9");
if(TB_IPT9==null){TB_IPT9=""; }

TB_IPT14=conn.rs.getString("TB_IPT14");
if(TB_IPT14==null){TB_IPT14=""; }

TB_IPT19=conn.rs.getString("TB_IPT19");
if(TB_IPT19==null){TB_IPT19=""; }

TB_IPT20=conn.rs.getString("TB_IPT20");
if(TB_IPT20==null){TB_IPT20=""; }

TB_IPTM=conn.rs.getString("TB_IPTM");
if(TB_IPTM==null){TB_IPTM=""; }

TB_IPTF=conn.rs.getString("TB_IPTF");
if(TB_IPTF==null){TB_IPTF=""; }

TB_SCREENN=conn.rs.getString("TB_SCREENN");
if(TB_SCREENN==null){TB_SCREENN=""; }
        
TB_SCREEND=conn.rs.getString("TB_SCREEND");
if(TB_SCREEND==null){TB_SCREEND=""; }
        
TB_SCREENP=conn.rs.getString("TB_SCREENP");
if(TB_SCREENP==null){TB_SCREENP=""; }
        
TB_OUTCOME=conn.rs.getString("TB_OUTCOME");
if(TB_OUTCOME==null){TB_OUTCOME=""; }

CARPCTHTMPR=conn.rs.getString("CARPCTHTMPR");
if(CARPCTHTMPR==null){CARPCTHTMPR=""; }
        
CARPCTHTMPR=conn.rs.getString("CARPCTHTMPR");
if(CARPCTHTMPR==null){CARPCTHTMPR=""; }
        
CARPCTHTFPR=conn.rs.getString("CARPCTHTFPR");
if(CARPCTHTFPR==null){CARPCTHTFPR=""; }
        
CARPCTHTTPR=conn.rs.getString("CARPCTHTTPR");
if(CARPCTHTTPR==null){CARPCTHTTPR=""; }
 isValidated=conn.rs.getString("isValidated");

        
          
    }
   //       System.out.println("Validity checker : "+isValidated);
          System.out.println("Validity checker : "+isValidated);
      if(isValidated.equals("0")){
  validity="<font color=\"white\" ><b style=\"font-size:16px;font-family: cambria;\">Form Not Validated.<img style=\"margin-left:10px;\" src=\"images/notValidated.jpg\" width=\"20px\" height=\"20px\"></b></font>"  ;
}
      else if(isValidated.equals("1")){
   validity="<font color=\"white\"><b style=\"font-size:16px;font-family: cambria;\">Form Validated.<img style=\"margin-left:10px;\" src=\"images/validated.jpg\" width=\"20px\" height=\"20px\"></b></font>"  ;  
}
      else{
     
        validity="<font color=\"white\" style=\"font-size:16px;font-family: cambria;\"><b>New Entry</b></font>"  ;          
              }
      
//     System.out.println("read from session : "+session.getAttribute("isValidated").toString());
 
             String createdtable="<p hidden=\"true\" id=\"recordcounter\"><b style=\"color:yellow;font-size:16px;font-family: cambria; margin-right:200px;\">  Record Counter: "+TotalTB+" out of "+expectedTB+" &nbsp; Validated Form(s) : "+validTB+"  &nbsp; "+invalidTBTXT+"</b> </p><p hidden=\"true\" id=\"checkValidity\"><b>"+validity+"</b></p>" ; 
             
     createdtable+=enterdby+"<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\"> TB/HIV</b></legend><table id=\"tbtable\" cellpadding=\"2px\"  style=\"border-color: #e5e5e5;margin-bottom: 3px;  \"><tr class='form-actions'><th colspan='4'><b> Case Sub Area 3: Clinical/ Preventive Services- Additional TB/HIV </b></th></tr>";
    
    createdtable+="<tr><td rowspan=\"3\" ><b> TB_STAT </b></td>"
            + "<td rowspan=\"3\">Proportion of registered new and relapse TB cases with document HIV </td>"
            + "<td colspan=\"2\" >Number of registered new and relapse TB cases with documented HIV status</td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('TB_STATN');TB_STAT();\" value='"+TB_STATN+"' name='TB_STATN' id='TB_STATN'></td></tr>";
    
    createdtable+="<tr>"
            + "<td  colspan=\"2\">Total Registered and new relapse TB cases</td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('TB_STATD');TB_STAT();\" value='"+TB_STATD+"' name='TB_STATD' id='TB_STATD' ></td></tr>";
    
    createdtable+="<tr>"
            + "<td  colspan=\"2\">Proportion</td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" readonly tabindex='-1' onblur=\"autosave('TB_STATP');TB_STAT();\" value='"+TB_STATP+"' name='TB_STATP' id='TB_STATP' ></td></tr>";
    
    
    createdtable+="<tr><td rowspan=\"3\" ><b> TB_IPT </b></td>"
            + "<td rowspan=\"3\">Percentage of PLHIV newly enrolled in HIV clinical care who start isoniazid preventative therapy(IPT) </td>"
            + "<td  colspan=\"2\">PLHIV newly enrolled in HIV clinical care who start isoniazid preventative therapy</td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('TB_IPTN');TB_IPT();\" value='"+TB_IPTN+"' name='TB_IPTN' id='TB_IPTN' ></td></tr>";
    
    createdtable+="<tr>"
            + "<td  colspan=\"2\">Total Registered and new relapse TB cases</td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('TB_IPTD');TB_IPT();\" value='"+TB_IPTD+"' name='TB_IPTD' id='TB_IPTD' ></td></tr>";
    
    createdtable+="<tr>"
            + "<td  colspan=\"2\">Proportion</td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" readonly tabindex='-1' onblur=\"autosave('TB_IPTP');TB_IPT();\" value='"+TB_IPTP+"' name='TB_IPTP' id='TB_IPTP' ></td></tr>";
    
    createdtable+="<tr><td  colspan='2' rowspan='8'></td>"
            + "<td  colspan=\"2\"> <1 </td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('TB_IPT1');\" value='"+TB_IPT1+"' name='TB_IPT1' id='TB_IPT1' ></td></tr>";
    
    createdtable+="<tr>"
            + "<td  colspan=\"2\"> 1-4 </td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('TB_IPT4');\" value='"+TB_IPT4+"' name='TB_IPT4' id='TB_IPT4' ></td></tr>";
    
    createdtable+="<tr></td>"
            + "<td  colspan=\"2\"> 5-9 </td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('TB_IPT9');\" value='"+TB_IPT9+"' name='TB_IPT9' id='TB_IPT9' ></td></tr>";
    
    createdtable+="<tr></td>"
            + "<td colspan=\"2\"> 10-14 </td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('TB_IPT14');\" value='"+TB_IPT14+"' name='TB_IPT14' id='TB_IPT14' ></td></tr>";
    
    
    createdtable+="<tr></td>"
            + "<td  colspan=\"2\"> 15-19 </td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('TB_IPT19');\" value='"+TB_IPT19+"' name='TB_IPT19' id='TB_IPT19' ></td></tr>";
    
    createdtable+="<tr></td>"
            + "<td  colspan=\"2\"> 20+ </td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('TB_IPT20');\" value='"+TB_IPT20+"' name='TB_IPT20' id='TB_IPT20' ></td></tr>";
    
    createdtable+="<tr></td>"
            + "<td  colspan=\"2\"> Total Male </td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('TB_IPTM');\" value='"+TB_IPTM+"' name='TB_IPTM' id='TB_IPTM' ></td></tr>";
    
    createdtable+="<tr>"
            + "<td  colspan=\"2\"> Total Female </td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('TB_IPTF');\" value='"+TB_IPTF+"' name='TB_IPTF' id='TB_IPTF' ></td></tr>";
    
    createdtable+="<tr><td rowspan=\"3\" ><b> TB_SCREEN </b></td>"
            + "<td rowspan=\"3\"> Proportion of PLHIV in HIV Clinical care who were screend for TB symptoms at the last clinical visit</td>"
            + "<td colspan=\"2\">Number of PLHIV in clinical care who were screened for TB </td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('TB_SCREENN');TB_SCREEN();\" value='"+TB_SCREENN+"' name='TB_SCREENN' id='TB_SCREENN' ></td></tr>";
    
    createdtable+="<tr>"
            + "<td colspan=\"2\">Total Registered and new relapse TB cases</td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('TB_SCREEND');TB_SCREEN();\" value='"+TB_SCREEND+"' name='TB_SCREEND' id='TB_SCREEND' ></td></tr>";
    
    createdtable+="<tr>"
            + "<td colspan=\"2\">Proportion</td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" readonly tabindex='-1' onblur=\"autosave('TB_SCREENP');TB_SCREEN();\" value='"+TB_SCREENP+"' name='TB_SCREENP' id='TB_SCREENP' ></td></tr>";
    
      createdtable+="<tr><td ><b> TB_OUTCOME </b></td>"
            + "<td colspan=\"3\"> Outcome of TB treatment among registered new and relapse TB cases who are HIV positive</td>"
         
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('TB_OUTCOME');\" value='"+TB_OUTCOME+"' name='TB_OUTCOME' id='TB_OUTCOME' ></td></tr>";
    
      createdtable+="<tr class='form-actions'><TD  colspan=\"1\"></TD><td>MALE</td>"
              + "<td>FEMALE</td>"
              + "<td>TOTAL</td></tr><tr>"
            + "<td colspan=\"2\"> 7.4 Number of reigistered TB patients who recieved HIV counselling, testing, and their test results at a USG supported TB service</td>"
         
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('CARPCTHTMPR');indic74();\" value='"+CARPCTHTMPR+"' name='CARPCTHTMPR' id='CARPCTHTMPR' ></td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" onblur=\"autosave('CARPCTHTFPR');indic74();\" value='"+CARPCTHTFPR+"' name='CARPCTHTFPR' id='CARPCTHTFPR' ></td>"
            + "<td><input type='text' onkeypress=\"return numbers(event,this);\" tabindex='-1' readonly onblur=\"autosave('CARPCTHTTPR');indic74();\" value='"+CARPCTHTTPR+"' name='CARPCTHTTPR' id='CARPCTHTTPR' ></td>"
              + "</tr>   </table>   </fieldset>";
    
    createdtable+=" <div class=\"form-actions\">\n" +
"                              <button type=\"submit\" class=\"btn blue\">Run Validation</button>\n" +
"<!--                              <button type=\"button\" class=\"btn\">Cancel</button>-->\n" +
"                           </div>";
      System.out.println(createdtable);
      
     String unvalidatedLink="";int counter=0;
     if(invalidTB>0 ){
     String getUnvalidated="SELECT tb.SubPartnerID,subpartnera.SubPartnerNom FROM tb JOIN subpartnera ON tb.SubPartnerID=subpartnera.SubPartnerID WHERE subpartnera.DistrictID='"+subcountyid+"' AND tb.Mois='"+month+"' AND tb.Annee='"+year+"' AND tb.isValidated='0'";
     conn.rs=conn.st.executeQuery(getUnvalidated);
     while(conn.rs.next()){
         counter++;
//     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=Form731.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=loadTb.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
     }
    }
     
    createdtable+="<p hidden=\"true\" id=\"invalidatedData\">"+unvalidatedLink+"</p>"; 
     
  
        out.println(createdtable);
        
        
         if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
     if(conn.st2!=null){conn.st2.close();}
     if(conn.st3!=null){conn.st3.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.rs3!=null){conn.rs3.close();}
     if(conn.conn!=null){conn.conn.close();}
   
     }else{
       out.println("<font color=\"red\" size=\"6px;\" ><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support module TB.</font>");
     }
}       catch (SQLException ex) {
            Logger.getLogger(loadTb.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
