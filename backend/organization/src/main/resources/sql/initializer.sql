DO
$$
    BEGIN
        IF
            EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'roles' AND table_schema = 'public') THEN
            INSERT INTO roles (name)
            SELECT *
            FROM (VALUES ('ADMIN'), ('USER'), ('SUPPLIER')) AS v(name)
            WHERE NOT EXISTS (SELECT 1
                              FROM roles
                              WHERE name = v.name);
        END IF;
    END
$$;

DO
$$
    BEGIN
        IF
            EXISTS (SELECT 1
                    FROM information_schema.tables
                    WHERE table_name = 'categories' AND table_schema = 'public') THEN
            INSERT INTO categories (name)
            SELECT *
            FROM (VALUES ('ELECTRONICS'),
                         ('FASHION'),
                         ('DOMESTIC'),
                         ('BEAUTY'),
                         ('HEALTH'),
                         ('BOOK'),
                         ('ENTERTAINMENT'),
                         ('AUTOMOTIVE'),
                         ('GAMES'),
                         ('SPORTS'),
                         ('JEWELRY'),
                         ('ACCESSORIES'),
                         ('ACADEMIC')) AS v(name)
            WHERE NOT EXISTS (SELECT 1
                              FROM categories
                              WHERE name = v.name);
        END IF;
    END
$$;
