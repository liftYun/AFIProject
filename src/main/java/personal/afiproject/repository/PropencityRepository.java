package personal.afiproject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import personal.afiproject.entity.PropencityEntity;

public interface PropencityRepository extends MongoRepository<PropencityEntity, String>{

}
