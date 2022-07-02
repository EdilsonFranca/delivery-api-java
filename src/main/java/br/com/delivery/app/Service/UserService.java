package br.com.delivery.app.Service;

import br.com.delivery.app.Model.User;
import br.com.delivery.app.Repository.UserRepository;
import br.com.delivery.app.Security.UserDetailsImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = new User();
        BeanUtils.copyProperties(userDetails, user);
        return user;
    }
}
