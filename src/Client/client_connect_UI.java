package Client;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class client_connect_UI extends JFrame{
	private JLabel label1;
	private JFrame frame;
	private JTextField text1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private String ID_C = "C001";
	
	
	public client_connect_UI() {
		frame = this;
		frame.setTitle("客户机认证");
		frame.setLocation(400, 150);
		frame.setSize(500, 400);
		
		Container c=frame.getContentPane();
				
		c.setLayout(null);
		label1 = new JLabel("ID");
		
		label1.setFont(new Font("",0,25));
		label1.setBounds(78, 80, 60, 20);
		
		text1 = new JTextField(ID_C);
		text1.setBounds(150, 80, 250, 35);
		text1.setFont(new Font("",0,20));
		text1.setEditable(false);
		text1.setFocusable(false);
		
		button2 = new JButton("服务器1");
		button2.setBounds(80, 150, 100, 40);
		button2.setFont(new Font("",0,18));
		
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				MyClient my = new MyClient(frame,ID_C,"V001");
				my.newS(my);
			}
		});
		
		button3 = new JButton("服务器2");
		button3.setBounds(200, 150, 100, 40);
		button3.setFont(new Font("",0,18));
		
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				MyClient my = new MyClient(frame,text1.getText(),"V002");
				my.newS(my);
			}
		});
		
		button4 = new JButton("服务器3");
		button4.setBounds(320, 150, 100, 40);
		button4.setFont(new Font("",0,18));
		
		button4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				MyClient my = new MyClient(frame,text1.getText(),"V003");
				my.newS(my);
			}
		});
		c.add(label1);
		c.add(text1);
		c.add(button2);
		c.add(button3);
		c.add(button4);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	
	public static void main(String[] args) {
		
		new client_connect_UI();
		
	}

}
