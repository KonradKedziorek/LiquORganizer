package pl.kedziorek.liquorganizer.liquor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kedziorek.liquorganizer.liquor.LiquorType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LiquorRequest {
    private String id;

    @NotBlank(message = "Name is mandatory!")
    private String name;

    @Enumerated(EnumType.STRING)
    private LiquorType liquorType;

    @NotBlank(message = "Description is mandatory!")
    private String description;

    @NotBlank(message = "Creation process description is mandatory!")
    private String creationProcess;

    @NotBlank(message = "Alcohol content is mandatory!")
    private String alcoholContent;

    @NotBlank(message = "Minimum price is mandatory!")
    private String minPrice;

    @NotBlank(message = "Maximum price is mandatory!")
    private String maxPrice;

    @NotBlank(message = "Country of origin is mandatory!")
    private String countryOfOrigin;

    private Boolean vegeFriendly;

    @NotBlank(message = "Location info is mandatory!")
    private String locationInfo;

    private String additionalInformations;
}

