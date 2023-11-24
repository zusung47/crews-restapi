package com.mentaljava.mentaljavarestapiproject.table.crew.service;

import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.crew.repository.CrewRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOError;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CrewService {

    private final CrewRepository crewRepository;

    private final ModelMapper modelMapper;

    public CrewService(CrewRepository crewRepository, ModelMapper modelMapper){
        this.crewRepository = crewRepository;
        this.modelMapper = modelMapper;
    }

    public List<CrewDTO> findAllCrewList() {

        List<Crew> crewList = crewRepository.findAll();
        List<CrewDTO> crewDTOList = crewList.stream().map(crew -> modelMapper.map(crew, CrewDTO.class)).collect(Collectors.toList());
        return crewDTOList;
    }

    public List<CrewDTO> selectCrewListAboutExercise() {

        List<Crew> crewAboutExercise = crewRepository.findByCrewCategoryCode_CategoryCode(1);

        List<CrewDTO> crewDTOList = crewAboutExercise.stream().map(crew -> modelMapper.map(crew, CrewDTO.class)).collect(Collectors.toList());

        return crewDTOList;
    }

    public List<CrewDTO> selectCrewListAboutStudy() {

        List<Crew> crewAboutStudy = crewRepository.findByCrewCategoryCode_CategoryCode(2);

        List<CrewDTO> crewDTOList = crewAboutStudy.stream().map(crew -> modelMapper.map(crew, CrewDTO.class)).collect(Collectors.toList());

        return crewDTOList;
    }

    public List<CrewDTO> selectCrewListAboutHabit() {

        List<Crew> crewAboutHabit = crewRepository.findByCrewCategoryCode_CategoryCode(3);

        List<CrewDTO> crewDTOList = crewAboutHabit.stream().map(crew -> modelMapper.map(crew, CrewDTO.class)).collect(Collectors.toList());

        return crewDTOList;
    }

    public List<CrewDTO> selectCrewListAboutEtc() {

        List<Crew> crewAboutEtc = crewRepository.findByCrewCategoryCode_CategoryCode(4);

        List<CrewDTO> crewDTOList = crewAboutEtc.stream().map(crew -> modelMapper.map(crew, CrewDTO.class)).collect(Collectors.toList());

        return crewDTOList;
    }

    public List<CrewDTO> selectCrewListAboutRecruitmentStatusOk() {

        List<Crew> crewListAboutRecruitmentStatusOk = crewRepository.findByRecruitmentStatus("1");

        List<CrewDTO> crewDTOList = crewListAboutRecruitmentStatusOk.stream()
                                        .map(crew -> modelMapper.map(crew, CrewDTO.class)).collect(Collectors.toList());

        return crewDTOList;
    }

    public List<CrewDTO> selectCrewListAboutRecruitmentStatusNo() {

        List<Crew> crewListAboutRecruitmentStatusNo = crewRepository.findByRecruitmentStatus("0");

        List<CrewDTO> crewDTOList = crewListAboutRecruitmentStatusNo.stream()
                                        .map(crew -> modelMapper.map(crew, CrewDTO.class)).collect(Collectors.toList());

        return crewDTOList;
    }

    public CrewDTO selectCrew(Integer crewId) {

        Crew crew = crewRepository.findById(crewId).get();
        CrewDTO crewDTO = modelMapper.map(crew, CrewDTO.class);

        return crewDTO;
    }

    public String selectCrewIntro(Integer crewId) {

        Crew crew = crewRepository.findById(crewId).get();
        CrewDTO crewDTO = modelMapper.map(crew, CrewDTO.class);
        String crewIntro = crewDTO.getIntroduction();

        return crewIntro;
    }

    @Transactional
    public String updateCrewIntro(CrewDTO crewDTO) {

        int result = 0;

        try {
            Crew crew = crewRepository.findById(crewDTO.getCrewId()).get();

            crew.setIntroduction(crewDTO.getIntroduction());

            result = 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (result > 0) ? "정보 업데이트 성공" : "정보 업데이트 실패";
    }

    @Transactional
    public String deleteCrew(Integer crewId) {
        int result = 0;

        try {
            Crew crew = crewRepository.findByCrewId(crewId);
            if(crew != null){
                crewRepository.delete(crew);

                result = 1;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (result > 0) ? "크루 삭제 성공" : "크루 삭제 실패";
    }

    @Transactional
    public String insertCrew(CrewDTO crewDTO) {

        int result = 0;

        try{
            crewDTO.setCreationDate(LocalDate.now());
            crewDTO.setRecruitmentStatus("1");

            log.info("[CrewService] insertCrew crewDTO ===========> " + crewDTO);
            Crew newCrew = modelMapper.map(crewDTO, Crew.class);

            crewRepository.save(newCrew);

            result = 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (result > 0) ? "크루 만들기 성공" : "크루 만들기 실패";
    }

}
