package com.ct.shop.order.feign;

import com.ct.shop.bean.Product;
import com.ct.shop.utils.resp.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("server-product")
public interface ProductService {
    @GetMapping("/product/get/{pid}")
    Product getProduct(@PathVariable("pid") Long pid);

    @GetMapping("/product/update_count/{pid}/{count}")
    ResponseResult<Integer> updateCount(@PathVariable("pid") Long pid, @PathVariable("count") Integer count);
}
