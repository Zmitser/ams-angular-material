package com.platform.ots.adminservice.filter

import org.springframework.http.HttpMethod.OPTIONS
import org.springframework.http.HttpStatus.OK
import org.springframework.stereotype.Component
import org.springframework.web.cors.reactive.CorsUtils.isCorsRequest
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono


@Component
class CorsFilter : WebFilter {

    private val ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN"
    private val ALLOWED_METHODS = "GET, PUT, POST, DELETE, OPTIONS"
    private val ALLOWED_ORIGIN = "*"
    private val MAX_AGE = "3600"

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val request = exchange.request
        if (isCorsRequest(request)) {
            val response = exchange.response
            val headers = response.headers
            headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN)
            headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS)
            headers.add("Access-Control-Max-Age", MAX_AGE)
            headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS)
            if (request.method === OPTIONS) {
                response.statusCode = OK
                return Mono.empty<Void>()
            }
        }
        return chain.filter(exchange)
    }
}