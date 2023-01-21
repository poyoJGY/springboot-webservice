package com.poyo.springboot.web;

import com.poyo.springboot.web.dto.HelloResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired  // 스프링이 관리하는 빈(Bean)을 주입 받는다.
    private MockMvc mvc;    // 스프링 MVC 테스트의 시작점

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))  // MockMvc를 통해 /hello 주소로 HTTP GET 요청
                .andExpect(status().isOk()) // mvc.perform의 결과 검증. HTTP Header의 Status를 검증
                .andExpect(content().string(hello));    // mvc.perform의 결과 검증. 응답 본문의 내용 검증. Controller 에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
    }

    @Test
    public void 롬복_기능_테스트() throws Exception {

        String name = "test";
        int amount = 1000;

        // param
        // API 테스트할 때 사용될 요청 파라미터를 설정
        // 값은 String만 허용
        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}