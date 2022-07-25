package com.haservi.book.domain.posts;

import com.haservi.book.domain.posts.dto.PostsResponseDto;
import com.haservi.book.domain.posts.dto.PostsSaveRequestDto;
import com.haservi.book.domain.posts.dto.PostsUpdateRequestDto;
import com.haservi.book.domain.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto dto) {
        return postsService.save(dto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto dto) {
        return postsService.update(id, dto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

}
