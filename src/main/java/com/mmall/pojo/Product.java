package com.mmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    private Integer id;

    private Integer categoryId;

    private String name;

    private String subtitle;

    private String mainImage;

    private String smallImages;

    private String bigImages;

    private String subImages;

    private String detail;

    private BigDecimal sprice;

    private BigDecimal price;

    private Integer stock;

    private String brand;

    private String weight;

    private String originCountry;

    private String itemDetail;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}