package com.snva.springboot.bootcamp.service;

import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.UpdateBootcampRequest;
import com.snva.springboot.bootcamp.dto.mapper.BootcampMapper;
import com.snva.springboot.bootcamp.dto.model.bootcamp.BootcampDto;
import com.snva.springboot.bootcamp.exception.LearnerDromeException;
import com.snva.springboot.bootcamp.model.bootcamp.Bootcamp;
import com.snva.springboot.bootcamp.model.bootcamp.Session;
import com.snva.springboot.bootcamp.model.bootcamp.Technology;
import com.snva.springboot.bootcamp.model.user.User;
import com.snva.springboot.bootcamp.repository.bootcamp.BootcampRepository;
import com.snva.springboot.bootcamp.repository.bootcamp.SessionItemRepository;
import com.snva.springboot.bootcamp.repository.bootcamp.SessionRepository;
import com.snva.springboot.bootcamp.repository.bootcamp.TechnologyRepository;
import com.snva.springboot.bootcamp.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BootcampService implements  IBootcampService {


    @Autowired
    BootcampRepository bootcampRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    SessionItemRepository sessionItemRepository;

    @Autowired
    TechnologyRepository technologyRepository;
    // call the bootcamo Repository


    @Override
    public List<BootcampDto> getAllBootcamps() {
        return BootcampMapper.toBootcampDtoList(bootcampRepository.findAll());
    }

    @Override
    public BootcampDto getById(String id) {

        Optional<Bootcamp> bootcamp = bootcampRepository.findById(id);
        if (bootcamp.isPresent()){
            return BootcampMapper.toBootcampDto(bootcamp.get());
            //List<Bootcamp> bootcamps = (List<Bootcamp>) bootcamp.get();
//            for (Bootcamp b : bootcamps) {
//                if (b.getId().equals(bootcampDto.getId())) {
//                    Bootcamp bootcampToSave = BootcampMapper.toBootcamp(bootcampDto);
//                    BootcampMapper.toBootcampDto(bootcampRepository.save(bootcampToSave));
//                    return true;
//                }
            //}


        }else { //BootcampDto bootcampDto = null;
                return null;}
       // return null///Optional.empty();
    }

    @Override
    public List<BootcampDto> getByName(String name) {
        return null;
    }

    @Override
    public List<BootcampDto> getAllBootcampsByUser(String email) {
        return null;
    }

    @Override
    public boolean createBootcamp(BootcampDto bootcamp) {
        return false;
    }

    @Override
    public boolean updateBootcamp(BootcampDto bootcampDto) {
        Optional<List<Bootcamp>> bootcamp = Optional.ofNullable(bootcampRepository.findByName(bootcampDto.getName()));

        if (bootcamp.isPresent()) {
            List<Bootcamp> bootcamps = bootcamp.get();
            for (Bootcamp b : bootcamps) {
                if (b.getId().equals(bootcampDto.getId())) {
                    Bootcamp bootcampToSave = BootcampMapper.toBootcamp(bootcampDto);
                    BootcampMapper.toBootcampDto(bootcampRepository.save(bootcampToSave));
                    return true;
                }
            }
            // source -> userDto
            // destination ->  User
            // String role= bootcampDto.getRoles().stream().findFirst().get().getRole();
            // RoleDto roleDto= RoleMapper.toRoleDto( roleRepository.findByRole(role));
            // Set<RoleDto> roles = new HashSet<RoleDto>();
            // roles.add(roleDto);
            // userDto.setRoles(roles);
        }
        return false;
    }

    @Override
    public boolean deleteBootcamp(String id) {
        return false;
    }

    @Override
    public List<BootcampDto> registerUserBootcamp(BootcampDto email) {
        return null;
    }

    @Override
    public Bootcamp updateUsersBootcamp(UpdateBootcampRequest bootcampRequest) {
        List<User> users = new ArrayList<>();
        List<Session> sessions = new ArrayList<>();
        List<Technology> technologies = new ArrayList<>();
        userRepository.findAllById(Arrays.asList(bootcampRequest.getUserIds())).forEach(
                x -> users.add(x)
        );
        sessionRepository.findAllById(Arrays.asList(bootcampRequest.getSessionIds())).forEach(
                x -> sessions.add(x)
        );
        technologyRepository.findAllById(Arrays.asList(bootcampRequest.getTechnologyStackIds())).forEach(
                x -> technologies.add(x)
        );
        Optional<Bootcamp> bootcamp = bootcampRepository.findById(bootcampRequest.getId());
        if (bootcamp.isPresent()) {
            Bootcamp bootcampFromDb = bootcamp.get();
            bootcampFromDb.setUsers(users)
                    .setSessions(sessions)
                    .setTechnologyStack(technologies)
                    .setName(bootcampRequest.getName())
                    .setStartDate(bootcampRequest.getStartSate())
                    .setEndDate(bootcampRequest.getEndDate())
                    .setLongHtml(bootcampRequest.getLongHtml())
                    .setDescription(bootcampRequest.getDescription());

                    return bootcampRepository.save(bootcampFromDb);
        }
        throw  new LearnerDromeException.EntityNotFoundException("The Bootcamp your are working has issues !");
    }
}