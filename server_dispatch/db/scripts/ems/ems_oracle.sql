BEGIN
DECLARE
cnt integer := 0;
BEGIN
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM user_tables WHERE table_name = upper( 'AN_COLLECTPOINTTABLE') );
    IF cnt != 0  THEN
        DECLARE
        s  varchar2(500);
        BEGIN
            s := 'DROP TABLE AN_COLLECTPOINTTABLE CASCADE CONSTRAINTS';
            DBMS_OUTPUT.PUT_LINE(s);
            EXECUTE IMMEDIATE  s;                
        END;
    END IF;
exception
    WHEN no_data_found  THEN
        DBMS_OUTPUT.PUT_LINE(cnt);
    END;
END;
/

CREATE TABLE AN_COLLECTPOINTTABLE(
    Ne_Ip VARCHAR(50) NOT NULL,
    Service_Type int NOT NULL,
    VLAN int,
    Rack int,
    Shelf int,
    Slot int,
    Port int,
    SyncStatus int,
    PRIMARY KEY ( Ne_Ip,Service_Type,VLAN,Rack,Shelf,Slot,Port))
/
