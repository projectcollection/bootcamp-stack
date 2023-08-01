package com.snva.springboot.bootcamp.service;

import com.snva.springboot.bootcamp.dto.mapper.RoleMapper;
import com.snva.springboot.bootcamp.dto.mapper.UserMapper;
import com.snva.springboot.bootcamp.dto.model.user.RoleDto;
import com.snva.springboot.bootcamp.dto.model.user.UserDto;
import com.snva.springboot.bootcamp.exception.LearnerDromeException;
import com.snva.springboot.bootcamp.exception.EntityType;
import com.snva.springboot.bootcamp.exception.ExceptionType;
import com.snva.springboot.bootcamp.model.user.Role;
import com.snva.springboot.bootcamp.model.user.User;
import com.snva.springboot.bootcamp.model.user.UserProfile;
import com.snva.springboot.bootcamp.model.user.UserRoles;
import com.snva.springboot.bootcamp.repository.user.ProfileRepository;
import com.snva.springboot.bootcamp.repository.user.RoleRepository;
import com.snva.springboot.bootcamp.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.snva.springboot.bootcamp.exception.EntityType.USER;
import static com.snva.springboot.bootcamp.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.snva.springboot.bootcamp.exception.ExceptionType.ENTITY_NOT_FOUND;

/**
 * Created by Arpit Khandelwal.
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private BusReservationService busReservationService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto signup(UserDto userDto) {
        Role userRole;
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user == null) {

            if (userDto.isAdmin()) {
                userRole = roleRepository.findByRole(UserRoles.ADMIN.name());
            } else {
                userRole = roleRepository.findByRole(UserRoles.PARTICIPANT.name());
            }
            UserProfile userProfile= new UserProfile(userDto.getEmail());
            Set<UserProfile> userProfileSet = new HashSet<>();


            userProfileSet.add(userProfile);

            userProfile = profileRepository.save(userProfile);


            user = new User()
                    .setAddress("")
                    .setEmail(userDto.getEmail())
                    .setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()))
                    .setRoles(new HashSet<>(Arrays.asList(userRole)))
                    .setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName())
                    .setProfilePicture(userDto.getProfilePicture())
                    .setUserProfiles(userProfileSet)
                    .setMobileNumber(userDto.getMobileNumber())
                    .setAddress(userDto.getAddress())
            ;
            return UserMapper.toUserDto(userRepository.save(user));
        }
        throw exception(USER, DUPLICATE_ENTITY, userDto.getEmail());
    }

    /**
     * Search an existing user
     *
     * @param email
     * @return
     */
    public UserDto findUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            return  UserMapper.toUserDto(user.get());
            //return modelMapper.map(, UserDto.class);
        }
        throw exception(USER, ENTITY_NOT_FOUND, email);
    }

    @Override
    public UserDto findUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return  UserMapper.toUserDto(user.get());
            //return modelMapper.map(, UserDto.class);
        }
        throw exception(USER, ENTITY_NOT_FOUND, id);
    }

    /**
     * Update User Profile
     *
     * @param userDto
     * @return
     */
    @Override
    public UserDto updateProfile(UserDto userDto) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if (user.isPresent()) {
            // source -> userDto
            // destination ->  User
            String role= userDto.getRoles().stream().findFirst().get().getRole();
            RoleDto roleDto= RoleMapper.toRoleDto( roleRepository.findByRole(role));
            Set<RoleDto> roles = new HashSet<RoleDto>();
            roles.add(roleDto);
            userDto.setRoles(roles);
            User userToSave=UserMapper.toUser(userDto);

            return UserMapper.toUserDto(userRepository.save(userToSave));
        }
        throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
    }

    /**
     * Change Password
     *
     * @param userDto
     * @param newPassword
     * @return
     */
    @Override
    public UserDto changePassword(UserDto userDto, String newPassword) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setPassword(bCryptPasswordEncoder.encode(newPassword));
            return UserMapper.toUserDto(userRepository.save(userModel));
        }
        throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
    }

    @Override
    public List<UserDto> allUsers() {
        return UserMapper.toUserDtoList( userRepository.findAll());
    }

    @Override
    public List<UserDto> allUsersSecure() {
        return UserMapper.toUserDtoListSecure( userRepository.findAll());
    }

    /**
     * Returns FarmApplicantRequest new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return LearnerDromeException.throwException(entityType, exceptionType, args);
    }
}
