To run this as main program you would need a servlet container.

Container is hooked as part of testing framework (check test package)

//Boot way
SpringApplication application = new SpringApplication(MvcJavaConfig.class);
application.setWebApplicationType(WebApplicationType.NONE);
application.run(args);