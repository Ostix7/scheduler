package ua.edu.ukma.mandarin.scheduler.service;


import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.edu.ukma.mandarin.scheduler.domain.entity.User;
import ua.edu.ukma.mandarin.scheduler.repository.UserRepository;

//import javax.servlet.http.HttpServletRequest;
/* TODO: Sasha next time start application and not just commit your code, which breaks everything.
    So that I do need to spend time figuring out what is wrong and removing
     all these imports. You do not even use them, but they broke JWT filter. Thanks
 */
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private static final Logger logger = LogManager.getLogger();
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    UserRepository userRepository;

      public UserService() {

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int userId) {
        return userRepository.findOneById(userId);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


}
