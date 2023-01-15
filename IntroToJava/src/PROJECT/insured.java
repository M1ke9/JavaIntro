package PROJECT;
public class insured {
    private long AMKA;
   private  String InsuredName;
    private String city;
    appointment App;
    private int AppNumber;

    insured(long AMKA,String InsuredName,String city){
        this.AMKA=AMKA;
        this.InsuredName=InsuredName;
        this.city=city;
        this.AppNumber=0;

    }

    public appointment getApp() {
        return App;
    }

    public void setApp(appointment app) {
        App = app;
    }

    public long getAMKA() {
        return AMKA;
    }

    public void setAMKA(long AMKA) {
        this.AMKA = AMKA;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInsuredName() {
        return InsuredName;
    }

    public void setInsuredName(String insuredName) {
        InsuredName = insuredName;
    }


    void InsertAppToInsured(appointment C){
        this.App=C;
        this.AppNumber=1;

    }

    public int getAppNumber() {
        return AppNumber;
    }

    public void setAppNumber(int appNumber) {
        AppNumber = appNumber;
    }

    public void PrintInsured(){

        System.out.println("AMKA:"+AMKA);
        System.out.println("NAME:"+InsuredName);
        System.out.println("CITY:"+city);
        System.out.println();
    }

    void printInsuredAPP(){
        System.out.println("KAR:"+App.getKAR());
        System.out.println("Insured Name:"+App.getInsuredName());
        System.out.println("Insured City:"+App.getInsuredCity());
        System.out.println("Center Name:"+App.getCenterName());
        System.out.println("Center Code:"+App.getCenterCode());
        System.out.println("Center Location:"+App.getCenterLocation());
        System.out.println("Doctor Name:"+App.getDoctorName());
        System.out.println("Doctor AM:"+App.getDoctorAM());
        System.out.println("Appointment Period: \n"+App.PrintAppointmentPeriod());
    }
}