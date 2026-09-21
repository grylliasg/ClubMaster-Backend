package com.clubmaster.clubmaster;

import com.clubmaster.clubmaster.entity.Team;
import com.clubmaster.clubmaster.entity.User;
import com.clubmaster.clubmaster.repository.PlayerRepository;
import com.clubmaster.clubmaster.repository.TeamRepository;
import com.clubmaster.clubmaster.repository.UserRepository;
import com.clubmaster.clubmaster.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClubMasterApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        playerRepository.deleteAll();
        teamRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void loginShouldReturnJwtTokenForValidUser() throws Exception {
        String password = "secret123";
        userRepository.save(new User("alice", passwordEncoder.encode(password), "USER"));

        MvcResult result = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"alice\",\"password\":\"secret123\"}"))
                .andExpect(status().isOk())
                .andReturn();

        String token = result.getResponse().getContentAsString();
        assertThat(token).isNotBlank();
        assertThat(jwtService.extractClaims(token).getSubject()).isEqualTo("alice");
    }

    @Test
    void usersEndpointShouldAcceptBearerToken() throws Exception {
        String password = "secret456";
        userRepository.save(new User("bob", passwordEncoder.encode(password), "USER"));

        String token = jwtService.generateToken("bob");

        mockMvc.perform(get("/users/bob")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("bob"));
    }

    @Test
    void teamEndpointShouldCreateTeam() throws Exception {
        mockMvc.perform(post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Arsenal\",\"country\":\"England\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Arsenal"))
                .andExpect(jsonPath("$.country").value("England"));
    }

    @Test
    void playerEndpointShouldCreatePlayerForExistingTeam() throws Exception {
        Team team = teamRepository.save(new Team("Real Madrid", "Spain"));

        mockMvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Kylian\",\"lastName\":\"Mbappe\",\"position\":\"Forward\",\"description\":\"Striker\",\"dateOfBirth\":\"1998-12-20\",\"team\":{\"id\":" + team.getId() + ",\"name\":\"Real Madrid\",\"country\":\"Spain\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Kylian"))
                .andExpect(jsonPath("$.lastName").value("Mbappe"))
                .andExpect(jsonPath("$.teamId").value(team.getId()));
    }
}
