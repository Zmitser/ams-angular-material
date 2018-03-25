package com.platform.ots.adminservice.security

import org.springframework.security.crypto.bcrypt.BCrypt.*
import org.springframework.security.crypto.password.PasswordEncoder
import java.nio.charset.Charset
import java.security.SecureRandom
import java.util.Base64.getDecoder


class CustomPasswordEncoder : PasswordEncoder {
    override fun encode(rawPassword: CharSequence): String {
        val random = SecureRandom()
        val bytes = ByteArray(20)
        random.nextBytes(bytes)
        return hashpw(rawPassword.toString(), gensalt(10, random))
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        val decodedString = String(getDecoder().decode(rawPassword.toString().toByteArray(Charset.forName("UTF-8"))), Charset.forName("UTF-8"))
        return checkpw(decodedString, encodedPassword)
    }
}