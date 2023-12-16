package com.mentaljava.mentaljavarestapiproject.table.crewcheck.service;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.table.crewcheck.dto.CrewCheckDTO;
import com.mentaljava.mentaljavarestapiproject.table.crewcheck.entity.CrewCheck;
import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.crew.repository.CrewRepository;
import com.mentaljava.mentaljavarestapiproject.table.crewcheck.repository.CrewCheckRepository;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.entity.CrewList;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.repository.CrewListRepository;
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
public class CrewCheckService {

    private final CrewCheckRepository crewCheckRepository;
    private final CrewRepository crewRepository;
    private final UserRepository userRepository;
    private final CrewListRepository crewListRepository;

    private final ModelMapper modelMapper;

    public int selectCrewCheckListByUserId(String userId, Integer crewId) {

        Crew crew = crewRepository.findByCrewId(crewId);
        User user = userRepository.findByUserId(userId);

        List<CrewCheck> crewCheckList = crewCheckRepository.findByCrewAndUser(crew, user);

        return crewCheckList.size();
    }

    public List<CrewCheckDTO> selectCrewCheckListByUserIdWithPaging(String userId, Integer crewId, Criteria cri) {
        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("today"));

        Crew crew = crewRepository.findByCrewId(crewId);
        User user = userRepository.findByUserId(userId);

        Page<CrewCheck> result = crewCheckRepository.findByCrewAndUser(crew, user, paging);

        List<CrewCheckDTO> crewCheckDTOS = result.stream()
                .map(crewCheck -> modelMapper.map(crewCheck, CrewCheckDTO.class)).collect(Collectors.toList());

        return crewCheckDTOS;
    }

    @Transactional
    public String updateCrewCheck(CrewCheckDTO crewCheckDTO) {

        int result = 0;

        try{
            Crew crew = crewRepository.findByCrewId(crewCheckDTO.getCrew().getCrewId());
            User user = userRepository.findByUserId(crewCheckDTO.getUser().getUserId());

            CrewCheck crewCheck = crewCheckRepository.findByCrewAndUserAndToday(crew, user, crewCheckDTO.getToday());

            crewCheck.setIsCheck("Y");

            CrewList crewList = crewListRepository.findByCrewAndUser(crew, user);

            crewList.setCheckCount(crewList.getCheckCount()+1);

            result = 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (result > 0) ? "정보 업데이트 성공" : "정보 업데이트 실패";
    }
}
