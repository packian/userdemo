package com.coding.challenge.userproj.controller;

import com.coding.challenge.userproj.model.UserAddrDetails;
import com.coding.challenge.userproj.model.UserDetails;
import com.coding.challenge.userproj.repositories.UserDetailsRepository;
import com.coding.challenge.userproj.service.UserApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserApplicationController.class)
public class UserApplicationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserApplicationService userApplicationService;

    @MockBean
    private UserDetailsRepository userDetailsRepository;


    @Test
    public void retrieveUserDetails() throws Exception {
        UserDetails userDetails = new UserDetails("Mrs", "Tommy", "John", "Female", new UserAddrDetails("King St", "Sydney", "NSW", "2848"));

        Mockito.when(
                userApplicationService.fetchUserDetails(anyInt())
        ).thenReturn(userDetails);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/getUserDetails/1");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        String expected = "{\"empid\":0,\"title\":\"Mrs\",\"firstn\":\"Tommy\",\"lastname\":\"John\",\"gender\":\"Female\",\"userAddrDetails\":{\"addrid\":0,\"street\":\"King St\",\"city\":\"Sydney\",\"state\":\"NSW\",\"postcode\":\"2848\"}}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void updateUserDetails() throws Exception {
        UserDetails userDetails = new UserDetails("Mrs", "Tommy Updated", "John", "Female", new UserAddrDetails("King St Updated", "Sydney", "NSW", "2948"));

        Mockito.when(
                userApplicationService.updateUserDetails(anyInt(), Mockito.any(UserDetails.class))
        ).thenReturn(userDetails);

        String updateInputJson = "{\"empid\":0,\"title\":\"Mrs\",\"firstn\":\"Tommy Updated\",\"lastname\":\"John\",\"gender\":\"Female\",\"userAddrDetails\":{\"addrid\":0,\"street\":\"King St Updated\",\"city\":\"Sydney\",\"state\":\"NSW\",\"postcode\":\"2948\"}}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/updateUserDetails/1")
                .accept(MediaType.APPLICATION_JSON).content(updateInputJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        JSONAssert.assertEquals(updateInputJson, result.getResponse()
                .getContentAsString(), false);


    }
}