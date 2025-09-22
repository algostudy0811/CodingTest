-- SELECT
--     ID,
--     EMAIL
-- FROM(
--     SELECT
--         ID,
--         EMAIL,
--         ROW_NUMBER() OVER(PARTITION BY EMAIL ORDER BY EMAIL) AS RN
--     FROM Person
-- ) AS BASE
-- WHERE RN = 1

-- delete selfjoin
DELETE 
    p1 
FROM Person p1, Person p2
WHERE p1.email = p2.email AND p1.id > p2.id;