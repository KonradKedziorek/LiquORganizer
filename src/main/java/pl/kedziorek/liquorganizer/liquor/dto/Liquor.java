package pl.kedziorek.liquorganizer.liquor.dto;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.kedziorek.liquorganizer.comment.dto.Comment;
import pl.kedziorek.liquorganizer.liquor.LiquorType;
import pl.kedziorek.liquorganizer.utils.CustomConverter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "liquor")
public class Liquor {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank(message = "Name is mandatory!")
    private String name;

    @Enumerated(EnumType.STRING)
    private LiquorType liquorType;

    @NotBlank(message = "Description is mandatory!")
    private String description;

    @NotBlank(message = "Creation process description is mandatory!")
    private String creationProcess;

    @NotBlank(message = "Alcohol content is mandatory!")
    private Double alcoholContent;   //zawartość alkoholu

    @NotBlank(message = "Minimum price is mandatory!")
    private Double minPrice;

    @NotBlank(message = "Maximum price is mandatory!")
    private Double maxPrice;

    @NotBlank(message = "Country of origin is mandatory!")
    private String countryOfOrigin;  //kraj pochodzenia

    private Boolean vegeFriendly;

    @NotBlank(message = "Location info is mandatory!")
    private String locationInfo;

    private String additionalInformations;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private String modifiedBy;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private Boolean deleted;

    @OneToMany(mappedBy = "liquor")
    private List<Comment> comments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Liquor liquor = (Liquor) o;
        return Objects.equals(id, liquor.id) &&
                Objects.equals(name, liquor.name) &&
                liquorType == liquor.liquorType &&
                Objects.equals(description, liquor.description) &&
                Objects.equals(creationProcess, liquor.creationProcess) &&
                Objects.equals(alcoholContent, liquor.alcoholContent) &&
                Objects.equals(minPrice, liquor.minPrice) &&
                Objects.equals(maxPrice, liquor.maxPrice) &&
                Objects.equals(countryOfOrigin, liquor.countryOfOrigin) &&
                Objects.equals(vegeFriendly, liquor.vegeFriendly) &&
                Objects.equals(locationInfo, liquor.locationInfo) &&
                Objects.equals(additionalInformations, liquor.additionalInformations) &&
                Objects.equals(createdBy, liquor.createdBy) &&
                Objects.equals(createdAt, liquor.createdAt) &&
                Objects.equals(modifiedBy, liquor.modifiedBy) &&
                Objects.equals(modifiedAt, liquor.modifiedAt) &&
                Objects.equals(deleted, liquor.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                name,
                liquorType,
                description,
                creationProcess,
                alcoholContent,
                minPrice,
                maxPrice,
                countryOfOrigin,
                vegeFriendly,
                locationInfo,
                additionalInformations,
                createdBy,
                createdAt,
                modifiedBy,
                modifiedAt,
                deleted);
    }

    public static Liquor mapToLiquor(LiquorRequest liquorRequest) {
        return Liquor.builder()
                .id(Objects.equals(liquorRequest.getId(), "") ? UUID.randomUUID() : UUID.fromString(liquorRequest.getId()))
                .name(liquorRequest.getName())
                .liquorType(liquorRequest.getLiquorType())
                .description(liquorRequest.getDescription())
                .creationProcess(liquorRequest.getCreationProcess())
                .alcoholContent(CustomConverter.convertToDouble(liquorRequest.getAlcoholContent()))
                .minPrice(CustomConverter.convertToDouble(liquorRequest.getMinPrice()))
                .maxPrice(CustomConverter.convertToDouble(liquorRequest.getMaxPrice()))
                .countryOfOrigin(liquorRequest.getCountryOfOrigin())
                .vegeFriendly(liquorRequest.getVegeFriendly())
                .locationInfo(liquorRequest.getLocationInfo())
                .additionalInformations(liquorRequest.getAdditionalInformations())
                .createdBy(SecurityContextHolder.getContext().getAuthentication().getName())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
