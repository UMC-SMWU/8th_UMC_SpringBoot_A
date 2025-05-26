package umc8.spring.apiPayload.exception.handler;

import umc8.spring.apiPayload.code.BaseErrorCode;
import umc8.spring.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
