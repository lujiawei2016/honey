package honey;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weixin.honey.util.RedisUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestRedis {
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Test
	public void test1() throws Exception{
		System.out.println(redisUtils.getList("name", 0, 100));
	}
}
