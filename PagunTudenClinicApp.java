import java.util.Scanner;

public class PagunTudenClinicApp{
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        System.out.print("Enter First Name: ");
        String fname = input.nextLine();

        System.out.print("Enter Middle Name: ");
        String mname = input.nextLine();

        System.out.print("Enter Last Name: ");
        String lname = input.nextLine();

        System.out.print("Enter Age: ");
        int age = input.nextInt();

        System.out.print("Enter Sex (F/M): ");
        String sex = input.nextLine();

        PatientInfo Info = new PatientInfo(fname, mname, lname, age, sex);

        input.close();
    }
}