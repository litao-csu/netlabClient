package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

import Controller.NotifierManager;
import Model.Notifier;
import Model.UsedConstant;
import net.miginfocom.swing.MigLayout;

public class ProcessFrame extends JFrame implements Notifier{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5275300062788856315L;
	private JPanel contentPane;
	private JLabel msgLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProcessFrame frame = new ProcessFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread.sleep(5*1000);
		NotifierManager.getInstance().notifyIt(UsedConstant.PROCESSFRAME_NOTIFY, "正在连接服务器...");
		
		Thread.sleep(5*1000);
		NotifierManager.getInstance().notifyIt(UsedConstant.PROCESSFRAME_NOTIFY, "正在发送数据...");
		
		Thread.sleep(5*1000);
		NotifierManager.getInstance().notifyIt(UsedConstant.PROCESSFRAME_NOTIFY, "等待服务器返回结果...");
		
		Thread.sleep(5*1000);
		NotifierManager.getInstance().notifyIt(UsedConstant.PROCESSFRAME_NOTIFY, "硬件信息上传成功...");
	}

	/**
	 * Create the frame.
	 */
	public ProcessFrame() {
		setTitle("主机硬件信息采集系统");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 110);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[222px][222px]", "[][78px]"));
		
		JProgressBar progressBar = new JProgressBar();
		contentPane.add(progressBar, "cell 0 0 2 1,grow");
		progressBar.setIndeterminate(true);
		
		msgLabel = new JLabel("程序正在初始化", JLabel.CENTER);
		contentPane.add(msgLabel, "cell 0 1 2 1,grow");
		
		NotifierManager.getInstance().addNotifier(UsedConstant.PROCESSFRAME_NOTIFY, this);
		this.setLocationRelativeTo(null);
	}

	@Override
	public void NotifyMe(String msg) {
		msgLabel.setText(msg);
		contentPane.updateUI();
	}
}
