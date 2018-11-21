/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form1a;

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
public class loadhiv extends HttpServlet {

    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            dbConn conn = new dbConn();
            session = request.getSession();

            String database_name = "fas_hts";

            String output, lock, enterdby, validity, Header;

            String year, month, yearmonth, isLocked;

            String columns[] = {"m_uk", "f_uk", "m_1", "f_1", "m_4", "f_4", "m_9", "f_9", "m_14", "f_14", "m_19", "f_19", "m_24", "f_24", "m_29", "f_29", "m_34", "f_34", "m_39", "f_39", "m_44", "f_44", "m_49", "f_49", "m_50", "f_50","total"};
            //load indicators from the table
            String tbls = "select * from fas_indicators where database_name='" + database_name + "' and is_active='1'";

            conn.rs = conn.st.executeQuery(tbls);

            //Create strings first which can be converted to arrays later
            String indicator_ids_string = "";
            String indicators_string = "";
            String main_indicator_string = "";
            String main_indic_rowspan_String = "";
            String disabledcolumnsstring = "";
            int rowspancount = 1;

            while (conn.rs.next()) {
                //the column lastspanrow should be used to determine where the row ends. 
                //If its that row, use character $ to denote starting of a new range of arrays

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

                    rowspancount = 0;
                } else {

                    indicator_ids_string += conn.rs.getString("id") + ",";
                    indicators_string += conn.rs.getString("indicator") + ",";

                    if (conn.rs.getString("disabledcolumns") != null) {
                        disabledcolumnsstring += conn.rs.getString("disabledcolumns") + "%";
                    } else {
                        disabledcolumnsstring += " " + "%";
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

            //for each set, replace $, with just $
            indicator_ids_string = indicator_ids_string.replace("$,", "$").replace(",)", "");
            indicators_string = indicators_string.replace("$,", "$").replace(",)", "");
            main_indicator_string = main_indicator_string.replace("$,", "$").replace(",)", "");
            main_indic_rowspan_String = main_indic_rowspan_String.replace("$,", "$").replace(",)", "");
            disabledcolumnsstring = disabledcolumnsstring.replace("$%", "$");

            String indicator_ids[] = indicator_ids_string.split("\\$");
            String indicators[] = indicators_string.split("\\$");
            String main_indicator[] = main_indicator_string.split(",");
            String main_indic_rowspan[] = main_indic_rowspan_String.split(",");
            String disabledcolumns_arr[] = disabledcolumnsstring.split("\\$");
            int indic_pos = 0, main_indic_pos = 0, max_length = 5;
            String facil = "";

            //   tableid="2018_10_331";
            lock = "";

            validity = "<b><font color='#4b8df8'>New Entry</font></b>";

//            if (session.getAttribute("year") != null) {
//                year = session.getAttribute("year").toString();
//            }
//            if (session.getAttribute("monthid") != null) {
//                month = session.getAttribute("monthid").toString();
//            }
//
//            if (session.getAttribute("facilityid") != null) {
//                facil = session.getAttribute("facilityid").toString();
//            }
            year = "2018";
            month = "10";
            facil = "383";

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

            String locked_DATA = "SELECT id FROM locked_data WHERE yearmonth=? AND form1a=?";
            conn.pst = conn.conn.prepareStatement(locked_DATA);
            conn.pst.setString(1, yearmonth);
            conn.pst.setInt(2, 1);
            conn.rs = conn.pst.executeQuery();
            if (conn.rs.next()) {
                isLocked = "1";
                lock = "disabled";
            }

            enterdby = "";
            output = "<table  border='1px;'>";
            output += "<tr>"
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
            output += "<tr><td>M</td><td>F</td>"
                    + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                    + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                    + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                    + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                    + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                    + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                    + "<td>Total</td>"
                    + "</tr>";

            main_indic_pos = 0;

            for (String main_indic : main_indicator) {
                indic_pos = 0;
                //System.out.println("main indicator pos = " + main_indic_pos);
              //System.out.println("main indicator  = " + main_indicator_string);
//                System.out.println("Indicator id string = " + indicator_ids_string);
//                System.out.println("Indicator id  = " + indicator_ids[main_indic_pos]);
                

                for (String indic_id : indicator_ids[main_indic_pos].split(",")) {
                    
           
            System.out.println( " indicator names  = " +main_indic);
                    
                    String tableid = yearmonth + "_" + facil + "_" + indic_id;
                    String get_data = "SELECT * FROM " + database_name + " WHERE id=?";
                    conn.pst = conn.conn.prepareStatement(get_data);
                    conn.pst.setString(1, tableid);
                    conn.rs = conn.pst.executeQuery();
                    if (conn.rs.next()) { // indicator data already exist
                        output += "<tr>";
                        if (indic_pos == 0) {
                            output += "<td rowspan='" + main_indic_rowspan[main_indic_pos] + "'>" + main_indic + "</td>";
                        }
                        output += "<td>" + indicators[main_indic_pos].split(",")[indic_pos] + "</td>";
                        for (String column_name : columns) {
                             System.out.println( " disabled columns  = " + disabledcolumns_arr[main_indic_pos].split("%")[indic_pos]);
                            
                            String isreadonly = "";
                            if (disabledcolumns_arr[main_indic_pos].split("%")[indic_pos].contains("," + column_name + ",")) {
                                isreadonly = " tabindex='-1' readonly='true' ";
                            }
                            output += "<td><input " + isreadonly + " type='text'  name='" + column_name + "_" + indic_id + "' id='" + column_name + "_" + indic_id + "' value='" + conn.rs.getString(column_name) + "' onblur=\"indicate_changed('" + column_name + "_" + indic_id + "');\"  oninput=\"" + database_name + "" + indic_pos + "(); \" class='data-cell' data-toggle='tooltip'  " + lock + "  data-placement='right' autocomplete='off' maxLength='" + max_length + "' onkeypress='return numbers(event)' ></td>";
                        }
                        output += "<p id='" + indic_id + "'></p></tr>";
                    } else { // new indicator
                        output += "<tr>";

                        if (indic_pos == 0) 
                        {
                            output += "<td rowspan='" + main_indic_rowspan[main_indic_pos] + "'>" + main_indic + "</td>";
                        }
                        output += "<td>" + indicators[main_indic_pos].split(",")[indic_pos] + "</td>";
                        for (String column_name : columns) {
                             System.out.println( " disabled columns  = " + disabledcolumns_arr[main_indic_pos].split("%")[indic_pos]);
                            
                            String isreadonly = "";
                            if (disabledcolumns_arr[main_indic_pos].split("%")[indic_pos].contains("," + column_name + ",")) 
                            {
                                isreadonly = " tabindex='-1' readonly='true' ";
                            }

                            output += "<td><input " + isreadonly + " type='text' name='" + column_name + "_" + indic_id + "' id='" + column_name + "_" + indic_id + "' value='' onblur=\"indicate_changed('" + column_name + "_" + indic_id + "');\"  oninput=\"" + database_name + "" + indic_pos + "(); \" class='data-cell' data-toggle='tooltip'  " + lock + "  data-placement='right' autocomplete='off' maxLength='" + max_length + "' onkeypress='return numbers(event)' ></td>";
                                                           }
                        output += "<p id='" + indic_id + "'></p></tr>";
                    }

                    indic_pos++;
                }
                main_indic_pos++;
            }
 output +="</table>";
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
            Logger.getLogger(load_art.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_art.class.getName()).log(Level.SEVERE, null, ex);
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
