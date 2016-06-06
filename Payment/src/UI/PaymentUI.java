package UI;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import program.CallPayment;
public class PaymentUI {

	JFrame jtfMainFrame;
	JButton jbnButton1, jbnButton2;
	JTextField jtfInput1,jtfInput2,jtfInput3,jtfoutput;//ͨ�����������δ�ɷѴ�������򣬲����ļ�������򣬼����������
	JPanel jplPanel;
	JLabel jlbLabel1,jlbLabel2,jlbLabel3,jlbLabel4;
	public PaymentUI() {
		jtfMainFrame = new JFrame("Call Payment");
		//jtfMainFrame.setSize(100, 500);
		jtfMainFrame.setPreferredSize(new Dimension(1000, 200));
		jplPanel = new JPanel();
		
		jbnButton1 = new JButton("ȷ��");
		jbnButton2 = new JButton("ȷ��");
		
		jtfInput1 = new JTextField(20);
		jtfInput2 = new JTextField(20);
		jtfInput3 = new JTextField(40);
		jtfoutput = new JTextField(20);

		jlbLabel1 = new JLabel("ͨ��ʱ��");
		jlbLabel2 = new JLabel("δ�ɷѴ���");
		jlbLabel3 = new JLabel("������");
		jlbLabel4 = new JLabel("�����ļ���");
		
		jbnButton1.setMnemonic(KeyEvent.VK_I); //Set ShortCut Keys
		final CallPayment callpayment=new CallPayment();

		jbnButton1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//String text1=jtfInput.getText();
				//String text2=jtfInput2.getText();
				if(jtfInput1.getText().isEmpty()||jtfInput2.getText().isEmpty())
				{
					jtfoutput.setText("�������벻������");
				}else{
					double call_minut=Double.parseDouble(jtfInput1.getText());
					int not_intime=Integer.parseInt(jtfInput2.getText());
					//System.out.println(callpayment.calculate_fee(call_minut, not_intime));
					System.out.println("d");
					if(callpayment.isValid(call_minut, not_intime))
					{
						jtfoutput.setText((callpayment.calculate_fee(call_minut, not_intime))+"");
					}else{
						jtfoutput.setText("�������벻�Ϸ���");
					}
				}
				
				
			}
		});
		jbnButton2.setMnemonic(KeyEvent.VK_I);
		jbnButton2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(jtfInput3.getText().isEmpty())
				{
					jtfoutput.setText("�������벻��������");
				}else{

					//System.out.println(callpayment.calculate_fee(call_minut, not_intime));
					String filepath="D:/workspace/Payment/src/excel/"+jtfInput3.getText();
					try {
						callpayment.calculate_fee(filepath);
					} catch (RowsExceededException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BiffException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (WriteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(filepath);
				}
			}
		});
		jplPanel.setLayout(new FlowLayout());
		jplPanel.add(jlbLabel1);
		jplPanel.add(jtfInput1);
		jplPanel.add(jlbLabel2);
		jplPanel.add(jtfInput2);
		jplPanel.add(jbnButton1);
		jplPanel.add(jlbLabel3);
		jplPanel.add(jtfoutput);
		jplPanel.add(jlbLabel4);
		jplPanel.add(jtfInput3);
		jplPanel.add(jbnButton2);

		jtfMainFrame.getContentPane().add(jplPanel, BorderLayout.CENTER);
		jtfMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jtfMainFrame.pack();
		jtfMainFrame.setVisible(true);
	}
//	public static void main(String[] args) {
//		// Set the look and feel to Java Swing Look
//		try {
//			UIManager.setLookAndFeel(UIManager
//					.getCrossPlatformLookAndFeelClassName());
//		} catch (Exception e) {
//		}
//		PaymentUI application = new PaymentUI();
//	}
}