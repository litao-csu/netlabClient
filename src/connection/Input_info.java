package connection;

import View.MainFrame;
import View.RegisterFrame;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.JOptionPane;

import Model.InitInfo;

public class Input_info {
	public static void main(String[] args) throws InterruptedException,ExecutionException 
	{	
		new Thread(new ConnectionThread()).start();	
		
	}
}

/*
 * @author litao
 * "连接网络"线程
 * 启动时尝试连接网络。当网络通畅时，再启动netlab。
 */

class ConnectionThread implements Runnable{
	private boolean is_need_connecting = true;   //断网了则为True, 反之False
 
	 public ConnectionThread(){
	 }
	
	 public void run(){
	       //连接网络
	       while(is_need_connecting){
	         //测试网络是否已经连通
	         for(int i = 0; i < 10; i++){
	            try{
	               Thread.sleep(2*1000);
	               InetAddress.getByName("www.baidu.com");
	               is_need_connecting = false;
	               break;
	            }catch(Exception e){
	               e.printStackTrace();
	            }
	         }
	      }
	      detectNet();
	}
	
	//监测网络状况
	public void detectNet(){
	    try{
	    	parameterInit();  //初始化所在建筑、实验室、研究组等参数
	    	
	    	String Serial_Number = Get_serialNumber.getLocalSerialNumber();
			final String ret_info = APPclient.Get_ret_info(Serial_Number);		
//			System.out.println("返回数据："+ret_info);
			if(ret_info.equals(null) || ret_info==null || ret_info == "" || ret_info.equals("")){
				int n = JOptionPane.showConfirmDialog(null, "服务器连接失败，请稍后再试 。","提示",JOptionPane.DEFAULT_OPTION);
		    	if(n == 0)
		    		System.exit(0);	
			}		
			else if(ret_info.equals("####"))
			{			
					RegisterFrame frame = RegisterFrame.getRegisterFrame();
					frame.setVisible(true);
			}
			else
			{
				
				final ExecutorService exec = Executors.newFixedThreadPool(1);  
				  
				Callable<String> call = new Callable<String>() {  
				    public String call() throws Exception {  
				    	Fun_invoke.login_Invoke(ret_info);
				    	MainFrame frame = MainFrame.getMainFrame();
						frame.setVisible(false);
						frame.dispose();
						frame.MyTray();
				         
				        return "线程执行完成."; 
				       
				    }  
				};  		  
				try {  
				    Future<String> future = exec.submit(call);  
				    String obj = future.get(1000 * 60, TimeUnit.MILLISECONDS); //任务处理超时时间设为 60 秒  
				    System.out.println("任务成功返回:" + obj);  
				} catch (TimeoutException ex) {  
//				    System.out.println("服务器连接失败，请稍后再试。");  
				    int n = JOptionPane.showConfirmDialog(null, "netlab 服务器连接失败，请稍后再试........","提示",JOptionPane.DEFAULT_OPTION);
			    	if(n == 0)
			    		System.exit(0);		    
				    ex.printStackTrace();  
				} catch (Exception e) {  
				    System.out.println("处理失败.");  
				    e.printStackTrace();  
				}  
				// 关闭线程池  
				exec.shutdown();  
				
			}
	    	
	//       }
	    }catch(Exception e){
	       is_need_connecting = true;
	       String message = "网络已断, 域名解析失败!正在重新连接.....";
	       System.out.println(message);
	       e.printStackTrace();
	    }
	}
	
	//初始化所在建筑、实验室、研究组等参数
    public static void parameterInit(){
    	String total = APPclient.getInitInfo();
    	String[] all = total.split(";");
    	String[] building_id = all[0].split(",");
    	String[] building = all[1].split(",");
    	String[] labName = all[2].split(",");
    	String[] group_study = all[3].split(",");
    	String[] grade = all[4].split(",");
	
    	int size1 = building.length;
    	int size2 = labName.length;
    	Map<String,String[]> lab_map = new HashMap<String,String[]>();
    	for(int i = 0;i < size1;i++){
    		StringBuffer lab = new StringBuffer();
    		for(int j = 0;j < size2;j++){
    			if(labName[j].startsWith(building[i]) && building[i].length() < 3){
    				lab.append(building[i]);
    				lab.append(",");
    				break;
    			}else if(labName[j].startsWith(building[i])){
    				lab.append(labName[j].substring(building[i].length(),labName[j].length()));
    				lab.append(",");
    			}	
    		}
    		String[] temp = lab.toString().split(",");
    		lab_map.put(Integer.toString(i), temp);
    		
    	}
    	InitInfo.getInstance().building_id = building_id;
    	InitInfo.getInstance().lab_building = building;
    	InitInfo.getInstance().lab_name = labName;
    	InitInfo.getInstance().group_study = group_study;
    	InitInfo.getInstance().user_grade = grade;
    	InitInfo.getInstance().lab_map = lab_map;
    	
//    	System.out.println("init" + InitInfo.getInstance().lab_map);
    }
}
