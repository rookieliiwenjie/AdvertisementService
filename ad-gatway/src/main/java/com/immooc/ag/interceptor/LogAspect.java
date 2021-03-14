package com.immooc.ag.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by lwj32 on 2021/2/28.
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String STRING_START = "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";
    private static final String STRING_END   = "\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n";

    @Around("within(@org.springframework.web.bind.annotation.RestController *)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        StringBuffer classAndMethod = new StringBuffer();
        String target = targetClass.getName() + "#" + method.getName();

        String params = null;
        params = JSONObject.toJSONStringWithDateFormat(joinPoint.getArgs(), dateFormat, SerializerFeature.WriteMapNullValue);

        log.info(STRING_START + "{} 开始调用--> {} 参数:{} 参数名称:{}", classAndMethod.toString(), target, params, JSON.toJSONString(signature.getParameterNames()));

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long timeConsuming = System.currentTimeMillis() - start;

        JSONObject.toJSONStringWithDateFormat(joinPoint.getArgs(), dateFormat, SerializerFeature.WriteMapNullValue) ;
        log.info("\n{} 调用结束<-- {} 返回值:{} 耗时:{}ms" + STRING_END, classAndMethod.toString(), target, JSONObject.toJSONStringWithDateFormat(result, dateFormat, SerializerFeature.WriteMapNullValue), timeConsuming);
        return result;

    }
   /* @Around("within(@org.springframework.web.bind.annotation.RestController *)")
    public Object around2(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = null;
        args = joinPoint.getArgs();

        //   String toString();         //连接点所在位置的相关信息
        //   String toShortString();     //连接点所在位置的简短相关信息
        //   String toLongString();     //连接点所在位置的全部相关信息
        //   Object getThis();         //返回AOP代理对象，也就是com.sun.proxy.$Proxy18
        //   Object getTarget();       //返回目标对象，一般我们都需要它或者（也就是定义方法的接口或类，为什么会是接口呢？这主要是在目标对象本身是动态代理的情况下，例如Mapper。所以返回的是定义方法的对象如aoptest.daoimpl.GoodDaoImpl或com.b.base.BaseMapper<T, E, PK>）
        //   Object[] getArgs();       //返回被通知方法参数列表
        //   Signature getSignature();  //返回当前连接点签名  其getName()方法返回方法的FQN
        //   ，如void aoptest.dao.GoodDao.delete()或com.b.base.BaseMapper.insert(T)(需要注意的是，很多时候我们定义了子类继承父类的时候，我们希望拿到基于子类的FQN，
        //   这直接可拿不到，要依赖于AopUtils.getTargetClass(point.getTarget())获取原始代理对象，下面会详细讲解)
        //   SourceLocation getSourceLocation();//返回连接点方法所在类文件中的位置
        //   String getKind();        //连接点类型
        //   StaticPart getStaticPart(); //返回连接点静态部分  joinPoint.get


    }*/
}
