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
public class loadKmmp extends HttpServlet {

    HttpSession session=null;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    session=request.getSession();
    
    
    dbConn conn=new dbConn();
    //get the existing data for the month, year and facility that is already on session
    
    String month="";      
    String year="";      
    String facil="";
    String enterdby="";
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
        
        
    String getexistingdata="select * from kmmp where tableid='"+tableid+"'";
    String formtype="<b><font color='#4b8df8'>New Entry</font></b>";
String KMMP1="";
String KMMP2="";
String KMMP3a="";
String KMMP3b="";
String KMMP3c="";
String KMMP4="";
String KMMP5a="";
String KMMP5b="";
String KMMP5c="";
String HV0205="";
String HV0206="";

int kmmpdone=0;
int kmmpundone=0;
int kmmpvalid=0;
int facilssupporting=0;
String distid="";

if(session.getAttribute("subcountyid")!=null){
distid=session.getAttribute("subcountyid").toString();
}


String kmmpcounter="SELECT 1 FROM kmmp join subpartnera on kmmp.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and (KMMP1 is not null ||KMMP1!='')  ";
 conn.rs1 = conn.st1.executeQuery(kmmpcounter);
 while(conn.rs1.next()){
 kmmpdone++;
  }
            System.out.println(kmmpcounter);
 
 String kmmpcounter1="SELECT subpartnera.SubPartnerID FROM kmmp join subpartnera on kmmp.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and isValidated='0' ";
 conn.rs1 = conn.st1.executeQuery(kmmpcounter1);
 while(conn.rs1.next()){
 kmmpundone++;
  }
 String kmmpvalidatedcounter1="SELECT 1 FROM kmmp join subpartnera on kmmp.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and isValidated='0' ";
 conn.rs1 = conn.st1.executeQuery(kmmpvalidatedcounter1);
 while(conn.rs1.next()){
 kmmpvalid++;
  }
 
 String countpmctfacility="Select * from subpartnera where KMMP ='1' and  DistrictID='"+distid+"'";
// String countfacility="Select * from subpartnera where FP='1' || PMTCT ='1' || Maternity='1' || HTC='1' ";
 conn.rs1 = conn.st1.executeQuery(countpmctfacility);
 while(conn.rs1.next()){
 facilssupporting++;
 }
 
 
 
 String validated="&nbsp &nbsp Validated Form(s): <b>"+kmmpvalid+" </b>";
 String unvalidated="&nbsp &nbsp Unvalidated Form (s) <font color='black'><b>"+kmmpundone+"</b></font>";
 
 
  String unvalidatedLink="";int counter=0;
     if(kmmpundone>0){
     String getUnvalidated="SELECT kmmp.SubPartnerID,subpartnera.SubPartnerNom FROM kmmp JOIN subpartnera ON kmmp.SubPartnerID=subpartnera.SubPartnerID WHERE subpartnera.DistrictID='"+distid+"' AND kmmp.Mois='"+month+"' AND kmmp.Annee='"+year+"' AND kmmp.isValidated='0'";
     conn.rs=conn.st.executeQuery(getUnvalidated);
     while(conn.rs.next()){
         counter++;
//     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=Form731.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=loadKmmp.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
                    }
    }
     
   if(counter>0){
    unvalidated="<button class='btn btn-primary btn-lg' data-target='#unvalidatedModal' style='width:auto; height:auto;' data-toggle='modal' type='button'> Unvalidated Form (s) <span class='badge badge-important'><b>"+kmmpundone+"</b></span></button>";
 
   }  
 
 
 
 
 String label="Record counter <font color='white'><b>"+kmmpdone+"<b></font>  out of <b>"+facilssupporting+"</b>"+validated+unvalidated;
     
    conn.rs=conn.st.executeQuery(getexistingdata);
    while(conn.rs.next()){
        
        
        
        //get the name of the person who entered the form 
        
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
        
        
        
    
        if(conn.rs.getString("isValidated").equals("1")){
        formtype="<font color='green'><b>Form Validated.<img width='20px' height='20px' src='images/validated.jpg' style='margin-left:10px;'></b></font>";
        }
        else
            
        {
        formtype="<font color='red'><b>Form Not Validated.<img width='20px' height='20px' src='images/notValidated.jpg' style='margin-left:10px;'></b></font>";
        }
		
        
        //now load the column values here
       
KMMP1=conn.rs.getString("KMMP1");
if(KMMP1==null){KMMP1=""; }

KMMP2=conn.rs.getString("KMMP2");
if(KMMP2==null){KMMP2=""; }

KMMP3a=conn.rs.getString("KMMP3a");
if(KMMP3a==null){KMMP3a=""; }

KMMP3b=conn.rs.getString("KMMP3b");
if(KMMP3b==null){KMMP3b=""; }

KMMP3c=conn.rs.getString("KMMP3c");
if(KMMP3c==null){KMMP3c=""; }

KMMP4=conn.rs.getString("KMMP4");
if(KMMP4==null){KMMP4=""; }

KMMP5a=conn.rs.getString("KMMP5a");
if(KMMP5a==null){KMMP5a=""; }

KMMP5b=conn.rs.getString("KMMP5b");
if(KMMP5b==null){KMMP5b=""; }

KMMP5c=conn.rs.getString("KMMP5c");
if(KMMP5c==null){KMMP5c=""; }

HV0205=conn.rs.getString("HV0205");
if(HV0205==null){HV0205=""; }

HV0206=conn.rs.getString("HV0206");
if(HV0206==null){HV0206=""; }
        
    }
    
    String createdtable="";
    
    
     if(session.getAttribute("forms_holder")!=null){ if(session.getAttribute("forms_holder").toString().contains("KMMP")){
    
    
    createdtable+=enterdby+"<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\"> KMMP OUTPUT DATA</b></legend><table  cellpadding=\"2px\" border=\"0\" style=\"border-color: #e5e5e5;margin-bottom: 3px;\"><tr class='form-actions'><th colspan='2'><b></b></th><th>Total</th></tr><tr><td><b> 1 </b></td><td colspan='2'>No of New HIV positive clients enrolled in KMMP Services (ANC and PN) </td><td><input type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('KMMP1','"+tableid+"');\" value='"+KMMP1+"' name='KMMP1' id='KMMP1' autofocus></td></tr>";
    
    createdtable+="<tr><td><b> 2 </b></td><td colspan='2'>No of New HIV negative clients enrolled in KMMP Services (ANC Only) </td><td><input type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('KMMP2','"+tableid+"');\" value='"+KMMP2+"' name='KMMP1' id='KMMP2'></td></tr>";
    
    createdtable+="<tr><td rowspan='3'><b> 3 </b></td><td colspan='2'> a) No. of HIV-positive pregnant women enrolled in KMMP Services </td><td><input type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"showpercent();autosave('KMMP3a','"+tableid+"');\" value='"+KMMP3a+"' name='KMMP3a' id='KMMP3a'></td></tr>";
    
    createdtable+="<tr><td colspan='2'>  b) Total number of HIV-positive pregnant women in facility (New positive and Known Positive-MOH731) </td><td><input type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"showpercent();autosave('KMMP3b','"+tableid+"');\" value='"+KMMP3b+"' name='KMMP3b' id='KMMP3b'></td></tr>";
    
    createdtable+="<tr><td colspan='2'><b> Percentage of new IV-positive pregnant women enrolled in KMMP Services </b></td><td><input type='text'  value='"+KMMP3c+"' tabindex='-1' readonly name='KMMP3c' id='KMMP3c'></td></tr>";
      
    createdtable+="<tr><td ><b> 4 </b></td><td colspan='2'>No. of KMMP support group sessions held </td><td><input type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('KMMP4','"+tableid+"');\" value='"+KMMP4+"' name='KMMP4' id='KMMP4'></td></tr>";
     
    createdtable+="<tr><td rowspan='3'><b> 5 </b></td><td rowspan='3'>Defaulter Tracing </td><td>New Defaulted Clients </td><td><input type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('KMMP5a','"+tableid+"');\" value='"+KMMP5a+"' name='KMMP5a' id='KMMP5a'></td></tr>";
     
    createdtable+="<tr><td>Clients Reached</td><td><input type='text' onclick=\"this.select();\" onblur=\"autosave('KMMP5b','"+tableid+"');\" value='"+KMMP5b+"' onkeypress=\"return numbers(event,this);\" name='KMMP5b' id='KMMP5b'></td></tr>";
      
      createdtable+="<tr><td>Successfully Resolved</td><td><input type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('KMMP5c','"+tableid+"');\" value='"+KMMP5c+"' name='KMMP5c' id='KMMP5c'></td></tr>";
   
      createdtable+="<tr><td></td><td colspan='2'>MOH 731 HV02-05 Known positive status (at entry into ANC) :</td><td><input type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('HV0205','"+tableid+"');\" value='"+HV0205+"' name='HV0205' id='HV0205'></td></tr>";
   
      createdtable+="<tr><td></td><td colspan='2'>MOH 731 HV02-06 Antenatal:</td><td><input type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('HV0206','"+tableid+"');\" value='"+HV0206+"' name='HV0206' id='HV0206'></td></tr></table></fieldset> <div class='form-actions'><input type='submit' class='btn blue' value='Run Validation' name='validate' id='validate'/></div><span id='formstatus' style='display:none;'>"+formtype+" </span><span id='rc' style='display:none;'>"+label+" </span> <p id='ufs' style='display:none;'>"+unvalidatedLink+"</p>";
   }
    
    else {
    createdtable="<tr ><td colspan='4'><font color=\"red\" size=\"6px;\" style=\"margin-left: 0%;\"><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support  KMMP module.</font></td></tr>";
    }
    
    }
    
      if(session.getAttribute("facilityid")!=null){ } else {
   createdtable="<tr ><td colspan='4'><font color=\"red\" size=\"6px;\" style=\"margin-left: 0%;\"><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support  KMMP module.</font></td></tr>";
    
  }
     
      System.out.println(createdtable);
      
    PrintWriter out = response.getWriter();
    try {
        /* TODO output your page here. You may use following sample code. */
  
        out.println(createdtable);
    } finally {
        
        if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}
         if(conn.rs1!=null){ conn.rs1.close();}
         if(conn.rs2!=null){ conn.rs2.close();}
         if(conn.st!=null){ conn.st.close();}
         if(conn.st2!=null){ conn.st2.close();}
        
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(loadKmmp.class.getName()).log(Level.SEVERE, null, ex);
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
