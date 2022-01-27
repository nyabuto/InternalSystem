/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VL;

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
 * @author EKaunda
 */
public class update_vl_results extends HttpServlet {

    
    HttpSession session;
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        session=request.getSession();
        
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
       
            String sdate="2020-01-01";
            String edate="2020-12-31";
            
           
            out.println("</html>");
            
            dbConn conn= new dbConn();
            //select * from vl_kenyaemr where ( Last_VL like 'missing' or Last_VL='' or Last_VL is null) and yearmonth='202012' and length(Uniquecccno(cccno))=10 ;

            String qry="";
            
            if (request.getParameter("qry") != null && !request.getParameter("qry").equals("")) {

                qry = request.getParameter("qry");

            }

            if (request.getParameter("sdate") != null) {
                sdate = request.getParameter("sdate");
            }

            if (request.getParameter("edate") != null) {
                edate = request.getParameter("edate");
            }
            
            if(qry.equals("updateEMRResults"))
            {
             out.println(updateEMRResults(conn, sdate, edate, ""));   
            }
            else if(qry.equals("updatePMTCT"))
            {
              out.println(updatePMTCT(conn, sdate, edate, ""));
            }
            else if(qry.equals("updateNonEMRResults"))
            {
              out.println(updateNonEMRResults(conn, sdate, edate, ""));  
            }
            
           //updateEMRResults(conn, sdate, edate, "");
           //updatePMTCT(conn, sdate, edate, "");
           //updateNonEMRResults(conn, sdate, edate, "");
            
            
         if(conn.rs!=null){conn.rs.close();}   
         if(conn.st!=null){conn.st.close();}   
         if(conn.rs1!=null){conn.rs1.close();}   
         if(conn.rs2!=null){conn.rs2.close();}   
         if(conn.st1!=null){conn.st1.close();}   
         if(conn.st2!=null){conn.st2.close();}   
         if(conn.st3!=null){conn.st3.close();}   
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
            Logger.getLogger(update_vl_results.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(update_vl_results.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    public String updatePMTCT(dbConn conn,String sdate,String edate,String mflcodes){
    
        try {
            String ym=edate.replace("-","").substring(0,6);
            
            String mflwhere=" ";
            if(!mflcodes.equals("")){
                
                mflwhere=" and MFL_Code in ('"+mflcodes+"')";
                
            }
            int no_of_rows=0;   
            
            String getpmtcts="select Uniquecccno(Patient_CCC_No) as cccno,Patient_CCC_No as origcccno, Justification, `Value` as VL_result, Date_Collected, PMTCT, Facility_Name ,MFL_Code  from vl_surge where pmtct in ('Breast Feeding','Pregnant') and ( Date_Collected between '"+sdate+"' and '"+edate+"' ) and value!='Collect New Sample' "+mflwhere+" order by Date_collected DESC  ";
            
            conn.rs1=conn.st1.executeQuery(getpmtcts);
            
            while(conn.rs1.next()){
                
                no_of_rows++;
                
                String res=conn.rs1.getString("VL_result");
                String jus=conn.rs1.getString("Justification");
                String cdate=conn.rs1.getString("Date_Collected");
                String pmtct=conn.rs1.getString("PMTCT");
                String facili=conn.rs1.getString("Facility_Name");
                String cc=conn.rs1.getString("cccno");
                String origicc=conn.rs1.getString("origcccno");
                String mfl=conn.rs1.getString("MFL_Code");
                
                String updaterecord="update vl_kenyaemr set  PMTCT='"+pmtct+"'  where cccno='"+cc+"' and replace(MFL_Code,'.0','') = '"+mfl+"' and yearmonth='"+ym+"'  ";
                session.setAttribute("vlquery",updaterecord);
                System.out.println(no_of_rows+" "+" update query: "+updaterecord+"\n");
                 session.setAttribute("vlcount",no_of_rows);
                
                
                conn.st3.executeUpdate(updaterecord);
                
                
            }
            
          
        } catch (SQLException ex) {
             session.setAttribute("vlerror",ex);
            Logger.getLogger(update_vl_results.class.getName()).log(Level.SEVERE, null, ex);
        }
          return "completed updating PMTCT";
}
    
    
    
    
    
    public String updateEMRResults(dbConn conn,String sdate,String edate,String mflcodes) {
    
        try {
            int updatedrows=0;
            int searchcount=0;
            String ym=edate.replace("-","").substring(0,6);
            
            String mfl="";
            String facil="";
            String cccno="";
            String origmfl="";
            
            String mflwhere=" ";
            if(!mflcodes.equals("")){
                
                mflwhere=" and MFL_Code in ('"+mflcodes+"')  ";
                
            }
            
            String getemrmissingresults="select vl_kenyaemr_id, cccno,replace(MFL_Code,'.0','') as MFL_Code,MFL_Code as origmfl ,Facility_Name from vl_kenyaemr where ( Last_VL like 'missing' or Last_VL='' or Last_VL is null) and yearmonth='"+ym+"' and length(Uniquecccno(cccno))=10  "+mflwhere+"  order by rand()";
            
            System.out.println("Missing vls:"+getemrmissingresults);
            conn.rs=conn.st.executeQuery(getemrmissingresults);
            
            while(conn.rs.next()){
                
                searchcount++;
                
                mfl=conn.rs.getString("MFL_Code");
                origmfl=conn.rs.getString("origmfl");
                cccno=conn.rs.getString("cccno");
                facil=conn.rs.getString("Facility_Name");
                
                String getmax_res="select Justification, `Value` as VL_result, Date_Collected, PMTCT, Facility_Name  from vl_surge where Uniquecccno(Patient_CCC_No) = '"+cccno+"' and MFL_Code='"+mfl+"' and ( Date_Collected between '"+sdate+"' and '"+edate+"' ) and value!='Collect New Sample'  order by Date_collected DESC limit 1 ";
                //System.out.println("count number:"+searchcount);
                conn.rs1=conn.st1.executeQuery(getmax_res);
                
                if(conn.rs1.next()){
                    
                    String res=conn.rs1.getString("VL_result");
                    String jus=conn.rs1.getString("Justification");
                    String cdate=conn.rs1.getString("Date_Collected");
                    String pmtct=conn.rs1.getString("PMTCT");
                    String facili=conn.rs1.getString("Facility_Name");
                    
                    if(jus.equals("No Data"))
                    {
                        jus="Routine VL";
                    }
                    
                    res=res.replace(" copies/ml","");
                    res=res.replace(" Copies/ mL","");
                    res=res.replace("<","");
                    res=res.replace(" ","");
                    
                    String updaterecord="update  vl_kenyaemr set Justification='"+jus+"', PMTCT='"+pmtct+"',Last_VL='"+res+"',Last_VL_Date='"+cdate+"' , missing_from_source='Yes' where cccno='"+cccno+"' and MFL_Code='"+origmfl+"' and yearmonth='"+ym+"'  ";
                    String updaterecordps="update ignore vl_kenyaemr set Justification=? , PMTCT=? , Last_VL=? , Last_VL_Date=? , missing_from_source=? where cccno=? and MFL_Code=? and yearmonth=?  ";
                    
                    conn.pst=conn.conn.prepareStatement(updaterecordps);
    conn.pst.setString(1,jus);
    conn.pst.setString(2,pmtct);
    conn.pst.setString(3,res);
    conn.pst.setString(4,cdate);
    conn.pst.setString(5,"Yes");
    conn.pst.setString(6,cccno);
    conn.pst.setString(7,origmfl);
    conn.pst.setInt(8,new Integer(ym));
                    
                    System.out.println(" update query: "+updaterecord+"\n");
                    
                    updatedrows++;
                    
                     session.setAttribute("vlquery",updaterecord);
                     session.setAttribute("vlcount",updatedrows);
                    
                    System.out.println(updatedrows+": Records updated "+facili+"\n");
                    
                    
                    System.out.println("Update Status "+conn.pst.executeUpdate());
                   
                    
                }
                
                
            }
            
            
            
            
            
        } catch (SQLException ex) {
            System.out.println("Error while updating VL Resuts:"+ex);
            session.setAttribute("vlerror",ex);
            Logger.getLogger(update_vl_results.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Completed EMR Results ";
    }
    
public String updateNonEMRResults(dbConn conn,String sdate,String edate,String mflcodes) {


    
        try {
            int updatedrows=0;
            int searchcount=0;
            String ym=edate.replace("-","").substring(0,6);
            
            String mfl="";
            String facil="";
            String cccno="";
            String origmfl="";
            
            String mflwhere="1=1";
            
            if(!mflcodes.equals(""))
            {
                
                mflwhere=" and  mflcode in ('"+mflcodes+"')";
                
            }
            
            
            String getemrmissingresults="call sp_nonemr_missingvl_raw_data ('"+sdate+"','"+edate+"','"+mflwhere+"')";
            
            System.out.println("Missing vls:"+getemrmissingresults);
            conn.rs=conn.st.executeQuery(getemrmissingresults);
            
            while(conn.rs.next()){
                
                searchcount++;
                
                mfl=conn.rs.getString("mflcode");
                
                cccno=conn.rs.getString("ccc_number");
                facil=conn.rs.getString("facility");
                
                String getmax_res="select Justification, `Value` as VL_result, Date_Collected, PMTCT, Facility_Name  from vl_surge where Uniquecccno(Patient_CCC_No) = '"+cccno+"' and MFL_Code='"+mfl+"' and ( Date_Collected between '"+sdate+"' and '"+edate+"' ) and value!='Collect New Sample'  order by Date_collected DESC limit 1 ";
                System.out.println("vlmis count number:"+searchcount);
                conn.rs1=conn.st1.executeQuery(getmax_res);
                
                if(conn.rs1.next()){
                    
                    String res=conn.rs1.getString("VL_result");
                    String jus=conn.rs1.getString("Justification");
                    String cdate=conn.rs1.getString("Date_Collected");
                    String pmtct=conn.rs1.getString("PMTCT");
                    String facili=conn.rs1.getString("Facility_Name");
                    
                    if(jus.equals("No Data"))
                    {
                        jus="Routine VL";
                    }
                    
                    //res=res.replace(" copies/ml","");
                    res=res.replace("< 40 Copies/ mL","40");
                    // res=res.replace("<","");
                    //res=res.replace(" ","");
                    
                    String updaterecord="update nonemr_all set Justification='"+jus+"', PMTCT_Status='"+pmtct+"',VL_Results='"+res+"',Date_Last_VL_Conducted='"+cdate+"' where ccc_number='"+cccno+"' and mflcode='"+mfl+"'   ";
                    
                    System.out.println(" update query: "+updaterecord+"\n");
                    
                    updatedrows++;
                    
                    conn.st3.executeUpdate(updaterecord);
                    
                    System.out.println(updatedrows+": Records updated "+facili+"\n");
                     session.setAttribute("vlcount",updatedrows);
                    //id=10056_1005600001_2020-05-08
                    
                    //do an insert to nonemr_vl
                    
                    String id=mfl+"_"+cccno+"_"+cdate;
                    
                    String replaceqry="Replace into nonemr_vl (id,ccc_number,visitdate,First_Viral_Load_Date,Date_Last_VL_Conducted,Justification,PMTCT_Status,VL_Results,user_id,device,mflcode,eligible) "
                            + " values ('"+id+"','"+cccno+"','','','"+cdate+"','"+jus+"','"+pmtct+"','"+res+"','system generated','lenovo','"+mflcodes+"','Yes'); ";
                    session.setAttribute("vlquery",replaceqry);
                    conn.st3.executeUpdate(replaceqry);
                    
                }
                
                
            }
            
            
            
            
            
            
            
           
        } catch (SQLException ex) {
            session.setAttribute("vlerror",ex);
            Logger.getLogger(update_vl_results.class.getName()).log(Level.SEVERE, null, ex);
        }
         return "Completed running Non EMR Results";
}
}