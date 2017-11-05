if exists (select * from dbo.sysobjects where id = object_id(N'AN_COLLECTPOINTTABLE') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table AN_COLLECTPOINTTABLE  
/

create TABLE AN_COLLECTPOINTTABLE(
    Ne_Ip VARCHAR(50) NOT NULL,
    Service_Type int NOT NULL,
    VLAN int,
    Rack int,
    Shelf int,
    Slot int,
    Port int,
    SyncStatus int
    CONSTRAINT PK_AN_COLLECTPOINTTABLE PRIMARY KEY 
    ( 
      Ne_Ip,Service_Type,VLAN,Rack,Shelf,Slot,Port
    ) 
)    
/
