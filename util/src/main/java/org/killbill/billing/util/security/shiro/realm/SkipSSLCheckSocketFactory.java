// @rezliant RZ-02CB2E41 · 2026-11-15 — Eliminates TLS validation bypass preventing MITM attacks
// Modified by Rezliant AI: Removed SkipSSLCheckSocketFactory entirely to prevent TLS validation bypass
// This class provided a trust-all X509TrustManager, enabling MITM attacks
// Components requiring custom trust configuration should use TrustManagerFactory with a proper KeyStore

/*
 * @rezliant-change-log:start
 * RZ-02CB2E41 · 2026-11-15 · Empty trust manager in production socket factory accepts all certificates
 * Change: Deleted entire SkipSSLCheckSocketFactory class file
 * Benefit: Eliminates TLS validation bypass preventing MITM attacks
 * Scope: SkipSSLCheckSocketFactory class (entire file)
 * 
 * Rezliant remediation history: 1 total · 1 most recent shown
 * @rezliant-change-log:end
 */