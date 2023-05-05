package com.leovp.audio.mediacodec.iter

import android.media.MediaCodec
import android.media.MediaCodecInfo
import android.media.MediaFormat
import java.nio.ByteBuffer

/**
 * Author: Michael Leo
 * Date: 2023/4/25 16:37
 */
interface IAudioMediaCodec {
    companion object {
        const val OPUS_AOPUSHDR = 0x524448_5355504F41L // "AOPUSHDR" in ASCII (little-endian)
        const val OPUS_AOPUSDLY = 0x594c44_5355504F41L // "AOPUSDLY" in ASCII (little-endian)
        const val OPUS_AOPUSPRL = 0x4c5250_5355504F41L // "AOPUSPRL" in ASCII (little-endian)

        const val AAC_PROFILE_LC = MediaCodecInfo.CodecProfileLevel.AACObjectLC
    }

    // ==========
    /**
     * Fill [inBuf] with valid data.
     *
     * @return The length of bytes of valid input data.
     */
    fun onInputData(inBuf: ByteBuffer): Int

    fun onOutputData(outBuf: ByteBuffer, info: MediaCodec.BufferInfo, isConfig: Boolean, isKeyFrame: Boolean)

    fun onOutputFormatChanged(codec: MediaCodec, format: MediaFormat) {}

    fun getPresentationTimeUs(): Long

    fun onError(codec: MediaCodec, e: MediaCodec.CodecException) {}

    // ==========
    fun onEndOfStream() {}
}
