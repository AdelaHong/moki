package com.im.moki.tomoko.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProgramDto implements Serializable {

    private Long id;
    /**
     * 所属频道id
     */
    private Long channelId;
    /**
     * 频道名称
     */
    private String channelName;
    /**
     * 节目单名称
     */
    private String programName;
    /**
     * 开始播放时间
     */
    private Date startTime;
    /**
     * 播放结束时间
     */
    private Date endTime;
    /**
     * 描述
     */
    private String description;
    /**
     * 类别：1 电视剧 2 电影 3 综艺 4 体育 5 纪录片
     */
    private Integer type;
    /**
     * 图片
     */
    private String picutureUrl;
    /**
     * 0 未删除 1 已删除
     */
    private Integer deleteFlag;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;

}
