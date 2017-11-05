package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine;

import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>文件名称: BordTypeTranslate.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-6-28</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author ljy
 */
public class BordTypeTranslate implements IFieldDataTranslate
{
    @Override
    public String translate(String intBordType)
    {
        if(intBordType == null || intBordType.length() == 0)
        {
            return "";
        }
        return snmpOperForGetCardTypeStr(Integer.parseInt(intBordType));
    }

    /**
     * 根据板卡类型值获取板卡字符串
     * @param cardtype int
     * @return String
     */
    public static String snmpOperForGetCardTypeStr(int cardtype)
    {
        switch(cardtype)
        {
            /**
             * 9806I类型定义
             */
            case BordTypeConst.CARD_ASMVC:
                return BordTypeConst.CARD_ASMVC_STR;

            case BordTypeConst.CARD_ADSL2:
                return BordTypeConst.CARD_ADSL2_STR;

                /**
                 * 9806H,9806E类型定义
                 */
            case BordTypeConst.CARD_VETEH:
                return BordTypeConst.CARD_VETEH_STR;

            case BordTypeConst.CARD_VSTEH:
                return BordTypeConst.CARD_VSTEH_STR;

            case BordTypeConst.CARD_VSTDNA:
                return BordTypeConst.CARD_VSTDNA_STR;

            case BordTypeConst.CARD_VSTDNE:
                return BordTypeConst.CARD_VSTDNE_STR;

            case BordTypeConst.CARD_VITDNA:
                return BordTypeConst.CARD_VITDNA_STR;

            case BordTypeConst.CARD_VITDNB:
                return BordTypeConst.CARD_VITDNB_STR;

            case BordTypeConst.CARD_VITDNP:
                return BordTypeConst.CARD_VITDNP_STR;

            case BordTypeConst.CARD_ASTEJ:
                return BordTypeConst.CARD_ASTEJ_STR;

            case BordTypeConst.CARD_AITEJ:
                return BordTypeConst.CARD_AITEJ_STR;

            case BordTypeConst.CARD_SCCH:
                return BordTypeConst.CARD_SCCH_STR;

            case BordTypeConst.CARD_SCCM:
                return BordTypeConst.CARD_SCCM_STR;

            case BordTypeConst.CARD_SCCF:
                return BordTypeConst.CARD_SCCF_STR;

            case BordTypeConst.CARD_SCCB:
                return BordTypeConst.CARD_SCCB_STR;

            case BordTypeConst.CARD_SCCT:
                return BordTypeConst.CARD_SCCT_STR;

            case BordTypeConst.CARD_SCCE:
                return BordTypeConst.CARD_SCCE_STR;

            case BordTypeConst.CARD_ASTEB:
                return BordTypeConst.CARD_ASTEB_STR;

            case BordTypeConst.CARD_ASTEC:
                return BordTypeConst.CARD_ASTEC_STR;

            case BordTypeConst.CARD_AITEC:
                return BordTypeConst.CARD_AITEC_STR;

            case BordTypeConst.CARD_ASTDE:
                return BordTypeConst.CARD_ASTDE_STR;

            case BordTypeConst.CARD_VSTDB:
                return BordTypeConst.CARD_VSTDB_STR;

            case BordTypeConst.CARD_VSTDG:
                return BordTypeConst.CARD_VSTDG_STR;

            case BordTypeConst.CARD_VSTDC:
                return BordTypeConst.CARD_VSTDC_STR;

            case BordTypeConst.CARD_VITDG:
                return BordTypeConst.CARD_VITDG_STR;

            case BordTypeConst.CARD_VSTEC:
                return BordTypeConst.CARD_VSTEC_STR;

            case BordTypeConst.CARD_VPTEC:
                return BordTypeConst.CARD_VPTEC_STR;

            case BordTypeConst.CARD_VITDN:
                return BordTypeConst.CARD_VITDN_STR;

            case BordTypeConst.CARD_VITEC:
                return BordTypeConst.CARD_VITEC_STR;

            case BordTypeConst.CARD_VSTDN:
                return BordTypeConst.CARD_VSTDN_STR;

            case BordTypeConst.CARD_VSTDNP:
                return BordTypeConst.CARD_VSTDNP_STR;

            case BordTypeConst.CARD_SSTEB:
                return BordTypeConst.CARD_SSTEB_STR;

            case BordTypeConst.CARD_SSTEB2:
                return BordTypeConst.CARD_SSTEB2_STR;

            case BordTypeConst.CARD_SSTDF:
                return BordTypeConst.CARD_SSTDF_STR;

            case BordTypeConst.CARD_ATTD:
                return BordTypeConst.CARD_ATTD_STR;

            case BordTypeConst.CARD_ATTEB:
                return BordTypeConst.CARD_ATTEB_STR;

            case BordTypeConst.CARD_AETEB:
                return BordTypeConst.CARD_AETEB_STR;

            case BordTypeConst.CARD_APTEB:
                return BordTypeConst.CARD_APTEB_STR;

            case BordTypeConst.CARD_APTDE:
                return BordTypeConst.CARD_APTDE_STR;

            case BordTypeConst.CARD_ASTDD:
                return BordTypeConst.CARD_ASTDD_STR;

            case BordTypeConst.CARD_ASTBD:
                return BordTypeConst.CARD_ASTBD_STR;

            case BordTypeConst.CARD_AITEB:
                return BordTypeConst.CARD_AITEB_STR;

            case BordTypeConst.CARD_AITDB:
                return BordTypeConst.CARD_AITDB_STR;

            case BordTypeConst.CARD_ATLC:
                return BordTypeConst.CARD_ATLC_STR;

            case BordTypeConst.CARD_ATLCI:
                return BordTypeConst.CARD_ATLCI_STR;

            case BordTypeConst.CARD_ATLDZ:
                return BordTypeConst.CARD_ATLCZ_STR;

            case BordTypeConst.CARD_ATLCZ:
                return BordTypeConst.CARD_ATLCZ_STR;

            case BordTypeConst.CARD_ATLDI:
                return BordTypeConst.CARD_ATLDI_STR;

            case BordTypeConst.CARD_ATLA:
                return BordTypeConst.CARD_ATLA_STR;

            case BordTypeConst.CARD_ETCA:
                return BordTypeConst.CARD_ETCA_STR;

            case BordTypeConst.CARD_ETCF:
                return BordTypeConst.CARD_ETCF_STR;

            case BordTypeConst.CARD_ASTEF:
                return BordTypeConst.CARD_ASTEF_STR;

            case BordTypeConst.CARD_AETEF:
                return BordTypeConst.CARD_AETEF_STR;

            case BordTypeConst.CARD_AITEF:
                return BordTypeConst.CARD_AITEF_STR;

            case BordTypeConst.CARD_ASTEFP:
                return BordTypeConst.CARD_ASTEFP_STR;

            case BordTypeConst.CARD_ASTEFM:
                return BordTypeConst.CARD_ASTEFM_STR;

            case BordTypeConst.CARD_AITEFP:
                return BordTypeConst.CARD_AITEFP_STR;

            case BordTypeConst.CARD_AITEFB:
                return BordTypeConst.CARD_AITEFB_STR;

            case BordTypeConst.CARD_ETCD:
                return BordTypeConst.CARD_ETCD_STR;

            case BordTypeConst.CARD_VSTEGP:
                return BordTypeConst.CARD_VSTEGP_STR;

            case BordTypeConst.CARD_VETEG:
                return BordTypeConst.CARD_VETEG_STR;

            case BordTypeConst.CARD_VSTEG:
                return BordTypeConst.CARD_VSTEG_STR;

            case BordTypeConst.CARD_VITEGP:
                return BordTypeConst.CARD_VITEGP_STR;

            case BordTypeConst.CARD_VITEG:
                return BordTypeConst.CARD_VITEG_STR;

            case BordTypeConst.CARD_VITEGB:
                return BordTypeConst.CARD_VITEGB_STR;

            case BordTypeConst.CARD_ASTGCP:
                return BordTypeConst.CARD_ASTGCP_STR;

            case BordTypeConst.CARD_AETGC:
                return BordTypeConst.CARD_AETGC_STR;

            case BordTypeConst.CARD_ASTGC:
                return BordTypeConst.CARD_ASTGC_STR;

            case BordTypeConst.CARD_AITGCP:
                return BordTypeConst.CARD_AITGCP_STR;

            case BordTypeConst.CARD_AITGC:
                return BordTypeConst.CARD_AITGC_STR;

            case BordTypeConst.CARD_AITGCB:
                return BordTypeConst.CARD_AITGCB_STR;

            case BordTypeConst.CARD_BTLC:
                return BordTypeConst.CARD_BTLC_STR;

            case BordTypeConst.CARD_ETUC:
                return BordTypeConst.CARD_ETUC_STR;

            case BordTypeConst.CARD_BTLCE:
                return BordTypeConst.CARD_BTLCE_STR;

            case BordTypeConst.CARD_ETUCE:
                return BordTypeConst.CARD_ETUCE_STR;

            case BordTypeConst.CARD_ETUCEFM:
                return BordTypeConst.CARD_ETUCEFM_STR;

            case BordTypeConst.CARD_ETUCEFS:
                return BordTypeConst.CARD_ETUCEFS_STR;

            case BordTypeConst.CARD_ETBC:
                return BordTypeConst.CARD_ETBC_STR;

            case BordTypeConst.CARD_ETBCE:
                return BordTypeConst.CARD_ETBCE_STR;

            case BordTypeConst.CARD_ETBCEFM:
                return BordTypeConst.CARD_ETBCEFM_STR;

            case BordTypeConst.CARD_ETBCEFS:
                return BordTypeConst.CARD_ETBCEFS_STR;

            case BordTypeConst.CARD_ASTGF:
                return BordTypeConst.CARD_ASTGF_STR;

            case BordTypeConst.CARD_ASTGFP:
                return BordTypeConst.CARD_ASTGFP_STR;

            case BordTypeConst.CARD_ASTGFE:
                return BordTypeConst.CARD_ASTGFE_STR;

            case BordTypeConst.CARD_AMTGF:
                return BordTypeConst.CARD_AMTGF_STR;

            case BordTypeConst.CARD_AITGF:
                return BordTypeConst.CARD_AITGF_STR;

            case BordTypeConst.CARD_ASTGD:
                return BordTypeConst.CARD_ASTGD_STR;

            case BordTypeConst.CARD_VTTDN:
                return BordTypeConst.CARD_VTTDN_STR;

            case BordTypeConst.CARD_VTTDNP:
                return BordTypeConst.CARD_VTTDNP_STR;

            case BordTypeConst.CARD_VTTDNE:
                return BordTypeConst.CARD_VTTDNE_STR;

            case BordTypeConst.CARD_VSTEHP:
                return BordTypeConst.CARD_VSTEHP_STR;

            case BordTypeConst.CARD_VSTGC:
                return BordTypeConst.CARD_VSTGC_STR;

            case BordTypeConst.CARD_VSTGCP:
                return BordTypeConst.CARD_VSTGCP_STR;

            case BordTypeConst.CARD_VSTGCE:
                return BordTypeConst.CARD_VSTGCE_STR;

            case BordTypeConst.CARD_VSTGD:
                return BordTypeConst.CARD_VSTGD_STR;

            case BordTypeConst.CARD_VSTGDE:
                return BordTypeConst.CARD_VSTGDE_STR;

            case BordTypeConst.CARD_VSTGDP:
                return BordTypeConst.CARD_VSTGDP_STR;

            case BordTypeConst.CARD_VMTGC:
                return BordTypeConst.CARD_VMTGC_STR;

            case BordTypeConst.CARD_VMTGD:
                return BordTypeConst.CARD_VMTGD_STR;

            case BordTypeConst.CARD_VITGC:
                return BordTypeConst.CARD_VITGC_STR;

            case BordTypeConst.CARD_STTEB:
                return BordTypeConst.CARD_STTEB_STR;

            case BordTypeConst.CARD_SSTDFD:
                return BordTypeConst.CARD_SSTDFD_STR;

            case BordTypeConst.CARD_SSTDFU:
                return BordTypeConst.CARD_SSTDFU_STR;

            case BordTypeConst.CARD_SSTDFF:
                return BordTypeConst.CARD_SSTDFF_STR;

            case BordTypeConst.CARD_SSTDFFM:
                return BordTypeConst.CARD_SSTDFFM_STR;

                /**
                 * 9812和F822类型定义
                 */
            case BordTypeConst.CARD_GNI:
                return BordTypeConst.CARD_GNI_STR;

            case BordTypeConst.CARD_M24E:
                return BordTypeConst.CARD_M24E_STR;

            case BordTypeConst.CARD_V24B:
                return BordTypeConst.CARD_V24B_STR;

            case BordTypeConst.CARD_V24D:
                return BordTypeConst.CARD_V24D_STR;

            case BordTypeConst.CARD_V48B:
                return BordTypeConst.CARD_V48B_STR;

            case BordTypeConst.CARD_V24C:
                return BordTypeConst.CARD_V24C_STR;

            case BordTypeConst.CARD_MSEBA:
                return BordTypeConst.CARD_MSEBA_STR;

            case BordTypeConst.CARD_MSEBB:
                return BordTypeConst.CARD_MSEBB_STR;

            case BordTypeConst.CARD_MSEBC:
                return BordTypeConst.CARD_MSEBC_STR;

            case BordTypeConst.CARD_MSEMA:
                return BordTypeConst.CARD_MSEMA_STR;

            case BordTypeConst.CARD_MSEMB:
                return BordTypeConst.CARD_MSEMB_STR;

            case BordTypeConst.CARD_MSEMC:
                return BordTypeConst.CARD_MSEMC_STR;

            case BordTypeConst.CARD_MSEG:
                return BordTypeConst.CARD_MSEG_STR;

            case BordTypeConst.CARD_MSECAF:
                return BordTypeConst.CARD_MSECAF_STR;

            case BordTypeConst.CARD_MSECAP:
                return BordTypeConst.CARD_MSECAP_STR;

            case BordTypeConst.CARD_MSECBF:
                return BordTypeConst.CARD_MSECBF_STR;

            case BordTypeConst.CARD_MSEGB:
                return BordTypeConst.CARD_MSEGB_STR;

            case BordTypeConst.CARD_MSECBP:
                return BordTypeConst.CARD_MSECBP_STR;

            case BordTypeConst.CARD_MSECCF:
                return BordTypeConst.CARD_MSECCF_STR;

            case BordTypeConst.CARD_MSECCP:
                return BordTypeConst.CARD_MSECCP_STR;

            case BordTypeConst.CARD_MSECDP:
                return BordTypeConst.CARD_MSECDP_STR;

            case BordTypeConst.CARD_MSECEP:
                return BordTypeConst.CARD_MSECEP_STR;

            case BordTypeConst.CARD_MSECFP:
                return BordTypeConst.CARD_MSECFP_STR;

            case BordTypeConst.CARD_VSJC:
                return BordTypeConst.CARD_VSJC_STR;

            case BordTypeConst.CARD_VEJC:
                return BordTypeConst.CARD_VEJC_STR;

            case BordTypeConst.CARD_VPJC:
                return BordTypeConst.CARD_VPJC_STR;

            case BordTypeConst.CARD_VIJC:
                return BordTypeConst.CARD_VIJC_STR;

            case BordTypeConst.CARD_VDSL2_C:
                return BordTypeConst.CARD_VDSL2_C_STR;

            case BordTypeConst.CARD_VDSL2_D:
                return BordTypeConst.CARD_VDSL2_D_STR;

            case BordTypeConst.CARD_VEJCC:
                return BordTypeConst.CARD_VEJCC_STR;

            case BordTypeConst.CARD_VPJCC:
                return BordTypeConst.CARD_VPJCC_STR;

            case BordTypeConst.CARD_VIJCC:
                return BordTypeConst.CARD_VIJCC_STR;

            case BordTypeConst.CARD_V24Z:
                return BordTypeConst.CARD_V24Z_STR;

                /* F821 */
            case BordTypeConst.F821_CARD_MSVE:
                return BordTypeConst.F821_CARD_MSVE_STR;
            case BordTypeConst.F821_CARD_MSVG:
                return BordTypeConst.F821_CARD_MSVG_STR;
            case BordTypeConst.F821_CARD_HMSVE:
                return BordTypeConst.F821_CARD_HMSVE_STR;
            case BordTypeConst.F821_CARD_HMSVG:
                return BordTypeConst.F821_CARD_HMSVG_STR;
            case BordTypeConst.F821_CARD_VGNI:
                return BordTypeConst.F821_CARD_VGNI_STR;
            case BordTypeConst.F821_CARD_EI8E:
                return BordTypeConst.F821_CARD_EI8E_STR;
            case BordTypeConst.F821_CARD_HEI8E:
                return BordTypeConst.F821_CARD_HEI8E_STR;
            case BordTypeConst.F821_CARD_ETC8U:
                return BordTypeConst.F821_CARD_ETC8U_STR;
            case BordTypeConst.F821_CARD_ETC8B:
                return BordTypeConst.F821_CARD_ETC8B_STR;
            case BordTypeConst.F821_CARD_V24:
                return BordTypeConst.F821_CARD_V24_STR;
            case BordTypeConst.F821_CARD_V16:
                return BordTypeConst.F821_CARD_V16_STR;
            case BordTypeConst.F821_CARD_V08:
                return BordTypeConst.F821_CARD_V08_STR;
            case BordTypeConst.F821_CARD_V16B:
                return BordTypeConst.F821_CARD_V16B_STR;
            case BordTypeConst.F821_CARD_V08B:
                return BordTypeConst.F821_CARD_V08B_STR;
            case BordTypeConst.F821_CARD_EPS:
                return BordTypeConst.F821_CARD_EPS_Str;
            case BordTypeConst.F821_CARD_EP2S:
                return BordTypeConst.F821_CARD_EP2S_Str;
            case BordTypeConst.F821_CARD_EPSD:
                return BordTypeConst.F821_CARD_EPSD_Str;
            case BordTypeConst.F821_CARD_GPS:
                return BordTypeConst.F821_CARD_GPS_Str;
            case BordTypeConst.F821_CARD_GP2S:
                return BordTypeConst.F821_CARD_GP2S_Str;
            case BordTypeConst.F821_CARD_GPSH:
                return BordTypeConst.F821_CARD_GPSH_Str;
            case BordTypeConst.F821_CARD_SEGB:
                return BordTypeConst.F821_CARD_SEGB_STR;
            case BordTypeConst.F821_CARD_SGGA:
                return BordTypeConst.F821_CARD_SGGA_STR;
            case BordTypeConst.F821_CARD_SEGD:
                return BordTypeConst.F821_CARD_SEGD_STR;
            case BordTypeConst.F821_CARD_XEQB:
                return BordTypeConst.F821_CARD_XEQB_STR;

                /* F802 */
            case BordTypeConst.F802_CARD_M8PE:
                return BordTypeConst.F802_CARD_M8PE_STR;
            case BordTypeConst.F802_CARD_V8B:
                return BordTypeConst.F802_CARD_V8B_STR;
            case BordTypeConst.F802_CARD_GNI:
                return BordTypeConst.F802_CARD_GNI_STR;

                /* F803-16 */
            case BordTypeConst.F803_16_CARD_JL09A:
                return BordTypeConst.F803_16_CARD_JL09A_STR;
            case BordTypeConst.F803_16_CARD_JL09C:
                return BordTypeConst.F803_16_CARD_JL09C_STR;
            case BordTypeConst.F803_16_CARD_EL09A:
                return BordTypeConst.F803_16_CARD_EL09A_STR;
            case BordTypeConst.F803_16_CARD_EL09C:
                return BordTypeConst.F803_16_CARD_EL09C_STR;
            case BordTypeConst.F803_16_CARD_DL39A:
                return BordTypeConst.F803_16_CARD_DL39A_STR;
            case BordTypeConst.F803_16_CARD_DL39C:
                return BordTypeConst.F803_16_CARD_DL39C_STR;
            case BordTypeConst.F803_16_CARD_GNI:
                return BordTypeConst.F803_16_CARD_GNI_STR;
            case BordTypeConst.F803_16_CARD_M16B:
                return BordTypeConst.F803_16_CARD_M16B_STR;

                /* F803-8 */
            case BordTypeConst.F803_8_CARD_JL0EA:
                return BordTypeConst.F803_8_CARD_JL0EA_STR;
            case BordTypeConst.F803_8_CARD_EL0EA:
                return BordTypeConst.F803_8_CARD_EL0EA_STR;
            case BordTypeConst.F803_8_CARD_DL3EA:
                return BordTypeConst.F803_8_CARD_DL3EA_STR;
            case BordTypeConst.F803_8_CARD_JL0EB:
                return BordTypeConst.F803_8_CARD_JL0EB_STR;
            case BordTypeConst.F803_8_CARD_EL0EB:
                return BordTypeConst.F803_8_CARD_EL0EB_STR;
            case BordTypeConst.F803_8_CARD_DL3EB:
                return BordTypeConst.F803_8_CARD_DL3EB_STR;

                /* F803G-16 */
            case BordTypeConst.F803G_16_CARD_JL3NA:
                return BordTypeConst.F803G_16_CARD_JL3NA_STR;
            case BordTypeConst.F803G_16_CARD_EL3NA:
                return BordTypeConst.F803G_16_CARD_EL3NA_STR;
            case BordTypeConst.F803G_16_CARD_JL3NC:
                return BordTypeConst.F803G_16_CARD_JL3NC_STR;
            case BordTypeConst.F803G_16_CARD_EL3NC:
                return BordTypeConst.F803G_16_CARD_EL3NC_STR;

                /* F803G-8 */
            case BordTypeConst.F803G_8_CARD_JL3PA:
                return BordTypeConst.F803G_8_CARD_JL3PA_STR;
            case BordTypeConst.F803G_8_CARD_EL3PA:
                return BordTypeConst.F803G_8_CARD_EL3PA_STR;
            case BordTypeConst.F803G_8_CARD_JL3PB:
                return BordTypeConst.F803G_8_CARD_JL3PB_STR;
            case BordTypeConst.F803G_8_CARD_EL3PB:
                return BordTypeConst.F803G_8_CARD_EL3PB_STR;

                /* F803V1 */
            case BordTypeConst.F803V1_CARD_M16BV1:
                return BordTypeConst.F803V1_CARD_M16BV1_STR;
            case BordTypeConst.F803V1_CARD_GNIV1:
                return BordTypeConst.F803V1_CARD_GNIV1_STR;

                /* F803V3 */
            case BordTypeConst.CARD_VEPB:
                return BordTypeConst.CARD_VEPB_STR;
            case BordTypeConst.CARD_VGPB:
                return BordTypeConst.CARD_VGPB_STR;
            case BordTypeConst.CARD_MSE08:
                return BordTypeConst.CARD_MSE08_STR;
            case BordTypeConst.CARD_MSE16:
                return BordTypeConst.CARD_MSE16_STR;

                /* F402 */
            case BordTypeConst.F402_CARD_M8B:
                return BordTypeConst.F402_CARD_M8B_STR;
            case BordTypeConst.F402_CARD_GNI:
                return BordTypeConst.F402_CARD_GNI_STR;
            case BordTypeConst.F402_CARD_DL35A:
                return BordTypeConst.F402_CARD_DL35A_STR;
            case BordTypeConst.F402_CARD_DL35B:
                return BordTypeConst.F402_CARD_DL35B_STR;

                /* 9816 */
            case BordTypeConst.DSL9816_CARD_ASLEF:
                return BordTypeConst.DSL9816_CARD_ASLEF_STR;
            case BordTypeConst.DSL9816_CARD_ADSL2_24:
                return BordTypeConst.DSL9816_CARD_ADSL2_24_STR;
            case BordTypeConst.DSL9816_CARD_ADSL2_16:
                return BordTypeConst.DSL9816_CARD_ADSL2_16_STR;
            case BordTypeConst.DSL9816_CARD_V24C:
                return BordTypeConst.DSL9816_CARD_V24C_STR;
            case BordTypeConst.DSL9816_CARD_V24CA:
                return BordTypeConst.DSL9816_CARD_V24CA_STR;
            case BordTypeConst.DSL9816_CARD_V24CB:
                return BordTypeConst.DSL9816_CARD_V24CB_STR;
            case BordTypeConst.DSL9816_CARD_ASLEFAP:
                return BordTypeConst.DSL9816_CARD_ASLEFAP_STR;
            case BordTypeConst.DSL9816_CARD_ASLEFAD:
                return BordTypeConst.DSL9816_CARD_ASLEFAD_STR;
            case BordTypeConst.DSL9816_CARD_ASLEFED:
                return BordTypeConst.DSL9816_CARD_ASLEFED_STR;
            case BordTypeConst.DSL9816_CARD_ASLEFEP:
                return BordTypeConst.DSL9816_CARD_ASLEFEP_STR;
            case BordTypeConst.DSL9816_CARD_ASLEFPD:
                return BordTypeConst.DSL9816_CARD_ASLEFPD_STR;
            case BordTypeConst.DSL9816_CARD_ASLDFAP:
                return BordTypeConst.DSL9816_CARD_ASLDFAP_STR;
            case BordTypeConst.DSL9816_CARD_ASLDFAD:
                return BordTypeConst.DSL9816_CARD_ASLDFAD_STR;
            case BordTypeConst.DSL9816_CARD_ASLDFED:
                return BordTypeConst.DSL9816_CARD_ASLDFED_STR;
            case BordTypeConst.DSL9816_CARD_ASLDFEP:
                return BordTypeConst.DSL9816_CARD_ASLDFEP_STR;
            case BordTypeConst.DSL9816_CARD_ASLDFPD:
                return BordTypeConst.DSL9816_CARD_ASLDFPD_STR;
            case BordTypeConst.DSL9816_CARD_VSLECAP:
                return BordTypeConst.DSL9816_CARD_VSLECAP_STR;
            case BordTypeConst.DSL9816_CARD_VSLECEP:
                return BordTypeConst.DSL9816_CARD_VSLECEP_STR;
            case BordTypeConst.DSL9816_CARD_VSLECAD:
                return BordTypeConst.DSL9816_CARD_VSLECAD_STR;
            case BordTypeConst.DSL9816_CARD_VSLECED:
                return BordTypeConst.DSL9816_CARD_VSLECED_STR;
            case BordTypeConst.DSL9816_CARD_VSLECPD:
                return BordTypeConst.DSL9816_CARD_VSLECPD_STR;
            case BordTypeConst.DSL9816_CARD_VSLDCAP:
                return BordTypeConst.DSL9816_CARD_VSLDCAP_STR;
            case BordTypeConst.DSL9816_CARD_VSLDCEP:
                return BordTypeConst.DSL9816_CARD_VSLDCEP_STR;
            case BordTypeConst.DSL9816_CARD_VSLDCAD:
                return BordTypeConst.DSL9816_CARD_VSLDCAD_STR;
            case BordTypeConst.DSL9816_CARD_VSLDCPD:
                return BordTypeConst.DSL9816_CARD_VSLDCPD_STR;
            case BordTypeConst.DSL9816_CARD_VSLDCED:
                return BordTypeConst.DSL9816_CARD_VSLDCED_STR;
            case BordTypeConst.DSL9816_CARD_VSLGCAD:
                return BordTypeConst.DSL9816_CARD_VSLGCAD_STR;
            case BordTypeConst.DSL9816_CARD_VDSL2_16:
                return BordTypeConst.DSL9816_CARD_VDSL2_16_STR;
            case BordTypeConst.DSL9816_CARD_VDSL2_24:
                return BordTypeConst.DSL9816_CARD_VDSL2_24_STR;
            case BordTypeConst.DSL9816_CARD_VDSL2_32:
                return BordTypeConst.DSL9816_CARD_VDSL2_32_STR;
            case BordTypeConst.DSL9816_CARD_VSLGCED:
                return BordTypeConst.DSL9816_CARD_VSLGCED_STR;
            case BordTypeConst.DSL9816_CARD_VSLGCPD:
                return BordTypeConst.DSL9816_CARD_VSLGCPD_STR;
            case BordTypeConst.DSL9816_CARD_VTLDCAP:
                return BordTypeConst.DSL9816_CARD_VTLDCAP_STR;
            case BordTypeConst.DSL9816_CARD_VTLDCEP:
                return BordTypeConst.DSL9816_CARD_VTLDCEP_STR;
            case BordTypeConst.DSL9816_CARD_VTLDCAD:
                return BordTypeConst.DSL9816_CARD_VTLDCAD_STR;
            case BordTypeConst.DSL9816_CARD_VTLDCED:
                return BordTypeConst.DSL9816_CARD_VTLDCED_STR;
            case BordTypeConst.DSL9816_CARD_VTLDCPD:
                return BordTypeConst.DSL9816_CARD_VTLDCPD_STR;
            case BordTypeConst.DSL9816_CARD_VTLECAP:
                return BordTypeConst.DSL9816_CARD_VTLECAP_STR;
            case BordTypeConst.DSL9816_CARD_VTLECEP:
                return BordTypeConst.DSL9816_CARD_VTLECEP_STR;
            case BordTypeConst.DSL9816_CARD_VTLECAD:
                return BordTypeConst.DSL9816_CARD_VTLECAD_STR;
            case BordTypeConst.DSL9816_CARD_VTLECED:
                return BordTypeConst.DSL9816_CARD_VTLECED_STR;
            case BordTypeConst.DSL9816_CARD_VTLECPD:
                return BordTypeConst.DSL9816_CARD_VTLECPD_STR;
            case BordTypeConst.DSL9816_CARD_VSLGDED:
                return BordTypeConst.DSL9816_CARD_VSLGDED_STR;
            case BordTypeConst.DSL9816_CARD_VSLGDPD:
                return BordTypeConst.DSL9816_CARD_VSLGDPD_STR;
            case BordTypeConst.DSL9816_CARD_VSLGCAP:
                return BordTypeConst.DSL9816_CARD_VSLGCAP_STR;
            case BordTypeConst.DSL9816_CARD_VSLGCEP:
                return BordTypeConst.DSL9816_CARD_VSLGCEP_STR;

                /* F829 */
            case BordTypeConst.F829_CARD_MSB:
                return BordTypeConst.F829_CARD_MSB_STR;
            case BordTypeConst.F829_CARD_MSBT:
                return BordTypeConst.F829_CARD_MSBT_STR;
            case BordTypeConst.F829_CARD_FE8S:
                return BordTypeConst.F829_CARD_FE8S_STR;
            case BordTypeConst.F829_CARD_CES4:
                return BordTypeConst.F829_CARD_CES4_STR;
            case BordTypeConst.F829_CARD_CES8:
                return BordTypeConst.F829_CARD_CES8_STR;
            case BordTypeConst.F829_CARD_CES8B:
                return BordTypeConst.F829_CARD_CES8B_STR;
                //下面6种板卡同时适用于F809网元
            case BordTypeConst.F829_CARD_EPB:
                return BordTypeConst.F829_CARD_EPB_STR;
            case BordTypeConst.F829_CARD_EPBC:
                return BordTypeConst.F829_CARD_EPBC_STR;
            case BordTypeConst.F829_CARD_EPBD:
                return BordTypeConst.F829_CARD_EPBD_STR;
            case BordTypeConst.F829_CARD_GPB:
                return BordTypeConst.F829_CARD_GPB_STR;
            case BordTypeConst.F829_CARD_GPBC:
                return BordTypeConst.F829_CARD_GPBC_STR;
            case BordTypeConst.F829_CARD_GPBD:
                return BordTypeConst.F829_CARD_GPBD_STR;
            case BordTypeConst.F829_CARD_GPAS:
                return BordTypeConst.F829_CARD_GPAS_STR;
            case BordTypeConst.F829_CARD_GPAC:
                return BordTypeConst.F829_CARD_GPAC_STR;
            case BordTypeConst.F829_CARD_GPA:
                return BordTypeConst.F829_CARD_GPA_STR;

                /* F822P */
            case BordTypeConst.F822P_CARD_M08:
                return BordTypeConst.F822P_CARD_M08_STR;
            case BordTypeConst.F822P_CARD_M16:
                return BordTypeConst.F822P_CARD_M16_STR;

                /* F809 */
            case BordTypeConst.F809_CARD_MBSS:
                return BordTypeConst.F809_CARD_MBSS_STR;
            case BordTypeConst.F809_CARD_COM4:
                return BordTypeConst.F809_CARD_COM4_STR;
            case BordTypeConst.F809_CARD_MBS:
                return BordTypeConst.F809_CARD_MBS_STR;
            case BordTypeConst.F809_CARD_COM2:
                return BordTypeConst.F809_CARD_COM2_STR;

                /* F823 */
            case BordTypeConst.F823_CARD_MSVC:
                return BordTypeConst.F823_CARD_MSVC_STR;
            case BordTypeConst.F823_CARD_EI8EP:
                return BordTypeConst.F823_CARD_EI8EP_STR;
            case BordTypeConst.F823_CARD_ETC8B1:
                return BordTypeConst.F823_CARD_ETC8B1_STR;
            case BordTypeConst.F823_CARD_VE8:
                return BordTypeConst.F823_CARD_VE8_STR;

                /* 9836 */
            case BordTypeConst.DSL9836_CARD_SCMF:
                return BordTypeConst.DSL9836_CARD_SCMF_STR;
            case BordTypeConst.DSL9836_CARD_APTGC:
                return BordTypeConst.DSL9836_CARD_APTGC_STR;

                /* MSG5208 */
            case BordTypeConst.MSG5208_CARD_OMCU:
                return BordTypeConst.MSG5208_CARD_OMCU_STR;
            case BordTypeConst.MSG5208_CARD_OVFVC:
                return BordTypeConst.MSG5208_CARD_OVFVC_STR;
            case BordTypeConst.MSG5208_CARD_OASVC:
                return BordTypeConst.MSG5208_CARD_OASVC_STR;
            case BordTypeConst.MSG5208_CARD_OPPVB:
                return BordTypeConst.MSG5208_CARD_OPPVB_STR;
                /* EC4023 */
            case BordTypeConst.EC4023_CARD_TYPE_CCE:
                return BordTypeConst.EC4023_STR_CARD_TYPE_CCE;
            case BordTypeConst.EC4023_CARD_TYPE_LBE:
                return BordTypeConst.EC4023_STR_CARD_TYPE_LBE;
                /* EC4026 */
            case BordTypeConst.EC4026_CARD_TYPE_CDZ:
                return BordTypeConst.EC4026_STR_CARD_TYPE_CDZ;
            case BordTypeConst.EC4026_CARD_TYPE_LCZ:
                return BordTypeConst.EC4026_STR_CARD_TYPE_LCZ;
            case BordTypeConst.EC4026_CARD_TYPE_LDZ:
                return BordTypeConst.EC4026_STR_CARD_TYPE_LDZ;
            default:
                return String.valueOf(cardtype);
        }
    }
}
