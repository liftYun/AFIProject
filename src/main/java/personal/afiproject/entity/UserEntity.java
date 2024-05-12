package personal.afiproject.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import personal.afiproject.dto.UserDTO;

@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;

    private String userId;
    private String userName;
    private String userPw;
    private String userPhone;
    private String userBirthday;
    private String userGender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
// Getters and Setters

    public static UserEntity fromDTO(UserDTO dto) {
        System.out.println("Entity");
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(dto.getUserId());
        userEntity.setUserName(dto.getUserName());
        userEntity.setUserPw(dto.getUserPw());
        userEntity.setUserPhone(dto.getUserPhone());
        userEntity.setUserBirthday(dto.getUserBirthday());
        userEntity.setUserGender(dto.getUserGender());
        return userEntity;
    }
}

