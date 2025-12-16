package com.yandey.data.chucker.decoder

import com.chuckerteam.chucker.api.BodyDecoder
import okhttp3.Request
import okhttp3.Response
import okio.ByteString

class CompositeBodyDecoder(private val decoders: List<BodyDecoder>) : BodyDecoder {
    override fun decodeRequest(request: Request, body: ByteString): String? {
        for (decoder in decoders) {
            decoder.decodeRequest(request, body)?.let { return it }
        }
        return null
    }

    override fun decodeResponse(response: Response, body: ByteString): String? {
        for (decoder in decoders) {
            decoder.decodeResponse(response, body)?.let { return it }
        }
        return null
    }
}
