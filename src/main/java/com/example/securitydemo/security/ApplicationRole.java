package com.example.securitydemo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationRole {
    STUDENT(Sets.newHashSet(ApplicationPermission.STUDENT_READ)),
    ADMIN(Sets.newHashSet(ApplicationPermission.COURSE_WRITE));

    private Set<ApplicationPermission> permissions;

    ApplicationRole(Set<ApplicationPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationPermission> getPermissions() {
        return this.getPermissions();
    }

    public Set<GrantedAuthority> getAuthorities(){
       return permissions.stream().map(p-> new SimpleGrantedAuthority(p.name())).collect(Collectors.toSet());
    }
}
