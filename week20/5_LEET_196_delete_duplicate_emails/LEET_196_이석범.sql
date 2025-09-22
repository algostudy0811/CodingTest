# p1과 p2의 이메일이 같고 p1의 아이디가 p2의 아이디보다 크면 삭제 
delete p1
from Person p1, Person p2
where p1.email = p2.email
and p1.id > p2.id
