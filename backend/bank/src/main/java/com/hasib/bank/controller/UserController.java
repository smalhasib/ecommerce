package com.hasib.bank.controller;

import com.hasib.bank.dto.UserDto;
import com.hasib.bank.dto.UsersResponseDto;
import com.hasib.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> userDetail(@PathVariable int id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-accountNumber")
    public ResponseEntity<?> userDetail(@RequestParam long accountNumber) {
        try {
            return ResponseEntity.ok(userService.getUserByAccountNumber(accountNumber));
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDto UserDto, @PathVariable("id") int UserId) {
        try {
            return ResponseEntity.ok(userService.updateUser(UserDto, UserId));
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int UserId) {
        try {
            userService.deleteUser(UserId);
            return ResponseEntity.ok("User deleted successfully ");
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
}
