package com.haservi.book.domain.posts.service;

import com.haservi.book.domain.posts.dto.PostsResponseDto;
import com.haservi.book.domain.posts.dto.PostsSaveRequestDto;
import com.haservi.book.domain.posts.dto.PostsUpdateRequestDto;
import com.haservi.book.domain.posts.repository.Posts;
import com.haservi.book.domain.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto dto) {
        postsRepository.save(dto.toEntity());
        return 0L;
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto dto) {
        Posts posts = postsRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id + " + id));
        posts.update(dto.getTitle(), dto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(("해당 게시글이 없습니다. id = " + id)));
        return new PostsResponseDto(posts);
    }
}
