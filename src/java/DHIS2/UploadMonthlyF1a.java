/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHIS2;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author EKaunda
 */
public class UploadMonthlyF1a extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
            String yearmonth="202102";
            
            
            if(request.getParameter("yearmonth")!=null){
            yearmonth=request.getParameter("yearmonth");                
            }
            
            dbConn conn = new dbConn();
            
           String uniquefacils="select distinct(facility_id) as facilid, "
                   + " case  when county='Baringo' then 'EMbawi' "
                   + " when county='Kajiado' then 'Rosoti' "
                   + " when county='Laikipia' then 'AOdhiambo' "
                   + " when districtNom in ('Gilgil','Nakuru East') then 'BAtieno' "
                   + " when districtNom in ('Kuresoi South','Kuresoi North','Molo') then 'NMuema' "
                   + " when subpartnernom like '%PGH%' then 'GChebole' "
                   + " when districtNom in ('Naivasha') then 'DMutua' "
                   + " when districtNom in ('Nakuru North','Nakuru West') then 'BChirchir' "
                   + " when districtNom in ('Njoro','Rongai') then 'GOdhiambo' "
                   + " when districtNom in ('Subukia') then 'MGitau' "
                   + " when districtNom in ('Samburu') then 'Maralal'"
                   + " else 'DJuma' "
                   + " end as dhis_uname "
                   + " , ifnull(dn,'DJuma') as dhis2_uname , ifnull(dp,'Datim2020!') as dhis2_pword "
                   + " from vw_fas "
                   + " join subpartnera sp on sp.subpartnerid=vw_fas.facility_id "
                   + " join district dis on dis.DistrictID=sp.DistrictID"
                   + " join county ct on ct.CountyID=dis.CountyID"
                   +" left join ( select dhis2_uname as dn, dhis2_pword as dp from user ) u on u.dn=  ( case  when county='Baringo' then 'EMbawi'   when county='Kajiado' then 'Rosoti'    when county='Laikipia' then 'AOdhiambo'   when districtNom in ('Gilgil','Nakuru East') then 'BAtieno'  when districtNom in ('Kuresoi South','Kuresoi North','Molo') then 'NMuema'     when subpartnernom like '%PGH%' then 'GChebole'   when districtNom in ('Naivasha') then 'DMutua'   when districtNom in ('Nakuru North','Nakuru West') then 'BChirchir'   when districtNom in ('Subukia') then 'MGitau'   when districtNom in ('Samburu') then 'Maralal' else 'DJuma'    end ) "
                   + " where yearmonth='"+yearmonth+"'  ";
          
           dhisconfig dc = new dhisconfig();
           conn.rs=conn.st.executeQuery(uniquefacils);
           
           
           while(conn.rs.next())
           {
         

       String fc=conn.rs.getString(1);
       String un=conn.rs.getString(3);
       String pw=conn.rs.getString(4);
       
       //Get this from a DB
       dc.setDhis2_username(un);
       dc.setDhis2_Password(pw);       
       
    pushDataToDHIS2 pd= new pushDataToDHIS2();
        //getDistinctColumns(conn,"Form 1a");
       ArrayList cols=pd.getDistinctF1aColumns(conn,"Form 1 A");
       ResultSet f1adata=pd.GetForm1aData(conn, yearmonth, fc, cols);
        
      try {
            JSONObject jo=pd.toJsonString(conn,f1adata, cols, dc.getDhis2_username());
            System.out.println(""+jo);
           out.print("<br/><br/>  "+pd.UploadF1aToServer(jo,dc.getDhis2_username(),dc.getDhis2_Password() ));            
            
        } catch (SQLException ex) 
        {
            Logger.getLogger(pushDataToDHIS2.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     
            
           }
            
            
           
           
         
        } catch (SQLException ex) {
            Logger.getLogger(UploadMonthlyF1a.class.getName()).log(Level.SEVERE, null, ex);
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
