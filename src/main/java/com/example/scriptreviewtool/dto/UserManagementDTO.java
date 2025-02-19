package com.example.scriptreviewtool.dto;

import java.util.List;

public class UserManagementDTO {
    private String username;
    private String email;
    private String password;
    private String roleName;
    private List<Long> assignedScriptIds;

    // Constructeurs
    public UserManagementDTO() {}

    // Getters et Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Long> getAssignedScriptIds() {
        return assignedScriptIds;
    }

    public void setAssignedScriptIds(List<Long> assignedScriptIds) {
        this.assignedScriptIds = assignedScriptIds;
    }
}
