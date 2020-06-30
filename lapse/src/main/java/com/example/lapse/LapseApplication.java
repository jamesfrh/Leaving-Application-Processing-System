package com.example.lapse;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.lapse.domain.LeaveType;
import com.example.lapse.domain.Manager;
import com.example.lapse.domain.Staff;
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
			
			Manager manager1 = new Manager("JAMES","JAMESPASSWORD","EMAIL@gmail.com");
			Manager manager2 = new Manager("BOB","BOBPASSWORD","BOBEMAIL@gmail.com");
			Manager manager3 = new Manager("MARK","MARKPASSWORD","MARKEMAIL@gmail.com");

			staffRepo.save(manager1);
			staffRepo.save(manager2);
			staffRepo.save(manager3);

			Staff staff1 = new Staff("JOHN", "JOHNPASSWORD", "JOHNEMAIL@gmail.com", manager1);
			Staff staff2 = new Staff("JAKE", "JAKEPASSWORD1", "JAKEEMAIL@gmail.com", manager3);
			Staff staff3 = new Staff("ELL", "ELLPASSWORD1", "ELLEMAIL@gmail.com", manager3);
			
			staffRepo.save(staff1);
			staffRepo.save(staff2);
			staffRepo.save(staff3);
			
			

			//Removed: 
//			//promoting a staff (can be put into a method)
//			Manager newManager = new Manager();
//			
//			//setting all staff attributes into new manager obj and delete previous staff obj
//			newManager.setEmail(staff1.getEmail());
//			newManager.setName(staff1.getName());
//			newManager.setPassword(staff1.getPassword());
//			newManager.setAnnualLeaveEntitlement(staff1.getAnnualLeaveEntitlement());
//			newManager.setMedicalLeaveEntitment(staff1.getMedicalLeaveEntitment());
//			newManager.setCompensationLeaveEntitlment(staff1.getCompensationLeaveEntitlment());
//			mgrRepo.save(newManager);
//			staffRepo.delete(staff1);

			
//			List<Manager> managerList1 = mgrRepo.findByEmail("BOBEMAIL");
//			for (Iterator <Manager> iterator = managerList1.iterator(); iterator.hasNext();) {
//				Manager user = (Manager) iterator.next();
//				System.out.println(user.toString());
//			}
			
			//User applied start date and end date of leave
			LocalDate dateStart = LocalDate.of(2020, 6, 15);
			LocalDate dateEnd = LocalDate.of(2020, 6,22);
			System.out.println(dateStart);
			System.out.println(dateEnd);

			//Validate that start day and end day must not be weekends
			DayOfWeek dayStart = DayOfWeek.of(dateStart.get(ChronoField.DAY_OF_WEEK));
			System.out.println(dayStart);
			DayOfWeek dayEnd = DayOfWeek.of(dateEnd.get(ChronoField.DAY_OF_WEEK));
			System.out.println(dayStart);
			
			if((dayStart == DayOfWeek.SATURDAY || dayStart == DayOfWeek.SUNDAY) ||
					(dayEnd == DayOfWeek.SATURDAY || dayEnd == DayOfWeek.SUNDAY)) {
				System.out.println("start day/ end day of leave is a weekend");
			}
			
			
			//Retrieve number of days in between start and end date (inclusive of start and end)
			float appliedCalendarLeaveDays = ChronoUnit.DAYS.between(dateStart,dateEnd) + 1;
			System.out.println(appliedCalendarLeaveDays);

			
			float actualLeaveDaysApplied = 0;

			//validate if appliedLeaveDays <=14 with a method and put the if statment below inside
			if(appliedCalendarLeaveDays <=14) {
				//converting start and end date from LocalDate format to Calendar format
	            GregorianCalendar calStart = GregorianCalendar.from(dateStart.atStartOfDay(ZoneId.systemDefault()));
	            GregorianCalendar calEnd = GregorianCalendar.from(dateEnd.atStartOfDay(ZoneId.systemDefault()));


	            //validate if start date and end date are the same, return an error if they are
	            //calStart.getTimeInMillis() == calEnd.getTimeInMillis()) 

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


			 	
		};
		
		
	}
}
