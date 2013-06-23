select sid,sname,gpa
from student
where gpa > 3.6


select cname,sname,major
from student s,apply a
where s.sid = a.sid
order by cname ASC

select sname,gpa,decision
from student s, apply a
where s.sid = a.sid
and sizeHs < 1000
and major = 'CS'
and cname = 'Stanford'

select distinct c.cname
from college c, apply a
where c.cname = a.cname
and enrollment > 20000
and major = 'CS'

select sname,major
from apply a, student s
where a.sid = s.sid
and major like '%BIO%'

select sname,gpa,gpa*(sizehs/1000) as scaledGpa
from student
order by scaledgpa

select s1.sid,s1.sname,s2.sname
from student s1,student s2
where s1.gpa = s2.gpa
and s1.sid < s2.sid

select cname from college
union all
select sname from student
order by cname

--Intersect
select s.sid,sname from student s,apply a
where s.sid = a.sid and a.major = 'CS'
intersect
select s.sid,sname from student s,apply a
where s.sid = a.sid and a.major = 'EE'


--This became a cross product as there is no join for the student table
select s.sid,sname
from student s, apply a1, apply a2
where a1.sid = a2.sid
and a1.major = 'CS'
and a2.major = 'EE';

--The correct version using subqueries - same as intersect
select s.sid,sname
from student s
where sid in (select a1.sid from apply a1, apply a2
where a1.sid = a2.sid
and a1.major = 'CS'
and a2.major = 'EE'
);

--except: MINUS in oracle is same as Except in other DBs
select s.sid,sname from student s,apply a
where s.sid = a.sid and a.major = 'CS'
minus
select s.sid,sname from student s,apply a
where s.sid = a.sid and a.major = 'EE';

select sid from apply where major = 'CS'
MINUS
select sid from apply where major = 'EE'

select distinct s.sid, sname
from student s, apply a
where s.sid = a.sid
and major = 'CS'
and s.sid not in (select sid
from apply
where major = 'EE')


--DUPLICATES MATTERS
select sid,sname,gpa
from student
where sid in (select sid from apply where major = 'CS')


--where exists
select cname,state
from college c1
where exists (
select * from college c2 where c2.state = c1.state
and c2.cname <> c1.cname
)

--find the college with the largest enrollment
select cname from college c1
where not exists (select * from college c2 where c2.enrollment > c1.enrollment)

--student with highest GPA
select sname from student s1
where not exists (select * from student s2 where s2.gpa > s1.gpa)

--Using ALL
select sname from student
where gpa >= all(select gpa from student)

select sname,gpa
from student s1
where gpa >= all (select gpa from student s2 where s2.sid <> s1.sid)

select cname,enrollment
from college c1
where enrollment > all (
select enrollment from college c2
where c2.cname <> c1.cname)


--using any
--lists the college with highest enrollment
select cname, enrollment
from college c1
where not enrollment <= any (
select enrollment from college c2
where c2.cname <> c1.cname)

--omits the college with highest enrollment
select cname, enrollment
from college c1
where not enrollment <= any (
select enrollment from college c2
where c2.cname <> c1.cname)

select sname,sizehs from student
where sizehs > any (select sizehs from student)

--writing except using any
select sid,sname
from student
where sid = any (select sid from apply where major = 'CS')
and not sid = any (select sid from apply where major = 'EE')


--subqueries in from
select sid,sname,gpa,gpa*(sizehs/1000) as scaledgpa
from student
where abs(gpa - gpa*(sizehs/1000)) > 1.0

select *
from (select sid,sname,gpa,gpa*(sizehs/1000) as scaledgpa
from student)
where abs(gpa - scaledgpa) > 1.0


--subqueries in select
select distinct c.cname,state,gpa
from college c, student s, apply a
where c.cname = a.cname
and s.sid = a.sid
and gpa >= all (select gpa from student s1, apply a1
where a1.sid = s1.sid
and a1.cname = c.cname)

select cname,state,
(select distinct GPA
from student s, apply a
where c.cname = a.cname
and s.sid = a.sid
and gpa >= all (select gpa from student s1, apply a1
where a1.sid = s1.sid
and a1.cname = c.cname)) as GPA
from college c;



----------------JOINS--------------------------
--Natural join joins the columns with the same name
--Natural Join
select sname,major
from student s, apply a
where s.sid = a.sid
order by sname

select sname, gpa
from student s, apply a
where s.sid = a.sid and sizehs < 1000 
and major = 'CS'
and cname = 'Stanford'

select s.sid, s.sname, s.gpa, a.major, c.cname,c.state,c.enrollment
from student s, college c, apply a
where s.sid = a.sid
and c.cname = a.cname

select sid, sname, major
from student natural join apply;

--using is better than natural join as it 
-- provides an ooportunity to specify what column to use
select sid, sname, major
from student join apply using(sid);

--inner join
select sname, major
from student s inner join apply a
on s.sid = a.sid
order by sname

select sname, major
from student s join apply a
on s.sid = a.sid
order by sname

select sname, gpa
from student s join apply a
on s.sid = a.sid 
and major = 'CS'
and cname = 'Stanford'

select s.sid, s.sname, s.gpa, a.major, c.cname,c.state,c.enrollment
from (student s join apply a on s.sid = a.sid) 
join college c on c.cname = a.cname

select s1.sid, s1.sname, s1.gpa, s2.sid,s2.sname,s2.gpa
from student s1, student s2
where s1.gpa = s2.gpa
and s1.sid < s2.sid

--using and on are not allowed together
select s1.sid, s1.sname, gpa, s2.sid,s2.sname,gpa
from student s1 join student s2 using(gpa)
where s1.sid < s2.sid



------------ OUTER JOINS ---------------------------
select sid,sname,cname,major
from student left join apply using(sid);

select sid,sname,cname,major
from student natural left join apply;

select sid,sname,cname,major
from APPLY natural left join STUDENT;

----writing left outer join without using outer jon
select s.sid,sname,cname,major
from student s,apply a
where s.sid = a.sid
union
select sid,sname,null,null
from student
where sid not in (select sid from apply)


---right join
select sid,sname,cname,major
from student natural right join apply;
  
--full outer join
select sid,sname,cname,major
from student natural full join apply;

select sid,sname,cname,major
from student full join apply using(sid);

select sid,sname,cname,major
from student natural left join apply
union
select sid,sname,cname,major
from student natural right join apply;


--aggregation functions

select avg(gpa)
from student

select min(gpa)
from student

select max(gpa)
from student

select sum(gpa)
from student

select min(gpa)
from student s,apply a
where a.sid = s.sid
and a.major = 'CS'

--not the right one as dulicates can be there
select avg(gpa)
from student s,apply a
where a.sid = s.sid
and a.major = 'CS'

select avg(gpa)
from student
where sid in (select sid from apply where major='CS')

select count(*)
from college
where enrollment > 15000

--no. of applications to college
select count(*)
from apply
where cname = 'Corneli'

--no of students who have applied to college
select count(*)
from (select distinct sid,cname from apply)
where cname = 'Corneli'

select count(distinct sid)
from apply
where cname = 'Corneli'

--find the records where for each student the no.people having same gpa = no. of people having same sizehs
select * 
from student s1
where (select count(*) from student s2
        where s1.sid <> s2.sid and s1.gpa = s2.gpa)
        =
      (select count(*) from student s2
        where s1.sid <> s2.sid and s1.sizehs = s2.sizehs)
        
--by how much the avg gpa of cs exceeds non cs

select cs.avggpa-noncs.avggpa from
(select avg(gpa) as avggpa
from student
where sid in (select sid from apply where major = 'CS')) CS,
(select avg(gpa) as avggpa
from student
where sid not in (select sid from apply where major = 'CS')) NONCS;


select * from apply
where cname = 'MIT'

--find the number of students to each college
select cname,count(*)
from apply
group by cname;

--total enrollment to the state
select state,sum(enrollment)
from college
group by state

--find the min and max gpa for each major in each college
select cname,major,min(gpa),max(gpa)
from student s, apply a
where s.sid = a.sid
group by cname,major

--how many colleges students applied
select s.sid, count(distinct cname)
from student s, apply a
where s.sid = a.sid
group by s.sid

--including students in the result who havenot applied anywhere
select s.sid, count(distinct cname) as count
from student s, apply a
where s.sid = a.sid
group by s.sid
union
select sid,0
from student
where sid not in (select sid from apply)
order by count

--find colleges having fewer applications
select cname
from apply
group by cname
having count(*) < 5

select distinct cname
from apply a1
where 5 > (select count(*) from apply a2 
where a1.cname = a2.cname)

--find colleges having fewer applicants (remember not applications)
select cname
from apply
group by cname
having count(distinct sid) < 5

--find all majors where the max gpa of it is lower than avg gpa of student

select major
from student s, apply a
where s.sid = a.sid
group by major
having max(gpa) < (select avg(gpa) from student)





---------------------------------------------------------------------------------
--Many DBs build indexes automatically on PRIMARY KEY AND UNIQUE attributes
-- with indexes full table scans will not happen.

CREATE INDEX NAME ON T(A)
CREATE INDEX NAME ON T(A1,A2....)
CREATE UNIQUE INDEX NAME ON T(A)
DROP INDEX NAME;



SELECT MIN(SID) FROM 
(SELECT SID FROM
STUDENT
WHERE ROWNUM <=3
ORDER BY SID DESC
)


CREATE TRIGGER name
BEFORE|AFTER|INSTEAD OF event
[REFERENCING variables]
[FOR EACH ROW]
WHEN (condition)
action



