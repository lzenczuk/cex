package com.github.lzenczuk.cex.config;

import com.github.lzenczuk.cex.service.ecb.EcbService;
import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesParser;
import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesWebClient;
import com.github.lzenczuk.cex.service.ecb.client.impl.AhcEcbExchangeRatesWebClientImpl;
import com.github.lzenczuk.cex.service.ecb.client.impl.SaxEcbExchangeRatesParserImpl;
import com.github.lzenczuk.cex.service.ecb.impl.EcbServiceImpl;
import com.github.lzenczuk.cex.service.ecb.scheduler.EcbScheduler;
import com.github.lzenczuk.cex.service.ecb.scheduler.impl.SpringEcbSchedulerImpl;
import com.github.lzenczuk.cex.service.exchangerate.ExchangeRatesService;
import com.github.lzenczuk.cex.service.exchangerate.impl.InMemoryExchangeRatesServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by lzenczuk on 24/03/17.
 */

@Configuration
@EnableScheduling
public class AppConfig {

    @Bean
    public ExchangeRatesService exchangeRatesService(){
        return new InMemoryExchangeRatesServiceImpl();
    }

    @Bean
    public EcbExchangeRatesParser ecbExchangeRatesParser(){
        return new SaxEcbExchangeRatesParserImpl();
    }

    @Bean
    public EcbExchangeRatesWebClient exchangeRatesWebClient(){
        return new AhcEcbExchangeRatesWebClientImpl();
    }

    @Bean
    public EcbService ecbService(EcbExchangeRatesWebClient client, EcbExchangeRatesParser parser, ExchangeRatesService exchangeRatesService){
        return new EcbServiceImpl(parser, client, exchangeRatesService);
    }

    @Bean
    public EcbScheduler ecbScheduler(EcbService ecbService){
        return new SpringEcbSchedulerImpl(ecbService);
    }
}
