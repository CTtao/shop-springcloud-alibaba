package com.ct.shop.product.service;

import com.ct.shop.bean.Product;

/**
 * @author Tao
 * @description 商品service接口
 */
public interface ProductService {
    Product getProductById(Long pid);

    int updateProductStockById(Integer count, Long id);
}
