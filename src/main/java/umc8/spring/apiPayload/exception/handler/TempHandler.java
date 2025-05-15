package umc8.spring.apiPayload.exception.handler;

import umc8.spring.apiPayload.code.BaseCode;
import umc8.spring.apiPayload.code.BaseErrorCode;
import umc8.spring.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
