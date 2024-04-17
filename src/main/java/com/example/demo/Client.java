package com.example.demo;

import okhttp3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {

    private static final String ENDPOINT = "http://localhost:8080/api/users";

    public static void main(String[] args) {
        registerUser("a@a.a", "a");
        getUserByEmail("a@a.a");
        updateUser("a@a.a", "b");
        getUserByEmail("a@a.a");
        deleteUser("a@a.a");
        getUserByEmail("a@a.a");
    }

    public static void registerUser(String email, String password) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}");
        Request request = new Request.Builder()
                .url(ENDPOINT + "/register")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            System.out.println("User registered successfully: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void getUserByEmail(String email) {
        OkHttpClient client = new OkHttpClient();

        String url = ENDPOINT + "/" + email;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            String responseBody = response.body().string();
            System.out.println("User details: " + responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(String email, String newPassword) {
        OkHttpClient client = new OkHttpClient();

        String url = ENDPOINT + "/" + email;

        String json = "{\"password\": \"" + newPassword + "\"}";
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
            System.out.println("User updated successfully: " + responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(String email) {
        OkHttpClient client = new OkHttpClient();

        String url = ENDPOINT + "/" + email;

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            System.out.println("User deleted successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
