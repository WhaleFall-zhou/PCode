package com.pcode.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemInfo<T> {
    private String id;
    private String principal_id;//负责人id
    private String Participant;//参与者id,用”，“隔开
    private Integer statue;//工作项当前状态，0：打开，1：进行中，3：已完成，4：关闭
    private String title;//标题
    private String browse_id;//项目id
    private Long start_time;//开始时间
    private Long end_time;//结束时间
    private Integer type;//工作项类别 0：史诗，1：用户故事，2：缺陷，3：特性，4：测试用例，5：迭代，6：工时
    private String description;//内容
    private String feature;//指标
    private String children_id;//子工作项
    private List<T> participantList;
    private T principal;
    private String startTime;
    private String endTime;
    private String create_by;
    private Long created_at;
    private T created;



}
