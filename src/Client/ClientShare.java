package Client;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

import javax.swing.*; 

import Protocol.*;
import Tools.*;

public class ClientShare extends JFrame{
	//static FileTransferClient client;
	Tool tool=new Tool();
	Protocol pro=new Protocol();
	Des des=new Des();
	Container co;
	JFrame f;
	JLabel label_ul = new JLabel("-------�ļ��ϴ�---------------------------------------------------------------");
	JLabel label_dl = new JLabel("-------�ļ�����---------------------------------------------------------------");
	JButton button_choose1 = new JButton("ѡ���ļ�");  
	JButton button_get = new JButton("��ȡĿ¼");  
	JButton button_choose2 = new JButton("ѡ���ļ���");  
	JButton button_ul = new JButton("�ϴ�"); 
	JButton button_exit=new JButton("�˳�");
	JButton button_dl = new JButton("����");  
	JTextField text_rode1 = new JTextField("");
	JTextField text_rode2 = new JTextField("");
	String title[] = new String[200];
	JComboBox choose_title = new JComboBox();
	public String KEY_C_V="";
	
	
	Dialog d1 = new Dialog(f, "��ʾ", true);
	Dialog d2 = new Dialog(f, "��ʾ", true);
	Dialog d3 = new Dialog(f, "��ʾ", true);
	
	Socket s;
	
	public ClientShare(Socket s,String K){
		this.s = s;
		f = this;
		this.KEY_C_V = K;
		co = this.getContentPane();
    	co.setLayout(null);
    	
    	this.setTitle("�ļ�����ר��");
    	this.setLocation(300, 100);
    	this.setSize(600, 500);
    	
    	label_ul.setBounds(5, 50, 1000, 20);
        label_ul.setFont(new Font("Serif", Font.PLAIN, 20));
        label_dl.setBounds(5, 200, 1000, 20);
        label_dl.setFont(new Font("Serif", Font.PLAIN, 20));
        button_choose1.setBounds(50, 120, 100, 30);
        button_choose1.setFont(new Font("Serif", Font.PLAIN, 15));
        button_get.setBounds(50, 270, 100, 30);
        button_get.setFont(new Font("Serif", Font.PLAIN, 15));
        button_choose2.setBounds(50, 350, 120, 30);
        button_choose2.setFont(new Font("Serif", Font.PLAIN, 15));
        button_exit.setBounds(460, 400, 80, 30);
        button_exit.setFont(new Font("Serif", Font.PLAIN, 15));
        button_ul.setBounds(460, 120, 80, 30);
        button_ul.setFont(new Font("Serif", Font.PLAIN, 15));
        button_dl.setBounds(460, 350, 80, 30);
        button_dl.setFont(new Font("Serif", Font.PLAIN, 15));
        text_rode1.setBounds(170, 120, 260, 30);
        text_rode2.setBounds(190, 350, 240, 30);
        choose_title.setBounds(170, 270, 300, 30);
        choose_title.setMaximumRowCount(20);
        choose_title.setFont(new Font("Serif", Font.PLAIN, 15));
        
        //choose_title.addItemListener(new lbHandler());
        
        //��ʾ��
        d1.setLayout(null);
        d2.setLayout(null);
        d3.setLayout(null);
        d1.setBounds(400, 200, 400, 300);//���õ����Ի����λ�úʹ�С
        d2.setBounds(400, 200, 400, 300);//���õ����Ի����λ�úʹ�С
        d3.setBounds(400, 200, 400, 300);//���õ����Ի����λ�úʹ�С
        JLabel lab1 = new JLabel("�ϴ��ɹ���");//����lab��ǩ��д��ʾ����
        lab1.setBounds(140, 100, 200, 40);
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        JLabel lab2 = new JLabel("���سɹ���");//����lab��ǩ��д��ʾ����
        lab2.setBounds(140, 100, 200, 40);
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        JLabel lab3 = new JLabel("��ѡ���ļ���");//����lab��ǩ��д��ʾ����
        lab3.setBounds(140, 100, 200, 40);
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        JButton okBut1 = new JButton("ȷ��");//����ȷ����ť
        JButton okBut2 = new JButton("ȷ��");//����ȷ����ť
        JButton okBut3 = new JButton("ȷ��");//����ȷ����ť
        okBut1.setBounds(150, 170, 80, 30);
        okBut2.setBounds(150, 170, 80, 30);
        okBut3.setBounds(150, 170, 80, 30);
        d1.add(lab1);//����ǩ��ӵ������ĶԻ�����
        d1.add(okBut1);//��ȷ����ť��ӵ������ĶԻ�����
        d2.add(lab2);
        d2.add(okBut2);
        d3.add(lab3);
        d3.add(okBut3);

        
        co.add(label_ul);
        co.add(label_dl);
        co.add(button_choose1);
        co.add(button_choose2);
        co.add(button_get);
        co.add(button_ul);
        co.add(button_exit);
        co.add(button_dl);
        co.add(text_rode1);
        co.add(text_rode2);
        co.add(choose_title);
        
        text_rode2.setText("C:\\Users\\Public\\Documents");
        
        
        //�ϴ�ʱ���ļ�·��
        button_choose1.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
        		JFileChooser jfc=new JFileChooser();  
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
                jfc.showDialog(new JLabel(), "ѡ��");  
                File file=jfc.getSelectedFile();  
                if(file!=null){
                if(file.isDirectory()){  
                }else if(file.isFile()){  
                    text_rode1.setText(file.getAbsolutePath());
                    
                }  
                }
			}
        });
        
        button_exit.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
        		try {
        			String temp="exit";
        			temp=des.Encrypt(temp, KEY_C_V);
					SendMessage("0011101111"+temp);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		f.dispose();
			}
        });
        
    	button_get.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
    			try {
    				choose_title.removeAllItems();;
    				sed();
    			} catch (UnsupportedEncodingException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
            
        
        //����ʱ�ı����ļ�·��
        button_choose2.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
        		JFileChooser jfc=new JFileChooser();  
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
                jfc.showDialog(new JLabel(), "ѡ��");  
                File file=jfc.getSelectedFile();
                if(file!=null){
                	if(file.isDirectory()){
                		text_rode2.setText(file.getAbsolutePath());
                	}else if(file.isFile()){  
                	}  
                }
			}
        });
        
        button_ul.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		String ulrode = text_rode1.getText();
        		int n=ulrode.length();
        		//System.out.println(n);
        		if(n!=0){
            		try {
    					sendFile(ulrode);
    				} catch (Exception e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
            		System.out.println("�û��ϴ��ļ���·����"+ ulrode);
            		d1.setVisible(true);
        		}
        		else{
        			d3.setVisible(true);
        			System.out.println("δѡ���ļ�");
        		}
        	}
        		
        });
        
        okBut1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                d1.setVisible(false);
            }
        });
        okBut2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                d2.setVisible(false);
            }
        });
        okBut3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                d3.setVisible(false);
            }
        });

        button_dl.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
    				// �ļ����ͳ��� 
        		
        		if(choose_title.getSelectedItem()==null)
        		{
        			System.out.println("null");
        			d3.setVisible(true);
        			return ;
        		}
//        		String selected_title = choose_title.getSelectedItem().toString();
//        		System.out.println(selected_title);
//        		int n = selected_title.length();
//        		//System.out.println(n);
//        		if(n!=0){
					try {
						sed1();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
    		       //System.out.println("======== �ļ����ճɹ� [File Name��"); 
    		        d2.setVisible(true);
        		}
//        		else{
//        			d3.setVisible(true);
//        			System.out.println("δѡ���ļ�");
//        		}
        	//}
        });
        
        
        
        
        
        this.setVisible(true); 
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void sed()throws Exception{
		String temp=des.Encrypt("req", KEY_C_V);
		
		temp="0011100010"+temp;
		SendMessage(temp);
		//System.out.println(temp);
		String mess="";
		int j=0;
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream(), "utf-8"));
	  while((mess = br.readLine())!=null) {
			  if(mess.equals("end"))
				  break;
			  mess=des.Decrypt(mess, KEY_C_V);
			 // System.out.println(mess);
			  if(mess!=null)
			  mess=tool.MoveSpace(mess);
			  System.out.println(mess);
			  title[j]=mess;
			 // System.out.println("ds");
			  j++;
		  }
		for(int i=0;i<j;i++){  
			choose_title.addItem(title[i]);
		}
	} 
	
	public void sed1()throws Exception{
		String fileName;
		String selected_title = choose_title.getSelectedItem().toString();
		System.out.println("�û�����de�ļ���"+ selected_title);
		selected_title = des.Encrypt(selected_title, KEY_C_V);
		//String temp="req"+selected_title;
	//	System.out.println(temp);
		
		
		//DataInputStream dis = new DataInputStream(client.getInputStream()); 
		SendMessage("0011100011"+selected_title);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream(), "utf-8"));
	
		//fileName=br.readLine();
		String dl_rode = text_rode2.getText();
		System.out.println("�û������ļ���·����"+ dl_rode);
		File directory = new File(dl_rode); 
		if(!directory.exists()) { 
		  directory.mkdir(); 
		} 
		File file = new File(directory.getAbsolutePath() + File.separatorChar + des.Decrypt(selected_title, KEY_C_V)); 
		FileOutputStream fos = new FileOutputStream(file); 

				  
		// ��ʼ�����ļ� 
		byte[] bytes = new byte[1024]; 
		String mess="";
		while((mess = br.readLine()) != null) {
			if(mess.equals("end")){
				break;
			}
			mess=des.Decrypt(mess, KEY_C_V);
			 // System.out.println(mess);
			  if(mess!=null)
			  mess=tool.MoveSpace(mess);
				  bytes = mess.getBytes();
				  fos.write(bytes, 0, bytes.length); 
				  fos.flush(); 
		}
	}
	
	
	
	  /** 
	   * �����˴����ļ� 
	   * @throws Exception 
	   */
		public void sendFile(String FilePathName) throws Exception { 
			FileInputStream fis=null; 
			try { 
				File file = new File(FilePathName); 
				if(file.exists()) { 
					fis = new FileInputStream(file); 
					
					// �ļ����ͳ��� 
					
					PrintWriter writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "utf-8"));
					String filename=file.getName();
					
					filename=des.Encrypt(filename, KEY_C_V);
					printMessage(writer,"0011100000"+filename);
					System.out.println(filename);
	    	  // ��ʼ�����ļ� 
	    	  System.out.println("======== ��ʼ�����ļ� ========"); 
	    	  byte[] bytes = new byte[1024]; 
	    	  int length = 0; 
	    	  long progress = 0; 
	    	  String temp="";
	    	  while((length = fis.read(bytes, 0, bytes.length)) != -1) { 
	    		  temp=new String(bytes);
	    		  temp=des.Encrypt(temp, KEY_C_V);
	    		  printMessage(writer,"0011100001"+temp);
	    	  } 
	    	  printMessage(writer,"end");
	    	  fis.close();
	    	  System.out.println(); 
	    	  System.out.println("======== �ļ�����ɹ� ========"); 
	      	} 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
		} 
		public void SendMessage(String message) throws UnsupportedEncodingException, IOException{
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "utf-8"));
			printMessage(writer,message);
		}
	 /* public static void main(String[] args) { 
		  Client_Docu_UI c = new Client_Docu_UI();
		  try { 
			  FileTransferClient client = new FileTransferClient(); // �����ͻ������� 
			  client.sendFile("C:/Users/acer0900/Pictures/lovewallpaper/1.jpg"); // �����ļ� 
		  } catch (Exception e) { 
			  e.printStackTrace(); 
		  } 
	  	} */
		public void printMessage(PrintWriter pw, String message) {
			pw.println(message);
			pw.flush();
		}
	
	
}

