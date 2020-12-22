package com.jjcc.zsl.cloud.common.exception.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSONObject;
import com.jjcc.zsl.cloud.common.exception.BadRequestException;
import com.jjcc.zsl.cloud.common.exception.EntityExistException;
import com.jjcc.zsl.cloud.common.exception.EntityNotFoundException;
import com.jjcc.zsl.cloud.common.util.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     */
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<ApiError> badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getStatus(),e.getMessage()));
	}

    /**
     * 限流异常
     * @param e
     * @return
     */
	@ExceptionHandler(value = FlowException.class)
	public ResponseEntity<ApiError> flowException(FlowException e) {
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error("系统繁忙请稍后再试！"));
    }

    /**
     * 热点参数限流异常
     * @title paramFlowException
     * @author Jjcc
     * @param e
     * @return org.springframework.http.ResponseEntity<com.jjcc.zsl.cloud.common.exception.handler.ApiError>
     * @createTime 2020/12/17 19:52
     */
    @ExceptionHandler(value = ParamFlowException.class)
    public ResponseEntity<ApiError> paramFlowException(ParamFlowException e) {
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error("系统繁忙请稍后再试！"));
    }

    /**
     * 系统自适应限流异常
     * @title systemBlockException
     * @author Jjcc
     * @param e
     * @return org.springframework.http.ResponseEntity<com.jjcc.zsl.cloud.common.exception.handler.ApiError>
     * @createTime 2020/12/17 20:24
     */
    @ExceptionHandler(value = SystemBlockException.class)
    public ResponseEntity<ApiError> systemBlockException(SystemBlockException e) {
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error("系统繁忙请稍后再试！"));
    }

    /**
     * 黑白名单限制异常
     * @title authorityException
     * @author Jjcc
     * @param e
     * @return org.springframework.http.ResponseEntity<com.jjcc.zsl.cloud.common.exception.handler.ApiError>
     * @createTime 2020/12/17 20:24
     */
    @ExceptionHandler(value = AuthorityException.class)
    public ResponseEntity<ApiError> authorityException(AuthorityException e) {
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error("系统繁忙请稍后再试！"));
    }


    @ExceptionHandler(value = BlockException.class) // 因为这里是示例，所以暂时使用 JSONObject，实际项目最终定义一个 CommonResult。
    public JSONObject blockExceptionHandler(BlockException blockException) {
        return new JSONObject().fluentPut("code", 1024)
                .fluentPut("msg", "请求被拦截，拦截类型为 " + blockException.getClass().getSimpleName());
    }

    /**
     * 服务降级异常；慢调用比例
     * ? 异常比例、异常数沒有效果
     * @param e
     * @return
     */
    @ExceptionHandler(value = DegradeException.class)
    public ResponseEntity<ApiError> degradeException(DegradeException e) {
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error("系统繁忙请稍后再试！"));
    }


    /**
     * 处理 EntityExist
     */
    @ExceptionHandler(value = EntityExistException.class)
    public ResponseEntity<ApiError> entityExistException(EntityExistException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }

    /**
     * 处理 EntityNotFound
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ApiError> entityNotFoundException(EntityNotFoundException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(NOT_FOUND.value(),e.getMessage()));
    }

    /**
     * 处理所有接口数据验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        String[] str = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split("\\.");
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        if("不能为空".equals(message)){
            message = str[1] + ":" + message;
        }
        return buildResponseEntity(ApiError.error(message));
    }


//    /**
//     * 处理所有不可知的异常
//     */
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity handleException(Throwable e){
//        // 打印堆栈信息
//        log.error(ThrowableUtil.getStackTrace(e));
//        return buildResponseEntity(ApiError.error(e.getMessage()));
//    }

    /**
     * 统一返回
     */
    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}
