package honey;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class LoggerTest {
	
	private static final Logger logger = Logger.getLogger(LoggerTest.class);

	@Test
	public void test1(){
		logger.info("普通消息");
		logger.error("错误消息错误消息");
	}
}
