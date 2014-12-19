package beans;

import java.awt.Image;
import java.awt.image.BufferedImage;
public class Profile {
	private String userid;
	private String password;
	private String name;
	private long dateOfBirth;
	private String nationality;
	private String gender;
	private Integer locationId;
	private String image;
	private String online;
	public String getUserid(){
		return userid;
	}
	public String getOnline(){
		return online;
	}
	public String getPassword(){
		return password;
	}
	public String getName(){
		return name;
	}
	public long getDateOfBirth(){
		return dateOfBirth;
	}
	public String getNationality(){
		return nationality;
	}
	public String getGender(){
		return gender;
	}
	public String getImage(){
		return image;
	}
	public Integer getLocationId(){
		return locationId;
	}
	public void setUserid(String userid){
		this.userid=userid;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setOnline(String online){
		this.online=online;
	}
	public void setDateOfBirth(long dateOfBirth){
		this.dateOfBirth=dateOfBirth;
	}
	public void setNationality(String nationality){
		this.nationality=nationality;
	}
	public void setGender(String gender){
		this.gender=gender;
	}
	public void setImage(String image){
		this.image=image;
	}
	public void setLocationId(int locationId){
		this.locationId=locationId;
	}
	public void setAll(	String userid,
	String password,
	String name,
	long dateOfBirth,
	String nationality,
	String gender,
	Integer locationId,
	String image,
	String online){
		 this.userid=userid;
		 this.password=password;
		 this.name=name;
		 this.dateOfBirth=dateOfBirth;
		 this.nationality=nationality;
	     this.gender=gender;
		 this.locationId=locationId;
		 this.image=image;
		 this.online=online;
	}
}
