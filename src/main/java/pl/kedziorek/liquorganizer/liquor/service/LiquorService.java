package pl.kedziorek.liquorganizer.liquor.service;

import pl.kedziorek.liquorganizer.liquor.dto.Liquor;
import pl.kedziorek.liquorganizer.liquor.dto.LiquorRequest;

public interface LiquorService {
    Liquor saveOrUpdateLiquor(LiquorRequest liquorRequest);
}
