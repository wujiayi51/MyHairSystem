package com.xlfd.common.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExportExcelUtils {  
    
    private String title; // �������ı���  
      
    private String[] rowName;// ������������  
      
    private List<Object[]>  dataList = new ArrayList<Object[]>(); // ���������List����  
      
    private HttpServletResponse  response;  
      
  
    // ����Ҫ���������  
    public ExportExcelUtils(String title,String[] rowName,List<Object[]>  dataList,HttpServletResponse  response){  
        this.title=title;  
        this.rowName=rowName;  
        this.dataList=dataList;  
        this.response = response;  
    }  
      
    // ��������  
    public void exportData(){  
        try {  
            HSSFWorkbook workbook =new HSSFWorkbook(); // ����һ��excel����  
            HSSFSheet sheet =workbook.createSheet(title); // �������  
            sheet.setDefaultColumnWidth(15);  
        
            // ������������  
            HSSFRow rowm  =sheet.createRow(0);  // ��  
            HSSFCell cellTiltle =rowm.createCell(0);  // ��Ԫ��  
           
         
            // sheet��ʽ����  
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook); // ͷ��ʽ  
            HSSFCellStyle style = this.getStyle(workbook);  // ��Ԫ����ʽ  
           
            style.setAlignment(HorizontalAlignment.CENTER);
       
           /* setBorder.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
            setBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);//ˮƽ���� 
            setBorder.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//��ֱ����
               */
            
             
            // �ϲ���һ�е�������  
            sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) (rowName.length-1)));  
            cellTiltle.setCellStyle(columnTopStyle);  
            cellTiltle.setCellValue(title); 

            int columnNum = rowName.length;  // ����еĳ���  
            HSSFRow rowRowName = sheet.createRow(1);  // �ڵڶ��д�����  
            HSSFCellStyle cells =workbook.createCellStyle();  
            cells.setBottomBorderColor(HSSFColor.BLACK.index);    
            rowRowName.setRowStyle(cells);  
            
              
            // ѭ�� �������Ž�ȥ  
            for (int i = 0; i < columnNum; i++) {  	
                HSSFCell  cellRowName = rowRowName.createCell((int)i);  
                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING); // ��Ԫ������            
                HSSFRichTextString text = new HSSFRichTextString(rowName[i]);  // �õ��е�ֵ  
                cellRowName.setCellValue(text); // �����е�ֵ  
                cellRowName.setCellStyle(columnTopStyle); // ��ʽ  
            }  
        

            // ����ѯ�����������õ���Ӧ�ĵ�Ԫ����  
            for (int i = 0; i < dataList.size(); i++) {  
                Object[] obj = dataList.get(i);//����ÿ������  
                HSSFRow row = sheet.createRow(i+2);//�������������  
                for (int j = 0; j < obj.length; j++) {  
                     HSSFCell  cell = null;   //���õ�Ԫ�����������   
                     if(j==0){  
                         // ��һ������Ϊ���  
                         cell = row.createCell(j,HSSFCell.CELL_TYPE_NUMERIC);  
                         cell.setCellValue(i+1);  
                     }else{  
                         cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);  
                         if(!"".equals(obj[j]) && obj[j] != null){    
                                cell.setCellValue(obj[j].toString());                       //���õ�Ԫ���ֵ    
                            }else{  
                                cell.setCellValue("  ");  
                            }    
                     }  
                     cell.setCellStyle(style); // ��ʽ  
                }  
            }  
            //  ���п����ŵ������г��Զ���Ӧ  
         /*    sheet.autoSizeColumn((short)0); //������һ�п��  
             sheet.autoSizeColumn((short)1); //�����ڶ��п��  
             sheet.autoSizeColumn((short)2); //���������п��  
             sheet.autoSizeColumn((short)3); //���������п��  
             sheet.autoSizeColumn((short)4); //���������п��  
             sheet.autoSizeColumn((short)5); //���������п��  
             sheet.autoSizeColumn((short)6); //���������п��  
             sheet.autoSizeColumn((short)7); //���������п��  
             sheet.autoSizeColumn((short)8); //���������п��  
*/               
             if(workbook !=null){    
                    try    
                    {    
                    	// excel ���ļ���  
                    	Date date = new Date();
                		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                		String datetime = sdf.format(date);
                    	String fileName = title + datetime;
                    	//String fileName0 = title + String.valueOf(System.currentTimeMillis()) + ".xls";    
                        //String fileName11 = URLEncoder.encode(fileName,"UTF-8");  
                        //String headStr = "attachment; filename=\"" + fileName11 + "\"";    
                        //response.setContentType("APPLICATION/OCTET-STREAM");    
                        //response.setHeader("Content-Disposition", headStr);
                        response.setContentType("application/x-download");
                        response.setCharacterEncoding("utf-8");
                        response.setHeader("Content-disposition", "attachment;filename="+new String(fileName.getBytes("gbk"), "iso8859-1")+".xls");
                        OutputStream out = response.getOutputStream();  
                       // FileOutputStream  out = new FileOutputStream ("C:/Users/asus-pc/Desktop/ccc/ÿ���˵�.xls");  
                        workbook.write(out);  
                        out.flush();  
                        out.close();  
                    	
                    	
                    }    
                    catch (IOException e)    
                    {    
                        e.printStackTrace();    
                    }   
                      
                }    
        
            }catch(Exception e){    
                e.printStackTrace();    
            }    
                
        }    
              
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {    
          
        // ��������    
        HSSFFont font = workbook.createFont();    
        //���������С    
        font.setFontHeightInPoints((short)14);    
        //����Ӵ�    
        font.setBold(true);
        /*font.setBold(bold);
        setBold(BorderStyle.THICK);  */  
        //������������     
        font.setFontName("Courier New");    
        //������ʽ;     
        HSSFCellStyle style = workbook.createCellStyle();    
        //���õױ߿�;     
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);//��߿�    
        style.setBorderTop(BorderStyle.THIN);//�ϱ߿�    
        style.setBorderRight(BorderStyle.THIN);//�ұ߿�   
        
        //font.setColor(Font.COLOR_RED);
       // style.setBorderBottom(HSSFCellStyle.BORDER_THIN);    
        //���õױ߿���ɫ;      
        style.setBottomBorderColor(HSSFColor.BLACK.index);    
        //������߿�;       
       // style.setBorderLeft(HSSFCellStyle.BORDER_THIN);    
        //������߿���ɫ;     
        style.setLeftBorderColor(HSSFColor.BLACK.index);    
        //�����ұ߿�;     
       // style.setBorderRight(HSSFColor.BLUE);    
        //�����ұ߿���ɫ;     
       // style.setRightBorderColor(HSSFColor.BLACK.index);    
        //���ö��߿�;     
        //style.setBorderTop(HSSFCellStyle.BORDER_THIN);    
        //���ö��߿���ɫ;      
        style.setTopBorderColor(HSSFColor.BLACK.index);    
        //����ʽ��Ӧ�����õ�����;      
        style.setFont(font);    
        //�����Զ�����;     
        style.setWrapText(false);    
        //����ˮƽ�������ʽΪ���ж���;      
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
        //���ô�ֱ�������ʽΪ���ж���;     
        //style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        style.setAlignment(HorizontalAlignment.CENTER);// ����  
        style.setVerticalAlignment(VerticalAlignment.CENTER);;//��ֱ 
            
        return style;    
            
  }    
      
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {    
        // ��������    
        HSSFFont font = workbook.createFont();    
        //���������С    
        font.setFontHeightInPoints((short)10);    
        //����Ӵ�    
        font.setBold(true);
        //font.setBoldweight(BorderStyle.BOLDWEIGHT_BOLD);    
        //������������     
        font.setFontName("Courier New");    
        //������ʽ;     
        HSSFCellStyle style = workbook.createCellStyle();    
        //���õױ߿�;     
        //style.setBorderBottom(HSSFCellStyle.BORDER_THIN);    
        //���õױ߿���ɫ;      
        style.setBottomBorderColor(HSSFColor.BLACK.index);    
        //������߿�;       
        //style.setBorderLeft(HSSFCellStyle.BORDER_THIN);    
        //������߿���ɫ;     
        style.setLeftBorderColor(HSSFColor.BLACK.index);    
        //�����ұ߿�;     
        //style.setBorderRight(HSSFCellStyle.BORDER_THIN);    
        //�����ұ߿���ɫ;     
        style.setRightBorderColor(HSSFColor.BLACK.index);    
        //���ö��߿�;     
        //style.setBorderTop(HSSFCellStyle.BORDER_THIN);    
        //���ö��߿���ɫ;      
        style.setTopBorderColor(HSSFColor.BLACK.index);    
        //����ʽ��Ӧ�����õ�����;      
        style.setFont(font);    
        //�����Զ�����;     
        style.setWrapText(false);    
        //����ˮƽ�������ʽΪ���ж���;      
       // style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
        //���ô�ֱ�������ʽΪ���ж���;     
        //style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);//��߿�    
        style.setBorderTop(BorderStyle.THIN);//�ϱ߿�    
        style.setBorderRight(BorderStyle.THIN);//�ұ߿�    
           
        return style;    
  }    
}    
