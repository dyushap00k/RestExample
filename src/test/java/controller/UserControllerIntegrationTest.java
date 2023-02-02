package controller;

import com.model.entity.User;
import com.repo.UserRepository;
import com.service.DtoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
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
    @Autowired
    private UserRepository userRepository;

    @Test
    public void gettingUser_whenGetUserById_ThenStatus200() throws Exception {

        User savedUser = userRepository.save(
                new User("Билли", "Бонс", LocalDate.of(1994, 2, 19))
        );

        mockMvc.perform(get("/v1/users/{id}", savedUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(savedUser.getName())))
                .andExpect(jsonPath("$.surname", equalTo(savedUser.getSurname())))
                .andExpect(jsonPath("$.age", equalTo(DtoMapper.toDto(savedUser).getAge())));
    }

    @Test
    public void gettingNonExistentUser_whenGetUserByNonExistId_ThenStatus400() throws Exception {
        mockMvc.perform(get("/v1/users/2"))
                .andExpect(status().isNotFound());
    }
}
