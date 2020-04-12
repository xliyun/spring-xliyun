package com.xliyun.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)

 execution(* com.xliyun.spring.aop.aspect.*.*(..))
 这里问号表示当前项可以有也可以没有，其中各项的语义如下
 modifiers-pattern：方法的可见性，如public，protected；
 ret-type-pattern：方法的返回值类型，如int，void等；
 declaring-type-pattern：方法所在类的全路径名，如com.spring.Aspect；
 name-pattern：方法名类型，如buisinessService()；
 param-pattern：方法的参数类型，如java.lang.String；
 throws-pattern：方法抛出的异常类型，如java.lang.Exception；
 */
@Component
//在@Aspect中或者@before注解中都可以直接写表达式，不用非要先定义pointCut
//为所有代理对象是IndexDao的，单独创建一个切点
@Aspect("perthis(this(com.xliyun.spring.aop.aspect.IndexDao))")
@Scope("prototype")//让切面变成原型模式
public class AopAspect {

    //Introductions @DeclareParents注解，扫描aspect包下的所有类，引入Dao接口的IndexDao.class这个实现。
    //相当于让OrderDao实现Dao接口，实现的方法就用IndexDao的实现
    @DeclareParents(value="com.xliyun.spring.aop.aspect.*", defaultImpl=IndexDao.class)
    public static Dao dao;

    //一个切点要关联一个连接点  连接点就是IndexDao.query
    @Pointcut("execution(* com.xliyun.spring.aop.aspect.*.*(java.lang.String,..))")//任意修饰符(public，private都可以，所以直接去掉) 返回值类型
    //(public * com.xliyun.spring.aop.aspect.*.*(..))")
    public void pointCutExecution(){

    }

    //within最小粒度是类
    @Pointcut("within(com.xliyun.spring.aop.aspect.*)")
    public void pointCutWithin(){

    }

    //args是根据参数
    @Pointcut("args(java.lang.String)")
    public void pointCutArgs(){
    }

    //annotation是根据注解
    @Pointcut("@annotation(customizeAnnotation)")
    public void pointCutAnnotation(){
    }

    //this表示的都是代理对象等不等于IndexDao，也就是target目标对象
    @Pointcut("this(com.xliyun.spring.aop.aspect.IndexDao)")
    public void pointCutThis(){
    }

    //this表示的都是代理对象等不等于IndexDao，也就是target目标对象
    @Pointcut("target(com.xliyun.spring.aop.aspect.IndexDao)")
    public void pointCutTarget(){
    }

    //一个通知，通知到pointCut()这个切点的前面
    /**
     * location
     * logic
     */
    @Before("pointCutExecution() || pointCutArgs() || pointCutAnnotation()")
    public void before(JoinPoint joinPoint){
        System.out.println(joinPoint.getThis());
        System.out.println(joinPoint.getTarget());

        //切面是单例的
        System.out.println("当前切面的hashCode是："+this.hashCode());

        System.out.println("通知：before");
    }

    @Before("pointCutThis()")
    public void before2(){
        System.out.println("如果是cglib动态代理，this对象就等于IndexDao，this切点生效");
    }

    @Before("pointCutTarget()")
    public void before3(){
        System.out.println("target对象等于IndexDao对象，this切点生效");
    }

    @After("pointCutTarget()")
    public void after(){
        System.out.println("通知：After");
    }

    @Around("pointCutTarget()")
    public void around(ProceedingJoinPoint pjp){
        Object args[]= pjp.getArgs();
        if(args!=null && args.length>0){
            for(int i=0;i<args.length;i++){
                args[i]+=" **** 环绕通知参数追加";
            }
        }

        System.out.println("==============环绕通知前===============");
        //ProceedingJoinPoint就是一个正在增强的连接点，继承自JoinPoint
        //JoinPoint没有办法执行invoke，但是PorceedingJoinPoint可以用 proceed

        try {
            //处理下一个通知，或者目标方法的执行
            pjp.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("==============环绕通知后===============");
    }

}
