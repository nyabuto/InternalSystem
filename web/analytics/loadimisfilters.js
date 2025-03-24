/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




function loadmonths(){
    
    var dt=new Date();
    
    
    var curmonth=dt.getMonth()+1;
   
       
        var curyr=dt.getYear()+1900;
     
       // console.log("mwezi wa "+curmonth+"  mwaka wa "+curyr);
        
      var yr=document.getElementById("year").value;
//      alert(yr);
              $.ajax({
url:'loadLastMonth?year='+yr,
type:'post',
dataType:'html',
success:function (data){
    $("#month").html(data.replace("<option value=''>Select Month </option>",""));
    var select = document.getElementById('month');
                    select.size = select.length;
    
}
});  
     }
     
     
    function loadcounty(){
        
        
        $.ajax({
            url:'loadCounty',
            type:'post',
            dataType:'html',
            success:function (data){
                $(".county").html(data);
                 $('.county').select2();  
               
                //loadfacils();
              //  App.init();   
            }
            
            
        });
        
    }
    
   
    
       function loadsubcounty(){
           
           
           
        
        var mdt=$(".mdt").val();
        $.ajax({
            url:'loadSubcountymdt?mdt='+mdt,
            type:'post',
            dataType:'html',
            success:function (data)
            {
                $(".subcounty").html(data.replace("<option value=''>Select sub-county</option>",""));
                var select = document.getElementById('subcounty');
                    //select.size = select.length;
                 $('.subcounty').select2();  
              //  App.init();   
            }
            
            
        });
      return true;  
    }
    
    loadsubcounty();
    
    
    
     function loadmdt(){
           
           
           
        
        var county=$(".county").val();
        $.ajax({
            url:'loadMdt?county='+county,
            type:'post',
            dataType:'html',
            success:function (data)
            {
                $(".mdt").html(data.replace("<option value=''>Select Mdt</option>",""));
//                var select = document.getElementById('mdt');
                    //select.size = select.length;
                 $('#mdt').select2();  
              //  App.init();   
            }
            
            
        });
      return true;  
    }
    
     $('.mdt').select2();  
    
     
    
    
    
       function loadfacils()
{
      var subcounty=$(".subcounty").val();
      
$.ajax({
url:'loadMultipleFacilities?subcounty='+subcounty,
type:'post',
dataType:'html',
success:function (data){
       $(".facility").html(data);
         if($(".facility").val()!==''){
      updatefacilsession();
      
     
      }  
      $('.facility').select2();  
         // $("#facility").chosen();
       
       
}


}); 
}
    loadfacils();
 
    
    
       function loadfacil2( far)
{
      var subcounty=$(".subcounty").val();
      
$.ajax({
url:'loadMultipleFacilities?subcounty='+subcounty,
type:'post',
dataType:'html',
success:function (data){
       $(".facility").html(data);
         if($(".facility").val()!==''){
      updatefacilsession();
      
      
     
      }  
      $(".facility").val(far);
    $(".facility").select2(); 
         // $("#facility").chosen();
       
       
}


}); 
}
    
 
 


function getPeriod(){
       
 
       
       
              $.ajax({
                         url:'loadyearmonth',                            
                    type:'post',  
                    dataType: 'json',  
                    success: function(data) {                        
                       
        var dat=data.periods;
        
      
        var std="";
        var end="";
                        
                        for(var a=0;a<dat.length;a++)
                        {                           
                     var selected="";
                     
                     if(dat[a].monthid==='10'){selected=" selected ";}
                     
                          std+="<option "+selected+" data-startdate='"+dat[a].year+"-"+dat[a].monthid+"-01' value='"+dat[a].id+"'>"+dat[a].year+" "+dat[a].month+"</option>";   
                          end+="<option data-enddate='"+dat[a].year+"-"+dat[a].monthid+"-"+dat[a].enddate+"' value='"+dat[a].id+"'>"+dat[a].year+" "+dat[a].month+"</option>";   
                        }
                       
                   $(".startdate").html(std);
                   $(".enddate").html(end);
                   $(document).ready(function() {
                    $('.startdate').select2(); 
                    $('.enddate').select2(); 
                     updtimis();
             
                                 } ); 
                        
                        
                    }});
   
   }
   

getPeriod();




function utjSitesSummaryOverall()
{ 
//    alert("called");
    //once the edit form is clicked, the assumption is that the add section will be loaded
    //$('#home-tab').click();
    //$('#home-tab').html("Edit Projects");
//      $("#ovc_enrolled_perc_rd").html('<div class="d-flex justify-content-center"><div class="spinner-border" role="status"><span class="sr-only">Loading...</span></div></div>');
//      $("#otz_enrolled_perc_rd").html('<div class="d-flex justify-content-center"><div class="spinner-border" role="status"><span class="sr-only">Loading...</span></div></div>');
//   
//    console.log("_"+fc+"vs"+dt);

    var fc="";
    var cnt="";
    var sct="";
    var rgn="";
    var sd="";
    var ed="";
    
    var full_sd="";
    var full_ed="";
    
    if($(".facility").val()!==null){fc=$(".facility").val();}
    if($(".county").val()!==null){cnt=$(".county").val();}
    if($(".subcounty").val()!==null){sct=$(".subcounty").val();}
    if($(".mdt").val()!==null){rgn=$(".mdt").val();}
    
    if($(".startdate").val()!==null){sd=$(".startdate").val();  full_sd=$(".startdate option:selected").data("startdate"); } 
    if($(".enddate").val()!==null){ed=$(".enddate").val();  full_ed=$(".enddate option:selected").data("enddate");} 
 
            
           
            
            //now load the data
          $.ajax({
                    url:'pulldata',                            
                    type:'post',  
                    data:
                    {
                        act:'getSitesSummary',
                        fc:fc,
                        cnt:cnt,
                        sct:sct,
                        mdt:rgn,
                        sd:sd,
                        ed:ed,
                        full_sd:full_sd,
                        full_ed:full_ed,                   
                         groupbyorgunit:'\"UTJ\" as OrganizationUnit,',
                        groupby:' '
                    },
                    dataType: 'json',  
                    success: function(data) 
                    {
                         //  console.log("data ni:"+data);
                  
//                      if I want to display ovc list only   
                  
                        
                       // console.log(data);
                        
                        const keys = Object.keys(data[0]);  
                       // console.log(keys);
for (const key in data[0]) 
{  
    
    
     
    
    var ky=key;
    var vl=data[0][key];
    
    
   
   
   //console.log("The length of Radial Length is :"+radialdt.length) 
    //don't include 
    if($("#"+ky)!==null){
     $("#"+ky).html(vl);
 }
 
 
  
}
                  

                    }  
//});
                        
                  
//                         $('.dates').datepicker({
//                             todayHighlight: true, daysOfWeekDisabled: "0,6",clearBtn: true, autoclose: true,format: "yyyy-mm-dd",
//     });
     
   
                        
                        
                    });    
             
    
    
}




function emrimisdataSummaryOverall()
{ 
    
    //once the edit form is clicked, the assumption is that the add section will be loaded
    //$('#home-tab').click();
    //$('#home-tab').html("Edit Projects");
      $("#ovc_enrolled_perc_rd").html('<div class="d-flex justify-content-center"><div class="spinner-border" role="status"><span class="sr-only">Loading...</span></div></div>');
      $("#otz_enrolled_perc_rd").html('<div class="d-flex justify-content-center"><div class="spinner-border" role="status"><span class="sr-only">Loading...</span></div></div>');
   
//    console.log("_"+fc+"vs"+dt);

    var fc="";
    var cnt="";
    var sct="";
    var rgn="";
    var sd="";
    var ed="";
    
    var full_sd="";
    var full_ed="";
    
    if($(".facility").val()!==null){fc=$(".facility").val();}
    if($(".county").val()!==null){cnt=$(".county").val();}
    if($(".subcounty").val()!==null){sct=$(".subcounty").val();}
    if($(".mdt").val()!==null){rgn=$(".mdt").val();}
    
    if($(".startdate").val()!==null){sd=$(".startdate").val();  full_sd=$(".startdate option:selected").data("startdate"); } 
    if($(".enddate").val()!==null){ed=$(".enddate").val();  full_ed=$(".enddate option:selected").data("enddate");} 
 
            
           
            
            //now load the data
          $.ajax({
                    url:'dataPulls',                            
                    type:'post',  
                    data:
                    {
                        act:'getEmrCascades',
                        fc:fc,
                        cnt:cnt,
                        sct:sct,
                        mdt:rgn,
                        sd:sd,
                        ed:ed,
                        full_sd:full_sd,
                        full_ed:full_ed,                   
                         groupbyorgunit:'\"UTJ\" as OrganizationUnit,',
                        groupby:' '
                    },
                    dataType: 'json',  
                    success: function(data) 
                    {
                         //  console.log("data ni:"+data);
                  
//                      if I want to display ovc list only   
                      var rdlitems=['ovc_enrolled_perc','otz_enrolled_perc'];  
                        
                        
                       // console.log(data);
                        
                        const keys = Object.keys(data[0]);  
                       // console.log(keys);
for (const key in data[0]) 
{  
    
    
      const radialdt=[];
      const radiallabel=[];
    
    
    var ky=key;
    var vl=data[0][key];
    
    
    vl=vl.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    
    if(rdlitems.includes(ky)){
        
     
     var percent_numeric=vl.replace('%','');
     radialdt.push(percent_numeric);
     radiallabel.push(ky.replace('_',' ').replace('_perc',''));
     
     
     loadRadialChart(ky+"_rd",percent_numeric,radialdt,radiallabel);
     
 }
   //console.log("The length of Radial Length is :"+radialdt.length) 
    //don't include 
    if($("."+ky)!==null){
     $("."+ky).html(vl);
 }
 
  if($("."+ky+"_pb")!==null){
     $("."+ky+"_pb").css("width",vl);
 }
  
}
                  

                    }  
//});
                        
                  
//                         $('.dates').datepicker({
//                             todayHighlight: true, daysOfWeekDisabled: "0,6",clearBtn: true, autoclose: true,format: "yyyy-mm-dd",
//     });
     
   
                        
                        
                    });    
             
    
    
}





//This function build all the tables that has data by region.

//it makes use of one query to load all data into a table
function emrimisdataSummarybyMdt()
{ 
    
  

    var fc="";
    var cnt="";
    var sct="";
    var rgn="";
    var sd="";
    var ed="";
    
    var full_sd="";
    var full_ed="";
    
    if($(".facility").val()!==null){fc=$(".facility").val();}
    if($(".county").val()!==null){cnt=$(".county").val();}
    if($(".subcounty").val()!==null){sct=$(".subcounty").val();}
    if($(".mdt").val()!==null){rgn=$(".mdt").val();}
    
    if($(".startdate").val()!==null){sd=$(".startdate").val();  full_sd=$(".startdate option:selected").data("startdate"); } 
    if($(".enddate").val()!==null){ed=$(".enddate").val();  full_ed=$(".enddate option:selected").data("enddate");} 
 
            
           
            
            //now load the data. Note that The data will be grouped into organization units
          $.ajax({
                    url:'dataPulls',                            
                    type:'post',  
                    data:
                    {
                        act:'getEmrCascadesReadable',
                        fc:fc,
                        cnt:cnt,
                        sct:sct,
                        mdt:rgn,
                        sd:sd,
                        ed:ed,
                        full_sd:full_sd,
                        full_ed:full_ed,
                        groupbyorgunit:' `mdt` as Region,',
                        groupby:' group by mdt '
                    },
                    dataType: 'JSON',  
                    success: function(data) 
                    {
                        
                         const d=JSON.parse("["+data+"]");
                        
//                        data=d.toString().replace(/\\"/g, '"');
                        data=JSON.parse("["+data+"]");
//                        data=JSON.stringify(data);
                        
//                          console.log("data 1 ni:"+d);
//                          console.log("data 2 ni:"+data);
                  
//                      if I want to display certain Tables, I will build tables for each Cascade 
// Note that below arrays contains the names of the table colums as written on the stored procedure for ease of  management. Any renaming done to the mysql column should also be done on the table below
  
                        
   var ncdtableitems=['ncd_summary','Region','TX_CURR','Screened for Hypertension','Screened for Hypertension(%)','Hypertension Cases','Hypertension Cases (%)','Hypertension Controlled','Hypertension Controlled (%)','Diabetic Cases','Diabetis Cases Controlled','Diabetis Cases Controlled (%)'];  
   var ipttableitems=['ipt_summary','Region','TX_CURR','Eligible for IPT/TPT','Started TPT/IPT','Started TPT/IPT (%)','Has TPT/IPT Outcome','Completed IPT/TPT','Completed IPT/TPT (%)','TX New Eligible for TPT/TPT ','TX New Started on IPT/TPT','TX New Started on IPT/TPT (%)'];  
   var mhtableitems=['mh_summary','Region','TX_CURR','Screened for Mental Health 1yr','Screened for Mental Health 1 yr %','Mental Health Cases 1 yr','Anxiety Screening 1 yr','Anxiety Screening 1 yr (%)','Anxiety Cases 1 yr','Anxiety Cases 1 yr(%)'];  
   var fpttableitems=['fpt_summary','Region','TX_CURR','Contacts Listing Done','Contacts Listing Done (%)','Listed Contacts','HIV -ve Contacts','HIV +ve','HIV Unknown'];  
   
   var avl_tableitems=['avl_summary','Region','TX_CURR','Eligible For VL','VL Done','VL Done (%)','VL Sup','VL Sup(%)','Eligible Not Done VL'];  
   var cvl_tableitems=['cvl_summary','Region','CALHIV Eligible For VL','CALHIV VL Done','CALHIV VL Done (%)','CALHIV VL Sup','CALHIV VL Sup (%) ','CALHIV Eligible Not Done VL'];  
   var pvl_tableitems=['pvl_summary','Region','TX_CURR PMTCT','PMTCT Eligible for VL','PMTCT VL Done','PMTCT VL Done (%)','PMTCT VL Sup','PMTCT VL Sup (%)','PMTCT Eligible Not Done VL'];  
   var txnew_tableitems=['txnew_summary','Region','TX_NEW','TX_New Done for CD4','TX_New Done for CD4 (%)','TX_New NUPI Verified','Tx_New NUPI Verified(%)'];  
   var cx_tableitems=['cxca_summary','Region','Women of Reproductive Age(WRA)','WRA Screened','WRA Screened(%)','WRA Positive','WRA Positive (%)','WRA Treated','WRA Treated (%)'];  
                        
                        
                        var tblheader=""; //only include the table columns. The row is alreday defined
                        var tblrows="";
                       
                        var ipttblheader=""; //only include the table columns. The row is alreday defined
                        var ipttblrows="";
                        
                        var mhtblheader=""; //only include the table columns. The row is alreday defined
                        var mhtblrows="";
                        
                        var fpttblheader=""; //only include the table columns. The row is alreday defined
                        var fpttblrows="";
                        
                        var avl_tblheader=""; //only include the table columns. The row is alreday defined
                        var avl_tblrows="";
                        
                        var cvl_tblheader=""; //only include the table columns. The row is alreday defined
                        var cvl_tblrows="";
                        
                        var pvl_tblheader=""; //only include the table columns. The row is alreday defined
                        var pvl_tblrows="";
                        
                        
                        var txnew_tblheader=""; //only include the table columns. The row is alreday defined
                        var txnew_tblrows="";
                        
                        
                        var cx_tblheader=""; //only include the table columns. The row is alreday defined
                        var cx_tblrows="";
                        
                        
//                        console.log(data);
                        
                        
                        for( var rw=0;rw<data.length;rw++){
                            
                       var keys = Object.keys(data[rw]);  
                       // console.log("Headers by order:"+keys);     
              
                            
                            
                            //
                            
                       var rowstart="<tr>";

if(rw===parseInt((data.length))-1){
    
    rowstart+="<tfoot class='table-info'>";
}     
                            
         //build the table row start 
         tblrows+=rowstart;
         ipttblrows+=rowstart;
         mhtblrows+=rowstart;
         fpttblrows+=rowstart;
         avl_tblrows+=rowstart;
         cvl_tblrows+=rowstart;
         pvl_tblrows+=rowstart;
         txnew_tblrows+=rowstart;
         cx_tblrows+=rowstart;
                            
for (var key in data[rw]) 
{  
   
    
    
    var ky=key;
    var vl=data[rw][key];
   
    
   
    
    vl=vl.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    
    vl=conditionalformat(vl);
     
    //___Ncds_tables_now build table for specific table elements based on our identified data elements
                    if (ncdtableitems.includes(ky))
                    {
      //build the table header if this is the first row
                        if (rw === 0) {
                            tblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        tblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }


   //IPT Table now build table for specific table elements based on our identified data elements
                    if (ipttableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            ipttblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        ipttblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }

 //MH Table now build table for specific table elements based on our identified data elements
                    if (mhtableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            mhtblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        mhtblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }

 //FPT Table now build table for specific table elements based on our identified data elements
                    if (fpttableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            fpttblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        fpttblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }

 //All vl Table now build table for specific table elements based on our identified data elements
                    if (avl_tableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            avl_tblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        avl_tblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }

 //CALHIV vl Table now build table for specific table elements based on our identified data elements
                    if (cvl_tableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            cvl_tblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        cvl_tblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }

 //PMTCT vl Table now build table for specific table elements based on our identified data elements
                    if (pvl_tableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            pvl_tblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        pvl_tblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }
                    
 //TXNew Table now build table for specific table elements based on our identified data elements
                    if (txnew_tableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            txnew_tblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        txnew_tblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }
 //CXCA Table now build table for specific table elements based on our identified data elements
                    if (cx_tableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            cx_tblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        cx_tblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }


  
}
//end the row outside the data elements for loop

var rowend="</tr>";

if(rw===parseInt((data.length))-1){
    
    rowend+="</tfoot>";
}


tblrows+=rowend;
ipttblrows+=rowend;
mhtblrows+=rowend;
fpttblrows+=rowend;
avl_tblrows+=rowend;
cvl_tblrows+=rowend;
pvl_tblrows+=rowend;
txnew_tblrows+=rowend;
cx_tblrows+=rowend;
                  
                  
                  if(rw===parseInt((data.length))-1){
//                      console.log("This is the end of orgunit rows");
   //note that the first item on the array (refer to argument below) contains the name of the DOM element that will host our table
                      htmltablebuilder( ncdtableitems[0],tblheader,tblrows );
                      htmltablebuilder( ipttableitems[0],ipttblheader,ipttblrows );
                      htmltablebuilder( mhtableitems[0],mhtblheader,mhtblrows );
                      htmltablebuilder( fpttableitems[0],fpttblheader,fpttblrows );
                      htmltablebuilder( avl_tableitems[0],avl_tblheader,avl_tblrows );
                      htmltablebuilder( cvl_tableitems[0],cvl_tblheader,cvl_tblrows );
                      htmltablebuilder( pvl_tableitems[0],pvl_tblheader,pvl_tblrows );
                      htmltablebuilder( txnew_tableitems[0],txnew_tblheader,txnew_tblrows );
                      htmltablebuilder( cx_tableitems[0],cx_tblheader,cx_tblrows );
                      
                  }
                  
                  }//end of outer loop that increments rows
                    }  
//});
                        
                  
//                         $('.dates').datepicker({
//                             todayHighlight: true, daysOfWeekDisabled: "0,6",clearBtn: true, autoclose: true,format: "yyyy-mm-dd",
//     });
     
   
                        
                        
                    });    
             
    
    
}

//This function build all the tables that has data by region.

//it makes use of one query to load all data into a table
function emrRdqabyMdt()
{ 
    
  

    var fc="";
    var cnt="";
    var sct="";
    var rgn="";
    var sd="";
    var ed="";
    
    var full_sd="";
    var full_ed="";
    
    if($(".facility").val()!==null){fc=$(".facility").val();}
    if($(".county").val()!==null){cnt=$(".county").val();}
    if($(".subcounty").val()!==null){sct=$(".subcounty").val();}
    if($(".mdt").val()!==null){rgn=$(".mdt").val();}
    
    if($(".startdate").val()!==null){sd=$(".startdate").val();  full_sd=$(".startdate option:selected").data("startdate"); } 
    if($(".enddate").val()!==null){ed=$(".enddate").val();  full_ed=$(".enddate option:selected").data("enddate");} 
 
            
           
            
            //now load the data. Note that The data will be grouped into organization units
          $.ajax({
                    url:'dataPulls',                            
                    type:'post',  
                    data:
                    {
                        act:'getEmrRdqaData',
                        fc:fc,
                        cnt:cnt,
                        sct:sct,
                        mdt:rgn,
                        sd:sd,
                        ed:ed,
                        full_sd:full_sd,
                        full_ed:full_ed,
                        groupbyorgunit:' `mdt` as Region,',
                        groupby:' group by mdt '
                    },
                    dataType: 'JSON',  
                    success: function(data) 
                    {
                        
                         const d=JSON.parse("["+data+"]");
                        
//                        data=d.toString().replace(/\\"/g, '"');
                        data=JSON.parse("["+data+"]");
//                        data=JSON.stringify(data);
                        
                     
                  
//                      if I want to display certain Tables, I will build tables for each Cascade 
// Note that below arrays contains the names of the table colums as written on the stored procedure for ease of  management. Any renaming done to the mysql column should also be done on the table below
  
                        
   var ncdtableitems=['rdqa_summary','Region','Data Accuracy','Completeness','Data Concordance','RDQA Score'];  
   var ipttableitems=['rdqadetails_summary','Region','Missing Entry Point','Missing Locator Information','Missing Date Confirmed HIV +ve','Missing Most Recent WHO Stage','Missing Most Recent Regimen Line','Has Triage Captured But No TCA Date','No Recent VL','Missing Baseline CD4','Missing Baseline WHO','Started on Prep without Follow UP Prep','Transfer Out Patients Missing TO Verification','Confirmed HIV +ve Before Date of Birth','Ever on TPT in Grencard But Not Enrolled in TPT Module','First VL before ART Start Date'];  
                        
                        
                        var tblheader=""; //only include the table columns. The row is alreday defined
                        var tblrows="";
                       
                        var ipttblheader=""; //only include the table columns. The row is alreday defined
                        var ipttblrows="";
                        
                        
//                        console.log(data);
                        
                        
                        for( var rw=0;rw<data.length;rw++){
                            
                       var keys = Object.keys(data[rw]);  
//                        console.log("Headers by order:"+keys);     
                            
                            
                              var rowstart="<tr>";

if(rw===parseInt((data.length))-1){
    
    rowstart+="<tfoot class='table-info'>";
}     
                            
         //build the table row start 
         tblrows+=rowstart;
         ipttblrows+=rowstart;
                            
for (var key in data[rw]) 
{  
   
    
    
    var ky=key;
    var vl=data[rw][key];
   
    
   
    
    vl=vl.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    
    vl=conditionalformat(vl);
     
    //___Ncds_tables_now build table for specific table elements based on our identified data elements
                    if (ncdtableitems.includes(ky))
                    {
      //build the table header if this is the first row
                        if (rw === 0) {
                            tblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        tblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }


   //IPT Table now build table for specific table elements based on our identified data elements
                    if (ipttableitems.includes(ky))
                    {
                        //build the table header if this is the first row
                        if (rw === 0) {
                            ipttblheader += "<th style='width:40px;'>" + ky + "</th>";
                        }
                        //add data into table rows  
                        ipttblrows += "<td style='width:40px;'>" + vl + "</td>";
                    }



  
}
//end the row outside the data elements for loop
var rowend="</tr>";

if(rw===parseInt((data.length))-1){
    
    rowend+="</tfoot>";
}


tblrows+=rowend;
ipttblrows+=rowend;

                  
                  
                  if(rw===parseInt((data.length))-1){
//                      console.log("This is the end of orgunit rows");
   //note that the first item on the array (refer to argument below) contains the name of the DOM element that will host our table
                      htmltablebuilder( ncdtableitems[0],tblheader,tblrows );
                      htmltablebuilder( ipttableitems[0],ipttblheader,ipttblrows );
                    
                      
                  }
                  
                  }//end of outer loop that increments rows
                    }  
//});
                        
                  
//                         $('.dates').datepicker({
//                             todayHighlight: true, daysOfWeekDisabled: "0,6",clearBtn: true, autoclose: true,format: "yyyy-mm-dd",
//     });
     
   
                        
                        
                    });    
             
    
    
}





function htmltablebuilder( hostelementname,tableheaders,tablebody )
{
    
    var tbl = "<thead>"
            + " <tr> "
            + tableheaders
           
            + "</tr>"
            + "</thead>"
            + "<tbody>"
            
            + tablebody
            

            + "</tbody>";
             
    
    
//    console.log(" Table for element "+hostelementname+" is: "+tbl);
    
    $("."+hostelementname).html(tbl);
    
}


//have a function that adds percentage values and does conditional formating 

function conditionalformat( val ){
    
   var conditioned_value="";
    
    var numericval=val.replace('%','');
   
   if(val.indexOf("%")>=0 && $.isNumeric(val.replace('%',''))){
   
         if(numericval>=90)                   { conditioned_value='<b>'+val+'</b> <div class="progress"><div class="progress-bar bg-success" style="width: '+numericval+'%"></div></div>';  }
    else if(numericval>=85  && numericval<90 ){ conditioned_value='<b>'+val+'</b> <div class="progress"><div class="progress-bar bg-success" style="width: '+numericval+'%"></div></div>';  }
    else if(numericval>=55 && numericval<85 ) { conditioned_value='<b>'+val+'</b> <div class="progress"><div class="progress-bar bg-warning" style="width: '+numericval+'%"></div></div>';  }
    else if(numericval>=35 && numericval<55 ) { conditioned_value='<b>'+val+'</b> <div class="progress"><div class="progress-bar bg-infor" style="width: '+numericval+'%"></div></div>';  }
    else                                      { conditioned_value='<b>'+val+'</b> <div class="progress"><div class="progress-bar bg-danger" style="width: '+numericval+'%"></div></div>';  }
       
   }
   else {
       
   conditioned_value= '<b>'+val+'</b>';    
   }
    
    
    
    
    return conditioned_value;
}



//This is a java script that loads prevention charts

//Here we are loading three type of charts for each indicator. The bignumber ( eg 14,000 45%) , The periodic trends data and the the mdt regional summary 

function loadAnnualPerformanceAgainstTarget(spname,elementname)
{ 
    
    //once the edit form is clicked, the assumption is that the add section will be loaded
    //$('#home-tab').click();
    //$('#home-tab').html("Edit Projects");
//      $("#ovc_enrolled_perc_rd").html('<div class="d-flex justify-content-center"><div class="spinner-border" role="status"><span class="sr-only">Loading...</span></div></div>');
//      $("#otz_enrolled_perc_rd").html('<div class="d-flex justify-content-center"><div class="spinner-border" role="status"><span class="sr-only">Loading...</span></div></div>');
//   
//    console.log("_"+fc+"vs"+dt);


var    bignumberelement="ap_"+elementname;
var    bignumberelement_perc="ap_"+elementname+"_perc";
var    bignumberelement_perc_pb="ap_"+elementname+"_perc_pb";
var    trendelement ="ap_"+elementname+"_trends";
var    regiontemplate="ap_"+elementname+"_summary";

    var fc="";
    var cnt="";
    var sct="";
    var rgn="";
    var sd="";
    var ed="";
    
    var full_sd="";
    var full_ed="";
    
    if($(".facility").val()!==null){fc=$(".facility").val();}
    if($(".county").val()!==null){cnt=$(".county").val();}
    if($(".subcounty").val()!==null){sct=$(".subcounty").val();}
    if($(".mdt").val()!==null){rgn=$(".mdt").val();}
    
    if($(".startdate").val()!==null){sd=$(".startdate").val();  full_sd=$(".startdate option:selected").data("startdate"); } 
    if($(".enddate").val()!==null){ed=$(".enddate").val();  full_ed=$(".enddate option:selected").data("enddate");} 
 
            
           
            
            //now load the data
          $.ajax({
                    url:'dataPulls',                            
                    type:'post',  
                    data:
                    {
                        act:'getAnnualPerformance',
                        fc:fc,
                        cnt:cnt,
                        sct:sct,
                        mdt:rgn,
                        sd:sd,
                        ed:ed,
                        full_sd:full_sd,
                        full_ed:full_ed,                   
                         groupbyorgunit:'`mdt` as Region,',
                        groupby:' group by  yearmonth  ',
                        annualperformancesp:spname
                    },
                    dataType: 'json',  
                    success: function(data) 
                    {
                           
                  var periodic_mns=[];
                  var periodic_perf=[];
                  var periodic_tgt=[];
                  var periodic_res=[];
                
                  var periodictitle="";
//               
                 //arrays for regional data
                 
               
                var tblheader= "<th>Region</th><th >Annual Target</th><th >Achieved</th><th >%Achieved</th>";
                  var tblrows="";
                    
//                    console.log("Data has rows: "+data.length);
                        
                        for (var rw=0;rw<data.length;rw++){
                          
                

                            
                            
//                            console.log("data is:"+data[rw]);
                        const keys = Object.keys(data[rw]);  
                        
                        var rws=data[rw];
                        var category=rws.category;
                        var monthname=rws.Month;
                        var period=rws.Period;
                        var region=rws.Region;
                        var indicator=rws.Indicator;
                            indicator=indicator.toUpperCase();
                        var result=Math.round(rws.Result);
                            var resultcomad=result.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                        var atarget=Math.round(rws.AnnualTarget);
                            atarget=atarget.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                        var mtarget=Math.round(rws.MonthlyTarget);
                            mtarget=mtarget.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                        var perf=Math.round(rws.Perf);
                        
//                        console.log("Categories ni: "+rws.category);
                            
                        /**  **/ 
                        
              if(category==='overalltotal'){ 
              
//This section deiplays the annual performance for big numbers
//
 $("."+bignumberelement).html(resultcomad);
 $("."+bignumberelement_perc).html(perf+"%");
 $("."+bignumberelement_perc_pb).css("width",perf);
                            
                            }  
                            
             if(category==='periodictrends'){
                 
                  periodic_mns.push(monthname);
                   periodic_perf.push(perf);
                   periodic_res.push(result);
                   periodic_tgt.push(mtarget);
                   
                   
                   
                 periodictitle=indicator+" Trends";
                 
//                 console.log("Months:"+periodic_mns);
//                 console.log("performance:"+periodic_dt);
                 
             }               
                            
       if(category==='regionaltotal'){
                 
            
//_____________Build an html table inside for loop_____
 var rowstart="<tr>";

if(rw===parseInt((data.length))-1)
{   
    rowstart+="<tfoot class='table-info'>";
}  

//build the table row start 
tblrows+=rowstart;
var vl1=conditionalformat(perf+"%");
//add data into table rows  
tblrows += "<td ><b>" + region + "</b></td>";
tblrows += "<td >" + atarget + "</td>";
tblrows += "<td >" + resultcomad + "</td>";
tblrows += "<td ><b>" + vl1 + "</b></td>";
//outside for loop						
var rowend="</tr>";

if(rw===parseInt((data.length))-1){
    
    rowend+="</tfoot>";
}

tblrows+=rowend;            
                 
             }//end of regional total                          
                

if(rw===parseInt(data.length)-1){
    
//   loadLiniearChart(trendelement,periodic_perf,periodic_mns,'bar',periodictitle);   
//   loadLiniearChart(trendelement,periodic_perf,periodic_mns,'bar',periodictitle);   
   htmltablebuilder( regiontemplate,tblheader,tblrows );
   loadSingleComboChart(trendelement,periodic_perf,periodic_tgt,periodic_res,periodic_mns,periodictitle);
//    loadComboChart(elementid,perc_dt,target_dt,results_dt,mns,title)
}

                    }  //end of looping throug all data rows
                    
                    
                  
                    
                    
                
                    }//end of success formula
                
                
                
//});
                        
                  

   
                        
                        
                    });    
             
    
    
}



        function loadLiniearChart(elementid,dt,mns,charttype,title)
{ 
  
//    alert("Called");
    
   
    //once the edit form is clicked, the assumption is that the add section will be loaded
    //$('#home-tab').click();
    //$('#home-tab').html("Edit Projects");
    
   
    console.log("element is_"+elementid);
            
  
                    
                        var ttl=title;
                       
                        
                        $(function() {
            var options = {
                chart: {
                    height: 350,
                    type: charttype,
                    zoom: {
                        enabled: true
                    }
                        },
                dataLabels: {
                    enabled: true,
                    width: 2
                },
                stroke: {
                    curve: 'straight',
                },
                colors: ["#1abc9c"],
                series: [{
                    name: '% Performance',
                    data: dt
                }],
                title: {
                    text: ttl,
                    align: 'center'
                },
                grid: {
                    row: {
                        colors: ['#f3f6ff', 'transparent'], // takes an array which will be repeated on columns
                        opacity: 0.5
                    },
                },
                xaxis: {
                    categories: mns,
                }
            };
  $("."+elementid).html('');
            var chart = new ApexCharts(
                document.querySelector("."+elementid),
                options
                                      );
   
            chart.render();
        });
        
        
              
                        
                        
                   
                        
                        }//end of loadliniar chart
               
    
        function loadSingleComboChart(elementid,perc_dt,target_dt,results_dt,mns,title)
{ 
  
//    alert("Called");
    
   
    //once the edit form is clicked, the assumption is that the add section will be loaded
    //$('#home-tab').click();
    //$('#home-tab').html("Edit Projects");
    
 
//    console.log("element is_"+elementid+":% echieved:_"+perc_dt+": target:_"+target_dt+": results:_"+results_dt);
            
  
                    
                        var ttl=title;
                       
                        
                        $(function() {
          var options = {
          series: [{
          name: 'Results',
          type: 'column',
          data: results_dt
        }, {
          name: '% Achieved',
          type: 'line',
          data: perc_dt
        
        }],
          chart: {
          height: 350,
          type: 'line',
            zoom: {
                        enabled: false
                    }
        },
        stroke: {
          width: [0, 4]
        },
        title: {
          text: ttl
          ,align: 'center'
          , floating: true
        },
        dataLabels: {
          enabled: true,
           style: {fontSize: '16px'}, 
          enabledOnSeries: [0,1]
          ,
           formatter: function (value, { seriesIndex }) {
      if (seriesIndex === 1) {
        return value + "%"; // Apply % only to the line chart values
      }
      return value;
    }
          
        }
        
        ,labels: mns,
        yaxis: [{
          title: {
            text: 'Results'
          }
        
        }, {
          opposite: true,
          title: {
            text: '% Achieved'
          },min: 0,
          labels: {
        formatter: function (val) {
          return val + "%"; // Adds % to y-axis labels
        }}
        }]//end of y axis
        };
         $("."+elementid).html('');
        var chart = new ApexCharts(document.querySelector("."+elementid), options);
        chart.render();
        });
        
        
              
                        
                        
                   
                        
                        }//end of loadliniar chart
               
    
        function loadMultiComboChart(elementid,perc_dt,target_dt,results_dt,mns,title)
{ 
  
//    alert("Called");
    
   
    //once the edit form is clicked, the assumption is that the add section will be loaded
    //$('#home-tab').click();
    //$('#home-tab').html("Edit Projects");
    
   
    console.log("element is_"+elementid);
            
  
                    
                        var ttl=title;
                       
                        
                        $(function() {
           var options = {
          series: [{
          name: 'Target',
          type: 'column',
          data: target_dt
        }, {
          name: 'Results',
          type: 'column',
          data: results_dt
        }, {
          name: 'Performance',
          type: 'line',
          data: perc_dt
        }],
          chart: {
          height: 350,
          type: 'line',
          stacked: false
        },
        dataLabels: {
          enabled: true
        },
        stroke: {
          width: [1, 1, 4]
        },
        title: {
          text: title,
          align: 'left',
          offsetX: 110
        },
        xaxis: {
          categories: mns,
        },
        yaxis: [
          {
            seriesName: 'Target',
            axisTicks: {
              show: true,
            },
            axisBorder: {
              show: true,
              color: '#008FFB'
            },
            labels: {
              style: {
                colors: '#008FFB',
              }
            },
            title: {
              text: "Target",
              style: {
                color: '#008FFB',
              }
            },
            tooltip: {
              enabled: true
            }
          },
          {
            seriesName: 'Results',
            opposite: true,
            axisTicks: {
              show: true,
            },
            axisBorder: {
              show: true,
              color: '#00E396'
            },
            labels: {
              style: {
                colors: '#00E396',
              }
            },
            title: {
              text: "Results",
              style: {
                color: '#00E396',
              }
            },
          },
          {
            seriesName: 'Performance',
            opposite: true,
            axisTicks: {
              show: true,
            },
            axisBorder: {
              show: true,
              color: '#FEB019'
            },
            labels: {
              style: {
                colors: '#FEB019',
              },
            },
            title: {
              text: "Performance (%)",
              style: {
                color: '#FEB019',
              }
            }
          },
        ],
        tooltip: {
          fixed: {
            enabled: true,
            position: 'topLeft', // topRight, topLeft, bottomRight, bottomLeft
            offsetY: 30,
            offsetX: 60
          },
        },
        legend: {
          horizontalAlign: 'left',
          offsetX: 40
        }
        };
  $("."+elementid).html('');
        var chart = new ApexCharts(document.querySelector("."+elementid), options);
        chart.render();
        });
        
        
              
                        
                        
                   
                        
                        }//end of loadliniar chart
               
    
    

    
    
    
    


  
function updtimis()
{
    utjSitesSummaryOverall();
 emrimisdataSummaryOverall();  
 emrimisdataSummarybyMdt();
 emrRdqabyMdt();

loadAnnualPerformanceAgainstTarget('analytics_prevention_cascades_gendgbv','gend_gbv');
loadAnnualPerformanceAgainstTarget('analytics_prevention_cascades_prepct','prep_ct');
loadAnnualPerformanceAgainstTarget('analytics_prevention_cascades_prepnew','prep_new');
loadAnnualPerformanceAgainstTarget('analytics_prevention_cascades_tb_prev_d','tb_prev_d');
loadAnnualPerformanceAgainstTarget('analytics_prevention_cascades_tb_prev_n','tb_prev_n');
//loadAnnualPerformanceAgainstTarget('analytics_testing_cascades_htstst','hts_tst');
//loadAnnualPerformanceAgainstTarget('analytics_testing_cascades_htspos','hts_tst_pos');
//loadAnnualPerformanceAgainstTarget('analytics_testing_other_cascades_cxca_scrn','cxca_scrn');
//loadAnnualPerformanceAgainstTarget('analytics_testing_other_cascades_htsself','hts_self');
//
//loadAnnualPerformanceAgainstTarget('analytics_testing_other_cascades_pmtct_stat_pos','pmtct_pos');
//loadAnnualPerformanceAgainstTarget('analytics_testing_other_cascades_pmtct_stat_d','pmtct_stat_d');
//loadAnnualPerformanceAgainstTarget('analytics_testing_other_cascades_pmtct_stat_n','pmtct_stat_n');
//loadAnnualPerformanceAgainstTarget('analytics_testing_other_cascades_tb_pos','tb_pos');
//loadAnnualPerformanceAgainstTarget('analytics_testing_other_cascades_tb_stat_d','tb_stat_d');
//loadAnnualPerformanceAgainstTarget('analytics_testing_other_cascades_tb_stat_n','tb_stat_n');
//loadAnnualPerformanceAgainstTarget('analytics_treatment_cascades_pmtct_art','pmtct_art');
//loadAnnualPerformanceAgainstTarget('analytics_treatment_cascades_tb_art','tb_art');
//loadAnnualPerformanceAgainstTarget('analytics_treatment_cascades_tx_curr','tx_curr');
//loadAnnualPerformanceAgainstTarget('analytics_treatment_cascades_tx_new','tx_new');
//loadAnnualPerformanceAgainstTarget('analytics_treatment_cascades_tx_tb_d','tx_tb_d');
//loadAnnualPerformanceAgainstTarget('analytics_treatment_cascades_tx_tb_n','tx_tb_n');
//loadAnnualPerformanceAgainstTarget('analytics_vl_suppression_cascades_txpvls_d','tx_pvls_d');
//loadAnnualPerformanceAgainstTarget('analytics_vl_suppression_cascades_txpvls_n','tx_pvls_n');


}

  