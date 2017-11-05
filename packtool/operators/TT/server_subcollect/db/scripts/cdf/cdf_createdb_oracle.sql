BEGIN
DECLARE
cnt integer := 0;
BEGIN
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM ALL_USERS WHERE USERNAME = UPPER(TRIM('cdf')));
    IF cnt != 0  THEN
        DECLARE
        s  VARCHAR2(500);
        BEGIN
            s := 'DROP USER cdf CASCADE';
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


BEGIN
DECLARE
cnt integer := 0;
BEGIN
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM user_tablespaces WHERE tablespace_name = UPPER(TRIM('cdf')));
    IF cnt != 0  THEN
        DECLARE
        s  VARCHAR2(500);
        BEGIN
            s := 'DROP TABLESPACE CDF INCLUDING CONTENTS AND DATAFILES';
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

create tablespace cdf
  datafile '%$DBPATH$%cdf.dbf' size 100M REUSE
   AUTOEXTEND ON NEXT 10M
   ONLINE
   PERMANENT
/

CREATE USER cdf  PROFILE "DEFAULT" 
    IDENTIFIED BY "U_tywg_2008" DEFAULT TABLESPACE cdf
    ACCOUNT UNLOCK
/


GRANT "CONNECT" TO cdf
/
GRANT "RESOURCE" TO cdf
/
GRANT UNLIMITED TABLESPACE TO cdf
/
GRANT "DBA" TO cdf
/
GRANT ALTER ANY INDEX TO cdf
/
GRANT ALTER ANY TABLE TO cdf
/
GRANT ALTER ANY PROCEDURE TO cdf
/
GRANT CREATE ANY INDEX TO cdf
/
GRANT CREATE ANY PROCEDURE TO cdf
/
GRANT CREATE ANY TABLE TO cdf
/
GRANT DROP ANY INDEX TO cdf
/
GRANT DROP ANY PROCEDURE TO cdf
/
GRANT DROP ANY TABLE TO cdf
/
GRANT EXECUTE ANY PROCEDURE TO cdf
/
GRANT SELECT ANY TABLE TO cdf
/

BEGIN
DBMS_OUTPUT.PUT_LINE('-------');
DBMS_OUTPUT.PUT_LINE('END-ORA');
DBMS_OUTPUT.PUT_LINE('-------');

END;
/
