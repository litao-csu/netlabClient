package connection;
import java.io.IOException;
import java.util.Random;

public class Fun_invoke {
	private static String project_path = getPath.get_realPath();
	private static String conf_path = project_path+"\\zabbix\\conf\\zabbix_agentd.win.conf";
//	private static String startbat_path = project_path+"\\zabbix\\start.bat";
//	private static String stopbat_path = project_path+"\\zabbix\\stop.bat";
//	private static String registerbat_path = project_path+"\\zabbix\\register.bat";
    private static String serial_number = Get_serialNumber.getLocalSerialNumber();
    /*private static String start_order ="\""+project_path+"\""+"\\zabbix\\bin\\win32\\zabbix_agentd.exe -c "
    		+"\""+project_path+"\""+"\\zabbix\\conf\\zabbix_agentd.win.conf -s";
    private static String stop_order = "\""+project_path+"\""+"\\zabbix\\bin\\win32\\zabbix_agentd.exe -c "
    		+"\""+project_path+"\""+"\\zabbix\\conf\\zabbix_agentd.win.conf -x";
    private static String register_order = "\""+project_path+"\""+"\\zabbix\\bin\\win32\\zabbix_agentd.exe -c "
    		+"\""+project_path+"\""+"\\zabbix\\conf\\zabbix_agentd.win.conf -i";*/
    
 	public static void registerZabbix(String Get_info) throws IOException{
 		String[] register_info = Get_info.split("[*]");
 		String hostname = "Hostname="+register_info[1]+"_"+serial_number;
		boolean flag = judgeProcess.isRunning("zabbix_agentd.exe");
//		System.out.println(flag);
		if(flag)
		{
			//RegisterFile.register(hostname,conf_path);
//			String cmd1 = "cmd /k cd zabbix && start stop.bat ";
//			String cmd2 = "cmd /k cd zabbix && start start.bat ";
			//RegisterFile.register(stop_order,stopbat_path);
			//RegisterFile.register(start_order,startbat_path);
			//String cmd1 = "cmd /k start D://register.bat ";
			RegisterFile.register(hostname,conf_path);
			String cmd2 = "cmd /k cd zabbix && start /b start.bat";
			Runtime.getRuntime().exec(cmd2);
//			System.out.println(cmd2);
		}
		else{
			//RegisterFile.register(register_order,registerbat_path);
			//RegisterFile.register(start_order,startbat_path);
			RegisterFile.register(hostname,conf_path);
			//String cmd1 = "cmd /k start D://register.bat ";
			String cmd2 = "cmd /k cd zabbix && start /b register.bat";
			//Runtime.getRuntime().exec(cmd1);
			Runtime.getRuntime().exec(cmd2);
			//System.out.println(cmd1);
//			System.out.println(cmd2);
		}			
		System.out.println("zabbix注册成功");
 	}
 
	public static void registerBoinc(String Get_info) throws IOException
	{
		String[] register_info = Get_info.split("[*]");
		String useremail = serial_number+"@csu.edu.cn";
		String userId = register_info[1];
		String userPwd = register_info[4];
		//String useremail = register_info[0]+"@csu.edu.cn";
		//String userId = register_info[0];
		//String userPwd = register_info[1];
		//调用Boinc注册接口
		//String cmd = "cmd /k e: && cd e:\\tmp && start "+"E:\\boincAPI\\StartBoinc.exe "+useremail+" "+userPwd+" "+userId+" register";
		
		System.out.println(System.getProperty("user.dir"));
		String cmd = "cmd /k cd BOINC && start /b start.exe "+useremail+" "+userPwd+" "+userId+" register";
		
//		System.out.println(cmd);
  	    Runtime r=Runtime.getRuntime();
  	    Process proc = r.exec(cmd);
  	    
 		System.out.println(proc.toString());
		System.out.println("Boinc注册成功");
	}
	//获取10位随机密码
    public static String generateString() 
    {
    	String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 10; i++) 
        {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }        
        return sb.toString();
    }
    public static void login_Invoke(String ret_info) throws IOException
    {
    	String[] userinfo = ret_info.split("[*]");			
		String userId = userinfo[0];
		String userPwd = userinfo[1];
		String useremail = serial_number + "@csu.edu.cn";
//		String username = userinfo[3];
		String ruleInvoke = userinfo[19];
		String device_rank = userinfo[20];		
		int rule = Integer.parseInt(ruleInvoke);
//		System.out.println(userId+" "+userPwd+" "+useremail+" "+ruleInvoke);
 	   	int Spark_Rule = 1;
 	   	int Zabbix_Rule = 2;
 	   	int Boinc_Rule = 4;
 	   	//通过按位与运算判断模块是否需要登录
 	   	if((rule&Zabbix_Rule) == Zabbix_Rule)
 	   	{
 	   
	 	   	boolean flag = judgeProcess.isRunning("zabbix_agentd.exe");
//			System.out.println(flag);
			System.out.println(project_path);
			String hostname = "Hostname="+userId+"_"+serial_number;
			if(!flag){
				//RegisterFile.register(register_order,registerbat_path);
				//RegisterFile.register(start_order,startbat_path);
				RegisterFile.register(hostname,conf_path);
				String cmd2 = "cmd /k cd zabbix && start /b register.bat";
				Runtime.getRuntime().exec(cmd2);
//				System.out.println(cmd2);
			}			
				System.out.println("zabbix启动成功");
 		}
 	   	if((rule&Boinc_Rule) == Boinc_Rule)
 	   	{
 	   		//调用Boinc登陆接口
 	   		String cmd = "cmd /k cd BOINC && start /b start.exe " +useremail + " " +userPwd;
 	   		//String cmd = "cmd /k e: && cd e:\\tmp && start /b "+"E:\\boincAPI\\StartBoinc.exe " +userId + " " +userPwd;
// 	   		System.out.println(cmd);
 	   		Runtime r=Runtime.getRuntime();
 	   		Process proc = r.exec(cmd);
 	   	   // Process proc = r.exec("cmd /k cd > e:\\a.txt");
 	   		System.out.println(proc.toString());
 		}
   	
 	   if((rule&Spark_Rule) == Spark_Rule && device_rank.equals("master"))
	   	{
 		   System.out.println("调用Spark登录");
 		 // 	UserInfo ui = UserInfo.getInstance();
 		 // 	ui.setUI(userId, userPwd); 	  
 		   
 		    String cmd = "cmd /k cd Spark && start /b Spark.exe " + userId + " " + "zndxjsjl";
	   		
//	   		System.out.println(cmd);
	   		Runtime r=Runtime.getRuntime();
	   		Process proc = r.exec(cmd);
	   	    
	   		System.out.println(proc.toString());
	   	}	   	 
    }      
}
