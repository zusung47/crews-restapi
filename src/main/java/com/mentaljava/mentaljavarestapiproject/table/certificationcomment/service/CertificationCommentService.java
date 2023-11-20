package com.mentaljava.mentaljavarestapiproject.table.certificationcomment.service;

import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.entity.CertificationComment;
import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.repository.CertificationCommentRepository;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CertificationCommentService {

    private final CertificationCommentRepository certificationCommentRepository;


    public Optional<CertificationComment> findPostComment(CertificationPost postId, int deleteStatus) {
        return certificationCommentRepository.findByPostIdAndDeleteStatus(postId,deleteStatus);
    }

    @Transactional
    public void newComment(String postComment, User user, CertificationPost postDetail) {
        CertificationComment certificationComment = new CertificationComment();
        certificationComment.setCommentContent(postComment);
//        certificationComment.setWriteDate(LocalDate.now());
        certificationComment.setUserId(user);
        certificationComment.setPostId(postDetail);
        certificationCommentRepository.save(certificationComment);
    }
}
