package Controller;

public class Communicator {

	protected static Communicator communicator = null;
	
	private Communicator() {
		
	}
	
	public static Communicator getInstance(){
		
		synchronized(Communicator.class){
			
			if(communicator == null){
				
				communicator = new Communicator();
			}
		}

		return communicator;
	}

	public String sendInfo(String info){
		
		//TODO send information to server
		
		return "";
	}
	
}
