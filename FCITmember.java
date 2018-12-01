// Esraa Mohammed amin allouh _1616810 _ GA_ P4


package fcitcarclean;


public class FCITmember {

     private int arrivalTime;
    private int timeStarted;
    private int ID;
    private String firstName;
    private String lastName;
    private String code;
    private int minutesRemaining;
    private FCITmember next;

    
    
    public FCITmember() {
        
    }

    public FCITmember(int arrivalTime, int ID, String firstName, String lastName, String code) {
        this.arrivalTime = arrivalTime;
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
        this.next = null;
    }

    public FCITmember(int arrivalTime, int ID, String firstName, String lastName, String code , int timeStarted , int minutesRemaining) {
        this.arrivalTime = arrivalTime;
        this.timeStarted = timeStarted;
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
        this.minutesRemaining = minutesRemaining;
       this.next = null;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(int timeStarted) {
        this.timeStarted = timeStarted;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getMinutesRemaining() {
        return minutesRemaining;
    }

    public void setMinutesRemaining(int minutesRemaining) {
        this.minutesRemaining = minutesRemaining;
    }

    public FCITmember getNext() {
        return next;
    }

    public void setNext(FCITmember next) {
        this.next = next;
    }
    
    
    
}
