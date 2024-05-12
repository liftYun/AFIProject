package personal.afiproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class AfiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AfiProjectApplication.class, args);
	}

}
/*
package personal.afiproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import personal.afiproject.entity.UserEntity;
import personal.afiproject.repository.UserRepository;

@SpringBootApplication
@EnableMongoRepositories
public class AfiProjectApplication implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AfiProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// 예시 사용자 생성 및 저장
		UserEntity user = new UserEntity();
		user.setUserId(user.getUserId());
		user.setUserName(user.getUserName());
		user.setUserPw(user.getUserPw());
		user.setUserPhone(user.getUserPhone());
		user.setUserBirthday(user.getUserBirthday());
		user.setUserGender(user.getUserGender());

		if (!userRepository.existsByUserId(user.getUserId())) {
			userRepository.save(user);
			System.out.println("새로운 사용자가 저장되었습니다.");
		} else {
			System.out.println("이미 존재하는 사용자입니다.");
		}
	}
}
*/
