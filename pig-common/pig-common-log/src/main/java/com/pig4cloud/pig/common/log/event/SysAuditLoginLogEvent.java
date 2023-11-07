package com.pig4cloud.pig.common.log.event;

import com.pig4cloud.pig.admin.api.entity.SysAuditLoginLog;
import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * description: SysLoginLogEvent
 * <p>
 *
 * @author 40233-胡佳艺 2023/11/04 17:04
 */
public class SysAuditLoginLogEvent extends ApplicationEvent {
	public SysAuditLoginLogEvent(SysAuditLoginLog sysAuditLoginLog) {
		super(sysAuditLoginLog);
	}
}
