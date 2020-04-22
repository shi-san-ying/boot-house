package com.etoak.exception;

<<<<<<< HEAD
import com.sun.org.apache.xpath.internal.operations.Mod;
=======
>>>>>>> dev
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
<<<<<<< HEAD
@Slf4j //可以在这里记下日志
public class GlobalExceptionHandler {

    @ExceptionHandler(ParamException.class)
    public ModelAndView handleParamException(ParamException e){
        log.error(e.getMessage(),e);
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.addObject("error",e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;

    }
=======
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理ParamException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ParamException.class)
    public ModelAndView handleParamException(ParamException e) {
        log.error(e.getMessage(), e);
        ModelAndView modelAndView = new ModelAndView();
        // 将错误信息添加到request域中
        modelAndView.addObject("error", e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }


>>>>>>> dev
}
