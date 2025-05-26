package umc8.spring.apiPayload.exception.handler;

import umc8.spring.apiPayload.code.BaseErrorCode;
import umc8.spring.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
