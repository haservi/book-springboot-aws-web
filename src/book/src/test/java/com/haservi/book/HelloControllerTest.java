package com.haservi.book;

import com.haservi.book.web.HelloController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// 매칭 확인?
import static org.hamcrest.Matchers.is;


//@RunWith(SpringRunner.class) // JUnit Vintage?
@WebMvcTest(controllers = HelloController.class)
//@SpringBootTest
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello_return() throws Exception {
        String hello = "hello";

        mvc.perform(MockMvcRequestBuilders.get("/hello"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(hello)); // hello 인지 검증
    }

    @Test
    public void hello_dto_return() throws Exception {

        String name = "hello";
        int amount = 1000;

        mvc.perform(MockMvcRequestBuilders.get("/hello/dto")
            .param("name", name)
            .param("amount", String.valueOf(amount))
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(name))) // json 응답값 확인용
            .andExpect(MockMvcResultMatchers.jsonPath("$.amount", is(amount)));

    }


}