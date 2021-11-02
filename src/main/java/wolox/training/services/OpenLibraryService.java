package wolox.training.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wolox.training.models.dtos.BookDTO;

@Service
public class OpenLibraryService implements IOpenLibraryService {

    @Override
    public String bookInfo(String isbn) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(
                "https://openlibrary.org/api/books?bibkeys=ISBN:" + isbn + "&format=json&jscmd=data", String.class);
    }
}
