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
 * @Date 2019-12-9
 */
@Data
@TableName("tf_b_program")
public class ProgramEntity  implements Serializable {

    /**
     *
     */
    @TableId(value="ID", type= IdType.AUTO)
    private Long id;
    /**
     * 所属频道id
     */
    @TableField("CHANNEL_ID")
    private Long channelId;
    /**
     * 节目单名称
     */
    @TableField("PROGRAM_NAME")
    private String programName;
    /**
     * 开始播放时间
     */
    @TableField("START_TIME")
    private Date startTime;
    /**
     * 播放结束时间
     */
    @TableField("END_TIME")
    private Date endTime;
    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;
    /**
     * 类别：1 电视剧 2 电影 3 综艺 4 体育 5 纪录片
     */
    @TableField("TYPE")
    private Integer type;
    /**
     * 图片
     */
    @TableField("PICUTURE_URL")
    private String picutureUrl;
    /**
     *
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     *
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;

}

