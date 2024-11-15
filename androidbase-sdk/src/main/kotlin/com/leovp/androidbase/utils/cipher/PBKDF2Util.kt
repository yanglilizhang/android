@file:Suppress("unused", "MemberVisibilityCanBePrivate", "WeakerAccess")

package com.leovp.androidbase.utils.cipher

import androidx.annotation.IntRange
import androidx.annotation.RequiresApi
import com.leovp.android.utils.API
import java.security.SecureRandom
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

/**
 * - PBKDF(Password-based-Key-Derivative-Function, a successor of PBKDF1)
 *
 * This class is suit for generating a secure key.
 * In other words, you can use this class to get a secure key for other cipher.
 *
 * https://stackoverflow.com/questions/13433529/android-4-2-broke-my-encrypt-decrypt-code-and-the-provided-solutions-dont-work/39002997#39002997
 *
 * Author: Michael Leo
 * Date: 20-12-21 下午8:15
 */
object PBKDF2Util {
    // PBKDF2WithHmacSHA1: will produce a hash length of 160 bits
    // PBKDF2WithHmacSHA512: will produce a hash length of 512 bits
    private const val ALGORITHM_SHA1 = "PBKDF2WithHmacSHA1"

    /**
     * As of Android 8.0(Oreo) Level 26
     *
     * Check this [link](https://developer.android.com/reference/javax/crypto/SecretKeyFactory)
     * to see supported SecretKeyFactory algorithms.
     */
    private const val ALGORITHM_SHA512 = "PBKDF2WithHmacSHA512"

    private const val DEFAULT_SALT_LENGTH = 32
    private const val DEFAULT_ITERATIONS = 1000

    // ===== ALGORITHM_SHA512 - Start ================================================================

    /**
     * @return The generated SecretKey. If you just want to get the result in ByteArray, just call [SecretKey#encoded]
     */
    @RequiresApi(api = API.O)
    fun generateKeyWithSHA512(
        plainPassphrase: CharArray,
        salt: ByteArray,
        iterations: Int = DEFAULT_ITERATIONS,
        @IntRange(from = 128, to = 256) outputKeyLengthInBits: Int = DEFAULT_SALT_LENGTH shl 3
    ): SecretKey = generateKey(plainPassphrase, salt, iterations, outputKeyLengthInBits, ALGORITHM_SHA512)

    /**
     * @return The generated SecretKey. If you just want to get the result in ByteArray, just call [SecretKey#encoded]
     */
    @RequiresApi(api = API.O)
    fun generateKeyWithSHA512(
        plainPassphrase: CharArray,
        salt: String,
        iterations: Int = DEFAULT_ITERATIONS,
        @IntRange(from = 128, to = 256) outputKeyLengthInBits: Int = DEFAULT_SALT_LENGTH shl 3
    ): SecretKey = generateKeyWithSHA512(plainPassphrase, salt.toByteArray(), iterations, outputKeyLengthInBits)

    /**
     * @return The generated SecretKey. If you just want to get the result in ByteArray, just call [SecretKey#encoded]
     */
    @RequiresApi(api = API.O)
    fun generateKeyWithSHA512(
        plainPassphrase: String,
        salt: String,
        iterations: Int = DEFAULT_ITERATIONS,
        @IntRange(from = 128, to = 256) outputKeyLengthInBits: Int = DEFAULT_SALT_LENGTH shl 3
    ): SecretKey =
        generateKeyWithSHA512(plainPassphrase.toCharArray(), salt.toByteArray(), iterations, outputKeyLengthInBits)

    /**
     * @return The generated SecretKey. If you just want to get the result in ByteArray, just call [SecretKey#encoded]
     */
    @RequiresApi(api = API.O)
    fun generateKeyWithSHA512(
        plainPassphrase: String,
        salt: ByteArray,
        iterations: Int = DEFAULT_ITERATIONS,
        @IntRange(from = 128, to = 256) outputKeyLengthInBits: Int = DEFAULT_SALT_LENGTH shl 3
    ): SecretKey = generateKeyWithSHA512(plainPassphrase.toCharArray(), salt, iterations, outputKeyLengthInBits)

    /**
     * @return The generated SecretKey. If you just want to get the result in ByteArray, just call [SecretKey#encoded]
     */
    @RequiresApi(api = API.O)
    fun generateKeyWithSHA512(
        plainPassphrase: CharArray,
        saltLength: Int = DEFAULT_SALT_LENGTH,
        iterations: Int = DEFAULT_ITERATIONS,
        @IntRange(from = 128, to = 256) outputKeyLengthInBits: Int = DEFAULT_SALT_LENGTH shl 3
    ): SecretKey = generateKeyWithSHA512(plainPassphrase, generateSalt(saltLength), iterations, outputKeyLengthInBits)

    /**
     * @return The generated SecretKey. If you just want to get the result in ByteArray, just call [SecretKey#encoded]
     */
    @RequiresApi(api = API.O)
    fun generateKeyWithSHA512(
        plainPassphrase: String,
        saltLength: Int = DEFAULT_SALT_LENGTH,
        iterations: Int = DEFAULT_ITERATIONS,
        @IntRange(from = 128, to = 256) outputKeyLengthInBits: Int = DEFAULT_SALT_LENGTH shl 3
    ): SecretKey = generateKeyWithSHA512(plainPassphrase.toCharArray(), saltLength, iterations, outputKeyLengthInBits)

    // ===== ALGORITHM_SHA512 - End ================================================================

    // ===== ALGORITHM_SHA1 - Start ================================================================

    /**
     * @return The generated SecretKey. If you just want to get the result in ByteArray, just call [SecretKey#encoded]
     */
    fun generateKeyWithSHA1(
        plainPassphrase: CharArray,
        salt: ByteArray,
        iterations: Int = DEFAULT_ITERATIONS,
        @IntRange(from = 128, to = 256) outputKeyLengthInBits: Int = DEFAULT_SALT_LENGTH shl 3
    ): SecretKey = generateKey(plainPassphrase, salt, iterations, outputKeyLengthInBits, ALGORITHM_SHA1)

    /**
     * @return The generated SecretKey. If you just want to get the result in ByteArray, just call [SecretKey#encoded]
     */
    fun generateKeyWithSHA1(
        plainPassphrase: CharArray,
        salt: String,
        iterations: Int = DEFAULT_ITERATIONS,
        @IntRange(from = 128, to = 256) outputKeyLengthInBits: Int = DEFAULT_SALT_LENGTH shl 3
    ): SecretKey = generateKeyWithSHA1(plainPassphrase, salt.toByteArray(), iterations, outputKeyLengthInBits)

    /**
     * @return The generated SecretKey. If you just want to get the result in ByteArray, just call [SecretKey#encoded]
     */
    fun generateKeyWithSHA1(
        plainPassphrase: String,
        salt: String,
        iterations: Int = DEFAULT_ITERATIONS,
        @IntRange(from = 128, to = 256) outputKeyLengthInBits: Int = DEFAULT_SALT_LENGTH shl 3
    ): SecretKey =
        generateKeyWithSHA1(plainPassphrase.toCharArray(), salt.toByteArray(), iterations, outputKeyLengthInBits)

    /**
     * @return The generated SecretKey. If you just want to get the result in ByteArray, just call [SecretKey#encoded]
     */
    fun generateKeyWithSHA1(
        plainPassphrase: String,
        salt: ByteArray,
        iterations: Int = DEFAULT_ITERATIONS,
        @IntRange(from = 128, to = 256) outputKeyLengthInBits: Int = DEFAULT_SALT_LENGTH shl 3
    ): SecretKey = generateKeyWithSHA1(plainPassphrase.toCharArray(), salt, iterations, outputKeyLengthInBits)

    /**
     * @return The generated SecretKey. If you just want to get the result in ByteArray, just call [SecretKey#encoded]
     */
    fun generateKeyWithSHA1(
        plainPassphrase: CharArray,
        saltLength: Int = DEFAULT_SALT_LENGTH,
        iterations: Int = DEFAULT_ITERATIONS,
        @IntRange(from = 128, to = 256) outputKeyLengthInBits: Int = DEFAULT_SALT_LENGTH shl 3
    ): SecretKey = generateKeyWithSHA1(plainPassphrase, generateSalt(saltLength), iterations, outputKeyLengthInBits)

    /**
     * @return The generated SecretKey. If you just want to get the result in ByteArray, just call [SecretKey#encoded]
     */
    fun generateKeyWithSHA1(
        plainPassphrase: String,
        saltLength: Int = DEFAULT_SALT_LENGTH,
        iterations: Int = DEFAULT_ITERATIONS,
        @IntRange(from = 128, to = 256) outputKeyLengthInBits: Int = DEFAULT_SALT_LENGTH shl 3
    ): SecretKey = generateKeyWithSHA1(plainPassphrase.toCharArray(), saltLength, iterations, outputKeyLengthInBits)

    // ===== ALGORITHM_SHA1 - End ================================================================

    fun generateSalt(saltLength: Int = DEFAULT_SALT_LENGTH): ByteArray {
        val salt = ByteArray(saltLength) // Do NOT seed secureRandom! Automatically seeded from system entropy.
        SecureRandom().nextBytes(salt)
        return salt
    }

    // =====================================
    /**
     * @param iterations Default value 1000.
     * Number of PBKDF2 hardening rounds to use. Larger values increase
     * computation time. You should select a value that causes computation
     * to take >100ms.
     *
     * @param outputKeyLengthInBits Default value 32 shl 3 = 256
     * AES allows 128, 192 and 256 bit of key length. In other words 16, 24 or 32 byte.
     *
     * val random = SecureRandom()
     * val salt = ByteArray(32)
     * random.nextBytes(salt)
     *
     * @return The encrypted byte array. To get string result, just call ```toHexStringLE(true, "")```
     */
    private fun generateKey(
        plainPassphrase: CharArray,
        salt: ByteArray,
        iterations: Int = DEFAULT_ITERATIONS,
        @IntRange(from = 128, to = 256) outputKeyLengthInBits: Int = DEFAULT_SALT_LENGTH shl 3,
        algorithm: String
    ): SecretKey {
        // LogContext.log.w(ITAG, "salt=${salt.toHexStringLE()} iterations=$iterations outputKeyLengthInBits=$outputKeyLengthInBits")

        // PBKDF2WithHmacSHA1
        // PBKDF2WithHmacSHA512
        val secretKeyFactory = SecretKeyFactory.getInstance(algorithm)
        val keySpec = PBEKeySpec(plainPassphrase, salt, iterations, outputKeyLengthInBits)
        return secretKeyFactory.generateSecret(keySpec)
    }

    // =====================================

    //    fun encryptWithSHA512(plainText: String): String {
    //        val preSalt = ByteArray(DEFAULT_PRE_SALT_LENGTH)
    //        // Do NOT seed secureRandom! Automatically seeded from system entropy.
    //        SecureRandom().nextBytes(preSalt)
    //        val preSaltHex = preSalt.toHexStringLE(true, "")
    //
    //        val suffixSalt = ByteArray(DEFAULT_SUFFIX_SALT_LENGTH)
    //        // Do NOT seed secureRandom! Automatically seeded from system entropy.
    //        SecureRandom().nextBytes(suffixSalt)
    //        val suffixSaltHex = suffixSalt.toHexStringLE(true, "")
    //
    // //        LogContext.log.w(ITAG, "encrypt preSaltHex=$preSaltHex suffixSaltHex=$suffixSaltHex")
    //
    //        val onlyHash = generateKeyWithSHA512(plainText.toCharArray(), preSalt, DEFAULT_ITERATIONS).toHexStringLE(true, "")
    // //        LogContext.log.w(ITAG, "encrypt onlyHash=$onlyHash")
    //
    //        return "$preSaltHex$onlyHash$suffixSaltHex"
    //    }

    //    /**
    //     * This method can valid the encrypted hash which is encrypted by [encryptWithSHA512] method.
    //     *
    //     * Usage:
    //     * ```kotlin
    //     * PBKDF2Util.validate("1", "724C135B1AD210C216EF40E9C5230D7BF30FC2FEBCAAB75986EAE1356464DF292486B158ADD7")
    //     * ```
    //     *
    //     * @param plainText The hex plain text should be padded.
    //     */
    //    fun validate(plainText: String, encryptedHash: String): Boolean {
    // //        LogContext.log.w(ITAG, "encryptedHash=encryptedHash")
    //        val preSalt = encryptedHash.substring(0, DEFAULT_PRE_SALT_LENGTH * 2)
    // //        LogContext.log.w(ITAG, "preSalt=$preSalt | ${preSalt.hexToByteArray().toJsonString()}")
    //        val onlyHash = encryptedHash.substring(DEFAULT_PRE_SALT_LENGTH * 2, encryptedHash.length - DEFAULT_SUFFIX_SALT_LENGTH * 2)
    // //        LogContext.log.w(ITAG, "onlyHash=$onlyHash")
    //        val testHash = generateKeyWithSHA512(plainText.toCharArray(), preSalt.hexToByteArray(), DEFAULT_ITERATIONS).toHexStringLE(true, "")
    // //        LogContext.log.w(ITAG, "testHash=$testHash")
    //        return onlyHash == testHash
    //    }
}
