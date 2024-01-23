package de.fshahy.filimp.catalog.resources;

import java.util.List;

import de.fshahy.filimp.catalog.models.Product;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/catalog/products")
@RequestScoped
public class ProductResource {

    @PersistenceContext(unitName = "catalog-pu")
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAll() {
        return entityManager
                .createNamedQuery("Product.findAll", Product.class)
                .getResultList();
    }

}
