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
public class loadServices extends HttpServlet {

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
            
//sx
//id
//pp
//ag
//wd


String sx="";
String id="";
String pp="";
String ag="";
String wd="";
            
if(request.getParameter("sx")!=null){sx=request.getParameter("sx");}
if(request.getParameter("id")!=null){id=request.getParameter("id");}
if(request.getParameter("pp")!=null){pp=request.getParameter("pp");}
if(request.getParameter("ag")!=null){ag=request.getParameter("ag");}
if(request.getParameter("wd")!=null){wd=request.getParameter("wd");}

dbConn conn= new dbConn();

String qry=" SELECT  " +
"participantid, " +
"pregnant, " +
"clientphone, " +
"maritalstatus, " +
"ref_from, " +
"ref_to, " +
"hivresults, " +
" case when htc_ref=1 then 'checked' else '' end  as htc_ref, " +
"htc_ref_date, " +
" case when vmmc_ref=1 then 'checked' else '' end  as vmmc_ref, " +
"vmmc_ref_date, " +
" case when anc_pmtct_ref=1 then 'checked' else '' end  as anc_pmtct_ref, " +
"anc_pmtct_ref_date, " +
" case when sti_ref=1 then 'checked' else '' end  as sti_ref, " +
"sti_ref_date, " +
" case when tb_ref=1 then 'checked' else '' end  as tb_ref, " +
"tb_ref_date, " +
" case when familyplanning_ref=1 then 'checked' else '' end  as familyplanning_ref, " +
"familyplanning_ref_date, " +
" case when pep_ref=1 then 'checked' else '' end  as pep_ref, " +
"pep_ref_date, " +
" case when care_ref=1 then 'checked' else '' end  as care_ref, " +
"care_ref_date, " +
" case when psychosocial_ref=1 then 'checked' else '' end  as psychosocial_ref, " +
"psychosocial_ref_date, " +
" case when drugabuse_ref=1 then 'checked' else '' end  as drugabuse_ref, " +
"drugabuse_ref_date, " +
" case when counselling_ref=1 then 'checked' else '' end  as counselling_ref, " +
"counselling_ref_date, " +
" case when bcc_ref=1 then 'checked' else '' end  as bcc_ref, " +
"bcc_ref_date, " +
" case when other_ref=1 then 'checked' else '' end  as other_ref, " +
"  other_specific_ref as other_specific_ref, " +
"other_ref_date, " +
" case when htc_rec=1 then 'checked' else '' end  as htc_rec, " +
"htc_rec_date, " +
" case when vmmc_rec=1 then 'checked' else '' end  as vmmc_rec, " +
"vmmc_rec_date, " +
" case when anc_pmtct_rec=1 then 'checked' else '' end  as anc_pmtct_rec, " +
"anc_pmtct_rec_date, " +
" case when sti_rec=1 then 'checked' else '' end  as sti_rec, " +
"sti_rec_date, " +
" case when tb_rec=1 then 'checked' else '' end  as tb_rec, " +
"tb_rec_date, " +
" case when familyplanning_rec=1 then 'checked' else '' end  as familyplanning_rec, " +
"familyplanning_rec_date, " +
" case when pep_rec=1 then 'checked' else '' end  as pep_rec, " +
"pep_rec_date, " +
" case when care_rec=1 then 'checked' else '' end  as care_rec, " +
"care_rec_date, " +
" case when psychosocial_rec=1 then 'checked' else '' end  as psychosocial_rec, " +
"psychosocial_rec_date, " +
" case when drugabuse_rec=1 then 'checked' else '' end  as drugabuse_rec, " +
"drugabuse_rec_date, " +
" case when counselling_rec=1 then 'checked' else '' end  as counselling_rec, " +
"counselling_rec_date, " +
" case when bcc_rec=1 then 'checked' else '' end  as bcc_rec, " +
"bcc_rec_date, " +
" case when other_rec=1 then 'checked' else '' end  as other_rec, " +
" other_specific_rec   as other_specific_rec, " +
"other_rec_date, " +
"ref_by, " +
"rec_by " +
" " +
" " +
"FROM hc_services where participantid='"+id+"'";

String pregnant="";
String clientphone="";
String maritalstatus="";
String ref_from="";
String ref_to="";
String hivresults="";
String htc_ref="";
String htc_ref_date="";
String vmmc_ref="";
String vmmc_ref_date="";
String anc_pmtct_ref="";
String anc_pmtct_ref_date="";
String sti_ref="";
String sti_ref_date="";
String tb_ref="";
String tb_ref_date="";
String familyplanning_ref="";
String familyplanning_ref_date="";
String pep_ref="";
String pep_ref_date="";
String care_ref="";
String care_ref_date="";
String psychosocial_ref="";
String psychosocial_ref_date="";
String drugabuse_ref="";
String drugabuse_ref_date="";
String counselling_ref="";
String counselling_ref_date="";
String bcc_ref="";
String bcc_ref_date="";
String other_ref="";
String other_specific_ref="";
String other_ref_date="";
String htc_rec="";
String htc_rec_date="";
String vmmc_rec="";
String vmmc_rec_date="";
String anc_pmtct_rec="";
String anc_pmtct_rec_date="";
String sti_rec="";
String sti_rec_date="";
String tb_rec="";
String tb_rec_date="";
String familyplanning_rec="";
String familyplanning_rec_date="";
String pep_rec="";
String pep_rec_date="";
String care_rec="";
String care_rec_date="";
String psychosocial_rec="";
String psychosocial_rec_date="";
String drugabuse_rec="";
String drugabuse_rec_date="";
String counselling_rec="";
String counselling_rec_date="";
String bcc_rec="";
String bcc_rec_date="";
String other_rec="";
String other_specific_rec="";
String other_rec_date="";
String ref_by="";
String rec_by="";





conn.rs=conn.st.executeQuery(qry);

while(conn.rs.next()){

 if(conn.rs.getString("pregnant")!=null){ pregnant= conn.rs.getString("pregnant"); }
 if(conn.rs.getString("clientphone")!=null){ clientphone= conn.rs.getString("clientphone"); }
 if(conn.rs.getString("maritalstatus")!=null){ maritalstatus= conn.rs.getString("maritalstatus"); }
 if(conn.rs.getString("ref_from")!=null){ ref_from= conn.rs.getString("ref_from"); }
 if(conn.rs.getString("ref_to")!=null){ ref_to= conn.rs.getString("ref_to"); }
 if(conn.rs.getString("hivresults")!=null){ hivresults= conn.rs.getString("hivresults"); }
 if(conn.rs.getString("htc_ref")!=null){ htc_ref= conn.rs.getString("htc_ref"); }
 if(conn.rs.getString("htc_ref_date")!=null){ htc_ref_date= conn.rs.getString("htc_ref_date"); }
 if(conn.rs.getString("vmmc_ref")!=null){ vmmc_ref= conn.rs.getString("vmmc_ref"); }
 if(conn.rs.getString("vmmc_ref_date")!=null){ vmmc_ref_date= conn.rs.getString("vmmc_ref_date"); }
 if(conn.rs.getString("anc_pmtct_ref")!=null){ anc_pmtct_ref= conn.rs.getString("anc_pmtct_ref"); }
 if(conn.rs.getString("anc_pmtct_ref_date")!=null){ anc_pmtct_ref_date= conn.rs.getString("anc_pmtct_ref_date"); }
 if(conn.rs.getString("sti_ref")!=null){ sti_ref= conn.rs.getString("sti_ref"); }
 if(conn.rs.getString("sti_ref_date")!=null){ sti_ref_date= conn.rs.getString("sti_ref_date"); }
 if(conn.rs.getString("tb_ref")!=null){ tb_ref= conn.rs.getString("tb_ref"); }
 if(conn.rs.getString("tb_ref_date")!=null){ tb_ref_date= conn.rs.getString("tb_ref_date"); }
 if(conn.rs.getString("familyplanning_ref")!=null){ familyplanning_ref= conn.rs.getString("familyplanning_ref"); }
 if(conn.rs.getString("familyplanning_ref_date")!=null){ familyplanning_ref_date= conn.rs.getString("familyplanning_ref_date"); }
 if(conn.rs.getString("pep_ref")!=null){ pep_ref= conn.rs.getString("pep_ref"); }
 if(conn.rs.getString("pep_ref_date")!=null){ pep_ref_date= conn.rs.getString("pep_ref_date"); }
 if(conn.rs.getString("care_ref")!=null){ care_ref= conn.rs.getString("care_ref"); }
 if(conn.rs.getString("care_ref_date")!=null){ care_ref_date= conn.rs.getString("care_ref_date"); }
 if(conn.rs.getString("psychosocial_ref")!=null){ psychosocial_ref= conn.rs.getString("psychosocial_ref"); }
 if(conn.rs.getString("psychosocial_ref_date")!=null){ psychosocial_ref_date= conn.rs.getString("psychosocial_ref_date"); }
 if(conn.rs.getString("drugabuse_ref")!=null){ drugabuse_ref= conn.rs.getString("drugabuse_ref"); }
 if(conn.rs.getString("drugabuse_ref_date")!=null){ drugabuse_ref_date= conn.rs.getString("drugabuse_ref_date"); }
 if(conn.rs.getString("counselling_ref")!=null){ counselling_ref= conn.rs.getString("counselling_ref"); }
 if(conn.rs.getString("counselling_ref_date")!=null){ counselling_ref_date= conn.rs.getString("counselling_ref_date"); }
 if(conn.rs.getString("bcc_ref")!=null){ bcc_ref= conn.rs.getString("bcc_ref"); }
 if(conn.rs.getString("bcc_ref_date")!=null){ bcc_ref_date= conn.rs.getString("bcc_ref_date"); }
 if(conn.rs.getString("other_ref")!=null){ other_ref= conn.rs.getString("other_ref"); }
 if(conn.rs.getString("other_specific_ref")!=null){ other_specific_ref= conn.rs.getString("other_specific_ref"); }
 if(conn.rs.getString("other_ref_date")!=null){ other_ref_date= conn.rs.getString("other_ref_date"); }
 if(conn.rs.getString("htc_rec")!=null){ htc_rec= conn.rs.getString("htc_rec"); }
 if(conn.rs.getString("htc_rec_date")!=null){ htc_rec_date= conn.rs.getString("htc_rec_date"); }
 if(conn.rs.getString("vmmc_rec")!=null){ vmmc_rec= conn.rs.getString("vmmc_rec"); }
 if(conn.rs.getString("vmmc_rec_date")!=null){ vmmc_rec_date= conn.rs.getString("vmmc_rec_date"); }
 if(conn.rs.getString("anc_pmtct_rec")!=null){ anc_pmtct_rec= conn.rs.getString("anc_pmtct_rec"); }
 if(conn.rs.getString("anc_pmtct_rec_date")!=null){ anc_pmtct_rec_date= conn.rs.getString("anc_pmtct_rec_date"); }
 if(conn.rs.getString("sti_rec")!=null){ sti_rec= conn.rs.getString("sti_rec"); }
 if(conn.rs.getString("sti_rec_date")!=null){ sti_rec_date= conn.rs.getString("sti_rec_date"); }
 if(conn.rs.getString("tb_rec")!=null){ tb_rec= conn.rs.getString("tb_rec"); }
 if(conn.rs.getString("tb_rec_date")!=null){ tb_rec_date= conn.rs.getString("tb_rec_date"); }
 if(conn.rs.getString("familyplanning_rec")!=null){ familyplanning_rec= conn.rs.getString("familyplanning_rec"); }
 if(conn.rs.getString("familyplanning_rec_date")!=null){ familyplanning_rec_date= conn.rs.getString("familyplanning_rec_date"); }
 if(conn.rs.getString("pep_rec")!=null){ pep_rec= conn.rs.getString("pep_rec"); }
 if(conn.rs.getString("pep_rec_date")!=null){ pep_rec_date= conn.rs.getString("pep_rec_date"); }
 if(conn.rs.getString("care_rec")!=null){ care_rec= conn.rs.getString("care_rec"); }
 if(conn.rs.getString("care_rec_date")!=null){ care_rec_date= conn.rs.getString("care_rec_date"); }
 if(conn.rs.getString("psychosocial_rec")!=null){ psychosocial_rec= conn.rs.getString("psychosocial_rec"); }
 if(conn.rs.getString("psychosocial_rec_date")!=null){ psychosocial_rec_date= conn.rs.getString("psychosocial_rec_date"); }
 if(conn.rs.getString("drugabuse_rec")!=null){ drugabuse_rec= conn.rs.getString("drugabuse_rec"); }
 if(conn.rs.getString("drugabuse_rec_date")!=null){ drugabuse_rec_date= conn.rs.getString("drugabuse_rec_date"); }
 if(conn.rs.getString("counselling_rec")!=null){ counselling_rec= conn.rs.getString("counselling_rec"); }
 if(conn.rs.getString("counselling_rec_date")!=null){ counselling_rec_date= conn.rs.getString("counselling_rec_date"); }
 if(conn.rs.getString("bcc_rec")!=null){ bcc_rec= conn.rs.getString("bcc_rec"); }
 if(conn.rs.getString("bcc_rec_date")!=null){ bcc_rec_date= conn.rs.getString("bcc_rec_date"); }
 if(conn.rs.getString("other_rec")!=null){ other_rec= conn.rs.getString("other_rec"); }
 if(conn.rs.getString("other_specific_rec")!=null){ other_specific_rec= conn.rs.getString("other_specific_rec"); }
 if(conn.rs.getString("other_rec_date")!=null){ other_rec_date= conn.rs.getString("other_rec_date"); }
 if(conn.rs.getString("ref_by")!=null){ ref_by= conn.rs.getString("ref_by"); }
 if(conn.rs.getString("rec_by")!=null){ rec_by= conn.rs.getString("rec_by"); }


    
    
}

String servicelist[]={"HTC","VMMC","ANC/PMTCT","STI","TB","Family Planning","Post Exposure Prophylaxis (PEP)","Care and Treatment(Starting on ART, Adherence counselling)","Psycho-social Support(including support group)","Drug and Substance Abuse","Counselling Services","Behaviour Change Communication","Others"};


            /* TODO output your page here. You may use following sample code. */
            String data="<table class= 'table  table-bordered' style='width:100%;' > " +
"<thead><tr><th colspan='6'><div class='control-group' >"
+"<h4 style='text-align:center;'><font color='red'><b></b></font><b>PP Prev Client Referral Form</b></h4></div></th></tr></thead> " +

"<tbody> " +
"<tr> " +
"<td> " +
"<label><font color='red'><b></b></font>Name</label> " +
"</td> " +
"<td> " +
"<div class='controls'> " +
"<b><input type='hidden' name='participantid' id='participantid' value='"+id+"'>" +pp+"</b>"+
"</div> " +
"</td> " +
            
"<td> " +
"<label><font color='red'><b></b></font>Sex</label> " +
"</td> " +
                    
"<td> " +
"<div class='controls'> " +
"<b>" +sx+"</b>"+
"</div> " +
"</td> "+

"<td> " +
"<label><font color='red'><b></b></font>age</label> " +
"</td> " +
"<td> " +
"<div class='controls'> " +
"<b>" +ag+"</b>"+
"</div> " +
"</td> " 
+"</tr>"+
                    
//______________________________end of row__________________________________________

"<tr> " +         
"<td> " +
"<label><font color='red'><b></b></font>ward</label> " +
"</td> " +
                    
"<td> " +
"<div class='controls'> " +
"<b>" +wd+"</b>"+
"</div> " +
"</td> "+
                    
"<td> " +
"<label><font color='red'><b></b></font>Pregnant</label> " +
"</td> " +
"<td> " +
"<div class='controls'> " +
"<b> <select   name='pregnant' id='pregnant' style='width:100%;' class='form-control'>" +
" " +pregnancyStatus(pregnant,sx)+
"</select> </b>"+
"</div> " +
"</td> " +
            
"<td> " +
"<label><font color='red'><b></b></font>Marital Status</label> " +
"</td> " +
                    
"<td> " +
"<div class='controls'> " +
"<b> <select   name='maritalstatus' id='maritalstatus' style='width:100%;' class='form-control'>" +
" " +maritalStatus(maritalstatus)+
"</select> </b>"+
"</div> " +
"</td> "
+"</tr>"+
   
                    
                    
                    //______________________________end of row__________________________________________

"<tr> " +         
"<td> " +
"<label><font color='red'><b></b></font>Client Mobile No</label> " +
"</td> " +
                    
"<td> " +
"<div class='controls'> " +
" <input type='text' placeholder='07XX XXX XXX' maxlength='10' style='width:100px;'   name ='clientphone' id='clientphone' value='"+clientphone+"'  class='form-control' > "+
"</div> " +
"</td> "+
                    
"<td> " +
"<label><font color='red'><b></b></font>Referred From</label> " +
"</td> " +
"<td> " +
"<div class='controls'> " +
"<b> <select   name='ref_from' id='ref_from' style='width:100%;' class='form-control'>" +
" " +referredFrom(ref_from)+
"</select> </b>"+
"</div> " +
"</td> " +
            
"<td> " +
"<label><font color='red'><b></b></font>Referred To</label> " +
"</td> " +
                    
"<td> " +
"<div class='controls'> " +
"<b> <select   name='ref_to' id='ref_to' style='width:100%;' class='form-control'>" +
" " +referredTo(ref_to)+
"</select> </b>"+
"</div> " +
"</td> " +
"</tr>"+
                    
                    
//______________________________services__________________________________________


                    
                    
                    
//String servicelist[]=;
"<tr><td colspan='6'><h4 style='text-align:center;'><b>Reason for Referral(Tick Appropriate Service</b></h4></td></tr>"+
"<tr><td>Service</td><td>Date</td><td>Service</td><td>Date</td><td>Service</td><td>Date</td></tr>"+
"<tr>";
data+= "<td><input type='checkbox' id='htc_ref' name='htc_ref' value='1' "+htc_ref+">HTC</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='htc_ref_date' name='htc_ref_date' value='"+htc_ref_date+"'>";
data+= "</td><td><input type='checkbox' id='familyplanning_ref' name='familyplanning_ref' value='1' "+familyplanning_ref+">Family Planning</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='familyplanning_ref_date' name='familyplanning_ref_date' value='"+familyplanning_ref_date+"'>";
data+= "</td><td><input type='checkbox' id='counselling_ref' name='counselling_ref' value='1' "+counselling_ref+">Counselling Services</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='counselling_ref_date' name='counselling_ref_date' value='"+counselling_ref_date+"'>";
data+= "</td></tr>"+

  "<tr>";
data+= "<td><input type='checkbox' id='vmmc_ref' name='vmmc_ref' value='1' "+vmmc_ref+">VMMC</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='vmmc_ref_date' name='vmmc_ref_date' value='"+vmmc_ref_date+"'>";
data+= "</td><td><input type='checkbox' id='pep_ref' name='pep_ref' value='1' "+pep_ref+">Post Exposure Prophylaxis (PEP)</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='pep_ref_date' name='pep_ref_date' value='"+pep_ref_date+"'>";
data+= "</td><td><input type='checkbox' id='bcc_ref' name='bcc_ref' value='1' "+bcc_ref+">Behaviour Change Communication</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='bcc_ref_date' name='bcc_ref_date' value='"+bcc_ref_date+"'>";
data+= "</td></tr>"+

        "<tr>";
data+= "<td><input type='checkbox' id='anc_pmtct_ref' name='anc_pmtct_ref' value='1' "+anc_pmtct_ref+">ANC/PMTCT</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='anc_pmtct_ref_date' name='anc_pmtct_ref_date' value='"+anc_pmtct_ref_date+"'>";
data+= "</td><td><input type='checkbox' id='care_ref' name='care_ref' value='1' "+care_ref+">Care and Treatment(Starting on ART, Adherence counselling)</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='care_ref_date' name='care_ref_date' value='"+care_ref_date+"'>";
data+= "</td><td><input type='checkbox' id='other_ref' name='other_ref' value='1' "+other_ref+">Others</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='other_ref_date' name='other_ref_date' value='"+other_ref_date+"'>";
data+= "</td></tr>"+



"<tr>";
data+= "<td><input type='checkbox' id='sti_ref' name='sti_ref' value='1' "+sti_ref+">STI</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='sti_ref_date' name='sti_ref_date' value='"+sti_ref_date+"'>";
data+= "</td><td><input type='checkbox' id='psychosocial_ref' name='psychosocial_ref' value='1' "+psychosocial_ref+">Psycho-social Support(including support group)</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='psychosocial_ref_date' name='psychosocial_ref_date' value='"+psychosocial_ref_date+"'>";
data+= "</td><td>Specify others</td><td>";
data+= "<input type='text' id='other_specific_ref' style='width:100px;'  name='other_specific_ref' value='"+other_specific_ref+"'>";

data+= "</td></tr>"+
        
        
        "<tr>";
data+= "<td><input type='checkbox' id='tb_ref' name='tb_ref' value='1' "+tb_ref+">TB</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='tb_ref_date' name='tb_ref_date' value='"+tb_ref_date+"'>";
data+= "</td><td><input type='checkbox' id='drugabuse_ref' name='drugabuse_ref' value='1' "+drugabuse_ref+">Drug and Substance Abuse</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='drugabuse_ref_date' name='drugabuse_ref_date' value='"+drugabuse_ref_date+"'>";
data+= "</td><td></td><td>";
data+="";
data+= "</td></tr>"+
        
  
                   
//String servicelist[]=;
"<tr><td colspan='6'><h4 style='text-align:center;'><b>Services offered to the Client(Tick Appropriate service)</b></h4></td></tr>"+
"<tr><td>Service</td><td>Date</td><td>Service</td><td>Date</td><td>Service</td><td>Date</td></tr>"+
"<tr>";
data+= "<td><input type='checkbox' id='htc_rec' name='htc_rec' value='1' "+htc_rec+">HTC</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='htc_rec_date' name='htc_rec_date' value='"+htc_rec_date+"'>";
data+= "</td><td><input type='checkbox' id='familyplanning_rec' name='familyplanning_rec' value='1' "+familyplanning_rec+">Family Planning</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='familyplanning_rec_date' name='familyplanning_rec_date' value='"+familyplanning_rec_date+"'>";
data+= "</td><td><input type='checkbox' id='counselling_rec' name='counselling_rec' value='1' "+counselling_rec+">Counselling Services</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='counselling_rec_date' name='counselling_rec_date' value='"+counselling_rec_date+"'>";
data+= "</td></tr>"+

  "<tr>";
data+= "<td><input type='checkbox' id='vmmc_rec' name='vmmc_rec' value='1' "+vmmc_rec+">VMMC</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='vmmc_rec_date' name='vmmc_rec_date' value='"+vmmc_rec_date+"'>";
data+= "</td><td><input type='checkbox' id='pep_rec' name='pep_rec' value='1' "+pep_rec+">Post Exposure Prophylaxis (PEP)</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='pep_rec_date' name='pep_rec_date' value='"+pep_rec_date+"'>";
data+= "</td><td><input type='checkbox' id='bcc_rec' name='bcc_rec' value='1' "+bcc_rec+">Behaviour Change Communication</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='bcc_rec_date' name='bcc_rec_date' value='"+bcc_rec_date+"'>";
data+= "</td></tr>"+

        "<tr>";
data+= "<td><input type='checkbox' id='anc_pmtct_rec' name='anc_pmtct_rec' value='1' "+anc_pmtct_rec+">ANC/PMTCT</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='anc_pmtct_rec_date' name='anc_pmtct_rec_date' value='"+anc_pmtct_rec_date+"'>";
data+= "</td><td><input type='checkbox' id='care_rec' name='care_rec' value='1' "+care_rec+">Care and Treatment(Starting on ART, Adherence counselling)</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='care_rec_date' name='care_rec_date' value='"+care_rec_date+"'>";
data+= "</td><td><input type='checkbox' id='other_rec' name='other_rec' value='1' "+other_rec+">Others</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='other_rec_date' name='other_rec_date' value='"+other_rec_date+"'>";
data+= "</td></tr>"+



"<tr>";
data+= "<td><input type='checkbox' id='sti_rec' name='sti_rec' value='1' "+sti_rec+">STI</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='sti_rec_date' name='sti_rec_date' value='"+sti_rec_date+"'>";
data+= "</td><td><input type='checkbox' id='psychosocial_rec' name='psychosocial_rec' value='1' "+psychosocial_rec+">Psycho-social Support(including support group)</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='psychosocial_rec_date' name='psychosocial_rec_date' value='"+psychosocial_rec_date+"'>";
data+= "</td><td>Specify others</td><td>";
data+= "<input type='text' id='other_specific_rec' style='width:100px;'  name='other_specific_rec' value='"+other_specific_rec+"'>";

data+= "</td></tr>"+
        
        
        "<tr>";
data+= "<td><input type='checkbox' id='tb_rec' name='tb_rec' value='1' "+tb_rec+">TB</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='tb_rec_date' name='tb_rec_date' value='"+tb_rec_date+"'>";
data+= "</td><td><input type='checkbox' id='drugabuse_rec' name='drugabuse_rec' value='1' "+drugabuse_rec+">Drug and Substance Abuse</td><td>";
data+="<input class='form-control tarehe3' style='width:100px;' type='text' readonly id='drugabuse_rec_date' name='drugabuse_rec_date' value='"+drugabuse_rec_date+"'>";
data+= "</td><td></td><td>";
data+="";
data+= "</td></tr>";
         
        
data+= "<tr><td colspan='1'>HIV Result</td>"
+"<td colspan='1'> " +
"<div class='controls'> " +
"<b> <select   name='hivresults' id='hivresults' style='width:100%;' class='form-control'>" +
" " +hivResults(hivresults)+
"</select> </b>"+
"</div> " +
"</td> <td colspan='4'></td>"
        
        + "</tr>";       
        
  
        data+= "<tr><td colspan='1'>Referred By</td>"
        + ""
        +"<td colspan='2'> " +
"<div class='controls'> " +
"<input class='form-control' style='width:180px;' type='text'  id='ref_by' name='ref_by' value='"+ref_by+"'>" +

"</div> " +
"</td> <td colspan='1'>Received By</td>"
        
        +"<td colspan='2'> " +
"<div class='controls'> " +
"<input class='form-control' style='width:180px;' type='text'  id='rec_by' name='rec_by' value='"+rec_by+"'>" +

"</div> " +
"</td> "
+ "<td colspan='4'></td>"
        
        + "</tr>"+  
        
//______________________________services__________________________________________                    
 
                    
" <tr><td colspan='6'> " +
"<div class='control-group'> " +
" " +
"<div class='controls'> " +
"<label onclick='saveServices();' id='saveservice' style='width;70px;'  class='btn-lg btn-success '> " +
"Save" +
"</label> " +
" " +
"<h4 id='ujumbe3'></h4>" +
" " +
"</div> " +
"</div></td></tr> " +
"</tbody> " +
"</table> </div> ";
            
            
            
            
            out.println(data);
            
            if(conn.rs!=null){conn.rs.close();}
            if(conn.st!=null){conn.st.close();}
            if(conn.conn!=null){conn.conn.close();}
            
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
            Logger.getLogger(loadServices.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(loadServices.class.getName()).log(Level.SEVERE, null, ex);
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

    
    public String pregnancyStatus(String curstatus,String Sex){

 
    
    String allattendance[]={"Yes","No","NA"};
   
    String men="";
   String options="<option value=''>status</option>";
    //String options="";
    for(int b=0;b<allattendance.length;b++){
        String isselected="";
        //System.out.println(" Compare statuses "+curstatus+"  "+allattendanceid[b]);
        if(curstatus.equals(allattendance[b])){isselected=" selected ";}
        if(Sex.equals("Male"))
        {
        options="<option "+isselected+" value='NA'>NA</option>";
        }else{
        
    options+="<option "+isselected+" value='"+allattendance[b]+"'>"+allattendance[b]+"</option>";
        }
    
    }
    

return options;
}
 
    
       public String maritalStatus(String curstatus){

 
    
    String allattendance[]={"Single","Married Monogamous","Married Polygamous","Cohabiting","Divorced/Separated","Widow/Widower"};
    
  
    String options="<option value=''>Choose Status</option>";
   // String options="";
    for(int b=0;b<allattendance.length;b++){
        String isselected="";
        //System.out.println(" Compare statuses "+curstatus+"  "+allattendanceid[b]);
        if(curstatus.equals(allattendance[b])){isselected=" selected ";}
        
        
    options+="<option "+isselected+" value='"+allattendance[b]+"'>"+allattendance[b]+"</option>";
        
    
    }
    

return options;
}
    
    
    
       public String referredFrom(String curstatus){

 
    
    String allattendance[]={"PP_Prev Group","Organization"};
    
  
    String options="<option value=''>Choose value</option>";
   // String options="";
    for(int b=0;b<allattendance.length;b++){
        String isselected="";
        //System.out.println(" Compare statuses "+curstatus+"  "+allattendanceid[b]);
        if(curstatus.equals(allattendance[b])){isselected=" selected ";}
        
        
    options+="<option "+isselected+" value='"+allattendance[b]+"'>"+allattendance[b]+"</option>";
        
    
    }
    

return options;
}
    
    
       
          
       public String referredTo(String curstatus){

 
    
    String allattendance[]={"Facility","Organization","Community and SDP"};
    
  
    String options="<option value=''>Choose value</option>";
   // String options="";
   
    for(int b=0;b<allattendance.length;b++){
        String isselected="";
        //System.out.println(" Compare statuses "+curstatus+"  "+allattendanceid[b]);
        if(curstatus.equals(allattendance[b])){isselected=" selected ";}
        
        
    options+="<option "+isselected+" value='"+allattendance[b]+"'>"+allattendance[b]+"</option>";
        
    
    }
    

return options;
}
       
       
       
  public String hivResults(String curstatus){

 
    
    String allattendance[]={"Negative","Positive","Unknown"};
    
  
    String options="<option value=''>Select Results</option>";
   // String options="";
   
    for(int b=0;b<allattendance.length;b++){
        String isselected="";
        //System.out.println(" Compare statuses "+curstatus+"  "+allattendanceid[b]);
        if(curstatus.equals(allattendance[b])){isselected=" selected ";}
        
        
    options+="<option "+isselected+" value='"+allattendance[b]+"'>"+allattendance[b]+"</option>";
        
    
    }
    

return options;
}     
       
       
       
}
