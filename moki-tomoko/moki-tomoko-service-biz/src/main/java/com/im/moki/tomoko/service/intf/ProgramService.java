package com.im.moki.tomoko.service.intf;

import com.baomidou.mybatisplus.service.IService;
import com.im.moki.common.util.PageInfo;
import com.im.moki.common.util.Result;
import com.im.moki.tomoko.entity.ProgramEntity;
import com.im.moki.tomoko.request.ProgramRequest;

import java.util.Map;

/**
 * @version V1.0
 * @Description: ( Service)
 * @Author huangsihong
 * @Date 2019-12-9
 */
public interface ProgramService extends IService<ProgramEntity> {

    PageInfo selectProgramList(Map<String, Object> params);

    Result addProgramData(ProgramRequest request);

    Result updateProgramData(ProgramRequest request);

    Result deleteProgramData(Map<String, Object> params);
}
