package com.pcode.demo.dto;

import java.util.List;

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
    private String parent_id;//父工作项
    private List<T> participantList;
    private T principal;
    private String startTime;
    private String endTime;

    public List<T> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<T> participantList) {
        this.participantList = participantList;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public T getPrincipal() {
        return principal;
    }

    public void setPrincipal(T principal) {
        this.principal = principal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrincipal_id() {
        return principal_id;
    }

    public void setPrincipal_id(String principal_id) {
        this.principal_id = principal_id;
    }

    public String getParticipant() {
        return Participant;
    }

    public void setParticipant(String participant) {
        Participant = participant;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrowse_id() {
        return browse_id;
    }

    public void setBrowse_id(String browse_id) {
        this.browse_id = browse_id;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Long end_time) {
        this.end_time = end_time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
}
