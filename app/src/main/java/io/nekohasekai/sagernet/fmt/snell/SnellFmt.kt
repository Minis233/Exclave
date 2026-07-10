/******************************************************************************
 * Snell share-link helpers for Exclave.
 * snell://psk@host:port?version=4&obfs=off&obfs-host=#name
 * (compatible with common Clash / Surge-style query keys)
 ******************************************************************************/

package io.nekohasekai.sagernet.fmt.snell

import io.nekohasekai.sagernet.ktx.*
import libexclavecore.Libexclavecore

fun parseSnell(url: String): SnellBean {
    val link = Libexclavecore.parseURL(url)
    return SnellBean().apply {
        name = link.fragment
        serverAddress = link.host.ifEmpty { error("empty host") }
        serverPort = link.port
        // username carries psk in snell://psk@host:port form; also accept password=
        psk = when {
            link.username.isNotEmpty() -> link.username
            link.password.isNotEmpty() -> link.password
            else -> link.queryParameter("psk") ?: error("empty psk")
        }
        link.queryParameter("version")?.toIntOrNull()?.also { version = it }
        link.queryParameter("obfs")?.also { obfs = it }
        // some generators put obfs mode in "obfs-mode"
        link.queryParameter("obfs-mode")?.also { obfs = it }
        link.queryParameter("reuse")?.also {
            reuse = it == "1" || it.equals("true", ignoreCase = true)
        }
        initializeDefaultValues()
    }
}

fun SnellBean.toUri(): String? {
    val builder = Libexclavecore.newURL("snell").apply {
        setHostPort(serverAddress.ifEmpty { error("empty server address") }, serverPort)
        username = psk.ifEmpty { error("empty psk") }
        if (name.isNotEmpty()) {
            fragment = name
        }
    }
    builder.addQueryParameter("version", (version ?: 4).toString())
    if (!obfs.isNullOrEmpty() && obfs != SnellBean.OBFS_OFF) {
        builder.addQueryParameter("obfs", obfs)
    }
    if (reuse == true) {
        builder.addQueryParameter("reuse", "1")
    }
    return builder.string
}
