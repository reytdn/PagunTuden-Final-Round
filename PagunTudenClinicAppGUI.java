import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PagunTudenClinicAppGUI extends JFrame {

    private static int nextID = 1001; //gives the patient a unique id
     //  demographics input fields, JTextFields The JTextField in Java Swing is a graphical user interface (GUI) component that allows a user to enter or edit a single line of text. It is a fundamental component for capturing user input like names, email addresses, or search queries in a GUI application. 
    private JTextField fnameField, mnameField, lnameField;
    private JTextField ageField, provinceField, cityField;
    private JTextField lastMealField, physicianField;


  // Combo boxes for selecting sex and AM/PM for last meal
    private JComboBox<String> sexBox, mealAmPmBox;
 // Table to enter lab test values, with a DefaultTableModel to manage the data
    private JTable testTable;
    private DefaultTableModel tableModel;

    private JTextArea resultArea;

      // show the final lab report output in a JTextArea (multi-line text area that can display the formatted report)
// Variables to hold patient ID, date and time of collection for the report header
    private int patientID;
    private String dateCollection;
    private String timeCollection;
 // Constructor to build the GUI
    public PagunTudenClinicAppGUI() {
 
        patientID = nextID++;

        dateCollection = LocalDate.now().toString();

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
        timeCollection = LocalTime.now().format(timeFormat);
  // Set up the main frame properties like title, size, close operation, and center it on the screen 
        setTitle("PagunTuden Clinic Laboratory Information System");
        setSize(1200, 850);//this is the measurement of the window size in pixels, where 1200 is the width and 850 is the height. This size is chosen to provide ample space for all the components of the GUI, including input fields, tables, and the report area, while still being manageable on most screens.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);// Centers the window on the screen


        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));// THIS IS THE MEASUREMENT OF THE GAP BETWEEN COMPONENTS IN THE BORDERLAYOUT, WHERE 15 IS THE HORIZONTAL GAP AND 15 IS THE VERTICAL GAP. THIS PROVIDES SPACING BETWEEN THE DIFFERENT PANELS AND COMPONENTS IN THE GUI, MAKING IT MORE VISUALLY APPEALING AND EASIER TO READ.
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));// THIS ADDS AN EMPTY BORDER AROUND THE MAIN PANEL WITH 15 PIXELS OF SPACE ON ALL SIDES (TOP, LEFT, BOTTOM, RIGHT). THIS CREATES A MARGIN AROUND THE CONTENT OF THE GUI, PREVENTING COMPONENTS FROM BEING TOO CLOSE TO THE EDGES OF THE WINDOW AND IMPROVING THE OVERALL LAYOUT AND AESTHETIC OF THE APPLICATION.  


        // ===================== PATIENT INFO =====================
        // tHIS IS THE MAIN PANEL THAT HOLDS ALL OTHER COMPONENTS, USING BORDERLayout TO ORGANIZE THEM INTO REGIONS (NORTH, SOUTH, EAST, WEST, CENTER)
        JPanel patientPanel = new JPanel(new GridLayout(8, 2, 8, 8));// THIS PANEL USES A GRIDLAYOUT WITH 8 ROWS AND 2 COLUMNS, AND A GAP OF 8 PIXELS BOTH HORIZONTALLY AND VERTICALLY BETWEEN COMPONENTS. THIS LAYOUT ORGANIZES THE LABELS AND INPUT FIELDS IN A NEAT GRID, MAKING IT EASY FOR USERS TO ENTER PATIENT INFORMATION IN A STRUCTURED MANNER.
        patientPanel.setBorder(BorderFactory.createTitledBorder("Patient Information"));//THIS ADDS A TITLED BORDER AROUND THE PATIENT INFORMATION PANEL WITH THE TITLE "Patient Information". THIS HELPS TO VISUALLY SEPARATE THIS SECTION FROM OTHER PARTS OF THE GUI AND PROVIDES A CLEAR LABEL FOR USERS TO UNDERSTAND THAT THIS AREA IS FOR ENTERING PATIENT DETAILS.

        // Initialize text fields

        fnameField = new JTextField();;// THIS CREATES A NEW JTextField FOR THE FIRST NAME INPUT. JTextField is a COMPONENT IN JAVA SWING THAT ALLOWS USERS TO ENTER OR EDIT A SINGLE LINE OF TEXT. THIS FIELD WILL BE USED TO CAPTURE THE FIRST NAME OF THE PATIENT IN THE GUI.
        mnameField = new JTextField();// THIS CREATES A NEW JTextField FOR THE MIDDLE NAME INPUT. THIS FIELD WILL BE USED TO CAPTURE THE MIDDLE NAME OF THE PATIENT IN THE GUI.
        lnameField = new JTextField();// THIS CREATES A NEW JTextField FOR THE LAST NAME INPUT. THIS FIELD WILL BE USED TO CAPTURE THE LAST NAME OF THE PATIENT IN THE GUI.
        ageField = new JTextField();// THIS CREATES A NEW JTextField FOR THE AGE INPUT. THIS FIELD WILL BE USED TO CAPTURE THE AGE OF THE PATIENT IN THE GUI.
        provinceField = new JTextField();// THIS CREATES A NEW JTextField FOR THE PROVINCE INPUT. THIS FIELD WILL BE USED TO CAPTURE THE PROVINCE OF THE PATIENT IN THE GUI.
        cityField = new JTextField();// THIS CREATES A NEW JTextField FOR THE CITY INPUT. THIS FIELD WILL BE USED TO CAPTURE THE CITY OF THE PATIENT IN THE GUI.

        sexBox = new JComboBox<>(new String[]{"M", "F"});

        patientPanel.add(new JLabel("Patient ID (Auto):"));// THIS ADDS A NEW JLabel WITH THE TEXT "Patient ID (Auto):" TO THE PATIENT PANEL. 
        patientPanel.add(new JLabel(String.valueOf(patientID)));// THIS ADDS A NEW JLabel TO THE PATIENT PANEL THAT DISPLAYS THE CURRENT VALUE OF patientID. THIS LABEL SHOWS THE AUTO-GENERATED PATIENT ID TO THE USER, PROVIDING IMMEDIATE FEEDBACK ON THE UNIQUE IDENTIFIER ASSIGNED TO THE PATIENT BEING ENTERED INTO THE SYSTEM.


        patientPanel.add(new JLabel("First Name:"));//this adds the first name label and text field to the patient information panel, allowing users to input the patient's first name.
        patientPanel.add(fnameField);

        patientPanel.add(new JLabel("Middle Name:"));//this adds the middle name label and text field to the patient information panel, allowing users to input the patient's middle name.

        patientPanel.add(mnameField);

        patientPanel.add(new JLabel("Last Name:"));;//this adds the last name label and text field to the patient information panel, allowing users to input the patient's last name.

        patientPanel.add(lnameField);

        patientPanel.add(new JLabel("Age:"));;//this adds the age label and text field to the patient information panel, allowing users to input the patient's age.

        patientPanel.add(ageField);

        patientPanel.add(new JLabel("Sex:"));//this adds the sex label and combo box to the patient information panel, allowing users to select the

        patientPanel.add(sexBox);

        patientPanel.add(new JLabel("Province:"));
        patientPanel.add(provinceField);

        patientPanel.add(new JLabel("City:"));
        patientPanel.add(cityField);

        // ===================== REQUEST DETAILS =====================
        JPanel requestPanel = new JPanel(new GridLayout(4, 2, 8, 8));
        requestPanel.setBorder(BorderFactory.createTitledBorder("Request Details"));

        lastMealField = new JTextField();
        physicianField = new JTextField();
        mealAmPmBox = new JComboBox<>(new String[]{"AM", "PM"});

        requestPanel.add(new JLabel("Last Meal Time:"));
        requestPanel.add(lastMealField);

        requestPanel.add(new JLabel("AM/PM:"));
        requestPanel.add(mealAmPmBox);

        requestPanel.add(new JLabel("Requesting Physician:"));
        requestPanel.add(physicianField);

        requestPanel.add(new JLabel("Date & Time Collected:"));
        requestPanel.add(new JLabel(dateCollection + " " + timeCollection));

        // ===================== LAB TEST TABLE =====================
        JPanel labPanel = new JPanel(new BorderLayout());
        labPanel.setBorder(BorderFactory.createTitledBorder("Laboratory Tests (Enter Value Per Test)"));
 // Table model with columns "Test Name" and "Conventional Value"
        tableModel = new DefaultTableModel(new Object[]{"Test Name", "Conventional Value"}, 0);

        String[] tests = {
                "FBS", "RBS", "Total Cholesterol", "HDL", "LDL",
                "Triglycerides", "Creatinine", "Uric Acid",
                "BUN", "AST", "ALT", "Sodium",
                "Potassium", "Chloride",
                "Total Calcium", "Ionized Calcium"
        };
  // it adds tests to the table model as rows, with the test name in the first column and an empty string in the second column for user input
        for (String test : tests) {
            tableModel.addRow(new Object[]{test, ""});
        }

        // Initialize JTable with model
        testTable = new JTable(tableModel);
        testTable.setRowHeight(30);

        JScrollPane tableScroll = new JScrollPane(testTable);
        labPanel.add(tableScroll, BorderLayout.CENTER);

        // ===================== REPORT AREA =====================
        resultArea = new JTextArea(15, 70);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Laboratory Report Output"));

        // ===================== PROCESS BUTTON =====================
        JButton processButton = new JButton("PROCESS ALL ENTERED TESTS");
        processButton.setFont(new Font("Arial", Font.BOLD, 14));
        processButton.addActionListener(e -> processMultipleTests());

        JPanel topPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        topPanel.add(patientPanel);
        topPanel.add(requestPanel);
// Creates a panel (topPanel) with a grid layout (1 row, 2 columns, spacing 15px).
//Adds patient info panel and request info panel side by side.
//Adds panels and components to mainPanel using BorderLayout:
//NORTH: patient + request info
//CENTER: lab test panel
//WEST: process button
//SOUTH: scrollable results area

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(labPanel, BorderLayout.CENTER);
        mainPanel.add(processButton, BorderLayout.WEST);
        mainPanel.add(scroll, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // ===================== PROCESS TESTS =====================
    private void processMultipleTests() {

        String sex = (String) sexBox.getSelectedItem();



        //Initializes the report header with patient details (ID, name, age, sex, address, physician, last meal, collection date/time).

        resultArea.setText(
                "=========== PAGUNTUDEN CLINIC LAB REPORT ===========\n\n" +
                        "Patient ID: " + patientID +
                        "\nName      : " + fnameField.getText() + " " +
                        mnameField.getText() + " " +
                        lnameField.getText() +
                        "\nAge       : " + ageField.getText() +
                        "\nSex       : " + sex +
                        "\nAddress   : " + provinceField.getText() + ", " +
                        cityField.getText() +
                        "\nPhysician : " + physicianField.getText() +
                        "\nLast Meal : " + lastMealField.getText() + " " +
                        mealAmPmBox.getSelectedItem() +
                        "\nCollected : " + dateCollection + " " + timeCollection +
                        "\n\n=================================================\n\n"
        );

        for (int i = 0; i < tableModel.getRowCount(); i++) {

            String testName = tableModel.getValueAt(i, 0).toString();
            String valueText = tableModel.getValueAt(i, 1).toString();

            if (valueText.isEmpty()) continue;

            try {
                double value = Double.parseDouble(valueText);

                double si = 0;
                double lowConv = 0, highConv = 0;
                double lowSI = 0, highSI = 0;

                String unitConv = "", unitSI = "";
                String status = "";

                // ===================== RANGES BASED ON THE TABLE =====================

                //- Converts value to SI units.
//Defines normal ranges (different for male/female in some tests).
//Sets units (mg/dL, mmol/L, etc.).
//Determines status using interpretRange().


                if (testName.equals("FBS")) {
                    si = value / 18.0;
                    lowConv = 74; highConv = 100;
                    lowSI = 4.07; highSI = 5.5;
                    unitConv = "mg/dL"; unitSI = "mmol/L";
                    status = interpretRange(value, lowConv, highConv);
                }

                else if (testName.equals("RBS")) {
                    si = value / 18.0;
                    lowConv = 70; highConv = 139;
                    lowSI = 3.9; highSI = 7.7;
                    unitConv = "mg/dL"; unitSI = "mmol/L";
                    status = interpretRange(value, lowConv, highConv);
                }

                else if (testName.equals("Total Cholesterol")) {
                    si = value / 38.67;
                    lowConv = 150; highConv = 200;
                    lowSI = 3.9; highSI = 5.72;
                    unitConv = "mg/dL"; unitSI = "mmol/L";
                    status = interpretRange(value, lowConv, highConv);
                }

                else if (testName.equals("HDL")) {
                    si = value / 38.67;
                    unitConv = "mg/dL"; unitSI = "mmol/L";

                    if (sex.equals("M")) {
                        lowConv = 35; highConv = 80;
                        lowSI = 0.91; highSI = 2.08;
                    } else {
                        lowConv = 42; highConv = 88;
                        lowSI = 1.09; highSI = 2.29;
                    }

                    status = interpretRange(value, lowConv, highConv);
                }

                else if (testName.equals("LDL")) {
                    si = value / 38.67;
                    lowConv = 50; highConv = 130;
                    lowSI = 1.3; highSI = 3.38;
                    unitConv = "mg/dL"; unitSI = "mmol/L";
                    status = interpretRange(value, lowConv, highConv);
                }

                else if (testName.equals("Triglycerides")) {
                    si = value / 88.57;
                    unitConv = "mg/dL"; unitSI = "mmol/L";

                    if (sex.equals("M")) {
                        lowConv = 60; highConv = 165;
                        lowSI = 0.68; highSI = 1.88;
                    } else {
                        lowConv = 40; highConv = 140;
                        lowSI = 0.46; highSI = 1.60;
                    }

                    status = interpretRange(value, lowConv, highConv);
                }

                else if (testName.equals("Creatinine")) {
                    si = value * 88.4;
                    unitConv = "mg/dL"; unitSI = "umol/L";

                    if (sex.equals("M")) {
                        lowConv = 0.9; highConv = 1.3;
                        lowSI = 79.6; highSI = 114.9;
                    } else {
                        lowConv = 0.6; highConv = 1.2;
                        lowSI = 53.04; highSI = 106.08;
                    }

                    status = interpretRange(value, lowConv, highConv);
                }

                else if (testName.equals("Uric Acid")) {
                    si = value / 16.81;
                    unitConv = "mg/dL"; unitSI = "mmol/L";

                    if (sex.equals("M")) {
                        lowConv = 3.5; highConv = 7.2;
                        lowSI = 0.21; highSI = 0.42;
                    } else {
                        lowConv = 2.6; highConv = 6.0;
                        lowSI = 0.15; highSI = 0.35;
                    }

                    status = interpretRange(value, lowConv, highConv);
                }

                else if (testName.equals("BUN")) {
                    si = value * 0.357;
                    lowConv = 6.0; highConv = 20.0;
                    lowSI = 2.14; highSI = 7.14;
                    unitConv = "mg/dL"; unitSI = "mmol/L";
                    status = interpretRange(value, lowConv, highConv);
                }

                else if (testName.equals("AST")) {
                    si = value * 0.0167;
                    lowConv = 0; highConv = 45;
                    lowSI = 0; highSI = 0.78;
                    unitConv = "U/L"; unitSI = "uKat/L";
                    status = interpretRange(value, lowConv, highConv);
                }

                else if (testName.equals("ALT")) {
                    si = value * 0.0167;
                    lowConv = 0; highConv = 48;
                    lowSI = 0; highSI = 0.83;
                    unitConv = "U/L"; unitSI = "uKat/L";
                    status = interpretRange(value, lowConv, highConv);
                }

                else if (testName.equals("Sodium")) {
                    si = value;
                    lowConv = 135; highConv = 145;
                    lowSI = 135; highSI = 145;
                    unitConv = "mEq/L"; unitSI = "mmol/L";
                    status = interpretRange(value, lowConv, highConv);
                }

                else if (testName.equals("Potassium")) {
                    si = value;
                    lowConv = 3.5; highConv = 5.0;
                    lowSI = 3.5; highSI = 5.0;
                    unitConv = "mEq/L"; unitSI = "mmol/L";
                    status = interpretRange(value, lowConv, highConv);
                }

                else if (testName.equals("Chloride")) {
                    si = value;
                    lowConv = 96; highConv = 110;
                    lowSI = 96; highSI = 110;
                    unitConv = "mEq/L"; unitSI = "mmol/L";
                    status = interpretRange(value, lowConv, highConv);
                }

                else if (testName.equals("Total Calcium")) {
                    si = value / 4.0;
                    lowConv = 8.6; highConv = 10.28;
                    lowSI = 2.15; highSI = 2.57;
                    unitConv = "mg/dL"; unitSI = "mmol/L";
                    status = interpretRange(value, lowConv, highConv);
                }

                else if (testName.equals("Ionized Calcium")) {
                    si = value / 4.0;
                    lowConv = 4.4; highConv = 5.2;
                    lowSI = 1.10; highSI = 1.30;
                    unitConv = "mg/dL"; unitSI = "mmol/L";
                    status = interpretRange(value, lowConv, highConv);
                }

                // ===================== PRINT RESULTS =====================
                resultArea.append(
                        "Test Name            : " + testName +
                                "\nValue Entered        : " + value + " " + unitConv +
                                "\nConventional Range   : " + lowConv + " - " + highConv + " " + unitConv +
                                "\nSI Converted Value   : " + String.format("%.2f", si) + " " + unitSI +
                                "\nSI Range             : " + lowSI + " - " + highSI + " " + unitSI +
                                "\nStatus               : " + status +
                                "\n-------------------------------------------------\n\n"
                );

            } catch (NumberFormatException ex) {
                resultArea.append("Invalid Input for: " + testName + "\n\n");
            }
        }

        resultArea.append("=========== END OF REPORT ===========\n");
    }

    private String interpretRange(double value, double low, double high) {
        if (value < low) return "LOW";
        else if (value > high) return "HIGH";
        else return "NORMAL";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PagunTudenClinicAppGUI().setVisible(true));
    }
}
