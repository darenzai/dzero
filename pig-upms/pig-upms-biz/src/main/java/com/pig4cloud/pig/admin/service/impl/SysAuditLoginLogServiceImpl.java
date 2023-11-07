package com.pig4cloud.pig.admin.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.dto.AuditLoginLogDTO;
import com.pig4cloud.pig.admin.api.entity.SysAuditLoginLog;
import com.pig4cloud.pig.admin.mapper.SysAuditLoginLogMapper;
import com.pig4cloud.pig.admin.service.SysAuditLoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * description: SysAuditLoginLogServiceImpl
 * <p>
 *
 * @author 40233-胡佳艺 2023/10/28 11:24
 */
@Service
public class SysAuditLoginLogServiceImpl extends ServiceImpl<SysAuditLoginLogMapper, SysAuditLoginLog>
				implements SysAuditLoginLogService {


	@Override
	public Page queryAuditLoginLogPage(Page page, AuditLoginLogDTO sysAuditLoginLog) {
		LambdaQueryWrapper<SysAuditLoginLog> wrapper = Wrappers.lambdaQuery();
		if(StrUtil.isNotBlank(sysAuditLoginLog.getLogType())) {
			wrapper.eq(SysAuditLoginLog::getLogType, sysAuditLoginLog.getLogType());
		}
		if(ArrayUtil.isNotEmpty(sysAuditLoginLog.getCreateTime())) {
			wrapper.ge(SysAuditLoginLog::getCreateTime, sysAuditLoginLog.getCreateTime()[0])
					.le(SysAuditLoginLog::getCreateTime, sysAuditLoginLog.getCreateTime()[1]);
		}
		return baseMapper.selectPage(page, wrapper);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveLog(SysAuditLoginLog sysAuditLoginLog) {
		baseMapper.insert(sysAuditLoginLog);
		return Boolean.TRUE;
	}
}
