package ru.kpfu.itis.demo.blog.web.aspect;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CacheAspect {

    private Logger logger = Logger.getLogger(CacheAspect.class);
    private Map<String, Object> cache;

    public CacheAspect() {
        cache = new HashMap<String, Object>();
    }

    @Pointcut("execution(@ru.kpfu.itis.demo.blog.web.aspect.Cacheable * *.*(..))")
    @SuppressWarnings("unused")
    private void cache() {
    }

    @Around("@annotation(ru.kpfu.itis.demo.blog.web.aspect.Cacheable)")
    public Object aroundCachedMethods(ProceedingJoinPoint thisJoinPoint)
            throws Throwable {
        logger.debug("Execution of Cacheable method catched");

        // generate the key under which cached value is stored
        // will look like caching.aspectj.Calculator.sum(Integer=1;Integer=2;)
        StringBuilder keyBuff = new StringBuilder();

        // append name of the class
        keyBuff.append(thisJoinPoint.getTarget().getClass().getName());

        // append name of the method
        keyBuff.append(".").append(thisJoinPoint.getSignature().getName());

        keyBuff.append("(");
        // loop through cacheable method arguments
        for (final Object arg : thisJoinPoint.getArgs()) {
            // append argument type and value
            keyBuff.append(arg.getClass().getSimpleName() + "=" + arg + ";");
        }
        keyBuff.append(")");
        String key = keyBuff.toString();

        logger.debug("Key = " + key);
        Object result = cache.get(key);
        if (result == null) {
            logger.debug("Result not yet cached. Must be calculated...");
            result = thisJoinPoint.proceed();
            logger.info("Storing calculated value '" + result + "' to cache");
            cache.put(key, result);
        } else {
            logger.debug("Result '" + result + "' was found in cache");
        }

        return result;
    }

}
