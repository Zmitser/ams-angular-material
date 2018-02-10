package com.platform.ots.adminservice.aop.logging

import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.context.annotation.Configuration

@Aspect
@Configuration
class LoggingAspect {

    private val log = KotlinLogging.logger {}

    @Pointcut("within(com.platform.ots.adminservice.handler..*) || within(com.platform.ots.adminservice.router..*)")
    fun applicationPackagePointCut() {
    }


    @AfterThrowing(pointcut = "applicationPackagePointCut()", throwing = "throwable")
    fun logAfterThrowing(joinPoint: JoinPoint, throwable: Throwable) {
        log.error(throwable) {
            """
               Exception in ${joinPoint.signature.declaringTypeName}.${joinPoint.signature.name}()
               with cause = '${throwable.cause}' and exception = '${throwable.message}'
            """
        }
    }

    @Around("applicationPackagePointCut()")
    @Throws(Throwable::class)
    fun logAround(proceedingJoinPoint: ProceedingJoinPoint): Any {
        if (log.isDebugEnabled) {
            log.debug {
                """
                    Enter: ${proceedingJoinPoint.signature.declaringTypeName}.${proceedingJoinPoint.signature.name}()
                    with argument[s] = ${proceedingJoinPoint.args}
               """
            }
        }
        try {
            val proceed: Any = proceedingJoinPoint.proceed()
            if (log.isDebugEnabled) {
                log.debug {
                    """
                        Exit: ${proceedingJoinPoint.signature.declaringTypeName}.${proceedingJoinPoint.signature.name}()
                        with result = $proceed
                    """
                }
            }
            return proceed
        } catch (e: IllegalArgumentException) {
            log.error(e) {
                """
                    Illegal argument: ${proceedingJoinPoint.args}
                    in ${proceedingJoinPoint.signature.declaringTypeName}.${proceedingJoinPoint.signature.name}()
                """
            }
            throw  e
        }
    }
}
