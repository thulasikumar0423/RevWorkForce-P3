package com.rev.user_service.controller;

import com.rev.user_service.dto.request.CreateUserRequest;
import com.rev.user_service.dto.request.SearchUserRequest;
import com.rev.user_service.dto.request.UpdateUserRequest;
import com.rev.user_service.dto.response.EmployeeDirectoryResponse;
import com.rev.user_service.dto.response.UserResponse;
import com.rev.user_service.service.UserManagementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")

@Tag(name = "User Management API", description = "Operations related to user management")
public class UserManagementController {

    private final UserManagementService userService;

    public UserManagementController(UserManagementService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get manager of a user")
    @ApiResponse(responseCode = "200", description = "Manager fetched successfully")
    @GetMapping("/{id}/manager")
    public ResponseEntity<UserResponse> getManager(@PathVariable Long id) {

        return ResponseEntity.ok(userService.getManager(id));
    }

    @Operation(summary = "Get employee directory")
    @ApiResponse(responseCode = "200", description = "Employee directory retrieved")
    @GetMapping("/directory")
    public ResponseEntity<List<EmployeeDirectoryResponse>> getEmployeeDirectory() {

        return ResponseEntity.ok(userService.getEmployeeDirectory());
    }

    @Operation(summary = "Get team members under a manager")
    @GetMapping("/manager/{managerId}/team")
    public ResponseEntity<List<EmployeeDirectoryResponse>> getTeamMembers(
            @PathVariable Long managerId) {

        return ResponseEntity.ok(userService.getTeamMembers(managerId));
    }

    @Operation(summary = "Create new user")
    @ApiResponse(responseCode = "200", description = "User created successfully")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {

        return ResponseEntity.ok(userService.createUser(request));
    }

    @Operation(summary = "Update existing user")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request) {

        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @Operation(summary = "Get user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "Search users")
    @PostMapping("/search")
    public ResponseEntity<List<EmployeeDirectoryResponse>> searchUsers(
            @RequestBody SearchUserRequest request) {

        return ResponseEntity.ok(userService.searchUsers(request));
    }

    @Operation(summary = "Deactivate user")
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable Long id) {

        userService.deactivateUser(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Reactivate user")
    @PutMapping("/{id}/reactivate")
    public ResponseEntity<Void> reactivateUser(@PathVariable Long id) {

        userService.reactivateUser(id);
        return ResponseEntity.ok().build();
    }
}