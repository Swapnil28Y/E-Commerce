package com.product.Service;

import com.product.exceptions.CartException;
import com.product.exceptions.LoginException;
import com.product.exceptions.OrderException;
import com.product.exceptions.ProductException;
import com.product.model.Orders;

public interface OrderService {
	public Orders registerOrder(Orders order, String key)
			throws OrderException, CartException, LoginException, ProductException;

	public Orders updateOrder(Orders order, String key) throws OrderException, LoginException;

	public Orders removeOrder(Integer oriderId, String key) throws OrderException, LoginException;

	public Orders viewOrder(Integer orderId, String key) throws OrderException, LoginException;
}
