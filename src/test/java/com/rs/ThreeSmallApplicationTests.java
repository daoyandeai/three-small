package com.rs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.rs.util.BaiDuMapUtil;

@SpringBootTest
class ThreeSmallApplicationTests {

	
	@Test
	void contextLoads() {
		test();
	}
	
	
	public static void test() {
		String address = "四川省成都市金堂县赵镇街道办事处梅林社区居委会";
		BaiDuMapUtil.getLngAndLatByLoc(address);
		
	}
}
