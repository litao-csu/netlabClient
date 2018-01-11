package Model;

import java.io.IOException;

public interface Collector {

	abstract String getCmd(Hardware_Type ht);
	abstract String getInfo(String cmd) throws IOException;
	public String getHardwareInfo(Hardware_Type ht);
}
