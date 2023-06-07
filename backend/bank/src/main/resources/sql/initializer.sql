INSERT INTO roles (id, name)
SELECT *
FROM (VALUES (1, 'ADMIN'), (2, 'USER')) AS v(id, name)
WHERE NOT EXISTS (SELECT 1
                  FROM roles
                  WHERE id = v.id AND name = v.name);
