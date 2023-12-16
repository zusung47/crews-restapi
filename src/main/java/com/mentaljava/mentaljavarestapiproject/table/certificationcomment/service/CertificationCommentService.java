package com.mentaljava.mentaljavarestapiproject.table.certificationcomment.service;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.dto.CertificationCommentDTO;
import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.entity.CertificationComment;
import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.repository.CertificationCommentRepository;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.dto.CertificationPostDTO;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.repository.CertificationPostRepository;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import com.mentaljava.mentaljavarestapiproject.table.user.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice.Local;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificationCommentService {

    private final CertificationCommentRepository certificationCommentRepository;
    private final CertificationPostRepository certificationPostRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<CertificationCommentDTO> findCommentList(Integer postId) {
        CertificationPost certificationPost = certificationPostRepository.findByPostId(postId);

        List<CertificationComment> certificationCommentList = certificationCommentRepository.findByPostId(
                certificationPost);
        List<CertificationCommentDTO> certificationCommentDTOList = certificationCommentList.stream()
                .map(certificationComment ->
                        modelMapper.map(certificationComment, CertificationCommentDTO.class))
                .collect(Collectors.toList());

        return certificationCommentDTOList;
    }

    @Transactional
    public String addComment(Integer postId, CertificationCommentDTO certificationCommentDTO) {
        int result = 0;
        try {
            CertificationPost certificationPost = certificationPostRepository.findByPostId(postId);
            CertificationPostDTO certificationPostDTO = modelMapper.map(certificationPost, CertificationPostDTO.class);
            User user = userRepository.findByUserId("seyoung2");
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);

            certificationCommentDTO.setPostId(certificationPostDTO);
            certificationCommentDTO.setUserId(userDTO);
            certificationCommentDTO.setWriteDate(LocalDate.now());
            certificationCommentDTO.setDeleteStatus(0);
            CertificationComment certificationComment = modelMapper.map(certificationCommentDTO,
                    CertificationComment.class);
            certificationCommentRepository.save(certificationComment);
            result = 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (result > 0) ? "댓글 등록 성공" : "댓글 등록 실패";
    }

    public int selectTotalComment(Integer postId) {

        CertificationPost certificationPost = certificationPostRepository.findByPostId(postId);

        List<CertificationComment> certificationCommentList = certificationCommentRepository.findByPostId(certificationPost);

        return certificationCommentList.size();

    }

    public List<CertificationCommentDTO> selectComment(Integer postId, Criteria cri) {
        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();

        Pageable paging = PageRequest.of(index, count, Sort.by("commentId"));

        CertificationPost certificationPost = certificationPostRepository.findByPostId(postId);

        Page<CertificationComment> result = certificationCommentRepository.findByPostId(certificationPost,paging);

        List<CertificationCommentDTO> certificationCommentDTOList = result.stream()
                .map(certificationComment -> modelMapper.map(certificationComment, CertificationCommentDTO.class))
                .collect(Collectors.toList());

        return certificationCommentDTOList;

    }
}
