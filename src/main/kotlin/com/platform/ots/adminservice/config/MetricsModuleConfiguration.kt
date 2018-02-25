package com.platform.ots.adminservice.config

import com.codahale.metrics.JmxReporter
import com.codahale.metrics.JvmAttributeGaugeSet
import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.Slf4jReporter
import com.codahale.metrics.health.HealthCheckRegistry
import com.codahale.metrics.jvm.*
import com.platform.ots.adminservice.constant.Constants
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter
import com.zaxxer.hikari.HikariDataSource
import mu.KotlinLogging
import org.slf4j.MarkerFactory.getMarker
import org.springframework.context.annotation.Configuration
import java.lang.management.ManagementFactory.getPlatformMBeanServer
import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.TimeUnit.SECONDS

@Configuration
@EnableMetrics
class MetricsModuleConfiguration(val hikariDataSource: HikariDataSource) : MetricsConfigurerAdapter() {

    private val log = KotlinLogging.logger {}


    override fun getMetricRegistry(): MetricRegistry {
        val metricRegistry = MetricRegistry()
        metricRegistry.register(Constants.PROPS_METRIC_REG_JVM_MEMORY, MemoryUsageGaugeSet())
        metricRegistry.register(Constants.PROPS_METRIC_REG_JVM_GARBAGE, GarbageCollectorMetricSet())
        metricRegistry.register(Constants.PROPS_METRIC_REG_JVM_THREADS, ThreadStatesGaugeSet())
        metricRegistry.register(Constants.PROPS_METRIC_REG_JVM_FILES, FileDescriptorRatioGauge())
        metricRegistry.register(Constants.PROPS_METRIC_REG_JVM_BUFFERS, BufferPoolMetricSet(getPlatformMBeanServer()))
        metricRegistry.register(Constants.PROPS_METRIC_REG_JVM_ATTRIBUTES, JvmAttributeGaugeSet())
        return metricRegistry
    }


    override fun getHealthCheckRegistry(): HealthCheckRegistry {
        return HealthCheckRegistry()
    }


    override fun configureReporters(metricRegistry: MetricRegistry) {
        log.debug("Monitoring Data Source")
        hikariDataSource.metricRegistry = metricRegistry
        log.debug("Initializing Metrics  JMX reporting")
        registerReporter(JmxReporter.forRegistry(metricRegistry).build()).start()
        log.debug("Initializing Metrics Log Reporting")
        val slf4jReporter: Slf4jReporter = Slf4jReporter.forRegistry(metricRegistry)
                .outputTo(KotlinLogging.logger("metrics"))
                .markWith(getMarker("metrics"))
                .convertRatesTo(SECONDS)
                .convertDurationsTo(MILLISECONDS)
                .build()
        registerReporter(slf4jReporter).start(60, SECONDS)
    }
}