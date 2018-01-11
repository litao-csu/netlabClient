package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Model.Collector;
import Model.Hardware_Type;

public class WindowsCollector implements Collector {

	public WindowsCollector() {
		// TODO Auto-generated constructor stub
	}

	public String getInfo(String cmd) throws IOException {
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

	public String getHardwareInfo(Hardware_Type ht) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCmd(Hardware_Type ht) {
		
		String cmd = "";
		
		switch(ht){
			
			case CPU:
				// TODO CPU cmd
				break;
			case Disk:
				// TODO Disk cmd
				break;
			case Mac:
				// TODO MAC cmd
				break;
			case Memory:
				// TODO Memery cmd
				
				break;
			default:
				break;
		}
		return cmd;
	}

}
