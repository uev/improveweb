package improve.web.improve.jsf;

import com.mysema.query.Tuple;
import improve.web.improve.ConstantReistry;
import improve.web.improve.jsf.view.dto.IndexDTO;
import improve.web.improve.services.jpa2.CatService;
import lombok.Data;
import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by uev on 24.09.16.
 */
@ManagedBean(name = "indexView")
@Data
@ViewScoped
public class IndexController implements Serializable {
    private String category;
    private String product;
    private Double priceOf;
    private Double priceTo;

    public List<IndexDTO> dataTable;


    @ManagedProperty("#{categoryService}")
    private CatService categoryService;

    public void setCategoryService(CatService service) {
        if (categoryService == null) {
            categoryService = service;
        }
    }

    public List<IndexDTO> getList() throws IllegalAccessException {
        Map<String,Object> filter = new HashMap<String,Object>(){{
            put(ConstantReistry.CATEGORY_NAME, category);
            put(ConstantReistry.CATALOG_PRICE_OF, priceOf);
            put(ConstantReistry.CATALOG_PRICE_TO, priceTo);
            put(ConstantReistry.PRODUCT_NAME, product);
        }};
        dataTable = categoryService.getList(filter);
        return dataTable;
    }
}