package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class judgeProcess {
	/**
	 * 
	 * @方法名 ：isRunning<br>
	 * @方法描述 ：判断系统进程是否存在<br>
	 * @创建者 ：zx<br>
	 * @param exeName ：进程名
	 * @return
	 * 返回类型 ：boolean
	 */
	public static boolean isRunning(String exeName) {
		Process proc;
		try {
			proc = Runtime.getRuntime().exec("tasklist");
			BufferedReader br = new BufferedReader(new InputStreamReader(proc
					.getInputStream()));
			String info = br.readLine();
			while (info != null) {
				//System.out.println(info);
				if (info.indexOf(exeName) >= 0) {
					return true;
				}
				info = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		System.out.println(false);
		return false;
	}

}
