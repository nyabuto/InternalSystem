/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checklist;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GNyabuto
 */
public class loadchecklist extends HttpServlet {

    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String database_name,section_name,section_code,section_label,value,data_source="";
int indic_counter;
  String save_data = null;
  int num_saved_elems;
  String btn_color = "blue";
        
        try {
            dbConn conn = new dbConn();
            session = request.getSession();
           String supported_services = " WHERE  is_active=1 && ";
                        
            if(request.getParameter("database_name")!=null && !request.getParameter("database_name").equals("") ){
             supported_services += " database_name='"+request.getParameter("database_name")+"' ";
            }
            else {
                
             supported_services +=" 1=1 ";   
                
            }
            
                      
                      
            String output="", lock="",lockUser = "", enterdby, validity, Header;

            String year, month, yearmonth, isLocked,isLockedUser,facil;


            year = "";
            month = "";
            facil = "";
            //if a user is only allowed to read the data
            String userreadonly="";
            
//            if (session.getAttribute("f1a_readonly") != null) {
//                userreadonly = session.getAttribute("f1a_readonly").toString();
//            }

            if (session.getAttribute("year") != null) {
                year = session.getAttribute("year").toString();
            }
            if (session.getAttribute("monthid") != null) {
                month = session.getAttribute("monthid").toString();
            }

            if (session.getAttribute("facilityid") != null) {
                facil = session.getAttribute("facilityid").toString();
            }
            
            
            System.out.println(" DQA Checklist upload for :"+facil+" for period "+year+""+month);
            String support_column_name,support_column_value;
            int num_serv_supported=0;
            // READ FACILITY SUPPORTED SERVICES
            String get_supported_service = "SELECT IFNULL(PMTCT,0) AS PMTCT,IFNULL(ART,0) AS ART,IFNULL(VMMC,0) AS VMMC,IFNULL(HTC,0) AS HTC,IFNULL(Gender,0) AS Gender,IFNULL(PNS,0) AS PNS, IFNULL(IPD,0) AS IPD FROM subpartnera WHERE SubPartnerID='"+facil+"'";
            //System.out.println(""+get_supported_service);
            conn.rs = conn.st.executeQuery(get_supported_service);
               ResultSetMetaData metaData = conn.rs.getMetaData();
                int col_count = metaData.getColumnCount(); //number of column
             if(conn.rs.next()){
                 supported_services +=" AND (";
              for(int i=1;i<=col_count; i++){
                support_column_name = metaData.getColumnLabel(i);
                support_column_value=conn.rs.getString(support_column_name);
                
                if(support_column_value.equals("1")){
                    num_serv_supported++;
                  supported_services+=support_column_name+"="+support_column_value+" OR ";    
                }
                }
              
              if(num_serv_supported>0){
               supported_services = removeLast(supported_services, 3)+")";
              }
            }
            
            //System.out.println("Supported services : "+supported_services);
            //From the facility supported services, load the list of indications in the indicators table source
            
            
          String getsections = "SELECT section_name,database_name FROM mne_cl_indicators "+supported_services+" GROUP BY section";
          
            //System.out.println(""+getsections);
          conn.rs2 = conn.st2.executeQuery(getsections);
          while(conn.rs2.next()){
            database_name = conn.rs2.getString("database_name");
//            database_name = "fas_art";
            
            //Create strings first which can be converted to arrays later
            String indicator_ids_string = "";
            String indicators_string = "";
            String main_indicator_string = "";
            String main_indic_rowspan_String = "";
            String disabledcolumnsstring = "";
            
             String autocalculatestring = "";
           
            
            int rowspancount = 1;
//            year = "2018";
//            month = "10";
//            facil = "383";

            section_name = "No Section";
            section_code="0";
            section_label="";
//            				

            String columns[] = {"recounted_register_emr","moh731","form1a","concordance","khis"};
            //load indicators from the table
            String tbls = "select * from mne_cl_indicators where database_name='" + database_name + "' and is_active='1' AND ("+supported_services.replace("WHERE", "")+") order by order_no asc";
             // System.out.println("indicators per table :"+tbls);
            conn.rs = conn.st.executeQuery(tbls);

            while (conn.rs.next()) {
                //the column lastspanrow should be used to determine where the row ends. 
                //If its that row, use character $ to denote starting of a new range of arrays
                data_source = conn.rs.getString("section_name");
                section_name = conn.rs.getString("main_indicator");
                section_code = conn.rs.getString("section");
                section_label = conn.rs.getString("section_label");
                
                
                
                if (conn.rs.getString("lastspanrow").equals("1")) {
String code="";

if(conn.rs.getString("code")!=null){code="["+conn.rs.getString("code")+"]";}
                    indicator_ids_string += "" + conn.rs.getString("id") + "$,";
                    indicators_string += "" + conn.rs.getString("indicator")+"<br><b style=\"color: #6c00ff\">"+code+"</b>" + "$,";
                    main_indicator_string += "" + conn.rs.getString("main_indicator") + ",";
                    main_indic_rowspan_String += "" + rowspancount + ",";

                    if (conn.rs.getString("disabledcolumns") != null) {
                        disabledcolumnsstring += "" + conn.rs.getString("disabledcolumns") + "$%";
                    } else {
                        disabledcolumnsstring += " " + "$%";
                    }
                    
                    //autocalculate string
                    if (conn.rs.getString("autocalculate") != null) {
                        autocalculatestring += "" + conn.rs.getString("autocalculate") + "$";
                    } else 
                    {
                        autocalculatestring += " " + "$";
                    }
                   

                    rowspancount = 0;
                } else {
                    String code="";

if(conn.rs.getString("code")!=null){code="["+conn.rs.getString("code")+"]";}

                    indicator_ids_string += conn.rs.getString("id") + ",";
                    indicators_string += conn.rs.getString("indicator")+"<br><b style=\"color: #6c00ff\">"+code+"</b>" + ",";

                    if (conn.rs.getString("disabledcolumns") != null) 
                    {
                        disabledcolumnsstring += conn.rs.getString("disabledcolumns") + "%";
                    } 
                    else 
                    {
                        disabledcolumnsstring += " " + "%";
                    }
                    
                    
                     
                  //autocalculate string
                    if (conn.rs.getString("autocalculate") != null) {
                        autocalculatestring += "" + conn.rs.getString("autocalculate") + "$";
                    } else 
                    {
                        autocalculatestring += " " + "$";
                    }
                   
              
                }

                rowspancount++;
//9
            }//end of while loop

            //add a static character at the end of the row inorder to do a replace
            indicator_ids_string += ")";
            indicators_string += ")";
            main_indicator_string += ")";
            main_indic_rowspan_String += ")";
            disabledcolumnsstring += ")";
            
            //__________________________________________
            autocalculatestring += ")";
          

            //for each set, replace $, with just $
            indicator_ids_string = indicator_ids_string.replace("$,", "$").replace(",)", "");
            indicators_string = indicators_string.replace("$,", "$").replace(",)", "");
            main_indicator_string = main_indicator_string.replace("$,", "$").replace(",)", "");
            main_indic_rowspan_String = main_indic_rowspan_String.replace("$,", "$").replace(",)", "");
            disabledcolumnsstring = disabledcolumnsstring.replace("$%", "$");
            //--------------
             autocalculatestring = autocalculatestring.replace("$)", "");
            

            String indicator_ids[] = indicator_ids_string.split("\\$");
            String indicators[] = indicators_string.split("\\$");
            String main_indicator[] = main_indicator_string.split(",");
            String main_indic_rowspan[] = main_indic_rowspan_String.split(",");
            String disabledcolumns_arr[] = disabledcolumnsstring.split("\\$");
            
            //____________________________________________________
	    String autocalculate_arr[]= autocalculatestring.split("\\$");
            
//            System.out.println(" status ya autocalculate string "+autocalculatestring+" Length ya Array "+autocalculate_arr.length);
            
            
            int indic_pos = 0, main_indic_pos = 0, max_length = 5;

            //   tableid="2018_10_331";
            lock = "";

            validity = "<b><font color='#4b8df8'>New Entry</font></b>";



            int Programareadone = 0;
            int Programareaundone = 0;
            int Programareavalid = 0;
            int facilssupporting = 0;
            String distid = "";

            if (session.getAttribute("subcountyid") != null) {
                distid = session.getAttribute("subcountyid").toString();
            }
            yearmonth = "";
            String tempmonth = month;
            int pepfaryear = Integer.parseInt(year);
            if (Integer.parseInt(month) < 10) {
                tempmonth = "0" + month;
            } else {
                pepfaryear--;
            }

            yearmonth = pepfaryear + "" + tempmonth;
            isLocked = "0";
            
            
            if(userreadonly.equals("1")){
              isLockedUser = "1";
                lockUser = " tabindex='-1' readonly='true' ";
                                        }
            
            String locked_DATA = "SELECT id FROM locked_data WHERE yearmonth=? AND checklist=?";
            conn.pst = conn.conn.prepareStatement(locked_DATA);
            conn.pst.setString(1, yearmonth);
            conn.pst.setInt(2, 1);
            conn.rs = conn.pst.executeQuery();
            if (conn.rs.next()) {
                isLocked = "1";
                lock = " tabindex='-1' readonly='true' ";
            }

              //System.out.println("is indicator locked?"+isLocked);
            enterdby = "";
            
            output += "<div class=\"card\">\n" +
            "    <div class=\"card-header\" id=\"headingART\">\n" +
            "      <h5 class=\"mb-0\">\n" +
            "        <button class=\"btn blue collapsed\"  id=\"section_"+section_code+"\" type=\"button\" data-toggle=\"collapse\" data-target=\"#collapse"+section_code+"\" aria-expanded=\"false\" aria-controls=\"collapse"+section_code+"\" style=\"width:35%; text-align:left;background-color:section_label_button_color;font-weight: bolder;\">\n" +
            "          "+section_name+" &nbsp;&nbsp;&nbsp; section_label_tick\n" +
            "        </button>\n" +
            "      </h5>\n" +
            "    </div>\n" +
            "    <div id=\"collapse"+section_code+"\" class=\"collapse\" aria-labelledby=\"heading"+section_code+"\" data-parent=\"#form1a_accordion\">\n" +
            "      <div class=\"card-body\">\n" +
            "          <div id=\"table\" style=\"margin-right:0%\"> "+
                  " <fieldset class='formatter' style=\"margin:20px; color:black;\"><legend class='formatter'><b style='text-align:center;color:green;'> "+section_name+" </b><i style='color:red;'>("+data_source+")</i></legend>" +
                 " <form action=\"#\" id=\""+database_name+"\" method=\"post\" class=\"form-horizontal\">"+
                  " <table  border=\"1px;\">";
            output += "<table  border='1px;'>";
            output += "<tr style=\"font-weight:bold; background:#a9c7e4;\">"
                    + "<td rowspan='2'>Program Area</td>"
                    + "<td rowspan='2'>Indicator</td>"
                    + "<td colspan='1' >Recounted Data</td>"
                    + "<td colspan='2' rowspan='1'>Data In Monthly Report</td>"
                    + "<td colspan='1' rowspan='2'>% Variance</td>"
                    + "<td colspan='1' rowspan='2'>Data in KHIS</td>"
                    
                    + "</tr>";
            output += "<tr  style=\"font-weight:bold; background:#a9c7e4;\">"
                    + "<td>Register/EMR</td><td>MOH 731</td><td>Form 1a / Datim</td>"
                   
                    + "</tr>";

            main_indic_pos = 0;
            indic_counter = 0;
            num_saved_elems=0;
            for (String main_indic : main_indicator) {
                indic_pos = 0;
                //System.out.println("main indicator pos = " + main_indic_pos);
              //System.out.println("main indicator  = " + main_indicator_string);
//                System.out.println("Indicator id string = " + indicator_ids_string);
//                System.out.println("Indicator id  = " + indicator_ids[main_indic_pos]);

                for (String indic_id : indicator_ids[main_indic_pos].split(",")) {
                    
                    indic_counter++;
           
//            System.out.println( " indicator names  = " +main_indic);
                    isLocked = "0";
                    lock = "";
                    String tableid = yearmonth + "_" + facil + "_" + indic_id;
                    String get_data = "SELECT * FROM " + database_name + " WHERE id=?";
                    conn.pst = conn.conn.prepareStatement(get_data);
                    conn.pst.setString(1, tableid);
                    //System.out.println("query: "+conn.pst);
                    conn.rs = conn.pst.executeQuery();
                    if (conn.rs.next()) { // indicator data already exist
                        if(conn.rs.getString("is_locked")!=null){
                            if(conn.rs.getString("is_locked").equals("1")){
                              isLocked = "1";
                                lock = " tabindex='-1' readonly='true' ";   
                            }
                            
                        }
                        save_data="UPDATE ";
                        num_saved_elems++;
                        output += "<tr>";
                        if (indic_pos == 0) {
                            output += "<td rowspan='" + main_indic_rowspan[main_indic_pos] + "'>" + main_indic + "</td>";
                        }
                        output += "<td>" + indicators[main_indic_pos].split(",")[indic_pos] + "<input type=\"hidden\" id=\"indic_pos_"+indic_counter+"\" name=\"indic_pos_"+indic_counter+"\" value=\""+indic_id+"\"></td>";
                       // System.out.println("column length :"+columns.length);
                       
                        String autocalc = "";
                             if (autocalculate_arr[main_indic_pos]!=null && !autocalculate_arr[main_indic_pos].trim().equals("") ) {
                             
                                 
                                 autocalc = " concordance("+autocalculate_arr[main_indic_pos].replace("%", "_"+indic_id)+"); ";
                            }
                       
                        int col_counter=0;
                        for (String column_name : columns) {
                            col_counter++;
                           // System.out.println("column counter : "+col_counter);
                             if(conn.rs.getString(column_name)!=null){
                                value = conn.rs.getString(column_name);
//                                if(isNumeric(value)){
//                                totals+=Integer.parseInt(value);
//                                        }
                            }
                            else{value="";}
                             
//                             System.out.println( " disabled columns  = " + disabledcolumns_arr[main_indic_pos].split("%")[indic_pos]);
                            
                            String isreadonly = "";
                            //String autocalc = "";
                           
                            
                            if (disabledcolumns_arr[main_indic_pos].split("%")[indic_pos].contains("," + column_name + ",")) {
                                isreadonly = " tabindex='-1' readonly='true' ";
                            }
                            
                             if(!lockUser.equals("")){isreadonly=lockUser;}
                             
                            
//                         if (autocalculate_arr[main_indic_pos].split("$")[indic_pos]!=null && !autocalculate_arr[main_indic_pos].split("$")[indic_pos].trim().equals("") ) {
//                                autocalc = " autocalculate("+autocalculate_arr[main_indic_pos].split("$")[indic_pos].replace("%", "_"+indic_id)+"); ";
//                            }    
                             
                             
//                        if (!autocalculatestring.trim().equals("") ) 
//                           {
//                                
//                                autocalculatestring=autocalculatestring.replace("%", "_"+indic_id);
//                                autocalc = " concordance("+autocalculatestring+"); ";
//                                
//                           }
                            //System.out.println(" tunacheckcheck "+autocalc);
                           // System.out.println("is readonly:-"+isreadonly+"-autocalc:"+autocalc);
                            if(isreadonly.equals("")){
                                if(col_counter==columns.length){
                             output += "<td><input onkeyup=\"removeFirstZero('" + column_name + "_" + indic_id + "');\" " + isreadonly + " type='text'  name='" + column_name + "_" + indic_id + "' id='" + column_name + "_" + indic_id + "' value='" + value + "' onblur=\"indicate_changed('" + column_name + "_" + indic_id + "'); section_changed('"+section_code+"'); "+autocalc+" \" class='data-cell' data-toggle='tooltip'  " + lock + "  data-placement='right' autocomplete='off' maxLength='" + max_length + "' onkeypress=\"return numbers(event);\" ></td>";
                                }
                                else{
                            output += "<td><input onkeyup=\"removeFirstZero('" + column_name + "_" + indic_id + "');\" " + isreadonly + " type='text'  name='" + column_name + "_" + indic_id + "' id='" + column_name + "_" + indic_id + "' value='" + value + "' onblur=\"indicate_changed('" + column_name + "_" + indic_id + "'); section_changed('"+section_code+"'); "+autocalc+" \" onkeyup=\"\" class='data-cell' data-toggle='tooltip'  " + lock + "  data-placement='right' autocomplete='off' maxLength='" + max_length + "' onkeypress=\"return numbers(event);\" ></td>";
                                }
                                }
                            else{
                            output += "<td><input onkeyup=\"removeFirstZero('" + column_name + "_" + indic_id + "');\" " + isreadonly + " type='text'  name='" + column_name + "_" + indic_id + "' id='" + column_name + "_" + indic_id + "' value='" + value + "' onblur=\"indicate_changed('" + column_name + "_" + indic_id + "'); section_changed('"+section_code+"'); "+autocalc+" \" class='data-cell' data-toggle='tooltip'  " + lock + "  data-placement='right' autocomplete='off' maxLength='" + max_length + "' onkeypress=\"return numbers(event);\" ></td>";
                            }
                            }
                        output += "<p id='" + indic_id + "'></p></tr>";
                    } else { // new indicator
                        save_data="SAVE ";
                        output += "<tr>";

                        if (indic_pos == 0) 
                        {
                            output += "<td rowspan='" + main_indic_rowspan[main_indic_pos] + "'>" + main_indic + "</td>";
                        }
                       // System.out.println(indicators[main_indic_pos]+"The indicator is : "+indicators[main_indic_pos].split(",")[indic_pos]);
                        output += "<td>" + indicators[main_indic_pos].split(",")[indic_pos] + "<input type=\"hidden\" id=\"indic_pos_"+indic_counter+"\" name=\"indic_pos_"+indic_counter+"\" value=\""+indic_id+"\"></td>";
                        int col_counter=0;
                        
//                           System.out.println(" tunacheckcheck 2 "+autocalculate_arr[main_indic_pos]+" Na hapa ni "+indic_pos);
                             String autocalc = "";
                             if (autocalculate_arr[main_indic_pos]!=null && !autocalculate_arr[main_indic_pos].trim().equals("") ) 
                           {
                             
                                 
                                 autocalc = " concordance("+autocalculate_arr[main_indic_pos].replace("%", "_"+indic_id)+"); ";
                            } 
                        
                        for (String column_name : columns) {
                            col_counter++;
//                             System.out.println( " disabled columns  = " + disabledcolumns_arr[main_indic_pos].split("%")[indic_pos]);
                            
                            String isreadonly = "";
                            
                            
                            if (disabledcolumns_arr[main_indic_pos].split("%")[indic_pos].contains("," + column_name + ",")) 
                            {
                                isreadonly = " tabindex='-1' readonly='true' ";
                            }
                            
                             if(!lockUser.equals("")){isreadonly=lockUser;}
                             
                               
                             
                             
//                       if (!autocalculatestring.trim().equals("") ) 
//                       {
//                                autocalculatestring=autocalculatestring.replace("%", "_"+indic_id);
//                                autocalc = " concordance("+autocalculatestring+"); ";
//                        }
//                       else {
//                       autocalc = "";
//                       }

                            if(isreadonly.equals(""))
                               {
                                if(col_counter==columns.length){
                                   output += "<td><input onkeyup=\"removeFirstZero('" + column_name + "_" + indic_id + "');\" " + isreadonly + " type='text' name='" + column_name + "_" + indic_id + "' id='" + column_name + "_" + indic_id + "' value=''  onblur=\"indicate_changed('" + column_name + "_" + indic_id + "'); section_changed('"+section_code+"'); "+autocalc+" \" class='data-cell' data-toggle='tooltip'  " + lock + "  data-placement='right' autocomplete='off' maxLength='" + max_length + "' onkeypress=\"return numbers(event);\" ></td>";
                                }
                                else{
                                output += "<td><input onkeyup=\"removeFirstZero('" + column_name + "_" + indic_id + "');\" " + isreadonly + " type='text' name='" + column_name + "_" + indic_id + "' id='" + column_name + "_" + indic_id + "' value='' onblur=\"indicate_changed('" + column_name + "_" + indic_id + "'); section_changed('"+section_code+"'); "+autocalc+" \" onkeyup=\" \" class='data-cell' data-toggle='tooltip'  " + lock + "  data-placement='right' autocomplete='off' maxLength='" + max_length + "' onkeypress=\"return numbers(event);\" ></td>";
                                }
                                }
                            else{
                                 output += "<td><input onkeyup=\"removeFirstZero('" + column_name + "_" + indic_id + "');\"  " + isreadonly + " type='text' name='" + column_name + "_" + indic_id + "' id='" + column_name + "_" + indic_id + "' value='' \" onblur=\"indicate_changed('" + column_name + "_" + indic_id + "'); section_changed('"+section_code+"'); "+autocalc+" \" class='data-cell' data-toggle='tooltip'  " + lock + "  data-placement='right' autocomplete='off' maxLength='" + max_length + "' onkeypress=\"return numbers(event);\" ></td>";
                            }
                                                           }
                        output += "<p id='" + indic_id + "'></p></tr>";
                    }

                    indic_pos++;
                }
                main_indic_pos++;
            }
         output+="</table>";
         output+="<input type=\"hidden\" name=\"num_indicators\" id=\"num_indicators\" value=\""+indic_counter+"\">";
         output+="<input type=\"hidden\" name=\"table_name\" id=\"table_name\" value=\""+database_name+"\">";

             // System.out.println("num saved : "+num_saved_elems);
         if(num_saved_elems>0){
          output = output.replace("section_label_button_color", "green");
          output = output.replace("section_label_tick", "<img src=\"images/checked.png\" style=\"\" alt=\"Entered\">");
         }
         else{
           output = output.replace("section_label_button_color", "#0394ff");
           output = output.replace("section_label_tick", "");
//           output = output.replace("section_label_button_color", "red");
         }
         
         
//         output+="<p id=\"submit_name\">"+submit_button_name+"</p>";
         output+="<div class='form-actions' style=\"text-align:right;\"><label id='msg"+section_code+"' style='text-align:left;color:red;'></label> &nbsp;  <button onmouseover='isDateEntered();' type='button' class='btn blue' data-save_"+section_code+"='"+save_data+""+section_label+"'  onclick=\"save_data('"+database_name+"','"+section_code+"');\" name='validate' id='validate_"+section_code+"' style=\"font-weight:700; font-size:20px; width:20%;\">"+save_data+""+section_label+"</button></div>"
                 + "</form>"
                 + " </fieldset>"
                 + "</div>\n" +
                "  </div>\n" +
                "  </div>\n" +
                "  </div>";
         
        }//end of sections while loop

        
        out.println(output);
           // System.out.println(""+output);
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
            Logger.getLogger(loadchecklist.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(loadchecklist.class.getName()).log(Level.SEVERE, null, ex);
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

       private static String removeLast(String str, int num) {
    return str.substring(0, str.length() - num);
}
       

 
 
 
 
 
       
       
       
}
