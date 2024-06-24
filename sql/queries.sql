-- Q1
INSERT INTO cd.facilities (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance) VALUES
    (9, 'Spa', 20, 30, 100000, 800);

-- Q2
INSERT INTO cd.facilities (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
    SELECT (SELECT max(facid) FROM cd.facilities)+1, 'Spa', 20, 30, 100000, 800;

--Q3
UPDATE cd.facilities
    SET initialoutlay=10000
    WHERE name='Tennis Court 2'

--Q4
UPDATE cd.facilities
SET
    membercost=(SELECT (SELECT membercost FROM cd.facilities WHERE name='Tennis Court 1')*1.1),
    guestcost=(SELECT (SELECT guestcost FROM cd.facilities WHERE name='Tennis Court 1')*1.1)
WHERE name='Tennis Court 2';

--Q5
DELETE FROM cd.bookings;

--Q6
Delete from cd.members Where memid=37;

--Q7
SELECT facid,name,membercost,monthlymaintenance FROM cd.facilities
WHERE
    membercost>0
    AND
    membercost<(SELECT (monthlymaintenance)/50);

--Q8
SELECT * FROM cd.facilities
WHERE
    name LIKE '%Tennis%';

--Q9
SELECT * FROM cd.facilities WHERE facid IN (1,5);

--Q10
SELECT memid,surname,firstname,joindate FROM cd.members WHERE joindate >= '2012-09-01';

--Q11
SELECT surname FROM cd.members UNION SELECT name FROM cd.facilities;

--Q12
select starttime
from
    cd.bookings bks
        inner join cd.members mems
                   on mems.memid = bks.memid
WHERE
    firstname='David'
  and
    surname='Farrell';

--Q13
SELECT starttime as start, name from cd.bookings b
    inner join cd.facilities f on b.facid=f.facid
    WHERE
        name like 'Tennis Court%' and starttime >= '2012-09-21' and
        starttime < '2012-09-22'
    order by starttime

--Q14
SELECT f.firstname as memfname,f.surname as memsname,s.firstname as recfname,s.surname as recsname
    FROM cd.members f
        LEFT OUTER JOIN cd.members s
            on f.recommendedby=s.memid
    ORDER BY f.surname,f.firstname

--Q15
SELECT DISTINCT s.firstname,s.surname
    from cd.members f
        INNER JOIN cd.members s
            ON f.recommendedby=s.memid
    ORDER BY s.surname,s.firstname

--Q16
select distinct mems.firstname || ' ' ||  mems.surname as member,
                (select recs.firstname || ' ' || recs.surname as recommender
                 from cd.members recs
                 where recs.memid = mems.recommendedby
                )
    from cd.members mems
    order by member;

--Q17
select recommendedby, count(*)
    from cd.members
    where recommendedby is not null
    group by recommendedby
    order by recommendedby;

--Q18
SELECT facid, SUM(slots)
    from cd.bookings
    group by facid
    order by facid;

--Q19
SELECT facid,SUM(slots)
    from cd.bookings
    where starttime>='2012-09-01' and starttime<'2012-10-1'
    group by facid
    order by sum

--Q20
SELECT facid, extract(month from starttime) as month, sum(slots)
    from cd.bookings
    where extract(year from starttime)=2012
    group by facid,month
    order by facid,month

--Q21
SELECT COUNT(DISTINCT memid) FROM cd.bookings

--Q22
SELECT surname, firstname,m.memid, MIN(starttime)
    FROM cd.members m INNER JOIN cd.bookings b
        ON m.memid=b.memid
    WHERE starttime>'2012-09-01'
    GROUP BY m.memid
    ORDER BY m.memid

--Q23
select (select count(*) from cd.members) as count, firstname, surname
    from cd.members
    order by joindate

--Q24
SELECT ROW_NUMBER () OVER (ORDER BY joindate), firstname,surname
    from cd.members

--Q25
SELECT b.facid, SUM(slots) as total
    FROM cd.bookings b
        INNER JOIN cd.facilities f
            ON b.facid=f.facid
    GROUP BY b.facid
    ORDER BY total
        DESC LIMIT 1

--Q26
SELECT surname || ', ' || firstname AS name
FROM cd.members

--Q27
SELECT memid, telephone
FROM cd.members
WHERE telephone
          LIKE '(%' ORDER BY memid

--Q28
SELECT substr(surname,1,1) as letter, COUNT(*)
FROM cd.members
GROUP BY letter
ORDER BY letter
