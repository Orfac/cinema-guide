package ru.kinoguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.User;
import ru.kinoguide.entity.UserRole;
import ru.kinoguide.exception.NameOccupiedException;
import ru.kinoguide.repository.UserRepository;
import ru.kinoguide.repository.UserRoleRepository;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private UserRoleRepository userRoleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    private User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    /**
     * @param user with password field not encoded
     * @return Returns saved {@code User} instance which should be used by a caller after update;
     */
    public User registerUser(User user) throws NameOccupiedException {
        if (!user.isNew()) {
            throw new IllegalArgumentException("User must be new and should contain not encoded password");
        }
        if (getUserByName(user.getName()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserRoles(userRoleRepository.findByName("ROLE_USER"));
            return userRepository.save(user);
        } else {
            throw new NameOccupiedException(user.getName());
        }
    }

    /**
     * @param user
     * @return Returns saved {@code User} instance which should be used by a caller after update;
     */
    public User updateUser(User user) throws NameOccupiedException {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User must have an id to make an update");
        }
        User persistedUser = userRepository.findOne(user.getId());
        if (!persistedUser.getName().equals(user.getName())) {
            if (getUserByName(user.getName()) != null) {
                throw new NameOccupiedException(user.getName());
            }
        }
        return userRepository.save(user);
    }
}
