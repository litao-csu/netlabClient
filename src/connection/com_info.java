package connection;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class com_info {
	protected static final String KEY = "Windows";
	static String[] temp = {"","","","",""};
	public static String[] get_cominfo() {		
		  try {
	            Process process = Runtime.getRuntime().exec("./Spark/getSysInfo.exe");
	            InputStreamReader ir = new InputStreamReader(process.getInputStream());
	            LineNumberReader input = new LineNumberReader(ir);
	            String line;
	            while ((line = input.readLine()) != null){
	            	System.out.println(line);
	            	if(line.contains(KEY)){
		            		temp = line.split("\\*\\*");
//		            		temp[4] = "Windows";
	            		}	            	
	            }
	            for(int i = 0;i < temp.length;++i)
	            {
	            	System.out.println(temp[i]);
	            }
      } catch (java.io.IOException e) {
          System.err.println("IOException " + e.getMessage());
      }
		  return temp;
	}
}