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
import com.mentaljava.mentaljavarestapiproject.util.FileUploadUtils;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificationCommentService {

    private final CertificationCommentRepository certificationCommentRepository;
    private final CertificationPostRepository certificationPostRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    @Value("${image.image-url}")
    private String IMAGE_URL;

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

//    @Transactional
//    public String insertComment(CertificationCommentDTO certificationCommentDTO, MultipartFile commentImage) {
//        log.info("[CertificationCommentService] insertComment start ==================");
//        log.info("[CertificationCommentService] dto =============== : " + certificationCommentDTO);
//
//        String imageName = UUID.randomUUID().toString().replace("-", "");
//        String replaceFileName = null;
//
//        int result = 0;
//
//        try {
//            // Find CertificationPost by postId
//            CertificationPost certificationPost = certificationPostRepository.findById(certificationCommentDTO.getPostId().getPostId())
//                    .orElseThrow(() -> new RuntimeException("Post not found: " + certificationCommentDTO.getPostId().getPostId()));
//
//            CertificationPostDTO certificationPostDTO = modelMapper.map(certificationPost, CertificationPostDTO.class);
//
//            // Find User by userId
//            User user = userRepository.findById(certificationCommentDTO.getUserId().getUserId())
//                    .orElseThrow(() -> new RuntimeException("User not found: " + certificationCommentDTO.getUserId().getUserId()));
//
//            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
//
//            replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, commentImage);
//
//            certificationCommentDTO.setCommentImageUrl(replaceFileName);
//            log.info("[CertificationCommentService] insert Image Name : ", replaceFileName);
//
//
//            certificationCommentDTO.setDeleteStatus(0);
//            certificationCommentDTO.setWriteDate(LocalDate.now());
//            certificationCommentDTO.setPostId(certificationPostDTO);
//            certificationCommentDTO.setUserId(userDTO);
//
//            CertificationComment certificationComment = modelMapper.map(certificationCommentDTO, CertificationComment.class);
//
//            certificationCommentRepository.save(certificationComment);
//
//            result = 1;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        return (result > 0) ? "댓글 등록 성공" : "댓글 등록 실패";
//    }

    @Transactional
    public String insertComment(CertificationCommentDTO certificationCommentDTO,
                                MultipartFile commentImage) {
        log.info("[CertificationCommentService] insertComment start ==================");
        log.info("[CertificationCommentService] dto =============== : " + certificationCommentDTO);

        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;

        int result = 0;

        try {
            // Find CertificationPost by postId
            CertificationPost certificationPost = certificationPostRepository.findById(certificationCommentDTO.getPostId().getPostId())
                    .orElseThrow(() -> new RuntimeException("Post not found: " + certificationCommentDTO.getPostId().getPostId()));

            // Find User by userId
            User user = userRepository.findById(certificationCommentDTO.getUserId().getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found: " + certificationCommentDTO.getUserId().getUserId()));

            replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, commentImage);

            certificationCommentDTO.setCommentImageUrl(replaceFileName);
            log.info("[CertificationCommentService] insert Image Name : ", replaceFileName);

            certificationCommentDTO.setDeleteStatus(0);
            certificationCommentDTO.setWriteDate(LocalDate.now());

            CertificationComment certificationComment = modelMapper.map(certificationCommentDTO, CertificationComment.class);

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


        for(int i = 0; i < certificationCommentDTOList.size(); i++){
            certificationCommentDTOList.get(i).setCommentImageUrl(IMAGE_URL + certificationCommentDTOList.get(i).getCommentImageUrl());
        }

        return certificationCommentDTOList;

    }
}
