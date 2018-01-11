package Model;

public class PCInfo {
	
	protected String cpu_info;
	protected String mem_info;
	protected String dsk_info;
	protected String mac_info;
	
	public PCInfo(String cpu_info, String mem_info, String dsk_info,
			String mac_info) {
		super();
		this.cpu_info = cpu_info;
		this.mem_info = mem_info;
		this.dsk_info = dsk_info;
		this.mac_info = mac_info;
	}

	public String getCpu_info() {
		return cpu_info;
	}

	public void setCpu_info(String cpu_info) {
		this.cpu_info = cpu_info;
	}

	public String getMem_info() {
		return mem_info;
	}

	public void setMem_info(String mem_info) {
		this.mem_info = mem_info;
	}

	public String getDsk_info() {
		return dsk_info;
	}

	public void setDsk_info(String dsk_info) {
		this.dsk_info = dsk_info;
	}

	public String getMac_info() {
		return mac_info;
	}

	public void setMac_info(String mac_info) {
		this.mac_info = mac_info;
	}
	
}
