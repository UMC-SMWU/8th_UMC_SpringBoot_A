package umc.spring.service.store;

import umc.spring.domain.Store;
import umc.spring.web.dto.store.StoreRequestDto;

public interface StoreCommandService {
    public Store createStore(StoreRequestDto.CreateDto request);
}
