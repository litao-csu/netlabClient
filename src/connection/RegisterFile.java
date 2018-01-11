package connection;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author litao
 * 在专网通生成的目录下建一个register.txt文件，并向其写入需要注册的信息
 */
public class RegisterFile {
	public static void register(String hostname,String Path) {  
	    //String directory="C:/zabbix/conf/zabbix_agentd.win.conf";    
	    try {   
			BufferedWriter out = new BufferedWriter(new FileWriter(Path, true));
			out.newLine();
        	out.write(hostname);      
        	out.close();	    
	    }catch (IOException e) {  
	        e.printStackTrace();  
	    }
	}
}
