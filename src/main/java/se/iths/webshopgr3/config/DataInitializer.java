package se.iths.webshopgr3.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.repository.AppUserRepository;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.password:changeThisPassword}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {

        if (!appUserRepository.existsByUsername("admin@test.com")) {
            AppUser admin = new AppUser();
            admin.setUsername("admin@test.com");
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setConsent(true);
            admin.setRole("ADMIN");
            appUserRepository.save(admin);
        }
    }
}



