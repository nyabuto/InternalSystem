/*
Notes: This raw data is for positive EID. The data doesnt have age and sex
Age and sex should be gotten from the eid tested raw data during the importing of the raw data positives into the eid_datim_output table.

 */

package LoadExcels;

import database.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
@MultipartConfig(fileSizeThreshold=1024*1024*20, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100) 

/**
 *
 * @author Emmanuel Kaunda
 */


  public class importsgbv extends HttpServlet {
   
 
  
  String full_path="";
  String fileName="";
  int checker_dist,checker_hf;
  File file_source;
  HttpSession session;
  private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "uploads";
  String nextpage="";
  String quarterName,facilityName,facilityID,id,missingFacility;
          
 

 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    
      int year,month,quarter,checker,missing = 0,added = 0,updated = 0;
      String county_name,county_id, district_name,district_id,hf_name,hf_id;
     
  
String repesurvivorM="";
String repesurvivorF="";
String repesurvivorT="";
String initiatedPepM="";
String initiatedPepF="";
String initiatedPep="0";
     
     
  id=""; 
String indicator="";
String indicatorid="";
String adult_3m="";
String child_3m="";


String mflcode="";
String reportingyear="";
String reportingmonth="";
String yearmonth="201610";
int annee=0;
String slightmonth="";
String subpartnerID="";
 


String serialnumber="";
    
String dbname="sgbv";
  
     
     session=request.getSession();
     dbConn conn = new dbConn();
     nextpage="importsgbv.jsp";
     String applicationPath = request.getServletContext().getRealPath("");
     String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
     session=request.getSession();
     File fileSaveDir = new File(uploadFilePath);
     if (!fileSaveDir.exists()) {
         fileSaveDir.mkdirs();
     }
     System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
     for (Part part : request.getParts()) {
         fileName = getFileName(part);
         part.write(uploadFilePath + File.separator + fileName);
         System.out.println("file name is  :  "+fileName);
     }
     if(!fileName.endsWith(".xlsx")){
         nextpage="importsgbv.jsp";
         session.setAttribute("upload_success", "<font color=\"red\">Failed to load the excel file. Please choose a .xlsx excel file .</font>");
     }
     else{
         
         full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
         
         System.out.println("the saved file directory is  :  "+full_path);
// GET DATA FROM THE EXCEL AND AND OUTPUT IT ON THE CONSOLE..................................
         
         FileInputStream fileInputStream = new FileInputStream(full_path);
         XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
         
         int totalsheets=workbook.getNumberOfSheets();
         DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale


        
         for(int a=0;a<totalsheets;a++){
         
             try {
                 XSSFSheet worksheet = workbook.getSheetAt(a);
                 System.out.println( a+" ("+workbook.getSheetName(a)+") out of "+totalsheets+" sheets");
                 mflcode=workbook.getSheetName(a);
                 String getfacil="select SubPartnerID, subpartnernom from subpartnera where CentreSanteId='"+mflcode+"'";
                 conn.rs=conn.st.executeQuery(getfacil);
                 if(conn.rs.next())
                 {
                 
                 subpartnerID=conn.rs.getString(1);
                     System.out.println(""+conn.rs.getString(2));
                 }
//______________________________________________________________________
if(1==1) {
//______________________________________________________________________
//-----------facility name-----------------------
XSSFCell cellfacil = worksheet.getRow(0).getCell((short) 0);

String ym="";
if(cellfacil.getCellType()==0){
    //numeric
    ym =""+(int)cellfacil.getNumericCellValue();
}
else if(cellfacil.getCellType()==1){
    ym =cellfacil.getStringCellValue();
}

if(ym.trim().equals("")){

}else {
yearmonth=ym;

}

year=new Integer(yearmonth.substring(0,4));
month=new Integer(yearmonth.substring(4));

if(month>=10){
    
annee=year+1;
slightmonth=""+month;
    
             }
else {
    
 slightmonth=yearmonth.substring(5);
 
annee=year;

   }

 id=annee+"_"+slightmonth+"_"+subpartnerID;



//System.out.println("test__"+id+"  "+indicator);


String test="";



//-----------rape survivor M-----------------------
XSSFCell cellmfl = worksheet.getRow(12).getCell((short) 10);
    System.out.println("Cell type"+cellmfl.getCellType());
if(cellmfl.getCellType()==0){
    //numeric
    repesurvivorM =""+(int)cellmfl.getNumericCellValue();
}
else if(cellmfl.getCellType()==1){
    repesurvivorM =cellmfl.getStringCellValue();
}
else if(cellmfl.getCellType()==2){
    repesurvivorM =""+(int)cellmfl.getNumericCellValue();
}
    System.out.println("repesurvivorM___"+repesurvivorM+"____");
if(repesurvivorM.trim().equals("")){repesurvivorM="0";}



//-----------rape survivor M-----------------------
XSSFCell cellrps = worksheet.getRow(12).getCell((short) 11);

if(cellrps.getCellType()==0){
    //numeric
    repesurvivorF =""+(int)cellrps.getNumericCellValue();
}
else if(cellrps.getCellType()==1){
    repesurvivorF =cellrps.getStringCellValue();
}
if(cellrps.getCellType()==2){
    //numeric
    repesurvivorF =""+(int)cellrps.getNumericCellValue();
}

if(repesurvivorF.trim().equals("")){repesurvivorF="0";}

 System.out.println("repesurvivorF___"+repesurvivorF+"____");

 
 if(repesurvivorF.trim().equals("")){repesurvivorF="0";}
if(repesurvivorM.trim().equals("")){repesurvivorM="0";}
repesurvivorT=""+(new Integer(repesurvivorF)+new Integer(repesurvivorM));

 
//-----------rape initiatedpep pep-----------------------
XSSFCell cellipep = worksheet.getRow(14).getCell((short) 10);

if(cellipep.getCellType()==0){
    //numeric
    initiatedPepM =""+(int)cellipep.getNumericCellValue();
    
}
else if(cellipep.getCellType()==1){
    
    initiatedPepM =cellipep.getStringCellValue();
    
                                  }
else if(cellipep.getCellType()==2){
    //numeric
    initiatedPepM =""+(int)cellipep.getNumericCellValue();
    
}

 System.out.println("initiatedPepM___"+initiatedPepM+"____");

//-----------rape initiatedpep pep-----------------------
XSSFCell cellipep2 = worksheet.getRow(14).getCell((short) 11);

if(cellipep2.getCellType()==0){
    //numeric
    initiatedPepF =""+(int)cellipep2.getNumericCellValue();
    
}
else if(cellipep2.getCellType()==1){
    
    initiatedPepF =cellipep2.getStringCellValue();
    
                                  }
else if(cellipep2.getCellType()==2){
    //numeric
    initiatedPepF =""+(int)cellipep2.getNumericCellValue();
    
}

if(initiatedPepF.trim().equals("")){initiatedPepF="0";}
if(initiatedPepM.trim().equals("")){initiatedPepM="0";}
initiatedPep=""+(new Integer(initiatedPepF)+new Integer(initiatedPepM));




}






//_____________________________________________insert into db___________________________________________________






facilityID="";
checker=0;

//
String get_id="SELECT id FROM "+dbname+" WHERE id like ? ";
conn.pst1=conn.conn.prepareStatement(get_id);
conn.pst1.setString(1,"%"+id+"%");

conn.rs=conn.pst1.executeQuery();
if(conn.rs.next()==true)
{
 
    checker=1;
   
}
if(!mflcode.equals("")) {
//                        DISTRICT FOUND ADD THE HF TO THE SYSTEM.........................




if(checker==0){
    
    //id	SubPartnerID 	Mflcode	samplecode	collectiondate	testingdate	validation	enrollment	treatment_init_date	enroll_cccno	other_reasons	year	quarter
    
    String inserter="INSERT INTO "+dbname+" ( id,SubPartnerID,Mois,Annee,rapesurvivor_M,rapesurvivor_F,initiatedpep_M,initiatedpep_F,initiatedpep_T,rapesurvivor_T,yearmonth) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    conn.pst=conn.conn.prepareStatement(inserter);
    conn.pst.setString(1,id);
    conn.pst.setString(2,subpartnerID);
    conn.pst.setString(3,slightmonth);
    conn.pst.setString(4,""+annee);
    conn.pst.setString(5,repesurvivorM);
    conn.pst.setString(6,repesurvivorF);
    conn.pst.setString(7,initiatedPepM);
    conn.pst.setString(8,initiatedPepF);
    conn.pst.setString(9,initiatedPep);
       conn.pst.setString(10,repesurvivorT);
    conn.pst.setString(11,yearmonth);
    
    
    conn.pst.executeUpdate();
     
    
    
    added++;
    
}
else {
    //id,SubPartnerID,Year,Quarter,Mflcode,Sex ,age,agebracket,SubPartnerNom,dateoftesting,patientccc,batchno,supporttype
    String inserter=" UPDATE "+dbname+" SET id=?,SubPartnerID=?,Mois=?,Annee=?,rapesurvivor_M=?,rapesurvivor_F=?,initiatedpep_M=?,initiatedpep_F=?,initiatedpep_T=?,rapesurvivor_T=?,yearmonth=?"
            + " WHERE id=?";
//
    conn.pst=conn.conn.prepareStatement(inserter);

    conn.pst.setString(1,id);
    conn.pst.setString(2,subpartnerID);
    conn.pst.setString(3,slightmonth);
    conn.pst.setString(4,""+annee);
    conn.pst.setString(5,repesurvivorM);
    conn.pst.setString(6,repesurvivorF);
    conn.pst.setString(7,initiatedPepM);
    conn.pst.setString(8,initiatedPepF);
    conn.pst.setString(9,initiatedPep);
    conn.pst.setString(10,repesurvivorT);
    conn.pst.setString(11,yearmonth);
    conn.pst.setString(12,id);
    conn.pst.executeUpdate();
    
updated++;

}

}

else{
    missing++; 
//                        missing facilities
missingFacility+="facility  : "+facilityName+" mfl code : "+mflcode+" not in system <br>";
System.out.println(facilityName+ " has no mflcode.");
}


//_____________________________________________end of insert into db____________________________________________




Iterator rowIterator = worksheet.rowIterator();
int i=4,y=0;
//static number of rows
while(i<=34){
   
        System.out.println(" row number "+i);
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null )
        {
            
            break;
        }
        
//                 y++;
//                 
//                if( y==26)
//                 {
//                     y=0;
//                     break;
//                 }


if(i>=3 && i<=14) {


}//end of cell values



 i++;  
    
}   } //end of worksheets loop
             catch (SQLException ex) {
                 Logger.getLogger(importsgbv.class.getName()).log(Level.SEVERE, null, ex);
             }
         
         }

     }//end of checking if excel file is valid
     if(conn.rs!=null){try {
         conn.rs.close();
          } catch (SQLException ex) {
              Logger.getLogger(importsgbv.class.getName()).log(Level.SEVERE, null, ex);
          }
}
      if(conn.st!=null){try {
          conn.st.close();
          } catch (SQLException ex) {
              Logger.getLogger(importsgbv.class.getName()).log(Level.SEVERE, null, ex);
          }
}
      if(conn.pst!=null){try {
          conn.pst.close();
          } catch (SQLException ex) {
              Logger.getLogger(importsgbv.class.getName()).log(Level.SEVERE, null, ex);
          }
}
    String sessionText="<br/><b> "+added+ "</b> New data added <br/> <b> "+updated+"</b> updated facilities<br> <br> <b>"+missing+"</b> sites not in Imis Facilities List ";    
    session.setAttribute("upload_success", sessionText);
    response.sendRedirect(nextpage);  
 

 
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
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//     try {
//         processRequest(request, response);
//     } catch (SQLException ex) {
//         Logger.getLogger(loadPMTCT_FO.class.getName()).log(Level.SEVERE, null, ex);
//     }
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//     try {
//         processRequest(request, response);
//     } catch (SQLException ex) {
//         Logger.getLogger(loadPMTCT_FO.class.getName()).log(Level.SEVERE, null, ex);
//     }
//    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
      
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return contentDisp;
    }
    

    
}
