package edu.kpi.iasa.mmsa.dance_std.service;

import edu.kpi.iasa.mmsa.dance_std.exception.RoleNotFoundException;
import edu.kpi.iasa.mmsa.dance_std.model.Role;
import edu.kpi.iasa.mmsa.dance_std.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String name) {
        return roleRepository.findRoleByName(name).orElseThrow(RoleNotFoundException::new);
    }

}
