package com.im.moki.tomoko.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.im.moki.tomoko.entity.ChannelEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @Description:  Mapper
 * @Author huangsihong
 * @Date 2019-12-9
 */
@Mapper
public interface ChannelMapper extends BaseMapper<ChannelEntity> {


    List<ChannelEntity> selectChannelData(Map<String, Object> params);
}
