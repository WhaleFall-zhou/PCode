package com.pcode.base_message.kafka;

import com.alibaba.fastjson.JSONObject;
import com.pcode.base_message.dto.ProjectInfo;
import com.pcode.base_message.es.ESUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
@Slf4j
public class ProjectConsumer {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @KafkaListener(topics = "add_in_project")
    public void addInProject(ConsumerRecord<?,?> record) {
        Optional message = Optional.ofNullable(record.value());
        try {
            if(message.isPresent()){
                ESUtil esUtil = new ESUtil();
                JSONObject jsonObject = JSONObject.parseObject(message.get().toString());
                ProjectInfo projectInfo = jsonObject.getObject("projectInfo", ProjectInfo.class);
                String[] team = projectInfo.getTeam().split(",");
                for(String var1:team){
                    if(!var1.equals(projectInfo.getCreated_by())) {
                        JSONObject var2 = new JSONObject();
                        var2.put("operator_id",projectInfo.getCreated_by());
                        var2.put("operated",var1);
                        var2.put("organization",projectInfo.getId());
                        var2.put("time", String.valueOf(projectInfo.getCreated_at()));
                        var2.put("operation","把{}加入了项目");
                        redisTemplate.opsForZSet().add("advice_project:"+var1, String.valueOf(projectInfo.getCreated_at()),System.currentTimeMillis());
                        if (redisTemplate.opsForZSet().size("advice_project:"+var1)>1){
                            redisTemplate.opsForZSet().removeRange("advice_project:"+var1,0,0);
                        }
                        esUtil.addData(var2 ,"operation",null);
                    }
                }
            }
        } catch (IOException e) {
            log.error("kafka addInProject elasticsearch insert error:{},object:{}",e,message.get());
        }

    }
}
