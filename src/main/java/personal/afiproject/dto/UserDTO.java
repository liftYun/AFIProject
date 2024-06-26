package personal.afiproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import personal.afiproject.entity.UserEntity;

// html의 값을 자동으로 DTO타입으로 읽어준다

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {

    private String userId;
    private String userPw;
    private String userEmail;
    private String userName;
    private String userPhone;
    private String userBirthday;
    private String userGender;
    private int userRole = 1;

    public static UserDTO toUserDTO(UserEntity userEntity){

        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(userEntity.getUserId());
        userDTO.setUserPw(userEntity.getUserPw());
        userDTO.setUserEmail(userEntity.getUserEmail());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setUserPhone(userEntity.getUserPhone());
        userDTO.setUserBirthday(userEntity.getUserBirthday());
        userDTO.setUserGender(userEntity.getUserGender());
        userDTO.setUserRole(userEntity.getUserRole());

        return userDTO;


    }
}
