package wolox.training.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Isbn {

    private String url;

    private String key;

    private String title;

    private String subtitle;

    private List<Authors> authors;

    @JsonProperty("number_of_pages")
    private Integer numberOfPages;

    private String pagination;

    @JsonProperty("publish_date")
    private String publishDate;

    private Identifiers identifiers;

    private Classifications classifications;

    private List<Publishers> publishers;

    @JsonProperty("publishers_places")
    private List<PublishPlaces> publishersPlaces;

    private List<Subjects> subjects;

    private Cover cover;

}
