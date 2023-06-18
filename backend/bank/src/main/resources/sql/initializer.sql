DO
$$
BEGIN
    IF
EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'roles' AND table_schema = 'public') THEN
        INSERT INTO roles (name)
SELECT *
FROM (VALUES ('ADMIN'), ('USER')) AS v(name)
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
EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'users' AND table_schema = 'public') THEN
        INSERT INTO users (account_number, address, email, is_verified, money, name, nid, password, username)
SELECT *
FROM (VALUES (1234567890, 'Dhaka', 'smalhasib0@gmail.com', true, 1000, 'Organization', 1234567890, '$2a$10$xxtRhgkC3MpzeeBh6nl4QevzTPW1C4PUbv9Wr09OGlKyBVCV33uMG', 'org')) AS v(account_number, address, email, is_verified, money, name, nid, password, username)
WHERE NOT EXISTS (SELECT 1
                  FROM users
                  WHERE name = v.name);
        INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);
END IF;
END
$$;