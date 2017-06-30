package TGS;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import TGS.Demo;
/*  实现TGS端的多线程通信
 * 
 * 
 */
	public class MyTgs{
		public static List<Socket> socketlist = Collections.synchronizedList(new ArrayList<Socket>());
		public static void main(String[] args) 
				throws IOException{  
					Demo d=new Demo();
					Connection conn = ConnectMysql(d);
					
				ServerSocket ss = new ServerSocket(45679);
				while(true){
					Socket s = ss.accept();
					socketlist.add(s);
					new Thread(new TGSThread(s,d,conn)).start();
					
				}		
		}
		
		public static Connection ConnectMysql(Demo d){
		    String url = "jdbc:mysql://127.0.0.1/TGS";  
		    String name = "com.mysql.jdbc.Driver";  
		    String user = "Mar";  
		    String password = "6260";
		    Connection conn = null;  
		    try {  
	            Class.forName(name);//指定连接类型  
	            conn = DriverManager.getConnection(url, user, password);//获取连接  
	            d.demo("数据库连接成功");
	            
	            
	           // String sql = "select *from TGS";//SQL语句  
	           // pst = conn.prepareStatement(sql);//准备执行语句 
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }
			return conn;  
		}

	}
