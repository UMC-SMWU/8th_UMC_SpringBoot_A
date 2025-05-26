package umc8.spring.apiPayload.exception.handler;

import umc8.spring.apiPayload.code.BaseErrorCode;
import umc8.spring.apiPayload.exception.GeneralException;

public class RegionHandler extends GeneralException {
    public RegionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
