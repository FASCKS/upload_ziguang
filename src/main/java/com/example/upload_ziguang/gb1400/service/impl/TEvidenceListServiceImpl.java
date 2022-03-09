package com.example.upload_ziguang.gb1400.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.upload_ziguang.gb1400.domain.TEvidenceList;
import com.example.upload_ziguang.gb1400.mapper.TEvidenceListMapper;
import com.example.upload_ziguang.gb1400.service.TEvidenceListService;
/**
 *  @author Gpxx
 *  @Date 2022/3/9 11:00
 */
@Service
public class TEvidenceListServiceImpl extends ServiceImpl<TEvidenceListMapper, TEvidenceList> implements TEvidenceListService{

    @Override
    public List<TEvidenceList> getByFrameId(Long frameId) {
        return baseMapper.getByFrameId( frameId);
    }
}
