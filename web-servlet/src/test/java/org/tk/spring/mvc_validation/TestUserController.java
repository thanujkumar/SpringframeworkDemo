package org.tk.spring.mvc_validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = { MvcValidationJavaConfig.class })
@TestInstance(Lifecycle.PER_CLASS)
public class TestUserController {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	void validateUserShouldCauseDefaultValidationErrorsAndForwardToFailed() throws Exception {
		MvcResult result = mockMvc.perform(get("/default").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertTrue(response.getForwardedUrl().equals("failure"));
	}
	
	@Test
	void validateUserShouldCauseAllValidationErrorsAndForwardToFailed() throws Exception {
		MvcResult result = mockMvc.perform(get("/all").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertTrue(response.getForwardedUrl().equals("failure"));
	}

	@Test
	void postMethodNoIssue() throws Exception {
		MvcResult result = mockMvc.perform(post("/post").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andReturn();
		MockHttpServletResponse response = result.getResponse();
		Assertions.assertEquals(200, response.getStatus());
		Assertions.assertEquals("post success", response.getContentAsString());
	}
}
