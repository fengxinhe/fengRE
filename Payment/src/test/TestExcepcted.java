package test;

public class TestExcepcted {
	public double test(double callMin,double rate)
	{
		double result=0;
		result=25+callMin*0.15*(1-rate);
		System.out.println(result);
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestExcepcted test=new TestExcepcted();
		test.test(5000,0);
	}

}
