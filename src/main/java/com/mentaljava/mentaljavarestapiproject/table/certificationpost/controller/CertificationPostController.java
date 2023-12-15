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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/certificationpost")
public class CertificationPostController {

    private final CertificationPostService certificationPostService;

    //크루별 인증게시판조회 페이징10개씩
    @GetMapping("/{crewId}/post")
    public ResponseEntity<ResponseDTO> selectPostListByCrewIdWithPaging(
            @PathVariable Integer crewId,
            @RequestParam(value = "offset", defaultValue = "1") String offset) {

        log.info("[CertificationPostController] selectPostListByCrewIdWithPaging START==============");
        log.info("[CertificationPostController] selectPostListByCrewIdWithPaging offset : {} ", offset);

        int total = certificationPostService.selectPostListByCrewId(crewId);

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(certificationPostService.selectPostListByCrewIdWithPaging(crewId, cri));
        pagingResponseDTO.setPageInfo(new PagingDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "크루별 인증게시판조회 성공", pagingResponseDTO));
    }

    @GetMapping("/{crewId}/list")
    public ResponseEntity<ResponseDTO> certificationPostList(
            @PathVariable Integer crewId,
            @RequestParam(value = "offset",defaultValue = "1") String offset) {
        int total = certificationPostService.selectTotalCertificationPost(crewId);

        Criteria cri = new Criteria(Integer.valueOf(offset),10);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(certificationPostService.selectCertificationPost(crewId,cri));
        pagingResponseDTO.setPageInfo(new PagingDTO(cri, total));

        log.info("[CertificationPost] CertificationDTO ===========> " + pagingResponseDTO);
        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK, "크루의 인증게시판 글 리스트 조회", pagingResponseDTO));
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
