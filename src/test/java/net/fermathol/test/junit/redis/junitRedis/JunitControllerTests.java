package net.fermathol.test.junit.redis.junitRedis;

import net.fermathol.test.junit.redis.junitRedis.controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@WebFluxTest(Controller.class)
class JunitControllerTests {

	@Autowired
	private WebTestClient webClient;

//	@MockBean
//	private RedisTemplate<Object,Object> redisTemplate;


//	@Spy
//	RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

	@Autowired
	Controller controller;

	@Mock
	private ValueOperations<Object,Object> valueOperations;

	@MockBean RedisTemplate<Object, Object> template;
	private @Mock RedisConnectionFactory connectionFactoryMock;
	private @Mock RedisConnection redisConnectionMock;
	private @Mock RedisTemplate<Object, Object> redisTemplate;

	@BeforeEach
	void setUp() {

		TransactionSynchronizationManager.clear();

		template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactoryMock);

//		when(redisTemplate).thenReturn(template);

		when(connectionFactoryMock.getConnection()).thenReturn(redisConnectionMock);

		template.afterPropertiesSet();
	}


	@Test
	@DisplayName("Basic Controller test")
	void testController(){

		valueOperations = template.opsForValue();
		Mockito.when(valueOperations).thenReturn(template.opsForValue());
		Mockito.when(valueOperations.get("key11"))
				.thenReturn("null");
//
//		valueOperations.set("key11","dd1234");


//		when(customerService.getCustomerOTCBalance(any()))
//				.thenReturn(responseMono);

		webClient.get().uri("/async")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.key11").isEqualTo(1.30);
//
//		mvc = MockMvcBuilders
//				.standaloneSetup(controller)
//				.build();
//
//		// given
//		valueOperations.set("key11","dd1234");
//		when(redisTemplate.opsForValue()).thenReturn(valueOperations);
//		when(valueOperations.get("key11")).thenReturn("dd123ek");


//		List<Member> members = new ArrayList<>();
//		members.add(Member.builder().name("John").build());

//		given(memberService.list()).willReturn(members);

//        try {
//            mvc.perform(get("/async"))
//                    .andExpect(status().isOk())
//                    .andExpect(content().string(containsString("dd")));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }


//		RedisCacheServiceImpl cacheService = new RedisCacheServiceImpl(redisTemplate);
//		assertEquals("value123", cacheService.getFromCache("test-key", "test-hash-key"));

		// when

		// then

	}

}
