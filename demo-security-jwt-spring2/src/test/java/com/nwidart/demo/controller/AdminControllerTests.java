package com.nwidart.demo.controller;

import com.nwidart.springbootstarterjwt.configuration.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwidart.springbootstarterjwt.entity.User;
import com.nwidart.springbootstarterjwt.repository.UserRepository;
import com.nwidart.springbootstarterjwt.security.SimpleLoginUser;
import com.nwidart.springbootstarterjwt.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AdminController.class)
@Import(value = {SecurityConfig.class})
public class AdminControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    final private MediaType contentTypeText = new MediaType(MediaType.TEXT_PLAIN.getType(),
            MediaType.TEXT_PLAIN.getSubtype(), Charset.forName("utf8"));

    @Test
    public void greeting() throws Exception {
        User user = new User(1L, "aaaa", "pass", "aaa.aaa@example.com", true);
        SimpleLoginUser loginUser = new SimpleLoginUser(user);
        RequestBuilder builder = MockMvcRequestBuilders.get("/admin")
                .with(user(loginUser))
                .accept(MediaType.TEXT_PLAIN);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentTypeText))
                .andExpect(content().string("hello admin aaaa"))
                .andDo(print())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("hello admin aaaa");
    }

    @Test
    public void greetingWithName() throws Exception {
        Mockito.when(userService.findByName(anyString())).thenReturn(Optional.of(new User(2L, "bbbb", "pass", "bbb.bbb@example.com", false)));

        User user = new User(1L, "aaaa", "pass", "aaa.aaa@example.com", true);
        SimpleLoginUser loginUser = new SimpleLoginUser(user);
        RequestBuilder builder = MockMvcRequestBuilders.get("/admin/{name}", "bbbb")
                .with(user(loginUser))
                .accept(MediaType.TEXT_PLAIN);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentTypeText))
                .andExpect(content().string("hello bbbb"))
                .andDo(print())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("hello bbbb");
    }

}
