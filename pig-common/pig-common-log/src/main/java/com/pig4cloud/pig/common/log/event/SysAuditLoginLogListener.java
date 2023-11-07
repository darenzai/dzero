package com.pig4cloud.pig.common.log.event;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.pig4cloud.pig.admin.api.entity.SysAuditLoginLog;
import com.pig4cloud.pig.admin.api.entity.SysLog;
import com.pig4cloud.pig.admin.api.feign.RemoteLogService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.jackson.PigJavaTimeModule;
import com.pig4cloud.pig.common.log.config.PigLogProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * <p>
 * description: SysAuditLoginLogListener
 * <p>
 *
 * @author 40233-胡佳艺 2023/11/04 17:11
 */
@Slf4j
@RequiredArgsConstructor
public class SysAuditLoginLogListener implements InitializingBean {


	private final static ObjectMapper objectMapper = new ObjectMapper();

	private final RemoteLogService remoteLogService;

	private final PigLogProperties logProperties;


	@SneakyThrows
	@Async
	@Order
	@EventListener(SysAuditLoginLogEvent.class)
	public void saveSysLog(SysAuditLoginLogEvent event) {
		SysAuditLoginLogEventSource source = (SysAuditLoginLogEventSource) event.getSource();
		SysAuditLoginLog sysAuditLoginLog = new SysAuditLoginLog();
		BeanUtils.copyProperties(source, sysAuditLoginLog);

		// json 格式刷参数放在异步中处理，提升性能
		if (Objects.nonNull(source.getBody())) {
			String params = objectMapper.writeValueAsString(source.getBody());
			sysAuditLoginLog.setParams(StrUtil.subPre(params, logProperties.getMaxLength()));
		}

		remoteLogService.saveAuditLoginLog(sysAuditLoginLog, SecurityConstants.FROM_IN);
	}

	@Override
	public void afterPropertiesSet() {
		objectMapper.addMixIn(Object.class, SysAuditLoginLogListener.PropertyFilterMixIn.class);
		String[] ignorableFieldNames = logProperties.getExcludeFields().toArray(new String[0]);

		FilterProvider filters = new SimpleFilterProvider().addFilter("filter properties by name",
				SimpleBeanPropertyFilter.serializeAllExcept(ignorableFieldNames));
		objectMapper.setFilterProvider(filters);
		objectMapper.registerModule(new PigJavaTimeModule());
	}

	@JsonFilter("filter properties by name")
	class PropertyFilterMixIn {

	}
}
