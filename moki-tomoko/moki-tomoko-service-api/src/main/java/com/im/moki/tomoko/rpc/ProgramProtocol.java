package com.im.moki.tomoko.rpc;

import com.im.moki.common.util.PageInfo;
import com.im.moki.common.util.Result;
import com.im.moki.tomoko.request.ProgramRequest;

import java.util.Map;

public interface ProgramProtocol {

    /**
     * 列表查询
     * @param params
     * @return
     */
    PageInfo selectProgramList(Map<String, Object> params);


    /**
     * 添加节目单
     * @param request
     * @return
     */
    Result addProgramData(ProgramRequest request);


    /**
     * 更新节目单
     * @param request
     * @return
     */
    Result updateProgramData(ProgramRequest request);


    /**
     * 删除节目单
     * @param params
     * @return
     */
    Result deleteProgramData(Map<String, Object> params);
}
