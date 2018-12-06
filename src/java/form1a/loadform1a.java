/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form1a;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSetMetaData;
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
public class loadform1a extends HttpServlet {

    HttpSession session;
String database_name,section_name,section_code,section_label,value="";
int indic_counter;
  String save_data;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            dbConn conn = new dbConn();
            session = request.getSession();
           String supported_services = " WHERE ";
                        
            if(request.getParameter("database_name")!=null && !request.getParameter("database_name").equals("") ){
             supported_services += " database_name='"+request.getParameter("database_name")+"' ";
            }
            else{
             supported_services +=" 1=1 ";   
            }
            
                      
                      
            String output="", lock, enterdby, validity, Header;

            String year, month, yearmonth, isLocked,facil;


            year = "";
            month = "";
            facil = "";

            if (session.getAttribute("year") != null) {
                year = session.getAttribute("year").toString();
            }
            if (session.getAttribute("monthid") != null) {
                month = session.getAttribute("monthid").toString();
            }

            if (session.getAttribute("facilityid") != null) {
                facil = session.getAttribute("facilityid").toString();
            }
            
            String support_column_name,support_column_value;
            int num_serv_supported=0;
            // READ FACILITY SUPPORTED SERVICES
            String get_supported_service = "SELECT IFNULL(PMTCT,0) AS PMTCT,IFNULL(ART,0) AS ART,IFNULL(VMMC,0) AS VMMC,IFNULL(HTC,0) AS HTC,IFNULL(Gender,0) AS Gender,IFNULL(PNS,0) AS PNS, IFNULL(IPD,0) AS IPD FROM subpartnera WHERE SubPartnerID='"+facil+"'";
            System.out.println(""+get_supported_service);
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
            
            
            
          String getsections = "SELECT section_name,database_name FROM fas_indicators "+supported_services+" GROUP BY section";
            System.out.println(""+getsections);
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
            String columns[] = {"m_uk", "f_uk", "m_1", "f_1", "m_4", "f_4", "m_9", "f_9", "m_14", "f_14", "m_19", "f_19", "m_24", "f_24", "m_29", "f_29", "m_34", "f_34", "m_39", "f_39", "m_44", "f_44", "m_49", "f_49", "m_50", "f_50","total"};
            //load indicators from the table
            String tbls = "select * from fas_indicators where database_name='" + database_name + "' and is_active='1' AND ("+supported_services.replace("WHERE", "")+") order by order_no asc";

            conn.rs = conn.st.executeQuery(tbls);

            while (conn.rs.next()) {
                //the column lastspanrow should be used to determine where the row ends. 
                //If its that row, use character $ to denote starting of a new range of arrays
                section_name = conn.rs.getString("section_name");
                section_code = conn.rs.getString("section");
                section_label = conn.rs.getString("section_label");
                if (conn.rs.getString("lastspanrow").equals("1")) {

                    indicator_ids_string += "" + conn.rs.getString("id") + "$,";
                    indicators_string += "" + conn.rs.getString("indicator") + "$,";
                    main_indicator_string += "" + conn.rs.getString("main_indicator") + ",";
                    main_indic_rowspan_String += "" + rowspancount + ",";

                    if (conn.rs.getString("disabledcolumns") != null) {
                        disabledcolumnsstring += "" + conn.rs.getString("disabledcolumns") + "$%";
                    } else {
                        disabledcolumnsstring += " " + "$%";
                    }
                    
                    //autocalculate string
                    if (conn.rs.getString("autocalculate") != null) {
                        autocalculatestring += "" + conn.rs.getString("autocalculate") + "$%";
                    } else {
                        autocalculatestring += " " + "$%";
                    }
                    

                    

                    rowspancount = 0;
                } else {

                    indicator_ids_string += conn.rs.getString("id") + ",";
                    indicators_string += conn.rs.getString("indicator") + ",";

                    if (conn.rs.getString("disabledcolumns") != null) {
                        disabledcolumnsstring += conn.rs.getString("disabledcolumns") + "%";
                    } else {
                        disabledcolumnsstring += " " + "%";
                    }
                    
                    
                     
                    //autocalculate
                     if (conn.rs.getString("autocalculate") != null) {
                        autocalculatestring += conn.rs.getString("autocalculate") + "%";
                    } else {
                        autocalculatestring += " " + "%";
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
             autocalculatestring = autocalculatestring.replace("$%", "$");
            

            String indicator_ids[] = indicator_ids_string.split("\\$");
            String indicators[] = indicators_string.split("\\$");
            String main_indicator[] = main_indicator_string.split(",");
            String main_indic_rowspan[] = main_indic_rowspan_String.split(",");
            String disabledcolumns_arr[] = disabledcolumnsstring.split("\\$");
            
            //____________________________________________________
	    String autocalculate_arr[]          = autocalculatestring.split("\\$");
           
            
            
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
            String locked_DATA = "SELECT id FROM locked_data WHERE yearmonth=? AND form1a=?";
            conn.pst = conn.conn.prepareStatement(locked_DATA);
            conn.pst.setString(1, yearmonth);
            conn.pst.setInt(2, 1);
            conn.rs = conn.pst.executeQuery();
            if (conn.rs.next()) {
                isLocked = "1";
                lock = "readonly=\"true\"";
            }

              System.out.println("is indicator locked?"+isLocked);
            enterdby = "";
            
            output += "<div class=\"card\">\n" +
            "    <div class=\"card-header\" id=\"headingART\">\n" +
            "      <h5 class=\"mb-0\">\n" +
            "        <button class=\"btn blue collapsed\" id=\"section_"+section_code+"\" type=\"button\" data-toggle=\"collapse\" data-target=\"#collapse"+section_code+"\" aria-expanded=\"false\" aria-controls=\"collapse"+section_code+"\" style=\"width:35%; text-align:left;\">\n" +
            "          "+section_name+"\n" +
            "        </button>\n" +
            "      </h5>\n" +
            "    </div>\n" +
            "    <div id=\"collapse"+section_code+"\" class=\"collapse\" aria-labelledby=\"heading"+section_code+"\" data-parent=\"#form1a_accordion\">\n" +
            "      <div class=\"card-body\">\n" +
            "          <div id=\"table\" style=\"margin-right:0%\"> "+
                  " <fieldset class='formatter' style=\"margin:20px; color:black;\"><legend class='formatter'><b style='text-align:center;'> "+section_name+"</b></legend>" +
                 " <form action=\"#\" id=\""+database_name+"\" method=\"post\" class=\"form-horizontal\">"+
                  " <table  border=\"1px;\">";
            output += "<table  border='1px;'>";
            output += "<tr style=\"font-weight:bold; background:#a9c7e4;\">"
                    + "<td rowspan='2'>Main Indicator</td>"
                    + "<td rowspan='2'>Indicator</td>"
                    + "<td colspan='2'>Unknown</td>"
                    + "<td colspan='2'><1</td>"
                    + "<td colspan='2'>1-4</td>"
                    + "<td colspan='2'>5-9</td>"
                    + "<td colspan='2'>10-14</td>"
                    + "<td colspan='2'>15-19</td>"
                    + "<td colspan='2'>20-24</td>"
                    + "<td colspan='2'>25-29</td>"
                    + "<td colspan='2'>30-34</td>"
                    + "<td colspan='2'>35-39</td>"
                    + "<td colspan='2'>40-44</td>"
                    + "<td colspan='2'>45-49</td>"
                    + "<td colspan='2'>50+</td>"
                    + "<td>Total</td>"
                    + "</tr>";
            output += "<tr  style=\"font-weight:bold; background:#a9c7e4;\"><td>M</td><td>F</td>"
                    + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                    + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                    + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                    + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                    + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                    + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                    + "<td>Total</td>"
                    + "</tr>";

            main_indic_pos = 0;
            indic_counter = 0;
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
                    conn.rs = conn.pst.executeQuery();
                    if (conn.rs.next()) { // indicator data already exist
                        if(conn.rs.getString("is_locked")!=null){
                            if(conn.rs.getString("is_locked").equals("1")){
                              isLocked = "1";
                                lock = "readonly=\"true\"";   
                            }
                            
                        }
                        save_data="UPDATE ";
                        output += "<tr>";
                        if (indic_pos == 0) {
                            output += "<td rowspan='" + main_indic_rowspan[main_indic_pos] + "'>" + main_indic + "</td>";
                        }
                        output += "<td>" + indicators[main_indic_pos].split(",")[indic_pos] + "<input type=\"hidden\" id=\"indic_pos_"+indic_counter+"\" name=\"indic_pos_"+indic_counter+"\" value=\""+indic_id+"\"></td>";
                        for (String column_name : columns) {
                            
                             if(conn.rs.getString(column_name)!=null){
                                value = conn.rs.getString(column_name);
//                                if(isNumeric(value)){
//                                totals+=Integer.parseInt(value);
//                                        }
                            }
                            else{value="";}
                             
//                             System.out.println( " disabled columns  = " + disabledcolumns_arr[main_indic_pos].split("%")[indic_pos]);
                            
                            String isreadonly = "";
                            String autocalc = "";
                            
                            
                            if (disabledcolumns_arr[main_indic_pos].split("%")[indic_pos].contains("," + column_name + ",")) {
                                isreadonly = " tabindex='-1' readonly='true' ";
                            }
                            
                            if (autocalculate_arr[main_indic_pos].split("%")[indic_pos]!=null && !autocalculate_arr[main_indic_pos].split("%")[indic_pos].trim().equals("") ) {
                                autocalc = " autocalculate("+autocalculate_arr[main_indic_pos].split("%")[indic_pos]+"); ";
                            }
                            
                            output += "<td><input " + isreadonly + " type='text'  name='" + column_name + "_" + indic_id + "' id='" + column_name + "_" + indic_id + "' value='" + value + "' onblur=\"indicate_changed('" + column_name + "_" + indic_id + "'); section_changed('"+section_code+"'); \" onkeyup=\"sum_indicators('"+indic_id+"'); "+autocalc+"\" class='data-cell' data-toggle='tooltip'  " + lock + "  data-placement='right' autocomplete='off' maxLength='" + max_length + "' onkeypress='return numbers(event)' ></td>";
                        }
                        output += "<p id='" + indic_id + "'></p></tr>";
                    } else { // new indicator
                        save_data="SAVE ";
                        output += "<tr>";

                        if (indic_pos == 0) 
                        {
                            output += "<td rowspan='" + main_indic_rowspan[main_indic_pos] + "'>" + main_indic + "</td>";
                        }
                        output += "<td>" + indicators[main_indic_pos].split(",")[indic_pos] + "<input type=\"hidden\" id=\"indic_pos_"+indic_counter+"\" name=\"indic_pos_"+indic_counter+"\" value=\""+indic_id+"\"></td>";
                        for (String column_name : columns) {
//                             System.out.println( " disabled columns  = " + disabledcolumns_arr[main_indic_pos].split("%")[indic_pos]);
                            
                            String isreadonly = "";
                            String autocalc = "";
                            
                            if (disabledcolumns_arr[main_indic_pos].split("%")[indic_pos].contains("," + column_name + ",")) 
                            {
                                isreadonly = " tabindex='-1' readonly='true' ";
                            }
                            
                            if (autocalculate_arr[main_indic_pos].split("%")[indic_pos]!=null && !autocalculate_arr[main_indic_pos].split("%")[indic_pos].trim().equals("") ) {
                                autocalc = " autocalculate("+autocalculate_arr[main_indic_pos].split("%")[indic_pos]+"); ";
                            }

                            output += "<td><input " + isreadonly + " type='text' name='" + column_name + "_" + indic_id + "' id='" + column_name + "_" + indic_id + "' value='' onblur=\"indicate_changed('" + column_name + "_" + indic_id + "'); section_changed('"+section_code+"');  \" onkeyup=\"sum_indicators('"+indic_id+"'); "+autocalc+"\" class='data-cell' data-toggle='tooltip'  " + lock + "  data-placement='right' autocomplete='off' maxLength='" + max_length + "' onkeypress='return numbers(event)' ></td>";
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

//         output+="<p id=\"submit_name\">"+submit_button_name+"</p>";
         output+="<div class='form-actions' style=\"text-align:right;\"><label id='msg"+section_code+"' style='text-align:left;color:red;'></label> &nbsp;  <button type='button' class='btn blue' data-save_"+section_code+"='"+save_data+""+section_label+"'  onclick=\"loadValidation('"+database_name+"','"+section_code+"');\" name='validate' id='validate_"+section_code+"' style=\"font-weight:700; font-size:20px; width:20%;\">"+save_data+""+section_label+"</button></div>"
                 + "</form>"
                 + " </fieldset>"
                 + "</div>\n" +
                "  </div>\n" +
                "  </div>\n" +
                "  </div>";
         
        }

        
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
            Logger.getLogger(loadform1a.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(loadform1a.class.getName()).log(Level.SEVERE, null, ex);
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
