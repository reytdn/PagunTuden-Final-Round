//This class is for utility purposes only

public class rangeInterpretation {

    //value is the lab test result entered by the user
    //low is the lower limit of the normal range
    //high is the upper limit of the normal range
    //it'll return only normal if the entered value is in the range
    //Example the FBS range is 74-100
    //if the inputted value is 74 or 100 it'll return Normal because it is in the range
    //if the inputted value is 73 and below it'll return Low and if 101 and above it'll return high
    public static String interpret(double value, double low, double high) {

        if (value > high) {
            return "HIGH";
        } 
        else if (value < low) {
            return "LOW";
        } 
        else {
            return "NORMAL";
        }
    }
}
