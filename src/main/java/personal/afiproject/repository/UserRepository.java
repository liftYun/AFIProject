package personal.afiproject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import personal.afiproject.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {
//    boolean existsById(String userId);
    boolean existsByUserId(String userId);

    Optional<UserEntity> findByUserId(String userId);

    void deleteByUserId(String userId);

    boolean existsByUserPhone(String userPhone);

}
