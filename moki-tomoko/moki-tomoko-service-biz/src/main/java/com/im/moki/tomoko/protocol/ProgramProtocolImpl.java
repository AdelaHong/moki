package com.im.moki.tomoko.protocol;

import com.im.moki.common.util.PageInfo;
import com.im.moki.common.util.Result;
import com.im.moki.tomoko.request.ProgramRequest;
import com.im.moki.tomoko.rpc.ProgramProtocol;
import com.im.moki.tomoko.service.intf.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("programProtocol")
public class ProgramProtocolImpl implements ProgramProtocol {

    @Autowired
    private ProgramService programService;


    /**
     * 列表查询
     * @param params
     * @return
     */
    @Override
    public PageInfo selectProgramList(Map<String, Object> params) {
        return programService.selectProgramList(params);
    }


    /**
     * 添加节目单
     * @param request
     * @return
     */
    @Override
    public Result addProgramData(ProgramRequest request) {
        return programService.addProgramData(request);
    }


    /**
     * 更新节目单
     * @param request
     * @return
     */
    @Override
    public Result updateProgramData(ProgramRequest request) {
        return programService.updateProgramData(request);
    }


    /**
     * 删除节目单
     * @param params
     * @return
     */
    @Override
    public Result deleteProgramData(Map<String, Object> params) {
        return programService.deleteProgramData(params);
    }
}
