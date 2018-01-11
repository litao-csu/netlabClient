package connection;

import java.io.File;
import java.io.IOException;

public class getPath {
	public static String get_realPath(){
		File directory = new File("");//参数为空 
		String courseFile = null;
		try {
			courseFile = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		System.out.println(courseFile);
		return courseFile;
	}
	
}
