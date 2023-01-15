package PROJECT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		system A = new system("upourgeio ugeias", "www.eerer.com", 8);
//Katalogos asfalismenwn
		A.InsertInsureds(11111111, "Asfalismenos 1", "XANIA");
		A.InsertInsureds(22222222, "Asfalismenos 2", "XANIA");
		A.InsertInsureds(33333333, "Asfalismenos 3", "XANIA");
		A.InsertInsureds(44444444,"Asfalismenos 4","XANIA");
		A.InsertInsureds(55555555,"Asfalismenos 5","XANIA");
        A.InsertInsureds(66666666,"asfalismenos 6","RETHIMNO");
		A.InsertInsureds(77777777,"asfalismenos 7","RETHIMNO");
		A.InsertInsureds(88888888,"asfalismenos 8","RETHIMNO");
		A.InsertInsureds(99999999,"asfalismenos 9","RETHIMNO");


//Kentra
        A.InsertCenter(22222,"CH-22","XANIA");
        A.InsertCenter(33333,"RTH-33","RETHIMNO");



        A.InsertDoctorToCenterManually(22222,111111,"DOCTOR 1");
        A.InsertDoctorToCenterManually(22222,222222,"DOCTOR 2");
        A.InsertDoctorToCenterManually(22222,333333,"DOCTOR 3");


        A.InsertDoctorToCenterManually(33333,444444,"DOCTOR 4");
        A.InsertDoctorToCenterManually(33333,555555,"DOCTOR 5");


          //Katalogos rantevou kentrou xaniwn.
        A.InsertAppToCenterManually(11111111,1,1,111111);
        A.InsertAppToCenterManually(22222222,1,4,111111);
        A.InsertAppToCenterManually(33333333,1,4,222222);
        A.InsertAppToCenterManually(44444444,1,4,333333);
        A.InsertAppToCenterManually(55555555,2,2,111111);


        //Katalogos rantevou kentrou rethimnou
		A.InsertAppToCenterManually(66666666,2,2,444444);
		A.InsertAppToCenterManually(77777777,2,2,555555);
		A.InsertAppToCenterManually(88888888,2,3,555555);
		A.InsertAppToCenterManually(99999999,3,2,444444);







		Scanner st = new Scanner(System.in);
		int Choise=0;
		boolean Check=true;
		while(Check) {
			System.out.println();
			System.out.println("\t---- Menu----");
			System.out.println("Make a choice(1-9) ");
			System.out.println("1. Insert an Insured to the system");
			System.out.println("2. Print all insureds");
			System.out.println("3. Insert a Vaccination Center to the System");
			System.out.println("4. Insert a doctor in the center you prefer");
			System.out.println("5. Print all Center and Doctors");
			System.out.println("6. Add Appointment");
			System.out.println("7.Find And Print Appointments(3 options)");
			System.out.println("8. Exit from the menu!!!");
			System.out.print("Please enter your choise:");
			Choise = st.nextInt();
			st.nextLine();
			System.out.println();
			System.out.println();


			switch (Choise) {
				case 1:
					System.out.println("Give the Name of the insured: ");
					String name = st.nextLine();


					System.out.println("Give the AMKA of the insured:");
					long AMKA = st.nextLong();

					st.nextLine();
					System.out.println("Give the city of the insured:");
					String city = st.nextLine();



					A.InsertInsureds(AMKA, name, city);


					break;


				case 2:
					A.PrintAllInsureds();
					break;


				case 3:
					System.out.println("Give the name of the Center:");
					String CenterName=st.nextLine();


					System.out.println("Give the Location of the Center:");
					String CenterLocation=st.nextLine();


					System.out.println("Give the code of the Center");
					int CenterCode=st.nextInt();

					st.nextLine();


					A.InsertCenter(CenterCode,CenterName,CenterLocation);

					break;

				case 4:
					System.out.println("Give the Name of the doctor:");
					String DoctorName=st.nextLine();

					System.out.println("Give the doctor's Am:");
					int DoctorAM=st.nextInt();


					A.InsertDoctorToCenter(st,DoctorAM,DoctorName);

                         break;

				case 5:
					A.PrintAllCentersAndDoctors();
					break;


				case 6:
					System.out.println("Give the AMKA :");
							long AmkaForSearch=st.nextLong();

							A.InsertAppToCenter(st,AmkaForSearch);


							break;


				case 7:
					System.out.println("Make o choise(1-3)");
					System.out.println("1.Search for the appointment by  the AMKA of the insured");
					System.out.println("2.Search for the appointments by the  Code of the Center");
					System.out.println("3.Search for the appointments by the AM of the Doctor");
					System.out.println("Please Enter your choise:");
					int choise=st.nextInt();
					switch(choise){

						case 1:
							System.out.println("Give the AMKA of the insured you want to see the Appointment");
							long AMKAToSearch= st.nextLong();
							A.FindInsuredAppFromAmka(AMKAToSearch);
							break;

						case 2:
							System.out.println("Give the Code of The Center you want to see the Appointments");
							int CenterCodeForSearch=st.nextInt();
							A.FindCentersAppsFromCode(CenterCodeForSearch);
							break;

						case 3:
							System.out.println("Give the AM of the Doctor you want to see his Appointments");
							int AmForSearch=st.nextInt();
                              A.FindAppsWithDoctorAM(AmForSearch);
                              break;



					}
					break;


				case 8:

					System.out.println("End of program");
                  Check=false;
                  break;
				case 9:
					A.printAPPS();


			}



		}
		st.close();

	}

}
