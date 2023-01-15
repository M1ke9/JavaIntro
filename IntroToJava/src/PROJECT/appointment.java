package PROJECT;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public class appointment {
    private int KAR;
    private long AMKA;
    private  String InsuredName;
    private String InsuredCity;
    private  int CenterCode;
    private  String CenterName;
    private String CenterLocation;
    private  int DoctorAM;
    private String DoctorName;
    private static int num=1;
    private LocalDateTime  StartAppointment;
    private LocalDateTime FinishAppointment;




appointment(insured A, VaccinationCenter B, LocalDateTime StartApp, LocalDateTime FinishApp, Doctor C)
{
    this.InsuredName=A.getInsuredName();
    this.InsuredCity=A.getCity();
    this.AMKA=A.getAMKA();
    this.CenterName=B.getCenterName();
    this.CenterCode=B.getCenterCode();
    this.CenterLocation=B.getCenterLocation();
    this.KAR=(int)num;
    num+=1;
    this.StartAppointment=StartApp;
    this.FinishAppointment=FinishApp;
    this.DoctorName=C.getDoctorName();
    this.DoctorAM=C.getDoctorAM();

}


        appointment(insured A,VaccinationCenter B)
        {
        this.InsuredName=A.getInsuredName();
        this.InsuredCity=A.getCity();
        this.AMKA=A.getAMKA();
        this.CenterName=B.getCenterName();
        this.CenterCode=B.getCenterCode();
        this.CenterLocation=B.getCenterLocation();


    }

    public LocalDateTime getStartAppointment() {
        return StartAppointment;
    }

    public void setStartAppointment(LocalDateTime startAppointment) {
        StartAppointment = startAppointment;
    }

    public LocalDateTime getFinishAppointment() {
        return FinishAppointment;
    }

    public void setFinishAppointment(LocalDateTime finishAppointment) {
        FinishAppointment = finishAppointment;
    }

    public int getKAR() {
        return KAR;
    }

    public void setKAR(int KAR) {
        this.KAR = KAR;
    }

    public int getCenterCode() {
        return CenterCode;
    }

    public void setCenterCode(int centerCode) {
        CenterCode = centerCode;
    }

    public String getCenterLocation() {
        return CenterLocation;
    }

    public void setCenterLocation(String centerLocation) {
        CenterLocation = centerLocation;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public int getDoctorAM() {
        return DoctorAM;
    }

    public void setDoctorAM(int doctorAM) {
        DoctorAM = doctorAM;
    }

    public String getCenterName() {
        return CenterName;
    }

    public void setCenterName(String centerName) {
        CenterName = centerName;
    }

    public long getAMKA() {
        return AMKA;
    }

    public void setAMKA(long AMKA) {
        this.AMKA = AMKA;
    }

    public String getInsuredName() {
        return InsuredName;
    }

    public void setInsuredName(String insuredName) {
        InsuredName = insuredName;
    }

    public String getInsuredCity() {
        return InsuredCity;
    }

    public void setInsuredCity(String insuredCity) {
        InsuredCity = insuredCity;
    }


    void printAppointment(){
    System.out.println("KAR:"+KAR);
    System.out.println("Insured Name:"+InsuredName);
    System.out.println("Insured AMKA:"+AMKA);
    System.out.println("Insured City:"+InsuredCity);
    System.out.println("Center name :"+CenterName);
    System.out.println("Center Location:"+CenterLocation);
    System.out.println("Doctor Name:"+DoctorName);
    System.out.println("Doctor AM:"+DoctorAM);

    System.out.println(PrintAppointmentPeriod());

    System.out.println();

}

public String  PrintAppointmentPeriod(){
    String s="";
    s="From:"+this.StartAppointment.toString()+" " +"until"+" " +this.FinishAppointment.toString();
    return s;

    }



}
