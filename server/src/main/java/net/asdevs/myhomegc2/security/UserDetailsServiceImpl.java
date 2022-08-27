package net.asdevs.myhomegc2.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import net.asdevs.myhomegc2.repository.ScUsersMapper;
import net.asdevs.myhomegc2.repository.entity.ScUsersEntity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ScUsersMapper scUsersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            System.out.println(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("root"));
            ScUsersEntity outEntity = scUsersMapper.findByName(username);
            return new User(outEntity.getName(), PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(outEntity.getPassword()), new ArrayList<>());
        } catch (Exception e) {
            throw new UsernameNotFoundException("ユーザが見つかりません", e);
        }
    }
    
}
