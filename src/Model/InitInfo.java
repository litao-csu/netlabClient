package Model;

import java.util.Map;

public class InitInfo {

	public String[] building_id;     //所在建筑id
	public String[] lab_building;    //所在建筑
	public String[] lab_name;        //所在实验室
	public String[] group_study;     //所在研究组
	public String[] user_grade;      //年级
	public static InitInfo initInfo = null;
	public Map<String,String[]> lab_map;
	
	public InitInfo(String[] building_id, String[] lab_building, String[] lab_name, 
			String[] group_study,String[] user_grade,Map<String,String[]> lab_map) {
		super();
		this.building_id = building_id;
		this.lab_building = lab_building;
		this.lab_name = lab_name;
		this.group_study = group_study;
		this.user_grade = user_grade;
		this.lab_map = lab_map;
	}
	
	public void setInitInfo(String[] building_id, String[] lab_building, String[] lab_name, 
			String[] group_study,String[] user_grade,Map<String,String[]> lab_map){
		initInfo.building_id = building_id;
		initInfo.lab_building = lab_building;
		initInfo.lab_name = lab_name;
		initInfo.group_study = group_study;
		initInfo.user_grade = user_grade;
		initInfo.lab_map = lab_map;
	}
	
	private InitInfo(){
			
			super();
	}
	
	public static InitInfo getInstance(){
			
		synchronized(UserInfo.class){
			if(initInfo == null){
				
				initInfo = new InitInfo();
			}
		}
		
		return initInfo;
	}

	public String[] getBuilding_id() {
		return building_id;
	}

	public void setBuilding_id(String[] building_id) {
		this.building_id = building_id;
	}

	public String[] getLab_building() {
		return lab_building;
	}

	public void setLab_building(String[] lab_building) {
		this.lab_building = lab_building;
	}

	public String[] getLab_name() {
		return lab_name;
	}

	public void setLab_name(String[] lab_name) {
		this.lab_name = lab_name;
	}

	public String[] getGroup_study() {
		return group_study;
	}

	public void setGroup_study(String[] group_study) {
		this.group_study = group_study;
	}

	public String[] getUser_grade() {
		return user_grade;
	}

	public void setUser_grade(String[] user_grade) {
		this.user_grade = user_grade;
	}

	public Map<String, String[]> getLab_map() {
		return lab_map;
	}

	public void setLab_map(Map<String, String[]> lab_map) {
		this.lab_map = lab_map;
	}
	
	

}
