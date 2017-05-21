package honey;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weixin.honey.pojo.Car;
import com.weixin.honey.util.RedisUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestRedis {
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Test
	public void test1() throws Exception{
		Car car1 = new Car(1, 1);
		Car car2 = new Car(2, 2);
		Car car3 = new Car(3, 3);
		Car car4 = new Car(4, 4);

		redisUtils.setList("redisCar", car1);
		redisUtils.setList("redisCar", car2);
		redisUtils.setList("redisCar", car3);
		redisUtils.setList("redisCar", car4);
		
		System.out.println(redisUtils.getList("redisCar", 0, -1));
		
		redisUtils.removeList("redisCar", 1, new Car(2, 2));
		
		List<Object> objList = redisUtils.getList("redisCar", 0, -1);
		for(Object obj:objList){
			Car car = (Car) obj;
			System.out.println(car.getUserId());
		}
		
		redisUtils.delete("redisCar");
	}
}
