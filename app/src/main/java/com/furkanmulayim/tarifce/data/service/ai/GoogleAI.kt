package com.furkanmulayim.tarifce.data.service.ai

import com.furkanmulayim.tarifce.config.AiAPIConfig
import com.google.ai.client.generativeai.GenerativeModel

class GoogleAI() {
    private var key = AiAPIConfig().googleAiApiKey
    private val modelName:String = "models/gemini-pro"
    private val generativeModel = GenerativeModel(modelName = modelName, apiKey = key)

    suspend fun generateResponse(text: String): String {
        return generativeModel.generateContent(text).text
            ?: throw Exception("Empty response from AI")
    }
}