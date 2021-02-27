package ru.onexone.spring_boot_task8;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.onexone.spring_boot_task8.model.User;


public class SpringBootTask8Application {
    static final String URL_USERS = "http://91.241.64.178:7081/api/users/";
    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> response = restTemplate.exchange
                (URL_USERS,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        String.class);

        String resultString = response.getBody();
        HttpHeaders responseHeaders = response.getHeaders();
        String set_cookie = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        System.out.println(resultString);
        System.out.println(responseHeaders);
        System.out.println(set_cookie);
        headers.add(HttpHeaders.COOKIE, set_cookie);
        User newUser = new User(3L, "James", "Brown", (byte) 30);

        response = restTemplate.exchange
                (URL_USERS,
                        HttpMethod.POST,
                        new HttpEntity<>(newUser, headers),
                        String.class);
        String partOne = response.getBody();
        newUser.setName("Thomas");
        newUser.setLastName("Shelby");

        response = restTemplate.exchange
                (URL_USERS,
                        HttpMethod.PUT,
                        new HttpEntity<>(newUser, headers),
                        String.class);
        String partTwo = response.getBody();

        response = restTemplate.exchange
                (URL_USERS + "3",
                        HttpMethod.DELETE,
                        new HttpEntity<>(headers),
                        String.class);
        String partThree = response.getBody();
        System.out.println(partOne + partTwo + partThree);

    }

}
