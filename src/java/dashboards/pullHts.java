/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboards;

import Datim.newdatimHTCResults_2018Q1;
import GapAnalysis.subcounty;
import database.dbConn;
import database.dbConnDash;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author EKaunda
 */
public class pullHts extends HttpServlet {

   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
          
          
//             HttpSession session=null;
//            session=request.getSession();
            
//            session.setAttribute("semi_annual", "");
//            session.setAttribute("quarter", "");
//            session.setAttribute("monthid", "5");
//            session.setAttribute("year", "2018");
//            session.setAttribute("reportDuration", "4");//quarterly, monthly,yearly
            

            // String sdate="201806";
            //String edate="201806";
            //String facil="";
            //htsdataset ds= new htsdataset();
            //dbConn conn1 = new dbConn();
            
            //totalHts

        } finally {
            out.close();
        }
    }
//constituency
//constituency
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(pullHts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(pullHts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public String hts_non731(String startyearmonth, String endyearmonth,String facil) {

        try {
           
            
            //_______________________________OVERVIEW______________________________
            //We are pulling data from various hts datatables table using a stored procedure( hts_dashboards ) into dashboards table2
            //The  assumption here is that the stored procedure will come in structure similar to the one for table2
            //any column output in the hts_dashboards stored procedure should also exist in table2
            //columns are fetched dynamically and then the respective data insert using a loop into the table  
            //the insert code is excecuted at the end of the loop
            
            dbConnDash conndb = new dbConnDash();
            dbConn conn = new dbConn();

            String facilitiestable = "subpartnera";

            int count1 = 1;

            String insertqry = " REPLACE INTO dashboards.table2 SET ";

            // String qry1 = "call tb_dashboard('2015-10-01','2016-09-30','')";
            String qry1 = "call hts_dashboard(\"" + startyearmonth + "\",\"" + endyearmonth + "\",\"" + facilitiestable + "\",\"" + facil + "\")";
            
            conn.rs = conn.st.executeQuery(qry1);

            ResultSetMetaData metaData = conn.rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            ArrayList mycolumns1 = new ArrayList();

            while (conn.rs.next()) {

                if (count1 == 1) {
//headers only

                    for (int i = 1; i <= columnCount; i++) {

                        if (i < columnCount) {

                            mycolumns1.add(metaData.getColumnLabel(i));
                            //build column
                            insertqry += " `dashboards`.`table2`.`" + metaData.getColumnLabel(i) + "`=?, ";

                        } else {
                            
                 //last column we dont need a coma at the end of the column name
                 //also initialize a prepared statement at this level
                 
                            mycolumns1.add(metaData.getColumnLabel(i));
                          
                            insertqry += " `dashboards`.`table2`.`" + metaData.getColumnLabel(i) + "`=? ";
                            // valuesqry+=" ? )";
                            conndb.pst = conn.conn.prepareStatement(insertqry);

                        }

                    }//end of for loop
                    count1++;
                }//end of if
                //data rows     

                for (int a = 0; a < columnCount; a++) {

                    conndb.pst.setString(a + 1, conn.rs.getString(mycolumns1.get(a).toString()));

                    if (a == (columnCount - 1)) {
                        conndb.pst.executeUpdate();
                        System.out.println("" + conndb.pst);
                    }
                }

                count1++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(pullTb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Data pulled";
    }


public String hts731( String sdate, String edate, String facil){
    
    
    dbConn conn = new dbConn();
            
           
            String facilwhere="";
            
        if(!facil.equals("")){ facilwhere=" ( and  subpartnerId='"+facil+"' ) ";}
            
      
            int year = new Integer(edate.substring(0,4));
            
            Calendar ca= Calendar.getInstance();
            
            int currentyear=ca.get(Calendar.YEAR);
            
           
            
              htsdataset htsclass= new htsdataset();
            
        try {
            
            //insert overall hts
                 //HashMap <String,String> htshm = htsclass.HtsTst(conn,sdate,edate,facil);
            
                 HashMap<String,String> ipd_hm=htsclass.Ipd(conn,sdate,edate,facil);
                 HashMap<String,String> opd_hm=htsclass.Opd(conn,sdate,edate,facil);
                 HashMap<String,String> vct_hm=htsclass.Vct(conn,sdate,edate,facil);
                 HashMap<String,String> pmtct_hm=htsclass.Pmtct(conn,sdate,edate,facil);
                 HashMap<String,String> hts_hm=htsclass.HtsTst(conn,sdate,edate,facil);
            
            
            insertHts(hts_hm, sdate,edate, facil, "HTS-POS@9",false, "9");//HTS POS
            //insertHts(hts_hm, sdate,edate, facil, "HTS TST@8",false, "8");//HTS TST
//            insertHts(ipd_hm, sdate,edate, facil, "HTS - Inpatient Services@12",true, "12"); //HTS IPD
//            insertHts(opd_hm, sdate,edate, facil, "HTS - Pediatric Services@13",true, "13"); //HTS Pediatrics and Other PITC
//            insertHts(opd_hm, sdate,edate, facil, "HTS - Other PITC@20",true, "20"); //HTS Pediatrics and Other PITC
//            insertHts(pmtct_hm,sdate,edate,facil, "HTS - PMTCT (ANC Only) Clinics@16",true, "16"); //PMTCT ANC ONLY
//            insertHts(vct_hm, sdate,edate, facil, "HTS - VCT@21",true, "21"); //VCT
//           
            
        } catch (SQLException ex) {
            Logger.getLogger(pullHts.class.getName()).log(Level.SEVERE, null, ex);
        }
            

          
        
 return "done";
}

public String insertHts( HashMap htshmap, String sdate, String edate, String facil,String  level3,boolean haslevel4,String ordernumber) throws SQLException{
    
    
        try {
           
            String facilwhere="";
            
        if(!facil.equals("")){ facilwhere="  and  (subpartnerId='"+facil+"' ) ";}
            
        
            dbConnDash conndb = new dbConnDash();
            dbConn conn = new dbConn();
            
            int year = new Integer(edate.substring(0,4));
            
            Calendar ca= Calendar.getInstance();
            
            int currentyear=ca.get(Calendar.YEAR);
            
            String facilitiestable="subpartnera";
            
            int selectedyear=year;
            
            
            
            if(selectedyear<currentyear){
                
                if(year<2014){
                    
                    //db for 2014 is the smallest
                    
                    facilitiestable="subpartnera2014";
                    
                }
                else
                {
                    
                    facilitiestable="subpartnera"+selectedyear;
                    
                }
            }
            
           
HashMap <String,String> htshm = htshmap;
            
            
 String getsites="SELECT  county.County as county,district.DistrictNom as district, " //
                    + " "+facilitiestable+".SubPartnerNom as facility, "+facilitiestable+".CentreSanteId as mflcode, "+facilitiestable+".HTC_Support1 as htcsupport, IFNULL(ART_highvolume,0) as arthv,  IFNULL(HTC_highvolume,0) as htchv,  IFNULL(PMTCT_highvolume,0) as pmtcthv, IFNULL(HTC,0) as HTC, IFNULL(PMTCT,0) as PMTCT"
                    + " FROM    "+facilitiestable+" join (district join county on county.CountyID=district.CountyID)  on district.DistrictID = "+facilitiestable+".DistrictID    where ( HTC=1 OR PMTCT=1 )  AND "+facilitiestable+".active=1 "+facilwhere+"  group by "+facilitiestable+".SubPartnerID  ";
            
            System.out.println(""+getsites);
            conn.rs=conn.st.executeQuery(getsites);
            
            while(conn.rs.next()){
                
                String mfl=conn.rs.getString("mflcode");
                String cty=conn.rs.getString("county");
                String sty=conn.rs.getString("district");
                String fac=conn.rs.getString("facility");
              
                String st=conn.rs.getString("htcsupport");
                
                for(int ymn=new Integer(sdate);ymn<=new Integer(edate);ymn++){
                
                double gtt=new Double(htshm.getOrDefault("gtt_"+mfl+ymn,"0"));
                double gtp=new Double(htshm.getOrDefault("gtp_"+mfl+ymn,"0"));
                double ukpf=(new Double(htshm.getOrDefault("ukpf_"+mfl+ymn,"0")));
                double ukpm=(new Double(htshm.getOrDefault("ukpm_"+mfl+ymn,"0")));
                double pf1=(new Double(htshm.getOrDefault("pf1_"+mfl+ymn,"0")) );
                double pm1=(new Double(htshm.getOrDefault("pm1_"+mfl+ymn,"0")) );
                double pf4=(new Double(htshm.getOrDefault("pf4_"+mfl+ymn,"0")) );
                double pm4=(new Double(htshm.getOrDefault("pm4_"+mfl+ymn,"0")) );
                double pf9=(new Double(htshm.getOrDefault("pf9_"+mfl+ymn,"0")) );
                double pm9=(new Double(htshm.getOrDefault("pm9_"+mfl+ymn,"0")) );
                double pf14=(new Double(htshm.getOrDefault("pf14_"+mfl+ymn,"0")));
                double pm14=(new Double(htshm.getOrDefault("pm14_"+mfl+ymn,"0")));
                double pf19=(new Double(htshm.getOrDefault("pf19_"+mfl+ymn,"0")));
                double pm19=(new Double(htshm.getOrDefault("pm19_"+mfl+ymn,"0")));
                double pf24=(new Double(htshm.getOrDefault("pf24_"+mfl+ymn,"0")));
                double pm24=(new Double(htshm.getOrDefault("pm24_"+mfl+ymn,"0")));
                double pf29=(new Double(htshm.getOrDefault("pf29_"+mfl+ymn,"0")));
                double pm29=(new Double(htshm.getOrDefault("pm29_"+mfl+ymn,"0")));
                double pf34=(new Double(htshm.getOrDefault("pf34_"+mfl+ymn,"0")));
                double pm34=(new Double(htshm.getOrDefault("pm34_"+mfl+ymn,"0")));
                double pf39=(new Double(htshm.getOrDefault("pf39_"+mfl+ymn,"0")));
                double pm39=(new Double(htshm.getOrDefault("pm39_"+mfl+ymn,"0")));
                double pf49=(new Double(htshm.getOrDefault("pf49_"+mfl+ymn,"0")));
                double pm49=(new Double(htshm.getOrDefault("pm49_"+mfl+ymn,"0")));
                double pf50=(new Double(htshm.getOrDefault("pf50_"+mfl+ymn,"0")));
                double pm50=(new Double(htshm.getOrDefault("pm50_"+mfl+ymn,"0")));
                
                
                
                double uknf=(new Double(htshm.getOrDefault("uknf_"+mfl+ymn,"0")) );
                double uknm=(new Double(htshm.getOrDefault("uknm_"+mfl+ymn,"0")) );
                double nf1=(new Double(htshm.getOrDefault("nf1_"+mfl+ymn,"0")));
                double nm1=(new Double(htshm.getOrDefault("nm1_"+mfl+ymn,"0")));
                double nf4=(new Double(htshm.getOrDefault("nf4_"+mfl+ymn,"0")));
                double nm4=(new Double(htshm.getOrDefault("nm4_"+mfl+ymn,"0")));
                double nf9=(new Double(htshm.getOrDefault("nf9_"+mfl+ymn,"0")));
                double nm9=(new Double(htshm.getOrDefault("nm9_"+mfl+ymn,"0")));
                double nf14=(new Double(htshm.getOrDefault("nf14_"+mfl+ymn,"0")));
                double nm14=(new Double(htshm.getOrDefault("nm14_"+mfl+ymn,"0")));
                double nf19=(new Double(htshm.getOrDefault("nf19_"+mfl+ymn,"0")));
                double nm19=(new Double(htshm.getOrDefault("nm19_"+mfl+ymn,"0")));
                double nf24=(new Double(htshm.getOrDefault("nf24_"+mfl+ymn,"0")));
                double nm24=(new Double(htshm.getOrDefault("nm24_"+mfl+ymn,"0")));
                double nf29=(new Double(htshm.getOrDefault("nf29_"+mfl+ymn,"0")));
                double nm29=(new Double(htshm.getOrDefault("nm29_"+mfl+ymn,"0")));
                double nf34=(new Double(htshm.getOrDefault("nf34_"+mfl+ymn,"0")));
                double nm34=(new Double(htshm.getOrDefault("nm34_"+mfl+ymn,"0")));
                double nf39=(new Double(htshm.getOrDefault("nf39_"+mfl+ymn,"0")));
                double nm39=(new Double(htshm.getOrDefault("nm39_"+mfl+ymn,"0")));
                double nf49=(new Double(htshm.getOrDefault("nf49_"+mfl+ymn,"0")));
                double nm49=(new Double(htshm.getOrDefault("nm49_"+mfl+ymn,"0")));
                double nf50=(new Double(htshm.getOrDefault("nf50_"+mfl+ymn,"0")));
                double nm50=(new Double(htshm.getOrDefault("nm50_"+mfl+ymn,"0")));
                double tt  =(new Double(htshm.getOrDefault("tt_"+mfl+ymn,"0")));
                String burdencategory = htshm.getOrDefault("burdencategory_"+mfl+ymn,"");
                String constituency = htshm.getOrDefault("constituency_"+mfl+ymn,"");
                String ward = htshm.getOrDefault("ward_"+mfl+ymn,"");
                String yr = htshm.getOrDefault("year_"+mfl+ymn,"");
                String semiannual = htshm.getOrDefault("semiannual_"+mfl+ymn,"");
                String qtr = htshm.getOrDefault("quarter_"+mfl+ymn,"");
                String mn = htshm.getOrDefault("month_"+mfl+ymn,"");
                String ym = htshm.getOrDefault("yearmonth_"+mfl+ymn,"");
                String ownedby = htshm.getOrDefault("ownedby_"+mfl+ymn,"");
                String facilitytype = htshm.getOrDefault("facilitytype_"+mfl+ymn,"");
                String art_hv = htshm.getOrDefault("art_hv_"+mfl+ymn,"0");
                String htc_hv = htshm.getOrDefault("htc_hv_"+mfl+ymn,"0");
                String pmtct_hv = htshm.getOrDefault("pmtct_hv_"+mfl+ymn,"0");
                String activity_hv = htshm.getOrDefault("activity_hv_"+mfl+ymn,"0");
                String latitude = htshm.getOrDefault("latitude_"+mfl+ymn,"");
                String longitude = htshm.getOrDefault("longitude_"+mfl+ymn,"");
                String maleclinic = htshm.getOrDefault("maleclinic_"+mfl+ymn,"0");
                String adoleclinic = htshm.getOrDefault("adoleclinic_"+mfl+ymn,"0");
                String viremiaclinic = htshm.getOrDefault("viremiaclinic_"+mfl+ymn,"0");
                String emrsite = htshm.getOrDefault("emrsite_"+mfl+ymn,"0");
                String linkdesk = htshm.getOrDefault("linkdesk_"+mfl+ymn,"0");
                String islocked = htshm.getOrDefault("islocked_"+mfl+ymn,"0");
                
                    System.out.println(" Loop number="+ymn+"   facility "+fac+"  "+tt);
                
String insertqry = " REPLACE INTO dashboards.table2 SET "
+ " id = ?  ," +
"county = ?  ," +
"burdencategory = ?  ," +
"constituency = ?  ," +
"subcounty = ?  ," +
"ward = ?  ," +
"facility = ?  ," +
"mflcode = ?  ," +
"supporttype = ?  ," +
"level1 = ?  ," +
"level2 = ?  ," +
"level3 = ?  ," +
"level4 = ?  ," +
"unknown_f = ?  ," +
"unknown_m = ?  ," +
"d60 = ?  ," +
"mn_0_2 = ?  ," +
"mn_2_12 = ?  ," +
"mn_2_4y = ?  ," +
"f_1 = ?  ," +
"m_1 = ?  ," +
"t_1 = ?  ," +
"f_4 = ?  ," +
"m_4 = ?  ," +
"f_5_9 = ?  ," +
"m_5_9 = ?  ," +
"f_1_9 = ?  ," +
"m_1_9 = ?  ," +
"t_1_9 = ?  ," +
"f_14 = ?  ," +
"m_14 = ?  ," +
"f_19 = ?  ," +
"m_19 = ?  ," +
"f_24 = ?  ," +
"m_24 = ?  ," +
"f_29 = ?  ," +
"m_29 = ?  ," +
"f_34 = ?  ," +
"m_34 = ?  ," +
"f_39 = ?  ," +
"m_39 = ?  ," +
"f_49 = ?  ," +
"m_49 = ?  ," +
"f_25_49 = ?  ," +
"m_25_49 = ?  ," +
"f_50 = ?  ," +
"m_50 = ?  ," +
"total = ?  ," +
"total_f = ?  ," +
"total_m = ?  ," +
"paeds_f = ?  ," +
"paeds_m = ?  ," +
"paeds = ?  ," +
"adult_f = ?  ," +
"adult_m = ?  ," +
"adult = ?  ," +
"f_15_24 = ?  ," +
"m_15_24 = ?  ," +
"t_15_24 = ?  ," +
"year = ?  ," +
"semiannual = ?  ," +
"quarter = ?  ," +
"month = ?  ," +
"yearmonth = ?  ," +
"ownedby = ?  ," +
"facilitytype = ?  ," +
"art_hv = ?  ," +
"htc_hv = ?  ," +
"pmtct_hv = ?  ," +
"activity_hv = ?  ," +
"latitude = ?  ," +
"longitude = ?  ," +
"maleclinic = ?  ," +
"adoleclinic = ?  ," +
"viremiaclinic = ?  ," +
"emrsite = ?  ," +
"linkdesk = ?  ," +
"islocked = ?  ," +

"ordernumber = ?    ";
                
   conndb.pst = conn.conn.prepareStatement(insertqry);
   
   String Level3arr[]=level3.split("@");
   
   String level4="";
   
   if(haslevel4 && !level3.contains("HTS - Pediatric Services")){
//Tested 40
      if(1==1){
          
   String id=ym+"_"+mfl+"_3_"+Level3arr[1]+"_40";
 conndb.pst.setString(1,id);
   conndb.pst.setString(2,cty);
   conndb.pst.setString(3,burdencategory);
   conndb.pst.setString(4,constituency);
   conndb.pst.setString(5,sty);
   conndb.pst.setString(6,ward);
   conndb.pst.setString(7,fac);
   conndb.pst.setString(8,mfl);
   conndb.pst.setString(9,st);
   conndb.pst.setString(10,"90=Knowing HIV Status");
   conndb.pst.setString(11,"HTS");
   conndb.pst.setString(12,Level3arr[0]);
   conndb.pst.setString(13,"Tested");
   conndb.pst.setInt(14,new Integer((int) (uknf+ukpf)));
   conndb.pst.setInt(15,new Integer((int) (uknm+ukpm)));
   conndb.pst.setInt(16,0);
   conndb.pst.setInt(17,0);
   conndb.pst.setInt(18,0);
   conndb.pst.setInt(19,0);
   conndb.pst.setInt(20,new Integer((int) (pf1+nf1)));
   conndb.pst.setInt(21,new Integer((int) (pm1+nm1)));
   conndb.pst.setInt(22,new Integer((int) (pm1+nm1+pf1+nf1)));
   conndb.pst.setInt(23,new Integer((int) (pf4+nf4)));
   conndb.pst.setInt(24,new Integer((int) (pm4+nm4)));
   conndb.pst.setInt(25,new Integer((int) (pf9+nf9)));
   conndb.pst.setInt(26,new Integer((int) (pm9+nm9)));
   conndb.pst.setInt(27,new Integer((int) (pf4+nf4+pf9+nf9)));
   conndb.pst.setInt(28,new Integer((int) (pm4+nm4+pm9+nm9)));
   conndb.pst.setInt(29,new Integer((int) (pf4+nf4+pf9+nf9+pm4+nm4+pm9+nm9)));
   conndb.pst.setInt(30,new Integer((int) (pf14+nf14)));
   conndb.pst.setInt(31,new Integer((int) (pm14+nm14)));
   conndb.pst.setInt(32,new Integer((int) (pf19+nf19)));
   conndb.pst.setInt(33,new Integer((int) (pm19+nm19)));
   conndb.pst.setInt(34,new Integer((int) (pf24+nf24)));
   conndb.pst.setInt(35,new Integer((int) (pm24+nm24)));
   conndb.pst.setInt(36,new Integer((int) (pf29+nf29)));
   conndb.pst.setInt(37,new Integer((int) (pm29+nm29)));
   conndb.pst.setInt(38,new Integer((int) (pf34+nf34)));
   conndb.pst.setInt(39,new Integer((int) (pm34+nm34)));
   conndb.pst.setInt(40,new Integer((int) (pf39+nf39)));
   conndb.pst.setInt(41,new Integer((int) (pm39+nm39)));
   conndb.pst.setInt(42,new Integer((int) (pf49+nf49)));
   conndb.pst.setInt(43,new Integer((int) (pm49+nm49)));
   conndb.pst.setInt(44,new Integer((int) (pf29+nf29+pf34+nf34+pf39+nf39+pf49+nf49)));
   conndb.pst.setInt(45,new Integer((int) (pm29+nm29+pm34+nm34+pm39+nm39+pm49+nm49)));
   conndb.pst.setInt(46,new Integer((int) (pf50+nf50)));
   conndb.pst.setInt(47,new Integer((int) (pm50+nm50)));
   conndb.pst.setInt(48,new Integer((int) (tt)));
   conndb.pst.setInt(49,new Integer((int) (pf1+nf1+pf4+nf4+pf9+nf9+pf14+nf14+pf19+nf19+pf24+nf24+pf29+nf29+pf34+nf34+pf39+nf39+pf49+nf49+pf50+nf50)));
   conndb.pst.setInt(50,new Integer((int) (pm1+nm1+pm4+nm4+pm9+nm9+pm14+nm14+pm19+nm19+pm24+nm24+pm29+nm29+pm34+nm34+pm39+nm39+pm49+nm49+pm50+nm50)));
   conndb.pst.setInt(51,new Integer((int) (pf1+nf1+pf4+nf4+pf9+nf9+pf14+nf14)));
   conndb.pst.setInt(52,new Integer((int) (pm1+nm1+pm4+nm4+pm9+nm9+pm14+nm14)));
   conndb.pst.setInt(53,new Integer((int) (pf1+nf1+pf4+nf4+pf9+nf9+pf14+nf14+pm1+nm1+pm4+nm4+pm9+nm9+pm14+nm14)));
   conndb.pst.setInt(54,new Integer((int) (pf19+nf19+pf24+nf24+pf29+nf29+pf34+nf34+pf39+nf39+pf49+nf49+pf50+nf50)));
   conndb.pst.setInt(55,new Integer((int) (pm19+nm19+pm24+nm24+pm29+nm29+pm34+nm34+pm39+nm39+pm49+nm49+pm50+nm50)));
   conndb.pst.setInt(56,new Integer((int) (pf19+nf19+pf24+nf24+pf29+nf29+pf34+nf34+pf39+nf39+pf49+nf49+pf50+nf50+pm19+nm19+pm24+nm24+pm29+nm29+pm34+nm34+pm39+nm39+pm49+nm49+pm50+nm50)));
   conndb.pst.setInt(57,new Integer((int) (pf19+nf19+pf24+nf24)));
   conndb.pst.setInt(58,new Integer((int) (pm19+nm19+pm24+nm24)));
   conndb.pst.setInt(59,new Integer((int) (pf19+nf19+pf24+nf24+pm19+nm19+pm24+nm24)));
   conndb.pst.setString(60,yr);
   conndb.pst.setString(61,semiannual);
   conndb.pst.setString(62,qtr);
   conndb.pst.setString(63,mn);
   conndb.pst.setString(64,ym);
   conndb.pst.setString(65,ownedby);
   conndb.pst.setString(66,facilitytype);
   conndb.pst.setString(67,art_hv);
   conndb.pst.setString(68,htc_hv);
   conndb.pst.setString(69,pmtct_hv);
   conndb.pst.setString(70,activity_hv);
   conndb.pst.setString(71,latitude);
   conndb.pst.setString(72,longitude);
   conndb.pst.setString(73,maleclinic);
   conndb.pst.setString(74,adoleclinic);
   conndb.pst.setString(75,viremiaclinic);
   conndb.pst.setString(76,emrsite);
   conndb.pst.setString(77,linkdesk);
   conndb.pst.setString(78,islocked);
   conndb.pst.setString(79,ordernumber);
    if(!ym.equals("")){
   conndb.pst.executeUpdate(); 
        System.out.println(""+conndb.pst);
   }
   
          
      
      }
      

//positive 41
      if(2==2){
       
          
          
   String id=ym+"_"+mfl+"_3_"+Level3arr[1]+"_41";
 conndb.pst.setString(1,id);
   conndb.pst.setString(2,cty);
   conndb.pst.setString(3,burdencategory);
   conndb.pst.setString(4,constituency);
   conndb.pst.setString(5,sty);
   conndb.pst.setString(6,ward);
   conndb.pst.setString(7,fac);
   conndb.pst.setString(8,mfl);
   conndb.pst.setString(9,st);
   conndb.pst.setString(10,"90=Knowing HIV Status");
   conndb.pst.setString(11,"HTS");
   conndb.pst.setString(12,Level3arr[0]);
   conndb.pst.setString(13,"Positive");
   conndb.pst.setInt(14,new Integer((int) (ukpf)));
   conndb.pst.setInt(15,new Integer((int) (ukpm)));
   conndb.pst.setInt(16,0);
   conndb.pst.setInt(17,0);
   conndb.pst.setInt(18,0);
   conndb.pst.setInt(19,0);
   conndb.pst.setInt(20,new Integer((int) (pf1)));
   conndb.pst.setInt(21,new Integer((int) (pm1)));
   conndb.pst.setInt(22,new Integer((int) (pm1+pf1)));
   conndb.pst.setInt(23,new Integer((int) (pf4)));
   conndb.pst.setInt(24,new Integer((int) (pm4)));
   conndb.pst.setInt(25,new Integer((int) (pf9)));
   conndb.pst.setInt(26,new Integer((int) (pm9)));
   conndb.pst.setInt(27,new Integer((int) (pf4+pf9)));
   conndb.pst.setInt(28,new Integer((int) (pm4+pm9)));
   conndb.pst.setInt(29,new Integer((int) (pf4+pf9+pm4+pm9)));
   conndb.pst.setInt(30,new Integer((int) (pf14)));
   conndb.pst.setInt(31,new Integer((int) (pm14)));
   conndb.pst.setInt(32,new Integer((int) (pf19)));
   conndb.pst.setInt(33,new Integer((int) (pm19)));
   conndb.pst.setInt(34,new Integer((int) (pf24)));
   conndb.pst.setInt(35,new Integer((int) (pm24)));
   conndb.pst.setInt(36,new Integer((int) (pf29)));
   conndb.pst.setInt(37,new Integer((int) (pm29)));
   conndb.pst.setInt(38,new Integer((int) (pf34)));
   conndb.pst.setInt(39,new Integer((int) (pm34)));
   conndb.pst.setInt(40,new Integer((int) (pf39)));
   conndb.pst.setInt(41,new Integer((int) (pm39)));
   conndb.pst.setInt(42,new Integer((int) (pf49)));
   conndb.pst.setInt(43,new Integer((int) (pm49)));
   conndb.pst.setInt(44,new Integer((int) (pf29+pf34+pf39+pf49)));
   conndb.pst.setInt(45,new Integer((int) (pm29+pm34+pm39+pm49)));
   conndb.pst.setInt(46,new Integer((int) (pf50)));
   conndb.pst.setInt(47,new Integer((int) (pm50)));
   conndb.pst.setInt(48,new Integer((int) (gtp)));
   conndb.pst.setInt(49,new Integer((int) (pf1+pf4+pf9+pf14+pf19+pf24+pf29+pf34+pf39+pf49+pf50)));
   conndb.pst.setInt(50,new Integer((int) (pm1+pm4+pm9+pm14+pm19+pm24+pm29+pm34+pm39+pm49+pm50)));
   conndb.pst.setInt(51,new Integer((int) (pf1+pf4+pf9+pf14)));
   conndb.pst.setInt(52,new Integer((int) (pm1+pm4+pm9+pm14)));
   conndb.pst.setInt(53,new Integer((int) (pf1+pf4+pf9+pf14+pm1+pm4+pm9+pm14)));
   conndb.pst.setInt(54,new Integer((int) (pf19+pf24+pf29+pf34+pf39+pf49+pf50)));
   conndb.pst.setInt(55,new Integer((int) (pm19+pm24+pm29+pm34+pm39+pm49+pm50)));
   conndb.pst.setInt(56,new Integer((int) (pf19+pf24+pf29+pf34+pf39+pf49+pf50+pm19+pm24+pm29+pm34+pm39+pm49+pm50)));
   conndb.pst.setInt(57,new Integer((int) (pf19+pf24)));
   conndb.pst.setInt(58,new Integer((int) (pm19+pm24)));
   conndb.pst.setInt(59,new Integer((int) (pf19+pf24+pm19+pm24)));
   conndb.pst.setString(60,yr);
   conndb.pst.setString(61,semiannual);
   conndb.pst.setString(62,qtr);
   conndb.pst.setString(63,mn);
   conndb.pst.setString(64,ym);
   conndb.pst.setString(65,ownedby);
   conndb.pst.setString(66,facilitytype);
   conndb.pst.setString(67,art_hv);
   conndb.pst.setString(68,htc_hv);
   conndb.pst.setString(69,pmtct_hv);
   conndb.pst.setString(70,activity_hv);
   conndb.pst.setString(71,latitude);
   conndb.pst.setString(72,longitude);
   conndb.pst.setString(73,maleclinic);
   conndb.pst.setString(74,adoleclinic);
   conndb.pst.setString(75,viremiaclinic);
   conndb.pst.setString(76,emrsite);
   conndb.pst.setString(77,linkdesk);
   conndb.pst.setString(78,islocked);
   conndb.pst.setString(79,ordernumber);
   //only insert sites that have submitted data
   if(!ym.equals("")){
   conndb.pst.executeUpdate(); 
    System.out.println(""+conndb.pst);
   }
      
          
      }
      }
   
   else if(haslevel4 && level3.contains("HTS - Pediatric Services")){
   
   
//Tested 40
      if(1==1){
          
   String id=ym+"_"+mfl+"_3_"+Level3arr[1]+"_40";
 conndb.pst.setString(1,id);
   conndb.pst.setString(2,cty);
   conndb.pst.setString(3,burdencategory);
   conndb.pst.setString(4,constituency);
   conndb.pst.setString(5,sty);
   conndb.pst.setString(6,ward);
   conndb.pst.setString(7,fac);
   conndb.pst.setString(8,mfl);
   conndb.pst.setString(9,st);
   conndb.pst.setString(10,"90=Knowing HIV Status");
   conndb.pst.setString(11,"HTS");
   conndb.pst.setString(12,Level3arr[0]);
   conndb.pst.setString(13,"Tested");
   conndb.pst.setInt(14,new Integer((int) (uknf+ukpf)));
   conndb.pst.setInt(15,new Integer((int) (uknm+ukpm)));
   conndb.pst.setInt(16,0);
   conndb.pst.setInt(17,0);
   conndb.pst.setInt(18,0);
   conndb.pst.setInt(19,0);
   conndb.pst.setInt(20,new Integer((int) (pf1+nf1)));
   conndb.pst.setInt(21,new Integer((int) (pm1+nm1)));
   conndb.pst.setInt(22,new Integer((int) (pm1+nm1+pf1+nf1)));
   conndb.pst.setInt(23,new Integer((int) (pf4+nf4)));
   conndb.pst.setInt(24,new Integer((int) (pm4+nm4)));
   conndb.pst.setInt(25,0);
   conndb.pst.setInt(26,0);
   conndb.pst.setInt(27,new Integer((int) (pf4+nf4)));
   conndb.pst.setInt(28,new Integer((int) (pm4+nm4)));
   conndb.pst.setInt(29,new Integer((int) (pf4+nf4+pm4+nm4)));
   conndb.pst.setInt(30,0);
   conndb.pst.setInt(31,0);
   conndb.pst.setInt(32,0);
   conndb.pst.setInt(33,0);
   conndb.pst.setInt(34,0);
   conndb.pst.setInt(35,0);
   conndb.pst.setInt(36,0);
   conndb.pst.setInt(37,0);
   conndb.pst.setInt(38,0);
   conndb.pst.setInt(39,0);
   conndb.pst.setInt(40,0);
   conndb.pst.setInt(41,0);
   conndb.pst.setInt(42,0);
   conndb.pst.setInt(43,0);
   conndb.pst.setInt(44,0);
   conndb.pst.setInt(45,0);
   conndb.pst.setInt(46,0);
   conndb.pst.setInt(47,0);
   conndb.pst.setInt(48,new Integer((int) (pf1+nf1+pf4+nf4+pm1+nm1+pm4+nm4)));
   conndb.pst.setInt(49,new Integer((int) (pf1+nf1+pf4+nf4)));
   conndb.pst.setInt(50,new Integer((int) (pm1+nm1+pm4+nm4)));
   conndb.pst.setInt(51,new Integer((int) (pf1+nf1+pf4+nf4)));
   conndb.pst.setInt(52,new Integer((int) (pm1+nm1+pm4+nm4)));
   conndb.pst.setInt(53,new Integer((int) (pf1+nf1+pf4+nf4+pm1+nm1+pm4+nm4)));
   conndb.pst.setInt(54,0);
   conndb.pst.setInt(55,0);
   conndb.pst.setInt(56,0);
   conndb.pst.setInt(57,0);
   conndb.pst.setInt(58,0);
   conndb.pst.setInt(59,0);
   conndb.pst.setString(60,yr);
   conndb.pst.setString(61,semiannual);
   conndb.pst.setString(62,qtr);
   conndb.pst.setString(63,mn);
   conndb.pst.setString(64,ym);
   conndb.pst.setString(65,ownedby);
   conndb.pst.setString(66,facilitytype);
   conndb.pst.setString(67,art_hv);
   conndb.pst.setString(68,htc_hv);
   conndb.pst.setString(69,pmtct_hv);
   conndb.pst.setString(70,activity_hv);
   conndb.pst.setString(71,latitude);
   conndb.pst.setString(72,longitude);
   conndb.pst.setString(73,maleclinic);
   conndb.pst.setString(74,adoleclinic);
   conndb.pst.setString(75,viremiaclinic);
   conndb.pst.setString(76,emrsite);
   conndb.pst.setString(77,linkdesk);
   conndb.pst.setString(78,islocked);
   conndb.pst.setString(79,ordernumber);
 //only insert sites that have submitted data
   if(!ym.equals("")){
        System.out.println(""+conndb.pst);
   conndb.pst.executeUpdate(); 
   }
   
      }
      

//positive 41
      if(2==2){
       
   String id=ym+"_"+mfl+"_3_"+Level3arr[1]+"_41";
 conndb.pst.setString(1,id);
   conndb.pst.setString(2,cty);
   conndb.pst.setString(3,burdencategory);
   conndb.pst.setString(4,constituency);
   conndb.pst.setString(5,sty);
   conndb.pst.setString(6,ward);
   conndb.pst.setString(7,fac);
   conndb.pst.setString(8,mfl);
   conndb.pst.setString(9,st);
   conndb.pst.setString(10,"90=Knowing HIV Status");
   conndb.pst.setString(11,"HTS");
   conndb.pst.setString(12,Level3arr[0]);
   conndb.pst.setString(13,"Positive");
   
   conndb.pst.setInt(14,new Integer((int) (uknf+ukpf)));
   conndb.pst.setInt(15,new Integer((int) (uknm+ukpm)));
   conndb.pst.setInt(16,0);
   conndb.pst.setInt(17,0);
   conndb.pst.setInt(18,0);
   conndb.pst.setInt(19,0);
   conndb.pst.setInt(20,new Integer((int) (pf1)));
   conndb.pst.setInt(21,new Integer((int) (pm1)));
   conndb.pst.setInt(22,new Integer((int) (pm1+pf1)));
   conndb.pst.setInt(23,new Integer((int) (pf4)));
   conndb.pst.setInt(24,new Integer((int) (pm4)));
   conndb.pst.setInt(25,0);
   conndb.pst.setInt(26,0);
   conndb.pst.setInt(27,new Integer((int) (pf4)));
   conndb.pst.setInt(28,new Integer((int) (pm4)));
   conndb.pst.setInt(29,new Integer((int) (pf4+pm4)));
   conndb.pst.setInt(30,0);
   conndb.pst.setInt(31,0);
   conndb.pst.setInt(32,0);
   conndb.pst.setInt(33,0);
   conndb.pst.setInt(34,0);
   conndb.pst.setInt(35,0);
   conndb.pst.setInt(36,0);
   conndb.pst.setInt(37,0);
   conndb.pst.setInt(38,0);
   conndb.pst.setInt(39,0);
   conndb.pst.setInt(40,0);
   conndb.pst.setInt(41,0);
   conndb.pst.setInt(42,0);
   conndb.pst.setInt(43,0);
   conndb.pst.setInt(44,0);
   conndb.pst.setInt(45,0);
   conndb.pst.setInt(46,0);
   conndb.pst.setInt(47,0);
   conndb.pst.setInt(48,new Integer((int) (pf1+pf4+pm1+pm4)));
   conndb.pst.setInt(49,new Integer((int) (pf1+pf4)));
   conndb.pst.setInt(50,new Integer((int) (pm1+pm4)));
   conndb.pst.setInt(51,new Integer((int) (pf1+pf4)));
   conndb.pst.setInt(52,new Integer((int) (pm1+pm4)));
   conndb.pst.setInt(53,new Integer((int) (pf1+pf4+pm1+pm4)));
   conndb.pst.setInt(54,0);
   conndb.pst.setInt(55,0);
   conndb.pst.setInt(56,0);
   conndb.pst.setInt(57,0);
   conndb.pst.setInt(58,0);
   conndb.pst.setInt(59,0);
   conndb.pst.setString(60,yr);
   conndb.pst.setString(61,semiannual);
   conndb.pst.setString(62,qtr);
   conndb.pst.setString(63,mn);
   conndb.pst.setString(64,ym);
   conndb.pst.setString(65,ownedby);
   conndb.pst.setString(66,facilitytype);
   conndb.pst.setString(67,art_hv);
   conndb.pst.setString(68,htc_hv);
   conndb.pst.setString(69,pmtct_hv);
   conndb.pst.setString(70,activity_hv);
   conndb.pst.setString(71,latitude);
   conndb.pst.setString(72,longitude);
   conndb.pst.setString(73,maleclinic);
   conndb.pst.setString(74,adoleclinic);
   conndb.pst.setString(75,viremiaclinic);
   conndb.pst.setString(76,emrsite);
   conndb.pst.setString(77,linkdesk);
   conndb.pst.setString(78,islocked);
   conndb.pst.setString(79,ordernumber);
   //only insert sites that have submitted data
   if(!ym.equals("")){
   conndb.pst.executeUpdate(); 
    System.out.println(""+conndb.pst);
   }
   
      
          
      }
      
   
   }
   
   
   else {
   //HTS-POS
if(level3.contains("@9")){
          
   String id=ym+"_"+mfl+"_3_"+Level3arr[1]+"";
 conndb.pst.setString(1,id);
   conndb.pst.setString(2,cty);
   conndb.pst.setString(3,burdencategory);
   conndb.pst.setString(4,constituency);
   conndb.pst.setString(5,sty);
   conndb.pst.setString(6,ward);
   conndb.pst.setString(7,fac);
   conndb.pst.setString(8,mfl);
   conndb.pst.setString(9,st);
   conndb.pst.setString(10,"90=Knowing HIV Status");
   conndb.pst.setString(11,"HTS");
   conndb.pst.setString(12,Level3arr[0]);
   conndb.pst.setString(13,"");
   conndb.pst.setInt(14,new Integer((int) (ukpf)));
   conndb.pst.setInt(15,new Integer((int) (ukpm)));
   conndb.pst.setInt(16,0);
   conndb.pst.setInt(17,0);
   conndb.pst.setInt(18,0);
   conndb.pst.setInt(19,0);
   conndb.pst.setInt(20,new Integer((int) (pf1)));
   conndb.pst.setInt(21,new Integer((int) (pm1)));
   conndb.pst.setInt(22,new Integer((int) (pm1+pf1)));
   conndb.pst.setInt(23,new Integer((int) (pf4)));
   conndb.pst.setInt(24,new Integer((int) (pm4)));
   conndb.pst.setInt(25,new Integer((int) (pf9)));
   conndb.pst.setInt(26,new Integer((int) (pm9)));
   conndb.pst.setInt(27,new Integer((int) (pf4+pf9)));
   conndb.pst.setInt(28,new Integer((int) (pm4+pm9)));
   conndb.pst.setInt(29,new Integer((int) (pf4+pf9+pm4+pm9)));
   conndb.pst.setInt(30,new Integer((int) (pf14)));
   conndb.pst.setInt(31,new Integer((int) (pm14)));
   conndb.pst.setInt(32,new Integer((int) (pf19)));
   conndb.pst.setInt(33,new Integer((int) (pm19)));
   conndb.pst.setInt(34,new Integer((int) (pf24)));
   conndb.pst.setInt(35,new Integer((int) (pm24)));
   conndb.pst.setInt(36,new Integer((int) (pf29)));
   conndb.pst.setInt(37,new Integer((int) (pm29)));
   conndb.pst.setInt(38,new Integer((int) (pf34)));
   conndb.pst.setInt(39,new Integer((int) (pm34)));
   conndb.pst.setInt(40,new Integer((int) (pf39)));
   conndb.pst.setInt(41,new Integer((int) (pm39)));
   conndb.pst.setInt(42,new Integer((int) (pf49)));
   conndb.pst.setInt(43,new Integer((int) (pm49)));
   conndb.pst.setInt(44,new Integer((int) (pf29+pf34+pf39+pf49)));
   conndb.pst.setInt(45,new Integer((int) (pm29+pm34+pm39+pm49)));
   conndb.pst.setInt(46,new Integer((int) (pf50)));
   conndb.pst.setInt(47,new Integer((int) (pm50)));
   conndb.pst.setInt(48,new Integer((int) (gtp)));
   conndb.pst.setInt(49,new Integer((int) (pf1+pf4+pf9+pf14+pf19+pf24+pf29+pf34+pf39+pf49+pf50)));
   conndb.pst.setInt(50,new Integer((int) (pm1+pm4+pm9+pm14+pm19+pm24+pm29+pm34+pm39+pm49+pm50)));
   conndb.pst.setInt(51,new Integer((int) (pf1+pf4+pf9+pf14)));
   conndb.pst.setInt(52,new Integer((int) (pm1+pm4+pm9+pm14)));
   conndb.pst.setInt(53,new Integer((int) (pf1+pf4+pf9+pf14+pm1+pm4+pm9+pm14)));
   conndb.pst.setInt(54,new Integer((int) (pf19+pf24+pf29+pf34+pf39+pf49+pf50)));
   conndb.pst.setInt(55,new Integer((int) (pm19+pm24+pm29+pm34+pm39+pm49+pm50)));
   conndb.pst.setInt(56,new Integer((int) (pf19+pf24+pf29+pf34+pf39+pf49+pf50+pm19+pm24+pm29+pm34+pm39+pm49+pm50)));
   conndb.pst.setInt(57,new Integer((int) (pf19+pf24)));
   conndb.pst.setInt(58,new Integer((int) (pm19+pm24)));
   conndb.pst.setInt(59,new Integer((int) (pf19+pf24+pm19+pm24)));
   conndb.pst.setString(60,yr);
   conndb.pst.setString(61,semiannual);
   conndb.pst.setString(62,qtr);
   conndb.pst.setString(63,mn);
   conndb.pst.setString(64,ym);
   conndb.pst.setString(65,ownedby);
   conndb.pst.setString(66,facilitytype);
   conndb.pst.setString(67,art_hv);
   conndb.pst.setString(68,htc_hv);
   conndb.pst.setString(69,pmtct_hv);
   conndb.pst.setString(70,activity_hv);
   conndb.pst.setString(71,latitude);
   conndb.pst.setString(72,longitude);
   conndb.pst.setString(73,maleclinic);
   conndb.pst.setString(74,adoleclinic);
   conndb.pst.setString(75,viremiaclinic);
   conndb.pst.setString(76,emrsite);
   conndb.pst.setString(77,linkdesk);
   conndb.pst.setString(78,islocked);
   conndb.pst.setString(79,ordernumber);
 //only insert sites that have submitted data
   if(!ym.equals("")){
   conndb.pst.executeUpdate(); 
    System.out.println(""+conndb.pst);
   } 
   
     
          
      }//end of if
//HTS-TST
if(level3.contains("@8")){

    
    
          
   String id=ym+"_"+mfl+"_3_"+Level3arr[1]+"_40";
 conndb.pst.setString(1,id);
   conndb.pst.setString(2,cty);
   conndb.pst.setString(3,burdencategory);
   conndb.pst.setString(4,constituency);
   conndb.pst.setString(5,sty);
   conndb.pst.setString(6,ward);
   conndb.pst.setString(7,fac);
   conndb.pst.setString(8,mfl);
   conndb.pst.setString(9,st);
   conndb.pst.setString(10,"90=Knowing HIV Status");
   conndb.pst.setString(11,"HTS");
   conndb.pst.setString(12,Level3arr[0]);
   conndb.pst.setString(13,"");
   conndb.pst.setInt(14,new Integer((int) (uknf+ukpf)));
   conndb.pst.setInt(15,new Integer((int) (uknm+ukpm)));
   conndb.pst.setInt(16,0);
   conndb.pst.setInt(17,0);
   conndb.pst.setInt(18,0);
   conndb.pst.setInt(19,0);
   conndb.pst.setInt(20,new Integer((int) (pf1+nf1)));
   conndb.pst.setInt(21,new Integer((int) (pm1+nm1)));
   conndb.pst.setInt(22,new Integer((int) (pm1+nm1+pf1+nf1)));
   conndb.pst.setInt(23,new Integer((int) (pf4+nf4)));
   conndb.pst.setInt(24,new Integer((int) (pm4+nm4)));
   conndb.pst.setInt(25,new Integer((int) (pf9+nf9)));
   conndb.pst.setInt(26,new Integer((int) (pm9+nm9)));
   conndb.pst.setInt(27,new Integer((int) (pf4+nf4+pf9+nf9)));
   conndb.pst.setInt(28,new Integer((int) (pm4+nm4+pm9+nm9)));
   conndb.pst.setInt(29,new Integer((int) (pf4+nf4+pf9+nf9+pm4+nm4+pm9+nm9)));
   conndb.pst.setInt(30,new Integer((int) (pf14+nf14)));
   conndb.pst.setInt(31,new Integer((int) (pm14+nm14)));
   conndb.pst.setInt(32,new Integer((int) (pf19+nf19)));
   conndb.pst.setInt(33,new Integer((int) (pm19+nm19)));
   conndb.pst.setInt(34,new Integer((int) (pf24+nf24)));
   conndb.pst.setInt(35,new Integer((int) (pm24+nm24)));
   conndb.pst.setInt(36,new Integer((int) (pf29+nf29)));
   conndb.pst.setInt(37,new Integer((int) (pm29+nm29)));
   conndb.pst.setInt(38,new Integer((int) (pf34+nf34)));
   conndb.pst.setInt(39,new Integer((int) (pm34+nm34)));
   conndb.pst.setInt(40,new Integer((int) (pf39+nf39)));
   conndb.pst.setInt(41,new Integer((int) (pm39+nm39)));
   conndb.pst.setInt(42,new Integer((int) (pf49+nf49)));
   conndb.pst.setInt(43,new Integer((int) (pm49+nm49)));
   conndb.pst.setInt(44,new Integer((int) (pf29+nf29+pf34+nf34+pf39+nf39+pf49+nf49)));
   conndb.pst.setInt(45,new Integer((int) (pm29+nm29+pm34+nm34+pm39+nm39+pm49+nm49)));
   conndb.pst.setInt(46,new Integer((int) (pf50+nf50)));
   conndb.pst.setInt(47,new Integer((int) (pm50+nm50)));
   conndb.pst.setInt(48,new Integer((int) (tt)));
   conndb.pst.setInt(49,new Integer((int) (pf1+nf1+pf4+nf4+pf9+nf9+pf14+nf14+pf19+nf19+pf24+nf24+pf29+nf29+pf34+nf34+pf39+nf39+pf49+nf49+pf50+nf50)));
   conndb.pst.setInt(50,new Integer((int) (pm1+nm1+pm4+nm4+pm9+nm9+pm14+nm14+pm19+nm19+pm24+nm24+pm29+nm29+pm34+nm34+pm39+nm39+pm49+nm49+pm50+nm50)));
   conndb.pst.setInt(51,new Integer((int) (pf1+nf1+pf4+nf4+pf9+nf9+pf14+nf14)));
   conndb.pst.setInt(52,new Integer((int) (pm1+nm1+pm4+nm4+pm9+nm9+pm14+nm14)));
   conndb.pst.setInt(53,new Integer((int) (pf1+nf1+pf4+nf4+pf9+nf9+pf14+nf14+pm1+nm1+pm4+nm4+pm9+nm9+pm14+nm14)));
   conndb.pst.setInt(54,new Integer((int) (pf19+nf19+pf24+nf24+pf29+nf29+pf34+nf34+pf39+nf39+pf49+nf49+pf50+nf50)));
   conndb.pst.setInt(55,new Integer((int) (pm19+nm19+pm24+nm24+pm29+nm29+pm34+nm34+pm39+nm39+pm49+nm49+pm50+nm50)));
   conndb.pst.setInt(56,new Integer((int) (pf19+nf19+pf24+nf24+pf29+nf29+pf34+nf34+pf39+nf39+pf49+nf49+pf50+nf50+pm19+nm19+pm24+nm24+pm29+nm29+pm34+nm34+pm39+nm39+pm49+nm49+pm50+nm50)));
   conndb.pst.setInt(57,new Integer((int) (pf19+nf19+pf24+nf24)));
   conndb.pst.setInt(58,new Integer((int) (pm19+nm19+pm24+nm24)));
   conndb.pst.setInt(59,new Integer((int) (pf19+nf19+pf24+nf24+pm19+nm19+pm24+nm24)));
   conndb.pst.setString(60,yr);
   conndb.pst.setString(61,semiannual);
   conndb.pst.setString(62,qtr);
   conndb.pst.setString(63,mn);
   conndb.pst.setString(64,ym);
   conndb.pst.setString(65,ownedby);
   conndb.pst.setString(66,facilitytype);
   conndb.pst.setString(67,art_hv);
   conndb.pst.setString(68,htc_hv);
   conndb.pst.setString(69,pmtct_hv);
   conndb.pst.setString(70,activity_hv);
   conndb.pst.setString(71,latitude);
   conndb.pst.setString(72,longitude);
   conndb.pst.setString(73,maleclinic);
   conndb.pst.setString(74,adoleclinic);
   conndb.pst.setString(75,viremiaclinic);
   conndb.pst.setString(76,emrsite);
   conndb.pst.setString(77,linkdesk);
   conndb.pst.setString(78,islocked);
   conndb.pst.setString(79,ordernumber);
  //only insert sites that have submitted data
   if(!ym.equals("")){
        System.out.println(""+conndb.pst);
   conndb.pst.executeUpdate(); 
   
       //System.out.println("Teeesting____"+(pf19+""+nf19+""+pf24+""+nf24+""+pf29+""+nf29+""+pf34+""+nf34+""+pf39+""+nf39+""+pf49+""+nf49+""+pf50+""+nf50+""+pm19+""+nm19+""+pm24+""+nm24+""+pm29+""+nm29+""+pm34+""+nm34+""+pm39+""+nm39+""+pm49+""+nm49+""+pm50+""+nm50=pf19+nf19+pf24+nf24+pf29+nf29+pf34+nf34+pf39+nf39+pf49+nf49+pf50+nf50+pm19+nm19+pm24+nm24+pm29+nm29+pm34+nm34+pm39+nm39+pm49+nm49+pm50+nm50));
   }
   
      
      
      
    
    
}


}  
   
        
            }
               
            }// end of while
        
        } catch (SQLException ex) {
            Logger.getLogger(pullHts.class.getName()).log(Level.SEVERE, null, ex);
        }
 return "done";
}


}
