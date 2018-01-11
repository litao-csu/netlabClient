package Controller;

import Model.PCInfo;
import Model.UsedConstant;
import Model.UserInfo;

public class Startup {

	protected UserInfo user = null;
	protected PCInfo pc = null;
	
	public Startup(){
		
		this.start();
	}
	
	public void start(){
		
		NotifierManager.getInstance().notifyIt(UsedConstant.PROCESSFRAME_NOTIFY, "正在收集CPU信息...");
		String cpu_info = InfoCollector.getInstance().getCpuInfo();
		NotifierManager.getInstance().notifyIt(UsedConstant.PROCESSFRAME_NOTIFY, "正在收集内存信息...");
		String mem_info = InfoCollector.getInstance().getMemInfo();
		NotifierManager.getInstance().notifyIt(UsedConstant.PROCESSFRAME_NOTIFY, "正在收集硬盘信息...");
		String dsk_info = InfoCollector.getInstance().getDskInfo();
		NotifierManager.getInstance().notifyIt(UsedConstant.PROCESSFRAME_NOTIFY, "正在收集网络信息...");
		String mac_info = InfoCollector.getInstance().getMacInfo();
		
		NotifierManager.getInstance().notifyIt(UsedConstant.PROCESSFRAME_NOTIFY, "主机信息收集完毕...");
		
		pc = new PCInfo(cpu_info, mem_info, dsk_info, mac_info);
		
		String result = Communicator.getInstance().sendInfo(dsk_info);
	}
}
