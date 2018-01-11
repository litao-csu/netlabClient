package Model;

public enum Hardware_Type {
	CPU("cpu"),  
    Memory("mem"),  
    Disk("disk"),  
    Mac("mac");  
      
    private Hardware_Type(String desc){  
        this.description = desc;  
    }  
      
    public String toString(){  
        return description;  
    }  
      
    private String description;  
}
