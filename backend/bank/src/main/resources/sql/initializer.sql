DO
$$
BEGIN
    IF
EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'roles' AND table_schema = 'public') THEN
        INSERT INTO roles (id, name)
SELECT *
FROM (VALUES (1, 'ADMIN'), (2, 'USER')) AS v(id, name)
WHERE NOT EXISTS (SELECT 1
                  FROM roles
                  WHERE id = v.id
                    AND name = v.name);
END IF;
END
$$;
