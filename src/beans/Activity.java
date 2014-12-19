package beans;

import java.io.Serializable;

public class Activity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String type;
	public void setName(String name){
		this.name=name;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getName(){
		return name;
	}
	public String getType(){
		return type;
	}
	public void setAll(String name,String type){
		this.name=name;
		this.type=type;
	}
}