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
public class SaveSummary extends HttpServlet {
HttpSession session;
String program_area_id,cordinator,districts,agency,venue,curriculum_id,start_date,end_date,training_name,year,month,year_month,s_male,s_female,user_id,date_range,nurse,rco,hrio,dclerk,pharmtec,labtec,mo,couns,hts_screener,acouns,linkdesk,mmother,mfather,expclient,chv,sto,to,tio,meo,mea,hro,dma,otherc;
String output;
int summary_id;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           dbConn conn = new dbConn();
           session = request.getSession();
           
           output= "";
           summary_id=0;
           
           program_area_id = request.getParameter("progam_area");
           cordinator = request.getParameter("cordinator");
           String districts_array = request.getParameter("districts");
           agency = request.getParameter("agency");
           venue = request.getParameter("venue");
           String curriculum_ids = request.getParameter("curriculum_ids");
           date_range = request.getParameter("date_range").replace("", "");
           end_date = request.getParameter("end_date");
           training_name = request.getParameter("training_name");
           s_male = request.getParameter("s_male");
           s_female = request.getParameter("s_female");
           
nurse = request.getParameter("nurse");
rco = request.getParameter("rco");
hrio = request.getParameter("hrio");
dclerk = request.getParameter("dclerk");
pharmtec = request.getParameter("pharmtec");
labtec = request.getParameter("labtec");
mo = request.getParameter("mo");
couns = request.getParameter("couns");
hts_screener = request.getParameter("hts_screener");
acouns = request.getParameter("acouns");
linkdesk = request.getParameter("linkdesk");
mmother = request.getParameter("mmother");
mfather = request.getParameter("mfather");
expclient = request.getParameter("expclient");
chv = request.getParameter("chv");
sto = request.getParameter("sto");
to = request.getParameter("to");
tio = request.getParameter("tio");
meo = request.getParameter("meo");
mea = request.getParameter("mea");
hro = request.getParameter("hro");
dma = request.getParameter("dma");
otherc = request.getParameter("otherc");

           
           String[] date_range_array=date_range.split("-");
           
           start_date=date_range_array[0].replace("/", "-");
           end_date=date_range_array[1].replace("/", "-");
           
           year =end_date.split("-")[0];
           month =end_date.split("-")[1];
           year_month = year+""+month;
            System.out.println("districts_array: "+districts_array+" cordinator : "+cordinator);
           districts="*"+districts_array.replace(",","*");
           
           curriculum_id="*"+curriculum_ids.replace(",","*");
           user_id = "1";
           
           
           String resout = program_area_id+","+cordinator+","+districts+","+agency+","+venue+","+curriculum_id+","+start_date+","+end_date+","+training_name+","+year+","+month+","+year_month+","+s_male+","+s_female+","+user_id+","+nurse+","+rco+","+hrio+","+dclerk+","+pharmtec+","+labtec+","+mo+","+couns+","+hts_screener+","+acouns+","+linkdesk+","+mmother+","+mfather+","+expclient+","+chv+","+sto+","+to+","+tio+","+meo+","+mea+","+hro+","+dma+","+otherc;
            System.out.println(" data : "+resout);
           if(session.getAttribute("summary_id")==null){
           if(s_male.equals("")){s_male="0";}
           if(s_female.equals("")){s_female="0";}
           String checker="SELECT id FROM t1_summary WHERE program_area_id=? AND training_name=? AND start_date=? AND end_date=? AND districts=?";
           conn.pst=conn.conn.prepareStatement(checker);
           conn.pst.setString(1, program_area_id);
           conn.pst.setString(2, training_name);
           conn.pst.setString(3, start_date);
           conn.pst.setString(4, end_date);
           conn.pst.setString(5, districts);
           
            System.out.println("stmt : "+conn.pst);
           conn.rs=conn.pst.executeQuery();
           if(conn.rs.next()){
                summary_id=conn.rs.getInt(1);
                output="Similar record is already registered in our system."; 
           }
           
           else{
               String inserter="REPLACE INTO t1_summary SET program_area_id=?,cordinator=?,"
                    + "districts=?,agency=?,venue=?,curriculum_id=?,start_date=?,end_date=?,"
                    + "training_name=?,year=?,month=?,s_male=?,s_female=?,ym=?,user_id=? ,nurse=?,rco=?,hrio=?,dclerk=?,pharmtec=?,labtec=?,mo=?,couns=?,hts_screener=?,acouns=?,linkdesk=?,mmother=?,mfather=?,expclient=?,chv=?,sto=?,to=?,tio=?,meo=?,mea=?,hro=?,dma=?,otherc=?";
               conn.pst=conn.conn.prepareStatement(inserter);
               conn.pst.setString(1, program_area_id);
               conn.pst.setString(2, cordinator);
               conn.pst.setString(3, districts);
               conn.pst.setString(4, agency);
               conn.pst.setString(5, venue);
               conn.pst.setString(6, curriculum_id);
               conn.pst.setString(7, start_date);
               conn.pst.setString(8, end_date);
               conn.pst.setString(9, training_name);
               conn.pst.setString(10, year);
               conn.pst.setString(11, month);
               conn.pst.setString(12, s_male);
               conn.pst.setString(13, s_female);
               conn.pst.setString(14, year_month);
               conn.pst.setString(15, user_id);
              conn.pst.setString(16, nurse);
conn.pst.setString(17, rco);
conn.pst.setString(18, hrio);
conn.pst.setString(19, dclerk);
conn.pst.setString(20, pharmtec);
conn.pst.setString(21, labtec);
conn.pst.setString(22, mo);
conn.pst.setString(23, couns);
conn.pst.setString(24, hts_screener);
conn.pst.setString(25, acouns);
conn.pst.setString(26, linkdesk);
conn.pst.setString(27, mmother);
conn.pst.setString(28, mfather);
conn.pst.setString(29, expclient);
conn.pst.setString(30, chv);
conn.pst.setString(31, sto);
conn.pst.setString(32, to);
conn.pst.setString(33, tio);
conn.pst.setString(34, meo);
conn.pst.setString(35, mea);
conn.pst.setString(36, hro);
conn.pst.setString(37, dma);
conn.pst.setString(38, otherc);

               conn.pst.executeUpdate();
           output="Summary saved successfully.";
           
           String getMax="SELECT MAX(id) FROM t1_summary";
           conn.rs=conn.st.executeQuery(getMax);
           if(conn.rs.next()==true){
               summary_id=conn.rs.getInt(1);
           }
           }
            }
            else{
               summary_id = Integer.parseInt(session.getAttribute("summary_id").toString());
                String updator="UPDATE t1_summary SET program_area_id=?,cordinator=?,"
                    + "districts=?,agency=?,venue=?,curriculum_id=?,start_date=?,end_date=?,"
                    + "training_name=?,year=?,month=?,s_male=?,s_female=?,ym=?,user_id=?, ,nurse=?,rco=?,hrio=?,dclerk=?,pharmtec=?,labtec=?,mo=?,couns=?,hts_screener=?,acouns=?,linkdesk=?,mmother=?,mfather=?,expclient=?,chv=?,sto=?,to=?,tio=?,meo=?,mea=?,hro=?,dma=?,otherc=? WHERE id=?";
               conn.pst=conn.conn.prepareStatement(updator);
               conn.pst.setString(1, program_area_id);
               conn.pst.setString(2, cordinator);
               conn.pst.setString(3, districts);
               conn.pst.setString(4, agency);
               conn.pst.setString(5, venue);
               conn.pst.setString(6, curriculum_id);
               conn.pst.setString(7, start_date);
               conn.pst.setString(8, end_date);
               conn.pst.setString(9, training_name);
               conn.pst.setString(10, year);
               conn.pst.setString(11, month);
               conn.pst.setString(12, s_male);
               conn.pst.setString(13, s_female);
               conn.pst.setString(14, year_month);
               conn.pst.setString(15, user_id);
               conn.pst.setString(16, nurse);
conn.pst.setString(17, rco);
conn.pst.setString(18, hrio);
conn.pst.setString(19, dclerk);
conn.pst.setString(20, pharmtec);
conn.pst.setString(21, labtec);
conn.pst.setString(22, mo);
conn.pst.setString(23, couns);
conn.pst.setString(24, hts_screener);
conn.pst.setString(25, acouns);
conn.pst.setString(26, linkdesk);
conn.pst.setString(27, mmother);
conn.pst.setString(28, mfather);
conn.pst.setString(29, expclient);
conn.pst.setString(30, chv);
conn.pst.setString(31, sto);
conn.pst.setString(32, to);
conn.pst.setString(33, tio);
conn.pst.setString(34, meo);
conn.pst.setString(35, mea);
conn.pst.setString(36, hro);
conn.pst.setString(37, dma);
conn.pst.setString(38, otherc);

               conn.pst.setInt(39, summary_id);
              
               conn.pst.executeUpdate();
               
               output="Summary updated successfully.";
            }
            out.println(summary_id);
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
        Logger.getLogger(SaveSummary.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(SaveSummary.class.getName()).log(Level.SEVERE, null, ex);
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
