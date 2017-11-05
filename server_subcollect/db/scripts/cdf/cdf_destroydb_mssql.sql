 
    USE MASTER
/

IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'cdf')
    DROP DATABASE [cdf]   
/ 
    
