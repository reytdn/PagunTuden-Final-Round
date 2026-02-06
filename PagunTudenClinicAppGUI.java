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
        setLayout(new BorderLayout(10,10));

        // ================= PATIENT PANEL =================
        JPanel patientPanel = new JPanel(new GridLayout(8,2,5,5));
        patientPanel.setBorder(BorderFactory.createTitledBorder("Patient Information"));

        fnameField = new JTextField();
        mnameField = new JTextField();
        lnameField = new JTextField();
        birthdateField = new JTextField();
        ageField = new JTextField();
        provinceField = new JTextField();
        cityField = new JTextField();
        lastMealField = new JTextField();
        physicianField = new JTextField();

        sexBox = new JComboBox<>(new String[]{"M","F"});
        mealAmPmBox = new JComboBox<>(new String[]{"AM","PM"});

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

        add(patientPanel, BorderLayout.NORTH);

        // ================= CENTER PANEL =================
        JPanel centerPanel = new JPanel(new GridLayout(6,2,5,5));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Laboratory Details"));

        testBox = new JComboBox<>(new String[]{
                "FBS","RBS","Total Cholesterol","HDL","LDL",
                "Triglycerides","Creatinine","Uric Acid",
                "BUN","AST","ALT","Sodium",
                "Potassium","Chloride",
                "Total Calcium","Ionized Calcium"
        });

        valueField = new JTextField();

        centerPanel.add(new JLabel("Select Test:"));
        centerPanel.add(testBox);

        centerPanel.add(new JLabel("Enter Conventional Value:"));
        centerPanel.add(valueField);

        centerPanel.add(new JLabel("Time of Last Meal:"));
        centerPanel.add(lastMealField);

        centerPanel.add(new JLabel("AM/PM:"));
        centerPanel.add(mealAmPmBox);

        centerPanel.add(new JLabel("Requesting Physician:"));
        centerPanel.add(physicianField);

        centerPanel.add(new JLabel("Date of Collection (Auto):"));
        centerPanel.add(new JLabel(dateCollection));

        centerPanel.add(new JLabel("Time of Collection (Auto):"));
        centerPanel.add(new JLabel(timeCollection));

        add(centerPanel, BorderLayout.CENTER);

        // ================= RESULT AREA =================
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Laboratory Result"));

        JButton processButton = new JButton("PROCESS TEST");
        processButton.addActionListener(e -> processLabTest());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(processButton, BorderLayout.NORTH);
        bottomPanel.add(scroll, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void processLabTest() {

        try {

            String test = (String) testBox.getSelectedItem();
            String sex = (String) sexBox.getSelectedItem();
            double value = Double.parseDouble(valueField.getText());

            double si = 0;
            double lowConv = 0, highConv = 0;
            double lowSI = 0, highSI = 0;
            String unitConv = "", unitSI = "";

            // SAME IF STRUCTURE AS BEFORE (shortened example)
            if (test.equals("FBS")) {
                si = CONVENTIONALtoSI.glucoseToSI(value);
                lowConv=74; highConv=100;
                lowSI=4.07; highSI=5.5;
                unitConv="mg/dL"; unitSI="mmol/L";
            }

            else if (test.equals("Creatinine")) {
                si = CONVENTIONALtoSI.creatinineToSI(value);
                unitConv="mg/dL"; unitSI="µmol/L";

                if(sex.equals("M")){
                    lowConv=0.9; highConv=1.3;
                    lowSI=79.6; highSI=114.9;
                } else {
                    lowConv=0.6; highConv=1.2;
                    lowSI=53.04; highSI=106.08;
                }
            }

            // (You can paste the rest of your IF blocks here exactly same as before)

            String interpretation = rangeFandM.interpret(si, lowSI, highSI);

            resultArea.setText(
                    "=========== PAGUNTUDEN CLINIC LAB REPORT ===========\n\n" +
                    "Patient ID: " + patientID +
                    "\nName: " + fnameField.getText() + " " +
                    mnameField.getText() + " " +
                    lnameField.getText() +
                    "\nBirthdate: " + birthdateField.getText() +
                    "\nAge: " + ageField.getText() +
                    "\nSex: " + sex +
                    "\nAddress: " + provinceField.getText() + ", " + cityField.getText() +
                    "\nDate Collected: " + dateCollection +
                    "\nTime Collected: " + timeCollection +
                    "\nLast Meal: " + lastMealField.getText() + " " + mealAmPmBox.getSelectedItem() +
                    "\nRequesting Physician: " + physicianField.getText() +
                    "\n\n---------------- TEST RESULT ----------------\n" +
                    "\nTest: " + test +
                    "\nConventional: " + value + " " + unitConv +
                    "\nReference: " + lowConv + " - " + highConv + " " + unitConv +
                    "\n\nSI Unit: " + String.format("%.2f", si) + " " + unitSI +
                    "\nReference: " + lowSI + " - " + highSI + " " + unitSI +
                    "\n\nINTERPRETATION: " + interpretation +
                    "\n====================================================="
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric value.");
        }
    }

    public static void main(String[] args) {
        new PagunTudenClinicAppGUI().setVisible(true);
    }
}
