package com.ct.shop.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderParams {
    private Long userId;
    private Long productId;
    private Integer count;

    public boolean isEmpty() {
        return (productId == null || productId <= 0) ||
                (userId == null || userId <= 0) ||
                (count == null || count <= 0);
    }
}
