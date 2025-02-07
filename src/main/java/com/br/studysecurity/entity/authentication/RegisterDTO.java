package com.br.studysecurity.entity.authentication;

import com.br.studysecurity.entity.UserRole;

public record RegisterDTO(String username, String password, UserRole role) {
}
