/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T1FORM;

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
public class LoadSummary extends HttpServlet {
HttpSession session;

String summary_id,program_area,cordinator,districts,agency,venue,curriculum,start_date,end_date,training_name,s_male,s_female,d_male,d_female,date_range,nurse,rco,hrio,dclerk,pharmtec,labtec,mo,couns,hts_screener,acouns,linkdesk,mmother,mfather,expclient,chv,sto,to,tio,meo,mea,hro,dma,otherc;
String program_area_id,district_ids,curriculum_id,possible_districts;
String lock;
String gender;
String input,hidden,hiddenx,edit,summary_button_label,btn_data,bg;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        dbConn conn = new dbConn();
        session=request.getSession();
        summary_id=request.getParameter("summary_id").trim();
            System.out.println("summary id is ::::::::::::::::::::::::::::"+summary_id);
        program_area=cordinator=districts=agency=venue=curriculum=start_date=end_date=training_name=s_male=s_female=d_male=d_female=date_range=nurse=rco=hrio=dclerk=pharmtec=labtec=mo=couns=hts_screener=acouns=linkdesk=mmother=mfather=expclient=chv=sto=to=tio=meo=mea=hro=dma=otherc="";
        program_area_id=district_ids=curriculum_id=possible_districts="";
        
        lock="";
        gender="";
        input="submit";
        hidden="hidden";
        hiddenx=edit="";
       int counter=0;
         String get_summary="SELECT id,program_area_id,cordinator,districts,agency,venue,curriculum_id,"
                 + "start_date,end_date,training_name,s_male,s_female,d_male,d_female,nurse,rco,hrio,dclerk,pharmtec,labtec,mo,couns,hts_screener,acouns,linkdesk,mmother,mfather,expclient,chv,sto,`to`,tio,meo,mea,hro,dma,otherc "
                 + "FROM t1_summary WHERE id=?";
         conn.pst=conn.conn.prepareStatement(get_summary);
         conn.pst.setString(1,summary_id);
         conn.rs=conn.pst.executeQuery();
         while(conn.rs.next()){
             counter++;
         summary_id = conn.rs.getString(1);
         program_area_id =  conn.rs.getString(2);
         cordinator =  conn.rs.getString(3);
         district_ids =  conn.rs.getString(4);
         agency =  conn.rs.getString(5);
         venue =  conn.rs.getString(6);
         curriculum_id =  conn.rs.getString(7);
         start_date =  conn.rs.getString(8).replace("-", "/");
         end_date =  conn.rs.getString(9).replace("-", "/");
         training_name =  conn.rs.getString(10);
         s_male =  conn.rs.getString(11);
         s_female =  conn.rs.getString(12);
         d_male =  conn.rs.getString(13);
         d_female = conn.rs.getString(14); 
         
         nurse = conn.rs.getString("nurse");
rco = conn.rs.getString("rco");
hrio = conn.rs.getString("hrio");
dclerk = conn.rs.getString("dclerk");
pharmtec = conn.rs.getString("pharmtec");
labtec = conn.rs.getString("labtec");
mo = conn.rs.getString("mo");
couns = conn.rs.getString("couns");
hts_screener = conn.rs.getString("hts_screener");
acouns = conn.rs.getString("acouns");
linkdesk = conn.rs.getString("linkdesk");
mmother = conn.rs.getString("mmother");
mfather = conn.rs.getString("mfather");
expclient = conn.rs.getString("expclient");
chv = conn.rs.getString("chv");
sto = conn.rs.getString("sto");
to = conn.rs.getString("to");
tio = conn.rs.getString("tio");
meo = conn.rs.getString("meo");
mea = conn.rs.getString("mea");
hro = conn.rs.getString("hro");
dma = conn.rs.getString("dma");
otherc = conn.rs.getString("otherc");

         
         date_range=start_date+" - "+end_date;
         lock="";
         input="hidden";
         hidden="";
         hiddenx="hidden";
session.setAttribute("summary_id", summary_id);
//session.setAttribute("districts", district_ids);

edit="<img src=\"images/edit.png\" data-toggle=\"tooltip\" id=\"edit_total\" title=\"click to edit totals.\" onclick=\"edit_totals();\">";
         }
        
         if(summary_id.equals("0")){
             session.removeAttribute("summary_id");
             summary_button_label = "Save Summary";
             btn_data="btn blue";
             bg="";
         }
         else{
         summary_button_label = "Update Summary";
         btn_data="btn ";
         bg="background-color:#ff901a;";
         }
         
         //GET DISTRICTS
         
         String getDistricts="SELECT DistrictID AS id,DistrictNom AS name FROM district WHERE active=1 ORDER BY name";
         conn.rs=conn.st.executeQuery(getDistricts);
         while(conn.rs.next()){
             String district_id=conn.rs.getString("id");
             String district_name=conn.rs.getString("name");
             if(district_ids.contains("*"+district_id+"*")){
                 districts+="<option value=\""+district_id+"\" selected>"+district_name+"</option>";
                 possible_districts+="<option value=\""+district_id+"\">"+district_name+"</option>";
             }
             else{
                 districts+="<option value=\""+district_id+"\">"+district_name+"</option>";
             }
        
         }
         
         
         
         //GET PROGRAM AREAS
         
         String getPrograms="SELECT * FROM t1_program_area where is_active='1'";
         conn.rs=conn.st.executeQuery(getPrograms);
         while(conn.rs.next()){
             String program_id=conn.rs.getString("id");
             String program_name=conn.rs.getString("name");
             if(program_id.equals(program_area_id)){
                 program_area+="<option value=\""+program_id+"\" selected>"+program_name+"</option>";
             }
             else{
                 program_area+="<option value=\""+program_id+"\">"+program_name+"</option>";
             }
         }
        
            System.out.println("venue is : "+venue);  
         //GET CURRICULUM
         
         String getcurriculum="SELECT * from t1_curriculum";
         conn.rs=conn.st.executeQuery(getcurriculum);
         while(conn.rs.next()){
             String curriculum_new_id=conn.rs.getString("id");
             String curriculum_name=conn.rs.getString("name");
             if(curriculum_id.contains("*"+curriculum_new_id+"*")){
                 curriculum+="<option value=\""+curriculum_new_id+"\" selected>"+curriculum_name+"</option>";
             }
             else{
                 curriculum+="<option value=\""+curriculum_new_id+"\">"+curriculum_name+"</option>";
             }
         }
         
         String getGender="SELECT * from t1_gender";
         conn.rs=conn.st.executeQuery(getGender);
         while(conn.rs.next()){
             String gender_v=conn.rs.getString("name");
             gender+="<option value=\""+gender_v+"\">"+gender_v+"</option>";
         }
        
         
            
     String  form="<form id=\"summary_form\" name=\"summary_form\" >"
             + "<fieldset class=\"formatter\">"
             + "<legend class=\"formatter\"><b style=\"text-align:center;\">A. Training Details</b></legend>"
             + "<div style=\"margin-left:2%;\"><div></div>"
             + "<div style=\"margin-top:30px;\">"
             + "<div class=\"div_title\"  style=\"\">Program Area :</div>"
             + "<div  class=\"div_data\" >"
             + "<select tabindex=\"1\"  name=\"progam_area\"  id=\"progam_area\" value=\"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" style=\"width: 22.5em;\">"
             + ""+program_area+""
             + "</select>"
             + "</div>"
            + "</div>"
             + "<div >"
              + "<div class=\"div_elem\" >"
             + "<div  class=\"div_title\" style=\"\">Name of Training <font color=\"red\">*</font>:</div>"
             + "<div  class=\"div_data\"><input type=\"text\"   autocomplete=\"off\" required placeholder=\"Name of Training\" tabindex=\"9\" name=\"training_name\" list=\"training_name_list\" id=\"training_name\" value=\""+training_name+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" style=\"width: 20em;\"></div>"
             + "</div>"
              
              + "<div class=\"clear\">"
              + "<div class=\"div_elem\" ><div class=\"div_title\" style=\"\">Training Coordinator:<font color='red'>*</font></div><div  class=\"div_data\" style=\"\" ><input type=\"text\"   autocomplete=\"off\"  placeholder=\"Name of training Coordinator\" required  tabindex=\"2\" name=\"cordinator\" id=\"cordinator\" list=\"cordinator_list\" value=\""+cordinator+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" style=\"width: 20em;\"></div></div>"
             + "<div class=\"div_elem\" ><div  class=\"div_title\" style=\"\">Sub-counties Participating:</div><div  class=\"div_data\" style=\"\"><select tabindex=\"3\"  name=\"districts\" id=\"districts\"  "+lock+"  data-placement=\"right\" placeholder=\"Select District\" class=\"readonly\" style=\"width: 22.5em;\" multiple>"+districts+" </select></div></div>"
              + "</div>"
               + "<div class=\"clear\">"
              + "<div class=\"div_elem\" ><div class=\"div_title\" style=\"\">Training Agency :</div><div  class=\"div_data\" ><input type=\"text\"   autocomplete=\"off\"  placeholder=\"e.g Usaid Tujenge Jamii\"  tabindex=\"4\" name=\"agency\" id=\"agency\" list=\"agency_list\" value=\""+agency+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" style=\"width: 20em;\"></div></div>"
              + "<div class=\"div_elem\" ><div  class=\"div_title\" style=\"\">Training venue <font color=\"red\">*</font>:</div><div  class=\"div_data\" ><input type=\"text\"   autocomplete=\"off\" required placeholder=\"Training venue\"  tabindex=\"5\" name=\"venue\" id=\"venue\" list=\"venue_list\" value=\""+venue+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" style=\"width: 20em;\"></div></div>"
              + "</div>"
               + "<div class=\"clear\">"
              + "<div class=\"div_elem\" ><div class=\"div_title\" style=\"\">Type of Curriculum Used :</div><div class=\"div_data\" ><select tabindex=\"6\" multiple name=\"curriculum_ids\" id=\"curriculum_ids\"  data-toggle=\"tooltip\"    "+lock+"  data-placement=\"right\" style=\"width: 22.5em;\">"+curriculum+"</select></div></div>"
              + "<div class=\"div_elem\" ><div  class=\"div_title\" style=\"\">Start Date - End Date <font color=\"red\">*</font>:</div><div  class=\"div_data\" ><input type=\"text\" readonly  autocomplete=\"off\" required placeholder=\"Date range\"  class=\"readonly\" tabindex=\"7\" name=\"date_range\" id=\"date_range\" value=\""+date_range+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" style=\"width: 20em;\"></div></div>"
//              + "<div class=\"div_elem\" ><div  class=\"div_title\" style=\"\">End Date <font color=\"red\">*</font>:</div><div  class=\"div_data\" ><input type=\"text\"   autocomplete=\"off\" required placeholder=\"End Date\"  tabindex=\"8\" name=\"end_date\" id=\"end_date\" value=\""+end_date+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" style=\"width: 10em;\"></div></div>"
              + "</div>"
               
             + "<div style='float:left;'>"
             + "<div class='clear'>"
              + "<hr/>"
             + "<b>Summary by Gender - Manual entries</b>"
             + "<br/>"
             + "<br/>"
             + "<div class='div_elem2' ><div  class='div_title' style=''>Male :<font color='red'>*</font></div><div  class='div_data' ><input required type='text'   autocomplete='off' placeholder='Male Participants'  tabindex='10' name='s_male' id='s_male' value='"+s_male+"'  data-toggle='tooltip'  "+lock+" onkeypress='return numbers(event)'  data-placement='right' style='width: 10em;'></div></div>"
              + "<div class='div_elem2' ><div  class='div_title' style=''>Female :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Female Participants'  tabindex='11' name='s_female' id='s_female' value='"+s_female+"'  data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;'></div></div>"
//              + "<div class='div_elem2' ><div  class='div_title' style=''>"+edit+"</div>"
            + "</div>"
            
             
             
             
             + ""
             + "<div class=\"clear\">"
             + "<hr/>"
             + "<b>Summary by Gender - Auto Generated from Participants List</b>"
             + "<br/>"
             + "<br/>"
             + "<div class=\"div_elem2\" ><div  class=\"div_title\" style=\"\"> Male :</div><div  class=\"div_data\" ><input type=\"text\" disabled   autocomplete=\"off\" placeholder=\"Male Participants\"  tabindex=\"10\" name=\"d_male\" id=\"d_male\" value=\""+d_male+"\"  data-toggle=\"tooltip\"  "+lock+" onkeypress=\"return numbers(event)\"  data-placement=\"right\" style=\"width: 10em;\"></div></div>"
              + "<div class=\"div_elem2\" ><div  class=\"div_title\" style=\"\">Female :</div><div  class=\"div_data\" ><input type=\"text\" disabled  autocomplete=\"off\" placeholder=\"Female Participants\"  tabindex=\"11\" name=\"d_female\" id=\"d_female\" value=\""+d_female+"\"  data-toggle=\"tooltip\" onkeypress=\"return numbers(event)\"  "+lock+"  data-placement=\"right\" style=\"width: 10em;\"></div></div>"
              + "</div>"
             + "</div>"
             + "</div>"
             + "<div class='clear'>"
             + "<hr/>"
             + "<b>Summary By Cadre</b>"
             + "<hr/>"
+"<div class='div_elem2' ><div  class='div_title' >Nurse :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Nurses'  tabindex='10' name='nurse' id='nurse'  value='"+nurse+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Registered Clinical officer :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Registered Clinical officers'  tabindex='11' name='rco' id='rco'  value='"+rco+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Health Records Information Officer :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Health Records Information Officers'  tabindex='12' name='hrio' id='hrio'  value='"+hrio+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Data clerk :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Data clerks'  tabindex='13' name='dclerk' id='dclerk'  value='"+dclerk+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Pharmaceutical Technologist :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Pharmaceutical Technologists'  tabindex='14' name='pharmtec' id='pharmtec'  value='"+pharmtec+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Laboratory Technologist :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Laboratory Technologists'  tabindex='15' name='labtec' id='labtec'  value='"+labtec+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Medical officer :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Medical officers'  tabindex='16' name='mo' id='mo'  value='"+mo+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >HTS counselor :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='HTS counselors'  tabindex='17' name='couns' id='couns'  value='"+couns+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >HTS screener :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='HTS screeners'  tabindex='18' name='hts_screener' id='hts_screener'  value='"+hts_screener+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Adherence counselor :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Adherence counselors'  tabindex='19' name='acouns' id='acouns'  value='"+acouns+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Link Desk :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Link Desks'  tabindex='20' name='linkdesk' id='linkdesk'  value='"+linkdesk+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Mentor Mother :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Mentor Mothers'  tabindex='21' name='mmother' id='mmother'  value='"+mmother+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Mentor Father :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Mentor Fathers'  tabindex='22' name='mfather' id='mfather'  value='"+mfather+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Expert Client :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Expert Clients'  tabindex='23' name='expclient' id='expclient'  value='"+expclient+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Community Health Volunteer :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Community Health Volunteers'  tabindex='' name='chv' id='chv'  value='"+chv+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Senior Technical Officer :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Senior Technical Officers'  tabindex='24' name='sto' id='sto'  value='"+sto+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Technical Officer :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Technical Officers'  tabindex='25' name='to' id='to'  value='"+to+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Technical Integration Officer :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Technical Integration Officers'  tabindex='26' name='tio' id='tio'  value='"+tio+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >M&E Officer :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='M&E Officers'  tabindex='27' name='meo' id='meo'  value='"+meo+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >M&E Associate :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='M&E Associates'  tabindex='28' name='mea' id='mea'  value='"+mea+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Human Resource Officer :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Human Resource Officers'  tabindex='31' name='hro' id='hro'  value='"+hro+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Data Management Assistant :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Data Management Assistants'  tabindex='32' name='dma' id='dma'  value='"+dma+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"
+"<div class='div_elem2' ><div  class='div_title' >Others :<font color='red'>*</font></div><div  class='div_data' ><input type='text' required   autocomplete='off' placeholder='Otherss'  tabindex='33' name='otherc' id='otherc'  value='"+otherc+"'   data-toggle='tooltip' onkeypress='return numbers(event)'  "+lock+"  data-placement='right' style='width: 10em;margin-top:3px;margin-bottom:3px;'></div></div>"

             + "</div>"
             
                          
             
             + "<div class=\"clear\">"
              + "<div style=\"width: 100%; margin-right:20%;\" ><button type=\""+input+"\" onClick=\"return subSummary();\" hidden class=\""+btn_data+"\" tabindex=\"12\" id=\"summarybtn\" style=\""+bg+" margin-right:35px;\">"+summary_button_label+"</button></div>"
             + "</div>"
             + "</form>"
             + "</div>"
             + "<br>"
             + "</fieldset>"
             + "";
     
     if(counter>0){
           form +="<form id=\"form_participant\" name=\"form_participant\"  "+hidden+" onsubmit=\"return save_participants();\">"
                   + "<div class=\"new_participant\">"
             + "<div class=\"div_new\">"
                   + "<input type=\"text\" tabindex=\"13\" placeholder=\"Participant Name\" autocomplete=\"off\" name=\"participant_name\" list=\"participant_name_list\" required max-length=\"50\" id=\"participant_name\" value=\"\"  data-toggle=\"tooltip\"  data-placement=\"right\" style=\" min-width:100px;  max-width:170px;\">"
             + "</div>"
             + "<div class=\"div_new\">"
                   + "<select tabindex=\"14\"  placeholder=\"Choose Gender\" name=\"gender\" required id=\"gender\"  data-toggle=\"tooltip\"  data-placement=\"right\"  style=\" min-width:30px; max-width:40px;\">"+gender+"</select>"
             + "</div>"
             + "<div class=\"div_new\">"
                   + "<input type=\"text\"  placeholder=\"Cadre\" tabindex=\"15\" name=\"profession\"  autocomplete=\"off\" id=\"profession\"  list=\"profession_list\" value=\"\"  data-toggle=\"tooltip\"  data-placement=\"right\"  style=\" min-width:100px; max-width:170px;\">"
             + "</div>"
             + "<div class=\"div_new\">"
                   + "<input type=\"text\"  placeholder=\"Personal Number(MOH Staff only)\" tabindex=\"16\"  autocomplete=\"off\" name=\"personal_no\" list=\"personal_no_list\" id=\"personal_no\" value=\"\"  data-toggle=\"tooltip\"  data-placement=\"right\"  style=\" min-width:100px; max-width:170px;\">"
             + "</div>"
             + "<div class=\"div_new\">"
                   + "<input type=\"text\"  placeholder=\"Name of organization/facility\" tabindex=\"17\"  autocomplete=\"off\" name=\"organization\" list=\"organization_list\" id=\"organization\" value=\"\"  data-toggle=\"tooltip\"  data-placement=\"right\"  style=\" min-width:100px; max-width:170px;\">"
             + "</div>"
             + "<div class=\"div_new\">"
                   + "<select tabindex=\"18\"  placeholder=\"Sub-county\" name=\"district\" required id=\"district\"  autocomplete=\"off\" data-toggle=\"tooltip\" data-placement=\"right\"  style=\" min-width:100px; max-width:170px;\">"+possible_districts+"</select>"
             + "</div>"
             + "<div class=\"div_new\">"
                   + "<input type=\"text\"  placeholder=\"Telephone No.\" tabindex=\"19\" name=\"telephone\"  autocomplete=\"off\" id=\"telephone\" list=\"telephone_list\" value=\"\" onkeypress=\"return numbers(event)\"  data-toggle=\"tooltip\"  data-placement=\"right\"    style=\" min-width:100px; max-width:170px;\">"
             + "</div>"
             + "<div class=\"div_new\">"
                   + "<input type=\"text\"  placeholder=\"Address (Email/Postal)\" tabindex=\"20\" name=\"address\"  autocomplete=\"off\" list=\"address_list\" id=\"address\" value=\"\"  data-toggle=\"tooltip\"  style=\" min-width:100px; max-width:170px;\">"
             + "</div>"
             + "<div class=\"div_new\">"
                   + "<button type=\"submit\" class=\"btn blue\" tabindex=\"21\" id=\"save_participant\" onmouseover=\"save_participantsr();\" style=\" min-width:60px; max-width:90px;\">Save</button>"
             + "</div>"
             + "</div>"
             + ""
                   + "<br><br>";   
           
           form+=""
                   + ""
                   + "<datalist id=\"participant_name_list\">"
                   + ""+getlist(conn,"t1_details","participant_name",summary_id)+"" 
                   + "</datalist>"
                   + ""
                   + "<datalist id=\"profession_list\">"
                   + ""+getlist(conn,"t1_details","profession",summary_id)+"" 
                   + "</datalist>"
                   + ""
                   + "<datalist id=\"personal_no_list\">"
                   + ""+getlist(conn,"t1_details","personal_no",summary_id)+"" 
                   + "</datalist>"
                   + ""
                   + "<datalist id=\"organization_list\">"
                   + ""+getlist(conn,"t1_details","organization",summary_id)+"" 
                   + "</datalist>"
                   + ""
                   + "<datalist id=\"telephone_list\">"
                   + ""+getlist(conn,"t1_details","telephone",summary_id)+"" 
                   + "</datalist>"
                   + ""
                   + ""
                   + "<datalist id=\"address_list\">"
                   + ""+getlist(conn,"t1_details","address",summary_id)+"" 
                   + "</datalist>"
                   + "</form>"
                   + "";
     }
     else{
      form+=""
                   + ""
                   + "<datalist id=\"cordinator_list\">"
                   + ""+getlist(conn,"t1_summary","cordinator","")+"" 
                   + "</datalist>"
                   + ""
                   + "<datalist id=\"agency_list\">"
                   + ""+getlist(conn,"t1_agencies","name","")+"" 
                   + "</datalist>"
                   + ""
                   + "<datalist id=\"venue_list\">"
                   + ""+getlist(conn,"t1_summary","venue","")+"" 
                   + "</datalist>"
                   + ""
                   + "<datalist id=\"training_name_list\">"
                   + ""+getlist(conn,"t1_summary","training_name","")+"" 
                   + "</datalist>"
                   + "</form>"
                   + "";    
     }
     

            out.println(form);
            
            System.out.println("output : "+form);
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
        Logger.getLogger(LoadSummary.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(LoadSummary.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getlist(dbConn conn,String table_name, String column_name, String summary_id) throws SQLException{
    String response="",where=""; 
//    if(table_name.equals("t1_details")){
//        where=" WHERE summary_id='"+summary_id+"'";
//    }
    String selector="SELECT DISTINCT("+column_name+") AS data FROM "+table_name+" "+where+"";
        System.out.println(""+selector);
    conn.rs=conn.st.executeQuery(selector);
    while(conn.rs.next()){
        response+="<option value=\""+conn.rs.getString(1)+"\" label=\""+conn.rs.getString(1)+"\">";       
    }
    return response;
    }
}
