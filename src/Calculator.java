import java.awt.event.ActionListener;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Calculator extends JFrame implements ActionListener{
	
	JLabel tf1,tf2;
	public Calculator() {
		setTitle("계산기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c=getContentPane();
		
		NorthPanel np=new NorthPanel();
		c.add(np, BorderLayout.NORTH);
		
		SouthPanel sp=new SouthPanel();
		c.add(sp,BorderLayout.SOUTH);
		
		CenterPanel cp=new CenterPanel();
		c.add(cp,BorderLayout.CENTER);

		setSize(300,300);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str=e.getActionCommand();
		
	}
	class NorthPanel extends JPanel{
		public NorthPanel() {
			setLayout(new FlowLayout(FlowLayout.LEFT));
			add(new JLabel("수식입력"));
			tf1=new JLabel("");
			add(tf1);
		}
	}
	class SouthPanel extends JPanel{
		public SouthPanel() {
			setLayout(new FlowLayout(FlowLayout.LEFT));
			add(new JLabel("계산결과"));
			tf2=new JLabel("");
			add(tf2);
		}
	}
	class CenterPanel extends JPanel{
		public CenterPanel() {
			JButton[] b= {new JButton("0"),new JButton("1"),
					new JButton("2"),new JButton("3"),new JButton("4"),
					new JButton("5"),new JButton("6"),new JButton("7"),
					new JButton("8"),new JButton("9"),new JButton("+"),
					new JButton("-"),new JButton("*"),new JButton("/"),
					new JButton("CE"),new JButton("계산")};
			setLayout(new GridLayout(4,4,5,5));
			for(int i=0;i<16;i++) {
				if(i<10) {
					add(b[i]);
					b[i].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JButton b=(JButton)e.getSource();
							String oldtext=tf1.getText();
							String text=b.getText();
							String newtext=oldtext+text;
							tf1.setText(newtext);
						}						
					});
				}
				else if(i==14||i==15) {
					add(b[14]);
					b[14].addActionListener(new ActionListener () {
						public void actionPerformed(ActionEvent e) {
							int n=tf1.getText().length()-1;
							if(n==0) {
								tf1.setText("");
							}
							else if(n>0&&n<=10) {
								tf1.setText(tf1.getText().substring(0,n));
							}
							else {
								tf1.setText(tf1.getText().substring(0, n));
							}
						}
					});
					add(b[15]);
					b[15].addActionListener(new CalcListener());
				}
				else if(i>=10&&i<=13) {
					add(b[i]);
					b[i].addActionListener(new MyListener());
				}	
			}			
		}
	}
	private class MyListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton b=(JButton)e.getSource();
			int n=tf1.getText().length();
			Character c=tf1.getText().charAt(n-1);
			if(c!='+'&&c!='-'&&c!='*'&&c!='/') {
				String oldtext=tf1.getText();
				String text=b.getText();
				String newtext=oldtext+text;
				tf1.setText(newtext);
			}
		}
	}
	public class CalcListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String s=tf1.getText();
			double result=Calculator(s);
			tf2.setText(Double.toString(result));
		}
	}
	public double Calculator(String ss) {
		int i; double ans;
		ArrayList<Double>v=new ArrayList<Double>();
		ArrayList<String>op=new ArrayList<String>();
		
		op.add(null);
		String str=new String("");
		for(i=0;i<ss.length();i++) {
			Character c=ss.charAt(i);
			String s=Character.toString(c);
			
			if(Character.isDigit(c)) {
				str+=Character.toString(c);
				if(i==ss.length()-1)
					v.add(Double.parseDouble(str));
			}
			else if(s.equals(",")) str+=s;
			else {
				v.add(Double.parseDouble(str));
				op.add(Character.toString(c));
				str="";
			}
		}
		for(i=1;i<v.size();i++) {
			String s=op.get(i);
			double tmp;
			if(s.equals("*")) {
				tmp=v.get(i-1)*v.get(i);
				op.remove(i);
				v.remove(i);
				v.remove(i-1);
				v.add(i-1,tmp);
				i--;
			}
			else if (s.equals("÷")) {
				tmp = v.get(i-1) / v.get(i);
				op.remove(i);
				v.remove(i);
				v.remove(i-1);		
				v.add(i-1, tmp);
				i--;
			}
		}
		
		ans = v.get(0);
		for(i = 1; i < v.size(); i++) {
			String s = op.get(i);
			double n = v.get(i);
			
			if(s.compareTo("+")==0) ans = ans + n;
			else if(s.compareTo("-")==0) ans = ans - n;
		}	
		
			return ans;
			
		}
		public static void main(String[] args) {
			new Calculator();
		}
}
		








