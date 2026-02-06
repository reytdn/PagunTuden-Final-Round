import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PatientsDemographicsInfo {
    private static int nextID = 1001;

    private int patientID;
    private String fname;
    private String mname;
    private String lname;
    private int age;
    private String sex;

    private String city;
    private String province;

    private String dateOfCollection;
    private String timeOfCollection;

    private String timeOfLastMeal;
    private String amorpm;
    private String requestingPhysician;

    public PatientsDemographicsInfo(String fname, String mname, String lname, int age, String sex, String city, String province, String timeOfLastMeal, String amorpm,  String requestingPhysician) {

        this.patientID = nextID++; 

        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.age = age;
        this.sex = sex;

        this.city = city;
        this.province = province;

        this.dateOfCollection = LocalDate.now().toString();

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
        this.timeOfCollection = LocalTime.now().format(timeFormat);
        
        this.timeOfLastMeal = timeOfLastMeal;
        this.amorpm = amorpm;
        this.requestingPhysician = requestingPhysician;
    }

    public String toString() {
        return "\nPatient ID: " + patientID +
               "\nPatient Name: " + fname + " " + mname + " " + lname +
               "\nAge: " + age +
               "\nSex: " + sex +
               "\nAddress: " + province + "," + city +
               "\nDate of Collection: " + dateOfCollection +
               "\nTime of Collection: " + timeOfCollection + 
               "\nTime of Last Meal: " + timeOfLastMeal + " " + amorpm +
               "\nRequesting Physician: " + requestingPhysician;
    }
}