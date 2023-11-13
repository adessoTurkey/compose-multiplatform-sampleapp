package com.example.moveeapp_compose_kmm.data.account.favorite

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlinx.serialization.json.Json

class AccountStateResponseTest {

    @Test
    fun `rated property should be deserialized as null when rated property is a boolean`() {
        val input = """
            {
                "favorite": true,
                "id": 1,
                "rated": false,
                "watchlist": null
            }
        """.trimIndent()

        val json = Json {
            ignoreUnknownKeys = true
        }

        val accountStateResponseModel = json.decodeFromString<AccountStateResponseModel>(input)

        assertNull(accountStateResponseModel.rated)
    }

    @Test
    fun `rated property should be deserialized as float when rated property is a number`() {
        val input = """
            {
                "favorite": true,
                "id": 1,
                "rated": { "value": 5 },
                "watchlist": null
            }
        """.trimIndent()

        val json = Json {
            ignoreUnknownKeys = true
        }

        val accountStateResponseModel = json.decodeFromString<AccountStateResponseModel>(input)

        assertEquals(5.0f, accountStateResponseModel.rated)
    }
}