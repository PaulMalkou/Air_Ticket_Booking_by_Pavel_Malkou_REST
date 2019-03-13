package com.vironit.airticketsbooking.springapp.controller;

import com.vironit.airticketsbooking.springapp.config.AirTicketBookingMvcConfig;
import com.vironit.airticketsbooking.springapp.config.AirTicketBookingSecurityConfig;
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
public class OrderControllerTest {
    private MockMvc mockMvc;

    @Autowired
    OrderController orderController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void getAllOrdersTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/order/");
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        mockMvc.perform(request)
                .andExpect(ok);
    }

    @Test
    public void updateOrderTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/order/109");
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        mockMvc.perform(request)
                .andExpect(ok);
    }

    @Test
    public void updateOrderMakeFinishedTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/order/109/make-order-finished");
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        mockMvc.perform(request)
                .andExpect(ok);
    }

    @Test
    public void deleteOrderTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/order/109");
        ResultMatcher noContent = MockMvcResultMatchers.status().isNoContent();
        mockMvc.perform(request)
                .andExpect(noContent);
    }

    @Test
    public void getOrdersOfUserTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/user/78/orders/");
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        mockMvc.perform(request)
                .andExpect(ok);
    }

    @Test
    public void getActiveOrdersOfUserTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/user/78/orders/order_status=active");
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        mockMvc.perform(request)
                .andExpect(ok);
    }

    @Test
    public void getFinishedOrdersOfUserTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/user/78/orders/order_status=finished");
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        mockMvc.perform(request)
                .andExpect(ok);
    }

}
