package com.aminenurgynk.service;



import com.aminenurgynk.dto.request.ActivateRequestDto;
import com.aminenurgynk.dto.request.LoginRequestDto;
import com.aminenurgynk.dto.request.RegisterRequestDto;
import com.aminenurgynk.dto.response.RegisterResponseDto;
import com.aminenurgynk.expection.AuthManagerException;
import com.aminenurgynk.expection.ErrorType;
import com.aminenurgynk.mapper.IAuthMapper;
import com.aminenurgynk.repository.IAuthRepository;
import com.aminenurgynk.repository.entity.Auth;
import com.aminenurgynk.repository.enums.EStatus;
import com.aminenurgynk.utility.CodeGenerator;
import com.aminenurgynk.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth , Long> {

    private final IAuthRepository AuthRepository;

    public AuthService(IAuthRepository authRepository) {
        super(authRepository);
        AuthRepository = authRepository;
    }


    public RegisterResponseDto register(RegisterRequestDto dto){
        Auth auth = IAuthMapper.INTANCE.toAuth(dto);
        auth.setActivationCode(CodeGenerator.generateCode());
        save(auth);

        RegisterResponseDto responseDto = IAuthMapper.INTANCE.toRegisterResponseDto(auth);
        return responseDto;
    }

    public Boolean login(LoginRequestDto dto){
        Optional<Auth> optionalAuth = AuthRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if(optionalAuth.isEmpty()){
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        if(!optionalAuth.get().getStatus().equals(EStatus.ACTIVE)){
            throw new AuthManagerException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }
        return true;
    }


    public Boolean activateStatus(ActivateRequestDto dto){
        Optional<Auth> optionalAuth = findById(dto.getId());

        if(optionalAuth.isEmpty()){
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        if(optionalAuth.get().getStatus().equals(EStatus.ACTIVE)){
            throw new AuthManagerException(ErrorType.ALREADY_ACTIVE);
        }
        if(dto.getActivationCode().equals(optionalAuth.get().getActivationCode())){
            optionalAuth.get().setStatus(EStatus.ACTIVE);
            update(optionalAuth.get());
            return true;
        }else {
            throw new AuthManagerException(ErrorType.INVALID_CODE);
        }
    }
}
