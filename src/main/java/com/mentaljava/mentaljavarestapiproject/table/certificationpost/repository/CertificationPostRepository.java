package com.mentaljava.mentaljavarestapiproject.table.certificationpost.repository;

import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationPostRepository extends JpaRepository<CertificationPost, Integer> {

    CertificationPost findByPostId(Integer postId);

}
