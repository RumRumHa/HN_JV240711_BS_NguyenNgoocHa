package ra.presentation;

import ra.business.CategoriesBusiness;
import ra.business.ProductsBusiness;
import ra.entity.Categories;
import ra.entity.Products;

import java.util.List;
import java.util.Scanner;

public class StoreManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = true;
        do {
            System.out.println("******************STORE-MANAGEMENT******************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showCategoriesMenu(scanner);
                    break;
                case 2:
                    showProductMenu(scanner);
                    break;
                case 3:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-3");
            }
        } while (isExit);
    }
    private static void showCategoriesMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("**********************CATEGORY-MENU********************");
            System.out.println("1. Danh sách danh mục");
            System.out.println("2. Tạo mới danh mục");
            System.out.println("3. Cập nhật danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Thống kê số lượng sản phẩm theo danh mục");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayListCategories();
                    break;
                case 2:
                    createCategory(scanner);
                    break;
                case 3:
                    updateCategory(scanner);
                    break;
                case 4:
                    deleteCategory(scanner);
                    break;
                case 5:
                    staticticCategories(scanner);
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-6");
            }
        } while (isExit);
    }

    private static void showProductMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("************************PRODUCT-MENU********************");
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Tạo mới sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Hiển thị danh sách sản phẩm theo ngày tạo giảm dần");
            System.out.println("6. Tìm kiếm sản phẩm theo khoản giá bán");
            System.out.println("7. Hiển thị top 3 sản phẩm có lợi nhuận cao nhất");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayListProducts();
                    break;
                case 2:
                    createProduct(scanner);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-8");
            }
        } while (isExit);
    }

    public static void displayListCategories() {
        List<Categories> listCategories = CategoriesBusiness.getAllCategories();
        listCategories.forEach(Categories::displayData);
    }

    public static void createCategory(Scanner scanner) {
        Categories category = new Categories();
        category.inputData(scanner);
        boolean result = CategoriesBusiness.addCategory(category);
        if (result) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Thêm mới thất bại");
        }
    }

    public static void updateCategory(Scanner scanner) {
        System.out.println("Nhập mã danh mục cần cập nhật: ");
        int categoryId = Integer.parseInt(scanner.nextLine());
        Categories categoryUpdate = CategoriesBusiness.findById(categoryId);
        if (categoryUpdate != null) {
            categoryUpdate.inputData(scanner);
            boolean result = CategoriesBusiness.updateCategory(categoryUpdate);
            if (result) {
                System.out.println("Cập nhật thành công");
            } else {
                System.out.println("Cập nhật thất bại");
            }
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public static void deleteCategory(Scanner scanner) {
        System.out.println("Nhập mã danh mục cần xóa: ");
        int categoryId = Integer.parseInt(scanner.nextLine());
        Categories categoryDelete = CategoriesBusiness.findById(categoryId);
        if (categoryDelete != null) {
            boolean result = CategoriesBusiness.deleteCategory(categoryId);
            if (result) {
                System.out.println("Xóa thành công");
            } else {
                System.out.println("Xóa thất bại");
            }
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public static void staticticCategories(Scanner scanner) {
        System.out.println("Nhập mã danh mục cần thông kê: ");
        int categoryId = Integer.parseInt(scanner.nextLine());
        int cntProdutcts = CategoriesBusiness.staticticCategories(categoryId);
        System.out.printf("Có %d sản phẩm thuộc danh mục %d.\n", cntProdutcts, categoryId);
    }

    public static void displayListProducts() {
        List<Products> listProducts = ProductsBusiness.findAllProducts();
        listProducts.forEach(Products::displayData);
    }

    public static void createProduct(Scanner scanner) {
        Products product = new Products();
        product.inputData(scanner);
        boolean result = ProductsBusiness.addProduct(product);
        if (result) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Thêm mới thất bại");
        }
    }

//    public static void updateProduct(Scanner scanner) {
//        System.out.println("Nhập mã san pham cần cập nhật: ");
//        int productId = Integer.parseInt(scanner.nextLine());
//        Products productUpdate = ProductsBusiness.findById(productId);
//        if (productUpdate != null) {
//            productUpdate.inputData(scanner);
//            boolean result = CategoriesBusiness.updateCategory(productUpdate);
//            if (result) {
//                System.out.println("Cập nhật thành công");
//            } else {
//                System.out.println("Cập nhật thất bại");
//            }
//        } else {
//            System.err.println("Mã san pham không tồn tại");
//        }
//    }
}
