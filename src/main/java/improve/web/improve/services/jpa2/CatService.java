package improve.web.improve.services.jpa2;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.JPQLQueryFactory;
import com.mysema.query.types.Projections;
import com.mysema.query.types.expr.SimpleExpression;
import improve.web.improve.ConstantReistry;
import improve.web.improve.jsf.view.dto.IndexDTO;
import improve.web.improve.model.ProdEntity;
import improve.web.improve.model.QCatEntity;
import improve.web.improve.model.QProdEntity;
import improve.web.improve.services.tomcat.EntityManagerContext;
import lombok.extern.java.Log;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by uev on 24.09.16.
 */

@ManagedBean(name = "categoryService")
@ApplicationScoped
@Log
public class CatService {

    public JPQLQuery getJPQLQuery() {
        return EntityManagerContext.getJPQL();
    }

    public List<IndexDTO> getList(Object... params) {
        final QCatEntity catEntity = QCatEntity.catEntity;
        final QProdEntity prodEntity = QProdEntity.prodEntity;
        Map<String,SimpleExpression> filterArgs = new HashMap<String, SimpleExpression>() {{
            put(ConstantReistry.CATALOG_PRICE_OF,prodEntity.price);
            put(ConstantReistry.CATALOG_PRICE_TO, prodEntity.price);
            put(ConstantReistry.CATEGORY_NAME, catEntity.name);
            put(ConstantReistry.PRODUCT_NAME, prodEntity.name);
        }};
        BooleanBuilder builder = new BooleanBuilder();
        JPQLQuery jpql = getJPQLQuery();
        jpql.from(prodEntity).join(prodEntity.categoryFk, catEntity);


        if (params.length > 0) {
            Map<String, String> args = (Map<String, String>) params[0];
            for (Map.Entry<String,SimpleExpression> entry: filterArgs.entrySet()) {
                if (args.containsKey(entry.getKey())) {
                    Object compValue = args.get(entry.getKey());
                    if (compValue != null && compValue != "") {
                        builder.and(entry.getValue().eq(compValue));
                    }
                }
            }
            jpql.where(builder);
        }
        List<IndexDTO> dlst = jpql.list(Projections.bean(IndexDTO.class, prodEntity.id, prodEntity.name, prodEntity.price));
        return dlst;
    }
}
