package TGS;

import java.io.IOException;
import java.net.*;
import java.sql.*;
import java.util.*;

/*  ʵ��TGS�˵Ķ��߳�ͨ��
 * 
 * 
 */
		public class TGSServer{
			public static List<Socket> socketlist = Collections.synchronizedList(new ArrayList<Socket>());
			public static void main(String[] args) 
				throws IOException{  
					TGSSurface d=new TGSSurface();
					Connection conn = ConnectMysql(d);
				@SuppressWarnings("resource")
				ServerSocket ss = new ServerSocket(45679);
				while(true){
					Socket s = ss.accept();
					socketlist.add(s);
					new Thread(new TGSServerThread(s,d,conn)).start();
					
				}		
		}
		
		public static Connection ConnectMysql(TGSSurface d){
		    String url = "jdbc:mysql://127.0.0.1/TGS";  
		    String name = "com.mysql.jdbc.Driver";  
		    String user = "Mar";  
		    String password = "6260";
		    Connection conn = null;  
		    try {  
	            Class.forName(name);//ָ����������  
	            conn = DriverManager.getConnection(url, user, password);//��ȡ����  
	            d.demo("���ݿ����ӳɹ�");
	           
	           // String sql = "select *from TGS";//SQL���  
	           // pst = conn.prepareStatement(sql);//׼��ִ����� 
	            
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }
			return conn;  
		}

	}
