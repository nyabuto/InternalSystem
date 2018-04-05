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
public class loadIndexTesting extends HttpServlet {
HttpSession session;
String output,lock,enterdby,validity,Header;
String clientsListed,clientStartedART,contactsListed,newlyTested,identifiedPOS,contactStartedART;
String year,month;
String tableid,SubPartnerID,Annee,Mois,clientsListed_1m,clientsListed_1f,clientsListed_4m,clientsListed_4f,clientsListed_9m,clientsListed_9f,clientsListed_14m,clientsListed_14f,clientsListed_19m,clientsListed_19f,clientsListed_24m,clientsListed_24f,clientsListed_29m,clientsListed_29f,clientsListed_34m,clientsListed_34f,clientsListed_39m,clientsListed_39f,clientsListed_49m,clientsListed_49f,clientsListed_50m,clientsListed_50f,clientsListed_STm,clientsListed_STf,clientsListed_GT,clientStartedART_1m,clientStartedART_1f,clientStartedART_4m,clientStartedART_4f,clientStartedART_9m,clientStartedART_9f,clientStartedART_14m,clientStartedART_14f,clientStartedART_19m,clientStartedART_19f,clientStartedART_24m,clientStartedART_24f,clientStartedART_29m,clientStartedART_29f,clientStartedART_34m,clientStartedART_34f,clientStartedART_39m,clientStartedART_39f,clientStartedART_49m,clientStartedART_49f,clientStartedART_50m,clientStartedART_50f,clientStartedART_STm,clientStartedART_STf,clientStartedART_GT,contactsListed_1m,contactsListed_1f,contactsListed_4m,contactsListed_4f,contactsListed_9m,contactsListed_9f,contactsListed_14m,contactsListed_14f,contactsListed_19m,contactsListed_19f,contactsListed_24m,contactsListed_24f,contactsListed_29m,contactsListed_29f,contactsListed_34m,contactsListed_34f,contactsListed_39m,contactsListed_39f,contactsListed_49m,contactsListed_49f,contactsListed_50m,contactsListed_50f,contactsListed_STm,contactsListed_STf,contactsListed_GT,newlyTested_1m,newlyTested_1f,newlyTested_4m,newlyTested_4f,newlyTested_9m,newlyTested_9f,newlyTested_14m,newlyTested_14f,newlyTested_19m,newlyTested_19f,newlyTested_24m,newlyTested_24f,newlyTested_29m,newlyTested_29f,newlyTested_34m,newlyTested_34f,newlyTested_39m,newlyTested_39f,newlyTested_49m,newlyTested_49f,newlyTested_50m,newlyTested_50f,newlyTested_STm,newlyTested_STf,newlyTested_GT,identifiedPOS_1m,identifiedPOS_1f,identifiedPOS_4m,identifiedPOS_4f,identifiedPOS_9m,identifiedPOS_9f,identifiedPOS_14m,identifiedPOS_14f,identifiedPOS_19m,identifiedPOS_19f,identifiedPOS_24m,identifiedPOS_24f,identifiedPOS_29m,identifiedPOS_29f,identifiedPOS_34m,identifiedPOS_34f,identifiedPOS_39m,identifiedPOS_39f,identifiedPOS_49m,identifiedPOS_49f,identifiedPOS_50m,identifiedPOS_50f,identifiedPOS_STm,identifiedPOS_STf,identifiedPOS_GT,contactStartedART_1m,contactStartedART_1f,contactStartedART_4m,contactStartedART_4f,contactStartedART_9m,contactStartedART_9f,contactStartedART_14m,contactStartedART_14f,contactStartedART_19m,contactStartedART_19f,contactStartedART_24m,contactStartedART_24f,contactStartedART_29m,contactStartedART_29f,contactStartedART_34m,contactStartedART_34f,contactStartedART_39m,contactStartedART_39f,contactStartedART_49m,contactStartedART_49f,contactStartedART_50m,contactStartedART_50f,contactStartedART_STm,contactStartedART_STf,contactStartedART_GT,user_id,isValidated,isLocked,updatedBy,updatedOn,yearmonth,timestamp;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           tableid=SubPartnerID=Annee=Mois=clientsListed_1m=clientsListed_1f=clientsListed_4m=clientsListed_4f=clientsListed_9m=clientsListed_9f=clientsListed_14m=clientsListed_14f=clientsListed_19m=clientsListed_19f=clientsListed_24m=clientsListed_24f=clientsListed_29m=clientsListed_29f=clientsListed_34m=clientsListed_34f=clientsListed_39m=clientsListed_39f=clientsListed_49m=clientsListed_49f=clientsListed_50m=clientsListed_50f=clientsListed_STm=clientsListed_STf=clientsListed_GT=clientStartedART_1m=clientStartedART_1f=clientStartedART_4m=clientStartedART_4f=clientStartedART_9m=clientStartedART_9f=clientStartedART_14m=clientStartedART_14f=clientStartedART_19m=clientStartedART_19f=clientStartedART_24m=clientStartedART_24f=clientStartedART_29m=clientStartedART_29f=clientStartedART_34m=clientStartedART_34f=clientStartedART_39m=clientStartedART_39f=clientStartedART_49m=clientStartedART_49f=clientStartedART_50m=clientStartedART_50f=clientStartedART_STm=clientStartedART_STf=clientStartedART_GT=contactsListed_1m=contactsListed_1f=contactsListed_4m=contactsListed_4f=contactsListed_9m=contactsListed_9f=contactsListed_14m=contactsListed_14f=contactsListed_19m=contactsListed_19f=contactsListed_24m=contactsListed_24f=contactsListed_29m=contactsListed_29f=contactsListed_34m=contactsListed_34f=contactsListed_39m=contactsListed_39f=contactsListed_49m=contactsListed_49f=contactsListed_50m=contactsListed_50f=contactsListed_STm=contactsListed_STf=contactsListed_GT=newlyTested_1m=newlyTested_1f=newlyTested_4m=newlyTested_4f=newlyTested_9m=newlyTested_9f=newlyTested_14m=newlyTested_14f=newlyTested_19m=newlyTested_19f=newlyTested_24m=newlyTested_24f=newlyTested_29m=newlyTested_29f=newlyTested_34m=newlyTested_34f=newlyTested_39m=newlyTested_39f=newlyTested_49m=newlyTested_49f=newlyTested_50m=newlyTested_50f=newlyTested_STm=newlyTested_STf=newlyTested_GT=identifiedPOS_1m=identifiedPOS_1f=identifiedPOS_4m=identifiedPOS_4f=identifiedPOS_9m=identifiedPOS_9f=identifiedPOS_14m=identifiedPOS_14f=identifiedPOS_19m=identifiedPOS_19f=identifiedPOS_24m=identifiedPOS_24f=identifiedPOS_29m=identifiedPOS_29f=identifiedPOS_34m=identifiedPOS_34f=identifiedPOS_39m=identifiedPOS_39f=identifiedPOS_49m=identifiedPOS_49f=identifiedPOS_50m=identifiedPOS_50f=identifiedPOS_STm=identifiedPOS_STf=identifiedPOS_GT=contactStartedART_1m=contactStartedART_1f=contactStartedART_4m=contactStartedART_4f=contactStartedART_9m=contactStartedART_9f=contactStartedART_14m=contactStartedART_14f=contactStartedART_19m=contactStartedART_19f=contactStartedART_24m=contactStartedART_24f=contactStartedART_29m=contactStartedART_29f=contactStartedART_34m=contactStartedART_34f=contactStartedART_39m=contactStartedART_39f=contactStartedART_49m=contactStartedART_49f=contactStartedART_50m=contactStartedART_50f=contactStartedART_STm=contactStartedART_STf=contactStartedART_GT=user_id=isValidated=isLocked=updatedBy=updatedOn=yearmonth=timestamp="";
           dbConn conn = new dbConn();
           session = request.getSession();
           //   tableid="2018_10_331";
        lock="";
            String facil = "";

            validity="<b><font color='#4b8df8'>New Entry</font></b>";

            if (session.getAttribute("year") != null) {
                year = session.getAttribute("year").toString();
            }
            if (session.getAttribute("monthid") != null) {
                month = session.getAttribute("monthid").toString();
            }

            if (session.getAttribute("facilityid") != null) {
                facil = session.getAttribute("facilityid").toString();
            }
            tableid = year + "_" + month + "_" + facil;
        
int indextestingdone=0;
int indextestingundone=0;
int indextestingvalid=0;
int facilssupporting=0;
String distid="";

if(session.getAttribute("subcountyid")!=null){
distid=session.getAttribute("subcountyid").toString();
}


String indexTcounter="SELECT 1 FROM index_testing join subpartnera on index_testing.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and (clientsListed_GT is not null ||clientsListed_GT!='')  ";
 conn.rs1 = conn.st1.executeQuery(indexTcounter);
 while(conn.rs1.next()){
 indextestingdone++;
  }
 
  String indexTvalidatedcounter1="SELECT 1 FROM index_testing join subpartnera on index_testing.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and isValidated='0' ";
 conn.rs1 = conn.st1.executeQuery(indexTvalidatedcounter1);
 while(conn.rs1.next()){
 indextestingvalid++;
  }
 
            System.out.println(indexTcounter);
 
 String indexTcounter1="SELECT 1 FROM index_testing join subpartnera on index_testing.SubPartnerID=subpartnera.SubPartnerID where Annee ='"+year+"' and DistrictID='"+distid+"'  and Mois='"+month+"' and  isValidated='0' ";
 conn.rs1 = conn.st1.executeQuery(indexTcounter1);
 while(conn.rs1.next()){
 indextestingundone++;
  }
 
 
 
 
 String countHTCfacility="Select * from subpartnera where HTC ='1' and  DistrictID='"+distid+"'";
// String countfacility="Select * from subpartnera where FP='1' || PMTCT ='1' || Maternity='1' || HTC='1' ";
 conn.rs1 = conn.st1.executeQuery(countHTCfacility);
 while(conn.rs1.next()){
 facilssupporting++;
 }
 
String validated="&nbsp &nbsp Validated Form(s): <b>"+indextestingvalid+" </b>";
 String unvalidated="&nbsp &nbsp Unvalidated Form (s) <font color='black'><b>"+indextestingundone+"</b></font>";
 
 
  String unvalidatedLink="";int counter=0;
     if(indextestingundone>0){
     String getUnvalidated="SELECT index_testing.SubPartnerID,subpartnera.SubPartnerNom FROM index_testing JOIN subpartnera ON index_testing.SubPartnerID=subpartnera.SubPartnerID WHERE subpartnera.DistrictID='"+distid+"' AND index_testing.Mois='"+month+"' AND index_testing.Annee='"+year+"' AND index_testing.isValidated='0'";
     conn.rs=conn.st.executeQuery(getUnvalidated);
     while(conn.rs.next()){
         counter++;
//     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=Form731.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=loadIndexTesting.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
                    }
    }
     
   if(counter>0){
    unvalidated="<button class='btn btn-primary btn-lg' data-target='#unvalidatedModal' style='width:auto; height:auto;' data-toggle='modal' type='button'> Unvalidated Form (s) <span class='badge badge-important'><b>"+indextestingundone+"</b></span></button>";
 
   }  
 
 String label="Record counter <font color='white'><b>"+indextestingdone+"<b></font>  out of <b>"+facilssupporting+"</b>"+validated+unvalidated;
 
         enterdby="";   
            String check_data="SELECT * FROM index_testing WHERE tableid=?";
            conn.pst=conn.conn.prepareStatement(check_data);
            conn.pst.setString(1, tableid);
            conn.rs=conn.pst.executeQuery();
            if(conn.rs.next()){
                    tableid= conn.rs.getString("tableid");
                    SubPartnerID= conn.rs.getString("SubPartnerID");
                    Annee= conn.rs.getString("Annee");
                    Mois= conn.rs.getString("Mois");
                    if(conn.rs.getString("clientsListed_1m")!=null){clientsListed_1m= conn.rs.getString("clientsListed_1m");}
                    if(conn.rs.getString("clientsListed_1f")!=null){clientsListed_1f= conn.rs.getString("clientsListed_1f");}
                    if(conn.rs.getString("clientsListed_4m")!=null){clientsListed_4m= conn.rs.getString("clientsListed_4m");}
                    if(conn.rs.getString("clientsListed_4f")!=null){clientsListed_4f= conn.rs.getString("clientsListed_4f");}
                    if(conn.rs.getString("clientsListed_9m")!=null){clientsListed_9m= conn.rs.getString("clientsListed_9m");}
                    if(conn.rs.getString("clientsListed_9f")!=null){clientsListed_9f= conn.rs.getString("clientsListed_9f");}
                    if(conn.rs.getString("clientsListed_14m")!=null){clientsListed_14m= conn.rs.getString("clientsListed_14m");}
                    if(conn.rs.getString("clientsListed_14f")!=null){clientsListed_14f= conn.rs.getString("clientsListed_14f");}
                    if(conn.rs.getString("clientsListed_19m")!=null){clientsListed_19m= conn.rs.getString("clientsListed_19m");}
                    if(conn.rs.getString("clientsListed_19f")!=null){clientsListed_19f= conn.rs.getString("clientsListed_19f");}
                    if(conn.rs.getString("clientsListed_24m")!=null){clientsListed_24m= conn.rs.getString("clientsListed_24m");}
                    if(conn.rs.getString("clientsListed_24f")!=null){clientsListed_24f= conn.rs.getString("clientsListed_24f");}
                    if(conn.rs.getString("clientsListed_29m")!=null){clientsListed_29m= conn.rs.getString("clientsListed_29m");}
                    if(conn.rs.getString("clientsListed_29f")!=null){clientsListed_29f= conn.rs.getString("clientsListed_29f");}
                    if(conn.rs.getString("clientsListed_34m")!=null){clientsListed_34m= conn.rs.getString("clientsListed_34m");}
                    if(conn.rs.getString("clientsListed_34f")!=null){clientsListed_34f= conn.rs.getString("clientsListed_34f");}
                    if(conn.rs.getString("clientsListed_39m")!=null){clientsListed_39m= conn.rs.getString("clientsListed_39m");}
                    if(conn.rs.getString("clientsListed_39f")!=null){clientsListed_39f= conn.rs.getString("clientsListed_39f");}
                    if(conn.rs.getString("clientsListed_49m")!=null){clientsListed_49m= conn.rs.getString("clientsListed_49m");}
                   if(conn.rs.getString("clientsListed_49f")!=null){ clientsListed_49f= conn.rs.getString("clientsListed_49f");}
                    if(conn.rs.getString("clientsListed_50m")!=null){clientsListed_50m= conn.rs.getString("clientsListed_50m");}
                    if(conn.rs.getString("clientsListed_50f")!=null){clientsListed_50f= conn.rs.getString("clientsListed_50f");}
                    if(conn.rs.getString("clientsListed_STm")!=null){clientsListed_STm= conn.rs.getString("clientsListed_STm");}
                    if(conn.rs.getString("clientsListed_STf")!=null){clientsListed_STf= conn.rs.getString("clientsListed_STf");}
                    if(conn.rs.getString("clientsListed_GT")!=null){clientsListed_GT= conn.rs.getString("clientsListed_GT");}


                    
                    if(conn.rs.getString("clientStartedART_1m")!=null){clientStartedART_1m= conn.rs.getString("clientStartedART_1m");}
                    if(conn.rs.getString("clientStartedART_1f")!=null){clientStartedART_1f= conn.rs.getString("clientStartedART_1f");}
                   if(conn.rs.getString("clientStartedART_4m")!=null){ clientStartedART_4m= conn.rs.getString("clientStartedART_4m");}
                    if(conn.rs.getString("clientStartedART_4f")!=null){clientStartedART_4f= conn.rs.getString("clientStartedART_4f");}
                    if(conn.rs.getString("clientStartedART_9m")!=null){clientStartedART_9m= conn.rs.getString("clientStartedART_9m");}
                    if(conn.rs.getString("clientStartedART_9f")!=null){clientStartedART_9f= conn.rs.getString("clientStartedART_9f");}
                    if(conn.rs.getString("clientStartedART_14m")!=null){clientStartedART_14m= conn.rs.getString("clientStartedART_14m");}
                    if(conn.rs.getString("clientStartedART_14f")!=null){clientStartedART_14f= conn.rs.getString("clientStartedART_14f");}
                    if(conn.rs.getString("clientStartedART_19m")!=null){clientStartedART_19m= conn.rs.getString("clientStartedART_19m");}
                    if(conn.rs.getString("clientStartedART_19f")!=null){clientStartedART_19f= conn.rs.getString("clientStartedART_19f");}
                   if(conn.rs.getString("clientStartedART_24m")!=null){ clientStartedART_24m= conn.rs.getString("clientStartedART_24m");}
                   if(conn.rs.getString("clientStartedART_24f")!=null){ clientStartedART_24f= conn.rs.getString("clientStartedART_24f");}
                   if(conn.rs.getString("clientStartedART_29m")!=null){clientStartedART_29m= conn.rs.getString("clientStartedART_29m");}
                   if(conn.rs.getString("clientStartedART_29f")!=null){ clientStartedART_29f= conn.rs.getString("clientStartedART_29f");}
                   if(conn.rs.getString("clientStartedART_34m")!=null){ clientStartedART_34m= conn.rs.getString("clientStartedART_34m");}
                   if(conn.rs.getString("clientStartedART_34f")!=null){ clientStartedART_34f= conn.rs.getString("clientStartedART_34f");}
                   if(conn.rs.getString("clientStartedART_39m")!=null){ clientStartedART_39m= conn.rs.getString("clientStartedART_39m");}
                   if(conn.rs.getString("clientStartedART_39f")!=null){ clientStartedART_39f= conn.rs.getString("clientStartedART_39f");}
                   if(conn.rs.getString("clientStartedART_49m")!=null){ clientStartedART_49m= conn.rs.getString("clientStartedART_49m");}
                    if(conn.rs.getString("clientStartedART_49f")!=null){clientStartedART_49f= conn.rs.getString("clientStartedART_49f");}
                    if(conn.rs.getString("clientStartedART_50m")!=null){clientStartedART_50m= conn.rs.getString("clientStartedART_50m");}
                    if(conn.rs.getString("clientStartedART_50f")!=null){clientStartedART_50f= conn.rs.getString("clientStartedART_50f");}
                    if(conn.rs.getString("clientStartedART_STm")!=null){clientStartedART_STm= conn.rs.getString("clientStartedART_STm");}
                    if(conn.rs.getString("clientStartedART_STf")!=null){clientStartedART_STf= conn.rs.getString("clientStartedART_STf");}
                    if(conn.rs.getString("clientStartedART_GT")!=null){clientStartedART_GT= conn.rs.getString("clientStartedART_GT");}


                    
                    if(conn.rs.getString("contactsListed_1m")!=null){contactsListed_1m= conn.rs.getString("contactsListed_1m");}
                    if(conn.rs.getString("contactsListed_1f")!=null){contactsListed_1f= conn.rs.getString("contactsListed_1f");}
                   if(conn.rs.getString("contactsListed_4m")!=null){ contactsListed_4m= conn.rs.getString("contactsListed_4m");}
                    if(conn.rs.getString("contactsListed_4f")!=null){contactsListed_4f= conn.rs.getString("contactsListed_4f");}
                    if(conn.rs.getString("contactsListed_9m")!=null){contactsListed_9m= conn.rs.getString("contactsListed_9m");}
                    if(conn.rs.getString("contactsListed_9f")!=null){contactsListed_9f= conn.rs.getString("contactsListed_9f");}
                    if(conn.rs.getString("contactsListed_14m")!=null){contactsListed_14m= conn.rs.getString("contactsListed_14m");}
                    if(conn.rs.getString("contactsListed_14f")!=null){contactsListed_14f= conn.rs.getString("contactsListed_14f");}
                    if(conn.rs.getString("contactsListed_19m")!=null){contactsListed_19m= conn.rs.getString("contactsListed_19m");}
                    if(conn.rs.getString("contactsListed_19f")!=null){contactsListed_19f= conn.rs.getString("contactsListed_19f");}
                    if(conn.rs.getString("contactsListed_24m")!=null){contactsListed_24m= conn.rs.getString("contactsListed_24m");}
                    if(conn.rs.getString("contactsListed_24f")!=null){contactsListed_24f= conn.rs.getString("contactsListed_24f");}
                   if(conn.rs.getString("contactsListed_29m")!=null){ contactsListed_29m= conn.rs.getString("contactsListed_29m");}
                   if(conn.rs.getString("contactsListed_29f")!=null){ contactsListed_29f= conn.rs.getString("contactsListed_29f");}
                   if(conn.rs.getString("contactsListed_34m")!=null){ contactsListed_34m= conn.rs.getString("contactsListed_34m");}
                   if(conn.rs.getString("contactsListed_34f")!=null){ contactsListed_34f= conn.rs.getString("contactsListed_34f");}
                   if(conn.rs.getString("contactsListed_39m")!=null){ contactsListed_39m= conn.rs.getString("contactsListed_39m");}
                   if(conn.rs.getString("contactsListed_39f")!=null){ contactsListed_39f= conn.rs.getString("contactsListed_39f");}
                   if(conn.rs.getString("contactsListed_49m")!=null){ contactsListed_49m= conn.rs.getString("contactsListed_49m");}
                   if(conn.rs.getString("contactsListed_49f")!=null){ contactsListed_49f= conn.rs.getString("contactsListed_49f");}
                   if(conn.rs.getString("contactsListed_50m")!=null){ contactsListed_50m= conn.rs.getString("contactsListed_50m");}
                   if(conn.rs.getString("contactsListed_50f")!=null){ contactsListed_50f= conn.rs.getString("contactsListed_50f");}
                   if(conn.rs.getString("contactsListed_STm")!=null){ contactsListed_STm= conn.rs.getString("contactsListed_STm");}
                   if(conn.rs.getString("contactsListed_STf")!=null){ contactsListed_STf= conn.rs.getString("contactsListed_STf");}
                   if(conn.rs.getString("contactsListed_GT")!=null){ contactsListed_GT= conn.rs.getString("contactsListed_GT");}


                    
                    if(conn.rs.getString("newlyTested_1m")!=null){newlyTested_1m= conn.rs.getString("newlyTested_1m");}
                    if(conn.rs.getString("newlyTested_1f")!=null){newlyTested_1f= conn.rs.getString("newlyTested_1f");}
                    if(conn.rs.getString("newlyTested_4m")!=null){newlyTested_4m= conn.rs.getString("newlyTested_4m");}
                   if(conn.rs.getString("newlyTested_4f")!=null){ newlyTested_4f= conn.rs.getString("newlyTested_4f");}
                   if(conn.rs.getString("newlyTested_9m")!=null){ newlyTested_9m= conn.rs.getString("newlyTested_9m");}
                   if(conn.rs.getString("newlyTested_9f")!=null){ newlyTested_9f= conn.rs.getString("newlyTested_9f");}
                   if(conn.rs.getString("newlyTested_14m")!=null){ newlyTested_14m= conn.rs.getString("newlyTested_14m");}
                   if(conn.rs.getString("newlyTested_14f")!=null){ newlyTested_14f= conn.rs.getString("newlyTested_14f");}
                   if(conn.rs.getString("newlyTested_19m")!=null){ newlyTested_19m= conn.rs.getString("newlyTested_19m");}
                   if(conn.rs.getString("newlyTested_19f")!=null){ newlyTested_19f= conn.rs.getString("newlyTested_19f");}
                    if(conn.rs.getString("newlyTested_24m")!=null){newlyTested_24m= conn.rs.getString("newlyTested_24m");}
                    if(conn.rs.getString("newlyTested_24f")!=null){newlyTested_24f= conn.rs.getString("newlyTested_24f");}
                   if(conn.rs.getString("newlyTested_29m")!=null){ newlyTested_29m= conn.rs.getString("newlyTested_29m");}
                    if(conn.rs.getString("newlyTested_29f")!=null){newlyTested_29f= conn.rs.getString("newlyTested_29f");}
                    if(conn.rs.getString("newlyTested_34m")!=null){newlyTested_34m= conn.rs.getString("newlyTested_34m");}
                    if(conn.rs.getString("newlyTested_34f")!=null){newlyTested_34f= conn.rs.getString("newlyTested_34f");}
                    if(conn.rs.getString("newlyTested_39m")!=null){newlyTested_39m= conn.rs.getString("newlyTested_39m");}
                   if(conn.rs.getString("newlyTested_39f")!=null){ newlyTested_39f= conn.rs.getString("newlyTested_39f");}
                    if(conn.rs.getString("newlyTested_49m")!=null){newlyTested_49m= conn.rs.getString("newlyTested_49m");}
                    if(conn.rs.getString("newlyTested_49f")!=null){newlyTested_49f= conn.rs.getString("newlyTested_49f");}
                   if(conn.rs.getString("newlyTested_50m")!=null){ newlyTested_50m= conn.rs.getString("newlyTested_50m");}
                   if(conn.rs.getString("newlyTested_50f")!=null){ newlyTested_50f= conn.rs.getString("newlyTested_50f");}
                   if(conn.rs.getString("newlyTested_STm")!=null){ newlyTested_STm= conn.rs.getString("newlyTested_STm");}
                    if(conn.rs.getString("newlyTested_STf")!=null){newlyTested_STf= conn.rs.getString("newlyTested_STf");}
                    if(conn.rs.getString("newlyTested_GT")!=null){newlyTested_GT= conn.rs.getString("newlyTested_GT");}


                    
                    if(conn.rs.getString("identifiedPOS_1m")!=null){identifiedPOS_1m= conn.rs.getString("identifiedPOS_1m");}
                    if(conn.rs.getString("identifiedPOS_1f")!=null){identifiedPOS_1f= conn.rs.getString("identifiedPOS_1f");}
                    if(conn.rs.getString("identifiedPOS_4m")!=null){identifiedPOS_4m= conn.rs.getString("identifiedPOS_4m");}
                    if(conn.rs.getString("identifiedPOS_4f")!=null){identifiedPOS_4f= conn.rs.getString("identifiedPOS_4f");}
                    if(conn.rs.getString("identifiedPOS_9m")!=null){identifiedPOS_9m= conn.rs.getString("identifiedPOS_9m");}
                    if(conn.rs.getString("identifiedPOS_9f")!=null){identifiedPOS_9f= conn.rs.getString("identifiedPOS_9f");}
                    if(conn.rs.getString("identifiedPOS_14m")!=null){identifiedPOS_14m= conn.rs.getString("identifiedPOS_14m");}
                   if(conn.rs.getString("identifiedPOS_14f")!=null){ identifiedPOS_14f= conn.rs.getString("identifiedPOS_14f");}
                   if(conn.rs.getString("identifiedPOS_19m")!=null){ identifiedPOS_19m= conn.rs.getString("identifiedPOS_19m");}
                   if(conn.rs.getString("identifiedPOS_19f")!=null){ identifiedPOS_19f= conn.rs.getString("identifiedPOS_19f");}
                   if(conn.rs.getString("identifiedPOS_24m")!=null){ identifiedPOS_24m= conn.rs.getString("identifiedPOS_24m");}
                   if(conn.rs.getString("identifiedPOS_24f")!=null){ identifiedPOS_24f= conn.rs.getString("identifiedPOS_24f");}
                   if(conn.rs.getString("identifiedPOS_29m")!=null){ identifiedPOS_29m= conn.rs.getString("identifiedPOS_29m");}
                  if(conn.rs.getString("identifiedPOS_29f")!=null){  identifiedPOS_29f= conn.rs.getString("identifiedPOS_29f");}
                   if(conn.rs.getString("identifiedPOS_34m")!=null){ identifiedPOS_34m= conn.rs.getString("identifiedPOS_34m");}
                   if(conn.rs.getString("identifiedPOS_34f")!=null){ identifiedPOS_34f= conn.rs.getString("identifiedPOS_34f");}
                   if(conn.rs.getString("identifiedPOS_39m")!=null){ identifiedPOS_39m= conn.rs.getString("identifiedPOS_39m");}
                  if(conn.rs.getString("identifiedPOS_39f")!=null){  identifiedPOS_39f= conn.rs.getString("identifiedPOS_39f");}
                   if(conn.rs.getString("identifiedPOS_49m")!=null){ identifiedPOS_49m= conn.rs.getString("identifiedPOS_49m");}
                   if(conn.rs.getString("identifiedPOS_49f")!=null){ identifiedPOS_49f= conn.rs.getString("identifiedPOS_49f");}
                   if(conn.rs.getString("identifiedPOS_50m")!=null){ identifiedPOS_50m= conn.rs.getString("identifiedPOS_50m");}
                   if(conn.rs.getString("identifiedPOS_50f")!=null){ identifiedPOS_50f= conn.rs.getString("identifiedPOS_50f");}
                   if(conn.rs.getString("identifiedPOS_STm")!=null){ identifiedPOS_STm= conn.rs.getString("identifiedPOS_STm");}
                   if(conn.rs.getString("identifiedPOS_STf")!=null){ identifiedPOS_STf= conn.rs.getString("identifiedPOS_STf");}
                   if(conn.rs.getString("identifiedPOS_GT")!=null){ identifiedPOS_GT= conn.rs.getString("identifiedPOS_GT");}

                   if(conn.rs.getString("contactStartedART_1m")!=null){ contactStartedART_1m= conn.rs.getString("contactStartedART_1m");}
                    if(conn.rs.getString("contactStartedART_1f")!=null){contactStartedART_1f= conn.rs.getString("contactStartedART_1f");}
                   if(conn.rs.getString("contactStartedART_4m")!=null){ contactStartedART_4m= conn.rs.getString("contactStartedART_4m");}
                   if(conn.rs.getString("contactStartedART_4f")!=null){ contactStartedART_4f= conn.rs.getString("contactStartedART_4f");}
                   if(conn.rs.getString("contactStartedART_9m")!=null){ contactStartedART_9m= conn.rs.getString("contactStartedART_9m");}
                   if(conn.rs.getString("contactStartedART_9f")!=null){ contactStartedART_9f= conn.rs.getString("contactStartedART_9f");}
                   if(conn.rs.getString("contactStartedART_14m")!=null){ contactStartedART_14m= conn.rs.getString("contactStartedART_14m");}
                   if(conn.rs.getString("contactStartedART_14f")!=null){ contactStartedART_14f= conn.rs.getString("contactStartedART_14f");}
                   if(conn.rs.getString("contactStartedART_19m")!=null){ contactStartedART_19m= conn.rs.getString("contactStartedART_19m");}
                   if(conn.rs.getString("contactStartedART_19f")!=null){ contactStartedART_19f= conn.rs.getString("contactStartedART_19f");}
                   if(conn.rs.getString("contactStartedART_24m")!=null){ contactStartedART_24m= conn.rs.getString("contactStartedART_24m");}
                  if(conn.rs.getString("contactStartedART_24f")!=null){ contactStartedART_24f= conn.rs.getString("contactStartedART_24f");}
                  if(conn.rs.getString("contactStartedART_29m")!=null){  contactStartedART_29m= conn.rs.getString("contactStartedART_29m");}
                   if(conn.rs.getString("contactStartedART_29f")!=null){ contactStartedART_29f= conn.rs.getString("contactStartedART_29f");}
                   if(conn.rs.getString("contactStartedART_34m")!=null){ contactStartedART_34m= conn.rs.getString("contactStartedART_34m");}
                   if(conn.rs.getString("contactStartedART_34f")!=null){ contactStartedART_34f= conn.rs.getString("contactStartedART_34f");}
                    if(conn.rs.getString("contactStartedART_39m")!=null){contactStartedART_39m= conn.rs.getString("contactStartedART_39m");}
                    if(conn.rs.getString("contactStartedART_39f")!=null){contactStartedART_39f= conn.rs.getString("contactStartedART_39f");}
                    if(conn.rs.getString("contactStartedART_49m")!=null){contactStartedART_49m= conn.rs.getString("contactStartedART_49m");}
                    if(conn.rs.getString("contactStartedART_49f")!=null){contactStartedART_49f= conn.rs.getString("contactStartedART_49f");}
                    if(conn.rs.getString("contactStartedART_50m")!=null){contactStartedART_50m= conn.rs.getString("contactStartedART_50m");}
                    if(conn.rs.getString("contactStartedART_50f")!=null){contactStartedART_50f= conn.rs.getString("contactStartedART_50f");}
                    if(conn.rs.getString("contactStartedART_STm")!=null){contactStartedART_STm= conn.rs.getString("contactStartedART_STm");}
                    if(conn.rs.getString("contactStartedART_STf")!=null){contactStartedART_STf= conn.rs.getString("contactStartedART_STf");}
                    if(conn.rs.getString("contactStartedART_GT")!=null){contactStartedART_GT= conn.rs.getString("contactStartedART_GT");}


                    
                    user_id= conn.rs.getString("user_id");
                    isValidated= conn.rs.getString("isValidated");
                    isLocked= conn.rs.getString("isLocked");
                    updatedBy= conn.rs.getString("updatedBy");
                    updatedOn= conn.rs.getString("updatedOn");
                    yearmonth= conn.rs.getString("yearmonth");
                    timestamp= conn.rs.getString("timestamp");
       
                
                  if(isLocked.equals("1")){lock="disabled";}
        //get the name of the person who entered the form 
        enterdby="";
        String enterer="select * from user where userid='"+conn.rs.getString("user_id") +"'";
        
        conn.rs1=conn.st1.executeQuery(enterer);
        //add details of person who entered
        if(conn.rs1.next()){
        enterdby="<font color='green'>Data 1st entered by:   <b> "+conn.rs1.getString("fname")+" "+conn.rs1.getString("mname")+" "+conn.rs1.getString("lname")+"</b>  on  <b>"+conn.rs.getString("timestamp") +"</b></font>";
        }
        
		
		//now check if form was updated and if its one month after data entry
        
        if(conn.rs.getString("updatedOn")!=null){
        //get difference in months between entered date and updated date
        String compdate="SELECT TIMESTAMPDIFF(MONTH,'"+conn.rs.getString("timestamp") +"','"+conn.rs.getString("updatedOn") +"')";
        conn.rs2=conn.st2.executeQuery(compdate);
        if (conn.rs2.next()){
            //now get the details of the person who updated the form
        //if the difference is greater than or equal to one, 
        
           enterdby=""; 
            if(conn.rs2.getInt(1)>=1){
        String updater="select * from user where userid='"+conn.rs.getString("updatedBy") +"'";
        
        conn.rs1=conn.st1.executeQuery(updater);
        //add details of person who entered
        if(conn.rs1.next()){
            enterdby += "<span style='margin-left:30%;'><font color='red'>   Updated  by:   <b> " + conn.rs1.getString("fname") + " " + conn.rs1.getString("mname") + " " + conn.rs1.getString("lname") + "</b>  on  <b>" + conn.rs.getString("updatedOn") + "</b></font></span>";
         }
        } //end of if month >=1 
        }//end of date comparison if 
        
        }//end of if updated !=null
                     
            }
              System.out.println("Validity checker : "+isValidated);
      if(isValidated.equals("0")){
     validity="<font color='red'><b>Form Not Validated.<img width='20px' height='20px' src='images/notValidated.jpg' style='margin-left:5px;'></b></font>";
    }
        else if(isValidated.equals("1")){
       validity="<font color='green'><b>Form Validated.<img width='20px' height='20px' src='images/validated.jpg' style='margin-left:5px;'></b></font>";
    }
      else{
        validity="<font color=\"blue\"><b>New entry form.</b></font>"  ;          
              }

    session.setAttribute("isValidated", validity);
    
    // code to output the table
    
    
     Header = "<table border=\"1px;\">"
        + "<tr border=\"1px;\">"
        + "<td rowspan=\"2\" class=\"title\">SNO</td>"
        + "<td  rowspan=\"2\" class=\"title\">Indicator</td>"
        + "<td  rowspan=\"2\" class=\"title\">Data source column from index client testing register</td>"
        
        + "<td colspan=\"2\"><1</td>"
        + "<td colspan=\"2\">1-4</td>"
        + "<td colspan=\"2\">5-9</td>"
        + "<td colspan=\"2\">10-14</td>"
        + "<td colspan=\"2\">15-19</td>"
        + "<td colspan=\"2\">20-24</td>"
        + "<td colspan=\"2\">25-29</td>"
        + "<td colspan=\"2\">30-34</td>"
        + "<td colspan=\"2\">35-39</td>"
        + "<td colspan=\"2\">40-49</td>"
        + "<td colspan=\"2\">50+</td>"
        + "<td colspan=\"2\">Sub Total</td>"
        + "<td rowspan=\"2\">Grand Total</td>"
        + "</tr>"
            
        + "<tr>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "<td>M</td>"
        + "<td>F</td>"
        + "</tr>";
    
    
    clientsListed = ""
        + "<tr border=\"1px;\">"
        + "<td class=\"title\">A</td>"
        + "<td class=\"title indicator\">Total number of index clients listed</td>"
        + "<td>d</td>"
            
        + "<td><input type=\"text\" name=\"clientsListed_1m\" id=\"clientsListed_1m\" value=\""+clientsListed_1m+"\"  onblur=\"autosave('clientsListed_1m');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_1f\" id=\"clientsListed_1f\" value=\""+clientsListed_1f+"\"  onblur=\"autosave('clientsListed_1f');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_4m\" id=\"clientsListed_4m\" value=\""+clientsListed_4m+"\"  onblur=\"autosave('clientsListed_4m');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_4f\" id=\"clientsListed_4f\" value=\""+clientsListed_4f+"\"  onblur=\"autosave('clientsListed_4f');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_9m\" id=\"clientsListed_9m\" value=\""+clientsListed_9m+"\"  onblur=\"autosave('clientsListed_9m');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_9f\" id=\"clientsListed_9f\" value=\""+clientsListed_9f+"\"  onblur=\"autosave('clientsListed_9f');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_14m\" id=\"clientsListed_14m\" value=\""+clientsListed_14m+"\" onblur=\"autosave('clientsListed_14m');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_14f\" id=\"clientsListed_14f\" value=\""+clientsListed_14f+"\" onblur=\"autosave('clientsListed_14f');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_19m\" id=\"clientsListed_19m\" value=\""+clientsListed_19m+"\"  onblur=\"autosave('clientsListed_19m');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_19f\" id=\"clientsListed_19f\" value=\""+clientsListed_19f+"\"  onblur=\"autosave('clientsListed_19f');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_24m\" id=\"clientsListed_24m\" value=\""+clientsListed_24m+"\"  onblur=\"autosave('clientsListed_24m');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_24f\" id=\"clientsListed_24f\" value=\""+clientsListed_24f+"\"  onblur=\"autosave('clientsListed_24f');\"  oninput=\"index_clients_listed();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_29m\" id=\"clientsListed_29m\" value=\""+clientsListed_29m+"\"  onblur=\"autosave('clientsListed_29m');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_29f\" id=\"clientsListed_29f\" value=\""+clientsListed_29f+"\"  onblur=\"autosave('clientsListed_29f');\"  oninput=\"index_clients_listed();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_34m\" id=\"clientsListed_34m\" value=\""+clientsListed_34m+"\"  onblur=\"autosave('clientsListed_34m');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_34f\" id=\"clientsListed_34f\" value=\""+clientsListed_34f+"\"  onblur=\"autosave('clientsListed_34f');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_39m\" id=\"clientsListed_39m\" value=\""+clientsListed_39m+"\"  onblur=\"autosave('clientsListed_39m');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_39f\" id=\"clientsListed_39f\" value=\""+clientsListed_39f+"\"  onblur=\"autosave('clientsListed_39f');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_49m\" id=\"clientsListed_49m\" value=\""+clientsListed_49m+"\"  onblur=\"autosave('clientsListed_49m');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_49f\" id=\"clientsListed_49f\" value=\""+clientsListed_49f+"\"  onblur=\"autosave('clientsListed_49f');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_50m\" id=\"clientsListed_50m\" value=\""+clientsListed_50m+"\"  onblur=\"autosave('clientsListed_50m');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_50f\" id=\"clientsListed_50f\" value=\""+clientsListed_50f+"\"  onblur=\"autosave('clientsListed_50f');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_STm\" id=\"clientsListed_STm\" value=\""+clientsListed_STm+"\" tabindex=\"-1\"  onblur=\"autosave('clientsListed_STm');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_STf\" id=\"clientsListed_STf\" value=\""+clientsListed_STf+"\" tabindex=\"-1\"  onblur=\"autosave('clientsListed_STf');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"clientsListed_GT\" id=\"clientsListed_GT\" value=\""+clientsListed_GT+"\" tabindex=\"-1\"  onblur=\"autosave('clientsListed_GT');\"  oninput=\"index_clients_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>";
            
    clientStartedART = "" 
        + "<tr border=\"1px;\">"
        + "<td class=\"title\">B</td>"
        + "<td class=\"title indicator\">Total number of index clients started on ART</td>"
        + "<td>b</td>"
         
        + "<td><input type=\"text\" name=\"clientStartedART_1m\" id=\"clientStartedART_1m\" value=\""+clientStartedART_1m+"\"  onblur=\"autosave('clientStartedART_1m');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_1f\" id=\"clientStartedART_1f\" value=\""+clientStartedART_1f+"\"  onblur=\"autosave('clientStartedART_1f');\"  oninput=\"index_clients_startedART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_4m\" id=\"clientStartedART_4m\" value=\""+clientStartedART_4m+"\"  onblur=\"autosave('clientStartedART_4m');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_4f\" id=\"clientStartedART_4f\" value=\""+clientStartedART_4f+"\"  onblur=\"autosave('clientStartedART_4f');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_9m\" id=\"clientStartedART_9m\" value=\""+clientStartedART_9m+"\"  onblur=\"autosave('clientStartedART_9m');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_9f\" id=\"clientStartedART_9f\" value=\""+clientStartedART_9f+"\"  onblur=\"autosave('clientStartedART_9f');\"  oninput=\"index_clients_startedART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_14m\" id=\"clientStartedART_14m\" value=\""+clientStartedART_14m+"\" onblur=\"autosave('clientStartedART_14m');\"  oninput=\"index_clients_startedART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_14f\" id=\"clientStartedART_14f\" value=\""+clientStartedART_14f+"\" onblur=\"autosave('clientStartedART_14f');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_19m\" id=\"clientStartedART_19m\" value=\""+clientStartedART_19m+"\"  onblur=\"autosave('clientStartedART_19m');\"  oninput=\"index_clients_startedART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_19f\" id=\"clientStartedART_19f\" value=\""+clientStartedART_19f+"\"  onblur=\"autosave('clientStartedART_19f');\"  oninput=\"index_clients_startedART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_24m\" id=\"clientStartedART_24m\" value=\""+clientStartedART_24m+"\"  onblur=\"autosave('clientStartedART_24m');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_24f\" id=\"clientStartedART_24f\" value=\""+clientStartedART_24f+"\"  onblur=\"autosave('clientStartedART_24f');\"  oninput=\"index_clients_startedART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_29m\" id=\"clientStartedART_29m\" value=\""+clientStartedART_29m+"\"  onblur=\"autosave('clientStartedART_29m');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_29f\" id=\"clientStartedART_29f\" value=\""+clientStartedART_29f+"\"  onblur=\"autosave('clientStartedART_29f');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_34m\" id=\"clientStartedART_34m\" value=\""+clientStartedART_34m+"\"  onblur=\"autosave('clientStartedART_34m');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_34f\" id=\"clientStartedART_34f\" value=\""+clientStartedART_34f+"\"  onblur=\"autosave('clientStartedART_34f');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_39m\" id=\"clientStartedART_39m\" value=\""+clientStartedART_39m+"\"  onblur=\"autosave('clientStartedART_39m');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_39f\" id=\"clientStartedART_39f\" value=\""+clientStartedART_39f+"\"  onblur=\"autosave('clientStartedART_39f');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_49m\" id=\"clientStartedART_49m\" value=\""+clientStartedART_49m+"\"  onblur=\"autosave('clientStartedART_49m');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_49f\" id=\"clientStartedART_49f\" value=\""+clientStartedART_49f+"\"  onblur=\"autosave('clientStartedART_49f');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_50m\" id=\"clientStartedART_50m\" value=\""+clientStartedART_50m+"\"  onblur=\"autosave('clientStartedART_50m');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_50f\" id=\"clientStartedART_50f\" value=\""+clientStartedART_50f+"\"  onblur=\"autosave('clientStartedART_50f');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_STm\" id=\"clientStartedART_STm\" value=\""+clientStartedART_STm+"\" tabindex=\"-1\"  onblur=\"autosave('clientStartedART_STm');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_STf\" id=\"clientStartedART_STf\" value=\""+clientStartedART_STf+"\" tabindex=\"-1\"  onblur=\"autosave('clientStartedART_STf');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"clientStartedART_GT\" id=\"clientStartedART_GT\" value=\""+clientStartedART_GT+"\" tabindex=\"-1\"  onblur=\"autosave('clientStartedART_GT');\"  oninput=\"index_clients_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + "";  
    
    
    
    
    contactsListed = ""
        + "<tr border=\"1px;\">"
        + "<td class=\"title\">C</td>"
        + "<td class=\"title indicator\">Total number of contacts listed</td>"
        + "<td>J</td>"
         
        + "<td><input type=\"text\" name=\"contactsListed_1m\" id=\"contactsListed_1m\" value=\""+contactsListed_1m+"\"  onblur=\"autosave('contactsListed_1m');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_1f\" id=\"contactsListed_1f\" value=\""+contactsListed_1f+"\"  onblur=\"autosave('contactsListed_1f');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_4m\" id=\"contactsListed_4m\" value=\""+contactsListed_4m+"\"  onblur=\"autosave('contactsListed_4m');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_4f\" id=\"contactsListed_4f\" value=\""+contactsListed_4f+"\"  onblur=\"autosave('contactsListed_4f');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_9m\" id=\"contactsListed_9m\" value=\""+contactsListed_9m+"\"  onblur=\"autosave('contactsListed_9m');\"  oninput=\"contacts_listed();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_9f\" id=\"contactsListed_9f\" value=\""+contactsListed_9f+"\"  onblur=\"autosave('contactsListed_9f');\"  oninput=\"contacts_listed();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_14m\" id=\"contactsListed_14m\" value=\""+contactsListed_14m+"\" onblur=\"autosave('contactsListed_14m');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_14f\" id=\"contactsListed_14f\" value=\""+contactsListed_14f+"\" onblur=\"autosave('contactsListed_14f');\"  oninput=\"contacts_listed();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_19m\" id=\"contactsListed_19m\" value=\""+contactsListed_19m+"\"  onblur=\"autosave('contactsListed_19m');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_19f\" id=\"contactsListed_19f\" value=\""+contactsListed_19f+"\"  onblur=\"autosave('contactsListed_19f');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_24m\" id=\"contactsListed_24m\" value=\""+contactsListed_24m+"\"  onblur=\"autosave('contactsListed_24m');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_24f\" id=\"contactsListed_24f\" value=\""+contactsListed_24f+"\"  onblur=\"autosave('contactsListed_24f');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_29m\" id=\"contactsListed_29m\" value=\""+contactsListed_29m+"\"  onblur=\"autosave('contactsListed_29m');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_29f\" id=\"contactsListed_29f\" value=\""+contactsListed_29f+"\"  onblur=\"autosave('contactsListed_29f');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_34m\" id=\"contactsListed_34m\" value=\""+contactsListed_34m+"\"  onblur=\"autosave('contactsListed_34m');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_34f\" id=\"contactsListed_34f\" value=\""+contactsListed_34f+"\"  onblur=\"autosave('contactsListed_34f');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_39m\" id=\"contactsListed_39m\" value=\""+contactsListed_39m+"\"  onblur=\"autosave('contactsListed_39m');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_39f\" id=\"contactsListed_39f\" value=\""+contactsListed_39f+"\"  onblur=\"autosave('contactsListed_39f');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_49m\" id=\"contactsListed_49m\" value=\""+contactsListed_49m+"\"  onblur=\"autosave('contactsListed_49m');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_49f\" id=\"contactsListed_49f\" value=\""+contactsListed_49f+"\"  onblur=\"autosave('contactsListed_49f');\"  oninput=\"contacts_listed();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_50m\" id=\"contactsListed_50m\" value=\""+contactsListed_50m+"\"  onblur=\"autosave('contactsListed_50m');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_50f\" id=\"contactsListed_50f\" value=\""+contactsListed_50f+"\"  onblur=\"autosave('contactsListed_50f');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_STm\" id=\"contactsListed_STm\" value=\""+contactsListed_STm+"\" tabindex=\"-1\"  onblur=\"autosave('contactsListed_STm');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_STf\" id=\"contactsListed_STf\" value=\""+contactsListed_STf+"\" tabindex=\"-1\"  onblur=\"autosave('contactsListed_STf');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"contactsListed_GT\" id=\"contactsListed_GT\" value=\""+contactsListed_GT+"\" tabindex=\"-1\"  onblur=\"autosave('contactsListed_GT');\"  oninput=\"contacts_listed(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>";
            
    newlyTested = ""
         + "<tr border=\"1px;\">"
        + "<td class=\"title\">D</td>"
        + "<td class=\"title indicator\">Total number of contacts newly tested for HIV</td>"
        + "<td>R</td>"
         
        + "<td><input type=\"text\" name=\"newlyTested_1m\" id=\"newlyTested_1m\" value=\""+newlyTested_1m+"\"  onblur=\"autosave('newlyTested_1m');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_1f\" id=\"newlyTested_1f\" value=\""+newlyTested_1f+"\"  onblur=\"autosave('newlyTested_1f');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_4m\" id=\"newlyTested_4m\" value=\""+newlyTested_4m+"\"  onblur=\"autosave('newlyTested_4m');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_4f\" id=\"newlyTested_4f\" value=\""+newlyTested_4f+"\"  onblur=\"autosave('newlyTested_4f');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_9m\" id=\"newlyTested_9m\" value=\""+newlyTested_9m+"\"  onblur=\"autosave('newlyTested_9m');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_9f\" id=\"newlyTested_9f\" value=\""+newlyTested_9f+"\"  onblur=\"autosave('newlyTested_9f');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_14m\" id=\"newlyTested_14m\" value=\""+newlyTested_14m+"\" onblur=\"autosave('newlyTested_14m');\"  oninput=\"contacts_newly_tested();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_14f\" id=\"newlyTested_14f\" value=\""+newlyTested_14f+"\" onblur=\"autosave('newlyTested_14f');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_19m\" id=\"newlyTested_19m\" value=\""+newlyTested_19m+"\"  onblur=\"autosave('newlyTested_19m');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_19f\" id=\"newlyTested_19f\" value=\""+newlyTested_19f+"\"  onblur=\"autosave('newlyTested_19f');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_24m\" id=\"newlyTested_24m\" value=\""+newlyTested_24m+"\"  onblur=\"autosave('newlyTested_24m');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_24f\" id=\"newlyTested_24f\" value=\""+newlyTested_24f+"\"  onblur=\"autosave('newlyTested_24f');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_29m\" id=\"newlyTested_29m\" value=\""+newlyTested_29m+"\"  onblur=\"autosave('newlyTested_29m');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_29f\" id=\"newlyTested_29f\" value=\""+newlyTested_29f+"\"  onblur=\"autosave('newlyTested_29f');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_34m\" id=\"newlyTested_34m\" value=\""+newlyTested_34m+"\"  onblur=\"autosave('newlyTested_34m');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_34f\" id=\"newlyTested_34f\" value=\""+newlyTested_34f+"\"  onblur=\"autosave('newlyTested_34f');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_39m\" id=\"newlyTested_39m\" value=\""+newlyTested_39m+"\"  onblur=\"autosave('newlyTested_39m');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_39f\" id=\"newlyTested_39f\" value=\""+newlyTested_39f+"\"  onblur=\"autosave('newlyTested_39f');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_49m\" id=\"newlyTested_49m\" value=\""+newlyTested_49m+"\"  onblur=\"autosave('newlyTested_49m');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_49f\" id=\"newlyTested_49f\" value=\""+newlyTested_49f+"\"  onblur=\"autosave('newlyTested_49f');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_50m\" id=\"newlyTested_50m\" value=\""+newlyTested_50m+"\"  onblur=\"autosave('newlyTested_50m');\"  oninput=\"contacts_newly_tested();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_50f\" id=\"newlyTested_50f\" value=\""+newlyTested_50f+"\"  onblur=\"autosave('newlyTested_50f');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_STm\" id=\"newlyTested_STm\" value=\""+newlyTested_STm+"\" tabindex=\"-1\"  onblur=\"autosave('newlyTested_STm');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_STf\" id=\"newlyTested_STf\" value=\""+newlyTested_STf+"\" tabindex=\"-1\"  onblur=\"autosave('newlyTested_STf');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"newlyTested_GT\" id=\"newlyTested_GT\" value=\""+newlyTested_GT+"\" tabindex=\"-1\"  onblur=\"autosave('newlyTested_GT');\"  oninput=\"contacts_newly_tested(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + "";
    
    identifiedPOS = ""
         + "<tr border=\"1px;\">"
        + "<td class=\"title\">E</td>"
        + "<td class=\"title indicator\">Total number of contacts identified HIV positive</td>"
        + "<td>T</td>"
         
        + "<td><input type=\"text\" name=\"identifiedPOS_1m\" id=\"identifiedPOS_1m\" value=\""+identifiedPOS_1m+"\"  onblur=\"autosave('identifiedPOS_1m');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_1f\" id=\"identifiedPOS_1f\" value=\""+identifiedPOS_1f+"\"  onblur=\"autosave('identifiedPOS_1f');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_4m\" id=\"identifiedPOS_4m\" value=\""+identifiedPOS_4m+"\"  onblur=\"autosave('identifiedPOS_4m');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_4f\" id=\"identifiedPOS_4f\" value=\""+identifiedPOS_4f+"\"  onblur=\"autosave('identifiedPOS_4f');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_9m\" id=\"identifiedPOS_9m\" value=\""+identifiedPOS_9m+"\"  onblur=\"autosave('identifiedPOS_9m');\"  oninput=\"contacts_HIVPos();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_9f\" id=\"identifiedPOS_9f\" value=\""+identifiedPOS_9f+"\"  onblur=\"autosave('identifiedPOS_9f');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_14m\" id=\"identifiedPOS_14m\" value=\""+identifiedPOS_14m+"\" onblur=\"autosave('identifiedPOS_14m');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_14f\" id=\"identifiedPOS_14f\" value=\""+identifiedPOS_14f+"\" onblur=\"autosave('identifiedPOS_14f');\"  oninput=\"contacts_HIVPos();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_19m\" id=\"identifiedPOS_19m\" value=\""+identifiedPOS_19m+"\"  onblur=\"autosave('identifiedPOS_19m');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_19f\" id=\"identifiedPOS_19f\" value=\""+identifiedPOS_19f+"\"  onblur=\"autosave('identifiedPOS_19f');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_24m\" id=\"identifiedPOS_24m\" value=\""+identifiedPOS_24m+"\"  onblur=\"autosave('identifiedPOS_24m');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_24f\" id=\"identifiedPOS_24f\" value=\""+identifiedPOS_24f+"\"  onblur=\"autosave('identifiedPOS_24f');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_29m\" id=\"identifiedPOS_29m\" value=\""+identifiedPOS_29m+"\"  onblur=\"autosave('identifiedPOS_29m');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_29f\" id=\"identifiedPOS_29f\" value=\""+identifiedPOS_29f+"\"  onblur=\"autosave('identifiedPOS_29f');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_34m\" id=\"identifiedPOS_34m\" value=\""+identifiedPOS_34m+"\"  onblur=\"autosave('identifiedPOS_34m');\"  oninput=\"contacts_HIVPos();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_34f\" id=\"identifiedPOS_34f\" value=\""+identifiedPOS_34f+"\"  onblur=\"autosave('identifiedPOS_34f');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_39m\" id=\"identifiedPOS_39m\" value=\""+identifiedPOS_39m+"\"  onblur=\"autosave('identifiedPOS_39m');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_39f\" id=\"identifiedPOS_39f\" value=\""+identifiedPOS_39f+"\"  onblur=\"autosave('identifiedPOS_39f');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_49m\" id=\"identifiedPOS_49m\" value=\""+identifiedPOS_49m+"\"  onblur=\"autosave('identifiedPOS_49m');\"  oninput=\"contacts_HIVPos();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_49f\" id=\"identifiedPOS_49f\" value=\""+identifiedPOS_49f+"\"  onblur=\"autosave('identifiedPOS_49f');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_50m\" id=\"identifiedPOS_50m\" value=\""+identifiedPOS_50m+"\"  onblur=\"autosave('identifiedPOS_50m');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_50f\" id=\"identifiedPOS_50f\" value=\""+identifiedPOS_50f+"\"  onblur=\"autosave('identifiedPOS_50f');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_STm\" id=\"identifiedPOS_STm\" value=\""+identifiedPOS_STm+"\" tabindex=\"-1\"  onblur=\"autosave('identifiedPOS_STm');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_STf\" id=\"identifiedPOS_STf\" value=\""+identifiedPOS_STf+"\" tabindex=\"-1\"  onblur=\"autosave('identifiedPOS_STf');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"identifiedPOS_GT\" id=\"identifiedPOS_GT\" value=\""+identifiedPOS_GT+"\" tabindex=\"-1\"  onblur=\"autosave('identifiedPOS_GT');\"  oninput=\"contacts_HIVPos(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>";
            
        contactStartedART = ""
        + "<tr border=\"1px;\">"
        + "<td class=\"title\">F</td>"
        + "<td class=\"title indicator\">Total number of contacts started on ART</td>"
        + "<td>U</td>"
         
        + "<td><input type=\"text\" name=\"contactStartedART_1m\" id=\"contactStartedART_1m\" value=\""+contactStartedART_1m+"\"  onblur=\"autosave('contactStartedART_1m');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_1f\" id=\"contactStartedART_1f\" value=\""+contactStartedART_1f+"\"  onblur=\"autosave('contactStartedART_1f');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_4m\" id=\"contactStartedART_4m\" value=\""+contactStartedART_4m+"\"  onblur=\"autosave('contactStartedART_4m');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_4f\" id=\"contactStartedART_4f\" value=\""+contactStartedART_4f+"\"  onblur=\"autosave('contactStartedART_4f');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_9m\" id=\"contactStartedART_9m\" value=\""+contactStartedART_9m+"\"  onblur=\"autosave('contactStartedART_9m');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_9f\" id=\"contactStartedART_9f\" value=\""+contactStartedART_9f+"\"  onblur=\"autosave('contactStartedART_9f');\"  oninput=\"contacts_startedART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_14m\" id=\"contactStartedART_14m\" value=\""+contactStartedART_14m+"\" onblur=\"autosave('contactStartedART_14m');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_14f\" id=\"contactStartedART_14f\" value=\""+contactStartedART_14f+"\" onblur=\"autosave('contactStartedART_14f');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_19m\" id=\"contactStartedART_19m\" value=\""+contactStartedART_19m+"\"  onblur=\"autosave('contactStartedART_19m');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_19f\" id=\"contactStartedART_19f\" value=\""+contactStartedART_19f+"\"  onblur=\"autosave('contactStartedART_19f');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_24m\" id=\"contactStartedART_24m\" value=\""+contactStartedART_24m+"\"  onblur=\"autosave('contactStartedART_24m');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_24f\" id=\"contactStartedART_24f\" value=\""+contactStartedART_24f+"\"  onblur=\"autosave('contactStartedART_24f');\"  oninput=\"contacts_startedART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_29m\" id=\"contactStartedART_29m\" value=\""+contactStartedART_29m+"\"  onblur=\"autosave('contactStartedART_29m');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_29f\" id=\"contactStartedART_29f\" value=\""+contactStartedART_29f+"\"  onblur=\"autosave('contactStartedART_29f');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_34m\" id=\"contactStartedART_34m\" value=\""+contactStartedART_34m+"\"  onblur=\"autosave('contactStartedART_34m');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_34f\" id=\"contactStartedART_34f\" value=\""+contactStartedART_34f+"\"  onblur=\"autosave('contactStartedART_34f');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_39m\" id=\"contactStartedART_39m\" value=\""+contactStartedART_39m+"\"  onblur=\"autosave('contactStartedART_39m');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_39f\" id=\"contactStartedART_39f\" value=\""+contactStartedART_39f+"\"  onblur=\"autosave('contactStartedART_39f');\"  oninput=\"contacts_startedART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_49m\" id=\"contactStartedART_49m\" value=\""+contactStartedART_49m+"\"  onblur=\"autosave('contactStartedART_49m');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_49f\" id=\"contactStartedART_49f\" value=\""+contactStartedART_49f+"\"  onblur=\"autosave('contactStartedART_49f');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_50m\" id=\"contactStartedART_50m\" value=\""+contactStartedART_50m+"\"  onblur=\"autosave('contactStartedART_50m');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_50f\" id=\"contactStartedART_50f\" value=\""+contactStartedART_50f+"\"  onblur=\"autosave('contactStartedART_50f');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_STm\" id=\"contactStartedART_STm\" value=\""+contactStartedART_STm+"\" tabindex=\"-1\"  onblur=\"autosave('contactStartedART_STm');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_STf\" id=\"contactStartedART_STf\" value=\""+contactStartedART_STf+"\" tabindex=\"-1\"  onblur=\"autosave('contactStartedART_STf');\"  oninput=\"contacts_startedART(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "<td><input type=\"text\" name=\"contactStartedART_GT\" id=\"contactStartedART_GT\" value=\""+contactStartedART_GT+"\" tabindex=\"-1\"  onblur=\"autosave('contactStartedART_GT');\"  oninput=\"contacts_startedART();\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"background-color:#DDDDDD;\"   readonly=\"true\"  ></td>"
        + "</tr>"
            + ""
            + "</table>";  
    
    

    
     //end of code  
     
     
      if (session.getAttribute("forms_holder") != null) {
     if (session.getAttribute("forms_holder").toString().contains("HTC")) {
        output=enterdby+Header+clientsListed+clientStartedART+contactsListed+newlyTested+identifiedPOS+contactStartedART;
        if(isLocked.equals("1")){
            output += "<div class='form-actions' style=\"text-align:center;\"><input type='submit' disabled class='btn red' value='Form Locked' name='validate' id='validate'/></div><span id='formstatus' style='display:none;'>" + validity + " </span><span id='rc' style='display:none;'>"+label+" </span><span id='ufs' style='display:none;'>"+unvalidatedLink+"</span>";
        }
        else{
            output += "<div class='form-actions' style=\"text-align:center;\"><input type='submit' class='btn blue' value='Save Index Testing Data' name='validate' id='validate'/></div><span id='formstatus' style='display:none;'>" + validity + " </span><span id='rc' style='display:none;'>"+label+" </span><span id='ufs' style='display:none;'>"+unvalidatedLink+"</span>";
        }
        
        } else {
                    output = "<tr ><td colspan='4'><font color=\"red\" size=\"6px;\" style=\"margin-left: 0%;\"><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support  Index testing module.</font></td></tr>";
                }
            }
        if (session.getAttribute("facilityid") != null) {
            }
        else {
                output = "<tr ><td colspan='4'><font color=\"red\" size=\"6px;\" style=\"margin-left: 0%;\"><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support  Index testing module.</font></td></tr>";

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
        Logger.getLogger(loadIndexTesting.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(loadIndexTesting.class.getName()).log(Level.SEVERE, null, ex);
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
