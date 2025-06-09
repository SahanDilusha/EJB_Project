package com.bid.bidsystem.beans;

import jakarta.ejb.Stateless;
import org.hibernate.Session;
import com.bid.bidsystem.entities.ProductCategory;
import com.bid.bidsystem.util.HibernateUtil;

@Stateless
public class ProductCategoryServiceBean {

    public ProductCategory registerCategory(String name) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            ProductCategory productCategory = new ProductCategory();
            productCategory.setName(name);
            int id = (int) session.save(productCategory);
            session.beginTransaction().commit();
            productCategory.setId(id);
            session.close();
            return productCategory;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

    public ProductCategory getProductCategory(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(ProductCategory.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }
}
