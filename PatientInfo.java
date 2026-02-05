import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PatientInfo {
    private static int nextID = 1001;

    private int patientID;
    private String fname;
    private String mname;
    private String lname;
    private int age;
    private String sex;

    private String address;
    private String city;
    private String province;

    private String dateOfCollection;
    private String timeOfCollection;

    private String timeOfLastMeal;
    private String requestingPhysician;

    public PatientInfo(String fname, String mname, String lname, int age, String sex,
                       String address, String city, String province,
                       String timeOfLastMeal, String requestingPhysician) {

        this.patientID = nextID++; 

        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.age = age;
        this.sex = sex;

        this.address = address;
        this.city = city;
        this.province = province;

        this.dateOfCollection = LocalDate.now().toString();

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
        this.timeOfCollection = LocalTime.now().format(timeFormat);

        this.timeOfLastMeal = timeOfLastMeal;
        this.requestingPhysician = requestingPhysician;
    }

    public String toString() {
        return "Patient ID: " + patientID +
               "Patient Name: " + fname + " " + mname + " " + lname +
               "Age: " + age +
               "Sex: " + sex +
               "Address: " + address +
               "City: " + city +
               "Province: " + province +
               "Date of Collection: " + dateOfCollection +
               "Time of Collection: " + timeOfCollection +
               "Time of Last Meal: " + timeOfLastMeal +
               "Requesting Physician: " + requestingPhysician;
    }
}