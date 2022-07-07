package com.micropos.delivery.repository;


import com.micropos.delivery.model.Delivery;
import com.micropos.delivery.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Primary
@Repository
public class mysql implements DeliveryRepository {
    private List<Delivery> deliveries;

    @Cacheable(value="deliveries")
    public List<Delivery> allDeliveries(){
        deliveries = new ArrayList<>();
        return parseMysql();
    }

    @Cacheable(value="deliveries",key = "#p0")
    @Override
    public Delivery findDelivery(String id) {
        for(Delivery d : allDeliveries()) {
            if(d.getId().equals(id)) {
                return d;
            }
        }
        return null;
    }

    public void addDelivery(Delivery d) {
        if(findDelivery(d.getId()) != null) {
            return;
        }
        try {
            String myDriver = "com.mysql.cj.jdbc.Driver";  ;
            String myUrl = "jdbc:mysql://localhost:8090/sys";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "root");

            String sql = "insert into deliveries values(?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, d.getId());
            stmt.setInt(2, phaseIntStatus(d.getStatus()));

            stmt.executeUpdate();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Delivery> parseMysql(){
        try {
            List<Delivery> list = new ArrayList<>();
            String myDriver = "com.mysql.cj.jdbc.Driver";  ;
            String myUrl = "jdbc:mysql://localhost:8090/sys";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "root");

            Statement st = conn.createStatement();
            String sql = "SELECT * FROM deliveries;";
            ResultSet rs = st.executeQuery(sql);
            Random rand = new Random();
            while(rs.next()) {
                String id = rs.getString("id");
                int status = rs.getInt("status");
                Delivery d = new Delivery(id,phaseStatus(status));
                list.add(d);
            }
            conn.close();
            return list;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    public static Status phaseStatus(int i) {
        if(i == 1) {
            return Status.PREPARING;
        }else if (i == 2) {
            return Status.HALFWAY;
        }else if (i == 3) {
            return Status.FINISHED;
        }
        return null;
    }
    public static int phaseIntStatus(Status s) {
        if(s == Status.PREPARING) {
            return 1;
        }else if (s == Status.HALFWAY) {
            return 2;
        }else if (s == Status.FINISHED) {
            return 3;
        }
        return 0;
    }
}

