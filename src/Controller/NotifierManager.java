package Controller;

import java.util.Hashtable;

import Model.Notifier;

public class NotifierManager {

	protected static NotifierManager nm;
	protected Hashtable<Integer, Notifier> table;
	
	private NotifierManager() {
		
		table = new Hashtable<Integer, Notifier>();
	}

	public static NotifierManager getInstance(){
		
		synchronized(NotifierManager.class){
			
			if(nm == null){
				
				nm = new NotifierManager();
			}
		}
		
		return nm;
	}
	
	public void addNotifier(int id, Notifier n){
		
		nm.table.put(id, n);
	}
	
	public void notifyIt(int id, String msg){
		
		nm.table.get(id).NotifyMe(msg);
	}
}
