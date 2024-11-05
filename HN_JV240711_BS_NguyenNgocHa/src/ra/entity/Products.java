package ra.entity;

import java.util.Date;
import java.util.Scanner;

public class Products implements IStoreManagement{
    private int productId;
    private String productName;
    private int stock;
    private double costPrice;
    private double sellingPrice;
    private Date createdAt;
    private int categoryId;

    public Products() {
    }

    public Products(int productId, String productName, int stock, double costPrice, double sellingPrice, Date createdAt, int categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.stock = stock;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.createdAt = createdAt;
        this.categoryId = categoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public java.sql.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.println("Nhập tên sản phầm: ");
        this.productName = scanner.nextLine();
        System.out.println("Nhập số lượng sản phẩm: ");
        this.stock = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập giá nhập sản phẩm: ");
        this.costPrice = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhập giá bán sản phẩm: ");
        this.sellingPrice = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhập mã danh mục: ");
        this.categoryId = Integer.parseInt(scanner.nextLine());
    }

    @Override
    public void displayData() {
        System.out.println("Mã sản phẩm: " + this.productId + " - Tên sản phầm: " + this.productName + " - Số lượng sản phẩm: " + this.stock + " - Giá nhập sản phẩm: " + this.costPrice + " - Giá bán sản phẩm: " + this.sellingPrice + " - Ngày tạo: " + this.createdAt + " - Mã danh mục: " + this.categoryId);
    }
}
