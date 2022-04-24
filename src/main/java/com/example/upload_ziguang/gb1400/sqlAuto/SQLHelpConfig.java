package com.example.upload_ziguang.gb1400.sqlAuto;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
public class SQLHelpConfig implements ApplicationRunner {


    @Autowired
    private SysParamService sysParamService;

    public void checkTpolice(){
        System.out.println("检查 t_temp_data 表 是否存在");
        int t_temp_data = sysParamService.checkTableColumn("db_light", "t_temp_data", "id");
        if (t_temp_data==0){
            sysParamService.createTTempData();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.checkTpolice();
    }
}
