package ra.business;

import ra.entity.Categories;
import ra.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesBusiness {
    public static List<Categories> getAllCategories() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Categories> listCategories = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_find_all_categories()}");
            ResultSet rs = callSt.executeQuery();
            listCategories = new ArrayList<>();
            while (rs.next()) {
                Categories category = new Categories();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setCategoryStatus(rs.getBoolean("category_status"));
                listCategories.add(category);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCategories;
    }

    public static boolean addCategory(Categories category) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_create_category(?)}");
            callSt.setString(1, category.getCategoryName());
            callSt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean updateCategory(Categories category) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_update_category(?, ?)}");
            callSt.setInt(1, category.getCategoryId());
            callSt.setString(2, category.getCategoryName());
            callSt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static Categories findById(int catalogId) {
        Connection conn = null;
        CallableStatement callSt = null;
        Categories catalog = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_find_categories_by_id(?)}");
            callSt.setInt(1, catalogId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                catalog = new Categories();
                catalog.setCategoryId(rs.getInt("category_id"));
                catalog.setCategoryName(rs.getString("category_name"));
                catalog.setCategoryStatus(rs.getBoolean("category_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return catalog;
    }

    public static boolean deleteCategory(int categoryId) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_delete_category(?)}");
            callSt.setInt(1, categoryId);
            callSt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static int staticticCategories(int catalogId) {
        Connection conn = null;
        CallableStatement callSt = null;
        int cntCategories = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_count_product_by_category(?, ?)}");
            callSt.setInt(1, catalogId);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            cntCategories = callSt.getInt(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return cntCategories;
    }
}
