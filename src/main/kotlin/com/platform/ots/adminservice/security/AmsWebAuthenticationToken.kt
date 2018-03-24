package com.platform.ots.adminservice.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority


class AmsWebAuthenticationToken(principal: Any?, credentials: Any?, authorities: List<GrantedAuthority>) : UsernamePasswordAuthenticationToken(principal, credentials, authorities)