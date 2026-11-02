# 如何统一捕获异常
1. @ControllerAdvice 标注异常处理类
`它的主要作用是“辅助”控制器，允许你在一个地方定义的方法，全局性地应用到多个（甚至所有）@Controller 上。默认情况下，如果不做任何配置，它的作用范围是所有的控制器。`
应用核心场景：全局异常处理（最常用）

2. @ExceptionHandler 异常处理器
![@ControllerAdvice](assets/exception/ControllerAdvice.png)


# 异常分类
首先需要区分错误和异常
- Error 错误，是代码无法处理的，程序无法启动或jvm虚拟机引起的
- Exception 异常，可以通过代码处理的才叫异常

## Exception 又分为两种异常
- CheckedExcption 可以被检测到的异常（这种异常必须要强制处理的，函数抛出异常也算是处理了异常不是只return异常）
可以处理的适用（比如文件路径不正确）
- RuntimeException 运行时异常
无法处理的适用（如用户输入不存在的订单id）

## 已知异常和未知异常
- 已知异常-程序员处理了的异常
如 分母 为0时候，判断了并抛出了异常
- 未知异常 - 未经过思考的异常
如 分母 为0时候，没有判断但是系统抛出了


# 自定义 HttpException


# 同时监听Exception和HttpException





