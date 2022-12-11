package com.example.demo;

import com.example.demo.models.Role;
import com.example.demo.models.account.Account;
import com.example.demo.models.account.Checking;
import com.example.demo.models.users.AccountHolders;
import com.example.demo.models.users.Admin;
import com.example.demo.models.users.ThirdParty;
import com.example.demo.repositories.account.AccountRepository;
import com.example.demo.repositories.account.RoleRepository;
import com.example.demo.repositories.user.AccountHoldersRepository;
import com.example.demo.repositories.user.AdminRepository;
import com.example.demo.repositories.user.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class FinalProjectApplication implements CommandLineRunner {

	@Autowired
	AccountHoldersRepository accountHoldersRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	ThirdPartyRepository thirdPartyRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		AccountHolders accountHolders1 = accountHoldersRepository.save(new AccountHolders("User1", LocalDate.now(),
				null, "asd", passwordEncoder.encode("123")));
		Role role1 = roleRepository.save(new Role("USER", accountHolders1));
		AccountHolders accountHolders2 = accountHoldersRepository.save(new AccountHolders("User2", LocalDate.now(),
				null, "asd", passwordEncoder.encode("123")));
		Role role4 = roleRepository.save(new Role("USER", accountHolders2));
		Account checking = accountRepository.save(
				new Checking(new BigDecimal(1000),"secret",accountHolders1, accountHolders2));
		Account checking1 = accountRepository.save(
				new Checking(new BigDecimal(1000),"secret",accountHolders2, accountHolders1));

		Admin admin1 = adminRepository.save(new Admin("Admin1", passwordEncoder.encode("123")));
		Role role2 = roleRepository.save(new Role("ADMIN", admin1));

		ThirdParty thirdParty1 = thirdPartyRepository.save(new ThirdParty("ThirdParty1", "123"));
		Role role3 = roleRepository.save(new Role("THIRD-PARTY", thirdParty1));

	}
}
