package personal.afiproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import personal.afiproject.entity.PropencityEntity;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PropencityDTO {

    private String userId;
    private String product;
    private String understanding;
    private String period;
    private String importance;
    private String income;
    private String loss;

    public static PropencityDTO toPropencityDTO(PropencityEntity propencityEntity){
        PropencityDTO propencityDTO = new PropencityDTO();

        propencityDTO.setUserId(propencityEntity.getUserId());
        propencityDTO.setProduct(propencityEntity.getProduct());
        propencityDTO.setUnderstanding(propencityEntity.getUnderstanding());
        propencityDTO.setPeriod(propencityEntity.getPeriod());
        propencityDTO.setImportance(propencityEntity.getImportance());
        propencityDTO.setIncome(propencityEntity.getIncome());
        propencityDTO.setLoss(propencityEntity.getLoss());

        return propencityDTO;
    }
}
