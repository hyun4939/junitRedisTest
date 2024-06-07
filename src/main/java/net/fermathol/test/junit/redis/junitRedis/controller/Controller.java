package net.fermathol.test.junit.redis.junitRedis.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class Controller {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

//    @Autowired
    Gson gson = new GsonBuilder().create();

    @PostMapping("dd")
    Mono<String> dd(){
        return Mono.create(callback -> {


            redisTemplate.opsForValue().set("key11","dd"+System.currentTimeMillis());
            String val = redisTemplate.opsForValue().get("key11");
            log.info("redis val  {},",val);

            Map<String,Object> result = new HashMap<>();
            result.put("key11",val);

            try { callback.success(gson.toJson(result)); }
            catch (Exception e) { callback.error(e); }
        });
    }

    @PostMapping("async")
    Mono<String> async(){
        return Mono.create(callback -> {


            Map<String,Object> result = new HashMap<>();
            result.put("key","ok");

            try { callback.success(gson.toJson(result)); }
            catch (Exception e) { callback.error(e); }
        });
    }

}
