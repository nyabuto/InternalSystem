/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataCleaning;

import java.util.Iterator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author GNyabuto
 */
public class DataCleanerClass {
    
    public XSSFWorkbook TB(XSSFWorkbook tb){
        int col_count = 20;
        XSSFSheet worksheet;
        
        worksheet = tb.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        while(rowIterator.hasNext()){
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;}
        
        
       for (int j=0;j<col_count;j++){
     
       } 

            i++;
        }
        
         
        
        return tb;
    }
    public XSSFWorkbook ViralLoad(XSSFWorkbook viralload){
           int col_count = 20;
        XSSFSheet worksheet;
        
        worksheet = viralload.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        while(rowIterator.hasNext()){
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;}
        
        
       for (int j=0;j<col_count;j++){
     
       } 

            i++;
        }
        
        
        
        return viralload;
    }
    public XSSFWorkbook EIDTST(XSSFWorkbook eid){
         int col_count = 20;
        XSSFSheet worksheet;
        
        worksheet = eid.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        while(rowIterator.hasNext()){
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;}
        
        
       for (int j=0;j<col_count;j++){
     
       } 

            i++;
        }
        
          
        
        return eid;
    }
    public XSSFWorkbook EIDPOS(XSSFWorkbook eid){
         int col_count = 20;
        XSSFSheet worksheet;
        
        worksheet = eid.getSheetAt(0);
        Iterator rowIterator = worksheet.iterator();

        int i=1,y=0;
        while(rowIterator.hasNext()){
        XSSFRow rowi = worksheet.getRow(i);
        if( rowi==null){
         break;}
        
        
       for (int j=0;j<col_count;j++){
     
       } 

            i++;
        }
        
        
        
        return eid;
    }
}
