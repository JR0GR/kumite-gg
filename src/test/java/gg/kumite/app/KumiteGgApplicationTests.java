package gg.kumite.app;

import gg.kumite.app.models.Tournament;
import gg.kumite.app.repositories.GameRepository;
import gg.kumite.app.repositories.TournamentRepository;
import gg.kumite.app.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class KumiteGgApplicationTests {
    @Autowired
    MockMvc mvc;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    TournamentRepository tournamentRepository;

    String tokenAdmin;
    @BeforeAll
    void getToken() throws Exception {
        tokenAdmin = authenticate("jr", "jr");
    }

    @Test
    void contextLoads() {

    }


    String authenticate(String user, String password) throws Exception {
        MvcResult result = mvc.perform(post("/token")
                                            .with(httpBasic(user, password)))
                                        .andExpect(status().isOk())
                                        .andReturn();

        return result.getResponse().getContentAsString();
    }



}
