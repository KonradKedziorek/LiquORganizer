package pl.kedziorek.liquorganizer.liquor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.kedziorek.liquorganizer.config.exception.ResourceNotFoundException;
import pl.kedziorek.liquorganizer.liquor.dto.Liquor;
import pl.kedziorek.liquorganizer.liquor.dto.LiquorRequest;
import pl.kedziorek.liquorganizer.liquor.repository.LiquorRepo;
import pl.kedziorek.liquorganizer.utils.CustomConverter;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LiquorServiceImpl implements LiquorService {
    private final LiquorRepo liquorRepo;
    @Override
    public Liquor saveOrUpdateLiquor(LiquorRequest liquorRequest) {
        if (Objects.equals(liquorRequest.getId(), "")) {
            return saveLiquor(liquorRequest);
        }
        return editLiquor(liquorRequest);
    }

    private Liquor editLiquor(LiquorRequest liquorRequest) {
        Liquor liquor = liquorRepo.findById(liquorRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Liquor with id '" + liquorRequest.getId() + "' not found in database")
                );

        log.info("Editing liquor (name = {})", liquorRequest.getName());
        return liquorRepo.save(changeProperties(liquorRequest, liquor));
    }

    private Liquor saveLiquor(LiquorRequest liquorRequest) {
        log.info("Saving new liquor (name = {})", liquorRequest.getName());
        return liquorRepo.save(Liquor.mapToLiquor(liquorRequest));
    }

    private Liquor changeProperties(LiquorRequest liquorRequest, Liquor liquor) {
        liquor.setName(liquorRequest.getName());
        liquor.setLiquorType(liquorRequest.getLiquorType());
        liquor.setDescription(liquorRequest.getDescription());
        liquor.setCreationProcess(liquorRequest.getCreationProcess());
        liquor.setAlcoholContent(CustomConverter.convertToDouble(liquorRequest.getAlcoholContent()));
        liquor.setMinPrice(CustomConverter.convertToDouble(liquorRequest.getMinPrice()));
        liquor.setMaxPrice(CustomConverter.convertToDouble(liquorRequest.getMaxPrice()));
        liquor.setCountryOfOrigin(liquorRequest.getCountryOfOrigin());
        liquor.setVegeFriendly(liquorRequest.getVegeFriendly());
        liquor.setLocationInfo(liquorRequest.getLocationInfo());
        liquor.setAdditionalInformations(liquorRequest.getAdditionalInformations());
        liquor.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        liquor.setModifiedAt(LocalDateTime.now());
        return liquor;
    }
}
