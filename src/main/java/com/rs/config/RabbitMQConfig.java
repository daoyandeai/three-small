package com.rs.config;

//import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 消息队列配置
 * @ClassName: MQConfig
 * @Description: 
 * @Author sven
 * @DateTime 2020年4月23日 下午3:37:04
 */
@Configuration
public class RabbitMQConfig {
//	@Autowired
//    RabbitAdmin rabbitAdmin;
	
	/***************************定义队列、路由键、交换器名称******************************/
	/**
	 * 达州传感器队列名称
	 */
	public final String DAZHOU_SENSOR_QUEUE = "dazhou_sensor_queue";
	/**
	 * 达州传感器路由键名称
	 */
	public final String DAZHOU_SENSOR_ROUTING_KEY = "dazhou_sensor_routing_key";
	/**
	 * 达州传感器交换机名称
	 */
	public final String DAZHOU_SENSOR_EXCHANGE = "dazhou_sensor_exchange";
	
	/**
	 * 达州传感器日志队列名称
	 */
	public final String DAZHOU_SENSOR_LOG_QUEUE = "dazhou_sensor_log_queue";
	/**
	 * 达州传感器日志路由键名称
	 */
	public final String DAZHOU_SENSOR_LOG_ROUTING_KEY = "dazhou_sensor_log_routing_key";
	/**
	 * 达州传感器日志交换机名称
	 */
	public final String DAZHOU_SENSOR_LOG_EXCHANGE = "dazhou_sensor_log_exchange";
	
	
	/**
	 * 测试传感器队列名称
	 */
	public final String TEST_SENSOR_QUEUE = "test_sensor_queue";
	/**
	 * 测试传感器路由键名称
	 */
	public final String TEST_SENSOR_ROUTING_KEY = "test_sensor_routing_key";
	/**
	 * 测试传感器交换机名称
	 */
	public final String TEST_SENSOR_EXCHANGE = "test_sensor_exchange";
	
	/**
	 * 测试传感器日志队列名称
	 */
	public final String TEST_SENSOR_LOG_QUEUE = "test_sensor_log_queue";
	/**
	 * 测试传感器日志路由键名称
	 */
	public final String TEST_SENSOR_LOG_ROUTING_KEY = "test_sensor_log_routing_key";
	/**
	 * 测试传感器日志交换机名称
	 */
	public final String TEST_SENSOR_LOG_EXCHANGE = "test_sensor_log_exchange";
	
	/***************************定义队列名称******************************/
	@Bean
	public Queue daZhouSensorQueue() {
		return new Queue(DAZHOU_SENSOR_QUEUE);
	}
	@Bean
	public Queue daZhouSensorLogQueue() {
		return new Queue(DAZHOU_SENSOR_LOG_QUEUE);
	}
	
	@Bean
	public Queue testSensorQueue() {
		return new Queue(TEST_SENSOR_QUEUE);
	}
	@Bean
	public Queue testSensorLogQueue() {
		return new Queue(TEST_SENSOR_LOG_QUEUE);
	}
	/***************************定义交换机名称******************************/
	@Bean
	public DirectExchange daZhouSensorExchange() {
		return new DirectExchange(DAZHOU_SENSOR_EXCHANGE);
	}
	@Bean
	public DirectExchange daZhouSensorLogExchange() {
		return new DirectExchange(DAZHOU_SENSOR_LOG_EXCHANGE);
	}
	
	@Bean
	public DirectExchange testSensorExchange() {
		return new DirectExchange(TEST_SENSOR_EXCHANGE);
	}
	@Bean
	public DirectExchange testSensorLogExchange() {
		return new DirectExchange(TEST_SENSOR_LOG_EXCHANGE);
	}
	/***************************绑定交换器、队列名称******************************/
	@Bean
	public Binding bindingDaZhouSENSORExchange(Queue daZhouSensorQueue,DirectExchange daZhouSensorExchange) {
		return BindingBuilder.bind(daZhouSensorQueue).to(daZhouSensorExchange).with(DAZHOU_SENSOR_ROUTING_KEY);
	}
	
	@Bean
	public Binding bindingDaZhouSENSORLogExchange(Queue daZhouSensorLogQueue,DirectExchange daZhouSensorLogExchange) {
		return BindingBuilder.bind(daZhouSensorLogQueue).to(daZhouSensorLogExchange).with(DAZHOU_SENSOR_LOG_ROUTING_KEY);
	}
	
	@Bean
	public Binding bindingTESTSENSORExchange(Queue testSensorQueue,DirectExchange testSensorExchange) {
		return BindingBuilder.bind(testSensorQueue).to(testSensorExchange).with(TEST_SENSOR_ROUTING_KEY);
	}
	
	@Bean
	public Binding bindingTESTSENSORLogExchange(Queue testSensorLogQueue,DirectExchange testSensorLogExchange) {
		return BindingBuilder.bind(testSensorLogQueue).to(testSensorLogExchange).with(TEST_SENSOR_LOG_ROUTING_KEY);
	}
	/**
	 * 
	 * @Title: rabbitAdmin
	 * @Description: 创建初始化RabbitAdmin对象
	 * @Author tangsh
	 * @DateTime 2020年4月24日 上午11:03:45
	 * @param connectionFactory
	 * @return
	 */
//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
//        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
//        rabbitAdmin.setAutoStartup(true);
//        return rabbitAdmin;
//    }
    /**
     * 
     * @Title: createExchangeQueue
     * @Description: 创建交换机和对列
     * @Author tangsh
     * @DateTime 2020年4月24日 上午11:03:49
     */
//    @Bean
//    public void createExchangeQueue (){
//    	rabbitAdmin.declareQueue(dzSensorQueue());
//    	rabbitAdmin.declareExchange(dzSensorExchange());
//    }

}
