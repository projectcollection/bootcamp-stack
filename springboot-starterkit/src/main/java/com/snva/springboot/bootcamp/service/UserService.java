package com.snva.springboot.bootcamp.service;

import com.snva.springboot.bootcamp.dto.model.user.UserDto;

import java.util.List;

/**
 * Created by Arpit Khandelwal.
 */
public interface UserService {
    /**
     * Register FarmApplicantRequest new user
     *
     * @param userDto
     * @return
     */
    UserDto signup(UserDto userDto);

    /**
     * Search an existing user
     *
     * @param email
     * @return
     */
    UserDto findUserByEmail(String email);

    UserDto findUserById(String id);

    /**
     * Update profile of the user
     *
     * @param userDto
     * @return
     */
    UserDto updateProfile(UserDto userDto);

    /**
     * Update password
     *
     * @param newPassword
     * @return
     */
    UserDto changePassword(UserDto userDto, String newPassword);

    List<UserDto> allUsers();

    List<UserDto> allUsersSecure();
}
