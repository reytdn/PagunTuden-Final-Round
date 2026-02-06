import java.util.Scanner;

public class LabTestMenuSystem {

    private SelectedLabTests labTests;
    private String sex;

    public LabTestMenuSystem(String sex) {
        labTests = new SelectedLabTests();
        this.sex = sex.toUpperCase();
    }

    public void startLabMenu() {

        Scanner input = new Scanner(System.in);
        int choice = 0;

        while (choice != 18) {

            displayMenu();
            choice = input.nextInt();

            processChoice(choice, input);
        }

        System.out.println("Program Ended.");
    }

    private void displayMenu() {

        System.out.println("\n===== LAB TEST MAIN MENU =====");
        System.out.println("1  - FBS Test");
        System.out.println("2  - RBS Test");
        System.out.println("3  - Total Cholesterol Test");
        System.out.println("4  - HDL Test");
        System.out.println("5  - LDL Test");
        System.out.println("6  - Triglycerides Test");
        System.out.println("7  - Creatinine Test");
        System.out.println("8  - Uric Acid Test");
        System.out.println("9  - BUN Test");
        System.out.println("10 - AST/SGOT Test");
        System.out.println("11 - ALT/SGPT Test");
        System.out.println("12 - Sodium Test");
        System.out.println("13 - Potassium Test");
        System.out.println("14 - Chloride Test");
        System.out.println("15 - Total Calcium Test");
        System.out.println("16 - Ionized Calcium Test");
        System.out.println("17 - Process Result");
        System.out.println("18 - Exit");
        System.out.println();

        System.out.print("Choose Test: ");
    }

    private void processChoice(int choice, Scanner input) {

        if (choice == 1) {
            System.out.print("Enter FBS Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.glucoseToSI(value);

            double low = 3.9;
            double high = 5.6;

            String interpretation = rangeFandM.interpret(si, low, high);

            labTests.addTestResult("FBS", value, si, "mg/dL", "mmol/L", low, high, interpretation);
        }

        else if (choice == 2) {
            System.out.print("Enter RBS Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.glucoseToSI(value);

            double low = 3.9;
            double high = 7.8;

            String interpretation = rangeFandM.interpret(si, low, high);

            labTests.addTestResult("RBS", value, si, "mg/dL", "mmol/L", low, high, interpretation);
        }

        else if (choice == 3) {
            System.out.print("Enter Total Cholesterol Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.cholesterolToSI(value);

            double low = 0.0;
            double high = 5.2;

            String interpretation = rangeFandM.interpret(si, low, high);

            labTests.addTestResult("Total Cholesterol", value, si, "mg/dL", "mmol/L", low, high, interpretation);
        }

        else if (choice == 4) {
            System.out.print("Enter HDL Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.hdlToSI(value);

            double low, high;

            if (sex.equals("M")) {
                low = 0.91;
                high = 2.08;
            } else {
                low = 1.09;
                high = 2.29;
            }

            String interpretation = rangeFandM.interpret(si, low, high);

            labTests.addTestResult("HDL", value, si, "mg/dL", "mmol/L", low, high, interpretation);
        }

        else if (choice == 5) {
            System.out.print("Enter LDL Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.ldlToSI(value);

            double low = 1.30;
            double high = 3.38;

            String interpretation = rangeFandM.interpret(si, low, high);

            labTests.addTestResult("LDL", value, si, "mg/dL", "mmol/L", low, high, interpretation);
        }

        else if (choice == 6) {
            System.out.print("Enter Triglycerides Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.triglyceridesToSI(value);

            double low, high;

            if (sex.equals("M")) {
                low = 0.68;
                high = 1.88;
            } else {
                low = 0.46;
                high = 1.60;
            }

            String interpretation = rangeFandM.interpret(si, low, high);

            labTests.addTestResult("Triglycerides", value, si, "mg/dL", "mmol/L", low, high, interpretation);
        }

        else if (choice == 7) {
            System.out.print("Enter Creatinine Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.creatinineToSI(value);

            double low, high;

            if (sex.equals("M")) {
                low = 79.6;
                high = 114.9;
            } else {
                low = 53.04;
                high = 106.08;
            }

            String interpretation = rangeFandM.interpret(si, low, high);

            labTests.addTestResult("Creatinine", value, si, "mg/dL", "µmol/L", low, high, interpretation);
        }

        else if (choice == 8) {
            System.out.print("Enter Uric Acid Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.uricAcidToSI(value);

            double low, high;

            if (sex.equals("M")) {
                low = 0.21;
                high = 0.42;
            } else {
                low = 0.15;
                high = 0.35;
            }

            String interpretation = rangeFandM.interpret(si, low, high);

            labTests.addTestResult("Uric Acid", value, si, "mg/dL", "mmol/L", low, high, interpretation);
        }

        else if (choice == 9) {
            System.out.print("Enter BUN Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.bunToSI(value);

            double low = 2.5;
            double high = 7.1;

            String interpretation = rangeFandM.interpret(si, low, high);

            labTests.addTestResult("BUN", value, si, "mg/dL", "mmol/L", low, high, interpretation);
        }

        else if (choice == 10) {
            System.out.print("Enter AST/SGOT Value (U/L): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.enzymeToSI(value);

            double low = 0.17;
            double high = 0.67;

            String interpretation = rangeFandM.interpret(si, low, high);

            labTests.addTestResult("AST/SGOT", value, si, "U/L", "µkat/L", low, high, interpretation);
        }

        else if (choice == 11) {
            System.out.print("Enter ALT/SGPT Value (U/L): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.enzymeToSI(value);
            double low = 0.12;
            double high = 0.77;

            String interpretation = rangeFandM.interpret(si, low, high);

            labTests.addTestResult("ALT/SGPT", value, si, "U/L", "µkat/L", low, high, interpretation);
        }

        else if (choice == 12) {
            System.out.print("Enter Sodium Value (mEq/L): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.electrolyteToSI(value);

            double low = 135;
            double high = 145;

            String interpretation = rangeFandM.interpret(si, low, high);

            labTests.addTestResult("Sodium", value, si, "mEq/L", "mmol/L", low, high, interpretation);
        }

        else if (choice == 13) {
            System.out.print("Enter Potassium Value (mEq/L): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.electrolyteToSI(value);

            double low = 3.5;
            double high = 5.1;

            String interpretation = rangeFandM.interpret(si, low, high);

            labTests.addTestResult("Potassium", value, si, "mEq/L", "mmol/L", low, high, interpretation);
        }

        else if (choice == 14) {
            System.out.print("Enter Chloride Value (mEq/L): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.electrolyteToSI(value);

            double low = 98;
            double high = 107;

            String interpretation = rangeFandM.interpret(si, low, high);

            labTests.addTestResult("Chloride", value, si, "mEq/L", "mmol/L", low, high, interpretation);
        }

        else if (choice == 15) {
            System.out.print("Enter Total Calcium Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.totalCalciumToSI(value);

            double low = 2.10;
            double high = 2.55;

            String interpretation = rangeFandM.interpret(si, low, high);

            labTests.addTestResult("Total Calcium", value, si, "mg/dL", "mmol/L", low, high, interpretation);
        }
        else if (choice == 16) {
            System.out.print("Enter Ionized Calcium Value (mg/dL): ");
            double value = input.nextDouble();

            double si = CONVENTIONALtoSI.ionizedCalciumToSI(value);
            double low = 1.12;
            double high = 1.32;

            String interpretation = rangeFandM.interpret(si, low, high);

            labTests.addTestResult("Ionized Calcium", value, si, "mg/dL", "mmol/L", low, high, interpretation);
        }

        else if (choice == 17) {
            labTests.displayResults();
        }

        else if (choice == 18) {
            System.out.println("Exiting Lab Menu...");
        }

        else {
            System.out.println("Invalid choice. Try again.");
        }
    }
}