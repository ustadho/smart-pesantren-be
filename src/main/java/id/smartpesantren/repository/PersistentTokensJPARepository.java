package id.smartpesantren.repository;
import id.smartpesantren.entity.PersistentLogins;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@Transactional
public class PersistentTokensJPARepository implements PersistentTokenRepository{
    @Autowired
    private PersistentLoginsRepository persistentLoginRepository;
    private static final Logger logger = LoggerFactory.getLogger(PersistentTokensJPARepository.class);

    @Override
    public void createNewToken(org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken token) {
        logger.info("Membuat token baru untuk user: {}", token.getUsername()); // Tambahkan logging

        PersistentLogins persistentLogin = new PersistentLogins(token.getSeries(), token.getUsername(), token.getTokenValue(), token.getDate()); // Baris yang ingin di-debug

        logger.info("Token berhasil dibuat: {}", persistentLogin.getSeries()); // Tambahkan logging setelah pembuatan objek

        persistentLoginRepository.save(persistentLogin);
    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        PersistentLogins persistentLogin = persistentLoginRepository.findBySeries(seriesId);
        if (persistentLogin != null) {
            persistentLogin.setToken(tokenValue);
            persistentLogin.setLastUsed(lastUsed);
            persistentLoginRepository.save(persistentLogin);
        }
    }

    @Override
    public org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken getTokenForSeries(String seriesId) {
        PersistentLogins persistentLogin = persistentLoginRepository.findBySeries(seriesId);
        if (persistentLogin != null) {
            return new org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken(persistentLogin.getSeries(), persistentLogin.getUsername(), persistentLogin.getToken(), persistentLogin.getLastUsed());
        }
        return null;
    }

    @Override
    public void removeUserTokens(String username) {
        persistentLoginRepository.deleteByUsername(username);
    }
}
