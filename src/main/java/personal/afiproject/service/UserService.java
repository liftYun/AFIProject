package personal.afiproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import personal.afiproject.dto.UserDTO;
import personal.afiproject.entity.UserEntity;
import personal.afiproject.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void save(UserDTO userDTO) {
        String hashedPassword = BCrypt.hashpw(userDTO.getUserPw(), BCrypt.gensalt());
        userDTO.setUserPw(hashedPassword);
        UserEntity userEntity = UserEntity.fromDTO(userDTO);
        userRepository.save(userEntity);
    }


    public UserDTO login(UserDTO userDTO) {
        Optional<UserEntity> userOptional = userRepository.findByUserId(userDTO.getUserId());
        System.out.println("UserService" + userDTO.getUserId());
        if (userOptional.isPresent()){
            UserEntity userEntity = userOptional.get();

            String hashedUserPw = userEntity.getUserPw();

            String inputPW = userDTO.getUserPw();

            if(BCrypt.checkpw(inputPW, hashedUserPw)) {
                UserDTO dto = UserDTO.toUserDTO(userEntity);
                return dto;
            }else {
                return null;
            }
        }else return null;
    }

    public UserDTO mypageForm(String loginUser) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserId(loginUser);
        //아이디에 해당하는 정보를 데이터베이스에서 Entuty타입으로 가져옴
        if(optionalUserEntity.isPresent()){
            return UserDTO.toUserDTO(optionalUserEntity.get());
            // 엔티티에 있는 정보를 dto 타입으로 바꾼 뒤 서비스로 반환
        }
        else{
            return null;
        }
    }

    public void updateUser(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findByUserId(userDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userDTO.getUserId()));
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setUserPhone(userDTO.getUserPhone());
        userEntity.setUserBirthday(userDTO.getUserBirthday());
        userEntity.setUserGender(userDTO.getUserGender());

        userRepository.save(userEntity);
    }

    public void deleteUser(String loginUser) {

        userRepository.deleteByUserId(loginUser);
    }

    public boolean existsByUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }

    public boolean existsByUserPhone(String userPhone) {
        return userRepository.existsByUserPhone(userPhone);
    }

}
