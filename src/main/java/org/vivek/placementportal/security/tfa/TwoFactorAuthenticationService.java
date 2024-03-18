package org.vivek.placementportal.security.tfa;

import dev.samstevens.totp.code.*;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import dev.samstevens.totp.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TwoFactorAuthenticationService {
    public String genrateNewSecret(){
        return new DefaultSecretGenerator().generate();
    }

    public String generateQRCodeImgURI(String secret){
        QrData qrData = new QrData.Builder()
                .label("DDU Placement Portal")
                .secret(secret)
                .issuer("DDU")
                .algorithm(HashingAlgorithm.SHA256)
                .digits(6)
                .period(180)
                .build();
        QrGenerator qrGenerator = new ZxingPngQrGenerator();
        byte[] imageData = new byte[0];
        try{
            imageData = qrGenerator.generate(qrData);
        }
        catch (QrGenerationException e){
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return Utils.getDataUriForImage(imageData, qrGenerator.getImageMimeType());
    }

    public boolean isOTPValid(String secret, String code){
        TimeProvider timeProvider = new SystemTimeProvider();
        CodeGenerator codeGenerator = new DefaultCodeGenerator();
        CodeVerifier codeVerifier = new DefaultCodeVerifier(codeGenerator, timeProvider);
        return codeVerifier.isValidCode(secret, code);
    }

    public boolean isOTPNotValid(String secret, String code){
        return !this.isOTPValid(secret, code);
    }
}
