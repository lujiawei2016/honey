package honey;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weixin.honey.util.EhcacheUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestEhcache {
	
	@Autowired
	private EhcacheUtils ehcacheUtils;
	
	@Test
	public void test1(){
		String str = "55555555555555";
		ehcacheUtils.setCache("myCache", "name", str);
		System.out.println(ehcacheUtils.getCache("myCache", "name"));
	}
	
}
