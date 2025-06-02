package umc.spring.service.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.region.RegionRepository;
import umc.spring.repository.store.StoreRepository;
import umc.spring.web.dto.store.StoreRequestDto;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final RegionRepository regionRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Store createStore(StoreRequestDto.CreateDto request) {
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.REGION_NOT_FOUND));

        Store store = StoreConverter.toStore(request, region);
        return storeRepository.save(store);
    }
}
