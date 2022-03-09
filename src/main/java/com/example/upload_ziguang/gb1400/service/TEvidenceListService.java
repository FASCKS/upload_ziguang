package com.example.upload_ziguang.gb1400.service;

import com.example.upload_ziguang.gb1400.domain.TEvidenceList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  @author Gpxx
 *  @Date 2022/3/9 11:00
 */
public interface TEvidenceListService extends IService<TEvidenceList>{


        List<TEvidenceList> getByFrameId(Long frameId);
    }
