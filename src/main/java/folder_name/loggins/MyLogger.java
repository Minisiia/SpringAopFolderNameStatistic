package folder_name.loggins;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
@Aspect
public class MyLogger {

    @Pointcut("execution(* *(..)) && within(folder_name.objects.*)")
    private void allMethods() {
    }


    @Around("allMethods()")
    public Object watchTime(ProceedingJoinPoint joinpoint) {
        long start = System.currentTimeMillis();
        System.out.println("Method begin: " + joinpoint.getSignature().toShortString() + " >>");
        Object output = null;

        for (Object object : joinpoint.getArgs()) {
            System.out.println("Param: " + object);

            String[] path = object.toString().split("\\\\");
            String folderName = path[path.length - 1];
            System.out.println("Folder Name: " + folderName);
        }

        try {
            output = joinpoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("method end: " + joinpoint.getSignature().toShortString() + ", time=" + time + " ms <<");
        System.out.println();

        return output;
    }

    @AfterReturning(pointcut = "allMethods()", returning = "obj")
    public void print(Object obj) {

        System.out.println("Print info begin >>");

        if (obj instanceof Set) {
            Set set = (Set) obj;
            for (Object object : set) {
                System.out.println(object);
            }

        } else if (obj instanceof Map) {
            Map map = (Map) obj;
            for (Object object : map.keySet()) {
                System.out.println("key=" + object + ", " + map.get(object));
            }
        }

        System.out.println("Print info end <<");
        System.out.println();

    }

    @Pointcut("execution(* folder_name.objects.SomeService.*(..))")
    public void getValue() {

    }

    @Before("getValue()")
    public void init() {
        System.out.println("init");
    }

    @After("getValue()")
    public void close() {
        System.out.println("close");
    }

    @AfterReturning(pointcut = "getValue()", returning = "obj")
    public void printNumbers(Object obj) {
        System.out.println(obj);
    }
}
