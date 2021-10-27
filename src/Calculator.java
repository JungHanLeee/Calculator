import java.awt.*;
import javax.swing.*;


public class Calculator extends JFrame{
	public Calculator() {
		setTitle("계산기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c=getContentPane();
		c.add(new SouthPanel(), BorderLayout.SOUTH);
		c.add(new CenterPanel(), BorderLayout.CENTER);
		c.add(new NorthPanel(), BorderLayout.NORTH);
		setSize(300,300);
		setVisible(true);
	}
	public static void main(String[] args) {
			new Calculator();
	}
}
class SouthPanel extends JPanel{
	public SouthPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(new JLabel("계산결과"));
		add(new JTextField(15));
	}
}
class CenterPanel extends JPanel{
	JButton[] b= {new JButton("+"),new JButton("-"),new JButton("*"),new JButton("/")};
	public CenterPanel() {
		setLayout(new GridLayout(4,4,5,5));
		for(int i=0;i<10;i++) {
			JButton b=new JButton(Integer.toString(i));
			add(b);
		}
		add(new JButton("CE"));
		add(new JButton("계산"));
		
		for(int i=0;i<b.length;i++) {
			add(b[i]);
		}
	}
}
class NorthPanel extends JPanel{
	public NorthPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(new JLabel("수식입력"));
		add(new JTextField(15));
	}
}









