
USE [AU_2014]
GO
--1
/****** Object:  Schema [au2014]    Script Date: 07/12/2014 18:39:59 ******/
CREATE SCHEMA [au2014] AUTHORIZATION [dbo]
GO

--2
CREATE LOGIN master 
    WITH PASSWORD = '123456';
CREATE USER master FOR LOGIN master;

grant all to master;

--3

create login readuser with password='123456';
create user readuser for login readuser;

EXEC sp_addrolemember N'db_datareader', N'readuser'

--4

use AU_2014;
create table transfers
(
Custmor_id_to int,
Customer_id_from int,
money_tranferred money,
CustAccount_num_to varchar(20)
constraint CK_MyTable_AccNmbrto check(CustAccount_num_to not like '%[a-z]%'),
CustAccount_num_from varchar(20)
constraint CK_MyTable_AccNmbrfrom check(CustAccount_num_from not like '%[a-z]%'),
date_of_trans date 
CONSTRAINT Refer_To_CustomerAc1 FOREIGN KEY (Custmor_id_to) 
    REFERENCES ass1.dbo.Customers (CustAccount_num),
CONSTRAINT Refer_To_CustomerAc2 FOREIGN KEY (Customer_id_from) 
    REFERENCES ass1.dbo.Customers (CustAccount_num)
)

use AU_2014;
insert into transfers values(1,2,12,123,124,'2014-05-22');
insert into transfers values(1,2,12,123,124,'2014-06-22');
insert into transfers values(2,1,12,124,123,'2014-07-22')
insert into transfers values(2,1,14,124,123,'2014-07-22');
insert into transfers values(2,1,16,124,123,'2013-07-22');



CREATE TABLE Customers
(
LastName nvarchar(50),
FirstName nvarchar(50),
CustId int,
CustAccount_num varchar(20)
constraint CK_MyTable_AccNmbr check(CustAccount_num not like '%[a-z]%'),
PhoneNumber varchar(10)
        constraint CK_MyTable_PhoneNumber check (PhoneNumber like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
Email varchar(50)
CONSTRAINT [EmailCheck] CHECK
(
      CHARINDEX('.',[Email],CHARINDEX('@',[Email]))-CHARINDEX('@',[Email])>1
      AND CHARINDEX('.',REVERSE(LTRIM(RTRIM([Email])))) > 2
      AND CHARINDEX('@',LTRIM([Email])) > 2
),
DOB date,
SecurityQues1 ntext,
SecurityAns1 ntext,
SecurityQues2 ntext,
SecurityAns2 ntext,
Bank_Balance money,        
cust_address varchar(200),
Acc_Password varchar(200)
);
insert into customers values('jindal', 'sapna',1,123,9128378271,'spnajindal1@gmail.com','1993-06-24','seerfwerewr','dssd','swdfsefwe','wew',12343,'wewqewqe','sapnasaewqe');
insert into customers values('sharma', 'sapna12',2,124,9124578271,'spnajindal231@gmail.com','1994-05-22','seerfwerewr','dssd','swdfsefwe','wew',12343,'wewqewqe','sapnasaewqe');

--4f(i)
select CustAccount_num_to,
DATEPART(yyyy,date_of_trans) as YearOfTransfer 
from transfers
group by DATEPART(yyyy,date_of_trans),CustAccount_num_to 
having count(CustAccount_num_to)>1
order by count(*) desc 

select CustAccount_num_from,
DATEPART(yyyy,date_of_trans) as YearOfTransfer 
from transfers
group by DATEPART(yyyy,date_of_trans),CustAccount_num_from 
having count(CustAccount_num_from)>1
order by count(*) desc 

--4f(ii)

CREATE PROCEDURE topUserTrans(@CustUserName nvarchar(50)) 
AS BEGIN

SELECT TOP 20 custs1.[CustAddress] as FromAddress, custs2.[CustAddress] as ToAddress, trans.[Amount] as ToAddress 
FROM Customers as custs1 INNER JOIN transfers as trans
ON custs1.CustAccount_num = trans.CustAccount_num_from
INNER JOIN  Customers as custs2 
ON trans.CustAccount_num_to = custs2.CustAccount_num
WHERE custs1.FirstName like @CustUserName
order  by trans.money_tranferred desc

END

--4f(iii)

select t1.FirstName as tranferedTOFirstName,t1.LastName as tranferedTOLastName,t2.FirstName as tranferedFromFirstName,t2.LastName as tranferedFromFirstName
from Customers as t1,Customers as t2,(SELECT ak.Custmor_id_to,ak.Customer_id_from,ak.[Year], ak.[money_tranferred] FROM (SELECT Custmor_id_to,Customer_id_from,DATEPART(yyyy,date_of_trans) as [Year],[money_tranferred]
	,Rank() OVER(Partition BY DATEPART(yyyy,date_of_trans) ORDER BY [money_tranferred] DESC) as [Order]
  FROM [AU_2014].[dbo].[transfers]) as ak WHERE ak.[Order] <= 10
 ) as t3 
where t1.CustId=t3.Custmor_id_to and t2.CustId=t3.Customer_id_from

--4f(iv)
select CustAccount_num,t_year from 
(
select rank() 
over (partition by t_year 
order by resultCount desc )
as recRank,CustAccount_num_from,t_year,resultCount
from (select  CustAccount_num_from, datepart(yyyy,date_of_trans)as t_Year,count(*) as resultCount 
from transfers
group by datepart(yyyy,date_of_trans),CustAccount_num_from
)as res
)as t , Customers as c
where t.recRank<=2 and t.CustAccount_num_from=c.CustAccount_num order by t_year desc

--DML
-- 1.
use AdventureWorks2012;
SELECT p.[FirstName]+' '+p.[LastName] as 'ContactName'
   ,perphone.[PhoneNumber]
   ,emailadd.EmailAddress
      ,cc.CardNumber
  FROM [AdventureWorks2012].[Sales].[CreditCard] cc INNER JOIN 
  [AdventureWorks2012].[Sales].[PersonCreditCard] percrecard 
  ON percrecard .CreditCardID = cc.CreditCardID 
  AND cc.ExpMonth <= 4 and cc.ExpYear <= 2007 
  INNER JOIN [AdventureWorks2012].[Person].[Person] p
  ON percrecard .BusinessEntityID = p.BusinessEntityID
  INNER JOIN [AdventureWorks2012].[Person].[PersonPhone] perphone
  ON perphone.BusinessEntityID = p.BusinessEntityID
  INNER JOIN [AdventureWorks2012].[Person].[EmailAddress] emailadd
  ON emailadd.BusinessEntityID = p.BusinessEntityID;

-- 2.
use AdventureWorks2012;
INSERT INTO [AdventureWorks2012].[Production].[ProductProductPhoto]
 VALUES (2,88,2,GETDATE());
 
INSERT INTO [AdventureWorks2012].[Production].[ProductProductPhoto]
 VALUES (2,89,2,GETDATE());
  
INSERT INTO [AdventureWorks2012].[Production].[ProductProductPhoto]
 VALUES (3,70,2,GETDATE());
 
 SELECT proproph.[ProductID]
 FROM [AdventureWorks2012].[Production].[ProductProductPhoto] AS proproph 
 INNER JOIN [AdventureWorks2012].[Production].[ProductPhoto] AS proph
 ON proproph.ProductPhotoID = proph.ProductPhotoID
 GROUP BY proproph.ProductID
 HAVING COUNT(proph.ProductPhotoID) > 2

-- 4.
use AdventureWorks2012;
SELECT COUNT(*) NumberOfProducts 
 FROM [AdventureWorks2012].[Production].[Product] p
 INNER JOIN [AdventureWorks2012].[Production].[ProductSubcategory] psc
 ON p.ProductSubcategoryID = psc.ProductSubcategoryID AND psc.Name='Cranksets'
 INNER JOIN [AdventureWorks2012].[Sales].[SalesOrderDetail] salesorddet
 ON salesorddet.ProductID = p.ProductID
 INNER JOIN [AdventureWorks2012].[Sales].[SalesOrderHeader] salesordhead
 ON salesordhead.SalesOrderID = salesorddet.SalesOrderID
 INNER JOIN [AdventureWorks2012].[Person].[Address] addr
 ON addr.AddressID = salesordhead.BillToAddressID AND addr.City like '%london%'



-- 5(a)
use AdventureWorks2012;
SELECT MIN([StandardCost])
      ,Color
  FROM [AdventureWorks2012].[Production].[Product]
  WHERE StandardCost>0.0 AND Color IS NOT NULL
  GROUP BY Color
-- 5(b,c,d).
use AdventureWorks2012;
SELECT p.[Name]
      ,p.[ListPrice]
      ,msc.[Color]
      ,msc.MinimumStandarCost
  FROM [AdventureWorks2012].[Production].[Product] p
  INNER JOIN (SELECT MIN(pin.[StandardCost]) AS MinimumStandarCost,pin.[Color]
  FROM [AdventureWorks2012].[Production].[Product] pin
  WHERE pin.[StandardCost]>0.0 AND pin.[Color] IS NOT NULL AND (pin.Color = 'Blue' OR  pin.Color = 'Yellow' OR pin.Color = 'Black')
  GROUP BY pin.[Color]) AS msc
  ON p.Color = msc.Color and p.StandardCost = msc.MinimumStandarCost;

-- 6 a.
use AdventureWorks2012;
SELECT pc.ProductCategoryID
 ,avg(p.ListPrice) AS AverageListPrice
 ,COALESCE(p.Color, 'N/A') AS Color
 ,COUNT( p.ProductID) AS ProductCount
 FROM [AdventureWorks2012].[Production].[Product] p
 INNER JOIN [AdventureWorks2012].[Production].[ProductSubcategory] pc
 ON pc.ProductSubcategoryID = p.ProductSubcategoryID
 GROUP BY PC.ProductCategoryID, p.Color
 
--6 b,c,d.
use AdventureWorks2012;
SELECT pcat.Name, col.Color, col.ProductCount, col.AverageListPrice
 FROM [AdventureWorks2012].[Production].[ProductCategory] pcat
 INNER JOIN (SELECT pc.ProductCategoryID
 ,avg(p.ListPrice) AS AverageListPrice
 ,COALESCE(p.Color, 'N/A') AS Color
 ,COUNT( p.ProductID) AS ProductCount
 FROM [AdventureWorks2012].[Production].[Product] p
 INNER JOIN [AdventureWorks2012].[Production].[ProductSubcategory] pc
 ON pc.ProductSubcategoryID = p.ProductSubcategoryID
 GROUP BY PC.ProductCategoryID, p.Color) AS col
 ON col.ProductCategoryID = pcat.ProductCategoryID
 ORDER BY pcat.ProductCategoryID;
 
-- 7.
use AdventureWorks2012;
SELECT s.Name
    ,soh.SubTotal
    ,tWeight.TotalWeight
 From [AdventureWorks2012].[Sales].[Customer] c
 INNER JOIN [AdventureWorks2012].[Sales].[SalesOrderHeader] soh
 ON c.CustomerID = soh.CustomerID
 INNER JOIN [AdventureWorks2012].[Sales].[Store] s
 ON s.BusinessEntityID = c.StoreID
 INNER JOIN (SELECT [SalesOrderID],SUM(p.[Weight]) AS TotalWeight
   FROM [AdventureWorks2012].[Sales].[SalesOrderDetail] sod
   INNER JOIN [AdventureWorks2012].[Production].[Product] p
   ON p.ProductID = sod.ProductID and p.[Weight] IS NOT NULL
   GROUP BY sod.SalesOrderID) AS tWeight
 ON  soh.SalesOrderID = tWeight.SalesOrderID
 ORDER BY [SubTotal] DESC

-- 8.
use AdventureWorks2012;
SELECT salesordhead.[SalesPersonID]
 FROM [AdventureWorks2012].[Sales].[SalesOrderHeader] salesordhead
 INNER JOIN [AdventureWorks2012].[Sales].[Customer] c
 ON c.CustomerID = salesordhead.CustomerID
 WHERE salesordhead.TotalDue > 10000
 GROUP BY salesordhead.SalesPersonID
 HAVING COUNT(*) > 5

-- 9.

use AdventureWorks2012;
SELECT *
 FROM 
 (SELECT soh.[SalesPersonID]
  FROM [AdventureWorks2012].[Sales].[SalesOrderHeader] soh
  INNER JOIN [AdventureWorks2012].[Sales].[Customer] c
  ON c.CustomerID = soh.CustomerID
  WHERE soh.TotalDue > 10000
  GROUP BY soh.SalesPersonID
  HAVING COUNT(*) > 5) AS t1
 INNER JOIN 
 (SELECT DISTINCT puorhead.[EmployeeID]
   FROM [AdventureWorks2012].[Purchasing].[PurchaseOrderHeader] puorhead
   INNER JOIN [AdventureWorks2012].[Purchasing].[PurchaseOrderDetail] puorde
   ON puorhead.PurchaseOrderID = puorde.PurchaseOrderID
   WHERE puorde.RejectedQty > 0.0) AS t2
  ON t1.SalesPersonID = t2.EmployeeID;





