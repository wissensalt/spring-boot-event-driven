package com.wissensalt.rnd.sbed.oa.client;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

/**
 * Created on 2/8/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FeignBuilderFactory {

    public static <T> T createClient(Class<T> type, String p_BaseUrl) {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(type))
                .logLevel(feign.Logger.Level.FULL)
                .target(type, p_BaseUrl);
    }

    public static <T> T createHttpBasicClient(Class<T> type, String p_BaseUrl, String p_UserName, String p_Password) {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(type))
                .logLevel(feign.Logger.Level.FULL)
                .requestInterceptor(new BasicAuthRequestInterceptor(p_UserName, p_Password))
                .target(type, p_BaseUrl);
    }

}
