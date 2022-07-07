package com.micropos.products.repository;


import com.micropos.products.model.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

@Primary
@Repository
public class mysql implements ProductRepository {
    private List<Product> products = null;

    @Cacheable(value="products")
    @Override
    public List<Product> allProducts(){
        if (products == null)
            products = parseMysql();
        return products;
    }

    @Cacheable(value="products",key = "#p0")
    @Override
    public Product findProduct(String productId){
        for (Product p : allProducts()) {
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Product> searchProducts(String name) throws Exception {
        try {
            List<Product> list = new ArrayList<>();
            String myDriver = "com.mysql.cj.jdbc.Driver";  ;
            String myUrl = "jdbc:mysql://localhost:8090/sys";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "root");

            Statement st = conn.createStatement();
            String sql = "select * from products where title like '% "+name +"%';";
            ResultSet rs = st.executeQuery(sql);
            Random rand = new Random();
            while(rs.next()) {
                String main_cat = rs.getString("main_cat");
                String title = rs.getString("title");
                String asin = rs.getString("asin");
                String category = rs.getString("category");
                List<String> categorys = new ArrayList<String>(Arrays.asList(category.split(",")));
                String imageURLHighRes = rs.getString("imageURLHighRes");
                List<String> images = new ArrayList<String>(Arrays.asList(imageURLHighRes.split(",")));
                Product product = new Product(asin,title,rand.nextDouble()*100,images.get(0));
                list.add(product);
            }
            conn.close();
            return list;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static List<Product> parseMysql(){
        try {
            List<Product> list = new ArrayList<>();
            String myDriver = "com.mysql.cj.jdbc.Driver";  ;
            String myUrl = "jdbc:mysql://localhost:8090/sys";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "root");

            Statement st = conn.createStatement();
            String sql = "SELECT * FROM products LIMIT 50;";
            ResultSet rs = st.executeQuery(sql);
            Random rand = new Random();
            while(rs.next()) {
                String main_cat = rs.getString("main_cat");
                String title = rs.getString("title");
                String asin = rs.getString("asin");
                String category = rs.getString("category");
                List<String> categorys = new ArrayList<String>(Arrays.asList(category.split(",")));
                String imageURLHighRes = rs.getString("imageURLHighRes");
                List<String> images = new ArrayList<String>(Arrays.asList(imageURLHighRes.split(",")));
                Product product = new Product(asin,title,rand.nextDouble()*100,images.get(0));
                list.add(product);
            }
            conn.close();
            return list;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
