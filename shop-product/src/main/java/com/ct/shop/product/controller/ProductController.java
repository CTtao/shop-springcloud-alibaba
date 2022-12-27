package com.ct.shop.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.ct.shop.bean.Product;
import com.ct.shop.product.service.ProductService;
import com.ct.shop.utils.constants.HttpCode;
import com.ct.shop.utils.resp.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Tao
 * @description 商品controller
 */
@RestController
@Slf4j
public class ProductController {
    @Resource
    private ProductService productService;

    @GetMapping("/get/{pid}")
    public Product getProduct(@PathVariable("pid") Long pid){
        Product product = productService.getProductById(pid);
        log.info("获取到的商品信息为:{}", JSONObject.toJSONString(product));
        return product;
    }

    @GetMapping("/update_count/{pid}/{count}")
    public ResponseResult<Integer> updateCount(@PathVariable("pid") Long pid,@PathVariable("count") Integer count){
        log.info("更新商品库存传递的参数为: 商品id:{}, 购买数量:{} ", pid, count);
        int updateCount = productService.updateProductStockById(count, pid);
        ResponseResult<Integer> result = new ResponseResult<>(HttpCode.SUCCESS, "执行成功", updateCount);
        return result;
    }
}
