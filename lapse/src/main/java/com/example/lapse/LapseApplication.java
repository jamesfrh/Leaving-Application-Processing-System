package com.example.lapse;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
			Manager manager1 = new Manager("JAMES","JAMESPASSWORD","EMAIL@gmail.com",10,11,12);
			Manager manager2 = new Manager("BOB","BOBPASSWORD","BOBEMAIL@gmail.com",13,14,15);
			Manager manager3 = new Manager("MARK","MARKPASSWORD","MARKEMAIL@gmail.com",16,17,18);

			staffRepo.save(manager1);
			staffRepo.save(manager2);
			staffRepo.save(manager3);

			
			Staff staff1 = new Staff("JOHN", "JOHNPASSWORD", "JOHNEMAIL@gmail.com", 20,21,22);
			Staff staff2 = new Staff("JAKE", "JAKEPASSWORD1", "JAKEEMAIL@gmail.com", 23,24,25);
			Staff staff3 = new Staff("ELL", "ELLPASSWORD1", "ELLEMAIL@gmail.com", 29,28,26);
			
			staff1.setManager(manager1);
			staff2.setManager(manager3);
			staff3.setManager(manager3);
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

			
			List<Manager> managerList1 = mgrRepo.findByEmail("BOBEMAIL");
			for (Iterator <Manager> iterator = managerList1.iterator(); iterator.hasNext();) {
				Manager user = (Manager) iterator.next();
				System.out.println(user.toString());
			}

			
			
		};
	}
}

