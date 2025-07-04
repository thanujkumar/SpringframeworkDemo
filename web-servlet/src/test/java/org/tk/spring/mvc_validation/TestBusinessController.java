package org.tk.spring.mvc_validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = { MvcValidationJavaConfig.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBusinessController {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testIllegalExceptionHandling() throws Exception {
        MvcResult result = mockMvc.perform(get("/mvc/illegalState").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        //status is 200 and will get exception message
    }

    @Test
    public void testIRuntimeExceptionHandling() throws Exception {
        MvcResult result = mockMvc.perform(get("/mvc/runtime").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        //status is 200 and will be handled by common-exception (ModelAndView)
    }
}
