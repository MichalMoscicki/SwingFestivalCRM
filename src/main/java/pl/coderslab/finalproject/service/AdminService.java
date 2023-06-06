package pl.coderslab.finalproject.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.models.person.Admin;
import pl.coderslab.finalproject.models.role.Role;
import pl.coderslab.finalproject.repositories.AdminRepository;
import pl.coderslab.finalproject.repositories.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminService  {

    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }



public Admin findByEmail(String email) {
    return adminRepository.findByEmail(email);
}

public void save(Admin admin) {
    admin.setPassword(passwordEncoder.encode(admin.getPassword()));
    Role adminRole = roleRepository.findByName("ROLE_ADMIN");
    admin.setRoles(new HashSet<>(Arrays.asList(adminRole)));
    adminRepository.save(admin);
}


    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin findById(Long adminId) {
        return adminRepository.findById(adminId).get();
    }

    public boolean deleteById(Long adminId) {
        if(numberOfAdmins() == 1){
            return false;
        }
        Admin admin = adminRepository.findById(adminId).get();
        Set<Role> adminRoles = admin.getRoles();
        adminRoles.clear();
        admin.setRoles(adminRoles);
        adminRepository.save(admin);
        adminRepository.delete(admin);
        return true;
    }

    public int numberOfAdmins(){
        return adminRepository.findAll().size();
    }
}