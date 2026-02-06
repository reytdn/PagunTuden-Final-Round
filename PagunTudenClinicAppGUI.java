import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PagunTudenClinicAppGUI extends JFrame {

    private static int nextID = 1001;

    private JTextField fnameField, mnameField, lnameField;
    private JTextField birthdateField, ageField;
    private JTextField provinceField, cityField;
    private JTextField lastMealField, physicianField;

    private JComboBox<String> sexBox, mealAmPmBox, testBox;

    private JTextField valueField;
    private JTextArea resultArea;

    private int patientID;
    private String dateCollection;
    private String timeCollection;

    public PagunTudenClinicAppGUI() {

        patientID = nextID++;

        dateCollection = LocalDate.now().toString();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
        timeCollection = LocalTime.now().format(timeFormat);

        setTitle("PagunTuden Clinic Laboratory Information System");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel patientPanel = new JPanel(new GridLayout(9, 2, 8, 8));
        patientPanel.setBorder(BorderFactory.createTitledBorder("Patient Information"));

        fnameField = new JTextField();
        mnameField = new JTextField();
        lnameField = new JTextField();
        birthdateField = new JTextField();
        ageField = new JTextField();
        provinceField = new JTextField();
        cityField = new JTextField();

        sexBox = new JComboBox<>(new String[]{"M", "F"});

        patientPanel.add(new JLabel("Patient ID (Auto):"));
        patientPanel.add(new JLabel(String.valueOf(patientID)));

        patientPanel.add(new JLabel("First Name:"));
        patientPanel.add(fnameField);

        patientPanel.add(new JLabel("Middle Name:"));
        patientPanel.add(mnameField);

        patientPanel.add(new JLabel("Last Name:"));
        patientPanel.add(lnameField);

        patientPanel.add(new JLabel("Birthdate (YYYY-MM-DD):"));
        patientPanel.add(birthdateField);

        patientPanel.add(new JLabel("Age:"));
        patientPanel.add(ageField);

        patientPanel.add(new JLabel("Sex:"));
        patientPanel.add(sexBox);

        patientPanel.add(new JLabel("Province:"));
        patientPanel.add(provinceField);

        patientPanel.add(new JLabel("City:"));
        patientPanel.add(cityField);

        JPanel labPanel = new JPanel(new GridLayout(9, 2, 8, 8));
        labPanel.setBorder(BorderFactory.createTitledBorder("Laboratory Details"));

        testBox = new JComboBox<>(new String[]{
                "FBS", "RBS", "Total Cholesterol", "HDL", "LDL",
                "Triglycerides", "Creatinine", "Uric Acid",
                "BUN", "AST", "ALT", "Sodium",
                "Potassium", "Chloride",
                "Total Calcium", "Ionized Calcium"
        });

        valueField = new JTextField();
        lastMealField = new JTextField();
        physicianField = new JTextField();

        mealAmPmBox = new JComboBox<>(new String[]{"AM", "PM"});

        labPanel.add(new JLabel("Select Test:"));
        labPanel.add(testBox);

        labPanel.add(new JLabel("Enter Conventional Value:"));
        labPanel.add(valueField);

        labPanel.add(new JLabel("Time of Last Meal:"));
        labPanel.add(lastMealField);

        labPanel.add(new JLabel("AM/PM:"));
        labPanel.add(mealAmPmBox);

        labPanel.add(new JLabel("Requesting Physician:"));
        labPanel.add(physicianField);

        labPanel.add(new JLabel("Date of Collection:"));
        labPanel.add(new JLabel(dateCollection));

        labPanel.add(new JLabel("Time of Collection:"));
        labPanel.add(new JLabel(timeCollection));

        JPanel topPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        topPanel.add(patientPanel);
        topPanel.add(labPanel);

        resultArea = new JTextArea(12, 50);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Laboratory Result"));

        JButton processButton = new JButton("PROCESS TEST");
        processButton.setFont(new Font("Arial", Font.BOLD, 14));
        processButton.addActionListener(e -> processLabTest());

        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.add(processButton, BorderLayout.NORTH);
        bottomPanel.add(scroll, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void processLabTest() {
        try {
            String test = (String) testBox.getSelectedItem();
            String sex = (String) sexBox.getSelectedItem();
            double value = Double.parseDouble(valueField.getText());

            String lastMeal = lastMealField.getText();
            String ampm = (String) mealAmPmBox.getSelectedItem();
            String physician = physicianField.getText();

            double si = 0;
            double lowConv = 0, highConv = 0;
            double lowSI = 0, highSI = 0;
            String unitConv = "", unitSI = "";

            if (test.equals("FBS")) {
                si = CONVENTIONALtoSI.glucoseToSI(value);
                lowConv = 74; highConv = 100;
                lowSI = 4.07; highSI = 5.5;
                unitConv = "mg/dL"; unitSI = "mmol/L";
            }
            else if (test.equals("RBS")) {
                si = CONVENTIONALtoSI.glucoseToSI(value);
                lowConv = 74; highConv = 140;
                lowSI = 4.07; highSI = 7.8;
                unitConv = "mg/dL"; unitSI = "mmol/L";
            }
            else if (test.equals("Total Cholesterol")) {
                si = CONVENTIONALtoSI.cholesterolToSI(value);
                lowConv = 150; highConv = 200;
                lowSI = 3.9; highSI = 5.72;
                unitConv = "mg/dL"; unitSI = "mmol/L";
            }
            else if (test.equals("HDL")) {
                si = CONVENTIONALtoSI.hdlToSI(value);
                unitConv = "mg/dL"; unitSI = "mmol/L";
                if (sex.equals("M")) {
                    lowConv = 35; highConv = 80;
                    lowSI = 0.91; highSI = 2.08;
                } else {
                    lowConv = 42; highConv = 88;
                    lowSI = 1.09; highSI = 2.29;
                }
            }
            else if (test.equals("LDL")) {
                si = CONVENTIONALtoSI.ldlToSI(value);
                lowConv = 50; highConv = 130;
                lowSI = 1.3; highSI = 3.38;
                unitConv = "mg/dL"; unitSI = "mmol/L";
            }
            else if (test.equals("Triglycerides")) {
                si = CONVENTIONALtoSI.triglyceridesToSI(value);
                unitConv = "mg/dL"; unitSI = "mmol/L";
                if (sex.equals("M")) {
                    lowConv = 60; highConv = 165;
                    lowSI = 0.68; highSI = 1.88;
                } else {
                    lowConv = 40; highConv = 140;
                    lowSI = 0.46; highSI = 1.6;
                }
            }
            else if (test.equals("Creatinine")) {
                si = CONVENTIONALtoSI.creatinineToSI(value);
                unitConv = "mg/dL"; unitSI = "µmol/L";
                if (sex.equals("M")) {
                    lowConv = 0.9; highConv = 1.31;
                    lowSI = 79.6; highSI = 114.9;
                } else {
                    lowConv = 0.6; highConv = 1.2;
                    lowSI = 53.04; highSI = 106.08;
                }
            }
            else if (test.equals("Uric Acid")) {
                si = CONVENTIONALtoSI.uricAcidToSI(value);
                unitConv = "mg/dL"; unitSI = "mmol/L";
                if (sex.equals("M")) {
                    lowConv = 3.5; highConv = 7.2;
                    lowSI = 0.21; highSI = 0.42;
                } else {
                    lowConv = 2.6; highConv = 6.0;
                    lowSI = 0.15; highSI = 0.35;
                }
            }
            else if (test.equals("BUN")) {
                si = CONVENTIONALtoSI.bunToSI(value);
                lowConv = 6.0; highConv = 20.0;
                lowSI = 2.14; highSI = 7.14;
                unitConv = "mg/dL"; unitSI = "mmol/L";
            }
            else if (test.equals("AST")) {
                si = CONVENTIONALtoSI.enzymeToSI(value);
                lowConv = 0; highConv = 46;
                lowSI = 0; highSI = 0.78;
                unitConv = "U/L"; unitSI = "µkat/L";
            }
            else if (test.equals("ALT")) {
                si = CONVENTIONALtoSI.enzymeToSI(value);
                lowConv = 0; highConv = 49;
                lowSI = 0; highSI = 0.83;
                unitConv = "U/L"; unitSI = "µkat/L";
            }
            else if (test.equals("Sodium")) {
                si = value;
                lowConv = 135; highConv = 145;
                lowSI = 135; highSI = 145;
                unitConv = "mEq/L"; unitSI = "mmol/L";
            }
            else if (test.equals("Potassium")) {
                si = value;
                lowConv = 3.5; highConv = 5.0;
                lowSI = 3.5; highSI = 5.0;
                unitConv = "mEq/L"; unitSI = "mmol/L";
            }
            else if (test.equals("Chloride")) {
                si = value;
                lowConv = 96; highConv = 110;
                lowSI = 96; highSI = 110;
                unitConv = "mEq/L"; unitSI = "mmol/L";
            }
            else if (test.equals("Total Calcium")) {
                si = CONVENTIONALtoSI.totalCalciumToSI(value);
                lowConv = 8.6; highConv = 10.28;
                lowSI = 2.15; highSI = 2.57;
                unitConv = "mg/dL"; unitSI = "mmol/L";
            }
            else if (test.equals("Ionized Calcium")) {
                si = CONVENTIONALtoSI.ionizedCalciumToSI(value);
                lowConv = 4.4; highConv = 5.2;
                lowSI = 1.10; highSI = 1.30;
                unitConv = "mg/dL"; unitSI = "mmol/L";
            }

            String interpretation;
            if (value > highConv) interpretation = "HIGH";
            else if (value < lowConv) interpretation = "LOW";
            else interpretation = "NORMAL";

            resultArea.setText(
                    "=========== PAGUNTUDEN CLINIC LABORATORY REPORT ===========\n\n" +
                    "Date Collected: " + dateCollection +
                    "\nTime Collected: " + timeCollection +
                    "\n\n---------------- PATIENT INFORMATION ----------------\n" +
                    "Patient ID: " + patientID +
                    "\nFull Name : " + fnameField.getText() + " " +
                                    mnameField.getText() + " " +
                                    lnameField.getText() +
                    "\nAge       : " + ageField.getText() +
                    "\nSex       : " + sex +
                    "\nAddress   : " + provinceField.getText() + ", " +
                                    cityField.getText() +
                    "\n\n---------------- REQUEST DETAILS ----------------\n" +
                    "Requesting Physician: " + physician +
                    "\nLast Meal Time       : " + lastMeal + " " + ampm +
                    "\n\n---------------- LAB TEST RESULT ----------------\n" +
                    "Test Name: " + test +
                    "\nConventional Value: " + value + " " + unitConv +
                    "\nReference Range   : " + lowConv + " - " + highConv +
                    "\nSI Converted Value: " + String.format("%.2f", si) + " " + unitSI +
                    "\nReference Range   : " + lowSI + " - " + highSI +
                    "\n\n---------------- INTERPRETATION ----------------\n" +
                    "Result Status: " + interpretation +
                    "\n\n========================================================="
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid numeric value.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PagunTudenClinicAppGUI().setVisible(true);
        });
    }
}
