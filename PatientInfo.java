public class PatientInfo {
    private String fname;
    private String mname;
    private String lname;
    private int age;
    private String sex; 

    public PatientInfo(String fname, String mname, String lname, int age, String sex) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.age = age;
        this.sex = sex;
    }
    public String getFName() { return fname; }
    public String getMName() { return mname; }
    public String getLName() { return lname; }
    public int getAge() { return age; }
    public String getSex() { return sex; }

    public String toString() {
        return "Patient: " + fname + mname + lname + ", Age: " + age + ", Sex: " + sex;
    }
}
