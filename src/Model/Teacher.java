package Model;

public class Teacher extends UserInfo {

	protected String priority;
	
	public Teacher(String name, String id, String building, String labroom) {
		super(name, id, building, labroom);
		// TODO Auto-generated constructor stub
	}
	
	
	public String isWho() {
		
		return UsedConstant.IS_TEACHER;
	}

}
