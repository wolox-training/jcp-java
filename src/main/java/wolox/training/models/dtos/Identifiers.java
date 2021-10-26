package wolox.training.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Identifiers {

    @JsonProperty("librarything")
    private List<String> libraryThing;

    @JsonProperty("goodreads")
    private List<String> goodreads;

    @JsonProperty("isbn_10")
    private List<String> isbnTen;

    @JsonProperty("lccn")
    private List<String> lcCn;

    @JsonProperty("openlibrary")
    private List<String> openLibrary;

}
