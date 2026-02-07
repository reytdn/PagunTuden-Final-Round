import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PagunTudenClinicAppGUI extends JFrame {

    private static int nextID = 1001;

    private JTextField fnameField, mnameField, lnameField;
    private JTextField ageField, provinceField, cityField;
    private JTextField lastMealField, physicianField;

    private JComboBox<String> sexBox, mealAmPmBox;

    // ✅ TABLE FOR MULTIPLE TESTS WITH VALUES
    private JTable testTable;
    private DefaultTableModel tableModel;

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
        setSize(1200, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ================= PATIENT PANEL =================
        JPanel patientPanel = new JPanel(new GridLayout(8, 2, 8, 8));
        patientPanel.setBorder(BorderFactory.createTitledBorder("Patient Information"));

        fnameField = new JTextField();
        mnameField = new JTextField();
        lnameField = new JTextField();
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

        patientPanel.add(new JLabel("Age:"));
        patientPanel.add(ageField);

        patientPanel.add(new JLabel("Sex:"));
        patientPanel.add(sexBox);

        patientPanel.add(new JLabel("Province:"));
        patientPanel.add(provinceField);

        patientPanel.add(new JLabel("City:"));
        patientPanel.add(cityField);

        // ================= REQUEST PANEL =================
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

        // ================= LAB PANEL =================
        JPanel labPanel = new JPanel(new BorderLayout());
        labPanel.setBorder(BorderFactory.createTitledBorder("Laboratory Tests (Enter Value Per Test)"));

        // ✅ TABLE MODEL
        tableModel = new DefaultTableModel(
                new Object[]{"Test Name", "Conventional Value"}, 0
        );

        // ADD TESTS ROWS
        String[] tests = {
                "FBS", "RBS", "Total Cholesterol", "HDL", "LDL",
                "Triglycerides", "Creatinine", "Uric Acid",
                "BUN", "AST", "ALT", "Sodium",
                "Potassium", "Chloride",
                "Total Calcium", "Ionized Calcium"
        };

        for (String test : tests) {
            tableModel.addRow(new Object[]{test, ""});
        }

        // ✅ TABLE
        testTable = new JTable(tableModel);

        // ✅ WIDEN THE VALUE INPUT BOX COLUMN
        testTable.getColumnModel().getColumn(0).setPreferredWidth(250); // Test Name
        testTable.getColumnModel().getColumn(1).setPreferredWidth(500); // Value Box Wider

        // ✅ Bigger Row Height for Easy Typing
        testTable.setRowHeight(30);

        JScrollPane tableScroll = new JScrollPane(testTable);
        labPanel.add(tableScroll, BorderLayout.CENTER);

        // ================= RESULT AREA =================
        resultArea = new JTextArea(15, 70);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Laboratory Report Output"));

        // ================= BUTTON =================
        JButton processButton = new JButton("PROCESS ALL ENTERED TESTS");
        processButton.setFont(new Font("Arial", Font.BOLD, 14));
        processButton.addActionListener(e -> processMultipleTests());

        // ================= TOP PANEL =================
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        topPanel.add(patientPanel);
        topPanel.add(requestPanel);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(labPanel, BorderLayout.CENTER);
        mainPanel.add(processButton, BorderLayout.WEST);
        mainPanel.add(scroll, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // ================= PROCESS MULTIPLE TESTS =================
    private void processMultipleTests() {

        String sex = (String) sexBox.getSelectedItem();

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

        // LOOP THROUGH TABLE ROWS
        for (int i = 0; i < tableModel.getRowCount(); i++) {

            String testName = tableModel.getValueAt(i, 0).toString();
            String valueText = tableModel.getValueAt(i, 1).toString();

            if (valueText.isEmpty()) continue;

            try {
                double value = Double.parseDouble(valueText);

                double si = 0;
                double low = 0, high = 0;
                String unitConv = "", unitSI = "";

                if (testName.equals("FBS")) {
                    si = value / 18.0;
                    low = 74; high = 100;
                    unitConv = "mg/dL"; unitSI = "mmol/L";
                }
                else if (testName.equals("RBS")) {
                    si = value / 18.0;
                    low = 74; high = 140;
                    unitConv = "mg/dL"; unitSI = "mmol/L";
                }

                String status;
                if (value > high) status = "HIGH";
                else if (value < low) status = "LOW";
                else status = "NORMAL";

                resultArea.append(
                        "Test Name          : " + testName +
                        "\nValue Entered      : " + value + " " + unitConv +
                        "\nReference Range    : " + low + " - " + high +
                        "\nSI Converted Value : " + String.format("%.2f", si) + " " + unitSI +
                        "\nStatus             : " + status +
                        "\n-------------------------------------------------\n\n"
                );

            } catch (NumberFormatException ex) {
                resultArea.append("Invalid Input for: " + testName + "\n\n");
            }
        }

        resultArea.append("=========== END OF REPORT ===========\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PagunTudenClinicAppGUI().setVisible(true);
        });
    }
}
