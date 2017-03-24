package com.github.lzenczuk.cex.config;

import com.github.lzenczuk.cex.service.ecb.EcbService;
import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesParser;
import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesWebClient;
import com.github.lzenczuk.cex.service.ecb.client.impl.AhcEcbExchangeRatesWebClientImpl;
import com.github.lzenczuk.cex.service.ecb.client.impl.SaxEcbExchangeRatesParserImpl;
import com.github.lzenczuk.cex.service.ecb.impl.EcbServiceImpl;
import com.github.lzenczuk.cex.service.exchangerate.ExchangeRatesService;
import com.github.lzenczuk.cex.service.exchangerate.impl.InMemoryExchangeRatesServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lzenczuk on 24/03/17.
 */

@Configuration
public class AppConfig {

    private Log logger = LogFactory.getLog(AppConfig.class);

    @Bean
    public ExchangeRatesService exchangeRatesService(){
        logger.info("--------> exchange rates service");
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
        logger.info("--------> ecb service");
        return new EcbServiceImpl(parser, client, exchangeRatesService);
    }


}
