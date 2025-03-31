package korit.com.make_fitness.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class SalesDto {
    private Date date;
    private int totalSales;
    private int ptSales;
    private int pilatesSales;
    private int healthSales;
}
