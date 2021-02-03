# eureka-ribbon-client-loadbalancing

## pom.xml => spring-cloud-starter-netflix-ribbon
## Application.java => @SpringBootApplication @EnableEurekaClient @RibbonClient(name="any-name", configuration=<RibbonClientConfig.class>)
## Configuration <RibbonClientConfig.java> This class can be left empty with just the annotation as well
  ` 
    @Configuration
  
    public class RibbonClientConfig {
    
      @Bean
      public IRule rule() {
      // Rule to be applied for load balancing
        return new WeightedResponseTimeRule();
        //return new RoundRobinRule();
        //return new AvailabilityFilteringRule();
      }
    }
   ` 
 ## Controller 
   `
      @Autowired
      private LoadBalancerClient loadBalancerClient;
      
      @GetMapping
      public void method() {
        ServiceInstance instance = loadBalancerClient.choose("CALLING-SERVICE-ID");
        System.out.println(instance.getPort());
      }
    `
  ## Application.properties => Note changes mentioned are only necessary when we are looking for eureka not assigning dynamic instances
    `
      ribbon.eureka.enabled=false
      CALLING-SERVICE-ID.ribbon.listOfServers=http://host:port1,http://host:port2,http://host:port3 
    `
