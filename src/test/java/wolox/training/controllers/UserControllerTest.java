package wolox.training.controllers;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import wolox.training.models.User;
import wolox.training.repositories.IUserRepository;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserController userController;

    @MockBean
    private IUserRepository userRepository;

    @Test
    public void givenUsers_whenGetUsers_thenReturnAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        doReturn(users).when(userRepository).findAll();
        mvc.perform(MockMvcRequestBuilders.get("/users").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAUserId_whenGetUser_thenReturnSelectedUser() throws Exception {
        User user = new User();
        when(userRepository.findById(29L)).thenReturn(Optional.of(user));
        doReturn(Optional.of(user)).when(userRepository).findById(29L);
        mvc.perform(MockMvcRequestBuilders.get("/users/29").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAnInvalidUserId_whenGetUser_thenReturnInvalidStatusCode() throws Exception {
        when(userRepository.findById(89L)).thenReturn(Optional.empty());
        doReturn(Optional.empty()).when(userRepository).findById(89L);
        mvc.perform(MockMvcRequestBuilders.get("/users/89").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenAUserId_whenDeleteUser_thenReturnOkStatusCode() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/users/29").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAUser_whenUpdateUser_thenReturnUpdatedUserOkStatusCode() throws Exception {
        String request = "{\n"
                + "    \"userId\": 29,\n"
                + "    \"userUsername\": \"Polii\",\n"
                + "    \"userName\": \"PoliJuan\",\n"
                + "    \"userBirthday\": \"1992-08-07\",\n"
                + "    \"books\": []\n"
                + "}";
        mvc.perform(MockMvcRequestBuilders.put("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAUser_whenPostAUser_thenReturnCreatedStatusCode() throws Exception {
        String request = "{\n"
                + "    \"userUsername\": \"Poliiii\",\n"
                + "    \"userName\": \"PoliiiJuan\",\n"
                + "    \"userBirthday\": \"1992-08-07\",\n"
                + "    \"books\": []\n"
                + "}";
        mvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request))
                .andExpect(status().is2xxSuccessful());
    }

}
