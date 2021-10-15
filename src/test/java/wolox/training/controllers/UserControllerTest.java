package wolox.training.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
        mvc.perform(MockMvcRequestBuilders.get("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(""))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAUserId_whenGetUser_thenReturnSelectedUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/23").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(""))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAUserId_whenDeleteUser_thenReturnOkStatusCode() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/users/23").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(""))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAUser_whenUpdateUser_thenReturnUpdatedUserOkStatusCode() throws Exception {
        String request = "{\n"
                + "    \"userId\": 23,\n"
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
                + "    \"userUsername\": \"Poli\",\n"
                + "    \"userName\": \"PoliJuan\",\n"
                + "    \"userBirthday\": \"1992-08-07\",\n"
                + "    \"books\": []\n"
                + "}";
        mvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request))
                .andExpect(status().is2xxSuccessful());
    }

}
