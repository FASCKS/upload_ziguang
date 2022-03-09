package com.example.upload_ziguang.gb1400.service;

import com.example.upload_ziguang.gb1400.domain.TEvidence;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  @author Gpxx
 *  @Date 2022/3/9 11:00
 */
public interface TEvidenceService extends IService<TEvidence>{

/**
 * 查找人脸
 */
    List<TEvidence> findAllFace();
/**
 * 查找车牌
 */
List<TEvidence> findAllCar();
    }
