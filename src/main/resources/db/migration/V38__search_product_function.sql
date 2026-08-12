DROP FUNCTION IF EXISTS fn_search_product(p_sku VARCHAR, p_upc VARCHAR, p_searchName VARCHAR);
CREATE OR REPLACE FUNCTION fn_search_product(p_sku VARCHAR,
                        p_upc VARCHAR,
                        p_searchName VARCHAR)
RETURNS SETOF pos_system_products
AS $$
BEGIN

    RETURN QUERY
    SELECT *
    FROM pos_system_products
    WHERE (p_sku IS NULL OR p_sku = sku )
    AND (p_upc IS NULL OR p_upc = upc)
    AND (p_searchName IS NULL OR search_name LIKE '%'||p_searchName||'%');

END;
$$ LANGUAGE plpgsql;