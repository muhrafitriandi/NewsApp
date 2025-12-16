package com.yandey.data.chucker.decoder

import com.chuckerteam.chucker.api.BodyDecoder
import okhttp3.Request
import okhttp3.Response
import okio.ByteString
import java.io.ByteArrayInputStream
import java.util.zip.GZIPInputStream

class GzipBodyDecoder : BodyDecoder {
    override fun decodeRequest(request: Request, body: ByteString): String? = decodeByEncoding(request.header("Content-Encoding"), body)

    override fun decodeResponse(response: Response, body: ByteString): String? = decodeByEncoding(response.header("Content-Encoding"), body)

    private fun decodeByEncoding(encoding: String?, body: ByteString): String? {
        val encodingLower = encoding?.lowercase() ?: return null

        return try {
            when (encodingLower) {
                "gzip", "x-gzip" -> gunzipToString(body.toByteArray())
                else -> null
            }
        } catch (_: Exception) {
            null
        }
    }

    private fun gunzipToString(input: ByteArray): String {
        GZIPInputStream(ByteArrayInputStream(input)).use { gis ->
            return gis.readBytes().decodeToString()
        }
    }
}