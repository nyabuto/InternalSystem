/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form1a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;

/**
 *
 * @author GNyabuto
 */
public class fas_trial extends HttpServlet {
    HttpSession session;
    String facility_id,yearmonth;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
            JSONObject obj;
            XSSFWorkbook wb;
          ValidateExcel vExcel = new ValidateExcel();
          
          facility_id = "1";
          yearmonth = "201902";
          
//          String facililtyids[]={"1","10","11","111","112","113","114","115","116","117","119","12","120","121","123","124","125","126","127","128","129","130","131","132","133","134","135","136","137","138","140","141","143","144","146","148","149","151","154","155","157","158","159","160","161","162","163","164","165","166","167","168","169","170","171","18","19","2","20","21","22","225","226","227","228","229","23","230","231","232","233","234","235","237","239","240","243","246","247","248","249","25","251","254","256","257","258","259","26","260","28","285","286","288","289","29","290","291","292","293","294","296","299","3","30","300","301","302","303","304","305","306","307","308","309","31","310","311","32","327","328","329","33","330","331","332","333","334","335","336","34","340","342","343","344","346","347","348","349","35","350","352","353","354","355","356","357","358","359","360","361","362","363","364","365","366","367","368","373","374","376","377","378","379","381","382","383","384","385","386","387","389","391","392","393","394","395","397","398","399","4","400","401","403","404","405","406","410","417","418","419","420","422","424","425","426","427","429","436","437","438","439","440","443","444","445","446","447","448","450","451","453","454","455","456","457","459","464","465","466","467","468","469","470","471","472","473","474","475","476","478","480","484","485","486","487","488","490","491","492","495","496","497","5","500","501","502","503","504","505","507","509","511","512","513","514","516","517","518","519","520","523","524","525","526","527","528","532","539","6","602","603","7","8","800","801","802","803","804","805","810","812","813","815","816","817","818","819","820","821","822","826","827","828","831","832","834","835","836","837","838","839","840","841","842","843","844","845","846","847","848","849","850","851"};
//          String facililtyids[]={"1","10","11","111","112","113","114","115","116","117","119","12","120","121","123","124","125","126","127","128","129","130"};
          String facililtyids[]={};
//          String period[] = {"201810","201811","201812","201901","201902"};
          String period[] = {"201902"};
          
          //call method to execute 
//          obj = vExcel.validate(facility_id, yearmonth); // for one facility and one period
          obj = vExcel.validate(facililtyids, period);
          wb = vExcel.generateExcel(obj); // to generate Excel file
//          out.println(obj);
          
    ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
    wb.write(outByteStream);
    byte [] outArray = outByteStream.toByteArray();
    response.setContentType("application/ms-excel");
    response.setContentLength(outArray.length);
    response.setHeader("Expires:", "0"); // eliminates browser caching
    response.setHeader("Set-Cookie:", "fileDownload=true; path=/"); // set cookie header
    response.setHeader("Content-Disposition", "attachment; filename=Data_Quality_Errors.xlsx");
    OutputStream outStream = response.getOutputStream();
    outStream.write(outArray);
    outStream.flush();
     
      
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
            Logger.getLogger(fas_trial.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(fas_trial.class.getName()).log(Level.SEVERE, null, ex);
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
