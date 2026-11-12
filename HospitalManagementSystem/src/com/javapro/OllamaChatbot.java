
package com.javapro;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class OllamaChatbot {

    public static void startChat(Scanner sc) {

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        JSONArray messages = new JSONArray();

        messages.put(new JSONObject()
                .put("role", "system")
                .put("content",
                        "You are a helpful hospital information assistant. " +
                        "Answer questions about hospital services, patient registration, " +
                        "appointments, doctors and billing. " +
                        "Do not diagnose diseases or recommend medicines. " +
                        "For emergencies, advise contacting emergency services. " +
                        "Do not invent hospital-specific information."));

        System.out.println("\n===== AI HOSPITAL CHATBOT =====");
        System.out.println("Ask me anything about hospital services.");
        System.out.println("Type 'exit' to return to the main menu.");

        while (true) {

            System.out.print("\nYou: ");
            String question = sc.nextLine().trim();

            if (question.equalsIgnoreCase("exit")) {
                System.out.println("Bot: Thank you! Have a nice day.");
                break;
            }

            if (question.isEmpty()) {
                continue;
            }

            try {

                messages.put(new JSONObject()
                        .put("role", "user")
                        .put("content", question));

                JSONObject requestBody = new JSONObject()
                        .put("model", "llama3.2:1b")
                        .put("messages", messages)
                        .put("stream", false);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:11434/api/chat"))
                        .header("Content-Type", "application/json")
                        .timeout(Duration.ofSeconds(120))
                        .POST(HttpRequest.BodyPublishers.ofString(
                                requestBody.toString()))
                        .build();

                HttpResponse<String> response = client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200) {
                    System.out.println("Bot: AI service error.");
                    messages.remove(messages.length() - 1);
                    continue;
                }

                JSONObject result = new JSONObject(response.body());

                String answer = result.getJSONObject("message")
                        .getString("content");

                System.out.println("\nBot: " + answer);

                messages.put(new JSONObject()
                        .put("role", "assistant")
                        .put("content", answer));
            } catch (Exception e) {

                messages.remove(messages.length() - 1);

                System.out.println(
                    "Bot: Unable to connect to AI. Please check whether Ollama is running."
                );

                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

