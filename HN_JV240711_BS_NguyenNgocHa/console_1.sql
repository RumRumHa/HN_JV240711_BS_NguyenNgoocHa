DELIMITER &&
create procedure proc_find_add_categories()
BEGIN
    select c.category_id, c.category_name, c.category_status
        from categories c;
END &&
DELIMITER &&;