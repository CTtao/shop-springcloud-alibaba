package com.ct.shop.filter;

import lombok.Data;

import java.io.Serializable;

/**
 * @author CT
 * @description
 */
@Data
public class GrayScaleGatewayFilterConfig implements Serializable {
    public static final long serialVersionUID = 983019309000445082L;
    private boolean grayscale;
}
