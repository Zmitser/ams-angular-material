package com.platform.ots.adminservice.config

import com.hazelcast.config.Config
import com.hazelcast.config.EvictionPolicy.LRU
import com.hazelcast.config.ManagementCenterConfig
import com.hazelcast.config.MapConfig
import com.hazelcast.config.MaxSizeConfig
import com.hazelcast.config.MaxSizeConfig.MaxSizePolicy.USED_HEAP_SIZE
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.spring.cache.HazelcastCacheManager
import com.platform.ots.adminservice.constant.Constants
import mu.KotlinLogging
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import java.lang.System.setProperty
import javax.annotation.PreDestroy

@Configuration
@EnableCaching
class CacheModuleConfiguration(val environment: Environment) {

    private val log = KotlinLogging.logger {}

    @PreDestroy
    fun destroy() {
        log.info { "Closing Cache Manager" }
        Hazelcast.shutdownAll()
    }

    @Bean
    fun cacheManager(hazelcastInstance: HazelcastInstance): CacheManager {
        log.debug { "Starting HazelCast Cache Manager" }
        return HazelcastCacheManager(hazelcastInstance)
    }

    @Bean
    fun hazelCastInstance(): HazelcastInstance? {
        log.debug { "Configure HazelCast" }
        val hazelCastInstance: HazelcastInstance? = Hazelcast.getHazelcastInstanceByName("amsApp")
        checkNotNull(hazelCastInstance) {
            val config = Config()
            config.instanceName = "amsApp"
            config.networkConfig.port = 5701
            config.networkConfig.isPortAutoIncrement = true
            if (environment.acceptsProfiles(Constants.SPRING_PROFILE_DEVELOPMENT)) {
                setDevelopmentProperties(config)
            }
            config.managementCenterConfig = managementCenterConfig()
            config.mapConfigs["default"] = mapConfig()
            config.mapConfigs["com.platform.ots.adminservice.domain.*"] = domainMapConfig()

            return Hazelcast.newHazelcastInstance(config)
        }
        return hazelCastInstance
    }

    private fun setDevelopmentProperties(config: Config) {
        setProperty("hazelcast.local.localAddress", "127.0.0.1")
        config.networkConfig.join.awsConfig.isEnabled = false
        config.networkConfig.join.multicastConfig.isEnabled = false
        config.networkConfig.join.tcpIpConfig.isEnabled = false
    }

    private fun domainMapConfig(): MapConfig {
        val domainConfig = MapConfig()
        domainConfig.timeToLiveSeconds = 3600
        return domainConfig
    }

    private fun mapConfig(): MapConfig {
        val mapConfig = MapConfig()
        mapConfig.backupCount = 0
        mapConfig.evictionPolicy = LRU
        mapConfig.maxSizeConfig = MaxSizeConfig(0, USED_HEAP_SIZE)
        return mapConfig
    }

    private fun managementCenterConfig(): ManagementCenterConfig {
        val managementCenterConfig = ManagementCenterConfig()
        managementCenterConfig.isEnabled = false
        managementCenterConfig.url = "http://localhost:8180/mancenter"
        managementCenterConfig.updateInterval = 3
        return managementCenterConfig
    }

}