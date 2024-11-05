package ra.business;

import ra.entity.Categories;
import ra.entity.Products;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsBusiness {
    public static List<Products> findAllProducts() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Products> listProducts = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_find_all_product()}");
            ResultSet rs = callSt.executeQuery();
            listProducts = new ArrayList<>();
            while (rs.next()) {
                Products product = new Products();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setStock(rs.getInt("stock"));
                product.setCostPrice(rs.getDouble("cost_price"));
                product.setSellingPrice(rs.getDouble("selling_price"));
                product.setCreatedAt(rs.getDate("created_at"));
                product.setCategoryId(rs.getInt("category_id"));
                listProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listProducts;
    }

    public static boolean addProduct(Products product) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_create_product(?, ?, ?, ?, ?)}");
            callSt.setString(1, product.getProductName());
            callSt.setInt(2, product.getStock());
            callSt.setDouble(3, product.getCostPrice());
            callSt.setDouble(4, product.getSellingPrice());
            callSt.setInt(5, product.getCategoryId());
            callSt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean updateProduct(Products product) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_update_product(?, ?, ?, ?, ?, ?, ?)}");
            callSt.setInt(1, product.getProductId());
            callSt.setString(2, product.getProductName());
            callSt.setInt(3, product.getStock());
            callSt.setDouble(4, product.getCostPrice());
            callSt.setDouble(5, product.getSellingPrice());
            callSt.setDate(6, product.getCreatedAt());
            callSt.setInt(7, product.getCategoryId());
            callSt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static Products findById(Products productId) {
        Connection conn = null;
        CallableStatement callSt = null;
        Categories catalog = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_find_products_by_id(?)}");
            //callSt.setInt(1, productId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                productId = new Products();
                productId.setProductId(rs.getInt("product_id"));
                productId.setProductName(rs.getString("product_name"));
                productId.setStock(rs.getInt("stock"));
                productId.setCostPrice(rs.getDouble("cost_price"));
                productId.setSellingPrice(rs.getDouble("selling_price"));
                productId.setCreatedAt(rs.getDate("created_at"));
                productId.setCategoryId(rs.getInt("category_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return productId;
    }
}
