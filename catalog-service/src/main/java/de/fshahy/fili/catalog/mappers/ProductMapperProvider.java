package de.fshahy.fili.catalog.mappers;

import java.util.Optional;

import de.fshahy.fili.catalog.models.ProductCategory;
import io.helidon.common.Weight;
import io.helidon.dbclient.DbMapper;
import io.helidon.dbclient.spi.DbMapperProvider;

@Weight(100)
public class ProductMapperProvider implements DbMapperProvider {

    private static final ProductMapper MAPPER = new ProductMapper();

    @SuppressWarnings("unchecked")
    @Override
    public <T> Optional<DbMapper<T>> mapper(Class<T> type) {
        if (type.equals(ProductCategory.class)) {
            return Optional.of((DbMapper<T>) MAPPER);
        }
        return Optional.empty();
    }
    
}
