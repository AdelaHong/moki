package com.im.moki.tomoko.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("tf_b_channel")
public class ChannelEntity  implements Serializable {

    /**
     *
     */
    @TableId(value="ID", type= IdType.AUTO)
    private Long id;
    /**
     * 频道名称
     */
    @TableField("CHANNEL_NAME")
    private String channelName;
    /**
     * 频道图标
     */
    @TableField("LOGO")
    private String logo;
    /**
     * 播放地址
     */
    @TableField("PLAY_URL")
    private String playUrl;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;

}
