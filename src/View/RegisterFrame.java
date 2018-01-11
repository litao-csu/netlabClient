package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.NotifierManager;
import Model.InitInfo;
import Model.Notifier;
import Model.UsedConstant;
import Model.UserInfo;
import connection.APPclient;
import connection.Get_serialNumber;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.CardLayout;

import javax.swing.JRadioButton;

import java.awt.Font;

public class RegisterFrame extends JFrame implements Notifier{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1439923710151654331L;
	private volatile static RegisterFrame registerFrame;
	
	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField IDTextField;
	private JComboBox<String> BuildingComboBox;
	private JComboBox<String> RoomComboBox;
	private JComboBox<String> GroupComboBox;
	private JComboBox<String> GradeComboBox;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JRadioButton radioButton_2;
	private JRadioButton radioButton_3;
	private JRadioButton radioButton_4;
	private JRadioButton radioButton_5;
	
	private final JButton saveButton = new JButton("登录");
    private final JButton quitButton = new JButton("退出");
	
	
	protected final JPanel buttonPanel = new JPanel(new GridBagLayout());
	protected final JLabel progressBar = new JLabel();
	protected final JPanel cardPanel = new JPanel();
	private final CardLayout cardLayout = new CardLayout(0, 5);
	
	private static String[] labBuildingStr = InitInfo.getInstance().lab_building;
	private static String[] labName = InitInfo.getInstance().lab_name;
	private static String[] groupStr = InitInfo.getInstance().group_study;
	private static Map<String,String[]> lab_map = InitInfo.getInstance().lab_map;
//	private static final String[] labBuildingStr = {"升华后楼", "计算机楼"};
//    private static final String[] labSHStr = {"老师","201", "212", "410"};
//    private static final String[] labCBStr = {"老师","113-1", "109", "201","213", "214", "216", "302", "406", "412"};
//    private static final short SHENGHUA_BUILDING = 0;
//    private static final short JISUANJI_BUILDING = 1;
    private static final String[] degree = {"老师","硕士","博士","访问学者"};
//    private static final String[] groupStr = {"老师","参数组","大数据组","互联网产品组","数据中心组","生物组","深度学习组","透明计算","图形组","网络软件组","网络与信息安全组","网络组"};
    private static final String[] degree_type = {"学术型","专业型"};
    
    private static String[] gradeStr = InitInfo.getInstance().user_grade;
//    private static final String[] gradeStr= new String[31];
//    public static void gradeInit(){
//	    for(int i = 0;i < gradeStr.length;i++){	    	
//	    	gradeStr[i] = Integer.toString(i+2000);
//	    }
//    }

    private static final String BUTTON_PANEL = "buttonpanel"; // NOTRANS
    private static final String PROGRESS_BAR = "progressbar"; // NOTRANS
    private JTextField DegreeTextField = new JTextField();
    private JTextField DegreeTypeTextField = new JTextField();
    
    private JLabel label;
    private JLabel label_1;
    private JLabel label_2;
    private JLabel label_3;
    private JLabel label_4;
    private JLabel label_5;
    private JLabel label_6;
    private JLabel label_7;
    private JLabel label_8;
    private JTextField majorTextField;
    private JTextField tutorTextField;
    private JLabel label_9;
    private JTextField advisorTextField;
    private JLabel label_10;
    private JLabel label_11;
    private JLabel label_12;
    private JLabel label_13;
    private JLabel label_17;
    private JLabel label_14;
    private JLabel label_15;
    private JLabel label_16;
    private JTextField telephoneTextField;
    private JTextField qqTextField;
    private JTextField emailTextField;
    private JTextField contactTextField;
    private JTextField relationshipTextField;
    private JTextField contactPhoneTextField;
    private JTextField researchTextField;
 
    /*
     * @author litao
     * 单例模式
     */
    public static RegisterFrame getRegisterFrame(){
  
    		synchronized (RegisterFrame.class) {  
    			 if (registerFrame == null) {  
    				 registerFrame = new RegisterFrame();  
    			 }  
    		}  
    	
    	return registerFrame;	
    }
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
    

	/**
	 * Create the frame.
	 */
	public RegisterFrame() {
//		gradeInit();
		parameterInit();
		
		labBuildingStr = InitInfo.getInstance().lab_building;
		labName = InitInfo.getInstance().lab_name;
		groupStr = InitInfo.getInstance().group_study;
		lab_map = InitInfo.getInstance().lab_map;
		gradeStr = InitInfo.getInstance().user_grade;
		
		Image icon0 = Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/resources/images/16px.png"));
		setIconImage(icon0);
		setTitle("计算机理论与软件研究所");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 689, 466);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("宋体", Font.PLAIN, 13));
		nameTextField.setBounds(453, 21, 191, 28);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		IDTextField = new JTextField();
		IDTextField.setFont(new Font("宋体", Font.PLAIN, 13));
		IDTextField.setColumns(10);
		IDTextField.setBounds(121, 21, 191, 28);
		contentPane.add(IDTextField);
		IDTextField.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub				
			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
//				System.out.println(IDTextField.getText());
				if(IDTextField.getText().equals(null))
				{
					System.out.println("此用户未注册！！！");
				}
				else{
					String ret_info =APPclient.Get_ret_info("check_userinfo"+"*"+IDTextField.getText());					 		
					if(ret_info.equals("####"))
					{
						System.out.println(ret_info);
						
					}
					else{
						getuserInfo(ret_info);
						registerInit();
					}
				}
			}						
		});
		
		BuildingComboBox = new JComboBox<String>(labBuildingStr);
//		BuildingComboBox = new JComboBox<String>();
		BuildingComboBox.setFont(new Font("宋体", Font.PLAIN, 13));
		BuildingComboBox.setBounds(121, 127, 191, 28);
		contentPane.add(BuildingComboBox);
		
		String[] labDefault = lab_map.get(Integer.toString(0));
		RoomComboBox = new JComboBox<String>(labDefault);
//		RoomComboBox = new JComboBox<String>();
		RoomComboBox.setFont(new Font("宋体", Font.PLAIN, 13));
		RoomComboBox.setBounds(453, 127, 191, 28);
		contentPane.add(RoomComboBox);
        
        GroupComboBox = new JComboBox<String>(groupStr);
//        GroupComboBox = new JComboBox<String>();
        GroupComboBox.setFont(new Font("宋体", Font.PLAIN, 13));
        GroupComboBox.setBounds(121, 165, 191, 28);
        contentPane.add(GroupComboBox);
      
        GradeComboBox = new JComboBox<String>(gradeStr);
//        GradeComboBox = new JComboBox<String>();
        GradeComboBox.setFont(new Font("宋体", Font.PLAIN, 13));
        GradeComboBox.setBounds(453, 165, 191, 28);
        contentPane.add(GradeComboBox);
		
		label = new JLabel("姓名:");
		label.setFont(new Font("宋体", Font.PLAIN, 13));
		label.setBounds(361, 21, 86, 28);
		contentPane.add(label);
		
		label_1 = new JLabel("学号/教工号:");
		label_1.setFont(new Font("宋体", Font.PLAIN, 13));
		label_1.setBounds(35, 21, 86, 28);
		contentPane.add(label_1);
				
		label_2 = new JLabel("所在建筑:");
		label_2.setFont(new Font("宋体", Font.PLAIN, 13));
		label_2.setBounds(35, 127, 86, 28);
		contentPane.add(label_2);
		
		label_3 = new JLabel("所在实验室:");
		label_3.setFont(new Font("宋体", Font.PLAIN, 13));
		label_3.setBounds(361, 127, 86, 28);
		contentPane.add(label_3);
		
		saveButton.setLocation(24, 325);
		saveButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Thread(){
				
					public void run() {
						save();
					}
				}.start();
			}
		});
		quitButton.setLocation(208, 325);
		quitButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Thread(){
				
					public void run() {
						quit();
					}
				}.start();
			}
		});

		// add buttons
		buttonPanel.add(saveButton,
                new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		

		cardPanel.setBounds(191, 366, 277, 62);
		contentPane.add(cardPanel);
		cardPanel.setLayout(cardLayout);
		
		cardPanel.add(buttonPanel, BUTTON_PANEL);

        cardPanel.setOpaque(false);
        buttonPanel.setOpaque(false);    
        
        buttonPanel.add(quitButton
        		, new GridBagConstraints(3, 0, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER
        				, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
        
        ImageIcon icon = new ImageIcon(RegisterFrame.class.getResource("/resources/images/ajax-loader.gif"));
        progressBar.setIcon(icon);
        cardPanel.add(progressBar, PROGRESS_BAR);
		
        // Set progress bar description
        progressBar.setText("正在连接服务器");
        progressBar.setVerticalTextPosition(JLabel.BOTTOM);
        progressBar.setHorizontalTextPosition(JLabel.CENTER);
        progressBar.setHorizontalAlignment(JLabel.CENTER);
        
        label_4 = new JLabel("身份类别:");
        label_4.setFont(new Font("宋体", Font.PLAIN, 13));
        label_4.setBounds(35, 55, 86, 28);
        contentPane.add(label_4);
        
        radioButton = new JRadioButton("老师");
        radioButton.setFont(new Font("宋体", Font.PLAIN, 13));
        radioButton.setBounds(118, 55, 51, 28);
        contentPane.add(radioButton);
        
        radioButton_1 = new JRadioButton("硕士");
        radioButton_1.setFont(new Font("宋体", Font.PLAIN, 13));
        radioButton_1.setBounds(168, 55, 51, 28);
        contentPane.add(radioButton_1);
        
        radioButton_2 = new JRadioButton("博士");
        radioButton_2.setFont(new Font("宋体", Font.PLAIN, 13));
        radioButton_2.setBounds(216, 55, 51, 28);
        contentPane.add(radioButton_2);      

        radioButton_3 = new JRadioButton("访问学者");
        radioButton_3.setFont(new Font("宋体", Font.PLAIN, 13));
        radioButton_3.setBounds(265, 55, 77, 28);
        contentPane.add(radioButton_3);
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton);
        buttonGroup.add(radioButton_1);
        buttonGroup.add(radioButton_2);
        buttonGroup.add(radioButton_3);
        /*
         * @author litao
         * 单选按钮选择身份类别为老师、硕士生、博士生或者访问学者
         */
        radioButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				new Thread(){
					public void run() {
						selectDegree();
						invisible();
					}
				}.start();
			}
		});
        
        radioButton_1.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				new Thread(){
					public void run() {
						selectDegree();
						returnVisible();
					}
				}.start();
			}
		});
        radioButton_2.addActionListener(new ActionListener() {
        	
			public void actionPerformed(ActionEvent e) {
				new Thread(){
					public void run() {
						selectDegree();
						returnVisible();
					}
				}.start();
			}
		});
        radioButton_3.addActionListener(new ActionListener() {
        	
			public void actionPerformed(ActionEvent e) {
				new Thread(){
					public void run() {
						selectDegree();
						returnVisible();
					}
				}.start();
			}
		});
        
        label_8 = new JLabel("所在项目组:");
        label_8.setFont(new Font("宋体", Font.PLAIN, 13));
        label_8.setBounds(35, 165, 86, 28);
        contentPane.add(label_8);       
        
        label_5 = new JLabel("年级:");
        label_5.setFont(new Font("宋体", Font.PLAIN, 13));
        label_5.setBounds(361, 165, 86, 28);
        contentPane.add(label_5);
        
        label_6 = new JLabel("专业:");
        label_6.setFont(new Font("宋体", Font.PLAIN, 13));
        label_6.setBounds(35, 203, 86, 28);
        contentPane.add(label_6);
        
        label_7 = new JLabel("导师");
        label_7.setFont(new Font("宋体", Font.PLAIN, 13));
        label_7.setBounds(35, 241, 86, 28);
        contentPane.add(label_7);
        
        majorTextField = new JTextField();
        majorTextField.setFont(new Font("宋体", Font.PLAIN, 13));
        majorTextField.setBounds(121, 203, 191, 28);
        contentPane.add(majorTextField);
        majorTextField.setColumns(10);
        
        tutorTextField = new JTextField();
        tutorTextField.setFont(new Font("宋体", Font.PLAIN, 13));
        tutorTextField.setBounds(121, 241, 191, 28);
        contentPane.add(tutorTextField);
        tutorTextField.setColumns(10);
        
        label_9 = new JLabel("指导老师:");
        label_9.setFont(new Font("宋体", Font.PLAIN, 13));
        label_9.setBounds(361, 241, 86, 28);
        contentPane.add(label_9);
        
        advisorTextField = new JTextField();
        advisorTextField.setFont(new Font("宋体", Font.PLAIN, 13));
        advisorTextField.setBounds(453, 241, 191, 28);
        contentPane.add(advisorTextField);
        advisorTextField.setColumns(10);
        
        label_10 = new JLabel("手机号码:");
        label_10.setFont(new Font("宋体", Font.PLAIN, 13));
        label_10.setBounds(35, 89, 86, 28);
        contentPane.add(label_10);
        
        label_11 = new JLabel("QQ:");
        label_11.setFont(new Font("宋体", Font.PLAIN, 13));
        label_11.setBounds(35, 283, 86, 28);
        contentPane.add(label_11);
        
        label_12 = new JLabel("Email:");
        label_12.setFont(new Font("宋体", Font.PLAIN, 13));
        label_12.setBounds(361, 89, 86, 28);
        contentPane.add(label_12);
        
        label_13 = new JLabel("紧急联系人:");
        label_13.setFont(new Font("宋体", Font.PLAIN, 13));
        label_13.setBounds(361, 283, 86, 28);
        contentPane.add(label_13);
        
        label_17 = new JLabel("与本人关系:");
        label_17.setFont(new Font("宋体", Font.PLAIN, 13));
        label_17.setBounds(35, 321, 86, 28);
        contentPane.add(label_17);
        
        label_14 = new JLabel("紧急联系电话：");
        label_14.setFont(new Font("宋体", Font.PLAIN, 13));
        label_14.setBounds(361, 321, 91, 28);
        contentPane.add(label_14);
        
        telephoneTextField = new JTextField();
        telephoneTextField.setFont(new Font("宋体", Font.PLAIN, 13));
        telephoneTextField.setBounds(121, 89, 191, 28);
        contentPane.add(telephoneTextField);
        telephoneTextField.setColumns(10);
        
        qqTextField = new JTextField();
        qqTextField.setFont(new Font("宋体", Font.PLAIN, 13));
        qqTextField.setBounds(121, 283, 191, 28);
        contentPane.add(qqTextField);
        qqTextField.setColumns(10);
        
        emailTextField = new JTextField();
        emailTextField.setFont(new Font("宋体", Font.PLAIN, 13));
        emailTextField.setBounds(453, 89, 191, 28);
        contentPane.add(emailTextField);
        emailTextField.setColumns(10);
        
        contactTextField = new JTextField();
        contactTextField.setFont(new Font("宋体", Font.PLAIN, 13));
        contactTextField.setBounds(453, 283, 191, 28);
        contentPane.add(contactTextField);
        contactTextField.setColumns(10);
        
        relationshipTextField = new JTextField();
        relationshipTextField.setFont(new Font("宋体", Font.PLAIN, 13));
        relationshipTextField.setBounds(121, 321, 191, 28);
        contentPane.add(relationshipTextField);
        relationshipTextField.setColumns(10);
        
        contactPhoneTextField = new JTextField();
        contactPhoneTextField.setFont(new Font("宋体", Font.PLAIN, 13));
        contactPhoneTextField.setBounds(453, 321, 191, 28);
        contentPane.add(contactPhoneTextField);
        contactPhoneTextField.setColumns(10);
        
        label_15 = new JLabel("研究方向:");
        label_15.setFont(new Font("宋体", Font.PLAIN, 13));
        label_15.setBounds(361, 55, 86, 28);
        contentPane.add(label_15);
        
        researchTextField = new JTextField();
        researchTextField.setFont(new Font("宋体", Font.PLAIN, 13));
        researchTextField.setColumns(10);
        researchTextField.setBounds(453, 55, 191, 28);
        contentPane.add(researchTextField);
        
        label_16 = new JLabel("学位类别:");
        label_16.setFont(new Font("宋体", Font.PLAIN, 13));
        label_16.setBounds(361, 203, 86, 28);
        contentPane.add(label_16);
        
        radioButton_4 = new JRadioButton("学术型");
        radioButton_4.setFont(new Font("宋体", Font.PLAIN, 13));
        radioButton_4.setBounds(453, 203, 77, 28);
        contentPane.add(radioButton_4);
        
        radioButton_5 = new JRadioButton("专业型");
        radioButton_5.setFont(new Font("宋体", Font.PLAIN, 13));
        radioButton_5.setBounds(554, 203, 90, 28);
        contentPane.add(radioButton_5);
        
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(radioButton_4);
        buttonGroup1.add(radioButton_5);
        /*
         * @author litao
         * 单选按钮选择学位类别为学术型或者专业型
         */
        radioButton_4.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				new Thread(){
					public void run() {
						selectDegreeType();
					}
				}.start();
			}
		});
        
        radioButton_5.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				new Thread(){
					public void run() {
						selectDegreeType();
					}
				}.start();
			}
		});

		BuildingComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				changeRoom();
			}
		});
		
		//为主窗体注册窗体事件
	   addWindowListener(new WindowAdapter()
	   {		    
		    // 当点击"X"关闭窗口按钮时，会询问用户是否要退出
		    // 是，表示同意，否，返回界面
		    public void windowClosing(WindowEvent e) {
		    	int option = JOptionPane.showConfirmDialog(RegisterFrame.this,
		    			"是否退出登录?", "提示:", JOptionPane.YES_NO_OPTION);
		    	if (option == JOptionPane.YES_OPTION) {	
		    		System.exit(0);
		    	} else {
		    		return;
		    	}
		    }
	   });

		NotifierManager.getInstance().addNotifier(UsedConstant.REGISTERFAME_NOTIFY, this);
	}
	
	/**
     * Displays the progress bar.
     *
     * @param visible true to display progress bar, false to hide it.
     */
    private void setProgressBarVisible(boolean visible) {
        if (visible) {
            cardLayout.show(cardPanel, PROGRESS_BAR);
            // progressBar.setIndeterminate(true);
        }
        else {
            cardLayout.show(cardPanel, BUTTON_PANEL);
        }
    }
    
    /*
     * @author litao
     * 单选按钮选择身份类别为老师、硕士生、博士生或者访问学者
     */
    private void selectDegree(){
    	if(radioButton.isSelected()){
    		DegreeTextField.setText(degree[0]);
    	}
    	else if(radioButton_1.isSelected())
    		DegreeTextField.setText(degree[1]);
    	else if(radioButton_2.isSelected())
    		DegreeTextField.setText(degree[2]);
    	else if(radioButton_3.isSelected())
    		DegreeTextField.setText(degree[3]);
    }
    
    /*
     * @author litao
     * 单选按钮选择身份类别为学术型或者专业型
     */
    private void selectDegreeType(){
    	if(radioButton_4.isSelected())
    		DegreeTypeTextField.setText(degree_type[0]);
    	else if(radioButton_5.isSelected())
    		DegreeTypeTextField.setText(degree_type[1]);
    }
	
	/*
     * @author Jesson LIU
     * change # of room when varying buildings
     * */
    private void changeRoom(){
//    	if(this.BuildingComboBox.getSelectedIndex() == 0)
    	/*switch(this.BuildingComboBox.getSelectedIndex()){
    	
    		case SHENGHUA_BUILDING:
    			this.RoomComboBox.removeAllItems();
    			this.RoomComboBox.updateUI();
    			for(int i=0; i<labSHStr.length; i++){
    				
    				this.RoomComboBox.addItem(labSHStr[i]);
    			}
    			this.RoomComboBox.setSelectedIndex(0);
    			break;
    		case JISUANJI_BUILDING:
    			this.RoomComboBox.removeAllItems();
    			this.RoomComboBox.updateUI();
    			for(int i=0; i<labCBStr.length; i++){
    				
    				this.RoomComboBox.addItem(labCBStr[i]);
    			}
    			this.RoomComboBox.setSelectedIndex(0);
    	}*/
    	String index = Integer.toString(0);
    	index = Integer.toString(this.BuildingComboBox.getSelectedIndex());
    	String[] labStr = lab_map.get(index);
    	this.RoomComboBox.removeAllItems();
		this.RoomComboBox.updateUI();
		for(int i=0; i<labStr.length; i++){
			
			this.RoomComboBox.addItem(labStr[i]);
		}
		this.RoomComboBox.setSelectedIndex(0);
    	
    }
    
    /**
     * @author litao
     * 
     * listeners for buttons
     * @param ret_info 
     * @throws SigarException 
     * 
     * */
    public static String getuserInfo(String ret_info){
    		
		String[] userinfo = ret_info.split("[*]");
		for(int i=4;i<=18;i++){
			if(userinfo[i].equals("null"))
				userinfo[i] = null;
		}
		String userId = userinfo[0];
//		String userPwd = userinfo[1];
//		String useremail = userinfo[2];
		String username = userinfo[3];
		String lab_num = userinfo[4];
		String degree = userinfo[5];
		String user_group = userinfo[6];
		String grade = userinfo[7];
		String major = userinfo[8];
		String tutor = userinfo[9];
		String advisor = userinfo[10];		
		String telephone = userinfo[11];
		String qq = userinfo[12];
		String email = userinfo[13];
		String contact = userinfo[14];
		String relationship = userinfo[15];
		String contact_phone = userinfo[16];
		String research = userinfo[17];
		String degree_type = userinfo[18];
		UserInfo.getInstance().id = userId;
		UserInfo.getInstance().name = username;
		UserInfo.getInstance().lab = lab_num;
		UserInfo.getInstance().degree = degree;
		UserInfo.getInstance().group = user_group;
		UserInfo.getInstance().grade = grade;
		UserInfo.getInstance().major = major;
		UserInfo.getInstance().tutor = tutor;
		UserInfo.getInstance().advisor = advisor;
		UserInfo.getInstance().telephone = telephone;
		UserInfo.getInstance().qq = qq;
		UserInfo.getInstance().email = email;
		UserInfo.getInstance().contact = contact;
		UserInfo.getInstance().relationship = relationship;
		UserInfo.getInstance().contactPhone = contact_phone;
		UserInfo.getInstance().research = research;
		UserInfo.getInstance().degree_type = degree_type;
		return ret_info;
    }
    private void registerInit(){
 //   	IDTextField.setText(UserInfo.getInstance().id);
    	nameTextField.setText(UserInfo.getInstance().name);
    	String lab = UserInfo.getInstance().lab;
//    	System.out.println(lab);
    	/*if(lab.startsWith(labBuildingStr[0])){
    		lab = lab.substring(labBuildingStr[0].length(),lab.length());   		
    		UserInfo.getInstance().building = labBuildingStr[0];
    		UserInfo.getInstance().labroom = lab;
    		
    	}else if(lab.startsWith(labBuildingStr[1])){
    		lab = lab.substring(labBuildingStr[1].length(),lab.length());
    		UserInfo.getInstance().building = labBuildingStr[1];
    		UserInfo.getInstance().labroom = lab;
    	}*/
    	
    	for(int i = 0; i < labBuildingStr.length;i++){
    		if(lab.startsWith(labBuildingStr[i])){
    			if(lab.length() < 3)
    				lab = labBuildingStr[i];
    			else
    				lab = lab.substring(labBuildingStr[i].length(),lab.length());   		
        		UserInfo.getInstance().building = labBuildingStr[i];
        		UserInfo.getInstance().labroom = lab;
        		break;      		
        	}
    	}
    	
//    	System.out.println(UserInfo.getInstance().labroom);
    	BuildingComboBox.setSelectedItem(UserInfo.getInstance().building);
    	changeRoom();
    	RoomComboBox.setSelectedItem(UserInfo.getInstance().labroom);
    	DegreeTextField.setText(UserInfo.getInstance().degree);
    	if(UserInfo.getInstance().degree.equals(degree[0]))
    		radioButton.setSelected(true);
    	else if(UserInfo.getInstance().degree.equals(degree[1]))
    		radioButton_1.setSelected(true);
    	else if(UserInfo.getInstance().degree.equals(degree[2]))
    		radioButton_2.setSelected(true);
    	else if(UserInfo.getInstance().degree.equals(degree[3]))
    		radioButton_3.setSelected(true);
    	GroupComboBox.setSelectedItem(UserInfo.getInstance().group);
    	GradeComboBox.setSelectedItem(UserInfo.getInstance().grade);
    	majorTextField.setText(UserInfo.getInstance().major);
    	tutorTextField.setText(UserInfo.getInstance().tutor);
    	advisorTextField.setText(UserInfo.getInstance().advisor);
    	telephoneTextField.setText(UserInfo.getInstance().telephone);
    	qqTextField.setText(UserInfo.getInstance().qq);
    	emailTextField.setText(UserInfo.getInstance().email);
    	contactTextField.setText(UserInfo.getInstance().contact);
    	relationshipTextField.setText(UserInfo.getInstance().relationship);
    	contactPhoneTextField.setText(UserInfo.getInstance().contactPhone);
    	researchTextField.setText(UserInfo.getInstance().research);
    	DegreeTypeTextField.setText(UserInfo.getInstance().degree_type);
    	if(!(UserInfo.getInstance().degree_type == null)){
    		if(UserInfo.getInstance().degree_type.equals(degree_type[0]))
        		radioButton_4.setSelected(true);
        	else if(UserInfo.getInstance().degree_type.equals(degree_type[1]))
        		radioButton_5.setSelected(true);
    	}
    }
    private void save() {

    	 setProgressBarVisible(true);
    	 if(this.verifyInputs()){
         	           
         	UserInfo.getInstance().id = IDTextField.getText();
         	UserInfo.getInstance().name = nameTextField.getText();
         	UserInfo.getInstance().building = (String)BuildingComboBox.getSelectedItem();
         	UserInfo.getInstance().labroom = (String)RoomComboBox.getSelectedItem();
         	UserInfo.getInstance().lab = (String)BuildingComboBox.getSelectedItem() + (String)RoomComboBox.getSelectedItem();
         	UserInfo.getInstance().degree = DegreeTextField.getText();
         	UserInfo.getInstance().group = (String)GroupComboBox.getSelectedItem();
         	UserInfo.getInstance().grade = (String)GradeComboBox.getSelectedItem();
         	UserInfo.getInstance().major = majorTextField.getText();
         	UserInfo.getInstance().tutor = tutorTextField.getText();
         	UserInfo.getInstance().advisor = advisorTextField.getText();
         	UserInfo.getInstance().telephone = telephoneTextField.getText();
         	UserInfo.getInstance().qq = qqTextField.getText();
         	UserInfo.getInstance().email = emailTextField.getText();
         	UserInfo.getInstance().contact = contactTextField.getText();
         	UserInfo.getInstance().relationship = relationshipTextField.getText();
         	UserInfo.getInstance().contactPhone = contactPhoneTextField.getText();
         	UserInfo.getInstance().research = researchTextField.getText();
         	UserInfo.getInstance().degree_type = DegreeTypeTextField.getText();
         	
//         	System.out.println(UserInfo.getInstance().id + "" + UserInfo.getInstance().name 
//         			+ "" + UserInfo.getInstance().building + "" + UserInfo.getInstance().labroom
//         			+ "" + UserInfo.getInstance().degree + "" + UserInfo.getInstance().group);
         	
         	try {
         		
				String userId = UserInfo.getInstance().id;
				String userName = UserInfo.getInstance().name;
				String userLab = UserInfo.getInstance().lab;
				String userDegree = UserInfo.getInstance().degree;
				String userGroup = UserInfo.getInstance().group;
				String userGrade = UserInfo.getInstance().grade;
				String userMajor = UserInfo.getInstance().major;
				String userTutor = UserInfo.getInstance().tutor;
				String userAdvisor = UserInfo.getInstance().advisor;
				String userTelephone = UserInfo.getInstance().telephone;
				String userQQ = UserInfo.getInstance().qq;
				String userEmail = UserInfo.getInstance().email;
				String userContact = UserInfo.getInstance().contact;
				String userRelationship = UserInfo.getInstance().relationship;
				String userContactPhone = UserInfo.getInstance().contactPhone;
				String userResearch = UserInfo.getInstance().research;
				String userDegreeType = UserInfo.getInstance().degree_type;
				if(userLab.equals(""))
					userLab="0";
				if(userDegree.equals(""))
					userDegree="0";
				if(userGroup.equals(""))
					userGroup="0";
				if(userGrade.equals(""))
					userGrade="0";
				if(userMajor.equals(""))
					userMajor="0";
				if(userTutor.equals(""))
					userTutor="0";
				if(userAdvisor.equals(""))
					userAdvisor="0";
				if(userTelephone.equals(""))
					userTelephone="0";
				if(userQQ.equals(""))
					userQQ="0";
				if(userEmail.equals(""))
					userEmail="0";
				if(userContact.equals(""))
					userContact="0";
				if(userRelationship.equals(""))
					userRelationship="0";
				if(userContactPhone.equals(""))
					userContactPhone="0";
				if(userResearch.equals(""))
					userResearch="0";
				if(userDegreeType.equals(""))
					userDegreeType="0";
				
				APPclient.Get_register_info(userId,userName,userLab,userDegree,userGroup,userGrade,userMajor,userTutor,userAdvisor,userTelephone,userQQ,userEmail,userContact,userRelationship,userContactPhone,userResearch,userDegreeType);

				//注册成功，则调用其他程序登录
				String Serial_Number = Get_serialNumber.getLocalSerialNumber();
				final String ret_info =APPclient.Get_ret_info(Serial_Number);
				
				//判断是否注册成功
				if(ret_info.equals("####")){
					int n = JOptionPane.showConfirmDialog(null, "注册失败，请重新注册或稍后重试！","提示",JOptionPane.OK_CANCEL_OPTION);
			    	if(n == 0){
			    		setProgressBarVisible(false);
			    		return;
			    	}
				}else{
					int n = JOptionPane.showConfirmDialog(null, "注册成功！","提示",JOptionPane.OK_CANCEL_OPTION);
			    	if(n == 0){
			    		this.setVisible(false);
			    	}
					
				}
				//超时判断
				final ExecutorService exec = Executors.newFixedThreadPool(1); 
				String[] userinfo = ret_info.split("[*]");			
				final String userID = userinfo[0];
				final String device_rank = userinfo[20];
				if(device_rank.equals("master"))
				{
					String cmd = "cmd /k cd Spark && start /b Spark.exe " + userID + " " + "zndxjsjl";				   		
//			   		System.out.println(cmd);
			   		Runtime r=Runtime.getRuntime();
			   		Process proc = r.exec(cmd);	
			   		System.out.println(proc.toString());
				}
				Callable<String> call = new Callable<String>() {  
				    public String call() throws Exception {				    				    	
						invokeMainFrame();				         
				        return "线程执行完成."; 				       
				    }  
				};  		  
				try {  
				    Future<String> future = exec.submit(call);  
				    String obj = future.get(1000 * 15, TimeUnit.MILLISECONDS); //任务处理超时时间设为 10 秒  
				    System.out.println("任务成功返回:" + obj);  
				} catch (TimeoutException ex) {  
				    System.out.println("服务器连接失败，请稍后再试。");  
				    int n1 = JOptionPane.showConfirmDialog(null, "服务器连接失败，请稍后再试。","Error",JOptionPane.DEFAULT_OPTION);
			    	if(n1 == 0)
			    		System.exit(0);		    
				    ex.printStackTrace();  
				} catch (Exception e) {  
				    System.out.println("处理失败.");  
				    e.printStackTrace();  
				}  
				// 关闭线程池  
				exec.shutdown(); 
				
			} catch (IOException e) {
				e.printStackTrace();
			} 

//    	try{
//    		Thread.sleep(5*1000);
//    		NotifierManager.getInstance().notifyIt(UsedConstant.REGISTERFAME_NOTIFY, "上传信息...");
//    	}catch(InterruptedException ie){
//    		
//    		
//    	}
         	setProgressBarVisible(false);
         	this.setVisible(false);
    		this.dispose();
    	 }
    	 else{
    		 setProgressBarVisible(false);
    		 return;
    	 }
    }
    
    private void invokeMainFrame() throws UnsupportedEncodingException{
    	MainFrame frame = MainFrame.getMainFrame();
		frame.setVisible(false);
		frame.dispose();
		frame.MyTray();
		setProgressBarVisible(false);
		this.setVisible(false);
		this.dispose();
    }
    
    /**
     * @author litao
     * a method for verify those inputs
     * return "true" to pass or "false" to where it fails
     * */
    private boolean verifyInputs(){
    	
    	boolean isValid = true;
    	boolean input = true;
    	boolean degree = true;
    	boolean phone = true;
    	
    	//学号输入格式为数字，只能是6、9或者10位
    	try{
        	
        	Long.parseLong(IDTextField.getText());

        	if(IDTextField.getText().length() != 6 && IDTextField.getText().length() !=9 && IDTextField.getText().length() != 10){
        		
        		isValid = false;
        	}
        	 
        }catch(NumberFormatException nfe){
        	
        	isValid = false;
        }
    	
    	//手机号码输入格式为数字，只能是11位
    	try{
        	
        	Long.parseLong(telephoneTextField.getText());

        	if(telephoneTextField.getText().length() != 11){
        		
        		phone = false;
        	}
        	 
        }catch(NumberFormatException nfe){
        	
        	phone = false;
        }
    	
    	try {
			if(nameTextField.getText() == null)
				input = false;
			else if(!radioButton.isSelected() && !radioButton_1.isSelected() && !radioButton_2.isSelected() && !radioButton_3.isSelected())
				degree = false;
		} catch (Exception e) {
			input = false;
		}
        
        if(!isValid){
        	
        	JOptionPane.showMessageDialog(RegisterFrame.this, "学（工）号输入错误！", "Error",
					JOptionPane.ERROR_MESSAGE);
        	IDTextField.grabFocus();
        	return isValid;
        }
        else if (!input){
        	JOptionPane.showMessageDialog(RegisterFrame.this, "姓名输入不能为空!", "Error",
					JOptionPane.ERROR_MESSAGE);
        	nameTextField.grabFocus();
        	return input;
        }
        else if (!phone){
        	JOptionPane.showMessageDialog(RegisterFrame.this, "手机号码输入错误!", "Error",
					JOptionPane.ERROR_MESSAGE);
        	telephoneTextField.grabFocus();
        	return phone;
        }
        else if(!degree){
        	JOptionPane.showMessageDialog(RegisterFrame.this, "请选择您的身份，并确认输入数据是否正确!", "Error",
					JOptionPane.ERROR_MESSAGE);
        	return degree;
        }
    	return isValid;
    }
    
    //退出程序
    private void quit(){
    	int n = JOptionPane.showConfirmDialog(null, "确定退出?","Exit",JOptionPane.OK_CANCEL_OPTION);
    	if(n == 0){
    		System.exit(0);   		
    	}
    	else if (n == 2)
    		return;    
    }

	@Override
	public void NotifyMe(String msg) {
		progressBar.setText(msg);
		contentPane.updateUI();
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
    }
    
    //身份类别为老师时，隐藏这些属性
    public void invisible(){
    	BuildingComboBox.setVisible(false);
    	RoomComboBox.setVisible(false);
    	GroupComboBox.setVisible(false);
    	GradeComboBox.setVisible(false);
    	label_2.setVisible(false);
    	label_3.setVisible(false);
    	label_8.setVisible(false);
    	label_5.setVisible(false);
    	label_6.setVisible(false);
    	label_7.setVisible(false);
    	majorTextField.setVisible(false);
    	tutorTextField.setVisible(false);
    	label_9.setVisible(false);
    	advisorTextField.setVisible(false);
    	label_11.setVisible(false);
    	label_14.setVisible(false);
    	label_13.setVisible(false);
    	label_17.setVisible(false);
    	qqTextField.setVisible(false);
    	contactTextField.setVisible(false);
    	relationshipTextField.setVisible(false);
    	contactPhoneTextField.setVisible(false);
    	label_16.setVisible(false);
    	radioButton_4.setVisible(false);
    	radioButton_5.setVisible(false);
    	
    }
    
  //身份类别不为老师时，显示这些属性
    public void returnVisible(){
    	BuildingComboBox.setVisible(true);
    	RoomComboBox.setVisible(true);
    	GroupComboBox.setVisible(true);
    	GradeComboBox.setVisible(true);
    	label_2.setVisible(true);
    	label_3.setVisible(true);
    	label_8.setVisible(true);
    	label_5.setVisible(true);
    	label_6.setVisible(true);
    	label_7.setVisible(true);
    	majorTextField.setVisible(true);
    	tutorTextField.setVisible(true);
    	label_9.setVisible(true);
    	advisorTextField.setVisible(true);
    	label_11.setVisible(true);
    	label_14.setVisible(true);
    	label_13.setVisible(true);
    	label_17.setVisible(true);
    	qqTextField.setVisible(true);
    	contactTextField.setVisible(true);
    	relationshipTextField.setVisible(true);
    	contactPhoneTextField.setVisible(true);
    	label_16.setVisible(true);
    	radioButton_4.setVisible(true);
    	radioButton_5.setVisible(true);
    	
    }
}
