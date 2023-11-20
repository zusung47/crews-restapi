package com.mentaljava.mentaljavarestapiproject.table.certificationpost.service;

import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.repository.CertificationPostRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificationPostService {

    private final CertificationPostRepository certificationPostRepository;

    public List<CertificationPost> findCertificationPost() {
        return certificationPostRepository.findAll();
    }


    public CertificationPost findPostDetail(Integer postId) {
        return certificationPostRepository.findByPostId(postId);
    }

//    public Optional<CertificationPost> findPostDetails(Integer postId) {
//        return certificationPostRepository.findByPostId(postId);
//    }
}
