# Write your MySQL query statement below

# root : p_id가 null인 거
# inner : root빼고 p_id에 포함되어 있는거 
SELECT
    id,
    CASE
        WHEN p_id is null THEN "Root"
        WHEN id IN (SELECT DISTINCT p_id FROM Tree) THEN "Inner"
        ELSE "Leaf" 
    END AS type
FROM Tree