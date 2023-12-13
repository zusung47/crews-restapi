package com.mentaljava.mentaljavarestapiproject.table.user.service;

import com.mentaljava.mentaljavarestapiproject.table.crewlist.entity.CrewList;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.repository.CrewListRepository;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.DiamondChangeDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import com.mentaljava.mentaljavarestapiproject.table.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final CrewListRepository crewListRepository;

    private final ModelMapper modelMapper;


    public List<UserDTO> findAllUserList() {

        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = userList.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return userDTOList;
    }

    @Transactional
    public String deleteUser(String userId) {
        int result = 0;

        try {
            User user = userRepository.findByUserId(userId);
            if(user != null){
                userRepository.delete(user);
                result = 1;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (result > 0) ? "크루원 삭제 성공" : "크루원 삭제 실패";
    }


    @Transactional
    public Object editUserNickname(UserDTO userDTO) {
        int result = 0;

        try {
            User user = userRepository.findById(userDTO.getUserId()).get();
            user.setNickname(userDTO.getNickname());
            userRepository.save(user);

            result = 1;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return (result > 0) ? "닉네임 변경 성공" : "닉네임 변경 실패";
    }

    @Transactional
    public Object plusUserDiamond(UserDTO userDTO) {

        int result = 0;

        try {
            User user = userRepository.findById(userDTO.getUserId()).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

            // 현재 다이아몬드 수를 가져와서 1을 더해줍니다.
            int currentDiamondCount = user.getDiamondCount();
            user.setDiamondCount(currentDiamondCount + 1);

            userRepository.save(user);  // 변경된 유저 정보를 저장합니다.

            result = 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (result > 0) ? "다이아몬드 추가 성공" : "다이아몬드 추가 실패";
    }

    @Transactional
    public Object minusUserDiamond(UserDTO userDTO) {

        int result = 0;

        try {
            User user = userRepository.findById(userDTO.getUserId()).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

            // 현재 다이아몬드 수를 가져와서 1을 빼줍니다.
            int currentDiamondCount = user.getDiamondCount();
            user.setDiamondCount(currentDiamondCount - 1);

            userRepository.save(user);  // 변경된 유저 정보를 저장합니다.

            result = 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (result > 0) ? "다이아몬드 감소 성공" : "다이아몬드 감소 실패";
    }

    @Transactional
    public String diamondSubmit(DiamondChangeDTO diamondChangeDTO) {

        int result = 0;
        try {
            User user = userRepository.findByUserId(diamondChangeDTO.getUserId());
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTO.setDiamondCount(userDTO.getDiamondCount() + diamondChangeDTO.getDiamond());

            User newUser = modelMapper.map(userDTO, User.class);

            userRepository.save(newUser);

            result =1;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (result> 0) ? "평가하기 성공 ": "평가하기 실패";


    }
}
