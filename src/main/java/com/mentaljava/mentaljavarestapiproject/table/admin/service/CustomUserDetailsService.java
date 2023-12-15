package com.mentaljava.mentaljavarestapiproject.table.admin.service;

import com.mentaljava.mentaljavarestapiproject.table.admin.dto.AdminDTO;
import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import com.mentaljava.mentaljavarestapiproject.table.admin.repository.AdminRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    private final ModelMapper modelMapper;

    public CustomUserDetailsService(AdminRepository adminRepository,
                                    ModelMapper modelMapper){
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        Optional<Admin> admin = adminRepository.findById(memberId);
        System.out.println("admin ==========> " + admin);
        /* AdminDTO는 엔티티를 옮겨 담는 DTO이자 UserDetails이다. */
        AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);

        List<GrantedAuthority> authorities = new ArrayList<>();

        String authorityName = adminDTO.getPermissionName();
        authorities.add(new SimpleGrantedAuthority(authorityName));


        adminDTO.setAuthorities(authorities);

        return adminDTO;
    }
}
