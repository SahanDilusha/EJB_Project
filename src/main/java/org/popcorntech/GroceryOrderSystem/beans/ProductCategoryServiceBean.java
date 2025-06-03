package org.popcorntech.GroceryOrderSystem.beans;

import jakarta.ejb.Stateless;
import org.hibernate.Session;
import org.popcorntech.GroceryOrderSystem.entities.ProductCategory;
import org.popcorntech.GroceryOrderSystem.entities.User;
import org.popcorntech.GroceryOrderSystem.util.HibernateUtil;

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
            return productCategory;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
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
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }


}
