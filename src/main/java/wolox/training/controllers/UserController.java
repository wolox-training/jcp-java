package wolox.training.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import wolox.training.exceptions.UserNotFoundException;
import wolox.training.models.Users;
import wolox.training.repositories.IUserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    /**
     * @return all the users contained in the model
     */
    @GetMapping("/all")
    public Optional<List<Users>> findAll() {
        List<Users> allUsers = new ArrayList<>();
        if (userRepository.findAll().iterator().hasNext()) {
            userRepository.findAll().forEach(b -> allUsers.add(b));
            return Optional.of(allUsers);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param id indicates the id to be searched
     *
     * @return the entire user of the selected id
     */
    @GetMapping("/{id}")
    public Optional<Users> findById(@PathVariable Long id) {
        Users selectedUser;
        if (userRepository.findById(id).isPresent()) {
            selectedUser = userRepository.findById(id).get();
            return Optional.of(selectedUser);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param user indicates the user to be added
     *
     * @return the created user
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Users create(@RequestBody Users user) {
        return userRepository.save(user);
    }

    /**
     * @param id indicates the id of the user to be searched
     *
     * @throws UserNotFoundException returns an exception if the user is not found
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable Long id) {
        userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(id);
    }

    /**
     * @param id   indicates the id of the user to be updated
     * @param user indicates the information of the user to be updated
     *
     * @return the updated user
     * @throws UserNotFoundException returns an exception if the user is not found
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Users updateUser(@RequestBody Users user, @PathVariable Long id) {
        userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return userRepository.save(user);
    }

}
