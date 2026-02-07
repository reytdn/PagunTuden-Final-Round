import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PagunTudenClinicAppGUI extends JFrame {

    // It will assign a unique patient ID to each new patient entry, starting from 1001 and incrementing for each new patient added to the system.
    private static int nextID = 1001;

    // Demographics input fields: JTextFields capture single-line text input for the patient
    private JTextField fnameField, mnameField, lnameField;
    private JTextField ageField, provinceField, cityField;
    private JTextField lastMealField, physicianField;

    // Combo boxes for selecting sex and AM/PM for last meal
    private JComboBox<String> sexBox, mealAmPmBox;

    // Table to enter lab test values, with a DefaultTableModel to manage the data
    private JTable testTable;
    private DefaultTableModel tableModel;

    // TextArea to display the final lab report output
    private JTextArea resultArea;

    // Variables to hold patient ID, date, and time of collection for the report header
    private int patientID;
    private String dateCollection;
    private String timeCollection;

    // Constructor to build the GUI
    public PagunTudenClinicAppGUI() {

        // Assign unique patient ID
        patientID = nextID++;

        // Get current date
        dateCollection = LocalDate.now().toString();

        // Get current time formatted as hh:mm AM/PM
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
        timeCollection = LocalTime.now().format(timeFormat);

        // Set up the main frame properties like title, size, close operation, and center it on the screen
        setTitle("PagunTuden Clinic Laboratory Information System");
        setSize(1200, 850); // width x height in pixels
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centers window on the screen

        // Main panel with BorderLayout to organize subpanels
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15)); // horizontal and vertical gaps = 15px
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // margin around edges

        // ------------------- Patient Information Panel -------------------
        JPanel patientPanel = new JPanel(new GridLayout(8, 2, 8, 8)); // 8 rows x 2 columns, 8px gaps
        patientPanel.setBorder(BorderFactory.createTitledBorder("Patient Information")); // titled border

        // Initialize text fields
        fnameField = new JTextField();
        mnameField = new JTextField();
        lnameField = new JTextField();
        ageField = new JTextField();
        provinceField = new JTextField();
        cityField = new JTextField();

        // Sex selection combo box
        sexBox = new JComboBox<>(new String[]{"M", "F"});

        // Add labels and fields in GridLayout
        patientPanel.add(new JLabel("Patient ID (Auto):"));
        patientPanel.add(new JLabel(String.valueOf(patientID))); // auto-generated patient ID

        patientPanel.add(new JLabel("First Name:"));
        patientPanel.add(fnameField);

        patientPanel.add(new JLabel("Middle Name:"));
        patientPanel.add(mnameField);

        patientPanel.add(new JLabel("Last Name:"));
        patientPanel.add(lnameField);

        patientPanel.add(new JLabel("Age:"));
        patientPanel.add(ageField);

        patientPanel.add(new JLabel("Sex:"));
        patientPanel.add(sexBox);

        patientPanel.add(new JLabel("Province:"));
        patientPanel.add(provinceField);

        patientPanel.add(new JLabel("City:"));
        patientPanel.add(cityField);

        // ------------------- Request Details Panel -------------------
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
        requestPanel.add(new JLabel(dateCollection + " " + timeCollection)); // shows timestamp

        // ------------------- Lab Tests Panel -------------------
        JPanel labPanel = new JPanel(new BorderLayout());
        labPanel.setBorder(BorderFactory.createTitledBorder("Laboratory Tests (Enter Value Per Test)"));

        tableModel = new DefaultTableModel(new Object[]{"Test Name", "Conventional Value"}, 0);

        String[] tests = {
                "FBS", "RBS", "Total Cholesterol", "HDL", "LDL",
                "Triglycerides", "Creatinine", "Uric Acid",
                "BUN", "AST", "ALT", "Sodium",
                "Potassium", "Chloride",
                "Total Calcium", "Ionized Calcium"
        };

        // add tests to table model with empty values for input
        for (String test : tests) {
            tableModel.addRow(new Object[]{test, ""});
        }

        testTable = new JTable(tableModel);
        testTable.getColumnModel().getColumn(0).setPreferredWidth(250); // Test Name column width
        testTable.getColumnModel().getColumn(1).setPreferredWidth(500); // Value column width
        testTable.setRowHeight(30);

        JScrollPane tableScroll = new JScrollPane(testTable);
        labPanel.add(tableScroll, BorderLayout.CENTER);

        // TextArea to show final lab report
        resultArea = new JTextArea(15, 70);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Laboratory Report Output"));

        // Button to process all entered lab tests
        JButton processButton = new JButton("PROCESS ALL ENTERED TESTS");
        processButton.setFont(new Font("Arial", Font.BOLD, 14));
        processButton.addActionListener(e -> processMultipleTests());

        // Layout top panels: patient info + request details
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        topPanel.add(patientPanel);
        topPanel.add(requestPanel);

        // Add components to main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(labPanel, BorderLayout.CENTER);
        mainPanel.add(processButton, BorderLayout.WEST);
        mainPanel.add(scroll, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);
    }

    // ------------------- Process Lab Test Values -------------------
    private void processMultipleTests() {

        String sex = (String) sexBox.getSelectedItem();

        // Header for lab report
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

        // Loop through each test in the table
        for (int i = 0; i < tableModel.getRowCount(); i++) {

            String testName = tableModel.getValueAt(i, 0).toString();
            String valueText = tableModel.getValueAt(i, 1).toString();

            // Skip if no value entered
            if (valueText.isEmpty()) continue;

            try {
                double value = Double.parseDouble(valueText); // convert input to double
                double si = 0; // SI converted value
                double lowConv = 0, highConv = 0; // conventional range
                double lowSI = 0, highSI = 0; // SI range
                String unitConv = "", unitSI = ""; // units
                String status = ""; // NORMAL/LOW/HIGH

                // If-else statements for each lab test
                if (testName.equals("FBS")) {
                    si = value / 18.0; lowConv = 74; highConv = 100; lowSI = 4.07; highSI = 5.5;
                    unitConv = "mg/dL"; unitSI = "mmol/L";
                    status = interpretRange(value, lowConv, highConv);
                } else if (testName.equals("RBS")) {
                    si = value / 18.0; lowConv = 70; highConv = 139; lowSI = 3.9; highSI = 7.7;
                    unitConv = "mg/dL"; unitSI = "mmol/L";
                    status = interpretRange(value, lowConv, highConv);
                } else if (testName.equals("Total Cholesterol")) {
                    si = value / 38.67; lowConv = 150; highConv = 200; lowSI = 3.9; highSI = 5.72;
                    unitConv = "mg/dL"; unitSI = "mmol/L";
                    status = interpretRange(value, lowConv, highConv);
                } else if (testName.equals("HDL")) {
                    si = value / 38.67; unitConv = "mg/dL"; unitSI = "mmol/L";
                    if (sex.equals("M")) { lowConv = 35; highConv = 80; lowSI = 0.91; highSI = 2.08; }
                    else { lowConv = 42; highConv = 88; lowSI = 1.09; highSI = 2.29; }
                    status = interpretRange(value, lowConv, highConv);
                } else if (testName.equals("AST")) {
                    si = value * 0.0167; highConv = 45; highSI = 0.75;
                    unitConv = "U/L"; unitSI = "µkat/L";
                    status = (value > highConv) ? "HIGH" : "NORMAL";
                } else if (testName.equals("ALT")) {
                    si = value * 0.0167; highConv = 48; highSI = 0.80;
                    unitConv = "U/L"; unitSI = "µkat/L";
                    status = (value > highConv) ? "HIGH" : "NORMAL";
                } else if (testName.equals("Sodium")) {
                    si = value; lowConv = 135; highConv = 145; lowSI = 135; highSI = 145;
                    unitConv = "mEq/L"; unitSI = "mmol/L"; status = interpretRange(value, lowConv, highConv);
                } else if (testName.equals("Potassium")) {
                    si = value; lowConv = 3.5; highConv = 5.0; lowSI = 3.5; highSI = 5.0;
                    unitConv = "mEq/L"; unitSI = "mmol/L"; status = interpretRange(value, lowConv, highConv);
                } else if (testName.equals("Chloride")) {
                    si = value; lowConv = 96; highConv = 110; lowSI = 96; highSI = 110;
                    unitConv = "mEq/L"; unitSI = "mmol/L"; status = interpretRange(value, lowConv, highConv);
                } else if (testName.equals("Total Calcium")) {
                    si = value / 4.0; lowConv = 8.6; highConv = 10.28; lowSI = 2.15; highSI = 2.57;
                    unitConv = "mg/dL"; unitSI = "mmol/L"; status = interpretRange(value, lowConv, highConv);
                } else if (testName.equals("Ionized Calcium")) {
                    si = value / 4.0; lowConv = 4.4; highConv = 5.2; lowSI = 1.10; highSI = 1.30;
                    unitConv = "mg/dL"; unitSI = "mmol/L"; status = interpretRange(value, lowConv, highConv);
                }

                // Append test results to report
                resultArea.append(
                        "Test Name            : " + testName +
                                "\nValue Entered        : " + value + " " + unitConv +
                                "\nConventional Range   : " + lowConv + (testName.equals("AST") || testName.equals("ALT") ? "" : " - " + highConv) + " " + unitConv +
                                "\nSI Converted Value   : " + String.format("%.2f", si) + " " + unitSI +
                                (testName.equals("AST") || testName.equals("ALT") ? "" : "\nSI Range             : " + lowSI + " - " + highSI + " " + unitSI) +
                                "\nStatus               : " + status +
                                "\n-------------------------------------------------\n\n"
                );

            } catch (NumberFormatException ex) {
                resultArea.append("Invalid Input for: " + testName + "\n\n");
            }
        }

        resultArea.append("=========== END OF REPORT ===========\n");
    }

    // Interpret a value against a reference range
    private String interpretRange(double value, double low, double high) {
        if (value < low) return "LOW";
        else if (value > high) return "HIGH";
        else return "NORMAL";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PagunTudenClinicAppGUI().setVisible(true));
    }
}
