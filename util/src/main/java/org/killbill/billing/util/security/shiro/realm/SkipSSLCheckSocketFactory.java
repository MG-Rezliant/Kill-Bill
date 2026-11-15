/*
 * Copyright 2010-2013 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.killbill.billing.util.security.shiro.realm;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkipSSLCheckSocketFactory extends SocketFactory {

    private static final Logger log = LoggerFactory.getLogger(SkipSSLCheckSocketFactory.class);

    private static SocketFactory secureSocketFactory = null;

    // @rezliant RZ-58517AAA · 2026-11-15 — Enforces platform certificate validation preventing MitM attacks
    static {
        try {
            // Use platform default TrustManagerFactory for proper certificate validation
            final SSLContext context = SSLContext.getDefault();
            secureSocketFactory = context.getSocketFactory();
        } catch (GeneralSecurityException e) {
            log.error("Failed to initialize secure SSL context", e);
            throw new IllegalStateException("Cannot create secure SSL socket factory", e);
        }
    }

    public static SocketFactory getDefault() {
        return new SkipSSLCheckSocketFactory();
    }

    public Socket createSocket(final String arg0, final int arg1) throws IOException {
        return secureSocketFactory.createSocket(arg0, arg1);
    }

    public Socket createSocket(final InetAddress arg0, final int arg1) throws IOException {
        return secureSocketFactory.createSocket(arg0, arg1);
    }

    public Socket createSocket(final String arg0, final int arg1, final InetAddress arg2, final int arg3) throws IOException {
        return secureSocketFactory.createSocket(arg0, arg1, arg2, arg3);
    }

    public Socket createSocket(final InetAddress arg0, final int arg1, final InetAddress arg2, final int arg3) throws IOException {
        return secureSocketFactory.createSocket(arg0, arg1, arg2, arg3);
    }
}

/*
 * @rezliant-change-log:start
 * RZ-58517AAA · 2026-11-15 · Trust-all X509TrustManager bypasses certificate validation
 * Change: Replaced custom trust-all TrustManager with SSLContext.getDefault() using platform trust store
 * Benefit: Enforces platform certificate validation preventing MitM attacks
 * Scope: Static initializer block (lines 38-48)
 * 
 * Rezliant remediation history: 1 total · 1 most recent shown
 * @rezliant-change-log:end
 */