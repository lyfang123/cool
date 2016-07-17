package com.uwaytech.cool.common.resolver;


import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/3/2 0002.
 */
public class XObjArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(XObject.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        Class<?> paramType = parameter.getParameterType();

        Object attribute = BeanUtils.instantiateClass(paramType);

        WebDataBinder binder = binderFactory.createBinder(webRequest, attribute, null);

        Field[] fields = paramType.getDeclaredFields();
        for (Field field : fields) {
            XValue xValue = field.getAnnotation(XValue.class);
            if (null == xValue) {
                continue;
            }

            String value = xValue.value();
            String x = webRequest.getParameter(value);
            String name = field.getName();
        }

        return attribute;
    }
}
