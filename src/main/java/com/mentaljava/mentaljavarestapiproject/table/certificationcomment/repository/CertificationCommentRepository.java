package com.mentaljava.mentaljavarestapiproject.table.certificationcomment.repository;

import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.entity.CertificationComment;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationCommentRepository extends JpaRepository<CertificationComment, Integer> {
    Optional<CertificationComment> findByPostIdAndDeleteStatus(CertificationPost postId, int deleteStatus);
}
