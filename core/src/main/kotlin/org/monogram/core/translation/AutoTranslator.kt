package org.monogram.core.translation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import java.net.URL

object AutoTranslator {

    suspend fun translate(
        text: String,
        targetLang: String = "en"
    ): String {

        return withContext(Dispatchers.IO) {

            try {

                val encoded =
                    URLEncoder.encode(text, "UTF-8")

                val url =
                    "https://translate.googleapis.com/translate_a/single" +
                    "?client=gtx&sl=auto&tl=$targetLang&dt=t&q=$encoded"

                val response =
                    URL(url).readText()

                parseTranslation(response)

            } catch (e: Exception) {

                text

            }

        }

    }

    private fun parseTranslation(
        response: String
    ): String {

        return try {

            response
                .split("\"")[1]

        } catch (e: Exception) {

            ""

        }

    }

}
