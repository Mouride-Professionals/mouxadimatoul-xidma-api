package com.touba.backend.service.auth;

import com.touba.backend.model.AccountType;
import com.touba.backend.model.Utilisateur;
import com.touba.backend.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );

        AccountType accountType = user.getAccountType() != null ? user.getAccountType() : AccountType.KHIDMA_AGENT;
        List<SimpleGrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + accountType.name())
        );
        return new User(username, user.getPassword(), authorities);
    }
}
