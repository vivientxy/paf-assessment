package ibf2024.assessment.paf.batch4.models;

import java.util.Date;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Order {
    
    private String orderId;
    private Date date;
    private Integer breweryId;
    private List<OrderItem> orders;
    
    public Order() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(Integer breweryId) {
        this.breweryId = breweryId;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }

    public String toJson() {
        JsonArrayBuilder ordersBuilder = Json.createArrayBuilder();
        for (OrderItem orderItem : orders) {
            JsonObjectBuilder objBuilder = Json.createObjectBuilder()
                .add("beerId", orderItem.getBeerId())
                .add("quantity", orderItem.getQuantity());
            ordersBuilder.add(objBuilder);
        }

        JsonObject json = Json.createObjectBuilder()
            .add("orderId", orderId)
            .add("date", date.toString())
            .add("breweryId", breweryId)
            .add("orders", ordersBuilder)
            .build();

        return json.toString();
    }
    
}
