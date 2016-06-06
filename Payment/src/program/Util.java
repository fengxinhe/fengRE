package program;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.SortingFocusTraversalPolicy;

//import com.sun.accessibility.internal.resources.accessibility;
//import com.sun.corba.se.spi.ior.Writeable;
//import com.sun.istack.internal.Nullable;
//import com.sun.webkit.graphics.WCPageBackBuffer;
//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import jxl.*;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import program.Bill;

public class Util {
	
	
	int numColumns=0;
	int numRows=0;
    File wfile,rfile;
    Workbook rWorkbook=null;
    WritableWorkbook wwb=null;

	ArrayList BillList=new ArrayList<Bill>();//账单ArrayList
	
	public void inputData(String str) throws BiffException, IOException{//读取数据
		rfile=createFile(str);
	    rWorkbook=Workbook.getWorkbook(rfile);
		Sheet rSheet=rWorkbook.getSheet(0);
		numColumns=rSheet.getColumns();
		numRows=rSheet.getRows();
		System.out.println(numRows+":"+numColumns);
		String[] column1Str =new String[numRows];
		String[] column2Str =new String[numRows];
		for(int i=1;i<numRows;i++){
			column1Str[i]=rSheet.getCell(0,i).getContents();
			column2Str[i]=rSheet.getCell(1,i).getContents();
			Bill bill=new Bill(Double.parseDouble(column1Str[i]),Integer.parseInt(column2Str[i]));
			BillList.add(bill);
		}

	}
	
	public ArrayList getBillList()//返回账单ArrayList
	{
		return BillList;
	}
	
	public File createFile(String str)//根据路径创建文件
	{
		File file=new File(str);
		return file;
	}
	
	public  void writeData(String str,ArrayList BillList) throws BiffException, IOException, RowsExceededException, WriteException{
		
		wfile=createFile(str);
		 Workbook wb = Workbook.getWorkbook(wfile); 
		 WritableWorkbook book =  Workbook.createWorkbook(wfile, wb); 
		 WritableSheet sheet1 = book.getSheet(0);
		 for(int i=0;i<BillList.size();i++)
		 {
			 Bill bill=(Bill) BillList.get(i);
			 sheet1.addCell(new Label(3, i+1,bill.getResult()+""));
		 }

		 book.write();  
         book.close(); 
		 
	}

	public static void main(String[] args) throws RowsExceededException, WriteException {
		
	}

}
