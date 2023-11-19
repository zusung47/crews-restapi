package com.mentaljava.mentaljavarestapiproject.table.certificationcomment.service;

import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.entity.CertificationComment;
import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.repository.CertificationCommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificationCommentService {

    private final CertificationCommentRepository certificationCommentRepository;


    public List<CertificationComment> findPostComment(Integer postId, int deleteStatus) {
        return certificationCommentRepository.findByPostIdAndDeleteStatus(postId,deleteStatus);
    }
}
