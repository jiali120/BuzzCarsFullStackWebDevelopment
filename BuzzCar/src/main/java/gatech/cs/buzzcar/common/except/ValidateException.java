package gatech.cs.buzzcar.common.except;


import gatech.cs.buzzcar.common.model.ValidateResult;

import java.util.List;


public class ValidateException extends RuntimeException{


    private final List<ValidateResult> validateResultList;

    public ValidateException(List<ValidateResult> validateResultList){
        super("param validate error");
        this.validateResultList = validateResultList;
    }

    @Override
    public synchronized Throwable fillInStackTrace(){
        return this;
    }

    public List<ValidateResult> getValidateResultList() {
        return validateResultList;
    }
}
