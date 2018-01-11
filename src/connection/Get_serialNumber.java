package connection;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

//浣跨敤涓�涓閮╠iskid32.exe鑾峰彇纭洏搴忓垪鍙�
public class Get_serialNumber {
	protected static final String KEY = "Serial Number =";
	
	public static String getLocalSerialNumber() {
		  String Serial_Num = new String();
		  try {
	            Process process = Runtime.getRuntime().exec("./Spark/diskid32.exe");
	            InputStreamReader ir = new InputStreamReader(process.getInputStream());
	            LineNumberReader input = new LineNumberReader(ir);
	            String line;
	            while ((line = input.readLine()) != null){
	            	//System.out.println(line);
	            	if(line.contains(KEY)){
	            		if("Serial Number = []".equals(line))
	            		{
	            			Serial_Num = getvirtualSerialNumber();
	            		}
	            		else{
		            		String[] temp = line.split("\\[");
		            		String[] temp1 = temp[1].split("\\]");
		            		Serial_Num = temp1[0];
		            		//System.out.println(line);
		            		//System.out.println(temp1);
		            		System.out.println(Serial_Num);
		            		break;
	            		}
	            	}
	            }
        } catch (java.io.IOException e) {
            System.err.println("IOException " + e.getMessage());
        }
		  return Serial_Num;
	}
	public static String getvirtualSerialNumber() {
		InetAddress addr = null;
		String virtualSerialNum = new String();
		String ret_info = new String();
		try {
			addr = InetAddress.getLocalHost();
			ret_info = APPclient.Get_ret_info("get_clientaddr");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String addrstr = addr.toString();
		String[] temp = addrstr.split("/");
		String ip = temp[1];
		System.out.println(addr);
		System.out.println(addrstr);
		virtualSerialNum = ret_info + "_" + ip;
		System.out.println(virtualSerialNum);
		return virtualSerialNum;
	}
}
