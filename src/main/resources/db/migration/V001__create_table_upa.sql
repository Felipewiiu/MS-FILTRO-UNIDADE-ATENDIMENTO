
CREATE TABLE upa
(
    upa_id            SERIAL PRIMARY KEY,
    name          VARCHAR(255),
    capacity      INT,
    street        VARCHAR(255),
    city          VARCHAR(255),
    state         INT,
    zip_code      VARCHAR(9),
    latitude      DOUBLE PRECISION,
    longitude     DOUBLE PRECISION,
    capacity_used INT
);

INSERT INTO upa (name, capacity, street, city, state, zip_code, latitude, longitude, capacity_used)
VALUES ('UPA Botafogo', 500, 'Rua das Flores', 'Rio de Janeiro', 18, '22021-000', -22.949912, -43.184591, 100),
       ('UPA Tijuca', 400, 'Rua Conde de Bonfim', 'Rio de Janeiro', 18, '20520-050', -22.922985374451635,
        -43.230619511439315, 350),
       ('UPA Madureira', 300, 'Rua Carolina Machado', 'Rio de Janeiro', 18, '21351-150', -22.882151326795704,
        -43.3446956719987, 90),
       ('UPA Campo Grande', 600, 'Avenida Cesário de Melo', 'Rio de Janeiro', 18, '23080-090', -22.90690417153584,
        -43.5744062970705, 75),
       ('UPA Niterói', 450, 'Rua Dr. Paulo Alves', 'Niterói', 18, '24020-080', -22.90088112295905, -43.10905419772469,
        120),
       ('UPA Vila Mariana', 550, 'Rua Domingos de Morais', 'São Paulo', 24, '04010-000', -23.59599584651218,
        -46.64286249747275, 255),
       ('UPA Santana', 350, 'Avenida Luiz Dumont Villares', 'São Paulo', 24, '02036-050', -25.102328667553415,
        -50.16088550287072, 15),
       ('UPA Itaquera', 700, 'Rua Augusto Carlos Bauman', 'São Paulo', 24, '08295-000', -23.54403907200052,
        -46.463635602905671, 630),
       ('UPA Pinheiros', 400, 'Rua Teodoro Sampaio', 'São Paulo', 24, '05406-050', -23.612260756594583,
        -46.77972539030136, 320),
       ('UPA Osasco', 500, 'Rua Antonio Agú', 'Osasco', 24, '06010-010', -23.530937342286638, -46.776423035050364, 123),
       ('UPA Barreiro', 600, 'R. Aurélio Lopes, 20 - Diamante', 'Belo Horizonte', 12, '30626-002', -19.995957828043185,
        -44.020133377910554, 330),
       ('UPA Contagem', 450, 'Rua Maria da Conceição', 'Belo Horizonte', 12, '31845-425', -19.935601200283656,
        -44.03846990166966, 89),
       ('Upa Norte', 500, 'Av. Risoleta Neves, 2580', 'Belo Horizonte', 12, '32600-000', -19.9357151062414,
        -44.20175893715252, 200),
       ('UPA Santa Luzia', 400, 'Rua Halfeld', 'Juiz de Fora', 12, '36016-000', -21.7873272244316, -43.354345884443234,
        100),
       ('UPA 24 Horas - Ipatinga', 550, 'Avenida Rondon Pacheco', 'Uberlândia', 12, '38400-000', -19.446764576503135,
        -42.553881517380674, 540);