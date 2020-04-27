package com.etoak.exception;

        import com.etoak.comons.CommonResult;
        import lombok.extern.slf4j.Slf4j;
        import org.omg.CORBA.UserException;
        import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.bind.annotation.ResponseBody;
        import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j //可以在这里记下日志
public class GlobalExceptionHandler {

    @ExceptionHandler(ParamException.class)
    public ModelAndView handleParamException(ParamException e) {
        log.error(e.getMessage(), e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;

    }
    @ExceptionHandler(UserLoginException.class)
    @ResponseBody
    public CommonResult handleUserLoginException(UserLoginException e){
        log.error(e.getMessage(),e);
        return new CommonResult(CommonResult.FAILED_CODE,e.getMessage());
    }
}