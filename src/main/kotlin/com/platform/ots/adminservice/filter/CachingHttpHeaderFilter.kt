package com.platform.ots.adminservice.filter

import com.platform.ots.adminservice.constant.Constant
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import java.lang.System.currentTimeMillis
import java.util.concurrent.TimeUnit.DAYS


@Component
class CachingHttpHeaderFilter : WebFilter {


    private val dateTime = currentTimeMillis()
    private val cacheTimeToLive: Long = DAYS.toMillis(1461)


    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        exchange.response.headers.setDate(Constant.LAST_MODIFIED, dateTime)
        exchange.response.headers.setDate(Constant.EXPIRES, this.cacheTimeToLive + currentTimeMillis())
        exchange.response.headers.set(Constant.PRAGMA, Constant.CACHE)
        exchange.response.headers.set(Constant.CACHE_CONTROL, "max-age=${this.cacheTimeToLive}, public")
        return chain.filter(exchange)
    }
}