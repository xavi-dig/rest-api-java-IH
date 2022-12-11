package com.example.demo.controllers;

import com.example.demo.models.users.AccountHolders;
import com.example.demo.models.users.Address;
import com.example.demo.repositories.account.AccountRepository;
import com.example.demo.repositories.account.CheckingRepository;
import com.example.demo.repositories.user.AccountHoldersRepository;
import com.example.demo.repositories.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountHoldersControllerTest {
/*
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private AccountHoldersRepository accountHoldersRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private AccountRepository accountRepository;

    MockMvc mockMvc;

    ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void commandLineRunnerRuns() {
        assertEquals(4, userRepository.findAll().size());
    }

    @Test
    void addNewAccountHolder_OK() throws Exception {
        AccountHolders accountHolder = new AccountHolders("Juan",
                LocalDate.of(1994, 11, 28), new Address("Froilan de Todos los Santos",
                1111, "Barcelona", "España"), "elfroi@example.com","123");

        String body = objectMapper.writeValueAsString(accountHolder);

        MvcResult result = mockMvc.perform(post("/accounts").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }*/
}


