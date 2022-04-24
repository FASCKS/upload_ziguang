package com.example.upload_ziguang.gb1400.sqlAuto;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.upload_ziguang.gb1400.domain.TempData;

import java.util.List;
import java.util.Map;


/**
 * 车载取证模式
 *
 * @author afeng
 * @email chenbin824@163.com
 * @date 2020-12-28 14:44:58
 */
public interface SysParamService extends IService<TempData> {

    public Integer checkSplicingModeColumn();

    public void addSplicingModeColumn();

    public Integer checkSoftInfoTable();

    public void createTableSoftInfo();
    public void createVehicleRestriction();

    public Integer checkIllegalStopInterval();

    public void addIllegalStopInterval();

    public List<Map<String, Object>> checkEvidenceUniqueKey();

    void dropAsd();

    void addFrameTrack();

    int checkTableColumn(String dbName, String tableName, String columnName);

    void addColumn(String tableName, String columnName, String dataType, String notes);

    int checkEvidenceCreateTime();

    void changeEvidenceCreateTime();

    void createTEvidenceDescribe();

    void insertTEvidenceDescribe();

    void addModelType();

    void createSysDeviceStatus();

    void insertSysDeviceStatus();

    /**
     * 查看表中的记录数
     * @param tableName
     * @return
     */
    int checkDataCnt(String tableName);

    void addIllDescribeSize();

    void addBlackCarEvidence();

    void addBlackCarEvidenceCnt();

    void addUploadRealId();

    void addHphmUploadTimeinterval();

    void createTpolice();

    void truncateTable(String tableName);

    void checkCarFaceUser();

    void checkCarFaceUserInfo();

    void checkCarFaceInfo();

    void checkBlackCarSwitch();

    void checkTFaceFeature();

    void checkBlackFaceSwitch();

    void checkCarFaceUserChanNo();

    void checkCarFaceUserUploadTime();

    void checkCarFaceUserUploadNum();

    void checkUploadTime();

    void checkUploadNum();

    void checkCarFaceUserLat();

    void checkCarFaceUserLng();

    List<Map<String, Object>> checkEvidenceListUniqueKey();

    void addUniqueKey();

    void checkFeatureType();

    void createSysConfigUpload();

    void initData();

    void createSysConfigUploadSwitch();

    void initSysConfigUploadSwitchData();

    void addIsBlack();

    void addSysConfiginfoDeviceSwitch();

    void createIsMergeSuccess();

    void addSysConfigPadSwitch();

    void addComboText();

    void addSysConfigPadSwitchFace();

//    List<ComboVo> getComboList();

//    List<ModelTypeVo> getComboItemList(Integer comboId);

    void updateAllComboChecked();

    void updateComboChecked(Integer comboId);

    void updateComboItemCheckedAll();

    void updateComboItemChecked(Integer comboId, String mt);

    void createSysParamCombo();

    void insertSysParamCombo();

    void createSysParamComboItem();

    void insertSysParamComboItem();

    void clearModelType();

    void addComboId();

    void createCameraInfo();

    void initCameraInfo();

    void createDingyue();

    void addIsOpen();

    void addSoftInfo();

    Integer checkNVRContent();

    void addNVRContent();

    Integer checkXjczLightEvidence();

    void addXjczLightEvidence();

    Integer checkXjczWeb();

    void addXjczWeb();

    void createCategoryTable();

    void addSysConfiginfo();

    Integer checkIPCContent();

    void addIPCContent();

    void addSerialNo();

    void createDeleteRuleDays();

    void addGpsOn();

    void updateSoftName();

    void updateSoftNameConfigIni();

    void addSysConfigCmdAddblcakOn();

    void addSysConfigCmdSearchcarOn();

    void addSysConfigCmdAddblackcarOn();

    void addSysConfiCmdOn();

    void addSysConfIcmdInterval();

    int geTruckCnt();

    void insertTruck();

    void addTrafficSigns();

    void createLightCheckReport();

    void addCatchUploadOn();

    void createDdSigns();

    void createDdRelation();

    void addCatchUploadType();

    void delIPCContent();

    Integer checkAi();

    void addAi();

    Integer checkIPCbin();

    Integer checkXjczUpload();

    void addXjUpload();

    Integer checkSofitInfoData(String softFullname);

    void addSofitInfoData(String softName, String softFullname);

    Integer checkSofitInfoVersionEmpt(String softName);

    void updateSofitInfoVersion(String softName, String versionName);

    void dropRegist();

    void createRegist();

    void initRegist();

    void delNullSoftname();

    void createTTempData();
}

