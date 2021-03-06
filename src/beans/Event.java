package beans;

import java.util.ArrayList;
import java.util.Random;

public class Event{

    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private int eventId;
    private int heldIn;
    private String activityName;
    private long startTime;
    private long endTime;
    private int numberLimit;
    private ArrayList<String> userList = new ArrayList<String>();
    private int preferedLocation;
    private double distance;
    private int numberLimitTo;
    private String address;
    private float latitude;
    private float longitude;
    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     */
    public Event(
		int heldInIn,
        String activityNameIn,
        long startTimeIn,
        long endTimeIn,
        int numberLimitIn,
        int numberLimitToIn) 
    {
        this.heldIn = heldInIn;
        this.activityName = activityNameIn;
        this.startTime = startTimeIn;
        this.endTime = endTimeIn;
        this.numberLimit = numberLimitIn;
        this.numberLimitTo = numberLimitToIn;
        eventId=new Random().nextInt(Integer.MAX_VALUE);
    }
    public Event(){}
    public ArrayList<String> getUserList(){
    	return userList;
    }
    public int getPreferedLocation(){
		return preferedLocation;
    }
    public double getDistance(){
    	return distance;
    }
    public void setDistance(double distance){
    	this.distance=distance;
    }
    public void setPreferedLocation(int preferedLocation){
    	this.preferedLocation=preferedLocation;
    }
    public int getEventId() {
          return this.eventId;
    }
    public void setEventId(int eventIdIn) {
          this.eventId = eventIdIn;
    }
    public int getHeldIn() {
          return this.heldIn;
    }
    public void setHeldIn(int heldInIn) {
          this.heldIn = heldInIn;
    }

    public String getActivityName() {
          return this.activityName;
    }
    public void setActivityName(String activityNameIn) {
          this.activityName = activityNameIn;
    }

    public long getStartTime() {
          return this.startTime;
    }
    public void setStartTime(long startTimeIn) {
          this.startTime = startTimeIn;
    }

    public long getEndTime() {
          return this.endTime;
    }
    public void setEndTime(long endTimeIn) {
          this.endTime = endTimeIn;
    }

    public int getNumberLimit() {
          return this.numberLimit;
    }
    public int getNumberLimitTo() {
        return this.numberLimitTo;
    }
    public void setNumberLimit(int numberLimitIn) {
          this.numberLimit = numberLimitIn;
    }
    public void setNumberLimitTo(int numberLimitToIn) {
        this.numberLimitTo = numberLimitToIn;
    }
    public void setLocation(String address,float lat, float longi){
    	this.address=address;
    	this.latitude=lat;
    	this.longitude=longi;
    }
    public String getAddress(){
    	return address;
    }
    public float getLatitude(){
    	return latitude;
    }
    public float getLongitude(){
    	return longitude;
    }
    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variales, without going trough the 
     * individual set-methods.
     */

    public void setAll(int eventIdIn,
          int heldInIn,
          String activityNameIn,
          long startTimeIn,
          long endTimeIn,
          int numberLimitIn,
          int numberLimitToIn) {
          this.eventId = eventIdIn;
          this.heldIn = heldInIn;
          this.activityName = activityNameIn;
          this.startTime = startTimeIn;
          this.endTime = endTimeIn;
          this.numberLimit = numberLimitIn;
          this.numberLimitTo = numberLimitToIn;
    }
}