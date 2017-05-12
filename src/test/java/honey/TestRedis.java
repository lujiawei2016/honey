package honey;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.honey.pojo.Banner;
import com.weixin.honey.util.RedisUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@Transactional
public class TestRedis {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Test
	public void test1() throws Exception{
		/*ListOperations<String, Object> opsForList = redisTemplate.opsForList();
		
		for(int i=0;i<10;i++){
			Banner b = new Banner("imgUrl"+i, "imgName"+i, "url"+i, i);
			opsForList.leftPush("strList", b);
		}
		
		Banner ba = new Banner("imgUrl"+100, "imgName"+100, "url"+100, 100);
		opsForList.leftPush("strList",ba);
		opsForList.leftPush("strList",ba);
		
		System.out.println(opsForList.range("strList", 0, -1).size());
		
		opsForList.remove("strList", 4, ba);
		
		System.out.println(opsForList.range("strList", 0, -1).size());
		
		List<Object> objList = opsForList.range("strList", 0, -1);
		for(Object obj:objList){
			System.out.println(obj);
		}*/
		
		redisUtils.delete("strList");
		
	}
}
