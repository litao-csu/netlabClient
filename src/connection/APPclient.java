package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Model.UserInfo;
public class APPclient {
			
//	static int PORT = 12345;
	static int PORT = UserInfo.get_PORT();
	static String IP_ADDR = UserInfo.get_IP_ADDR();
	//static String IP_ADDR = "192.168.1.110";
	static Socket socket = null;
	
	public static String Get_ret_info(String input_info) 
	{ 		
//		System.out.println("client run...");          	
    	String ret_info = new String();
		try
		{
        	socket = new Socket(IP_ADDR, PORT);  	              
            DataInputStream input = new DataInputStream(socket.getInputStream());  
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());    	            
            out.writeUTF(input_info);
    		out.flush();	        
    		ret_info = input.readUTF();//读取服务器通过硬盘序列号查询数据库返回的信息
//            	System.out.println("服务器返回来的信息: " + ret_info);	           			            
        	out.close();
        	input.close();
		}
		catch (Exception e) {
    		System.out.println("客户端异常:" + e.getMessage());
    	} 
		
		finally {
    		if (socket != null) {
    			try {
					socket.close();
				} catch (IOException e) {
					socket = null; 
					System.out.println("客户端 finally异常:" + e.getMessage()); 
				}
    		}
    	}
		return ret_info;
    }	
	public static void Get_register_info(String userId,String userName,String userLab,String degree,String group,
			String grade,String major,String tutor,String advisor,String telephone,String qq,String email,String contact,
			String relationship,String contactPhone,String research,String degreeType) throws IOException
	{
		String serialNumber = Get_serialNumber.getLocalSerialNumber();
//    	String passWord = Fun_invoke.generateString();
		String passWord = "zndxjsjl";
		String [] cominfo = com_info.get_cominfo();
		String cpu_type = cominfo[1];
		String disk_Totalnumber = cominfo[2];
    	String mem_Totalnumber = cominfo[3];
    	
    	
    	String info = "register"+"*"+userId+"*"+userName+"*"+userLab+"*"+passWord+"*"+serialNumber+"*"+mem_Totalnumber+"*"
	              	   +disk_Totalnumber+"*"+cpu_type+"*"+degree+"*"+group+"*"+grade+"*"+major+"*"+tutor+"*"+advisor+"*"
	              	   +telephone+"*"+qq+"*"+email+"*"+contact+"*"+relationship+"*"+contactPhone+"*"+research+"*"+degreeType;    	
//    	System.out.println(info+"\n");
    	String ret = Get_ret_info(info);
//    	System.out.println(ret);
    	Fun_invoke.registerBoinc(ret); 					//获取注册信息，注册boinc
    	Fun_invoke.registerZabbix(ret);         	//注册zabbix
    	 
    }
	
	public static void Get_update_info(String userId,String lab,String degree,String group,String grade,String major,
			String tutor,String advisor,String telephone,String qq,String email,String contact,String relationship,
			String contactPhone,String research,String degreeType)throws IOException
	{		
		String ret = Get_ret_info("updateinfo"+"*"+userId+"*"+lab+"*"+degree+"*"+group+"*"+grade+"*"+major+"*"+tutor
				+"*"+advisor+"*"+telephone+"*"+qq+"*"+email+"*"+contact+"*"+relationship+"*"+contactPhone+"*"+research+"*"+degreeType);
		System.out.println(ret);
	}
	
	//从后台获取用户所在建筑、实验室和所在项目组等信息
	public static String getInitInfo(){
		
//		0,1,;计算机楼,升华后楼,;计算机楼老师,升华后楼老师,;老师,参数组,;2016,2017,
		String totalString = Get_ret_info("initinfo" + "*");
		
		return totalString;
	}
	
}   