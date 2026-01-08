package com.qiyuan;


import org.junit.jupiter.api.Test;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class IPTest {
    @Test
    public void test() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入查询IP(1.2.3.4):");
        String ip = sc.nextLine();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://ip.cn/ip/" + ip + ".html"))
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36 Edg/143.0.0.0")
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String html = response.body();
//        <meta name="description" content="ip.cn提供IP地址免费在线查询，39.178.34.117归属地为：中国 江西 九江 濂溪 移动，提供精准的IP地址归属地查询服务，帮助您了解IP地址位置信息。">
//        第三个 " 所在的位置
        int descriptionMetaLocation = html.indexOf("归属地为：") + "归属地为：".length();
//            第四个 " 所在的位置
        int tp = descriptionMetaLocation;
        while (!"，".equals(html.charAt(tp) + "")) {
            tp++;
        }
//        System.out.println(descriptionMetaLocation + ":" + tp);
        System.out.println("归属地" + html.substring(descriptionMetaLocation, tp));


    }
}
