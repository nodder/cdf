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
