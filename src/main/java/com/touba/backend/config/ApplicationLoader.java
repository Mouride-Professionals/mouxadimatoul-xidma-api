package com.touba.backend.config;

import com.touba.backend.model.AccountType;
import com.touba.backend.model.Evenement;
import com.touba.backend.model.Utilisateur;
import com.touba.backend.repository.EvenementRepository;
import com.touba.backend.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationLoader implements CommandLineRunner {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private EvenementRepository evenementRepository;

    @Override
    public void run(String... args) {
        if (utilisateurRepository.count() <= 0) {
            System.out.println("Load fixtures");
            utilisateurRepository.saveAll(List.of(new Utilisateur(
                    "777197482",
                    new BCryptPasswordEncoder().encode("admin"),
                    AccountType.ADMIN,
                    true,
                    "Admin",
                    "Admin",
                    "   ",
                    null
            )));
            evenementRepository.saveAll(List.of(
                    new Evenement("Magal de Touba"),
                    new Evenement("Magal Kaju Rajab"),
                    new Evenement("Touba Bootcamp 1ere edition")
            ));
        }
    }
}
