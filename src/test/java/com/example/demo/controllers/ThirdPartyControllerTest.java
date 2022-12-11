package com.example.demo.controllers;

import com.example.demo.models.users.Admin;
import com.example.demo.models.users.ThirdParty;
import com.example.demo.repositories.account.RoleRepository;
import com.example.demo.repositories.user.ThirdPartyRepository;
import com.example.demo.repositories.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ThirdPartyControllerTest {
/*
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        roleRepository.deleteAll();
        userRepository.deleteAll();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper.findAndRegisterModules();
    }
    @AfterEach
    void tearDown() {

    }

    @Test
    //La etiqueta de abajo tan solo sirve para pasarle el username y password para el userDetails pero la securizacion de la ruta no se aplica
    //en los tests para que lo recordeis
    @WithMockUser(username = "user1", password = "pwd")
    void createUser() throws Exception {
        String body = objectMapper.writeValueAsString(new Admin("Admin","123"));
        System.out.println(body);
        MvcResult mvcResult = mockMvc.perform(post("/user/").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

    }

    @Test
    @WithMockUser(username = "admin", password = "1234")
    void createThirdPartyUser_works() throws Exception {
        ThirdParty thirdParty = new ThirdParty("Xavi", "1234");
        String body = objectMapper.writeValueAsString(thirdParty);
        System.out.println(body);

        MvcResult mvcResult = mockMvc.perform(post("/create-third-party-user").
                content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Xavi"));
    }

*/}