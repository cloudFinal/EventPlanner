package beans;

public class Preference{

    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private String userId;
    private int locationId;
    private String preferenceName;
    private double distanceTolerance;
    private long startTime;
    private long endTime;
    private String keyWord;
    private String activityName;
    private int numberLimitFrom;
    private int numberLimitTo;
    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     */

    public String getUserId() {
          return this.userId;
    }
    public void setUserId(String userIdIn) {
          this.userId = userIdIn;
    }
    public int getLocationId() {
          return this.locationId;
    }
    public void setLocationId(int locationIdIn) {
          this.locationId = locationIdIn;
    }

    public String getPreferenceName() {
          return this.preferenceName;
    }
    public void setPreferenceName(String preferenceNameIn) {
          this.preferenceName = preferenceNameIn;
    }

    public double getDistanceTolerance() {
          return this.distanceTolerance;
    }
    public void setDistanceTolerance(double distanceToleranceIn) {
          this.distanceTolerance = distanceToleranceIn;
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

    public String getKeyWord() {
          return this.keyWord;
    }
    public void setKeyWord(String keyWordIn) {
          this.keyWord = keyWordIn;
    }

    public String getActivityName() {
          return this.activityName;
    }
    public void setActivityName(String activityNameIn) {
          this.activityName = activityNameIn;
    }
    public void setNumberLimitFrom(int numberLimitFrom){
    	this.numberLimitFrom=numberLimitFrom;
    }
    public void setNumberLimitTo(int numberLimitTo){
    	this.numberLimitTo=numberLimitTo;
    }
    public int getNumberLimitFrom(){
    	return numberLimitFrom;
    }
    public int getNumberLimitTo(){
    	return numberLimitTo;
    }
    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variales, without going trough the 
     * individual set-methods.
     */

    public void setAll(String userIdIn,
          String preferenceNameIn,
          int locationIdIn,
          double distanceToleranceIn,
          long startTimeIn,
          long endTimeIn,
          String keyWordIn,
          String activityNameIn,
          int numberLimitFrom,
          int numberLimitTo) {
          this.userId = userIdIn;
          this.locationId = locationIdIn;
          this.preferenceName = preferenceNameIn;
          this.distanceTolerance = distanceToleranceIn;
          this.startTime = startTimeIn;
          this.endTime = endTimeIn;
          this.keyWord = keyWordIn;
          this.activityName = activityNameIn;
          this.numberLimitFrom=numberLimitFrom;
          this.numberLimitTo=numberLimitTo;
    }
}