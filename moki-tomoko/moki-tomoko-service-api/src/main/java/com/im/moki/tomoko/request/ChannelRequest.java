package com.im.moki.tomoko.request;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @version V1.0
 * @Description:
 * @Author huangsihong
 * @Date 2019-12-11
 */
@Data
public class ChannelRequest implements Serializable {

    /**
     *
     */
    private Long id;
    /**
     * 频道名称
     */
    private String channelName;
    /**
     * 频道图标
     */
    private String logo;
    /**
     * 播放地址
     */
    private String playUrl;
    /**
     * 0 未删除 1 已删除
     */
    private Integer deleteFlag;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
