package com.example.upload_ziguang.gb1400.sqlAuto;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.upload_ziguang.gb1400.domain.TempData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 车载取证模式
 * 
 * @author afeng
 * @email chenbin824@163.com
 * @date 2020-12-28 14:44:58
 */
@Mapper
public interface SysParamDao extends BaseMapper<TempData> {

    @Select ("select count(1) from information_schema.columns \n" +
            "where table_name='sys_param' and table_schema='db_light' and column_name = 'splicing_mode';")
    int checkSplicingModeColumn();

    @Select("alter table sys_param add column splicing_mode int default 0;")
    void addSplicingModeColumn();

    @Select("select count(*) from information_schema.TABLES where table_schema='db_light' and table_name='soft_info';")
    Integer checkSoftInfoTable();

    @Select("create table soft_info(\n" +
            "    id           int auto_increment primary key,\n" +
            "    soft_name    varchar(50)   null comment '软件名称',\n" +
            "    soft_fullname    varchar(50)   null comment '软件全名称',\n" +
            "    datetime     datetime      null,\n" +
            "    description  varchar(200)  null comment '描述',\n" +
            "    url          varchar(200)  null comment '下载连接地址',\n" +
            "    user         varchar(50)   null,\n" +
            "    pwd          varchar(50)   null,\n" +
            "    version      int default 0 null,\n" +
            "    tag          varchar(100)  null,\n" +
            "    md          varchar(50)  null\n" +
            ")comment '软件版本信息';")
    void createTableSoftInfo();



    @Update("CREATE TABLE if not exists `t_vehicle_restriction` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `cycle_type` varchar(50) DEFAULT NULL COMMENT '周期，\\n“W”代表以周为周期，\\n“M”代表以月为周期,\\n“1”代表日期为单数,\\n“0”代表日期为双数',\n" +
            "  `cycle_date` int(2) DEFAULT NULL COMMENT '日期，配合周期使用：\\n周期为“W”时，“0”代表周日，“1”-“6”分别代表周一至周六\\n周期为“M”时，“1”-“31”分别代表每月的日期\\n周期为“1”或“0”时，本值无意义，可取“0”值',\n" +
            "  `st_time_start` time DEFAULT NULL COMMENT '时段1开始时间，整天禁行可设“0:00”至“24:00”',\n" +
            "  `st_time_end` time DEFAULT NULL COMMENT '时段1结束时间',\n" +
            "  `nd_time_start` time DEFAULT NULL COMMENT '时段2开始时间，时段2不使用可设为“0:00”-“0:00”',\n" +
            "  `nd_time_end` time DEFAULT NULL COMMENT '时段2结束时间',\n" +
            "  `restriction_num` varchar(100) DEFAULT NULL COMMENT '禁行尾号：禁行的尾号列表，多个尾号可连续排列，如“15”（代表1和5）、“13579ABCDEFGHJKLMN”、“02468PQRSTUVWXYZ”等',\n" +
            "  `only_num` int(1) DEFAULT NULL COMMENT '仅含数字：\\n“1”代表限行车牌尾号仅为数字，若车牌最后一位为字母，则从后找到最后一位数字为止\\n“0”代表限行车牌尾号可以是数字和字母',\n" +
            "  `only_local_num` varchar(100) DEFAULT NULL COMMENT '仅本地车牌：备用，暂无作用',\n" +
            "  `only_white_list` varchar(100) DEFAULT NULL COMMENT '仅白名单：备用，暂无作用',\n" +
            "  PRIMARY KEY (`id`) USING BTREE\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='车牌尾号限行规则表'")
    void createVehicleRestriction();

    @Select("select count(1) from information_schema.`COLUMNS` c where table_name='sys_param'\n" +
            "and table_schema='db_light' and column_name = 'illegal_stop_interval'")
    Integer checkIllegalStopInterval();

    @Select("ALTER TABLE sys_param ADD illegal_stop_interval INT(6) DEFAULT 0 NULL COMMENT '二次违停时间间隔'")
    void addIllegalStopInterval();

    @Select("show keys from t_evidence")
    List<Map<String, Object>> checkEvidenceUniqueKey();

    @Select("ALTER TABLE t_evidence DROP KEY asd")
    void dropAsd();

    @Select("ALTER TABLE t_evidence ADD CONSTRAINT frame_track UNIQUE KEY (frame_id,track_id)")
    void addFrameTrack();

    @Select("select count(1) from information_schema.columns where  table_schema=#{dbName} and table_name=#{tableName} and column_name = #{columnName}")
    int checkTableColumn(@Param("dbName") String dbName, @Param("tableName")String tableName, @Param("columnName")String columnName);

    @Select("ALTER TABLE ${tableName} ADD ${columnName} ${dataType} NULL COMMENT '${notes}'")
    void addColumn(@Param("tableName")String tableName, @Param("columnName")String columnName,
                   @Param("dataType")String dataType, @Param("notes")String notes);

    @Select("select count(1) from information_schema.columns where table_schema= 'db_light' and table_name= 't_evidence' and column_name = 'create_time' and  DATETIME_PRECISION  = 3")
    int checkEvidenceCreateTime();

    @Select("ALTER TABLE db_light.t_evidence MODIFY COLUMN create_time datetime(3) NULL;")
    void changeEvidenceCreateTime();

    @Select("CREATE TABLE `t_evidence_describe` (\n" +
            "  `id` int(3) NOT NULL AUTO_INCREMENT,\n" +
            "  `model_id` int(11) DEFAULT NULL COMMENT '违章类型id',\n" +
            "  `model_name` varchar(100) DEFAULT NULL COMMENT '违章类型名称',\n" +
            "  `code` varchar(100) DEFAULT '' COMMENT '违法代码',\n" +
            "  `ill_describe` varchar(100) DEFAULT '' COMMENT '违法描述',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='违法类型对应违法代码，描述'")
    void createTEvidenceDescribe();

    @Select("INSERT INTO db_light.t_evidence_describe (model_id,model_name,code,ill_describe) VALUES \n" +
            "(1,'左侧单次违停','','')\n" +
            ",(2,'右侧单次违停','','')\n" +
            ",(3,'左侧二次违停','','')\n" +
            ",(4,'右侧二次违停','','')\n" +
            ",(5,'左侧违停驱离','','')\n" +
            ",(6,'右侧违停驱离','','')\n" +
            ",(7,'左侧鹰眼模式','','')\n" +
            ",(8,'右侧鹰眼模式','','')\n" +
            ",(9,'来向逆向行驶','','')\n" +
            ",(10,'去向逆向行驶','','')\n" +
            ",(11,'尾号车牌限行','','')\n" +
            ",(12,'交通号牌限行','','')\n" +
            ",(13,'斑马线不礼让行人','','')\n" +
            ",(14,'左侧占用专用车道行驶','','')\n" +
            ",(15,'右侧占用专用车道行驶','','');")
    void insertTEvidenceDescribe();

//    @Select("ALTER TABLE t_evidence ADD model_type INT(2) DEFAULT -1 NULL COMMENT '违章类型(对应sys_model表)'")
    @Select("ALTER TABLE t_evidence DROP COLUMN model_type;")
    void addModelType();

    @Select("CREATE TABLE `sys_device_status` (\n" +
            "  `id` int(3) NOT NULL AUTO_INCREMENT,\n" +
            "  `device_name` varchar(100) DEFAULT NULL,\n" +
            "  `device_ip` varchar(100) DEFAULT NULL,\n" +
            "  `status` int(1) DEFAULT '0' COMMENT '0故障，1正常',\n" +
            "  `device_switch` int(1) DEFAULT '1' COMMENT '设备是否启用的开关，0关，1开',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='设备状态，用于开机自检，看ping通不通'")
    void createSysDeviceStatus();

    @Select("INSERT INTO sys_device_status (id,device_name,device_ip,status,device_switch) VALUES \n" +
            "(1,'摄像头左前','192.168.0.101',0,1)\n" +
            ",(2,'摄像头前中','192.168.0.102',0,1)\n" +
            ",(3,'摄像头右前','192.168.0.103',0,1)\n" +
            ",(4,'摄像头左中','192.168.0.104',0,1)\n" +
            ",(5,'摄像头顶','192.168.0.105',0,1)\n" +
            ",(6,'摄像头右中','192.168.0.106',0,1)\n" +
            ",(7,'摄像头左后','192.168.0.107',0,1)\n" +
            ",(8,'摄像头后中','192.168.0.108',0,1)\n" +
            ",(9,'摄像头右后','192.168.0.109',0,1)\n" +
            ",(10,'朝前车内摄像头','192.168.0.110',0,1)\n" +
            ",(11,'车尾摄像头','192.168.0.111',0,1)\n" +
            ",(12,'NVR','192.168.0.91',0,1)\n" +
            ",(13,'PI','192.168.0.92',0,1)\n" +
            ",(14,'PAD','192.168.0.161',0,1)\n" +
            ",(15,'后视镜','192.168.0.162',0,1)\n" +
            ",(16,'4G路由器','192.168.0.1',0,1)\n" +
            ",(17,'后备箱','192.168.0.93',0,1);")
    void insertSysDeviceStatus();

    @Select("select count(1) from ${tableName}")
    int checkDataCnt(String tableName);

    @Select("ALTER TABLE t_evidence_describe MODIFY COLUMN ill_describe varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' NULL COMMENT '违法描述'")
    void addIllDescribeSize();

    @Select("ALTER TABLE car_black_car_list ADD evidence varchar(200) DEFAULT '' NULL COMMENT '违章'")
    void addBlackCarEvidence();

    @Select("ALTER TABLE car_black_car_list ADD evidence_cnt INT(11) DEFAULT 0 NULL COMMENT '违章次数'")
    void addBlackCarEvidenceCnt();

    @Select("ALTER TABLE t_evidence ADD upload_real_id BIGINT NULL COMMENT '实际上传的车牌id'")
    void addUploadRealId();

    @Select("ALTER TABLE db_light.sys_configinfo ADD hphm_upload_timeinterval INT(11) DEFAULT 30 NULL COMMENT '车牌上传，设定的时间内不会重复上传，单位：分钟'")
    void addHphmUploadTimeinterval();

    @Select("CREATE TABLE `t_police` (\n" +
            "  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
            "  `start_time` timestamp NULL DEFAULT NULL,\n" +
            "  `end_time` timestamp NULL DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='出警的开始，结束时间'")
    void createTpolice();

    @Select("truncate table ${tableName}")
    void truncateTable(String tableName);

    @Select("CREATE TABLE `car_face_user` (\n" +
            "  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
            "  `check_time` varchar(255) DEFAULT NULL,\n" +
            "  `dev_id` varchar(255) DEFAULT NULL COMMENT '设备id',\n" +
            "  `msg_id` varchar(255) DEFAULT NULL,\n" +
            "  `check_image_str` varchar(255) DEFAULT NULL,\n" +
            "  `panorama_image_str` varchar(255) DEFAULT NULL,\n" +
            "  `is_black` int(1) DEFAULT NULL COMMENT '人员类型 0:白名单,1:黑名单,2:陌生人,3:临时人员',\n" +
            "  `is_uploaded` int(1) DEFAULT '0' COMMENT '0未上传，1已上传',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8")
    void checkCarFaceUser();

    @Select("CREATE TABLE `car_face_user_info` (\n" +
            "  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
            "  `user_id` varchar(255) DEFAULT NULL,\n" +
            "  `car_black_face_info_id` bigint(20) DEFAULT NULL,\n" +
            "  `user_info` int(11) DEFAULT NULL COMMENT '数量',\n" +
            "  `user_name` varchar(255) DEFAULT NULL,\n" +
            "  `user_sex` int(11) DEFAULT NULL,\n" +
            "  `context` varchar(255) DEFAULT NULL,\n" +
            "  `label` int(11) DEFAULT NULL,\n" +
            "  `similarity` varchar(255) DEFAULT NULL,\n" +
            "  `update_time` datetime DEFAULT NULL,\n" +
            "  `user_age` int(11) DEFAULT NULL,\n" +
            "  `user_image_str` varchar(255) DEFAULT NULL,\n" +
            "  `car_face_user_id` bigint(20) DEFAULT NULL,\n" +
            "  `is_black` int(11) DEFAULT NULL COMMENT '人员类型 0:白名单,1:黑名单,2:陌生人,3:临时人员',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8")
    void checkCarFaceUserInfo();

    @Select("CREATE TABLE `car_face_info` (\n" +
            "  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
            "  `user_name` varchar(50) DEFAULT NULL COMMENT '名称',\n" +
            "  `sex` int(11) DEFAULT NULL COMMENT '性别 -1:未知,0:女,1:男',\n" +
            "  `age` int(11) DEFAULT NULL COMMENT '年龄',\n" +
            "  `type` int(11) DEFAULT NULL COMMENT '人员类型 0:白名单,1:黑名单,2:陌生人,3:临时人员',\n" +
            "  `state` int(11) DEFAULT NULL COMMENT '人脸导入状态 0:已导入 1:未导入',\n" +
            "  `created_time` datetime DEFAULT CURRENT_TIMESTAMP,\n" +
            "  `birthday` varchar(255) DEFAULT NULL COMMENT '生日',\n" +
            "  `group_name` varchar(255) DEFAULT NULL COMMENT '部门名称',\n" +
            "  `phone` varchar(255) DEFAULT NULL COMMENT '联系方式',\n" +
            "  `addr` varchar(255) DEFAULT NULL COMMENT '地址',\n" +
            "  `img_list` varchar(255) DEFAULT NULL COMMENT '图片地址',\n" +
            "  `expires` bigint(20) DEFAULT NULL COMMENT '失效时间 0：永久有效，非零为失效时间戳',\n" +
            "  `context` varchar(255) DEFAULT NULL COMMENT '用户自定义数据',\n" +
            "  `info_code` varchar(255) DEFAULT '' COMMENT '图片特征码',\n" +
            "  `origin` int(2) DEFAULT NULL COMMENT '1下发，2本地上传，3抓拍',\n" +
            "  `image_path` text COMMENT '图片路径',\n" +
            "  `image_path_list` varchar(100) DEFAULT NULL COMMENT '返回给前段的路径数组（此处不存数据）',\n" +
            "  PRIMARY KEY (`user_id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8")
    void checkCarFaceInfo();

    @Select("ALTER TABLE sys_configinfo ADD black_car_switch INT DEFAULT 0 NULL COMMENT '黑车开关，0关，1开';")
    void checkBlackCarSwitch();

    @Select("CREATE TABLE `t_face_feature` (\n" +
            "  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
            "  `evidence_id` bigint(20) DEFAULT NULL,\n" +
            "  `feature_code` text,\n" +
            "  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='人脸，特征值对应表'")
    void checkTFaceFeature();

    @Select("ALTER TABLE sys_configinfo ADD black_face_switch INT(1) DEFAULT 0 NULL COMMENT '黑脸开关，0关，1开'")
    void checkBlackFaceSwitch();

    @Select("ALTER TABLE car_face_user ADD chan_no INT(2) NULL COMMENT '盒子的通道号，1对应192.169.0.101，2对应102，以此类推';")
    void checkCarFaceUserChanNo();

    @Select("ALTER TABLE car_face_user ADD upload_time DATETIME NULL COMMENT '上传时间';")
    void checkCarFaceUserUploadTime();

    @Select("ALTER TABLE car_face_user ADD upload_num INT(2) DEFAULT 0 NULL COMMENT '上传次数 5次为上限';")
    void checkCarFaceUserUploadNum();

    @Select("ALTER TABLE param_evidence ADD upload_time DATETIME NULL COMMENT '上传时间';")
    void checkUploadTime();

    @Select("ALTER TABLE param_evidence ADD upload_num int(2) DEFAULT 0 NULL COMMENT '上传次数 5次为上限';")
    void checkUploadNum();

    @Select("ALTER TABLE car_face_user ADD lat varchar(100) NULL;")
    void checkCarFaceUserLat();

    @Select("ALTER TABLE car_face_user ADD lng varchar(100) NULL;")
    void checkCarFaceUserLng();

    @Select("show keys from t_evidence_list")
    List<Map<String, Object>> checkEvidenceListUniqueKey();

    @Select("create index t_evidence_list_evidence_id_path_ip_index on t_evidence_list (evidence_id, path, ip);")
    void addUniqueKey();

    @Select("ALTER TABLE db_light.t_face_feature ADD feature_type INT(1) DEFAULT 0 NULL COMMENT '0普通摄像头拍的人脸，1人脸盒子推的人脸';")
    void checkFeatureType();

    @Select("CREATE TABLE `sys_config_upload` (\n" +
            "  `id` int(3) NOT NULL AUTO_INCREMENT,\n" +
            "  `upload_ip` varchar(255) DEFAULT '' COMMENT '上传目标服务器ip',\n" +
            "  `upload_port` varchar(255) DEFAULT '' COMMENT '上传目标服务器端口',\n" +
            "  `upload_memo` varchar(255) DEFAULT '' COMMENT '上传服务器备注',\n" +
            "  `switch_name` varchar(255) DEFAULT '' COMMENT '开关名称',\n" +
            "  `is_chose` int(1) DEFAULT '0' COMMENT '最后一次选中状态，0是未选中，1是选中',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='上传种类表'")
    void createSysConfigUpload();

    @Select("INSERT INTO sys_config_upload (id,upload_ip,upload_port,upload_memo,switch_name,is_chose) VALUES \n" +
            "(1,'221.12.76.40','9090','','自己平台',0)\n" +
            ",(2,'127.0.0.1','7000','','1400平台',0)\n" +
            ",(3,'127.0.0.1','9000','','其他平台',0);")
    void initData();

    @Select("CREATE TABLE `sys_config_upload_switch` (\n" +
            "  `id` int(8) NOT NULL AUTO_INCREMENT,\n" +
            "  `switch_name` varchar(255) DEFAULT NULL COMMENT '开关名称',\n" +
            "  `switch_status` varchar(255) DEFAULT NULL COMMENT '开关状态，0是关，1是开',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='上传开关表'")
    void createSysConfigUploadSwitch();

    @Select("INSERT INTO sys_config_upload_switch (id,switch_name,switch_status) VALUES \n" +
            "(1,'车牌上传','0')\n" +
            ",(2,'人脸上传','0')\n" +
            ",(3,'盒子人脸上传','0')\n" +
            ",(4,'违法数据拼合上传','0');")
    void initSysConfigUploadSwitchData();

    @Select("ALTER TABLE db_light.t_evidence ADD is_black_car INT(1) DEFAULT 0 NULL COMMENT '0未分类，人脸，1黑车牌（仅用作黑车和普通车的上传区分用），2普通车牌';")
    void addIsBlack();

    @Select("ALTER TABLE db_light.sys_configinfo ADD device_switch INT(1) DEFAULT 0 NULL COMMENT '设备自检上传开关，0关闭，1开启';")
    void addSysConfiginfoDeviceSwitch();

    @Select("ALTER TABLE param_evidence ADD is_merge_success INT(1) DEFAULT 1 NULL COMMENT '拼合是否成功，0失败，1成功';")
    void createIsMergeSuccess();

    @Select("ALTER TABLE db_light.sys_configinfo ADD pad_queue_switch INT(1) DEFAULT 1 NULL COMMENT '0关闭，1开启。平板队列开关，如果关闭，就不往平板队列里写数据。';")
    void addSysConfigPadSwitch();

    @Select("update sys_param set combo_list_str = '[{\"modelTypeList\":[{\"modelName\":\"左侧单次违停\",\"modelType\":1,\"isChecked\":0,\"remarks\":\"\"},{\"modelName\":\"右侧单次违停\",\"modelType\":2,\"isChecked\":0,\"remarks\":\"\"},{\"modelName\":\"号牌限行\",\"modelType\":11,\"isChecked\":0,\"remarks\":\"未抓拍违停的相机\"}],\"comboName\":\"违法组合一\",\"isChecked\":0},{\"modelTypeList\":[{\"modelName\":\"左侧二次违停\",\"modelType\":3,\"isChecked\":0,\"remarks\":\"\"},{\"modelName\":\"右侧二次违停\",\"modelType\":4,\"isChecked\":0,\"remarks\":\"\"},{\"modelName\":\"号牌限行\",\"modelType\":11,\"isChecked\":0,\"remarks\":\"未抓拍违停的相机\"}],\"comboName\":\"违法组合二\",\"isChecked\":0},{\"modelTypeList\":[{\"modelName\":\"左侧违停驱离\",\"modelType\":5,\"isChecked\":0,\"remarks\":\"\"},{\"modelName\":\"右侧违停驱离\",\"modelType\":6,\"isChecked\":0,\"remarks\":\"\"},{\"modelName\":\"号牌限行\",\"modelType\":11,\"isChecked\":0,\"remarks\":\"未抓拍违停的相机\"}],\"comboName\":\"违法组合三\",\"isChecked\":0},{\"modelTypeList\":[{\"modelName\":\"左侧鹰眼模式\",\"modelType\":7,\"isChecked\":0,\"remarks\":\"\"},{\"modelName\":\"右侧鹰眼模式\",\"modelType\":8,\"isChecked\":0,\"remarks\":\"\"},{\"modelName\":\"号牌限行\",\"modelType\":11,\"isChecked\":0,\"remarks\":\"未抓拍违停的相机\"}],\"comboName\":\"违法组合四\",\"isChecked\":0},{\"modelTypeList\":[{\"modelName\":\"来向逆行\",\"modelType\":9,\"isChecked\":0,\"remarks\":\"\"},{\"modelName\":\"号牌限行\",\"modelType\":11,\"isChecked\":0,\"remarks\":\"未抓拍违停的相机\"}],\"comboName\":\"违法组合五\",\"isChecked\":0},{\"modelTypeList\":[{\"modelName\":\"去向逆行\",\"modelType\":10,\"isChecked\":0,\"remarks\":\"\"},{\"modelName\":\"号牌限行\",\"modelType\":11,\"isChecked\":0,\"remarks\":\"未抓拍违停的相机\"}],\"comboName\":\"违法组合六\",\"isChecked\":0},{\"modelTypeList\":[{\"modelName\":\"斑马线不礼让行人\",\"modelType\":13,\"isChecked\":0,\"remarks\":\"\"},{\"modelName\":\"号牌限行\",\"modelType\":11,\"isChecked\":0,\"remarks\":\"未抓拍违停的相机\"}],\"comboName\":\"违法组合七\",\"isChecked\":0},{\"modelTypeList\":[{\"modelName\":\"左侧占用专用车道行驶\",\"modelType\":14,\"isChecked\":0,\"remarks\":\"\"},{\"modelName\":\"右侧占用专用车道行驶\",\"modelType\":15,\"isChecked\":0,\"remarks\":\"\"},{\"modelName\":\"占用应急车道\",\"modelType\":16,\"isChecked\":0,\"remarks\":\"\"},{\"modelName\":\"号牌限行\",\"modelType\":11,\"isChecked\":0,\"remarks\":\"未抓拍违停的相机\"}],\"comboName\":\"违法组合八\",\"isChecked\":0}]' where id = 1")
    void addComboText();

    @Select("ALTER TABLE sys_configinfo ADD pad_queue_switch_face INT(1) DEFAULT 1 NULL COMMENT '人脸开关，0关闭，1开启。平板队列开关，如果关闭，就不往平板队列里写数据。';")
    void addSysConfigPadSwitchFace();

//    @Select("select * from sys_param_combo")
//    List<ComboVo> getComboList();
//
//    @Select("select model_type , model_name , remarks , is_checked from sys_param_combo_item spci where combo_id = #{comboId}")
//    List<ModelTypeVo> getComboItemList(Integer comboId);

    @Select("update sys_param_combo set is_checked = 1 where combo_id = #{comboId}")
    void updateComboChecked(Integer comboId);

    @Select("update sys_param_combo set is_checked = 0")
    void updateAllComboChecked();

    @Select("update sys_param_combo_item set is_checked = 0")
    void updateComboItemCheckedAll();

    @Select("update sys_param_combo_item set is_checked = 1 where model_type = #{mt} and combo_id = #{comboId}")
    void updateComboItemChecked(@Param("comboId") Integer comboId, @Param("mt")String mt);

    @Select("CREATE TABLE `sys_param_combo` (\n" +
            "  `combo_id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `is_checked` int(1) DEFAULT '0' COMMENT '0未选中，1选中',\n" +
            "  `combo_name` varchar(100) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`combo_id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='违章组合表'")
    void createSysParamCombo();


    @Select("INSERT INTO sys_param_combo (combo_id,is_checked,combo_name) VALUES\n" +
            "\t (1,0,'违法组合一'),\n" +
            "\t (2,0,'违法组合二'),\n" +
            "\t (3,0,'违法组合三'),\n" +
            "\t (4,0,'违法组合四'),\n" +
            "\t (5,0,'违法组合五'),\n" +
            "\t (6,0,'违法组合六'),\n" +
            "\t (7,0,'违法组合七'),\n" +
            "\t (8,0,'违法组合八');")
    void insertSysParamCombo();

    @Select("CREATE TABLE `sys_param_combo_item` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `model_type` int(3) DEFAULT NULL,\n" +
            "  `model_name` varchar(500) DEFAULT NULL,\n" +
            "  `remarks` varchar(500) DEFAULT '',\n" +
            "  `is_checked` int(1) DEFAULT '0' COMMENT '0未选中，1选中',\n" +
            "  `combo_id` int(11) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8")
    void createSysParamComboItem();

    @Select("INSERT INTO sys_param_combo_item (id,model_type,model_name,remarks,is_checked,combo_id) VALUES\n" +
            "\t (1,1,'左侧单次违停','',0,1),\n" +
            "\t (2,2,'右侧单次违停','',0,1),\n" +
            "\t (3,11,'号牌限行','未抓拍违停的相机',0,1),\n" +
            "\t (4,3,'左侧二次违停','',0,2),\n" +
            "\t (5,4,'右侧二次违停','',0,2),\n" +
            "\t (6,11,'号牌限行','未抓拍违停的相机',0,2),\n" +
            "\t (7,5,'左侧违停驱离','',0,3),\n" +
            "\t (8,6,'右侧违停驱离','',0,3),\n" +
            "\t (9,11,'号牌限行','未抓拍违停的相机',0,3),\n" +
            "\t (10,7,'左侧鹰眼模式','',0,4),\n" +
            "\t (11,8,'右侧鹰眼模式','',0,4),\n" +
            "\t (12,11,'号牌限行','未抓拍违停的相机',0,4),\n" +
            "\t (13,9,'来向逆行','',0,5),\n" +
            "\t (14,11,'号牌限行','未抓拍违停的相机',0,5),\n" +
            "\t (15,10,'去向逆行','',0,6),\n" +
            "\t (16,11,'号牌限行','未抓拍违停的相机',0,6),\n" +
            "\t (17,13,'斑马线不礼让行人','',0,7),\n" +
            "\t (18,11,'号牌限行','未抓拍违停的相机',0,7),\n" +
            "\t (19,14,'左侧占用专用车道行驶','',0,8),\n" +
            "\t (20,15,'右侧占用专用车道行驶','',0,8),\n" +
            "\t (21,16,'占用应急车道','',0,8),\n" +
            "\t (22,11,'号牌限行','未抓拍违停的相机',0,8);")
    void insertSysParamComboItem();

    @Select("update sys_param set model_type = '' where id = 1")
    void clearModelType();

    @Select("ALTER TABLE sys_param ADD combo_id INT(1) DEFAULT 1 NULL COMMENT '违章组合id';")
    void addComboId();

    @Select("CREATE TABLE `camera_info` (\n" +
            "  `id` int(1) NOT NULL AUTO_INCREMENT,\n" +
            "  `call_name` varchar(500) DEFAULT '' COMMENT '称呼',\n" +
            "  `position` varchar(500) DEFAULT '' COMMENT '摄像头位置',\n" +
            "  `ip` int(6) DEFAULT NULL COMMENT '摄像头ip(最后一位ip。如192.168.0.101，则取101)',\n" +
            "  `name` varchar(100) DEFAULT '' COMMENT '摄像头名称',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='相机信息表'")
    void createCameraInfo();

    @Select("INSERT INTO camera_info (id,call_name,`position`,ip,name) VALUES\n" +
            "\t (1,'左前摄像头','左前',101,'动球外治'),\n" +
            "\t (2,'前中摄像头','前中',102,'动球外治'),\n" +
            "\t (3,'右前摄像头','右前',103,'动球外治'),\n" +
            "\t (4,'左中摄像头','左中',104,'动球外治'),\n" +
            "\t (5,'顶摄像头','顶',105,'动球外治'),\n" +
            "\t (6,'右中像头','右中',106,'动球外治'),\n" +
            "\t (7,'左后像头','左后',107,'动球外治'),\n" +
            "\t (8,'后中像头','后中',108,'动球外治'),\n" +
            "\t (9,'右后像头','右后',109,'动球外治')")
    void initCameraInfo();

    @Select("CREATE TABLE `dingyue` (\n" +
            "  `id` int NOT NULL,\n" +
            "  `subscribes` varchar(128) DEFAULT NULL,\n" +
            "  `subscribeDetails` varchar(128) DEFAULT NULL,\n" +
            "  `receiveAddr` varchar(128) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;")
    void createDingyue();

    @Select("ALTER TABLE t_vehicle_restriction ADD is_open INT(1) DEFAULT 1 NULL COMMENT '开关，0关闭，1开启';")
    void addIsOpen();

    @Select("ALTER TABLE soft_info ADD soft_status varchar(500) DEFAULT '更新完成' NULL COMMENT '软件状态：更新完成，更新中，更新失败...等待';")
    void addSoftInfo();

    @Select("select count(1) from soft_info si where si.soft_name in ('UP_XM_NVR', 'CONFIG_XM_NVR')")
    Integer checkNVRContent();

    @Select("INSERT INTO soft_info (soft_name,soft_fullname,datetime,description,url,`user`,pwd,version,tag,md,version_name,soft_status) VALUES\n" +
            "\t ('UP_XM_IPC','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_NVR','UP_XM_NVR.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_NVR','CONFIG_XM_NVR.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成');")
    void addNVRContent();

    @Select("select count(1) from soft_info si where si.soft_name IN ('xjcz_light_evidence', 'merge_img_py')")
    Integer checkXjczLightEvidence();

    @Select("INSERT INTO soft_info (soft_name,soft_fullname,`datetime`,description,url,`user`,pwd,version,tag,md,version_name,soft_status) VALUES\n" +
            "\t ('xjcz_light_evidence','xjcz_light_evidence.jar',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'2.0.1.20211129b','更新完成'),\n" +
            "\t ('merge_img_py','merge_img_py.zip',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'1.0.0.20211102b','更新完成'),\n" +
            "\t ('sleepstart','sleepstart.sh',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'1.0.0.20211203b','更新完成'), \n" +
            "\t ('otherstart','otherstart.sh',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'1.0.0.20211203b','更新完成');")
    void addXjczLightEvidence();

    @Select("select count(1) from soft_info si where si.soft_name = 'xjcz_web'")
    Integer checkXjczWeb();

    @Select("INSERT INTO soft_info (soft_name,soft_fullname,datetime,description,url,`user`,pwd,version,tag,md,version_name,soft_status) VALUES\n" +
            "\t ('xjcz_web','xjcz_web.zip',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成');")
    void addXjczWeb();

    @Select("CREATE TABLE `car_black_category` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `name` varchar(300) DEFAULT NULL COMMENT '布控类别名称',\n" +
            "  `is_open` int(1) DEFAULT '0' COMMENT '0关闭，1打开',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='黑车布控类别'")
    void createCategoryTable();

    @Select("ALTER TABLE sys_configinfo ADD category varchar(300) DEFAULT '' NULL COMMENT '布控类别';")
    void addSysConfiginfo();

    @Select("select count(1) from soft_info si where si.soft_name = 'CONFIG_XM_IPC_109'")
    Integer checkIPCContent();

    @Select("INSERT INTO soft_info (soft_name,soft_fullname,datetime,description,url,`user`,pwd,version,tag,md,version_name,soft_status) VALUES\n" +
            "\t ('CONFIG_XM_IPC_101','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_102','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_103','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_104','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_105','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_106','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_107','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_108','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_109','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_110','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_111','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_112','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_113','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_114','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_115','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_116','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_117','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_118','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_119','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('CONFIG_XM_IPC_120','CONFIG_XM_IPC.cfg',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_101','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_102','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_103','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_104','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_105','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_106','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_107','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_108','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_109','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_110','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_111','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_112','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_113','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_114','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_115','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_116','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_117','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_118','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_119','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成'),\n" +
            "\t ('UP_XM_IPC_120','UP_XM_IPC.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成');")
    void addIPCContent();

    @Select("ALTER TABLE soft_info ADD serial_no varchar(300) DEFAULT '' NULL COMMENT '序列号';")
    void addSerialNo();

    @Select("ALTER TABLE sys_configinfo ADD delete_rule_days INT(10) DEFAULT 30 NULL COMMENT '删除多少天以前的数据，例如30天，则删除30天以前的数据';")
    void createDeleteRuleDays();

    @Select("ALTER TABLE sys_configinfo ADD gps_on INT(1) DEFAULT 0 NULL COMMENT '0对应NVR，1对应平板';")
    void addGpsOn();

    @Select("UPDATE soft_info set soft_name = 'ndemo' WHERE soft_name = 'NetSDK'")
    void updateSoftName();

    @Select("UPDATE soft_info set soft_name = 'config' WHERE soft_name = 'NetSDK-CONFIG'")
    void updateSoftNameConfigIni();

    @Select("ALTER TABLE db_light.sys_configinfo ADD cmd_addblcak_on INT(1) DEFAULT 0 NULL COMMENT '聚合平台指令集-添加黑人脸开关，0关，1开';")
    void addSysConfigCmdAddblcakOn();

    @Select("ALTER TABLE db_light.sys_configinfo ADD cmd_searchcar_on INT(1) DEFAULT 0 NULL COMMENT '聚合平台指令集-搜索车牌开关，0关，1开';")
    void addSysConfigCmdSearchcarOn();

    @Select("ALTER TABLE db_light.sys_configinfo ADD cmd_addblackcar_on INT(1) DEFAULT 0 NULL COMMENT '聚合平台指令集-添加黑车牌开关，0关，1开';")
    void addSysConfigCmdAddblackcarOn();

    @Select("ALTER TABLE db_light.sys_configinfo ADD cmd_on INT(1) DEFAULT 0 NULL COMMENT '聚合平台指令集 总开关，0关，1开';")
    void addSysConfiCmdOn();

    @Select("ALTER TABLE db_light.sys_configinfo ADD cmd_interval INT(11) DEFAULT 3 NULL COMMENT '聚合平台指令集 执行的时间间隔，秒为单位';")
    void addSysConfIcmdInterval();

    @Select("select count(1) from sys_param_combo_item spci WHERE model_type = 18")
    int geTruckCnt();

    @Select("INSERT INTO db_light.sys_param_combo_item (id,model_type,model_name,remarks,is_checked,combo_id) VALUES\n" +
            "\t (23,18,'货车闯入','',0,1),\n" +
            "\t (24,18,'货车闯入','',0,2),\n" +
            "\t (25,18,'货车闯入','',0,3),\n" +
            "\t (26,18,'货车闯入','',0,4),\n" +
            "\t (27,18,'货车闯入','',0,5),\n" +
            "\t (28,18,'货车闯入','',0,6),\n" +
            "\t (29,18,'货车闯入','',0,7),\n" +
            "\t (30,18,'货车闯入','',0,8)")
    void insertTruck();

    @Select("ALTER TABLE sys_model ADD traffic_signs INT(1) DEFAULT 0 NULL COMMENT '是否启用关联路段图标模式，0不启用，1启用';")
    void addTrafficSigns();

    @Select("CREATE TABLE `light_check_report` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `content` text DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4")
    void createLightCheckReport();

    @Select("ALTER TABLE db_light.sys_configinfo ADD catch_upload_on INT(1) DEFAULT 1 NULL COMMENT '是否上传抓拍数据，0关，1开';")
    void addCatchUploadOn();

    @Select("CREATE TABLE `dd_signs` (\n" +
            "  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
            "  `url` varchar(2000) DEFAULT NULL COMMENT '路段图标存放地址',\n" +
            "  `name` varchar(500) DEFAULT NULL COMMENT '路段名称',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='路段图标表'")
    void createDdSigns();

    @Select("CREATE TABLE `dd_relation` (\n" +
            "  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
            "  `signs_id` bigint(20) DEFAULT NULL,\n" +
            "  `dw_id` bigint(20) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='dd_signs 和 dd_dwxx 关联表'")
    void createDdRelation();

    @Select("ALTER TABLE db_light.sys_configinfo ADD catch_upload_type INT(1) DEFAULT 0 NULL COMMENT '上传类型，0即时上传，1起始上传';")
    void addCatchUploadType();

    @Select("delete from soft_info where soft_name like 'CONFIG_XM_IPC_%' or soft_name like 'UP_XM_IPC_%'")
    void delIPCContent();

    @Select("select count(1) from soft_info si where si.soft_name = 'UP_XM_AI'")
    Integer checkAi();

    @Select("INSERT INTO soft_info\n" +
            "(soft_name,soft_fullname,datetime,description,url,`user`,pwd,version,tag,md,version_name,soft_status)\n" +
            "VALUES('UP_XM_AI','UP_XM_AI.bin',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成');")
    void addAi();

    @Select("select count(1) from soft_info si where si.soft_fullname = 'UP_XM_IPC_101.bin'")
    Integer checkIPCbin();

    @Select("select count(1) from soft_info si where si.soft_fullname = 'xj_upload.jar'")
    Integer checkXjczUpload();

    @Select("INSERT INTO soft_info (soft_name,soft_fullname,datetime,description,url,`user`,pwd,version,tag,md,version_name,soft_status) VALUES\n" +
            "\t ('xj_upload','xj_upload.jar',NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成');")
    void addXjUpload();

    @Select("select count(1) from soft_info si where si.soft_fullname = #{softFullname}")
    Integer checkSofitInfoData(@Param("softFullname") String softFullname);

    @Select("INSERT INTO soft_info (soft_name,soft_fullname,datetime,description,url,`user`,pwd,version,tag,md,version_name,soft_status) VALUES\n" +
            "\t (#{softName},#{softFullname},NULL,NULL,NULL,NULL,NULL,0,'',NULL,'','更新完成');")
    void addSofitInfoData(@Param("softName") String softName, @Param("softFullname") String softFullname);

    @Select("SELECT COUNT(1) FROM soft_info si WHERE soft_name = #{softName} AND version_name != ''")
    Integer checkSofitInfoVersionEmpt(@Param("softName") String softName);

    @Select("UPDATE soft_info set version_name = #{versionName} WHERE soft_name = #{softName}")
    void updateSofitInfoVersion(@Param("softName")String softName, @Param("versionName") String versionName);

    @Select("DROP TABLE IF EXISTS `register_1400`;")
    void dropRegist();

    @Select("CREATE TABLE `register_1400` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `use_type` int(11) DEFAULT NULL COMMENT '使用标识',\n" +
            "  `ip` varchar(50) DEFAULT NULL COMMENT '注册-ip地址',\n" +
            "  `port` varchar(50) DEFAULT NULL COMMENT '注册-端口',\n" +
            "  `device_id` varchar(50) DEFAULT NULL COMMENT '注册-视图库编码ID',\n" +
            "  `user_name` varchar(100) DEFAULT NULL COMMENT '注册-视图库用户名',\n" +
            "  `password` varchar(100) DEFAULT NULL COMMENT '注册-视图库密码',\n" +
            "  `ape_id_face` varchar(50) DEFAULT NULL COMMENT '设备-人脸采集',\n" +
            "  `tollgate_id_car` varchar(50) DEFAULT NULL COMMENT '设备-卡口采集（车辆）',\n" +
            "  `ape_name` varchar(50) DEFAULT NULL COMMENT '设备-名称',\n" +
            "  `place` varchar(255) DEFAULT NULL COMMENT '设备-地址',\n" +
            "  `place_code` varchar(100) DEFAULT NULL COMMENT '设备-地点代码',\n" +
            "  `model` varchar(50) DEFAULT NULL COMMENT '设备-型号',\n" +
            "  `lng` varchar(50) DEFAULT NULL COMMENT '设备-经度',\n" +
            "  `lat` varchar(50) DEFAULT NULL COMMENT '设备-维度',\n" +
            "  `upload_type` int(11) DEFAULT '1' COMMENT '内部设置-上传方式 1设备直传 2订阅与通知',\n" +
            "  `data_type` int(11) DEFAULT '1' COMMENT '内部设置-数据类型  1车牌和人脸 2车牌 3人脸',\n" +
            "  `img_type` int(11) DEFAULT '1' COMMENT '内部设置-图片格式  1:大小图base64  2:大小图url  3:大图url小图base6',\n" +
            "  `img_server` varchar(50) DEFAULT NULL COMMENT '图片访问IP地址：一般填写pi可访问的ip',\n" +
            "  `img_server_port` varchar(50) DEFAULT NULL COMMENT '端口',\n" +
            "  `register_code` int(11) DEFAULT '0',\n" +
            "  `created_time` datetime DEFAULT CURRENT_TIMESTAMP,\n" +
            "  `name` varchar(255) DEFAULT '' COMMENT '说明',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;")
    void createRegist();

    @Select("INSERT INTO `register_1400` VALUES (1, 1400, '43.15.244.2', '8120', '43010920215030220319', 'changsha', 'changsha', '43010900001190002007', '43010900001210002004', '星际智能警灯', '湖南省长沙市', '430109', 'SK-CS', '121.44287', '28.67301', 1, 1, 1, NULL, NULL, 0, '2021-09-27 16:21:46', '注销请求'),\n" +
            "(2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2021-09-28 15:23:54', '文件上传'),\n" +
            "(3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2021-09-28 15:24:23', '设备录像-关闭'),\n" +
            "(4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2021-09-28 15:24:53', '图像上传'),\n" +
            "(5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2021-09-28 17:16:32', '校时');")
    void initRegist();

    @Select("delete from soft_info where soft_fullname is null ")
    void delNullSoftname();

    @Select("CREATE TABLE\n" +
            "IF\n" +
            "\tNOT EXISTS `t_temp_data` (\n" +
            "\t\t`id` INT UNSIGNED AUTO_INCREMENT,\n" +
            "\t\t`data` longtext ,\n" +
            "\t\t`upload` int ( 1 ) ,\n" +
            "\tPRIMARY KEY ( `id` ) \n" +
            "\t) ENGINE = INNODB DEFAULT CHARSET = utf8;")
    void createTTempData();
}
