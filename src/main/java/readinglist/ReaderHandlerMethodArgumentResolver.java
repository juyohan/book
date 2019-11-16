package readinglist;

import org.springframework.security.core.Authentication;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import readinglist.Read.ReaderDB;

public class ReaderHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter){
        // 바인딩할 클래스를 지정해주면 됨.
        return ReaderDB.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer
    , NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Authentication auth=(Authentication) webRequest.getUserPrincipal();
        return auth != null && auth.getPrincipal() instanceof ReaderDB ?
                auth.getPrincipal() : null;
    }

}
