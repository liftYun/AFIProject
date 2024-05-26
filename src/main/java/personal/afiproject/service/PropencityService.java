package personal.afiproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.afiproject.dto.PropencityDTO;
import personal.afiproject.entity.PropencityEntity;
import personal.afiproject.repository.PropencityRepository;

@Service
public class PropencityService {
    @Autowired
    private PropencityRepository propencityRepository;
    public void save(PropencityDTO propencityDTO) {
        PropencityEntity propencityEntity = PropencityEntity.fromDTO(propencityDTO);
        propencityRepository.save(propencityEntity);
    }
}
