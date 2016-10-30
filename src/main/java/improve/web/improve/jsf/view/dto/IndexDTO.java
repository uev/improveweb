package improve.web.improve.jsf.view.dto;

import improve.web.improve.ConstantReistry;
import lombok.Data;

/**
 * Created by uev on 06.10.16.
 */
@Data
public class IndexDTO {
    @Dsl(value = ConstantReistry.PRODUCT_ID)
    public Long id;
    @Dsl(value = ConstantReistry.PRODUCT_NAME)
    public  String name;
    @Dsl(value = ConstantReistry.CATALOG_PRICE_OF)
    public  Double price;
}