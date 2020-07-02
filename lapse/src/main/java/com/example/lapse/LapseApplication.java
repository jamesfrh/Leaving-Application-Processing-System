package com.example.lapse;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.lapse.domain.LeaveApplication;
import com.example.lapse.domain.LeaveType;
import com.example.lapse.domain.Manager;
import com.example.lapse.domain.Staff;
import com.example.lapse.enums.LeaveStatus;
import com.example.lapse.enums.TimeOfDay;
import com.example.lapse.repo.AdminRepo;
import com.example.lapse.repo.LeaveApplicationRepo;
import com.example.lapse.repo.LeaveTypeRepo;
import com.example.lapse.repo.ManagerRepo;
import com.example.lapse.repo.StaffRepo;

@SpringBootApplication
public class LapseApplication {

	@Autowired
	StaffRepo staffRepo;

	@Autowired
	ManagerRepo mgrRepo;

	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	LeaveTypeRepo ltRepo;
	
	@Autowired
	LeaveApplicationRepo laRepo;

	public static void main(String[] args) {
		SpringApplication.run(LapseApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			LeaveType lt1 = new LeaveType("Annual Leave", 14);
			LeaveType lt2 = new LeaveType("Medical Leave", 60);
			LeaveType lt3 = new LeaveType("Compensation Leave", 0);
			ltRepo.save(lt1);
			ltRepo.save(lt2);
			ltRepo.save(lt3);
			
			List<LeaveType> leaveList = ltRepo.findAll();
			for (Iterator<LeaveType> iterator = leaveList.iterator(); iterator.hasNext();) {
				LeaveType leaveType = (LeaveType) iterator.next();
				System.out.println(leaveType.toString());
				
			}
			
			
			Manager manager1 = new Manager("JAMES","JAMESPASSWORD","EMAIL@gmail.com",leaveList );
			Manager manager2 = new Manager("BOB","BOBPASSWORD","BOBEMAIL@gmail.com",leaveList);
			Manager manager3 = new Manager("MARK","MARKPASSWORD","MARKEMAIL@gmail.com",leaveList);

			staffRepo.save(manager1);
			staffRepo.save(manager2);
			staffRepo.save(manager3);

			Staff staff1 = new Staff("JOHN", "JOHNPASSWORD", "JOHNEMAIL@gmail.com", leaveList, manager1);
			Staff staff2 = new Staff("JAKE", "JAKEPASSWORD1", "JAKEEMAIL@gmail.com", leaveList, manager3);
			Staff staff3 = new Staff("ELL", "ELLPASSWORD1", "ELLEMAIL@gmail.com", leaveList, manager3);
			
			staffRepo.save(staff1);
			staffRepo.save(staff2);
			staffRepo.save(staff3);
			
			//User applied start date and end date of leave
			LocalDate ApplicationDate = LocalDate.of(2020, 6, 1);

			
			LocalDate dateStart1 = LocalDate.of(2020, 6, 15);
			LocalDate dateEnd1 = LocalDate.of(2020, 6,17);
			
			LocalDate dateStart2 = LocalDate.of(2020, 6, 3);
			LocalDate dateEnd2 = LocalDate.of(2020, 6,18);

			//Retrieve number of days in between start and end date (inclusive of start and end)
			float noOfDays1 = ChronoUnit.DAYS.between(dateStart1,dateEnd1) + 1;
			float noOfDays2 = ChronoUnit.DAYS.between(dateStart2,dateEnd2) + 1;
			
			//converting localdate to date
			Date APPLICATIONDATE =java.sql.Date.valueOf(ApplicationDate);

			Date START1 =java.sql.Date.valueOf(dateStart1);
			Date END1 = java.sql.Date.valueOf(dateEnd1);
			
			Date START2 =java.sql.Date.valueOf(dateStart2);
			Date END2 = java.sql.Date.valueOf(dateEnd2);
			
			
			LeaveApplication apply1 = new LeaveApplication(APPLICATIONDATE, START1,/*TimeOfDay.PM,*/END1, /*TimeOfDay.AM,*/
					lt1, noOfDays1, LeaveStatus.APPLIED,"holiday","staff 2", true, false, "999",  "holiday", staff1);
			
			LeaveApplication apply2 = new LeaveApplication(APPLICATIONDATE, START2,/*TimeOfDay.AM,*/ END2, /*TimeOfDay.AM,*/
					lt2, noOfDays2, LeaveStatus.APPLIED, "sick", "wait for return", true, false, "888",  "medical", staff2);
			
			LeaveApplication apply3 = new LeaveApplication(APPLICATIONDATE, START2,/*TimeOfDay.AM,*/END2, /*TimeOfDay.AM,*/
					lt2, noOfDays2, LeaveStatus.APPROVED, "sick again", "return", true, false, "777",  "hospital", manager2);
			
			laRepo.save(apply1);
			laRepo.save(apply2);
			laRepo.save(apply3);


			//Validate that start day and end day must not be weekends
			DayOfWeek dayStart = DayOfWeek.of(dateStart1.get(ChronoField.DAY_OF_WEEK));
			System.out.println(dayStart);
			DayOfWeek dayEnd = DayOfWeek.of(dateEnd1.get(ChronoField.DAY_OF_WEEK));
			System.out.println(dayStart);
			
			if((dayStart == DayOfWeek.SATURDAY || dayStart == DayOfWeek.SUNDAY) ||
					(dayEnd == DayOfWeek.SATURDAY || dayEnd == DayOfWeek.SUNDAY)) {
				System.out.println("start day/ end day of leave is a weekend");
			}
			
		
			float actualLeaveDaysApplied = 0;

			//validate if appliedLeaveDays <=14 with a method and put the if statment below inside
			if(noOfDays1 <=14) {
				//converting start and end date from LocalDate format to Calendar format
	            GregorianCalendar calStart = GregorianCalendar.from(dateStart1.atStartOfDay(ZoneId.systemDefault()));
	            GregorianCalendar calEnd = GregorianCalendar.from(dateEnd1.atStartOfDay(ZoneId.systemDefault()));
				System.out.println(calStart);
				
//				System.out.println(daysBetween);
//				System.out.println(noOfDays1);


	            //validate if end date is before start date, return an error if they are
	            //startCal.getTimeInMillis() > endCal.getTimeInMillis()


				do {
				  if (calStart.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY &&
				  calStart.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {;
				  actualLeaveDaysApplied++; }
				  calStart.add(Calendar.DAY_OF_MONTH, 1);
				  } 
				while (calStart.getTimeInMillis() <= calEnd.getTimeInMillis());
				
				System.out.println(actualLeaveDaysApplied);
				// return actualLeaveDaysApplied
			}
			//query if balance in DB >= actualLeaveDaysApplied
			//deduct from balance if ok, if not then return an error

			//when appliedLeaveDays >14 
			else {
				//query if balance in DB >= appliedLeaveDays
				//deduct from balance if ok, if not then return an error
				
			}

			
				  Calendar cal = Calendar.getInstance();
				  cal.setTime(START1);
			      System.out.println(cal);
			      
			      
			      // test get sum of leaves applied by Staff3 
					LeaveApplication apply4 = new LeaveApplication(APPLICATIONDATE, START1,/*TimeOfDay.PM,*/END1, /*TimeOfDay.AM,*/
							lt2, noOfDays1, LeaveStatus.APPLIED,"holiday","staff 2", true, false, "999",  "holiday", staff3);
					LeaveApplication apply5 = new LeaveApplication(APPLICATIONDATE, START1,/*TimeOfDay.PM,*/END1, /*TimeOfDay.AM,*/
							lt2, noOfDays1, LeaveStatus.APPLIED,"holiday","staff 2", true,false, "999",  "holiday", staff3);
					LeaveApplication apply6 = new LeaveApplication(APPLICATIONDATE, START1,/*TimeOfDay.PM,*/END1, /*TimeOfDay.AM,*/
							lt2, noOfDays1, LeaveStatus.APPLIED,"holiday","staff 2", true,false, "999",  "holiday", staff3);
					laRepo.save(apply4);
					laRepo.save(apply5);
					laRepo.save(apply6);
					
			      float totaldaysapplied = laRepo.getSumOfLeavesAppliedByStaff(6, 2);
			      System.out.println(totaldaysapplied);
		};
	}
}
