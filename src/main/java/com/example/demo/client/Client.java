package com.example.demo.client;

import okhttp3.*;
import java.io.IOException;

public class Client {

    private static final String ENDPOINT = "http://localhost:8080/api/jokes";

    public static void main(String[] args) {
        createJoke("Šel ftip a zakopl o ftip.", 1);
        getJoke(1L);
        updateJokeHumourRatio(1L, 5);
        getJoke(1L);
        deleteJoke(1L);
        getJoke(1L);
    }

    public static void createJoke(String text, int humourRatio) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"text\": \"" + text + "\", \"humourRatio\": " + humourRatio + "}");
        Request request = new Request.Builder()
                .url(ENDPOINT + "/new")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            System.out.println("Joke has been created: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void getJoke(Long id) {
        OkHttpClient client = new OkHttpClient();

        String url = ENDPOINT + "/" + id;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            String responseBody = response.body().string();
            System.out.println("Joke details: " + responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateJokeHumourRatio(Long id, int humourRatio) {
        OkHttpClient client = new OkHttpClient();

        String url = ENDPOINT + "/update";

        String json = "{\"id\": " + id + ", \"humourRatio\": " + humourRatio + "}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            String responseBody = response.body().string();
            System.out.println("Joke has been updated: " + responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteJoke(Long id) {
        OkHttpClient client = new OkHttpClient();

        String url = ENDPOINT + "/" + id;

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            String responseBody = response.body().string();
            System.out.println("Joke has been deleted: " + responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
