BEGIN
DECLARE
cnt integer := 0;
BEGIN
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM user_tables WHERE table_name = 'USERPORT_BRT');
    IF cnt != 0  THEN
        DECLARE
        s  VARCHAR2(500);
        BEGIN
            s := 'DROP TABLE USERPORT_BRT';
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
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM user_tables WHERE table_name = 'USERPORT_RABBIT');
    IF cnt != 0  THEN
        DECLARE
        s  VARCHAR2(500);
        BEGIN
            s := 'DROP TABLE USERPORT_RABBIT';
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

create TABLE USERPORT_BRT
(Collection_Time date NOT NULL,
Device_IP VARCHAR(50) NOT NULL,
Device_Name VARCHAR(50) NOT NULL,
Device_Type VARCHAR(50) NOT NULL,
Port_Id VARCHAR(50) NOT NULL,
ifAlias VARCHAR(50),
dsl2LnCh1DsDataRateConfPrf VARCHAR(50),
dsl2LnCh1UsDataRateConfPrf VARCHAR(50),
zxAnXdsl2LnSpectrumConfPrf VARCHAR(50),
zxAnXdsl2LnDpboConfPrf VARCHAR(50),
zxAnXdsl2LnSnrMarginConfPrf VARCHAR(50),
zxAnXdsl2LnCh1DelayInpConfPrf VARCHAR(50),
zxAnXdsl2LnStatusXtuTransSys VARCHAR(50),
zxAnXdsl2LnStatusAttainRateDs VARCHAR(50),
zxAnXdsl2LnStatusAttainRateUs VARCHAR(50),
zxAnXdsl2LnStatusActAtpDs VARCHAR(50),
zxAnXdsl2LnStatusActAtpUs VARCHAR(50),
zxAnXdsl2LnStatusElectLenDs VARCHAR(50),
zxAnXdsl2LnStatusElectLenUs VARCHAR(50),
zxAnXdsl2LnStatusLnAttenDs VARCHAR(50),
zxAnXdsl2LnStatusLnAttenUs VARCHAR(50),
zxAnXdsl2LnStatusSigAttenDs VARCHAR(50),
zxAnXdsl2LnStatusSigAttenUs VARCHAR(50),
zxAnXdsl2LnStatusSnrMarginDs VARCHAR(50),
zxAnXdsl2LnStatusSnrMarginUs VARCHAR(50),
Bond_Id VARCHAR(50),
xdsl2LInvG994VendorIdXtuc VARCHAR(50),
xdsl2LInvSystemVendorIdXtuc VARCHAR(50),
xdsl2LInvVersionNumberXtuc VARCHAR(50),
TransmissionCapabilitiesXtuc VARCHAR(50),
xdsl2LInvG994VendorIdXtur VARCHAR(50),
xdsl2LInvSystemVendorIdXtur VARCHAR(50),
xdsl2LInvVersionNumberXtur VARCHAR(50),
TransmissionCapabilitiesXtur VARCHAR(50),
xdsl2ChStatusActDataRateXtuc VARCHAR(50),
xdsl2ChStatusActDelayXtuc VARCHAR(50),
xdsl2ChStatusActInpXtuc VARCHAR(50),
xdsl2ChStatusActDataRateXtur VARCHAR(50),
xdsl2ChStatusActDelayXtur VARCHAR(50),
xdsl2ChStatusActInpXtur VARCHAR(50),
zxAnXdsl2LConfXtuTransSysEna VARCHAR(50),
zxAnXdsl2ChConfMaxDataRateDs VARCHAR(50),
zxAnXdsl2ChConfMaxDataRateUs VARCHAR(50),
zxAnXdsl2ChConfMaxDelayDs VARCHAR(50),
zxAnXdsl2ChConfMaxDelayUs VARCHAR(50),
zxAnXdsl2ChConfMinProtectDs VARCHAR(50),
zxAnXdsl2ChConfMinProtectUs VARCHAR(50),
zxAnXdsl2ChConfForceInpDs VARCHAR(50),
zxAnXdsl2ChConfForceInpUs VARCHAR(50),
gBondDownDataRate VARCHAR(50),
gBondUpDataRate VARCHAR(50),
ifOperStatus VARCHAR(50),
zxDslBoardType VARCHAR(50),
insertTime numeric(20,0) NOT NULL,
PRIMARY KEY(Device_IP,Port_Id,Collection_Time)) 
/

create TABLE USERPORT_RABBIT
(Collection_Time date NOT NULL,
Device_IP VARCHAR(50) NOT NULL,
Device_Name VARCHAR(50) NOT NULL,
Device_Type VARCHAR(50) NOT NULL,
Port_Id VARCHAR(50) NOT NULL,
ifAlias VARCHAR(50),
dsl2LnCh1DsDataRateConfPrf VARCHAR(50),
dsl2LnCh1UsDataRateConfPrf VARCHAR(50),
zxAnXdsl2LnSpectrumConfPrf VARCHAR(50),
zxAnXdsl2LnStatusXtuTransSys VARCHAR(50),
zxDslBoardType VARCHAR(50),
insertTime numeric(20,0) NOT NULL,
PRIMARY KEY(Device_IP,Port_Id,Collection_Time)) 
/



BEGIN
DECLARE
cnt integer := 0;
BEGIN
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM user_tables WHERE table_name = 'UPLINKLAG');
    IF cnt != 0  THEN
        DECLARE
        s  VARCHAR2(500);
        BEGIN
            s := 'DROP TABLE UPLINKLAG';
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
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM user_tables WHERE table_name = 'UPLINKPORT');
    IF cnt != 0  THEN
        DECLARE
        s  VARCHAR2(500);
        BEGIN
            s := 'DROP TABLE UPLINKPORT';
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
    SELECT 1 INTO cnt FROM dual WHERE exists(SELECT * FROM user_tables WHERE table_name = 'USERPORT');
    IF cnt != 0  THEN
        DECLARE
        s  VARCHAR2(500);
        BEGIN
            s := 'DROP TABLE USERPORT';
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

create TABLE UPLINKPORT
(Collection_Time date NOT NULL,
Device_Name VARCHAR(50) NOT NULL,
Device_IP VARCHAR(50) NOT NULL,
RackShelfSlotPort VARCHAR(50) NOT NULL,
ifHCOutOctets VARCHAR(50) NOT NULL,
ifHCInOctets VARCHAR(50),
ifHCOutUcastPkts VARCHAR(50),
ifHCInUcastPkts VARCHAR(50),
ifHCOutMulticastPkts VARCHAR(50),
ifHCInMulticastPkts VARCHAR(50),
ifHCOutBroadcastPkts VARCHAR(50),
ifHCInBroadcastPkts VARCHAR(50),
zxAnEtherIfOutDiscardPktRatio VARCHAR(50),
zxAnEtherIfInDiscardPktRatio VARCHAR(50),
zxAnEtherIfInErrPktRatio VARCHAR(50),
zxAnEtherIfStatOutDiscardPkt VARCHAR(50),
zxAnEtherIfStatInDiscardPkt VARCHAR(50),
zxAnEtherIfInOctetsError VARCHAR(50),
insertTime number(20),
PRIMARY KEY(Device_IP,RackShelfSlotPort,Collection_Time)) 
/

create TABLE UPLINKLAG
(Collection_Time date NOT NULL,
Device_Name VARCHAR(50) NOT NULL,
Device_IP VARCHAR(50) NOT NULL,
TrunkId VARCHAR(50) NOT NULL,
zxDslTrunkingOutOctets VARCHAR(50),
zxDslTrunkingInOctets VARCHAR(50),
zxDslTrunkingOutUCastPkts VARCHAR(50),
zxDslTrunkingInUCastPkts VARCHAR(50),
zxDslTrunkingOutMulticastPkts VARCHAR(50),
zxDslTrunkingInMulitcastPkts VARCHAR(50),
zxDslTrunkingOutBroadcastPkts VARCHAR(50),
zxDslTrunkingInBroadcastPkts VARCHAR(50),
zxDslTrunkingOutDiscardRatio VARCHAR(50),
zxDslTrunkingInDiscardRatio VARCHAR(50),
zxDslTrunkingInErrorRatio VARCHAR(50),
zxDslTrunkingOutDiscards VARCHAR(50),
zxDslTrunkingInDiscards VARCHAR(50),
zxDslTrunkingInErrors VARCHAR(50),
insertTime number(20),
PRIMARY KEY(Device_IP,TrunkId,Collection_Time)) 
/

create TABLE UPLINKPORTVLAN
(Collection_Time date NOT NULL,
Device_Name VARCHAR(50) NOT NULL,
Device_IP VARCHAR(50) NOT NULL,
NetworkVlanId VARCHAR(50) NOT NULL,
zxAnIfVlanOutOctets VARCHAR(50),
zxAnIfVlanInOctets VARCHAR(50),
zxAnIfVlanOutUnicastPkts VARCHAR(50),
zxAnIfVlanInUnicastPkts VARCHAR(50),
zxAnIfVlanOutMulticastPkts VARCHAR(50),
zxAnIfVlanInMulticastPkts VARCHAR(50),
zxAnIfVlanOutBroadcastPkts VARCHAR(50),
zxAnIfVlanInBroadcastPkts VARCHAR(50),
zxAnIfVlanOutBandwidthDisRatio VARCHAR(50),
zxAnIfVlanInBandwidthDisRatio VARCHAR(50),
zxAnIfVlanOutWredDisRatio VARCHAR(50),
zxAnIfVlanOutBandwidthDisPkts VARCHAR(50),
zxAnIfVlanInBandwidthDisPkts VARCHAR(50),
zxAnIfVlanOutWredDisPkts VARCHAR(50),
insertTime number(19),
PRIMARY KEY(Device_IP,NetworkVlanId,Collection_Time)) 
/

create TABLE UPLINKLAGVLAN
(Collection_Time date NOT NULL,
Device_Name VARCHAR(50) NOT NULL,
Device_IP VARCHAR(50) NOT NULL,
trunkAndVlan VARCHAR(50) NOT NULL,
zxDslTrunkVlanOutOctets VARCHAR(50),
zxDslTrunkVlanInOctets VARCHAR(50),
zxDslTrunkVlanOutUCastPkts VARCHAR(50),
zxDslTrunkVlanInUCastPkts VARCHAR(50),
zxDslTrunkVlanOutMCastPkts VARCHAR(50),
zxDslTrunkVlanInMCastPkts VARCHAR(50),
zxDslTrunkVlanOutBCastPkts VARCHAR(50),
zxDslTrunkVlanInBCastPkts VARCHAR(50),
zxDslTrunkVlanOutBwDisRatio VARCHAR(50),
zxDslTrunkVlanInBwDisRatio VARCHAR(50),
zxDslTrunkVlanOutWredDisRatio VARCHAR(50),
zxDslTrunkVlanOutBwDisPkts VARCHAR(50),
zxDslTrunkVlanInBwDisPkts VARCHAR(50),
zxDslTrunkVlanOutWredDisPkts VARCHAR(50),
insertTime number(19),
PRIMARY KEY(Device_IP,trunkAndVlan,Collection_Time)) 
/


create TABLE DATA_VERSION
(version VARCHAR(50) NOT NULL,
PRIMARY KEY(version))
/

insert into DATA_VERSION (version) values('%$VERSION_ID$%')
/