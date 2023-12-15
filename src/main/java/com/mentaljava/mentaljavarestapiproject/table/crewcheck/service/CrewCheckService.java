package com.mentaljava.mentaljavarestapiproject.table.crewcheck.service;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.table.crewcheck.dto.CrewCheckDTO;
import com.mentaljava.mentaljavarestapiproject.table.crewcheck.entity.CrewCheck;
import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.crew.repository.CrewRepository;
import com.mentaljava.mentaljavarestapiproject.table.crewcheck.repository.CrewCheckRepository;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CrewCheckService {

    private final CrewCheckRepository crewCheckRepository;
    private final CrewRepository crewRepository;
    private final UserRepository userRepository;

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
}
