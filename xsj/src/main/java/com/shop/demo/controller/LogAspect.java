package com.shop.demo.controller;

import com.shop.demo.dao.MyLog;
import com.shop.demo.pojo.Log;
import com.shop.demo.service.LogServive;
import com.shop.demo.utiles.HttpContextUtils;
import com.shop.demo.utiles.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 系统日志：切面处理类
 * @author Alice
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private LogServive logServive;

    public LogAspect(LogServive logServive) {
        this.logServive = logServive;
    }

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.shop.demo.dao.MyLog)")
    public void logPoinCut() {
    }

    //切面配置通知
    @AfterReturning("logPoinCut()")
    public void addLog(JoinPoint joinPoint) {
        System.out.println("启动系统日志");

        Log log = new Log();

        try {
            HttpServletRequest request = HttpContextUtils.getHttpServletRequest();

//            获取操作人的ID
            String openid=WXLoginController.openid;
//            String openid="obUGC4rrDf-HgmFt5TrvIzSkDHQo";
            System.out.println(openid);
            log.setOpenid(openid);
            log.setNickname(logServive.selectnickname(openid));

            //从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();

            //获取操作
            MyLog myLog = method.getAnnotation(MyLog.class);
            if (myLog != null) {
                String value = myLog.value();
                //保存获取的操作
                log.setOperation(value);
            }

            //获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            //获取请求的方法名
            String methodName = method.getName();
            log.setMethod(className + "." + methodName);

            //获取用户ip地址
            log.setIP(IPUtils.getIpAddr(request));

            //获取创建时间
            Date date_Date=new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String date=formatter.format(date_Date).toString();
            log.setCreate_time(date);

            //调用service添加Log实体类到数据库
            logServive.addLog(log);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}