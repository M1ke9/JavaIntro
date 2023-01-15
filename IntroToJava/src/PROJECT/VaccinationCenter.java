package PROJECT;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class VaccinationCenter {
    private int CenterCode;
    private String CenterName;
    private String CenterLocation;
    private Doctor[] ArrayOfDoctors;
    private int NumOfDoctors;
    private final int MaxDoctors = 5;
    private int CenterAppointmentNumber;
    private appointment[][][] AppsTimeTable;


    VaccinationCenter(int CenterCode, String CenterName, String CenterLocation) {

        this.CenterCode = CenterCode;
        this.CenterName = CenterName;
        this.CenterLocation = CenterLocation;
        this.ArrayOfDoctors = new Doctor[MaxDoctors];
        this.NumOfDoctors = 0;
        CenterAppointmentNumber = 0;
        AppsTimeTable = new appointment[8][5][5];


    }


    public int getCenterCode() {
        return CenterCode;
    }

    public void setCenterCode(int centerCode) {
        CenterCode = centerCode;
    }

    public String getCenterName() {
        return CenterName;
    }

    public void setCenterName(String centerName) {
        CenterName = centerName;
    }

    public String getCenterLocation() {
        return CenterLocation;
    }

    public void setCenterLocation(String centerLocation) {
        CenterLocation = centerLocation;
    }

    public Doctor[] getArrayOfDoctors() {
        return ArrayOfDoctors;
    }

    public void setArrayOfDoctors(Doctor[] arrayOfDoctors) {
        ArrayOfDoctors = arrayOfDoctors;
    }

    public int getCenterAppointmentNumber() {
        return CenterAppointmentNumber;
    }

    public void setCenterAppointmentNumber(int centerAppointmentNumber) {
        CenterAppointmentNumber = centerAppointmentNumber;
    }


    public void InsertDoctor(int DoctorAM, String DoctorName) {
        try {
            ArrayOfDoctors[NumOfDoctors] = new Doctor(DoctorAM, DoctorName);
            NumOfDoctors++;
        } catch (ArrayIndexOutOfBoundsException ob) {
            System.out.println("the maximum number of doctors in a center is 5!!!");
        }


    }

    public void AddAppointment(insured A, VaccinationCenter B, int day, int slot, int DoctorAm) {

        LocalDateTime FirstDaySlot = LocalDateTime.parse(LocalDate.now().plusDays(day).toString() + "T08:30");

        LocalDateTime Starts = FirstDaySlot.plusMinutes(slot * 30);

        LocalDateTime finish = Starts.plusMinutes(30);


        for (int i = 0; i < NumOfDoctors; i++) {
            if (DoctorAm == ArrayOfDoctors[i].getDoctorAM()) {
                AppsTimeTable[day][slot][i] = new appointment(A, B, Starts, finish, ArrayOfDoctors[i]);
                A.InsertAppToInsured(AppsTimeTable[day][slot][i]);
                ArrayOfDoctors[i].AddAppToDoctor(AppsTimeTable[day][slot][i]);
                CenterAppointmentNumber++;
                break;
            }
        }


    }


    public int MinDocApps() {
        int FindDoc = 0;
        int min = 10;
        for (int i = 0; i < NumOfDoctors; i++) {
            if (ArrayOfDoctors[i].getDoctorsNumOfAppointements() < min) {
                min = ArrayOfDoctors[i].getDoctorsNumOfAppointements();
                FindDoc = i;
            }
        }
        return FindDoc;

    }

    public void AppTimeTable(Scanner sc, insured A, VaccinationCenter B) {
        Boolean check = false;
        int day;
        int slot;
        PrintAppsMenu();

        do {
            LocalDateTime LocalD = LocalDateTime.parse(LocalDate.now().toString() + "T08:30");
            do {
                System.out.println("Choose the day you prefer(1-7):");
                day = sc.nextInt();
                if (day < 1 || day > 7)
                    System.out.println("Wrong choise!!The vaccination period in only for 7 days!!!");
            }
            while (day < 1 || day > 7);
            sc.nextLine();
            LocalDateTime SpDay = LocalD.plusDays(day);
            do {
                System.out.println("Choose the slot  you want(1-4): ");
                slot = sc.nextInt();
                if (slot < 1 || slot > 4)
                    System.out.println("There are only 4 available slots");
            }
            while (slot < 1 || slot > 4);
            LocalDateTime Starts = SpDay.plusMinutes(slot * 30);
            LocalDateTime finish = Starts.plusMinutes(30);


            label:
            for (int Day = 1; Day < 8; Day++) {
                for (int Slot = 1; Slot < 5; Slot++) {
                    for (int Ndoc = 0; Ndoc < NumOfDoctors; Ndoc++) {
                        if (AppsTimeTable[day][slot][Ndoc] == null && Ndoc == MinDocApps()) {

                                AppsTimeTable[day][slot][Ndoc] = new appointment(A, B, Starts, finish, ArrayOfDoctors[Ndoc]);
                                A.InsertAppToInsured(AppsTimeTable[day][slot][Ndoc]);
                                CenterAppointmentNumber++;
                                ArrayOfDoctors[Ndoc].AddAppToDoctor(AppsTimeTable[day][slot][Ndoc]);
                                check = true;
                                break label;

                            }



                        }


                    }


                }
                if (check == false)
                    System.out.println(">>>> Please make an other choise because the slot is full!!!<<<<");
            }
        while (check == false) ;
        }




    void PrintAppsMenu(){

        LocalDate today=LocalDate.now();
        LocalTime NineM=LocalTime.of(8,30);
        for(int day=1;day<8;day++)
        {

            System.out.println("Day "+day+"."+"("+today.plusDays(day)+")"+":");


            for(int slot=1;slot<5;slot++)
            {
                int Calc=0;

                System.out.print("\t"+(slot)+"."+" " +"("+NineM.plusMinutes(slot*30)+")"+":");

                for(int Ndoc=0;Ndoc<NumOfDoctors;Ndoc++)
                {
                     if(AppsTimeTable[day][slot][Ndoc]==null) {
                         System.out.println("Available");
                         break;
                     }

                     if(AppsTimeTable[day][slot][Ndoc]!=null)
                     {
                         Calc++;

                         if(Calc==NumOfDoctors)
                             System.out.println("**FULL**");

                     }





                }

            }
        }


    }

    void FindDocApps(int AM){
        Doctor C=null;
        for(int i=0;i<NumOfDoctors;i++)
        {
            if(ArrayOfDoctors[i].getDoctorAM()==AM)
               C=ArrayOfDoctors[i];
        }
        if(C!=null && C.getDoctorsNumOfAppointements()!=0)
        {
            System.out.println("APPOINTMENTS FOR DOCTOR WITH AM:"+AM);
            C.PrintDoctorsApps();
        }
        if(C!=null && C.getDoctorsNumOfAppointements()==0)
            System.out.println("There are no Appointments for Doctor with AM:"+AM);

        if(C==null)
            System.out.println("There is no Doctor with AM"+AM);
    }



    void PrintAllAppointments() {
        for (int day = 1; day <8; day++) {
            for (int slot = 1; slot < 5; slot++) {
                for (int doc = 0; doc < NumOfDoctors; doc++) {
                    if (AppsTimeTable[day][slot][doc] != null) {
                        System.out.println("KAR:"+AppsTimeTable[day][slot][doc].getKAR());
                        System.out.println("Insured name:" + AppsTimeTable[day][slot][doc].getInsuredName());
                        System.out.println("Insured AMKA:" + AppsTimeTable[day][slot][doc].getAMKA());
                        System.out.println("Doctor name:" + AppsTimeTable[day][slot][doc].getDoctorName());
                        System.out.println("Doctor AM:" + AppsTimeTable[day][slot][doc].getDoctorAM());
                        System.out.println("Appointment period: "+AppsTimeTable[day][slot][doc].PrintAppointmentPeriod());
                        System.out.println();
                        System.out.println();
                    }
                }
            }
        }
    }

    public void PrintCenter() {
        System.out.println("CenterName:" + CenterName);
        System.out.println("CenterCode:" + CenterCode);
        System.out.println("CenterLocation:" + CenterLocation);
        if (NumOfDoctors == 0)
            System.out.println("There are no Doctors at center:" + CenterName);
        else
            System.out.print("  DOCTORS:");
        System.out.println();
        for (int i = 0; i < NumOfDoctors; i++) {
            System.out.println((i + 1) + "." + "Doctor Name:" + ArrayOfDoctors[i].getDoctorName());
            System.out.println("  Doctor Am:" + ArrayOfDoctors[i].getDoctorAM());
            if (ArrayOfDoctors[i].getDoctorsNumOfAppointements() != 0) {
                System.out.println("    APPOINTMENTS for Doctor " + ArrayOfDoctors[i].getDoctorName() + ":");
                ArrayOfDoctors[i].PrintDoctorsApps();

            }


        }

    }


}




