# Introduction
This project serves to demonstrate the use of SQL to gain valuable insights from the data saved on RDBMS instances. Developers and BSA can use this tool to practice building efficient queries, that extracts valuable data for their reports or software applications. The technologies used in this project include Docker, PostgreSQL, Git, Bash. 

# SQL Queries

###### Table Setup (DDL)
Assuming that there is a PostgreSQL docker instance running on `port 5432`, the user can create the database schema and relational tables using the `clubdata.sql` script. To do so, the user must use the psql CLI tool and run the following command:

`psql -h HOST_NAME -p 5432 -U USER_NAME -c clubdata.sql`

###### Question 1: Insert some data into a table
```
INSERT INTO cd.facilities
(
    facid,
    name,
    membercost,
    guestcost,
    initialoutlay,
    monthlymaintenance
)
VALUES
(9, 'Spa', 20, 30, 100000, 800);
```

###### Question 2: Insert calculated data into a table
```
INSERT INTO cd.facilities
(
    facid,
    name,
    membercost,
    guestcost,
    initialoutlay,
    monthlymaintenance
)
SELECT
    (
        SELECT max(facid) FROM cd.facilities
    ) + 1,
    'Spa',
    20,
    30,
    100000,
    800;
```

###### Question 3: Update some existing data
```
UPDATE cd.facilities
SET initialoutlay = 10000
WHERE name = 'Tennis Court 2'
```

###### Question 4: Update a row based on the contents of another row
```
UPDATE cd.facilities
SET membercost =
    (
        SELECT
            (
                SELECT membercost FROM cd.facilities WHERE name = 'Tennis Court 1'
            ) * 1.1
    ),
    guestcost =
    (
        SELECT
            (
                SELECT guestcost FROM cd.facilities WHERE name = 'Tennis Court 1'
            ) * 1.1
    )
WHERE name = 'Tennis Court 2';
```

###### Question 5: Delete all bookings
```
DELETE FROM cd.bookings;
```

###### Question 6: Delete a member from the cd.members table
```
Delete from cd.members
Where memid = 37;
```

###### Question 7: Control which rows are retrieved - part 2
```
SELECT facid,
       name,
       membercost,
       monthlymaintenance
FROM cd.facilities
WHERE membercost > 0
      AND membercost <
      (
          SELECT (monthlymaintenance) / 50
      );
```

###### Question 8: Basic string searches
```
SELECT *
FROM cd.facilities
WHERE name LIKE '%Tennis%';
```

###### Question 9: Matching against multiple possible values
```
SELECT *
FROM cd.facilities
WHERE facid IN ( 1, 5 );
```

###### Question 10: Working with dates
```
SELECT memid,
       surname,
       firstname,
       joindate
FROM cd.members
WHERE joindate >= '2012-09-01';
```
###### Question 11: Combining results from multiple queries
```
SELECT surname
FROM cd.members
UNION
SELECT name
FROM cd.facilities;
```

###### Question 12: Retrieve the start times of members' bookings
```
select starttime
from cd.bookings bks
    inner join cd.members mems
        on mems.memid = bks.memid
WHERE firstname = 'David'
      and surname = 'Farrell';
```

###### Question 13: Work out the start times of bookings for tennis courts
```
SELECT starttime as start,
       name
from cd.bookings b
    inner join cd.facilities f
        on b.facid = f.facid
WHERE name like 'Tennis Court%'
      and starttime >= '2012-09-21'
      and starttime < '2012-09-22'
order by starttime
```
###### Question 14: Produce a list of all members, along with their recommender
```
SELECT f.firstname as memfname,
       f.surname as memsname,
       s.firstname as recfname,
       s.surname as recsname
FROM cd.members f
    LEFT OUTER JOIN cd.members s
        on f.recommendedby = s.memid
ORDER BY f.surname,
         f.firstname
```
###### Question 15: Produce a list of all members who have recommended another member
```
SELECT DISTINCT
    s.firstname,
    s.surname
from cd.members f
    INNER JOIN cd.members s
        ON f.recommendedby = s.memid
ORDER BY s.surname,
         s.firstname
```
###### Question 16: Produce a list of all members, along with their recommender, using no joins.
```
select distinct mems.firstname || ' ' ||  mems.surname as member,
                (select recs.firstname || ' ' || recs.surname as recommender
                 from cd.members recs
                 where recs.memid = mems.recommendedby
                )
    from cd.members mems
    order by member;
```

###### Question 17: Count the number of recommendations each member makes.
```
select recommendedby,
       count(*)
from cd.members
where recommendedby is not null
group by recommendedby
order by recommendedby;
```

###### Question 18: List the total slots booked per facility
```
SELECT facid,
       SUM(slots)
from cd.bookings
group by facid
order by facid;
```

###### Question 19: List the total slots booked per facility in a given month
```
SELECT facid,
       SUM(slots)
from cd.bookings
where starttime >= '2012-09-01'
      and starttime < '2012-10-1'
group by facid
order by sum
```

###### Question 20: List the total slots booked per facility per month
```
SELECT facid, extract(month from starttime) as month, sum(slots)
    from cd.bookings
    where extract(year from starttime)=2012
    group by facid,month
    order by facid,month
```

###### Question 21: Find the count of members who have made at least one booking
```
SELECT COUNT(DISTINCT memid)
FROM cd.bookings
```

###### Question 22: List each member's first booking after September 1st 2012
```
SELECT surname,
       firstname,
       m.memid,
       MIN(starttime)
FROM cd.members m
    INNER JOIN cd.bookings b
        ON m.memid = b.memid
WHERE starttime > '2012-09-01'
GROUP BY m.memid
ORDER BY m.memid
```

###### Question 23: Produce a list of member names, with each row containing the total member count
```
select
    (
        select count(*) from cd.members
    ) as count,
    firstname,
    surname
from cd.members
order by joindate
```

###### Question 24: Produce a numbered list of members
```
SELECT ROW_NUMBER() OVER (ORDER BY joindate),
       firstname,
       surname
from cd.members
```

###### Question 25: Output the facility id that has the highest number of slots booked, again
```
SELECT b.facid, SUM(slots) as total
    FROM cd.bookings b
        INNER JOIN cd.facilities f
            ON b.facid=f.facid
    GROUP BY b.facid
    ORDER BY total
        DESC LIMIT 1
```

###### Question 26: Format the names of members
```
SELECT surname || ', ' || firstname AS name
FROM cd.members
```

###### Question 27: Find telephone numbers with parentheses
```
SELECT memid,
       telephone
FROM cd.members
WHERE telephone LIKE '(%'
ORDER BY memid
```

###### Question 28: Count the number of members whose surname starts with each letter of the alphabet
```
SELECT substr(surname, 1, 1) as letter,
       COUNT(*)
FROM cd.members
GROUP BY letter
ORDER BY letter
```


