package com.nwidart.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nwidart.springbootstarterjwt.configuration.SecurityConfig;
import com.nwidart.springbootstarterjwt.configuration.properties.JwtStarterProperties;
import com.nwidart.springbootstarterjwt.entity.User;
import com.nwidart.springbootstarterjwt.repository.UserRepository;
import com.nwidart.springbootstarterjwt.security.SimpleLoginUser;
import java.nio.charset.Charset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
@Import(value = {SecurityConfig.class, JwtStarterProperties.class})
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    final private MediaType contentTypeText = new MediaType(MediaType.TEXT_PLAIN.getType(),
            MediaType.TEXT_PLAIN.getSubtype(), Charset.forName("utf8"));
    final private MediaType contentTypeJson = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void greeting() throws Exception {
        User user = new User(1L, "test_user", "pass", "aaa.aaa@example.com", true);
        SimpleLoginUser loginUser = new SimpleLoginUser(user);
        RequestBuilder builder = MockMvcRequestBuilders.get("/user")
                .with(user(loginUser))
                .accept(MediaType.TEXT_PLAIN);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(authenticated().withUsername("test_user").withRoles("USER", "ADMIN"))
                .andExpect(content().contentType(contentTypeText))
                .andExpect(content().string("hello test_user"))
                .andExpect(forwardedUrl(null))
                .andExpect(redirectedUrl(null))
                .andDo(print())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("hello test_user");
    }

    @WithMockUser(roles = "USER")
    @Test
    public void getEcho() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/user/echo/{message}", "abc")
                .accept(MediaType.TEXT_PLAIN);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentTypeText))
                .andExpect(content().string("ABC"))
                .andDo(print())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("ABC");
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void getEcho_403() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/user/echo/{message}", "abc");

        mvc.perform(builder)
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()))
                .andDo(print());
    }

    @Test
    public void getEcho_401() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/user/echo/{message}", "abc");

        mvc.perform(builder)
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()))
                .andDo(print());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void postEcho() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.post("/user/echo")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"message\": \"hello world\"}")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentTypeJson))
                .andExpect(content().string("{message=hello world}"))
                .andDo(print())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("{message=hello world}");
    }

}
