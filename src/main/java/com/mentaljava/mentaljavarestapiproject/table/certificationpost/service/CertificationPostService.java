package com.mentaljava.mentaljavarestapiproject.table.certificationpost.service;

import com.mentaljava.mentaljavarestapiproject.table.certificationpost.dto.CertificationPostDTO;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.repository.CertificationPostRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CertificationPostService {

    private final CertificationPostRepository certificationPostRepository;

    public CertificationPost findPostDetail(Integer postId) {
        return certificationPostRepository.findByPostId(postId);
    }

    public String findCertificationPost(CertificationPostDTO certificationPostDTO) {

        int result = 0;
        try{


        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return (result > 0) ? "인증게시판 조회 성공" : "인증게시판 조회 실패";
    }
}
