package com.soft.ressystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnSignInMessage() throws Exception {
		this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(org.hamcrest.Matchers.containsString("Enter valid credentials.")));
	}
	
	@Test
	public void shouldReturnSignUptMessage() throws Exception {
		this.mockMvc.perform(get("/signup")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(org.hamcrest.Matchers.containsString("Sign up")));
	}
	
	@Test
	public void shouldReturnListMessage() throws Exception {
		this.mockMvc.perform(get("/gamelist")).andDo(print()).andExpect(status().is(302));

	}
	
	@Test
	public void shouldReturnEditMessage() throws Exception {
		this.mockMvc.perform(get("/edit")).andDo(print()).andExpect(status().is(302));

	}
	
	@Test
	public void shouldReturnAddMessage() throws Exception {
		this.mockMvc.perform(get("/reservation")).andDo(print()).andExpect(status().is(302));

	}
	
	@Test
	public void shouldReturnErrorMessage() throws Exception {
		this.mockMvc.perform(get("/asd")).andDo(print()).andExpect(status().is(302));

	}
}
