package de.fshahy.filimp.catalog.resources;

import java.util.List;

import de.fshahy.filimp.catalog.models.ProductCategory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/catalog/product_categories")
public class ProductCategoryResource {

    @PersistenceContext(unitName = "catalog-pu")
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductCategory> getAll() {
        return entityManager
                .createNamedQuery("ProductCategory.findAll", ProductCategory.class)
                .getResultList();
    }

}