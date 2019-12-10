package com.im.moki.tomoko.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * @version V1.0
 * @Description:
 * @Author huangsihong
 * @Date 2019-12-9
 */
@Data
@TableName("tf_b_channel")
public class ChannelEntity  implements Serializable {

    /**
     * 频道id
     */
    @TableId(value="CHANNEL_ID", type= IdType.AUTO)
    private Long channelId;
    /**
     * 频道号
     */
    @TableField("CHANNEL_NO")
    private String channelNo;
    /**
     * 频道名称
     */
    @TableField("CHANNEL_NAME")
    private String channelName;

}
