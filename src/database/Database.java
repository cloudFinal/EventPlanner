package database;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import javax.sql.DataSource;

import parse.Parse;

import com.google.gson.Gson;

import logic.SearchForEvent;
import Servlet.Center;
import beans.*;

public class Database
{
    String jdbcUrl ="jdbc:mysql://cloudfinal.cvdk3xvzipiw.us-east-1.rds.amazonaws.com:3306/cloudFinal";
    static Connection conn;
	private Statement stmt;
	//private Query query;
	public Database(){
		try {
			connect();
			/*test();
			for(String s:test()){
				System.out.println(s);
			}*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void connect() throws SQLException{
		try {
			Class.forName ("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userid = "final";
	    String password = "88522712";
	    conn = DriverManager.getConnection (jdbcUrl,userid,password);
    }
	public void close(){
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
	public ArrayList<String> test() throws SQLException{
			stmt=conn.createStatement();
			ArrayList<String> result = new ArrayList<String>();
			ResultSet rset = stmt.executeQuery("show tables");
			while(rset.next()){
				result.add(rset.getString(1));
			}
			return result;
	}
	public ArrayList<Activity> findActivityList(){
    	ArrayList<Activity> activityList = new ArrayList<Activity>();
    	try{
    		stmt=conn.createStatement();
    		ResultSet rset =stmt.executeQuery("select activity_name,activity_type from activity");
    		while(rset.next()){
    			Activity curr = new Activity();
    			curr.setAll(rset.getString(1),rset.getString(2));
				activityList.add(curr);
			}
    	}catch(SQLException e){
			e.printStackTrace();
		}
    	return activityList;
    }
	public boolean login(String username,String password){
		try{
			stmt=conn.createStatement();
			ResultSet rset = stmt.executeQuery("select * from users where user_id='"+username+"' and password='"+password+"'");
			if(rset.next()){
				setOnline(username,true);
				return true;
			}else{
				return false;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	public boolean userExist(String username,String password){
		try{
			stmt=conn.createStatement();
			ResultSet rset = stmt.executeQuery("select * from users where user_id='"+username+"' and password='"+password+"'");
			if(rset.next()){
				return true;
			}else{
				return false;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	public boolean setOnline(String username,boolean ifOnline){
		try {
			stmt=conn.createStatement();
			String t = (ifOnline?"y":"n");
			stmt.execute(QueryGenerator.updateQ("users","online","String",t)+QueryGenerator.whereQ("user_id","String",username));
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	public boolean isOnline(String username){
		ResultSet rset;
		try {
			stmt=conn.createStatement();
			rset = stmt.executeQuery("select online from users where user_id='"+username+"'");
			if(rset.next()){
				String r = rset.getString(1);
				if(r.equals("y")){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	//select queries
	public Event getEvent(int eventId){
		try{
			stmt=conn.createStatement();
			ResultSet rset = stmt.executeQuery("select * from event where event_id="+eventId);
			if(rset.next()){
				Event e=new Event();
				e.setAll(rset.getInt(1), rset.getInt(2), rset.getString(3), rset.getLong(4),rset.getLong(5),rset.getInt(6),rset.getInt(7));
				return e;
			}else{
				return null;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	public Preference getPreference(String userId,String preferenceName){
		try{
			stmt=conn.createStatement();
			ResultSet rset = stmt.executeQuery("select * from prefer_at where preference_name='"+preferenceName+"' and user_id='"+userId+"'");
			if(rset.next()){
				Preference p=new Preference();
				p.setAll(rset.getString(1),rset.getString(2),rset.getInt(3),rset.getDouble(4),rset.getLong(5),rset.getLong(6),rset.getString(7),rset.getString(8),rset.getInt(9),rset.getInt(10));
				return p;
			}else{
				return null;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	public Preference getPreference(String userId,int eventId){
		try{
			stmt=conn.createStatement();
			ResultSet rset = stmt.executeQuery("select P1.user_id,P1.preference_name,P1.location_id,P1.distance_tolerance,P1.start_time,P1.end_time,P1.key_word,P1.activity_name,P1.number_limit_from,P1.number_limit_to from prefer_at P1,participates_in P2 where P2.event_id="+eventId+" and P2.user_id='"+userId+"' and P2.preference_name=P1.preference_name");
			if(rset.next()){
				Preference p=new Preference();
				p.setAll(rset.getString(1),rset.getString(2),rset.getInt(3),rset.getDouble(4),rset.getLong(5),rset.getLong(6),rset.getString(7),rset.getString(8),rset.getInt(9),rset.getInt(10));
				return p;
			}else{
				return null;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	public ArrayList<Preference> getPreferenceFromUserId(String userId){
		ArrayList<Preference> preferenceList = new ArrayList<Preference>();
		try{
			stmt=conn.createStatement();
			ResultSet rset = stmt.executeQuery("select * from prefer_at where user_id='"+userId+"'");
			while(rset.next()){
				Preference p=new Preference();
				p.setAll(rset.getString(1),rset.getString(2),rset.getInt(3),rset.getDouble(4),rset.getLong(5),rset.getLong(6),rset.getString(7),rset.getString(8),rset.getInt(9),rset.getInt(10));
				preferenceList.add(p);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			return preferenceList;
		}
		return preferenceList;
	}
	public Location getLocation(int locationId){
		try{
			if(Center.locationInfo.containsKey(locationId)){
				return Center.locationInfo.get(locationId);
			}
			//System.out.println("here");
			stmt=conn.createStatement();
			ResultSet rset = stmt.executeQuery("select * from location where location_id="+locationId);
			if(rset.next()){
				Location l=new Location();
				l.setAll(rset.getInt(1), rset.getString(2), rset.getFloat(3), rset.getFloat(4));
				Center.locationInfo.put(locationId,l);
				return l;
			}else{
				return null;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	public ArrayList<Event> getEventAtPrefer(Preference preference){
		ArrayList<Event> waitingList = new ArrayList<Event>();
		try{
			ArrayList<Event> events = new ArrayList<Event>();
			stmt=conn.createStatement();
			ResultSet rset = stmt.executeQuery("select * from event where activity_name='"+preference.getActivityName()+"' and start_time<"+preference.getEndTime()+" and end_time>"+preference.getStartTime()+" and number_limit_from<="+preference.getNumberLimitTo()+" and number_limit_to>="+preference.getNumberLimitFrom());
			while(rset.next()){
				Event e=new Event();
				e.setAll(rset.getInt(1),rset.getInt(2),rset.getString(3),rset.getLong(4),rset.getLong(5),rset.getInt(6),rset.getInt(7));
				events.add(e);
				//System.out.println("!!!"+e.getActivityName());
			}
			for(Event event:events){
				if(SearchForEvent.testPreferenceWithOthers(preference, event)!=null){
					waitingList.add(event);
				}
				//for(Preference)
			}
			if(waitingList.size()==0){
				Event event = new Event(preference.getLocationId(),preference.getActivityName(),preference.getStartTime(),preference.getEndTime(),preference.getNumberLimitFrom(),preference.getNumberLimitTo());
				waitingList.add(event);
			}
			for(Event e:waitingList){
				Location l= Center.db.getLocation(e.getHeldIn());
				e.setLocation(l.getAddress(), l.getY(), l.getY());
			}
			return waitingList;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			return waitingList;
		}
	}
	public int numberInEvent(Event event){
		try {
		stmt=conn.createStatement();
		ResultSet rset = stmt.executeQuery("select count(*) from participates_in where event_id="+event.getEventId());
		if(rset.next()){
				return rset.getInt(1);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public ArrayList<Preference> getRelatedPreference(Event event){
		try{
			ArrayList<Preference> ps = new ArrayList<Preference>();
			stmt=conn.createStatement();
			ResultSet rset = stmt.executeQuery("select P1.user_id,P1.preference_name,P1.location_id,P1.distance_tolerance,P1.start_time,P1.end_time,P1.key_word,P1.activity_name,P1.number_limit_from,P1.number_limit_to from prefer_at P1,participates_in P2 where P2.event_id="+event.getEventId()+" and P1.user_id=P2.user_id and P1.preference_name=P2.preference_name");
			while(rset.next()){
				Preference p=new Preference();
				p.setAll(rset.getString(1),rset.getString(2),rset.getInt(3),rset.getDouble(4),rset.getLong(5),rset.getLong(6),rset.getString(7),rset.getString(8),rset.getInt(9),rset.getInt(10));
				ps.add(p);
			}
			return ps;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	//insert queries
	public boolean register(String username,String password){
		try {
			stmt=conn.createStatement();
			stmt.execute(QueryGenerator.insertQ("users","user_id","String",username,"password","String",password));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	public boolean updateEvent(Event event){
		try {
			stmt=conn.createStatement();
			stmt.execute("update event set held_in="+event.getHeldIn()+", start_time="+event.getStartTime()+", end_time="+event.getEndTime()+", number_limit_from="+event.getNumberLimit()+", number_limit_to="+event.getNumberLimitTo()+" where event_id="+event.getEventId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean insertLocation(Location location){
		try {
			stmt=conn.createStatement();
			stmt.execute(QueryGenerator.insertQ("location","location_id","Integer",String.valueOf(location.getLocationid()),"address","String",String.valueOf(location.getAddress()),"x_coordinate","Float",String.valueOf(location.getX()),"y_coordinate","Float",String.valueOf(location.getY())));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	public boolean insertPreference(Preference preference){
		try {
			stmt=conn.createStatement();
			stmt.execute(QueryGenerator.insertQ("prefer_at","user_id","String",preference.getUserId(),"preference_name","String",preference.getPreferenceName(),"location_id","Integer",String.valueOf(preference.getLocationId()),"distance_tolerance","float",String.valueOf(preference.getDistanceTolerance()),"start_time","Long",String.valueOf(preference.getStartTime()),"end_time","Long",String.valueOf(preference.getEndTime()),"key_word","String",preference.getKeyWord(),"activity_name","String",preference.getActivityName(),"number_limit_from","Integer",String.valueOf(preference.getNumberLimitFrom()),"number_limit_to","Integer",String.valueOf(preference.getNumberLimitTo())));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	public boolean insertEvent(Event event){
		try {
			stmt=conn.createStatement();
			stmt.execute(QueryGenerator.insertQ("event","event_id","Integer",String.valueOf(event.getEventId()),"held_in","Integer",String.valueOf(event.getHeldIn()),"activity_name","String",event.getActivityName(),"start_time","Long",String.valueOf(event.getStartTime()),"end_time","Long",String.valueOf(event.getEndTime()),"number_limit_from","Integer",String.valueOf(event.getNumberLimit()),"number_limit_to","Integer",String.valueOf(event.getNumberLimitTo())));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	public boolean insertParticipatesIn(Preference preference, Event event){
		try {
			stmt=conn.createStatement();
			stmt.execute(QueryGenerator.insertQ("participates_in","event_id","Integer",String.valueOf(event.getEventId()),"user_id","String",preference.getUserId(),"preference_name","String",preference.getPreferenceName()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	public boolean deleteParticipatesIn(String userId,int eventId){
		try {
			stmt=conn.createStatement();
			stmt.execute("delete from participates_in where user_id='"+userId+"' and event_id="+eventId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	public boolean deleteEvent(int eventId){
		try {
			stmt=conn.createStatement();
			stmt.execute("delete from event where event_id="+eventId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	public boolean joinEvent(Preference preference, Event event){
		Location l= SearchForEvent.testPreferenceWithOthers(preference, event);
		if(l!=null){
			event.setHeldIn(l.getLocationid());
			event.setEndTime(Math.min(preference.getEndTime(),event.getEndTime()));
			event.setStartTime(Math.max(preference.getStartTime(),event.getStartTime()));
			event.setNumberLimit((int)Math.max(preference.getNumberLimitFrom(),event.getNumberLimit()));
			event.setNumberLimitTo((int)Math.min(preference.getNumberLimitTo(),event.getNumberLimitTo()));
			if(event.getStartTime()>event.getEndTime()){
				return false;
			}
			if(Center.db.numberInEvent(event)<event.getNumberLimitTo()){
				return (insertParticipatesIn(preference, event)&&updateEvent(event));
			}else{
				return false;
			}
		}else{
			//System.out.println("result!1!!!!!");
			return false;
		}
	}
	public boolean updateProfile(Profile profile){
		try {
			stmt=conn.createStatement();
			stmt.execute(QueryGenerator.updateQ("users","password","String",profile.getPassword(),"name","String",profile.getName(),"date_of_birth","Integer",String.valueOf(profile.getDateOfBirth()),"nationality","String",profile.getNationality(),"gender","String",profile.getGender(),"location_id","Integer",String.valueOf(profile.getLocationId()),"image","String",profile.getImage())+" where user_id='"+profile.getUserid()+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	public Profile getProfile(String userId){
		try{
			stmt=conn.createStatement();
			ResultSet rset = stmt.executeQuery("select * from users where user_id='"+userId+"'");
			if(rset.next()){
				Profile p=new Profile();
				p.setAll(rset.getString(1), rset.getString(2), rset.getString(3), rset.getLong(4), rset.getString(5), rset.getString(6), rset.getInt(7), rset.getString(8), rset.getString(9));
				return p;
			}else{
				return null;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	public boolean leaveEvent(String userId,int eventId){
		Preference preference = Center.db.getPreference(userId, eventId);
		Event event = Center.db.getEvent(eventId);
		ArrayList<Preference> list = getRelatedPreference(event);
		if(list.size()==1){
			return(deleteParticipatesIn(userId,eventId)&&deleteEvent(eventId));
		}
		Preference removed = null;
		for(Preference p:list){
			if(p.getPreferenceName().equals(preference.getPreferenceName())&&p.getUserId().equals(preference.getUserId())){
				removed=p;
			}
		}
		list.remove(removed);
		Integer locationId = null;
		for(Preference p1:list){
			if(SearchForEvent.testPreferenceWithOthers(p1, list)){
				locationId=p1.getLocationId();
				break;
			}
		}
		if(locationId!=null){
			if(deleteParticipatesIn(userId,eventId)){
				long startTime=Long.MIN_VALUE;
				long endTime=Long.MAX_VALUE;
				int numberLimitFrom=Integer.MIN_VALUE;
				int numberLimitTo=Integer.MAX_VALUE;
				for(Preference p:list){
					if(p.getStartTime()>startTime){startTime=p.getStartTime();}
					if(p.getEndTime()<endTime){endTime=p.getEndTime();}
					if(p.getNumberLimitFrom()>numberLimitFrom){numberLimitFrom=p.getNumberLimitFrom();}
					if(p.getNumberLimitTo()<numberLimitTo){numberLimitTo=p.getNumberLimitTo();}
				}
				try {
					stmt=conn.createStatement();
					stmt.execute("update event set held_in="+locationId+", start_time="+startTime+", end_time="+endTime+", number_limit_from="+numberLimitFrom+", number_limit_to="+numberLimitTo+" where event_id="+eventId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
