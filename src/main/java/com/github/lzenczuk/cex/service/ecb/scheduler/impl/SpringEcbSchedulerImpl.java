package com.github.lzenczuk.cex.service.ecb.scheduler.impl;

import com.github.lzenczuk.cex.service.ecb.EcbService;
import com.github.lzenczuk.cex.service.ecb.scheduler.EcbScheduler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;

/**
 * Created by lzenczuk on 26/03/17.
 */

public class SpringEcbSchedulerImpl implements EcbScheduler {

    private Log logger = LogFactory.getLog(SpringEcbSchedulerImpl.class);

    public static final int ONE_MINUTE = 60000;

    private final EcbService ecbService;

    public SpringEcbSchedulerImpl(EcbService ecbService) {
        this.ecbService = ecbService;
    }

    @Override
    @Scheduled(fixedRate = ONE_MINUTE, initialDelay = ONE_MINUTE)
    @PostConstruct
    public void updateEcbExchangeRates(){
        logger.info("Scheduling exchange rates update.");
        ecbService.updateLatestExchangeRates();
        ecbService.updateHistoricalExchangeRates();
    }
}
