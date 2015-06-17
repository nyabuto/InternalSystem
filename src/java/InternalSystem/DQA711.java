/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalSystem;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maureen
 */
public class DQA711 extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        session= request.getSession();
        PrintWriter out = response.getWriter();
//        String form="";
//        String year="";
//        String month="";
//        String facilityId="";
//        String subcountyid="";
//        
          dbConn conn = new dbConn();
        try {
//            if( request.getParameter("form")!=null){
//           form =  request.getParameter("form");
//            }
//            else{
//                  if(session.getAttribute("form")!=null){
//          form=  session.getAttribute("form").toString();
//                  }}
//      
       String months="";
         String  years="";
             String  DistrictID="";
             String SubPartnerID="";
//        System.out.println("nnn "+form); 
//        session.setAttribute("form",form);
//               if(session.getAttribute("year")!=null){        
//   year=session.getAttribute("year").toString();
//    }
//      if(session.getAttribute("monthid")!=null){        
//   month=session.getAttribute("monthid").toString();
//    }
//   
//        if(session.getAttribute("facilityid")!=null){        
//   facilityId=session.getAttribute("facilityid").toString();
//    }
//        if(session.getAttribute("subcountyid")!=null){        
//   subcountyid=session.getAttribute("subcountyid").toString();
//    }
           ArrayList viewerrors=new ArrayList();
       String[] parts=null; 
//        errorss
//        parseInt(VCTClient_HIV_CF)> parseInt(VCTClient_Tested_CF)    
//parseInt(VCTClient_HIV_CM)> parseInt(VCTClient_Tested_CM)   
//parseInt(VCTClient_HIV_AF)> parseInt(VCTClient_Tested_AF)
//parseInt(VCTClient_HIV_AM)> parseInt(VCTClient_Tested_AM)
        String data="";
         String VCTClient_HIV_CF ="";
           String  VCTClient_Tested_CF ="";
           String facility="";
           String HTC="";
//        if(form.equals("load711.jsp")  ){
            
            
            data+="<thead><th>No.</th><th>COLUMN</th><th>ERROR</th> </thead><tbody>";
//      String getdata="  SELECT *  FROM subpartnera JOIN moh711 ON subpartnera.SubPartnerID=moh711.SubPartnerID WHERE "
//            + "moh711.Mois='"+month+"' AND moh711.Annee='"+year+"' AND subpartnera.DistrictID='"+subcountyid+"' "
//              + "and  "      CASE riskreductiondetails.QID \n" +
//"		WHEN VCTClient_HIV_CF < VCTClient_Tested_CF'   THEN  'hiv>tested 15-24' f ""  \n" +
//"		WHEN 'B3' AND RiskReductionDetails.action LIKE 'WBL Provided_%' THEN SUM(SUBSTRING_INDEX(riskreductiondetails.Action, '_', -1) ) end as e,\n" +
String allerrors="";
            String getfacility="select * from subpartnera ";
            conn.rs2= conn.st2.executeQuery(getfacility);
            while(conn.rs2.next()){
                facility=conn.rs2.getString(1);
            String getdata=" SELECT "+
" CASE  WHEN VCTClient_HIV_CF > VCTClient_Tested_CF THEN  'VCT CLIENTS HIV + F(15-24)%HIV + is greater than Tested (15-24 F)' ELSE '' END AS HIV1,"+
" CASE  WHEN VCTClient_HIV_CM > VCTClient_Tested_CM THEN  'VCT CLIENTS HIV + M(15-24)%HIV + is greater than Tested (15-24 M)' ELSE '' END AS HIV2, "+
" CASE  WHEN VCTClient_HIV_AF > VCTClient_Tested_AF THEN  'VCT CLIENTS HIV + F(>25)%HIV + is greater than Tested (>25 F)' ELSE '' END AS HIV3, "+
" CASE  WHEN VCTClient_HIV_AM > VCTClient_Tested_AM THEN  'VCT CLIENTS HIV + M(>25)%HIV + is greater than Tested (>25 M)' ELSE '' END AS HIV4, "+

" CASE  WHEN DTCC_HIV_Out_CF > DTCB_Test_Out_CF THEN  'DTC HIV+ OUTPATIENT F(15-24)%HIV + is greater than Tested (15-24 F)-OUTPATIENT' ELSE '' END AS HIV5, "+
" CASE  WHEN DTCC_HIV_Out_CM > DTCB_Test_Out_CM THEN  'DTC HIV+ OUTPATIENT M(15-24)%HIV + is greater than Tested (15-24 M)-OUTPATIENT' ELSE '' END AS HIV6, "+
" CASE  WHEN DTCC_HIV_Out_AM > DTCB_Test_Out_AM THEN  'DTC HIV+ OUTPATIENT M(>25)%HIV + is greater than Tested (>25 M)-OUTPATIENT' ELSE '' END AS HIV7, "+
" CASE  WHEN DTCC_HIV_Out_AF > DTCB_Test_Out_AF THEN  'DTC HIV+ OUTPATIENT F(>25)%HIV + is greater than Tested (>25 F)-OUTPATIENT' ELSE ''  END AS HIV8, "+

" CASE  WHEN DTCC_HIV_In_CF > DTCB_Test_In_CF THEN  'DTC HIV+ INPATIENT F(15-24)%HIV + is greater than Tested (15-24 F)-INPATIENT' ELSE '' END AS HIV9, "+
" CASE  WHEN DTCC_HIV_In_CM > DTCB_Test_In_CM THEN  'DTC HIV+ INPATIENT M(15-24)% HIV + is greater than Tested (15-24 M)-INPATIENT' ELSE ''  END AS HIV10, "+
" CASE  WHEN DTCC_HIV_In_AM > DTCB_Test_In_AM THEN  'DTC HIV+ INPATIENT M(>25)%HIV + is greater than Tested (>25 M)-INPATIENT' ELSE '' END AS HIV11,  "+
" CASE  WHEN DTCC_HIV_In_AF > DTCB_Test_In_AF THEN  'DTC HIV+ INPATIENT F(>25)%HIV + is greater than Tested (>25 F)-INPATIENT' ELSE '' END AS HIV12  "+

",ID,moh711.Mois,moh711.Annee,subpartnera.DistrictID, MOH711.SubPartnerID,HTC FROM  moh711 JOIN  subpartnera ON subpartnera.SubPartnerID=moh711.SubPartnerID WHERE  MOH711.SubPartnerID='"+facility+"' ";
   

         String tableID="";
         String columns="";
         String errors="";
      System.out.println(getdata);
//            String getdata="select * from moh711 where VCTClient_HIV_CF > VCTClient_Tested_CF";
            conn.rs= conn.st.executeQuery(getdata);
            if(conn.rs.next()==true){
         
                allerrors += conn.rs.getString(1) +
                        " @"+conn.rs.getString(2)+
                        " @"+conn.rs.getString(3)+
                        " @"+conn.rs.getString(4)+
                        " @"+conn.rs.getString(5)+
                        " @"+conn.rs.getString(6)+
                        " @"+conn.rs.getString(7)+
                        " @"+conn.rs.getString(8)+
                        " @"+conn.rs.getString(9)+
                        " @"+conn.rs.getString(10)+
                        " @"+conn.rs.getString(11)+
                        " @"+conn.rs.getString(12);
                       
              tableID=conn.rs.getString(13);
              months=conn.rs.getString(14);
              years=conn.rs.getString(15);
              DistrictID=conn.rs.getString(16);
              SubPartnerID=conn.rs.getString(17);
              HTC=conn.rs.getString(18);
                 parts = allerrors.split("@");
             
               viewerrors.add(allerrors.split("@"));
//             System.out.println("mmmm     "+allerrors+" "+tableID+" "+months+" "+years+" "+DistrictID+" "+SubPartnerID); 
           
            
//            System.out.println("nnnn     "+allerrors+" "+tableID+" "+months+" "+years+" "+DistrictID+" "+SubPartnerID);
            int counter=0;
            String otherpart[]=null;
            for(int i=0;i<parts.length;i++){
             
                if(parts[i]!=null && !parts[i].equals("null") && !parts[i].equals(" ")){
                       counter++;
//                    System.out.println("vvv"+parts[i]+"MMM");
                     String part[];
                     part= parts[i].split("%");
//                     System.out.println(part[0]+"___"+part[1]);
              data+="<tr>"
                     
                      
                      
                        + "<td>"+counter+"</td><td>"+part[0]+"</td><td>"+part[1] +"</td>"
                      
                        + "</tr>";
              if(!columns.contains(part[0])){
              columns+=part[0]+"@";
              errors+=part[1]+"@";}
              
                  System.out.println("errors    "+columns+"___"+errors);
                }
                
                
               
            }
            data+="</tbody>";
           if(data.equals("")){
           
           data+="nodata";
           }
            
            if(!tableID.equals("") && !columns.equals("") &&  !errors.equals("") &&  !SubPartnerID.equals("") && HTC.equals("1") ){
             String insert="insert into DQA set tableid='"+tableID+"',form='moh711',facilityid='"+SubPartnerID+"',year='"+years+"',month='"+months+"',columns='"+columns+"',errors='"+errors+"'"; 
                    conn.st1.executeUpdate(insert);
             System.out.println(insert);}
             columns="";  
         errors="";
        }
       }
        
            out.println(data);     
          System.out.println(   data  );  
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
            Logger.getLogger(loadDQA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
            Logger.getLogger(loadDQA.class.getName()).log(Level.SEVERE, null, ex);
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
