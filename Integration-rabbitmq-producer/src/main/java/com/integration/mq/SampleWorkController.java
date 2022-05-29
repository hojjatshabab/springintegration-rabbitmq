package com.integration.mq;

import com.integration.mq.domain.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleWorkController {

    private final WorkUnitGateway workUnitGateway;

    @PostMapping("/transaction")
    public Transaction generateWork(@RequestBody Transaction transaction) {
        workUnitGateway.generate(transaction);
        return transaction;
    }
}
