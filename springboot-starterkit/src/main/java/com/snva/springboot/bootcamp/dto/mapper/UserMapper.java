package com.snva.springboot.bootcamp.dto.mapper;

import com.snva.springboot.bootcamp.dto.model.user.RoleDto;
import com.snva.springboot.bootcamp.dto.model.user.UserDto;
import com.snva.springboot.bootcamp.dto.model.user.UserProfileDto;
import com.snva.springboot.bootcamp.model.user.Role;
import com.snva.springboot.bootcamp.model.user.User;
import com.snva.springboot.bootcamp.model.user.UserProfile;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Arpit Khandelwal.
 */
@Component
public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto()
                .setId(user.getId())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setMobileNumber(user.getMobileNumber())
                .setAddress(user.getAddress())
                .setAdmin(false)
                .setPassword(user.getPassword())
                .setProfilePicture(user.getProfilePicture())
                .setUserProfile(new HashSet<UserProfileDto>(
                        user.getUserProfiles()
                                .stream()
                                .map(profile-> new ModelMapper().map(profile,UserProfileDto.class))
                                .collect(Collectors.toSet())
                ))
                .setRoles(new HashSet<RoleDto>(user
                        .getRoles()
                        .stream()
                        .map(role -> new ModelMapper().map(role, RoleDto.class))
                        .collect(Collectors.toSet())));
    }

    public static User toUser(UserDto user) {
        return new User()
                .setId(user.getId())
                .setProfilePicture(user.getProfilePicture())
                .setPassword(user.getPassword())
                .setAddress(user.getAddress())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setMobileNumber(user.getMobileNumber())
                .setUserProfiles(new HashSet<UserProfile>(
                        user.getUserProfile()
                                .stream()
                                .map(profile-> new ModelMapper().map(profile, UserProfile.class))
                                .collect(Collectors.toSet())
                ))
                .setRoles(new HashSet<Role>(user
                        .getRoles()
                        .stream()
                        .map(role -> new ModelMapper().map(role, Role.class))
                        .collect(Collectors.toSet())));
    }

    public static List<UserDto> toUserDtoList(List<User> all) {
        List<UserDto> userDtors = new ArrayList<>();
        all.stream().forEach(x-> userDtors.add( toUserDto(x)));
        return  userDtors;
    }

    public static List<UserDto> toUserDtoListSecure(List<User> all) {
        List<UserDto> userDtors = new ArrayList<>();
            all.stream().forEach(x->{
                UserDto userDto = toUserDto(x);
                userDto.setPassword("**************Secured*************");
                userDtors.add(userDto);
            });
        return  userDtors;
    }
}
