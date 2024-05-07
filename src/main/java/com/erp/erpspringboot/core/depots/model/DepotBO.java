package com.erp.erpspringboot.core.depots.model;

import com.erp.erpspringboot.base.models.BaseBO;
import com.erp.erpspringboot.core.vendors.model.VendorBO;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/3/2024
 */

@Entity(name = "storages")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepotBO extends BaseBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber; // 货号
    private String category;
    private String batchId;
    private String traceId;
    //    到货属性
    private String specification;
    private String batch;

    private String brand;
    //    商品属性
    private Integer quantity;
    private Long weight;
    private Long boxPrice;
    private Long weightPrice;
    private Long subtotal;
    //    对外属性
    @OneToOne
    @JoinColumn(name = "vendor_id")
    private VendorBO vendor;

    @Column
    private boolean isDept;

    private String accountMethod;
    private String saleMethod;
    //    对内属性
//    private PurchasingAgentBO purchasingAgentBO;
//    private SalesAgentBO salesAgent;
//    private String carLicense;
    // 备注
    private String comment1;
    private String comment2;

}
