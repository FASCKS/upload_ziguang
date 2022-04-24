package com.example.upload_ziguang.gb1400.sqlAuto;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.upload_ziguang.gb1400.domain.TempData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("paramService")
public class SysParamServiceImpl extends ServiceImpl<SysParamDao, TempData> implements SysParamService {

    @Override
    public Integer checkSplicingModeColumn() {
        return baseMapper.checkSplicingModeColumn();
    }

    @Override
    public void addSplicingModeColumn() {
        baseMapper.addSplicingModeColumn();
    }

    @Override
    public Integer checkSoftInfoTable() {
        return baseMapper.checkSoftInfoTable();
    }

    @Override
    public void createTableSoftInfo() {
        baseMapper.createTableSoftInfo();
    }

    @Override
    public void createVehicleRestriction() {
        baseMapper.createVehicleRestriction();
    }

    @Override
    public Integer checkIllegalStopInterval() {
        return baseMapper.checkIllegalStopInterval();
    }

    @Override
    public void addIllegalStopInterval() {
        baseMapper.addIllegalStopInterval();
    }

    @Override
    public List<Map<String, Object>> checkEvidenceUniqueKey() {
        return baseMapper.checkEvidenceUniqueKey();
    }

    @Override
    public void dropAsd() {
        baseMapper.dropAsd();
    }

    @Override
    public void addFrameTrack() {
        baseMapper.addFrameTrack();
    }

    @Override
    public int checkTableColumn(String dbName, String tableName, String columnName) {
        return baseMapper.checkTableColumn(dbName, tableName, columnName);
    }

    @Override
    public void addColumn(String tableName, String columnName, String dataType, String notes) {
        baseMapper.addColumn(tableName, columnName, dataType, notes);
    }

    @Override
    public int checkEvidenceCreateTime() {
        return baseMapper.checkEvidenceCreateTime();
    }

    @Override
    public void changeEvidenceCreateTime() {
        baseMapper.changeEvidenceCreateTime();
    }

    @Override
    public void createTEvidenceDescribe() {
        baseMapper.createTEvidenceDescribe();
    }

    @Override
    public void insertTEvidenceDescribe() {
        baseMapper.insertTEvidenceDescribe();
    }

    @Override
    public void addModelType() {
        baseMapper.addModelType();
    }

    @Override
    public void createSysDeviceStatus() {
        baseMapper.createSysDeviceStatus();
    }

    @Override
    public void insertSysDeviceStatus() {
        baseMapper.insertSysDeviceStatus();
    }

    @Override
    public int checkDataCnt(String tableName) {
        return baseMapper.checkDataCnt(tableName);
    }

    @Override
    public void addIllDescribeSize() {
        baseMapper.addIllDescribeSize();
    }

    @Override
    public void addBlackCarEvidence() {
        baseMapper.addBlackCarEvidence();
    }

    @Override
    public void addBlackCarEvidenceCnt() {
        baseMapper.addBlackCarEvidenceCnt();
    }

    @Override
    public void addUploadRealId() {
        baseMapper.addUploadRealId();
    }

    @Override
    public void addHphmUploadTimeinterval() {
        baseMapper.addHphmUploadTimeinterval();
    }

    @Override
    public void createTpolice() {
        baseMapper.createTpolice();
    }

    @Override
    public void truncateTable(String tableName) {
        baseMapper.truncateTable(tableName);
    }

    @Override
    public void checkCarFaceUser() {
        baseMapper.checkCarFaceUser();
    }

    @Override
    public void checkCarFaceUserInfo() {
        baseMapper.checkCarFaceUserInfo();
    }

    @Override
    public void checkCarFaceInfo() {
        baseMapper.checkCarFaceInfo();
    }

    @Override
    public void checkBlackCarSwitch() {
        baseMapper.checkBlackCarSwitch();
    }

    @Override
    public void checkTFaceFeature() {
        baseMapper.checkTFaceFeature();
    }

    @Override
    public void checkBlackFaceSwitch() {
        baseMapper.checkBlackFaceSwitch();
    }

    @Override
    public void checkCarFaceUserChanNo() {
        baseMapper.checkCarFaceUserChanNo();
    }

    @Override
    public void checkCarFaceUserUploadTime() {
        baseMapper.checkCarFaceUserUploadTime();
    }

    @Override
    public void checkCarFaceUserUploadNum() {
        baseMapper.checkCarFaceUserUploadNum();
    }

    @Override
    public void checkUploadTime() {
        baseMapper.checkUploadTime();
    }

    @Override
    public void checkUploadNum() {
        baseMapper.checkUploadNum();
    }

    @Override
    public void checkCarFaceUserLat() {
        baseMapper.checkCarFaceUserLat();
    }

    @Override
    public void checkCarFaceUserLng() {
        baseMapper.checkCarFaceUserLng();
    }

    @Override
    public List<Map<String, Object>> checkEvidenceListUniqueKey() {
        return baseMapper.checkEvidenceListUniqueKey();
    }

    @Override
    public void addUniqueKey() {
        baseMapper.addUniqueKey();
    }

    @Override
    public void checkFeatureType() {
        baseMapper.checkFeatureType();
    }

    @Override
    public void createSysConfigUpload() {
        baseMapper.createSysConfigUpload();
    }

    @Override
    public void initData() {
        baseMapper.initData();
    }

    @Override
    public void createSysConfigUploadSwitch() {
        baseMapper.createSysConfigUploadSwitch();
    }

    @Override
    public void initSysConfigUploadSwitchData() {
        baseMapper.initSysConfigUploadSwitchData();
    }

    @Override
    public void addIsBlack() {
        baseMapper.addIsBlack();
    }

    @Override
    public void addSysConfiginfoDeviceSwitch() {
        baseMapper.addSysConfiginfoDeviceSwitch();
    }

    @Override
    public void createIsMergeSuccess() {
        baseMapper.createIsMergeSuccess();
    }

    @Override
    public void addSysConfigPadSwitch() {
        baseMapper.addSysConfigPadSwitch();
    }

    @Override
    public void addComboText() {
        baseMapper.addComboText();
    }

    @Override
    public void addSysConfigPadSwitchFace() {
        baseMapper.addSysConfigPadSwitchFace();
    }

//    @Override
//    public List<ComboVo> getComboList() {
//        return baseMapper.getComboList();
//    }

//    @Override
//    public List<ModelTypeVo> getComboItemList(Integer comboId) {
//        return baseMapper.getComboItemList(comboId);
//    }

    @Override
    public void updateAllComboChecked() {
        baseMapper.updateAllComboChecked();
    }

    @Override
    public void updateComboChecked(Integer comboId) {
        baseMapper.updateComboChecked(comboId);
    }

    @Override
    public void updateComboItemCheckedAll() {
        baseMapper.updateComboItemCheckedAll();
    }

    @Override
    public void updateComboItemChecked(Integer comboId, String mt) {
        baseMapper.updateComboItemChecked(comboId, mt);
    }

    @Override
    public void createSysParamCombo() {
        baseMapper.createSysParamCombo();
    }

    @Override
    public void insertSysParamCombo() {
        baseMapper.insertSysParamCombo();
    }

    @Override
    public void createSysParamComboItem() {
        baseMapper.createSysParamComboItem();
    }

    @Override
    public void insertSysParamComboItem() {
        baseMapper.insertSysParamComboItem();
    }

    @Override
    public void clearModelType() {
        baseMapper.clearModelType();
    }

    @Override
    public void addComboId() {
        baseMapper.addComboId();
    }

    @Override
    public void createCameraInfo() {
        baseMapper.createCameraInfo();
    }

    @Override
    public void initCameraInfo() {
        baseMapper.initCameraInfo();
    }

    @Override
    public void createDingyue() {
        baseMapper.createDingyue();
    }

    @Override
    public void addIsOpen() {
        baseMapper.addIsOpen();
    }

    @Override
    public void addSoftInfo() {
        baseMapper.addSoftInfo();
    }

    @Override
    public Integer checkNVRContent() {
        return baseMapper.checkNVRContent();
    }

    @Override
    public void addNVRContent() {
        baseMapper.addNVRContent();
    }

    @Override
    public Integer checkXjczLightEvidence() {
        return baseMapper.checkXjczLightEvidence();
    }

    @Override
    public void addXjczLightEvidence() {
        baseMapper.addXjczLightEvidence();
    }

    @Override
    public Integer checkXjczWeb() {
        return baseMapper.checkXjczWeb();
    }

    @Override
    public void addXjczWeb() {
        baseMapper.addXjczWeb();
    }

    @Override
    public void createCategoryTable() {
        baseMapper.createCategoryTable();
    }

    @Override
    public void addSysConfiginfo() {
        baseMapper.addSysConfiginfo();
    }

    @Override
    public Integer checkIPCContent() {
        return baseMapper.checkIPCContent();
    }

    @Override
    public void addIPCContent() {
        baseMapper.addIPCContent();
    }

    @Override
    public void addSerialNo() {
        baseMapper.addSerialNo();
    }

    @Override
    public void createDeleteRuleDays() {
        baseMapper.createDeleteRuleDays();
    }

    @Override
    public void addGpsOn() {
        baseMapper.addGpsOn();
    }

    @Override
    public void updateSoftName() {
        baseMapper.updateSoftName();
    }

    @Override
    public void updateSoftNameConfigIni() {
        baseMapper.updateSoftNameConfigIni();
    }

    @Override
    public void addSysConfigCmdAddblcakOn() {
        baseMapper.addSysConfigCmdAddblcakOn();
    }

    @Override
    public void addSysConfigCmdSearchcarOn() {
        baseMapper.addSysConfigCmdSearchcarOn();
    }

    @Override
    public void addSysConfigCmdAddblackcarOn() {
        baseMapper.addSysConfigCmdAddblackcarOn();
    }

    @Override
    public void addSysConfiCmdOn() {
        baseMapper.addSysConfiCmdOn();
    }

    @Override
    public void addSysConfIcmdInterval() {
        baseMapper.addSysConfIcmdInterval();
    }

    @Override
    public int geTruckCnt() {
        return baseMapper.geTruckCnt();
    }

    @Override
    public void insertTruck() {
        baseMapper.insertTruck();
    }

    @Override
    public void addTrafficSigns() {
        baseMapper.addTrafficSigns();
    }

    @Override
    public void createLightCheckReport() {
        baseMapper.createLightCheckReport();
    }

    @Override
    public void addCatchUploadOn() {
        baseMapper.addCatchUploadOn();
    }

    @Override
    public void createDdSigns() {
        baseMapper.createDdSigns();
    }

    @Override
    public void createDdRelation() {
        baseMapper.createDdRelation();
    }

    @Override
    public void addCatchUploadType() {
        baseMapper.addCatchUploadType();
    }

    @Override
    public void delIPCContent() {
        baseMapper.delIPCContent();
    }

    @Override
    public Integer checkAi() {
        return baseMapper.checkAi();
    }

    @Override
    public void addAi() {
        baseMapper.addAi();
    }

    @Override
    public Integer checkIPCbin() {
        return baseMapper.checkIPCbin();
    }

    @Override
    public Integer checkXjczUpload() {
        return baseMapper.checkXjczUpload();
    }

    @Override
    public void addXjUpload() {
        baseMapper.addXjUpload();
    }

    @Override
    public Integer checkSofitInfoData(String softFullname) {
        return baseMapper.checkSofitInfoData(softFullname);
    }

    @Override
    public void addSofitInfoData(String softName, String softFullname) {
        baseMapper.addSofitInfoData(softName, softFullname);
    }

    @Override
    public Integer checkSofitInfoVersionEmpt(String softName) {
        return baseMapper.checkSofitInfoVersionEmpt(softName);
    }

    @Override
    public void updateSofitInfoVersion(String softName, String versionName) {
        baseMapper.updateSofitInfoVersion(softName, versionName);
    }

    @Override
    public void dropRegist() {
        baseMapper.dropRegist();
    }

    @Override
    public void createRegist() {
        baseMapper.createRegist();
    }

    @Override
    public void initRegist() {
        baseMapper.initRegist();
    }

    @Override
    public void delNullSoftname() {
        baseMapper.delNullSoftname();
    }

    @Override
    public void createTTempData() {
        baseMapper.createTTempData();
    }
}