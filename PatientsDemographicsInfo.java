import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//This class stores all the demographic information of the patient
//*NAME
//*ADDRESS
//*SEX
//*AGE
//*DATE AND TIME OF COLLECTION
//*TIME OF LAST MEAL
//*REQUESTING PHYSICIAN
public class PatientsDemographicsInfo {

    //This automatically sets a unique ID starting from 1001 and if added new patient it'll be 1002
    //But this feature is not that relevant as of now for our program
    //Because our program implements only 1Patient Multiple Tests
    private static int nextID = 1001;

    private int patientID;
    private String fname;
    private String mname;
    private String lname;
    private int age;
    private String sex;

    private String city;
    private String province;
    private String baranggay;

    private String dateOfCollection;
    private String timeOfCollection;

    private String timeOfLastMeal;
    private String amorpm;
    private String requestingPhysician;

    public PatientsDemographicsInfo(String fname, String mname, String lname,
            int age, String sex, String city, String province, String baranggay,
            String timeOfLastMeal, String amorpm, String requestingPhysician) {

        this.patientID = nextID++;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.age = age;

        
        this.sex = sex.trim().toUpperCase().startsWith("M") ? "M" : "F";

        this.city = city;
        this.province = province;
        this.baranggay = baranggay;

        this.dateOfCollection = LocalDate.now().toString();
        this.timeOfCollection = LocalTime.now()
                .format(DateTimeFormatter.ofPattern("hh:mm a"));

        this.timeOfLastMeal = timeOfLastMeal;
        this.amorpm = amorpm;
        this.requestingPhysician = requestingPhysician;
    }

    //Returns the patient's sex
    //Because Sex of the patient is relevant in other LabTest 
    //The inputted sex of the patient is used in the LabTestMenuSystem
    public String getSex() {
        return sex;
    }

    
    public String toString() {
        return "\nPatient ID: " + patientID +
               "\nPatient Name: " + fname + " " + mname + " " + lname +
               "\nAge: " + age +
               "\nSex: " + sex +
               "\nAddress: " + province + ", " + city + ", " + baranggay +
               "\nDate of Collection: " + dateOfCollection +
               "\nTime of Collection: " + timeOfCollection +
               "\nTime of Last Meal: " + timeOfLastMeal + " " + amorpm +
               "\nRequesting Physician: " + requestingPhysician;
    }
}