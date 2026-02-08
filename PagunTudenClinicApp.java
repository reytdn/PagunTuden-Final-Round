import java.util.Scanner;

//Main Application Class of the PagunTuden Clinic
//Purpose of this is to collect the patients information and then starts the labtest
public class PagunTudenClinicApp {


    public static void main(String[] args) {

        //Scanner for asking inputs from the user
        Scanner input = new Scanner(System.in);

        //Next line of codes are just asking for the patients demographics
        //Then returns the value to the PatientsDemographicsInfo class       
        System.out.print("Enter First Name: ");
        String fname = input.nextLine();

        System.out.println("");
        System.out.print("Enter Middle Name: ");
        String mname = input.nextLine();

        System.out.println("");
        System.out.print("Enter Last Name: ");
        String lname = input.nextLine();

        System.out.println("");
        System.out.print("Enter Age: ");
        int age = input.nextInt();
        input.nextLine(); 
        
        System.out.println("");
        System.out.print("Enter Sex (F/M): ");
        String sex = input.nextLine();

        System.out.println("");
        System.out.print("Enter Province: ");
        String province = input.nextLine();

        System.out.println("");
        System.out.print("Enter City: ");
        String city = input.nextLine();

        System.out.println("");
        System.out.print("Enter Baranggay: ");
        String baranggay = input.nextLine();

        System.out.println("");
        System.out.print("Enter Time of Last Meal: ");
        String lastMeal = input.nextLine();

        System.out.println("");
        System.out.print("Enter (AM/PM): ");
        String amorpm = input.nextLine();

        System.out.println("");
        System.out.print("Enter Requesting Physician: ");
        String physician = input.nextLine();

        //This part stores all the gathered details to the PatientsDemographicsInfo class
        //This part also we create an object named demoinfo with all the collected data      
        PatientsDemographicsInfo demoinfo =
                new PatientsDemographicsInfo(
                        fname, mname, lname,
                        age, sex,
                        city, province, baranggay,
                        lastMeal, amorpm, physician
                );
        
        //Prints out the patient's demographic information
        System.out.println("\n===== PATIENT INFORMATION =====");
        System.out.println(demoinfo);

        //Creates a new LabTestMenuSystem object called menu
        //Passes the patient's inputted sex to ensure sex-specific ranges are used in some tests
        //*HDL
        //*Triglycerides
        //*Creatinine
        //*Uric Acid
        //Calls startLabMenu to proceed on LabTesting      
        LabTestMenuSystem menu =
                new LabTestMenuSystem(demoinfo.getSex());
        menu.startLabMenu();

        input.close();
    }
}