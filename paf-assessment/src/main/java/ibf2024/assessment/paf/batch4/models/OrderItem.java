package ibf2024.assessment.paf.batch4.models;

public class OrderItem {

    private Integer beerId;
    private Integer quantity;
    
    public OrderItem() {
    }

    public OrderItem(Integer beerId, Integer quantity) {
        this.beerId = beerId;
        this.quantity = quantity;
    }

    public Integer getBeerId() {
        return beerId;
    }

    public void setBeerId(Integer beerId) {
        this.beerId = beerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
}
