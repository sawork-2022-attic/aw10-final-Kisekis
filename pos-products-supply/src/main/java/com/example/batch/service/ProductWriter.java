package com.example.batch.service;

import com.example.batch.model.Product;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class ProductWriter implements ItemWriter<Product>, StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {

    }
    public static int count = 1;

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public void write(List<? extends Product> list) throws Exception {

        String myDriver = "com.mysql.cj.jdbc.Driver";  ;
        String myUrl = "jdbc:mysql://localhost:8090/sys";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "root");
        String sql = " insert into products (main_cat, title, asin, category, imageURLHighRes)"
                + " values (?, ?, ?, ?, ?)";
        for(Product p : list) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, p.getMain_cat());
            preparedStatement.setString(2, p.getTitle());
            preparedStatement.setString(3, p.getAsin());
            StringBuilder category = new StringBuilder();
            for(String s : p.getCategory()) {
                category.append(s).append(",");
                if(category.length()>1024) {
                    break;
                }
            }
            preparedStatement.setString(4, category.toString());

            StringBuilder imageURL = new StringBuilder();
            for(String s : p.getImageURLHighRes()) {
                imageURL.append(s).append(",");
                if(imageURL.length()>1024) {
                    break;
                }
            }
            preparedStatement.setString(5, imageURL.toString());
            try {
                preparedStatement.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        System.out.println(count + "chunk written");
        count++;
    }
}
