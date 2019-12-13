package com.im.moki.tomoko.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.im.moki.tomoko.dto.ProgramDto;
import com.im.moki.tomoko.entity.ProgramEntity;
import com.im.moki.tomoko.request.ProgramRequest;
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
public interface ProgramMapper extends BaseMapper<ProgramEntity> {


    List<ProgramDto> selectChannelData(Map<String, Object> params);

    ProgramEntity selectByProgramName(ProgramRequest request);
}