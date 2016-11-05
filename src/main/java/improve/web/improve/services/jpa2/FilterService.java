package improve.web.improve.services.jpa2;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.support.Expressions;
import com.mysema.query.types.Expression;
import com.mysema.query.types.Path;
import com.mysema.query.types.Projections;
import com.mysema.query.types.expr.SimpleExpression;
import improve.web.improve.ConstantReistry;
import improve.web.improve.jsf.view.dto.Dsl;
import improve.web.improve.model.QCatEntity;
import improve.web.improve.model.QProdEntity;
import improve.web.improve.services.tomcat.EntityManagerContext;
import javafx.util.Pair;
import lombok.extern.java.Log;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.arraycopy;

/**
 * Created by uev on 24.09.16.
 */

@ManagedBean(name = "filterService")
@ApplicationScoped
@Log
public class FilterService {

    private final Map<String,SimpleExpression> filterArgs = new HashMap<String, SimpleExpression>() {{
        put(ConstantReistry.CATALOG_PRICE_OF,QProdEntity.prodEntity.price);
        put(ConstantReistry.CATALOG_PRICE_TO, QProdEntity.prodEntity.price);
        put(ConstantReistry.CATEGORY_NAME, QCatEntity.catEntity.name);
        put(ConstantReistry.PRODUCT_NAME, QProdEntity.prodEntity.name);
        put(ConstantReistry.PRODUCT_ID,QProdEntity.prodEntity.id);
    }};


    public JPQLQuery getJPQLQuery() {
        return EntityManagerContext.getJPQL();
    }

    private Pair getListQuery(Object... params) {
        QProdEntity prodEntity = QProdEntity.prodEntity;
        QCatEntity catEntity = QCatEntity.catEntity;
        BooleanBuilder builder = new BooleanBuilder();
        JPQLQuery jpql = getJPQLQuery();
        jpql.from(prodEntity).join(prodEntity.categoryFk, catEntity);
        Map<String, String> args = (Map<String, String>) params[0];
        SimpleExpression[] queryParams = new SimpleExpression[args.size()];
        if (params.length > 0) {
            int index = 0;
            for (Map.Entry<String,SimpleExpression> entry: filterArgs.entrySet()) {
                if (args.containsKey(entry.getKey())) {
                    // сохраняем параметр запроса
                    queryParams[index] = entry.getValue();
                    index += 1;
                    Object compValue = args.get(entry.getKey());
                    if (compValue instanceof String && ((String) compValue).isEmpty()) {
                        builder.and(entry.getValue().eq(compValue));
                    }
                }
            }
            jpql.where(builder);
        }
        return new Pair<JPQLQuery, Expression[]>(jpql, queryParams);
    }



    public List<?> getList(Object... params) {
        // установим формат ответа функции
        assert(params.length == 2);
        // пара : запрос, параметры
        Pair<JPQLQuery,Object[]> query = getListQuery(params[1]);
        Class cl = (Class) params[0];
        List<?> res;
        JPQLQuery jpql = query.getKey();
        if (cl != null && cl.getName().contains("DTO")) {
            // временно создадим массив Expression по числу полей в DTO
            Field[] fields = cl.getDeclaredFields();
            Expression[] queryParamsTemp =  new Expression[fields.length];
            // получаем значения аннотаций
            int index = 0;
            for (Field field: fields) {
                if (field.isAnnotationPresent(Dsl.class)) {
                    Dsl annotation = field.getAnnotation(Dsl.class);
                    queryParamsTemp[index] = filterArgs.get(annotation.value());
                    index += 1;
                }
            }
            // не все элементы могут быть проаннатированы
            Expression[] queryParams =  new Expression[index];
            arraycopy(queryParamsTemp, 0, queryParams,0 , index);
            // выполняем запрос
            res = jpql.list(Projections.bean(cl, queryParams));
        } else {
            // формируем имя переменной для querydsl
            assert (cl != null);
            char[] value = cl.getSimpleName().toCharArray();
            value[0] = Character.toLowerCase(value[0]);
            Path<?> path = Expressions.path(cl, String.copyValueOf(value));
            res = jpql.list(path);
        }
        log.info("Featched " + res.size() + " records");
        return res;
    }
}