package com.pig4cloud.pig.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.dto.AuditLoginLogDTO;
import com.pig4cloud.pig.admin.api.entity.SysAuditLoginLog;

/**
 * <p>
 * description: SysAuditLoginLogService
 * <p>
 *
 * @author 40233-胡佳艺 2023/10/27 15:58
 */
public interface SysAuditLoginLogService extends IService<SysAuditLoginLog> {

	/**
	 * 分页查询审计登录日志
	 *
	 * @param page  分页参数
	 * @param sysAuditLoginLogDTO  查询参数
	 * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page 返回的分页数据
	 */
	Page queryAuditLoginLogPage(Page page, AuditLoginLogDTO sysAuditLoginLogDTO);

	/**
	 * 保存日志
	 *
	 * @param sysAuditLoginLog 保存审计日志
	 * @return java.lang.Boolean 是否插入成功
	 * @throws
	 */
	Boolean saveLog(SysAuditLoginLog sysAuditLoginLog);

}
