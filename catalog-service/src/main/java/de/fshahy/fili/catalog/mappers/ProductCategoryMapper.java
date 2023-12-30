package de.fshahy.fili.catalog.mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fshahy.fili.catalog.models.ProductCategory;
import io.helidon.dbclient.DbColumn;
import io.helidon.dbclient.DbMapper;
import io.helidon.dbclient.DbRow;

public class ProductCategoryMapper implements DbMapper<ProductCategory> {

    @Override
    public ProductCategory read(DbRow row) {
        DbColumn id = row.column("id");
        DbColumn name = row.column("name");
        return new ProductCategory(id.get(Integer.class), name.get(String.class));
    }

    @Override
    public Map<String, ?> toNamedParameters(ProductCategory value) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("id", value.id());
        map.put("name", value.name());
        return map;
    }

    @Override
    public List<?> toIndexedParameters(ProductCategory value) {
        List<Object> list = new ArrayList<>(2);
        list.add(value.id());
        list.add(value.name());
        return list;
    }
    
}
