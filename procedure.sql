DROP PROCEDURE IF EXISTS produto;

DELIMITER $$

CREATE PROCEDURE produto(
    IN product_id INT
)
BEGIN
    SELECT * FROM produtos WHERE id_produto = product_id;
END$$

DELIMITER ;

-- Chamar o procedimento
-- CALL produto(1);
