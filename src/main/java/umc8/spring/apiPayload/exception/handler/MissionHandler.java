package umc8.spring.apiPayload.exception.handler;

import umc8.spring.apiPayload.code.BaseErrorCode;
import umc8.spring.apiPayload.exception.GeneralException;

public class MissionHandler extends GeneralException {
    public MissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
