package ibf2024.assessment.paf.batch4.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2024.assessment.paf.batch4.models.Order;

@Repository
public class OrderRepository {

	@Autowired
	private MongoTemplate template;

	// Task 5
	public void insertOrder(Order order) {
		template.insert(order.toJson(), "orders");
	}

}
