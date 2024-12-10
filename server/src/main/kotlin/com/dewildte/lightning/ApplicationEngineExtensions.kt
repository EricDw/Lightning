package com.dewildte.lightning

import com.dewildte.lightning.network.LOCAL_HOST
import io.ktor.network.tls.certificates.*
import io.ktor.server.engine.*
import java.io.File

fun ApplicationEngine.Configuration.envConfig() {
    val appPassword = "lightning app mot de passe"
    val privateAppPassword = "lightning app mot de passe"
    val keystoreFileName = "keystore.jks"
    val certificateAlias = "lightningAlias"
    val keyStoreFile = File("build/$keystoreFileName")
    val keyStore = buildKeyStore {
        certificate(certificateAlias) {
            password = privateAppPassword
            domains = listOf(LOCAL_HOST, "0.0.0.0", "localhost")
        }
    }
    keyStore.saveToFile(
        output = keyStoreFile,
        password = appPassword
    )

    connector {
        host = LOCAL_HOST
        port = 8080
    }
    sslConnector(
        keyStore = keyStore,
        keyAlias = certificateAlias,
        keyStorePassword = { appPassword.toCharArray() },
        privateKeyPassword = { privateAppPassword.toCharArray() }) {
        host = LOCAL_HOST
        port = 8443
        keyStorePath = keyStoreFile
    }
}