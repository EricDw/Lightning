package com.dewildte.lightning

import com.dewildte.lightning.network.LOCAL_HOST
import io.ktor.network.tls.certificates.*
import io.ktor.server.engine.*
import java.io.File

fun ApplicationEngine.Configuration.envConfig() {
    val keystorePassword = "lightning-keystore-mot-de-passe"
    val privateKeyPassword = "lightning-key-mot-de-passe"
    val keystoreFileName = "lightningKeystore.jks"
    val certificateAlias = "lightningAlias"
    val keyStoreFile = File("build/$keystoreFileName")
    val keyStore = buildKeyStore {
        certificate(certificateAlias) {
            password = privateKeyPassword
            domains = listOf(LOCAL_HOST, "0.0.0.0", "localhost")
        }
    }
    keyStore.saveToFile(
        output = keyStoreFile,
        password = keystorePassword
    )

    connector {
        host = LOCAL_HOST
        port = 8080
    }
    sslConnector(
        keyStore = keyStore,
        keyAlias = certificateAlias,
        keyStorePassword = { keystorePassword.toCharArray() },
        privateKeyPassword = { privateKeyPassword.toCharArray() }) {
        host = LOCAL_HOST
        port = 8443
        keyStorePath = keyStoreFile
    }
}