package com.mentaljava.mentaljavarestapiproject.table.certificationpost.service;

import com.mentaljava.mentaljavarestapiproject.table.certificationpost.dto.CertificationPostDTO;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.repository.CertificationPostRepository;
import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.crew.repository.CrewRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CertificationPostService {

    private final CertificationPostRepository certificationPostRepository;
    private final CrewRepository crewRepository;
    private final ModelMapper modelMapper;

    public CertificationPost findPostDetail(Integer postId) {
        return certificationPostRepository.findByPostId(postId);
    }

    public List<CertificationPostDTO> findOnePost(Integer crewId) {
        Crew crew = crewRepository.findByCrewId(crewId);
        log.info("[CertificationPost] crew ===========> " + crew);
        List<CertificationPost> certificationPostList = certificationPostRepository.findByCrewId(crew);
        List<CertificationPostDTO> certificationPostDTOList = certificationPostList.stream().map(certificationPost ->
                modelMapper.map(certificationPost, CertificationPostDTO.class)).collect(Collectors.toList());
        return certificationPostDTOList;
    }

}
