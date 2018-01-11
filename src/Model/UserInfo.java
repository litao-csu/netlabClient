package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UserInfo {
	
	public String name;
	public String id;
	public String building;
	public String labroom;
	public String lab;
	public String degree;
	public String grade;
	public String major;
	public String tutor;
	public String group;
	public String advisor;
	public String telephone;
	public String qq;
	public String email;
	public String contact;
	public String relationship;
	public String contactPhone;
	public String deviceRank;
	public String research;
	public String degree_type;
	public static UserInfo ui = null;
	
	private static String serverIP ;//= "192.168.1.164";
	private static  String openfirePort ;//= "5222";
	public static String IP_ADDR ;//= "192.168.1.164";
	public static int PORT ;//= 12345;

	/**
	 * @author litao
	 * 从外部文件读取服务器的IP和端口
	 */
	public static  void readServerIP(){
		try {
            // read file content from file
            StringBuffer sb= new StringBuffer("");
            FileReader reader = new FileReader("./Spark/readServerInformation/readServerInformation.ini");
            BufferedReader br = new BufferedReader(reader);
           
            String str = null;
            String[] data = new String[4];
            int i = 0;
           
            while((str = br.readLine()) != null) {
                  sb.append(str+"/n");
                  data[i++] = str;
            }
            
            IP_ADDR = data[0];
            serverIP = data[1];
            PORT = Integer.parseInt(data[2]);
            openfirePort = data[3];         
            br.close();
            reader.close();
		}
		catch(FileNotFoundException e) {
            e.printStackTrace();
        }
		catch(IOException e) {
            e.printStackTrace();
		}
			
	}
	/**
	 * @author litao
	 * 在类加载到内存时就执行该函数，获取服务器的IP和端口
	*/ 
	static{
		readServerIP();
	}
	
	public UserInfo(String name, String id, String building, String labroom) {
		super();
		this.name = name;
		this.id = id;
		this.building = building;
		this.labroom = labroom;
	}
	
	public void setUi(String name, String id, String building, String labroom){
		ui.name = name;
		ui.id = id;
		ui.building = building;
		ui.labroom = labroom;
	}
	
	public void setUi(String name, String id){
		ui.name = name;
		ui.id = id;
	}
	
	private UserInfo(){
			
			super();
	}
	
	public static UserInfo getInstance(){
			
		synchronized(UserInfo.class){
			if(ui == null){
				
				ui = new UserInfo();
			}
		}
		
		return ui;
	}

	public String getOpenFireServer(){
			
			return serverIP;
	}
	public static String get_IP_ADDR(){
		
		return IP_ADDR;
	}
	public static int get_PORT(){
		
		return PORT;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getLabroom() {
		return labroom;
	}
	public void setLabroom(String labroom) {
		this.labroom = labroom;
	}
		
	public String getLab() {
		return lab;
	}

	public void setLab(String lab) {
		this.lab = lab;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getAdvisor() {
		return advisor;
	}

	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getDeviceRank() {
		return deviceRank;
	}

	public void setDeviceRank(String deviceRank) {
		this.deviceRank = deviceRank;
	}

	public String getResearch() {
		return research;
	}

	public void setResearch(String research) {
		this.research = research;
	}

	public String getDegree_type() {
		return degree_type;
	}

	public void setDegree_type(String degree_type) {
		this.degree_type = degree_type;
	}
		
//	public abstract String isWho();
	/**
	public static void main(String[] args){
		System.out.println(getOpenFireServer() +" " +  get_IP_ADDR() + " " + get_PORT());
	}
*/
}
