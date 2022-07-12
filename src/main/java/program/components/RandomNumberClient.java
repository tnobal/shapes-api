package program.components;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RandomNumberClient {
    public static int getRandomNumber() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<RandomNumber[]> response = template.getForEntity("https://csrng.net/csrng/csrng.php?min=0&max=100", RandomNumber[].class);
        RandomNumber random = response.getBody()[0];
        return random.getRandom();
    }
}
