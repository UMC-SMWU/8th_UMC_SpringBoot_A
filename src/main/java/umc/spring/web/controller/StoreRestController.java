package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.domain.Store;
import umc.spring.service.store.StoreCommandService;
import umc.spring.web.dto.store.StoreRequestDto;
import umc.spring.web.dto.store.StoreResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDto.CreateResultDto> createStore(@RequestBody @Valid StoreRequestDto.CreateDto createDto) {
        Store store = storeCommandService.createStore(createDto);

        StoreResponseDto.CreateResultDto createResultDto = new StoreResponseDto.CreateResultDto().builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .createAt(store.getCreatedAt())
                .build();

        return ApiResponse.onSuccess(createResultDto);
    }
}
