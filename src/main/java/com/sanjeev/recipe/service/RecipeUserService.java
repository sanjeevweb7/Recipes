package com.sanjeev.recipe.service;

import com.sanjeev.recipe.model.MyUserDetails;
import com.sanjeev.recipe.model.User;
import com.sanjeev.recipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeUserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUserId(userId);

        user.orElseThrow(() -> new UsernameNotFoundException("User Name not found exception for user ::"+userId));
        return user.map(MyUserDetails::new).get();
    }
}
