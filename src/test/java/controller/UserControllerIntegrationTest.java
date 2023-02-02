package controller;

import com.model.entity.User;
import com.repo.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.Application.class)
@ContextConfiguration(classes = com.Application.class)
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void gettingUser_whenGetUserById_ThenStatus200() throws Exception {

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(
                new User("Билли", "Бонс", LocalDate.of(1994, 2, 19))
        ));

        mockMvc.perform(get("/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Билли")))
                .andExpect(jsonPath("$.surname", equalTo("Бонс")))
                .andExpect(jsonPath("$.age", equalTo(28)));
    }

    @Test
    public void gettingNonExistentUser_whenGetUserByNonExistId_ThenStatus400() throws Exception {
        mockMvc.perform(get("/v1/users/1"))
                .andExpect(status().isNotFound());
    }
}
