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
public class loadNutrition extends HttpServlet {

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
      
    
    
    String getexistingdata="select tableid,SubPartnerID,Annee,Mois,MCHCCNtrTMC , MCHCCNtrTFC ,MCHCCNtrTTC,MCHCCNtrTM,MCHCCNtrTF, MCHCCNtrTT,MCHNtrnCHWTrain,MCHNutChRch,MCHNtrnWasted,MCHNtrnUnderweight,MCHChild5D,MCHNtrnHealthFacility,MCHVaccVitA,MCHNtrnFoodOVC,MCHNtrnFoodPLHIV,MCHNtrnFood,C51DCM,C51DCF,C51DC,C51DAM,C51DAF,C51DA,C51DP,C51DMT,C51DFT,C51DT,user_id,timestamp,isValidated,updatedOn,updatedBy from nutrition where tableid='"+tableid+"'";
    
String formtype="<b>New Entry</b>";
    
String MCHCCNtrTM="";
String MCHCCNtrTF="";
String MCHCCNtrTT="";

String MCHCCNtrTMC="0";
String MCHCCNtrTFC="0";
String MCHCCNtrTTC="0";


String MCHCCNtrTMCH="0";
String MCHCCNtrTFCH="0";
String MCHCCNtrTTCH="0";

String MCHNtrnCHWTrain="";
String MCHNutChRch="";
String MCHNtrnWasted="";
String MCHNtrnUnderweight="";
String MCHChild5D="";
String MCHNtrnHealthFacility="";
String MCHVaccVitA="";
String MCHNtrnFoodOVC="";
String MCHNtrnFoodPLHIV="";
String MCHNtrnFood="";
String C51DCM="";
String C51DCF="";
String C51DC="";
String C51DAM="";
String C51DAF="";
String C51DA="";
String C51DP="";
String C51DMT="";
String C51DFT="";
String C51DT="";



int kmmpdone=0;
int kmmpundone=0;
int facilssupporting=0;
String distid="";

if(session.getAttribute("subcountyid")!=null){
distid=session.getAttribute("subcountyid").toString();
}


String kmmpcounter="SELECT 1 FROM nutrition join subpartnera on nutrition.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and (C51DC is not null ||C51DC!='')  ";
 conn.rs1 = conn.st1.executeQuery(kmmpcounter);
 while(conn.rs1.next()){
 kmmpdone++;
  }
            System.out.println(kmmpcounter);
 
 String kmmpcounter1="SELECT 1 FROM nutrition join subpartnera on nutrition.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and (C51DC is not null ||C51DC!='') and isValidated='0' ";
 conn.rs1 = conn.st1.executeQuery(kmmpcounter1);
 while(conn.rs1.next()){
 kmmpundone++;
  }
 String countpmctfacility="Select * from subpartnera where Nutrition ='1' and  DistrictID='"+distid+"'";
// String countfacility="Select * from subpartnera where FP='1' || PMTCT ='1' || Maternity='1' || HTC='1' ";
 conn.rs1 = conn.st1.executeQuery(countpmctfacility);
 while(conn.rs1.next()){
 facilssupporting++;
 }
 
    String label="Record counter <font color='green'><b>"+kmmpdone+"<b></font>  out of <b>"+facilssupporting+"</b> &nbsp &nbsp Unvalidated Forms are <font color='black'><b>"+kmmpundone+"</b></font>";
   



String yearmonth="";
int tempyear=Integer.parseInt(year);
String tempmonth=month;
if(Integer.parseInt(month)<10){ tempmonth="0"+month; }
if(Integer.parseInt(month)>=10){tempyear=Integer.parseInt(year)-1;}
yearmonth=tempyear+tempmonth;

//____We need to get the cumulatives up to the maximum reported yearmonth   as long as its less than the current month and year
//eg if its 2016 jan, get the max reported yearmonth.from 2014 12 backwards. Also ensure that the value of accumulatives for that max month is not Zero
//this is the default maxyearmonth
String maxyearmonth="201505";

          

String getmaxmonthandyear="select Max(yearmonth) as yearmo from nutrition where yearmonth < '"+yearmonth+"' and (MCHCCNtrTTC !='0' && MCHCCNtrTTC is not null) and SubPartnerID='"+facil+"'";
  
            System.out.println("__"+getmaxmonthandyear);

conn.rs2=conn.st2.executeQuery(getmaxmonthandyear);

if(conn.rs2.next()){
    
    if(conn.rs2.getString(1)!=null){
maxyearmonth=conn.rs2.getString(1);
    }

}

            System.out.println("Max Year Month____"+maxyearmonth);
//After getting the Maximum valid YearMonth,(By Valid I mean a month that was reported with a cumulative greater than zero) select the cumulatives for male, female and totals

    String gatmaxs="select MCHCCNtrTMC , MCHCCNtrTFC ,MCHCCNtrTTC  from nutrition where yearmonth='"+maxyearmonth+"' and  SubPartnerID='"+facil+"'";
    
      conn.rs=conn.st.executeQuery(gatmaxs);
    while(conn.rs.next()){
    
    MCHCCNtrTMCH=conn.rs.getString("MCHCCNtrTMC");
if(MCHCCNtrTMCH==null){MCHCCNtrTMCH="0"; }

MCHCCNtrTFCH=conn.rs.getString("MCHCCNtrTFC");
if(MCHCCNtrTFCH==null){MCHCCNtrTFCH="0"; }

MCHCCNtrTTCH=conn.rs.getString("MCHCCNtrTTC");
if(MCHCCNtrTTCH==null){MCHCCNtrTTCH="0"; }
    
    }
   

    
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
       
MCHCCNtrTM=conn.rs.getString("MCHCCNtrTM");
if(MCHCCNtrTM==null){MCHCCNtrTM=""; }

MCHCCNtrTF=conn.rs.getString("MCHCCNtrTF");
if(MCHCCNtrTF==null){MCHCCNtrTF=""; }

MCHCCNtrTT=conn.rs.getString("MCHCCNtrTT");
if(MCHCCNtrTT==null){MCHCCNtrTT=""; }


 MCHCCNtrTMC=conn.rs.getString("MCHCCNtrTMC");
if(MCHCCNtrTMC==null){MCHCCNtrTMC=""; }

MCHCCNtrTFC=conn.rs.getString("MCHCCNtrTFC");
if(MCHCCNtrTFC==null){MCHCCNtrTFC=""; }

MCHCCNtrTTC=conn.rs.getString("MCHCCNtrTTC");
if(MCHCCNtrTTC==null){MCHCCNtrTTC=""; }


MCHNtrnCHWTrain=conn.rs.getString("MCHNtrnCHWTrain");
if(MCHNtrnCHWTrain==null){MCHNtrnCHWTrain=""; }

MCHNutChRch=conn.rs.getString("MCHNutChRch");
if(MCHNutChRch==null){MCHNutChRch=""; }

MCHNtrnWasted=conn.rs.getString("MCHNtrnWasted");
if(MCHNtrnWasted==null){MCHNtrnWasted=""; }

MCHNtrnUnderweight=conn.rs.getString("MCHNtrnUnderweight");
if(MCHNtrnUnderweight==null){MCHNtrnUnderweight=""; }

MCHChild5D=conn.rs.getString("MCHChild5D");
if(MCHChild5D==null){MCHChild5D=""; }

MCHNtrnHealthFacility=conn.rs.getString("MCHNtrnHealthFacility");
if(MCHNtrnHealthFacility==null){MCHNtrnHealthFacility=""; }

MCHVaccVitA=conn.rs.getString("MCHVaccVitA");
if(MCHVaccVitA==null){MCHVaccVitA=""; }

MCHNtrnFoodOVC=conn.rs.getString("MCHNtrnFoodOVC");
if(MCHNtrnFoodOVC==null){MCHNtrnFoodOVC=""; }
        
MCHNtrnFoodPLHIV=conn.rs.getString("MCHNtrnFoodPLHIV");
if(MCHNtrnFoodPLHIV==null){MCHNtrnFoodPLHIV=""; }
        
MCHNtrnFood=conn.rs.getString("MCHNtrnFood");
if(MCHNtrnFood==null){MCHNtrnFood=""; }
        
C51DCM=conn.rs.getString("C51DCM");
if(C51DCM==null){C51DCM=""; }
        
C51DCF=conn.rs.getString("C51DCF");
if(C51DCF==null){C51DCF=""; }
        
C51DC=conn.rs.getString("C51DC");
if(C51DC==null){C51DC=""; }
        
C51DAM=conn.rs.getString("C51DAM");
if(C51DAM==null){C51DAM=""; }
        
C51DAF=conn.rs.getString("C51DAF");
if(C51DAF==null){C51DAF=""; }
        
C51DA=conn.rs.getString("C51DA");
if(C51DA==null){C51DA=""; }
        
C51DP=conn.rs.getString("C51DP");
if(C51DP==null){C51DP=""; }
        
C51DMT=conn.rs.getString("C51DMT");
if(C51DMT==null){C51DMT=""; }
        
C51DFT=conn.rs.getString("C51DFT");
if(C51DFT==null){C51DFT=""; }
        
C51DT=conn.rs.getString("C51DT");
if(C51DT==null){C51DT=""; }
        
    }
    
    String createdtable=enterdby+"<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;'>3.1.9.2 population-based Nutrition Service Delivery</b></legend><table  cellpadding='2px' border='0' style='border-color: #e5e5e5;margin-bottom: 3px;'><tr class='form-actions'><th colspan='7'><b></b></th></tr>";
    
    if(session.getAttribute("forms_holder")!=null){ if(session.getAttribute("forms_holder").toString().contains("Nutrition")){
    
    createdtable+="<tr><td colspan='4'><b>Number of People trained in child health care and nutrition through USG-supported health area programs</b></td><td><b>Current Values</b></td><td><b>Cumulative</b></td></tr>";
    createdtable+="<tr><td colspan='4' style='text-align:right;'>No of Men </td><td><input   style='width:100px;' type='text' onclick=\"this.select();\" onkeypress=\"return numbers(event,this);\" onblur=\"autosave('MCHCCNtrTM');t3190total();\"  value='"+MCHCCNtrTM+"' name='MCHCCNtrTM' id='MCHCCNtrTM' ></td><td><input   style='width:100px;' type='text'  onkeypress=\"return numbers(event,this);\" tabindex='-1' readonly   value='"+MCHCCNtrTMC+"' name='MCHCCNtrTMC' id='MCHCCNtrTMC' ><input type='hidden' name='MCHCCNtrTMCH' id='MCHCCNtrTMCH' value='"+MCHCCNtrTMCH+"'></td></tr>";
    createdtable+="<tr><td colspan='4' style='text-align:right;'>No of Women </td><td><input style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\" onblur=\"autosave('MCHCCNtrTF');t3190total();\" value='"+MCHCCNtrTF+"' name='MCHCCNtrTF' id='MCHCCNtrTF' ></td><td><input style='width:100px;' type='text'    onkeypress=\"return numbers(event,this);\" tabindex='-1' readonly value='"+MCHCCNtrTFC+"' name='MCHCCNtrTFC' id='MCHCCNtrTFC' ><input type='hidden' name='MCHCCNtrTFCH' id='MCHCCNtrTFCH' value='"+MCHCCNtrTFCH+"'></td></tr>";
    createdtable+="<tr><td colspan='4'><b>Total Number of people trained in child health care and nutrition through USG-supported health area programs</b></td><td><input tabindex='-1' style='width:100px;' type='text' readonly   onkeypress=\"return numbers(event,this);\"  value='"+MCHCCNtrTT+"' name='MCHCCNtrTT' id='MCHCCNtrTT' ></td><td><input tabindex='-1' style='width:100px;' type='text' readonly   onkeypress=\"return numbers(event,this);\"  value='"+MCHCCNtrTTC+"' name='MCHCCNtrTTC' id='MCHCCNtrTTC' > <input type='hidden' name='MCHCCNtrTTCH' id='MCHCCNtrTTCH' value='"+MCHCCNtrTTCH+"'> </td></tr>";
    createdtable+="<tr><td colspan='5'><b>Number of Community health workers trained in child health and/or nutrition</b></td><td><input style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\" onblur=\"autosave('MCHNtrnCHWTrain');\" value='"+MCHNtrnCHWTrain+"' name='MCHNtrnCHWTrain' id='MCHNtrnCHWTrain' ></td></tr>";
    createdtable+="<tr><td colspan='5'><b>Number of children reached by USG-supported nutrition programs</b></td><td><input style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\" onblur=\"autosave('MCHNutChRch');\" value='"+MCHNutChRch+"' name='MCHNutChRch' id='MCHNutChRch' ></td></tr>";
    createdtable+="<tr><td colspan='5'>Total number of children under five who are wasted (with weight for height Z score < - 2)</td><td><input style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\" onblur=\"autosave('MCHNtrnWasted');\" value='"+MCHNtrnWasted+"' name='MCHNtrnWasted' id='MCHNtrnWasted' ></td></tr>";
    createdtable+="<tr><td colspan='5'>Total number of children under five who are underweight (with weight for age Z score < - 2) (SEE Indicator </td><td><input style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\" onblur=\"autosave('MCHNtrnUnderweight');\" value='"+MCHNtrnUnderweight+"' name='MCHNtrnUnderweight' id='MCHNtrnUnderweight' ></td></tr>";
    createdtable+="<tr><td colspan='5'>Total number of children under five years</td><td><input style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\" onblur=\"autosave('MCHChild5D');\" value='"+MCHChild5D+"' name='MCHChild5D' id='MCHChild5D' ></td></tr>";
    createdtable+="<tr><td colspan='5'>Number of health facilities with established capacity to manage acute under-nutrition</td><td><input style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\" onblur=\"autosave('MCHNtrnHealthFacility');\" value='"+MCHNtrnHealthFacility+"' name='MCHNtrnHealthFacility' id='MCHNtrnHealthFacility' ></td></tr>";
    
    createdtable+="</table></fieldset><fieldset class='formatter'><legend class='formatter'><b style='text-align:center;'>HIV and Nutrition </b></legend><table  cellpadding='2px' border='0' style='border-color: #e5e5e5;margin-bottom: 3px;'>"
            + "<tr><td></td> <td colspan='6'> <b>Number of children under 5 years of age who received Vitamin A from USG-supported programs</b> </td><td><input style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\" onblur=\"autosave('MCHVaccVitA');\" value='"+MCHVaccVitA+"' name='MCHVaccVitA' id='MCHVaccVitA' ></td></tr>";
    createdtable+="<tr><td rowspan='3' colspan='2'><b>C2.3.D</b></td><td colspan='5'>Number of HIV – positive clinically malnourished clients who received therapeutic and/or supplementary food < 18</td><td><input style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\" onblur=\"autosave('MCHNtrnFoodOVC');c23dtotal();\" value='"+MCHNtrnFoodOVC+"' name='MCHNtrnFoodOVC' id='MCHNtrnFoodOVC' ></td></tr>";
    createdtable+="<tr><td colspan='5'>Number of HIV – positive clinically malnourished clients who received therapeutic and/or supplementary food 18+ (PLHIV)</td><td><input style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\" onblur=\"autosave('MCHNtrnFoodPLHIV');c23dtotal();\" value='"+MCHNtrnFoodPLHIV+"' name='MCHNtrnFoodPLHIV' id='MCHNtrnFoodPLHIV' ></td></tr>";
    createdtable+="<tr><td colspan='5'>Number of HIV – positive clinically malnourished clients who received therapeutic and/or supplementary food -<b> Total</b></td><td><input readonly tabindex='-1'  style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\" onblur=\"autosave('MCHNtrnFood');\" value='"+MCHNtrnFood+"' name='MCHNtrnFood' id='MCHNtrnFood' ></td></tr>";
    createdtable+="<tr><td></td><td colspan='4'></td><td><b>Male</b></td><td><b>Female</b></td><td><b>Total</b></td></tr>";
    createdtable+="<tr><td rowspan='4' colspan='2'> <b>C5.1.D</b></td><td colspan='2' rowspan='4'>Number of eligible clients who received food and / or other nutrition Services</td><td> <b><18</b> </td><td><input style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\" onblur=\"autosave('C51DCM');c51dtotal();\" value='"+C51DCM+"' name='C51DCM' id='C51DCM' ></td><td><input style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\" onblur=\"autosave('C51DCF');c51dtotal();\" value='"+C51DCF+"' name='C51DCF' id='C51DCF' ></td><td><input readonly tabindex='-1' style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\"  value='"+C51DC+"' name='C51DC' id='C51DC' ></td></tr>";
    createdtable+="<tr><td> <b> >=18</b> </td><td><input tabindex='-1' readonly style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\"  value='"+C51DAM+"' name='C51DAM' id='C51DAM' ></td><td><input tabindex='-1' readonly style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\" onblur=\"autosave('C51DAF');c51dtotal();\" value='"+C51DAF+"' name='C51DAF' id='C51DCF' ></td><td><input tabindex='-1' readonly style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\"  value='"+C51DA+"' name='C51DA' id='C51DA' ></td></tr>";
    createdtable+="<tr><td colspan='3'> <b> Pregnant/Lactating (PMTCT 1.5)</b> </td><td><input style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\" onblur=\"autosave('C51DP');\" value='"+C51DP+"' name='C51DP' id='C51DP' ></td></tr>";
    createdtable+="<tr><td> <b> Total</b> </td><td><input tabindex='-1' readonly style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\"  value='"+C51DMT+"' name='C51DMT' id='C51DMT' ></td><td><input tabindex='-1' readonly style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\"  value='"+C51DFT+"' name='C51DFT' id='C51DFT' ></td><td><input tabindex='-1' readonly style='width:100px;' type='text'  onclick=\"this.select();\"  onkeypress=\"return numbers(event,this);\" onblur=\"autosave('C51DT');\" value='"+C51DT+"' name='C51DT' id='C51DT' ></td></tr>"
            + "   </table></fieldset><div class='form-actions'><input type='submit' class='btn blue' value='Run Validation' name='validate' id='validate'/></div><span id='formstatus' style='display:none;'>"+formtype+" </span><span id='rc' style='display:none;'>"+label+" </span>	";
    
    }
    
    else {
    createdtable="<tr ><td colspan='4'><font color=\"red\" size=\"6px;\" style=\"margin-left: 0%;\"><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support  Nutrition module.</font></td></tr>";
    }
    
    }
    
        if(session.getAttribute("facilityid")!=null){ } else {
  createdtable="<tr ><td colspan='4'><font color=\"red\" size=\"6px;\" style=\"margin-left: 0%;\"><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support  Nutrition module.</font></td></tr>";
       
  }
    
    
      System.out.println(createdtable);
      
    PrintWriter out = response.getWriter();
    try {
        /* TODO output your page here. You may use following sample code. */
  
        out.println(createdtable);
    } finally {
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(loadNutrition.class.getName()).log(Level.SEVERE, null, ex);
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
