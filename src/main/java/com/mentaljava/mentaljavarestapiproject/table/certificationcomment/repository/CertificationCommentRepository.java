package com.mentaljava.mentaljavarestapiproject.table.certificationcomment.repository;

import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.entity.CertificationComment;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationCommentRepository extends JpaRepository<CertificationComment, Integer> {
    List<CertificationComment> findByPostId(CertificationPost certificationPost);


    Page<CertificationComment> findByPostId(CertificationPost certificationPost, Pageable paging);
}
