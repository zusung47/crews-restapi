package com.mentaljava.mentaljavarestapiproject.table.crewlist.service;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.crew.repository.CrewRepository;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.dto.CrewListDTO;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.entity.CrewList;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.repository.CrewListRepository;
import com.mentaljava.mentaljavarestapiproject.table.crewlistid.entity.CrewListId;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import com.mentaljava.mentaljavarestapiproject.table.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CrewListService {

    private final CrewListRepository crewListRepository;
    private final UserRepository userRepository;

    private final CrewRepository crewRepository;

    private final ModelMapper modelMapper;



    public List<CrewListDTO> selectCrewListByCrewId(Integer crewId) {

        List<CrewList> crewListByCrewId = crewListRepository.findAllById_CrewId(crewId);

        List<CrewListDTO> crewlistDTO = crewListByCrewId.stream()
                .map(crewList -> modelMapper.map(crewList, CrewListDTO.class)).collect(Collectors.toList());

        return crewlistDTO;
    }

    public List<CrewListDTO> selectCrewListByCrewIdAndWaitStatus(Integer crewId) {

        List<CrewList> crewListByCrewIdAndWaitStatus = crewListRepository.findById_CrewIdAndApprovalStatus(crewId, 0);

        List<CrewListDTO> crewlistDTO = crewListByCrewIdAndWaitStatus.stream()
                .map(crewList -> modelMapper.map(crewList, CrewListDTO.class)).collect(Collectors.toList());

        return crewlistDTO;
    }

    @Transactional
    public String updateStatusAgree(CrewListDTO crewListDTO) {

        int result = 0;

        try {
            CrewList crewListInfo = modelMapper.map(crewListDTO, CrewList.class);
            CrewList crewList = crewListRepository
                    .findById_CrewIdAndId_UserId(crewListInfo.getCrew(), crewListInfo.getUser());

            crewList.setApprovalStatus(1);

            result = 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (result > 0) ? "크루 받아주기 성공" : "크루 받아주기 실패";
    }

    @Transactional
    public String updateStatusRejection(CrewListDTO crewListDTO) {

        int result = 0;

        try {
            CrewList crewListInfo = modelMapper.map(crewListDTO, CrewList.class);
            CrewList crewList = crewListRepository
                    .findById_CrewIdAndId_UserId(crewListInfo.getCrew(), crewListInfo.getUser());
            crewList.setApprovalStatus(2);

            result = 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (result > 0) ? "크루 거절하기 성공" : "크루 거절하기 실패";
    }

    public List<CrewListDTO> getCrewListByUserId(String userId) {
        List<CrewList> crewListByUserId = crewListRepository.findCrewByUser_UserId(userId);

        List<CrewListDTO> crewlistDTO = crewListByUserId.stream()
                .map(crewList -> modelMapper.map(crewList, CrewListDTO.class))
                .collect(Collectors.toList());

        log.info("가입된 크루 목록 ====" + crewlistDTO);

        return crewlistDTO;
    }

    @Transactional
    public Object inserCrewListApply(Integer crewId, CrewListDTO crewListDTO) {

        int result = 0;

        try {
//            CrewListId crewListId = new CrewListId(crewListDTO.getUser().getUserId(), crewId);

            Crew crew = crewRepository.findById(crewId).get();
            CrewDTO crewDTO = modelMapper.map(crew, CrewDTO.class);

//            crewListDTO.setUser(crewListId);
            crewListDTO.setCrew(crewDTO);
            crewListDTO.setApprovalStatus(0);

            CrewList newCrewList = modelMapper.map(crewListDTO, CrewList.class);

            crewListRepository.save(newCrewList);

            log.info("[CrewService] insertCrewListApply crewDTO ===========> " + crewListDTO);
            log.info("[CrewService] insertCrewListApply crewDTO ===========> " + newCrewList);

            result = 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (result > 0) ? "크루 신청 성공" : "크루 신청 실패";
    }

    public int seletTotalCrewList(String userId) {
        User user = userRepository.findByUserId(userId);
        List<CrewList> crewLists = crewListRepository.findByUserAndApprovalStatus(user,1);


        return crewLists.size();
    }

    public List<CrewListDTO> selectCrewListWithPaging(String userId,Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("user").descending());

        User user = userRepository.findByUserId(userId);

        Page<CrewList> result = crewListRepository.findByUserAndApprovalStatus(user,1,paging);

        List<CrewListDTO> crewListDTOS = result.stream()
                .map(crewList -> modelMapper.map(crewList, CrewListDTO.class))
                .collect(Collectors.toList());

        return crewListDTOS;
    }
}
