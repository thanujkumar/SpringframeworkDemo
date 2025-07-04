package org.tk.spring.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * At high level.
 *
 * <ol>
 *    <li> Instantiate the bean </li>
 *    <li> Populate properties </li>
 *    <li> Bean Name Aware </li>
 *    <li> Bean classloader Aware </li>
 *    <li> Bean Factory Aware </li>
 *    <li> Environment Aware </li>
 *    <li> EmbeddedValue Resolver Aware </li>
 *    <li> Resource Loader Aware </li>
 *    <li> ApplicationEvent Publisher Aware </li>
 *    <li> MessageSource Aware </li>
 *    <li> ApplicationContext Aware </li>
 *    <li> ServletContext Aware </li>
 *    <li> Pre-Initialization BeanPostProcessor </li>
 *    <li> Custom init-method </li>
 *    <li> InitializingBean afterPropertiesSet </li>
 *    <li> Post-Initialization BeanPostProcessor </li>
 *    <li> DisposableBean.destroy </li>
 * </ol>
 */
public class MainBeanLifeCycle {

    // Only two beans initialized, so will see BeanPostProcessor for JavaConfig and BeanLifeCycle
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);

        //List all the beans,
        Arrays.stream(annotationConfigApplicationContext.getBeanDefinitionNames()).forEach(System.out::println);

        annotationConfigApplicationContext.close();
    }
}

