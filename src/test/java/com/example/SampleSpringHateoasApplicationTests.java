package com.example;

import com.example.hello.GreetingController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SampleSpringHateoasApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	GreetingController greetingController;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(greetingController).build();
	}

	@Test
	public void defaultValue() throws Exception {
		this.mockMvc.perform(get("/greeting?name={name}", "World")).andExpect(status().isOk());
		//assertTrue(greetingController.greeting("").toString().contains("World"));
	}

}
