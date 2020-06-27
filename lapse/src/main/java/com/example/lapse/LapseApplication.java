package com.example.lapse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.lapse.domain.Manager;
import com.example.lapse.domain.Staff;
import com.example.lapse.repo.AdminRepo;
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


	public static void main(String[] args) {
		SpringApplication.run(LapseApplication.class, args);
		
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			Manager manager1 = new Manager("JAMES","JAMESPASSWORD","EMAIL",10,11,12);
			Manager manager2 = new Manager("BOB","BOBPASSWORD","BOBEMAIL",13,14,15);
			Manager manager3 = new Manager("MARK","MARKPASSWORD","MARKEMAIL",16,17,18);

			mgrRepo.save(manager1);
			mgrRepo.save(manager2);
			mgrRepo.save(manager3);

			
			Staff staff1 = new Staff("JOHN", "JOHNPASSWORD", "JOHNEMAIL", 20,21,22, manager1);
			Staff staff2 = new Staff("JAKE", "JAKEPASSWORD1", "JAKEEMAIL", 23,24,25, manager3);
			Staff staff3 = new Staff("ELL", "ELLPASSWORD1", "ELLEMAIL", 29,28,26, manager3);

			staffRepo.save(staff1);
			staffRepo.save(staff2);
			staffRepo.save(staff3);
			
			//promoting a staff (can be put into a method)
			Manager newManager = new Manager();
			
			//setting all staff attributes into new manager obj
			newManager.setEmail(staff1.getEmail());
			newManager.setName(staff1.getName());
			newManager.setPassword(staff1.getPassword());
			newManager.setAnnualLeaveEntitlement(staff1.getAnnualLeaveEntitlement());
			newManager.setMedicalLeaveEntitment(staff1.getMedicalLeaveEntitment());
			newManager.setCompensationLeaveEntitlment(staff1.getCompensationLeaveEntitlment());
			
			mgrRepo.save(newManager);
			staffRepo.delete(staff1);

				
			
			
			

		};
	}
}

