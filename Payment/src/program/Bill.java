package program;

public class Bill {
	public Bill(){final_fee=-1;}
	public Bill(double call,int not) {
		super();
		call_minut=call;
		not_intime=not;
		// TODO Auto-generated constructor stub
	}
	double call_minut=0;
	int not_intime=0;
	double final_fee=-1;
	String result;//记录最终测试结果
	public double getCall_minut() {
		return call_minut;
	}
	public void setCall_minut(double call_minut) {
		this.call_minut = call_minut;
	}
	public int getNot_intime() {
		return not_intime;
	}
	public void setNot_intime(int not_intime) {
		this.not_intime = not_intime;
	}
	public double getFinal_fee() {
		return final_fee;
	}
	public void setFinal_fee(double final_fee) {
		this.final_fee = final_fee;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

}
