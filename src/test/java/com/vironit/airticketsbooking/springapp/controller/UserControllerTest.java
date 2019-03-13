package com.vironit.airticketsbooking.springapp.controller;

import com.vironit.airticketsbooking.springapp.config.AirTicketBookingMvcConfig;
import com.vironit.airticketsbooking.springapp.config.AirTicketBookingSecurityConfig;
import com.vironit.airticketsbooking.springapp.manager.PageManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AirTicketBookingMvcConfig.class, AirTicketBookingSecurityConfig.class})
@WebAppConfiguration
public class UserControllerTest {
    private MockMvc mockMvc;

    @Autowired
    UserController userController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllUsersTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/user/");
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        mockMvc.perform(request)
                .andExpect(ok);
    }

    @Test
    public void getUserTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/user/92");
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        mockMvc.perform(request)
                .andExpect(ok);
    }

    @Test
    public void addMoneyTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/user/78/recharge_balance_by=300");
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        mockMvc.perform(request)
                .andExpect(ok);
    }

    @Test
    public void deleteUserTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/user/92");
        ResultMatcher noContent = MockMvcResultMatchers.status().isNoContent();
        mockMvc.perform(request)
                .andExpect(noContent);
    }
}
