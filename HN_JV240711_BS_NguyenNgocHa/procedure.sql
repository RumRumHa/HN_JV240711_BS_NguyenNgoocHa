DELIMITER &&
create procedure proc_find_all_categories()
BEGIN
    select c.category_id, c.category_name, c.category_status
    from categories c
    where category_status = 1;
END &&
DELIMITER &&;

DELIMITER &&
create procedure proc_create_category(
    category_name_in varchar(50)
)
BEGIN
    insert into Categories (category_name)
    values (category_name_in);
END &&
DELIMITER &&;

DELIMITER &&
create procedure proc_update_category(
    category_id_in int,
    category_name_in varchar(50)
)
BEGIN
    update Categories
    set category_name = category_name_in
    where category_id = category_id_in;
END &&
DELIMITER &&;

DELIMITER &&
create procedure proc_delete_category(
    category_id_in int
)
BEGIN
    update Categories
    set category_status = 0
    where category_id = category_id_in;
END &&
DELIMITER &&;

DELIMITER &&
create procedure proc_count_product_by_category(
    cat_id_in int,
    OUT cnt_product int
)
BEGIN
    set cnt_product = (select count(p.product_id)
                       from Categories c
                                join Products P on c.category_id = P.category_id
                       where c.category_id = cat_id_in);
END &&
DELIMITER &&;

DELIMITER &&
create procedure proc_find_all_product()
BEGIN
    select p.product_id, p.product_name, p.stock, p.cost_price, p.selling_price, p.created_at, p.category_id
    from products p;
END &&
DELIMITER &&;

DELIMITER &&
CREATE PROCEDURE proc_find_categories_by_id(
    cat_id_in int
)
BEGIN
    select c.category_id, c.category_name, c.category_status
    from Categories c
    where c.category_id = cat_id_in;
END &&
DELIMITER &&;

DELIMITER &&
create procedure proc_create_product(
    product_name_in varchar(20),
    stock_in int,
    cost_price_in double,
    selling_price_in double,
    category_id_in int
)
BEGIN
    insert into Products (product_name, stock, cost_price, selling_price, category_id)
    values (product_name_in, stock_in, cost_price_in, selling_price_in, category_id_in);
END &&
DELIMITER &&;

DELIMITER &&
create procedure proc_update_product(
    product_id_in int,
    product_name_in varchar(20),
    stock_in int,
    cost_price_in double,
    selling_price_in double,
    created_at_in datetime,
    category_id_in int
)
BEGIN
    update Products
    set product_name = product_name_in,
        stock = stock_in,
        cost_price = cost_price_in,
        selling_price = selling_price_in,
        created_at = created_at_in,
        category_id = category_id_in
    where product_id = product_id_in;
END &&
DELIMITER &&;

DELIMITER &&
CREATE PROCEDURE proc_find_products_by_id(
    product_id_in int
)
BEGIN
    select p.product_id, p.product_name, p.stock, p.cost_price, p.selling_price, p.created_at, p.category_id
    from Products p
    where p.product_id = product_id_in;
END &&
DELIMITER &&;

