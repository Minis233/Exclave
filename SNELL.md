# Snell (personal build, v3–v6)

- App: `feature/snell-support`
- Core: https://github.com/Minis233/exclave-core/tree/feature/snell-support

Protocol stack: [SagerNet/sing-snell](https://github.com/SagerNet/sing-snell) (same as sing-box testing).

## Versions

| Version | Backend | Notes |
|---------|---------|-------|
| 3–5 | snellv4 | AES-128-GCM |
| **6** | snellv6 | ChaCha20-Poly1305, deployment-unique framing |

v6 modes: `default` | `unshaped` | `unsafe-raw`

## Config JSON

```json
{
  "protocol": "snell",
  "settings": {
    "address": "1.2.3.4",
    "port": 44046,
    "psk": "your-psk",
    "obfs": "tls",
    "obfsHost": "www.bing.com",
    "version": 6,
    "reuse": true,
    "mode": "default"
  }
}
```

Share link:

```
snell://psk@host:port?version=6&obfs=tls&obfs-host=www.bing.com&mode=default&reuse=1#name
```

## Build

CI checks out `Minis233/exclave-core@feature/snell-support` into `./exclave-core`.

```
replace github.com/exclavenetwork/exclave-core/v5 => ../../exclave-core
```
