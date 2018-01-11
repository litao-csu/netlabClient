package Controller;

import Model.OS_Type;

public class OSTypeIdentifier {

	protected static OSTypeIdentifier os_identifier = null;
	protected static String OS = null;
	
	private OS_Type platform;
	private boolean isInitialized = false;
	
	private OSTypeIdentifier() {
		
		OS = System.getProperty("os.name").toLowerCase();
	}
	
	public static OSTypeIdentifier getInstance(){
		
		synchronized(OSTypeIdentifier.class){
			
			if(os_identifier == null){
				
				os_identifier = new OSTypeIdentifier();
			}
		}
		
		return os_identifier;
	}
	
	public OS_Type getOSname(){
		
		if(!isInitialized){
	        if(isAix()){  
	            os_identifier.platform = OS_Type.AIX;  
	        }else if (isDigitalUnix()) {  
	            os_identifier.platform = OS_Type.Digital_Unix;  
	        }else if (isFreeBSD()) {  
	            os_identifier.platform = OS_Type.FreeBSD;  
	        }else if (isHPUX()) {  
	            os_identifier.platform = OS_Type.HP_UX;  
	        }else if (isIrix()) {  
	            os_identifier.platform = OS_Type.Irix;  
	        }else if (isLinux()) {  
	            os_identifier.platform = OS_Type.Linux;  
	        }else if (isMacOS()) {  
	            os_identifier.platform = OS_Type.Mac_OS;  
	        }else if (isMacOSX()) {  
	            os_identifier.platform = OS_Type.Mac_OS_X;  
	        }else if (isMPEiX()) {  
	            os_identifier.platform = OS_Type.MPEiX;  
	        }else if (isNetWare()) {  
	            os_identifier.platform = OS_Type.NetWare_411;  
	        }else if (isOpenVMS()) {  
	            os_identifier.platform = OS_Type.OpenVMS;  
	        }else if (isOS2()) {  
	            os_identifier.platform = OS_Type.OS2;  
	        }else if (isOS390()) {  
	            os_identifier.platform = OS_Type.OS390;  
	        }else if (isOSF1()) {  
	            os_identifier.platform = OS_Type.OSF1;  
	        }else if (isSolaris()) {  
	            os_identifier.platform = OS_Type.Solaris;  
	        }else if (isSunOS()) {  
	            os_identifier.platform = OS_Type.SunOS;  
	        }else if (isWindows()) {  
	            os_identifier.platform = OS_Type.Windows;  
	        }else{  
	            os_identifier.platform = OS_Type.Others;  
	        }
	        
	        isInitialized = true;
		}
        return os_identifier.platform;  
    }  

	public static boolean isLinux(){  
        return OS.indexOf("linux")>=0;  
    }  
      
    public static boolean isMacOS(){  
        return OS.indexOf("mac")>=0&&OS.indexOf("os")>0&&OS.indexOf("x")<0;  
    }  
      
    public static boolean isMacOSX(){  
        return OS.indexOf("mac")>=0&&OS.indexOf("os")>0&&OS.indexOf("x")>0;  
    }  
      
    public static boolean isWindows(){  
        return OS.indexOf("windows")>=0;  
    }  
      
    public static boolean isOS2(){  
        return OS.indexOf("os/2")>=0;  
    }  
      
    public static boolean isSolaris(){  
        return OS.indexOf("solaris")>=0;  
    }  
      
    public static boolean isSunOS(){  
        return OS.indexOf("sunos")>=0;  
    }  
      
    public static boolean isMPEiX(){  
        return OS.indexOf("mpe/ix")>=0;  
    }  
      
    public static boolean isHPUX(){  
        return OS.indexOf("hp-ux")>=0;  
    }  
      
    public static boolean isAix(){  
        return OS.indexOf("aix")>=0;  
    }  
      
    public static boolean isOS390(){  
        return OS.indexOf("os/390")>=0;  
    }  
      
    public static boolean isFreeBSD(){  
        return OS.indexOf("freebsd")>=0;  
    }  
      
    public static boolean isIrix(){  
        return OS.indexOf("irix")>=0;  
    }  
      
    public static boolean isDigitalUnix(){  
        return OS.indexOf("digital")>=0&&OS.indexOf("unix")>0;  
    }  
      
    public static boolean isNetWare(){  
        return OS.indexOf("netware")>=0;  
    }  
      
    public static boolean isOSF1(){  
        return OS.indexOf("osf1")>=0;  
    }  
      
    public static boolean isOpenVMS(){  
        return OS.indexOf("openvms")>=0;  
    } 
}
