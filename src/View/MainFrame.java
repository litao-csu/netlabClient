package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.NotifierManager;
import Model.Notifier;
import Model.UsedConstant;
import Model.UserInfo;
import connection.APPclient;
import connection.Get_serialNumber;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.Font;

public class MainFrame extends JFrame implements Notifier{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1439923710151654331L;
	private volatile static MainFrame mainFrame;
	
	private JPanel contentPane;
	
	private final JButton modifyButton = new JButton("修改信息");
	private final static JButton switchButton = new JButton("切换为主设备");
//    private final JButton quitButton = new JButton("退出");
	
	
	protected final JPanel buttonPanel = new JPanel(new GridBagLayout());
	protected final JLabel progressBar = new JLabel();
	protected final JPanel cardPanel = new JPanel();
	private final CardLayout cardLayout = new CardLayout(0, 5);
	protected final JLabel label_deviceRank = new JLabel();

    private static final String BUTTON_PANEL = "buttonpanel"; // NOTRANS
    private static final String PROGRESS_BAR = "progressbar"; // NOTRANS
    
    TrayIcon trayIcon; // 托盘图标
    SystemTray tray; // 本操作系统托盘的实例
    
    /*
     * @author litao
     * 单例模式
     */
    public static MainFrame getMainFrame(){
  
    		synchronized (MainFrame.class) {  
    			 if (mainFrame == null) {  
    				 mainFrame = new MainFrame();  
    			 }  
    		}  
    	
    	return mainFrame;	
    }
 
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		
		userInfoInit();
		Image icon0 = Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/resources/images/16px.png"));
		setIconImage(icon0);
		setTitle("计算机理论与软件研究所");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 352, 202);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("姓名: " + UserInfo.getInstance().getName());
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(226, 50, 86, 28);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("学号/教工号: " + UserInfo.getInstance().getId());
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(35, 50, 181, 28);
		contentPane.add(label_1);
		modifyButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Thread(){
				
					public void run() {
						edit();
					}
				}.start();
			}
		});
//		quitButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
//		quitButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				new Thread(){
//				
//					public void run() {
//						quit();
//					}
//				}.start();
//			}
//		});

		// add buttons
		buttonPanel.add(modifyButton,
                new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		

		cardPanel.setBounds(10, 102, 326, 62);
		contentPane.add(cardPanel);
		cardPanel.setLayout(cardLayout);
		
		cardPanel.add(buttonPanel, BUTTON_PANEL);

        cardPanel.setOpaque(false);
        buttonPanel.setOpaque(false);
        switchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        switchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Thread(){
				
					public void run() {
						switchDevice();						
					}
				}.start();
			}
		});
        
        buttonPanel.add(switchButton,
                new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
        
//        buttonPanel.add(quitButton
//        		, new GridBagConstraints(3, 0, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER
//        				, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
        
        ImageIcon icon = new ImageIcon(MainFrame.class.getResource("/resources/images/ajax-loader.gif"));
        progressBar.setIcon(icon);
        cardPanel.add(progressBar, PROGRESS_BAR);
		
        // Set progress bar description
        progressBar.setText("正在连接服务器");
        progressBar.setVerticalTextPosition(JLabel.BOTTOM);
        progressBar.setHorizontalTextPosition(JLabel.CENTER);
        progressBar.setHorizontalAlignment(JLabel.CENTER);
               
        JLabel label_2 = new JLabel("此设备为：");
        label_2.setFont(new Font("宋体", Font.PLAIN, 13));
        label_2.setBounds(104, 10, 68, 15);
        contentPane.add(label_2);
        //当进行主从设备切换时，设备等级同时改变显示
        String device = "主设备";
        if (!UserInfo.getInstance().deviceRank.equals("master"))
        	device = "从设备"; 
        else{
        	device = "主设备";
        	switchButton.setEnabled(false);
        }
        label_deviceRank.setText(device);
        label_deviceRank.setBounds(182, 10, 64, 15);
        contentPane.add(label_deviceRank);
             

		NotifierManager.getInstance().addNotifier(UsedConstant.REGISTERFAME_NOTIFY, this);
	}
	
	/**
     * Displays the progress bar.
     *
     * @param visible true to display progress bar, false to hide it.
     
    private void setProgressBarVisible(boolean visible) {
        if (visible) {
            cardLayout.show(cardPanel, PROGRESS_BAR);
            // progressBar.setIndeterminate(true);
        }
        else {
            cardLayout.show(cardPanel, BUTTON_PANEL);
        }
    }*/
 /*
  * @author litao
  * 在登录时，获取用户所有信息
  */
    public static void userInfoInit(){
    	String Serial_Number = Get_serialNumber.getLocalSerialNumber();
		String ret_info =APPclient.Get_ret_info(Serial_Number);		
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
		String device_rank = userinfo[20];
		System.out.println(device_rank);
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
		UserInfo.getInstance().deviceRank = device_rank;
		
		if(device_rank.equals("master"))
		{
			switchButton.setEnabled(false);
		}
		
    }
    
    /**
     * @author litao
     * 
     * listeners for buttons
     * @throws SigarException 
     * 
     * */
    private void edit(){
    	//更新修改界面的数据
    	userInfoInit();
    	
    	EditFrame frame = EditFrame.getEditFrame();
		frame.setVisible(true);
		
		this.setVisible(false);
		this.dispose();
//    	setProgressBarVisible(true);

    }
    
    /*
     * @author litao
     * 当前设备为从设备时，可以点击按钮切换为主设备，否则，按钮不可用
     * 为切换按钮添加事件
     */   
    private void switchDevice(){
    	String userId = UserInfo.getInstance().getId();
    	String Serial_Number = connection.Get_serialNumber.getLocalSerialNumber();
		String ret_info = APPclient.Get_ret_info("set_mainequipment"+"*"+Serial_Number+"*"+userId);
		System.out.println("切换为主设备后返回信息："+ret_info);
		label_deviceRank.setText("主设备");
		UserInfo.getInstance().deviceRank = "master";
		
		String cmd = "cmd /k cd Spark && start /b Spark.exe " + userId + " " + "zndxjsjl";  		
//   		System.out.println(cmd);
   		Runtime r=Runtime.getRuntime();
   		Process proc = null;
		try {
			proc = r.exec(cmd);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		switchButton.setEnabled(false);
   		System.out.println(proc.toString());
    }
    
 /*
  * @author litao
  * 为该窗体添加系统托盘
  */
    public void MyTray() throws UnsupportedEncodingException
	{		  
    	  final MainFrame frame = MainFrame.getMainFrame();
		  tray = SystemTray.getSystemTray(); // 获得本操作系统托盘的实例
		  URL url = MainFrame.class.getResource("/resources/images/16px.png");
		  ImageIcon icon = new ImageIcon(url); // 将要显示到托盘中的图标
		  
		  PopupMenu pop = new PopupMenu(); // 构造一个右键弹出式菜单		  
		  final MenuItem show = new MenuItem("显示窗口");
		  final MenuItem switchDevice = new MenuItem("切换为主设备");
		  final MenuItem exit = new MenuItem("退出");
		  pop.add(show);
		  pop.add(switchDevice);
		  pop.add(exit);
		  
		  trayIcon = new TrayIcon(icon.getImage(),"计算机理论与软件所", pop);//实例化托盘图标
			  
		  //为托盘图标监听点击事件
		  trayIcon.addMouseListener(new MouseAdapter() 
		  {
		      public void mouseClicked(MouseEvent e)
		      {
		         if(e.getClickCount()== 1)//鼠标单击图标
		         { 
		        	 //tray.remove(trayIcon); // 从系统的托盘实例中移除托盘图标    
		        	 frame.setExtendedState(JFrame.NORMAL);//设置状态为正常
		        	 frame.setVisible(true);//显示主窗体
		          }
		      }
		   });
		  
		  //选项注册事件
		  ActionListener al2=new ActionListener()
		  {
		       public void actionPerformed(ActionEvent e)
		       {
			        //退出程序
			        if(e.getSource()==exit)
			        {
			        	quit();//退出程序
			        }
			        //打开程序
			        if(e.getSource()==show)
			        {
			        	frame.setExtendedState(JFrame.NORMAL);//设置状态为正常
			         	frame.setVisible(true);
			        }
			        //切换为主设备
			        if(e.getSource()==switchDevice){
			        	switchDevice();			        	
			        }
		       }
		  };
	      exit.addActionListener(al2);
	      show.addActionListener(al2);
	      if(!UserInfo.getInstance().deviceRank.equals("master")){
	    	  switchDevice.addActionListener(al2);
	      }else{
	    	  switchDevice.setEnabled(false);
	      }
	      
	        
	      try 
	      {
	           tray.add(trayIcon); // 将托盘图标添加到系统的托盘实例中
	       } 
	      catch(AWTException ex)
	      {
	           ex.printStackTrace();
	       }
	   
		   //为主窗体注册窗体事件
		   addWindowListener(new WindowAdapter()
		   {
			    //窗体最小化事件
			    public void windowIconified(WindowEvent e)
		        {    
		            frame.setVisible(false);//使窗口不可视
		            frame.dispose();//释放当前窗体资源
		         } 
			    
			    // 当点击"X"关闭窗口按钮时，最小化到托盘
			    public void windowClosing(WindowEvent e) {			    	
			    		frame.setVisible(false);
			    		frame.dispose();//释放当前窗体资源		    	
			    }
		   });
	 }
    
    //退出程序
    private void quit(){
    	int n = JOptionPane.showConfirmDialog(null, "确定退出?","Exit",JOptionPane.OK_CANCEL_OPTION);
    	if(n == 0){
    		/**
			 * @author litao
			 * 在退出主界面时，同时退出boinc和Spark
			 */
			try{
				String cmd1 = "cmd /k taskkill /f /im boinc.exe ";
				String cmd2 = "cmd /k taskkill /f /im start.exe ";
				String cmd3 = "cmd /k taskkill /f /im Spark.exe ";
				String cmd4 = "cmd /k taskkill /f /im BravoMonitor.exe ";
				Runtime.getRuntime().exec(cmd1);
				Runtime.getRuntime().exec(cmd2);
				Runtime.getRuntime().exec(cmd3);
				Runtime.getRuntime().exec(cmd4);
//				System.out.println(cmd1);
//				System.out.println(cmd2);
//				System.out.println(cmd3);
//				System.out.println(cmd4);
			}
			catch (IOException e1) {  
	            e1.printStackTrace();  
	        }
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
}
