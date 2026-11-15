// @rezliant RZ-E12671E1 · 2026-11-15 — Eliminates trust-all SSL bypass preventing MITM attacks
// File deleted - insecure trust-all SSL implementation removed
// Callers must migrate to SSLSocketFactory.getDefault() or SSLContext.getDefault().getSocketFactory()
// for proper certificate validation using platform trust store

/*
 * @rezliant-change-log:start
 * RZ-E12671E1 · 2026-11-15 · Trust-all X509TrustManager bypass in SkipSSLCheckSocketFactory
 * Change: Deleted entire SkipSSLCheckSocketFactory class containing no-op certificate validation
 * Benefit: Eliminates trust-all SSL bypass preventing MITM attacks
 * Scope: util/src/main/java/org/killbill/billing/util/security/shiro/realm/SkipSSLCheckSocketFactory.java
 * 
 * Rezliant remediation history: 1 total · 1 most recent shown
 * @rezliant-change-log:end
 */