package korit.com.make_fitness.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class SalesDto {
    private Date date;
    private int totalAmount;
    private int ptTotalAmount;
    private int pltTotalAmount;
    private int htTotalAmount;
}
