package com.haservi.book;

import com.haservi.book.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// 매칭 확인?
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class) // JUnit Vintage?
//@WebMvcTest(controllers = HelloController.class)
@SpringBootTest
@AutoConfigureMockMvc // MockMvc 자동 설정
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void hello_return() throws Exception {
        String hello = "hello";

        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(hello)); // hello 인지 검증
    }

    @Test
    public void hello_dto_return() throws Exception {

        String name = "hello";
        int amount = 1000;

        mockMvc.perform(MockMvcRequestBuilders.get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(name))) // json 응답값 확인용
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount", is(amount)));

    }


}