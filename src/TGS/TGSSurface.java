package TGS;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*;

@SuppressWarnings("serial")
public class TGSSurface extends JFrame{
		Container co;
		JButton Clear_button = new JButton("���");      
		JTextArea Content_text = new JTextArea(); 
		JScrollPane jsp = new JScrollPane(Content_text);
		JLabel label = new JLabel("TGS��Ϣ�б�");
        
	    public void demo(String str){
	    	
	    	char[] time = getTime().toCharArray();

	        Content_text.append(time[1]+"��"+time[2]+time[3]+"��"+time[4]+time[5]+"ʱ"+time[6]+time[7]+"��"+time[8]+time[9]+"��"+" : "+str+"\r\n"); 
	    }
	    public TGSSurface(){
	    	co=this.getContentPane();
	    	co.setLayout(null); 
	    	
	    	this.setTitle("TGS������");
	        this.setLocation(300, 100);  //����λ��
	        this.setSize(600, 500);  //���ڴ�С
	        
	        Content_text.setFont(new Font("Serif", Font.PLAIN, 16));

	        Clear_button.setBounds(220, 400, 130, 40);
	        Clear_button.setFont(new Font("Serif", Font.PLAIN, 25));
	        
	        jsp.setBounds(40, 60, 500, 320);
	        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
	        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);   
	        label.setBounds(220, 10, 200, 40);
	        label.setFont(new Font("Serif", Font.PLAIN, 25));
	        co.add(jsp);
	        co.add(label);
	        //co.add(Content_text);
	        co.add(Clear_button);
	        
	         
	        //��ť��Ӧ
	        Clear_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Content_text.setText(null);   //����ı���
				}
			});  
	        
	        
	        Content_text.setEditable(false);//���ɱ༭
	        
	        this.setVisible(true); 
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	    
	    public static String getTime(){
			Calendar c = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸� 
			int month = c.get(Calendar.MONTH)+1; 
			int date = c.get(Calendar.DATE); 
			int hour = c.get(Calendar.HOUR_OF_DAY); 
			int minute = c.get(Calendar.MINUTE); 
			int second = c.get(Calendar.SECOND); 
			String TS = "";
			if(month < 10)
				TS+= 0;
			TS += Integer.toString(month);
			if(date < 10)
				TS+= 0;
			TS+=Integer.toString(date);
			if(hour < 10)
				TS+= 0;
			TS+=Integer.toString(hour);
			if(minute < 10)
				TS+= 0;
			TS+=Integer.toString(minute);
			if(second < 10)
				TS+= 0;
			TS+=Integer.toString(second);
			return TS;
		}
	    
	    public static void main(String args[]){	
	    	TGSSurface d = new TGSSurface();
	    	d.demo("21342rf3");
	    }  
}
