package com.ct.shop.order.feign.fallback;

import com.ct.shop.bean.Product;
import com.ct.shop.order.feign.ProductService;
import com.ct.shop.utils.resp.ResponseResult;
import org.springframework.stereotype.Component;

/**
 * @author CT
 * @description
 */
@Component
public class ProductServiceFallback implements ProductService {
    @Override
    public Product getProduct(Long pid) {
        Product product = new Product();
        product.setId(-1L);
        return product;
    }

    @Override
    public ResponseResult<Integer> updateCount(Long pid, Integer count) {
        ResponseResult<Integer> result = new ResponseResult<>();
        result.setCode(1001);
        result.setCodeMsg("触发容错");
        return result;
    }
}
