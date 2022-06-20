package com.salapp.ecommerce.api.rest.composite;

import com.salapp.ecommerce.api.rest.product.ProductRestController;
import com.salapp.ecommerce.api.rest.user.UserRestController;

public interface EcommerceApi extends ProductRestController, UserRestController {

}
