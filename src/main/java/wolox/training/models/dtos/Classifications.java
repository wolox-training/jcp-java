package wolox.training.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classifications {

    @JsonProperty("lc_classifications")
    private List<String> lcClassifications;

    @JsonProperty("dewey_decimal_class")
    private List<String> deweyDecimalClass;

}
