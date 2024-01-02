package com.ks.gr.user.controller;

import com.ks.gr.user.entity.dto.BaseResponseDto;
import com.ks.gr.user.entity.dto.UserResponseDto;
import com.ks.gr.user.entity.dto.UserUpdateDto;
import com.ks.gr.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/by-id/{id}")
    public UserResponseDto getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/by-username/{username}")
    public UserResponseDto getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/by-email/{email}")
    public UserResponseDto getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto updateUser(@PathVariable long id, @RequestBody UserUpdateDto userUpdateDto) {
        return userService.updateUser(id, userUpdateDto);
    }

    @DeleteMapping("/{id}")
    public BaseResponseDto deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return new BaseResponseDto("User successfully deleted");
    }


}

