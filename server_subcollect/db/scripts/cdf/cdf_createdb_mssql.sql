 
    USE MASTER
/

IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'cdf')
    DROP DATABASE [cdf]   
/ 

declare 
  @startpos int,
  @foundpos int,
  @masterfile varchar(400),
  @sql nvarchar(800)


  begin
    set @startpos=0
    select @masterfile=filename from master.dbo.sysdatabases where dbid=1
    print @masterfile
		
    select @foundpos=charindex('\',@masterfile,@startpos)
		
    while (@foundpos<>0)
    begin
      set @startpos=@foundpos+1
      select @foundpos=charindex('\',@masterfile,@startpos)
      print @foundpos
    end
		
    set @masterfile=substring(@masterfile,1,@startpos-1)
		
    print @masterfile
    print @startpos-1
	
  end
  

set @sql='create database cdf on('
set @sql=@sql+'name='+''''+'cdf'+''''+','
set @sql=@sql+'filename='+''''+@masterfile+'cdf_Data.dbf'+''''+','
set @sql=@sql+'size=100,'
set @sql=@sql+'filegrowth=1'+')'
set @sql=@sql+'LOG ON('
set @sql=@sql+'name='+''''+'cdf_LOG'+''''+','
set @sql=@sql+'filename='+''''+@masterfile+'cdf_Log.lbf'+''''+','
set @sql=@sql+'size=100,'
set @sql=@sql+'filegrowth=10%'+')'  

print @sql
exec sp_executesql @sql

ALTER DATABASE  cdf set RECOVERY SIMPLE;
/

if  not exists (select * from master.dbo.syslogins where loginname = N'cdf')
      	exec sp_addlogin  @loginame = 'cdf', @passwd = 'U_tywg_2008' 
/
sp_password @new = 'U_tywg_2008',  @loginame ='cdf'
/

sp_defaultdb @loginame = 'cdf', @defdb='cdf'
/

use cdf
/

if  exists (select * from cdf.dbo.sysusers where name = N'cdf')
    exec sp_dropuser 'cdf'
/

EXEC sp_changedbowner  @loginame = 'cdf' , @map=true

/


    
