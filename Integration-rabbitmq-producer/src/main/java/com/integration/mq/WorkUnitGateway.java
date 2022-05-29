package com.integration.mq;

import com.integration.mq.domain.Transaction;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface WorkUnitGateway {
	@Gateway(requestChannel = "requests")
	void generate(Transaction transaction);

}
