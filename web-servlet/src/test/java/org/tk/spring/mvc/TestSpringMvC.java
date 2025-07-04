package org.tk.spring.mvc;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {MvcJavaConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestSpringMvC {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/")
                .header("User-Agent", "MyBrowser");
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).defaultRequest(requestBuilder).build();
    }

    @Test
    @DisplayName("Ping Test")
    void ping() throws Exception {
        MvcResult result = mockMvc.perform(get("/mvc/ping").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        String output = new String(response.getContentAsByteArray());
        Assertions.assertEquals(output, "pong");
    }

    @Test
    @DisplayName("Get Headers Sent as Text and Async details")
    void getHeaderSentAsText() throws Exception {
        MvcResult result = mockMvc.perform(get("/mvc/args").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(200, response.getStatus());

    }
}
