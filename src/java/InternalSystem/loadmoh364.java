/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalSystem;

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
public class loadmoh364 extends HttpServlet {
HttpSession session;
String id,facility_id,year,month,output;
String PSRapeSurvivor_11M,PSRapeSurvivor_11F,PSRapeSurvivor_17M,PSRapeSurvivor_17F,PSRapeSurvivor_49M,PSRapeSurvivor_49F,PSRapeSurvivor_50M,PSRapeSurvivor_50F,PSRapeSurvivor_TM,PSRapeSurvivor_TF,
PSPresenting72_11M,PSPresenting72_11F,PSPresenting72_17M,PSPresenting72_17F,PSPresenting72_49M,PSPresenting72_49F,PSPresenting72_50M,PSPresenting72_50F,PSPresenting72_TM,PSPresenting72_TF,
PSInitiatedPEP_11M,PSInitiatedPEP_11F,PSInitiatedPEP_17M,PSInitiatedPEP_17F,PSInitiatedPEP_49M,PSInitiatedPEP_49F,PSInitiatedPEP_50M,PSInitiatedPEP_50F,PSInitiatedPEP_TM,PSInitiatedPEP_TF,
PSSTITreatment_11M,PSSTITreatment_11F,PSSTITreatment_17M,PSSTITreatment_17F,PSSTITreatment_49M,PSSTITreatment_49F,PSSTITreatment_50M,PSSTITreatment_50F,PSSTITreatment_TM,PSSTITreatment_TF,
PSEligibleContraceptive_11M,PSEligibleContraceptive_11F,PSEligibleContraceptive_17M,PSEligibleContraceptive_17F,PSEligibleContraceptive_49M,PSEligibleContraceptive_49F,PSEligibleContraceptive_50M,PSEligibleContraceptive_50F,PSEligibleContraceptive_TM,PSEligibleContraceptive_TF,
PSGivenContraceptive_11M,PSGivenContraceptive_11F,PSGivenContraceptive_17M,PSGivenContraceptive_17F,PSGivenContraceptive_49M,PSGivenContraceptive_49F,PSGivenContraceptive_50M,PSGivenContraceptive_50F,PSGivenContraceptive_TM,PSGivenContraceptive_TF,
PSTestedHIV_11M,PSTestedHIV_11F,PSTestedHIV_17M,PSTestedHIV_17F,PSTestedHIV_49M,PSTestedHIV_49F,PSTestedHIV_50M,PSTestedHIV_50F,PSTestedHIV_TM,PSTestedHIV_TF,
PS1STVisit_11M,PS1STVisit_11F,PS1STVisit_17M,PS1STVisit_17F,PS1STVisit_49M,PS1STVisit_49F,PS1STVisit_50M,PS1STVisit_50F,PS1STVisit_TM,PS1STVisit_TF,
PSDisability_11M,PSDisability_11F,PSDisability_17M,PSDisability_17F,PSDisability_49M,PSDisability_49F,PSDisability_50M,PSDisability_50F,PSDisability_TM,PSDisability_TF,
PSPerpetrator_11M,PSPerpetrator_11F,PSPerpetrator_17M,PSPerpetrator_17F,PSPerpetrator_49M,PSPerpetrator_49F,PSPerpetrator_50M,PSPerpetrator_50F,PSPerpetrator_TM,PSPerpetrator_TF,
CS1Visit_11M,CS1Visit_11F,CS1Visit_17M,CS1Visit_17F,CS1Visit_49M,CS1Visit_49F,CS1Visit_50M,CS1Visit_50F,CS1Visit_TM,CS1Visit_TF,
CS2Visit_11M,CS2Visit_11F,CS2Visit_17M,CS2Visit_17F,CS2Visit_49M,CS2Visit_49F,CS2Visit_50M,CS2Visit_50F,CS2Visit_TM,CS2Visit_TF,
CS3Visit_11M,CS3Visit_11F,CS3Visit_17M,CS3Visit_17F,CS3Visit_49M,CS3Visit_49F,CS3Visit_50M,CS3Visit_50F,CS3Visit_TM,CS3Visit_TF,
CS4Visit_11M,CS4Visit_11F,CS4Visit_17M,CS4Visit_17F,CS4Visit_49M,CS4Visit_49F,CS4Visit_50M,CS4Visit_50F,CS4Visit_TM,CS4Visit_TF,
CS5Visit_11M,CS5Visit_11F,CS5Visit_17M,CS5Visit_17F,CS5Visit_49M,CS5Visit_49F,CS5Visit_50M,CS5Visit_50F,CS5Visit_TM,CS5Visit_TF,
CSNoCompletedPEP_11M,CSNoCompletedPEP_11F,CSNoCompletedPEP_17M,CSNoCompletedPEP_17F,CSNoCompletedPEP_49M,CSNoCompletedPEP_49F,CSNoCompletedPEP_50M,CSNoCompletedPEP_50F,CSNoCompletedPEP_TM,CSNoCompletedPEP_TF,
CSNoSeroconverted_11M,CSNoSeroconverted_11F,CSNoSeroconverted_17M,CSNoSeroconverted_17F,CSNoSeroconverted_49M,CSNoSeroconverted_49F,CSNoSeroconverted_50M,CSNoSeroconverted_50F,CSNoSeroconverted_TM,CSNoSeroconverted_TF,
CSNoPregnant_11M,CSNoPregnant_11F,CSNoPregnant_17M,CSNoPregnant_17F,CSNoPregnant_49M,CSNoPregnant_49F,CSNoPregnant_50M,CSNoPregnant_50F,CSNoPregnant_TM,CSNoPregnant_TF,
CSNoCompletedTrauma_11M,CSNoCompletedTrauma_11F,CSNoCompletedTrauma_17M,CSNoCompletedTrauma_17F,CSNoCompletedTrauma_49M,CSNoCompletedTrauma_49F,CSNoCompletedTrauma_50M,CSNoCompletedTrauma_50F,CSNoCompletedTrauma_TM,CSNoCompletedTrauma_TF;
int has_data=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        dbConn conn = new dbConn();
        session = request.getSession();
        output="";
        
        
        id="";
        has_data=0;
        
PSRapeSurvivor_11M=PSRapeSurvivor_11F=PSRapeSurvivor_17M=PSRapeSurvivor_17F=PSRapeSurvivor_49M=PSRapeSurvivor_49F=PSRapeSurvivor_50M=PSRapeSurvivor_50F=PSRapeSurvivor_TM=PSRapeSurvivor_TF=
PSPresenting72_11M=PSPresenting72_11F=PSPresenting72_17M=PSPresenting72_17F=PSPresenting72_49M=PSPresenting72_49F=PSPresenting72_50M=PSPresenting72_50F=PSPresenting72_TM=PSPresenting72_TF=
PSInitiatedPEP_11M=PSInitiatedPEP_11F=PSInitiatedPEP_17M=PSInitiatedPEP_17F=PSInitiatedPEP_49M=PSInitiatedPEP_49F=PSInitiatedPEP_50M=PSInitiatedPEP_50F=PSInitiatedPEP_TM=PSInitiatedPEP_TF=
PSSTITreatment_11M=PSSTITreatment_11F=PSSTITreatment_17M=PSSTITreatment_17F=PSSTITreatment_49M=PSSTITreatment_49F=PSSTITreatment_50M=PSSTITreatment_50F=PSSTITreatment_TM=PSSTITreatment_TF=
PSEligibleContraceptive_11M=PSEligibleContraceptive_11F=PSEligibleContraceptive_17M=PSEligibleContraceptive_17F=PSEligibleContraceptive_49M=PSEligibleContraceptive_49F=PSEligibleContraceptive_50M=PSEligibleContraceptive_50F=PSEligibleContraceptive_TM=PSEligibleContraceptive_TF=
PSGivenContraceptive_11M=PSGivenContraceptive_11F=PSGivenContraceptive_17M=PSGivenContraceptive_17F=PSGivenContraceptive_49M=PSGivenContraceptive_49F=PSGivenContraceptive_50M=PSGivenContraceptive_50F=PSGivenContraceptive_TM=PSGivenContraceptive_TF=
PSTestedHIV_11M=PSTestedHIV_11F=PSTestedHIV_17M=PSTestedHIV_17F=PSTestedHIV_49M=PSTestedHIV_49F=PSTestedHIV_50M=PSTestedHIV_50F=PSTestedHIV_TM=PSTestedHIV_TF=
PS1STVisit_11M=PS1STVisit_11F=PS1STVisit_17M=PS1STVisit_17F=PS1STVisit_49M=PS1STVisit_49F=PS1STVisit_50M=PS1STVisit_50F=PS1STVisit_TM=PS1STVisit_TF=
PSDisability_11M=PSDisability_11F=PSDisability_17M=PSDisability_17F=PSDisability_49M=PSDisability_49F=PSDisability_50M=PSDisability_50F=PSDisability_TM=PSDisability_TF=
PSPerpetrator_11M=PSPerpetrator_11F=PSPerpetrator_17M=PSPerpetrator_17F=PSPerpetrator_49M=PSPerpetrator_49F=PSPerpetrator_50M=PSPerpetrator_50F=PSPerpetrator_TM=PSPerpetrator_TF=
CS1Visit_11M=CS1Visit_11F=CS1Visit_17M=CS1Visit_17F=CS1Visit_49M=CS1Visit_49F=CS1Visit_50M=CS1Visit_50F=CS1Visit_TM=CS1Visit_TF=
CS2Visit_11M=CS2Visit_11F=CS2Visit_17M=CS2Visit_17F=CS2Visit_49M=CS2Visit_49F=CS2Visit_50M=CS2Visit_50F=CS2Visit_TM=CS2Visit_TF=
CS3Visit_11M=CS3Visit_11F=CS3Visit_17M=CS3Visit_17F=CS3Visit_49M=CS3Visit_49F=CS3Visit_50M=CS3Visit_50F=CS3Visit_TM=CS3Visit_TF=
CS4Visit_11M=CS4Visit_11F=CS4Visit_17M=CS4Visit_17F=CS4Visit_49M=CS4Visit_49F=CS4Visit_50M=CS4Visit_50F=CS4Visit_TM=CS4Visit_TF=
CS5Visit_11M=CS5Visit_11F=CS5Visit_17M=CS5Visit_17F=CS5Visit_49M=CS5Visit_49F=CS5Visit_50M=CS5Visit_50F=CS5Visit_TM=CS5Visit_TF=
CSNoCompletedPEP_11M=CSNoCompletedPEP_11F=CSNoCompletedPEP_17M=CSNoCompletedPEP_17F=CSNoCompletedPEP_49M=CSNoCompletedPEP_49F=CSNoCompletedPEP_50M=CSNoCompletedPEP_50F=CSNoCompletedPEP_TM=CSNoCompletedPEP_TF=
CSNoSeroconverted_11M=CSNoSeroconverted_11F=CSNoSeroconverted_17M=CSNoSeroconverted_17F=CSNoSeroconverted_49M=CSNoSeroconverted_49F=CSNoSeroconverted_50M=CSNoSeroconverted_50F=CSNoSeroconverted_TM=CSNoSeroconverted_TF=
CSNoPregnant_11M=CSNoPregnant_11F=CSNoPregnant_17M=CSNoPregnant_17F=CSNoPregnant_49M=CSNoPregnant_49F=CSNoPregnant_50M=CSNoPregnant_50F=CSNoPregnant_TM=CSNoPregnant_TF=
CSNoCompletedTrauma_11M=CSNoCompletedTrauma_11F=CSNoCompletedTrauma_17M=CSNoCompletedTrauma_17F=CSNoCompletedTrauma_49M=CSNoCompletedTrauma_49F=CSNoCompletedTrauma_50M=CSNoCompletedTrauma_50F=CSNoCompletedTrauma_TM=CSNoCompletedTrauma_TF="";
        
        
        
        String query="SELECT * FROM moh364 WHERE id='"+id+"'"; 
        conn.rs=conn.st.executeQuery(query);
        if(conn.rs.next()){
               PSRapeSurvivor_11M= conn.rs.getString("");
               PSRapeSurvivor_11F= conn.rs.getString("");
               PSRapeSurvivor_17M= conn.rs.getString("");
               PSRapeSurvivor_17F= conn.rs.getString("");
               PSRapeSurvivor_49M= conn.rs.getString("");
               PSRapeSurvivor_49F= conn.rs.getString("");
               PSRapeSurvivor_50M= conn.rs.getString("");
               PSRapeSurvivor_50F= conn.rs.getString("");
               PSRapeSurvivor_TM= conn.rs.getString("");
               PSRapeSurvivor_TF= conn.rs.getString("");
               PSPresenting72_11M= conn.rs.getString("");
               PSPresenting72_11F= conn.rs.getString("");
               PSPresenting72_17M= conn.rs.getString("");
               PSPresenting72_17F= conn.rs.getString("");
               PSPresenting72_49M= conn.rs.getString("");
               PSPresenting72_49F= conn.rs.getString("");
               PSPresenting72_50M= conn.rs.getString("");
               PSPresenting72_50F= conn.rs.getString("");
               PSPresenting72_TM= conn.rs.getString("");
               PSPresenting72_TF= conn.rs.getString("");
               PSInitiatedPEP_11M= conn.rs.getString("");
               PSInitiatedPEP_11F= conn.rs.getString("");
               PSInitiatedPEP_17M= conn.rs.getString("");
               PSInitiatedPEP_17F= conn.rs.getString("");
               PSInitiatedPEP_49M= conn.rs.getString("");
               PSInitiatedPEP_49F= conn.rs.getString("");
               PSInitiatedPEP_50M= conn.rs.getString("");
               PSInitiatedPEP_50F= conn.rs.getString("");
               PSInitiatedPEP_TM= conn.rs.getString("");
               PSInitiatedPEP_TF= conn.rs.getString("");
               PSSTITreatment_11M= conn.rs.getString("");
               PSSTITreatment_11F= conn.rs.getString("");
               PSSTITreatment_17M= conn.rs.getString("");
               PSSTITreatment_17F= conn.rs.getString("");
               PSSTITreatment_49M= conn.rs.getString("");
               PSSTITreatment_49F= conn.rs.getString("");
               PSSTITreatment_50M= conn.rs.getString("");
               PSSTITreatment_50F= conn.rs.getString("");
               PSSTITreatment_TM= conn.rs.getString("");
               PSSTITreatment_TF= conn.rs.getString("");
               PSEligibleContraceptive_11M= conn.rs.getString("");
               PSEligibleContraceptive_11F= conn.rs.getString("");
               PSEligibleContraceptive_17M= conn.rs.getString("");
               PSEligibleContraceptive_17F= conn.rs.getString("");
               PSEligibleContraceptive_49M= conn.rs.getString("");
               PSEligibleContraceptive_49F= conn.rs.getString("");
               PSEligibleContraceptive_50M= conn.rs.getString("");
               PSEligibleContraceptive_50F= conn.rs.getString("");
               PSEligibleContraceptive_TM= conn.rs.getString("");
               PSEligibleContraceptive_TF= conn.rs.getString("");
               PSGivenContraceptive_11M= conn.rs.getString("");
               PSGivenContraceptive_11F= conn.rs.getString("");
               PSGivenContraceptive_17M= conn.rs.getString("");
               PSGivenContraceptive_17F= conn.rs.getString("");
               PSGivenContraceptive_49M= conn.rs.getString("");
               PSGivenContraceptive_49F= conn.rs.getString("");
               PSGivenContraceptive_50M= conn.rs.getString("");
               PSGivenContraceptive_50F= conn.rs.getString("");
               PSGivenContraceptive_TM= conn.rs.getString("");
               PSGivenContraceptive_TF= conn.rs.getString("");
               PSTestedHIV_11M= conn.rs.getString("");
               PSTestedHIV_11F= conn.rs.getString("");
               PSTestedHIV_17M= conn.rs.getString("");
               PSTestedHIV_17F= conn.rs.getString("");
               PSTestedHIV_49M= conn.rs.getString("");
               PSTestedHIV_49F= conn.rs.getString("");
               PSTestedHIV_50M= conn.rs.getString("");
               PSTestedHIV_50F= conn.rs.getString("");
               PSTestedHIV_TM= conn.rs.getString("");
               PSTestedHIV_TF= conn.rs.getString("");
               PS1STVisit_11M= conn.rs.getString("");
               PS1STVisit_11F= conn.rs.getString("");
               PS1STVisit_17M= conn.rs.getString("");
               PS1STVisit_17F= conn.rs.getString("");
               PS1STVisit_49M= conn.rs.getString("");
               PS1STVisit_49F= conn.rs.getString("");
               PS1STVisit_50M= conn.rs.getString("");
               PS1STVisit_50F= conn.rs.getString("");
               PS1STVisit_TM= conn.rs.getString("");
               PS1STVisit_TF= conn.rs.getString("");
               PSDisability_11M= conn.rs.getString("");
               PSDisability_11F= conn.rs.getString("");
               PSDisability_17M= conn.rs.getString("");
               PSDisability_17F= conn.rs.getString("");
               PSDisability_49M= conn.rs.getString("");
               PSDisability_49F= conn.rs.getString("");
               PSDisability_50M= conn.rs.getString("");
               PSDisability_50F= conn.rs.getString("");
               PSDisability_TM= conn.rs.getString("");
               PSDisability_TF= conn.rs.getString("");
               PSPerpetrator_11M= conn.rs.getString("");
               PSPerpetrator_11F= conn.rs.getString("");
               PSPerpetrator_17M= conn.rs.getString("");
               PSPerpetrator_17F= conn.rs.getString("");
               PSPerpetrator_49M= conn.rs.getString("");
               PSPerpetrator_49F= conn.rs.getString("");
               PSPerpetrator_50M= conn.rs.getString("");
               PSPerpetrator_50F= conn.rs.getString("");
               PSPerpetrator_TM= conn.rs.getString("");
               PSPerpetrator_TF= conn.rs.getString("");
               CS1Visit_11M= conn.rs.getString("");
               CS1Visit_11F= conn.rs.getString("");
               CS1Visit_17M= conn.rs.getString("");
               CS1Visit_17F= conn.rs.getString("");
               CS1Visit_49M= conn.rs.getString("");
               CS1Visit_49F= conn.rs.getString("");
               CS1Visit_50M= conn.rs.getString("");
               CS1Visit_50F= conn.rs.getString("");
               CS1Visit_TM= conn.rs.getString("");
               CS1Visit_TF= conn.rs.getString("");
               CS2Visit_11M= conn.rs.getString("");
               CS2Visit_11F= conn.rs.getString("");
               CS2Visit_17M= conn.rs.getString("");
               CS2Visit_17F= conn.rs.getString("");
               CS2Visit_49M= conn.rs.getString("");
               CS2Visit_49F= conn.rs.getString("");
               CS2Visit_50M= conn.rs.getString("");
               CS2Visit_50F= conn.rs.getString("");
               CS2Visit_TM= conn.rs.getString("");
               CS2Visit_TF= conn.rs.getString("");
               CS3Visit_11M= conn.rs.getString("");
               CS3Visit_11F= conn.rs.getString("");
               CS3Visit_17M= conn.rs.getString("");
               CS3Visit_17F= conn.rs.getString("");
               CS3Visit_49M= conn.rs.getString("");
               CS3Visit_49F= conn.rs.getString("");
               CS3Visit_50M= conn.rs.getString("");
               CS3Visit_50F= conn.rs.getString("");
               CS3Visit_TM= conn.rs.getString("");
               CS3Visit_TF= conn.rs.getString("");
               CS4Visit_11M= conn.rs.getString("");
               CS4Visit_11F= conn.rs.getString("");
               CS4Visit_17M= conn.rs.getString("");
               CS4Visit_17F= conn.rs.getString("");
               CS4Visit_49M= conn.rs.getString("");
               CS4Visit_49F= conn.rs.getString("");
               CS4Visit_50M= conn.rs.getString("");
               CS4Visit_50F= conn.rs.getString("");
               CS4Visit_TM= conn.rs.getString("");
               CS4Visit_TF= conn.rs.getString("");
               CS5Visit_11M= conn.rs.getString("");
               CS5Visit_11F= conn.rs.getString("");
               CS5Visit_17M= conn.rs.getString("");
               CS5Visit_17F= conn.rs.getString("");
               CS5Visit_49M= conn.rs.getString("");
               CS5Visit_49F= conn.rs.getString("");
               CS5Visit_50M= conn.rs.getString("");
               CS5Visit_50F= conn.rs.getString("");
               CS5Visit_TM= conn.rs.getString("");
               CS5Visit_TF= conn.rs.getString("");
               CSNoCompletedPEP_11M= conn.rs.getString("");
               CSNoCompletedPEP_11F= conn.rs.getString("");
               CSNoCompletedPEP_17M= conn.rs.getString("");
               CSNoCompletedPEP_17F= conn.rs.getString("");
               CSNoCompletedPEP_49M= conn.rs.getString("");
               CSNoCompletedPEP_49F= conn.rs.getString("");
               CSNoCompletedPEP_50M= conn.rs.getString("");
               CSNoCompletedPEP_50F= conn.rs.getString("");
               CSNoCompletedPEP_TM= conn.rs.getString("");
               CSNoCompletedPEP_TF= conn.rs.getString("");
               CSNoSeroconverted_11M= conn.rs.getString("");
               CSNoSeroconverted_11F= conn.rs.getString("");
               CSNoSeroconverted_17M= conn.rs.getString("");
               CSNoSeroconverted_17F= conn.rs.getString("");
               CSNoSeroconverted_49M= conn.rs.getString("");
               CSNoSeroconverted_49F= conn.rs.getString("");
               CSNoSeroconverted_50M= conn.rs.getString("");
               CSNoSeroconverted_50F= conn.rs.getString("");
               CSNoSeroconverted_TM= conn.rs.getString("");
               CSNoSeroconverted_TF= conn.rs.getString("");
               CSNoPregnant_11M= conn.rs.getString("");
               CSNoPregnant_11F= conn.rs.getString("");
               CSNoPregnant_17M= conn.rs.getString("");
               CSNoPregnant_17F= conn.rs.getString("");
               CSNoPregnant_49M= conn.rs.getString("");
               CSNoPregnant_49F= conn.rs.getString("");
               CSNoPregnant_50M= conn.rs.getString("");
               CSNoPregnant_50F= conn.rs.getString("");
               CSNoPregnant_TM= conn.rs.getString("");
               CSNoPregnant_TF= conn.rs.getString("");
               CSNoCompletedTrauma_11M= conn.rs.getString("");
               CSNoCompletedTrauma_11F= conn.rs.getString("");
               CSNoCompletedTrauma_17M= conn.rs.getString("");
               CSNoCompletedTrauma_17F= conn.rs.getString("");
               CSNoCompletedTrauma_49M= conn.rs.getString("");
               CSNoCompletedTrauma_49F= conn.rs.getString("");
               CSNoCompletedTrauma_50M= conn.rs.getString("");
               CSNoCompletedTrauma_50F= conn.rs.getString("");
               CSNoCompletedTrauma_TM= conn.rs.getString("");
               CSNoCompletedTrauma_TF= conn.rs.getString(""); 
        }
        
            out.println(output);
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
        Logger.getLogger(loadmoh364.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadmoh364.class.getName()).log(Level.SEVERE, null, ex);
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
