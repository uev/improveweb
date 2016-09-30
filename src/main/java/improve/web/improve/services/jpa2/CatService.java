package improve.web.improve.services.jpa2;

import improve.web.improve.model.CatEntity;
import improve.web.improve.model.QCatEntity;
import improve.web.improve.services.tomcat.EntityManagerContext;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by uev on 24.09.16.
 */

@ManagedBean(name = "categoryService")
@ApplicationScoped
public class CatService {

    public List<CatEntity> getList() {
        QCatEntity catEntity = QCatEntity.catEntity;
        List<CatEntity> result = EntityManagerContext.getJPQL().from(catEntity).fetchAll().list(catEntity);
        return result;
    }
}
