package View;

import java.awt.EventQueue;

import Controller.NotifierManager;
import Controller.Startup;
import Model.UsedConstant;

public class MainEntrance {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProcessFrame frame = new ProcessFrame();
					frame.setVisible(true);
					
					NotifierManager.getInstance().notifyIt(UsedConstant.PROCESSFRAME_NOTIFY, "正在收集机器信息...");
					new Startup();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
