package eureka.cloud.servicehello;

import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.discovery.DiscoveryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.cloud.client.discovery.DiscoveryClient;

@RestController
public class HelloEndpoint {


    protected Logger logger=LoggerFactory.getLogger(HelloEndpoint.class);

    @Autowired
    private EurekaInstanceConfig eurekaInstanceConfig;
    @Value("${server.port}")
    private int serverPort = 0;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        this.logger.info("/hello, instanceId:{}, host:{}", eurekaInstanceConfig.getInstanceId(), eurekaInstanceConfig.getHostName(false));
        return "Hello, Spring Cloud! My port is " + String.valueOf(serverPort);
    }

    @RequestMapping(value="/offline",method = RequestMethod.GET)
    public void offline(){
        DiscoveryManager.getInstance().shutdownComponent();
    }
}
