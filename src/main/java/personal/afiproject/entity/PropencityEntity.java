package personal.afiproject.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import personal.afiproject.dto.PropencityDTO;

@Document(collection = "propencity")
public class PropencityEntity {
    @Id
    private String id;

    private String userId;
    private String product;
    private String understanding;
    private String period;
    private String importance;
    private String income;
    private String loss;

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
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUnderstanding() {
        return understanding;
    }

    public void setUnderstanding(String understanding) {
        this.understanding = understanding;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getLoss() {
        return loss;
    }

    public void setLoss(String loss) {
        this.loss = loss;
    }

    public static PropencityEntity fromDTO(PropencityDTO dto) {
        PropencityEntity propencityEntity = new PropencityEntity();

        propencityEntity.setUserId(dto.getUserId());
        propencityEntity.setProduct(dto.getProduct());
        propencityEntity.setUnderstanding(dto.getUnderstanding());
        propencityEntity.setPeriod(dto.getPeriod());
        propencityEntity.setImportance(dto.getImportance());
        propencityEntity.setIncome(dto.getIncome());
        propencityEntity.setLoss(dto.getLoss());
        return propencityEntity;
    }
}
