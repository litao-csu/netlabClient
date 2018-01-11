package connection;

import java.io.File;

public class DeleteFile {
/*	
	public static void main(String[] args) {
	    new DeleteFile().clearFiles("c:/zhuangwangtong");
	    System.out.println("c:/zhuangwangtong的目录下文件已删除");
	   }
*/
	//删除文件和目录
	public static void clearFiles(String workspaceRootPath){
	     
		File file = new File(workspaceRootPath);
	     if(file.exists()){
	          deleteFile(file);
	     }
	}
	private static void deleteFile(File file){
	     if(file.isDirectory()){
	          File[] files = file.listFiles();
	          for(int i=0; i<files.length; i++){
	               deleteFile(files[i]);
	          }
	     }
	     file.delete();
	}
}
