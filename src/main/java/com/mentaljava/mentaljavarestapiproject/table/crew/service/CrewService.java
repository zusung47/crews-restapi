package com.mentaljava.mentaljavarestapiproject.table.crew.service;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.crew.repository.CrewRepository;
import com.mentaljava.mentaljavarestapiproject.table.crewcategory.entity.CrewCategory;
import com.mentaljava.mentaljavarestapiproject.table.crewcheck.dto.CrewCheckDTO;
import com.mentaljava.mentaljavarestapiproject.table.crewcheck.entity.CrewCheck;
import com.mentaljava.mentaljavarestapiproject.table.crewcheck.repository.CrewCheckRepository;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.dto.CrewListDTO;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.entity.CrewList;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.repository.CrewListRepository;
import com.mentaljava.mentaljavarestapiproject.table.crewlistid.dto.CrewListIdDTO;
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
@RequiredArgsConstructor
@Slf4j
public class CrewService {

    private final CrewRepository crewRepository;
    private final UserRepository userRepository;
    private final CrewListRepository crewListRepository;
    private final CrewCheckRepository crewCheckRepository;

    private final ModelMapper modelMapper;


    public List<CrewDTO> findAllCrewList() {

        List<Crew> crewList = crewRepository.findAll();
        List<CrewDTO> crewDTOList = crewList.stream().map(crew -> modelMapper.map(crew, CrewDTO.class))
                .collect(Collectors.toList());
        return crewDTOList;
    }

    public List<CrewDTO> selectCrewListAboutExercise() {

        List<Crew> crewAboutExercise = crewRepository.findByCrewCategoryCode_CategoryCode(1);

        List<CrewDTO> crewDTOList = crewAboutExercise.stream().map(crew -> modelMapper.map(crew, CrewDTO.class))
                .collect(Collectors.toList());

        return crewDTOList;
    }

    public List<CrewDTO> selectCrewListAboutStudy() {

        List<Crew> crewAboutStudy = crewRepository.findByCrewCategoryCode_CategoryCode(2);

        List<CrewDTO> crewDTOList = crewAboutStudy.stream().map(crew -> modelMapper.map(crew, CrewDTO.class))
                .collect(Collectors.toList());

        return crewDTOList;
    }

    public List<CrewDTO> selectCrewListAboutHabit() {

        List<Crew> crewAboutHabit = crewRepository.findByCrewCategoryCode_CategoryCode(3);

        List<CrewDTO> crewDTOList = crewAboutHabit.stream().map(crew -> modelMapper.map(crew, CrewDTO.class))
                .collect(Collectors.toList());

        return crewDTOList;
    }

    public List<CrewDTO> selectCrewListAboutEtc() {

        List<Crew> crewAboutEtc = crewRepository.findByCrewCategoryCode_CategoryCode(4);

        List<CrewDTO> crewDTOList = crewAboutEtc.stream().map(crew -> modelMapper.map(crew, CrewDTO.class))
                .collect(Collectors.toList());

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
    public String updateCrew(CrewDTO crewDTO) {

        int result = 0;

        try {
            Crew crew = crewRepository.findById(crewDTO.getCrewId()).get();

            CrewCategory crewCategory = new CrewCategory();
            crewCategory.setCategoryCode(crewDTO.getCrewCategoryCode().getCategoryCode());

            crew.setCrewCategoryCode(crewCategory);
            crew.setCrewName(crewDTO.getCrewName());
            crew.setCrewRecruitmentContent(crewDTO.getCrewRecruitmentContent());
            crew.setCrewRecruitmentPost(crewDTO.getCrewRecruitmentPost());
            crew.setStartDate(crewDTO.getStartDate());
            crew.setEndDate(crewDTO.getEndDate());
            crew.setRecruitmentStatus(crewDTO.getRecruitmentStatus());

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
            if (crew != null) {
                crewRepository.delete(crew);

                result = 1;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (result > 0) ? "크루 삭제 성공" : "크루 삭제 실패";
    }

    @Transactional
    public CrewDTO insertCrew(CrewDTO crewDTO) {


            crewDTO.setCreationDate(LocalDate.now());
            crewDTO.setRecruitmentStatus("1");
            crewDTO.setIntroduction("크루소개글을 입력해주세요.");

            log.info("[CrewService] insertCrew crewDTO ===========> " + crewDTO);
            Crew newCrew = modelMapper.map(crewDTO, Crew.class);

            crewRepository.save(newCrew);

            CrewDTO giveCrewInfo = new CrewDTO();
            giveCrewInfo = modelMapper.map(newCrew, CrewDTO.class);



            return giveCrewInfo;
    }

    private void newCrewList(int crewNum) {

        CrewListDTO crewListDTO = new CrewListDTO();
        Crew giveInfo = crewRepository.findByCrewId(crewNum);
        log.info("newCrew give" + giveInfo);
        CrewDTO giveCrew = modelMapper.map(giveInfo, CrewDTO.class);
        crewListDTO.setUser(giveCrew.getCaptain());
        crewListDTO.setCrew(giveCrew);
        crewListDTO.setApprovalStatus(1);
        crewListDTO.setIsCaptain("CAPTAIN");

        CrewList newCrewList = modelMapper.map(crewListDTO, CrewList.class);

        crewListRepository.save(newCrewList);
    }

    public int seletTotalCrew() {

        List<Crew> crewList = crewRepository.findAll();

        return crewList.size();
    }

    public List<CrewDTO> selectCrewListWithPaging(Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("crewId").descending());

        Page<Crew> result = crewRepository.findAll(paging);
        List<CrewDTO> crewDTOList = result.stream()
                .map(crew -> modelMapper.map(crew, CrewDTO.class))
                .collect(Collectors.toList());

        return crewDTOList;

    }

    public int selectTotalCrewListAboutExercise() {
        log.info("[CrewService] selectTotalCrewListAboutExercise start =============");

        List<Crew> crewList = crewRepository.findByCrewCategoryCode_CategoryCode(1);

        log.info("[CrewService] crewList.size : {}", crewList.size());
        log.info("[CrewService] selectTotalCrewListAboutExercise end =============");

        return crewList.size();
    }

    public List<CrewDTO> selectCrewListAboutExerciseWithPaging(Criteria cri) {

        log.info("[CrewService] selectCrewListAboutExerciseWithPaging start ============");

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("crewId").descending());

        Page<Crew> result = crewRepository.findByCrewCategoryCode_CategoryCode(1, paging);

        List<CrewDTO> crewDTOList = result.stream()
                .map(crew -> modelMapper.map(crew, CrewDTO.class)).collect(Collectors.toList());

        log.info("[CrewService] selectCrewListAboutExerciseWithPaging end ============");
        return crewDTOList;
    }

    public int selectTotalCrewListAboutStudy() {
        log.info("[CrewService] selectTotalCrewListAboutStudy start =============");

        List<Crew> crewList = crewRepository.findByCrewCategoryCode_CategoryCode(2);

        log.info("[CrewService] crewList.size : {}", crewList.size());
        log.info("[CrewService] selectTotalCrewListAboutStudy end =============");

        return crewList.size();
    }

    public List<CrewDTO> selectCrewListAboutStudyWithPaging(Criteria cri) {

        log.info("[CrewService] selectCrewListAboutStudyWithPaging start ============");

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("crewId").descending());

        Page<Crew> result = crewRepository.findByCrewCategoryCode_CategoryCode(2, paging);

        List<CrewDTO> crewDTOList = result.stream()
                .map(crew -> modelMapper.map(crew, CrewDTO.class)).collect(Collectors.toList());

        log.info("[CrewService] selectCrewListAboutStudyWithPaging end ============");
        return crewDTOList;
    }

    public int selectTotalCrewListAboutHabit() {
        log.info("[CrewService] selectTotalCrewListAboutHabit start =============");

        List<Crew> crewList = crewRepository.findByCrewCategoryCode_CategoryCode(3);

        log.info("[CrewService] crewList.size : {}", crewList.size());
        log.info("[CrewService] selectTotalCrewListAboutHabit end =============");

        return crewList.size();
    }

    public List<CrewDTO> selectCrewListAboutHabitWithPaging(Criteria cri) {

        log.info("[CrewService] selectCrewListAboutHabitWithPaging start ============");

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("crewId").descending());

        Page<Crew> result = crewRepository.findByCrewCategoryCode_CategoryCode(3, paging);

        List<CrewDTO> crewDTOList = result.stream()
                .map(crew -> modelMapper.map(crew, CrewDTO.class)).collect(Collectors.toList());

        log.info("[CrewService] selectCrewListAboutHabitWithPaging end ============");
        return crewDTOList;
    }

    public int selectTotalCrewListAboutEtc() {
        log.info("[CrewService] selectTotalCrewListAboutEtc start =============");

        List<Crew> crewList = crewRepository.findByCrewCategoryCode_CategoryCode(4);

        log.info("[CrewService] crewList.size : {}", crewList.size());
        log.info("[CrewService] selectTotalCrewListAboutEtc end =============");

        return crewList.size();
    }

    public List<CrewDTO> selectCrewListAboutEtcWithPaging(Criteria cri) {

        log.info("[CrewService] selectCrewListAboutEtcWithPaging start ============");

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("crewId").descending());

        Page<Crew> result = crewRepository.findByCrewCategoryCode_CategoryCode(4, paging);

        List<CrewDTO> crewDTOList = result.stream()
                .map(crew -> modelMapper.map(crew, CrewDTO.class)).collect(Collectors.toList());

        log.info("[CrewService] selectCrewListAboutEtcWithPaging end ============");
        return crewDTOList;
    }

    public int selectSearchCrewList(String search) {
        log.info("[CrewService] selectSearchCrewList start ============");
        log.info("[CrewService] search : {} ", search);

        List<Crew> crewListWithSearchValue = crewRepository.findByCrewNameContaining(search);

        log.info("[CrewService] crewListWithSearchValue.size : {}", crewListWithSearchValue.size());
        log.info("[CrewService] selectSearchCrewList end ============");
        return crewListWithSearchValue.size();
    }

    public List<CrewDTO> selectSearchCrewListWithPaging(String search, Criteria cri) {

        log.info("[CrewService] selectSearchCrewListWithPaging start ============");

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("crewId").descending());

        Page<Crew> result = crewRepository.findByCrewNameContaining(search, paging);

        List<CrewDTO> crewDTOList = result.stream()
                .map(crew -> modelMapper.map(crew, CrewDTO.class)).collect(Collectors.toList());

        log.info("[CrewService] selectSearchCrewListWithPaging end ============");
        return crewDTOList;
    }

    //캡틴을 통해 크루 정보 조회 추후 필요한 데이터만 가져오도록 수정
//    public List<CrewDTO> getCrewByCaptain(User captain) {
//        List<Crew> crewListWithCaptain = crewRepository.findByCaptain(captain);
//
//        List<CrewDTO> crewDTOList = crewListWithCaptain.stream()
//                .map(crew -> modelMapper.map(crew, CrewDTO.class)).collect(Collectors.toList());
//
//        return crewDTOList;
//    }

    public int getCrewByCaptainTotal(String captain) {

        User user = userRepository.findByUserId(captain);
        List<Crew> crewList = crewRepository.findByCaptain(user);

        return crewList.size();
    }

    public List<CrewDTO> selectCrewCaptainWithPaging(String captain, Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();

        Pageable paging = PageRequest.of(index, count, Sort.by("crewId").descending());

        User user = userRepository.findByUserId(captain);

        Page<Crew> result = crewRepository.findByCaptain(user, paging);

        List<CrewDTO> crewDTOList = result.stream()
                .map(crew -> modelMapper.map(crew, CrewDTO.class))
                .collect(Collectors.toList());

        return crewDTOList;
    }

    @Transactional
    public String insertCrewList(CrewDTO getCrewInfo) {

        int result = 0;

        try{

            CrewListDTO crewListDTO = new CrewListDTO();
            crewListDTO.setCrew(getCrewInfo);
            crewListDTO.setUser(getCrewInfo.getCaptain());
            crewListDTO.setIsCaptain("CAPTAIN");
            crewListDTO.setApprovalStatus(1);
            crewListDTO.setEndDate(getCrewInfo.getEndDate());
            crewListDTO.setScoreStatus("no");
            crewListDTO.setCheckCount(0);
            crewListDTO.setId(new CrewListIdDTO(getCrewInfo.getCaptain().getUserId(),getCrewInfo.getCrewId()));
            CrewList crewList = modelMapper.map(crewListDTO, CrewList.class);

            crewListRepository.save(crewList);

            LocalDate startDate = getCrewInfo.getStartDate();
            LocalDate endDate = getCrewInfo.getEndDate();
            log.info("startdate =========================== {}" , startDate);
            log.info("endDate =========================== {}" , endDate);

            long difference = ChronoUnit.DAYS.between(startDate, endDate) + 1;
            log.info("difference =========================== {}" , difference);

            for(long i = 0; i < difference; i++){
                log.info("test code i ========================= {}" , i);
                CrewCheckDTO crewCheckDTO = new CrewCheckDTO();
                crewCheckDTO.setCrew(getCrewInfo);
                crewCheckDTO.setUser(getCrewInfo.getCaptain());
                crewCheckDTO.setIsCheck("N");
                crewCheckDTO.setToday(startDate.plusDays(i));

                CrewCheck crewCheck = modelMapper.map(crewCheckDTO, CrewCheck.class);

                crewCheckRepository.save(crewCheck);
            }

            result = 1;

        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return (result>0)? "크루리스트 등록성공" : "크루리스트 등록 실패";
    }

}
