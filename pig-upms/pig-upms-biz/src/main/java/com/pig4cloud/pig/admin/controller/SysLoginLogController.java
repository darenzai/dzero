package com.pig4cloud.pig.admin.controller;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.AuditLoginLogDTO;
import com.pig4cloud.pig.admin.api.dto.SysLogDTO;
import com.pig4cloud.pig.admin.api.entity.SysAuditLoginLog;
import com.pig4cloud.pig.admin.service.SysAuditLoginLogService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.annotation.Inner;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * description: SysLoginLogController
 * <p>
 *
 * @author 40233-胡佳艺 2023/10/27 15:27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/login-log")
@Tag(description = "LoginLog", name = "登录日志管理模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysLoginLogController {

	private final SysAuditLoginLogService sysAuditLoginLogService;

	/**
	 * 查询登录日志分页请求
	 *
	 * @param page  分页请求
	 * @param auditLginLogDTO 登录审计日志查询参数
	 * @return com.pig4cloud.pig.common.core.util.R
	 * @throws
	 */
	@GetMapping("/page")
	public R queryLoginLogPage(@ParameterObject Page page, @ParameterObject AuditLoginLogDTO auditLginLogDTO) {
		return R.ok(sysAuditLoginLogService.queryAuditLoginLogPage(page, auditLginLogDTO));
	}

	/**
	 * 批量删除
	 *
	 * @param ids  主键
	 * @return com.pig4cloud.pig.common.core.util.R
	 */
	@DeleteMapping
	public R removeByIds(@RequestBody Long[] ids) {
		return R.ok(sysAuditLoginLogService.removeBatchByIds(CollUtil.toList(ids)));
	}

	@Inner
	@PostMapping("/save")
	public R save(@Valid @RequestBody SysAuditLoginLog sysAuditLoginLog) {
		return R.ok(sysAuditLoginLogService.saveLog(sysAuditLoginLog));
	}

}
