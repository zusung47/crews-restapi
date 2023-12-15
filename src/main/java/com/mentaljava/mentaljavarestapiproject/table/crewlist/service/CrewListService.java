package com.mentaljava.mentaljavarestapiproject.table.crewlist.service;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.crew.repository.CrewRepository;
import com.mentaljava.mentaljavarestapiproject.table.crewcheck.dto.CrewCheckDTO;
import com.mentaljava.mentaljavarestapiproject.table.crewcheck.entity.CrewCheck;
import com.mentaljava.mentaljavarestapiproject.table.crewcheck.repository.CrewCheckRepository;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.dto.CrewListDTO;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.entity.CrewList;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.repository.CrewListRepository;
import com.mentaljava.mentaljavarestapiproject.table.crewlistid.dto.CrewListIdDTO;
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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CrewListService {

    private final CrewListRepository crewListRepository;
    private final UserRepository userRepository;
    private final CrewCheckRepository crewCheckRepository;
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
            CrewList crewList = crewListRepository
                    .findById_CrewIdAndId_UserId(crewListDTO.getId().getCrewId(), crewListDTO.getId().getUserId());

            Crew crew = crewRepository.findByCrewId(crewListDTO.getCrew().getCrewId());

            log.info("크루리스트디티오 =============== {}", crewListDTO);


            crewList.setApprovalStatus(1);
            crewList.setIsCaptain("CREWON");
            crewList.setEndDate(crew.getEndDate());

            LocalDate startDate = crew.getStartDate();
            LocalDate endDate = crew.getEndDate();

            long difference = ChronoUnit.DAYS.between(startDate, endDate) + 1;

            CrewDTO crewDTO = modelMapper.map(crew, CrewDTO.class);

            for(long i = 0; i < difference; i++){
                CrewCheckDTO crewCheckDTO = new CrewCheckDTO();
                crewCheckDTO.setCrew(crewDTO);
                crewCheckDTO.setUser(crewListDTO.getUser());
                crewCheckDTO.setIsCheck("N");
                crewCheckDTO.setToday(startDate.plusDays(i));

                CrewCheck crewCheck = modelMapper.map(crewCheckDTO, CrewCheck.class);

                crewCheckRepository.save(crewCheck);
            }

            result = 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (result > 0) ? "크루 받아주기 성공" : "크루 받아주기 실패";
    }

    @Transactional
    public String updateStatusDisagree(CrewListDTO crewListDTO) {

        int result = 0;

        try {
            CrewList crewList = crewListRepository
                    .findById_CrewIdAndId_UserId(crewListDTO.getId().getCrewId(), crewListDTO.getId().getUserId());
            crewList.setApprovalStatus(2);
            crewList.setIsCaptain("USER");

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
    public Object insertCrewListApply(Integer crewId, CrewListDTO crewListDTO) {

        int result = 0;

        try {
            CrewListId crewListId = new CrewListId(crewListDTO.getUser().getUserId(), crewId);
            CrewListIdDTO crewListIdDTO = modelMapper.map(crewListId, CrewListIdDTO.class);

            Crew crew = crewRepository.findById(crewId).get();
            CrewDTO crewDTO = modelMapper.map(crew, CrewDTO.class);

            crewListDTO.setId(crewListIdDTO);
            crewListDTO.setCrew(crewDTO);
            crewListDTO.setApprovalStatus(0);
            crewListDTO.setScoreStatus("no");

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

        List<CrewList> crewLists = crewListRepository.findByUserAndApprovalStatus(user, 1);

        return crewLists.size();
    }

    public List<CrewListDTO> selectCrewListWithPaging(String userId, Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("user").descending());

        User user = userRepository.findByUserId(userId);

        Page<CrewList> result = crewListRepository.findByUserAndApprovalStatus(user, paging);

        List<CrewListDTO> crewListDTOS = result.stream()
                .map(crewList -> modelMapper.map(crewList, CrewListDTO.class))
                .collect(Collectors.toList());

        return crewListDTOS;
    }

    public List<CrewListDTO> selectEndCrewListWithPaging(String userId, Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("user").descending());

        User user = userRepository.findByUserId(userId);

        Page<CrewList> result = crewListRepository.findByUserAndEndCrew(user, paging);

        List<CrewListDTO> crewListDTOS = result.stream()
                .map(crewList -> modelMapper.map(crewList, CrewListDTO.class))
                .collect(Collectors.toList());

        return crewListDTOS;
    }

    public int seletTotalCrewLists(String userId) {
        User user = userRepository.findByUserId(userId);
        List<CrewList> crewLists = crewListRepository.findByUserAndApprovalStatusAndDate(user);

        return crewLists.size();

    }

    public int selectTotalCrewUser(Integer crewId) {
        Crew crew = crewRepository.findByCrewId(crewId);
        List<CrewList> crewLists = crewListRepository.findByCrew(crew);

        return crewLists.size();
    }

    public List<CrewListDTO> selectCrewUsertWithPaging(Integer crewId, Criteria cri) {
        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("user").descending());

        Crew crew = crewRepository.findByCrewId(crewId);
        Page<CrewList> result = crewListRepository.findByCrew(crew, paging);

        List<CrewListDTO> crewListDTOS = result.stream()
                .map(crewList -> modelMapper.map(crewList, CrewListDTO.class))
                .collect(Collectors.toList());

        return crewListDTOS;
    }

    @Transactional
    public String updateScoreStatus(CrewListDTO crewListDTO) {
        int result = 0;

        try {
            CrewList crewList = crewListRepository.findById_CrewIdAndId_UserId(crewListDTO.getId().getCrewId(),
                    crewListDTO.getId().getUserId());
            CrewListDTO cld = modelMapper.map(crewList, CrewListDTO.class);
            cld.setScoreStatus(crewListDTO.getScoreStatus());

            CrewList cl = modelMapper.map(cld, CrewList.class);
            log.info("변경된 status"+cl);

            crewListRepository.save(cl);
            result = 1;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (result > 0) ? "status 변경성공" : "status 변경 실패";
    }
}
