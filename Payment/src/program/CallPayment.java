package program;
import java.io.IOException;
import java.util.ArrayList;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import program.Util;
import program.Bill;

public class CallPayment {
	private double call_min=0;
	private int not_intime=0;//未按时缴费次数
	private double count_rate[]=new double[]{0,0.015,0.02,0.025,0.03};//折扣率数组
	private double final_fee=-1;
	private double expected_fee=0;
	final private double Month_Rent=25;//月租费
	final private double Fee_per_min=0.15;
	
	
	public double calculate_fee(double callMin,int notIntime)
	{
		double discountRate=0;
		double finalFee=0;
		if(callMin>0&&callMin<=60&&notIntime==0){discountRate=count_rate[0];}
		if(callMin>60&&callMin<=120&&notIntime<=2){discountRate=count_rate[1];}
		if(callMin>120&&callMin<=180&&notIntime<=3){discountRate=count_rate[2];}
		if(callMin>180&&callMin<=300&&notIntime<=3){discountRate=count_rate[3];}
		if(callMin>300&&notIntime<=4){discountRate=count_rate[4];}
		finalFee=Month_Rent+callMin*Fee_per_min*(1-discountRate);
		return finalFee;
	}
	
	public Bill calculate_fee(Bill billtemp)//面向对象计算电话费，返回Bill对象
	{
		double callMin=billtemp.getCall_minut();
		int notIntime=billtemp.getNot_intime();
		double discountRate=0;
		double finalFee=0;
		if(callMin>0&&callMin<=60&&notIntime==0){discountRate=count_rate[0];}
		if(callMin>60&&callMin<=120&&notIntime<=2){discountRate=count_rate[1];}
		if(callMin>120&&callMin<=180&&notIntime<=3){discountRate=count_rate[2];}
		if(callMin>180&&callMin<=300&&notIntime<=3){discountRate=count_rate[3];}
		if(callMin>300&&notIntime<=4){discountRate=count_rate[4];}
		finalFee=Month_Rent+callMin*Fee_per_min*(1-discountRate);
		billtemp.setFinal_fee(finalFee);
		return billtemp;
	}
	
	public boolean isValid(double callMin,int notIntime)//自定义call_min在[0,10000]之间，判断输入是否合法
	{
		boolean isvalid=false;
		if(callMin>=0&&callMin<=10000&&notIntime>=0&&notIntime<=11)
		{
			isvalid=true;
		}
		return isvalid;
	}
	
	public boolean isValid(Bill billtemp)//自定义call_min在[0,10000]之间，判断输入是否合法
	{
		double callMin=billtemp.getCall_minut();
		int notIntime=billtemp.getNot_intime();
		boolean isvalid=false;
		if(callMin>=0&&callMin<=10000&&notIntime>=0&&notIntime<=11)
		{
			isvalid=true;
		}
		return isvalid;
	}
	
	
	public void calculate(double callMin,int notIntime)
	{
		if(isValid(callMin,notIntime))
		{
			final_fee=calculate_fee(callMin,notIntime);
			System.out.println("final fee:"+final_fee);
		}
		else{
			System.out.println("Invalid Input");
		}
	}
	
	public void calculate_fee(String str) throws BiffException, IOException, RowsExceededException, WriteException
	{
		Util util=new Util();
		util.inputData(str);
		ArrayList BillList=util.getBillList();
		for(int i=0;i<BillList.size();i++)
		{
			Bill bill=(program.Bill) BillList.get(i);
			if(isValid(bill)){
				calculate_fee(bill);
				bill.setResult(bill.getFinal_fee()+"");
			}else{
				bill.setResult("Invalid Input");
			}
		
			
		}
		util.writeData(str, BillList);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
