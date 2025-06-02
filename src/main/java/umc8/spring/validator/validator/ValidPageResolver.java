package umc8.spring.validator.validator;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc8.spring.apiPayload.exception.PageOutOfRangeException;
import umc8.spring.validator.annotation.ValidPage;

@Component
public class ValidPageResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ValidPage.class)
                && parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String pageStr = webRequest.getParameter("page");
        int page = (pageStr != null) ? Integer.parseInt(pageStr) : 1;
        if(page < 1) throw new PageOutOfRangeException();
        return page -1;
    }
}
