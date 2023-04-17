package net.the42null.personalwebsite.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DtoOrder {
    private String customerName;
    private LocalDateTime purchaseDate;
    private String purchaseOrderNumber;
    private String productName;
    private String terms;
    private LocalDateTime shippedDate;
    private double productCost;

}
