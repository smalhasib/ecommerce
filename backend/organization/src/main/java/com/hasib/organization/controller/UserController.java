package com.hasib.organization.controller;

import com.hasib.organization.dto.UserDto;
import com.hasib.organization.dto.UsersResponseDto;
import com.hasib.organization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<UsersResponseDto> getUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return ResponseEntity.ok(userService.getAllUsers(pageNumber, pageSize));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> userDetail(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("{id}/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("id") int userId) {
        System.out.println(userDto);
        return ResponseEntity.ok(userService.updateUser(userDto, userId));
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully ");
    }
}
