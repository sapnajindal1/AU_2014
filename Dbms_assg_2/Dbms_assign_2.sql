-- 1. Construct DB tables for the above system. 

USE DBMS_Assign2;

-- Bus Depot – Should be uniquely identified and has a name and address.
CREATE TABLE BusDepot(
 [BusDepotID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
 [Name] VARCHAR(225),
 [Address] VARCHAR(225) 
);

-- Bus Driver – Should be uniquely identified  , is associated with a   particular depot  and has a name , earns salary.
CREATE TABLE BusDriver(
 [BusDriverID] INT identity(1,1) Not Null PRIMARY KEY,
 [BusDepotID] INT FOREIGN KEY REFERENCES BusDepot([BusDepotID]),
 [Name] VARCHAR(225),
 [Salary] DECIMAL(7,2)
);

-- Bus Cleaner - Should be uniquely identified ,is associated with a particular depot and has a name ,earns salary.
CREATE TABLE BusCleaner(
 [BusCleanerID] INT identity(1,1) Not Null PRIMARY KEY,
 [BusDepotID] INT FOREIGN KEY REFERENCES BusDepot([BusDepotID]),
 [Name] VARCHAR(225),
 [Salary] DECIMAL(7,2)
);

-- Bus Route -  Should be uniquely identified , has a name/description and starts from a particular depot. 
CREATE TABLE BusRoute(
 [BusRouteID] INT identity(1,1) Not Null PRIMARY KEY, 
 [Name] VARCHAR(225),
 [Description] VARCHAR(225),
 [StartBusDepotID] INT FOREIGN KEY REFERENCES BusDepot([BusDepotID])
);

-- Bus – Should have a registration number,model name and type . Each bus will be having a driver and cleaner from 
-- the same depot associated with it and will ply on a particular route.
CREATE TABLE Bus(
 [BusID] INT identity(1,1) Not Null PRIMARY KEY, 
 [Name] VARCHAR(225),
 [Model] VARCHAR(50),
 [Type] VARCHAR(25),
 [StartBusDepotID] INT FOREIGN KEY REFERENCES BusDepot([BusDepotID]),
 [BusDriverID] INT FOREIGN KEY REFERENCES BusDriver([BusDriverID]),
 [BusCleanerID] INT FOREIGN KEY REFERENCES BusCleaner([BusCleanerID]),
 [BusRouteID] INT FOREIGN KEY REFERENCES BusRoute([BusRouteID])
);

-- 2. Stored procedure's which will take values for the columns of these tables as input and insert the data .
-- Bus Depot

CREATE PROCEDURE  InsertBusDepot(@name VARCHAR(225), @address VARCHAR(225))
AS
BEGIN
 INSERT INTO [DBMS_Assign2].[dbo].[BusDepot]
           ([Name]
           ,[Address])
     VALUES (@name, @address)
END
EXEC InsertBusDepot 'Depot 1','kormangla, Bangalore';
EXEC InsertBusDepot 'Depot 2','indira nagr1, Bangalore';
EXEC InsertBusDepot 'Depot 3','Jayanagar1, Bangalore';
EXEC InsertBusDepot 'Depot 4','Indiranagar 2, Bangalore';
EXEC InsertBusDepot 'Depot 5','bellndur, Bangalore';

-- Bus Driver
CREATE PROCEDURE InsertBusDriver(@busdepotid INT,@name VARCHAR(225), @salary DECIMAL(7,2))
AS
BEGIN
 INSERT INTO [DBMS_Assign2].[dbo].[BusDriver]
           ([BusDepotID]
           ,[Name]
           ,[Salary])
     VALUES (@busdepotid, @name, @salary)
END

EXEC DBMS_Assign2.dbo.insertbusdriver 1,'sunderam', 22000.00;
EXEC InsertBusDriver 1,'rajesh', 24000.65;

EXEC InsertBusDriver 2,'kamla', 19000.23;
EXEC InsertBusDriver 2,' kartik',34000;

EXEC InsertBusDriver 3,'darleen', 21000.00;
EXEC InsertBusDriver 3,'annendra', 12000.54;

EXEC InsertBusDriver 4,'rajendra reddy', 20400.32;
EXEC InsertBusDriver 4,'Chama', 21000.00;

--Bus Cleaner
CREATE PROCEDURE InsertBusCleaner(@busdepotid INT,@name VARCHAR(225), @salary DECIMAL(7,2))
AS
BEGIN
 INSERT INTO [DBMS_Assign2].[dbo].[BusCleaner]
           ([BusDepotID]
           ,[Name]
           ,[Salary])
     VALUES (@busdepotid, @name, @salary)
END

EXEC InsertBusCleaner 1,'devenila', 10000.32;
EXEC InsertBusCleaner 1,'avinash', 11200.65;

EXEC InsertBusCleaner 2,'pawan lal', 10030.45;
EXEC InsertBusCleaner 2,' aditya lal', 11200.45;

EXEC InsertBusCleaner 3,'nehalya', 12000.23;
EXEC InsertBusCleaner 3,'jocokia', 2300.00;

EXEC InsertBusCleaner 4,'Bolendr M', 10000.45;
EXEC InsertBusCleaner 4,'Chandrashekar M', 11000.45;

-- Bus Route
CREATE PROCEDURE InsertBusRoute(@name VARCHAR(225), @description VARCHAR(225), @busdeport INT)
AS
BEGIN
 INSERT INTO [DBMS_Assign2].[dbo].[BusRoute]
           ([Name]
           ,[Description]
           ,[StartBusDepotID])
     VALUES (@name, @description, @busdeport)
END

EXEC InsertBusRoute '172F','Koramangala to Kempegowda Bus Station', 1;
EXEC InsertBusRoute '171J','Koramangala to Kempegowda Bus Station', 1;

EXEC InsertBusRoute '34f','MG road Road to Koramangala', 2;
EXEC InsertBusRoute '31E','KH Road to Koramangala', 2;

EXEC InsertBusRoute '18d','marathli Market to sarjapur', 3;
EXEC InsertBusRoute '30j','btm to hsr layout', 3;

EXEC InsertBusRoute '20A','sriganaganagr to  Bhavana', 4;
EXEC InsertBusRoute '286','Cauvery Bhavana to Jayanagara', 4;

-- Bus
CREATE PROCEDURE InsertBus(@name VARCHAR(225), @model VARCHAR(50), @type VARCHAR(25), @busdepot INT, @driverid INT, @cleanerid INT, @routeid INT)
AS
BEGIN
 INSERT INTO [DBMS_Assign2].[dbo].[Bus]
           ([Name]
           ,[Model]
           ,[Type]
           ,[StartBusDepotID]
           ,[BusDriverID]
           ,[BusCleanerID]
           ,[BusRouteID])
     VALUES (@name, @model, @type, @busdepot, @driverid, @cleanerid, @routeid)
END

--3
Create Procedure TakeCleanerReturnNameSal
(
@cleanerId INT,                   --Input parameter ,  cleaner id of bus cleaner
@cleanerName VARCHAR (200) OUT,    -- Output parameter to collect the cleaner name
@cleanerSalary Decimal (7,2)OUT     -- Output Parameter to collect the cleaner salary
)
AS
BEGIN
SELECT @cleanerName= name, 
    @cleanerSalary=salary FROM DBMS_Assign2.dbo.BusCleaner WHERE buscleanerid=@cleanerId
END

Declare @clname as nvarchar(200)   -- Declaring the variable to collect the cleaner name
Declare @clsalary as Decimal (7,2)     -- Declaring the variable to collect the cleaner salary
Execute TakeCleanerReturnNameSal 1 , @clname output, @clsalary output
select @clname,@clsalary      -- "Select" Statement is used to show the output from Procedure

--4

CREATE FUNCTION highestSalary(@n int, @ptype varchar(100))
RETURNs decimal(7,2)
AS
BEGIN
  declare @highestsal decimal(7,2)=0;
   if @ptype='cleaner'
   begin
   SELECT TOP 1 @highestsal=SalarySubquery.Salary
FROM 
(
    SELECT TOP (@n) Salary
    FROM DBMS_Assign2.dbo.BusCleaner
    ORDER BY Salary DESC
) SalarySubquery
ORDER BY Salary ASC
   end
   else if @ptype='driver'
   begin
   SELECT TOP 1 @highestsal=SalarySubquery.Salary
FROM 
(
    SELECT TOP (@n) Salary
    FROM DBMS_Assign2.dbo.BusDriver
    ORDER BY Salary DESC
) SalarySubquery
ORDER BY Salary ASC
   end
   RETURN @highestSal;
END;

select dbo.highestSalary(4,'driver');

--5
create view DriverDetailView
as
select BusDriverID,Name ,Salary
from DBMS_Assign2.dbo.BusDriver
where BusDepotID in (select DBMS_Assign2.dbo.BusRoute.StartBusDepotID from DBMS_Assign2.dbo.BusRoute where DBMS_Assign2.dbo.BusRoute.Name like '%M.G road%' )

select * 
from DriverDetailView 
where Salary>2000 ;


--7

CREATE TRIGGER CheckIncreaseSalary ON DBMS_Assign2.dbo.BusDriver 
INSTEAD OF INSERT
AS
	declare @SalaryNew decimal(7,2);
	declare @SalaryOld decimal(7,2);
	declare @id int;
	select @id=i.BusDriverID from inserted i;
	select @SalaryNew=i.Salary from inserted i;
	select @SalaryOld=(select salary from DBMS_Assign2.dbo.BusDriver where BusDepotID=@id);
	BEGIN
		if(@SalaryNew > ((@SalaryOld*6)/5) )
		begin
			RAISERROR('Cannot insert salary where salary increased by 20%',16,1);
			print('error came');
			ROLLBACK;
		end
		else
		begin
			 UPDATE [DBMS_Assign2].[dbo].[BusDriver] SET [Salary]=@SalaryNew WHERE [BusDriverID]=@id
		end
	END
UPDATE [DBMS_Assign2].[dbo].[BusDriver] SET [Salary]=40000 WHERE [BusDriverID]=1

--8

Create Table UnluckyCleaner(
[BusCleanerId] INT identity(1,1) Not Null PRIMARY KEY,
 [BusDepotId] INT FOREIGN KEY REFERENCES BusDepot([BusDepotId]),
 [Name] VARCHAR(200),
 [Salary] DECIMAL(5,1)
);

Create Trigger Nojob
on DBMS_Assign2.dbo.BusCleaner
Instead of Insert As
Declare @CleanerSalary Decimal(5,1)
Declare @MaxDriverSalary Decimal (5,1)
Declare @DepotId Int
Declare @Name Varchar(200)


Select @CleanerSalary=i.Salary from inserted i;
Select @DepotId=i.[BusDepotId] from inserted i;
Select @MaxDriverSalary=MAX(Salary) from DBMS_Assign2.dbo.BusDriver where DBMS_Assign2.dbo.BusDriver.BusDepotId=@DepotId ;
Select @Name=i.[Name] from inserted i;
If (@CleanerSalary>@MaxDriverSalary)
 Begin
  Insert Into DBMS_Assign2.dbo.UnluckyCleaner 
  (BusDepotId,Name,Salary)values (@DepotId,@Name,@CleanerSalary)
  Print 'Archived into UncleanerTable'
 End
else
Insert Into DBMS_Assign2.dbo.BusCleaner 
(BusDepotId,Name,Salary)values (@DepotId,@Name,@CleanerSalary)

--9
create procedure check_dup
as
begin
WITH check_tab AS(
   SELECT [BusID], [Name], [Type], [BusDriverId], [BusCleanerId], [BusRouteId], [StartBusDepotID],
       RowNum = ROW_NUMBER()OVER(
       PARTITION BY Busid,Name,[Type],BusCleanerId,BusRouteId,[StartBusDepotID]
        ORDER BY Busid)
   FROM DBMS_Assign2.dbo.Bus
   )

--checking if count is greater tahn 1 and deleting those rows
DELETE FROM check_tab WHERE RowNum > 1
end;

--10
create trigger duplicate_check on dbms_assign2.dbo.Bus 
for insert as
if exists ( select * from dbms_assign2.dbo.Bus  inner join inserted i on i.Name = dbms_assign2.dbo.Bus.Name and i.BusCleanerID=dbms_assign2.dbo.Bus.BusCleanerID 
and i.BusID = dbms_assign2.dbo.Bus.BusID and i.BusDriverID=dbms_assign2.dbo.Bus.BusDriverID and i.[Type]=dbms_assign2.dbo.Bus.[Type] 
and i.StartBusDepotID=Bus.StartBusDepotID )
 print 'this bus is duplicate'
else
 print 'this busis new'
 
 --11
 
 CREATE PROCEDURE InsertNoDupBusTrans(@regnum VARCHAR(225),@name VARCHAR(225), @model VARCHAR(50), @type VARCHAR(25), @busdepot INT, 
@driverid INT, @cleanerid INT, @routeid INT)
AS
BEGIN
 DECLARE @count_rows INT
 BEGIN TRAN
 SELECT @count_rows = COUNT(*) FROM [DBMS_Assign2].[dbo].[Bus] 
  WHERE [Name] = @name AND
  [BusID] = @regnum AND
        [Model] = @model AND
        [Type] = @type AND
        StartBusDepotID = @busdepot AND
        [BusDriverID] = @driverid AND
        [BusCleanerID] = @cleanerid AND
        [BusRouteID] = @routeid;
 IF @count_rows < 1
  INSERT INTO [DBMS_Assign2].[dbo].[Bus]
      ([BusID]
      ,[Name]
      ,[Model]
      ,[Type]
      ,StartBusDepotID
      ,[BusDriverID]
      ,[BusCleanerID]
      ,[BusRouteID])
   VALUES (@regnum, @name, @model, @type, @busdepot, @driverid, @cleanerid, @routeid)
 ELSE 
 begin
 PRINT 'Duplicate Entry!'
    ROLLBACK TRAN
 end
COMMIT TRAN
    
END

