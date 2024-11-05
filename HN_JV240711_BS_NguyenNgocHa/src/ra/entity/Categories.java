package ra.entity;

import java.util.Scanner;

public class Categories implements IStoreManagement{
    private int categoryId;
    private String categoryName;
    private boolean categoryStatus;

    public Categories() {
    }

    public Categories(int categoryId, String categoryName, boolean categoryStatus) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.print("Nhập tên danh mục: ");
        this.categoryName = scanner.nextLine();
    }

    @Override
    public void displayData() {
        System.out.println("Mã danh mục: " + this.categoryId + " - Tên danh mục: " + this.categoryName + " - Trạng thái: " + (this.categoryStatus ? "Chưa xóa" : "Đã xóa"));
    }
}
