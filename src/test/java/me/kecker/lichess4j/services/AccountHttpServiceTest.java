package me.kecker.lichess4j.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import me.kecker.lichess4j.http.base.HttpBaseClient;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.model.account.Account;
import me.kecker.lichess4j.model.account.Email;
import me.kecker.lichess4j.model.account.KidModeStatusWrapper;
import me.kecker.lichess4j.model.account.Preferences;
import me.kecker.lichess4j.model.account.PreferencesWrapper;
import me.kecker.lichess4j.model.enums.KidModeStatus;
import me.kecker.lichess4j.test.providers.AccountTestProvider;
import me.kecker.lichess4j.test.providers.EmailTestProvider;
import me.kecker.lichess4j.test.providers.KidModeStatusTestProvider;
import me.kecker.lichess4j.test.providers.PreferencesTestProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountHttpServiceTest {

    private static final String ACCOUNT_URL = "account";
    private static final String EMAIL_URL = "email";
    private static final String PREFERENCES_URL = "preferences";
    private static final String KID_MODE_STATUS_URL = "kid";

    private AccountHttpService objectUnderTest;

    @Mock
    private HttpBaseClient httpBaseClientMock;

    @Before
    public void setup() throws IOException {
        this.objectUnderTest = new AccountHttpService(httpBaseClientMock);
    }

    @Test
    public void getAccountInfo_happyDay_returnsAccountInfo() throws IOException,
            InterruptedException, IllegalStatusCodeException {

        when(httpBaseClientMock.get(ACCOUNT_URL, Account.class)).thenReturn(AccountTestProvider
                .getAccount());

        Account result = this.objectUnderTest.getAccountInfo();

        assertNotNull(result);
        assertThat(result).isEqualTo(AccountTestProvider.getAccount())
                .usingRecursiveComparison();

    }

    @Test
    public void getEmail_happyDay_returnsEmail() throws IOException, InterruptedException,
            IllegalStatusCodeException {

        when(httpBaseClientMock.get(EMAIL_URL, Email.class)).thenReturn(EmailTestProvider
                .getEmail());

        String result = this.objectUnderTest.getEmail();

        assertNotNull(result);
        assertThat(result).isEqualTo(EmailTestProvider.EMAIL);

    }

    @Test
    public void getPreferences_happyDay_returnsPreferences() throws IOException,
            InterruptedException, IllegalStatusCodeException {

        when(httpBaseClientMock.get(PREFERENCES_URL, PreferencesWrapper.class)).thenReturn(
                PreferencesTestProvider.getPreferencesWrapper());

        Preferences result = this.objectUnderTest.getPreferenes();

        assertNotNull(result);
        assertThat(result).isEqualTo(PreferencesTestProvider.getPreferences());

    }

    @Test
    public void getKidModeStatus_happyDay_returnKidModeStatus() throws IOException,
            InterruptedException, IllegalStatusCodeException {

        when(httpBaseClientMock.get(KID_MODE_STATUS_URL, KidModeStatusWrapper.class)).thenReturn(
                KidModeStatusTestProvider.getKidModeStatusWrapper());

        KidModeStatus result = this.objectUnderTest.getKidModeStatus();

        assertNotNull(result);
        assertThat(result).isEqualTo(KidModeStatusTestProvider.getKidModeStatus());

    }
}
