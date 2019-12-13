package com.im.moki.tomoko.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.im.moki.common.util.BeanUtility;
import com.im.moki.common.util.PageInfo;
import com.im.moki.common.util.PageInfoHelper;
import com.im.moki.common.util.Result;
import com.im.moki.tomoko.constants.TomokoCode;
import com.im.moki.tomoko.dto.ProgramDto;
import com.im.moki.tomoko.entity.ProgramEntity;
import com.im.moki.tomoko.mapper.ProgramMapper;
import com.im.moki.tomoko.request.ProgramRequest;
import com.im.moki.tomoko.response.TomokoResult;
import com.im.moki.tomoko.service.intf.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @Description: ( Service Impl)
 * @Author huangsihong
 * @Date 2019-12-9
 */
@Service
@Slf4j
public class ProgramServiceImpl extends ServiceImpl<ProgramMapper, ProgramEntity> implements ProgramService {


    /**
     * 列表查询
     * @param params
     * @return
     */
    @Override
    public PageInfo selectProgramList(Map<String, Object> params) {
        Page<ProgramDto> page = PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        List<ProgramDto> list = baseMapper.selectChannelData(params);
        return PageInfoHelper.pageToPageInfo(page, list);
    }


    /**
     * 添加节目单
     * @param request
     * @return
     */
    @Override
    public Result addProgramData(ProgramRequest request) {
        try {

            if (StringUtils.isEmpty(request.getProgramName())) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"请输入节目单名称！");
            }
            if (null == request.getStartTime()) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"请填写开始播放时间！");
            }
            if (null == request.getEndTime()) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"请填写结束播放时间！");
            }
            if (null == request.getType()) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"请选择类型！");
            }

            ProgramEntity entity = baseMapper.selectByProgramName(request);
            if (null != entity) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"该时间段内已存在节目单，请核实！");
            }
            entity = BeanUtility.copy(request, ProgramEntity.class);
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());

            if (this.insert(entity)){
                return TomokoResult.createSuccRes("success");
            }
        } catch (Exception e) {
            log.error("增加频道异常",e);
        }

        return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL);
    }


    /**
     * 更新节目单
     * @param request
     * @return
     */
    @Override
    public Result updateProgramData(ProgramRequest request) {
        try {
            if (null == request.getId()) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"请选择需要修改的节目单！");
            }
            ProgramEntity entity = baseMapper.selectById(request.getId());
            if (null == entity) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"不存在该节目单信息，请核实！");
            }

            entity = baseMapper.selectByProgramName(request);
            if (null != entity) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"该时间段内已存在节目单，请核实！");
            }

            request.setUpdateTime(new Date());
            entity = BeanUtility.copy(request, ProgramEntity.class);
            if (this.updateById(entity)){
                return TomokoResult.createSuccRes("success");
            }

        } catch (Exception e) {
            log.error("更新节目单信息异常",e);
        }

        return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL);
    }


    /**
     * 删除节目单
     * @param params
     * @return
     */
    @Override
    public Result deleteProgramData(Map<String, Object> params) {
        try {
            Long id = (Long)params.get("id");
            if (null == id) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"请选择需要删除的节目单！");
            }
            ProgramEntity entity = baseMapper.selectById(id);
            if (null == entity) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"不存在该节目单信息，请核实！");
            }

            entity.setDeleteFlag(1);
            entity.setUpdateTime(new Date());
            if (this.updateById(entity)){
                return TomokoResult.createSuccRes("success");
            }

        } catch (Exception e) {
            log.error("删除节目单异常",e);
        }

        return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL);
    }
}
