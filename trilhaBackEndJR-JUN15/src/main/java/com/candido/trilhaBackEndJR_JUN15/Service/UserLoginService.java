package com.candido.trilhaBackEndJR_JUN15.Service;

import com.candido.trilhaBackEndJR_JUN15.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserLoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userRepository.findByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new ObjectNotFoundException("Usuário não encontrado: ", e);

        }
    }

}
