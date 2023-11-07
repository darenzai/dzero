package com.pig4cloud.pig.common.log.event;

import com.pig4cloud.pig.admin.api.entity.SysAuditLoginLog;
import lombok.Data;

/**
 * <p>
 * description: SysAuditLoginLogEventSource
 * <p>
 *
 * @author 40233-胡佳艺 2023/11/04 17:08
 */
@Data
public class SysAuditLoginLogEventSource extends SysAuditLoginLog {
	private Object body;
}
