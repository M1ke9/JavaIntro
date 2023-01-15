package PROJECT;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public class Doctor {
    private int DoctorAM;
    private String DoctorName;
    private appointment[] DoctorArrayOfAppointments;
   private  int DoctorsNumOfAppointments;


    Doctor(int DoctorAM, String DoctorName) {
        this.DoctorAM = DoctorAM;
        this.DoctorName = DoctorName;
       this.DoctorArrayOfAppointments = new appointment[28];
        DoctorsNumOfAppointments=0;

    }


    public int getDoctorAM() {
        return DoctorAM;
    }

    public void setDoctorAM(int doctorAM) {
        DoctorAM = doctorAM;
    }

    public String getDoctorName() {
        return DoctorName;
    }


    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public int getDoctorsNumOfAppointements() {
        return DoctorsNumOfAppointments;
    }




    void AddAppToDoctor(appointment D) {

            DoctorArrayOfAppointments[DoctorsNumOfAppointments] = D;

            DoctorsNumOfAppointments++;

    }


    void PrintDoctorsApps() {
        for (int i = 0; i < DoctorsNumOfAppointments; i++) {
            DoctorArrayOfAppointments[i].printAppointment();


        }

    }
}
