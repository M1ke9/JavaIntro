package PROJECT;

import java.util.Scanner;

public class system {
    private String SystemName;
    private String URL;
    private static int  VaccinationPeriod;
    private insured[] ArrayOfInsureds;
    private int NumberOfInsureds;
    private final int MaxNumberOfInsureds = 100;
    private VaccinationCenter[] ArrayOfCenters;
    private int NumberOfCenters;
    private final int MaxNumberOfCenters = 10;


    system(String SystemName, String URL, int VaccinationPeriod) {
        this.SystemName = SystemName;
        this.URL = URL;
        this.VaccinationPeriod = VaccinationPeriod;
        this.ArrayOfInsureds = new insured[MaxNumberOfInsureds];
        this.ArrayOfCenters = new VaccinationCenter[MaxNumberOfCenters];
        this.NumberOfInsureds = 0;
        this.NumberOfCenters = 0;

    }

    public void InsertInsureds(long AMKA, String InsuredName, String city) {

        try {
            ArrayOfInsureds[NumberOfInsureds] = new insured(AMKA, InsuredName, city);
            NumberOfInsureds++;
        }
        catch (ArrayIndexOutOfBoundsException ob) {

            System.out.println("Max  insured is 100");
        }
    }

        public void InsertCenter( int CenterCode, String CenterName, String CenterLocation){
            try {
                ArrayOfCenters[NumberOfCenters] = new VaccinationCenter(CenterCode, CenterName, CenterLocation);
                NumberOfCenters++;
            } catch (ArrayIndexOutOfBoundsException ob) {
                System.out.println("Out of Memory,The maximum number of Centers in the System is 10!!!");

            }

        }


        public void InsertDoctorToCenter ( Scanner sc, int DoctorAm, String DoctorName) {
            System.out.println("The available centers are:");
        for(int k=0;k<NumberOfCenters;k++)
        {
            System.out.println((k+1)+"./"+"Center Name:"+ArrayOfCenters[k].getCenterName()+"  "+"Center Code: "+ArrayOfCenters[k].getCenterCode());

        }
        System.out.println("Enter the Code of the Center you want to register the Doctor: ");
        int CenterCode= sc.nextInt();
            boolean check1 = false;
            for (int i = 0; i < NumberOfCenters; i++) {
                if (ArrayOfCenters[i].getCenterCode() == CenterCode) {

                    ArrayOfCenters[i].InsertDoctor(DoctorAm, DoctorName);
                    check1=true;


                }

            }
            if(check1==false)
                System.out.println("There is no center with code:"+CenterCode);
        }


    public void InsertDoctorToCenterManually ( int CenterCode, int DoctorAm, String DoctorName) {
        boolean check1 = false;
        for (int i = 0; i < NumberOfCenters; i++) {
            if (ArrayOfCenters[i].getCenterCode() == CenterCode) {

                ArrayOfCenters[i].InsertDoctor(DoctorAm, DoctorName);
                check1=true;


            }

        }
        if(check1==false)
            System.out.println("There is no center with code:"+CenterCode);
    }

        /* finds insured from AMKA and chooses the vaccination center witch is closer to the insured(Location) */
        public void InsertAppToCenterManually ( long InsuredAMKA,int day,int Slot,int DoctorAm) {
            boolean check1=false;
            boolean check2 = false;

            for (int i = 0; i < NumberOfInsureds; i++) {
                if (InsuredAMKA == ArrayOfInsureds[i].getAMKA()) {
                    check1 = true;

                    for (int k = 0; k < NumberOfCenters; k++) {

                        if (ArrayOfInsureds[i].getCity().equals(ArrayOfCenters[k].getCenterLocation())) {
                            ArrayOfCenters[k].AddAppointment(ArrayOfInsureds[i], ArrayOfCenters[k],day,Slot,DoctorAm);
                            check2=true;

                        }
                    }
                    if(check2==false)
                        System.out.println("den uparxei kontiko kentro ston asfalismeno ");
                }

            }
            if (check1 == false)
                System.out.println("Den uparxei  asfalismenos me kwdiko:" + InsuredAMKA);
        }



        public void InsertAppToCenter(Scanner sc, long InsuredAMKA){

            boolean check1=false;
            boolean check2 = false;

            for (int i = 0; i < NumberOfInsureds; i++) {
                if (InsuredAMKA == ArrayOfInsureds[i].getAMKA()) {
                    check1 = true;

                    for (int k = 0; k < NumberOfCenters; k++) {

                        if (ArrayOfInsureds[i].getCity().equals(ArrayOfCenters[k].getCenterLocation())) {
                            ArrayOfCenters[k].AppTimeTable(sc,ArrayOfInsureds[i], ArrayOfCenters[k]);
                            check2=true;

                        }
                    }
                    if(check2==false)
                        System.out.println("---Den uparxei kontiko kentro ston asfalismeno--- ");
                }

            }
            if (check1 == false)
                System.out.println("---Den uparxei  asfalismenos me kwdiko:" + InsuredAMKA+"---");
        }


    void printAPPS() {
        for (int i = 0; i < NumberOfCenters; i++) {
            if (ArrayOfCenters[i].getCenterAppointmentNumber()!= 0) {
                System.out.println("Center:" + ArrayOfCenters[i].getCenterName());
                ArrayOfCenters[i].PrintAllAppointments();
                System.out.println();
            }
        }
    }

        public void PrintAllInsureds () {
            System.out.println("  Insureds:");
            for (int i = 0; i < NumberOfInsureds; i++) {
                System.out.println((i + 1) + ":");
                ArrayOfInsureds[i].PrintInsured();
            }
        }

        public void PrintAllCentersAndDoctors () {
            System.out.println();
            for (int i = 0; i < NumberOfCenters; i++) {
                System.out.println("Center:" + (i + 1));
                ArrayOfCenters[i].PrintCenter();

                System.out.println();
                System.out.println();


            }
        }

        public void FindInsuredAppFromAmka(long AMKA) {

            insured A = null;
            for (int i = 0; i < NumberOfInsureds; i++) {
                if (AMKA == ArrayOfInsureds[i].getAMKA()) {
                    A = ArrayOfInsureds[i];

                }

            }


            if (A != null && A.getAppNumber() != 0) {
                System.out.println("Appointment for insured with amka:" + AMKA);
                A.printInsuredAPP();
            }
            if ( A == null) {
                System.out.println();
                System.out.println("--There is no Insured with AMKA:" + AMKA + "--");

            }
            if(A!=null && A.getAppNumber()==0)
            {
                System.out.println();
                System.out.println("--There is no Appointment for insured with AMKA:"+AMKA);
            }
        }

        void FindCentersAppsFromCode(int CenterCode){
            VaccinationCenter A=null;
            for(int i=0;i<NumberOfCenters;i++)
            {
                if(ArrayOfCenters[i].getCenterCode()==CenterCode)
                    A=ArrayOfCenters[i];
            }

            if(A!=null && A.getCenterAppointmentNumber()!=0) {
                System.out.println("Appointmets for Center with code:"+CenterCode);
                A.PrintAllAppointments();
            }
            if(A!=null && A.getCenterAppointmentNumber()==0)
            {
                System.out.println();
                System.out.println("--There are no appointments for center with code:"+CenterCode+"--");
            }
            if(A==null)
            {
                System.out.println();
                System.out.println("--There is no Center with code :"+CenterCode+"--");
            }
        }


        void FindAppsWithDoctorAM(int DoctorAm){
            for(int i=0;i<NumberOfCenters;i++)
            {
                ArrayOfCenters[i].FindDocApps(DoctorAm);
            }

        }

    }

