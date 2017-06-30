package AS;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import AS.ServerThread;

/*  实现AS端的多线程通信
 * 
 * 陈培璐
 */
	public class MyAS{
		public static List<Object> socketlist = Collections.synchronizedList(new ArrayList<>());
		
		public static Connection DB_connect() throws ClassNotFoundException, SQLException{
			Connection connection;
			//驱动程序名
			String driver = "com.mysql.jdbc.Driver";
			//URL指向要访问的数据库名
			String url = "jdbc:mysql://localhost:3306/as_server";
			String user = "chen";
			String password = "1234";
			Class.forName(driver);	
			connection = DriverManager.getConnection(url,user,password);
			if(!connection.isClosed()){				
				System.out.println("Succeeded connecting to the Database");
			}
			else{
				
				System.out.println("connection failed");
			}
			return connection;
		}	

		
		public static void main(String[] args) 
				throws IOException, ClassNotFoundException, SQLException{  
					Demo d = new Demo();
					Connection c = DB_connect();
					if(c==null){
						System.out.println("SQLERROR");
						
					}
					ServerSocket ss = new ServerSocket(45678);
					
				while(true){
					Socket s = ss.accept();
					socketlist.add(s);					
					new Thread(new ServerThread(s,d,c)).start();
				}		
		}
	}
