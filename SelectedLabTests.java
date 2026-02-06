import java.util.ArrayList;

public class SelectedLabTests {

    private ArrayList<String> chosenTests = new ArrayList<>();

    public void addTestResult(String testName,
                            double conventional,
                            double siValue,
                            String unitConv,
                            String unitSI,
                            double lowConv,
                            double highConv,
                            double lowSI,
                            double highSI,
                            String interpretation) {

        chosenTests.add(
                testName +
                "\n   Conventional: " + String.format("%.2f", conventional) + " " + unitConv +
                "\n   Conv Ref Range: " + lowConv + " - " + highConv + " " + unitConv +
                "\n   SI Units: " + String.format("%.2f", siValue) + " " + unitSI +
                "\n   SI Ref Range: " + lowSI + " - " + highSI + " " + unitSI +
                "\n   Result: " + interpretation +
                "\n"
        );
    }

    public void displayResults() {

        System.out.println("\n===== SELECTED LAB TEST RESULTS =====");

        if (chosenTests.isEmpty()) {
            System.out.println("No tests were selected.");
        } else {
            for (String test : chosenTests) {
                System.out.println(test);
            }
        }
    }
}