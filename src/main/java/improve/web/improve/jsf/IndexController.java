package improve.web.improve.jsf;

import improve.web.improve.ConstantReistry;
import improve.web.improve.jsf.view.dto.IndexDTO;
import improve.web.improve.services.jpa2.FilterService;
import lombok.Data;

import javax.faces.bean.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by uev on 24.09.16.
 */
@ManagedBean(name = "indexView")
@Data
@ViewScoped
public class IndexController {
    private String category;
    private String product;
    private Double priceOf;
    private Double priceTo;

    public List<IndexDTO> dataTable;


    @ManagedProperty("#{filterService}")
    private FilterService filterService;

    public void setfilterService(FilterService service) {
        if (filterService == null) {
            filterService = service;
        }
    }

    public List<IndexDTO> getList() throws IllegalAccessException {
        Map<String,Object> filter = new HashMap<String,Object>(){{
            put(ConstantReistry.CATEGORY_NAME, category);
            put(ConstantReistry.CATALOG_PRICE_OF, priceOf);
            put(ConstantReistry.CATALOG_PRICE_TO, priceTo);
            put(ConstantReistry.PRODUCT_NAME, product);
        }};
        dataTable = (List<IndexDTO>) filterService.getList(IndexDTO.class,filter);
        return  dataTable;
    }
}