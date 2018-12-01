// Esraa Mohammed amin allouh _1616810 _ GA_ P4

package fcitcarclean;

public class FCITvoucher {
    private int arrivalTime;
    private int ID;
    private String firstName;
    private String lastName;
    private String code;
    private int timeStarted;
    private int timeFinished;
    private FCITvoucher next;

    public FCITvoucher() {
    }

    
    
    public FCITvoucher(int arrivalTime, int ID, String firstName, String lastName, String code, int timeFinished ,int startsurv , FCITvoucher next) {
        this.arrivalTime = arrivalTime;
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
        this.timeStarted = startsurv;
        this.timeFinished = timeFinished;
        this.next = next;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCode() {
        return code;
    }

    public int getTimeStarted() {
        return timeStarted;
    }

    public int getTimeFinished() {
        return timeFinished;
    }

    public FCITvoucher getNext() {
        return next;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTimeStarted(int timeStarted) {
        this.timeStarted = timeStarted;
    }

    public void setTimeFinished(int timeFinished) {
        this.timeFinished = timeFinished;
    }

    public void setNext(FCITvoucher next) {
        this.next = next;
    }



}
