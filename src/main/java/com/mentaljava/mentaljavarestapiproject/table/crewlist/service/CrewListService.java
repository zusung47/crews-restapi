package com.mentaljava.mentaljavarestapiproject.table.crewlist.service;

import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.dto.CrewListDTO;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.entity.CrewList;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.repository.CrewListRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CrewListService {

    private final CrewListRepository crewListRepository;

    private final ModelMapper modelMapper;

    public CrewListService(CrewListRepository crewListRepository, ModelMapper modelMapper){
        this.crewListRepository = crewListRepository;
        this.modelMapper = modelMapper;
    }

    public List<CrewListDTO> selectCrewListByCrewId(Integer crewId) {

        List<CrewList> crewListByCrewId = crewListRepository.findAllById_CrewId(crewId);

        List<CrewListDTO> crewlistDTO = crewListByCrewId.stream().map(crewList -> modelMapper.map(crewList, CrewListDTO.class)).collect(Collectors.toList());

        return crewlistDTO;
    }

    public List<CrewListDTO> selectCrewListByCrewIdAndWaitStatus(Integer crewId) {

        List<CrewList> crewListByCrewIdAndWaitStatus = crewListRepository.findById_CrewIdAndApprovalStatus(crewId, 0);

        List<CrewListDTO> crewlistDTO = crewListByCrewIdAndWaitStatus.stream().map(crewList -> modelMapper.map(crewList, CrewListDTO.class)).collect(Collectors.toList());


        return crewlistDTO;
    }

    @Transactional
    public String updateStatusAgree(CrewListDTO crewListDTO) {

        int result = 0;

        try {
            CrewList crewList = crewListRepository
                    .findById_CrewIdAndId_UserId(crewListDTO.getId().getCrewId(), crewListDTO.getId().getUserId());

            crewList.setApprovalStatus(1);

            result = 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (result > 0) ? "크루 받아주기 성공" : "크루 받아주기 실패";
    }

    @Transactional
    public Object updateStatusRejection(CrewListDTO crewListDTO) {

        int result = 0;

        try {
            CrewList crewList = crewListRepository
                    .findById_CrewIdAndId_UserId(crewListDTO.getId().getCrewId(), crewListDTO.getId().getUserId());

            crewList.setApprovalStatus(2);

            result = 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (result > 0) ? "크루 거절하기 성공" : "크루 거절하기 실패";
    }
}
