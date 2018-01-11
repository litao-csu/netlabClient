package Controller;

import Model.Collector;
import Model.Hardware_Type;

public class InfoCollector {

	protected static InfoCollector self = null;
	protected Collector collector = null;
	
	private InfoCollector() {
		
		// get OS name
		switch(OSTypeIdentifier.getInstance().getOSname()){

			case Windows:
				collector = new WindowsCollector();
				break;
			case Linux:
				collector = new LinuxInfoCollector();
				break;
			case AIX:
//				break;
			case Any:
//				break;
			case Digital_Unix:
//				break;
			case FreeBSD:
//				break;
			case HP_UX:
//				break;
			case Irix:
//				break;
			case MPEiX:
//				break;
			case Mac_OS:
//				break;
			case Mac_OS_X:
//				break;
			case NetWare_411:
//				break;
			case OS2:
//				break;
			case OS390:
//				break;
			case OSF1:
//				break;
			case OpenVMS:
//				break;
			case Others:
//				break;
			case Solaris:
//				break;
			case SunOS:
//				break;
			default:
				collector = new WindowsCollector();
				break;
		}
	}

	public static InfoCollector getInstance(){
		
		synchronized(InfoCollector.class){
			
			if(self == null){
				self = new InfoCollector();
			}
		}
		
		return self;
	}
	
	public String getCpuInfo(){
		
		return this.collector.getHardwareInfo(Hardware_Type.CPU);
	}
	
	public String getMemInfo(){
		
		return this.collector.getHardwareInfo(Hardware_Type.Memory);
	}
	
	public String getDskInfo(){
		
		return this.collector.getHardwareInfo(Hardware_Type.Disk);
	}
	
	public String getMacInfo(){
		
		return this.collector.getHardwareInfo(Hardware_Type.Mac);
	}
}
