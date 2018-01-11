package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Model.Collector;
import Model.Hardware_Type;

public class LinuxInfoCollector implements Collector {

	public LinuxInfoCollector() {
		
	}
	
	public String getInfo(String cmd) throws IOException{
		// get information through Terminal/CMD
		Process process = Runtime.getRuntime().exec(cmd);
		BufferedReader br = null;  
		br = new BufferedReader(new InputStreamReader(process.getInputStream()));  
		String line = null;  
		StringBuilder sb = new StringBuilder();  
		while ((line = br.readLine()) != null) {  
			sb.append(line + "\n");  
		}
		return sb.toString();
	}
	
	public String getHardwareInfo(Hardware_Type ht){
		
		String info = null;
		
		try{
			info = getInfo(getCmd(ht));
			
			//TODO handle information get exact part like name/type of cpu, memory or disk capacity etc.
			
		}catch(IOException ioe){
			
			ioe.printStackTrace();
			// TODO and other operations (optional)
		}
		return info;
	}

	
	public String getCmd(Hardware_Type ht) {
		
		String cmd = "";
		
		switch(ht){
			
			case CPU:
				// TODO CPU cmd, an example (probably right, but not sure) is shown as follows,
				cmd = "cat /proc/cpuinfo | grep model\\ name";
				break;
			case Disk:
				// TODO Disk cmd, an example (probably right, but not sure) is shown as follows,
				cmd = "df -m | grep /dev/sd";
				break;
			case Mac:
				// TODO MAC cmd, an example (probably right, but not sure) is shown as follows,
				cmd = "ifconfig | grep HWaddr";
				break;
			case Memory:
				// TODO Memery cmd, an example (probably right, but not sure) is shown as follows,
				cmd = "free -m | grep Mem";
				
				break;
			default:
				break;
		}
		return cmd;
	}
}
