package ibf2024.assessment.paf.batch4.services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import ibf2024.assessment.paf.batch4.models.Order;
import ibf2024.assessment.paf.batch4.models.OrderItem;
import ibf2024.assessment.paf.batch4.repositories.OrderRepository;

@Service
public class BeerService {

	@Autowired
	private OrderRepository orderRepo;

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public String placeOrder(Integer breweryId, MultiValueMap<String,String> mvm) {
		// Task 5
		Order order = new Order();

		String orderId = UUID.randomUUID().toString().substring(0, 8);

		List<OrderItem> items = new LinkedList<>();
		for (int i = 0; i < mvm.get("beerId").size(); i++) {
			if (!"".equals(mvm.get("quantity").get(i))) {
				OrderItem item = new OrderItem();
				item.setBeerId(Integer.parseInt(mvm.get("beerId").get(i))); 
				item.setQuantity(Integer.parseInt(mvm.get("quantity").get(i)));
				items.add(item);
			}
		}

		order.setOrderId(orderId);
		order.setDate(new Date());
		order.setBreweryId(breweryId);
		order.setOrders(items);

		orderRepo.insertOrder(order);

		return orderId;
	}

}