package com.ct.shop.product.service.impl;

import com.ct.shop.bean.Product;
import com.ct.shop.product.mapper.ProductMapper;
import com.ct.shop.product.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Tao
 * @description 商品service实现类
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Override
    public Product getProductById(Long pid) {
        return productMapper.selectById(pid);
    }

    @Override
    public int updateProductStockById(Integer count, Long id) {
        return productMapper.updateProductStockById(count,id);
    }
}
