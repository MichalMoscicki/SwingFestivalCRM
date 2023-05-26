package pl.coderslab.finalproject.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.models.person.Admin;

import java.util.HashSet;
import java.util.Set;

@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    private final AdminService adminService;

    public SpringDataUserDetailsService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Admin admin = adminService.findByEmail(email);
        if (admin == null) {throw new UsernameNotFoundException(email); }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        admin.getRoles().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
        return new org.springframework.security.core.userdetails.User(
                admin.getEmail(), admin.getPassword(), grantedAuthorities);
    }
}


