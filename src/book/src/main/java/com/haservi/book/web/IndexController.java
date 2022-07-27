package com.haservi.book.web;

import com.haservi.book.config.auth.LoginUser;
import com.haservi.book.config.auth.dto.SessionUser;
import com.haservi.book.domain.posts.dto.PostsResponseDto;
import com.haservi.book.domain.posts.dto.PostsSaveRequestDto;
import com.haservi.book.domain.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
//        sampleData();
        model.addAttribute("posts", postsService.findAllDesc());

//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

    private void sampleData() {
        postsService.save(PostsSaveRequestDto.builder()
                .title("샘플 제목")
                .content("샘플 내용")
                .author("샘플 작성자")
                .build()
        );
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
