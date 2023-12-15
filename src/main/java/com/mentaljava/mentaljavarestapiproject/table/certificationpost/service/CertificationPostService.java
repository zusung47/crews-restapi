package com.mentaljava.mentaljavarestapiproject.table.certificationpost.service;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.dto.CertificationPostDTO;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.repository.CertificationPostRepository;
import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.crew.repository.CrewRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CertificationPostService {

    private final CertificationPostRepository certificationPostRepository;
    private final CrewRepository crewRepository;

    private final ModelMapper modelMapper;

    public List<CertificationPostDTO> findOnePost(Integer crewId) {
        Crew crew = crewRepository.findByCrewId(crewId);

        Integer crewId2 = crew.getCrewId();
        log.info("[CertificationPost] crew ===========> " + crew);
        List<CertificationPost> certificationPostList = certificationPostRepository.findByCrewId_CrewId(crewId2);
        List<CertificationPostDTO> certificationPostDTOList = certificationPostList.stream().map(certificationPost ->
                modelMapper.map(certificationPost, CertificationPostDTO.class)).collect(Collectors.toList());
        return certificationPostDTOList;
    }


    public String registComment(Integer crewId, CertificationPostDTO certificationPostDTO) {
        int result = 0;
        try {
            Crew crew = crewRepository.findByCrewId(crewId);
            CrewDTO crewDTO = modelMapper.map(crew, CrewDTO.class);
            certificationPostDTO.setCrewId(crewDTO);
            certificationPostDTO.setPostDate(LocalDate.now());

            log.info("[certificationDTO] dto=============" + certificationPostDTO);

            CertificationPost certificationPost = modelMapper.map(certificationPostDTO, CertificationPost.class);
            certificationPostRepository.save(certificationPost);
            result = 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (result > 0) ? "댓글 등록 성공" : "댓글 등록 실패";

    }


    public int selectTotalCertificationPost(Integer crewId) {

        Crew crew = crewRepository.findByCrewId(crewId);

        List<CertificationPost> certificationPostList = certificationPostRepository.findByCrewId(crew);

        return certificationPostList.size();

    }

    public List<CertificationPostDTO> selectCertificationPost(Integer crewId, Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();

        Pageable paging = PageRequest.of(index, count, Sort.by("postId"));

        Crew crew = crewRepository.findByCrewId(crewId);

        Page<CertificationPost> result = certificationPostRepository.findByCrewId(crew, paging);

        List<CertificationPostDTO> certificationPostDTOList = result.stream()
                .map(certificationPost -> modelMapper.map(certificationPost, CertificationPostDTO.class))
                .collect(Collectors.toList());

        return certificationPostDTOList;
    }


}
