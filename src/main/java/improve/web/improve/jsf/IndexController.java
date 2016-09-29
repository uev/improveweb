package improve.web.improve.jsf;

import improve.web.improve.model.CatEntity;
import improve.web.improve.services.jpa2.CatService;
import improve.web.improve.services.tomcat.EntityManagerContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by uev on 24.09.16.
 */
@ManagedBean(name = "indexView")
@ViewScoped
public class IndexController {

    @ManagedProperty("#{categoryService}")
    private CatService categoryService;

    public List<CatEntity> getString() {
        return categoryService.getList();
    }

    public void setCategoryService(CatService service) {
        if (categoryService == null) {
            categoryService = service;
        }
    }
}