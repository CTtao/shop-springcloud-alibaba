package com.ct.shop.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ct.shop.bean.Product;
import org.apache.ibatis.annotations.Param;

/**
 * @author Tao
 * @description 商品服务Mapper接口
 */
public interface ProductMapper extends BaseMapper<Product> {
    /**
     * 扣减商品库存
     */
    int updateProductStockById(@Param("count") Integer count, @Param("id") Long id);
}
