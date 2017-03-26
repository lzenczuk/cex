package com.github.lzenczuk.cex.service.ecb.client.impl;

import com.github.lzenczuk.cex.model.ConversionRate;
import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by lzenczuk on 24/03/17.
 */
public class SaxEcbExchangeRatesParserImpl implements EcbExchangeRatesParser {

    private Log logger = LogFactory.getLog(SaxEcbExchangeRatesParserImpl.class);

    @Override
    public void parse(Optional<InputStream> optionalInputStream, Consumer<ConversionRate> conversionRateConsumer) {

        if(!optionalInputStream.isPresent()){
            logger.info("Receive empty input stream. Nothing to parse.");
        }

        optionalInputStream.ifPresent(inputStream -> {
            try {
                logger.info("Parsing currencies input stream.");
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();
                EcbXmlHandler ecbXmlHandler = new EcbXmlHandler(conversionRateConsumer);

                saxParser.parse(inputStream, ecbXmlHandler);
                logger.info("Currencies input stream parsed.");

            } catch (ParserConfigurationException e) {
                logger.error("Parser configuration error.", e);
            } catch (SAXException e) {
                logger.error("Sax parser error.", e);
            } catch (IOException e) {
                logger.error("IO error during parsing.", e);
            }
        });
    }

    private class EcbXmlHandler extends DefaultHandler{

        private String currentDate;

        private final Consumer<ConversionRate> conversionRateConsumer;

        public EcbXmlHandler(Consumer<ConversionRate> conversionRateConsumer) {
            this.conversionRateConsumer = conversionRateConsumer;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if(qName.equalsIgnoreCase("cube")){
                String newData = attributes.getValue("time");

                if(newData!=null){
                    currentDate = newData;
                    return;
                }

                String currency = attributes.getValue("currency");
                String rate = attributes.getValue("rate");

                if(currency!=null && rate!=null){
                    if(currentDate==null){
                        logger.error("Receive currency rate but without date.");
                    }else{
                        conversionRateConsumer.accept(new ConversionRate(currency, rate, currentDate));
                    }
                }

            }
        }
    }
}
