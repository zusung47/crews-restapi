package com.mentaljava.mentaljavarestapiproject.table.certificationcomment.repository;

import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.entity.CertificationComment;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.dto.CertificationPostDTO;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CertificationCommentRepository extends JpaRepository<CertificationComment, Integer> {

    List<CertificationComment> findByPostId(CertificationPost certificationPost);

}
