package net.asdevs.myhomegc2.security;

import lombok.Data;

@Data
public class AuthenticationRequestValue {
    private String username;
    private String password;
}
