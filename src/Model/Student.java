package Model;

public class Student extends UserInfo {

	public Student(String name, String id, String building, String labroom) {
		super(name, id, building, labroom);
		// TODO Auto-generated constructor stub
	}


	public String isWho() {

		return UsedConstant.IS_STUDENT;
	}

}
