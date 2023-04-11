package com.starterkit.springboot.brs.service;

import com.starterkit.springboot.brs.controller.v1.request.bootcamp.UpdateBootcampRequest;
import com.starterkit.springboot.brs.dto.mapper.BootcampMapper;
import com.starterkit.springboot.brs.dto.mapper.RoleMapper;
import com.starterkit.springboot.brs.dto.mapper.UserMapper;
import com.starterkit.springboot.brs.dto.model.bootcamp.BootcampDto;
import com.starterkit.springboot.brs.dto.model.user.RoleDto;
import com.starterkit.springboot.brs.exception.LearnerDromeException;
import com.starterkit.springboot.brs.model.bootcamp.Bootcamp;
import com.starterkit.springboot.brs.model.bootcamp.Session;
import com.starterkit.springboot.brs.model.bootcamp.Technology;
import com.starterkit.springboot.brs.model.user.User;
import com.starterkit.springboot.brs.repository.bootcamp.BootcampRepository;
import com.starterkit.springboot.brs.repository.bootcamp.SessionItemRepository;
import com.starterkit.springboot.brs.repository.bootcamp.SessionRepository;
import com.starterkit.springboot.brs.repository.bootcamp.TechnologyRepository;
import com.starterkit.springboot.brs.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.starterkit.springboot.brs.exception.EntityType.BOOTCAMP;
import static com.starterkit.springboot.brs.exception.ExceptionType.ENTITY_NOT_FOUND;

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
    public List<BootcampDto> getAllBootcampsByUser(String id) {
        return  BootcampMapper.toBootcampDtoList(  bootcampRepository.findAll().stream().filter(x->x.getUsers().stream().anyMatch(y->y.getId().equals(id))).collect(Collectors.toList()));
        //return BootcampMapper.toBootcampDtoList( bootcampRepository.findByUserId(id));
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