package wolox.training.security;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import wolox.training.models.User;
import wolox.training.repositories.IUserRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;

    public User registerNewUserAccount(User userDto) {
        User user = new User();
        user.setUserUsername(userDto.getUserUsername());
        user.setUserName(userDto.getUserName());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        user.setUserUsername(userDto.getUserUsername());
        return userRepository.save(user);
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        return new UsernamePasswordAuthenticationToken(
                name, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
