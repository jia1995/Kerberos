package Server;


import java.io.*;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.swing.JTextArea;
import Protocol.Protocol;
import Tools.Des;
import Tools.Tool;

public class ServerChatVerify{
	
	//ȫ�ֱ���
	public Protocol pro = new Protocol();
	public Tool tool=new Tool();
	public String ID_V_1="";//you
	public String AD_C_1="";//you
	public String ID_C_1="";//you
	public String Prelude = "";//you
	public String ID_V = "";//you
	public String Authenticator_C = "";//you
	public String Ticket_V = "";//you
	public String KEY_C_V = "";//you
	public String KEY_V = "00000101";//you
	public String ID_C = "";//you
	public String AD_C = "";//you
	public String TS_3 = "";//you
	public String TS_4 = "";//you
	public String TS_5 = "";//you
	public String LifeTime_4 = "2359";//you
	public String ERROR=null;
	//���嵱ǰ�߳��������Socket
	Socket s = null;
	//ĳ�̴߳����Socket��Ӧ��������
	BufferedReader br = null;
	BufferedWriter bw = null;
	//�����˿ڣ������յ��̱߳�����������
	//������ÿ���߳�ͨ�ŵ��߳���
	JTextArea text = null;
	
	public ServerChatVerify(JTextArea text){//���캯��
		this.text = text;
	}

	public void PullPack(String message){//���
		LinkedHashMap<String, Integer> map = pro.Unpack(message);
		//System.out.println("��Client���յ���Message���ȣ�"+message.length());
	//	System.out.println("message  "+message);
		for(Entry<String, Integer> entry : map.entrySet()){
			if(entry.getKey() == "Prelude"){
				Prelude = message.substring(0, entry.getValue());
				//System.out.println("Prelude  "+Prelude);
				message = message.substring(entry.getValue());
			}
			if(entry.getKey() == "Ticket_V"){
				Ticket_V = message.substring(0, entry.getValue());
				//System.out.println("Ticket_V  "+Ticket_V);
				message = message.substring(entry.getValue());
			}
			if(entry.getKey() == "Authenticator_C"){
				Authenticator_C = message;
				//System.out.println("Authenticator_C  "+Authenticator_C);
			}
		}
	}
	
	public String Analyse_Ticket_v(String message){//����Ticket_V
		LinkedHashMap<String, Integer> map = pro.TICKET_V();
		String Message="";
		for(Entry<String, Integer> entry : map.entrySet()){
			if(entry.getKey() == "KEY_C_V"){
				KEY_C_V = message.substring(0, entry.getValue());
				//System.out.println("KEY_C_V"+KEY_C_V);
				message = message.substring(entry.getValue());
			}
			if(entry.getKey() == "ID_C"){
				ID_C_1 = message.substring(0, entry.getValue());
				//System.out.println("ID_C_1"+ID_C_1);
				message = message.substring(entry.getValue());
			}
			if(entry.getKey() == "AD_C"){
				AD_C_1 = message.substring(0, entry.getValue());
				//System.out.println("AD_C_1"+AD_C_1);
				message = message.substring(entry.getValue());
			}
			if(entry.getKey() == "ID_V"){
				ID_V_1 = message.substring(0, entry.getValue());
				//System.out.println("ID_V_1"+ID_V_1);
				message = message.substring(entry.getValue());
			}
			if(entry.getKey() == "TS_4"){
				TS_4 = message.substring(0, entry.getValue());
				//System.out.println("TS_2"+TS_4);
				message = message.substring(entry.getValue());
			}
			if(entry.getKey() == "LifeTime_4"){
				LifeTime_4 = message.substring(0, entry.getValue());
				//System.out.println("LifeTime_4"+LifeTime_4);
			}
		}
		Analyse_Authenticator_C();
		if(Verify()){
			//System.out.println("TS_5:   "+TS_5);
			int num = Integer.parseInt(TS_5)+1;
			String ReturnMessage="0"+num;
			//System.out.println("����ǰMessage  "+ReturnMessage);
			Des des=new Des();
			Message=des.Encrypt(ReturnMessage, KEY_C_V);
			//System.out.println("���ܺ�Message  "+Message);
			Message="1100000000"+Message;
		}
		else{
			Des des=new Des();
			Message=des.Encrypt(ERROR, KEY_C_V);
			Message="1100010000"+Message;
		}
		return Message;
	}
	
	public void Analyse_Authenticator_C(){//����Authenticator_C
		Des des=new Des();
		String message=des.Decrypt(Authenticator_C, KEY_C_V);
		//System.out.println(Authenticator_C+"     KCV      "+KEY_C_V);
		//System.out.println(message);
		LinkedHashMap<String, Integer> map = pro.AUTHENTICATOR_C_V();
		for(Entry<String, Integer> entry : map.entrySet()){
			if(entry.getKey() == "ID_C"){
				ID_C = message.substring(0, entry.getValue());
				//System.out.println("ID_C"+ID_C);
				message = message.substring(entry.getValue());
			}
			if(entry.getKey() == "AD_C"){
				AD_C = message.substring(0, entry.getValue());
				//System.out.println("AD_C"+AD_C);
				message = message.substring(entry.getValue());
			}
			if(entry.getKey() == "TS_5"){
				TS_5 = message.substring(0, entry.getValue());
				//System.out.println("TS_5"+TS_5);
			}
		}
	}

	public boolean Verify(){//��֤
		if(ID_C.equals(ID_C_1)){
			if(AD_C.equals(AD_C_1)){
				//text.append(ID_C+"��֤�ɹ���\r\n");
				if(tool.comT(TS_4, tool.getTime(), LifeTime_4)){
					//�����ӡ�յ�C001
					System.out.println("���յ�"+ID_C+"��������Ϣ��ʱ���Ϊ��"+TS_5);
					return true;
				}
				else{
					ERROR="Ticket_V Time Out!";
					System.out.println("Ticket_V���ڣ�");
					return false;
				}
			}
			else{
				System.out.println("ADc��ͬ��֤ʧ�ܣ�");
				return false;
			}
		}
		else{
			System.out.println("Client ID ��ͬ��֤ʧ�ܣ�");
			return false;
		}
	}
	
	public String Decrypt_Ticket_V(){//����Ticket_V
		Des des=new Des();
		String temp=des.Decrypt(Ticket_V, KEY_V);
	//	System.out.println("TICKET_v����"+Ticket_V+"  "+KEY_V);
		return temp;
	}
	
}

