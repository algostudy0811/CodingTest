# Write your MySQL query statement below
# that will, for each user, return the number of followers. v
#  table ordered by user_id in ascending order. v
# 
SELECT
    user_id,
    count(user_id) as followers_count
FROM FOLLOWERS
GROUP BY user_id
ORDER BY user_id;