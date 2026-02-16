INSERT INTO customers (
    id,
    first_name,
    last_name,
    customer_id,
    nationality_id,
    gender_id,
    identity_document,
    identity_document_number,
    identity_document_type_id
) VALUES
-- ===============================
-- CLIENTES COLOMBIA (CC)
-- ===============================
(1, 'Juan',   'Perez',     'CUST001', 57, 1, 'CC', '10000001', 1),
(2, 'Maria',  'Gomez',     'CUST002', 57, 2, 'CC', '10000002', 1),
(3, 'Carlos', 'Ramirez',   'CUST003', 57, 1, 'CC', '10000003', 1),
(4, 'Laura',  'Martinez',  'CUST004', 57, 2, 'CC', '10000004', 1),
(5, 'Andres', 'Lopez',     'CUST005', 57, 1, 'CC', '10000005', 1),

-- ===============================
-- CLIENTES EXTRANJEROS (PASAPORTE)
-- ===============================
(6, 'John',   'Smith',     'CUST006', 1,  1, 'PASSPORT', 'A1234567', 2),
(7, 'Anna',   'Brown',     'CUST007', 44, 2, 'PASSPORT', 'B7654321', 2),
(8, 'Pierre', 'Dupont',    'CUST008', 33, 1, 'PASSPORT', 'C9988776', 2),
(9, 'Sofia',  'Rossi',     'CUST009', 39, 2, 'PASSPORT', 'D5544332', 2),

-- ===============================
-- TARJETA DE IDENTIDAD (TI)
-- ===============================
(10, 'Miguel', 'Torres',   'CUST010', 57, 1, 'TI', '90000001', 3),
(11, 'Daniela','Castro',   'CUST011', 57, 2, 'TI', '90000002', 3),
(12, 'Felipe', 'Herrera',  'CUST012', 57, 1, 'TI', '90000003', 3),

-- ===============================
-- CÉDULA DE EXTRANJERÍA (CE)
-- ===============================
(13, 'Luis',   'Mendoza',  'CUST013', 52, 1, 'CE', 'E12345', 4),
(14, 'Paula',  'Silva',    'CUST014', 55, 2, 'CE', 'E54321', 4),

-- ===============================
-- CASOS ADICIONALES
-- ===============================
(15, 'Ricardo', 'Alvarez', 'CUST015', 57, 1, 'CC', '10000006', 1),
(16, 'Camila',  'Suarez',  'CUST016', 57, 2, 'CC', '10000007', 1),
(17, 'Jorge',   'Castillo','CUST017', 57, 1, 'CC', '10000008', 1),
(18, 'Valentina','Ortiz',  'CUST018', 57, 2, 'CC', '10000009', 1),
(19, 'Sebastian','Vega',   'CUST019', 57, 1, 'CC', '10000010', 1),
(20, 'Natalia', 'Reyes',   'CUST020', 57, 2, 'CC', '10000011', 1);
