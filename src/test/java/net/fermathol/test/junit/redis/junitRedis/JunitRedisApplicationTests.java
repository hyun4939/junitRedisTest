package net.fermathol.test.junit.redis.junitRedis;

import net.fermathol.test.junit.redis.junitRedis.controller.Controller;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
class JunitRedisApplicationTests {
//	@Autowired
	MockMvc mvc;

	@Autowired
	Controller controller;

	@Mock
	RedisTemplate<String, String> redisTemplate;

	@Mock
	private ValueOperations<String,String> valueOperations;

//	@BeforeAll
//	public void setUp() {
//		MockitoAnnotations.initMocks(this);
//		when(redisTemplate.opsForValue()).thenReturn(valueOperations);
//		Mockito.doNothing().when(valueOperations).set(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
//	}

//	@Test
//	void contextLoads() {
////		this.mvc = new MockMv
//	}
	@BeforeEach
	public void initMockMvc() {
		mvc = MockMvcBuilders
			.standaloneSetup(controller)
			.build();
	}

	@Test
	@DisplayName("Basic Controller test")
	void testController(){

		mvc = MockMvcBuilders
				.standaloneSetup(controller)
				.build();

		// given
		valueOperations.set("key11","dd1234");
		when(redisTemplate.opsForValue()).thenReturn(valueOperations);
		when(valueOperations.get("key11")).thenReturn("dd123ek");


//		List<Member> members = new ArrayList<>();
//		members.add(Member.builder().name("John").build());

//		given(memberService.list()).willReturn(members);

        try {
            mvc.perform(get("/async"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("dd")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


//		RedisCacheServiceImpl cacheService = new RedisCacheServiceImpl(redisTemplate);
//		assertEquals("value123", cacheService.getFromCache("test-key", "test-hash-key"));

		// when

		// then

	}

	@Test
	@DisplayName("Redis Controller test")
	void testController2(){
		// given
		valueOperations.set("key11","dd1234");
		when(redisTemplate.opsForValue()).thenReturn(valueOperations);
		when(valueOperations.get("key11")).thenReturn("dd123ek");

	//	Con


//		RedisCacheServiceImpl cacheService = new RedisCacheServiceImpl(redisTemplate);
//		assertEquals("value123", cacheService.getFromCache("test-key", "test-hash-key"));

		// when

		// then

	}

}
