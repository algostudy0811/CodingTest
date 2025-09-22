# left join을 할경우 bonus에 없는 아이디는 null
# bonus가 null인 경우도 출력해야함
select e.name as "name", b.bonus as "bonus"
from Employee e
left join (
    select *
    from Bonus
) as b
on e.empId = b.empId
where bonus < 1000 or bonus is null
