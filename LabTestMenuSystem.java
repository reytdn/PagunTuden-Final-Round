import java.util.Scanner;

public class LabTestMenuSystem {

    private SelectedLabTests labTests;

    public LabTestMenuSystem() {
        labTests = new SelectedLabTests();
    }

    // ✅ This is the method you must call from App
    public void startLabMenu() {

        Scanner input = new Scanner(System.in);
        int choice = 0;

        while (choice != 16) {

            displayMenu();
            choice = input.nextInt();

            processChoice(choice, input);
        }

        System.out.println("Program Ended.");
    }

    private void displayMenu() {

        System.out.println("\n===== LAB TEST MAIN MENU =====");
        System.out.println("1 - FBS Test");
        System.out.println("2 - RBS Test");
        System.out.println("3 - Total Cholesterol Test");
        System.out.println("5 - Triglycerides Test");
        System.out.println("6 - Creatinine Test");
        System.out.println("7 - Uric Acid Test");
        System.out.println("8 - AST/SGOT Test");
        System.out.println("9 - ALT/SGPT Test");
        System.out.println("10 - Sodium Test");
        System.out.println("11 - Potassium Test");
        System.out.println("12 - Chloride Test");
        System.out.println("13 - TCalcium Test");
        System.out.println("14 - iCalcium Test");
        System.out.println("15 - Process Result");
        System.out.println("16 - Exit");

        System.out.print("Choose Test: ");
    }

    private void processChoice(int choice, Scanner input) {

        if (choice == 1) {
            System.out.print("Enter FBS Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.glucoseToSI(value);

            labTests.addTestResult("FBS", value, si, "mg/dL", "mmol/L");
        }

        else if (choice == 2) {
            System.out.print("Enter RBS Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.glucoseToSI(value);

            labTests.addTestResult("RBS", value, si, "mg/dL", "mmol/L");
        }

        else if (choice == 3) {
            System.out.print("Enter Total Cholesterol Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.cholesterolToSI(value);

            labTests.addTestResult("Total Cholesterol", value, si, "mg/dL", "mmol/L");
        }

        else if (choice == 5) {
            System.out.print("Enter Triglycerides Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.triglyceridesToSI(value);

            labTests.addTestResult("Triglycerides", value, si, "mg/dL", "mmol/L");
        }

        else if (choice == 6) {
            System.out.print("Enter Creatinine Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.creatinineToSI(value);

            labTests.addTestResult("Creatinine", value, si, "mg/dL", "µmol/L");
        }

        else if (choice == 7) {
            System.out.print("Enter Uric Acid Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.uricAcidToSI(value);

            labTests.addTestResult("Uric Acid", value, si, "mg/dL", "mmol/L");
        }

        else if (choice == 8) {
            System.out.print("Enter AST/SGOT Value (U/L): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.enzymeToSI(value);

            labTests.addTestResult("AST/SGOT", value, si, "U/L", "µkat/L");
        }

        else if (choice == 9) {
            System.out.print("Enter ALT/SGPT Value (U/L): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.enzymeToSI(value);

            labTests.addTestResult("ALT/SGPT", value, si, "U/L", "µkat/L");
        }

        else if (choice == 10) {
            System.out.print("Enter Sodium Value (mEq/L): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.electrolyteToSI(value);

            labTests.addTestResult("Sodium", value, si, "mEq/L", "mmol/L");
        }

        else if (choice == 11) {
            System.out.print("Enter Potassium Value (mEq/L): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.electrolyteToSI(value);

            labTests.addTestResult("Potassium", value, si, "mEq/L", "mmol/L");
        }

        else if (choice == 12) {
            System.out.print("Enter Chloride Value (mEq/L): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.electrolyteToSI(value);

            labTests.addTestResult("Chloride", value, si, "mEq/L", "mmol/L");
        }
        else if (choice == 13) {
            System.out.print("Enter Total Calcium Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.totalCalciumToSI(value);

            labTests.addTestResult("Total Calcium", value, si, "mg/dL", "mmol/L");
        }
        else if (choice == 14) {
            System.out.print("Enter Ionized Calcium Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.ionizedCalciumToSI(value);

            labTests.addTestResult("Ionized Calcium", value, si, "mg/dL", "mmol/L");
        }

        else if (choice == 15) {
            labTests.displayResults();
        }

        else if (choice == 16) {
            System.out.println("Exiting Lab Menu...");
        }

        else {
            System.out.println("Invalid choice. Try again.");
        }
    }
}
