package com.mentaljava.mentaljavarestapiproject.table.certificationcomment.repository;

import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.entity.CertificationComment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationCommentRepository extends JpaRepository<CertificationComment, Integer> {
    List<CertificationComment> findByPostIdAndDeleteStatus(Integer postId, int deleteStatus);
}
