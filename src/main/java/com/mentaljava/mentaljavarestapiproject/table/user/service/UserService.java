package com.mentaljava.mentaljavarestapiproject.table.user.service;

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
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public User findOne(String userID) {
        return userRepository.findByUserId(userID);
    }

    public List<UserDTO> findAllUserList() {

        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = userList.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return userDTOList;
    }

    @Transactional
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }


    @Transactional
    public Object editUserNickname(UserDTO userDTO) {
        int result = 0;

        try {
            User user = userRepository.findById(userDTO.getUserId()).get();
            user.setNickname(userDTO.getNickname());

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
}
