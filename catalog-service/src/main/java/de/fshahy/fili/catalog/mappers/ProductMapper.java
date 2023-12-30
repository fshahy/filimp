package de.fshahy.fili.catalog.mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fshahy.fili.catalog.models.Product;
import io.helidon.dbclient.DbColumn;
import io.helidon.dbclient.DbMapper;
import io.helidon.dbclient.DbRow;

public class ProductMapper implements DbMapper<Product> {

    @Override
    public Product read(DbRow row) {
        DbColumn id = row.column("id");
        DbColumn name = row.column("name");
        DbColumn categoryId = row.column("category_id");
        return new Product(
            id.get(Integer.class),
            name.get(String.class),
            categoryId.get(Integer.class)
        );
    }

    @Override
    public Map<String, ?> toNamedParameters(Product value) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("id", value.id());
        map.put("name", value.name());
        map.put("category_id", value.categoryId());
        return map;
    }

    @Override
    public List<?> toIndexedParameters(Product value) {
        List<Object> list = new ArrayList<>(3);
        list.add(value.id());
        list.add(value.name());
        list.add(value.categoryId());
        return list;
    }
   
}
