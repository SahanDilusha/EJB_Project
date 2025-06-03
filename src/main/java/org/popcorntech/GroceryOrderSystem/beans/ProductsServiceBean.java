package org.popcorntech.GroceryOrderSystem.beans;

import jakarta.ejb.Stateless;
import org.hibernate.Session;
import org.popcorntech.GroceryOrderSystem.entities.Product;
import org.popcorntech.GroceryOrderSystem.entities.ProductCategory;
import org.popcorntech.GroceryOrderSystem.util.HibernateUtil;

@Stateless
public class ProductsServiceBean {

    public Product registerProduct(String productName, String productDescription, double basePrice, ProductCategory productCategory,int quantity) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Product product = new Product();
            product.setName(productName);
            product.setDescription(productDescription);
            product.setBasePrice(basePrice);
            product.setProductCategory(productCategory);
            product.setQuantity(quantity);


            int id = (int) session.save(product);
            session.beginTransaction().commit();

            product.setId(id);

            return product;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }


}
