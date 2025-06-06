package com.popcorntech.bidsystem.beans;

import jakarta.ejb.Stateless;
import org.hibernate.Session;
import com.popcorntech.bidsystem.entities.BidStatus;
import com.popcorntech.bidsystem.entities.Product;
import com.popcorntech.bidsystem.entities.ProductCategory;
import com.popcorntech.bidsystem.util.HibernateUtil;

@Stateless
public class ProductsServiceBean {

    public Product registerProduct(String productName, String productDescription, double basePrice, ProductCategory productCategory, int quantity, BidStatus bidStatus) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Product product = new Product();
            product.setName(productName);
            product.setDescription(productDescription);
            product.setBasePrice(basePrice);
            product.setProductCategory(productCategory);
            product.setQuantity(quantity);
            product.setBidStatus(bidStatus);
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

    public Product getById(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Product.class, id);
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
