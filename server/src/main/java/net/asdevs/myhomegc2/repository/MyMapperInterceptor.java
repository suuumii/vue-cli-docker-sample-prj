package net.asdevs.myhomegc2.repository;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class MyMapperInterceptor {

    @Before("execution(* net.asdevs.myhomegc2.repository.*Mapper.insert*(..)) || "
            + "execution(* net.asdevs.myhomegc2.repository.*Mapper.update*(..))")
    public void setValueToCommonColumn(JoinPoint joinPoint) throws Exception {
        // メソッド名を取得
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String methodName = method.getName();

        // メソッドの1番目の引数を取得
        Object obj = joinPoint.getArgs()[0];

        if (methodName.startsWith("insert")) {
            // メソッド名がinsertから始まる場合
            String created = LocalDateTime.now(ZoneOffset.UTC).toString();
            String createduser = SecurityContextHolder.getContext().getAuthentication().getName();
            setCreated(obj, created, createduser);
            setModified(obj, created, createduser);
        } else if (methodName.startsWith("update")) {
            // メソッド名がupdateから始まる場合
            String modified = LocalDateTime.now(ZoneOffset.UTC).toString();
            String modifiedUser = SecurityContextHolder.getContext().getAuthentication().getName();
            setModified(obj, modified, modifiedUser);
        }
    }

    public void setCreated(Object obj, String created, String createdUser) throws Exception {
        // 対象カラムのセッターを取得
        Method setCreatedMethod = ReflectionUtils.findMethod(obj.getClass(), "setCreated", String.class);
        Method setCreatedUserMethod = ReflectionUtils.findMethod(obj.getClass(), "setCreatedUser", String.class);

        if (Objects.nonNull(setCreatedMethod)) {
            setCreatedMethod.invoke(obj, created);
        }
        if (Objects.nonNull(setCreatedUserMethod)) {
            setCreatedUserMethod.invoke(obj, createdUser);
        }

    }

    public void setModified(Object obj, String modified, String modifiedUser) throws Exception {
        // 対象カラムのセッターを取得
        Method setModifiedMethod = ReflectionUtils.findMethod(obj.getClass(), "setModified", String.class);
        Method setModifiedUserMethod = ReflectionUtils.findMethod(obj.getClass(), "setModifiedUser", String.class);

        if (Objects.nonNull(setModifiedMethod)) {
            setModifiedMethod.invoke(obj, modified);
        }
        if (Objects.nonNull(setModifiedUserMethod)) {
            setModifiedUserMethod.invoke(obj, modifiedUser);
        }

    }
}
