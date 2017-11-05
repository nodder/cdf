BEGIN
DECLARE
cnt integer := 0;
BEGIN
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM user_tables WHERE table_name = 'AN_BOARDTABLE');
    IF cnt != 0  THEN
        DECLARE
        s  VARCHAR2(500);
        BEGIN
            s := 'DROP TABLE AN_BOARDTABLE';
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
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM user_tables WHERE table_name = 'AN_ADSLPORTPERFTABLE');
    IF cnt != 0  THEN
        DECLARE
        s  VARCHAR2(500);
        BEGIN
            s := 'DROP TABLE AN_ADSLPORTPERFTABLE';
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
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM user_tables WHERE table_name = 'AN_VDSLPORTPERFTABLE');
    IF cnt != 0  THEN
        DECLARE
        s  VARCHAR2(500);
        BEGIN
            s := 'DROP TABLE AN_VDSLPORTPERFTABLE';
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
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM user_tables WHERE table_name = 'AN_SHDSLPORTPERFTABLE');
    IF cnt != 0  THEN
        DECLARE
        s  VARCHAR2(500);
        BEGIN
            s := 'DROP TABLE AN_SHDSLPORTPERFTABLE';
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
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM user_tables WHERE table_name = 'AN_ETHPORTPERFTABLE');
    IF cnt != 0  THEN
        DECLARE
        s  VARCHAR2(500);
        BEGIN
            s := 'DROP TABLE AN_ETHPORTPERFTABLE';
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
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM user_tables WHERE table_name = 'AN_IGMPUSERTABLE');
    IF cnt != 0  THEN
        DECLARE
        s  VARCHAR2(500);
        BEGIN
            s := 'DROP TABLE AN_IGMPUSERTABLE';
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
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM user_tables WHERE table_name = 'AN_IGMPTRAFFICTABALE');
    IF cnt != 0  THEN
        DECLARE
        s  VARCHAR2(500);
        BEGIN
            s := 'DROP TABLE AN_IGMPTRAFFICTABALE';
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
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM user_tables WHERE table_name = 'AN_IGMPVLANTABLE');
    IF cnt != 0  THEN
        DECLARE
        s  VARCHAR2(500);
        BEGIN
            s := 'DROP TABLE AN_IGMPVLANTABLE';
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
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM user_tables WHERE table_name = 'DATA_VERSION');
    IF cnt != 0  THEN
        DECLARE
        s  VARCHAR2(500);
        BEGIN
            s := 'DROP TABLE DATA_VERSION';
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

create TABLE DATA_VERSION
(version VARCHAR(50) NOT NULL,
PRIMARY KEY(version))
/

insert into DATA_VERSION (version) values('%$VERSION_ID$%')
/


create TABLE AN_BOARDTABLE
(
Collection_Time date NOT NULL,
Device_IP VARCHAR(50) NOT NULL,
Device_Type VARCHAR(50) NOT NULL,
Measuring_Object VARCHAR(50) NOT NULL,
cpuLoad VARCHAR(50),
memUsage VARCHAR(50),
insertTime numeric(20,0) NOT NULL,
PRIMARY KEY(Collection_Time,Device_IP,Measuring_Object)
) 
/
create TABLE AN_ADSLPORTPERFTABLE
(
Collection_Time date NOT NULL,
Device_IP VARCHAR(50) NOT NULL,
Device_Type VARCHAR(50) NOT NULL,
Measuring_Object VARCHAR(50) NOT NULL,
adslAtucPerfLofs VARCHAR(50),
adslAturPerfLofs VARCHAR(50),
adslAtucPerfLoss VARCHAR(50),
adslAturPerfLoss VARCHAR(50),
adslAtucPerfLols VARCHAR(50),
adslAtucPerfLprs VARCHAR(50),
adslAturPerfLprs VARCHAR(50),
adslAtucPerfInits VARCHAR(50),
adslAtucChanReceivedBlks VARCHAR(50),
adslAturChanReceivedBlks VARCHAR(50),
adslAtucChanTransmittedBlks VARCHAR(50),
adslAturChanTransmittedBlks VARCHAR(50),
adslAtucChanCorrectedBlks VARCHAR(50),
adslAturChanCorrectedBlks VARCHAR(50),
adslAtucChanUncorrectBlks VARCHAR(50),
adslAturChanUncorrectBlks VARCHAR(50),
adslAtucPerfESs VARCHAR(50),
adslAturPerfESs VARCHAR(50),
adslAturCurrSnrMgn VARCHAR(50),
adslAtucCurrSnrMgn VARCHAR(50),
adslAturCurrAtn VARCHAR(50),
adslAtucCurrAtn VARCHAR(50),
adslAturCurrAttainableRate VARCHAR(50),
adslAtucCurrAttainableRate VARCHAR(50),
adslAturCurrOutputPwr VARCHAR(50),
adslAtucCurrOutputPwr VARCHAR(50),
adslAtucPerfStatFastR VARCHAR(50),
adslAtucPerfStatFailedFastR VARCHAR(50),
adslAtucPerfStatSesL VARCHAR(50),
adslAturPerfStatSesL VARCHAR(50),
adslAtucPerfStatUasL VARCHAR(50),
adslAturPerfStatUasL VARCHAR(50),
adslAturChanCurrTxRate VARCHAR(50),
adslAtucChanCurrTxRate VARCHAR(50),
insertTime numeric(20,0) NOT NULL,
PRIMARY KEY(Collection_Time,Device_IP,Measuring_Object)
) 
/
create TABLE AN_VDSLPORTPERFTABLE
(
Collection_Time date NOT NULL,
Device_IP VARCHAR(50) NOT NULL,
Device_Type VARCHAR(50) NOT NULL,
Measuring_Object VARCHAR(50) NOT NULL,
dsl2LnCh1DsDataRateConfPrf VARCHAR(50),
dsl2LnCh1UsDataRateConfPrf VARCHAR(50),
zxAnXdsl2LnSpectrumConfPrf VARCHAR(50),
zxAnXdsl2LnStatusAttainRateDs VARCHAR(50),
zxAnXdsl2LnStatusAttainRateUs VARCHAR(50),
zxAnXdsl2LnStatusActAtpDs VARCHAR(50),
zxAnXdsl2LnStatusActAtpUs VARCHAR(50),
insertTime numeric(20,0) NOT NULL,
PRIMARY KEY(Collection_Time,Device_IP,Measuring_Object)
) 
/
create TABLE AN_SHDSLPORTPERFTABLE
(
Collection_Time date NOT NULL,
Device_IP VARCHAR(50) NOT NULL,
Device_Type VARCHAR(50) NOT NULL,
Measuring_Object VARCHAR(50) NOT NULL,
dslStatusMaxAttainableLineRate VARCHAR(50),
hdsl2ShdslStatusActualLineRate VARCHAR(50),
hdsl2ShdslEndpointCurrAtn VARCHAR(50),
hdsl2ShdslEndpointCurrSnrMgn VARCHAR(50),
hdsl2ShdslEndpointES VARCHAR(50),
hdsl2ShdslEndpointSES VARCHAR(50),
hdsl2ShdslEndpointCRCanomalies VARCHAR(50),
hdsl2ShdslEndpointLOSWS VARCHAR(50),
hdsl2ShdslEndpointUAS VARCHAR(50),
zxAnEtherIfInOctetRate VARCHAR(50),
zxAnEtherIfOutOctetRate VARCHAR(50),
insertTime numeric(20,0) NOT NULL,
PRIMARY KEY(Collection_Time,Device_IP,Measuring_Object)
) 
/
create TABLE AN_ETHPORTPERFTABLE
(
Collection_Time date NOT NULL,
Device_IP VARCHAR(50) NOT NULL,
Device_Type VARCHAR(50) NOT NULL,
Measuring_Object VARCHAR(50) NOT NULL,
etherStatsDropEvents VARCHAR(50),
etherStatsPkts VARCHAR(50),
etherStatsCRCAlignErrors VARCHAR(50),
etherStatsUndersizePkts VARCHAR(50),
etherStatsOversizePkts VARCHAR(50),
etherStatsFragments VARCHAR(50),
etherStatsJabbers VARCHAR(50),
etherStatsPkts64Octets VARCHAR(50), 
etherStatsPkts65to127Octets VARCHAR(50),
etherStatsPkts128to255Octets VARCHAR(50),
etherStatsPkts256to511Octets VARCHAR(50),
etherStatsPkts512to1023Octets VARCHAR(50),
etherStatsPkts1024to1518Octets VARCHAR(50),
etherStatsCollisions VARCHAR(50),
ifInUcastPkts VARCHAR(50),
ifOutUcastPkts VARCHAR(50),
etherStatsMulticastPkts VARCHAR(50),
etherStatsBroadcastPkts VARCHAR(50),
ifInNUcastPkts VARCHAR(50),
ifOutNUcastPkts VARCHAR(50),
ifInDiscards VARCHAR(50),
ifOutDiscards VARCHAR(50),
zxAnEtherIfInOctetRate VARCHAR(50),
zxAnEtherIfOutOctetRate VARCHAR(50),
ifInOctets VARCHAR(50),
ifOutOctets VARCHAR(50),
etherStatsOctets VARCHAR(50),
zxAnEtherIfInBandwidthUtil VARCHAR(50),
zxAnEtherIfOutBandwidthUtil VARCHAR(50),
insertTime numeric(20,0) NOT NULL,
PRIMARY KEY(Collection_Time,Device_IP,Measuring_Object)
) 
/
create TABLE AN_IGMPUSERTABLE
(
Collection_Time date NOT NULL,
Device_IP VARCHAR(50) NOT NULL,
Device_Type VARCHAR(50) NOT NULL,
Measuring_Object VARCHAR(50) NOT NULL,
zxAnIgmpCounterRxV1Report VARCHAR(50),
zxAnIgmpCounterDropRxV1Report VARCHAR(50),
zxAnIgmpCounterRxV2Report VARCHAR(50),
zxAnIgmpCounterDropRxV2Report VARCHAR(50),
zxAnIgmpCounterRxV3Report VARCHAR(50),
zxAnIgmpCounterDropRxV3Report VARCHAR(50),
zxAnIgmpCounterRxLeave VARCHAR(50),
zxAnIgmpCounterTxCommQuery VARCHAR(50),
zxAnIgmpCounterTxSpecialQuery VARCHAR(50),
zxAnIgmpCounterDropRxUnknown VARCHAR(50),
insertTime numeric(20,0) NOT NULL,
PRIMARY KEY(Collection_Time,Device_IP,Measuring_Object)
) 
/
create TABLE AN_IGMPTRAFFICTABALE
(
Collection_Time date NOT NULL,
Device_IP VARCHAR(50) NOT NULL,
Device_Type VARCHAR(50) NOT NULL,
Measuring_Object VARCHAR(50) NOT NULL,
insertTime numeric(20,0) NOT NULL,
PRIMARY KEY(Collection_Time,Device_IP,Measuring_Object)
) 
/
create TABLE AN_IGMPVLANTABLE
(
Collection_Time date NOT NULL,
Device_IP VARCHAR(50) NOT NULL,
Device_Type VARCHAR(50) NOT NULL,
Measuring_Object VARCHAR(50) NOT NULL,
zxAnIgmpCounterRxCommQuery VARCHAR(50),
zxAnIgmpCounterTxCommQuery VARCHAR(50),
zxAnIgmpCounterRxSpecialQuery VARCHAR(50),
zxAnIgmpCounterTxSpecialQuery VARCHAR(50),
zxAnIgmpCounterRxV2Report VARCHAR(50),
zxAnIgmpCounterTxV2Report VARCHAR(50),
zxAnIgmpCounterDropRxV2Report VARCHAR(50),
zxAnIgmpCounterRxV3Report VARCHAR(50),
zxAnIgmpCounterTxV3Report VARCHAR(50),
zxAnIgmpCounterDropRxV3Report VARCHAR(50),
zxAnIgmpCounterRxLeave VARCHAR(50),
zxAnIgmpCounterTxLeave VARCHAR(50),
zxAnMvlanCurrActGroups VARCHAR(50),
zxAnMvlanActHosts VARCHAR(50),
zxAnIgmpCounterDropRxUnknown VARCHAR(50),
insertTime numeric(20,0) NOT NULL,
PRIMARY KEY(Collection_Time,Device_IP,Measuring_Object)
) 
/


