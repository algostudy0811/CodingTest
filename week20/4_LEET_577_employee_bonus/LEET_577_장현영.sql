# Write your MySQL query statement below

-- Write a solution to report the name and bonus amount of each employee with a bonus less than 1000.

-- Return the result table in any order.

-- The result format is in the following example.
-- bonus가 1000보다 작거나 null인 경우 출력하기

SELECT
    name,
    bonus
FROM EMPLOYEE
LEFT JOIN (
    SELECT
        empId,
        bonus
    FROM BONUS
) as B
USING(empId)
WHERE bonus < 1000 OR bonus is null