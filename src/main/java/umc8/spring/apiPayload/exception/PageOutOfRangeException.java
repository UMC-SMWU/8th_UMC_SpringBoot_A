package umc8.spring.apiPayload.exception;

public class PageOutOfRangeException extends RuntimeException {
    public PageOutOfRangeException() {
        super("page는 1 이상의 정수여야 합니다.");
    }
}
