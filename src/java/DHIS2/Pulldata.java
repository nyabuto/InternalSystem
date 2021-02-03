/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHIS2;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.dbConn;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



/**
 *
 * @author EKaunda
 */
public class Pulldata extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
            
            
            
            dhis2DataSave ds= new dhis2DataSave();
            
            dhis2Datapull dp= new dhis2Datapull();
            
            dhisconfig dc = new dhisconfig();
            
            dbConn conn= new dbConn();
            
            UrlResourceManager urm = new UrlResourceManager();
            
            
            
            String datarequest="";
            
            
           if(request.getParameter("datarequest")!=null)
           {
               
           datarequest=request.getParameter("datarequest");
           
                      
           }
           
          //pullCategoryOptionCombination
          //pullDataElements
          //pullDataSet
          //pullOrgUnitGroups
          //pullOrgUnits
          
          
            if (datarequest.equals("pullOrgUnits")) {

                out.println(dp.pullOrgUnits(conn, dc, urm));
            } 
            
            
            
            else if (datarequest.equals("pullDataElements")) {
                out.println(dp.pullDataElements(conn, dc, urm));
            } 
            
            
            
            else if (datarequest.equals("pullDataSet")) {
                out.println(dp.pullDataSet(conn, dc, urm));
            }
            
            
            
            
            else if (datarequest.equals("pullOrgUnitGroups")) {
                out.println(dp.pullOrgUnitGroups(conn, dc, urm));
            } 
            
            
            
            else if (datarequest.equals("pullCategoryOptionCombination")) {
                out.println(dp.pullCategoryOptionCombination(conn, dc, urm));
            }
            
            
            else if (datarequest.equals("pullForm1a")) {
                out.println(dp.pullForm1a(conn, dc, urm));
            } 
            
            
            else if (datarequest.equals("pullDailyArt")) {
                out.println(dp.pullDailyArt(conn, dc, urm));
            }
          
            else if (datarequest.equals("migrateData")) {
                out.println("[migrateData:{'a':''}]");
            }
          
        
            
            
        } 
        
        finally 
        {
            out.close();
        }
    }
//    192.168.0.119

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
        } catch (ParseException ex) {
            Logger.getLogger(Pulldata.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
    
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Pulldata.class.getName()).log(Level.SEVERE, null, ex);
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

    
    /*****
     * 
     * 
     * 
     * 
       Category option combinations ( 2 columns 
       Data elements
       Dataset
       Organization units
       Organization unit groups
     * 
     */
    
   
   
      

       
    
}
