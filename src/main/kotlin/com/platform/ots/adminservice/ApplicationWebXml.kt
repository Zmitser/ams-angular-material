package com.platform.ots.adminservice

import com.platform.ots.adminservice.util.DefaultProfileUtil
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

class ApplicationWebXml : SpringBootServletInitializer() {

    override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder {
        DefaultProfileUtil.addDefaultProfile(builder.application())
        return builder.sources(AdminServiceApplication::class.java)
    }
}