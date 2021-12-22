package edu.kpi.iasa.mmsa.dance_std.service;
import edu.kpi.iasa.mmsa.dance_std.exception.GroupNotFoundException;
import edu.kpi.iasa.mmsa.dance_std.exception.RequestNotFoundException;
import edu.kpi.iasa.mmsa.dance_std.exception.StatusNotFoundException;
import edu.kpi.iasa.mmsa.dance_std.exception.UserNotFoundException;
import edu.kpi.iasa.mmsa.dance_std.model.Account;
import edu.kpi.iasa.mmsa.dance_std.model.Group;
import edu.kpi.iasa.mmsa.dance_std.model.Request;
import edu.kpi.iasa.mmsa.dance_std.repository.GroupRepository;
import edu.kpi.iasa.mmsa.dance_std.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }
    public List<Group> getGroup(Long idtrain) {

        return groupRepository.findAllByIdtrain(idtrain);
    }
    public List<Group> getGroups() {

        return groupRepository.findAll();
    }
    public Group getGroupById(Long id) {
        return groupRepository.findGroupById(id).orElseThrow(RequestNotFoundException::new);
    }
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group updateGroupById(Long id, Long id_user){
        Group group = groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
        Collection<Account> users = group.getUsers();
        users.add(userRepository.findById(id_user).orElseThrow(UserNotFoundException::new));
        group.setUsers(users);
        return groupRepository.save(group);
    }

}

