package edu.wctc.wholesale.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DtoOrder {
    private String customerName;
    private LocalDate purchaseDate;
    private String purchaseOrderNumber;
    private String productName;
    private String terms;
    private LocalDate shippedDate;
    private double productCost;

}
