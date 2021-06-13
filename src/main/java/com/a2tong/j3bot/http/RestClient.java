package com.a2tong.j3bot.http;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestClient {
    private final HttpClient client;

    private final String baseUrl;


    public RestClient(String baseUrl){
        if(!baseUrl.endsWith("/"))
            baseUrl = baseUrl+"/";
        this.baseUrl = baseUrl;
        client = HttpClient.newBuilder().build();
    }

    public <T> T post(String path,Object data,Class<T> clz) throws Exception {
        return this.post(path,data,clz,null);
    }

    public <T> T post(String path,Object data,Class<T> clz,String resTarget) throws Exception {
        Gson gson = new Gson();

        if(path.startsWith("/"))
            path = path.substring(1);
        String reqJson = gson.toJson(data);
        T result = null;
        try {
            HttpRequest req = buildRestRequest(baseUrl+path,gson.toJson(data));
            HttpResponse<String> httpRes = client.send(req, HttpResponse.BodyHandlers.ofString());

            if(httpRes.statusCode()!=200)
                throw new Exception("rest接口调用失败："+baseUrl+path+"参数"+reqJson);
            String resJson = httpRes.body();
            if(!"".equals(resTarget) && null != resTarget) {
                JsonObject jObj = gson.fromJson(resJson, JsonObject.class);
                result = gson.fromJson(jObj.getAsJsonObject(resTarget),clz);
            }
            else
                result = gson.fromJson(resJson,clz);
        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
            throw new Exception("rest接口调用失败："+baseUrl+path+"参数"+reqJson);
        }
        return result;
    }


    private HttpRequest buildRestRequest(String url,String jsonStr) throws URISyntaxException {
        return HttpRequest.newBuilder().uri(new URI(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonStr))
                .build();
    }

}
