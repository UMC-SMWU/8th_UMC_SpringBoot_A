package umc.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 멤버 관련 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수입니다."),

    FOOD_CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "FOODCATEGORY4001", "음식 카테고리가 없습니다"),


    // 지역 관련 에러
    REGION_NOT_FOUND(HttpStatus.BAD_REQUEST, "REGION4001", "지역이 없습니다."),

    // 가게 관련 에러
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "STORE4001", "가게가 없습니다."),


    // 미션 관련 에러
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION4001", "미션이 없습니다."),

    // AUTH003 - 액세스 토큰을 주쇼
    // AUTH004 - 액세스 토큰 만료
    // AUTH006 - 액세스 토큰 모양이 이상하다
    MISSING_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, "AUTH003", "액세스 토큰이 없습니다."),
    EXPIRED_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, "AUTH004",  "액세스 토큰이 만료되었습니다."),
    MALFORMED_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, "AUTH006", "액세스 토큰 모양이 이상합니다."),
//    MAL

    // 액세스 관련 에러




    // 예시
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
