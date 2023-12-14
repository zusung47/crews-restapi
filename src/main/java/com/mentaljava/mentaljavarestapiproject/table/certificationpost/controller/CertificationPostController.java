package com.mentaljava.mentaljavarestapiproject.table.certificationpost.controller;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.common.PagingDTO;
import com.mentaljava.mentaljavarestapiproject.common.PagingResponseDTO;
import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.service.CertificationCommentService;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.dto.CertificationPostDTO;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.service.CertificationPostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/certificationPost")
public class CertificationPostController {

    private final CertificationPostService certificationPostService;
    private final CertificationCommentService certificationCommentService;
//
//    @GetMapping("/list")
//    public ResponseEntity<ResponseDTO> selectCertificationPostListWithPagingTen(
//            @RequestParam(value = "offset", defaultValue = "1") String offset) {
//
//        int total = CertificationPostService.selectTotalPost();
//
//        Criteria cri = new Criteria(Integer.valueOf(offset), 10);
//
//        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
//
//        pagingResponseDTO.setData(CertificationPostService.selectCertificationListWithPaging(cri));
//        pagingResponseDTO.setPageInfo(new PagingDTO(cri, total));
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "인증게시글 리스트 조회 성공", pagingResponseDTO));
//    }

    @GetMapping("/{crewId}/list")
    public ResponseEntity<ResponseDTO> certificationPostList(@PathVariable Integer crewId) {
        List<CertificationPostDTO> certificationPostList = certificationPostService.findOnePost(crewId);

        log.info("[CertificationPost] CertificationDTO ===========> " + certificationPostList);
        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK, "크루의 인증게시판 글 리스트 조회", certificationPostList));
    }

    @PutMapping("/{crewIds}/regist")
    public ResponseEntity<ResponseDTO> registPost(@PathVariable Integer crewIds,
                                                  @RequestBody CertificationPostDTO certificationPostDTO) {

        log.info("[CertificationPost] CertificationDTO ===========> " + certificationPostDTO);

        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK, "크루의 인증게시판 글 만들",
                        certificationPostService.registComment(crewIds, certificationPostDTO)));

    }

}
