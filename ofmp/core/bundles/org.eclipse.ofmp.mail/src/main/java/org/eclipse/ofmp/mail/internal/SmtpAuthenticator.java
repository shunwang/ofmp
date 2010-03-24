package org.eclipse.ofmp.mail.internal;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuthenticator extends Authenticator
{
    private final String username;
    private final String password;

    public SmtpAuthenticator(String username, String password)
    {
        super();
        this.username = username;
        this.password = password;
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(username, password);
    }

}
